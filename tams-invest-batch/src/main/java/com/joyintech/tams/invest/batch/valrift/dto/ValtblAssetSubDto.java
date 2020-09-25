/*
 * 文件名：ValtblAssetSubDto.java.
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.valrift.dto;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.RskValtblAssetSub;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年1月28日
 * @see ValtblAssetSubDto
 * @since 1.0
 */
public class ValtblAssetSubDto extends RskValtblAssetSub{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    private String hasChildren = DictCodeList.是否_否.getValue();
    
    private BigDecimal positionRatio = new BigDecimal(1); 
    
    /**
     * 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    private String finprodType2;
    
    /**
     * 市场代码
     */
    private String finprodMarketId;
    
    /**
     * 获取 hasChildren
     * @return hasChildren.
     */
    public String getHasChildren() {
        return hasChildren;
    }

    
    /**
     * 设置 hasChildren
     * @param hasChildren hasChildren
     */
    public void setHasChildren(String hasChildren) {
        this.hasChildren = hasChildren;
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
     * 获取 finprodType2
     * @return finprodType2.
     */
    public String getFinprodType2() {
        return finprodType2;
    }


    
    /**
     * 设置 finprodType2
     * @param finprodType2 finprodType2
     */
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }


	public String getFinprodMarketId() {
		return finprodMarketId;
	}


	public void setFinprodMarketId(String finprodMarketId) {
		this.finprodMarketId = finprodMarketId;
	}
    
    

}
