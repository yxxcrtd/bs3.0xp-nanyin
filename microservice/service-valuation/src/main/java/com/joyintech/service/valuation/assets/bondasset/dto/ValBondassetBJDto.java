/*
 * 文件名：ValBondassetBJDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月30日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.assets.bondasset.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2018年10月30日
 * @see ValBondassetBJDto
 * @since 1.0
 */
public class ValBondassetBJDto extends BaseEntity {
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 60568328893292320L;

    private Date efdate;
    
    private BigDecimal rate2;


    /**
     * 获取 efdate
     * @return efdate.
     */
    public Date getEfdate() {
        return efdate;
    }

    
    /**
     * 设置 efdate
     * @param efdate efdate
     */
    public void setEfdate(Date efdate) {
        this.efdate = efdate;
    }
    
    /**
     * 获取 rate2
     * @return rate2.
     */
    public BigDecimal getRate2() {
        return rate2;
    }


    
    /**
     * 设置 rate2
     * @param rate2 rate2
     */
    public void setRate2(BigDecimal rate2) {
        this.rate2 = rate2;
    }
}
