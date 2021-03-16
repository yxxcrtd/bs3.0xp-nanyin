package com.joyintech.tams.report.submitexamination.investorsqualitycheck.controller;

import java.util.HashMap;
import java.util.List;
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
@PageId("InvestorsQualityCheck001")
@RequestMapping("/svreport/submitexamination/investorsqualitycheck/InvestorsQualityCheck001")
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

    /**
     * 查询列表
     * --产品状态合格率
     * @return Page
     */
    @ResponseBody
    @RequestMapping("/searchTableCPZT")
    public Page<InvestorsQualityCheck001Dto> searchTableCPZT(){
        return investorsQualityCheckMService.searchTableCPZT(RequestUtil.getPageParam());
    }

    /**
     * 查询列表
     * --投资者数量匹配合格率
     * @return Page
     */
    @ResponseBody
    @RequestMapping("/searchTableSLPP")
    public Page<InvestorsQualityCheck001Dto> searchTableSLPP(){
        return investorsQualityCheckMService.searchTableSLPP(RequestUtil.getPageParam());
    }
    
    /**
     * 查询列表
     * --募集总份额合格率
     * @return List
     */
    @ResponseBody
    @RequestMapping("/searchTableMJQFE")
    public List<InvestorsQualityCheck001Dto> searchTableMJQFE(){
        return investorsQualityCheckMService.searchTableMJQFE(RequestUtil.getPageParam());
    }
    
    /**
     * 查询列表
     * --实际募集金额（元）合格率
     * @return List
     */
    @ResponseBody
    @RequestMapping("/searchTableMJQYE")
    public List<InvestorsQualityCheck001Dto> searchTableMJQYE(){
        return investorsQualityCheckMService.searchTableMJQYE(RequestUtil.getPageParam());
    }
    
    /**
     * 查询列表
     * --登记完整性合格率
     * @return List
     */
    @ResponseBody
    @RequestMapping("/searchTableDJWZX")
    public Page<InvestorsQualityCheck001Dto> searchTableDJWZX(){
        return investorsQualityCheckMService.searchTableDJWZX(RequestUtil.getPageParam());
    }
    
    /**
     * 查询列表
     * --产品余额（元）合格率（封闭式产品）
     * @return List
     */
    @ResponseBody
    @RequestMapping("/searchTableCPYEFBX")
    public List<InvestorsQualityCheck001Dto> searchTableCPYEFBX(){
        return investorsQualityCheckMService.searchTableCPYEFBX(RequestUtil.getPageParam());
    }
    
    /**
    * 查询列表
    * --产品余额（元）合格率（开放式式产品）
    * @return List
    */
   @ResponseBody
   @RequestMapping("/searchTableCPYEKFX")
   public List<InvestorsQualityCheck001Dto> searchTableCPYEKFX(){
       return investorsQualityCheckMService.searchTableCPYEKFX(RequestUtil.getPageParam());
   }
    
    /**
    * 查询列表
    * --产品余额（元）合格率（封闭式产品）
    * @return Page
    */
   @ResponseBody
   @RequestMapping("/searchTableCPFEFBX")
   public List<InvestorsQualityCheck001Dto> searchTableCPFEFBX(){
       return investorsQualityCheckMService.searchTableCPFEFBX(RequestUtil.getPageParam());
   }
    
    /**
    * 查询列表
    * --产品余额（元）合格率（封闭式产品）
    * @return Page
    */
   @ResponseBody
   @RequestMapping("/searchTableCPFEKFX")
   public List<InvestorsQualityCheck001Dto> searchTableCPFEKFX(){
       return investorsQualityCheckMService.searchTableCPFEKFX(RequestUtil.getPageParam());
   }
    
}
