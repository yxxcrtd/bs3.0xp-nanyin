package com.joyintech.tams.report.submitexamination.investorsqualitycheck.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.dto.InvestorsQualityCheck001Dto;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.service.InvestorsQualityCheckMService;

/**
 * 投资者质量核查Controller
 * @author hpp
 *
 */
@Controller
@PageId("SvrInvestorsQualityCheck001")
@RequestMapping("/svreport/submitexamination/investorsqualitycheck/SvrInvestorsQualityCheck001")
public class InvestorsQualityCheck001Controller extends BaseController{

    
	@Autowired
	private InvestorsQualityCheckMService investorsQualityCheckMService;
	
	/**
	 * 画面的初始化的函数
	 */
	@RequestMapping(PAGE_INIT)
	public String init(Model model) {
        model.addAttribute("DATE",DateUtil.getCurrentDate());
        return super.init(model);
	}
	
	/**
     * 查询列表-颜色
     * 
     * @return Page
     */
    @ResponseBody
    @RequestMapping("/readConfig")
    public Map<String,String>  readConfig() {
        Map<String,String> resultMap=new HashMap<String,String>();
        resultMap=investorsQualityCheckMService.readConfig(RequestUtil.getPageParam());
        return resultMap;
    }
    
    /**
     * 查询左侧-核查项合格率信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/searchInvestorsPassRate")
    public Page<InvestorsQualityCheck001Dto> searchInvestorsPassRate(){
    	return investorsQualityCheckMService.searchInvestorsPassRate(RequestUtil.getPageParam());
    }
    
    /**
     * 查询列表 
     * --证件类型合格率
     * @return Page
     */
    @ResponseBody
    @RequestMapping("/searchTableZJLX")
    public Page<InvestorsQualityCheck001Dto> searchTableZJLX(){
        return investorsQualityCheckMService.searchTableZJLX(RequestUtil.getPageParam());
    }
	
    /**
     * 查询列表 
     * --证件匹配合格率
     * @return Page
     */
    @ResponseBody
    @RequestMapping("/searchTableZJPP")
    public Page<InvestorsQualityCheck001Dto> searchTableZJPP(){
        return investorsQualityCheckMService.searchTableZJPP(RequestUtil.getPageParam());
    }
    
    /**
     * 查询列表 
     * --及时性合格率
     * @return Page
     */
    @ResponseBody
    @RequestMapping("/searchTableJSX")
    public Page<InvestorsQualityCheck001Dto> searchTableJSX(){
        return investorsQualityCheckMService.searchTableJSX(RequestUtil.getPageParam());
    }
    
}
