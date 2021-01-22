package com.joyintech.tams.svreport.chbhz.inPrdEndSub.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ChbPrdEndSubTmp;
import com.joyintech.tams.svreport.chb.prodterminationregist.dto.ProdTerminationRegist001Dto;

@Repository
public interface InprodEndInfoSvrMapper {
	
	/**
	 * 查询终止接口临时表list
	 * @param batchdate
	 * @param prodIdList
	 * @return
	 */
	List<ChbPrdEndSubTmp>  genChbPrdEndSubTmpList(@Param("batchdate")String  batchdate,@Param("prodIdList") List<String> prodIdList);
    /**
     * 根据接口信息查询终止信息表
     * @return
     */
    List<ProdTerminationRegist001Dto>   genChbPrdEndSubList(@Param("keylist")List<String> keylist);
}