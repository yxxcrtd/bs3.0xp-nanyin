package com.jixu.mvc.control.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jixu.common.controller.ExceptionControl;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.util.JsonUtil;
import com.jixu.msg.dto.CurrencyDto;
import com.jixu.mvc.control.service.ConfigService;
import com.orm.obj.Country;
import com.orm.obj.FxCurrency;
import com.orm.obj.Province;

@Controller
public class InternationalController extends ExceptionControl{

	@Resource(name = "configService")
	private ConfigService configService;
	
	@Resource(name = "commfuncService")
	private CommFuncService commService;

	@RequestMapping(value="/international.do", method = RequestMethod.GET, name="管理-国际-页面显示")
	public String bank() {
		return "international";
	}
	
	@RequestMapping(value = "/config/currencyList.do", method = RequestMethod.POST)
	public void currencyList(@RequestParam(value = "all", required = true) boolean all,
			                 HttpServletResponse response) {
		HashMap<String, String> result=configService.getCurrencyList();
		if (all){
			result.put("0", "全部");
		}
		JsonUtil.writeObjToResponse(response, result);
	}
	
	
	// 返回记账货币简称 新框架前端费用显示需要
	@RequestMapping(value = "/config/funcCurrencyCode.do", method = RequestMethod.POST)
	public void funcCurrencyCode(@RequestParam(value = "funcCurrency", required = true) String fxCurNumStr,
			HttpServletResponse response) {
		String curCode = commService.loadFxCurrencyCode(fxCurNumStr);
		if (curCode != null) {
			JsonUtil.writeObjToResponse(response, curCode);
		}
	}
	
	// 返回交易货币简称 新框架前端费用显示需要
	@RequestMapping(value = "/config/tradeCurrencyCode.do", method = RequestMethod.POST)
	public void tradeCurrencyCode(@RequestParam(value = "feeTradeCurrency", required = true) String fxCurNumStr,
			HttpServletResponse response) {
		if(fxCurNumStr!=""){
			String curCode = commService.loadFxCurrencyCode(fxCurNumStr);
			if (curCode != null) {
				JsonUtil.writeObjToResponse(response, curCode);
			}
		}else{
			JsonUtil.writeObjToResponse(response, "");
		}
	}
		
	// 返回费用货币简称 新框架前端费用显示需要 
	@RequestMapping(value = "/config/feeCurrencyCode.do", method = RequestMethod.POST)
	public void feeCurrencyCode(@RequestParam(value = "tableFeeCurrency", required = true) String fxCurNumStr,
			HttpServletResponse response) {
		if(fxCurNumStr!=""){
			String curCode = commService.loadFxCurrencyCode(fxCurNumStr);
			if (curCode != null) {
				JsonUtil.writeObjToResponse(response, curCode);
			}
		}else{
			JsonUtil.writeObjToResponse(response, "");
		}
	}
	
	@RequestMapping(value = "/config/currencyDetail.do", method = RequestMethod.POST, name="管理-国际-货币详情")
	public void currencyDetail(HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
        List<String> titles = Arrays.asList("编号", "短名", "货币名称", "状态");
        result.put("titles", titles);
        List<Object> data = new ArrayList<Object>();
        
        List<CurrencyDto> currencyList=configService.loadCurrencyList();
        for(CurrencyDto dto:currencyList){
	        List<Object> item1 = new ArrayList<Object>();
	        item1.add(dto.getCurID());
	        item1.add(dto.getCurCode());
	        item1.add(dto.getCurName());
	        item1.add(dto.getActive());
	        data.add(item1);
        }
        
        result.put("data", data);
        JsonUtil.writeObjToResponse(response, result);
	}
	
	 @RequestMapping(value="/config/currencyUpdate.do", method = RequestMethod.POST, name="管理-国际-货币更新")
     public void currencyUpdate(@RequestParam(value = "id", required = true) String id,
             				 @RequestParam(value = "params", required = true) String params,
             			     HttpServletResponse response){
        List<Object> param_list = JsonUtil.stringToList(params);
        String curCode=param_list.get(0).toString();
        String curName=param_list.get(1).toString();
        int activeFlag=Integer.valueOf(param_list.get(2).toString());
        
        FxCurrency cur=new FxCurrency();
        cur.setId(id);
        cur.setCurrCode(curCode);
        cur.setCurrName(curName);
        cur.setActive(activeFlag);
        
        configService.updateCurrency(cur);
        JsonUtil.writeObjToResponse(response, "");
    }
	 
	 @RequestMapping(value="/config/currencyCreate.do", method = RequestMethod.POST, name="管理-国际-货币创建")
     public void currencyCreate(@RequestParam(value = "params", required = true) String params,
             			        HttpServletResponse response){
		 List<Object> param_list = JsonUtil.stringToList(params);
	     String curCode=param_list.get(0).toString();
	     String curName=param_list.get(1).toString();
	     int activeFlag=Integer.valueOf(param_list.get(2).toString());
	        
	     FxCurrency cur=new FxCurrency();
	     cur.setId(curCode);
	     cur.setCurrCode(curCode);
	     cur.setCurrName(curName);
	     cur.setActive(activeFlag);
	        
	     configService.createCurrency(cur);
        JsonUtil.writeObjToResponse(response, cur.getId() + "");
    }

	@RequestMapping(value = "/config/countryList.do", method = RequestMethod.POST)
	public void countryList(HttpServletResponse response) {
		HashMap<String, String> result = configService.loadCountry();
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value = "/config/countryCode.do", method = RequestMethod.POST)
	public void countryCode(@RequestParam(value = "country", required = false) String countryID,
			HttpServletResponse response) {
		HashMap<String, String> map=configService.loadCountry();
		if(map.containsKey(countryID))
			JsonUtil.writeObjToResponse(response, map.get(countryID));
		else
			JsonUtil.writeObjToResponse(response, "");
	}
	
	@RequestMapping(value = "/config/countryDetail.do", method = RequestMethod.POST, name="管理-国际-国家详情")
	public void countryDetail(HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
        List<String> titles = Arrays.asList("编号", "国家名称", "国家代码", "状态");
        result.put("titles", titles);
        List<Object> data = new ArrayList<Object>();
        
        List<Country> countryList=configService.loadCountryList();
        for(Country country:countryList){
	        List<Object> item1 = new ArrayList<Object>();
	        item1.add(country.getCountryID());
	        item1.add(country.getCountryName());
	        item1.add(country.getCountryCode());
	        item1.add(country.getActive());
	        data.add(item1);
        }
        
        result.put("data", data);
        JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/config/countryUpdate.do", method = RequestMethod.POST, name="管理-国际-国家更新")
    public void countryUpdate(@RequestParam(value = "id", required = true) String id,
            				  @RequestParam(value = "params", required = true) String params,
            			      HttpServletResponse response){
       List<Object> param_list = JsonUtil.stringToList(params);
       String countryname=param_list.get(0).toString();
       String countryCode=param_list.get(1).toString();
       int activeFlag=Integer.valueOf(param_list.get(2).toString());
       
       Country country=new Country();
       country.setCountryID(id);
       country.setCountryName(countryname);
       country.setCountryCode(countryCode);
       country.setActive(activeFlag);
       
       configService.updateCountry(country);
       
       JsonUtil.writeObjToResponse(response, "");
   }
	 
	 @RequestMapping(value="/config/countryCreate.do", method = RequestMethod.POST, name="管理-国际-国家创建")
    public void countryCreate(@RequestParam(value = "params", required = true) String params,
            			      HttpServletResponse response){
		 List<Object> param_list = JsonUtil.stringToList(params);
		 String countryname=param_list.get(0).toString();
		 String countryCode=param_list.get(1).toString();
		 int activeFlag=Integer.valueOf(param_list.get(2).toString());
	       
	     Country country=new Country();
	     country.setCountryID(countryCode);
	     country.setCountryName(countryname);
	     country.setCountryCode(countryCode);
	     country.setActive(activeFlag);
	    
	     configService.createCountry(country);
	     JsonUtil.writeObjToResponse(response, country.getCountryID() + "");
   }

	@RequestMapping(value = "/config/exchangeList.do", method = RequestMethod.POST)
	public void exchnageList(HttpServletResponse response) {
		HashMap<String, String> result = configService.loadExchange();
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/config/stateList.do", method = RequestMethod.POST)
	public void stateList(HttpServletResponse response) {
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		List<Province> provinceList = configService.getProvinceist();
		for(Province pro:provinceList){
			result.put(pro.getProID(), pro.getProName());
		}
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value = "/config/currencyRoundMethod.do", method = RequestMethod.POST)
	public void currencyRoundMethod(HttpServletResponse response){
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		result.put(0, "四舍五入");
		result.put(1, "截位");
		JsonUtil.writeObjToResponse(response, result);
   }
	
	@RequestMapping(value = "/config/currencyStatus.do", method = RequestMethod.POST)
	public void currencyStatus(HttpServletResponse response){
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		result.put(0, "有效");
		result.put(1, "停用");
		JsonUtil.writeObjToResponse(response, result);
   }
	
	@RequestMapping(value = "/config/countryStatus.do", method = RequestMethod.POST)
	public void countryStatus(HttpServletResponse response){
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		result.put(0, "有效");
		result.put(1, "停用");
		JsonUtil.writeObjToResponse(response, result);
   }
}
