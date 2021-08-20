package com.joyintech.tams.invest.bankuplink.reponew.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 质押式回购报价
 * @author chenjiaxing
 * @version 1.0 2021年8月6日
 * @see CfetsRepoDealQuotedPrMainController
 * @since 1.0
 */
@Controller
@PageId("CfetsRepoDealQuotedPrMain")
@RequestMapping("/invest/bankuplink/reponew/CfetsRepoDealQuotedPrMain")
public class CfetsRepoDealQuotedPrMainController extends BaseController{

    @Override
    public String init(Model model) {
        model.addAttribute("ENTRUST_STATUS_05", DictCodeList.委托状态_已退回.getValue());
        model.addAttribute("ENTRUST_STATUS_07", DictCodeList.委托状态_已撤回.getValue());

        model.addAttribute("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());

        model.addAttribute("single_etr_isshow",CacheUtil.getSysParam(BussinessConstant.SINGLE_ETR_ISSHOW));
        model.addAttribute("repo_pledge_model",CacheUtil.getSysParam(BussinessConstant.REPO_PLEDGE_MODEL));
        return super.init(model);
    }
}
