/*
 * 文件名：MoneyTaAutoCalReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：May 25, 2019
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.investratechange.reader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.batch.investratechange.dto.InvestRateChangeReaderDto;
import com.joyintech.tams.valuation.batch.investratechange.service.InvestRateChangeReaderService;
import com.joyintech.tams.valuation.common.constants.BookConstant;

import org.apache.poi.hpsf.Decimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.joyintech.framework.util.DateUtil;

/**
 * 利率互换
 * @author taoran
 * @version 20200114
 * @see InvestRateChangeReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class InvestRateChangeReader extends BaseReader<InvestRateChangeReaderDto> {

    @Autowired
    private BookCommonLogic bookCommonLogic;
    
    @Autowired
    private InvestRateChangeReaderService investRateChangeReaderService;
    
	@Override
	public List<InvestRateChangeReaderDto> createList() {
		List<InvestRateChangeReaderDto> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		Map<String, String> dimValueMap = bookCommonLogic.getDimensionValue(this.getBatchExecuteInfo());
		map.put("seqNo", this.getBatchExecuteDetail().getSeqNo());	//批处理任务组id
		map.put("bookSetDate", DateUtil.parse(dimValueMap.get(BookConstant.BOOKSET_DATE)));	//账套日期
		map.put("bookDate", DateUtil.parse(dimValueMap.get(BookConstant.BOOK_DATE)));	//入账日
		map.put("happenDate", DateUtil.parse(dimValueMap.get(BookConstant.HAPPEN_DATE)));	//发生日
		map.put("taskId", this.getBatchExecuteInfo().getTaskId());	//任务代码
		map.put("isSubBookSet", dimValueMap.get(BookConstant.IS_SUB_BOOKSET));	//是否子账套
		map.put("branch", 0L);	//分支序号
		map.put("intType02", DictCodeList.利率类型_浮动利率.getValue());	//利率类型-浮动利率
		map.put("intType01", DictCodeList.利率类型_固定利率.getValue());	//利率类型-固定利率
		map.put("isInitial", DictCodeList.是否_是.getValue());		//是否——是
		map.put("C5", DictCodeList.交易方向_产品到期.getValue());		//交易方向
		map.put("C20", DictCodeList.交易方向_提前终止.getValue());		//交易方向
		map.put("tradeStatus", DictCodeListExt1.交易状态_初始.getValue());		//交易状态_初始
		map.put("finProdType", DictCodeListExt1.金融产品类型_投管用_利率互换.getValue());		//金融产品类型_投管用_利率互换
		map.put("PS", DictCodeList.交易方向_买入.getValue());		//交易方向
		List<InvestRateChangeReaderDto> resultList = investRateChangeReaderService.getNeedChangeTrade(map);
		InvestRateChangeReaderDto investRateChangeReaderDto = new InvestRateChangeReaderDto();
		investRateChangeReaderDto.setInvestRateChangeReaderList(resultList);
		investRateChangeReaderDto.setHappenDate(DateUtil.parse(dimValueMap.get(BookConstant.HAPPEN_DATE)));
		investRateChangeReaderDto.setBookDate(DateUtil.parse(dimValueMap.get(BookConstant.BOOK_DATE)));
		investRateChangeReaderDto.setBooksetDate(DateUtil.parse(dimValueMap.get(BookConstant.BOOKSET_DATE)));
		list.add(investRateChangeReaderDto);
		return list;
	}

}
