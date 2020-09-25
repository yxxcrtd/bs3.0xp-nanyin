/*
 * 文件名：BaseRepFinProductInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwei
 * 修改时间：2018年8月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.base.report.prod;

import java.util.List;

import com.joyintech.tams.component.base.report.dto.BaseRepFinProductDto;
import com.joyintech.tams.component.base.report.dto.BaseRepFinProductInfoDto;

/**
 * 产品端中债报备信息组件接口类
 * 〈一句话功能简述〉 〈功能详细描述〉
 * @author huwei
 * @version 1.0 2018年8月31日
 * @see BaseRepFinProductInfoService
 * @since 1.0
 */
public interface BaseRepFinProductInfoService {

    /**
     * 新建产品中债报备信息 
     * <一句话功能简述> 
     * <功能详细描述>
     * @param dtos List
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean newRepFinProductInfo(List<BaseRepFinProductInfoDto> dtos);

    /**
     * 删除产品中债报备信息 
     * <一句话功能简述> 
     * <功能详细描述>
     * @param dto BaseRepFinProductDto
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean deleteRepFinProductInfo(BaseRepFinProductDto dto);

    /**
     * 修改产品中债报备信息 
     * <一句话功能简述> 
     * <功能详细描述>
     * @param dto BaseRepFinProductDto
     * @param dtos List
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean updateRepFinProductInfo(BaseRepFinProductDto dto,
                                           List<BaseRepFinProductInfoDto> dtos);

}
