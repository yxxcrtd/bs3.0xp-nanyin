package com.joyintech.service.basicdata.priceinfo.controller;

import com.joyintech.tams.invest.common.asset.service.PriceinfoService;
import com.joyintech.tams.invest.common.asset.service.impl.AutoAssetPriceServiceImpl;
import com.joyintech.tams.invest.common.dto.mock.basicdata.MockMstExchargeQuoteDto;
import com.joyintech.tams.invest.common.dto.mock.basicdata.MockQuotationInfoDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
/**
 * 行情服务
 * @author wl
 * @version 1.0 2020年4月15日
 * @see PriceinfoController
 * @since 1.0
 */
@RestController
@RequestMapping("/Priceinfo")
public class PriceinfoController{
    /**注入service
     * 
     */
    @Autowired
    private PriceinfoService priceinfoService;


    @Autowired
    private AutoAssetPriceServiceImpl autoAssetPriceServiceImpl;
    /**
     * 行情服务
     * @param param 请求参数
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("selectPriceinfo")
    public List<MockQuotationInfoDto> selectPriceinfo(@RequestBody Map<String, Object> param) throws Exception {
        return priceinfoService.selectPriceinfo(param);
    }
    /**
     * 汇率行情
     * @param param 请求参数
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("selectQuotationofrate")
    public List<MockMstExchargeQuoteDto> selectQuotationofrate(@RequestBody Map<String, Object> param) throws Exception {
        return priceinfoService.selectQuotationofrate(param);
    }


    @PostMapping("AutoPrice")
    public int AutoPrice(@RequestBody Map<String, Object> param) throws Exception {
        autoAssetPriceServiceImpl.autoProcess(param);
        return 0;
    }

}
