package com.joyintech.tams.invest.riskredis.quota.supervisionindividuation.controller;

import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.joyintech.tams.invest.riskredis.quota.superviseindicator.service.RskSupervisionIndicator002Service;
import com.joyintech.tams.invest.riskredis.quota.supervisionindividuation.dto.RskIndividSpecialStageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.riskredis.quota.supervisionindividuation.service.RskSupervisionIndividuation001Service;

@Controller
@PageId("RskSupervisionIndividuation003")
@RequestMapping("/invest/riskredis/quota/supervisionindividuation/RskSupervisionIndividuation003")
public class RskSupervisionIndividuation003Controller extends BaseController{
	
	@Autowired
	private RskSupervisionIndividuation001Service rskSupervisionIndividuation001Service;

	@Autowired(required = false)
	private RskSupervisionIndicator002Service rskSupervisionIndicator002Service;
	/**
     * 初始化的查询条件
     */
    private static final IDictCode[] DICT_CODES = new BussinessDictCodeRds[] { BussinessDictCodeRds.运算符,
        BussinessDictCodeRds.单位, BussinessDictCodeRds.触警操作
       };
	@Override
    public String init(Model model) {
		setSelectOptionToModel(model, DICT_CODES);
		String indId = this.getParam("indId");
		RskIndividSpecialStageDto dto = new RskIndividSpecialStageDto();
		if(StringUtil.isNotEmpty(this.getParam("uniteId"))){
			dto.setUniteId(this.getParam("uniteId"));
			dto.setApproveCond(this.getParam("approveCond"));
			dto.setFoulCond(this.getParam("foulCond"));
			dto.setWarnCond(this.getParam("warnCond"));
			if(this.getParam("startDate").contains("-")){
				dto.setStartDate(DateUtil.parse(this.getParam("startDate")));
			}
			else{
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				long startDate = Long.parseLong(this.getParam("startDate"));
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(startDate);
				dto.setStartDate(DateUtil.parse(formatter.format(calendar.getTime())));
			}
			if(this.getParam("endDate").contains("-")){
				dto.setEndDate(DateUtil.parse(this.getParam("endDate")));
			}
			else{
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				long endDate = Long.parseLong(this.getParam("endDate"));
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(endDate);
				dto.setEndDate(DateUtil.parse(formatter.format(calendar.getTime())));
			}
			model.addAttribute("entity", dto);
		}else{
			dto.setUniteId(IDGenerator.generate());
			model.addAttribute("entity", dto);
		}
		model.addAttribute("IND_TYPE", rskSupervisionIndividuation001Service.getIndType(indId));
		model.addAttribute("cjcz_yj", BussinessDictCodeListRds.触警操作_预警.getValue());
        model.addAttribute("cjcz_sp", BussinessDictCodeListRds.触警操作_审批.getValue());
        model.addAttribute("cjcz_jz", BussinessDictCodeListRds.触警操作_禁止.getValue());
        //-------add by 裴家俊 2019-12-03----获取阈值方向与单位 start---------------
		model.addAttribute("yzInfo", rskSupervisionIndicator002Service.getBussinessEntityByIndId(indId));
		//-------add by 裴家俊 2019-12-03----获取阈值方向与单位  end---------------
		return super.init(model);
	}
}
