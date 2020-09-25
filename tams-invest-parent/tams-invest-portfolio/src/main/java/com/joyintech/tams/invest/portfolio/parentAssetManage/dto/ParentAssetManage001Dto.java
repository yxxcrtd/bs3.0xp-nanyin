/*
 * 文件名：ParentAssetManage001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：ZhouYu
 * 修改时间：2019年11月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.parentAssetManage.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.grid.GridRowDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 母资管计划管理DTO
 * 〈功能详细描述〉
 * @author ZhouYu
 * @version 1.0 2019年11月26日
 * @see ParentAssetManage001Dto
 * @since 1.0
 */
public class ParentAssetManage001Dto extends BaseEntity implements GridRowDto {

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1L;
	
	  private String assetName;    /*资产名称*/
	  
	  private String assetCode;   /*资产代码*/
	  
	  private String assetTerm;   /*期次*/
	  
	  private String vdate;       /*起息日*/
	  
	  private String mdate;       /*到期日*/
	  
	  private BigDecimal assetAmt;    /*融资金额*/
	  
	  private BigDecimal assetRate;   /*到期收益率（%）*/
	  
	  /**
	     * 是否有子节点
	     */
	    private String hasChildren;

	    /**
	     * 行ID
	     */
	    private String rowId;

	    /**
	     * 名称
	     */
	    private String name;
	    /**
	     * 父节点
	     */
	    private String parentInfo;
	    /**
	     * 子节点
	     */
	    private String childInfo;
	    

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

	/**
	 * 获取 assetName
	 * @return assetName.
	 */
	public String getAssetName() {
		return assetName;
	}

	/**
	 * 设置 assetName
	 * @param assetName assetName
	 */
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	/**
	 * 获取 assetCode
	 * @return assetCode.
	 */
	public String getAssetCode() {
	    if (StringUtil.isNotEmpty(assetCode)) {
	        assetCode =  assetCode.replace("F27_", "");  
        }
		return assetCode;
	}

	/**
	 * 设置 assetCode
	 * @param assetCode assetCode
	 */
	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	/**
	 * 获取 assetTerm
	 * @return assetTerm.
	 */
	public String getAssetTerm() {
		return assetTerm;
	}

	/**
	 * 设置 assetTerm
	 * @param assetTerm assetTerm
	 */
	public void setAssetTerm(String assetTerm) {
		this.assetTerm = assetTerm;
	}

	/**
	 * 获取 vdate
	 * @return vdate.
	 */
	public String getVdate() {
		return vdate;
	}

	/**
	 * 设置 vdate
	 * @param vdate vdate
	 */
	public void setVdate(String vdate) {
		this.vdate = vdate;
	}

	/**
	 * 获取 mdate
	 * @return mdate.
	 */
	public String getMdate() {
		return mdate;
	}

	/**
	 * 设置 mdate
	 * @param mdate mdate
	 */
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}

	/**
	 * 获取 assetAmt
	 * @return assetAmt.
	 */
	public BigDecimal getAssetAmt() {
		return assetAmt;
	}

	/**
	 * 设置 assetAmt
	 * @param assetAmt assetAmt
	 */
	public void setAssetAmt(BigDecimal assetAmt) {
		this.assetAmt = assetAmt;
	}

	/**
	 * 获取 assetRate
	 * @return assetRate.
	 */
	public BigDecimal getAssetRate() {
		return assetRate;
	}

	/**
	 * 设置 assetRate
	 * @param assetRate assetRate
	 */
	public void setAssetRate(BigDecimal assetRate) {
		this.assetRate = assetRate;
	}

	/**
	 * 获取 serialversionuid
	 * @return serialversionuid.
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
    public String getName() { 
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getOrder() { 
        return 0;
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
    public String getRowId() {
        return rowId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLeaf() {
        return StringUtil.equals(DictCodeListExt1.是或否_是.getValue(), hasChildren);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(String id) {
        this.setChildInfo(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLeaf(boolean arg0) { 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(String name) {
        this.name = name; 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOrder(int arg0) {  
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParentId(String parentId) {
        this.setParentInfo(parentId); 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRowId(String rowId) {
        this.rowId = rowId; 
    }
}
