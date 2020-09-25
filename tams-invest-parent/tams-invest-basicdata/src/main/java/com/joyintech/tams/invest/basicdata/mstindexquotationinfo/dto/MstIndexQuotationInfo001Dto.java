/*
 * 文件名：MstIndexQuotationInfo001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstindexquotationinfo.dto;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.MstExponentQuotation;
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
import com.joyintech.tams.invest.basicdata.mstindexquotationinfo.validate.MstIndexQuotationInfo001Validator;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 指数行情信息实体类
 * 
 * @author fanshuanghu
 * @version 1.0 2018年8月14日
 * @see MstIndexQuotationInfo001Dto
 * @since 1.0
 */
@PageId("MstIndexQuotationInfo002") // 页面id
@ValidLogic(targetClass = MstIndexQuotationInfo001Validator.class, groups = {
    ValidateGroupEdit.class, ValidateGroupDelete.class})
public class MstIndexQuotationInfo001Dto extends MstExponentQuotation implements ITableRow, ProcessEntity {

    /**
     * 序列化值
     */
    private static final long serialVersionUID = 2154752416199295418L;

    /**
     * 选中的数据
     */
    private transient List<MstIndexQuotationInfo001Dto> selectedItem;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 设置指数代码为主键 {@inheritDoc}
     */
    @Override
    @PrimaryKey(value = Field.PK_1)
    @NotEmpty(fieldName = "MstIndexQuotationInfo001.indexName", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "MstIndexQuotationInfo001.indexName", max = 66, groups = {
        ValidateGroupEdit.class})
    public String getMarketId() {
        return super.getMarketId();
    }

    /**
     * 设置有效日为主键 {@inheritDoc}
     */
    @PrimaryKey(value = Field.PK_2)
    public String getDay() {
        return DateUtil.format(super.getEffDate());
    }

    /**
     * 设置收盘价的限制条件 {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "MstIndexQuotationInfo001.closePrice", groups = {
        ValidateGroupEdit.class})
    @Amount(fieldName = "MstIndexQuotationInfo001.closePrice", maxIntBit = 30, groups = {
        ValidateGroupEdit.class})
    public BigDecimal getClosePrice() {
        return super.getClosePrice();
    }

    /**
     * 开盘价设置限制条件 {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "MstIndexQuotationInfo001.openPrice", groups = {ValidateGroupEdit.class})
    @Amount(fieldName = "MstIndexQuotationInfo001.openPrice", maxIntBit = 30, groups = {
        ValidateGroupEdit.class})
    public BigDecimal getOpenPrice() {
        return super.getOpenPrice();
    }

    /**
     * 最高价设置限制条件 {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "MstIndexQuotationInfo001.maxPrice", groups = {ValidateGroupEdit.class})
    @Amount(fieldName = "MstIndexQuotationInfo001.maxPrice", maxIntBit = 30, groups = {
        ValidateGroupEdit.class})
    public BigDecimal getMaxPrice() {
        return super.getMaxPrice();
    }

    /**
     * 最低价设置限制条件 {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "MstIndexQuotationInfo001.minPrice", groups = {ValidateGroupEdit.class})
    @Amount(fieldName = "MstIndexQuotationInfo001.minPrice", maxIntBit = 30, groups = {
        ValidateGroupEdit.class})
    public BigDecimal getMinPrice() {
        return super.getMinPrice();
    }

    /**
     * 前收盘价设置限制条件 {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "MstIndexQuotationInfo001.pClosePrice", groups = {
        ValidateGroupEdit.class})
    @Amount(fieldName = "MstIndexQuotationInfo001.pClosePrice", maxIntBit = 30, groups = {
        ValidateGroupEdit.class})
    public BigDecimal getpClosePrice() {
        return super.getpClosePrice();
    }

    /**
     * 发布日设置限制条件 {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "MstIndexQuotationInfo001.relaeseDate", groups = {ValidateGroupEdit.class})
    public Date getRelaeseDate() {
        return super.getRelaeseDate();
    }

    /**
     * 给有效日设置限制条件 {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "MstIndexQuotationInfo001.effDate", groups = {ValidateGroupEdit.class})
    public Date getEffDate() {
        return super.getEffDate();
    }

    /**
     * 获取指数行情
     * 
     * @return String 指数行情
     * @see
     * @since 1.0
     */
    public String getIndexName() {
        return SysDataUtil.getNameFromDataDict(DictCode.指数行情, super.getMarketId());
    }

    /**
     * 获取 selectedItem
     * 
     * @return selectedItem.
     */
    public List<MstIndexQuotationInfo001Dto> getSelectedItem() {
        return selectedItem;
    }

    /**
     * pageModel操作类型
     */
    private String pageModel;

    /**
     * elementId请求的节点名
     */
    private String elementId;

    /**
     * 设置 selectedItem
     * 
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<MstIndexQuotationInfo001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * 获取 operateType
     * 
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     * 
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 pageModel
     * 
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }

    /**
     * 设置 pageModel
     * 
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    /**
     * 获取 elementId
     * 
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 elementId
     * 
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    /**
     * 获取 serialversionuid
     * 
     * @return serialversionuid.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
