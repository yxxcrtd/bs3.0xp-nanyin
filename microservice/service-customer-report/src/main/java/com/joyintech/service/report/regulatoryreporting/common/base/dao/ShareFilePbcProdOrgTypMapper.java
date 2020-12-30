package com.joyintech.service.report.regulatoryreporting.common.base.dao;

import com.joyintech.service.report.regulatoryreporting.common.base.entity.ShareFilePbcProdOrgTyp;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareFilePbcProdOrgTypMapper {
    int insert(ShareFilePbcProdOrgTyp record);

    int insertSelective(ShareFilePbcProdOrgTyp record);

    int insertBasicByBasicInfo(ShareFilePbcProdOrgTyp record);
}