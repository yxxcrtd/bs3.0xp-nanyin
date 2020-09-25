/*
 * 文件名：BaseRepAssetCreditNonstdInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.common.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.RepAssetCreditNonstd;

/**
 * 信贷及非标资产中债报送
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月2日
 * @see BaseRepAssetCreditNonstdInfoDto
 * @since 1.0
 */
public class BaseRepAssetCreditNonstdInfoDto {

    /**
     * 金融产品代码List
     */
    private List<String> repFinprodIdList;
    
    /**
     * 非标资产(票据或信用)资产中债报送实体list
     */
    private List<RepAssetCreditNonstd> repAssetCreditNonstdList;

    
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
     * 获取 repAssetCreditNonstdList
     * @return repAssetCreditNonstdList.
     */
    public List<RepAssetCreditNonstd> getRepAssetCreditNonstdList() {
        return repAssetCreditNonstdList;
    }

    
    /**
     * 设置 repAssetCreditNonstdList
     * @param repAssetCreditNonstdList repAssetCreditNonstdList
     */
    public void setRepAssetCreditNonstdList(List<RepAssetCreditNonstd> repAssetCreditNonstdList) {
        this.repAssetCreditNonstdList = repAssetCreditNonstdList;
    }
    
    
}
