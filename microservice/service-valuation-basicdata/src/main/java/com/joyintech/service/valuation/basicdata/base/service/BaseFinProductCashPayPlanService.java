package com.joyintech.service.valuation.basicdata.base.service;/**
 * @author yanya
 * @create 2021-03-03 9:28
 */

import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.basicdata.base.dao.BaseFinProductCashPayPlanMapper;
import com.joyintech.service.valuation.basicdata.base.dto.BaseFinProductCashPayPlanDto;
import com.joyintech.tams.common.base.dao.FinCashPlanMapper;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName BaseFinProductCashPayPlanService
 * @Description: 金融产品现金流支付计划组件
 * @Author yanya
 * @Date 2021/3/5 0005
 * @Version V1.0
 **/
@Service(value="serviceBaseFinProductCashPayPlanService")
@Transactional(rollbackFor = Exception.class)
public class BaseFinProductCashPayPlanService {

    /**
     * 金融产品现金流支付计划组件mapper
     */
    @Autowired
    private BaseFinProductCashPayPlanMapper baseFinProductCashPayPlanMapper;
    /**
     * 自动生成mapper
     */
    @Autowired
    private FinCashPlanMapper finCashPlanMapper;

    /**
     * 删除金融产品现金流支付计划信息
     */
    public boolean deleteByPrimaryKey(FinCashPlan finCashPlan) {
        return baseFinProductCashPayPlanMapper.deleteByPrimaryKey(finCashPlan)==1?true:false;
    }

    /**
     * 新增金融产品现金流支付计划信息
     */
    public boolean insertList(List<FinCashPlan> list) {
        if(list!=null&&list.size()>0){
            for(FinCashPlan dto:list){
                //新增数据前根据现金流代码和结束日删除已有数据
                // finCashPlanMapper.deleteByPrimaryKey(dto);
                finCashPlanMapper.insert(dto);
            }
            return true;
        }
        return false;
    }

    /**
     * 修改金融产品现金流支付计划信息
     */
    public boolean update(BaseFinProductCashPayPlanDto dto) {
        List<FinCashPlan> list =dto.getFinCashPlanList();
        String cashId =dto.getCashId();
        if(StringUtil.isNotEmpty(cashId)&&StringUtil.isNotEmpty(list)){
            FinCashPlan entity =new FinCashPlan();
            entity.setCashId(cashId);
            entity.setMdate(dto.getMdate());
            entity.setBranch(dto.getBranch());
            entity.setFinprodId(dto.getFinprodId());
            if(this.deleteByPrimaryKey(entity)){
                this.insertList(list);
                return true;
            }
        }
        return false;

    }

    /**
     * 查询金融产品现金流支付计划信息
     */
    public List<FinCashPlan> select(FinCashPlan finCashPlan) {
        return baseFinProductCashPayPlanMapper.selectDetail(finCashPlan);
    }

    //新增数据前根据现金流代码删除已有数据
    public Boolean deleteByCashId(String cashId) {
        return baseFinProductCashPayPlanMapper.deleteByCashId(cashId);
    }
}
