package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PtlManageObj;
import com.joyintech.tams.common.base.entity.PtlManageObjKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PtlManageObjMapper {
    int deleteByPrimaryKey(PtlManageObjKey key);

    int insert(PtlManageObj record);

    int insertSelective(PtlManageObj record);

    int insertBasicByBasicInfo(PtlManageObj record);

    PtlManageObj selectByPrimaryKey(PtlManageObjKey key);

    int updateByPrimaryKeySelective(PtlManageObj record);

    int updateByPrimaryKey(PtlManageObj record);
}