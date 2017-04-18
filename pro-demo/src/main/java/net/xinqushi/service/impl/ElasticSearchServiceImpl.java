package net.xinqushi.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xinqushi.common.exceptions.CommonException;
import net.xinqushi.orm.entity.City;
import net.xinqushi.orm.mapper.CityMapper;
import net.xinqushi.service.ElasticSearchService;
import net.xinqushi.util.ElasticSearchUtil;
@Service
public class ElasticSearchServiceImpl implements ElasticSearchService{
	
	private static Logger logger = LoggerFactory.getLogger(ElasticSearchServiceImpl.class);
	
	@Autowired
	private CityMapper cityMapper;
	
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
	
}
