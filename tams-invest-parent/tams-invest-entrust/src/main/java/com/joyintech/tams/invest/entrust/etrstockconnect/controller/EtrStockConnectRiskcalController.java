
package com.joyintech.tams.invest.entrust.etrstockconnect.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 风险试算
 * @see EtrStockConnectRiskcalController
 * @since 1.0
 */
@Controller
@PageId("EtrStockConnectRiskcal")
@RequestMapping("/invest/entrust/etrstockconnect/EtrStockConnectRiskcal")
public class EtrStockConnectRiskcalController extends BaseController {
    @Override
    public String init(Model model) {
        return super.init(model);
    }
}
