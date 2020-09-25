package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InfCmoneymarketfscarryoverm;
import org.springframework.stereotype.Repository;

@Repository
public interface InfCmoneymarketfscarryovermMapper {
    int deleteByPrimaryKey(String objectId);

    int insert(InfCmoneymarketfscarryoverm record);

    int insertSelective(InfCmoneymarketfscarryoverm record);

    int insertBasicByBasicInfo(InfCmoneymarketfscarryoverm record);

    InfCmoneymarketfscarryoverm selectByPrimaryKey(String objectId);

    int updateByPrimaryKeySelective(InfCmoneymarketfscarryoverm record);

    int updateByPrimaryKey(InfCmoneymarketfscarryoverm record);
}