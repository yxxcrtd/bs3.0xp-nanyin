/*
 * 文件名：TrdCommandReceive001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2018年9月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.investtrade.commandreceive.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.system.base.dao.SysParaMapper;
import com.joyintech.system.base.entity.SysPara;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.trade.cstp.service.CstpSelect001Service;
import com.joyintech.tams.valuation.common.interf.val.dao.ValSysParaMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.trade.common.dto.EtrInvestInfoDto;
import com.joyintech.tams.invest.trade.common.service.TradeCommonService;
import com.joyintech.tams.invest.trade.investtrade.commandreceive.dto.TrdCommandReceive001Dto;

/**
 * 指令接收service
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2018年09月26日
 * @see TrdCommandReceive001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class TrdCommandReceive001Service extends CommonExportService<TrdCommandReceive001Dto> {

    @Autowired
    private ValSysParaMapper sysParaMapper;
    
    @Autowired
	private TradeCommonService tradeCommonService;
    @Autowired
    private CstpSelect001Service cstpSelect001Service;
	/**
     * 查询 <功能详细描述>
     * 
     * @param pageParam 查询信息
     * @return 查询结果
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
	public Page<TrdCommandReceive001Dto> selectForPage(Map<String, Object> pageParam){
	    //设置属性
        setParams(pageParam);
        return restTemplate.postForPage(
                ServiceApplication.TRADE_SERVICE_NAME + "/TrdCommandReceive001/findPage", pageParam,
                TrdCommandReceive001Dto.class);
	}


	public Page<TrdCommandReceive001Dto> selectForPageFromCstp(Map<String, Object> pageParam){
		//设置属性
		setParams(pageParam);
		return restTemplate.postForPage(
				ServiceApplication.TRADE_SERVICE_NAME + "/TrdCommandReceive001/selectForPageFromCstp", pageParam,
				TrdCommandReceive001Dto.class);
	}

    public void setParams(Map<String, Object> pageParam) {

        String isCstp = "";
        if(pageParam.containsKey("isCstp")){
             isCstp = pageParam.get("isCstp").toString();
        }
        if(StringUtil.isNotBlank(isCstp)&&StringUtil.equals(isCstp,"false")){
            pageParam.put("cstpTradeDate",null);
        }else if(StringUtil.isNotBlank(isCstp)&&StringUtil.equals(isCstp,"true")){
            if(pageParam.containsKey("cstpFinprodType2")){
                pageParam.put("finprodType2",pageParam.get("cstpFinprodType2").toString());
            }
            if(pageParam.containsKey("cstpFfinprodMarketId")){
                pageParam.put("finprodMarketId",pageParam.get("cstpFfinprodMarketId").toString());
            }
            if(pageParam.containsKey("cstpPrice")){
                pageParam.put("price",pageParam.get("cstpPrice").toString());
            }
            if(pageParam.containsKey("cstpMatureYield")){
                pageParam.put("matureYield",pageParam.get("cstpMatureYield").toString());
            }
            if(pageParam.containsKey("cstpExerYield")){
                pageParam.put("exerYield",pageParam.get("cstpExerYield").toString());
            }
            if(pageParam.containsKey("cstpRepoTerm")){
                pageParam.put("repoTerm",pageParam.get("cstpRepoTerm").toString());
            }
            if(pageParam.containsKey("cstpTradeMarket")){
                pageParam.put("tradeMarket",pageParam.get("cstpTradeMarket").toString());
            }
            if(pageParam.containsKey("cstpPs")){
                pageParam.put("ps",pageParam.get("cstpPs").toString());
            }
            if(pageParam.containsKey("cstpSecAcctId")){
                pageParam.put("secAcctId",pageParam.get("cstpSecAcctId").toString());
            }
            if(pageParam.containsKey("cstpSettleSpeed")){
                pageParam.put("settleSpeed",pageParam.get("cstpSettleSpeed").toString());
            }
        }
        pageParam.put("loginUser", LoginUserUtil.getUserName());
        pageParam.put("entrustReceive", DictCodeList.委托状态_已接收.getValue());
        pageParam.put("entrustCompleted", DictCodeList.委托状态_已完成.getValue());
        pageParam.put("entrustClose", DictCodeList.委托状态_已关闭.getValue());
        //组合
        String portfolioIdOld = (String)pageParam.get("portfolioId");
        if (StringUtil.isNotEmpty(portfolioIdOld)) {
        	pageParam.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA, portfolioIdOld));
        }
        //交易类型
        String matchTypeOld = (String)pageParam.get("matchType");
        if (StringUtil.isNotEmpty(matchTypeOld)) {
        	pageParam.put("matchTypeList", StringUtil.splitToList(GlobalConstant.COMMA, matchTypeOld));
        }
        String finprodType2Old = (String)pageParam.get("finprodType2");
        List<String> list1=new ArrayList<>();
        List<String> list2=new ArrayList<>();
        List<String> list3=new ArrayList<>();
        if (StringUtil.isNotEmpty(finprodType2Old) || StringUtil.isNotEmpty(pageParam.get("ps"))) {
            List<String> finprodType2ListOld = StringUtil.splitToList(GlobalConstant.COMMA, finprodType2Old);
            for(String finprodType2 : finprodType2ListOld){
            //              //为不同资产类型赋查询条件
            if(StringUtil.equals(DictCodeList.平安资产类型_债券.getValue(), finprodType2)){
            list1.add(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
            }
            else if(StringUtil.equals(DictCodeList.平安资产类型_基金.getValue(), finprodType2)){
            list1.add(DictCodeListExt1.金融产品类型_投管用_基金.getValue());
            }
            else if(StringUtil.equals(DictCodeList.平安资产类型_计划类资产_利率型.getValue(), finprodType2)){
            list3.add(DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
            list2.add(DictCodeList.收益类型_收益型.getValue());
            }
            else if(StringUtil.equals(DictCodeList.平安资产类型_计划类资产_净值型.getValue(), finprodType2)){
            list3.add(DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
            list2.add(DictCodeList.收益类型_净值型.getValue());
            }
            else if(StringUtil.equals(DictCodeList.平安资产类型_计划类资产_货币型.getValue(), finprodType2)){
            list3.add(DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
            list2.add(DictCodeList.收益类型_货币型.getValue());
            }
            else if(StringUtil.equals(DictCodeList.平安资产类型_存款.getValue(), finprodType2)){
            list1.add(DictCodeListExt1.金融产品类型_存款.getValue());
            }
            else if(StringUtil.equals(DictCodeList.平安资产类型_项目类资产.getValue(), finprodType2)){
            list1.add(DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
            }
            else if(StringUtil.equals(DictCodeList.平安资产类型_质押式回购.getValue(), finprodType2)){
            list1.add(DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue());
            list1.add(DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue());
            }
            else if(StringUtil.equals(DictCodeList.平安资产类型_买断式回购.getValue(), finprodType2)){
            list1.add(DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue());
            list1.add(DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue());
            }
            else if(StringUtil.equals(DictCodeList.平安资产类型_拆借.getValue(), finprodType2)){
            list1.add(DictCodeListExt1.金融产品类型_投管用_拆入.getValue());
            list1.add(DictCodeListExt1.金融产品类型_投管用_拆出.getValue());
            }


            else if(StringUtil.equals(DictCodeListExt2.平安资产类型_股票交易.getValue(), finprodType2)){
            list1.add(DictCodeListExt1.金融产品类型_投管用_拆入.getValue());
            list1.add(DictCodeListExt1.金融产品类型_投管用_拆出.getValue());
            }



            else if(StringUtil.equals(DictCodeListExt2.平安资产类型_外汇即期.getValue(), finprodType2)){
            list1.add(DictCodeListExt1.金融产品类型_投管用_即期.getValue());
            }
            else if(StringUtil.equals(DictCodeListExt2.平安资产类型_外汇远期.getValue(), finprodType2)){
            list1.add(DictCodeListExt1.金融产品类型_投管用_远期.getValue());
            }
            else if(StringUtil.equals(DictCodeListExt2.平安资产类型_外汇掉期.getValue(), finprodType2)){
            list1.add(DictCodeListExt1.金融产品类型_投管用_掉期.getValue());
            }
            else if(StringUtil.equals(DictCodeListExt2.平安资产类型_利率互换.getValue(), finprodType2)){
            list1.add(DictCodeListExt1.金融产品类型_投管用_利率互换.getValue());
            }
            else if(StringUtil.equals(DictCodeListExt2.平安资产类型_债券借贷.getValue(), finprodType2)){
            list1.add(DictCodeListExt1.金融产品类型_投管用_债券借贷融入.getValue());
            list1.add(DictCodeListExt1.金融产品类型_投管用_债券借贷融出.getValue());
            }
            else if(StringUtil.equals(DictCodeListExt2.平安资产类型_股票.getValue(), finprodType2)){
            list1.add(DictCodeListExt1.金融产品类型_投管用_股票.getValue());
            }
            else if(StringUtil.equals(BussinessDictCodeList.金融产品类型_投管用_一级投标债券.getValue(), finprodType2)){
            list1.add(BussinessDictCodeList.金融产品类型_投管用_一级投标债券.getValue());
            }
            //交易方向买入卖出
            if( list1.isEmpty() ){
            //                if( StringUtil.equals((String)pageParam.get("ps") , DictCodeList.交易方向_买入.getValue())
            //                        ||StringUtil.equals((String)pageParam.get("ps") , DictCodeList.交易方向_卖出.getValue()) ){
            //                    list1.add(DictCodeListExt1.金融产品类型_投管用_存款.getValue());
            //                    list1.add(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
            //                    list1.add(DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
            //                }
            }//finprodType2不为空时
            /* else{
            boolean flag=false;
            for(String finprodType2 : finprodType2ListOld){
            if(StringUtil.equals( DictCodeList.平安资产类型_质押式回购.getValue(), finprodType2 )
            || StringUtil.equals( DictCodeList.平安资产类型_拆借.getValue(), finprodType2 ) ){
            flag=true;
            }
            }
            if(flag){
            //                    pageParam.put("busiPs", (String)pageParam.get("ps"));
            }*/
            }
//            pageParam.put("profitType", list2);
          //交易方向为利率互换 转换
            if(StringUtil.isNotEmpty((String)pageParam.get("ps"))){
            	if( StringUtil.equals((String)pageParam.get("ps") , DictCode.互换方式.getValue()+DictCodeListExt2.互换方式_收取固定.getValue()) ){
            		pageParam.put("ps", DictCodeListExt2.互换方式_收取固定.getValue());
            		pageParam.put("IRSps", DictCodeListExt2.互换方式_收取固定.getValue());
                }
                if( StringUtil.equals((String)pageParam.get("ps") , DictCode.互换方式.getValue()+DictCodeListExt2.互换方式_收取浮动.getValue()) ){
                	pageParam.put("ps", DictCodeListExt2.互换方式_收取浮动.getValue());
                	pageParam.put("IRSps", DictCodeListExt2.互换方式_收取浮动.getValue());
                }
                pageParam.put("PS_T01", DictCodeList.交易方向_买入.getValue());
            }
            pageParam.put("finprodType2ListPre", list1);
            pageParam.put("finprodType2List", finprodType2ListOld);
//            pageParam.put("list3", list3);
            }
        //ADD BY GAOPENG 20191126 增加债券和交易方向 start 
        pageParam.put("finType_zq", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        pageParam.put("ps_49", DictCodeListExt3.交易方向_债转股.getValue());
        //ADD BY GAOPENG 20191126 增加债券和交易方向 end 
        pageParam.put("IRSType_01", DictCodeListExt2.互换方式_收取固定.getValue());
        //pageParam.put("IRSType_01", DictCodeListExt2.互换方式_收取固定.getValue());
        pageParam.put("IRSType_02", DictCodeListExt2.互换方式_收取浮动.getValue());
        pageParam.put("ASSET_TYPE_07", DictCodeList.平安资产类型_拆借.getValue());
        pageParam.put("ASSET_TYPE_08", DictCodeList.平安资产类型_质押式回购.getValue());
        pageParam.put("ASSET_TYPE_10", DictCodeList.平安资产类型_买断式回购.getValue());
        pageParam.put("ASSET_TYPE_12", DictCodeListExt2.平安资产类型_债券借贷.getValue());
        pageParam.put("ASSET_TYPE_16", DictCodeListExt2.平安资产类型_利率互换.getValue());
        pageParam.put("BACK_LEND_DIRECTION", DictCode.回购拆借方向.getValue());
        pageParam.put("IRS_TYPE", DictCode.互换方式.getValue());
        pageParam.put("PS", DictCode.交易方向.getValue());
        if(StringUtil.equals("99", (String)pageParam.get("entrustStatus"))){
            pageParam.put("entrustStatus1", DictCodeList.委托状态_待接收.getValue());
            pageParam.put("entrustStatus2", DictCodeList.委托状态_已接收.getValue());
        }
        pageParam.put("TRADE_PLACE_05",DictCodeListExt1.交易场所_机构间私募系统.getValue());
        pageParam.put("TRADE_PLACE_01",DictCodeListExt1.交易场所_银行间.getValue());
        pageParam.put("TRADE_PLACE_09",DictCodeListExt1.交易场所_OTC场外.getValue());
        pageParam.put("TRADE_PLACE_03",DictCodeListExt1.交易场所_上海证券交易所.getValue());
        pageParam.put("TRADE_PLACE_04",DictCodeListExt1.交易场所_深圳证券交易所.getValue());
    }

    /**
	 * 指令未接收查询
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	public Page<TrdCommandReceive001Dto> otherSelectForPage(Map<String, Object> pageParam){
        setOtherParams(pageParam);
        return restTemplate.postForPage(
	            ServiceApplication.TRADE_SERVICE_NAME + "/TrdCommandReceive001/otherFindPage", pageParam,
	            TrdCommandReceive001Dto.class);
	}

    public void setOtherParams(Map<String, Object> pageParam) {
        pageParam.put("loginUser", LoginUserUtil.getUserName());
        pageParam.put("entrustAwait", DictCodeList.委托状态_待接收.getValue());
        String finprodType2Old = (String)pageParam.get("finprodType2");
//        List<String> list1=new ArrayList<>();
//        List<String> list2=new ArrayList<>();
//        List<String> list3=new ArrayList<>();
        List<String> finprodType2ListOld = StringUtil.splitToList(GlobalConstant.COMMA, finprodType2Old);
//        if (StringUtil.isNotEmpty(finprodType2Old)) {
//            List<String> finprodType2ListOld = StringUtil.splitToList(GlobalConstant.COMMA, finprodType2Old);
//            for(String finprodType2 : finprodType2ListOld){
//                //为不同资产类型赋查询条件
//                if(StringUtil.equals(DictCodeList.平安资产类型_债券.getValue(), finprodType2)){
//                    list1.add(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
//                }
//                else if(StringUtil.equals(DictCodeList.平安资产类型_基金.getValue(), finprodType2)){
//                    list1.add(DictCodeListExt1.金融产品类型_投管用_基金.getValue());
//                }
//                else if(StringUtil.equals(DictCodeList.平安资产类型_计划类资产_利率型.getValue(), finprodType2)){
//                    list3.add(DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
//                    list2.add(DictCodeList.收益类型_收益型.getValue());
//                }
//                else if(StringUtil.equals(DictCodeList.平安资产类型_计划类资产_净值型.getValue(), finprodType2)){
//                    list3.add(DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
//                    list2.add(DictCodeList.收益类型_净值型.getValue());
//                }
//                else if(StringUtil.equals(DictCodeList.平安资产类型_计划类资产_货币型.getValue(), finprodType2)){
//                    list3.add(DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
//                    list2.add(DictCodeList.收益类型_货币型.getValue());
//                }
//                else if(StringUtil.equals(DictCodeList.平安资产类型_存款.getValue(), finprodType2)){
//                    list1.add(DictCodeListExt1.金融产品类型_存款.getValue());
//                }
//                else if(StringUtil.equals(DictCodeList.平安资产类型_项目类资产.getValue(), finprodType2)){
//                    list1.add(DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
//                }
//                else if(StringUtil.equals(DictCodeList.平安资产类型_质押式回购.getValue(), finprodType2)){
//                    list1.add(DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue());
//                    list1.add(DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue());
//                }
//                else if(StringUtil.equals(DictCodeList.平安资产类型_买断式回购.getValue(), finprodType2)){
//                    list1.add(DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue());
//                    list1.add(DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue());
//                }
//                else if(StringUtil.equals(DictCodeList.平安资产类型_拆借.getValue(), finprodType2)){
//                    list1.add(DictCodeListExt1.金融产品类型_投管用_拆入.getValue());
//                    list1.add(DictCodeListExt1.金融产品类型_投管用_拆出.getValue());
//                }
//                else if(StringUtil.equals(DictCodeListExt2.平安资产类型_外汇即期.getValue(), finprodType2)){
//                    list1.add(DictCodeListExt1.金融产品类型_投管用_即期.getValue());
//                }
//                else if(StringUtil.equals(DictCodeListExt2.平安资产类型_外汇远期.getValue(), finprodType2)){
//                    list1.add(DictCodeListExt1.金融产品类型_投管用_远期.getValue());
//                }
//                else if(StringUtil.equals(DictCodeListExt2.平安资产类型_外汇掉期.getValue(), finprodType2)){
//                    list1.add(DictCodeListExt1.金融产品类型_投管用_掉期.getValue());
//                }
//                else if(StringUtil.equals(DictCodeListExt2.平安资产类型_利率互换.getValue(), finprodType2)){
//                    list1.add(DictCodeListExt1.金融产品类型_投管用_利率互换.getValue());
//                }
//                else if(StringUtil.equals(BussinessDictCodeList.金融产品类型_投管用_一级投标债券.getValue(), finprodType2)){
//                    list1.add(BussinessDictCodeList.金融产品类型_投管用_一级投标债券.getValue());
//                }
//            }
          //交易方向买入卖出
//            if( finprodType2ListOld.isEmpty() ){
//                if( StringUtil.equals((String)pageParam.get("ps") , DictCodeList.交易方向_买入.getValue())
//                        ||StringUtil.equals((String)pageParam.get("ps") , DictCodeList.交易方向_卖出.getValue()) ){
//                	finprodType2ListOld.add(DictCodeListExt1.金融产品类型_投管用_存款.getValue());
//                	finprodType2ListOld.add(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
//                	finprodType2ListOld.add(DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
//                }
//            }//finprodType2不为空时
//            else{
//                boolean flag=false;
//                for(String finprodType2 : finprodType2ListOld){
//                    if(StringUtil.equals( DictCodeList.平安资产类型_质押式回购.getValue(), finprodType2 )
//                        || StringUtil.equals( DictCodeList.平安资产类型_拆借.getValue(), finprodType2 ) ){
//                        flag=true;
//                    }
//                }
//                if(flag){
////                    pageParam.put("busiPs", (String)pageParam.get("ps"));
//                }
//            }
//            pageParam.put("profitType", list2);
          //交易方向为利率互换 转换
            if(StringUtil.isNotEmpty((String)pageParam.get("ps"))){
            	if( StringUtil.equals((String)pageParam.get("ps") , DictCode.互换方式.getValue()+DictCodeListExt2.互换方式_收取固定.getValue()) ){
            		pageParam.put("ps", DictCodeListExt2.互换方式_收取固定.getValue());
            		pageParam.put("IRSps", DictCodeListExt2.互换方式_收取固定.getValue());
                }
                if( StringUtil.equals((String)pageParam.get("ps") , DictCode.互换方式.getValue()+DictCodeListExt2.互换方式_收取浮动.getValue()) ){
                	pageParam.put("ps", DictCodeListExt2.互换方式_收取浮动.getValue());
                	pageParam.put("IRSps", DictCodeListExt2.互换方式_收取浮动.getValue());
                }
                pageParam.put("PS_T01", DictCodeList.交易方向_买入.getValue());
            }
            //ADD BY GAOPENG 20191126 增加债券和交易方向 start 
            pageParam.put("finType_zq", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
            pageParam.put("ps_49", DictCodeListExt3.交易方向_债转股.getValue());
            //ADD BY GAOPENG 20191126 增加债券和交易方向 end
            pageParam.put("IRSType_01", DictCodeListExt2.互换方式_收取固定.getValue());
            pageParam.put("IRSType_02", DictCodeListExt2.互换方式_收取浮动.getValue());
            pageParam.put("ASSET_TYPE_07", DictCodeList.平安资产类型_拆借.getValue());
            pageParam.put("ASSET_TYPE_08", DictCodeList.平安资产类型_质押式回购.getValue());
            pageParam.put("ASSET_TYPE_10", DictCodeList.平安资产类型_买断式回购.getValue());
            pageParam.put("ASSET_TYPE_12", DictCodeListExt2.平安资产类型_债券借贷.getValue());
            pageParam.put("ASSET_TYPE_16", DictCodeListExt2.平安资产类型_利率互换.getValue());
            pageParam.put("BACK_LEND_DIRECTION", DictCode.回购拆借方向.getValue());
            pageParam.put("IRS_TYPE", DictCode.互换方式.getValue());
            pageParam.put("PS", DictCode.交易方向.getValue());
            pageParam.put("finprodType2List", finprodType2ListOld);
            pageParam.put("TRADE_PLACE_05",DictCodeListExt1.交易场所_机构间私募系统.getValue());
            pageParam.put("TRADE_PLACE_01",DictCodeListExt1.交易场所_银行间.getValue());
            pageParam.put("TRADE_PLACE_09",DictCodeListExt1.交易场所_OTC场外.getValue());
            pageParam.put("TRADE_PLACE_03",DictCodeListExt1.交易场所_上海证券交易所.getValue());
            pageParam.put("TRADE_PLACE_04",DictCodeListExt1.交易场所_深圳证券交易所.getValue());
//            pageParam.put("list3", list3);
        }

    /**
     * 指令未接收查询
     * @param map entrustAwait 委托状态 true为已接受  false为待接收
     * @return
     */
    public List<TrdCommandReceive001Dto> otherSelectList(Map<String, Object> map,boolean entrustAwait){
        map.put("loginUser", LoginUserUtil.getUserName());
        if(entrustAwait){
            map.put("isReceive",true);
        	setParams(map);
        }else{
            map.put("isReceive",false);
        	setOtherParams(map);
        }

        return restTemplate.postForList(
                ServiceApplication.TRADE_SERVICE_NAME + "/TrdCommandReceive001/otherSelectList", map,
            TrdCommandReceive001Dto.class);
    }
	
	/**
	 * 市场代码的联想查询
	 * @param finprodMarketId
	 * @return
	 */
	public List<SuggestDto> getFinprodMarketIds(String finprodMarketId){
		return restTemplate.getForList(
				ServiceApplication.TRADE_SERVICE_NAME + "/TrdCommandReceive001/getFinprodMarketIds?finprodMarketId="+finprodMarketId,
	            SuggestDto.class);
	}
	
	/**
	 * 根据委托代码查询委托完成状况
	 * @param entrustId
	 * @return
	 */
	public List<TrdCommandReceive001Dto> getEtrInvest(String entrustId){
		return restTemplate.getForList(ServiceApplication.TRADE_SERVICE_NAME
    			+ "/TrdCommandReceive001/getEtrInvest?entrustId="+entrustId,TrdCommandReceive001Dto.class);		
	}
   
	/**
	 * 接收委托
	 */
	public List<ValidMessage> receiveEntrust(Map<String,Object> param){
		return restTemplate.postForList(
	    	ServiceApplication.TRADE_SERVICE_NAME + "/TrdCommandReceive001/receiveEntrust", param,ValidMessage.class);
	}
    
	/**
	 * 退回委托
	 */
	public List<ValidMessage> returnEntrust(Map<String,Object> param){
		return restTemplate.postForList(
	            ServiceApplication.TRADE_SERVICE_NAME + "/TrdCommandReceive001/refuseEntrust", param,ValidMessage.class);
	}
	
	/**
    * 导出 
    */
	@Override
	protected void buildExportParam(ExportParam exportParam) {
        String tab = (String) exportParam.getSelectParam().get("tab");
        if (StringUtil.equals(exportParam.getSelectParam().get("cstpType"),"cstp")){
            exportParam.config("/com/joyintech/tams/invest/trade/cstp/conf/export_conf_1.xml").dataSource(DataSourceType.MASERT);
        }else {
            // 未接收导出
            if (StringUtil.equals("1", tab)) {
                exportParam.config(
                        "/com/joyintech/tams/invest/trade/investtrade/commandreceive/conf/export_conf_await.xml");
            } else {
                // 已接收导出
                exportParam
                        .config("/com/joyintech/tams/invest/trade/investtrade/commandreceive/conf/export_conf.xml");
            }
        }
	}
	/**
	 * 
	 */
	@Override
	public Page<TrdCommandReceive001Dto> selectPageData(Map<String, Object> selectParam) {
		// TODO Auto-generated method stub
		//得到tab选项卡
		String tab=(String)selectParam.get("tab");
        if (StringUtil.equals(selectParam.get("cstpType"),"cstp")) {
            Page<TrdCommandReceive001Dto> trdCommandReceive001DtoPage = this.selectForPageFromCstp(selectParam);
            String isCstp = (String) selectParam.get("isCstp");
            if(Boolean.parseBoolean(isCstp)){
                cstpSelect001Service.findEntrustPageByCstp(selectParam,trdCommandReceive001DtoPage);
            }
            return trdCommandReceive001DtoPage;
        }else {
            //未接收tab
            if(StringUtil.equals("1", tab)){
                return otherSelectForPage(selectParam);
            }//未接收
            else{
                return selectForPage(selectParam);
            }
        }
	}
	
	/**
     * {@inheritDoc}
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(StringUtil.equals("entrustAmt", propName) || StringUtil.equals("proAvailableCash", propName) || StringUtil.equals("finshAmt", propName)) {
            cell.setCellValue(value.toString());
        }
    }
    
	/**
	 * 操作check
	 * <功能详细描述>
	 * @param dto
	 * @return
	 * @see
	 * @since 1.0
	 */
	public List<ValidMessage> check(TrdCommandReceive001Dto dto){
	    List<ValidMessage> list=new ArrayList<>();
	    if(StringUtil.isNotEmpty(dto.getOperateType())){
			String pageId="";
			//存款
			if(StringUtil.equals(DictCodeList.平安资产类型_存款.getValue(), dto.getFinprodType2())){
				pageId="TrdDepositInvestTrade002";
			}
			//拆借
			else if(StringUtil.equals(DictCodeList.平安资产类型_拆借.getValue(), dto.getFinprodType2())){
				pageId="TrdLendInvestTrade002";
			}
			//基金
			else if(StringUtil.equals(DictCodeList.平安资产类型_基金.getValue(), dto.getFinprodType2())){
				pageId="TrdFundInvestTrade002";
			}
			//计划类净值型
			else if(StringUtil.equals(DictCodeList.平安资产类型_计划类资产_净值型.getValue(), dto.getFinprodType2())){
				pageId="TrdPlanNetInvestTrade002";
			}
			//计划类货币型
			else if(StringUtil.equals(DictCodeList.平安资产类型_计划类资产_货币型.getValue(), dto.getFinprodType2())){
				pageId="TrdPlanCcyInvestTrade002";
			}
			//计划类利率型
			else if(StringUtil.equals(DictCodeList.平安资产类型_计划类资产_利率型.getValue(), dto.getFinprodType2())){
				pageId="TrdPlanRateInvestTrade002";
			}
			//股票
			else if(StringUtil.equals(DictCodeListExt2.平安资产类型_股票.getValue(), dto.getFinprodType2())){
				pageId="TrdStock002";
			}
			//项目类
			else if(StringUtil.equals(DictCodeList.平安资产类型_项目类资产.getValue(), dto.getFinprodType2())){
				pageId="TrdProjectInvestTrade002";
			}
			//外汇即期
			else if(StringUtil.equals(DictCodeListExt2.平安资产类型_外汇即期.getValue(), dto.getFinprodType2())){
				pageId="TrdSpotexInvestTrade002";
			}
			//外汇远期
			else if(StringUtil.equals(DictCodeListExt2.平安资产类型_外汇远期.getValue(), dto.getFinprodType2())){
				pageId="TrdForwexInvestTrade002";
			}
			//外汇掉期
			else if(StringUtil.equals(DictCodeListExt2.平安资产类型_外汇掉期.getValue(), dto.getFinprodType2())){
				pageId="TrdFxswapInvestTrade002";
			}
			//债券借贷
			else if(StringUtil.equals(DictCodeListExt2.平安资产类型_债券借贷.getValue(), dto.getFinprodType2())){
				pageId="TrdBondLendInvestTrade002";
			}
			//利率互换
			else if(StringUtil.equals(DictCodeListExt2.平安资产类型_利率互换.getValue(), dto.getFinprodType2())){
				pageId="TrdIRSTrade002";
			}//债券交易
			else if(StringUtil.equals(DictCodeList.平安资产类型_债券.getValue(), dto.getFinprodType2())){
				pageId = "TrdBondInvestTrade002";
			}
	        if(StringUtil.equals("trdTrade", dto.getOperateType())){
				if(StringUtil.equals(DictCodeList.委托状态_已完成.getValue(), dto.getEntrustStatus())
						|| StringUtil.equals(DictCodeList.委托状态_已关闭.getValue(), dto.getEntrustStatus())){
					list.add(new ValidMessage("",getMessage("TRD_W_0001",SysDataUtil.getNameFromDataDict(DictCode.委托状态,dto.getEntrustStatus()),"交易录单")) );
				}
				//验证是否存在流程中数据				
				if(StringUtil.isNotEmpty(dto.getEntrustId())
						&& StringUtil.isNotEmpty(dto.getFinprodType2())){
//					//非债券 回购类型验证 （债券 回购 自身会check流程中额度与已完成之和）
//					if(!StringUtil.equals(DictCodeList.平安资产类型_债券.getValue(), dto.getFinprodType2())
//							&&!StringUtil.equals(DictCodeList.平安资产类型_买断式回购.getValue(), dto.getFinprodType2())
//							&&!StringUtil.equals(DictCodeList.平安资产类型_质押式回购.getValue(), dto.getFinprodType2())
//					){
						
						String str = tradeCommonService.check(dto.getEntrustId(), pageId);
						if(StringUtil.isNotEmpty(str)){
							ValidMessage validMessage = new ValidMessage(str);
							list.add(validMessage);
						}
//					}
				else{
						BigDecimal entrustNum = tradeCommonService.getEntrustAmtByEntrustId(dto.getEntrustId());
						BigDecimal wflPrinAmt = tradeCommonService.getWflPrinAmt(dto.getEntrustId(), dto.getFinprodType2(), null);
						if(entrustNum!=null&&entrustNum.compareTo(wflPrinAmt) != 1) {
							ValidMessage validMessage = new ValidMessage("当前交易流程交易额度与已完成额度之和已等于委托额度");
							list.add(validMessage);
						}
					}
				}
			}else  if(StringUtil.equals("refuce", dto.getOperateType())){
				String str = tradeCommonService.check(dto.getEntrustId(), pageId);
				if(StringUtil.isNotEmpty(str)){
					ValidMessage validMessage = new ValidMessage(str);
					list.add(validMessage);
				}
			}
	    }
	    return list;
	}

	public boolean isTradeRdCommandReceive(){
        String is_trade_rdCommandReceive = sysParaMapper.selectParamValue("is_trade_rdCommandReceive");
        if(StringUtils.isNotBlank(is_trade_rdCommandReceive)){
            if(StringUtils.equals(is_trade_rdCommandReceive,DictCodeListExt1.是或否_是.getValue())){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
	
}
