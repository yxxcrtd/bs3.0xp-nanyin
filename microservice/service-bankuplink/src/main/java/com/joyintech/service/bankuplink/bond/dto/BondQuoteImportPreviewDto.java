/*
 * 文件名：BondQuoteImportPreviewDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2020年5月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.bankuplink.bond.dto;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 现卷报价导入预览对象
 * 
 * @author Jiaoxujin
 * @version 1.0 2020年5月23日
 * @see BondQuoteImportPreviewDto
 * @since 1.0
 */
public class BondQuoteImportPreviewDto extends CfetsBondDealEntrustOrderDto {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -3775754130971918093L;

    /**
     * 序号
     */
    private Integer rowNumber;

    /**
     * 错误信息
     */
    private String validationErrorMsg;

    /**
     * 预警信息
     */
    private String validationWarnMsg;

    /**
     * 有错误的字段名称
     */
    private List<String> inValidFieldNames;

    /**
     * 获取 rowNumber
     * 
     * @return rowNumber.
     */
    public Integer getRowNumber() {
        return rowNumber;
    }

    /**
     * 设置 rowNumber
     * 
     * @param rowNumber rowNumber
     */
    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    /**
     * 获取 validationErrorMsg
     * 
     * @return validationErrorMsg.
     */
    public String getValidationErrorMsg() {
        return validationErrorMsg;
    }

    /**
     * 设置 validationErrorMsg
     * 
     * @param validationErrorMsg validationErrorMsg
     */
    public void setValidationErrorMsg(String validationErrorMsg) {
        this.validationErrorMsg = validationErrorMsg;
    }

    /**
     * 获取 validationWarnMsg
     * 
     * @return validationWarnMsg.
     */
    public String getValidationWarnMsg() {
        return validationWarnMsg;
    }

    /**
     * 设置 validationWarnMsg
     * 
     * @param validationWarnMsg validationWarnMsg
     */
    public void setValidationWarnMsg(String validationWarnMsg) {
        this.validationWarnMsg = validationWarnMsg;
    }

    /**
     * 获取 inValidFieldNames
     * @return inValidFieldNames.
     */
    public List<String> getInValidFieldNames() {
        return inValidFieldNames;
    }

    /**
     * 设置 inValidFieldNames
     * @param inValidFieldNames List<String>
     */
    public void setInValidFieldNames(List<String> inValidFieldNames) {
        this.inValidFieldNames = inValidFieldNames;
    }

    public void addValidationErrorMsg(String validationErrorMsg) {
        if (StringUtils.isNotBlank(this.validationErrorMsg)) {
            this.validationErrorMsg = this.validationErrorMsg + "\n" + validationErrorMsg;
        }
        else {
            this.validationErrorMsg = validationErrorMsg;
        }
    }
}
