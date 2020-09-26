package com.joyintech.tams.valuation.accounadjustmanage.valsztinterfbalance.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.accounadjustmanage.valsztinterfbalance.dto.ValSztInterfBalance001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.valsztinterfbalance.service.ValSztInterfBalance001Service;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.service.BaseCommonService;
import com.joyintech.tams.valuation.interf.inertfStartUp.service.ValInertfStartUp001Service;

/**
 * 科目余额表 从xybranch20190115版本合并过来 by yanya on 20191102
 * @author hudongyang
 *
 */
@Controller
@PageId("ValSztInterfBalance001")
@RequestMapping("/valuation/accounadjustmanage/valsztinterfbalance/ValSztInterfBalance001")
public class ValSztInterfBalance001Controller extends BaseController{
	
	@Autowired
    private ValSztInterfBalance001Service valSztInterfBalance001Service;
	
	@Autowired
	private BaseCommonService baseCommonService;
	
	@Autowired
	private ValInertfStartUp001Service valInertfStartUp001Service;
	
	@Override
    public String init(Model model) {
		this.setSelectOptionToModel(model, DictCode.深圳通对账状态);
        this.setSelectOptionToModel(model, DictCode.深圳通报文状态);
		String sysDate=baseCommonService.getSysDateString();
        if(sysDate==null||"".equals(sysDate)){
            sysDate=DateUtil.format(new Date());
        }
        model.addAttribute("sysDate", sysDate);
        
        return super.init(model);
	}
	
	/**
     * 深圳通科目余额对账页面查询方法
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
	@RequestMapping("findPage")
    @ResponseBody
    public Page<ValSztInterfBalance001Dto> findPage()
            throws Exception {
		Page<ValSztInterfBalance001Dto> page = valSztInterfBalance001Service.findPage(RequestUtil.getPageParam());
		return page;
	}
	
	@ResponseBody
    @RequestMapping("check")
	public AjaxResult check(@RequestBody @Valid ValSztInterfBalance001Dto dto, BindingResult result){
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        return commonCheck(result, "");
	}
	
	/**
     * 调用接口发送对账请求 从xybranch20190115版本合并过来 by yanya on 20191102
     * <功能详细描述>
     * @return
     * @throws IOException
     * @see
     * @since 1.0
     */
	@ResponseBody
    @RequestMapping("sztRunAs")
	public String sztRunAs() throws IOException{
		JsonObject json = new JsonObject();
		String interType = "0";//0标识科目余额表
		String prodIds = this.getParam("prodIds");
		String url = valInertfStartUp001Service.getSztUrl(null);
		Map<String, String> urlParams = valSztInterfBalance001Service.getParm(prodIds);
		String cdate = this.getParam("valDate");
		urlParams.put(InterfCommonConstant.接口_开始日, DateUtil.formatDate(cdate));
		urlParams.put(InterfCommonConstant.接口_运行接口名称, interType);//此处是科目信息表
        valInertfStartUp001Service.runInterf(url, urlParams);
		return json.toString();
	}
}
