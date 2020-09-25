/*
 * 文件名：FinOpenPlanMaintenanceDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.messageadjust.maintenance.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.product.messageadjust.maintenance.validate.FinOpenPlanMaintenanceValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2018年10月22日
 * @see FinOpenPlanMaintenanceDto
 * @since 1.0
 */
@PageId("FinOpenPlanMaintenance001")
@ValidLogic(targetClass = FinOpenPlanMaintenanceValidator.class, groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class FinOpenPlanMaintenanceDto extends FinOpenPlan implements ProcessEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 是否周期型
     */
    private String isCycle;
    /**
     * 处理模式
     */
    private String dealMode;
    /**
     * 市场代码
     */
    private String finprodMarketId;
    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 选择数据
     */
    private List<FinOpenPlanMaintenanceDto> selectedItem;
    /**
     * 选择数据
     */
    private List<FinOpenPlanMaintenanceDto> selectedDto;

    /**
     * 要素id
     */
    private String elementId;
    /**
     * 表格编辑事件
     */
    private String updateFlag;
    /**
     * 用来校验
     */
    private String updateFlagStr;
    /**
     * pageModel
     */
    private String pageModel;
    /**
     * 收益类型
     */
    private String profitType;
    /**
     * 产品起息日
     */
    private String vdate;
    
    @PrimaryKey(value=Field.PK_1)
    public String getCashId(){
        return super.getCashId();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @PrimaryKey(value=Field.PK_2)
    public String getFinprodId() {
        return super.getFinprodId();
    }
    
    public String getIsCycle() {
        return isCycle;
    }
    /*@NotEmpty(fieldName = "FinOpenPlanMaintenance001.openDateStr", groups = {ValidateGroupEdit.class})
    public Date getOpenDateStr(){
        return super.getOpenDateStr();
    }*/
    public void setIsCycle(String isCycle) {
        this.isCycle = isCycle;
    }
    
    public String getDealMode() {
        return dealMode;
    }


    public String getOpenDateStrStr(){
        return DateUtil.format(super.getOpenDateStr());
    }
    public String getOpenDateEndStr(){
        return DateUtil.format(super.getOpenDateEnd());
    }
    public String getOpenVdateStr(){
        return DateUtil.format(super.getOpenVdate());
    }
    public String getOpenMdateStr(){
        return DateUtil.format(super.getOpenMdate());
    }
    public String getOpenNdateStr(){
        return DateUtil.format(super.getOpenNdate());
    }
    public void setDealMode(String dealMode) {
        this.dealMode = dealMode;
    }


    public String getOperateType() {
        return operateType;
    }

    
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    
    public List<FinOpenPlanMaintenanceDto> getSelectedItem() {
        return selectedItem;
    }

    
    public void setSelectedItem(List<FinOpenPlanMaintenanceDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    
    public String getElementId() {
        return elementId;
    }

    
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    
    public String getPageModel() {
        return pageModel;
    }

    
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }
    /**
     * 起息日小于平台日的开放计划编辑
     * <一句话功能简述>
     * <功能详细描述>
     * @return String
     * @see
     * @since 1.0
     */
    public String getUpdateFlag(){
        if(super.getOpenVdate() != null){
            return super.getOpenVdate().compareTo(DateUtil.platDateFromCache()) < 0?"02":"01";
        }
        return "01";
    }
    /**
     * 设置表格控件不可用
     * <一句话功能简述>
     * <功能详细描述>
     * @return String
     * @see
     * @since 1.0
     */
    public String getUpdateFlg(){
        return this.getUpdateFlag();
        
    }
    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
    }
    
    public String getUpdateFlagStr() {
        return updateFlagStr;
    }
    
    public void setUpdateFlagStr(String updateFlagStr) {
        this.updateFlagStr = updateFlagStr;
    }
    public String getFinprodMarketId() {
        return finprodMarketId;
    }
    
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }
    
    public List<FinOpenPlanMaintenanceDto> getSelectedDto() {
        return selectedDto;
    }
    
    public void setSelectedDto(List<FinOpenPlanMaintenanceDto> selectedDto) {
        this.selectedDto = selectedDto;
    }

    
    /**
     * 获取 profitType
     * @return profitType.
     */
    public String getProfitType() {
        return profitType;
    }

    
    /**
     * 设置 profitType
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    /**
     * 获取 vdate
     * @return vdate.
     */
    public String getVdate() {
        return vdate;
    }

    /**
     * 设置 vdate
     * @param vdate vdate
     */
    public void setVdate(String vdate) {
        this.vdate = vdate;
    }
    
    // 发送状态
    private String sendStatus;
    // 发送状态备注
    private String sendRemark;
    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getSendRemark() {
        return sendRemark;
    }

    public void setSendRemark(String sendRemark) {
        this.sendRemark = sendRemark;
    }

    public String getSendStatusStr() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.发送状态, getSendStatus())) ? SysDataUtil.getNameFromDataDict(DictCode.发送状态, getSendStatus()):"";
    }
}
