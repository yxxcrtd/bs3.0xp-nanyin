package com.joyintech.service.valuation.basicdata.base.service;/**
 * @author yanya
 * @create 2021-03-03 9:31
 */

import com.joyintech.service.valuation.basicdata.base.dao.BaseFinProductCashCountingRuleMapper;
import com.joyintech.service.valuation.basicdata.base.dto.BaseFinProductCashCountingRuleDto;
import com.joyintech.tams.common.base.entity.FinFloatCountingRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName BaseFinProductCashCountingRuleService
 * @Description: 现金流计提规则
 * @Author yanya
 * @Date 2021/3/5 0005
 * @Version V1.0
 **/
@Service(value="serviceBaseFinProductCashCountingRuleService")
@Transactional(rollbackFor = Exception.class)
public class BaseFinProductCashCountingRuleService {

    /**
     *
     */
    @Autowired
    private BaseFinProductCashCountingRuleMapper baseFinProductCashCountingRuleMapper;

    /**
     * 现金流计划新建
     */
    public boolean insert(List<FinFloatCountingRule> finCashCountingRuleList) {
        int i =0;
        if(finCashCountingRuleList!=null){
            for(FinFloatCountingRule finFloatCountingRule:finCashCountingRuleList){
                baseFinProductCashCountingRuleMapper.insert(finFloatCountingRule);
                i++;
            }
        }
        return i>0 && i==finCashCountingRuleList.size()?true:false;
    }
    /**
     * 现金流计算查询
     */
    public List<FinFloatCountingRule> selectByBaseFinProductCashCountingRuleDto(BaseFinProductCashCountingRuleDto baseFinProductCashCountingRuleDto) {

        return baseFinProductCashCountingRuleMapper.selectByBaseFinProductCashCountingRuleDto(baseFinProductCashCountingRuleDto);
    }
    /**
     * 现金流计算计划删除
     */
    public boolean deleteByBaseFinProductCashCountingRuleDto(BaseFinProductCashCountingRuleDto baseFinProductCashCountingRuleDto) {
        return baseFinProductCashCountingRuleMapper.deleteByBaseFinProductCashCountingRuleDto(baseFinProductCashCountingRuleDto)>0?true:false;
    }
    /**
     * 现金流计算计划修改
     */
    public boolean updateBaseFinProductCashCountingRuleDto(BaseFinProductCashCountingRuleDto baseFinProductCashCountingRuleDto) {
        int i =0;
        if(baseFinProductCashCountingRuleDto.getFinCashCountingRuleList().size()>0){
            baseFinProductCashCountingRuleMapper.deleteByBaseFinProductCashCountingRuleDto(baseFinProductCashCountingRuleDto);
            for(FinFloatCountingRule baseFinProductCashCountingRule:baseFinProductCashCountingRuleDto.getFinCashCountingRuleList()){
                baseFinProductCashCountingRuleMapper.insert(baseFinProductCashCountingRule);
                i++;
            }
        }
        return i>0 && i==baseFinProductCashCountingRuleDto.getFinCashCountingRuleList().size()?true:false;
    }

    public boolean insertFinFloatCountingRule(FinFloatCountingRule finCashCountingRule) {
        if(finCashCountingRule!=null){
            baseFinProductCashCountingRuleMapper.insert(finCashCountingRule);
            return true;
        }
        return false;
    }
}
