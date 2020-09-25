package com.joyintech.service.valuation.valuationparametermanage.withholdingamortize.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.valuationparametermanage.withholdingamortize.dto.ValWithholdingAmortizeSet001Dto;
import com.joyintech.service.valuation.valuationparametermanage.withholdingamortize.dto.ValWithholdingAmortizeSet002Dto;
import com.joyintech.service.valuation.valuationparametermanage.withholdingamortize.service.ValWithholdingAmortizeSet002Service;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 待摊设置
 *
 * @author tanxuan
 * @version 1.0 2019年11月25日
 * @see ValWithholdingAmortizeSet002Controller
 * @since 1.0
 */
@RestController(value = "serviceValWithholdingAmortizeSet002Controller")
@RequestMapping("/ValWithholdingAmortizeSet002")
public class ValWithholdingAmortizeSet002Controller extends BaseController {

    @Autowired
    private ValWithholdingAmortizeSet002Service valWithholdingAmortizeSet002Service;

    /**
     * 账套代码 - 业务编号获取
     *
     * @return 返回的数据
     */
    @PostMapping("/getFinprodId")
    public List<SelectOptionDto> getFinprodId(@RequestBody Map<String, Object> param)
        throws Exception {

        return valWithholdingAmortizeSet002Service.getFinprodId(param);
    }

    /**
     * 通过业务编号，获取信息
     * 
     * @return valWithholdingAmortizeSet001Dto
     * @throws Exception
     */
    @PostMapping("getFinprodInfo")
    public ValWithholdingAmortizeSet001Dto getFinprodInfo(@RequestBody Map<String, Object> pageParam) {
        return valWithholdingAmortizeSet002Service.getFinprodInfo(pageParam);
    }
    
    /**
     * 获取交易日期
     * 
     * @return valWithholdingAmortizeSet001Dto
     * @throws Exception
     */
    @PostMapping("getTradeDate")
    public ValWithholdingAmortizeSet001Dto getTradeDate(@RequestBody Map<String, Object> pageParam) {
        return valWithholdingAmortizeSet002Service.getTradeDate(pageParam);
    }

    /**
     * 查询
     * 
     * @return valWithholdingAmortizeSet002Dto
     * @throws Exception
     */
    @PostMapping("findByPrimaryKey")
    public ValWithholdingAmortizeSet002Dto findByPrimaryKey(@RequestBody Map<String, Object> pageParam) {
        return valWithholdingAmortizeSet002Service.findByPrimaryKey(pageParam);
    }

    /**
     * 流程完成后的操作
     *
     * @param dto 业务实体
     * @param action 
     * @return 画面地址
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping(value = "doComplete/{action}")
    public void doComplete(@RequestBody ValWithholdingAmortizeSet002Dto dto,
                           @PathVariable("action") String action) {
        valWithholdingAmortizeSet002Service.doComplete(dto, OperationType.valueOf(action));
    }
}
