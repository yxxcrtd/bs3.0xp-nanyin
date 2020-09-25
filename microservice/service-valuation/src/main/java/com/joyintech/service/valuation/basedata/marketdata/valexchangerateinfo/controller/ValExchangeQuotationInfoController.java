package com.joyintech.service.valuation.basedata.marketdata.valexchangerateinfo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.basedata.marketdata.valexchangerateinfo.dto.ValExchangeQuotationInfoDto;
import com.joyintech.service.valuation.basedata.marketdata.valexchangerateinfo.service.ValExchangeQuotationInfoService;

/**
 * 汇率行情 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangzhao
 * @version 1.0 2018年8月9日
 * @see ValExchangeQuotationInfo001Controller
 * @since 1.0
 */
@RestController("ValExchangeQuotationInfoCtr_dtx")
@RequestMapping("/ValExchangeQuotationInfo")
public class ValExchangeQuotationInfoController {

    /**
     * 汇率行情service
     */
    @Autowired
    private ValExchangeQuotationInfoService valExchangeQuotationInfoService;

    /**
     * 汇率行情分页查询 <功能详细描述>
     * 
     * @param pageParam 查询参数
     * @return 返回查询结果
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @RequestMapping("/selectForPage/{page}/{length}")
    public Page<ValExchangeQuotationInfoDto> selectForPage(@RequestBody Map<String, Object> pageParam,
                                                           @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return valExchangeQuotationInfoService.selectForPage(page, length, pageParam);
    }

    /**
     * 查询所有数据验证删除是否存在 <功能详细描述>
     * 
     * @param pageParam 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @RequestMapping("/selectAllList")
    public List<ValExchangeQuotationInfoDto> selectAllList(@RequestBody Map<String, Object> pageParam) {
        return valExchangeQuotationInfoService.selectAllList(pageParam);
    }

    /**
     * 保存操作 <功能详细描述>
     * 
     * @param map 参数
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("/doComplete")
    public void doComplete(@RequestBody ValExchangeQuotationInfoDto dto) {
        valExchangeQuotationInfoService.doComplete(dto);
    }
}
