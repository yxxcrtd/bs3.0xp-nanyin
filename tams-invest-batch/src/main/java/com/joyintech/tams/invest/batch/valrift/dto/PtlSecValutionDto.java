/*
 * 文件名：PtlSecValutionDto.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：zhangnian 修改时间：2019年1月25日 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.valrift.dto;

import com.joyintech.tams.common.base.entity.PtlSecValution;

/**
 * 组合资产估值Dto 〈功能详细描述〉
 * 
 * @author zhangnian
 * @version 1.0 2019年1月25日
 * @see PtlSecValutionDto
 * @since 1.0
 */
public class PtlSecValutionDto extends PtlSecValution {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    private String finprodType2;

    private String finprodMarketId;

    /**
     * 获取 finprodType2
     * 
     * @return finprodType2.
     */
    public String getFinprodType2() {
        return finprodType2;
    }

    /**
     * 设置 finprodType2
     * 
     * @param finprodType2 finprodType2
     */
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }

    /**
     * 获取 finprodMarketId
     * 
     * @return finprodMarketId.
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    /**
     * 设置 finprodMarketId
     * 
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

}
