/*
 * 文件名：SubPtlNode.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.valrift.dto;

import java.math.BigDecimal;

/**
 *  子级组合节点
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年1月25日
 * @see SubPtlNode
 * @since 1.0
 */
public class SubPtlNode {

    private String code;
    private String parentCode = "-";
    
    private BigDecimal positionRatio = new BigDecimal(1);
    private long virtAssetObjectIdSeq = -10000;
    
    public long getNextVirtAssetObjectId() {
		return (-- virtAssetObjectIdSeq);
	}
    
    public SubPtlNode(String code) {
        this.code = code;
    }

    
    /**
     * 获取 code
     * @return code.
     */
    public String getCode() {
        return code;
    }

    
    /**
     * 设置 code
     * @param code code
     */
    public void setCode(String code) {
        this.code = code;
    }

    
    /**
     * 获取 parentCode
     * @return parentCode.
     */
    public String getParentCode() {
        return parentCode;
    }

    
    /**
     * 设置 parentCode
     * @param parentCode parentCode
     */
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    
    /**
     * 获取 positionRatio
     * @return positionRatio.
     */
    public BigDecimal getPositionRatio() {
        return positionRatio;
    }

    
    /**
     * 设置 positionRatio
     * @param positionRatio positionRatio
     */
    public void setPositionRatio(BigDecimal positionRatio) {
        this.positionRatio = positionRatio;
    }

    
    /**
     * 获取 virtAssetObjectIdSeq
     * @return virtAssetObjectIdSeq.
     */
    public long getVirtAssetObjectIdSeq() {
        return virtAssetObjectIdSeq;
    }

    
    /**
     * 设置 virtAssetObjectIdSeq
     * @param virtAssetObjectIdSeq virtAssetObjectIdSeq
     */
    public void setVirtAssetObjectIdSeq(long virtAssetObjectIdSeq) {
        this.virtAssetObjectIdSeq = virtAssetObjectIdSeq;
    }
    
    
}
