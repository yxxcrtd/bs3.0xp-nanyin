package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PbcSendZipDatFileMapper {
    PbcSendZipDatFile selectByPrimaryKey(String reportuuid);

    int insert(PbcSendZipDatFile record);

    int insertSelective(PbcSendZipDatFile record);

    int insertBasicByBasicInfo(PbcSendZipDatFile record);

    List<PbcSendZipDatFile> searchPage(Map<String,Object> map);

    List<PbcSendZipDatFile> selectByZipTypeAndStatusVdate(Map<String,Object> map);

    int deleteByUUIDAndType(Map<String,Object> paramsMap);
}