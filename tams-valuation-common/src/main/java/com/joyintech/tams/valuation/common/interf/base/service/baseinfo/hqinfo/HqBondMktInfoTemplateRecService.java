/*
 * 文件名：NPrdBaseinfoTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangwei1
 * 修改时间：2018年6月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.hqinfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.MstBondMarketInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.HqInfoForInterfRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.HqBondMarketInfo;

/**
 * 行情信息-债券市场行情
 * 
 * @author wangwei1
 * @version 1.0 2018年6月28日
 * @see HqBondMktInfoTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class HqBondMktInfoTemplateRecService {

	@Autowired
	private TemplateAnalysisForXMLService templateAnalysisForXMLService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public HqInfoForInterfRecCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
			TemplateOrderInfoReaderResult acctEntity) {
		// 定义返回变量
		HqInfoForInterfRecCommonResult result = new HqInfoForInterfRecCommonResult();

		List<HqBondMarketInfo> hqBondMarketInfo = new ArrayList<HqBondMarketInfo>();

		// 解析模板后的list以及日志list
		TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();

		// 文件路径设置 TODO
		// 此部分处理待以后从画面入口的时候进行acctEntity的文件路径的重置，根据templateSourceFlgDTO中的路径进行重新设置。
		// 1、文件读取解析 进行数据有效性的check,并生出进入日志表的数据结构
		// XML读取追加过滤器
        if (DictCodeInterfList.接口文件类型_XML.getValue().equals(acctEntity.getTmpType())) {
            templateAnalysisForXMLService.setRootElement("Security");
            Map<String, List<String>> filter = new HashMap<String, List<String>>();
            String[] filters = {"5","6","7","8","9","10","11","13","34","35"};
            filter.put("SecurityType", Arrays.asList(filters));
            templateAnalysisForXMLService.setFilter(filter);
        }   
		returnResult = templateAnalysisForXMLService.execute(acctEntity);
		hqBondMarketInfo = returnResult.getT(HqBondMarketInfo.class);
		
		List<HqBondMarketInfo> infoList = new ArrayList<HqBondMarketInfo>();
		/** add by dongjun on 20180921 start **/
		/** add by dongjun on 20181211 start **/
        // 如果是中债估值需要选择推荐的那条估值
//        if (acctEntity.getTmpEName().equals(TempConstant.中国债券交易所债券行情)||
//        		acctEntity.getTmpEName().equals(TempConstant.中国债券行情_净价)||
//        		acctEntity.getTmpEName().equals(TempConstant.中国债券行情_全价)) {
        infoList = hqBondMarketInfo;
//        } 
        /** add by dongjun on 20181211 end **/
		// 2、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        List<MstBondMarketInfo> listValBondMarketInfo = (List<MstBondMarketInfo>)handleListToVaLInfo(
            infoList, acctEntity.getResovleDate(),acctEntity.getTmpEName()).get("valBondMarketInfo");
        /** add by dongjun on 20180921 start **/
		result.setListValBondMarketInfo(listValBondMarketInfo);
		// 添加日志
		result.setListValInterflog(returnResult.getValInterflog());
		return result;
	}

	/**
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @param hqShanghaiStockExchangeValuation
	 * @return
	 * @see
	 * @since 1.0
	 */
	/** add by dongjun on 20180921 start **/
	private Map<String, Object> handleListToVaLInfo(List<HqBondMarketInfo> infoList, Date date, String tempEName) {
	/** add by dongjun on 20180921 start **/
		Map<String, Object> resultMap = new HashMap<>();
		List<MstBondMarketInfo> valBondMarketInfoList = new ArrayList<MstBondMarketInfo>();
		for (HqBondMarketInfo dto : infoList) {
			// 债券市场行情 处理
			MstBondMarketInfo valBondMarketInfo = getValBondMarketInfo(dto, date, tempEName);
			valBondMarketInfoList.add(valBondMarketInfo);
		}
		resultMap.put("valBondMarketInfo", valBondMarketInfoList);
		return resultMap;
	}

	/**
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @param dto
	 * @return
	 * @see
	 * @since 1.0
	 */
	private MstBondMarketInfo getValBondMarketInfo(HqBondMarketInfo dto, Date date, String tempEName) {
		MstBondMarketInfo valBondMarketInfo = new MstBondMarketInfo();

		// 债券代码 SEC_ID A.证券代码 需根据A.证券代码确认是债券还是基金，规则由需求提供
		// 估值来源 VALUE_SOURCE 默认值：03 数据字典：交易流通场所-上海交易所
		/** add by dongjun on 20180921 start **/
		if(tempEName.equals(TempConstant.中国债券交易所债券行情)){
//		    String valueSorce = dto.getSecId().split("\\.")[1];
//		    valBondMarketInfo.setValueSource(getValueSource(valueSorce));
		    valBondMarketInfo.setSecId(dto.getSecId());
		} else if(tempEName.equals(TempConstant.中国债券行情_净价)){
//		    String valueSorce = dto.getSecId().split("\\.")[1];
//            valBondMarketInfo.setValueSource(getValueSource(valueSorce));
            valBondMarketInfo.setSecId(dto.getSecId());
		} else if(tempEName.equals(TempConstant.中国债券行情_全价)){
//		    String valueSorce = dto.getSecId().split("\\.")[1];
//            valBondMarketInfo.setValueSource(getValueSource(valueSorce));
            valBondMarketInfo.setSecId(dto.getSecId());
        } else{
        /** add by dongjun on 20180921 start **/
            if (dto.getValueSource() != null) {
//                valBondMarketInfo.setValueSource(dto.getValueSource());
                if (DictCodeList.交易流通场所_银行间债券市场.getValue().equals(dto.getValueSource())) {
                    valBondMarketInfo.setSecId(dto.getSecId() + ".IB");
                } else if (DictCodeList.交易流通场所_上海证券交易所.getValue().equals(dto.getValueSource())) {
                    valBondMarketInfo.setSecId(dto.getSecId() + ".SH");
                } else if (DictCodeList.交易流通场所_深圳证券交易所.getValue().equals(dto.getValueSource())) {
                    valBondMarketInfo.setSecId(dto.getSecId() + ".SZ");
                } else {
                    valBondMarketInfo.setSecId(dto.getSecId() + ".99");
                }
            } else {
//                valBondMarketInfo.setValueSource(DictCodeList.交易流通场所_深圳证券交易所.getValue());
                valBondMarketInfo.setSecId(dto.getSecId() + ".SZ");
            }
        }
		
		// 估值日 VALUE_DATE 默认值：参数给定
		if (dto.getValueDate() != null) {
		    valBondMarketInfo.setValueDate(dto.getValueDate());
		} else {
		    valBondMarketInfo.setValueDate(date);
		}
		// 收盘价 CLOSE_PRICE A.今日收盘价
		valBondMarketInfo.setClosePrice(dto.getClosePrice());
		// 平均价 AVERAGE_PRICE
//		valBondMarketInfo.setAveragePrice();
		// 最高价 MAX_PRICE A.最高价
		valBondMarketInfo.setMaxPrice(dto.getMaxPrice());
		// 最低价 MIN_PRICE A.最低价
		valBondMarketInfo.setMinPrice(dto.getMinPrice());
		// 买一价 BUY_ONE_PRICE A.申买价一
		valBondMarketInfo.setBuyOnePrice(dto.getBuyOnePrice());
		// 卖一价 SELL_ONE_PRICE A.申卖价一
		valBondMarketInfo.setSellOnePrice(dto.getSellOnePrice());
		return valBondMarketInfo;
	}

	/**
	 * 
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param type
	 * @return
	 * @see
	 * @since 1.0
	 */
	/** add by dongjun on 20180921 start **/
	private String getValueSource(String type){
	    String valueSource;
	    if("IB".equals(type)){
	        valueSource = DictCodeList.交易流通场所_银行间债券市场.getValue();
	    } else if("SH".equals(type)){
	        valueSource = DictCodeList.交易流通场所_上海证券交易所.getValue();
	    } else if("SZ".equals(type)){
            valueSource = DictCodeList.交易流通场所_深圳证券交易所.getValue();
        } else{
            valueSource = DictCodeList.交易流通场所_其他交易流通场所.getValue();
        }
	    return valueSource;
	}
	/** add by dongjun on 20180921 start **/
}
