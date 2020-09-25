/*
 * 文件名：BaseRepBondAssetInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.report.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.RepAssetBond;

/**
 * 债券资产报送信息组件dto
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年9月3日
 * @see BaseRepBondAssetInfoDto
 * @since 1.0
 */
public class BaseRepBondAssetInfoDto{

    /**
     * 金融产品代码List
     */
    private List<String> repFinprodIdList;
    /**
     * 债券资产报送信息Entity
     */
    private List<RepAssetBond> repAssetBondList;
    
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
     * 获取 repAssetBondList
     * @return repAssetBondList.
     */
    public List<RepAssetBond> getRepAssetBondList() {
        return repAssetBondList;
    }
    
    /**
     * 设置 repAssetBondList
     * @param repAssetBondList repAssetBondList
     */
    public void setRepAssetBondList(List<RepAssetBond> repAssetBondList) {
        this.repAssetBondList = repAssetBondList;
    }
    
    

}
