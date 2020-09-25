/*
 * 文件名：LoadOdsFileTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.interf.ods.runner;

import org.springframework.stereotype.Service;

import com.joyintech.tams.invest.interf.constant.InterfServiceCaller;
import com.joyintech.tams.invest.batch.interf.runner.LoadOtherDataTask;


/**
 * ODS-获取每日托管流水定时任务
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月1日
 * @see LoadOdsAccountCashTask
 * @since 1.0
 */
@Service("loadOdsAccountCashTask")
public class LoadOdsAccountCashTask extends LoadOtherDataTask {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCaller() {
        return InterfServiceCaller.获取每日托管流水.code();
    }
}
