package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixmenmailinfo.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixmenmailinfo.dto.FxImixMenMailInfoDto;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixmenmailinfo.service.FxImixMenMailInfo001Service;
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
 * @date 2020-01-15
 */
@Controller
@PageId("FxImixMenMailInfo001")
@RequestMapping("/interf/cfetsup/imixbasicinfo/imixmenmailinfo/FxImixMenMailInfo001")
public class FxImixMenMailInfo001Controller extends BaseController {

    @Autowired
    private FxImixMenMailInfo001Service fxImixMenMailInfo001Service;

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
    public Page<FxImixMenMailInfoDto> selectForPage(){
        return fxImixMenMailInfo001Service.selectPageData(RequestUtil.getPageParam());
    }

}
