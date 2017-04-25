package net.xinqushi.common.constants;

import java.util.List;

import com.alibaba.fastjson.TypeReference;

import net.xinqushi.orm.entity.City;

/**
 * 用于json转换的常量类
 * @author yangli
 * 2017年4月25日-上午9:29:02
 */
public class TypeReferenceConstants {
	
	/** city转换*/
	public static final TypeReference<List<City>> cityTypeRef = new TypeReference<List<City>>() {
	};
	
	
}
