package com.joyintech.tams.invest.trade.transfer.common.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.trade.transfer.common.service.TransferCommon001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 转仓共同
 * @author liupengfei
 * @version 2019/12/23
 */
@Controller
@PageId("TransferCommon001")
@RequestMapping("/invest/trade/transfer/common/TransferCommon001")
public class TransferCommon001Controller extends BaseController {
    /**
     * transferCommon001Service
     */
    @Autowired
    private TransferCommon001Service transferCommon001Service;

    @Override
    public String init(Model model){
        model.addAttribute("entityList",transferCommon001Service.findAvaPortfolioInfo(RequestUtil.getPageParam()));
        return "/invest/trade/transfer/common/TransferCommon001";
    }
}
