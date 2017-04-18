package net.xinqushi.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.quartz.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import groovyjarjarantlr.Lookahead;
import net.xinqushi.common.exceptions.CommonException;
import net.xinqushi.orm.entity.City;
import net.xinqushi.orm.mapper.CityMapper;
import net.xinqushi.service.ElasticSearchService;
import net.xinqushi.util.ElasticSearchUtil;
@Service
public class ElasticSearchServiceImpl implements ElasticSearchService{
	
	private static Logger logger = LoggerFactory.getLogger(ElasticSearchServiceImpl.class);
	
	private Client client;
	
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
		
	}

	@Override
	public String getIndexById(String indexname, String type, String id) throws CommonException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SearchResponse searcher(QueryBuilder queryBuilder, String indexname, String type, String sort, String order,
			Integer page, Integer pageSize) throws CommonException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long countQuery(QueryBuilder queryBuilder, String indexname, String type) throws CommonException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateIndex(String indexname, String type, String jsondata, String id) throws CommonException {
		// TODO Auto-generated method stub
		
	}
	
}
