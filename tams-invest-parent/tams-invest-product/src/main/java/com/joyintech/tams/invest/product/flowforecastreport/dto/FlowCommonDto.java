/*
 * 文件名：FlowCommonDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：weipeng
 * 修改时间：2019年8月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.flowforecastreport.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.grid.GridRowDto;
import com.joyintech.framework.util.ArithmeticUtil;

/**
 * 共通继承Dto，用于Controller和Service返回Dto
 * 
 * @author weipeng
 * @version 1.0 2019年8月13日
 * @see FlowCommonDto
 * @since 1.0
 */
public class FlowCommonDto extends BaseEntity implements GridRowDto {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 8277257823119334084L;

    /**
     * 行ID（分类节点NodeId）
     */
    private String rowId;

    /**
     * 父级节点
     */
    private String fRowId;

    /**
     * 是否是叶子
     */
    private boolean isLeaf;

    /**
     * 是否有子节点
     */
    private String hasChildren;

    /**
     * 行名称（分类）
     */
    private String rowName;

    /**
     * 日日升余额
     */
    private BigDecimal rrsBalance;

    /**
     * 月月升余额
     */
    private BigDecimal yysBalance;

    /**
     * 鑫鑫向荣余额
     */
    private BigDecimal xxxrBalance;

    /**
     * 鑫鑫向荣B款余额
     */
    private BigDecimal xxxrBBalance;

    /**
     * 理财宝余额
     */
    private BigDecimal lcbBalance;

    /**
     * 定开净值型汇总
     */
    private BigDecimal dkNetSum;

    /**
     * 合计
     */
    private BigDecimal totalBalance;

    /**
     * 分类
     */
    private String type;

    /**
     * 明细
     */
    private BigDecimal detail;

    /**
     * 指标值1
     */
    private BigDecimal targetTimeone;

    /**
     * 指标值2
     */
    private BigDecimal targetTimetwo;

    /**
     * 指标值3
     */
    private BigDecimal targetTimethree;

    /**
     * 指标值4
     */
    private BigDecimal targetTimefour;

    /**
     * 指标值5
     */
    private BigDecimal targetTimefive;

    /**
     * 指标值6
     */
    private BigDecimal targetTimesix;

    /**
     * 获取 行ID
     * 
     * @return 行ID
     */
    public String getRowId() {
        return rowId;
    }

    /**
     * 设置 行ID
     * 
     * @param rowId 行ID
     */
    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    /**
     * 获取 父级节点
     * 
     * @return 父级节点
     */
    public String getfRowId() {
        return fRowId;
    }

    /**
     * 设置 父级节点
     * 
     * @param fRowId 父级节点
     */
    public void setfRowId(String fRowId) {
        this.fRowId = fRowId;
    }

    /**
     * 获取 是否有子节点
     * 
     * @return 是否有子节点
     */
    public String getHasChildren() {
        return hasChildren;
    }

    /**
     * 设置 是否有子节点
     * 
     * @param hasChildren 是否有子节点
     */
    public void setHasChildren(String hasChildren) {
        this.hasChildren = hasChildren;
    }

    /**
     * 获取 行名称
     * 
     * @return 行名称
     */
    public String getRowName() {
        return rowName;
    }

    /**
     * 设置 行名称
     * 
     * @param rowName 行名称
     */
    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

    /**
     * 获取 分类
     * 
     * @return 分类
     */
    public String getType() {
        return type;
    }

    /**
     * 设置 分类
     * 
     * @param type 分类
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取 明细
     * 
     * @return 明细
     */
    public BigDecimal getDetail() {
        return detail;
    }

    /**
     * 设置 明细
     * 
     * @param detail 明细
     */
    public void setDetail(BigDecimal detail) {
        this.detail = detail;
    }

    /**
     * 取得 指标值1
     * 
     * @return 指标值1
     */
    public BigDecimal getTargetTimeone() {
        return targetTimeone;
    }

    /**
     * 设置 指标值1
     * 
     * @param targetTimeone 指标值1
     */
    public void setTargetTimeone(BigDecimal targetTimeone) {
        this.targetTimeone = targetTimeone;
    }

    /**
     * 取得 指标值2
     * 
     * @return 指标值2
     */
    public BigDecimal getTargetTimetwo() {
        return targetTimetwo;
    }

    /**
     * 设置 指标值2
     * 
     * @param targetTimetwo 指标值2
     */
    public void setTargetTimetwo(BigDecimal targetTimetwo) {
        this.targetTimetwo = targetTimetwo;
    }

    /**
     * 取得 指标值3
     * 
     * @return 指标值3
     */
    public BigDecimal getTargetTimethree() {
        return targetTimethree;
    }

    /**
     * 设置 指标值3
     * 
     * @param targetTimethree 指标值3
     */
    public void setTargetTimethree(BigDecimal targetTimethree) {
        this.targetTimethree = targetTimethree;
    }

    /**
     * 取得 指标值4
     * 
     * @return 指标值4
     */
    public BigDecimal getTargetTimefour() {
        return targetTimefour;
    }

    /**
     * 设置 指标值4
     * 
     * @param targetTimefour 指标值4
     */
    public void setTargetTimefour(BigDecimal targetTimefour) {
        this.targetTimefour = targetTimefour;
    }

    /**
     * 取得 指标值5
     * 
     * @return 指标值5
     */
    public BigDecimal getTargetTimefive() {
        return targetTimefive;
    }

    /**
     * 设置 指标值5
     * 
     * @param targetTimefive 指标值5
     */
    public void setTargetTimefive(BigDecimal targetTimefive) {
        this.targetTimefive = targetTimefive;
    }

    /**
     * 取得 指标值6
     * 
     * @return 指标值6
     */
    public BigDecimal getTargetTimesix() {
        return targetTimesix;
    }

    /**
     * 设置 指标值6
     * 
     * @param targetTimesix 指标值6
     */
    public void setTargetTimesix(BigDecimal targetTimesix) {
        this.targetTimesix = targetTimesix;
    }

    /**
     * 获取 合计1
     * 
     * @return 合计1
     */
    public BigDecimal getTotalBalance1() {
        return ArithmeticUtil.doAdd(this.getTargetTimeone(), this.getTargetTimetwo(),
            this.getTargetTimethree());
    }

    /**
     * 获取 合计2
     * 
     * @return 合计2
     */
    public BigDecimal getTotalBalance2() {
        return ArithmeticUtil.doAdd(this.getTotalBalance1(), this.getTargetTimefour());
    }

    /**
     * 获取 合计3
     * 
     * @return 合计3
     */
    public BigDecimal getTotalBalance3() {
        return ArithmeticUtil.doAdd(this.getTotalBalance2(), this.getTargetTimefive());
    }

    /**
     * 获取 合计4
     * 
     * @return 合计4
     */
    public BigDecimal getTotalBalance4() {
        return ArithmeticUtil.doAdd(this.getTotalBalance3(), this.getTargetTimesix());
    }

    /**
     * 获取 日日升余额
     * 
     * @return 日日升余额
     */
    public BigDecimal getRrsBalance() {
        return rrsBalance;
    }

    /**
     * 设置 日日升余额
     * 
     * @param rrsBalance 日日升余额
     */
    public void setRrsBalance(BigDecimal rrsBalance) {
        this.rrsBalance = rrsBalance;
    }

    /**
     * 获取 月月升余额
     * 
     * @return 月月升余额
     */
    public BigDecimal getYysBalance() {
        return yysBalance;
    }

    /**
     * 设置 月月升余额
     * 
     * @param yysBalance 月月升余额
     */
    public void setYysBalance(BigDecimal yysBalance) {
        this.yysBalance = yysBalance;
    }

    /**
     * 获取 鑫鑫向荣余额
     * 
     * @return 鑫鑫向荣余额
     */
    public BigDecimal getXxxrBalance() {
        return xxxrBalance;
    }

    /**
     * 设置 鑫鑫向荣余额
     * 
     * @param xxxrBalance 鑫鑫向荣余额
     */
    public void setXxxrBalance(BigDecimal xxxrBalance) {
        this.xxxrBalance = xxxrBalance;
    }

    /**
     * 获取 鑫鑫向荣B款余额
     * 
     * @return 鑫鑫向荣B款余额
     */
    public BigDecimal getXxxrBBalance() {
        return xxxrBBalance;
    }

    /**
     * 设置 鑫鑫向荣B款余额
     * 
     * @param xxxrBBalance 鑫鑫向荣B款余额
     */
    public void setXxxrBBalance(BigDecimal xxxrBBalance) {
        this.xxxrBBalance = xxxrBBalance;
    }

    /**
     * 获取 理财宝余额
     * 
     * @return 理财宝余额
     */
    public BigDecimal getLcbBalance() {
        return lcbBalance;
    }

    /**
     * 设置 理财宝余额
     * 
     * @param lcbBalance 理财宝余额
     */
    public void setLcbBalance(BigDecimal lcbBalance) {
        this.lcbBalance = lcbBalance;
    }

    /**
     * 获取 定开净值型汇总
     * 
     * @return 定开净值型汇总
     */
    public BigDecimal getDkNetSum() {
        return dkNetSum;
    }

    /**
     * 设置 定开净值型汇总
     * 
     * @param dkNetSum 定开净值型汇总
     */
    public void setDkNetSum(BigDecimal dkNetSum) {
        this.dkNetSum = dkNetSum;
    }

    /**
     * 获取 合计
     * 
     * @return 合计
     */
    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    /**
     * 设置 合计
     * 
     * @param totalBalance 合计
     */
    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }

    /**
     * 获取 是否子节点
     * 
     * @return 是否子节点
     */
    public boolean isLeaf() {
        return isLeaf;
    }

    /**
     * 设置 是否子节点
     * 
     * @param isLeaf 是否子节点
     */
    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getOrder() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOrder(int order) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return this.getRowId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(String id) {
        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getParentId() {
        return getfRowId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParentId(String parentId) {
        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(String name) {
        // TODO Auto-generated method stub

    }

}
