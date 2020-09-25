package com.joyintech.service.period.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: component-base
 * @description: 共通
 * @author: 强成西
 * @create: 2018-10-10 15:00
 **/
@Controller
@RequestMapping("/")
public class PeriodCommonController {
    @ResponseBody
    @RequestMapping("/info")
    public String index(){
        return "资产存续期微服务-<a href='index.html'>API文档</a>";
    }
}
