package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstFeeinfo;
import org.springframework.stereotype.Repository;

@Repository
public interface MstFeeinfoMapper {
    int deleteByPrimaryKey(String feeId);

    int insert(MstFeeinfo record);

    int insertSelective(MstFeeinfo record);

    int insertBasicByBasicInfo(MstFeeinfo record);

    MstFeeinfo selectByPrimaryKey(String feeId);

    int updateByPrimaryKeySelective(MstFeeinfo record);

    int updateByPrimaryKey(MstFeeinfo record);
}