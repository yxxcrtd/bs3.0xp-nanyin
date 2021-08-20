package com.joyintech.tams.invest.bankuplink.reponew.controller;

import com.joyintech.framework.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 陈佳兴
 * @date 20210810
 * @Description
 */
@Controller
@RequestMapping("/invest/bankuplink/reponew/CfetsRepoDealQuotedPrInitCash")
public class CfetsRepoDealQuotePrInitCashController extends BaseController {

    /***
     * 托管户可用现金，证券户可用现金，T+1日可用现金
     * @return
     */
    @RequestMapping(value = "/initCash")
    public String initCash(Model model){
        return "invest/bankuplink/reponew/CfetsRepoDealQuotedPrInitCash";
    }

}