/*
 * 文件名：BussCalendarService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年7月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.asset;

import com.joyintech.tams.common.base.entity.FinProductTypeConfig;

/**
 * 资产五级分类
 * 
 * @author renping
 * @version 1.0 2019年7月11日
 * @see BussAssetFiveTypeService
 * @since 1.0
 */
public interface BussAssetFiveTypeService {

    /**
     * 资产五级分类联动处理
     * 
     * @param finProductTypeConfig 参数
     * @return 输出
     * @see
     * @since 1.0
     */
    FinProductTypeConfig doAssetFiveTypeChange(FinProductTypeConfig finProductTypeConfig);
}
