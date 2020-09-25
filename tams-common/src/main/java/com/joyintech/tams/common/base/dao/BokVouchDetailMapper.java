package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokVouchDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface BokVouchDetailMapper {
    int deleteByPrimaryKey(String vouchSubnum);

    int insert(BokVouchDetail record);

    int insertSelective(BokVouchDetail record);

    int insertBasicByBasicInfo(BokVouchDetail record);

    BokVouchDetail selectByPrimaryKey(String vouchSubnum);

    int updateByPrimaryKeySelective(BokVouchDetail record);

    int updateByPrimaryKey(BokVouchDetail record);
}