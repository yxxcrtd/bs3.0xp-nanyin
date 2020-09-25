package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfProdLadderDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfProdLadderDetailMapper {
    int insert(InterfProdLadderDetail record);

    int insertSelective(InterfProdLadderDetail record);

    int insertBasicByBasicInfo(InterfProdLadderDetail record);
}