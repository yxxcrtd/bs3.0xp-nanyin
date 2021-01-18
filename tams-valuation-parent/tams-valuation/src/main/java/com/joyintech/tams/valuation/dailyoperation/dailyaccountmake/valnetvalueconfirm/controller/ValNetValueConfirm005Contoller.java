package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.controller;


import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dto.ValNetValueConfirm005Dto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.service.ValNetValueConfirm005Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 净值修改
 * 
 * @author hechuan
 *
 */
@Controller
@PageId("ValNetValueConfirm005")
@RequestMapping("/valuation/dailyoperation/dailyaccountmake/valnetvalueconfirm/ValNetValueConfirm005")
public class ValNetValueConfirm005Contoller extends BaseController {

	/**
	 * 
	 */
	@Autowired
	private ValNetValueConfirm005Service valNetValueConfirm005Service;

	/**
	 * 重写init方法，加载页面 {@inheritDoc}
	 */
	@Override
	public String init(Model model) {
	    model.addAttribute("sysdate", DateUtil.getSysDate());
 		return super.init(model);
	}

    //产品查询
    @ResponseBody
    @RequestMapping("/findProduct")
    public Page<SelectOptionDto> findPort(){
		Map<String,Object> param=RequestUtil.getPageParam();
		int page=Integer.parseInt((String) param.get("page"));
		int length=Integer.parseInt((String) param.get("length"));

        return valNetValueConfirm005Service.findProduct(page, length,param);
    }
    //指定区间单位利率、周期性年化收益率、业绩基准
	@ResponseBody
	@RequestMapping("/getInfo")
	public List<ValNetValueConfirm005Dto> getInfo(@RequestBody Map<String,Object> param){
		List<ValNetValueConfirm005Dto> list=valNetValueConfirm005Service.getInfo(param);

		list.addAll(valNetValueConfirm005Service.getInfo1(param));
		list.addAll(valNetValueConfirm005Service.getInfo(param));
		return list;
	}
}
