package com.joyintech.tams.invest.common.cstp.controller;

import com.joyintech.tams.invest.common.cstp.service.CstpCommonMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @auther zhangnian
 * @date 2021/3/30
 */
@RestController
@RequestMapping("/cstp/match")
public class CstpMatchController {

    @Autowired
    private CstpCommonMatchService cstpCommonMatchService;

    @RequestMapping("/dealMatchEntrust")
    public void dealMatchEntrust(@RequestBody Map<String,Object> map){
        cstpCommonMatchService.dealMatchEntrust(map);
    }
}

