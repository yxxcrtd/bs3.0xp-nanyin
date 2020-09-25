/*
 * 文件名：RepFinProductAssetBalanceDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年3月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.balance.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.GridRowDto;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.system.department.dto.SysDepartmentDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 资产余额表
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年3月4日
 * @see RepFinProductAssetBalanceDto
 * @since 1.0
 */
public class RepFinProductAssetBalanceDto extends FinProduct implements ProcessEntity, GridRowDto{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 组合名称
     */
    private String portfolioName;
    
    /**
     * 计息基础，A/360，A/365等
     */
    private String basis;
    
    /**
     * 数量（当前余额）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amount;
    
    /**
     * 初始本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal firstAmount;
    
    /**
     * 交易日期(上次付息截止日)
     */
    private Date tradeDate;
    
    /**
     * 区间净收益率(收益率)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rangeYield;
    
    /**
     * 摊销价(成本价)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal costPrice;
    
    /**
     * 到期收益率(收益率)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mYield;
    
    /**
     * 市价(收盘价)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal closePrice;
    
    /**
     * 所属部门(交易所属部门)
     */
    private String deptCode;
    
    /**
     * 分类4（wind一级类型）
     */
    private String type4;

    /**
     * 分类5（wind二级类型）
     */
    private String type5;
    
    /**
     * 资产类型
     */
    private String assetType;
    
    /**
     * 父节点
     */
    private String parentInfo;

    /**
     * 子节点
     */
    private String childInfo;

    /**
     * 是否有子节点
     */
    private String hasChildren;

    
    /**
     * 获取 basis
     * @return basis.
     */
    public String getBasis() {
        return basis;
    }

    
    /**
     * 设置 basis
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }

    
    /**
     * 获取 amount
     * @return amount.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    
    /**
     * 设置 amount
     * @param amount amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    
    /**
     * 获取 firstAmount
     * @return firstAmount.
     */
    public BigDecimal getFirstAmount() {
        return firstAmount;
    }

    
    /**
     * 设置 firstAmount
     * @param firstAmount firstAmount
     */
    public void setFirstAmount(BigDecimal firstAmount) {
        this.firstAmount = firstAmount;
    }

    
    /**
     * 获取 tradeDate
     * @return tradeDate.
     */
    public Date getTradeDate() {
        return tradeDate;
    }

    
    /**
     * 设置 tradeDate
     * @param tradeDate tradeDate
     */
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    
    /**
     * 获取 rangeYield
     * @return rangeYield.
     */
    public BigDecimal getRangeYield() {
        return rangeYield;
    }

    
    /**
     * 设置 rangeYield
     * @param rangeYield rangeYield
     */
    public void setRangeYield(BigDecimal rangeYield) {
        this.rangeYield = rangeYield;
    }

    
    /**
     * 获取 costPrice
     * @return costPrice.
     */
    public BigDecimal getCostPrice() {
        return costPrice;
    }

    
    /**
     * 设置 costPrice
     * @param costPrice costPrice
     */
    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    
    /**
     * 获取 mYield
     * @return mYield.
     */
    public BigDecimal getmYield() {
        return mYield;
    }

    
    /**
     * 设置 mYield
     * @param mYield mYield
     */
    public void setmYield(BigDecimal mYield) {
        this.mYield = mYield;
    }

    
    /**
     * 获取 closePrice
     * @return closePrice.
     */
    public BigDecimal getClosePrice() {
        return closePrice;
    }

    
    /**
     * 设置 closePrice
     * @param closePrice closePrice
     */
    public void setClosePrice(BigDecimal closePrice) {
        this.closePrice = closePrice;
    }

    
    /**
     * 获取 deptCode
     * @return deptCode.
     */
    public String getDeptCode() {
        return deptCode;
    }

    
    /**
     * 设置 deptCode
     * @param deptCode deptCode
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }


    
    /**
     * 获取 type4
     * @return type4.
     */
    public String getType4() {
        return type4;
    }


    
    /**
     * 设置 type4
     * @param type4 type4
     */
    public void setType4(String type4) {
        this.type4 = type4;
    }


    
    /**
     * 获取 type5
     * @return type5.
     */
    public String getType5() {
        return type5;
    }


    
    /**
     * 设置 type5
     * @param type5 type5
     */
    public void setType5(String type5) {
        this.type5 = type5;
    }


    
    /**
     * 获取 assetType
     * @return assetType.
     */
    public String getAssetType() {
        return assetType;
    }


    
    /**
     * 设置 assetType
     * @param assetType assetType
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }


    
    /**
     * 获取 portfolioId
     * @return portfolioId.
     */
    public String getPortfolioId() {
        return portfolioId;
    }


    
    /**
     * 设置 portfolioId
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }


    
    /**
     * 获取 portfolioName
     * @return portfolioName.
     */
    public String getPortfolioName() {
       /* if(StringUtil.isEmpty(this.getPortfolioName())){
            return StringUtil.EMPTY;
        }*/
        return portfolioName;
    }


    
    /**
     * 设置 portfolioName
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }


    
    /**
     * 获取 parentInfo
     * @return parentInfo.
     */
    public String getParentInfo() {
        return parentInfo;
    }


    
    /**
     * 设置 parentInfo
     * @param parentInfo parentInfo
     */
    public void setParentInfo(String parentInfo) {
        this.parentInfo = parentInfo;
    }


    
    /**
     * 获取 childInfo
     * @return childInfo.
     */
    public String getChildInfo() {
        return childInfo;
    }


    
    /**
     * 设置 childInfo
     * @param childInfo childInfo
     */
    public void setChildInfo(String childInfo) {
        this.childInfo = childInfo;
    }


    
    /**
     * 获取 hasChildren
     * @return hasChildren.
     */
    public String getHasChildren() {
        return hasChildren;
    }


    
    /**
     * 设置 hasChildren
     * @param hasChildren hasChildren
     */
    public void setHasChildren(String hasChildren) {
        this.hasChildren = hasChildren;
    }
    
    public String getFinprodType2Name(){
        return SysDataUtil.getNameFromDataDict(DictCode.金融产品类型_投管用, this.getFinprodType2());
    }
    
    public String getCcyName(){
        return SysDataUtil.getNameFromDataDict(DictCode.币种, this.getCcy());
    }
    
    public String getBasisName(){
        return SysDataUtil.getNameFromDataDict(DictCode.计息基础, this.getBasis());
    }
    
    /*一级资产类型 */
    public String getType4Name(){
        if(StringUtil.equals(this.getAssetType(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())){
            return SysDataUtil.getNameFromDataDict(DictCode.WIND一级分类_含债券一级分类等, this.getType4());
        }else if (StringUtil.equals(this.getAssetType(), DictCodeListExt1.金融产品类型_投管用_存款.getValue())){
            return SysDataUtil.getNameFromDataDict(DictCode.存款类资产类型, this.getType4());
        }else if(StringUtil.equals(this.getAssetType(), DictCodeListExt1.金融产品类型_投管用_基金.getValue())){
            return SysDataUtil.getNameFromDataDict(DictCode.基金类资产类型, this.getType4());
        }else if(StringUtil.equals(this.getAssetType(), DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())){
            return SysDataUtil.getNameFromDataDict(DictCode.计划类资产一级分类, this.getType4());
        }else if(StringUtil.equals(this.getAssetType(), DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())){
            return SysDataUtil.getNameFromDataDict(DictCode.项目类一级分类, this.getType4());
        }
        return null;
    }
    
   /*二级资产类型*/
    public String getType5Name(){
        if(StringUtil.equals(this.getAssetType(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())){
            return SysDataUtil.getNameFromDataDict(DictCode.WIND二级分类_含债券二级分类等, this.getType5());
        }else if (StringUtil.equals(this.getAssetType(), DictCodeListExt1.金融产品类型_投管用_存款.getValue())){
            return SysDataUtil.getNameFromDataDict(DictCode.存款类资产存款类型, this.getType5());
        }else if(StringUtil.equals(this.getAssetType(), DictCodeListExt1.金融产品类型_投管用_基金.getValue())){
            return SysDataUtil.getNameFromDataDict(DictCode.基金类资产二级类型, this.getType5());
        }else if(StringUtil.equals(this.getAssetType(), DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())){
            return SysDataUtil.getNameFromDataDict(DictCode.计划类资产二级分类, this.getType5());
        }else if(StringUtil.equals(this.getAssetType(), DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())){
            return SysDataUtil.getNameFromDataDict(DictCode.资产_负债二级类别, this.getType5());
        }
        return null;
    }
    
    /**
     * 所属团队
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
   @SuppressWarnings("unchecked")
    public String getDeptCodeName(){
            StringBuffer sbf = new StringBuffer();
            if(StringUtil.isNotEmpty(this.getDeptCode())){
                String[] deptCodeArr = this.getDeptCode().split(",");
                Map<String, SysDepartmentDto> deptMap = CacheUtil.getAppCache(GlobalConstant.SYS_DEPARTMENT_KEY, Map.class);
                for(String deptCode : deptCodeArr){
                    for (String in : deptMap.keySet()) {
                        SysDepartmentDto SysDepartmentDto = (SysDepartmentDto)deptMap.get(in);
                        if(StringUtil.equals(deptCode, SysDepartmentDto.getDeptCode())){
                            sbf.append(SysDepartmentDto.getDeptName()).append(",");
                            break;
                        }
                        }
                }
                return sbf.toString();
            }
            return null;
        }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getRowId() {
        // TODO Auto-generated method stub
        return null;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void setRowId(String rowId) {
        // TODO Auto-generated method stub
        
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
        return this.getChildInfo();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(String id) {
        setPortfolioId(id);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getParentId() {
        return this.getParentInfo();
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


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLeaf() {
        return StringUtil.equals("01", hasChildren);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void setLeaf(boolean leaf) {
    }
    
    
}
