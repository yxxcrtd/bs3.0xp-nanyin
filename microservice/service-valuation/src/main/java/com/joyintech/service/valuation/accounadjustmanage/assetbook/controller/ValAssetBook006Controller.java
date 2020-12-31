package com.joyintech.service.valuation.accounadjustmanage.assetbook.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.dto.ValAssetBook006Dto;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.service.ValAssetBook006Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * add by yangwenjun 20201130 账套参数修改功能
 */
@RestController("ValAssetBook006Ctr_dtx")
@RequestMapping("/ValAssetBook006")
public class ValAssetBook006Controller{

    @Autowired
    private ValAssetBook006Service valAssetBook006Service;

    @PostMapping("/getParamCode")
    public List<SuggestDto> getParamCode(@RequestBody Map<String, String> pageParam) {
        return valAssetBook006Service.getParamCode(pageParam);
    }

    @PostMapping("findPage/{page}/{length}")
    public Page<ValAssetBook006Dto> findPage(@RequestBody Map<String, Object> param,
                                             @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return valAssetBook006Service.findPage(page, length, param);
    }

    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public void doComplete(@RequestBody ValAssetBook006Dto entity){
        valAssetBook006Service.doComplete(entity);
    }
}
