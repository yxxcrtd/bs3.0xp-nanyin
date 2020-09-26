package com.joyintech.tams.svreport.interf.dis.repassetindexInterf.dao;

import com.joyintech.tams.svreport.interf.dis.repassetindexInterf.dto.RepAssetIndex;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface DisRepAssetIndexMapper {
    int insert(List<RepAssetIndex> list);

    int insertSelective(RepAssetIndex record);

    int insertBasicByBasicInfo(RepAssetIndex record);
    
    void   delete(String valueDate);
}