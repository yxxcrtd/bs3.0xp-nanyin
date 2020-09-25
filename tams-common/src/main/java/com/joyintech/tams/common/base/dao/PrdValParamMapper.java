package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PrdValParam;
import org.springframework.stereotype.Repository;

@Repository
public interface PrdValParamMapper {
    int deleteByPrimaryKey(String finprodId);

    int insert(PrdValParam record);

    int insertSelective(PrdValParam record);

    int insertBasicByBasicInfo(PrdValParam record);

    PrdValParam selectByPrimaryKey(String finprodId);

    int updateByPrimaryKeySelective(PrdValParam record);

    int updateByPrimaryKey(PrdValParam record);
}