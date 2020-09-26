/*
 * 文件名：MoneyProdValuationDataService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.moneyvaluationdata.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.vatflow.dto.ValAddTaxFlowprocessorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.BokBookset;
import com.joyintech.tams.common.base.entity.BokStatBokPosition;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.moneyvaluationdata.dao.MoneyProdValuationDataMapper;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.moneyvaluationdata.dto.MoneyProdValuationDataDto;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.moneyvaluationdata.dto.MoneyProdValuationDataResult;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.netvaluationdata.dto.NetBokStatBokPositionDto;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.netvaluationdata.service.NetProdAccountingCommonService;
import com.joyintech.tams.valuation.interf.valuation.send.common.entity.SendReaderEntity;

/**
 * 货币型产品估值数据Service
 * @author qiangchengxi
 * @version 1.0 2018年11月19日
 * @see MoneyProdValuationDataService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class MoneyProdValuationDataService {

	/**
	 * 货币型产品估值数据Mapper
	 */
	@Autowired
	private MoneyProdValuationDataMapper moneyProdValuationDataMapper;
	
	/**
     * 帐套资产负债方法Service
     */
    @Autowired
	private NetProdAccountingCommonService netAccountingCommonService;
	public ValAddTaxFlowprocessorResult execute(SendReaderEntity item){
		ValAddTaxFlowprocessorResult moneyProdValuationDataResult = new ValAddTaxFlowprocessorResult();

		List<BokBookset> bokBooksetList = new ArrayList<BokBookset>();
		List<String>idList = new ArrayList<String>();
		for(BokBookset bokBookset : bokBooksetList) {
			idList.add(bokBookset.getBooksetId());
		}
		//当前年化收益率
		List<MoneyProdValuationDataDto> list=moneyProdValuationDataMapper.queryMoneyValueData(item);
		for(MoneyProdValuationDataDto dto:list){
			NetBokStatBokPositionDto position=new NetBokStatBokPositionDto();
			position.setSdateStart(item.getExeDate());
			position.setSdateEnd(item.getExeDate());
			position.setBookDate(item.getExeDate());
			if(StringUtil.isNotEmpty(dto.getBookSetId())){
				position.setBooksetId(dto.getBookSetId());
			}
			List<BokStatBokPosition> bokStatBokPosition = netAccountingCommonService.getValStatBokPosition(position);
			if(!StringUtil.isEmptyList(bokStatBokPosition)){
				dto.setYrrate(bokStatBokPosition.get(0).getYieldTerm());
			}else{
				dto.setYrrate(BigDecimal.ZERO);
			}
		}
		moneyProdValuationDataResult.setDatas(list);
		moneyProdValuationDataResult.setTmpEName("CProdDealDataBack");
		return moneyProdValuationDataResult;
	}
}
