/*
 * 文件名：BussBenchMarketInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：张亮
 * 修改时间：2018年9月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.prod;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.component.business.prod.dto.BussFinProductInvestPeriodDto;

/**
 * 业绩基准组件接口
 * @author 陈卓
 * @version 1.0 2018年9月3日
 * @see BussBenchMarketInfoService211
 * @since 1.0
 */
public interface BussFinProductInvestPeriodService {
    
    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "bussFinProductInvestPeriodDto";
    
    List<BussFinProductInvestPeriodDto> queryPeriodList(Map<String,Object> map);
    
    
    
}
