package com.utoo.orm.mapper;

import com.utoo.orm.entity.CompanyLine;

public interface CompanyLineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CompanyLine record);

    int insertSelective(CompanyLine record);

    CompanyLine selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CompanyLine record);

    int updateByPrimaryKey(CompanyLine record);
}