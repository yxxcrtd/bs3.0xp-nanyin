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

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.disclosure.rptdata.dto.BonusPlanDto;
import com.joyintech.tams.disclosure.rptdata.dto.BonusReportDto;

/**
 * 报表查询
 * @author leipeng
 * @version 1.0 2018年8月2日
 * @see BonusReportMapper
 * @since 1.0
 */
@Repository
public interface BonusReportMapper {
	
	List<BonusReportDto> selectBonusReport(Map<String,Object>paras) ;
	
	BonusPlanDto selectLastBonusPlan(Map<String,Object>paras) ;
}
