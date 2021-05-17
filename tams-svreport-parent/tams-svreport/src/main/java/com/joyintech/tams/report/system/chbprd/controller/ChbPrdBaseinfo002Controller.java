package com.joyintech.tams.report.system.chbprd.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.system.chbprd.dto.ChbPrdBaseinfo001Dto;
import com.joyintech.tams.report.system.chbprd.dto.ChbPrdBaseinfoErrodataWithBLOBs;
import com.joyintech.tams.report.system.chbprd.service.ChbPrdBaseinfo001Service;
import com.joyintech.tams.report.system.chbprd.service.ChbPrdBaseinfoService;
import com.joyintech.tams.report.system.chbprd.util.ErrdataConvertUtil;
import com.joyintech.tams.report.system.prorate.dto.ProRateInfo001Dto;
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

import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@PageId("ChbPrdBaseinfo002")
@RequestMapping("/svreport/system/chbprd/ChbPrdBaseinfo002")
public class ChbPrdBaseinfo002Controller extends BaseController {

    @Autowired
    private ChbPrdBaseinfo001Service chbPrdBaseinfo001Service;
    @Autowired
    private ChbPrdBaseinfoService chbPrdBaseinfoService;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        super.setSelectOptionToModel(model,DictCode.管理方式_发行登记, DictCode.是或否,DictCode.开放模式_中债,
                DictCode.规律开放周期,DictCode.开放期业务,DictCode.产品类型_中债,DictCode.计息基础,
                DictCode.产品受托职责_中债,DictCode.人行客户类型,DictCode.发行状态);
        Map<String,Object> map= RequestUtil.getPageParam();
        String dataUuid= (String) map.get("dataUuid");
        ChbPrdBaseinfo chbPrdBaseinfo =new ChbPrdBaseinfo();
        /*SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
        DisRepCashYield disRepCashYield =new DisRepCashYield();
        if(null!=map.get("startDate")){
            String startDate = dfs.format(Long.parseLong(String.valueOf(map.get("startDate"))));
            map.put("startDate",startDate);
        }*/
        //UUID为空 查询产品费率信息表
         if("".equals(dataUuid) || null==dataUuid){
             List<ChbPrdBaseinfo> chbPrdBaseinfoList =chbPrdBaseinfo001Service.selectOneDate(map);
             if(null!=chbPrdBaseinfoList && chbPrdBaseinfoList.size()>0){
                 ChbPrdBaseinfo d=chbPrdBaseinfoList.get(0);
                 d.setType("update");
                 model.addAttribute("entity", d);
             }else{
                 chbPrdBaseinfo.setType("add");
                 model.addAttribute("entity", chbPrdBaseinfo);
             }
         }else{
             //根据UUID 查询数据库对应记录 错误表
             List<ChbPrdBaseinfoErrodataWithBLOBs> chbPrdBaseinfoErrordataList=chbPrdBaseinfo001Service.selectchbPrdBaseinfoErrordata(dataUuid);
             if(chbPrdBaseinfoErrordataList!=null && chbPrdBaseinfoErrordataList.size()>0){
                 ErrdataConvertUtil.errdataChbPrdBaseinfoInfoConvert(chbPrdBaseinfo,chbPrdBaseinfoErrordataList.get(0));
             }
             chbPrdBaseinfo.setProduuid(dataUuid);
             model.addAttribute("entity", chbPrdBaseinfo);
         }
        return super.init(model);
    }


    @ResponseBody
    @PostMapping("save")
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) ChbPrdBaseinfo dto, BindingResult result) {
        AjaxResult webResult = new AjaxResult();
        List<ChbPrdBaseinfo> dtoList=new ArrayList<>();
        dto.setProduuid(IDGenerator.generate(36));
        dto.setCreateUser(LoginUserUtil.getUserName());
        dto.setCreateTime(new Date());
        dto.setUpdateUser(LoginUserUtil.getUserName());
        dto.setUpdateTime(new Date());
        dto.setDataSource("01");
        dto.setStatus("E");
        dtoList.add(dto);
        Map<String, Object> map=new HashMap<>();
        Map<String, Object> mapOld=new HashMap<>();
        map.put("prodId",dto.getProdId());
        List<ChbPrdBaseinfo> chbPrdBaseinfoList =chbPrdBaseinfo001Service.selectOneDate(map);
        if(null!=dto && "add".equals(dto.getType())){
            if(null!=chbPrdBaseinfoList && chbPrdBaseinfoList.size()>0){
                webResult.setMessage("已存在相同产品代码的数据", MessageType.错误消息);
            }else{
                try{
                    chbPrdBaseinfo001Service.insertPrdFeeData(dtoList);
                    webResult.setMessage("保存成功");
                }catch (Exception e){
                    e.printStackTrace();
                    webResult.setMessage("保存失败", MessageType.错误消息);
                }
            }
        }
            if(null!=dto && "update".equals(dto.getType())){
                if(chbPrdBaseinfoList.size()>0){
                    mapOld.put("prodId",dto.getProdId());
                    mapOld.put("orgCode1",dto.getOrgCode());
                    mapOld.put("raiseVdate",dto.getRaiseVdate());
                    mapOld.put("raiseEnddate",dto.getRaiseEnddate());
                    mapOld.put("vdate",dto.getVdate());
                    mapOld.put("mdate",dto.getMdate());
                    mapOld.put("manageType",dto.getManageType());
                    mapOld.put("isConstracte",dto.getIsConstracte());
                    mapOld.put("yieldHigh",dto.getYieldHigh());
                    mapOld.put("yieldLow",dto.getYieldLow());
                    mapOld.put("yieldRemark",dto.getYieldRemark());
                    mapOld.put("openMode",dto.getOpenMode());
                    mapOld.put("openCircle",dto.getOpencyclep());
                    mapOld.put("openCircleDays",dto.getOpenCircleDays());
                    mapOld.put("ruleComment",dto.getRuleComment());
                    mapOld.put("firstOpenDay",dto.getFirstOpenDay());
                    mapOld.put("holidayOnFlag",dto.getHolidayOnFlag());
                    mapOld.put("avgOpenCount",dto.getAvgOpenCount());
                    mapOld.put("openBuss",dto.getOpenBuss());
                    mapOld.put("openBussRemark",dto.getOpenBussRemark());
                    mapOld.put("busiAcctNo",dto.getBusiAcctNo());
                    mapOld.put("acctName",dto.getAcctName());
                    mapOld.put("prodtype",dto.getProdtype());
                    mapOld.put("basic",dto.getBasic());
                    mapOld.put("fiduciaryDuty",dto.getFiduciaryDuty());
                    mapOld.put("customerType",dto.getCustomerType());
                    mapOld.put("isTransfer",dto.getIsTransfer());
                    mapOld.put("isFullyCompliant",dto.getIsFullyCompliant());
                    mapOld.put("isYllccp",dto.getIsYllccp());
                    mapOld.put("isSyqzccp",dto.getIsSyqzccp());
                    mapOld.put("issuingStatus",dto.getIssuingStatus());
                    mapOld.put("status","E");
                    mapOld.put("dataSource","01");
                    mapOld.put("updateUser",LoginUserUtil.getUserName());
                    mapOld.put("updateTime",new Date());
                    chbPrdBaseinfo001Service.updateOne(mapOld);
                    webResult.setMessage("保存成功");
                }else{
                    chbPrdBaseinfo001Service.insertPrdFeeData(dtoList);
                    List<ChbPrdBaseinfo> deleteList=new ArrayList<>();
                    if(!"".equals(dto.getProduuid())){
                        deleteList.add(dto);
                    }
                    if(deleteList.size()>0){
                        chbPrdBaseinfoService.deleteChbPrdBaseinfoErrorData(deleteList);
                    }
                }

        }
        return webResult;
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) ChbPrdBaseinfo001Dto dto, BindingResult result) {
        return commonCheck(result, "save");
    }


}
