package com.joyintech.tams.report.system.prorate.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
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
import java.util.*;


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
        DisRepCashYield disRepCashYield =new DisRepCashYield();
        if(null!=map.get("startDate")){
            String startDate = dfs.format(Long.parseLong(String.valueOf(map.get("startDate"))));
            map.put("startDate",startDate);
        }
        //UUID为空 查询产品费率信息表
         if("".equals(datauuid) || null==datauuid){
             List<DisRepCashYield> disRepCashYieldList =prorateService.selectOneDate(map);
             if(null!=disRepCashYieldList && disRepCashYieldList.size()>0){
                 DisRepCashYield d=disRepCashYieldList.get(0);
                 d.setType("update");
                 model.addAttribute("entity", d);
             }else{
                 disRepCashYield.setType("add");
                 model.addAttribute("entity", disRepCashYield);
             }
         }else{
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
        dto.setUpdateUser(LoginUserUtil.getUserName());
        dto.setUpdateTime(new Date());
        dto.setDataSource("01");
        dto.setStatus("E");
        dto.setAdapterId("无");
        dtoList.add(dto);
        Map<String, Object> map=new HashMap<>();
        Map<String, Object> mapOld=new HashMap<>();
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
        map.put("busiId",dto.getBusiId());
        map.put("cashType2",dto.getCashType2());
        String startDate = dfs.format(dto.getStartDate());
        map.put("startDate",startDate);
        List<DisRepCashYield> disRepCashYieldList =prorateService.selectOneDate(map);
        if(null!=dto && "add".equals(dto.getType())){
            if(null!=disRepCashYieldList && disRepCashYieldList.size()>0){
                webResult.setMessage("已存在相同产品代码,启用日期,信披费用类型的数据", MessageType.错误消息);
            }else{
                try{
                    prorateService.insertPrdFeeData(dtoList);
                    webResult.setMessage("保存成功");
                }catch (Exception e){
                    e.printStackTrace();
                    webResult.setMessage("保存失败", MessageType.错误消息);
                }
            }
        }
            if(null!=dto && "update".equals(dto.getType())){
                if(null!=dto.getOldCashType2() && null!=dto.getOldStartDateStr() && (!dto.getCashType2().equals(dto.getOldCashType2())
                || !startDate.equals(dto.getOldStartDateStr())) ){
                    if(disRepCashYieldList.size()>0){
                        webResult.setMessage("已存在相同产品代码,启用日期,信披费用类型的数据", MessageType.错误消息);
                    }else{
                        mapOld.put("busiId",dto.getBusiId());
                        mapOld.put("oldCashType2",dto.getOldCashType2());
                        mapOld.put("oldStartDateStr",dto.getOldStartDateStr());
                        mapOld.put("cashType2",dto.getCashType2());
                        mapOld.put("startDate",startDate);
                        mapOld.put("rate",dto.getRate());
                        mapOld.put("status","E");
                        mapOld.put("dataSource","01");
                        mapOld.put("updateUser",LoginUserUtil.getUserName());
                        mapOld.put("updateTime",new Date());
                        prorateService.updateOne(mapOld);
                        webResult.setMessage("保存成功");
                    }
                }else{
                    mapOld.put("busiId",dto.getBusiId());
                    mapOld.put("oldCashType2",dto.getOldCashType2());
                    mapOld.put("oldStartDateStr",dto.getOldStartDateStr());
                    mapOld.put("cashType2",dto.getCashType2());
                    mapOld.put("startDate",startDate);
                    mapOld.put("rate",dto.getRate());
                    mapOld.put("status","E");
                    mapOld.put("dataSource","01");
                    mapOld.put("updateUser",LoginUserUtil.getUserName());
                    mapOld.put("updateTime",new Date());
                    prorateService.updateOne(mapOld);
                    webResult.setMessage("保存成功");
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
