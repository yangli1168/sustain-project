package net.xinqushi.orm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.xinqushi.orm.entity.City;

public interface CityMapper extends SqlMapper{
    int deleteByPrimaryKey(Integer id);

    int insert(City record);

    int insertSelective(City record);

    City selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);
    
    /** 返回分页 */
   	List<City> getCityList(@Param("name")String name, @Param("start")Integer start, @Param("end")Integer end);
   	
   	/** 返回单个记录*/
   	City getCityById(@Param("id")Integer id);
   	
   	/** 更新记录*/
   	int updateCityById(City city);
   	
   	/** 按name返回记录数*/
   	int countByName(@Param("name")String name);
   	
	/** 按pinyin返回记录数*/
   	int countByPinyin(@Param("pinyin")String pinyin);
   	
   	List<City> getCityByName(@Param("name") String name);
   	
}