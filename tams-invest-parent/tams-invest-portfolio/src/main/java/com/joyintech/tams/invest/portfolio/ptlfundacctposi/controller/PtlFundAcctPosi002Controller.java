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

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.portfolio.ptlfundacctposi.dto.PtlFundAcctPosiDto;
import com.joyintech.tams.invest.portfolio.ptlfundacctposi.service.PtlFundAcctPosiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

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
        List<SelectOptionDto> psList = makeAllFloatRate();
        model.addAttribute("PS", JsonUtils.objectArray2Json(psList.toArray()));
//        super.setSelectOptionToModel(model, DictCode.金融产品类型_投管用, DictCode.交易方向);
        return super.init(model);
    }

    private List<SelectOptionDto> makeAllFloatRate() {
        List<SelectOptionDto> listFloatRate = new ArrayList<SelectOptionDto>();

        SelectDto selectDto1 = this.getSelectDataFromDataDic(DictCode.交易方向, null);
        for (SelectOptionDto dto : selectDto1.getOptions()) {
            listFloatRate.add(dto);
        }
        SelectDto selectDto2 = this.getSelectDataFromDataDic(DictCode.回购拆借方向, null);
        for (SelectOptionDto dto : selectDto2.getOptions()) {
            listFloatRate.add(dto);
        }
        SelectDto selectDto3 = this.getSelectDataFromDataDic(DictCode.互换方式, null);
        for (SelectOptionDto dto : selectDto3.getOptions()) {
            listFloatRate.add(dto);
        }
        return listFloatRate;
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
