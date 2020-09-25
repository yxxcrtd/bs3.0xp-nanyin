package com.joyintech.tams.invest.product.forecast.controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.product.forecast.dto.FinFlowForecastConfig;
import com.joyintech.tams.invest.product.forecast.service.FinFlowForecastService;

@Controller
@PageId("MaintainInvest001")
@RequestMapping("/invest/product/forecast/MaintainInvest001")
public class MaintainInvest001Conroller extends BaseController { 
	/* 
	 * 流动性预测表维护-投融资处
	 * 〈功能详细描述〉
	 * @author dongzhikang
	 * @version 1.0 2019年7月31日
	 * @see MaintainInvest001Conroller
	 * @since 1.0*/
	    @Autowired
	    private FinFlowForecastService flowForecastService;
	    @Override
	    	 public String init(Model model) {
	    	        FinFlowForecastConfig entity = new FinFlowForecastConfig();
	    	        entity.setQueryDate(DateUtil.format(DateUtil.platDateFromCache(), DateUtil.DEFAULT_DATE_FORMAT_EN));
	    	        entity.setDepType(DictCodeListExt2.流动性预测维护_部门类型_投融资处.getValue());
	    	        model.addAttribute("TARGETTYPE_JJCPKFQJS10",DictCodeListExt2.流动性预测维护_指标种类_非标资产.getValue());
	    	        model.addAttribute("TARGETTYPE_JJCPKFQJS13",DictCodeListExt2.流动性预测维护_指标种类_投融资处.getValue());
	    	        model.addAttribute("entity",entity);
	    	        return super.init(model);
	    	    }
	       
	    @ResponseBody
	    @RequestMapping("getTargetName")
	    public FinFlowForecastConfig getTargetName() throws Exception{
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	        String queryDate = this.getRequest().getParameter("queryDate"); 
	        FinFlowForecastConfig flowForecastConfig = new FinFlowForecastConfig();
	            Date qryDate = sdf.parse(queryDate);
	            flowForecastConfig = flowForecastService.dateTransfore(qryDate, flowForecastConfig);
	        return flowForecastConfig;
	    }
	    @ResponseBody
	    @RequestMapping("findForecast")
	    public Page<FinFlowForecastConfig> findForecast() throws Exception {
	        Map<String,Object> map = new HashMap<String, Object>();
	        map.put("queryDate", this.getRequest().getParameter("queryDate"));
	        map.put("depType", this.getRequest().getParameter("depType"));
	        Page<FinFlowForecastConfig> forecastList = flowForecastService.findForecast(map);
	        return forecastList;
	    }
	    @ResponseBody
	    @RequestMapping("save")
	    public AjaxResult save(@RequestBody @Validated FinFlowForecastConfig dto, BindingResult br)
	        throws Exception {
	        if (br.hasErrors()) {
	            return new AjaxResult(br);
	        }
	        String msg = flowForecastService.save(dto);
	        return new AjaxResult(msg);  
	    }

	    @ResponseBody
	    @RequestMapping("check")
	    public AjaxResult check(@RequestBody @Validated FinFlowForecastConfig dto,
	                                  BindingResult result) {
	        return commonCheck(result, "save");
	    }
	    }
