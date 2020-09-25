/*
 * 文件名：BaseFinProductOpenRuleMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.core.impl.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinOpenRule;
import com.joyintech.tams.component.base.core.dto.BaseFinProductOpenRuleDto;

/**
 * 开放规则组件mapper
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年9月4日
 * @see BaseFinProductOpenRuleMapper
 * @since 1.0
 */
@Repository
public interface BaseFinProductOpenRuleMapper {

    /**
     * 根据id和类型删除开放规则
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    int delete(BaseFinProductOpenRuleDto dto);

    
    /**
     * 查询
     * <功能详细描述>
     * @param cashId
     * @param openType
     * @return
     * @see
     * @since 1.0
     */
    List<FinOpenRule> search(BaseFinProductOpenRuleDto dto);

}
