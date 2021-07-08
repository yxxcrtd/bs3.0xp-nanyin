package com.joyintech.service.common.config.controller;

import com.joyintech.service.common.config.service.DynamicConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dynamicConfig")
public class DynamicConfigController {
    
    @Autowired
    private DynamicConfigService dynamicConfigService;

    @RequestMapping("selectByDynId")
    @ResponseBody
    public String selectByDynId(@RequestBody String dynId) {
        return dynamicConfigService.selectByDynId(dynId);
    }

    @RequestMapping("updateByParam")
    public Integer updateByParam(@RequestBody Map<String, Object> param){
        return dynamicConfigService.updateByParam(param);
    }
}
