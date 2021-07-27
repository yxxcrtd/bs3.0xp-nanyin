package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import org.springframework.stereotype.Repository;

@Repository
public interface PbcSendZipDatFileMapper {
    int insert(PbcSendZipDatFile record);

    int insertSelective(PbcSendZipDatFile record);

    int insertBasicByBasicInfo(PbcSendZipDatFile record);
}