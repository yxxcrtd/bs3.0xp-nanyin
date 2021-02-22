/*
 * 文件名：PtlPositionAnalysis001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wanghb
 * 修改时间：2019年1月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlmock.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.grid.GridRowDto;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.MockTest;
import com.joyintech.tams.invest.portfolio.ptlmock.validate.PtlMockTestValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 模拟试算
 * 
 * @author yjm
 * @version 1.0 2020/04/13
 * @see PtlMockTest001Dto
 * @since 1.0
 */
@PageId("PtlMockTest001")
@ValidLogic(targetClass = PtlMockTestValidator.class, groups = ValidateGroupEdit.class)
public class PtlMockTest001Dto extends MockTest implements GridRowDto, ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -6568035942591602028L;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 选择数据
     */
    private List<PtlMockTest001Dto> selectedItem;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 组合类型，管理组合、投资组合、资金单元、过渡组合
     */
    private String portfolioType;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 试算日期开始日
     */
    private Date dataDateStart;

    /**
     * 试算日期结束日
     */
    private Date dataDateEnd;

    /**
     * 组合开始日
     */
    private Date vdate;

    /**
     * 组合结束日
     */
    private Date mdate;

    /**
     * 收益类型，预期收益型/净值型/货币型
     */
    private String profitType;

    /**
     * 是否保本
     */
    private String incomeFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 组合代码
     * 
     * @return 组合代码
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 组合代码
     * 
     * @param portfolioId 组合代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId == null ? null : portfolioId.trim();
    }

    /**
     * 取得 组合类型，管理组合、投资组合、资金单元、过渡组合
     * 
     * @return 组合类型，管理组合、投资组合、资金单元、过渡组合
     */
    public String getPortfolioType() {
        return portfolioType;
    }

    /**
     * 设置 组合类型，管理组合、投资组合、资金单元、过渡组合
     * 
     * @param portfolioType 组合类型，管理组合、投资组合、资金单元、过渡组合
     */
    public void setPortfolioType(String portfolioType) {
        this.portfolioType = portfolioType == null ? null : portfolioType.trim();
    }

    /**
     * 取得 组合名称
     * 
     * @return 组合名称
     */
    public String getPortfolioName() {
        return portfolioName;
    }

    /**
     * 设置 组合名称
     * 
     * @param portfolioName 组合名称
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName == null ? null : portfolioName.trim();
    }

    /**
     * 取得 组合开始日
     * 
     * @return 组合开始日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 组合开始日
     * 
     * @param vdate 组合开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 组合结束日
     * 
     * @return 组合结束日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 组合结束日
     * 
     * @param mdate 组合结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 收益类型，预期收益型/净值型/货币型
     * 
     * @return 收益类型，预期收益型/净值型/货币型
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 收益类型，预期收益型/净值型/货币型
     * 
     * @param profitType 收益类型，预期收益型/净值型/货币型
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType == null ? null : profitType.trim();
    }

    /**
     * 取得 是否保本
     * 
     * @return 是否保本
     */
    public String getIncomeFlag() {
        return incomeFlag;
    }

    /**
     * 设置 是否保本
     * 
     * @param incomeFlag 是否保本
     */
    public void setIncomeFlag(String incomeFlag) {
        this.incomeFlag = incomeFlag == null ? null : incomeFlag.trim();
    }

    /**
     * 取得 备注
     * 
     * @return 备注
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
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getDataDateStart() {
        return dataDateStart;
    }

    public void setDataDateStart(Date dataDateStart) {
        this.dataDateStart = dataDateStart;
    }

    public Date getDataDateEnd() {
        return dataDateEnd;
    }

    public void setDataDateEnd(Date dataDateEnd) {
        this.dataDateEnd = dataDateEnd;
    }

    @Override
    public String getCreateUser() {
        return null;
    }

    @Override
    public void setCreateUser(String s) {

    }

    @Override
    public String getCreateDept() {
        return null;
    }

    @Override
    public void setCreateDept(String s) {

    }

    @Override
    public String getRowId() {

        return null;
    }

    @Override
    public void setRowId(String rowId) {

    }

    @Override
    public int getOrder() {

        return 0;
    }

    @Override
    public void setOrder(int order) {

    }

    @Override
    public String getId() {

        return null;
    }

    @Override
    public void setId(String id) {

    }

    @Override
    public String getParentId() {

        return null;
    }

    @Override
    public void setParentId(String parentId) {

    }

    @Override
    public String getName() {

        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public boolean isLeaf() {

        return false;
    }

    @Override
    public void setLeaf(boolean leaf) {

    }

    public List<PtlMockTest001Dto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<PtlMockTest001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getMockId() {
        return super.getMockId();
    }

    /**
     * 获取 operateType
     *
     * @return operateType
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
     * 到期日Str
     * @return
     */
    public String getMdateStr(){
        return DateUtil.format(this.getMdate());
    }

    /**
     * 测算日期Str
     * @return
     */
    public String getMockDateStr(){
        return DateUtil.format(super.getMockDate());
    }
}
