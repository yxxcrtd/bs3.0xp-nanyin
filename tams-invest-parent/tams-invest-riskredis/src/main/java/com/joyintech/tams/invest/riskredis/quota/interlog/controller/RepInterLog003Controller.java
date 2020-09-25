package com.joyintech.tams.invest.riskredis.quota.interlog.controller;


import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.riskredis.quota.interlog.dto.RepInterLogDto;
import com.joyintech.tams.invest.riskredis.quota.interlog.service.RepInterLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PageId("RepInterLog003")
@RequestMapping("/invest/riskredis/quota/interlog/RepInterLog003")
public class RepInterLog003Controller extends BaseController{
	
	@Autowired
	private RepInterLogService repInterLogService;
	/**
     * 接口日志查询详细的初始化
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
         this.setSelectOptionToModel(model, BussinessDictCodeRds.指标类型, BussinessDictCodeRds.指标状态);
         
         String uuid = this.getParam("uuid");
         //String responseData = this.getParam("responseData");
         RepInterLogDto responseData = repInterLogService.selectInfo(uuid);
         //repInterLogDto.setPageModel(this.getParam("pageModel"));;
         //model.addAttribute("entity", repInterLogDto);
         model.addAttribute("responseData", responseData.getResponseData());
         model.addAttribute("uuid", uuid);
         return super.init(model);
    }
}
