/*
 * 文件名：ValNetValueConfirmParamDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年9月27日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.validator.ReportConfirmManageParamValidator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 净值下发确认流程传参实体类
 * @author yangjian
 * @version 1.0 2018年9月27日
 * @see ValNetValueConfirmParamDto
 * @since 1.0
 */
@ValidLogic(targetClass = ReportConfirmManageParamValidator.class)
public class ReportConfirmManageParamDto extends BaseEntity implements ProcessEntity {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 4121616544845257548L;

	/**
	 * 画面id
	 */
	private String pageId;
	/**
     * 净值下发确认勾选的数据json字符串
     */
    private String data;
    
    /**
     * 当前操作为确认还是下发【确认："QR",下发："XF"】
     */
    private String confirmOrNotice;
    
    /**
     * 操作类型
     */
    private String operateType;
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String pageModel;

    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String elementId;

    
    /**
     * 获取 data
     * @return data.
     */
    public String getData() {
        return data;
    }

    
    /**
     * 设置 data
     * @param data data
     */
    public void setData(String data) {
        this.data = data;
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


    
    /**
     * 获取 elementId
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }


    
    /**
     * 设置 elementId
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }


    
    /**
     * 获取 confirmOrNotice
     * @return confirmOrNotice.
     */
    public String getConfirmOrNotice() {
        return confirmOrNotice;
    }


    
    /**
     * 设置 confirmOrNotice
     * @param confirmOrNotice confirmOrNotice
     */
    public void setConfirmOrNotice(String confirmOrNotice) {
        this.confirmOrNotice = confirmOrNotice;
    }


	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
    
}
