/*
 * 文件名：BussRepAssetMngProdInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：liuwei
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.report.asset.plan;


import com.joyintech.tams.component.business.report.asset.plan.dto.BussRepAssetMngProdInfo;
import com.joyintech.tams.component.business.report.asset.plan.dto.BussRepAssetMngProdInfoDto;

/**
 * 资产管理计划报送信息组件接口
 *
 * @author liuwei
 * @version 1.0 2018年9月18日
 * @see BussRepAssetMngProdInfoService
 * @since 1.0
 */
public interface BussRepAssetMngProdInfoService {
    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "bussRepAssetMngProdInfo";

    /**
     * 输出dto
     * @param dto
     * @return
     */
    BussRepAssetMngProdInfoDto getBussRepAssetMngProdInfoDto(BussRepAssetMngProdInfo dto);
    /**
     * 资产管理计划中债报送信息组件参数获取
     * @param finprodId 金融产品代码
     * @return
     */
    BussRepAssetMngProdInfo getByFinprodId(String finprodId);

}
