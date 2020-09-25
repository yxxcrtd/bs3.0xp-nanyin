package com.joyintech.tams.invest.basicdata.mstbondquotationinfo.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.MstBondValuationInfo;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.basicdata.mstbondquotationinfo.validator.MstBondQuotationInfoValidator;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;
/**
 * 
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年9月25日
 * @see MstBondQuotationInfoDto
 * @since 1.0
 */
@PageId("MstBondQuotationInfo002")
@ValidLogic(targetClass = MstBondQuotationInfoValidator.class, groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class MstBondQuotationInfoDto extends MstBondValuationInfo implements ProcessEntity {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;

    /**
     * 金融产品简称
     */
    @Length(fieldName = "MstBondQuotationInfo001.finprodAbbr", max = 66, groups = {
        ValidateGroupEdit.class})
    private String finprodAbbr;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 选择数据
     */
    private List<MstBondQuotationInfoDto> selectedItem;

    /**
     * 要素id
     */
    private String elementId;

    /**
     * 导入日
     */
    private Date importDay;

    /**
     * 获取 importDay
     * @return importDay.
     */
    public Date getImportDay() {
        return importDay;
    }
    
    /**
     * 设置 importDay
     * @param importDay importDay
     */
    public void setImportDay(Date importDay) {
        this.importDay = importDay;
    }
    
    /**
     * 目的和意义以及可能会用到的地方
     */
    private String pageModel;
    @PrimaryKey(Field.PK_3)
    public String getValueSourceStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.估值来源, this.getValueSource());
    }
    @Override
    @NotEmpty(fieldName = "MstBondQuotationInfo001.valueSource", groups = {ValidateGroupEdit.class})
    public String getValueSource(){
        return super.getValueSource();
    }
    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    /**
     * 债券代码
     */
    @Override
    @NotEmpty(fieldName = "MstBondQuotationInfo001.secId", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "MstBondQuotationInfo001.secId", max = 32, groups = {
        ValidateGroupEdit.class})
    @PrimaryKey(value = Field.PK_1)
    public String getSecId() {
        return super.getSecId();
    }

    /**
     * 债券简称
     * 
     * @return String
     */
    // @NotEmpty(fieldName = "MstBondQuotationInfo001.finprodAbbr", groups =
    // {ValidateGroupEdit.class})
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    /**
     * 估值日
     * 
     * @return Date
     */
    @Override
    @NotEmpty(fieldName = "MstBondQuotationInfo001.valueDate", groups = {ValidateGroupEdit.class})
    public Date getValueDate() {
        return super.getValueDate();
    }

    /**
     * 估值日Str
     * 
     * @return String
     */
    @PrimaryKey(Field.PK_2)
    public String getValueDateStr() {
        return DateUtil.format(super.getValueDate());
    }

    /**
     * 剩余期限
     * 
     * @return String
     */
    @Override
    @Length(fieldName = "MstBondQuotationInfo002.lastPeriod", max = 30, groups = {
        ValidateGroupEdit.class})
    public BigDecimal getLastPeriod() {
        return super.getLastPeriod();
    }

    /**
     * 市场全价
     * 
     * @return String
     */
    @Override
    @Length(fieldName = "MstBondQuotationInfo002.price", max = 30, groups = {
        ValidateGroupEdit.class})
    @NotEmpty(fieldName = "MstBondQuotationInfo002.price", groups = {ValidateGroupEdit.class})
    public BigDecimal getPrice() {
        return super.getPrice();
    }

    /**
     * 市场净价
     * 
     * @return BigDecimal
     */
    @Override
    @Length(fieldName = "MstBondQuotationInfo002.netprice", max = 30, groups = {
        ValidateGroupEdit.class})
    @NotEmpty(fieldName = "MstBondQuotationInfo002.netprice", groups = {ValidateGroupEdit.class})
    public BigDecimal getNetprice() {
        return super.getNetprice();
    }

    /**
     * 市场久期
     * 
     * @return BigDecimal
     */
    @Override
    @Length(fieldName = "MstBondQuotationInfo002.mDuration", max = 30, groups = {
        ValidateGroupEdit.class})
    public BigDecimal getmDuration() {
        return super.getmDuration();
    }

    /**
     * 市场凸性
     * 
     * @return BigDecimal
     */
    @Override
    @Length(fieldName = "MstBondQuotationInfo002.mConvexity", max = 30, groups = {
        ValidateGroupEdit.class})
    public BigDecimal getmConvexity() {
        return super.getmConvexity();
    }

    /**
     * 基点价值
     * 
     * @return BigDecimal
     */
    @Override
    @Length(fieldName = "MstBondQuotationInfo002.bpvalue", max = 30, groups = {
        ValidateGroupEdit.class})
    public BigDecimal getBpvalue() {
        return super.getBpvalue();
    }

    /**
     * 利差久期
     * 
     * @return BigDecimal
     */
    @Override
    @Length(fieldName = "MstBondQuotationInfo002.sduration", max = 30, groups = {
        ValidateGroupEdit.class})
    public BigDecimal getSduration() {
        return super.getSduration();
    }

    /**
     * 利差凸性
     * 
     * @return BigDecimal
     */
    @Override
    @Length(fieldName = "MstBondQuotationInfo002.scnvxty", max = 30, groups = {
        ValidateGroupEdit.class})
    public BigDecimal getScnvxty() {
        return super.getScnvxty();
    }

    /**
     * 利率久期
     * 
     * @return BigDecimal
     */
    @Override
    @Length(fieldName = "MstBondQuotationInfo002.interestDuration", max = 30, groups = {
        ValidateGroupEdit.class})
    public BigDecimal getInterestDuration() {
        return super.getInterestDuration();
    }

    /**
     * 利率凸性
     * 
     * @return BigDecimal
     */
    @Override
    @Length(fieldName = "MstBondQuotationInfo002.interestCnvxty", max = 30, groups = {
        ValidateGroupEdit.class})
    public BigDecimal getInterestCnvxty() {
        return super.getInterestCnvxty();
    }

    /**
     * 市场收益率
     * 
     * @return BigDecimal
     */
    @Override
    @Length(fieldName = "MstBondQuotationInfo002.bpyield", max = 30, groups = {
        ValidateGroupEdit.class})
    public BigDecimal getBpyield() {
        return super.getBpyield();
    }

    /**
     * 债券简称
     * 
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    /**
     * 操作类型
     * 
     * @return String
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 操作类型
     * 
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 选择的数据
     * 
     * @return List
     */
    public List<MstBondQuotationInfoDto> getSelectedItem() {
        return selectedItem;
    }

    /**
     * 选择的数据
     * 
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<MstBondQuotationInfoDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * 要素id
     * 
     * @return String
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 要素id
     * 
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }
    /**
     * 导入日
     */
    private String importDayStr;

    public String getImportDayStr() {
        return importDayStr;
    }

    public void setImportDayStr(String importDayStr) {
        this.importDayStr = importDayStr;
    }
}
