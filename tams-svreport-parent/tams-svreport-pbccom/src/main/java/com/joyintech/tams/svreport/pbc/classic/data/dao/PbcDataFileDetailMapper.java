package com.joyintech.tams.svreport.pbc.classic.data.dao;

import com.joyintech.tams.svreport.pbc.classic.data.dto.PbcDataFileDetail;

public interface PbcDataFileDetailMapper {
    int deleteByPrimaryKey(String detailid);

    int insert(PbcDataFileDetail record);

    int insertSelective(PbcDataFileDetail record);

    PbcDataFileDetail selectByPrimaryKey(String detailid);

    int updateByPrimaryKeySelective(PbcDataFileDetail record);

    int updateByPrimaryKey(PbcDataFileDetail record);
}