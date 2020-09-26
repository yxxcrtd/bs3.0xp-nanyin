package com.joyintech.tams.batch.svreport.chbbottomassetposition.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.common.BotAstPosFileImpService;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.common.base.entity.ChbInterfConfigDto;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.interf.common.constant.InterfConstants;
import com.joyintech.tams.svreport.interf.chb.bottomassetposition.service.ChbBotAstPosInterf;

@Service
public class ChbBotAstPosImpMainService  extends AutoSendCBBaseService{

    @Autowired
    private ChbBotAstPosInterf chbBotAstPosInterf;
    
    @Autowired
    private BotAstPosFileImpService botAstPosFileImpService;
    
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
	public void createInfo(Date batchTime) throws BatchException {
        // 设置批处理bean
        setBatchBeanName(BatchBeanName.资管计划底层资产持仓接口数据接入);

        logInfoBatchStart();

        // 检查通过数据入库
        ChbInterfConfigDto interfConfig = ChbInterfUtil.queryInterfConfigByInterfid(com.joyintech.tams.common.InterfDefine.资管计划底层资产持仓接口数据.getCode());
        if (interfConfig == null){
            logger.info("CHB_INTERF_CONFIG表中无配置项！");
        }
        String contentSrc = interfConfig.getContentSrc();
        if (InterfConstants.CONTENTSRC_DB.equals(contentSrc)){
        	//一体化上游数据库接入
        	chbBotAstPosInterf.generatePtlValutionData(batchTime);
        } else if (InterfConstants.CONTENTSRC_FILE_XML.equals(contentSrc)||InterfConstants.CONTENTSRC_FILE_TXT.equals(contentSrc)) {
        	//外部文件接入
        	botAstPosFileImpService.execute(batchTime,com.joyintech.tams.common.InterfDefine.资管计划底层资产持仓接口数据.getCode());
        } else {
            logger.error("CHB_INTERF_CONFIG表中内容来源配置无效！");
        }

		logInfoBatchEnd();
	}
	
	
	
}