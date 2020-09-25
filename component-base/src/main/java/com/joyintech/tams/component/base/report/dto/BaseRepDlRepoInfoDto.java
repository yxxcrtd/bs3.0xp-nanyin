/*
 * 文件名：RepDlRepoInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.report.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.RepAssetInterLend;

/**
 * 拆放同业及买入返售报送信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月5日
 * @see RepDlRepoInfoDto
 * @since 1.0
 */
public class BaseRepDlRepoInfoDto {
    /**
     * 金融产品代码List
     */
    private List<String> repFinprodIdList;
    
    /**
     * 拆放同业及买入返售报送信息list
     */
    private List<RepAssetInterLend> repDlRepoInfoList;

    
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
     * 获取 repDlRepoInfoList
     * @return repDlRepoInfoList.
     */
    public List<RepAssetInterLend> getRepDlRepoInfoList() {
        return repDlRepoInfoList;
    }

    
    /**
     * 设置 repDlRepoInfoList
     * @param repDlRepoInfoList repDlRepoInfoList
     */
    public void setRepDlRepoInfoList(List<RepAssetInterLend> repDlRepoInfoList) {
        this.repDlRepoInfoList = repDlRepoInfoList;
    }
    
    
}
