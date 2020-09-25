/*
 * 文件名：FinNetProductBonusDealDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zqz
 * 修改时间：2019年1月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.productduration.netproductbonusscheme.dto;

import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;

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
     * 交易币种名称
     */
    private String ccystr;
    
    /**
     * 产品id
     */
    private String finprodMarketId;
    /**
     * 收益类型
     */
    private String profitType;
    
    
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
     * 获取 ccystr
     * @return ccystr.
     */
    public String getCcystr() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种, getCcy());
    }
    
    /**
     * 设置 ccystr
     * @param ccystr ccystr
     */
    public void setCcystr(String ccystr) {
        this.ccystr = ccystr;
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
    
    /**
     * 获取 profitType
     * @return profitType.
     */
    public String getProfitType() {
        return StringUtil.isNotEmpty(profitType)?this.profitType:DictCodeList.收益类型_净值型.getValue();
    }

    /**
     * 设置 profitType
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }
}
