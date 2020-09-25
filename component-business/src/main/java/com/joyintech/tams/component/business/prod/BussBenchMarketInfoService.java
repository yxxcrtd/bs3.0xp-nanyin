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
import com.joyintech.tams.common.base.entity.FinBaseRuleInfoKey;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.component.business.prod.dto.BussBenchMarkInfoDto;
import com.joyintech.tams.component.business.prod.dto.BussBenchMarkLayeredInfoDto;

/**
 * 业绩基准组件接口
 * @author 陈卓
 * @version 1.0 2018年9月3日
 * @see BussBenchMarketInfoService
 * @since 1.0
 */
public interface BussBenchMarketInfoService {
    
    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "bussBenchMarkInfoDto";
    
    /**
     * 复合基准一览取得
     * 
     * @return 复合基准一览
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getFinBaseRuleInfo();

    /**
     * 设置页面参数
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    BussBenchMarkInfoDto getByFinprodIdAndEffDate(Map<String,Object> param);
    
    /**
     * 设置页面参数
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    List<BussBenchMarkLayeredInfoDto> getBenchMarkLayeredInfoList(List<BussBenchMarkLayeredInfoDto> result);
    
    /**
     * 设置页面参数
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    List<BussBenchMarkLayeredInfoDto> getByFinprodIdAndEffDateList(Map<String,Object> param);

    /**
     * 根据基准代码删除
     * <功能详细描述>
     * @param finprodId
     * @see
     * @since 1.0
     */
    int delete(String baseRuleId);
    /**
     * 根据主键删除
     * <功能详细描述>
     * @param finprodId
     * @see
     * @since 1.0
     */
    int deleteByPrimaryKey(FinBaseRuleInfoKey key);
    
    
}
