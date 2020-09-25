package com.joyintech.service.product.productevaluate.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.productevaluate.dto.FinAssetConfigDto;
import com.joyintech.service.product.productevaluate.dto.FinBokPositionPrdDto;
import com.joyintech.service.product.productevaluate.dto.FinProductVarDto;
import com.joyintech.service.product.productevaluate.dto.FinproductEvaluateDto;
import com.joyintech.service.product.productevaluate.service.FinProductEvaluateService;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 产品评价管理
 * @author guyunfei
 * @see
 * @since
 */
@RestController
@RequestMapping("/FinProductEvaluate")
public class FinProductEvaluateController {

    @Autowired
    private FinProductEvaluateService finProductEvaluateService;

    /**
     * 产品名称联想框
     * @param params
     * @return
     */
    @RequestMapping("/getProNames")
    public List<SelectOptionDto>getProNames(@RequestBody Map<String,Object> params){
        return finProductEvaluateService.getProNames(params);
    }

    /**
     * 存续产品的发行前信息查询
     * @param params
     * @param page
     * @param length
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectForPage/{page}/{length}")
    public Page<FinproductEvaluateDto> selectForPage(@RequestBody Map<String,Object> params,
           @PathVariable("page") int page, @PathVariable("length") int length)throws Exception{
        return finProductEvaluateService.selectForPage(page,length,params);
    }

    /**
     * 评价日资产配置情况查询
     * @param params
     * @param page
     * @param length
     * @return
     */
    @RequestMapping("/selectForPage2/{page}/{length}")
    public Page<FinAssetConfigDto> selectForPage2(@RequestBody Map<String,Object> params,
           @PathVariable("page") int page,@PathVariable("length") int length){
        return finProductEvaluateService.selectForPage2(page,length,params);
    }

    /**
     * 产品净值表现查询
     * @param params
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectForPage3")
    public List<FinBokPositionPrdDto> selectForPage3(@RequestBody Map<String,Object> params)throws Exception{
        return finProductEvaluateService.selectForPage3(params);
    }

    /**
     * 查询产品信息
     * @param params
     * @return
     */
    @RequestMapping("/queryProduct")
    public FinProductVarDto queryProduct(@RequestBody Map<String,Object> params){
        return finProductEvaluateService.queryProduct(params);
    }

    /**
     * 组合份额查询
     * @param params
     * @return
     */
    @RequestMapping("/queryCombinedShare")
    public List<FinAssetConfigDto> queryCombinedShare(@RequestBody Map<String,Object> params){
        return finProductEvaluateService.queryCombinedShare(params);
    }

    /**
     * 根据产品代码查询子组合代码
     * @param params
     * @return
     */
    @RequestMapping("/getsubCombinations")
    public List<PtlPortfolio> getsubCombinations(@RequestBody Map<String,Object> params){
        return finProductEvaluateService.getsubCombinations(params);
    }

}
