
package com.joyintech.tams.invest.entrust.etrstockconnect.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 港股通
 * @see EtrStockConnectMainController
 * @since 1.0
 */
@Controller
@PageId("EtrStockConnectMain")
@RequestMapping("/invest/entrust/etrstockconnect/EtrStockConnectMain")
public class EtrStockConnectMainController extends BaseController {

    @Override
    public String init(Model model) {
        return super.init(model);
    }
}
