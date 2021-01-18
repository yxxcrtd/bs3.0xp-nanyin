package com.joyintech.tams.svreport.chbsd.prddeclaresub.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.chb.perprodeclarereg.dto.PerProDeclareRegDto;

@Repository
public interface SdProDeclareSvrMapper {
	
	/**
	 * 查询申报sub表list
	 * @param batchdate
	 * @param prodIdList
	 * @return
	 */
	public List<PerProDeclareRegDto> selcteDataByIds(@Param("batchdate")String  batchdate, @Param("prodIdList")List<String> prodIds);

}
