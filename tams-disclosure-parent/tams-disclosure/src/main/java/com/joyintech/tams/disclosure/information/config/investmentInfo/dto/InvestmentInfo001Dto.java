package com.joyintech.tams.disclosure.information.config.investmentInfo.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.information.config.investmentInfo.validator.InvestmentInfoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@PageId("InvestmentInfo002")
@ValidLogic(targetClass = InvestmentInfoValidator.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
public class InvestmentInfo001Dto extends BaseEntity implements ITableRow, ProcessEntity {

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
    private transient List<InvestmentInfo001Dto> selectedItem;

    /**
     * 元素ID
     */
    private String elementId;

    /**
     * 业务主键
     */
    private String id;

    private String itemId;


    /**
     * 产品主代码
     */
    private String booksetId;

    /**
     * 年度
     */
    private String indexYear;
    /**
     * 指标类型
     */
    private String indexType;
    /**
     * 排名
     */
    private String tzRank;

    /**
     * 类别
     */
    private String tzType;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 持有份额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal holdShare;

    /**
     * 占总份额比例（%）
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal proportionShare;

    private String status;

    //是否导入 true导入
    private Boolean flag;

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    public String getId() {
        return id;
    }
    public String getItemId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public List<InvestmentInfo001Dto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<InvestmentInfo001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    @NotEmpty(fieldName = "产品名称",groups={ValidateGroupEdit.class})
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getBooksetId() {
        return booksetId;
    }

    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    @NotEmpty(fieldName = "年度",groups={ValidateGroupEdit.class})
    public String getIndexYear() {
        return indexYear;
    }

    public void setIndexYear(String indexYear) {
        this.indexYear = indexYear;
    }

    @NotEmpty(fieldName = "指标类型",groups={ValidateGroupEdit.class})
    public String getIndexType() {
        return indexType;
    }
    public String getIndexTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.前十大投资者信息指标类型,this.getIndexType());
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    @NotEmpty(fieldName = "排名",groups={ValidateGroupEdit.class})
    public String getTzRank() {
        return tzRank;
    }

    public void setTzRank(String tzRank) {
        this.tzRank = tzRank;
    }

    @NotEmpty(fieldName = "类别",groups={ValidateGroupEdit.class})
    public String getTzType() {
        return tzType;
    }
    public String getTzTypeStr(){
        if (Objects.isNull(SysDataUtil.getNameFromDataDict(DictCode.前十大投资者信息类别,this.getTzType()))){
            return this.getTzType();
        }
        return SysDataUtil.getNameFromDataDict(DictCode.前十大投资者信息类别,this.getTzType());
    }

    public void setTzType(String tzType) {
        this.tzType = tzType;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    @NotEmpty(fieldName = "持有份额（份）",groups={ValidateGroupEdit.class})
    public BigDecimal getHoldShare() {
        return holdShare;
    }

    public void setHoldShare(BigDecimal holdShare) {
        this.holdShare = holdShare;
    }

    @NotEmpty(fieldName = "占总份额比例（%）",groups={ValidateGroupEdit.class})
    public BigDecimal getProportionShare() {
        return proportionShare;
    }
    public String getProportionShareStr(){
        if (Objects.nonNull(this.getProportionShare())){
            return this.getProportionShare().multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        }else {
            return "";
        }
    }

    public void setProportionShare(BigDecimal proportionShare) {
        this.proportionShare = proportionShare;
    }

    public String getStatus() {
        return status;
    }
    public String getStatusStr(){
        if (Objects.isNull(SysDataUtil.getNameFromDataDict(DictCode.维护数据状态,this.getStatus()))){
            return this.getStatus();
        }
        return SysDataUtil.getNameFromDataDict(DictCode.维护数据状态,this.getStatus());
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }
}
