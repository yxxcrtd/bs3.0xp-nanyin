package com.joyintech.interf.bankname.cfetsup.secrepoquote.controller;

import com.alibaba.fastjson.JSONArray;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.dto.EtrRepoDto;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.dto.FxSecrepoQuote002Dto;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.dto.FxSecrepoQuote003Dto;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.dto.FxSecrepoQuote004Dto;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.service.FxSecrepoQuote002Service;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.service.FxSecrepoQuote004Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 选择委托
 * @author zcf
 * @version 1.0
 * @date 2020-01-18
 */

@Controller
@PageId("FxSecrepoQuote004")
@RequestMapping("/interf/cfetsup/secrepoquote/FxSecrepoQuote004")
public class FxSecrepoQuote004Controller extends BaseController {

    @Autowired
    private FxSecrepoQuote004Service fxSecrepoQuote004Service;
    @Autowired
    private FxSecrepoQuote002Service fxSecrepoQuote002Service;

    @Override
    public String init(Model model) {
        return super.init(model);
    }

    /**
     *  分页查询
     * @return
     */
    @PostMapping("selectForPage")
    @ResponseBody
    public Page<FxSecrepoQuote004Dto> selectForPage(){
        return fxSecrepoQuote004Service.selectPageData(RequestUtil.getPageParam());
    }

    /**
     * 根据委托查询报价信息
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public AjaxResult save(){
        AjaxResult ajaxResult = new AjaxResult();
        Map map = RequestUtil.getPageParam();
        String entrustId = "";
        if(StringUtil.isNotEmpty(map.get("entrustId"))){
            entrustId = (String) map.get("entrustId");
        }
        EtrRepoDto etrRepoDto = fxSecrepoQuote002Service.selectByEntrustId(entrustId);
        FxSecrepoQuote002Dto dto = fxSecrepoQuote002Service.getRepoQuoteInfo(etrRepoDto);
        ajaxResult.setData(dto);
        return ajaxResult;
    }

}
