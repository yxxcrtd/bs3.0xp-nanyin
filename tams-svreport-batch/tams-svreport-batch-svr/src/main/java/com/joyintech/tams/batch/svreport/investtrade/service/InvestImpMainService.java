package com.joyintech.tams.batch.svreport.investtrade.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.batch.svreport.assetinfo.service.AssetInfoImpMainService;
import com.joyintech.tams.common.InvestTradeFileImpService;
import com.joyintech.tams.common.base.entity.ChbInterfConfigDto;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.interf.common.constant.InterfConstants;
import com.joyintech.tams.svreport.interf.chb.investtrade.service.ChbInvestTradeInterf;

/**
 * 资产要素自动接入批处理
 * @author 魏玉强
 * @version 1.0 2019年1月26日
 * @see AssetInfoImpMainService
 * @since 1.0
 */
@Service(value = "investImpMainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class InvestImpMainService extends AutoSendCBBaseService{

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private ChbInvestTradeInterf chbInvestTradeInterf;
	
    @Autowired
    private InvestTradeFileImpService investTradeFileImpService;
    
    /**
     * 功能:根据跑批日自动接入资产交易、交易对手信息
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年2月20日
     * @param batchDate 跑批日
     * @prodList 产品代码
     * void
     */
    public Map<String,List<String>> generateInvestTradeData(Date batchDate,List<String> prodList,boolean isSynchronizeTask){
    	Map<String,List<String>> mapPara = new HashMap<String,List<String>>();	
    	List<String> tradenoList = new ArrayList<String>();   	
    	List<String> prodAddList = new ArrayList<String>(); 	
    	List<String> assetList = new ArrayList<String>(); 
    	// 设置批处理bean
    	setBatchBeanName(BatchBeanName.投资交易信息接口数据接入);
		logInfoBatchStart();
    	try{
    		// 资产交易信息
            ChbInterfConfigDto interfConfig = ChbInterfUtil.queryInterfConfigByInterfid(com.joyintech.tams.common.InterfDefine.投资交易信息接口数据.getCode());
            if (interfConfig == null){
                logger.info("CHB_INTERF_CONFIG表中无配置项！");
            }
            String contentSrc = interfConfig.getContentSrc();
            // 资产交易信息
            Map<String,List<String>> mapParaDeal = new HashMap<String,List<String>>();
            if (InterfConstants.CONTENTSRC_DB.equals(contentSrc)){
                mapParaDeal = chbInvestTradeInterf.generateInvestTradeData(batchDate,prodList,isSynchronizeTask);
            } else if (InterfConstants.CONTENTSRC_FILE_XML.equals(contentSrc)
                    ||InterfConstants.CONTENTSRC_FILE_TXT.equals(contentSrc)) {
                String result = investTradeFileImpService.execute(batchDate,com.joyintech.tams.common.InterfDefine.投资交易信息接口数据.getCode());
                if ("fail".equals(result)){
                    throw new BusinessException("fail");
                }
            } else {
                logger.error("CHB_INTERF_CONFIG表中内容来源配置无效！");
            }
    		// 交易对手信息
//    		Map<String,List<String>> mapParaCounter = chbMstCounterPartyInterf.generateCounterPartyInfoData(batchDate, prodList,isSynchronizeTask);
    		if (!StringUtil.isEmptyList(mapParaDeal.get(InterfConstants.SVR_TRADENOLIST))){
    			tradenoList.addAll(mapParaDeal.get(InterfConstants.SVR_TRADENOLIST));
    		}
//    		if (!StringUtil.isEmptyList(mapParaCounter.get(InterfConstants.SVR_TRADENOLIST))){
//    			tradenoList.addAll(mapParaCounter.get(InterfConstants.SVR_TRADENOLIST));
//    		}
//    		if (!StringUtil.isEmptyList(mapParaDeal.get(InterfConstants.SVR_PRODLIST))){
//    			prodAddList.addAll(mapParaDeal.get(InterfConstants.SVR_PRODLIST));
//    		}
//    		if (!StringUtil.isEmptyList(mapParaCounter.get(InterfConstants.SVR_PRODLIST))){
//    			prodAddList.addAll(mapParaCounter.get(InterfConstants.SVR_PRODLIST));
//    		}
//    		if (!StringUtil.isEmptyList(mapParaDeal.get(InterfConstants.SVR_ASSETLIST))){
//    			assetList.addAll(mapParaDeal.get(InterfConstants.SVR_ASSETLIST));
//    		}
//    		if (!StringUtil.isEmptyList(mapParaCounter.get(InterfConstants.SVR_ASSETLIST))){
//    			assetList.addAll(mapParaCounter.get(InterfConstants.SVR_ASSETLIST));
//    		}
    	}catch (Exception e) {
             logger.error("投资交易表上游数据接入批处理Error",e);
             throw e;
        }
		logger.debug("投资交易表上游数据接入批处理");
		logInfoBatchEnd();
		if(isSynchronizeTask){
			if (!StringUtil.isEmptyList(prodAddList)){
				mapPara.put(InterfConstants.SVR_PRODLIST,prodAddList);
			}
			if (!StringUtil.isEmptyList(assetList)){
				mapPara.put(InterfConstants.SVR_ASSETLIST,assetList);
			}
			if (!StringUtil.isEmptyList(tradenoList)){
				mapPara.put(InterfConstants.SVR_TRADENOLIST,tradenoList);
			}
		}
		return mapPara;
    }

}