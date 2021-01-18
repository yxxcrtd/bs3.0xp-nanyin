package com.joyintech.tams.svreport.chbsd.prdissuesub.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.chbsd.prdissuesub.dto.SdProdPersonIssueDto;

@Repository
public interface SdprodIssueSvrMapper {
	
	/**
	 * 查询发行sub表list
	 * @param batchdate
	 * @param prodIdList
	 * @return
	 */
	List<SdProdPersonIssueDto>  selcteDataByIds(@Param("batchdate")String  batchdate,@Param("prodIdList") List<String> prodIdList);
 
}
