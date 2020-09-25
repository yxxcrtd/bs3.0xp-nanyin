package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.quotesendinfo.controller;


import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.quotesendinfo.dto.FxQuoteSendInfoDto;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.quotesendinfo.service.FxQuoteSendInfo001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@PageId("FxQuoteSendInfo001")
@RequestMapping("/interf/cfetsup/imixbasicinfo/quotesendinfo/FxQuoteSendInfo001")
public class FxQuoteSendInfo001Controller extends BaseController {

    @Autowired
    private FxQuoteSendInfo001Service fxQuoteSendInfo001Service;

    /**
     * 查询
     *
     */
    @ResponseBody
    @RequestMapping("/selectForPage")
    public Page<FxQuoteSendInfoDto> selectForPage(){
        Page<FxQuoteSendInfoDto>  page = fxQuoteSendInfo001Service.selectForPage(RequestUtil.getPageParam());
        return page;
    }



}
