/*
 * 文件名：FinNetProductBonusDealDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zqz
 * 修改时间：2019年1月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.productduration.netproductbonusscheme.dto;

import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zqz
 * @version 1.0 2019年1月14日
 * @see FinNetProductBonusDealDto
 * @since 1.0
 */
public class FinNetProductBonusDealDto extends TrdProductDeal{

    /**
     * 产品简称
     */
    private String finprodAbbr;
    
    /**
     * 交易方向 C9-现金分红，C10-红利再投
     */
    private String psstr;
    
    /**
     * 产品id
     */
    private String finprodMarketId;
    
    /**
     * 获取 finprodAbbr
     * @return finprodAbbr.
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }
    
    /**
     * 设置 finprodAbbr
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }
    
    /**
     * 获取 psstr
     * @return psstr.
     */
    public String getPsstr() {
        return psstr;
    }
    
    /**
     * 设置 psstr
     * @param psstr psstr
     */
    public void setPsstr(String psstr) {
        this.psstr = psstr;
    }

    
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
   
}
