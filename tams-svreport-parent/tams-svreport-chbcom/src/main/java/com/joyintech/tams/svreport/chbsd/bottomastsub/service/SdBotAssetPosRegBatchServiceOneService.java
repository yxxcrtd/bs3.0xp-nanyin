/*
 * 文件名：InvestorsHoldInfoRegistAutoSendCBMainService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：zuoyinyi
 * 修改时间：2018年5月14日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.svreport.chbsd.bottomastsub.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.component.svrreport.chb.dto.ChbBotAstPosSubDto;
import com.joyintech.component.svrreport.chb.service.MsgSendService;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;

/**
 * 底层资产持仓信息登记自动报送 业务service - 按批次
 * @since 1.0.0
 * @version 1.0.0
 * @作者：lingxq
 * @see SdBotAssetPosRegBatchServiceOneService
 */
@Service
public class SdBotAssetPosRegBatchServiceOneService extends AutoSendCBBaseService{

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MsgSendService msgSendServiceImpl;


   /**
    * 单批次处理数据
    * @param handleIndex
    * @param dataCountPerTime
    * @param msgRstList
    * @param sendResult
    * @return
    * @throws Exception 
    */
    public boolean handleDataPerTime(int handleIndex, 
    		                         int dataCountPerTime,List<ChbBotAstPosSubDto> msgRstList,
    		                         List<ChbBotAstPosSubDto> sendResult)  throws Exception{
        logger.info("【底层资产持仓信息登记】直连报送处理开始，批次：" + handleIndex + "  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        boolean hasData = true;
        try {
        	msgSendServiceImpl.sendChbBotAstPosSubMsgs(msgRstList);
       	    sendResult.addAll(msgRstList);
        } catch (Exception e) {
	        logger.error("【底层资产持仓信息登记】直连报送处理异常，批次：" + handleIndex + e.getMessage());
	        throw e;
		}
        logger.info("【底层资产持仓信息登记】直连报送处理结束，批次：" + handleIndex + "  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        return hasData;
    }
}
