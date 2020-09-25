package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PtlValParam;
import org.springframework.stereotype.Repository;

@Repository
public interface PtlValParamMapper {
    int deleteByPrimaryKey(String finprodId);

    int insert(PtlValParam record);

    int insertSelective(PtlValParam record);

    int insertBasicByBasicInfo(PtlValParam record);

    PtlValParam selectByPrimaryKey(String finprodId);

    int updateByPrimaryKeySelective(PtlValParam record);

    int updateByPrimaryKey(PtlValParam record);
}