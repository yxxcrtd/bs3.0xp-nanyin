package com.joyintech.service.valuation.basicdata.base.service;/**
 * @author yanya
 * @create 2021-03-03 9:44
 */

import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.basicdata.base.dao.BaseFinProductExerciseRuleMapper;
import com.joyintech.service.valuation.basicdata.base.dto.BaseFinProductExerciseRuleDto;
import com.joyintech.tams.common.base.dao.FinExerciseRuleMapper;
import com.joyintech.tams.common.base.entity.FinExerciseRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName BaseFinProductExerciseRuleService
 * @Description: 金融产品现金流行权规则组件
 * @Author yanya
 * @Date 2021/3/5 0005
 * @Version V1.0
 **/
@Service(value="serviceBaseFinProductExerciseRuleService")
@Transactional(rollbackFor = Exception.class)
public class BaseFinProductExerciseRuleService {

    /**
     * 行权规则组件自动生成mapper
     */
    @Autowired
    private FinExerciseRuleMapper finExerciseRuleMapper;

    /**
     * 行权规则组件自定义mapper
     */
    @Autowired
    private BaseFinProductExerciseRuleMapper baseFinProductExerciseRuleMapper;

    /**
     * 行权规则组件新建
     */
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
    public boolean delete(BaseFinProductExerciseRuleDto dto) {
        if (StringUtil.isNotEmpty(dto.getFinprodId())) {
            baseFinProductExerciseRuleMapper.delete(dto);
        }
        return false;
    }

    /**
     * 行权规则组件查询
     */
    public List<FinExerciseRule> select(BaseFinProductExerciseRuleDto dto) {
        return baseFinProductExerciseRuleMapper.select(dto);
    }

    /**
     * 行权规则组件修改
     */
    public boolean update(BaseFinProductExerciseRuleDto dto) {
        if (StringUtil.isNotEmpty(dto.getFinprodId()) && dto.getFinExerciseRuleList() != null
                && dto.getFinExerciseRuleList().size() > 0) {
            if(this.delete(dto)){
                return this.insert(dto.getFinExerciseRuleList());
            }
        }
        return false;
    }
}
