/*
 * 文件名：BaseRepBankBackAssetInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：刘二通
 * 修改时间：2018年11月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.base.report.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.RepLiabilities;

/**
 * 拆借及回购资产报送DTO
 *
 * @author 刘二通
 * @version 1.0 2018年11月5日
 * @see BaseRepBankBackAssetInfoDto
 * @since 1.0
 */
public class BaseRepBankBackAssetInfoDto {

    /**
     * 拆借及回购资产报送DTO
     */
    private RepLiabilities repLiabilities;

    /**
     * List<金融产品代码>
     */
    private List<String> repFinprodIdList;

    /**
     * 获取 repLiabilities
     *
     * @return repLiabilities.
     */
    public RepLiabilities getRepLiabilities() {
        return repLiabilities;
    }

    /**
     * 设置 repLiabilities
     *
     * @param repLiabilities repLiabilities
     */
    public void setRepLiabilities(RepLiabilities repLiabilities) {
        this.repLiabilities = repLiabilities;
    }

    /**
     * 获取 repFinprodIdList
     *
     * @return repFinprodIdList.
     */
    public List<String> getRepFinprodIdList() {
        return repFinprodIdList;
    }

    /**
     * 设置 repFinprodIdList
     *
     * @param repFinprodIdList repFinprodIdList
     */
    public void setRepFinprodIdList(List<String> repFinprodIdList) {
        this.repFinprodIdList = repFinprodIdList;
    }

}
