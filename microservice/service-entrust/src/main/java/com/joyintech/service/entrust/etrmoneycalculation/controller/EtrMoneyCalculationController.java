package com.joyintech.service.entrust.etrmoneycalculation.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.entrust.etrmoneycalculation.dto.EtrMoneyCalculationDto;
import com.joyintech.service.entrust.etrmoneycalculation.service.EtrMoneyCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 *
 * 可投金额试算
 * <功能详细描述>
 * @return
 * @see
 * @since 1.0
 */
@RestController
@RequestMapping("/EtrMoneyCalculation")
public class EtrMoneyCalculationController {
    @Autowired
    private EtrMoneyCalculationService etrMoneyCalculationService;
    /**
     *
     * 可投金额试算-> 选择资产代码后查询出相关属性项内容
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @PostMapping("getFinprodMsg")
    public EtrMoneyCalculationDto getFinprodMsg(@RequestBody  String finprodId){
        return  etrMoneyCalculationService.getFinprodMsg(
                finprodId);

    };
    @ResponseBody
    @PostMapping("getFinprodByFinprod")
    public EtrMoneyCalculationDto getFinprodByFinprod(@RequestBody  String finprodId){
        return  etrMoneyCalculationService.getFinprodByFinprod(
                finprodId);

    };
    /**
     *
     * 分页查询-可投金额计算
     * <功能详细描述>
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("calculation/{page}/{length}")
    public Page<EtrMoneyCalculationDto> selectForPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return etrMoneyCalculationService.selectForPage(page, length, param);
    }

}
