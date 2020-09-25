package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixtraderassociation.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixtraderassociation.dto.FxTraderManagerDto;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixtraderassociation.service.FxTraderManager001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@PageId("FxTraderManager001")
@RequestMapping("/interf/cfetsup/imixbasicinfo/imixtradermanager/FxTraderManager001")
public class FxTraderManagerController001 extends BaseController {
    @Autowired
    private FxTraderManager001Service fxTraderManager001Service;
    @Override
    public String init(Model model) {
        return super.init(model);
    }

    /**
     *  分页查询
     * @return
     */
    @RequestMapping("selectForPage")
    @ResponseBody
    public Page<FxTraderManagerDto> selectForPage(){
        return fxTraderManager001Service.selectPageData(RequestUtil.getPageParam());
    }
    /**
     *
     * 联想文本框
     * <功能详细描述>
     * @return List<SuggestDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getUser")
    public List<SuggestDto> getUser() throws Exception {
        List<SuggestDto> temp = fxTraderManager001Service.getUser(RequestUtil.getPageParam());
        return temp;
    }

}
