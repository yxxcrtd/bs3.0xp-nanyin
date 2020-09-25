/*
 * 文件名：Interf190011InEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年1月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i190011.entity;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.ta.dto.SendBonusDto;

/**
 * 净值分红信息查询接口（190011）-详细处理
 * 
 * @author zhangrui1
 * @version 1.0 2019年1月18日
 * @see Interf190011InEntity
 * @since 1.0
 */
public class Interf190011InEntity {

    /**
     * 净值分红信息查询接口（190011）查询DTO
     */
    private SendBonusDto dto;

    public Interf190011InEntity(SendBonusDto dto) {
        this.dto = dto;
    }

    /**
     * 取得 产品代码
     * 
     * @return 产品代码
     */
    @EsbName(value = "PRODUCT_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 32)
    @Required
    public String getProdCode() {
        return dto.getProdId();
    }

    /**
     * 取得 母产品代码
     * 
     * @return 母产品代码
     */
    @EsbName(value = "MOTHER_PROD_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 32)
    public String getMotherProdCode() {
        return dto.getProdId();
    }

    /**
     * 取得 产品期次号
     * 
     * @return 产品期次号
     */
    @EsbName(value = "PROD_PERIOD_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 32)
    public String getProdPeriodNo() {
        return dto.getProdBatchNo();
    }

    /**
     * 取得 销售商代码
     * 
     * @return 销售商代码
     */
    @EsbName(value = "VENDOR_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    @Required
    public String getVendorCode() {
        return dto.getVendorCode();
    }

    /**
     * 取得 查询起始日期
     * 
     * @return 查询起始日期
     */
    @EsbName(value = "QUERY_START_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getQueryStartDate() {
        return dto.getQueryStartDate();
    }

    /**
     * 取得 查询结束日期
     * 
     * @return 查询结束日期
     */
    @EsbName(value = "QUERY_END_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getQueryEndDate() {
        return dto.getQueryEndDate();
    }

    /**
     * 取得 第几页数据
     * 
     * @return 第几页数据
     */
    @EsbName(value = "PAGE_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    public String getPageNo() {
        return StringUtil.numberToString(dto.getPageNo());
    }

    /**
     * 取得 页大小
     * 
     * @return 页大小
     */
    @EsbName(value = "PAGE_SIZE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    public String getPageSize() {
        return StringUtil.numberToString(dto.getPageSize());
    }
}
