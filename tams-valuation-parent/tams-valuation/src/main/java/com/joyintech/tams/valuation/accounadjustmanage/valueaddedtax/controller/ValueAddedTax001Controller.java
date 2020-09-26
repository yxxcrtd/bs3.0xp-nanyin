/*
 * 文件名：ValueAddedTax001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年9月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.accounadjustmanage.valueaddedtax.controller;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.valuation.accounadjustmanage.valueaddedtax.dto.ValueAddedTax001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.valueaddedtax.service.ValueAddedTax001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.dao.BaseCommonMapper;

/**
 * 估值余额表查询Controller
 * 
 * @author ruanhongming
 * @version 1.0 2018年9月25日
 * @see ValueAddedTax001Controller
 * @since 1.0
 */
@Controller
@PageId("ValueAddedTaxQuery001")
@RequestMapping("/valuation/accounadjustmanage/valueaddedtax/ValueAddedTax001")
public class ValueAddedTax001Controller extends BaseController {

    @Autowired
    private ValueAddedTax001Service valueAddedTax001Service;
    
    @Autowired
    private BaseCommonMapper baseCommonMapper;
    
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.汇总维度);
//        this.setSelectOptionToModel(model, DictCode.年份);
        this.setSelectOptionToModel(model, DictCode.申报日期);
        //当前系统平台日期
        Date date = baseCommonMapper.getNowSysDate();
        if(date==null){
            date = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        List<SelectOptionDto> list = getYearData(year);
        model.addAttribute("yearDates", JsonUtils.object2Json(list));
        
        /** modify by dongjun on 20181127 start **/
        int month = c.get(Calendar.MONTH) + 1; 	
        if(month == 1 || month == 2 || month == 3){
        	model.addAttribute("yearDateInit", list.get(1).getId());
        	year = year-1;
        	model.addAttribute("declarDateInit",DictCodeList.申报日期_第四征期.getValue());
        }else {
        	model.addAttribute("yearDateInit", list.get(0).getId());
        	if(month == 4 || month == 5 || month == 6){
        		model.addAttribute("declarDateInit",DictCodeList.申报日期_第一征期.getValue());
        	} else if(month == 7 || month == 8 || month == 9){
        		model.addAttribute("declarDateInit",DictCodeList.申报日期_第二征期.getValue());
        	} else if(month == 10 || month == 11 || month == 12){
        		model.addAttribute("declarDateInit",DictCodeList.申报日期_第三征期.getValue());
        	}
        }
        model.addAttribute("yearDate", year);
        /** modify by dongjun on 20181127 end **/
        return super.init(model);
    }
    
    /**
     *获取最近5年份下拉框的值
     * @param year
     * @return
     */
    private List<SelectOptionDto> getYearData(int year){
        List<SelectOptionDto> list= new ArrayList<SelectOptionDto>();
        for(int i=year;i>year-5;i--){
            SelectOptionDto dto = new SelectOptionDto();
            dto.setId(i+"");
            dto.setText(i+"年");
            dto.setCol("");
            list.add(dto);
        }
        return list;
    }
    
    /**
     * 校验是否存在价税分离
     * @return
     * @throws ParseException
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check() throws ParseException {
        AjaxResult ajaxResult = checkNotEmpty();
        String massage = "";
        Map<String,Object> map = RequestUtil.getPageParam();
        //年份
        String year = (String)map.get("data[yearDate]");
        if(StringUtil.isEmpty(year)){
            massage = "年份不能为空！";
            return new AjaxResult(massage, MessageType.错误消息);
        }else {
            map.put("year", year);
        }
        //征期
        String declarDate = (String)map.get("data[declarDate]");
        if(StringUtil.isEmpty(declarDate)){
            massage = "征期不能为空！";
            return new AjaxResult(massage, MessageType.错误消息);
        }else {
            map.put("declarDate", declarDate);
        }
        //汇总维度
        String sumDimen = (String)map.get("data[sumDimen]");
        if(StringUtil.isEmpty(sumDimen)){
            massage = "汇总维度不能为空！";
            return new AjaxResult(massage, MessageType.错误消息);
        }else {
            map.put("sumDimen", sumDimen);
        }
        massage = valueAddedTax001Service.checkTaxDeclareSumInfo(map);
        ajaxResult.setData(massage);
        return ajaxResult;
    }
    
    /**
     * 生成页面
     * @return
     * @throws ParseException
     */
    @ResponseBody
    @RequestMapping("build")
    public AjaxResult build() throws ParseException {
        AjaxResult ajaxResult = new AjaxResult();
        String massage = "";
        Map<String,Object> map = RequestUtil.getPageParam();
        massage = valueAddedTax001Service.buildTaxDeclareSumInfo(map);
        ajaxResult.setData(massage);
        return ajaxResult;
    }
    
    /**
     * 查询页面
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<ValueAddedTax001Dto> selectBalanceStatement()
        throws Exception {
        Page<ValueAddedTax001Dto> page = valueAddedTax001Service.selectForPage(RequestUtil.getParameters(getRequest()));
        return page;
    }
    
    /**
     * 查询页面
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("selectSum")
    public Page<ValueAddedTax001Dto> selectSum()
        throws Exception {
        return valueAddedTax001Service.selectSum(RequestUtil.getParameters(getRequest()));
    }
    /**
     * 导出信息excel
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception {
        String datas = this.getParam("datas");
        Map<String, Object> para= RequestUtil.getPageParam();
        List<ValueAddedTax001Dto> dtoList;
        if (datas == null) {
            dtoList = valueAddedTax001Service.selectAllList(para);
        }
        else {
            dtoList = JsonUtils.json2List(datas, ValueAddedTax001Dto.class);
        }
//        List<ValueAddedTax001Dto> dtoList = valueAddedTax001Service.selectAllList(para);
        File outFile = valueAddedTax001Service.makeFile(
                "/com/joyintech/tams/valuation/accounadjustmanage/valueaddedtax/conf/export_conf.xml",
                dtoList);
        return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS
                        + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }
    
    /**
     * 查询的非空校验
     * @return
     * @throws ParseException
     */
    @ResponseBody
    @RequestMapping("checkEmpty")
    public AjaxResult checkNotEmpty() throws ParseException {
        AjaxResult ajaxResult = new AjaxResult();
        String massage = "";
        Map<String,Object> map = RequestUtil.getPageParam();
        //年份
        String year = (String)map.get("data[yearDate]");
        if(StringUtil.isEmpty(year)){
            massage = "年份不能为空！";
            return new AjaxResult(massage, MessageType.错误消息);
        }else {
            map.put("year", year);
        }
        //征期
        String declarDate = (String)map.get("data[declarDate]");
        if(StringUtil.isEmpty(declarDate)){
            massage = "征期不能为空！";
            return new AjaxResult(massage, MessageType.错误消息);
        }else {
            map.put("declarDate", declarDate);
        }
        //汇总维度
        String sumDimen = (String)map.get("data[sumDimen]");
        if(StringUtil.isEmpty(sumDimen)){
            massage = "汇总维度不能为空！";
            return new AjaxResult(massage, MessageType.错误消息);
        }else {
            map.put("sumDimen", sumDimen);
        }
        return ajaxResult;
    }
}
