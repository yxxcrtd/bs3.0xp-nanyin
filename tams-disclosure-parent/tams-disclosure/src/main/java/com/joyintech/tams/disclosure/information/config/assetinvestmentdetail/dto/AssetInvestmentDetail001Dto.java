package com.joyintech.tams.disclosure.information.config.assetinvestmentdetail.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.information.config.assetinvestmentdetail.validator.AssetInvestmentDetailValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@PageId("AssetInvestmentDetail002")
@ValidLogic(targetClass = AssetInvestmentDetailValidator.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
public class AssetInvestmentDetail001Dto extends RepProdInfo implements ITableRow, ProcessEntity {
    /**
     * excle行
     */
    private String excelNum;
    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 选中的数据
     */
    private transient List<AssetInvestmentDetail001Dto> selectedItem;

    /**
     * 元素ID
     */
    private String elementId;
    /**
     * 产品主代码
     */
    private String prodId;
    /**
     * 日期
     */
    private Date pubDate;
    /**
     * 资产代码
     */
    private String assetCode;
    /**
     * 资产名称
     */
    private String assetName;
    /**
     * 公允价值(元)
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal fairValue;

    /**
     * 占产品资产比例(%)
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal pdDrop;

    private String pubDateStr;

    /**
     * 产品名称
     */
    private String prodName;



    private String status;

    public String getExcelNum() {
        return excelNum;
    }

    public void setExcelNum(String excelNum) {
        this.excelNum = excelNum;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public List<AssetInvestmentDetail001Dto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<AssetInvestmentDetail001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    @NotEmpty(fieldName = "产品主代码",groups={ValidateGroupEdit.class})
    public String getProdId() {
        return prodId;
    }

    @Override
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    @PrimaryKey(PrimaryKey.Field.PK_3)
    @NotEmpty(fieldName = "资产代码",groups={ValidateGroupEdit.class})
    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public BigDecimal getFairValue() {
        return fairValue;
    }

    public void setFairValue(BigDecimal fairValue) {
        this.fairValue = fairValue;
    }

    public BigDecimal getPdDrop() {
        return pdDrop;
    }

    public void setPdDrop(BigDecimal pdDrop) {
        this.pdDrop = pdDrop;
    }

    @Override
    public String getProdName() {
        return prodName;
    }

    @Override
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatusStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.维护数据状态,this.getStatus());
    }

    public String getUpdateTimeStr() {
        return this.getUpdateTime()==null?null: DateUtil.format(this.getUpdateTime(), "yyyy-MM-dd HH:mm:ss");
    }

    public void setPubDateStr(String pubDateStr) {
        this.pubDateStr = pubDateStr;
    }

    @PrimaryKey(PrimaryKey.Field.PK_2)
    @NotEmpty(fieldName = "日期",groups={ValidateGroupEdit.class})
    public String getPubDateStr() {
        if (StringUtil.isNotEmpty(this.pubDateStr)) {
            return this.pubDateStr;
        } else {
            return DateUtil.format(this.getPubDate());
        }
    }
}
