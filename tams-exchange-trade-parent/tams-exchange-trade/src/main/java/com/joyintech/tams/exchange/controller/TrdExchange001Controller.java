package com.joyintech.tams.exchange.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.exchange.common.constant.ExchangeConstant;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@PageId("TrdExchange001")
@RequestMapping("/invest/trade/investtrade/exchangesystem/TrdExchange001")
public class TrdExchange001Controller extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(TrdExchange001Controller.class);

    @Override
    public String init(Model model) {

        if (SecurityUtil.hasAuthenticated()) {
            User user = SecurityUtil.getLoginUser();
            Date sysDate = DateUtil.platDateFromCache();
            String fairCode = CacheUtil.getSysParam(ExchangeConstant.FAIR_CODE_IN_PARA);
            String fairModeEntrustPriceBestThanInstruction = CacheUtil.getSysParam(ExchangeConstant.FAIR_MODE_ENTRUST_PRICE_BEST_THAN_INSTRUCTION_IN_PARA);
            String fairModeWithUnacceptInstruction = CacheUtil.getSysParam(ExchangeConstant.FAIR_MODE_WITH_UNACCEPT_INSTRUCTION_IN_PARA);

            model.addAttribute("fairCode", fairCode);
            model.addAttribute("fairModeEntrustPriceBestThanInstruction", fairModeEntrustPriceBestThanInstruction);
            model.addAttribute("fairModeWithUnacceptInstruction", fairModeWithUnacceptInstruction);
            model.addAttribute("username", user.getUserName());
            model.addAttribute("sysdate", sysDate);
        }
        return super.init(model);
    }

}
