package com.joyintech.service.report.regulatoryreporting.common.base.dao;

import com.joyintech.service.report.regulatoryreporting.common.base.entity.ShareFilePbcBalance;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareFilePbcBalanceMapper {
    int insert(ShareFilePbcBalance record);

    int insertSelective(ShareFilePbcBalance record);

    int insertBasicByBasicInfo(ShareFilePbcBalance record);
}