package com.joyintech.tams.disclosure.information.config.repFloatFee.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RepFloatFee;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeExt1;
import com.joyintech.tams.disclosure.information.config.repFloatFee.validator.RepFloatFeeValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@PageId("RepFloatFee002")
@ValidLogic(targetClass = RepFloatFeeValidator.class, groups = { ValidateGroupEdit.class, ValidateGroupDelete.class })
public class RepFloatFeeDto extends RepFloatFee implements ITableRow, ProcessEntity {
    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 页面类型
     */
    private String pageModel;

    /**
     * 选中的数据列表
     */
    private transient List<RepFloatFeeDto> selectedItem;

    /**
     * 要素id
     */
    private String elementId;

    /**
     * 子产品代码：内部销售代码
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    private String prodId;

    /**
     * 产品主代码
     */
    private String pProdId;
    /**
     * 确认日
     */
    private Date affirmDate;
    /**
     * 业务代码：只需要传业务代码为“03赎回”的数据
     */
    private String businessCode;
    /**
     * 确认份额：保留2位小数
     */
    private BigDecimal affirmNum;
    /**
     * 确认金额：保留2位小数
     */
    private BigDecimal affirmAmt;
    /**
     * 超额报酬：保留2位小数
     */
    private BigDecimal aboveAmt;
    /**
     * 数据状态
     */
    private String status;
    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 产品代码
     */
    private String prodName;

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

    public List<RepFloatFeeDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<RepFloatFeeDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    @Override
    public String getProdId() {
        return prodId;
    }

    @Override
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    @Override
    public String getpProdId() {
        return pProdId;
    }

    @Override
    public void setpProdId(String pProdId) {
        this.pProdId = pProdId;
    }

    @Override
    public Date getAffirmDate() {
        return affirmDate;
    }

    @Override
    public void setAffirmDate(Date affirmDate) {
        this.affirmDate = affirmDate;
    }

    @Override
    public String getBusinessCode() {
        return businessCode;
    }

    @Override
    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    @Override
    public BigDecimal getAffirmNum() {
        return affirmNum;
    }

    @Override
    public void setAffirmNum(BigDecimal affirmNum) {
        this.affirmNum = affirmNum;
    }

    @Override
    public BigDecimal getAffirmAmt() {
        return affirmAmt;
    }

    @Override
    public void setAffirmAmt(BigDecimal affirmAmt) {
        this.affirmAmt = affirmAmt;
    }

    @Override
    public BigDecimal getAboveAmt() {
        return aboveAmt;
    }

    @Override
    public void setAboveAmt(BigDecimal aboveAmt) {
        this.aboveAmt = aboveAmt;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getStatusStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.维护数据状态, this.getStatus());
    }

    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getAffirmDateStr() {
        return this.getAffirmDate()==null?null: DateUtil.format(this.getAffirmDate(), "yyyy-MM-dd");
    }

    public String getUpdateTimeStr() {
        return this.getUpdateTime()==null?null: DateUtil.format(this.getUpdateTime(), "yyyy-MM-dd HH:mm:ss");
    }
    public String getBusinessCodeStr(){
        return SysDataUtil.getNameFromDataDict(DictCodeExt1.业务代码, this.getBusinessCode());
    }

}
