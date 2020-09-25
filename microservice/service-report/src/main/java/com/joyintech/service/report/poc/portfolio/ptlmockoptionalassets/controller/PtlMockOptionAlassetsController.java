package com.joyintech.service.report.poc.portfolio.ptlmockoptionalassets.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.poc.portfolio.ptlmockoptionalassets.dto.PtlMockOptionAlassetsDto;
import com.joyintech.service.report.poc.portfolio.ptlmockoptionalassets.service.PtlMockOptionAlassetsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 模拟试算可选资产
 * @author wl 
 * @version 1.0 2020/04/19
 * @see PtlMockOptionAlassetsController
 * @since 1.0
 */
@RestController
@RequestMapping("/Ptlmockoptionalassets")
public class PtlMockOptionAlassetsController {
    
    /**
     * PtlMockOptionAlassetsService
     */
    @Autowired
    private PtlMockOptionAlassetsService ptlmockoptionalassetsService;
    
    /**
     * 画面查询方法
     * @param param 请求参数
     * @param page 页面
     * @param length 长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<PtlMockOptionAlassetsDto> selectAll(@RequestBody Map<String, Object> param, 
                                                @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return ptlmockoptionalassetsService.selectForPage(page,length,param);
    }
    
    /**
     * 资产代码下拉框列表
     * @param map 请求参数
     * @return 资产代码下拉框列表
     */
    @PostMapping("getFinprodId")
    public List<SelectOptionDto> getFinprodId(@RequestBody Map<String, Object> map){
        return ptlmockoptionalassetsService.getFinprodId(map);
    }
}
