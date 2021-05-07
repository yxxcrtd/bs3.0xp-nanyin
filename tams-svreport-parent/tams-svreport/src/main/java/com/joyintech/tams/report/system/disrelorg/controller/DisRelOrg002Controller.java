package com.joyintech.tams.report.system.disrelorg.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.system.disrelorg.dto.DisRelOrgInfo001Dto;
import com.joyintech.tams.report.system.disrelorg.service.DisRelOrgService;
import com.joyintech.tams.report.system.prorate.dto.ProRateInfo001Dto;
import com.joyintech.tams.svreport.interf.dis.institutiondata.dto.DisAssociatedInstitutions;
import com.joyintech.tams.svreport.interf.dis.prodcashyield.dto.DisRepCashYield;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@PageId("DisRelOrg002")
@RequestMapping("/svreport/system/disrelorg/DisRelOrg002")
public class DisRelOrg002Controller extends BaseController {

    @Autowired
    private DisRelOrgService disRelOrgService;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        super.setSelectOptionToModel(model,DictCode.维护数据状态,
                DictCode.数据来源);
        Map<String,Object> map= RequestUtil.getPageParam();
        DisAssociatedInstitutions disAssociatedInstitutions= new DisAssociatedInstitutions();
        if(!"add".equals(map.get("operateType"))){
            String relorgId= (String) map.get("relorgId");
            List<DisAssociatedInstitutions> entitys=disRelOrgService.selectOne(relorgId);
            if(null!=entitys && entitys.size()>0){
                DisAssociatedInstitutions d=entitys.get(0);
                d.setType("update");
                model.addAttribute("entity", d);
            }else{
                disAssociatedInstitutions.setType("add");
                model.addAttribute("entity",disAssociatedInstitutions);
            }
        }else{
            disAssociatedInstitutions.setType("add");
            model.addAttribute("entity",disAssociatedInstitutions);
        }
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) DisRelOrgInfo001Dto dto, BindingResult result) {
        return commonCheck(result, "save");
    }

    @ResponseBody
    @PostMapping("save")
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) DisAssociatedInstitutions dto, BindingResult result) {
        AjaxResult webResult = new AjaxResult();
        dto.setCreateUser(LoginUserUtil.getUserName());
        dto.setCreateTime(new Date());
        dto.setUpdateteUser(LoginUserUtil.getUserName());
        dto.setUpdateTime(new Date());
        dto.setSvrDataSources("01");
        dto.setStatus("E");
        List<DisAssociatedInstitutions> entitys=disRelOrgService.selectOne(dto.getRelorgId());
        if(null!=entitys && entitys.size()>0){
            if(null!=dto && "add".equals(dto.getType())){
                webResult.setMessage("已存在管理机构代码的数据");
            }
            if(null!=dto && "update".equals(dto.getType())){
                DisAssociatedInstitutions d=entitys.get(0);
                d.setOrganName(dto.getOrganName());
                d.setStatus("E");
                disRelOrgService.updateOne(d);
                webResult.setMessage("保存成功");
            }
        } else{
            try{
                disRelOrgService.insertOne(dto);
                webResult.setMessage("保存成功");
            }catch (Exception e){
                e.printStackTrace();
                webResult.setMessage("保存失败");
            }
        }
        return webResult;
    }

}
