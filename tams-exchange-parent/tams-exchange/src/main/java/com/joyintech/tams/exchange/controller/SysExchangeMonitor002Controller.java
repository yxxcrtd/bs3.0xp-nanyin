package com.joyintech.tams.exchange.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.util.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * @ClassName SysExchangeMonitor002Controller
 * @Description 动态监控
 * @Author swwan
 * @Date 2020/8/26 10:00
 * @Version 1.0
 **/
@Controller
@PageId("SysExchangeMonitor002")
@RequestMapping("/invest/trade/investtrade/exchangesystem/SysExchangeMonitor002")
public class SysExchangeMonitor002Controller extends BaseController {

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        if (SecurityUtil.hasAuthenticated()){
            User user = SecurityUtil.getLoginUser();
            Date sysDate = DateUtil.platDateFromCache();
            model.addAttribute("username", user.getUserName());
            model.addAttribute("sysdate", sysDate);
        }
        return super.init(model);
    }
}
