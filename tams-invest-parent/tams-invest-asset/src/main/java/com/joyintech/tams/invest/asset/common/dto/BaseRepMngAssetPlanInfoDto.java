/*
 * 文件名：BaseRepMngAssetPlanInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.common.dto;

import com.joyintech.tams.common.base.entity.RepAssetMngPlan;

import java.util.List;

/**
 * 资产管理报送dto
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年9月4日
 * @see BaseRepMngAssetPlanInfoDto
 * @since 1.0
 */
public class BaseRepMngAssetPlanInfoDto {
    /**
     * List<金融产品代码>
     */
    private List<String> repFinprodIdList;
    /**
     * List<资产管理计划报送信息Entity>
     */
    private List<RepAssetMngPlan> repAssetMngPlanList;

    
    public List<String> getRepFinprodIdList() {
        return repFinprodIdList;
    }

    
    public void setRepFinprodIdList(List<String> repFinprodIdList) {
        this.repFinprodIdList = repFinprodIdList;
    }

    
    public List<RepAssetMngPlan> getRepAssetMngPlanList() {
        return repAssetMngPlanList;
    }

    
    public void setRepAssetMngPlanList(List<RepAssetMngPlan> repAssetMngPlanList) {
        this.repAssetMngPlanList = repAssetMngPlanList;
    }
    

}
