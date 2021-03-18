package com.joyintech.tams.svreport.chb.datacheck.beforesub.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbBeforeSubErrorCheckMapper {
       
    List<Map<String,Object>> selectProdDealSub(@Param("checkdate") Date checkdate,@Param("listProd") List<String> listProd);
        
    int updateProdDealSub(String seqno);
    
    List<Map<String,Object>> selectProdIssueSub(@Param("checkdate") Date checkdate,@Param("listProd") List<String> listProd);
    
    int updateProdIssueSub(String seqno);        
    
    List<Map<String,Object>> selectProdEndSub(@Param("checkdate") Date checkdate,@Param("listProd") List<String> listProd);
    
    int updateProdEndSub(String seqno);

    List<Map<String,Object>> selectInvestTradeSub(@Param("checkdate") Date checkdate,@Param("listProd") List<String> listProd);
    
    int updateInvestTradeSub(String seqno);  
    
    List<Map<String,Object>> selectAssetInfoSub(@Param("checkdate") Date checkdate,@Param("listAsset") List<String> listProd);
    
    int updateAssetInfoSub(String seqno);
    List<Map<String,Object>> selectProdDeclareSub(@Param("checkdate") Date checkdate,@Param("listProd") List<String> listProd);
    
    int updateProdDeclareSub(String seqno);
    List<Map<String,Object>> selectProdRaiseTotalSub(@Param("checkdate") Date checkdate,@Param("listProd") List<String> listProd);

    int updateProdRaiseTotalSub(String seqno);

    List<Map<String,Object>> selectChbAstValSub(@Param("checkdate") Date checkdate);

    int updateChbAstValSub(String seqno);
    Integer isInEmpoyeeSub(@Param("id") String id, @Param("employeeType") String employeeType);
    Integer isInAssetInfoSub(String assetCode);
    Integer isInRaisetotSub(String prodId);
    Integer isInPrdEndSub(String prodId);


    Integer isInPrdIssueSub(String prodId);

    Integer isInProDeclareReg(String prodId);

    String isUploadFile(Map<String, String> dataMap);

    List<Map<String, Object>> selectChbBotAst(@Param("checkdate") Date checkdate,@Param("listProd") List<String> listProd);

    int updateChbBotAstSub(String seqno);

    List<String> isOutsourOrAssetMana(String assetCode);

	List<Map<String, String>> selectChbEmployeeSub(@Param("checkdate") Date checkdate);
	
	int isInEmployeeSub(@Param("key") String key);
}