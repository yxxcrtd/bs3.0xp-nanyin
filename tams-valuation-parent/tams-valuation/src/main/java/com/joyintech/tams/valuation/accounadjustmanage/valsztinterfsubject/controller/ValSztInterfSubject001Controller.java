package com.joyintech.tams.valuation.accounadjustmanage.valsztinterfsubject.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.accounadjustmanage.valsztinterfsubject.dto.ValSztInterfSubject001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.valsztinterfsubject.service.ValSztInterfSubject001Service;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.service.BaseCommonService;
import com.joyintech.tams.valuation.interf.inertfStartUp.service.ValInertfStartUp001Service;

/**
 * 科目信息表  从xybranch20190115版本合并过来 by yanya on 20191102
 * @author hudongyang
 *
 */
@Controller
@PageId("ValSztInterfSubject001")
@RequestMapping("/valuation/accounadjustmanage/valsztinterfsubject/ValSztInterfSubject001")
public class ValSztInterfSubject001Controller extends BaseController{
	
	@Autowired
	private ValSztInterfSubject001Service valSztInterfSubject001Service;
	
	@Autowired
    private BaseCommonService baseCommonService;
	
	@Autowired
	private ValInertfStartUp001Service valInertfStartUp001Service;
	
	@Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.深圳通报文状态);
		String sysDate = baseCommonService.getSysDateString();
        if(sysDate==null||"".equals(sysDate)){
            sysDate=DateUtil.format(new Date());
        }
        model.addAttribute("sysDate", sysDate);
        
        return super.init(model);
	}
	
	/**
	 * 深圳通科目信息页面查询方法
	 * <功能详细描述>
	 * @return
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("findPage")
    @ResponseBody
    public Page<ValSztInterfSubject001Dto> findPage()
            throws Exception {
		Page<ValSztInterfSubject001Dto> page = valSztInterfSubject001Service.findPage(RequestUtil.getPageParam());
		return page;
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
		String interType = "2";//2标识科目信息表
		String datas = this.getParam("datas");
		String url = valInertfStartUp001Service.getSztUrl(null);
		Map<String, String> urlParams = valSztInterfSubject001Service.getParm(datas);
		String cdate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		urlParams.put(InterfCommonConstant.接口_开始日, cdate);//默认未系统当前时间
		urlParams.put(InterfCommonConstant.接口_运行接口名称, interType);//此处是科目信息表
        valInertfStartUp001Service.runInterf(url, urlParams);
		return json.toString();
	}
}
