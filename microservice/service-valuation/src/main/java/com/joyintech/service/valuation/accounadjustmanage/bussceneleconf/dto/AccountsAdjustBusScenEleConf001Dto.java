/*
 * 文件名：AccountsAdjustBusScenEleConf001Dto.java
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
 * 业务场景配置dto
 * 〈功能详细描述〉
 * @author 李国瑞
 * @version 1.0 2017年1月3日
 * @see AccountsAdjustBusScenEleConf001Dto
 * @since 1.0
 */
public class AccountsAdjustBusScenEleConf001Dto extends BaseEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 8238823316453962254L;
    
    /**
     * 履历号
     */
    private String hisNo;
    
    /**
     * 操作栏 和 行是否可选中标示
     */
    private String disCbxCol;
    
    /**
     * 更新时间时间戳字符型
     */
    private String updateTimeStr;

    /**
     * 操作类型，修改或删除
     */
    private String operateType;
    
    /**
     * 页面模式
     */
    private String pageModel;
    
    /**
     * 业务场景代码
     */
    private String tableId;
    /**
     * 业务场景名称
     */
    private String tableName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 业务要素配置
     */
    private String tableConfig;
    
    
    /**
     * 获取 hisNo
     * @return hisNo.
     */
    public String getHisNo() {
        return hisNo;
    }

    
    /**
     * 设置 hisNo
     * @param hisNo hisNo
     */
    public void setHisNo(String hisNo) {
        this.hisNo = hisNo;
    }

    
    /**
     * 获取 disCbxCol
     * @return disCbxCol.
     */
    public String getDisCbxCol() {
        return disCbxCol;
    }

    
    /**
     * 设置 disCbxCol
     * @param disCbxCol disCbxCol
     */
    public void setDisCbxCol(String disCbxCol) {
        this.disCbxCol = disCbxCol;
    }

    
    /**
     * 获取 updateTimeStr
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
        if(updateTime != null) {
            this.updateTimeStr = String.valueOf(updateTime.getTime());
        }
        super.setUpdateTime(updateTime);
    }


    
    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }


    
    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }


    
    /**
     * 获取 pageModel
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }


    
    /**
     * 设置 pageModel
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


	public String getTableName() {
		return tableName;
	}


	public void setTableName(String tableName) {
		this.tableName = tableName;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getTableConfig() {
		return tableConfig;
	}


	public void setTableConfig(String tableConfig) {
		this.tableConfig = tableConfig;
	}


	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}
}
