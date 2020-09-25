package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixaccountcfets.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixaccountcfets.dto.FxImixAccountInfo001Dto;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixaccountcfets.service.FxImixAccountInfo001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@PageId("FxImixAccountInfo001")
@RequestMapping("/interf/cfetsup/imixbasicinfo/imixaccountcfets/FxImixAccountInfo001")
public class FxImixAccountInfo001Controller extends BaseController {

    @Autowired
    private FxImixAccountInfo001Service fxImixAccountInfo001Service;

    /**
     * 查询界面入口
     */
    @Override
    public String init(Model model){
        model.addAttribute("effectflag","E");
        return super.init(model);
    }

    @RequestMapping("findPage")
    @ResponseBody
    public Page<FxImixAccountInfo001Dto> findPage()throws Exception{
        Page<FxImixAccountInfo001Dto> pageInfo = fxImixAccountInfo001Service.findPage(RequestUtil.getPageParam());
        return pageInfo;
    }

    /**
     * 查询产品代码
     * @return
     */
    @RequestMapping("getProductid")
    @ResponseBody
    public Page<SelectOptionDto> getProductid(){
        return fxImixAccountInfo001Service.getProductid(RequestUtil.getPageParam());
    }

    /**
     * 查询外汇交易中机构代码
     * @return
     */
    @RequestMapping("getCfetsid")
    @ResponseBody
    public Page<SelectOptionDto> getCfetsid(){
        return fxImixAccountInfo001Service.getCfetsid(RequestUtil.getPageParam());
    }

    /**
     *
     * @return
     */
    @RequestMapping("getEffectfalg")
    @ResponseBody
    public Page<SelectOptionDto> getEffectfalg(){
        return fxImixAccountInfo001Service.getEffectfalg(RequestUtil.getPageParam());
    }

    /**
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete",method = POST)
    public AjaxResult delete(){
        AjaxResult result = new AjaxResult();
        Map<String,Object> map=RequestUtil.getPageParam();
        String seqno= (String) map.get("seqno");
        fxImixAccountInfo001Service.deleteBySeqno(seqno);
        result.setMessage("删除成功");
        return result;
    }


}
