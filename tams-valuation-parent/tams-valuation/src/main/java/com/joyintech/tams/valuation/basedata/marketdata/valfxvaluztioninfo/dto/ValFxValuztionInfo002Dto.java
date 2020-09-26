package com.joyintech.tams.valuation.basedata.marketdata.valfxvaluztioninfo.dto;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.valuation.basedata.marketdata.valfxvaluztioninfo.validator.ValFxValuztionInfo002DtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import javax.validation.constraints.NotNull;

/**
 * @author zhangwei
 * @createTime 2019-07-26 11:27
 */
@PageId("ValFxValuztionInfo002")
@ValidLogic(targetClass = ValFxValuztionInfo002DtoValidator.class, groups = {ValidateGroupEdit.class, ValidateGroupDelete.class})
public class ValFxValuztionInfo002Dto extends BaseEntity  implements ProcessEntity {

    /**
     * 金融产品代码（市场代码）-- 交易编号
     */
    @PrimaryKey(value= PrimaryKey.Field.PK_1)
    @NotBlank(message = "交易编号不能为空!", groups = ValidateGroupEdit.class)
    private String finprodId2;

    /**
     * 数据来源
     */
    @PrimaryKey(value= PrimaryKey.Field.PK_2)
    @NotBlank(message = "数据来源不能为空!", groups = ValidateGroupEdit.class)
    private String datasourceType;

    /**
     * 估值日期
     */
    @PrimaryKey(value= PrimaryKey.Field.PK_3)
    @NotBlank(message = "估值日期不能为空!", groups = ValidateGroupEdit.class)
    private String valDate;

    /**
     * 每日估值
     */
    @NotNull(message = "估值不能为空!", groups = ValidateGroupEdit.class)
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal valuation;

    /**
     * 估值模型
     */
    private String valmodelId;

    /**
     * 币种
     */
    @NotBlank(message = "币种不能为空!", groups = ValidateGroupEdit.class)
    private String ccy;

    /**
     * 录入方式，接口、手工等
     */
    private String inputType;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String pageModel;

    /**
     * 处理操作
     */
    private String operateType;
    
    /**
     * 外汇类型
     */
    private String  foreignexChangeType;

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 取得 金融产品代码（市场代码）
     * @return 金融产品代码（市场代码）
     */
    public String getFinprodId2() {
        return finprodId2;
    }

    /**
     * 设置 金融产品代码（市场代码）
     * @param finprodId2 金融产品代码（市场代码）
     */
    public void setFinprodId2(String finprodId2) {
        this.finprodId2 = finprodId2 == null ? null : finprodId2.trim();
    }

    /**
     * 取得 数据来源
     * @return 数据来源
     */
    public String getDatasourceType() {
        return datasourceType;
    }

    /**
     * 设置 数据来源
     * @param datasourceType 数据来源
     */
    public void setDatasourceType(String datasourceType) {
        this.datasourceType = datasourceType == null ? null : datasourceType.trim();
    }

    public String getValDate() {
        return valDate;
    }

    public void setValDate(String valDate) {
        this.valDate = valDate;
    }

    /**
     * 取得 每日估值
     * @return 每日估值
     */
    public BigDecimal getValuation() {
        return valuation;
    }

    /**
     * 设置 每日估值
     * @param valuation 每日估值
     */
    public void setValuation(BigDecimal valuation) {
        this.valuation = valuation;
    }

    /**
     * 取得 估值模型
     * @return 估值模型
     */
    public String getValmodelId() {
        return valmodelId;
    }

    /**
     * 设置 估值模型
     * @param valmodelId 估值模型
     */
    public void setValmodelId(String valmodelId) {
        this.valmodelId = valmodelId == null ? null : valmodelId.trim();
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 录入方式，接口、手工等
     * @return 录入方式，接口、手工等
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * 设置 录入方式，接口、手工等
     * @param inputType 录入方式，接口、手工等
     */
    public void setInputType(String inputType) {
        this.inputType = inputType == null ? null : inputType.trim();
    }

	public String getForeignexChangeType() {
		return foreignexChangeType;
	}

	public void setForeignexChangeType(String foreignexChangeType) {
		this.foreignexChangeType = foreignexChangeType ==  null ? null : foreignexChangeType.trim() ;
	}

    
}
