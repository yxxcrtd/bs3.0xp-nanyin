
package com.joyintech.service.valuation.valmodel.valgeneralfixedincomeassets.cashflowdiscountmanagement.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.valmodel.valgeneralfixedincomeassets.cashflowdiscountmanagement.dto.CashFlowDiscount001Dto;
import com.joyintech.service.valuation.valmodel.valgeneralfixedincomeassets.cashflowdiscountmanagement.service.CashFlowDiscount001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 现金流折现模型曲线微服务控制类
 * 
 * @author 刘华明
 * @version 1.0 2021年7月1日
 * @see CashFlowDiscount001Controller
 * @since 1.0
 */
@RestController("CashFlowDiscount001Ctl")
@RequestMapping("CashFlowDiscount001")
public class CashFlowDiscount001Controller {

    /**
     * CashFlowDiscount001Service
     */
    @Autowired
    private CashFlowDiscount001Service cashFlowDiscount001Service;



    /**
     * 通过流水号查询现金流折现模型曲线配置的信息
     * @param params 流水号
     * @return CashFlowDiscount001Dto
     * @see
     * @since 1.0
     */
    @PostMapping("/getCashFlowDataBySeqNo")
    public CashFlowDiscount001Dto getCashFlowDataBySeqNo(@RequestBody Map<String,Object> params) {
        List<CashFlowDiscount001Dto> rstList = cashFlowDiscount001Service.getCashFlowDiscountList(params);
        if(!rstList.isEmpty()){
            return rstList.get(0);
        }
        return null;
    }

    /**
     * 删除
     *
     * @param dto 页面选中的数据
     * @return 结果
     * @throws Exception
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody CashFlowDiscount001Dto dto) throws Exception {
        return cashFlowDiscount001Service.delete(dto);
    }

    /**
     * 修改
     *
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @PostMapping("update")
    public void update(@RequestBody CashFlowDiscount001Dto dto) throws Exception {
        cashFlowDiscount001Service.update(dto);
    }

    /**
     * 新增
     *
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @PostMapping("insert")
    public void insert(@RequestBody CashFlowDiscount001Dto dto) throws Exception {
        cashFlowDiscount001Service.insert(dto);
    }

    /**
     * 检查数据是否已经存在
     * @param dto
     * @return
     */
    @PostMapping("/haveDataCheck")
    public boolean haveDataCheck(@RequestBody CashFlowDiscount001Dto dto) {
        return cashFlowDiscount001Service.haveData(dto);
    }

    /**
     * 根据资产类型、资产细分类和（资产名称或资产代码）查询
     *
     * @return List<CashFlowDiscount001Dto>
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/getCashFlowDiscountListPage/{page}/{length}")
    public Page<CashFlowDiscount001Dto> getCashFlowDiscountListPage(@RequestBody Map<String,Object> params,
                                                                    @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return cashFlowDiscount001Service.getCashFlowDiscountList(page,length,params);
    }

    /**
     *
     * 导出数据查询
     *
     * @param map
     * @return
     */
    @PostMapping("selectForExport")
    public List<CashFlowDiscount001Dto> selectForExport(@RequestBody Map<String, Object> map) {
        return cashFlowDiscount001Service.selectForExport(map);
    }
}
