package com.xinqushi.designpattern.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元模式：网站工厂
 * @author yangli
 */
public class WebSiteFactory {
	
	@SuppressWarnings("rawtypes")
	private Map flyWeights = new HashMap<>();
	
	/** 获得网站分类*/
	@SuppressWarnings("unchecked")
	public WebSite getWebSiteCategory(String key){
		if (!flyWeights.containsKey(key)) {
			flyWeights.put(key, new ConcreteWebSite(key));
		}
		return (WebSite) flyWeights.get(key);
	}
	
	/** 获得网站分类总数*/
	public int getWebSiteCount(){
		return flyWeights.size();
	}
}
