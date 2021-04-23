package com.joyintech.service.svrcom.system.prorate.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.report.system.prorate.dto.ProRateInfo001Dto;
import com.joyintech.tams.report.system.prorate.service.ProRateInfo001Service;
import com.joyintech.tams.systemtools.dto.SysDataDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
    @PostMapping("getProdinfo")
    public List<ChbPrdBaseinfo> getProdinfo(@RequestBody Map<String, Object> param) {
        List<ChbPrdBaseinfo> list = proRateInfo001Service.getProdinfo(param);
        return list;
    }
    @PostMapping("getSysDataDict")
    public List<SysDataDict> getSysDataDict(@RequestBody Map<String, Object> param) {
        List<SysDataDict> list = proRateInfo001Service.getSysDataDict(param);
        return list;
    }
    @PostMapping("selectPrdFeeData")
    public List<Integer> selectPrdFeeData (List<ProRateInfo001Dto> dtoList){
        List<Integer> intList =proRateInfo001Service.selectPrdFeeData(dtoList);
        return intList;
    }
}
