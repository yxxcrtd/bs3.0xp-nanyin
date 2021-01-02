package com.joyintech.service.report.regulatoryreporting.common.base.dao;

import com.joyintech.service.report.regulatoryreporting.common.base.entity.ShareFilePbcProdinfo;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareFilePbcProdinfoMapper {
    int insert(ShareFilePbcProdinfo record);

    int insertSelective(ShareFilePbcProdinfo record);

    int insertBasicByBasicInfo(ShareFilePbcProdinfo record);
}