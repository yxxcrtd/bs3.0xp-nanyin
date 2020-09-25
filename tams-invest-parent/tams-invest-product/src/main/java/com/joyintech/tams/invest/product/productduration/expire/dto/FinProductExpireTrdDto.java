/*
 * 文件名：FinProductExpireTrdDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：LiJun
 * 修改时间：2019年6月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.productduration.expire.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.PaTrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.product.dto.FinProductExpireBaseTrdDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author LiJun
 * @version 1.0 2019年6月14日
 * @see FinProductExpireTrdDto
 * @since 1.0
 */
public class FinProductExpireTrdDto extends FinProductExpireBaseTrdDto {

    private List<StlPayOrdersDto> stlPayOrdersDtoList;
    
    private TrdFinProductDealTotal trdFinProductDealTotal;
    
    private PaTrdFinProductDealTotal paTrdFinProductDealTotal;

    
    /**
     * 获取 stlPayOrdersDtoList
     * @return stlPayOrdersDtoList.
     */
    public List<StlPayOrdersDto> getStlPayOrdersDtoList() {
        return stlPayOrdersDtoList;
    }

    
    /**
     * 设置 stlPayOrdersDtoList
     * @param stlPayOrdersDtoList stlPayOrdersDtoList
     */
    public void setStlPayOrdersDtoList(List<StlPayOrdersDto> stlPayOrdersDtoList) {
        this.stlPayOrdersDtoList = stlPayOrdersDtoList;
    }


    
    /**
     * 获取 trdFinProductDealTotal
     * @return trdFinProductDealTotal.
     */
    public TrdFinProductDealTotal getTrdFinProductDealTotal() {
        return trdFinProductDealTotal;
    }


    
    /**
     * 设置 trdFinProductDealTotal
     * @param trdFinProductDealTotal trdFinProductDealTotal
     */
    public void setTrdFinProductDealTotal(TrdFinProductDealTotal trdFinProductDealTotal) {
        this.trdFinProductDealTotal = trdFinProductDealTotal;
    }


    
    /**
     * 获取 paTrdFinProductDealTotal
     * @return paTrdFinProductDealTotal.
     */
    public PaTrdFinProductDealTotal getPaTrdFinProductDealTotal() {
        return paTrdFinProductDealTotal;
    }


    
    /**
     * 设置 paTrdFinProductDealTotal
     * @param paTrdFinProductDealTotal paTrdFinProductDealTotal
     */
    public void setPaTrdFinProductDealTotal(PaTrdFinProductDealTotal paTrdFinProductDealTotal) {
        this.paTrdFinProductDealTotal = paTrdFinProductDealTotal;
    }

}
