/*
 * 文件名：LoadPayhsrcbondencashyieldTask.java
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
 * WIND-收益率曲线表数据接入定时任务
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月20日
 * @see LoadPayhsrcbondencashyieldTask
 * @since 1.0
 */
@Service("loadPayhsrcbondencashyieldTask")
@Scope("prototype")
public class LoadPayhsrcbondencashyieldTask extends LoadWindTask {

}
