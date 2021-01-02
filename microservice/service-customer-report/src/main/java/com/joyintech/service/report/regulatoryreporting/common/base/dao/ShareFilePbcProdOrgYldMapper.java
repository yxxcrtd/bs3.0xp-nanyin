package com.joyintech.service.report.regulatoryreporting.common.base.dao;

import com.joyintech.service.report.regulatoryreporting.common.base.entity.ShareFilePbcProdOrgYld;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareFilePbcProdOrgYldMapper {
    int insert(ShareFilePbcProdOrgYld record);

    int insertSelective(ShareFilePbcProdOrgYld record);

    int insertBasicByBasicInfo(ShareFilePbcProdOrgYld record);
}