/*
 * 文件名：TemplateName.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：何阳阳
 * 修改时间：2017年11月20日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
 
package com.joyintech.tams.disclosure.common.common.constant;


/**
 * 导入模板文件名枚举类
 * <pre>
 * 由于中文模板名可能会因为服务器编码问题导致乱码，
 * 所有添加此类来决定英文模板名和画面的对应关系
 * </pre>
 * @author 何阳阳
 * @version 1.0 2017年11月20日
 * @see TemplateName
 * @since 1.0
 */
public enum TemplateName {
    /**
     * 产品费率信息导出模板
     */
    产品费率信息导出导入模板("ProRateInfo001", "ProRate.xlsx"),
    /**
     * 产品登记编码管理导入导出模板
     */
    产品登记编码管理导入导出模板("ProRegistCodeManage001", "ProRegistCode.xlsx"),
    /**
     * 基期信息导入导出模板
     */
    基期信息导入导出模板("EquityBaseInfo001", "EquityBaseInfo.xlsx"),
    /**
     * 分类总量信息登记导入模板
     */
    分类总量信息登记导入模板("ClassifyTotalInfoRegist001", "ClassifyTotalInfoRegistIn.xlsx"),
    /**
     * 汇率行情导入导出模板
     */
    汇率行情导入导出模板("Exchangerate001", "Exchangerate.xlsx"),
    /**
     * 投资者身份信息登记导入模板
     */
    投资者身份信息登记导入模板("EquityInfoRegist001", "EquityInfoRegist.xlsx"),
    /**
     * 投资者身份信息维护导入模板
     */
    投资者身份信息维护导入模板("EquityInfo001", "EquityInfo.xlsx"),
    /**
     * 业务明细信息登记导入模板
     */
    业务明细信息登记导入模板("EquityBusiInfo001", "EquityBusiInfo.xlsx"),
    /**
     * 业务明细信息维护导入模板
     */
    业务明细信息维护导入模板("EquityBusinessDetails001", "EquityBusinessDetails.xlsx"),
    /**
     * 投资者持有信息维护导入模板
     */
    投资者持有信息维护导入模板("InvestorsHoldInfo001", "InvestorsHoldInfo001.xlsx"),
    /**
     * 投资者持有信息登记导入模板
     */
    投资者持有信息登记导入模板("InvestorsHoldInfoRegist001", "InvestorsHoldInfoRegist001.xlsx"),
    ;
    
    /**
     * 模板名对应的画面ID
     */
    private String pageId;
    
    /**
     * 模板文件名
     */
    private String fileName;
    
    /**
     * @param pageId 画面ID
     * @param fileName 模板文件名
     */
    TemplateName(String pageId, String fileName) {
        this.pageId = pageId;
        this.fileName = fileName;
    }

    /**
     * 获取 pageId
     * @return pageId.
     */
    public String getPageId() {
        return pageId;
    }
    
    /**
     * 设置 pageId
     * @param pageId pageId
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
    
    /**
     * 获取 fileName
     * @return fileName.
     */
    public String getFileName() {
        return fileName;
    }
    
    /**
     * 设置 fileName
     * @param fileName fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
}
