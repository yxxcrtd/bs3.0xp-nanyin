package com.joyintech.service.valuation.basedata.marketdata.rateswapvaluationinfo.controller;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.basedata.marketdata.rateswapvaluationinfo.dto.ValRateSwapValuationInfo001Dto;
import com.joyintech.service.valuation.basedata.marketdata.rateswapvaluationinfo.service.ValRateSwapValuationInfo001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 利率互换估值信息
 *
 * @author zhangwei
 * @createTime 2019-07-26 11:21
 */
@RestController(value="serviceValRateSwapValuationInfo001Controller")
@RequestMapping("/ValRateSwapValuationInfo001")
public class ValRateSwapValuationInfo001Controller extends BaseController {

    @Autowired
    private ValRateSwapValuationInfo001Service valRateSwapValuationInfo001Service;

    /**
     * 列表查询
     * @return 列表信息
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ValRateSwapValuationInfo001Dto> queryPageList(@RequestBody Map<String, Object> params,
                                                              @PathVariable("page") int page, @PathVariable("length") int length){
        return valRateSwapValuationInfo001Service.queryPageList(page,length,params);
    }

    /**
     * 获取增值税则表 - 不分页
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("queryAllList")
    public List<ValRateSwapValuationInfo001Dto> queryAllList(@RequestBody Map<String, Object> param){
        return valRateSwapValuationInfo001Service.queryAllList(param);
    }
    
    /**
     * 交易编号获取
     * @param param
     *
     * @return 返回的数据
     */
    @PostMapping("getFinprodId2")
	public List<SelectOptionDto> getFinprodId2(@RequestBody Map<String, Object> param){
    	
		return valRateSwapValuationInfo001Service.getFinprodId2(param);
	}
}
