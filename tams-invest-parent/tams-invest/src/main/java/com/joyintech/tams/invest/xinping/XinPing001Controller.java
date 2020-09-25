/*
 * 文件名：PaActiveRemind001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年8月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.xinping;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.security.LoginUserUtil;

/**
 * 跳转信批系统
 * 〈功能详细描述〉
 * @author chengzhong
 * @version 1.0 2018年8月20日
 * @see XinPing001Controller
 * @since 1.0
 */
@Controller
@PageId("XinPing001")
@RequestMapping("/invest/xinping/XinPing001")
public class XinPing001Controller extends BaseController {
  
    @Value("${xinping.properties.urlIP}")
    private String urlIP;
    
    @Value("${xinping.properties.urlPort}")
    private String urlPort;
    
    /**
     * 主动提醒页面初始化
     */
    @Override
    public String init(Model model) {
    	
    	String httpUrl = "http://" + urlIP + ":" + urlPort + "/Page/loginForCas.html?account=" + LoginUserUtil.getUserName();
    	model.addAttribute("httpUrl", httpUrl);
    	return super.init(model);
    }
    
}
