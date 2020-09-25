/*
 * 文件名：BussFinProductAnapleroticService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：LiJun
 * 修改时间：2019年4月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.prod;

import java.util.List;

import com.joyintech.tams.component.business.prod.dto.BussFinProductAnapleroticInfoDto;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author LiJun
 * @version 1.0 2019年4月21日
 * @see BussFinProductAnapleroticService
 * @since 1.0
 */
public interface BussFinProductAnapleroticService {

    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "finProductAnapleroticTabInfoDtoList";
    
    /**
     * 根据组合代码或产品代码回补查询
     * <功能详细描述>
     * @param ptlId
     * @return
     * @see
     * @since 1.0
     */
    List<BussFinProductAnapleroticInfoDto> queryFinProductAnapleroticInfoById(String ptlId);
}
