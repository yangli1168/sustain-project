package net.xinqushi.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.count.CountResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import net.xinqushi.common.exceptions.CommonException;
import net.xinqushi.orm.entity.City;
import net.xinqushi.orm.mapper.CityMapper;
import net.xinqushi.service.ElasticSearchService;
import net.xinqushi.util.ElasticSearchUtil;
@Service
public class ElasticSearchServiceImpl implements ElasticSearchService{
	
	private static Logger logger = LoggerFactory.getLogger(ElasticSearchServiceImpl.class);
	
	private Client client = null;
	
	@Autowired
	private CityMapper cityMapper;
	
	@PostConstruct
	public void init(){
		try {
			client = ElasticSearchUtil.getESClient(null, null);
		} catch (Exception e) {
			logger.info("fail to initialize es client");
		}
	}
	
	@PreDestroy
	public void end(){
		try {
			if (null != client) {
				client.close();
			}
		} catch (Exception e) {
			logger.info("fail to close es client");
		}
	}
	
	@Override
	public void putDoc(List<City> cities) throws CommonException {
		try {
			if (null != cities) {
				ElasticSearchUtil.putDoc(cities);
			}
			ElasticSearchUtil.putDoc(cityMapper.getCityList(null, null, null));
			
		} catch (Exception e) {
			logger.info("fail to store the city info to es", e);
			throw new CommonException("城市信息存入es失败");
		}
	}

	@Override
	public void createSingleIndexResponse(String indexName, String type, String jsondata, String id)
			throws CommonException {
		try {
			IndexRequestBuilder requestBuilder = this.client.prepareIndex(indexName, type).setRefresh(true);
			//id判断:id为空时使用默认
			if ((null != id) && (!id.equals(""))) {
				requestBuilder.setId(id);
			}
			//创建
			IndexResponse response = requestBuilder.setSource(jsondata).get();
			long version = response.getVersion();
			boolean created = response.isCreated();
			if (created) {
				logger.info("创建索引: {}:-<<: {} :-<<: {}", indexName, type, jsondata);
			} else {
				logger.info("索引存在,已重复至第{}版本: {}:-<<: {} :-<<: {} :-<<: {}", version, indexName, type, jsondata);
			}
		} catch (Exception e) {
			logger.info("fail to create single index");
			throw new CommonException("创建索引失败");
		}
	}

	@Override
	public void delteIndexResponse(String indexname, String type, String id) throws CommonException {
		try {
			if ((null != id) && (!id.isEmpty())) {
				DeleteResponse response = client.prepareDelete(indexname, type, id).execute().actionGet();
				if (response.isFound()) {
					logger.info("删除索引: {} :-<<: {} :-<<: {}", indexname, type, id);
				} else {
					logger.info("不存在该索引: {} :-<<: {} :-<<: {}", indexname, type, id);
				}
			}
		} catch (Exception e) {
			logger.info("fail to delete single index");
			throw new CommonException("删除索引失败");
		}
	}

	@Override
	public void delteIndexResponse(SearchHit[] searchHits, String index, String type) throws CommonException {
		try {
			logger.info("needs to del {} records", searchHits.length);
			//创建请求
			BulkRequestBuilder bulkRequest = client.prepareBulk().setRefresh(true);
			for (SearchHit searchHit : searchHits) {
				DeleteRequest deleteRequest = new DeleteRequest(index, type, searchHit.getId());
				bulkRequest.add(deleteRequest);
			}
			//执行
			BulkResponse bulkResponse = bulkRequest.execute().actionGet();
			if (bulkResponse.hasFailures()) {
				logger.info(bulkResponse.buildFailureMessage());
			}
		} catch (Exception e) {
			logger.info("fail to delete single index");
			throw new CommonException("删除索引失败");
		}
	}

	@Override
	public String getIndexById(String indexname, String type, String id) throws CommonException {
		try {
			GetResponse response = client.prepareGet(indexname, type, id).execute().actionGet();
			String jsonString = JSON.toJSONString(response.getSource());
			return jsonString;
		} catch (Exception e) {
			logger.info("fail to get single doc");
			throw new CommonException("索引记录失败");
		}
	}
	
	/**
	 * 分页查询
	 * @return
	 * @throws CommonException
	 * 2017年4月19日-上午8:48:24
	 */
	@Override
	public SearchResponse searcher(QueryBuilder queryBuilder, String indexname, String type, String sort, String order,
			Integer page, Integer pageSize) throws CommonException {
		try {
			//创建查询索引
			SearchRequestBuilder searchRequestBuilder = client.prepareSearch(indexname);
			searchRequestBuilder.setTypes(type);
			searchRequestBuilder.setQuery(queryBuilder);
			//分页
			if ((page >0) && (pageSize > 0)) {
				searchRequestBuilder.setFrom(pageSize * (page - 1));
				searchRequestBuilder.setSize(pageSize);
			}
			//sort
			SortOrder sortOrder = SortOrder.ASC;
			if (order.equals("desc")) {
				sortOrder = SortOrder.DESC;
			}
			if ((null != sort) && (!sort.equals(""))) {
				searchRequestBuilder.addSort(sort, sortOrder);
			}
			//按匹配度查询
			searchRequestBuilder.setExplain(true);
			//返回
			SearchResponse response = searchRequestBuilder.execute().actionGet();
			return response;
		} catch (Exception e) {
			logger.info("fail to get docs");
			throw new CommonException("索引记录失败");
		}
	}

	@Override
	public long countQuery(QueryBuilder queryBuilder, String indexname, String type) throws CommonException {
		try {
			CountResponse response = client.prepareCount(indexname).setTypes(type)
										.setQuery(queryBuilder)
										.execute().actionGet();
			return response.getCount();
		} catch (Exception e) {
			logger.info("fail to get docs num");
			throw new CommonException("返回索引记录条数失败");
		}
	}

	@Override
	public void updateIndex(String indexname, String type, String jsondata, String id) throws CommonException {
		try {
			UpdateRequest updateRequest = new UpdateRequest();
			updateRequest.index(indexname);
			updateRequest.type(type);
			updateRequest.id(id);
			updateRequest.doc(jsondata);
			
			UpdateResponse response = client.update(updateRequest).get();
			long version = response.getVersion();
			boolean created = response.isCreated();
			if (created) {
				logger.info("UPSERT operation，已更新索引至第{}版本：{}:-<<:{}:-<<:{}:-<<:{}", version, indexname, type, id, jsondata);
			} else {
				logger.info("已更新索引至第{}版本：{}:-<<:{}:-<<:{}:-<<:{}", version, indexname, type, id, jsondata);
			}
		} catch (Exception e) {
			logger.info("fail to update index");
			throw new CommonException("更新索引失败");
		}
	}
	
}
