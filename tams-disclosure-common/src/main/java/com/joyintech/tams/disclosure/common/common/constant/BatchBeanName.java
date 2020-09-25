/*
 * 文件名：BatchBeanName.java 版权：Copyright 2016_2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统 修改人：qiangchengxi 修改时间：2017年4月19日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.common.common.constant;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.util.StringUtil;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author qiangchengxi
 * @version 1.0 2017年4月19日
 * @see BatchBeanName
 * @since 1.0
 */
public enum BatchBeanName {

//    /**
//     * 业务明细数据自动检查报送
//     */
//    业务明细数据自动检查报送("1", "equityBusiInfoAutoSendCBService", "业务明细数据自动检查报送"),
//
//    /**
//     * 分类总量数据自动检查报送
//     */
//    分类总量数据自动检查报送("2", "classifyTotalInfoAutoSendCBService", "分类总量数据自动检查报送"),

    /**
     * 投资者身份信息数据自动检查报送
     */
    投资者身份信息数据自动检查报送("1", "equityInfoAutoSendCBService", "投资者身份信息数据自动检查报送"),
    /**
     * 投资者持有信息数据自动检查报送
     */
    投资者持有信息数据自动检查报送("2", "investorsHoldInfoRegistAutoSendCBService", "投资者持有信息数据自动检查报送"),
    /**
     * 投资者持有信息登记数据自动生成
     */
    投资者持有信息登记数据自动生成("3", "getInvestorsHoldInfoRegistAutoSendCBService", "投资者持有信息登记数据自动生成"),
    /**
     * 报送前检查自动检查
     */
    报送前检查自动检查("4", "checkReportAutoSendCheckService", "报送前检查自动检查"),
   
    
    /**
     * 银行月度统计表
     */
    银行月度统计表("5", "MonthreportCBService", "银行月度统计表"),
    /**
     * 自动生成投资者持有信息批处理
     */
    自动生成投资者持有信息批处理("6", "generateInvestorsHoldInfoAutoSendCBService", "自动生成投资者持有信息批处理"),
    /**
     * 数据归档批处理
     */
    数据归档批处理("7", "reportDataBackupService", "数据归档批处理"),
    /**
     * 信息披露生成报表并发送批处理
     */
    信息披露生成报表并发送批处理("1000", "infoDisclosureGenAndSendService", "信息披露生成报表并发送批处理"),
    /**
     * 读取作业信息并生成待处理信息
     */
    读取作业信息并生成待处理信息("1001", "infoDisclosureReadAndGenService", "读取作业信息并生成待处理信息"),
    
    
    
    /**
     * 信息披露生成报表并发送批处理
     */
    信息披露扫描作业情况批处理("1002", "InfoDisclosureQuartzRrunner", "信息披露扫描作业情况"),
    /**
     * 自动确认批处理
     */
    自动确认批处理("1003", "infoDiscAutoConfirmService", "自动确认批处理"),
    /**
     * 自动披露批处理
     */
    自动披露批处理("1004", "infoDiscAutoRevealTask", "自动披露批处理"),
    /**
     * 预处理作业信息
     */
    预处理作业信息("1005", "infoDiscPreReadAndGenService", "预处理作业信息"),
    /**
     * 生成待处理详情
     */
    生成待处理详情("1006", "infoDiscGenDetailFileService", "生成待处理详情"),
    /**
     * 生成待处理详情
     */
    投资组合详细批处理("1009", "infoDiscInvestGroupDetailTask", "投资组合详细批处理"),
    
    /**
     * 生成待处理详情
     */
    复杂生成产品指标表("1008", "disReportProdIndexRunner", "复杂生成产品指标表"),
    
    
    /**
     * 生成待处理详情
     */
    复杂生成资产指标表("1007", "disReportAssetIndexRunner", "复杂生成资产指标表"),
    
    /**
     * 生成产品申赎信息批处理
     */
    生成产品申赎信息批处理("1010", "infoDisProdtrdTask", "生成产品申赎信息批处理"),
    /**
     * 生成产品申赎信息批处理
     */
    生成确认标识批处理("1011", "DisDoReadyRunner", "生成数据就位标识批处理"),
    ;

    /**
     * taskId
     */
    private String taskId;

    /**
     * beanName
     */
    private String beanName;

    /**
     * 批处理名称
     */
    private String batchName;

    /**
     * @param beanName 批的beanName
     * @param batchName 批的名称
     */
    BatchBeanName(String taskId, String beanName, String batchName) {
        this.taskId = taskId;
        this.beanName = beanName;
        this.batchName = batchName;
    }

    public String getBeanName() {
        return beanName;
    }

    /**
     * 获取 taskId
     * @return taskId.
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 获取 batchName
     * @return batchName.
     */
    public String getBatchName() {
        return batchName;
    }

    /**
     * 获取所有自动检查报送批处理列表
     *
     * @return 所有自动检查报送批处理列表
     * @see
     * @since 1.0
     */
    public static List<BatchBeanName> getAutoSendCBBatchList() {
        List<BatchBeanName> autoSendCBBatchList = new ArrayList<BatchBeanName>();
        autoSendCBBatchList.add(BatchBeanName.投资者身份信息数据自动检查报送);
        autoSendCBBatchList.add(BatchBeanName.投资者持有信息数据自动检查报送);
        return autoSendCBBatchList;
    }

    /**
     * 根据taskId获取批处理名称
     *
     * @param taskId taskId
     * @return 批处理名称
     * @see
     * @since 1.0
     */
    public static String getBatchNameByTaskId(String taskId) {
        for (BatchBeanName batchBeanName : BatchBeanName.values()) {
            if (StringUtil.equals(taskId, batchBeanName.getTaskId())) {
                return batchBeanName.getBatchName();
            }
        }

        return StringUtil.EMPTY;
    }
}
