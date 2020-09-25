package com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.controller;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;

@Controller
@PageId("RskSupervisionIndicatorParam004")
@RequestMapping("/invest/riskredis/quota/rsksupervisionindicatorparam/RskSupervisionIndicatorParam004")
public class RskSupervisionIndicatorParam004Controller extends BaseController{
	
	@Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
		if(StringUtil.isNotEmpty(this.getParam("redisField"))){
			model.addAttribute("redisField", this.getParam("redisField"));
		}
		return super.init(model);
	}
	
	/**
     * 画面查询方法
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<SelectOptionDto> findPage(){
    	Page<SelectOptionDto> page = new Page<SelectOptionDto>();
    	List<SelectOptionDto> list = new ArrayList<SelectOptionDto>();
    	SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.存储识别域, null);
    	for (SelectOptionDto dto : selectDto.getOptions()) {
    		list.add(dto);
        }
    	page.setRoot(list);
    	page.setRecords(list.size());
    	return page;
    }
}
