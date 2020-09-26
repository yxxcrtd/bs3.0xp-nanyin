/*
 * 文件名：ProfitDistributionBookMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.report.common.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.report.common.dto.FinProduct;
import com.joyintech.tams.valuation.report.common.dto.MstHoliday;
import com.joyintech.tams.valuation.report.common.dto.NetvaluerResultDto;
import com.joyintech.tams.valuation.report.common.dto.RepProdIndex;

import feign.Param;


/**
 * 
 * 
 * @author 
 * @version 1.0 
 * @see TargetIndexMapper
 * @since 1.0
 */
@Repository
public interface TargetIndexMapper {
	
	/**
	 * 产品指标表的查询
	 * @param startDate  endDate prodId
	 * @return
	 */
	List<RepProdIndex>  slectTargetIndex(Map<String,Object> paramMap);
    
	/**
	 * 标的类金融产品的查询
	 * @param prodId
	 * @return
	 */
	FinProduct  selectFinProduct(String prodId);
	
	/**
	 * 日历节假日的查询
	 * @param prodId
	 * @return
	 */
	List<MstHoliday>   selectMstHoliday(Map<String,Object> paramMap);
	
	/**
	 * 根据日期查询产品指标
	 * @param cDate
	 * @return
	 */
	RepProdIndex seletcRepProdIndexByDate(Date cDate);
	
	/**
	 * 根据日期分组万份收益
	 */
	List<NetvaluerResultDto>  getNetvalue(Map<String,Object> paramMap);
	
}
