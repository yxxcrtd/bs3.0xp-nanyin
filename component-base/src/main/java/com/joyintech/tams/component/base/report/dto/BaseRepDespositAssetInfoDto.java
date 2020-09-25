/*
 * 文件名：BaseRepDespositAssetInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年9月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.report.dto;

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
public class BaseRepDespositAssetInfoDto extends RepAssetBankDeposit{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * List<银行存款类资产报送信息Entity>
     */
    private List<RepAssetBankDeposit> RepAssetBankDepositList;
    /**
     * List<金融产品代码>
     */
    private List<String> RepFinprodIdList;
    
    /**
     * 获取List<银行存款类资产报送信息Entity>
     * @return repAssetBankDepositList.
     */
    public List<RepAssetBankDeposit> getRepAssetBankDepositList() {
        return RepAssetBankDepositList;
    }
    
    /**
     * 设置 List<银行存款类资产报送信息Entity>
     * @param repAssetBankDepositList repAssetBankDepositList
     */
    public void setRepAssetBankDepositList(List<RepAssetBankDeposit> repAssetBankDepositList) {
        RepAssetBankDepositList = repAssetBankDepositList;
    }
    
    /**
     * 获取 List<金融产品代码>
     * @return repFinprodIdList.
     */
    public List<String> getRepFinprodIdList() {
        return RepFinprodIdList;
    }
    
    /**
     * 设置 List<金融产品代码>
     * @param repFinprodIdList repFinprodIdList
     */
    public void setRepFinprodIdList(List<String> repFinprodIdList) {
        RepFinprodIdList = repFinprodIdList;
    }
    
    
}
