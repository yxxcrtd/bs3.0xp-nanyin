package com.joyintech.tams.invest.basicdata.mstinterestratequotationinfo.dto;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.MstIntRateInfo;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.basicdata.mstinterestratequotationinfo.validator.MstInterestRateQuotationInfoValidator;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 基准利率实体类
 * @author fanshuanghu
 * @version 1.0 2018年8月17日
 * @see MstInterestRateQuotationInfoDto
 * @since 1.0
 */
@PageId("MstInterestRateQuotationInfo002")
@ValidLogic(targetClass = MstInterestRateQuotationInfoValidator.class, groups = {
    ValidateGroupEdit.class, ValidateGroupDelete.class})
public class MstInterestRateQuotationInfoDto extends MstIntRateInfo implements ITableRow, ProcessEntity {

    /**
     * 序列化值
     */
    private static final long serialVersionUID = 938342833630662060L;


    /**
     * 选中的数据
     */
    private transient List<MstInterestRateQuotationInfoDto> selectedItem;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 页面模型
     */
    private String pageModel;

    /**
     * 请求的结构名
     */
    private String elementId;

    /**
     * 获取选择的数据
     * @return List<MstInterestRateQuotationInfoDto> 实体类集合
     * @see
     * @since 1.0
     */
    public List<MstInterestRateQuotationInfoDto> getSelectedItem() {
        return selectedItem;
    }

    /**
     * 设置选择的数据
     * @param selectedItem 实体类集合
     * @see
     * @since 1.0
     */
    public void setSelectedItem(List<MstInterestRateQuotationInfoDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * 获取操作类型
     * @return String 操作类型
     * @see
     * @since 1.0
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置操作类型
     * @param operateType 操作类型
     * @see
     * @since 1.0
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取page模型
     * @return String page模型
     * @see
     * @since 1.0
     */
    public String getPageModel() {
        return pageModel;
    }

    /**
     * 设置page模型
     * @param pageModel page模型
     * @see
     * @since 1.0
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    /**
     * 获取请求控件Id
     * @return String 请求控件Id
     * @see
     * @since 1.0
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置请求控件Id
     * @param elementId 请求控件Id
     * @see
     * @since 1.0
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    /**
     * 利率代码
     */
    @Override
    @NotEmpty(fieldName = "MstInterestRateQuotationInfo001.intRateName", groups = {
        ValidateGroupEdit.class})
    @Length(fieldName = "MstInterestRateQuotationInfo001.intRateName", max = 32, groups = {
        ValidateGroupEdit.class})
    @PrimaryKey(value = Field.PK_1)
    public String getIntRateId() {
        return super.getIntRateId();
    }

    /**
     * 发布日字符串格式
     * @return String 发布日字符串格式
     */
    @PrimaryKey(value = Field.PK_2)
    public String getEffDateStr() {
        return DateUtil.format(super.getEffDate());
    }

    /**
     * 生效日
     * {@inheritDoc}
     */
    @NotEmpty(fieldName = "MstInterestRateQuotationInfo001.effDate", groups = {
        ValidateGroupEdit.class})
    public Date getEffDate() {
        return super.getEffDate();
    }

    /**
     * 货币
     */
    @Override
    @NotEmpty(fieldName = "MstInterestRateQuotationInfo001.ccy", groups = {
        ValidateGroupEdit.class})
    @Length(fieldName = "MstInterestRateQuotationInfo001.ccy", max = 50, groups = {
        ValidateGroupEdit.class})
    @PrimaryKey(Field.PK_3)
    public String getCcy() {
        return super.getCcy();
    }

    /**
     * 利率限制条件
     * {@inheritDoc}
     */
    @NotEmpty(fieldName = "MstInterestRateQuotationInfo001.intRate", groups = {
        ValidateGroupEdit.class})
    @Amount(fieldName = "MstInterestRateQuotationInfo001.intRate", maxIntBit = 30, groups = {
        ValidateGroupEdit.class})
    public BigDecimal getIntRate() {
        return super.getIntRate();
    }

    /**
     * 获取发布日
     * {@inheritDoc}
     */
    @NotEmpty(fieldName = "MstInterestRateQuotationInfo001.relaeseDate", groups = {
        ValidateGroupEdit.class})
    public Date getRelaeseDate() {
        return super.getRelaeseDate();
    }

    /**
     * 获取利率名称
     * @return String 利率名称
     */

    public String getIntRateName() {
        return SysDataUtil.getNameFromDataDict(DictCode.利率行情, super.getIntRateId());
    }

    /**
     * 获取货币名称
     * @return String 货币名称
     */
    public String getCcyName() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
    }
}
