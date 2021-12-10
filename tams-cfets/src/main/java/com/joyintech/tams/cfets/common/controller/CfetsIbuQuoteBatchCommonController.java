package com.joyintech.tams.cfets.common.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.tams.cfets.trdconfirm.service.CfetsTrdConfirmService;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 供批处理用
 * @author zznn
 * @date 2021/11/26
 */
@Controller
@RequestMapping("/cfets/common/CfetsIbuQuoteBatchCommon")
public class CfetsIbuQuoteBatchCommonController {

    @Autowired
    private CfetsTrdConfirmService cfetsTrdConfirmService;
    /**
     * 回溯登记成交确认
     * @param parm
     */
    @ResponseBody
    @PostMapping("resaleregautodeal")
    public void resaleregautodeal(@RequestBody Map<String,Object> parm){
        List<String> totleTradeIds = (List<String>) parm.get("totleTradeIds");
        totleTradeIds.forEach(item ->{
            cfetsTrdConfirmService.prepareEntity(item, DictCodeListExt1.生成方式_批处理生成.getValue());
        });
    }

}

