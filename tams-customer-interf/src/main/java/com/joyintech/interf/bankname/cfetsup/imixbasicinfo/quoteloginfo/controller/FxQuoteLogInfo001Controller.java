package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.quoteloginfo.controller;


import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.interf.bankname.cfetsup.constant.FxDictCode;
import com.joyintech.interf.bankname.cfetsup.constant.FxDictCodeList;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.quoteloginfo.dto.FxQuoteLogInfoDto;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.quoteloginfo.service.FxQuoteLogInfo001Service;
import com.joyintech.tams.constant.DictCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@PageId("FxQuoteLogInfo001")
@RequestMapping("/interf/cfetsup/imixbasicinfo/quoteloginfo/FxQuoteLogInfo001")
public class FxQuoteLogInfo001Controller extends BaseController {

    @Autowired
    private FxQuoteLogInfo001Service fxQuoteLogInfo001Service;


    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        model.addAttribute(FxDictCode.交易方向_现券.getCode(), JsonUtils.object2Json(FxDictCodeList.fromDictCode(FxDictCode.交易方向_现券)));
        return super.init(model);
    }

    /**
     * 查询
     *
     */
    @ResponseBody
    @RequestMapping("/selectForPage")
    public Page<FxQuoteLogInfoDto> selectForPage(){
        Page<FxQuoteLogInfoDto>  page = fxQuoteLogInfo001Service.selectForPage(RequestUtil.getPageParam());
        return page;
    }





}
