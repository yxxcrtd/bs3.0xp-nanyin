package com.joyintech.tams.svreport.chbsd.prdendsub.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.chbsd.prdendsub.dto.SdProdTerminationSvrDto;
@Repository
public interface SdProdTerminationSvrMapper {
	
	/**
	 * 查询产品终止sub表list
	 * @param batchdate
	 * @param prodIdList
	 * @return
	 */
	List<SdProdTerminationSvrDto>  selcteDataByIds(@Param("batchdate")String  batchdate,@Param("prodIdList") List<String> prodIdList);
 
}
