package com.joyintech.service.valuation.basicdata.base.service;/**
 * @author yanya
 * @create 2021-03-03 9:37
 */

import com.joyintech.service.valuation.basicdata.base.dao.BaseFinProductCashRateRuleMapper;
import com.joyintech.service.valuation.basicdata.base.dto.BaseFinProductCashRateRuleDto;
import com.joyintech.tams.common.base.entity.FinRateInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName BaseFinProductCashRateRuleService
 * @Description: 现金流利率规则信息组件
 * @Author Administrator
 * @Date 2021/3/5 0005
 * @Version V1.0
 **/
@Service(value="serviceBaseFinProductCashRateRuleService")
@Transactional(rollbackFor = Exception.class)
public class BaseFinProductCashRateRuleService {

    /**
     *
     */
    @Autowired
    private BaseFinProductCashRateRuleMapper baseFinProductCashRateRuleMapper;

    /**
     * 现金流利率信息新建
     */
    public boolean insert(List<FinRateInfo> finRateInfoList) {
        int i=0;
        if(finRateInfoList!=null && finRateInfoList.size()>0){
            for(FinRateInfo baseFinProductCashRateRuleDto:finRateInfoList){
                baseFinProductCashRateRuleMapper.insert(baseFinProductCashRateRuleDto);
                i++;
            }
        }
        return i>0 && i==finRateInfoList.size()?true:false;
    }

    /**
     * 现金流利率信息查询
     */
    public List<FinRateInfo> selectByBaseFinProductCashRateRuleDto(BaseFinProductCashRateRuleDto baseFinProductCashRateRuleDto) {
        return baseFinProductCashRateRuleMapper.selectByBaseFinProductCashRateRuleDto(baseFinProductCashRateRuleDto);
    }

    /**
     * 现金流利率信息删除
     */
    public boolean deleteByBaseFinProductCashRateRuleDto(BaseFinProductCashRateRuleDto baseFinProductCashRateRuleDto) {

        return baseFinProductCashRateRuleMapper.deleteByBaseFinProductCashRateRuleDto(baseFinProductCashRateRuleDto)>0?true:false;
    }

    /**
     * 现金流利率信息修改
     */
    public boolean updateBaseFinProductCashRateRuleDto(BaseFinProductCashRateRuleDto baseFinProductCashRateRuleDto) {
        int i=0;
        baseFinProductCashRateRuleMapper.deleteByBaseFinProductCashRateRuleDto(baseFinProductCashRateRuleDto);
        if(baseFinProductCashRateRuleDto.getFinRateInfoList().size()>0){
            for(FinRateInfo baseFinProductCashRateRule :baseFinProductCashRateRuleDto.getFinRateInfoList()){
                baseFinProductCashRateRuleMapper.insert(baseFinProductCashRateRule);
                i++;
            }
        }
        return i>0 && i==baseFinProductCashRateRuleDto.getFinRateInfoList().size()?true:false;
    }
}
