/*
 * 文件名：SecurityEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.bankuplink.common.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

/**
 * 抵押品信息
 * @version 1.0 2018年11月22日
 * @see SecurityEntity
 * @since 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SecurityEntity {

    /**
     * 债券ID
     */
    private String secid;

    /**
     * 自增流水号
     */
    private Long seqNo;

    /**
     * 折算比例
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal convrate;

    /**
     * 券面总额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal facetotalamt;

    /**
     * 债券名称
     */
    private String secname;

    /**
     * 取得 债券ID
     * 
     * @return 债券ID
     */
    public String getSecid() {
        return secid;
    }

    /**
     * 设置 债券ID
     * 
     * @param secid 债券ID
     */
    public void setSecid(String secid) {
        this.secid = secid == null ? null : secid.trim();
    }

    /**
     * 取得 自增流水号
     * 
     * @return 自增流水号
     */
    public Long getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 自增流水号
     * 
     * @param seqNo 自增流水号
     */
    public void setSeqNo(Long seqNo) {
        this.seqNo = seqNo;
    }

    /**
     * 取得 折算比例
     * 
     * @return 折算比例
     */
    public BigDecimal getConvrate() {
        return convrate;
    }

    /**
     * 设置 折算比例
     * 
     * @param convrate 折算比例
     */
    public void setConvrate(BigDecimal convrate) {
        this.convrate = convrate;
    }

    /**
     * 取得 券面总额
     * 
     * @return 券面总额
     */
    public BigDecimal getFacetotalamt() {
        return facetotalamt;
    }

    /**
     * 设置 券面总额
     * 
     * @param facetotalamt 券面总额
     */
    public void setFacetotalamt(BigDecimal facetotalamt) {
        this.facetotalamt = facetotalamt;
    }

    /**
     * 取得 债券代码
     * 
     * @return 债券代码
     */
    public String getSecname() {
        return secname;
    }

    /**
     * 设置 债券代码
     * 
     * @param secname 债券代码
     */
    public void setSecname(String secname) {
        this.secname = secname == null ? null : secname.trim();
    }
}
