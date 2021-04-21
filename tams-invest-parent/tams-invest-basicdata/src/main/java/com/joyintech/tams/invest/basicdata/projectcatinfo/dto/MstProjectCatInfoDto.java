/*
 * 文件名：MstProjectCatInfoDto.java
 * 版权：Copyright 2017-2021 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：mrbbp@qq.com
 * 修改时间：2020-06-30T15:13:49.836
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.projectcatinfo.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.basicdata.projectcatinfo.validator.MstProjectCatInfoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 项目类资产估值行情信息Dto
 *
 * @author mrbbp@qq.com
 * @version 1.0 2020-06-30T15:13:49.836
 * @see MstProjectCatInfoDto
 * @since 1.0
 */
@PageId("MstProjectCatInfo002")
@ValidLogic(targetClass = MstProjectCatInfoValidator.class, groups = {ValidateGroupEdit.class, ValidateGroupDelete.class})
public class MstProjectCatInfoDto extends BaseEntity implements ProcessEntity {

    /**
     * 选中的数据列表
     */
    private transient List<MstProjectCatInfoDto> selectedItem;

    /**
     * 画面元素ID
     */
    private String elementId;

    /**
     * 画面处理模式
     */
    private String pageModel;

    /**
     * 操作类型 - 工作流参数
     */
    private String operateType;

    /**
     * 金融市场代码
     */
    private String finProdMarketId;
    @NotEmpty(fieldName = "MstProjectCatInfo002.finProdMarketId", groups = {ValidateGroupEdit.class})
    public String getFinProdMarketId() {
        return finProdMarketId;
    }

    public void setFinProdMarketId(String finProdMarketId) {
        this.finProdMarketId = finProdMarketId;
    }

    /**
     * 资产代码
     */
    private String finprodId;

    /**
     * 获取 资产代码
     *
     * @return 资产代码.
     */
//    @NotEmpty(fieldName = "MstProjectCatInfo002.finprodId", groups = {ValidateGroupEdit.class})
    @PrimaryKey(Field.PK_1)
    public String getFinprodId() {
        return finprodId;

    }

    /**
     * 设置 资产代码
     *
     * @param finprodId 资产代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 资产名称
     */
    private String finProdName;

    /**
     * 获取 资产名称
     *
     * @return 资产名称.
     */
    @NotEmpty(fieldName = "MstProjectCatInfo002.finProdName", groups = {ValidateGroupEdit.class})
    public String getFinProdName() {
        return finProdName;

    }

    /**
     * 设置 资产名称
     *
     * @param finProdName 资产名称
     */
    public void setFinProdName(String finProdName) {
        this.finProdName = finProdName;
    }

    /**
     * 估值日期
     */
    private Date valDate;

    /**
     * 获取 估值日期
     *
     * @return 估值日期.
     */
    @NotEmpty(fieldName = "MstProjectCatInfo002.valDate", groups = {ValidateGroupEdit.class})
    public Date getValDate() {
        return valDate;
    }

    /**
     * 取得 估值日期字符串格式
     *
     * @return 估值日期
     */
    @PrimaryKey(Field.PK_2)
    public String getValDateStr() {
        return DateUtil.format(this.getValDate());
    }

    /**
     * 设置 估值日期
     *
     * @param valDate 估值日期
     */
    public void setValDate(Date valDate) {
        this.valDate = valDate;
    }

    /**
     * 单位净值
     */
    private BigDecimal valuation;

    /**
     * 获取 单位净值
     *
     * @return 单位净值.
     */
    @NotEmpty(fieldName = "MstProjectCatInfo002.valuation", groups = {ValidateGroupEdit.class})
    public BigDecimal getValuation() {
        return valuation;

    }

    /**
     * 设置 单位净值
     *
     * @param valuation 单位净值
     */
    public void setValuation(BigDecimal valuation) {
        this.valuation = valuation;
    }

    /**
     * 管理人
     */
    private String fundManager;

    /**
     * 获取 管理人
     *
     * @return 管理人.
     */
    @NotEmpty(fieldName = "MstProjectCatInfo002.fundManager", groups = {ValidateGroupEdit.class})
    public String getFundManager() {
        return fundManager;

    }

    /**
     * 设置 管理人
     *
     * @param fundManager 管理人
     */
    public void setFundManager(String fundManager) {
        this.fundManager = fundManager;
    }

    /**
     * 备注
     */
    private String remark;

    /**
     * 获取 备注
     *
     * @return 备注.
     */
    public String getRemark() {
        return remark;

    }

    /**
     * 设置 备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 导入日
     */
    private Date importDate;

    /**
     * 获取 导入日
     *
     * @return 导入日.
     */
    public Date getImportDate() {
        return importDate;

    }

    /**
     * 设置 导入日
     *
     * @param importDate 导入日
     */
    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    /**
     * 收益类型
     */
    private String profitType;

    public String getProfitType() {
        return profitType;
    }

    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    /**
     * 获取 selectedItem
     *
     * @return selectedItem.
     */
    public List<MstProjectCatInfoDto> getSelectedItem() {
        return selectedItem;
    }

    /**
     * 设置 selectedItem
     *
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<MstProjectCatInfoDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * 获取 画面元素ID
     *
     * @return 画面元素ID.
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 画面元素ID
     *
     * @param pageModel 画面元素ID
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    /**
     * 获取 画面处理模式
     *
     * @return 画面处理模式.
     */
    public String getPageModel() {
        return pageModel;
    }

    /**
     * 设置 画面处理模式
     *
     * @param pageModel 画面处理模式
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    /**
     * 获取 操作类型 - 工作流参数
     *
     * @return 操作类型 - 工作流参数.
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 操作类型 - 工作流参数
     *
     * @param operateType 操作类型 - 工作流参数
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }
}