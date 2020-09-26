/*
 * 文件名：ErrorInformationEntity.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：lihuadong
 * 修改时间：2017年5月17日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.interf.common.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 下载报文错误信息部分
 * 
 * @author lihuadong
 * @version 1.0 2017年5月17日
 * @see ErrorInformationEntity
 * @since 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class InformationEntity {

    /**
     * 子节点
     */
    @XmlElement(name = "wemax-retInstanceFileNo")
    private CommonNodeEntity instanceFileNo;

    /**
     * 子节点
     */
    @XmlElement(name = "wemax-retRegistrationSerialNumber")
    private CommonNodeEntity registrationSerialNumber;

    /**
     * 子节点
     */
    @XmlElement(name = "wemax-retErrorMessage")
    private CommonNodeEntity errorMessage;

    /**
     * 子节点
     */
    @XmlElement(name = "wemax-retProductRegistrationCode")
    private CommonNodeEntity productRegistrationCode;

    /**
     * 子节点
     */
    @XmlElement(name = "wemax-retProductCode")
    private CommonNodeEntity productCode;

    /**
     * 子节点
     */
    @XmlElement(name = "wemax-retPreIdentificationCode")
    private CommonNodeEntity preIdentificationCode;

    /**
     * 获取 instanceFileNo
     * 
     * @return instanceFileNo.
     */
    public CommonNodeEntity getInstanceFileNo() {
        return instanceFileNo;
    }

    /**
     * 设置 instanceFileNo
     * 
     * @param instanceFileNo instanceFileNo
     */
    public void setInstanceFileNo(CommonNodeEntity instanceFileNo) {
        this.instanceFileNo = instanceFileNo;
    }

    /**
     * 获取 registrationSerialNumber
     * 
     * @return registrationSerialNumber.
     */
    public CommonNodeEntity getRegistrationSerialNumber() {
        return registrationSerialNumber;
    }

    /**
     * 设置 registrationSerialNumber
     * 
     * @param registrationSerialNumber registrationSerialNumber
     */
    public void setRegistrationSerialNumber(CommonNodeEntity registrationSerialNumber) {
        this.registrationSerialNumber = registrationSerialNumber;
    }

    /**
     * 获取 errorMessage
     * 
     * @return errorMessage.
     */
    public CommonNodeEntity getErrorMessage() {
        return errorMessage;
    }

    /**
     * 设置 errorMessage
     * 
     * @param errorMessage errorMessage
     */
    public void setErrorMessage(CommonNodeEntity errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * 获取 productRegistrationCode
     * 
     * @return productRegistrationCode.
     */
    public CommonNodeEntity getProductRegistrationCode() {
        return productRegistrationCode;
    }

    /**
     * 设置 productRegistrationCode
     * 
     * @param productRegistrationCode productRegistrationCode
     */
    public void setProductRegistrationCode(CommonNodeEntity productRegistrationCode) {
        this.productRegistrationCode = productRegistrationCode;
    }

    /**
     * 获取 productCode
     * 
     * @return productCode.
     */
    public CommonNodeEntity getProductCode() {
        return productCode;
    }

    /**
     * 设置 productCode
     * 
     * @param productCode productCode
     */
    public void setProductCode(CommonNodeEntity productCode) {
        this.productCode = productCode;
    }

    /**
     * 获取 preIdentificationCode
     * 
     * @return preIdentificationCode.
     */
    public CommonNodeEntity getPreIdentificationCode() {
        return preIdentificationCode;
    }

    /**
     * 设置 preIdentificationCode
     * 
     * @param preIdentificationCode preIdentificationCode
     */
    public void setPreIdentificationCode(CommonNodeEntity preIdentificationCode) {
        this.preIdentificationCode = preIdentificationCode;
    }

}
