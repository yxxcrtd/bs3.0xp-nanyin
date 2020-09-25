package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfCmoneymarketfincome;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfCmoneymarketfincomeMapper {
    int deleteByPrimaryKey(String objectId);

    int insert(InterfCmoneymarketfincome record);

    int insertSelective(InterfCmoneymarketfincome record);

    int insertBasicByBasicInfo(InterfCmoneymarketfincome record);

    InterfCmoneymarketfincome selectByPrimaryKey(String objectId);

    int updateByPrimaryKeySelective(InterfCmoneymarketfincome record);

    int updateByPrimaryKey(InterfCmoneymarketfincome record);
}