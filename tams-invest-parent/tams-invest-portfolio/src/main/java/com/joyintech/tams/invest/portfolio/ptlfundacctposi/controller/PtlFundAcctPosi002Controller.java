/*
 * 文件名：PtlFundAcctPosi.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2019年2月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlfundacctposi.controller;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.constant.DictCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.portfolio.ptlfundacctposi.dto.PtlFundAcctPosiDto;
import com.joyintech.tams.invest.portfolio.ptlfundacctposi.service.PtlFundAcctPosiService;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 
 * 资金流水明细
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2019年2月20日
 * @see PtlFundAcctPosi002Controller
 * @since 1.0
 */
@PageId("PtlFundAcctPosi002")
@Controller
@RequestMapping("/invest/portfolio/ptlfundacctposi/PtlFundAcctPosi002")
public class PtlFundAcctPosi002Controller extends BaseController{
	
	@Autowired
	private PtlFundAcctPosiService ptlFundAcctPosiService;
	
	/**
     * 界面初始化
     * {@inheritDoc}
     */
    @Override
    public String init(Model model){
        model.addAttribute("PORTFOLIO_ID", getParam("portfolioId"));
        model.addAttribute("HODING_TYPE", getParam("hodingType"));
        model.addAttribute("ACCT_ID", getParam("acctId"));
        model.addAttribute("CCY", getParam("ccy"));
        model.addAttribute("CDATE", getParam("cdate"));
        super.setSelectOptionToModel(model, DictCode.金融产品类型_投管用, DictCode.交易方向);
        return super.init(model);
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
        return ptlFundAcctPosiService.selectForPage1(RequestUtil.getPageParam());
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
