/*
 * 文件名：MarketCalendarInfoController001.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：
 * 修改时间：2018年7月16日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
package com.joyintech.tams.report.submitmaintenance.mstmarketcalendarinfo.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.submitmaintenance.mstmarketcalendarinfo.dto.SvrMstCalendarInfoDto;
import com.joyintech.tams.report.submitmaintenance.mstmarketcalendarinfo.dto.SvrMstMarketCalendarInfo001Dto;
import com.joyintech.tams.report.submitmaintenance.mstmarketcalendarinfo.service.SvrMstMarketCalendarInfoMService;
import com.joyintech.system.constant.SystemDictCode;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;


/**
 * 市场日历信息
 * @author xiexinting
 *
 */                                                        

@Controller
@PageId("SvrMstMarketCalendarInfo001")
@RequestMapping("/submitmaintenance/mstmarketcalendarinfo/SvrMstMarketCalendarInfo001")
public class SvrMstMarketCalendarInfo001Controller extends BaseController {
	/**
	 * logger
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 
     */
    @Autowired
    private SvrMstMarketCalendarInfoMService mstMarketCalendarInfoMService;
    /**
     * 初始化页面信息
     *
     * @return 处理结果
     * @throws Exception 异常
     */
    @Override
    public String init(Model model) {
        //初始化是否节假日下拉框
        this.setSelectOptionToModel(model, SystemDictCode.是否);
        //初始化日历类型下拉框
        List<SelectOptionDto> listCalendar = new ArrayList<SelectOptionDto>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("calendarType", "");
        List<SvrMstCalendarInfoDto> listresult = mstMarketCalendarInfoMService.selectCalendarInfo(map);
        for (SvrMstCalendarInfoDto m:listresult) {
            SelectOptionDto dto = new SelectOptionDto();
            dto.setId(m.getCalendarId());
            dto.setText(m.getCalendarName());
            dto.setCol("");
            listCalendar.add(dto);
        }
        model.addAttribute("calendarType", JsonUtils.objectArray2Json(listCalendar.toArray()));    
        Date sysDate = DateUtil.platDateFromCache();
        model.addAttribute("sysDate",sysDate);
        model.addAttribute("_yes",1);
        model.addAttribute("_no",0);
        return super.init(model);
    }
    /**
     * 市场日历信息查询
     *
     * @return 处理结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("queryList")
    public Page<SvrMstMarketCalendarInfo001Dto> selectInfo() throws Exception{
        Map<String,Object> param = RequestUtil.getPageParam();
        return mstMarketCalendarInfoMService.selectInfo(param);
    }
    /**
     * 
     * 将工作日设置为节假日
     * <功能详细描述>
     * @param dto 实体类集合
     * @return 返回处理信息
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("sethday")
    public AjaxResult sethday(@RequestBody List<SvrMstMarketCalendarInfo001Dto> dto) throws Exception{
        ProcessParameter<SvrMstMarketCalendarInfo001Dto> processParam = new ProcessParameter<SvrMstMarketCalendarInfo001Dto>();
        processParam.setPageId(this.getPageId());
        processParam.setOperationType(OperationType.MODIFY);
        Date sysDate = DateUtil.platDateFromCache();
        //判断日期是否小于平台日
        for(SvrMstMarketCalendarInfo001Dto mci:dto){
            if(mci.getCdate().before(sysDate)){
                AjaxResult aj = new AjaxResult();
                List<ValidMessage> errorList = new ArrayList<>();
                errorList.add(new ValidMessage(getMessage("MST_INFO_E_0003")));
                aj.setErrorList(errorList);
                return aj;
            }
        }
        for(SvrMstMarketCalendarInfo001Dto mci:dto){
            mstMarketCalendarInfoMService.doComplete(mci);
        }
        return new AjaxResult(getMessage("COMMON_I_0011"));
    }
   /**
    * 
    * 将节假日设为工作日
    * <功能详细描述>
    * @param dto 实体类集合
    * @return 返回处理信息
    * @throws Exception 抛出异常
    * @see
    * @since 1.0
    */
    @ResponseBody
    @RequestMapping("setwday")
    public AjaxResult setwday(@RequestBody List<SvrMstMarketCalendarInfo001Dto> dto) throws Exception{
        ProcessParameter<SvrMstMarketCalendarInfo001Dto> processParam = new ProcessParameter<SvrMstMarketCalendarInfo001Dto>();
        processParam.setPageId(this.getPageId());
        processParam.setOperationType(OperationType.MODIFY);
        Date sysDate = DateUtil.platDateFromCache();
        //判断日期是否小于平台日
        for(SvrMstMarketCalendarInfo001Dto mci:dto){
            if(mci.getCdate().before(sysDate)){
                AjaxResult aj = new AjaxResult();
                List<ValidMessage> errorList = new ArrayList<>();
                errorList.add(new ValidMessage(getMessage("MST_INFO_E_0003")));
                aj.setErrorList(errorList);
                return aj;
            }
        }
        for(SvrMstMarketCalendarInfo001Dto mci:dto){
            mstMarketCalendarInfoMService.doComplete(mci);
        }
        return new AjaxResult(getMessage("COMMON_I_0011"));
    }
    /**
     * 导入
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("importFile")
    public List<ExcelValidDto> importData(String fileName)
        throws Exception{
        return this.mstMarketCalendarInfoMService.importData(fileName);
    }
    /**
     * 
     * 导出
     * <功能详细描述>
     * @return 返回导出报错信息
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export() throws Exception {
    	Map<String, Object> param = RequestUtil.getParameters(getRequest());
    	if(param.containsKey("keyList")){
        	String keyList = param.get("keyList").toString();
			try {
				List<SvrMstMarketCalendarInfo001Dto> investList = JsonUtils.json2List(keyList,
						SvrMstMarketCalendarInfo001Dto.class);
				param.put("investList", investList);
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}
			List<SvrMstMarketCalendarInfo001Dto> dtoList = mstMarketCalendarInfoMService.selectForExport(param);
			File outFile = mstMarketCalendarInfoMService.makeFile(
					"/com/joyintech/tams/report/submitmaintenance/exchangerateinfo/conf/export_conf_c.xml", dtoList);
		         return ExportUtil.export(
		             this.getMessage(this.getPageId()) + GlobalConstant.OPEN_PARENTHESIS
		                                  + DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS
		                                  + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
		             outFile, true);
        }else{
        	return mstMarketCalendarInfoMService.export(this.getPageId(),param);
        }
    }
}
