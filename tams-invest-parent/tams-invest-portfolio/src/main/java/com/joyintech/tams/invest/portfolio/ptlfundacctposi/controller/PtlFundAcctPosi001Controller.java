/*
 * 文件名：PtlFundAcctPosi001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2019年2月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlfundacctposi.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.portfolio.ptlfundacctposi.dto.PtlFundAcctPosiDto;
import com.joyintech.tams.invest.portfolio.ptlfundacctposi.service.PtlFundAcctPosiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 
 * 组合资金账户头寸查询
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2019年2月20日
 * @see PtlFundAcctPosi001Controller
 * @since 1.0
 */
@Controller
@PageId("PtlFundAcctPosi001")
@RequestMapping("/invest/portfolio/ptlfundacctposi/PtlFundAcctPosi001")
public class PtlFundAcctPosi001Controller extends BaseController{
	
	@Autowired
	private PtlFundAcctPosiService ptlFundAcctPosiService;
	/**
     * 界面初始化
     * {@inheritDoc}
     */
    @Override
    public String init(Model model){
    	this.setSelectOptionToModel(model, DictCode.资金账户类型);
        /*this.setSelectOptionToModel(model, BussinessDictCode.资金账户类型,BussinessDictCode.交易流程状态);*/
        Date sysDate = DateUtil.platDateFromCache();
        String morenDate = DateUtil.format(sysDate);
        // update by caiqun 20200819 东莞 工单YHZGCPB20200814003  start
        //model.addAttribute("sysDate", morenDate);
        model.addAttribute("sysDate", morenDate + " - " + morenDate);
        // update by caiqun 20200819 东莞 工单YHZGCPB20200814003  end
        return super.init(model);
    }
	
    /**
     * 
     * 组合代码下拉框
     * <功能详细描述>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @RequestMapping("/getPortfolioId")
    @ResponseBody
    public Page<SelectOptionDto> getPortfolioId(){
        return ptlFundAcctPosiService.getPortfolioId(RequestUtil.getPageParam());
    }
    
    /**
     * 
     * 页面查询，表格分页
     * <功能详细描述>
     * @return Page
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<PtlFundAcctPosiDto> selectAllMstCaptialAccount()
        throws Exception {
        return ptlFundAcctPosiService.selectForPage(RequestUtil.getPageParam());
    }
    
    /**
     * 导出信息excel
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */

    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return ptlFundAcctPosiService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
}
