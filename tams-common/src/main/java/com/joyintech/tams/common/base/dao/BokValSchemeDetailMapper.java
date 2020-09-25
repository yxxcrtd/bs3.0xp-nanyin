package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokValSchemeDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface BokValSchemeDetailMapper {
    int deleteByPrimaryKey(String schemeDetailId);

    int insert(BokValSchemeDetail record);

    int insertSelective(BokValSchemeDetail record);

    int insertBasicByBasicInfo(BokValSchemeDetail record);

    BokValSchemeDetail selectByPrimaryKey(String schemeDetailId);

    int updateByPrimaryKeySelective(BokValSchemeDetail record);

    int updateByPrimaryKey(BokValSchemeDetail record);
}