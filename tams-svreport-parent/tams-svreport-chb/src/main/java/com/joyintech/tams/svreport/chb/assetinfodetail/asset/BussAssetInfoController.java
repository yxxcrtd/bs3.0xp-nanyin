/*
 * 文件名：BussFinBondRepInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.svreport.chb.assetinfodetail.asset;

import org.springframework.ui.Model;

/**
 *  债券中债报送
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月23日
 * @see BussAssetInfoController
 * @since 1.0
 */
public interface BussAssetInfoController {

    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "bussFinBondRepInfoDto";

    void setEntity(Model model);
}
