package com.joyintech.tams.svreport.chbsd.proddealsub.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.chbsd.proddealsub.dto.SdProdDealSvrDto;
@Repository
public interface SdProdDealSvrMapper {
	/**
	 * 查询存续期sub表list
	 * @param batchdate
	 * @param prodIdList
	 * @return
	 */
	List<SdProdDealSvrDto>  selcteDataByIds(@Param("batchdate")String  batchdate,@Param("prodIdList") List<String> prodIdList);
 
}
