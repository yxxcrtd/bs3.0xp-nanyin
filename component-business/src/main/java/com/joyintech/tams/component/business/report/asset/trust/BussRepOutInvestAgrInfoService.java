/*
 * 文件名：BussRepOutInvestAgrInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：liuwei
 * 修改时间：2018年9月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.report.asset.trust;

import com.joyintech.tams.component.business.report.asset.trust.dto.BussRepOutInvestAgrInfo;
import com.joyintech.tams.component.business.report.asset.trust.dto.BussRepOutInvestAgrInfoDto;
/**
 * 委外投资—协议方式中债报送信息组件接口
 *
 * @author liuwei
 * @version 1.0 2018年9月19日
 * @see BussRepOutInvestAgrInfoService
 * @since 1.0
 */
public interface BussRepOutInvestAgrInfoService {
    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "bussRepAssetMngProdInfo";

    /**
     * 输出dto
     * @param dto
     * @return
     */
    BussRepOutInvestAgrInfoDto getBussRepOutInvestAgrInfoDto(BussRepOutInvestAgrInfo dto);
    /**
     * 委外投资-协议方式中债报送信息组件参数获取
     * @param finprodId 金融产品代码
     * @return
     */
    BussRepOutInvestAgrInfo getByFinprodId(String finprodId);

}
