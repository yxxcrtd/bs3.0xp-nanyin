package com.joyintech.tams.invest.risk.creditrisklimitmanagement.linemonitordaily.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.linemonitordaily.dto.AbsLineMonitorDailyDto;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.linemonitordaily.dto.BusLineMonitorDailyDto;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.linemonitordaily.dto.EbCbLineMonitorDailyDto;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.linemonitordaily.dto.LineMonitorDailyDto;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.linemonitordaily.dto.ObjLineMonitorDailyDto;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.linemonitordaily.dto.TotalLineMonitorDailyDto;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.linemonitordaily.service.AbsLineMonitorDailyService;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.linemonitordaily.service.BusLineMonitorDailyService;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.linemonitordaily.service.EbCbLineMonitorDailyService;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.linemonitordaily.service.ObjLineMonitorDailyService;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.linemonitordaily.service.TotalLineMonitorDailyService;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.linemonitordaily.service.TyLineMonitorDailyService;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.linemonitordaily.util.Column;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.linemonitordaily.util.ExcelTool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 额度监测日报
 * @author gejiaao
 * @version 1.0 2020年03月09日
 * @see LineMonitorDaily001Controller
 * @since 1.0
 */
@Controller
@PageId("LineMonitorDaily001")
@RequestMapping("/invest/risk/creditrisklimitmanagement/linemonitordaily/LineMonitorDaily001")
public class LineMonitorDaily001Controller extends BaseController {
	 
	/**
     * lineSupervisionService
     */
    @Autowired
    private AbsLineMonitorDailyService abslineMonitorDailyService;
    
    @Autowired
    private EbCbLineMonitorDailyService ebCbLineMonitorDailyService;
    
    @Autowired
    private ObjLineMonitorDailyService objLineMonitorDailyService;
    
    @Autowired
    private BusLineMonitorDailyService busLineMonitorDailyService;
    
    @Autowired
    private TotalLineMonitorDailyService totalLineMonitorDailyService;
    
    @Autowired
    private TyLineMonitorDailyService tyLineMonitorDailyService;
    
    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.额度监测日报类型};
    
    
    
    @Override
    public String init(Model model) {
    	setSelectOptionToModel(model, DICT_CODES);
        Date sysDate = DateUtil.platDateFromCache();
        String morenDate = DateUtil.format(sysDate);
        model.addAttribute("sysDate", morenDate);
        return super.init(model);
    }

    /**
     * 导出信息excel
     * @return excel
     * @throws Exception 异常
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
    	Map<String,Object> selectParam = RequestUtil.getPageParam();
    	Date searchDate = DateUtil.parse((String) selectParam.get("searchDate"));
    	String dailyType = (String) selectParam.get("dailyType");
    	
    	LineMonitorDailyDto queryDto = new LineMonitorDailyDto(searchDate,dailyType);
    	
    	File outFile = null;
		/*
    	if(DictCode.额度监控总表.getValue().equals(dailyType)){
    		List<TotalLineMonitorDailyDto> dtoList = totalLineMonitorDailyService.querylineMonitorDailyList(queryDto);
            outFile = totalLineMonitorDailyService.makeFile(
                "/com/joyintech/tams/invest/risk/creditrisklimitmanagement/linemonitordaily/conf/export_total_conf.xml", dtoList);
    	}else if(DictCode.同业类额度监控.getValue().equals(dailyType)){
    	     
    	     outFile = tyLineMonitorDailyService.querylineMonitorDailyList(queryDto);
    		
    	}else if(DictCode.企业汇总.getValue().equals(dailyType)){
    		List<BusLineMonitorDailyDto> dtoList = busLineMonitorDailyService.querylineMonitorDailyList(queryDto);
            outFile = busLineMonitorDailyService.makeFile(
                "/com/joyintech/tams/invest/risk/creditrisklimitmanagement/linemonitordaily/conf/export_bus_conf.xml", dtoList);
    	}else if(DictCode.转债交换债监控日报.getValue().equals(dailyType)){
    		List<EbCbLineMonitorDailyDto> dtoList = ebCbLineMonitorDailyService.querylineMonitorDailyList(queryDto);
            outFile = ebCbLineMonitorDailyService.makeFile(
                "/com/joyintech/tams/invest/risk/creditrisklimitmanagement/linemonitordaily/conf/export_ebcb_conf.xml", dtoList);
    	}else if(DictCode.同业ABS限额监控日报.getValue().equals(dailyType)){
    		List<AbsLineMonitorDailyDto> dtoList = abslineMonitorDailyService.querylineMonitorDailyList(queryDto);
            outFile = abslineMonitorDailyService.makeFile(
                "/com/joyintech/tams/invest/risk/creditrisklimitmanagement/linemonitordaily/conf/export_abs_conf.xml", dtoList);
    	}else if(DictCode.项目类额度日报.getValue().equals(dailyType)){
    		List<ObjLineMonitorDailyDto> dtoList = objLineMonitorDailyService.querylineMonitorDailyList(queryDto);
            outFile = objLineMonitorDailyService.makeFile(
                "/com/joyintech/tams/invest/risk/creditrisklimitmanagement/linemonitordaily/conf/export_obj_conf.xml", dtoList);
    	}
		*/
        
        return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                + DateUtil.format(searchDate) + SystemGlobalConstant.CLOSE_PARENTHESIS
                + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
            	outFile, true);

    }
}
