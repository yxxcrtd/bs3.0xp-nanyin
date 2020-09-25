package com.joyintech.interf.bankname.cfetsup.secrepoquote.controller;

import com.alibaba.fastjson.JSONArray;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.dto.EtrRepoDto;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.dto.FxSecrepoQuote002Dto;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.dto.FxSecrepoQuote003Dto;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.service.FxSecrepoQuote002Service;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.service.FxSecrepoQuote003Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 添加质押券
 * @author zcf
 * @version 1.0
 * @date 2020-01-06
 */

@Controller
@PageId("FxSecrepoQuote003")
@RequestMapping("/interf/cfetsup/secrepoquote/FxSecrepoQuote003")
public class FxSecrepoQuote003Controller extends BaseController {

    @Autowired
    private FxSecrepoQuote003Service fxSecrepoQuote003Service;
    @Autowired
    private FxSecrepoQuote002Service fxSecrepoQuote002Service;

    @Override
    public String init(Model model) {
        //组合代码
        model.addAttribute("entrustId",RequestUtil.getPageParam().get("entrustId"));
        return super.init(model);
    }

    /**
     *  分页查询
     * @return
     */
    @PostMapping("selectForPage")
    @ResponseBody
    public Page<FxSecrepoQuote003Dto > selectForPage(){
        return fxSecrepoQuote003Service.selectPageData(RequestUtil.getPageParam());
    }

    /**
     * 前台分页查询
     * @return
     */
    @PostMapping("queryForPage")
    @ResponseBody
    public List<FxSecrepoQuote003Dto> queryForPage(){
        Map map = RequestUtil.getPageParam();
        String entrustId = "";
        if(StringUtil.isNotEmpty(map.get("entrustId"))){
            entrustId = (String) map.get("entrustId");
        }
        EtrRepoDto etrRepoDto = fxSecrepoQuote002Service.selectByEntrustId(entrustId);
        FxSecrepoQuote002Dto dto = fxSecrepoQuote002Service.getRepoQuoteInfo(etrRepoDto);
        return dto.getFinprodList();
    }
    /**
     * 查询组合代码
     * @return
     */
    @RequestMapping("getFinprodId")
    @ResponseBody
    public Page<SelectOptionDto> getFinprodId(){
        return fxSecrepoQuote003Service.getFinprodId(RequestUtil.getPageParam());
    }


    /**
     * 保存提交
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public AjaxResult save(){
        AjaxResult result = new AjaxResult();
        Map map = RequestUtil.getPageParam();
        List<FxSecrepoQuote003Dto> seclist = JSONArray.parseArray(map.get("data").toString(), FxSecrepoQuote003Dto .class);
        map.put("seclist",seclist);
        result.setData(map);
        return result;
    }
}
