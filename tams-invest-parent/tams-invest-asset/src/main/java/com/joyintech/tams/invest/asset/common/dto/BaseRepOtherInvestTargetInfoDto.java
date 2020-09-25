/*
 * 文件名：BaseRepOtherInvestTargetInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.common.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.RepOtherInvestTarget;

/**
 * 其他资产报送信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月2日
 * @see BaseRepOtherInvestTargetInfoDto
 * @since 1.0
 */
public class BaseRepOtherInvestTargetInfoDto {

    /**
     * 金融产品代码List
     */
    private List<String> repFinprodIdList;
    
    /**
     * 其他资产中债报送实体list
     */
    private List<RepOtherInvestTarget> repOtherInvestTargetList;

    
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
     * 获取 repOtherInvestTargetList
     * @return repOtherInvestTargetList.
     */
    public List<RepOtherInvestTarget> getRepOtherInvestTargetList() {
        return repOtherInvestTargetList;
    }

    
    /**
     * 设置 repOtherInvestTargetList
     * @param repOtherInvestTargetList repOtherInvestTargetList
     */
    public void setRepOtherInvestTargetList(List<RepOtherInvestTarget> repOtherInvestTargetList) {
        this.repOtherInvestTargetList = repOtherInvestTargetList;
    }
    
    
}
