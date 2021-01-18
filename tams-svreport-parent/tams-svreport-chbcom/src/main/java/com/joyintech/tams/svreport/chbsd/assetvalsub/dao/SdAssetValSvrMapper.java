package com.joyintech.tams.svreport.chbsd.assetvalsub.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.chb.astvalueregist.dto.ChbAstValueRegist001Dto;

@Repository
public interface SdAssetValSvrMapper {
	/**
	 * 查询跑批日未报送的数据
	 * @param batchDate
	 * @return
	 */
	public List<ChbAstValueRegist001Dto> selcteUnRegData( @Param("batchDate")String batchDate);

}
