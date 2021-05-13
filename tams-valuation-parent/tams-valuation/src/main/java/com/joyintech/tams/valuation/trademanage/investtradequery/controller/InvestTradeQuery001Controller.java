/*
 * 文件名：InvestTradeQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年7月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.trademanage.investtradequery.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccounthandle.service.ValAccountHandle001Service;
import com.joyintech.tams.valuation.trademanage.investtradequery.dto.InvestTradeDto;
import com.joyintech.tams.valuation.trademanage.investtradequery.service.CurrentDeposit001Service;
import com.joyintech.tams.valuation.trademanage.investtradequery.service.InvestTradeQuery001Service;
import com.joyintech.tams.constant.DictCode;

/**
 * 投资交易查询-controller
 * 
 * @author yangjian
 * @version 1.0 2018年7月24日
 * @see InvestTradeQuery001Controller
 * @since 1.0
 */
@Controller
@PageId("InvestTradeQuery001")
@RequestMapping("/valuation/trademanage/investtradequery/InvestTradeQuery001")
public class InvestTradeQuery001Controller extends BaseController {

    @Autowired
    private InvestTradeQuery001Service investTradeQuery001Service;
    /**
     * 活期存款交易currentDeposit001Service
     */
    @Autowired
    private CurrentDeposit001Service currentDeposit001Service;
    @Autowired
    private ValAccountHandle001Service valAccountHandle001Service;
    
    
    static final Map<String,String> fileMap = new HashMap<>();
    static{
    	fileMap.put("bond-title","/com/joyintech/tams/valuation/trademanage/investtradequery/conf/export_InvestTradeQuery.xml");
    	fileMap.put("plan-profit-title","/com/joyintech/tams/valuation/trademanage/investtradequery/conf/export_ PlanProfitTable.xml");
    	fileMap.put("plan-netvalue-title","/com/joyintech/tams/valuation/trademanage/investtradequery/conf/export_ PlanNetvalueTable.xml");
    	fileMap.put("plan-currency-title","/com/joyintech/tams/valuation/trademanage/investtradequery/conf/export_PlanCurrencyTable.xml");
    	fileMap.put("deposit-loan-title","/com/joyintech/tams/valuation/trademanage/investtradequery/conf/export_Depositloan.xml");
    	fileMap.put("lend-title","/com/joyintech/tams/valuation/trademanage/investtradequery/conf/export_LendTable.xml");
    	fileMap.put("repurchase-title","/com/joyintech/tams/valuation/trademanage/investtradequery/conf/export_RepurchaseTable.xml");
    	fileMap.put("fund-currency-title","/com/joyintech/tams/valuation/trademanage/investtradequery/conf/export_FundCurrencyTable.xml");
    	fileMap.put("fund-netvalue-title","/com/joyintech/tams/valuation/trademanage/investtradequery/conf/export_FundNetvalueTable.xml");
    	fileMap.put("current-deposit-title","/com/joyintech/tams/valuation/trademanage/investtradequery/conf/export_CurrentDepositTable.xml");
    	fileMap.put("cash-transactions-title","/com/joyintech/tams/valuation/trademanage/investtradequery/conf/export_CashtransactionsTable.xml");
    	fileMap.put("trade-account-title","/com/joyintech/tams/valuation/trademanage/investtradequery/conf/export_TradeAccountTable.xml");
    	fileMap.put("value-addedtaxs-title","/com/joyintech/tams/valuation/trademanage/investtradequery/conf/export_ValueAddedtaxs.xml");
        fileMap.put("value-reclass-title","/com/joyintech/tams/valuation/trademanage/investtradequery/conf/export_ValueReclass.xml");
        fileMap.put("bond-lend-title", "/com/joyintech/tams/valuation/trademanage/investtradequery/conf/export_BondLendTable.xml");
        fileMap.put("overdue-penalty-title", "/com/joyintech/tams/valuation/trademanage/investtradequery/conf/export_OverduePenalty.xml");
    }
	
    
    /**
     * 加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        // 业务类别下拉
        this.setSelectOptionToModel(model, DictCode.业务类型);
        /*  busiType
         *  01:拆借交易
            02:债券交易
            03:存款类资产交易
            05:债券买断式回购交易
            06:货币基金交易
            07:非货币基金交易
            08:权益类资产交易
            09:计划类资产交易
            10:外汇即期交易
            11:外汇远期交易
            12:外汇掉期交易
            13:利率互换交易
            14:债券借贷交易
            30:债券重分类交易
            37:逾期罚息交易
         * */
        //modify by yanya on 20210310 增加债券预处理查询
        String[] busiType={"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","18","19","30","37","24"};
        //参数过滤
        DictCode.checkDictCode(model,DictCode.业务类型,busiType,true);
        /* PS   T01:买入
                T02:卖出
                T03:行权
                T04:申购
                T05:申购确认
                T06:赎回
                T07:到期
                T08:还本
                T09:收息
                T10:付费
                T11:现金分红
                T12:红利再投
                T13:除权登记
                T14:外汇即期
                T15:外汇远期
                T16:外汇掉期
                T17:利率互换
                T18:认购确认
                T19:赎回确认
                T20:转入
                T21:转出
                T35:资产重分类
                T25:应收款转入
                T26:应收款转出
                T63:转换赎回确认
                T64:转换申购确认
         * */
        //modify by yanya on 20210310 增加行权
        //String[] psArr={"T01","T02","T03","T04","T05","T06","T07","T08","T09","T10","T11","T12","T13","T14","T15","T16","T17","T18","T19","T20","T21","T25","T26","T35","T63","T64"};
        //modify by yanya on 20210320 去除赎回结转
//        String[] psArr={"T01","T02","T03","T04","T05","T06","T07","T08","T09","T10","T11","T12","T13","T14","T15","T16","T17","T18","T19",
//                "T20","T21","T25","T26","T35","T63","T64","T39","T67","T68"};
        String[] psArr={"T01","T02","T03","T04","T05","T06","T07","T08","T09","T10","T11","T12","T13","T14","T15","T16","T17","T18","T19",
                "T20","T21","T25","T26","T35","T63","T64","T39","T67"};
        DictCode.checkDictCode(model,DictCode.交易方向,psArr,true);
        // 核算主体类别
//        this.setSelectOptionToModel(model, DictCode.核算主体类别);
        // 交易方向下拉
        this.setSelectOptionToModel(model, DictCode.交易方向);
        return super.init(model);
    }

    /**
     * 分页数据查询
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<InvestTradeDto> findPage()  throws Exception {
        return investTradeQuery001Service.findPage(RequestUtil.getPageParam());
    }
    /**
     * 套账下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getBookSetId")
    @ResponseBody
    public Page<SelectOptionDto> getBookSetId() {
        Map<String,Object> map = RequestUtil.getPageParam();
        return valAccountHandle001Service.getBookSetId2(map);
    }
    
    /**
     * 
     * 活期存款收息交易（点击资金账户名）查看
     * @param tradeId
     * @param model
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("selectByAccountName")
    public String openBatchGroupDetail(String tradeId, Model model) {
        this.setSelectOptionToModel(model, DictCode.交易方向);
        this.setSelectOptionToModel(model, DictCode.币种);
        this.setSelectOptionToModel(model, DictCode.交易对手类型);
        Map<String, Object> params = new HashMap<>();
        params.put("tradeId", tradeId);
        InvestTradeDto entity = currentDeposit001Service.selectByTradeId(params);
        model.addAttribute("entity", entity);
        return "/valuation/trademanage/investtradequery/currentdeposit/CurrentDeposit002";
    }
    
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception {
    	String datas = this.getParam("expDatas");
    	String id = this.getParam("expId");
    	List<InvestTradeDto> listdto;

    	if(StringUtil.isNotEmpty(datas)){
            listdto = JsonUtils.json2List(datas, InvestTradeDto.class);
    	}else{
    		listdto = investTradeQuery001Service.findList(RequestUtil.getPageParam());
    	}
    	return buildFile(fileMap.get(id),listdto);
    }
 
    public ResponseEntity<byte[]> buildFile(String modlePath,List<InvestTradeDto> listdto) throws Exception{
        File outFile = investTradeQuery001Service.makeFile(modlePath,listdto);
        return ExportUtil.export(
            this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                                 + DateUtil.format(new Date())
                                 + SystemGlobalConstant.CLOSE_PARENTHESIS
                                 + SystemGlobalConstant.POINT
                                 + FileNameUtil.getFileExtName(outFile),
            outFile, true);
    }
}
