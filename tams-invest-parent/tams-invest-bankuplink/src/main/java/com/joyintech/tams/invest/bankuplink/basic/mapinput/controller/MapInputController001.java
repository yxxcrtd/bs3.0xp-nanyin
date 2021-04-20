/*
 * 文件名：MapInputController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2020年5月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.bankuplink.basic.mapinput.controller;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.invest.bankuplink.basic.mapinput.servie.MapInput001Service;

/**
 * Map数据输入控制类
 * @author Jiaoxujin
 * @version 1.0 2020年5月21日
 * @see MapInputController
 * @since 1.0
 */
@Controller
@PageId("MapInput001")
@RequestMapping("/invest/bankuplink/basic/mapinput/MapInput001")
public class MapInputController001 extends BaseController {
    
    /**
     * {@linkplain}
     */
    @Autowired
    private MapInput001Service mapInput001Service;
    
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        return super.init(model);
    }

    @ResponseBody
    @PostMapping("/bodyMap")
    public AjaxResult getMapByRequestBody(@RequestBody Map<String, Object> paramMap) {
        AjaxResult result = new AjaxResult();
        int mapSize = 0;
        
        if (MapUtils.isNotEmpty(paramMap)) {
            mapSize = paramMap.size();
        }
        
        mapInput001Service.getMapByRequestBody(paramMap);

        result.setStatuCode(HttpStatus.OK.value());
        result.setMessageType(MessageType.提示消息);
        result.setMessage("The map size is: " + mapSize);
        return result;
    }
}
