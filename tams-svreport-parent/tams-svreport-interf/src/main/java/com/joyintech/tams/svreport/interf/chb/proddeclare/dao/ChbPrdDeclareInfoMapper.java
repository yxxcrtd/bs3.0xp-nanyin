package com.joyintech.tams.svreport.interf.chb.proddeclare.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.svreport.interf.chb.proddeclare.dto.ChbPrdDeclareInfoDto;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbPrdDeclareInfoMapper {
    int deleteByPrimaryKey(String declareUuid);

    int insert(ChbPrdDeclareInfoDto record);

    int insertSelective(ChbPrdDeclareInfoDto record);

    ChbPrdDeclareInfoDto selectByPrimaryKey(String declareUuid);

    int updateByPrimaryKeySelective(ChbPrdDeclareInfoDto record);

    int updateByPrimaryKey(ChbPrdDeclareInfoDto record);
    
    List<ChbPrdDeclareInfoDto> selectRepDeclare(@Param("updatetime") Date updatetime);
    int mergeInfoList(@Param("newDataList")List<ChbPrdDeclareInfoDto> insertList);

    ChbPrdDeclareInfoDto selectByProdInsId(String prodInsId);
}