package com.joyintech.service.svrcom.system.prorate.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.system.prorate.dto.ProRateInfo001Dto;
import com.joyintech.tams.report.system.prorate.service.ProRateInfo001Service;
import com.joyintech.tams.report.system.proregistcodemanage.dto.ProRegistCodeManage001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/Prorate")
public class ProrateController {

    @Autowired
    private ProRateInfo001Service proRateInfo001Service;

    @PostMapping("findPage/{page}/{length}")
    public Page<ProRateInfo001Dto> search(@RequestBody Map<String,Object> paramMap) {
        return proRateInfo001Service.searchForPage(paramMap);
    }
}
