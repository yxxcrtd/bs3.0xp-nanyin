package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ValManualVoucherTmp;
import org.springframework.stereotype.Repository;

@Repository
public interface ValManualVoucherTmpMapper {
    int deleteByPrimaryKey(String tmpCode);

    int insert(ValManualVoucherTmp record);

    int insertSelective(ValManualVoucherTmp record);

    int insertBasicByBasicInfo(ValManualVoucherTmp record);

    ValManualVoucherTmp selectByPrimaryKey(String tmpCode);

    int updateByPrimaryKeySelective(ValManualVoucherTmp record);

    int updateByPrimaryKey(ValManualVoucherTmp record);
}