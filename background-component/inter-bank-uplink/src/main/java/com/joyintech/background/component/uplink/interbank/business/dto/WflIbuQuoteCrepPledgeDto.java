/*
 * 文件名：IbuQuoteCrepPledgeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2020年5月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.uplink.interbank.business.dto;

import com.joyintech.background.component.uplink.interbank.database.entity.WflIbuQuoteCrepPledge;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author Jiaoxujin
 * @version 1.0 2020年5月14日
 * @see WflIbuQuoteCrepPledgeDto
 * @since 1.0
 */
public class WflIbuQuoteCrepPledgeDto extends WflIbuQuoteCrepPledge {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -302695451253888578L;

    /**
     * 市场代码
     */
    private String finprodMarketId;
    
    /**
     * 债券代码
     */
    private String finprodId;
    
    /**
     * 债券简称
     */
    private String finprodAbbr;
    
    /**
     * 托管机构
     */
    private String registOrg;
    
    /**
     * 获取 finprodMarketId
     * @return finprodMarketId.
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }
    
    /**
     * 设置 finprodMarketId
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    
    public String getFinprodId() {
        return finprodId;
    }

    
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    
    public String getRegistOrg() {
        return registOrg;
    }

    
    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg;
    }

    
}
