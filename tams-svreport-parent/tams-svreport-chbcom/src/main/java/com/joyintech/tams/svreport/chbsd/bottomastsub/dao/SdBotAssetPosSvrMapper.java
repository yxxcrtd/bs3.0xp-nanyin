package com.joyintech.tams.svreport.chbsd.bottomastsub.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.chb.botastpositionregist.dto.ChbBotAstPositionRegist001Dto;

@Repository
public interface SdBotAssetPosSvrMapper {
	/**
	 * 查询跑批日未报送的数据
	 * @param batchDate
	 * @return
	 */
	public List<ChbBotAstPositionRegist001Dto> selcteUnRegData( @Param("batchDate")String batchDate);

}
