package com.joyintech.tams.svreport.interf.dis.prodindexinterf.dao;

import com.joyintech.tams.svreport.interf.dis.prodindexinterf.dto.RepProdIndex;


import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface DisRepProdIndexMapper {
    int insert(List<RepProdIndex> list);

    int insertSelective(RepProdIndex record);

    int insertBasicByBasicInfo(RepProdIndex record);
    
    void  deleteByValueDate(String valueDate);
    
    int mergeRepProdIndex(List<RepProdIndex> dtoList);
}