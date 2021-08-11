/*
 * 文件名：RiskPremiumAdjConf001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xutong
 * 修改时间：2021-06-30
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.validator.RiskPremiumAdjConf002Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 风险溢价调整配置
 * @author xutong
 * @createTime 2021-06-30
 * @see RiskPremiumAdjConf002Dto
 * @since 1.0
 */
@PageId("RiskPremiumAdjConf002")
@ValidLogic(targetClass = RiskPremiumAdjConf002Validator.class, groups = {ValidateGroupEdit.class, ValidateGroupDelete.class})
public class RiskPremiumAdjConf002Dto extends BaseEntity implements ProcessEntity {
    /**
     * 流水号
     */
    @PrimaryKey(value= PrimaryKey.Field.PK_1)
    private String seqno;

    /**
     * 证券品种（数据字典.证券品种_模型）
     */
    @NotBlank(message = "资产类型不能为空!", groups = {ValidateGroupEdit.class})
    private String secTypeModel;

    /**
     * 证券细分类（数据字典.证券细分类_模型）
     */
    private String secDetailTypeModel;

    /**
     * 金融产品代码，存储上游系统内部代码，比如金融产品代码
     */
    @NotBlank(message = "资产代码不能为空!", groups = {ValidateGroupEdit.class})
    private String finprodId;

    /**
     * 金融产品名称
     */
    @NotBlank(message = "资产名称不能为空!", groups = {ValidateGroupEdit.class})
    private String finprodName;

    /**
     * 评级来源
     */
    @NotBlank(message = "评级来源不能为空!", groups = {ValidateGroupEdit.class})
    private String gradeSource;

    /**
     * 评级结果
     */
    @NotBlank(message = "评级不能为空!", groups = {ValidateGroupEdit.class})
    private String gradeResult;

    /**
     * 基准日
     */
    @NotNull(message = "基准日不能为空!", groups = {ValidateGroupEdit.class})
    private Date basedate;

    /**
     * 风险溢价
     */
    @NotNull(message = "风险溢价不能为空!", groups = {ValidateGroupEdit.class})
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal spread;

    /**
     * 风险溢价调整
     */
    @NotNull(message = "风险溢价调整不能为空!", groups = {ValidateGroupEdit.class})
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal spreadAdj;

    /**
     * 买入全价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bFullprice;

    /**
     * 买入数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bQty;

    /**
     * 估值模型方法
     */
    private String modMethod;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 界面类型
     */
    private String pageModel;

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    public String getSecTypeModel() {
        return secTypeModel;
    }

    public String getSecTypeModelFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.资产类型, secTypeModel))?
                SysDataUtil.getNameFromDataDict(DictCode.资产类型, secTypeModel):secTypeModel;
    }

    public void setSecTypeModel(String secTypeModel) {
        this.secTypeModel = secTypeModel;
    }

    public String getSecDetailTypeModel() {
        return secDetailTypeModel;
    }

    public void setSecDetailTypeModel(String secDetailTypeModel) {
        this.secDetailTypeModel = secDetailTypeModel;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    public String getGradeSource() {
        return gradeSource;
    }

    public String getGradeSourceFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.评级来源_模型, gradeSource))?
                SysDataUtil.getNameFromDataDict(DictCode.评级来源_模型, gradeSource):gradeSource;
    }

    public void setGradeSource(String gradeSource) {
        this.gradeSource = gradeSource;
    }

    public String getGradeResult() {
        return gradeResult;
    }

    public String getGradeResultFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.评级结果, gradeResult))?
                SysDataUtil.getNameFromDataDict(DictCode.评级结果, gradeResult):gradeResult;
    }

    public void setGradeResult(String gradeResult) {
        this.gradeResult = gradeResult;
    }

    public Date getBasedate() {
        return basedate;
    }

    public String getBasedateFmt(){
        if(StringUtil.isNotEmpty(basedate)){
            return DateUtil.format(basedate,"yyyy-MM-dd");
        }
        return "";
    }

    public void setBasedate(Date basedate) {
        this.basedate = basedate;
    }

    public BigDecimal getSpread() {
        return spread;
    }

    public void setSpread(BigDecimal spread) {
        this.spread = spread;
    }

    public BigDecimal getSpreadAdj() {
        return spreadAdj;
    }

    public void setSpreadAdj(BigDecimal spreadAdj) {
        this.spreadAdj = spreadAdj;
    }

    public BigDecimal getbFullprice() {
        return bFullprice;
    }

    public void setbFullprice(BigDecimal bFullprice) {
        this.bFullprice = bFullprice;
    }

    public BigDecimal getbQty() {
        return bQty;
    }

    public void setbQty(BigDecimal bQty) {
        this.bQty = bQty;
    }

    public String getModMethod() {
        return modMethod;
    }

    public void setModMethod(String modMethod) {
        this.modMethod = modMethod;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }
}
