/*
 * 文件名：ValVoucherQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.validator.ValManualVoucherTemplete003Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 手工凭证新建dto
 * 
 * @author sunshuaishuai
 * @version 2.0 2021年2月9日
 * @see ValManualVoucherTemplete003Dto
 * @since 2.0
 */
@PageId("ValManualVoucherTemplete003")
@ValidLogic(targetClass = ValManualVoucherTemplete003Validator.class)
public class ValManualVoucherTemplete003Dto extends BaseEntity implements ProcessEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 模板代码
     */
    @PrimaryKey(value = Field.PK_1)
    private String tmpCode;

    /**
     * 模板名称
     */
    @NotEmpty(fieldName = "ValManualVoucherTemplete.tmpName")
    private String tmpName;

    /**
     * 业务类别
     */
    private String busiType;

    /**
     * 摘要
     */
    private String remark;

    /**
     * 分录类型
     */
    private String recordType;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 操作类型
     */
    private String pageModel;

    /**
     * 凭证模板新建时的分录信息
     */
    private List<ValManualVoucherTemplete003AddDto> listAddDto = new ArrayList<ValManualVoucherTemplete003AddDto>();

    public String getTmpCode() {
        return tmpCode;
    }

    public void setTmpCode(String tmpCode) {
        this.tmpCode = tmpCode;
    }

    public String getTmpName() {
        return tmpName;
    }

    public void setTmpName(String tmpName) {
        this.tmpName = tmpName;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public List<ValManualVoucherTemplete003AddDto> getListAddDto() {
        return listAddDto;
    }

    public void setListAddDto(List<ValManualVoucherTemplete003AddDto> listAddDto) {
        this.listAddDto = listAddDto;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }
}
