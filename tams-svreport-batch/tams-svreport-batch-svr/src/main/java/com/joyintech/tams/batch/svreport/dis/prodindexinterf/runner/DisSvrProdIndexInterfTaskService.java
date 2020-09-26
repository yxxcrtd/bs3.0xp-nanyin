/*
 * 文件名：DisProdBondsRegistrationInterfTaskService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：信息披露系统V1.0
 * 修改人：ZhangYangFei
 * 修改时间：2019年5月13日
 * 修改内容：新建
 * 系统名称：信息披露系统V1.0
 */
 
package com.joyintech.tams.batch.svreport.dis.prodindexinterf.runner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.batch.svreport.dis.prodindexinterf.service.DisRepProdIndexFileInterfMainService;


/**
 * 产品指标接口数据接入批处理
 * 
 * @author Dongxiangyang
 * @version 1.0 2019年5月13日
 * @see DisSvrProdIndexInterfTaskService
 * @since 1.0
 */
@Service(value = "svrProdIndexInterfTaskService")
@Scope("prototype")
public class DisSvrProdIndexInterfTaskService extends BatchBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DisRepProdIndexFileInterfMainService disRepProdIndexFileInterfMainService;
    /**
     * {@inheritDoc}
     */
    @Override
    public JobExecution execute()
        throws BatchException {
        JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return result;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date bdate=new Date();
        Long bt=bdate.getTime();
        logger.info(BatchBeanName.产品基础指标数据接入.getBatchName()+"批处理启动:"+sdf.format(bdate));
        try {
            SysBatchgrpExecInfo batchExecuteDetail = this.getBatchExecuteDetail();
            List<String> prodList = new ArrayList<String>();
            if (StringUtil.isNotEmpty(batchExecuteDetail.getPrdGroupSeqNo())) {
                prodList = this.getProductIdList();
            }
            disRepProdIndexFileInterfMainService.generateData(batchExecuteInfo.getExecuteDate(),prodList);
            result.setStatus(BatchStatus.COMPLETED);
        }
        catch (Exception e) {
			logger.info(BatchBeanName.产品基础指标数据接入.getBatchName()+"批处理异常" + e.getMessage());
            logger.error("产品基础指标数据接入批处理Error",e);
        }finally{
            Date edate=new Date();
            Long et=edate.getTime();
            Long time=(et-bt)/1000;
            logger.info(BatchBeanName.产品基础指标数据接入.getBatchName()+"批处理结束:"+sdf.format(edate)+"耗时"+time+"秒");    
        }
        return result;
    }

}
