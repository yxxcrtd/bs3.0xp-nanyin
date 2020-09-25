/*
 * 文件名：BussRepAssetCreditNonstdInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.service.asset.common.dao.BussRepAssetCreditNonstdInfoMapper;
import com.joyintech.service.asset.common.dto.BussRepAssetCreditNonstdInfoDto;

/**
 * 信贷及非标资产报送信息DTO
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月6日
 * @see BussRepAssetCreditNonstdInfoService
 * @since 1.0
 */
@Service
public class BussRepAssetCreditNonstdInfoService {

    @Autowired
    private BussRepAssetCreditNonstdInfoMapper bussRepAssetCreditNonstdInfoMapper;
    
    public BussRepAssetCreditNonstdInfoDto getBussRepAssetCreditNonstdInfoByProductId(String finprodId){
        return bussRepAssetCreditNonstdInfoMapper.getBussRepAssetCreditNonstdInfoByProductId(finprodId);
    }
}
