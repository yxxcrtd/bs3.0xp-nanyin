package com.joyintech.service.report.regulatoryreporting.common.base.dao;

import com.joyintech.service.report.regulatoryreporting.common.base.entity.ShareFileConfg;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareFileConfgMapper {
    int insert(ShareFileConfg record);

    int insertSelective(ShareFileConfg record);

    int insertBasicByBasicInfo(ShareFileConfg record);
}