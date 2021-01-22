package com.joyintech.tams.svreport.chbhz.inPrdIssueSub.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ChbPrdIssueSubTmp;
import com.joyintech.tams.svreport.chb.prodpersonissue.dto.ProdPersonIssue001Dto;

@Repository
public interface InprodIssueInfoSvrMapper {
	
	/**
	 * 查询申报接口临时表list
	 * @param batchdate
	 * @param prodIdList
	 * @return
	 */
	List<ChbPrdIssueSubTmp>  genChbPrdIssueSubTmpList(@Param("batchdate")String  batchdate,@Param("prodIdList") List<String> prodIdList);
    /**
     * 根据接口信息查询申报信息表
     * @return
     */
    List<ProdPersonIssue001Dto>   genChbPrdIssueSubList(@Param("keylist")List<String> keylist);
    
    int  updateChbprdBaseInfo(ProdPersonIssue001Dto  dto);
}