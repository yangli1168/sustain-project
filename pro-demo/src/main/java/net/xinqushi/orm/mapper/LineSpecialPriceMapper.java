package net.xinqushi.orm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.xinqushi.orm.entity.LineSpecialPrice;

public interface LineSpecialPriceMapper extends SqlMapper{
    int deleteByPrimaryKey(Integer id);

    int insert(LineSpecialPrice record);

    int insertSelective(LineSpecialPrice record);

    LineSpecialPrice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LineSpecialPrice record);

    int updateByPrimaryKey(LineSpecialPrice record);
    
    List<LineSpecialPrice> getAllLinePricingRules(@Param("now") long now);
}