package com.joyintech.tams.svreport.chbsd.assetdealsub.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.chb.investtradereg.dto.InvestTradeRegist001Dto;

@Repository
public interface SdAssetDealSvrMapper {
	/**
	 * 查询跑批日未报送的数据
	 * @param batchDate
	 * @return
	 */
	public List<InvestTradeRegist001Dto> selcteUnRegData( @Param("batchDate")String batchDate,@Param("prodIdList")List<String> prodList);

}
