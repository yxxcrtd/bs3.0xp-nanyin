/*
 * 文件名：BaseFinProductOpenRuleService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.core;

import java.util.List;

import org.springframework.stereotype.Component;

import com.joyintech.tams.common.base.entity.FinOpenRule;
import com.joyintech.tams.component.base.core.dto.BaseFinProductOpenRuleDto;

/**
 * 开放规则组件
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年9月4日
 * @see BaseFinProductOpenRuleService
 * @since 1.0
 */
@Component
public interface BaseFinProductOpenRuleService {
    /**
     * 
     * 修改开放规则
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    boolean update(BaseFinProductOpenRuleDto dto);
    /**
     * 
     * 删除开放规则
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    boolean delete(BaseFinProductOpenRuleDto dto);
    /**
     * 新建开放规则
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    boolean insert(List<FinOpenRule> entityList);
    /**
     * 
     * 查询已生效的开放规则
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    List<FinOpenRule> search(BaseFinProductOpenRuleDto dto);
}
