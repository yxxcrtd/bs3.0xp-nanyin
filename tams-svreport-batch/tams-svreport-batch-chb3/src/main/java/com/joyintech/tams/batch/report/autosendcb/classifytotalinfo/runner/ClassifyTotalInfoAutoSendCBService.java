///*
// * 文件名：ClassifyTotalInfoAutoSendCBService.java
// * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
// * 描述：中信银行资管系统
// * 修改人：刘二通
// * 修改时间：2017年11月14日
// * 修改内容：新建
// * 系统名称：中债报送系统
// */
// 
//package com.joyintech.tams.report.batch.autosendcb.classifytotalinfo.runner;
//
//import org.joda.time.DateTime;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.batch.core.ExitStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Service;
//
//import com.joyintech.tams.report.batch.autosendcb.classifytotalinfo.service.ClassifyTotalInfoAutoSendCBMainService;
//import com.joyintech.tams.report.batch.common.base.runner.BatchBaseService;
//import com.joyintech.tams.common.base.entity.ChbSysBatchInfo;
//import com.joyintech.tams.report.common.constant.StartMode;
//import com.joyintech.tams.report.framework.util.DateUtil;
//
///**
// * 分类总量数据自动检查报送
// *
// * @author 刘二通
// * @version 1.0 2017年11月14日
// * @see ClassifyTotalInfoAutoSendCBService
// * @since 1.0
// */
//@Service(value = "classifyTotalInfoAutoSendCBService")
//@Scope("prototype")
//public class ClassifyTotalInfoAutoSendCBService extends BatchBaseService {
//
//    /**
//     * 日志
//     */
//    private Logger logger = LoggerFactory.getLogger(getClass());
//    
//    /**
//     * 分类总量数据自动检查报送 业务service
//     */
//    @Autowired
//    private ClassifyTotalInfoAutoSendCBMainService mainService;
//    
//    @Override
//    public String execute() {
//        ChbSysBatchInfo batchInfo = this.getBatchInfo();
//        super.setBatchInfo(batchInfo, getLogger());
//
//        StartMode startMode = StartMode.fromCode(batchInfo.getStartMode());
//        getLogger().log("启动模式：" + startMode.name());
//        getLogger().log(
//            "批处理开始日：" + new DateTime(batchInfo.getBatchStartDate()).toString(
//                DateUtil.DEFAULT_DATE_FORMAT_EN) + "，批处理结束日："
//                        + new DateTime(batchInfo.getBatchEndDate()).toString(
//                            DateUtil.DEFAULT_DATE_FORMAT_EN));
//
//        try {
//            mainService.autoSendCB();
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            getLogger().log(e);
//        }
//
//        return ExitStatus.COMPLETED.toString();
//    }
//}
