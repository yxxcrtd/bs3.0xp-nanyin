package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixerrorinfo.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixerrorinfo.dto.FxImixErrorInfoDto;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixerrorinfo.service.FxImixErrorInfo001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 银行间交易员信息查询
 *
 * @author zcf
 * @version 1.0
 * @date 2020-01-06
 */
@Controller
@PageId("FxImixErrorInfo001")
@RequestMapping("/interf/cfetsup/imixbasicinfo/imixerrorinfo/FxImixErrorInfo001")
public class FxImixErrorInfo001Controller extends BaseController {

    @Autowired
    private FxImixErrorInfo001Service fxImixErrorInfo001Service;

    @Override
    public String init(Model model) {
        return super.init(model);
    }

    /**
     * 分页查询
     * @return
     */
    @RequestMapping("selectForPage")
    @ResponseBody
    public Page<FxImixErrorInfoDto > selectForPage(){
        return fxImixErrorInfo001Service.selectPageData(RequestUtil.getPageParam());
    }

    /**
     * 查询组合代码
     * @return
     */
    @RequestMapping("getApplerrorcode")
    @ResponseBody
    public Page<SelectOptionDto> getApplerrorcode(){
        return fxImixErrorInfo001Service.getApplerrorcode(RequestUtil.getPageParam());
    }
}
