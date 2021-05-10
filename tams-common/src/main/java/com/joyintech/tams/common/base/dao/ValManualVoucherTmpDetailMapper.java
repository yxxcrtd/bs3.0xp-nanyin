package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ValManualVoucherTmpDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface ValManualVoucherTmpDetailMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(ValManualVoucherTmpDetail record);

    int insertSelective(ValManualVoucherTmpDetail record);

    int insertBasicByBasicInfo(ValManualVoucherTmpDetail record);

    ValManualVoucherTmpDetail selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(ValManualVoucherTmpDetail record);

    int updateByPrimaryKey(ValManualVoucherTmpDetail record);
}