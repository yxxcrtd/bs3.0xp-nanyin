/*
 * 文件名：MstCustomerNatureDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwei
 * 修改时间：2018年8月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstcustomernature.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.MstCustomerInfo;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 客户性质
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author huwei
 * @version 1.0 2018年8月16日
 * @see MstCustomerNatureDto
 * @since 1.0
 */
@PageId("MstCustomerNature002")
public class MstCustomerNatureDto extends MstCustomerInfo implements ITableRow, ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 客户性质
     */
    private String customerCharater;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String elementId;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String pageModel;
    
    /**
     * 选中的数据
     */
    private transient List<MstCustomerNatureDto> selectedItem;

    /**
     * 
     */
    private List<String> selectedCustomerId;

    /**
     * 
     */
    private List<String> selectedCustomerCharater;
    
    
    /**
     * 交易对手代码
     */
    private String counterId;
    
    /**
     * 交易对手类型
     */
    private String counterType;

    /**
     * 取得 客户代码
     * @return 客户代码
     */
    @PrimaryKey(Field.PK_1)
    public String getCustomerId() {
        return super.getCustomerId();
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
     * 获取 customerCharater
     * 
     * @return customerCharater.
     */
    public String getCustomerCharater() {
        return customerCharater;
    }

    /**
     * 获取 customerCharater
     * 
     * @return customerCharater.
     */
    public String getCustomerCharaterStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.客户性质, this.getCustomerCharater());
    }

    /**
     * 获取 selectedItem
     * 
     * @return selectedItem.
     */
    public List<MstCustomerNatureDto> getSelectedItem() {
        return selectedItem;
    }

    /**
     * 设置 selectedItem
     * 
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<MstCustomerNatureDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * 设置 customerCharater
     * 
     * @param customerCharater customerCharater
     */
    public void setCustomerCharater(String customerCharater) {
        this.customerCharater = customerCharater;
    }

    /**
     * 获取 serialversionuid
     * 
     * @return serialversionuid.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * 获取 selectedCustomerId
     * 
     * @return selectedCustomerId.
     */
    public List<String> getSelectedCustomerId() {
        return selectedCustomerId;
    }

    /**
     * 设置 selectedCustomerId
     * 
     * @param selectedCustomerId selectedCustomerId
     */
    public void setSelectedCustomerId(List<String> selectedCustomerId) {
        this.selectedCustomerId = selectedCustomerId;
    }

    /**
     * 获取 selectedCustomerCharater
     * 
     * @return selectedCustomerCharater.
     */
    public List<String> getSelectedCustomerCharater() {
        return selectedCustomerCharater;
    }

    /**
     * 设置 selectedCustomerCharater
     * 
     * @param selectedCustomerCharater selectedCustomerCharater
     */
    public void setSelectedCustomerCharater(List<String> selectedCustomerCharater) {
        this.selectedCustomerCharater = selectedCustomerCharater;
    }

    /**
     * 获取 CustomerTypeType
     * 
     * @return String.
     */
    public String getCustomerTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.客户类型, super.getCustomerType());
    }


	public String getCounterId() {
		return counterId;
	}


	public void setCounterId(String counterId) {
		this.counterId = counterId;
	}


	public String getCounterType() {
		return counterType;
	}


	public void setCounterType(String counterType) {
		this.counterType = counterType;
	}
}
