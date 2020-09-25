/*
 * 文件名：FinPayInterestPlanDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.common.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.tams.component.business.asset.dto.BussFinRepaymentDto;
import com.joyintech.tams.component.business.asset.dto.FinPayInterestStdInfoDto;
import com.joyintech.tams.invest.asset.project.dto.FinProjectTargetAssetDto;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 *  支付计划Dto
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月23日
 * @see FinPayPlanDto
 * @since 1.0
 */
public class FinPayPlanDto extends FinProduct implements ITableRow,ProcessEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 操作类型
     */
    private String operateType;
    
    private String pageModel;
    
    private String elementId;

    /**
     * 选择数据
     */
    private List<FinProjectTargetAssetDto> selectedItem;
    
    /**
     * 资产类别
     */
    private String type6;
    
    /**
     * 支付频率
     */
    private String payCycle;
    
    /**
     * 计息基础，A/360，A/365等
     */
    private String basis;
    
    /**
     * 发行规模
     */
    private BigDecimal issueAmt;
    
    /**
     * 付息营业日准则
     */
    private String payTdayRule;
    
    /**
     *付息信息(业务组件)
     */
    private FinPayInterestStdInfoDto finPayInterestStdInfoDto;
    
    /**
     * 还本信息(业务组件)
     */
    private BussFinRepaymentDto bussFinRepaymentDto;
    
    /**
     * 现金流计划
     */
    private List<FinCashPlan> finCashPlanList;
    
    /**
     * 付息计划
     */
    private List<FinCashPlan> invList;
    
    /**
     * 还本计划
     */
    private List<FinCashPlan> invRepayList;

    /**
     * 现金流代码
     */
    private String cashId;


    /**
     * 金融产品代码
     */
    @Override
    @PrimaryKey(Field.PK_1)
    public String getFinprodId(){
        return super.getFinprodId();
    }
    
    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }

    
    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    
    /**
     * 获取 pageModel
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }

    
    /**
     * 设置 pageModel
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    
    
    /**
     * 获取 elementId
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }


    
    /**
     * 设置 elementId
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }


    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<FinProjectTargetAssetDto> getSelectedItem() {
        return selectedItem;
    }

    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<FinProjectTargetAssetDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    
    /**
     * 获取 type6
     * @return type6.
     */
    public String getType6() {
        return type6;
    }

    
    /**
     * 设置 type6
     * @param type6 type6
     */
    public void setType6(String type6) {
        this.type6 = type6;
    }

    
    /**
     * 获取 payCycle
     * @return payCycle.
     */
    public String getPayCycle() {
        return payCycle;
    }

    
    /**
     * 设置 payCycle
     * @param payCycle payCycle
     */
    public void setPayCycle(String payCycle) {
        this.payCycle = payCycle;
    }

    
    
    /**
     * 获取 payTdayRule
     * @return payTdayRule.
     */
    public String getPayTdayRule() {
        return payTdayRule;
    }

    
    /**
     * 设置 payTdayRule
     * @param payTdayRule payTdayRule
     */
    public void setPayTdayRule(String payTdayRule) {
        this.payTdayRule = payTdayRule;
    }

    /**
     * 获取 finCashPlanList
     * @return finCashPlanList.
     */
    public List<FinCashPlan> getFinCashPlanList() {
        return finCashPlanList;
    }

    
    /**
     * 设置 finCashPlanList
     * @param finCashPlanList finCashPlanList
     */
    public void setFinCashPlanList(List<FinCashPlan> finCashPlanList) {
        this.finCashPlanList = finCashPlanList;
    }


    
    /**
     * 获取 basis
     * @return basis.
     */
    public String getBasis() {
        return basis;
    }


    
    /**
     * 设置 basis
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }


    
    /**
     * 获取 issueAmt
     * @return issueAmt.
     */
    public BigDecimal getIssueAmt() {
        return issueAmt;
    }


    
    /**
     * 设置 issueAmt
     * @param issueAmt issueAmt
     */
    public void setIssueAmt(BigDecimal issueAmt) {
        this.issueAmt = issueAmt;
    }


    
    /**
     * 获取 invList
     * @return invList.
     */
    public List<FinCashPlan> getInvList() {
        return invList;
    }


    
    /**
     * 设置 invList
     * @param invList invList
     */
    public void setInvList(List<FinCashPlan> invList) {
        this.invList = invList;
    }


    
    /**
     * 获取 invRepayList
     * @return invRepayList.
     */
    public List<FinCashPlan> getInvRepayList() {
        return invRepayList;
    }


    
    /**
     * 设置 invRepayList
     * @param invRepayList invRepayList
     */
    public void setInvRepayList(List<FinCashPlan> invRepayList) {
        this.invRepayList = invRepayList;
    }

    
    /**
     * 获取 finPayInterestStdInfoDto
     * @return finPayInterestStdInfoDto.
     */
    public FinPayInterestStdInfoDto getFinPayInterestStdInfoDto() {
        return finPayInterestStdInfoDto;
    }

    
    /**
     * 设置 finPayInterestStdInfoDto
     * @param finPayInterestStdInfoDto finPayInterestStdInfoDto
     */
    public void setFinPayInterestStdInfoDto(FinPayInterestStdInfoDto finPayInterestStdInfoDto) {
        this.finPayInterestStdInfoDto = finPayInterestStdInfoDto;
    }

    
    /**
     * 获取 bussFinRepaymentDto
     * @return bussFinRepaymentDto.
     */
    public BussFinRepaymentDto getBussFinRepaymentDto() {
        return bussFinRepaymentDto;
    }

    
    /**
     * 设置 bussFinRepaymentDto
     * @param bussFinRepaymentDto bussFinRepaymentDto
     */
    public void setBussFinRepaymentDto(BussFinRepaymentDto bussFinRepaymentDto) {
        this.bussFinRepaymentDto = bussFinRepaymentDto;
    }

    public String getCashId() {
        return cashId;
    }

    public void setCashId(String cashId) {
        this.cashId = cashId;
    }
}
