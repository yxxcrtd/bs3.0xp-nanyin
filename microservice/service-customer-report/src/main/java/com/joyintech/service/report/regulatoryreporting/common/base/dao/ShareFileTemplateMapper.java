package com.joyintech.service.report.regulatoryreporting.common.base.dao;

import com.joyintech.service.report.regulatoryreporting.common.base.entity.ShareFileTemplate;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareFileTemplateMapper {
    int insert(ShareFileTemplate record);

    int insertSelective(ShareFileTemplate record);

    int insertBasicByBasicInfo(ShareFileTemplate record);
}