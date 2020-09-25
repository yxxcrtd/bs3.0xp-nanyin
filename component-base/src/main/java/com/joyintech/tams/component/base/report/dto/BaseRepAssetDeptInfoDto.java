/*
 * 文件名：BaseRepAssetDeptInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年9月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.report.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.RepAssetDept;

/**
 * 资产负债信息报送组件实体
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年9月4日
 * @see BaseRepAssetDeptInfoDto
 * @since 1.0
 */
public class BaseRepAssetDeptInfoDto{

    /**
     * 
     */
    private List<RepAssetDept> repAssetDeptList;
    
    /**
     * 
     */
    private List<String> finprodIdList;


    
    
    /**
     * 获取 repAssetDeptList
     * @return repAssetDeptList.
     */
    public List<RepAssetDept> getRepAssetDeptList() {
        return repAssetDeptList;
    }




    
    /**
     * 设置 repAssetDeptList
     * @param repAssetDeptList repAssetDeptList
     */
    public void setRepAssetDeptList(List<RepAssetDept> repAssetDeptList) {
        this.repAssetDeptList = repAssetDeptList;
    }




    /**
     * 获取 finprodIdList
     * @return finprodIdList.
     */
    public List<String> getFinprodIdList() {
        return finprodIdList;
    }



    
    /**
     * 设置 finprodIdList
     * @param finprodIdList finprodIdList
     */
    public void setFinprodIdList(List<String> finprodIdList) {
        this.finprodIdList = finprodIdList;
    }
    
    
    

}
