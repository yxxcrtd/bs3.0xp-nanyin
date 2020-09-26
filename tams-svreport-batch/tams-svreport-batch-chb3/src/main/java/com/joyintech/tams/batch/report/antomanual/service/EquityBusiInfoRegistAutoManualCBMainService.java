/*
 * 文件名：EquityInfoAutoSendCBMainService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：刘二通
 * 修改时间：2017年12月9日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.batch.report.antomanual.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.submitmanagement.equitybusiinforegist.service.EquityBusiInfoRegist001Service;

/**
 * 投资者明细信息登记自动手工处理 业务service
 *
 * @author yuluan
 * @version 1.0 2019年11月15日
 * @see EquityBusiInfoRegistAutoManualCBMainService
 * @since 1.0
 */
@Service
public class EquityBusiInfoRegistAutoManualCBMainService extends AutoSendCBBaseService {


    /**
     * EquityInfoRegist001Service
     */
    @Autowired
    private EquityBusiInfoRegist001Service equityBusiInfoRegist001Service;

    /**
     * 自动检查报送
     *
     * @see
     * @since 1.0
     */
    public void autoManualCB(SysBatchExecInfo batchExecuteInfo) {

        setBatchBeanName(BatchBeanName.投资者明细信息登记手工处理);
            logInfoBatchStart();
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("holdDateStart", DateUtil.format(batchExecuteInfo.getExecuteDate()));
            map.put("holdDateEnd", DateUtil.format(batchExecuteInfo.getExecuteDate()));
            map.put("sendStatus", "01");
            equityBusiInfoRegist001Service.updateSumStausAll(map);
            logInfoBatchEnd();
    }
}
