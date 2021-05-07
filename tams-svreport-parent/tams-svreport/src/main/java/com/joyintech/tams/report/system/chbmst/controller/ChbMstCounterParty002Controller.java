package com.joyintech.tams.report.system.chbmst.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.ChbMstCounterParty;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.system.chbmst.dto.ChbMstCounterPartyDto;
import com.joyintech.tams.report.system.chbmst.dto.ChbMstCounterPartyErrodataWithBLOBs;
import com.joyintech.tams.report.system.chbmst.service.ChbMstCounterPartyService;
import com.joyintech.tams.report.system.chbmst.util.ErrdataConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@PageId("ChbMstCounterParty002")
@RequestMapping("/svreport/system/chbmst/ChbMstCounterParty002")
public class ChbMstCounterParty002Controller extends BaseController {

    @Autowired
    private ChbMstCounterPartyService chbMstCounterPartyService;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        super.setSelectOptionToModel(model, DictCode.客户二级类型, DictCode.维护数据状态,DictCode.客户一级类型);
        ChbMstCounterParty chbMstCounterParty=new ChbMstCounterParty();
        Map<String,Object> map= RequestUtil.getPageParam();
        String uuid= (String) map.get("uuid");
        if(!"add".equals(map.get("operateType"))){
            //UUid为空时,查询交易对手信息，否则查询交易对手错误表信息
            if("".equals(uuid)){
                List<ChbMstCounterParty> ChbMstCounterPartys= chbMstCounterPartyService.selectOneDate(map);
                if(null!=ChbMstCounterPartys && ChbMstCounterPartys.size()>0){
                    ChbMstCounterParty c=ChbMstCounterPartys.get(0);
                    c.setType("update");
                    model.addAttribute("entity", c);
                }else{
                    chbMstCounterParty.setType("add");
                    model.addAttribute("entity", chbMstCounterParty);
                }
            }else{
                List<ChbMstCounterPartyErrodataWithBLOBs> chbMsList=chbMstCounterPartyService.selectChbMstCounterPartyErrordata(uuid);
                if(null!=chbMsList && chbMsList.size()>0){
                    ErrdataConvertUtil.errdataChbMstCounterPartyInfoConvert(chbMstCounterParty,chbMsList.get(0));
                }
                model.addAttribute("entity", chbMstCounterParty);
            }
        }else{
            chbMstCounterParty.setType("add");
            model.addAttribute("entity",chbMstCounterParty);
        }
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) ChbMstCounterPartyDto dto, BindingResult result) {
        return commonCheck(result, "save");
    }

    @ResponseBody
    @PostMapping("save")
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) ChbMstCounterParty dto, BindingResult result) {
        AjaxResult webResult = new AjaxResult();
        List<ChbMstCounterParty> dtoList=new ArrayList<>();
        dto.setCreateUser(LoginUserUtil.getUserName());
        dto.setCreateTime(new Date());
        dto.setDataSource("01");
        dto.setStatus("E");
        dtoList.add(dto);
        Map<String, Object> map=new HashMap<>();
        map.put("counterId",dto.getCounterId());
        List<ChbMstCounterParty> ChbMstCounterPartys= chbMstCounterPartyService.selectOneDate(map);
        if(null!=ChbMstCounterPartys && ChbMstCounterPartys.size()>0){
            if(null!=dto && "add".equals(dto.getType())){
                webResult.setMessage("已存在相同交易对手代码的数据");
            }
            if(null!=dto && "update".equals(dto.getType())){
                ChbMstCounterParty chb=ChbMstCounterPartys.get(0);
                chb.setCounterName(dto.getCounterName());
                chb.setCounterType1(dto.getCounterType1());
                chb.setCounterType2(dto.getCounterType2());
                chb.setStatus("E");
                chbMstCounterPartyService.updateOne(chb);
                webResult.setMessage("保存成功");
            }
        }else{
            try{
                chbMstCounterPartyService.insertChbMstCounterPartyData(dtoList);
                webResult.setMessage("保存成功");
            }catch (Exception e){
                e.printStackTrace();
                webResult.setMessage("保存失败");
            }
        }
        return webResult;
    }

}
