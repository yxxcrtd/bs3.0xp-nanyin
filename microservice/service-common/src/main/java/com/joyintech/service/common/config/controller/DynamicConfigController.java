package com.joyintech.service.common.config.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.common.config.service.DynamicConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dynamicConfig")
public class DynamicConfigController {
    
    @Autowired
    private DynamicConfigService dynamicConfigService;

    @RequestMapping(value="selectByDynId")
    @ResponseBody
    public String selectByDynId(@RequestBody Map<String,Object> map) {
        return dynamicConfigService.selectByMap(map);
    }
    
    @RequestMapping("updateByParam")
    public Integer updateByParam(@RequestBody Map<String, Object> param){
        return dynamicConfigService.updateByParam(param);
    }

    @RequestMapping("selectPage/{page}/{length}")
    public Page<SelectOptionDto> selectPage(@PathVariable("page") int page,
                                                    @PathVariable("length") int length,
                                                    @RequestBody Map<String, Object> param) {
        return dynamicConfigService.selectPage(page, length, param);
    }
    
}
