package com.joyintech.service.risk.creditrisklimitmanagement.linemonitordaily.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.risk.creditrisklimitmanagement.linemonitordaily.dto.AbsLineMonitorDailyDto;
import com.joyintech.service.risk.creditrisklimitmanagement.linemonitordaily.dto.BusLineMonitorDailyDto;
import com.joyintech.service.risk.creditrisklimitmanagement.linemonitordaily.dto.EbCbLineMonitorDailyDto;
import com.joyintech.service.risk.creditrisklimitmanagement.linemonitordaily.dto.LineMonitorDailyDto;
import com.joyintech.service.risk.creditrisklimitmanagement.linemonitordaily.dto.ObjLineMonitorDailyDto;
import com.joyintech.service.risk.creditrisklimitmanagement.linemonitordaily.dto.TotalLineMonitorDailyDto;
import com.joyintech.service.risk.creditrisklimitmanagement.linemonitordaily.dto.TyLineMonitorDailyDto;
import com.joyintech.service.risk.creditrisklimitmanagement.linemonitordaily.service.LineMonitorDailyService;

@RestController
@RequestMapping("/linemonitordaily")
public class LineMonitorDailyController {
	
    @Autowired
    private LineMonitorDailyService lineMonitorDailyService;
    
    @ResponseBody
    @RequestMapping("queryAbsLineMonitorTest")
	public Map<String,Object> abslineMonitorDaily(@RequestBody LineMonitorDailyDto queryDto){
		return lineMonitorDailyService.abslineMonitorDaily(queryDto.getCdate());
	}
	
    @ResponseBody
    @RequestMapping("queryAbsLineMonitor")
    public List<AbsLineMonitorDailyDto> queryAbsLineMonitorDailyList(@RequestBody LineMonitorDailyDto queryDto){
    	return lineMonitorDailyService.queryAbsLineMonitorDailyList(queryDto.getCdate());
    }
    
    @ResponseBody
    @RequestMapping("queryEbCbLineMonitor")
    public List<EbCbLineMonitorDailyDto> queryEbCbLineMonitorDailyList(@RequestBody LineMonitorDailyDto queryDto){
    	return lineMonitorDailyService.queryEbCbLineMonitorDailyList(queryDto.getCdate());
    }
    
    @ResponseBody
    @RequestMapping("queryObjLineMonitor")
    public List<ObjLineMonitorDailyDto> queryObjLineMonitorDailyList(@RequestBody LineMonitorDailyDto queryDto){
    	return lineMonitorDailyService.queryObjLineMonitorDailyList(queryDto.getCdate());
    }
	
    @ResponseBody
    @RequestMapping("queryBusLineMonitor")
    public List<BusLineMonitorDailyDto> queryBusLineMonitorDailyList(@RequestBody LineMonitorDailyDto queryDto){
    	return lineMonitorDailyService.queryBusLineMonitorDailyList(queryDto.getCdate());
    }	
    
    
    @ResponseBody
    @RequestMapping("queryTatolLineMonitor")
    public List<TotalLineMonitorDailyDto> queryTotalLineMonitorDailyList(@RequestBody LineMonitorDailyDto queryDto){
    	return lineMonitorDailyService.queryTotalLineMonitorDailyList(queryDto.getCdate());
    }
    
    @ResponseBody
    @RequestMapping("queryTyLineMonitor")
    public List<TyLineMonitorDailyDto> queryTyLineMonitorDailyList(@RequestBody Map<String, Object> param){
    	return lineMonitorDailyService.queryTyLineMonitorDailyList(param);
    }

    
    
}
