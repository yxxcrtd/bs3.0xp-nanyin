/*
 * 文件名：Interf150010InEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年1月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i150010.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 资管费用信息接口（150010）-输入
 * 
 * @author zhangrui1
 * @version 1.0 2019年1月18日
 * @see Interf150010InEntity
 * @since 1.0
 */
public class Interf150010InEntity {

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 母产品代码
     */
    private String mProdId;

    /**
     * 费项数组
     */
    private List<Interf150010InArrEntity> feeList;

    /**
     * 取得 产品代码
     * 
     * @return 产品代码
     */
    @EsbName(value = "PRODUCT_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 32)
    @Required
    public String getProdId() {
        return prodId;
    }

    /**
     * 取得 母产品代码
     * 
     * @return 母产品代码
     */
    @EsbName(value = "MOTHER_PROD_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 32)
    @Required
    public String getMProdId() {
        return mProdId;
    }

    /**
     * 取得 费项数组
     * 
     * @return 费项数组
     */
    @EsbName(value = "FEE_ARRAY")
    @EsbFieldType(value = EsbFieldType.ARRAY)
    public List<Interf150010InArrEntity> getFeeList() {
        return feeList;
    }

    /**
     * 设置 产品代码
     * 
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    /**
     * 设置 母产品代码
     * 
     * @param mProdId 母产品代码
     */
    public void setMProdId(String mProdId) {
        this.mProdId = mProdId;
    }

    /**
     * 设置 费项数组
     * 
     * @param feeList 费项数组
     */
    public void setFeeList(List<Interf150010InArrEntity> feeList) {
        this.feeList = feeList;
    }
}
