/*
 * 文件名：LoadCmsCreditAssertInfoTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.interf.ods.runner;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.invest.interf.constant.InterfServiceCaller;
import com.joyintech.tams.invest.batch.interf.runner.LoadOtherDataTask;


/**
 * ODS-每日上传CMS信贷资产余额信息定时任务
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月20日
 * @see LoadCmsCreditAssertInfoTask
 * @since 1.0
 */
@Service("loadCmsCreditAssertInfoTask")
@Scope("prototype")
public class LoadCmsCreditAssertInfoTask extends LoadOtherDataTask {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCaller() {
        return InterfServiceCaller.每日上传CMS信贷资产余额信息.code();
    }

}
