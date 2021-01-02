package com.joyintech.service.report.regulatoryreporting.common.base.dao;

import com.joyintech.service.report.regulatoryreporting.common.base.entity.ShareFilePbcProdinfoOrg;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareFilePbcProdinfoOrgMapper {
    int insert(ShareFilePbcProdinfoOrg record);

    int insertSelective(ShareFilePbcProdinfoOrg record);

    int insertBasicByBasicInfo(ShareFilePbcProdinfoOrg record);
}