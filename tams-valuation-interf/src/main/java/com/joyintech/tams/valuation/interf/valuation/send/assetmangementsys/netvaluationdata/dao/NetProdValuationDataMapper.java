/*
 * 文件名：MoneyProdValuationDataMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.netvaluationdata.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinFloatAccrualSche;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.moneyvaluationdata.dao.MoneyProdValuationDataMapper;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.netvaluationdata.dto.NetProdValuationDataDto;
import com.joyintech.tams.valuation.interf.valuation.send.common.entity.SendReaderEntity;



/**
 * 净值型产品估值数据Mapper
 * @author chendong
 * @version 1.0 2018年11月20日
 * @see MoneyProdValuationDataMapper
 * @since 1.0
 */
@Repository
public interface NetProdValuationDataMapper {
	/**
	 *  查询净值型产品数据
	 *  @param item
	 *  @return 净值型产品数据List
	 */
	List<NetProdValuationDataDto> queryNetValuationData(SendReaderEntity item);
	
	/**
	 *  查询当前年化收益率
	 *  @param 产品代码
	 *  @param 估值日期
	 *  @param 浮动管理费
	 *  @return FinFloatAccrualSche
	 */
//	List<NetProdNowRateDto> queryNowRate(@Param("prodId")String prodId, @Param("valDate")Date valDate);

	 List<FinFloatAccrualSche> queryNowRate(@Param("prodId")String prodId, @Param("valDate")Date valDate,@Param("type2")String type2);

}
