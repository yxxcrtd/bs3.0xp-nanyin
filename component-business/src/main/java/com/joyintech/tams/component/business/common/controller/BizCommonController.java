package com.joyintech.tams.component.business.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;

/**
 * @program: tams-invest-parent
 * @description: 存续期共通
 * @author: 强成西
 * @create: 2018-11-13 09:48
 **/
@Controller
@RequestMapping("/common/bizCommon")
public class BizCommonController extends BaseController {

    @Autowired
    private CommonBusinessService commonBusinessService;

    /**
     * 判断是否是总集节点
     * 
     * @return
     */
    @RequestMapping("isBackCheckNode")
    @ResponseBody
    public boolean isBackCheckNode(String tradeId) {
        return commonBusinessService.isBackCheckNode(tradeId);
    }
}
