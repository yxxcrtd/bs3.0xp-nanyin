package com.joyintech.tams.cefts.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("SwaggerDemoController")
@RequestMapping("/cefts/demo")
@Api(tags = "demo请求")
public class DemoController {

    @PostMapping("/select")
    @ApiOperation(value = "select", notes = "select")
    public String select(@RequestBody Map<String, String> map) {
        System.out.println(map);
        return "success";
    }

}
