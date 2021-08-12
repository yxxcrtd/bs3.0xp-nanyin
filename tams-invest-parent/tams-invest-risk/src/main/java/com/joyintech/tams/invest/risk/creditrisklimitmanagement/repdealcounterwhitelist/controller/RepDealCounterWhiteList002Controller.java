package com.joyintech.tams.invest.risk.creditrisklimitmanagement.repdealcounterwhitelist.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.constant.DictCodeList;

/**
 *  质押回购交易对手白名单维护新建
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2020年2月26日
 * @see RepDealCounterWhiteList002Controller
 * @since 1.0
 */
@Controller
@PageId("RepDealCounterWhiteList002")
@RequestMapping("/invest/risk/creditrisklimitmanagement/repdealcounterwhitelist/RepDealCounterWhiteList002")
public class RepDealCounterWhiteList002Controller  extends BaseController {
	
    @Override
    public String init(Model model) {
 
        model.addAttribute("RSK02", DictCodeList.共通业务类型_授权管理.getValue());
        model.addAttribute("AUTHORIZE_TYPE_02", DictCodeList.授权类型_授权.getValue());
        return super.init(model);
    }

}
