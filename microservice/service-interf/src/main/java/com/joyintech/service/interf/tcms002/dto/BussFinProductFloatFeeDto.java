/*
 * 文件名：BussFinProductFloatFeeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.tcms002.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.FinFloatCalcRuleDetail;

/**
 * 浮动管理费业务组件dto
 * 
 * @author changwenbin
 * @version 1.0 2018年11月14日
 * @see BussFinProductFloatFeeDto
 * @since 1.0
 */
public class BussFinProductFloatFeeDto {

    /**
     * 主画面传入的pageId
     */
    private String pageId;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 交易日里
     */
    private String dealCalId;

    /**
     * 费用代码
     */
    private String feeId;

    /**
     * 计费基数
     */
    private String basicBill;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 资产净值日
     */
    private String netAssetValueDay;

    /**
     * 首次计提日
     */
    private Date provisionFirstDay;

    /**
     * 业绩基准规则
     */
    private String floatBaseRule;

    /**
     * 业绩报酬计算规则明细DtoList
     */
    private List<FinFloatCalcRuleDetail> finFloatCalcRuleDetailDtoList;

    /**
     * 获取 pageId
     * 
     * @return pageId.
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * 设置 pageId
     * 
     * @param pageId pageId
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    /**
     * 获取 vdate
     * 
     * @return vdate.
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 vdate
     * 
     * @param vdate vdate
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 获取 mdate
     * 
     * @return mdate.
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 mdate
     * 
     * @param mdate mdate
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 获取 dealCalId
     * 
     * @return dealCalId.
     */
    public String getDealCalId() {
        return dealCalId;
    }

    /**
     * 设置 dealCalId
     * 
     * @param dealCalId dealCalId
     */
    public void setDealCalId(String dealCalId) {
        this.dealCalId = dealCalId;
    }

    /**
     * 获取 feeId
     * 
     * @return feeId.
     */
    public String getFeeId() {
        return feeId;
    }

    /**
     * 设置 feeId
     * 
     * @param feeId feeId
     */
    public void setFeeId(String feeId) {
        this.feeId = feeId;
    }

    /**
     * 获取 basicBill
     * 
     * @return basicBill.
     */
    public String getBasicBill() {
        return basicBill;
    }

    /**
     * 设置 basicBill
     * 
     * @param basicBill basicBill
     */
    public void setBasicBill(String basicBill) {
        this.basicBill = basicBill;
    }

    /**
     * 获取 basis
     * 
     * @return basis.
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 basis
     * 
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }

    /**
     * 获取 netAssetValueDay
     * 
     * @return netAssetValueDay.
     */
    public String getNetAssetValueDay() {
        return netAssetValueDay;
    }

    /**
     * 设置 netAssetValueDay
     * 
     * @param netAssetValueDay netAssetValueDay
     */
    public void setNetAssetValueDay(String netAssetValueDay) {
        this.netAssetValueDay = netAssetValueDay;
    }

    /**
     * 获取 provisionFirstDay
     * 
     * @return provisionFirstDay.
     */
    public Date getProvisionFirstDay() {
        return provisionFirstDay;
    }

    /**
     * 设置 provisionFirstDay
     * 
     * @param provisionFirstDay provisionFirstDay
     */
    public void setProvisionFirstDay(Date provisionFirstDay) {
        this.provisionFirstDay = provisionFirstDay;
    }

    /**
     * 获取 floatBaseRule
     * 
     * @return floatBaseRule.
     */
    public String getFloatBaseRule() {
        return floatBaseRule;
    }

    /**
     * 设置 floatBaseRule
     * 
     * @param floatBaseRule floatBaseRule
     */
    public void setFloatBaseRule(String floatBaseRule) {
        this.floatBaseRule = floatBaseRule;
    }

    /**
     * 获取 finFloatCalcRuleDetailDtoList
     * 
     * @return finFloatCalcRuleDetailDtoList.
     */
    public List<FinFloatCalcRuleDetail> getFinFloatCalcRuleDetailDtoList() {
        return finFloatCalcRuleDetailDtoList;
    }

    /**
     * 设置 finFloatCalcRuleDetailDtoList
     * 
     * @param finFloatCalcRuleDetailDtoList finFloatCalcRuleDetailDtoList
     */
    public void setFinFloatCalcRuleDetailDtoList(List<FinFloatCalcRuleDetail> finFloatCalcRuleDetailDtoList) {
        this.finFloatCalcRuleDetailDtoList = finFloatCalcRuleDetailDtoList;
    }

}
