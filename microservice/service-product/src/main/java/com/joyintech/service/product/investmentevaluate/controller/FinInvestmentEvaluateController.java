/**
 * 
 */
package com.joyintech.service.product.investmentevaluate.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.investmentevaluate.dto.*;
import com.joyintech.service.product.investmentevaluate.service.FinInvestmentEvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author jyl
 */
@RestController("FinInvestmentEvaluateController_dtx")
@RequestMapping("/FinInvestmentEvaluate")
public class FinInvestmentEvaluateController {
    @Autowired
    private FinInvestmentEvaluateService finInvestmentEvaluateService;

    /**
     *
     * 评价日资产配置
     * @param param 查询条件
     * @param page 第几页
     * @param length 每页展示个数
     * @return
     * @throws Exception 异常信息
     */
    @PostMapping("findPage2/{page}/{length}")
    public Page<FinInvestmentEvaluateDto> selectReserve(@RequestBody Map<String, Object> param,
                                                        @PathVariable("page") int page,
                                                        @PathVariable("length") int length)
        throws Exception {
        return finInvestmentEvaluateService.findPage2(page, length, param);
    }
/**
 * 净值表现查询
 */
@PostMapping("findPage3/{page}/{length}")
public Page<BokPositionPrdNetvalueDto> findPage3(@RequestBody Map<String, Object> param,
                                                 @PathVariable("page") int page,
                                                 @PathVariable("length") int length)
        throws Exception {
    return finInvestmentEvaluateService.findPage3(page, length, param);
}
    /**
     * 净值表现查询echart图
     */
    @PostMapping("findPage3e")
    public List<BokPositionPrdNetvalueDto> findPage3e(@RequestBody Map<String, Object> param)
            throws Exception {
        return finInvestmentEvaluateService.findPage3e(param);
    }
/**
 *金融产品信息+组合代码名称+区间开始日结束日
 */
@PostMapping("findProductInfo")
    public FinInvestmentEvaluate002Dto findProductInfo(@RequestBody Map<String, Object> param){

    return finInvestmentEvaluateService.searchFinancialProductInformationDto(param);
}
    /**
     *组合信息查询
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<FinInvestmentEvaluate003Dto> findPage(@RequestBody Map<String, Object> param,
                                                       @PathVariable("page") int page,
                                                       @PathVariable("length") int length)
            throws Exception {
        Page<FinInvestmentEvaluate003Dto> page1=finInvestmentEvaluateService.findPage(page, length, param);
        System.out.println(page1);
        return finInvestmentEvaluateService.findPage(page, length, param);
    }
    /**
     *策略执行有效性查询
     */
    @PostMapping("findPage7")
    public List<FinInvestmentEvaluate005Dto> findPage7(@RequestBody Map<String, Object> param){

        return finInvestmentEvaluateService.findPage7(param);
    }
    /**
     *策略执收益来源查询
     */
    @PostMapping("findPage8")
    public List<FinInvestmentEvaluate006Dto> findPage8(@RequestBody Map<String, Object> param){

        return finInvestmentEvaluateService.findPage8(param);
    }
    /**
     *组合下拉框查询
     */
    @PostMapping("findPort")
    public List<SelectOptionDto> findPort(@RequestBody Map<String, Object> param){

        return finInvestmentEvaluateService.findPort(param);
    }
    /**
     *策略执收益来源查询
     */
    @PostMapping("findStrategyId")
    public List<SelectOptionDto> findStrategyId(@RequestBody Map<String, Object> param){

        return finInvestmentEvaluateService.findStrategyId(param);
    }
    /**
     * 收益指标目标
     */
    @PostMapping("info7")
    public List<FinInvestmentEvaluate007Dto> info7(@RequestBody Map<String, Object> param){

        return finInvestmentEvaluateService.info7(param);
    }
    /**
     * 基准明细
     */
    @PostMapping("info8")
    public List<FinInvestmentEvaluate008Dto> info8(@RequestBody Map<String, Object> param){

        return finInvestmentEvaluateService.info8(param);
    }

}
