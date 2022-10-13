package com.joyintech.tams.disclosure.information.config.repProdIndexGz.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RepProdIndex;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.information.config.repProdIndex.validator.RepProdIndexValidator;
import com.joyintech.tams.disclosure.information.config.repProdIndexGz.validator.RepProdIndexGzValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.List;

@PageId("RepProdIndexGz002")
@ValidLogic(targetClass = RepProdIndexGzValidator.class, groups = { ValidateGroupEdit.class, ValidateGroupDelete.class })
public class RepProdIndexGzDto extends RepProdIndex implements ITableRow, ProcessEntity {
    /**
     * excle行
     */
    private String excelNum;
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
    private transient List<RepProdIndexGzDto> selectedItem;

//    /**
//     * 产品代码
//     */
//    @PrimaryKey(PrimaryKey.Field.PK_1)
//    private String prodId;
    /**
     * 产品名称
     */
    private String prodName;

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
     * 是否是分级产品：01 是/02 否
     */
    private String isGrade;

    /**
     * 产品类型
     */
    private String prodType;

    private String valueDateStr;


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

    public List<RepProdIndexGzDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<RepProdIndexGzDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getProdId() {
        return super.getProdId();
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

    public void setValueDateStr(String valueDateStr) {
        this.valueDateStr = valueDateStr;
    }

    public String getValueDateStr() {
        if (StringUtil.isNotEmpty(this.valueDateStr)) {
            return this.valueDateStr;
        } else {
            return this.getValueDate() == null ? null : DateUtil.format(this.getValueDate(), "yyyy-MM-dd");
        }
    }
    public String getUpdateTimeStr() {
        return this.getUpdateTime()==null?null: DateUtil.format(this.getUpdateTime(), "yyyy-MM-dd HH:mm:ss");
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getExcelNum() {
        return excelNum;
    }

    public void setExcelNum(String excelNum) {
        this.excelNum = excelNum;
    }

    private String dataStatus;

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }
}
