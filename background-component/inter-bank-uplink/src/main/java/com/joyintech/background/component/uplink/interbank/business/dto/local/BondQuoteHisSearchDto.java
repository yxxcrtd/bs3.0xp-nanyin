/*
 * 文件名：BondQuoteLocalSearchDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2020年5月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.background.component.uplink.interbank.business.dto.local;

/**
 * 内部服务使用的报价查询条件对象
 * @author Jiaoxujin
 * @version 1.0 2020年5月14日
 * @see BondQuoteHisSearchDto
 * @since 1.0
 */
public class BondQuoteHisSearchDto  {

    /**
     * 版本号
     */
    private Long version;
    
    /**
     * 报价编号
     */
    private String quoteId;
    
    public BondQuoteHisSearchDto() {}
    
    public BondQuoteHisSearchDto(String quoteId, Long version) {
        this.quoteId = quoteId;
        this.version = version;
    }

    /**
     * 获取 version
     * @return version.
     */
    public Long getVersion() {
        return version;
    }

    /**
     * 设置 version
     * @param version version
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * 获取 quoteId
     * @return quoteId.
     */
    public String getQuoteId() {
        return quoteId;
    }

    /**
     * 设置 quoteId
     * @param quoteId quoteId
     */
    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

}
