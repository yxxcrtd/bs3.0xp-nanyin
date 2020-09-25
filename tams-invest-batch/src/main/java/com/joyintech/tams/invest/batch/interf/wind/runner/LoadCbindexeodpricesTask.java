/*
 * 文件名：LoadCbindexeodpricesTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.interf.wind.runner;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


/**
 * WIND-中国债券指数日行情数据接入定时任务
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月20日
 * @see LoadCbindexeodpricesTask
 * @since 1.0
 */
@Service("loadCbindexeodpricesTask")
@Scope("prototype")
public class LoadCbindexeodpricesTask extends LoadWindTask {

}
