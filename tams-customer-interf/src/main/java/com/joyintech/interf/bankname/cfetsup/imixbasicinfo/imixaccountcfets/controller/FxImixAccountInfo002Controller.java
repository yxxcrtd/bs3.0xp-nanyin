package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixaccountcfets.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixaccountcfets.dto.FxImixAccountInfo001Dto;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixaccountcfets.service.FxImixAccountInfo001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@PageId("FxImixAccountInfo002")
@RequestMapping("/interf/cfetsup/imixbasicinfo/imixaccountcfets/FxImixAccountInfo002")
public class FxImixAccountInfo002Controller extends BaseController {

    @Autowired
    private FxImixAccountInfo001Service fxImixAccountInfo001Service;
    /**
     * 查询界面入口
     */
    @Override
    public String init(Model model){
        String seqno = this.getParam("seqno");
        if(seqno!=null&&!"".equals(seqno)){
            FxImixAccountInfo001Dto dto= fxImixAccountInfo001Service.selectDtoInfoBySeqno(seqno);
            if(dto!=null){
                model.addAttribute("entity",dto);
            }
        }else {
            FxImixAccountInfo001Dto dt=new FxImixAccountInfo001Dto();
            dt.setJycs("IB");
            model.addAttribute("entity",dt);
        }
        return super.init(model);
    }

    /**
     * 保存
     *
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping(value = "/save",method = POST)
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) FxImixAccountInfo001Dto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        AjaxResult wt=new AjaxResult();
        int i=fxImixAccountInfo001Service.checkAccountid(dto.getAccountid());
        if(i>0){
            wt.setMessage("该产品"+dto.getAccountid()+"已经存在映射关系！");
            wt.setStatuCode(1);
            return wt;
        }
        dto.setSeqno(IDGenerator.generate());
        dto.setCreatorid(LoginUserUtil.getUserName());
        fxImixAccountInfo001Service.save(dto);
        wt.setMessage("保存成功");
        wt.setStatuCode(0);
        return wt;
    }

    @ResponseBody
    @RequestMapping(value = "/update",method = POST)
    public AjaxResult update(@RequestBody @Validated({
            ValidateGroupEdit.class}) FxImixAccountInfo001Dto dto, BindingResult br){
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        int i=fxImixAccountInfo001Service.checkAccountid(dto.getAccountid());
        if(i>0){
            AjaxResult wt=new AjaxResult();
            wt.setMessage("该产品"+dto.getAccountid()+"已经存在映射关系！");
            return wt;
        }
        dto.setLastmodifierid(LoginUserUtil.getUserName());
        fxImixAccountInfo001Service.update(dto);
        return new AjaxResult("成功");
    }

    @ResponseBody
    @RequestMapping(value = "/findJgdm",method = POST)
    public Map<String,Object> findJgdm(@RequestBody FxImixAccountInfo001Dto dto) {
        Map<String,Object> map=new HashMap<>();
        String serviceJgdm= fxImixAccountInfo001Service.findJgdm(dto.getCstpid());
        map.put("jgdm",serviceJgdm);
        return map;
    }

    /**
     *
     * @param para
     * @return
     */
    @RequestMapping("getJycs")
    @ResponseBody
    public List<SelectOptionDto> getJycs(Map para){
        return fxImixAccountInfo001Service.getJycs(RequestUtil.getPageParam());
    }

}
