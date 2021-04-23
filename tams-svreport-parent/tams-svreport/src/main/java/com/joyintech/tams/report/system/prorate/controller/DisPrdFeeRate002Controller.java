package com.joyintech.tams.report.system.prorate.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.system.prorate.dto.DisPrdFeeRateErrordata;
import com.joyintech.tams.report.system.prorate.dto.DisPrdFeeRateInfoErrodataWithBLOBs;
import com.joyintech.tams.report.system.prorate.dto.ProRateInfo001Dto;
import com.joyintech.tams.report.system.prorate.service.ProrateService;
import com.joyintech.tams.report.system.prorate.util.ErrdataConvertUtil;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
@PageId("DisPrdFeeRate002")
@RequestMapping("/svreport/system/prorate/DisPrdFeeRate002")
public class DisPrdFeeRate002Controller extends BaseController {


    @Autowired
    private ProrateService prorateService;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        super.setSelectOptionToModel(model, DictCode.信披费用类型,DictCode.维护数据状态,
                DictCode.数据来源);
        Map<String,Object> map= RequestUtil.getPageParam();
        String datauuid= (String) map.get("datauuid");
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
        if(null!=map.get("startDate")){
            String startDate = dfs.format(Long.parseLong(String.valueOf(map.get("startDate"))));
            map.put("startDate",startDate);
        }
        //UUID为空 查询产品费率信息表
         if("".equals(datauuid)){
             List<DisRepCashYield> disRepCashYieldList =prorateService.selectOneDate(map);
             model.addAttribute("entity", disRepCashYieldList.get(0));
         }else{
             DisRepCashYield disRepCashYield =new DisRepCashYield();
             //根据UUID 查询数据库对应记录 错误表
             List<DisPrdFeeRateInfoErrodataWithBLOBs> disPrdFeeRateErrordataList=prorateService.selectdisPrdFeeRateErrordata(datauuid);
             if(disPrdFeeRateErrordataList!=null && disPrdFeeRateErrordataList.size()>0){
                 ErrdataConvertUtil.errdataDisRepCashYieldInfoConvert(disRepCashYield,disPrdFeeRateErrordataList.get(0));
             }
             model.addAttribute("entity", disRepCashYield);
         }
        return super.init(model);
    }


    @ResponseBody
    @PostMapping("save")
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) DisRepCashYield dto, BindingResult result) {
        AjaxResult webResult = new AjaxResult();
        List<DisRepCashYield> dtoList=new ArrayList<>();
        dto.setCashId(IDGenerator.generate());
        dto.setCreateUser(LoginUserUtil.getUserName());
        dto.setCreateTime(new Date());
        dto.setDataSource("01");
        dto.setStatus("E");
        dto.setAdapterId("无");
        dtoList.add(dto);
        List<Integer> intList =prorateService.selectPrdFeeData(dtoList);
        if(intList.size()>0){
            webResult.setMessage("已存在相同产品代码,启用日期,信披费用类型的数据");
        }else{
            try{
                prorateService.insertPrdFeeData(dtoList);
                webResult.setMessage("保存成功");
            }catch (Exception e){
                e.printStackTrace();
                webResult.setMessage("保存失败");
            }
        }
        return webResult;
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) ProRateInfo001Dto dto, BindingResult result) {
        return commonCheck(result, "save");
    }


}
