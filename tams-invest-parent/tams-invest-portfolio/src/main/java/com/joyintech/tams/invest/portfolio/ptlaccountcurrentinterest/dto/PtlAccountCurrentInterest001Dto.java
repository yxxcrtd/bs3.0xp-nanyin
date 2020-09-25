/*
 * 文件名：PtlAccountCurrentInterest001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlaccountcurrentinterest.dto;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.StlCapacctInfo;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.portfolio.ptlaccountcurrentinterest.validate.PtlAccountCurrentInterest001Validator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 账户活期利率
 * @author fanshuanghu
 * @version 1.0 2018年10月11日
 * @see PtlAccountCurrentInterest001Dto
 * @since 1.0
 */
@PageId("PtlAccountCurrentInterest002")
@ValidLogic(targetClass = PtlAccountCurrentInterest001Validator.class, groups = {ValidateGroupEdit.class,
  ValidateGroupDelete.class})
public class PtlAccountCurrentInterest001Dto extends StlCapacctInfo  implements ITableRow,ProcessEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -837654224701129989L;
    /**
     * 利率类型
     */
    @NotEmpty(fieldName="PtlAccountCurrentInterest001.rateType",groups = {ValidateGroupEdit.class})
    private String rateType;
    /**
     * 生效日
     */
    @NotEmpty(fieldName="PtlAccountCurrentInterest001.effDate",groups = {ValidateGroupEdit.class})
    private Date effDate;
    /**
     * 原有的生效日
     */
    private Date oldEffDate;
    /**
     * 利率
     */
    
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rate;
    /**
     * 活期利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal currentRate;
    /**
     * 系数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal coefficient;
    /**
     * 利差
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal spreadRate;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 现金流代码
     */
    @PrimaryKey(Field.PK_1)
    private String cashId;
    /**
     * 请求类型
     */
    private String elementId;
    /**
     * 现金流一级代码
     */
    private String cashType1;
    /**
     * 现金流二级代码
     */
    private String cashType2;
    /**
     * 业务代码
     */
    private String finprodId;
    /**
     * 指数类型
     */
    private String indexSource;
    
    /**
     * 编号代码
     */
    private String indexId;
    /**
     * 计息基础
     */
    private String basis;
    /**
     * 为展示浮动利率公式“浮动利率名称*系数+利差”增加的参数
     */
    private String rateStr;
    
    public BigDecimal getFinRate(){
        BigDecimal finRate = null;
        if(StringUtil.equals(rateType, DictCodeList.利率类型_浮动利率.getValue())){
            if(coefficient != null  && spreadRate != null  && rate != null){
                finRate = rate.divide(new BigDecimal("100")).multiply(coefficient).add(spreadRate.divide(new BigDecimal("100"))).multiply(new BigDecimal("100"));
            }
        }
        return finRate;
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
     * 获取 oldEffDate
     * @return oldEffDate.
     */
    public Date getOldEffDate() {
        return oldEffDate;
    }
    
    /**
     * 设置 oldEffDate
     * @param oldEffDate oldEffDate
     */
    public void setOldEffDate(Date oldEffDate) {
        this.oldEffDate = oldEffDate;
    }

    /**
     * 获取 indexId
     * @return indexId.
     */
    public String getIndexId() {
        return indexId;
    }
    
    public String getIndexIdStr(){
        String indexIdStr = "";
        if(StringUtil.equals(rateType, DictCodeList.利率类型_浮动利率.getValue())){
            indexIdStr = SysDataUtil.getNameFromDataDict(DictCode.利率行情, indexId);
        }else{
            indexIdStr = SysDataUtil.getNameFromDataDict(DictCode.利率类型, indexId);
        }
        return indexIdStr;
    }
    /**
     * 设置 indexId
     * @param indexId indexId
     */
    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }

    /**
     * 获取 indexSource
     * @return indexSource.
     */
    public String getIndexSource() {
        return indexSource;
    }
    
    /**
     * 设置 indexSource
     * @param indexSource indexSource
     */
    public void setIndexSource(String indexSource) {
        this.indexSource = indexSource;
    }

    /**
     * 业务类型
     */
    @PrimaryKey(Field.PK_3)
    private String finprodType;
    /**
     * 计算类型
     */
    private String calcType;
    @NotEmpty(fieldName="PtlAccountCurrentInterest001.acctName",groups = {ValidateGroupEdit.class})
    @Override
    public String getBusiAcctNo(){
        return super.getBusiAcctNo();
    }
//    public String getCurrentRateStr(){
//        return this.getCurrentRate().toString();
//    }
    /**
     * 有效状态
     * @return
     * @see
     * @since 1.0
     */
    public String getStatusStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.有效状态, DictCodeListExt1.有效状态_有效.getValue());
    }
    
    /**
     * 分支序号
     */
    private String branch;
    @PrimaryKey(Field.PK_2)
    public String getEffDateStr(){
        return DateUtil.format(this.getEffDate());
    }
    
    /**
     * 获取 branch
     * @return branch.
     */
    public String getBranch() {
        return branch;
    }
    
    /**
     * 设置 branch
     * @param branch branch
     */
    public void setBranch(String branch) {
        this.branch = branch;
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
     * 获取 cashType1
     * @return cashType1.
     */
    public String getCashType1() {
        return cashType1;
    }

    /**
     * 设置 cashType1
     * @param cashTyp1 cashType1
     */
    public void setCashType1(String cashType1) {
        this.cashType1 = cashType1;
    }
    
    /**
     * 获取 cashType2
     * @return cashType2.
     */
    public String getCashType2() {
        return cashType2;
    }
    
    /**
     * 设置 cashType2
     * @param cashType2 cashType2
     */
    public void setCashType2(String cashType2) {
        this.cashType2 = cashType2;
    }
    
    /**
     * 获取 finprodId
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }
    
    /**
     * 设置 finprodId
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }
    
    /**
     * 获取 finprodType
     * @return finprodType.
     */
    public String getFinprodType() {
        return finprodType;
    }
    
    /**
     * 设置 finprodType
     * @param finprodType finprodType
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }
    
    /**
     * 获取 calcType
     * @return calcType.
     */
    public String getCalcType() {
        return calcType;
    }
    
    /**
     * 设置 calcType
     * @param calcType calcType
     */
    public void setCalcType(String calcType) {
        this.calcType = calcType;
    }

    /**
     * 获取 cashId
     * @return cashId.
     */
    public String getCashId() {
        return cashId;
    }
    
    /**
     * 设置 cashId
     * @param cashId cashId
     */
    public void setCashId(String cashId) {
        this.cashId = cashId;
    }

    /**
     * 选择项
     */
    private List<PtlAccountCurrentInterest001Dto> selectedItem;
    /**
     * 界面类型
     */
    private String pageModel;
    
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
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<PtlAccountCurrentInterest001Dto> getSelectedItem() {
        return selectedItem;
    }
    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<PtlAccountCurrentInterest001Dto> selectedItem) {
        this.selectedItem = selectedItem;
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
     * 获取 rate
     * @return rate.
     */
    public BigDecimal getRate() {
        return rate;
    }
    
    /**
     * 设置 rate
     * @param rate rate
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
    
    /**
     * 获取 currentRate
     * @return currentRate.
     */
    public BigDecimal getCurrentRate() {
        return currentRate;
    }
    
    /**
     * 设置 currentRate
     * @param currentRate currentRate
     */
    public void setCurrentRate(BigDecimal currentRate) {
        this.currentRate = currentRate;
    }

    
    /**
     * 获取 coefficient
     * @return coefficient.
     */
    public BigDecimal getCoefficient() {
        return coefficient;
    }

    
    /**
     * 设置 coefficient
     * @param coefficient coefficient
     */
    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }
    
    /**
     * 获取 spreadRate
     * @return spreadRate.
     */
    public BigDecimal getSpreadRate() {
        return spreadRate;
    }
    
    /**
     * 设置 spreadRate
     * @param spreadRate spreadRate
     */
    public void setSpreadRate(BigDecimal spreadRate) {
        this.spreadRate = spreadRate;
    }

    /**
     * 获取 rateType
     * @return rateType.
     */
    public String getRateType() {
        return rateType;
    }
    
    /**
     * 设置 rateType
     * @param rateType rateType
     */
    public void setRateType(String rateType) {
        this.rateType = rateType;
    }
    /**
     * 利率类型字符串
     * @return 利率类型的中文
     * @see
     * @since 1.0
     */
    public String getRateTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.利率类型, this.getRateType());
    }
    @NotEmpty(fieldName="PtlAccountCurrentInterest001.ccy",groups = {ValidateGroupEdit.class})
    @Override
    public String getCcy(){
        return super.getCcy();
    }
    /**
     * 币种字符串
     */
    public String getCcyStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
    }
    /**
     * 获取 effDate
     * @return effDate.
     */
    public Date getEffDate() {
        return effDate;
    }
    
    /**
     * 设置 effDate
     * @param effDate effDate
     */
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }
	public String getRateStr() {
		return rateStr;
	}
	public void setRateStr(String rateStr) {
		this.rateStr = rateStr;
	}
    
}
