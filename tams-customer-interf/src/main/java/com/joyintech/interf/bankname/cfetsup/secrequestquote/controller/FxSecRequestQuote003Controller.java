package com.joyintech.interf.bankname.cfetsup.secrequestquote.controller;

import com.alibaba.fastjson.JSONArray;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.dto.FxSecrepoQuote003Dto;
import com.joyintech.interf.bankname.cfetsup.secrequestquote.dto.FxSecRequestQuoteCounterDto;
import com.joyintech.interf.bankname.cfetsup.secrequestquote.service.FxSecRequestQuoteCounterService;
import com.joyintech.interf.bankname.cfetsup.secrequestquote.service.FxSecRequestQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@PageId("FxSecRequestQuote003")
@RequestMapping("/interf/cfetsup/secrequestquote/FxSecRequestQuote003")
public class FxSecRequestQuote003Controller extends BaseController {

    @Autowired
    private FxSecRequestQuoteCounterService fxSecRequestQuoteCounterService;
    @Override
    public String init(Model model) {
        return super.init(model);
    }

    @RequestMapping("findPage")
    @ResponseBody
    public Page<FxSecRequestQuoteCounterDto> findPage()throws Exception{
        Page<FxSecRequestQuoteCounterDto> pageInfo =fxSecRequestQuoteCounterService.findCounterPage(RequestUtil.getPageParam());
        return pageInfo;
    }
    /**
     *
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public AjaxResult save(){
        AjaxResult result = new AjaxResult();
        Map map = RequestUtil.getPageParam();
        List<FxSecrepoQuote003Dto> counterList = JSONArray.parseArray(map.get("data").toString(), FxSecrepoQuote003Dto .class);
        map.put("counterList",counterList);
        result.setData(map);
        return result;
    }
}
