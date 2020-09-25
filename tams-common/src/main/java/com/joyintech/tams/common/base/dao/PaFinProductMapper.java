package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PaFinProduct;
import org.springframework.stereotype.Repository;

@Repository
public interface PaFinProductMapper {
    int deleteByPrimaryKey(String finprodId);

    int insert(PaFinProduct record);

    int insertSelective(PaFinProduct record);

    int insertBasicByBasicInfo(PaFinProduct record);

    PaFinProduct selectByPrimaryKey(String finprodId);

    int updateByPrimaryKeySelective(PaFinProduct record);

    int updateByPrimaryKey(PaFinProduct record);
}