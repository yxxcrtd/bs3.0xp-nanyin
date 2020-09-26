/*
 * 文件名：CnBondUpInterfTaskService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：Administrator
 * 修改时间：2017年11月9日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.batch.svreport.common;

import java.beans.Introspector;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.batch.report.autosendcb.common.service.SvrCBBaseService;
import com.joyintech.tams.batch.svreport.assetinfo.service.AssetInfoImpMainService;
import com.joyintech.tams.batch.svreport.baseinfo.service.ThirdPartyInterfMainService;
import com.joyintech.tams.batch.svreport.investtrade.service.InvestImpMainService;
import com.joyintech.tams.batch.svreport.prodduration.service.SvrProdFeeDealInterfMainService;
import com.joyintech.tams.batch.svreport.prodinfo.service.ProdIncomeImpMainService;
import com.joyintech.tams.batch.svreport.prodinfo.service.ProdPlanImpMainService;
import com.joyintech.tams.batch.svreport.prodinfo.service.SvrProdDeclareInfoImpMainService;
import com.joyintech.tams.batch.svreport.prodinfo.service.SvrProdInfoImpMainService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.interf.common.constant.InterfConstants;
import com.joyintech.tams.report.interf.common.service.InterfService;
import com.joyintech.tams.svreport.interf.chb.assetbasis.service.SvrAssetBasisInterf;
import com.joyintech.tams.svreport.interf.chb.bottomassetposition.service.ChbBotAstPosInterf;
import com.joyintech.tams.svreport.interf.chb.proddeal.service.ProdDealInterfService;

/**
 * 中债上游接口定时任务处理
 *
 * @author wm
 * @version 1.0 2017年11月9日
 * @see ChbInterfSynchronizeTaskService
 * @since 1.0
 */
@Service("chbInterfSynchronizeTaskService")
public class ChbInterfSynchronizeTaskService extends InterfService {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ChbInterfSynchronizeTaskService.class);
    
    
    @Autowired
    private AssetInfoImpMainService assetInfoImpMainService;

    
    @Autowired
    private InvestImpMainService investImpMainService;
    
    @Autowired
    private ProdDealInterfService prodDealInterfService;
    
    @Autowired
    private ChbBotAstPosInterf chbBotAstPosInterf;
    
    @Autowired
    private  SvrProdInfoImpMainService svrProdInfoImpMainService;
    
    @Autowired
    private  ProdPlanImpMainService prodPlanImpMainService;
    
    @Autowired
    private  ProdIncomeImpMainService prodIncomeImpMainService;
    
    @Autowired
    private SvrProdFeeDealInterfMainService svrProdFeeDealInterfMainService;
    
    @Autowired
    private ThirdPartyInterfMainService thirdPartyInterfMainService;
    
    @Autowired
    private SvrAssetBasisInterf svrAssetBasisInterf;
    
    @Autowired
    private SvrProdDeclareInfoImpMainService svrProdDeclareInfoImpMainService;
    /**
     * 上游接口轮询任务
     * @throws BatchException 
     *
     */
    public void execute() throws BatchException {
    	logger.debug("中债上游信息接口与轮询任务开始:"+DateUtil.getCurrentDate());
    	
    	/**
    	 * 数据接口
    	 */
    	//交易对手接口数据接入
    	thirdPartyInterfMainService.generateMstCounterPartyData(new Date());
    	//产品要素上游接口接入
    	List<String> prodinfo = svrProdInfoImpMainService.generateProdInfoData(new Date(), null);
    	//产品申报要素上游接口接入
    	svrProdDeclareInfoImpMainService.generateProdInfoData(new Date(),null);
        //产品开放计划接口数据接入
    	prodPlanImpMainService.generatePrdOpensch(new Date(), null);
		//产品交易信息接入接入
    	HashMap<String,List<String>> dealMap = prodDealInterfService.generatePrdDealData(null,null,true);
		//产品费用交易接口数据接入
    	HashMap<String,List<String>> feeDealMap = svrProdFeeDealInterfMainService.generatePrdDealData(new Date(), null,true);
		//产品收益率净值接口数据接入
		prodIncomeImpMainService.generatePrdPeriodyieldData(new Date(), null);
    	//资产要素信息接口数据接入
    	Map<String,List<String>> paraAssetInfo = assetInfoImpMainService.generateAssetInfoData(new Date(),null,true);
    	//投资交易信息接口数据接入
    	Map<String,List<String>> paraInvestTrade = investImpMainService.generateInvestTradeData(new Date(),null,true);
    	//底层资产持仓信息接入
    	List<Date> botAstPosList =  chbBotAstPosInterf.generatePtlValutionData(null);
    	//资产基础指标数据接入
    	List<Date> assetIndexList = svrAssetBasisInterf.generateData(null);
    	 
    	/**
    	 * 头寸
    	 */
    	//产品头寸接口
    	if(!StringUtil.isEmptyList(dealMap.get(ProdDealInterfService.DEAL_INTER_DURADEAL))){
    	    List<String> prodIdList = dealMap.get(ProdDealInterfService.DEAL_INTER_DURADEAL);
            if (prodIdList.size() > 500){
                prodIdList = null;
            }
            executeBean("ProdPosition001Service",DateUtil.parse(DateUtil.getSysDate()),prodIdList,null,null);
//    		prodPositionMainService.generatePositionData(DateUtil.parse(DateUtil.getSysDate()), prodIdList);
    	}
    	//费用头寸接口
    	if(!StringUtil.isEmptyList(feeDealMap.get(ProdDealInterfService.DEAL_INTER_FEEDEAL))){
    	    List<String> prodIdList = feeDealMap.get(ProdDealInterfService.DEAL_INTER_FEEDEAL);
            if (prodIdList.size() > 500){
                prodIdList = null;
            }
            executeBean("ProdFeePositionService",DateUtil.parse(DateUtil.getSysDate()),prodIdList,null,null);
//            prodFeePositionMainService.generatePositionData(new Date(), prodIdList);
    	}
    	
    	//资产头寸
    	if(paraInvestTrade.get(InterfConstants.SVR_TRADENOLIST)!=null){
            executeBean("AssetPositionService",DateUtil.parse(DateUtil.getSysDate()),null,null, paraInvestTrade);
//    	    assetPositionService.genAssetPosition(new Date(), paraInvestTrade);
    	} 
    	

        boolean checkFlag = false;//是否执行报送前检查

        // 参数
        Map<String,List<String>> param = new HashMap<String,List<String>>();
        List<String> isSynList = StringUtil.splitToList(GlobalConstant.COMMA, DictCodeList.是否_中债_是.getValue());
        param.put("isSynchronizeTask",isSynList);
    	/**
    	 * 登记
    	 */
        //产品发行申报登记
        if(!StringUtil.isEmptyList(prodinfo)){
            executeBean("ProdIssueAndDecalreRegBatchService",DateUtil.parse(DateUtil.getSysDate()),null,null, param);
//            prodIssueAndDecalreMainService.generateIssueData(new Date(), prodinfo,true);
            checkFlag = true;
        }
        
    	//产品存续交易登记
        List<String> prodIdList = new ArrayList<String>();
    	//存续交易变更产品
    	if(null!=dealMap.get(ProdDealInterfService.DEAL_INTER_DURADEAL))
    		prodIdList.addAll(dealMap.get(ProdDealInterfService.DEAL_INTER_DURADEAL));
    	//费用交易变更产品
    	if(null!=feeDealMap.get(ProdDealInterfService.DEAL_INTER_FEEDEAL))
    		prodIdList.addAll(feeDealMap.get(ProdDealInterfService.DEAL_INTER_FEEDEAL));
    	List<String> updProdList = new ArrayList<String>();
    	for(String prod : prodIdList){
    		if(!updProdList.contains(prod)){
    			updProdList.add(prod);
    		}
    	}
        if (updProdList.size() > 500){
            updProdList = null;
        }
    	if(!StringUtil.isEmptyList(dealMap.get(ProdDealInterfService.DEAL_INTER_DURADEAL))
    			||!StringUtil.isEmptyList(feeDealMap.get(ProdDealInterfService.DEAL_INTER_FEEDEAL))){
            executeBean("ProdDurationRegRegistBatchService",DateUtil.parse(DateUtil.getSysDate()),updProdList,null, null);
//    		prodDurationRegMainService.generateDurationRegData(DateUtil.parse(DateUtil.getSysDate()), updProdList);
    		checkFlag = true;
    	} 
        
    	//资产要素登记
    	if(paraAssetInfo.get(InterfConstants.SVR_ASSETLIST)!=null){
    	    List<String> assetInfoList = paraAssetInfo.get(InterfConstants.SVR_ASSETLIST);
    	    if (assetInfoList.size() > 500){
    	        assetInfoList = null;
    	    }
            executeBean("AssetInfoRegistBatchService",DateUtil.parse(DateUtil.getSysDate()),null,assetInfoList, null);
//    		assetInfoCBMainService.autoRegistAssetInfo(new Date(), null, assetInfoList);
    	} 
    	//投资交易登记
    	if(paraInvestTrade.get(InterfConstants.SVR_TRADENOLIST)!=null){
            List<String> tradeNoList = paraInvestTrade.get(InterfConstants.SVR_TRADENOLIST);
            if (tradeNoList.size() > 500){
                tradeNoList = null;
            }
            param.put("tradeNoList",tradeNoList);
            executeBean("InvestTradeRegistBatchService",DateUtil.parse(DateUtil.getSysDate()),null,null, param);
//    		investTradeCBMainService.autoRegistInvestTrade(new Date(), null, tradeNoList);
    		checkFlag = true;
    	}
    	
       	if(!StringUtil.isEmptyList(dealMap.get(ProdDealInterfService.DEAL_INTER_DURADEAL))){
	    	//募集总量登记
            executeBean("ProdRaiseTotalRegistBatchService",DateUtil.parse(DateUtil.getSysDate()),null,null, null);
//	    	prodRaiseTotalMainService.generateRegistData(new Date(), null);
	    	checkFlag = true;
    	}
      	if(!StringUtil.isEmptyList(dealMap.get(ProdDealInterfService.DEAL_INTER_DURADEAL))
    			||!StringUtil.isEmptyList(feeDealMap.get(ProdDealInterfService.DEAL_INTER_FEEDEAL))){
	    	//终止登记
            executeBean("ProdTerminationRegistBatchService",DateUtil.parse(DateUtil.getSysDate()),null,null, null);
//	    	prodTerminationMainService.generateRegistData(new Date(), null);
	    	checkFlag = true;
    	}
    	//底层资产持仓登记
    	if(!botAstPosList.isEmpty()){
            executeBean("ChbBotAstPosRegistBatchService",DateUtil.parse(DateUtil.getSysDate()),null,null, null);
//    		chbBotAstPosCBMainService.createRegist(new Date());
    		checkFlag = true;
    	}
    	//资产估值登记
    	if(!assetIndexList.isEmpty()){
            executeBean("ChbAstValRegistBatchService",DateUtil.parse(DateUtil.getSysDate()),null,null, null);
//    		chbAstValCBMainService.createRegist(new Date());
    	}
    	// 报送前检查
    	if(checkFlag){
            executeBean("BeforeSubCheck",DateUtil.parse(DateUtil.getSysDate()),null,null, null);
//    		beforeSubCheck.checkBeforeSub(new Date());
    	}
    		
    	logger.debug("中债上游信息接口与轮询任结束:"+DateUtil.getCurrentDate());
    }

    /**
     * 功能:执行批处理
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月10日
     * @param taskBean 执行的bean
     * @param executeDate 执行日期
     * @param prodList 产品list
     * @param assetList 资产list
     * @return 
     * String
     */
    private String executeBean(String taskBean,Date executeDate,List<String> prodList,List<String> assetList,Map<String,List<String>> param){
        // 获取对应批处理的bean
        Object bean = null;
        try{
            bean = SpringContextUtil.getBean(Introspector.decapitalize(taskBean));
        }
        catch (BeansException e){
            logger.error("批处理{}执行失败，失败原因:找不到Bean:{}!" ,taskBean,taskBean,e.getMessage(),e);
            return "fail";
        }
        if (bean instanceof SvrCBBaseService) {
            SvrCBBaseService chbReportRegistCBBaseService = (SvrCBBaseService) bean;
            Date startDate = new Date();
            Long startTime = startDate.getTime();
            logger.debug("{}:批处理开始,开始时间：{}！", taskBean,
                    DateUtil.format(startDate, DateUtil.DATETIME_FORMAT_WITH_SSS));
            try {
                // 执行对应批处理
                chbReportRegistCBBaseService.execute(executeDate, prodList, assetList,param);
            } catch (BusinessException e) {
                logger.error("批处理{}执行失败，失败原因:{}",taskBean,e.getMessage(), e);
                return "fail";
            }
            Date endDate = new Date();
            Long endTime = endDate.getTime();
            Long timeCost = (endTime - startTime) ;
            logger.debug("{}:批处理结束,结束时间：{},耗时：{}毫秒！", taskBean,
                    DateUtil.format(endDate, DateUtil.DATETIME_FORMAT_WITH_SSS), timeCost);
        } else {
            logger.error("批处理{}执行失败，失败原因:Bean:{}不是对应的报表登记批处理！",taskBean,bean.getClass().getName());
            return "fail";
        }
        return "success";
    }
    

}
