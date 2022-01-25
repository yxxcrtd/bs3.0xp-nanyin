/*
 * 文件名：TrdRepoTrade002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年12月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.cfets.trdquery.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.cfets.trdquery.dto.CfetsTrdRepoTrade002Dto;
import com.joyintech.tams.cfets.trdquery.service.CfetsTrdRepoTrade002Service;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.riskrds.dto.InterfRiskRdsDto;
import com.joyintech.tams.component.business.riskrds.service.InterfRiskRdsService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import com.joyintech.tams.invest.common.dto.cstp.TrdRepoTradeCSTPDto;
import com.joyintech.tams.invest.common.pagemenusplit.PageMenuSplitUtil;
import com.joyintech.tams.invest.trade.common.aop.TradeRdsCommonAop;
import com.joyintech.tams.invest.trade.common.controller.TrdCommonController;
import com.joyintech.tams.invest.trade.common.service.TradeCommonService;
import com.joyintech.tams.invest.trade.trdrepotrade.dto.TrdRepoTrade002Dto;
import com.joyintech.tams.invest.trade.trdrepotrade.service.TrdRepoTrade002Service;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * 回购投资交易新建
 * 〈功能详细描述〉
 *
 * @author 裴家俊
 * @version 1.0 2018年12月18日
 * @see CfetsTrdRepoTrade002Controller
 * @since 1.0
 */
@PageId("CfetsTrdRepoTrade002")
@RequestMapping("/cfets/trdquery/CfetsTrdRepoTrade002")
@Controller
public class CfetsTrdRepoTrade002Controller extends BaseController {
	@Autowired
	private TradeCommonService tradeCommonService;
    /**
     * trdLendInvestTrade002Service
     */
    @Autowired
    private CfetsTrdRepoTrade002Service cfetsTrdRepoTrade002Service;

    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;
    
    /**
     * DateCalService
     */
    @Autowired
    private DateCalService dateCalService;

    @Autowired(required = false)
    private InterfRiskRdsService<InterfRiskRdsDto> interfRiskRdsService;
    
    @Autowired
    private TrdCommonController trdCommonController;
    

    @Override
    public String init(Model model) {
        // 向model添加下拉选项列表，及相关下拉选项常量
        this.setSelectOptions(model);
        String repoamt = this.getParam("repoamt");
        String tradeId = this.getParam("tradeId");
        String workflowStatus = this.getParam("workflowStatus");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String isCstp = this.getParam("isCstp");
        String isCstpSure = this.getParam("isCstpSure");
        String isSubmit = this.getParam("isSubmit");
        String tranRecord = this.getParam("tranRecord");



        // add by etliu 20210501 银行间场内场外拆分 start
        model.addAttribute("parentPageId", this.getParam("parentPageId"));
        PageMenuSplitUtil.handlePageSelectDictList(model, DictCode.交易场所, this.getParam("parentPageId"));
        PageMenuSplitUtil.handlePageSelectDictList(model, DictCode.回购类型, this.getParam("parentPageId"));
        // add by etliu 20210501 银行间场内场外拆分 end

        CfetsTrdRepoTrade002Dto dto = new CfetsTrdRepoTrade002Dto();

        if (StringUtil.equals(workflowStatus, DictCodeListExt1.交易流程状态_流程中.getValue())
                || (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotBlank(workflowId))) {
            dto = cfetsTrdRepoTrade002Service.getBussinessEntityByWorkflowId(
                    workflowId);
            dto.setRate(ArithmeticUtil.multiply(dto.getRate(),new BigDecimal(100)));
            model.addAttribute("riskTrialRetDto", StringUtil.isEmptyList(dto.getRiskTrialRetDto()) ? null : JsonUtils.object2Json(dto.getRiskTrialRetDto()));
            model.addAttribute("workflowId", workflowId);
            if (StringUtil.isBlank(dto.getTradeId())) {
                // 根据工作流代码查询交易编号
                dto.setTradeId(cfetsTrdRepoTrade002Service.selectTradeIdByWorkflowId(workflowId));
            }
            model.addAttribute("entity_BussCombDistribtList",  JsonUtils.object2Json(dto.getCfetsBussCombDistribtList()));
            model.addAttribute("entity_CfetsPledgeList",  JsonUtils.object2Json(dto.getCfetsPledgeInfoList()));

            // add by yeming 20210914 银行间场内场外拆分 start
            model.addAttribute("parentPageId", dto.getParentPageId());
            PageMenuSplitUtil.handlePageSelectDictList(model, DictCode.交易场所, dto.getParentPageId());
            PageMenuSplitUtil.handlePageSelectDictList(model, DictCode.回购类型, dto.getParentPageId());
            // add by yeming 20210914 银行间场内场外拆分 end
        }
        if (StringUtil.isNotBlank(tradeId)) {
            model.addAttribute("tradeId", tradeId);
        } else {
//        	model.addAttribute("tradeId", dto.getTradeId()==null?this.getParam("entrustId"):dto.getTradeId());
            model.addAttribute("tradeId", dto.getTradeId());
        }
        if (StringUtil.isNotBlank(workflowId)) {
            model.addAttribute("workflowId", workflowId);
        } else {
            model.addAttribute("workflowId", dto.getWorkflowId());
        }
        model.addAttribute("noBranchNoValue", CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
        //add by chenjiaxing 银行间下行交易费用匹配展示 start
        if(StringUtils.isNotBlank(this.getParam("procedureAmt")) && StringUtils.isNotBlank(this.getParam("fTradeId"))){
            dto.setProcedureAmt(new BigDecimal(this.getParam("procedureAmt")));
        }
        if(StringUtils.isNotBlank(this.getParam("settleAmt")) && StringUtils.isNotBlank(this.getParam("fTradeId"))){
            dto.setSettleAmt(new BigDecimal(this.getParam("settleAmt")));
        }
        //add by chenjiaxing 银行间下行交易费用匹配展示 end
        model.addAttribute("entity", dto);
        // 人员信息
        model.addAttribute("entity_trdInvestPersonInfo",
                dto.getTrdInvestPersonInfo() == null ? null : JsonUtils.object2Json(
                        dto.getTrdInvestPersonInfo()));
        // 添加质押券信息
        model.addAttribute("entity_bussRepoSecDto",
                dto.getBussRepoSec() == null ? null : JsonUtils.object2Json(
                        dto.getBussRepoSec()));
        // 划款信息
        if (dto != null && !StringUtil.isEmptyList(dto.getBussTrdTransferOrder())) {
            model.addAttribute("entity_bussRepFinProductInfoDto",
                    JsonUtils.object2Json(dto.getBussTrdTransferOrder().get(0)));
        }
        model.addAttribute("formTodoList", !WorkFlowUtil.isNotFromToDoList());
        Date sysDate = DateUtil.platDateFromCache();
        String formatDate = DateUtil.format(sysDate);
        model.addAttribute("formatDate",formatDate);
        if(StringUtils.isNotBlank(this.getParam("fTradeId"))){
            model.addAttribute("fTradeId",this.getParam("fTradeId"));
            //add by chenjiaxing 银行间下行交易费用匹配展示 start
            model.addAttribute("procedureAmt",this.getParam("procedureAmt"));
            model.addAttribute("settleAmtSS",this.getParam("settleAmt"));
            //add by chenjiaxing 银行间下行交易费用匹配展示 end
            model.addAttribute("repoamt",repoamt);
        }
        model.addAttribute("isCstp",isCstp);
        model.addAttribute("isCstpSure",isCstpSure);
        model.addAttribute("isSubmit",isSubmit);
        //add by lizhi 是否限制划款指令开关 2020年2月26日  start
        model.addAttribute("GEN_PAY_ORDERS_AUTO",CacheUtil.getSysParam(BussinessConstant.GEN_PAY_ORDERS_AUTO));
        //add by lizhi 是否限制划款指令开关 2020年2月26日  end
        model.addAttribute("PINAN_ASSET_TYPE_F07",DictCodeList.平安资产类型_质押式回购.getValue());
        
		model.addAttribute("REPO_TYPE_R001", DictCodeList.回购期限品种_R001.getValue());
		model.addAttribute("REPO_TYPE_R007", DictCodeList.回购期限品种_R007.getValue());
		model.addAttribute("REPO_TYPE_R014", DictCodeList.回购期限品种_R014.getValue());
		model.addAttribute("REPO_TYPE_R021", DictCodeList.回购期限品种_R021.getValue());
		model.addAttribute("REPO_TYPE_R1M", DictCodeList.回购期限品种_R1M.getValue());
		model.addAttribute("REPO_TYPE_R2M", DictCodeList.回购期限品种_R2M.getValue());
		model.addAttribute("REPO_TYPE_R3M", DictCodeList.回购期限品种_R3M.getValue());
		model.addAttribute("REPO_TYPE_R4M", DictCodeList.回购期限品种_R4M.getValue());
		model.addAttribute("REPO_TYPE_R6M", DictCodeList.回购期限品种_R6M.getValue());
		model.addAttribute("REPO_TYPE_R9M", DictCodeList.回购期限品种_R9M.getValue());
		model.addAttribute("REPO_TYPE_R1Y", DictCodeList.回购期限品种_R1Y.getValue());
		PageElementConfigDto pageElementConfigDto = new PageElementConfigDto();
        pageElementConfigDto.setPageId("BaseRiskMathComm");
        Map<String, String> map = PageElementUtil.isPageElementShow(pageElementConfigDto); // 风险试算页面
        pageElementConfigDto.setPageId(this.getPageId());
        Map<String, String> isEleShow = PageElementUtil.isPageElementShow(pageElementConfigDto);
        isEleShow.putAll(map);
        model.addAttribute("isEleShow", isEleShow);
        model.addAttribute("userId", LoginUserUtil.getUserName());
        return super.init(model);
    }

    /**
     * 向model添加下拉选项列表，及相关下拉选项常量
     *
     * @param model model
     */
    private void setSelectOptions(Model model) {
        this.setSelectOptionToModel(model, DictCode.回购拆借方向, DictCode.回购类型,DictCode.回购方式,
                DictCode.清算类型,DictCode.结算方式,
                /* 添加五级分类数据字典、zhuyongkang 2019/8/8  start*/
                DictCode.行内资产二级分类,
                DictCode.行内资产三级分类,
                DictCode.行内资产四级分类,
                DictCode.行内资产五级分类,
                /* 、zhuyongkang 2019/8/8  end*/
                DictCode.交易场所, DictCode.清算速度, DictCode.有效状态, DictCode.币种, DictCode.计息基础,
				DictCode.计息基础,DictCode.回购期限品种);

        // 交易场所_银行间
        model.addAttribute("ACCT_TYPE_A01", DictCodeListExt1.资金账户类型_产品托管户.getValue());
        // 交易场所_银行间
        model.addAttribute("TRADE_PLACE_01", DictCodeListExt1.交易场所_银行间.getValue());
        // 交易场所_上海证券交易所
        model.addAttribute("TRADE_PLACE_02", DictCodeListExt1.交易场所_上海证券交易所.getValue());
        // 交易场所_深圳证券交易所
        model.addAttribute("TRADE_PLACE_03", DictCodeListExt1.交易场所_深圳证券交易所.getValue());
        // 回购拆借方向_正回购
        model.addAttribute("BACK_LEND_DIRECTION_03", DictCodeList.回购拆借方向_正回购.getValue());
        // 回购拆借方向_逆回购
        model.addAttribute("BACK_LEND_DIRECTION_04", DictCodeList.回购拆借方向_逆回购.getValue());
        // 回购拆借方向_逆回购
        model.addAttribute("FINPROD_TYPE_TG_F02", DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue());
        // 回购拆借方向_逆回购
        model.addAttribute("FINPROD_TYPE_TG_F03", DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue());
        // 监督时分_交易监控
        model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_交易监控.getValue());
        // 回购类型_质押式协议回购
        model.addAttribute("TARGET_BACK_TYPE_H01", DictCodeList.回购类型_质押式协议回购.getValue());
        // 回购类型_质押式竞价回购
        model.addAttribute("TARGET_BACK_TYPE_H02", DictCodeList.回购类型_质押式竞价回购.getValue());
        //add by yujiaping 2020-11-25 BS-1144 交易市场选择“银行间”，回购类型为“质押式回购”start
        // 回购类型_质押式回购
        model.addAttribute("TARGET_BACK_TYPE_H04", DictCodeList.回购类型_质押式回购.getValue());
        //add by yujiaping 2020-11-25 BS-1144 交易市场选择“银行间”，回购类型为“质押式回购”end
        // 清算速度_T_0
        model.addAttribute("ACCOUNT_SPEED_01", DictCodeList.清算速度_T_0.getValue());
        // 清算速度_T_1
        model.addAttribute("ACCOUNT_SPEED_02", DictCodeList.清算速度_T_1.getValue());
        // 计息基础_A_360
        model.addAttribute("BASIS_A_360", DictCodeList.计息基础_A_360.getValue());
        // 计息基础_A_365
        model.addAttribute("BASIS_A_365", DictCodeList.计息基础_A_365.getValue());
        // 是否_否
        model.addAttribute("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        // 是否_是
        model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
        // 交易方向_买入
        model.addAttribute("PS_T01", DictCodeList.交易方向_买入.getValue());
        // 资产类型_债券质押式逆回购
        model.addAttribute("FINPROD_TYPE_F03", DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue());
        // 资产类型_债券质押式正回购
        model.addAttribute("FINPROD_TYPE_F02", DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue());
        // 清算速度_T_1
        model.addAttribute("GC001", DictCodeList.回购期限品种_GC001.getValue());
        model.addAttribute("GC002", DictCodeList.回购期限品种_GC002.getValue());
        model.addAttribute("GC003", DictCodeList.回购期限品种_GC003.getValue());
        model.addAttribute("GC004", DictCodeList.回购期限品种_GC004.getValue());
        model.addAttribute("GC007", DictCodeList.回购期限品种_GC007.getValue());
        model.addAttribute("GC014", DictCodeList.回购期限品种_GC014.getValue());
        model.addAttribute("GC028", DictCodeList.回购期限品种_GC028.getValue());
        model.addAttribute("GC091", DictCodeList.回购期限品种_GC091.getValue());
        model.addAttribute("GC182", DictCodeList.回购期限品种_GC182.getValue());
        model.addAttribute("R001", DictCodeList.回购期限品种_R001.getValue());
        model.addAttribute("R007", DictCodeList.回购期限品种_R007.getValue());
        model.addAttribute("R014", DictCodeList.回购期限品种_R014.getValue());
        model.addAttribute("R021", DictCodeList.回购期限品种_R021.getValue());
        model.addAttribute("RC001", DictCodeList.回购期限品种_RC001.getValue());
        model.addAttribute("RC003", DictCodeList.回购期限品种_RC003.getValue());
        model.addAttribute("RC007", DictCodeList.回购期限品种_RC007.getValue());
        model.addAttribute("R_001", DictCodeList.回购期限品种_R_001.getValue());
        model.addAttribute("R_002", DictCodeList.回购期限品种_R_002.getValue());
        model.addAttribute("R_003", DictCodeList.回购期限品种_R_003.getValue());
        model.addAttribute("R_004", DictCodeList.回购期限品种_R_004.getValue());
        model.addAttribute("R_007", DictCodeList.回购期限品种_R_007.getValue());
        model.addAttribute("R_014", DictCodeList.回购期限品种_R_014.getValue());
        model.addAttribute("R_028", DictCodeList.回购期限品种_R_028.getValue());
        model.addAttribute("R_091", DictCodeList.回购期限品种_R_091.getValue());
        model.addAttribute("R_182", DictCodeList.回购期限品种_R_182.getValue());
        model.addAttribute("R1M", DictCodeList.回购期限品种_R1M.getValue());
        model.addAttribute("R2M", DictCodeList.回购期限品种_R2M.getValue());
        model.addAttribute("R3M", DictCodeList.回购期限品种_R3M.getValue());
        model.addAttribute("R4M", DictCodeList.回购期限品种_R4M.getValue());
        model.addAttribute("R6M", DictCodeList.回购期限品种_R6M.getValue());
        model.addAttribute("R9M", DictCodeList.回购期限品种_R9M.getValue());
        model.addAttribute("R1Y", DictCodeList.回购期限品种_R1Y.getValue());
		model.addAttribute("SETTLESPEED_1", DictCodeList.清算速度_T_1.getValue());
		model.addAttribute("SETTLESPEED_2", DictCodeListExt2.清算速度_T_2.getValue());
		//--------add by 王国显 费用设置 2019/07/19------start--------
        // 佣金
        model.addAttribute("FEE_TYPE_05", BussinessDictCodeList.费用类型_佣金.getValue());
        // 交易手续费
        model.addAttribute("FEE_TYPE_06", BussinessDictCodeList.费用类型_交易手续费.getValue());
        // 结算手续费
        model.addAttribute("FEE_TYPE_08", BussinessDictCodeList.费用类型_结算手续费.getValue());
        //--------add by 王国显 费用设置 2019/07/19------end--------

    }

}
