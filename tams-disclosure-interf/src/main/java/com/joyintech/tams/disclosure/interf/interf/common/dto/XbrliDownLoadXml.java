/*
 * 文件名：XbrliDownLoadXml.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：lihuadong
 * 修改时间：2017年5月17日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.interf.interf.common.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 下载报文报文结构
 * 
 * @author lihuadong
 * @version 1.0 2017年5月17日
 * @see XbrliDownLoadXml
 * @since 1.0
 */
@XmlRootElement(name = "root")
public class XbrliDownLoadXml extends XbrliXbrlEntity {

    /**
     * 子节点
     */
    @XmlElement(name = "wemax-retInstanceFileName")
    private CommonNodeEntity instanceFileName;

    /**
     * 子节点
     */
    @XmlElement(name = "wemax-retErrorInformationTuple")
    private List<InformationEntity> errorInformationTuple;

    /**
     * 子节点
     */
    @XmlElement(name = "wemax-retCorrectInformationTuple")
    private List<InformationEntity> correctInformationTuple;

    /**
     * 子节点
     */
    @XmlElement(name = "wemax-retProductRegistrationCodeInformationTuple")
    private List<InformationEntity> productRegistrationCodeInformationTuple;

    /**
     * 获取 instanceFileName
     * 
     * @return instanceFileName.
     */
    public CommonNodeEntity getInstanceFileName() {
        return instanceFileName;
    }

    /**
     * 设置 instanceFileName
     * 
     * @param instanceFileName instanceFileName
     */
    public void setInstanceFileName(CommonNodeEntity instanceFileName) {
        this.instanceFileName = instanceFileName;
    }

    /**
     * 获取 errorInformationTuple
     * 
     * @return errorInformationTuple.
     */
    public List<InformationEntity> getErrorInformationTuple() {
        return errorInformationTuple;
    }

    /**
     * 设置 errorInformationTuple
     * 
     * @param errorInformationTuple errorInformationTuple
     */
    public void setErrorInformationTuple(List<InformationEntity> errorInformationTuple) {
        this.errorInformationTuple = errorInformationTuple;
    }

    /**
     * 获取 correctInformationTuple
     * 
     * @return correctInformationTuple.
     */
    public List<InformationEntity> getCorrectInformationTuple() {
        return correctInformationTuple;
    }

    /**
     * 设置 correctInformationTuple
     * 
     * @param correctInformationTuple correctInformationTuple
     */
    public void setCorrectInformationTuple(List<InformationEntity> correctInformationTuple) {
        this.correctInformationTuple = correctInformationTuple;
    }

    /**
     * 获取 productRegistrationCodeInformationTuple
     * 
     * @return productRegistrationCodeInformationTuple.
     */
    public List<InformationEntity> getProductRegistrationCodeInformationTuple() {
        return productRegistrationCodeInformationTuple;
    }

    /**
     * 设置 productRegistrationCodeInformationTuple
     * 
     * @param productRegistrationCodeInformationTuple productRegistrationCodeInformationTuple
     */
    public void setProductRegistrationCodeInformationTuple(List<InformationEntity> productRegistrationCodeInformationTuple) {
        this.productRegistrationCodeInformationTuple = productRegistrationCodeInformationTuple;
    }

}
