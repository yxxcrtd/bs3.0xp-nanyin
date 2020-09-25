/*
 * 文件名：BaseFinProductExerciseRuleServiceImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.component.base.core.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.component.base.core.ValBaseFinProductExerciseRuleService;
import com.joyintech.service.valuation.component.base.core.dto.ValBaseFinProductExerciseRuleDto;
import com.joyintech.service.valuation.component.base.core.impl.dao.ValBaseFinProductExerciseRuleMapper;
import com.joyintech.tams.common.base.dao.FinExerciseRuleMapper;
import com.joyintech.tams.common.base.entity.FinExerciseRule;

/**
 * 行权规则组件实现类 〈功能详细描述〉
 * 
 * @author xiexinting
 * @version 1.0 2018年9月5日
 * @see ValBaseFinProductExerciseRuleServiceImpl
 * @since 1.0
 */
@Service
public class ValBaseFinProductExerciseRuleServiceImpl implements ValBaseFinProductExerciseRuleService {

    /**
     * 行权规则组件自动生成mapper
     */
    @Autowired
    private FinExerciseRuleMapper finExerciseRuleMapper;

    /**
     * 行权规则组件自定义mapper
     */
    @Autowired
    private ValBaseFinProductExerciseRuleMapper baseFinProductExerciseRuleMapper;

    /**
     * 行权规则组件新建
     */
    @Override
    public boolean insert(List<FinExerciseRule> finExerciseRuleList) {
        if (finExerciseRuleList != null && finExerciseRuleList.size() > 0) {
            for (FinExerciseRule finExerciseRule : finExerciseRuleList) {
                finExerciseRuleMapper.insert(finExerciseRule);
            }
            return true;
        }
        return false;
    }

    /**
     * 行权规则组件删除
     */
    @Override
    public boolean delete(ValBaseFinProductExerciseRuleDto dto) {
        if (StringUtil.isNotEmpty(dto.getFinprodId())) {
            baseFinProductExerciseRuleMapper.delete(dto);
        }
        return false;
    }

    /**
     * 行权规则组件查询
     */
    @Override
    public List<FinExerciseRule> select(ValBaseFinProductExerciseRuleDto dto) {
        return baseFinProductExerciseRuleMapper.select(dto);
    }

    /**
     * 行权规则组件修改
     */
    @Override
    public boolean update(ValBaseFinProductExerciseRuleDto dto) {
        if (StringUtil.isNotEmpty(dto.getFinprodId()) && dto.getFinExerciseRuleList() != null
            && dto.getFinExerciseRuleList().size() > 0) {
            if(this.delete(dto)){
               return this.insert(dto.getFinExerciseRuleList());
            }
        }
        return false;
    }

}
