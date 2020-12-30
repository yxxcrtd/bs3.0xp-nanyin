package com.joyintech.service.report.regulatoryreporting.common.base.dao;

import com.joyintech.service.report.regulatoryreporting.common.base.entity.ShareFilePbcBalanceOrg;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareFilePbcBalanceOrgMapper {
    int insert(ShareFilePbcBalanceOrg record);

    int insertSelective(ShareFilePbcBalanceOrg record);

    int insertBasicByBasicInfo(ShareFilePbcBalanceOrg record);
}