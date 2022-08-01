/*
 * 文件名：ValFinprodTradeQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.busicommon.rptdata.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.DisNostdCreditor;
import com.joyintech.tams.common.base.entity.DisProdAsset;
import com.joyintech.tams.common.base.entity.RepAssetsComb;
import com.joyintech.tams.common.base.entity.RepIndexSub;
import com.joyintech.tams.common.base.entity.RepInvestAccount;
import com.joyintech.tams.common.base.entity.RepProdBasicChange;
import com.joyintech.tams.common.base.entity.RepProdIndex;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.common.base.entity.RepRelDeal;
import com.joyintech.tams.disclosure.busicommon.rptdata.dto.DurationReportDto;

/**
 * 阶段性报告数据mapper
 * @author chenxc
 * @version 1.0 2022-07-21
 * @since 1.0
 */
@Repository
public interface DurationReportMapper {
	
	DurationReportDto selectProdInfo(Map<String,Object> paras);
	
	List<RepProdInfo> selectProdChildren(Map<String,Object> paras );
	
	List<RepProdIndex> selectFinIndex(Map<String,Object>paras) ;
	
	/**
	 * 查询业绩基准
	 */
	List<RepProdBasicChange> selectAchievementBasis(String prodId);
	
	List<RepIndexSub> selectReportIndex ( Map<String,Object>paras );
	
	List<DisProdAsset> selectProdAssetComb ( Map<String,Object>paras );
	
	List<RepAssetsComb> selectProdAssetDetail ( Map<String,Object>paras );
	
	List<RepInvestAccount> selectAccts ( Map<String,Object>paras );
	
	List<RepRelDeal> selectRelDeals ( Map<String,Object>paras );
	
	//List<Map<String,Object>>selectOtherFee(Map<String,Object>paras );
	
	List<DisNostdCreditor>selectNostdCreditor(Map<String,Object>paras );
}
