/*
 * 文件名：BaseFinProductExerciseRuleMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.component.base.core.impl.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.component.base.core.dto.ValBaseFinProductExerciseRuleDto;
import com.joyintech.tams.common.base.entity.FinExerciseRule;

/**
 * 行权规则组件mapper
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年9月5日
 * @see ValBaseFinProductExerciseRuleMapper
 * @since 1.0
 */
@Repository
public interface ValBaseFinProductExerciseRuleMapper {
    /**
     * 
     * 行权规则组件删除
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    int delete(ValBaseFinProductExerciseRuleDto dto);
    /**
     * 
     * 行权规则组件查询
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    List<FinExerciseRule> select(ValBaseFinProductExerciseRuleDto dto);
}
