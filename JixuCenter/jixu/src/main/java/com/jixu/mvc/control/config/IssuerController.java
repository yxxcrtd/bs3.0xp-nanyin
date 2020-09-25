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

import com.alibaba.fastjson.JSONObject;
import com.jixu.common.controller.ExceptionControl;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.util.JsonUtil;
import com.jixu.common.util.Pages;
import com.jixu.framework.msg.MsgFactory;
import com.jixu.mvc.control.model.IssuerModel;
import com.jixu.mvc.control.model.RPSecTypeLevelModel;
import com.jixu.mvc.control.model.VouchersModel;
import com.jixu.mvc.control.service.BankCfgService;
import com.jixu.mvc.control.service.ConfigService;
import com.orm.obj.IssueInfo;

@Controller
public class IssuerController extends ExceptionControl{
	@Resource(name="configService")
	private ConfigService configService;
	
	@Resource(name="msgFactory")
	private MsgFactory msgFactory;
	
	@Resource(name="commfuncService")
	private CommFuncService commService;
	
	@Resource(name="bankCfgService")
	private BankCfgService bankCfgService;
	
	@RequestMapping(value="/issuer.do", method = RequestMethod.GET, name="管理-发行商-页面显示")
	public String issuer() {
		return "issuer";
	}
	
	//给出最末级的ID，返回主分类和次分类
	@RequestMapping(value = "/config/issuerType.do", method = RequestMethod.POST)
	public void issuerType(@RequestParam(value = "id", required = true) int id, HttpServletResponse response) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		RPSecTypeLevelModel rpTypeModel=configService.loadIndustryType(id);
        data.put("major", rpTypeModel.getLevel1name());
        data.put("minor", rpTypeModel.getLevel2name());
        JsonUtil.writeObjToResponse(response, data);
	}
		
	//给出所有最末级的ID
	@RequestMapping(value = "/config/issuerTypes.do", method = RequestMethod.POST)
	public void industryType(HttpServletResponse response) {
	    
        HashMap<Integer, String> data = configService.loadIndustryType();
        JsonUtil.writeObjToResponse(response, data);
	}
	
	@RequestMapping(value="/config/issuerList.do", method = RequestMethod.POST)
	public void issuerList(HttpServletResponse response) {
		HashMap<String, String> data = configService.loadIssuer();
        JsonUtil.writeObjToResponse(response, data);
	}
	
	@RequestMapping(value="/config/issuers.do", method = RequestMethod.POST, name="管理-发行商-查询")
	public void issuers(@RequestParam(value = "name", required = false) String name, 
						@RequestParam(value = "num", required = false) Integer num,
						HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号", "代码", "名称", "国家", "行业分类", "行业主分类", "行业次分类");
		result.put("titles", titles);
		
		List<Object> data = new ArrayList<Object>();
		Pages<VouchersModel> pages=new Pages<VouchersModel>(num);
		
		List<IssuerModel> issuerList=configService.loadIssuerModelList(pages, name);
		
		for(IssuerModel issuer:issuerList){
			List<Object> item1 = new ArrayList<Object>();
			item1.add(issuer.getId());
			item1.add(issuer.getIssuercode());
			item1.add(issuer.getIssuername());
			item1.add(issuer.getCountryCode());
			item1.add(issuer.getIndustryType3());
			item1.add(issuer.getIndustrype1());
			item1.add(issuer.getIndustryType2());
			data.add(item1);
		}
		
		result.put("data", data);
		result.put("pages", pages.getTotalPages());
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/config/newIssuers.do", method = RequestMethod.POST, name="管理-发行商-新查询")
	public void newIssuers(@RequestParam(value = "name", required = false) String name, 
							@RequestParam(value = "page", required = true) int num,
							@RequestParam(value = "limits", required = true) int limits,
							@RequestParam(value = "export2excel", required = false) String export2excel,
						HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号", "代码", "名称", "国家", "行业分类", "行业主分类", "行业次分类");
		result.put("titles", titles);
		
		List<Object> data = new ArrayList<Object>();
		//判断是否需要导出  如果导出  limits需要很大
		limits=pageLimits(export2excel,limits);
		
		Pages<VouchersModel> pages=new Pages<VouchersModel>(num,limits);
		List<IssuerModel> issuerList=configService.loadIssuerModelList(pages, name);
		
		for(IssuerModel issuer:issuerList){
			List<Object> item1 = new ArrayList<Object>();
			item1.add(issuer.getId());
			item1.add(issuer.getIssuercode());
			item1.add(issuer.getIssuername());
			item1.add(issuer.getCountryCode());
			item1.add(issuer.getIndustryType3());
			item1.add(issuer.getIndustrype1());
			item1.add(issuer.getIndustryType2());
			data.add(item1);
		}
		
		result.put("data", data);
		result.put("pages", pages.getTotalPages());
		result.put("totalCount", pages.getTotalCount());
		//导出excel或返给前台显示
		returnControllerList(export2excel,result,response);
	}
	
	@RequestMapping(value="/config/issuerRemove.do", method = RequestMethod.POST, name="管理-发行商-删除")
    public void issuerRemove( @RequestParam(value = "id", required = true) String issuerID,
            			    HttpServletResponse response){
		ResultContainer container=configService.removeIssuer(issuerID);
		if(!container.isCorrectFlag()){
			HashMap<String, Object> result = new HashMap<String, Object>();
			HashMap<String, Object> issue = new HashMap<String, Object>();
			issue.put("code", container.getErrorCode());
			issue.put("level", "fail");
			issue.put("detail", container.getErrorMsg());
			result.put("issue", issue);
			JsonUtil.writeObjToResponse(response, result);
		}else{
			JsonUtil.writeObjToResponse(response, "");
		}
   }
	
	@RequestMapping(value="/config/newIssuerRemove.do", method = RequestMethod.POST, name="管理-发行商-删除")
    public void newIssuerRemove( @RequestParam(value = "json", required = true) String json,
            			    HttpServletResponse response){
		MsgContainer container = commService.executeNewProcessMsg(json);
		controllerReturnNew(response, container);
   }
   
   @RequestMapping(value="/config/issuerUpdate.do", method = RequestMethod.POST, name="管理-发行商-更新")
    public void issuerUpdate( @RequestParam(value = "json", required = true) String json,
            			   HttpServletResponse response){
	   MsgContainer container = commService.executeNewProcessMsg(json);
	   controllerReturnNew(response, container);
   }
   
   @RequestMapping(value="/config/issuerCreate.do", method = RequestMethod.POST, name="管理-发行商-创建")
   public void issuerCreate( @RequestParam(value = "json", required = true) String json,
           			        HttpServletResponse response){
	   	MsgContainer container=null;
	   	if(JixuUserUtil.joyinUserSystemFlag())
	   		container= commService.executeNewProcessMsg(json);
	   	else
	   		container= commService.executeMsg(json);
		controllerReturnNew(response, container);
	}
   
   @RequestMapping(value = "/config/getSpotSmf.do", method = RequestMethod.POST)
	public void getSpotSmf(String assetType, String bankAcct, String bankAcct2, HttpServletResponse response) {
	 
	   String secid="";
	   if(bankAcct!=null && bankAcct2!=null){
		   String longCurrency=bankCfgService.loadFxCurrencyByCashAcct(Integer.valueOf(bankAcct));
		   String shortCurrency=bankCfgService.loadFxCurrencyByCashAcct(Integer.valueOf(bankAcct2));
		   IssueInfo sec=configService.loadIssueInfoBuyCur(assetType,longCurrency,shortCurrency);
		   	if(sec!=null){
		   		secid=sec.getId()+"|"+sec.getSecurityid()+"|"+sec.getSecurityname();
		   	}  
	   }
		JsonUtil.writeObjToResponse(response, secid);
	}
}
