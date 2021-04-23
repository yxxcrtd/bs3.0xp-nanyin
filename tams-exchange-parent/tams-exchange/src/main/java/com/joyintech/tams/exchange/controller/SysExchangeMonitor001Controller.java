package com.joyintech.tams.exchange.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.service.ExchangeSystemMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * @ClassName ExchangeSystemMonitorController
 * @Description TODO
 * @Author swwan
 * @Date 2020/8/26 10:00
 * @Version 1.0
 **/
@Controller
@PageId("SysExchangeMonitor001")
@RequestMapping("/invest/trade/investtrade/exchangesystem/SysExchangeMonitor001")
public class SysExchangeMonitor001Controller extends BaseController {

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
