package com.joyintech.service.chb.common.controller;

import com.joyintech.component.svrreport.chb.chbcommoncheck.ChbDataCheckCommonUtil;
import com.joyintech.framework.validation.ValidMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @auth 何阳阳
 * @date 2021/7/7
 */
@RestController
@RequestMapping("/openapi")
public class ChbDataCheckController {

    @Autowired
    private ChbDataCheckCommonUtil chbDataCheckCommonUtil;

    @PostMapping("chbDataCheck")
    public List<ValidMessage> chbDataCheck(@RequestBody Map entity, @RequestParam(value = "pageId", required = false) String pageId, @RequestParam(value = "repType", required = false) String repType) {
        return chbDataCheckCommonUtil.chbCheckCommon(null, entity, pageId, repType, ValidMessage.class);
    }
}
