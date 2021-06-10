package com.joyintech.service.valuation.basicdata.base.service;/**
 * @author yanya
 * @create 2021-03-03 9:52
 */

import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.basicdata.base.dao.BaseFinProductOpenRuleMapper;
import com.joyintech.service.valuation.basicdata.base.dto.BaseFinProductOpenRuleDto;
import com.joyintech.tams.common.base.dao.FinOpenRuleMapper;
import com.joyintech.tams.common.base.entity.FinOpenRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BaseFinProductOpenRuleService
 * @Description: 开放规则组件
 * @Author yanya
 * @Date 2021/3/5 0005
 * @Version V1.0
 **/
@Service(value="serviceBaseFinProductOpenRuleService")
@Transactional(rollbackFor = Exception.class)
public class BaseFinProductOpenRuleService {

    /**
     * 开放规则组件自定义mapper
     */
    @Autowired
    private BaseFinProductOpenRuleMapper baseFinProductOpenRuleMapper;
    /**
     * 开放规则组件自动生成mapper
     */
    @Autowired
    private FinOpenRuleMapper finOpenRuleMapper;

    @Autowired
    private BaseFinProductOpenRuleService baseFinProductOpenRuleService;

    /**
     * 开放规则组件修改
     */
    public boolean update(BaseFinProductOpenRuleDto dto) {
        return baseFinProductOpenRuleService.delete(dto) && baseFinProductOpenRuleService.insert(dto.getFinOpenRuleList());
    }

    /**
     * 开放规则组件删除
     */
    public boolean delete(BaseFinProductOpenRuleDto dto) {
//        if(dto.getFinOpenRuleList()!=null && dto.getFinOpenRuleList().size()>0){
//            for(FinOpenRule finOpenRule:dto.getFinOpenRuleList()){
//                BaseFinProductOpenRuleDto entity = new BaseFinProductOpenRuleDto();
//                entity.setCashId(finOpenRule.getCashId());
//                entity.setOpenType(finOpenRule.getOpenType());
//                return baseFinProductOpenRuleMapper.delete(entity)>0;
//            }
//        }else{
//            if(dto.getCashId()!=null){
//                return baseFinProductOpenRuleMapper.delete(dto)>0;
//            }
//        }
        if(StringUtil.isNotEmpty(dto.getCashId())){
            baseFinProductOpenRuleMapper.delete(dto);
            return true;
        }
        return false;
    }

    /**
     * 开放规则组件新增
     */
    public boolean insert(List<FinOpenRule> entityList) {
        int i = 0;
        if(entityList!=null){
            for(FinOpenRule entity:entityList){
                i+=finOpenRuleMapper.insert(entity);
            }
            return i==entityList.size();
        }
        return false;
    }

    /**
     * 开放规则组件查找
     */
    public List<FinOpenRule> search(BaseFinProductOpenRuleDto dto) {
        List<FinOpenRule> dtoList = new ArrayList<FinOpenRule>();
        if(dto.getCashId()!=null){
            dtoList.addAll(baseFinProductOpenRuleMapper.search(dto));
        }
        return dtoList;
    }

}
