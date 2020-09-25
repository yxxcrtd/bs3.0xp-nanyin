/*
 * 文件名：Holidaymarket001Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：zuoyinyi
 * 修改时间：2017年9月22日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.service.disclosure.submitmaintenance.holidaymarket.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.disclosure.submitmaintenance.holidaymarket.dto.DisclosureHolidaymarket001Dto;
import com.joyintech.service.disclosure.submitmaintenance.holidaymarket.service.DisclosureHolidaymarket001Service;
import com.joyintech.tams.disclosure.common.common.dto.ExcelValidDto;

/**
 * 节假日市场维护
 * @author zyf
 * @version 1.0 2017年9月22日
 * @see DisclosureHolidaymarket001Controller
 * @since 1.0
 */
@RestController(value="serviceDisclosureHolidaymarket001Controller")
@RequestMapping("/DisclosureHolidaymarket001")
public class DisclosureHolidaymarket001Controller extends BaseController{
    /**
     * service
     */
    @Autowired
    private DisclosureHolidaymarket001Service holidaymarket001Service;
    
    /**
     * {@inheritDoc}
     */
//    @Override
//    public String init(Model model) {
//        super.setSelectOptionToModel(model,DisclosureDictCode.交易市场);
////        SELECT_YEAR
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
//        Date date = DateUtil.now();
//        String year = sdf.format(date);
//        List<SelectOptionDto> selectYear=holidaymarket001Service.getYearSelect(50,year);
//        model.addAttribute("nowYear",year);
//        model.addAttribute("SELECT_YEAR",JsonUtils.objectArray2Json(selectYear));
//        return super.init(model);
//    }
    
    /**
     * 查询列表
     * 
     * @throws Exception 异常
     * @return Page
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<DisclosureHolidaymarket001Dto> findPage(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return holidaymarket001Service.searchForPage(page, length, param);
    }
    @GetMapping("importData")
    public List<ExcelValidDto> importData (String fileName) throws Exception{
    	
    	  return this.holidaymarket001Service.importData(fileName);
    }
    
    
    @PostMapping("setDateByType")
    public int setDateByType (@RequestBody Map<String, Object> param) throws Exception{
    	
    	  return holidaymarket001Service.setDateByType(param);
    }
    
    /**
     * 导入数据文件
     * 
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     * @throws Exception
     */
//    @ResponseBody
//    @RequestMapping("/importFile")
//    public List<ExcelValidDto> importData(String fileName)
//        throws Exception {
//        return this.holidaymarket001Service.importData(fileName);
//    }
//    
//    @RequestMapping(value = "setDateByType",method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String,Object> setDateByType(){
//        Map<String, Object> map = RequestUtil.getPageParam();
//        map.put("deptCode", CommonUtil.getDeptCode());
//        map.put("userId", SecurityUtil.getLoginUser().getUserName());
//        Map<String,Object> resultMap = holidaymarket001Service.setDateByType(map);
//        return resultMap;
//    }
    
//    @RequestMapping(value = "initHolid",method = RequestMethod.POST)
//    @ResponseBody
//    public List<DisclosureHolidaymarket001Dto> initHolid(){
//        Map<String, Object> map = RequestUtil.getPageParam();
//        List<DisclosureHolidaymarket001Dto> list = holidaymarket001Service.initHolid(map);
//        return list;
//    }
    @PostMapping("/initHolid")
    public List<DisclosureHolidaymarket001Dto> initHolid(@RequestBody Map<String, Object> param) throws Exception{
    	
    	  return holidaymarket001Service.initHolid(param);
    }
   
}
