package com.joyintech.service.valuation.basicdata.base.service;/**
 * @author yanya
 * @create 2021-03-03 9:22
 */

import com.joyintech.framework.base.BaseService;
import com.joyintech.service.valuation.basicdata.base.dao.BaseFinProductCashCalcRuleMapper;
import com.joyintech.service.valuation.basicdata.base.dto.BaseFinProductCashCalcRuleDto;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName BaseFinProductCashCalcRuleService
 * @Description: 现金流计算规则
 * @Author yanya
 * @Date 2021/3/5 0005
 * @Version V1.0
 **/
@Service(value="serviceBaseFinProductCashCalcRuleService")
@Transactional(rollbackFor = Exception.class)
public class BaseFinProductCashCalcRuleService extends BaseService<FinCashCalcRule> {

    /**
     *
     */
    @Autowired
    private BaseFinProductCashCalcRuleMapper baseFinProductCashCalcRuleMapper;

    /**
     * 现金流计划新建
     */
    public boolean insert(List<FinCashCalcRule> finCashCalcRuleList) {
        int i =0;
        if(finCashCalcRuleList!=null){
            for(FinCashCalcRule finCashCalcRule:finCashCalcRuleList){
                baseFinProductCashCalcRuleMapper.insert(finCashCalcRule);
                i++;
            }
        }
        return i>0 && i==finCashCalcRuleList.size()?true:false;
    }
    /**
     * 现金流计算查询
     */
    public List<FinCashCalcRule> selectByBaseFinProductCashCalcRuleDto(BaseFinProductCashCalcRuleDto baseFinProductCashCalcRuleDto) {

        return baseFinProductCashCalcRuleMapper.selectByBaseFinProductCashCalcRuleDto(baseFinProductCashCalcRuleDto);
    }
    /**
     * 现金流计算计划删除
     */
    public boolean deleteByBaseFinProductCashCalcRuleDto(BaseFinProductCashCalcRuleDto baseFinProductCashCalcRuleDto) {
        return baseFinProductCashCalcRuleMapper.deleteByBaseFinProductCashCalcRuleDto(baseFinProductCashCalcRuleDto)>0?true:false;
    }
    /**
     * 现金流计算计划修改
     */
    public boolean updateBaseFinProductCashCalcRuleDto(BaseFinProductCashCalcRuleDto baseFinProductCashCalcRuleDto) {
        int i =0;
        if(baseFinProductCashCalcRuleDto.getFinCashCalcRuleList().size()>0){
            baseFinProductCashCalcRuleMapper.deleteByBaseFinProductCashCalcRuleDto(baseFinProductCashCalcRuleDto);
            for(FinCashCalcRule baseFinProductCashCalcRule:baseFinProductCashCalcRuleDto.getFinCashCalcRuleList()){
                baseFinProductCashCalcRuleMapper.insert(baseFinProductCashCalcRule);
                i++;
            }
        }
        return i>0 && i==baseFinProductCashCalcRuleDto.getFinCashCalcRuleList().size()?true:false;
    }

    /**
     * 根据现金流ID及产品代码删除初始利率
     * @param baseFinProductCashCalcRuleDto
     * @return
     */
    public boolean deleteBaseFinProductCashCalcRuleByCondition(BaseFinProductCashCalcRuleDto baseFinProductCashCalcRuleDto) {
        return baseFinProductCashCalcRuleMapper.deleteBaseFinProductCashCalcRuleByCondition(baseFinProductCashCalcRuleDto)>0 ? true : false;
    }

}
