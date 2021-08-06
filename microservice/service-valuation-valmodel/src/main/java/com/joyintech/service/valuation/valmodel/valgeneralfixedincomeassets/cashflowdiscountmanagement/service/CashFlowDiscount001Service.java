package com.joyintech.service.valuation.valmodel.valgeneralfixedincomeassets.cashflowdiscountmanagement.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.base.BaseService;
import com.joyintech.service.valuation.valmodel.valgeneralfixedincomeassets.cashflowdiscountmanagement.dao.CashFlowDiscount001Mapper;
import com.joyintech.service.valuation.valmodel.valgeneralfixedincomeassets.cashflowdiscountmanagement.dto.CashFlowDiscount001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 现金流折现模型曲线微服务的服务类
 * 
 * @author 刘华明
 * @version 1.0 2021年7月1日
 * @see CashFlowDiscount001Service
 * @since 1.0
 */
@Service(value="serviceCashFlowDiscount001Service")
@Transactional(rollbackFor = Exception.class)
public class CashFlowDiscount001Service extends BaseService<CashFlowDiscount001Dto> {

    /**
     * CashFlowDiscount001Mapper
     */
    @Autowired
    private CashFlowDiscount001Mapper mapper;

    /**
     * 删除
     * @param dto
     * @return
     */
    public int delete(CashFlowDiscount001Dto dto) {
        return mapper.deleteByPrimaryKey(dto.getSeqNo());
    }

    /**
     * 更新
     * @param dto
     */
    public void update(CashFlowDiscount001Dto dto){
        mapper.update(dto);
    }

    /**
     * 插入
     * @param dto
     */
    public void insert(CashFlowDiscount001Dto dto){
        // 保存
        mapper.insert(dto);
    }

    /**
     * 导出
     * @param map
     * @return
     */
    public List<CashFlowDiscount001Dto> selectForExport(Map<String, Object> map) {
        return mapper.getForExcel(map);
    }

    /**
     * 查询
     * @param map
     * @return
     */
    public List<CashFlowDiscount001Dto> getCashFlowDiscountList(Map<String, Object> map) {
        return mapper.getCashFlowDiscountList(map);
    }

    /**
     * 现金流折现模型曲线配置查询
     *
     * @param map
     * @return List<CashFlowDiscount001Dto>
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<CashFlowDiscount001Dto> getCashFlowDiscountList(int page, int length, Map<String, Object> map) throws Exception {
        return super.queryForPage(page,length,()-> mapper.getCashFlowDiscountList(map));
    }

    /**
     * 增加时重复校验
     * @param dto
     * @return
     */
    public boolean haveData(CashFlowDiscount001Dto dto){
        int count=mapper.checkData(dto);
        if(count>0){
            return true;
        }
        return false;
    }
}
