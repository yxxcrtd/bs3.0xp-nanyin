/*
 * 文件名：BaseFinAnapleroticCalcRuleService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：LiJun
 * 修改时间：2019年4月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.core;

import java.util.List;
import org.springframework.stereotype.Component;

import com.joyintech.tams.common.base.entity.FinClawbackCalcRule;
import com.joyintech.tams.common.base.entity.FinClawbackCalcRuleKey;


/**
 * 业绩回补计算规则
 * 〈功能详细描述〉
 * @author LiJun
 * @version 1.0 2019年4月24日
 * @see BaseFinAnapleroticCalcRuleService
 * @since 1.0
 */
@Component
public interface BaseFinAnapleroticCalcRuleService {

    /**
     * 
     * 删除业绩回补计算规则
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    boolean delete(FinClawbackCalcRule dto);
    /**
     * 插入业绩回补计算规则
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    boolean insert(FinClawbackCalcRule entityList);
    
   /**
     * 查询业绩回补计算规则
     * <功能详细描述>
     * @param key
     * @return
     * @see
     * @since 1.0
     */
    List<FinClawbackCalcRule> selectListByPrimaryKey(FinClawbackCalcRuleKey key);

}
