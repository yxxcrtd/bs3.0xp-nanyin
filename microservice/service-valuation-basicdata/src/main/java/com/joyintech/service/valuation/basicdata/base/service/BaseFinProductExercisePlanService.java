package com.joyintech.service.valuation.basicdata.base.service;/**
 * @author yanya
 * @create 2021-03-03 9:50
 */

import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.basicdata.base.dao.BaseFinProductExercisePlanMapper;
import com.joyintech.service.valuation.basicdata.base.dto.BaseFinProductExercisePlanDto;
import com.joyintech.tams.common.base.dao.FinExercisePlanMapper;
import com.joyintech.tams.common.base.entity.FinExercisePlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName BaseFinProductExercisePlanService
 * @Description: 金融产品现金流行权计划组件
 * @Author yanya
 * @Date 2021/3/5 0005
 * @Version V1.0
 **/
@Service(value="serviceBaseFinProductExercisePlanService")
@Transactional(rollbackFor = Exception.class)
public class BaseFinProductExercisePlanService {

    /**
     * 金融产品现金流行权计划组件mapper
     */
    @Autowired
    private BaseFinProductExercisePlanMapper baseFinProductExercisePlanMapper;
    /**
     *自动生成mapper
     */
    @Autowired
    private FinExercisePlanMapper finExercisePlanMapper;

    /**
     * 删除金融产品现金流行权计划组件信息
     */
    public boolean deleteByKey(FinExercisePlan finExercisePlan) {
        return baseFinProductExercisePlanMapper.deleteByKey(finExercisePlan)==1?true:false;
    }

    /**
     * 新增金融产品现金流行权计划组件信息
     */
    public boolean insert(List<FinExercisePlan> list) {
        if(list!=null&&list.size()>0){
            for(FinExercisePlan finExercisePlan:list){
                finExercisePlanMapper.insert(finExercisePlan);
            }
            return true;
        }
        return false;
    }

    /**
     *修改金融产品现金流行权计划组件信息
     */
    public boolean update(BaseFinProductExercisePlanDto dto) {
        List<FinExercisePlan> list=dto.getFinExercisePlanList();
        String cashId =dto.getCashId();
        if(StringUtil.isNotEmpty(cashId)&&StringUtil.isNotEmpty(list)){
            FinExercisePlan entity =new FinExercisePlan();
            entity.setCashId(cashId);
            entity.setExerciseType(dto.getExerciseType());
            entity.setExerciseDate(dto.getExerciseDate());
            entity.setExerciseStatus(dto.getExerciseStatus());
            entity.setFinprodId(dto.getFinprodId());
            entity.setBranch(dto.getBranch());
            if(this.deleteByKey(entity)){
                this.insert(list);
                return true;
            }
        }
        return false;

    }

    /**
     * 查询金融产品现金流行权计划组件信息
     */
    public List<FinExercisePlan> select(FinExercisePlan finExercisePlan) {
        return baseFinProductExercisePlanMapper.selectDetail(finExercisePlan);
    }

}
