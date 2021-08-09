package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PbcPrdBaseendSubNewHis;
import org.springframework.stereotype.Repository;

@Repository
public interface PbcPrdBaseendSubNewHisMapper {
    int insert(PbcPrdBaseendSubNewHis record);

    int insertSelective(PbcPrdBaseendSubNewHis record);

    int insertHisByBasicInfo(PbcPrdBaseendSubNewHis record);
}