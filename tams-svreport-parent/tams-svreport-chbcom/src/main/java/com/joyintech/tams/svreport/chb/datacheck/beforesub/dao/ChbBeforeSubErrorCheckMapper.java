package com.joyintech.tams.svreport.chb.datacheck.beforesub.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ChbBeforeSubErrorCheckMapper {
       
    List<Map<String,Object>> selectProdDealSub(@Param("checkdate") Date checkdate,@Param("listProd") List<String> listProd);
        
    int updateProdDealSub(String seqno);
    
    List<Map<String,Object>> selectProdIssueSub(@Param("checkdate") Date checkdate,@Param("listProd") List<String> listProd);
    
    int updateProdIssueSub(String seqno);        
    
    List<Map<String,Object>> selectProdEndSub(@Param("checkdate") Date checkdate,@Param("listProd") List<String> listProd);
    
    int updateProdEndSub(String seqno);

    List<Map<String,Object>> selectInvestTradeSub(@Param("checkdate") Date checkdate,@Param("listProd") List<String> listProd);
    
    int updateInvestTradeSub(String seqno);    

}