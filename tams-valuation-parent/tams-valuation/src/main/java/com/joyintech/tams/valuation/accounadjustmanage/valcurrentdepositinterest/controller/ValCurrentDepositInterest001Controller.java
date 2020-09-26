/*
 * 文件名：ValCurrentDepositInterest001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangzhao
 * 修改时间：2019年1月30日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.accounadjustmanage.valcurrentdepositinterest.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.valuation.accounadjustmanage.valcurrentdepositinterest.dto.ValCurrentDepositInterest001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.valcurrentdepositinterest.service.ValCurrentDepositInterest001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.common.service.BaseCommonService;

/**
 * 活期存款利息报表Controller
 * 
 * @author zhangzhao
 * @version 1.0 2019年1月30日
 * @see ValCurrentDepositInterest001Controller
 * @since 1.0
 */
@Controller
@PageId("ValCurrentDepositInterest001")
@RequestMapping("/valuation/accounadjustmanage/valcurrentdepositinterest/ValCurrentDepositInterest001")
public class ValCurrentDepositInterest001Controller  extends BaseController {

    @Autowired
    private ValCurrentDepositInterest001Service valCurrentDepositInterest001Service;
    @Autowired
    private BaseCommonService baseCommonService;
	@Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.季度);
        String sysDate=baseCommonService.getSysDateString();
        if(sysDate==null||"".equals(sysDate)){
            sysDate=DateUtil.format(new Date());
        }
        int year=DateUtil.getYear(sysDate);
        Date date1=DateUtil.parse((year-1) +"-12-31");
        Date date2=DateUtil.parse(year+"-03-21");
        Date date3=DateUtil.parse(year+"-03-20");
        Date date4=DateUtil.parse(year+"-06-21");
        Date date5=DateUtil.parse(year+"-06-20");
        Date date6=DateUtil.parse(year+"-09-21");
        Date date7=DateUtil.parse(year+"-09-20");
        Date date8=DateUtil.parse(year+"-12-21");
        Date date9=DateUtil.parse(year+"-12-20");
        Date date10=DateUtil.parse((year+1)+"-01-01");
        if(DateUtil.parse(sysDate).after(date1)&&DateUtil.parse(sysDate).before(date2)){
            model.addAttribute("year", year-1);
            model.addAttribute("quarter", "04");
        }
        if(DateUtil.parse(sysDate).after(date3)&&DateUtil.parse(sysDate).before(date4)){
        	model.addAttribute("year", year);
            model.addAttribute("quarter", "01");
        }
        if(DateUtil.parse(sysDate).after(date5)&&DateUtil.parse(sysDate).before(date6)){
        	model.addAttribute("year", year);
            model.addAttribute("quarter", "02");
        }
        if(DateUtil.parse(sysDate).after(date7)&&DateUtil.parse(sysDate).before(date8)){
        	model.addAttribute("year", year);
            model.addAttribute("quarter", "03");
        }
        if(DateUtil.parse(sysDate).after(date9)&&DateUtil.parse(sysDate).before(date10)){
        	model.addAttribute("year", year);
            model.addAttribute("quarter", "04");
        }
          return super.init(model);
    }
    
    /**
     * 条件检查
     * 
     * @return
     * @throws Exception 
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid ValCurrentDepositInterest001Dto dto, BindingResult result) throws Exception {
        AjaxResult ajaxResult = new AjaxResult();
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        // 处理查询开始日和结束日
        Map<String, Object> pageParam = new HashMap<String, Object>();
        pageParam.put("year", dto.getYear());
        pageParam.put("quarter", dto.getQuarter());
       
        valCurrentDepositInterest001Service.analyseDate(pageParam);
        String queryStart = DateUtil.format(DateUtil.parse(String.valueOf(pageParam.get("queryStart"))), DateUtil.DEFAULT_DATE_FORMAT_EN);
        String queryEnd = DateUtil.format(DateUtil.parse(String.valueOf(pageParam.get("queryEnd"))), DateUtil.DEFAULT_DATE_FORMAT_EN);
        dto.setQueryStart(queryStart); //开始日
        dto.setQueryEnd(queryEnd);  //结束日
        ajaxResult.setData(dto);
        return ajaxResult;
    }
    
    
    /**
     * 导出信息
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> exportExcel()
        throws Exception {
    	Map<String, Object> params = RequestUtil.getParameters(getRequest());
    	return valCurrentDepositInterest001Service.exportExcel(params);
    }
    
   
}
