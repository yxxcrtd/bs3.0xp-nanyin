package com.joyintech.service.svrcom.system.bonddiscountrate.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.system.bonddiscountrate.dto.ChbSecDiscountRateDto;
import com.joyintech.tams.report.system.bonddiscountrate.service.ChbSecDiscountRate001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/ChbSecDiscountRate")
public class ChbSecDiscountRateController {

    @Autowired
    private ChbSecDiscountRate001Service chbSecDiscountRate001Service;

    @PostMapping("findPage/{page}/{length}")
    public Page<ChbSecDiscountRateDto> search(@RequestBody Map<String,Object> paramMap) {
        return chbSecDiscountRate001Service.searchForPage(paramMap);
    }

    @PostMapping("selectOneDate")
    public ChbSecDiscountRateDto selectOneDate(@RequestBody Map<String, Object> param) {
        return chbSecDiscountRate001Service.selectOneDate(param);
    }

    @PostMapping("save")
    public AjaxResult save(@RequestBody ChbSecDiscountRateDto dto)  {
        return chbSecDiscountRate001Service.save(dto);
    }

}
