package net.xinqushi.service;

import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;

import net.xinqushi.common.exceptions.CommonException;
import net.xinqushi.orm.entity.City;

/**
 * 
 * @author yangli
 * @date 2017年4月16日 - 下午7:09:09
 * @Description:es服务
 */
public interface ElasticSearchService {

	/** 将信息存入es */
	public void putDoc(List<City> cities) throws CommonException;

	/**
	 * 单条索引创建,如果id為空，才用默認id
	 * 
	 * @param indexName
	 * @param type
	 * @param jsondata
	 * @param id
	 *            索引对象单独指定ID
	 * @throws CommonException
	 */
	public void createSingleIndexResponse(String indexName, String type,

			String jsondata, String id) throws CommonException;

	/**
	 * 删除指定索引
	 * 
	 * @param indexname
	 * @param type
	 * @param id
	 * @throws CommonException
	 */
	public void delteIndexResponse(String indexname, String type, String id) throws CommonException;

	/**
	 * 按照查询结果，删除对应索引
	 * 
	 * @param searchHits
	 * @param index
	 * @param type
	 * @throws CommonException
	 */
	public void delteIndexResponse(SearchHit[] searchHits, String index, String type) throws CommonException;
	
	/**
	 * （指定索引）单条查询 根据索引，类型，及其id,查询，返回一个json字符串
	 * @param indexname
	 * @param type
	 * @param id
	 * @return
	 * @throws CommonException
	 */
	public String getIndexById(String indexname, String type, 
				String id) throws CommonException;
	
	/**
	 * 分頁查詢，返回搜索結果SearchResponse
	 * @param queryBuilder
	 * @param indexname
	 * @param type
	 * @param sort
	 * @param order
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws CommonException
	 */
	public SearchResponse searcher(QueryBuilder queryBuilder, String indexname,
				String type, String sort, String order, Integer page, Integer pageSize) throws CommonException;
	
	/**
	 * 根据条件 统计个数
	 */
	public long countQuery(QueryBuilder queryBuilder, String indexname,
				String type) throws CommonException;
	
	/**
	 * 更新对应id 的索引数据
	 */
	public void updateIndex(String indexname, String type, String jsondata,
				String id) throws CommonException;
	
	
	
}
