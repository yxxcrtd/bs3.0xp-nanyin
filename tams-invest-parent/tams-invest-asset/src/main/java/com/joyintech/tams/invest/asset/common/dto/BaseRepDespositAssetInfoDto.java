/*
 * 文件名：BaseRepDespositAssetInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年9月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.common.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.RepAssetBankDeposit;

/**
 * 存款资产报送信息组件Dto
 * 〈功能详细描述〉
 * @author zhangjun
 * @version 1.0 2018年9月3日
 * @see BaseRepDespositAssetInfoDto
 * @since 1.0
 */
public class BaseRepDespositAssetInfoDto{

    /**
     * List<银行存款类资产报送信息Entity>
     */
    private List<RepAssetBankDeposit> repAssetBankDepositList;
    
    /**
     * List<金融产品代码>
     */
    private List<String> repFinprodIdList;

    
    /**
     * 获取 repAssetBankDepositList
     * @return repAssetBankDepositList.
     */
    public List<RepAssetBankDeposit> getRepAssetBankDepositList() {
        return repAssetBankDepositList;
    }

    
    /**
     * 设置 repAssetBankDepositList
     * @param repAssetBankDepositList repAssetBankDepositList
     */
    public void setRepAssetBankDepositList(List<RepAssetBankDeposit> repAssetBankDepositList) {
        this.repAssetBankDepositList = repAssetBankDepositList;
    }

    
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
    
    
    
}
