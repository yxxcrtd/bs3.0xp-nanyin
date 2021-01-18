package com.joyintech.tams.svreport.chbsd.prdraisetotsub.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.chbsd.prdraisetotsub.dto.SdRaiseTotalSvrDto;
@Repository
public interface SdRaiseTotalSvrMapper {
	
	/**
	 * 查询申报接口临时表list
	 * @param batchdate
	 * @param prodIdList
	 * @return
	 */
	List<SdRaiseTotalSvrDto>  selcteDataByIds(@Param("batchdate")String  batchdate,@Param("prodIdList") List<String> prodIdList);


}
