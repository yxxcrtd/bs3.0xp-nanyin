/*
 * 文件名：LoadOpicsTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年12月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.interf.opics.runner;

import com.joyintech.tams.invest.interf.constant.InterfServiceCaller;
import com.joyintech.tams.invest.batch.interf.runner.LoadOtherDataTask;


/**
 * OPICS定时任务基类
 * 
 * @author zhangrui1
 * @version 1.0 2018年12月27日
 * @see LoadOpicsTask
 * @since 1.0
 */
public class LoadOpicsTask extends LoadOtherDataTask {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCaller() {
        return InterfServiceCaller.OPICS_BATCH.code();
    }

}
