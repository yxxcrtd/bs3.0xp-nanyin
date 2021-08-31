package com.joyintech.service.chb.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.joyintech.component.svrreport.chb.chbcommoncheck.ChbDataCheckCommonUtil;
import com.joyintech.component.svrreport.chb.chbcommoncheck.dao.ChbDataCheckCommonMapper;
import com.joyintech.component.svrreport.chb.chbcommoncheck.dto.ChbCheckRule;
import com.joyintech.framework.validation.ValidMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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
    private ChbDataCheckCommonMapper chbDataCheckCommonMapper;

    @Autowired
    private ChbDataCheckCommonUtil chbDataCheckCommonUtil;

    @PostMapping("chbDataCheck")
    public List<ValidMessage> chbDataCheck(@RequestBody Map entity, @RequestParam(value = "pageId", required = false) String pageId, @RequestParam(value = "repType", required = false) String repType) {
        String className = (String)entity.get("entityClass");
        try {
            return chbDataCheckCommonUtil.chbCheckCommon(null, JSONObject.parseObject(JSONObject.toJSONString(entity), Class.forName(className)), pageId, repType, ValidMessage.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @GetMapping("queryChbCheckRule")
    public List<ChbCheckRule> queryChbCheckRule(@RequestParam(value = "pageId") String pageId, @RequestParam(value = "pageFlag") String pageFlag) {
        return chbDataCheckCommonMapper.queryChbCheckRule(pageId, pageFlag);
    }
}
