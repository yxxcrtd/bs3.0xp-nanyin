package com.jixu.mvc.control.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jixu.common.controller.ExceptionControl;
import com.jixu.common.util.JsonUtil;
import com.jixu.mvc.control.service.ConfigService;
import com.orm.obj.RPSecType;

@Controller
public class IndustryController extends ExceptionControl{
	
	@Resource(name="configService")
	private ConfigService configService;
	
	@RequestMapping(value="/industry.do", method = RequestMethod.GET, name="管理-行业-页面显示")
	public String bank() {
		return "industry";
	}
	
	@RequestMapping(value = "/config/industryList.do", method = RequestMethod.POST, name="管理-行业-查询")
	public void industryList(HttpServletResponse response) {
		HashMap<Integer, String> result = configService.loadIndustryTypeFather();
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value = "/config/industryDetail.do", method = RequestMethod.POST, name="管理-行业-详情")
	public void industryDetail(
			@RequestParam(value = "id", required = true) int id,
			HttpServletResponse response) {
		
        
        List<Object> data = new ArrayList<Object>();
        
        List<RPSecType> industryList=configService.loadIndustryTypeList(id);
        
        for(RPSecType rtype:industryList){
        	HashMap<String, Object> lvl1 = new HashMap<String, Object>();
            lvl1.put("id", rtype.getId());
            lvl1.put("name", rtype.getTypename());
            lvl1.put("parent", rtype.getFathernode());
            data.add(lvl1);
        }
        
        JsonUtil.writeObjToResponse(response, data);
	}
	
	 @RequestMapping(value="/config/industryUpdate.do", method = RequestMethod.POST, name="管理-行业-更新")
     public void industryUpdate(@RequestParam(value = "id", required = true) String id,
             				    @RequestParam(value = "params", required = true) String params,
             			     HttpServletResponse response){
        System.out.println("Update industry id -" + id + ". Detail is as below - ");
        List<Object> param_list = JsonUtil.stringToList(params);
        configService.updateIndustry(param_list);
        
        System.out.println(param_list);
        JsonUtil.writeObjToResponse(response, "");
    }
}
