/*
 * 文件名：AccountsAdjustBusScenEleConf002Dto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhngming
 * 修改时间：2018年5月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.accounadjustmanage.bussceneleconf.dto;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;


/**
 * 业务要素配置dto 〈功能详细描述〉
 * 
 * @author 李国瑞
 * @version 1.0 2017年1月3日
 * @see AccountsAdjustBusScenEleConf002Dto
 * @since 1.0
 */
public class AccountsAdjustBusScenEleConf002Dto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 8238823316453962254L;

    
    /**
     * 业务场景代码
     */
    private String tableId;
    
    /**
     * 数据项id
     */
    private String dictId;
    
    /**
     * 数据项代码
     */
    private String dictCode;
    
    /**
     * 数据项名称
     */
    private String dictName;
    
    /**
     * 数据项类型
     */
    private String dictType;
    
    /**
     * 数据项来源
     */
    private String dictSource;
    
    /**
     * 业务场景名称
     */
    private String tableName;

    /**
     * 元素数据类型说明
     */
    private String dictTypeDesc;

    /**
     * 更新时间时间戳字符型
     */
    private String updateTimeStr;

    /**
     * 行内操作类型
     */
    private String operateType;

    /**
     * 页面模式
     */
    private String pageModel;
    
    private String dealType;


    /**
     * 获取 updateTimeStr
     * 
     * @return updateTimeStr.
     */
    public String getUpdateTimeStr() {
        return updateTimeStr;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void setUpdateTime(Date updateTime) {
        if (updateTime != null) {
            this.updateTimeStr = String.valueOf(updateTime.getTime());
        }
        super.setUpdateTime(updateTime);
    }

    /**
     * 获取 dictTypeDesc
     * 
     * @return dictTypeDesc.
     */
    public String getDictTypeDesc() {
        return dictTypeDesc;
    }

    /**
     * 设置 dictTypeDesc
     * 
     * @param dictTypeDesc dictTypeDesc
     */
    public void setDictTypeDesc(String dictTypeDesc) {
        this.dictTypeDesc = dictTypeDesc;
    }

    /**
     * 获取 tableName
     * 
     * @return tableName.
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * 设置 tableName
     * 
     * @param tableName tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
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


	public String getTableId() {
		return tableId;
	}


	public void setTableId(String tableId) {
		this.tableId = tableId;
	}


	public String getDictId() {
		return dictId;
	}


	public void setDictId(String dictId) {
		this.dictId = dictId;
	}


	public String getDictCode() {
		return dictCode;
	}


	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}


	public String getDictName() {
		return dictName;
	}


	public void setDictName(String dictName) {
		this.dictName = dictName;
	}


	public String getDictType() {
		return dictType;
	}


	public void setDictType(String dictType) {
		this.dictType = dictType;
	}


	public String getDictSource() {
		return dictSource;
	}


	public void setDictSource(String dictSource) {
		this.dictSource = dictSource;
	}


	public String getDealType() {
		return dealType;
	}


	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	
}
