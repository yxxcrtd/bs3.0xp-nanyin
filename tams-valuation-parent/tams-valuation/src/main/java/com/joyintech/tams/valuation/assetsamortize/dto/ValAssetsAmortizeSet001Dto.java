/*
 * 文件名：ValAssetsAmortizeSet001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2018年12月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.assetsamortize.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.valuation.assetsamortize.validator.ValAssetsAmortizeSet001CheckValidator;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author HuangJian
 * @version 1.0 2018年12月5日
 * @see ValAssetsAmortizeSet001Dto
 * @since 1.0
 */
@ValidLogic(targetClass = ValAssetsAmortizeSet001CheckValidator.class)
public class ValAssetsAmortizeSet001Dto extends BaseEntity implements ProcessEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 账套代码
     */
    @PrimaryKey(value = Field.PK_2)
    private String booksetId;

    /**
     * 账套名称
     */
    private String booksetName;

    /**
     * 业务明细代码（资产代码）
     */
    @PrimaryKey(value = Field.PK_1)
    private String busiId;

    /**
     * 金融产品代码
     */
    private String finprodName;

    /**
     * 日期
     */
    @PrimaryKey(value = Field.PK_3)
    private String cdate;

    /**
     * 重算方式
     */
    private String recalType;
    
    /**
     * 重算方式
     */
    private String recalTypeVal;

    /**
     * 实际日利率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal dailydscYield;
    
    
    private String dailydscYieldFt;
    
    
    
    

    
    /**
     * 获取 dailydscYieldFt
     * @return dailydscYieldFt.
     */
    public String getDailydscYieldFt() {
        return dailydscYieldFt;
    }

    
    /**
     * 设置 dailydscYieldFt
     * @param dailydscYieldFt dailydscYieldFt
     */
    public void setDailydscYieldFt(String dailydscYieldFt) {
        this.dailydscYieldFt = dailydscYieldFt;
    }


    /**
     * 操作类型
     */
    private String operate;

    /**
     * 选中的数据
     */
    private transient List<ValAssetsAmortizeSet001Dto> listValAssetsAmortizeSet001Dto;

    /**
     * 获取 booksetId
     * 
     * @return booksetId.
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 booksetId
     * 
     * @param booksetId booksetId
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    /**
     * 获取 busiId
     * 
     * @return busiId.
     */
    public String getBusiId() {
        return busiId;
    }

    /**
     * 设置 busiId
     * 
     * @param busiId busiId
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }

    /**
     * 获取 cdate
     * 
     * @return cdate.
     */
    public String getCdate() {
        return cdate;
    }

    /**
     * 设置 cdate
     * 
     * @param cdate cdate
     */
    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    /**
     * 获取 recalType
     * 
     * @return recalType.
     */
    public String getRecalType() {
        return recalType;
    }

    /**
     * 获取 recalType
     * 
     * @return recalType.
     */
    public String getRecalTypeFt() {
        return SysDataUtil.getNameFromDataDict(DictCode.重算方式, recalType);
    }

    /**
     * 设置 recalType
     * 
     * @param recalType recalType
     */
    public void setRecalType(String recalType) {
        this.recalType = recalType;
    }

    /**
     * 获取 dailydscYield
     * 
     * @return dailydscYield.
     */
    public BigDecimal getDailydscYield() {
        return dailydscYield;
    }

    /**
     * 设置 dailydscYield
     * 
     * @param dailydscYield dailydscYield
     */
    public void setDailydscYield(BigDecimal dailydscYield) {
        this.dailydscYield = dailydscYield;
    }

    /**
     * 获取 booksetName
     * 
     * @return booksetName.
     */
    public String getBooksetName() {
        return booksetName;
    }

    /**
     * 设置 booksetName
     * 
     * @param booksetName booksetName
     */
    public void setBooksetName(String booksetName) {
        this.booksetName = booksetName;
    }

    /**
     * 获取 finprodName
     * 
     * @return finprodName.
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 finprodName
     * 
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    /**
     * 获取 operate
     * 
     * @return operate.
     */
    public String getOperate() {
        return operate;
    }

    /**
     * 设置 operate
     * 
     * @param operate operate
     */
    public void setOperate(String operate) {
        this.operate = operate;
    }

    /**
     * 获取 listValAssetsAmortizeSet001Dto
     * 
     * @return listValAssetsAmortizeSet001Dto.
     */
    public List<ValAssetsAmortizeSet001Dto> getListValAssetsAmortizeSet001Dto() {
        return listValAssetsAmortizeSet001Dto;
    }

    /**
     * 设置 listValAssetsAmortizeSet001Dto
     * 
     * @param listValAssetsAmortizeSet001Dto listValAssetsAmortizeSet001Dto
     */
    public void setListValAssetsAmortizeSet001Dto(List<ValAssetsAmortizeSet001Dto> listValAssetsAmortizeSet001Dto) {
        this.listValAssetsAmortizeSet001Dto = listValAssetsAmortizeSet001Dto;
    }

    
    /**
     * 获取 recalTypeVal
     * @return recalTypeVal.
     */
    public String getRecalTypeVal() {
        return recalTypeVal;
    }

    
    /**
     * 设置 recalTypeVal
     * @param recalTypeVal recalTypeVal
     */
    public void setRecalTypeVal(String recalTypeVal) {
        this.recalTypeVal = recalTypeVal;
    }

    
}
