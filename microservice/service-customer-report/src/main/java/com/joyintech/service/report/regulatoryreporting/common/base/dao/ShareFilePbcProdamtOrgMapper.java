package com.joyintech.service.report.regulatoryreporting.common.base.dao;

import com.joyintech.service.report.regulatoryreporting.common.base.entity.ShareFilePbcProdamtOrg;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareFilePbcProdamtOrgMapper {
    int insert(ShareFilePbcProdamtOrg record);

    int insertSelective(ShareFilePbcProdamtOrg record);

    int insertBasicByBasicInfo(ShareFilePbcProdamtOrg record);
}