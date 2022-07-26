/*
 * 文件名：ValFinprodTradeQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.rptdata.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepFeeTrd;
import com.joyintech.tams.disclosure.rptdata.dto.BonusReportDto;

/**
 * 到期报告
 * @author chenxc
 * @version 1.0 2018年8月2日
 * @see MatureReportMapper
 * @since 1.0
 */
@Repository
public interface MatureReportMapper {
	List<BonusReportDto> selectBonusPlan(Map<String,Object>paras) ;
	
	List<RepFeeTrd> selectFees(Map<String,Object>paras);
}
