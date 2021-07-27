/*
 * 文件名：RedisViewParamController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2020年7月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.riskredis.quota.redismgr.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.redis.common.entity.IValue;
import com.joyintech.redis.common.entity.Value;
import com.joyintech.redis.core.data.RedisHash;
import com.joyintech.tams.invest.common.riskinfrds.param.service.IndicatorParamValService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * redis更新参数值画面
 * 
 * @author 裴家俊
 * @version 1.0 2020年7月9日
 * @see RedisViewParamController
 * @since 1.0
 */
@Controller
@RequestMapping("/invest/riskredis/quota/redismgr/RedisViewParam")
@PageId("RedisViewParam")
public class RedisViewParamController extends BaseController {

    @Autowired
    private IndicatorParamValService paramValService;

    @Override
    public String init(Model model) {
        model.addAttribute("key", this.getParam("key"));
        model.addAttribute("field", this.getParam("field"));
        model.addAttribute("oldValue", this.getParam("value"));
        return "invest/riskredis/quota/redismgr/RedisViewParam";
    }

    /**
     * 设置参数新值
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("setParamValue")
    public AjaxResult setParamValue(@RequestBody RedisHash<String, String, String> dto)
            throws Exception {
        AjaxResult aj = new AjaxResult();
        if(StringUtil.isNotEmpty(dto)&&StringUtil.isNotEmpty(dto.getKey())&&StringUtil.isNotEmpty(dto.getValue())){
            Map<String, Map<String, IValue<Object>>> totalParamValMap = new HashMap<>();
            Map<String, IValue<Object>> mkParamValMap = new HashMap<>();
            Value<Object> rValue=new Value<>(str -> new BigDecimal(str));;
            try {
                 BigDecimal setDec = new BigDecimal(dto.getValue());
            }catch (Exception e){
                 rValue = new Value<>(str ->  str);
            }
            rValue.value(dto.getValue().toString());
            mkParamValMap.put(dto.getField(), rValue);
            if(dto.getKey().contains(GlobalConstant.COLON)) {
                totalParamValMap.put(dto.getKey(), mkParamValMap);
                //totalParamValMap.put(dto.getKey().substring(0, dto.getKey().indexOf(GlobalConstant.COLON)) + ":01", mkParamValMap);
                //totalParamValMap.put(dto.getKey().substring(0, dto.getKey().indexOf(GlobalConstant.COLON)) + ":02", mkParamValMap);
                //totalParamValMap.put(dto.getKey().substring(0, dto.getKey().indexOf(GlobalConstant.COLON)) + ":03", mkParamValMap);
                paramValService.setParamVal(totalParamValMap);
            }
        }
        return aj;
    }

}
