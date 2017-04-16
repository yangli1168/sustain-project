package net.xinqushi.service;

import java.util.List;

import net.xinqushi.common.exceptions.CommonException;
import net.xinqushi.orm.entity.City;

/**
 * 
 * @author yangli
 * @date 2017年4月16日 - 下午7:09:09
 * @Description:es服务
 */
public interface ElasticSearchService {
	
	/** 将信息存入es*/
	public void putDoc(List<City> cities) throws CommonException;
	
	
}
