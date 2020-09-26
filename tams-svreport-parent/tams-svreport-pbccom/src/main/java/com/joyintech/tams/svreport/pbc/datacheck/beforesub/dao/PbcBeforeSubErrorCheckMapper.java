package com.joyintech.tams.svreport.pbc.datacheck.beforesub.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PbcBeforeSubErrorCheckMapper {
       
    List<Map<String,Object>> selectPrdBaseInfoSub(@Param("checkdate") Date checkdate,@Param("listProd") List<String> listProd);

    int updatePrdBaseInfoSub(String seqno);
    
}