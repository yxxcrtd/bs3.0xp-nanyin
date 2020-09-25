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
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.valuationparametermanage.withholdingamortize.dto.ValWithholdingAmortizeSet001Dto;
import com.joyintech.service.valuation.valuationparametermanage.withholdingamortize.dto.ValWithholdingAmortizeSet002Dto;
import com.joyintech.service.valuation.valuationparametermanage.withholdingamortize.service.ValWithholdingAmortizeSet001Service;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author tanxuan
 * @version 1.0 2019年11月29日
 * @see ValWithholdingAmortizeSet001Controller
 * @since 1.0
 */
@RestController(value = "serviceValWithholdingAmortizeSet001Controller")
@RequestMapping("/ValWithholdingAmortizeSet001")
public class ValWithholdingAmortizeSet001Controller extends BaseController {

    @Autowired
    private ValWithholdingAmortizeSet001Service valWithholdingAmortizeSet001Service;

    /**
     * 账套代码
     * 
     * @param pageParam
     * @return page
     */
    @PostMapping("/getBooksetIdNew/{page}/{length}")
    public Page<SelectOptionDto> getBooksetIdNew(@RequestBody Map<String, Object> pageParam,
                                                 @PathVariable("page") int page,
                                                 @PathVariable("length") int length) {
        return valWithholdingAmortizeSet001Service.getBooksetIdNew(page, length, pageParam);
    }

    /**
     * 列表查询
     * 
     *  @param params
     *  @param page
     *  @param length
     * @return 列表信息
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ValWithholdingAmortizeSet001Dto> queryPageList(@RequestBody Map<String, Object> params,
                                                               @PathVariable("page") int page,
                                                               @PathVariable("length") int length) {
        return valWithholdingAmortizeSet001Service.queryPageList(page, length, params);
    }

    @PostMapping("check")
    public List<SelectOptionDto> check(@RequestBody ValWithholdingAmortizeSet002Dto param) {
        return valWithholdingAmortizeSet001Service.check(param);
    }
}
