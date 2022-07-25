package com.joyintech.tams.disclosure.information.config.repProdIndex.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RepProdIndexKey;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.information.config.repProdIndex.validator.RepProdIndexValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@PageId("RepProdIndex002")
@ValidLogic(targetClass = RepProdIndexValidator.class, groups = { ValidateGroupEdit.class, ValidateGroupDelete.class })
public class RepProdIndexDto extends RepProdIndexKey implements ITableRow, ProcessEntity {
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 页面类型
     */
    private String pageModel;
    /**
     * 要素id
     */
    private String elementId;
    /**
     * 选中的数据列表
     */
    private transient List<RepProdIndexDto> selectedItem;

    /**
     * 产品代码
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    private String prodId;
    /**
     * 产品名称
     */
    private String prodName;
    /**
     * 日期
     */
    private Date valueDate;
    /**
     * 母产品总资产：母产品的总资产余额，子产品为空
     */
    private BigDecimal pAssets;
    /**
     * 产品资产净值
     */
    private BigDecimal assetsValue;
    /**
     * 产品实收资本
     */
    private BigDecimal capital;
    /**
     * 产品单位净值：若产品为现管类产品，此处填写1.0000
     */
    private BigDecimal unitValue;
    /**
     * 产品单位累计净值：若产品为现管类产品，此处填写1.0000
     */
    private BigDecimal unitValueT;
    /**
     * 产品万份收益：现管类产品填写，非现管类产品置空
     */
    private BigDecimal wYield;
    /**
     * 产品七日年化收益率：现管类产品填写，非现管类产品置空，存储的是不乘以100
     */
    private BigDecimal yield7;
    /**
     * 数据状态
     */
    private String status;

    /**
     * 母产品资产净值
     */
    private BigDecimal pAssetsValue;
    /**
     * 母产品实收资本
     */
    private BigDecimal pCapital;
    /**
     * 母产品单位净值：若产品为现管类产品，此处填写1.0000
     */
    private BigDecimal pUnitValue;
    /**
     * 母产品单位累计净值：若产品为现管类产品，此处填写1.0000
     */
    private BigDecimal pUnitValueT;
    /**
     * 母产品万份收益：现管类产品填写，非现管类产品置空
     */
    private BigDecimal pWYield;
    /**
     * 母产品七日年化收益率：现管类产品填写，非现管类产品置空，存储的是不乘以100
     */
    private BigDecimal pYield7;

    /**
     * 产品主代码
     */
    private String pProdId;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 是否是分级产品：01 是/02 否
     */
    private String isGrade;

    /**
     * 产品类型
     */
    private String prodType;


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

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public List<RepProdIndexDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<RepProdIndexDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    @Override
    public String getProdId() {
        return prodId;
    }

    @Override
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    @Override
    public Date getValueDate() {
        return valueDate;
    }

    @Override
    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    @Override
    public BigDecimal getpAssets() {
        return pAssets;
    }

    @Override
    public void setpAssets(BigDecimal pAssets) {
        this.pAssets = pAssets;
    }

    @Override
    public BigDecimal getAssetsValue() {
        return assetsValue;
    }

    @Override
    public void setAssetsValue(BigDecimal assetsValue) {
        this.assetsValue = assetsValue;
    }

    @Override
    public BigDecimal getCapital() {
        return capital;
    }

    @Override
    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    @Override
    public BigDecimal getUnitValue() {
        return unitValue;
    }

    @Override
    public void setUnitValue(BigDecimal unitValue) {
        this.unitValue = unitValue;
    }

    @Override
    public BigDecimal getUnitValueT() {
        return unitValueT;
    }

    @Override
    public void setUnitValueT(BigDecimal unitValueT) {
        this.unitValueT = unitValueT;
    }

    @Override
    public BigDecimal getwYield() {
        return wYield;
    }

    @Override
    public void setwYield(BigDecimal wYield) {
        this.wYield = wYield;
    }

    @Override
    public BigDecimal getYield7() {
        return yield7;
    }

    @Override
    public void setYield7(BigDecimal yield7) {
        this.yield7 = yield7;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getpAssetsValue() {
        return pAssetsValue;
    }

    public void setpAssetsValue(BigDecimal pAssetsValue) {
        this.pAssetsValue = pAssetsValue;
    }

    public BigDecimal getpCapital() {
        return pCapital;
    }

    public void setpCapital(BigDecimal pCapital) {
        this.pCapital = pCapital;
    }

    public BigDecimal getpUnitValue() {
        return pUnitValue;
    }

    public void setpUnitValue(BigDecimal pUnitValue) {
        this.pUnitValue = pUnitValue;
    }

    public BigDecimal getpUnitValueT() {
        return pUnitValueT;
    }

    public void setpUnitValueT(BigDecimal pUnitValueT) {
        this.pUnitValueT = pUnitValueT;
    }

    public BigDecimal getpWYield() {
        return pWYield;
    }

    public void setpWYield(BigDecimal pWYield) {
        this.pWYield = pWYield;
    }

    public BigDecimal getpYield7() {
        return pYield7;
    }

    public void setpYield7(BigDecimal pYield7) {
        this.pYield7 = pYield7;
    }

    public String getpProdId() {
        return pProdId;
    }

    public void setpProdId(String pProdId) {
        this.pProdId = pProdId;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getIsGrade() {
        return isGrade;
    }

    public void setIsGrade(String isGrade) {
        this.isGrade = isGrade;
    }

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public String getStatusStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.维护数据状态, this.getStatus());
    }

    public String getValueDateStr() {
        return this.getValueDate()==null?null: DateUtil.format(this.getValueDate(), "yyyy-MM-dd");
    }
    public String getUpdateTimeStr() {
        return this.getUpdateTime()==null?null: DateUtil.format(this.getUpdateTime(), "yyyyMMddHHmm");
    }
}
