/*
 * 文件名：BaseRepAssetEquityInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.report.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.RepAssetEquity;

/**
 *  权益类资产中债报送组件Dto
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月2日
 * @see BaseRepAssetEquityInfoDto
 * @since 1.0
 */
public class BaseRepAssetEquityInfoDto {

    /**
     * 金融产品代码List
     */
    private List<String> repFinprodIdList;
    
    /**
     * 权益类资产中债报送实体list
     */
    private List<RepAssetEquity> repAssetEquityList;

    
    /**
     * 获取 repFinprodIdList
     * @return repFinprodIdList.
     */
    public List<String> getRepFinprodIdList() {
        return repFinprodIdList;
    }

    
    /**
     * 设置 repFinprodIdList
     * @param repFinprodIdList repFinprodIdList
     */
    public void setRepFinprodIdList(List<String> repFinprodIdList) {
        this.repFinprodIdList = repFinprodIdList;
    }

    
    /**
     * 获取 repAssetEquityList
     * @return repAssetEquityList.
     */
    public List<RepAssetEquity> getRepAssetEquityList() {
        return repAssetEquityList;
    }

    
    /**
     * 设置 repAssetEquityList
     * @param repAssetEquityList repAssetEquityList
     */
    public void setRepAssetEquityList(List<RepAssetEquity> repAssetEquityList) {
        this.repAssetEquityList = repAssetEquityList;
    }
    
}
