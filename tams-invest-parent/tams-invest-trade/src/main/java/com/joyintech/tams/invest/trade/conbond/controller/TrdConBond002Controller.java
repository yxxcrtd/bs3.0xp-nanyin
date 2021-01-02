/*
 * 文件名：TrdConBond002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年11月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.conbond.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.component.business.riskrds.dto.InterfRiskRdsDto;
import com.joyintech.tams.component.business.riskrds.service.InterfRiskRdsService;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.component.base.entrust.BaseEtrInvestInfoService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.trade.bond.controller.TrdBondInvestTrade002Controller;
import com.joyintech.tams.invest.trade.bond.dto.TrdBondInvestTradeCSTPDto;
import com.joyintech.tams.invest.trade.bond.dto.TrdBondInvestTradeDto;
import com.joyintech.tams.invest.trade.common.service.TradeCommonService;
import com.joyintech.tams.invest.trade.conbond.dto.TrdConBondDto;
import com.joyintech.tams.invest.trade.conbond.service.TrdConBond002Service;
import com.joyintech.tams.invest.trade.conbond.validator.TrdConBondDtoValidator;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 可转债申购
 *
 * @author wangpeng
 * @version 1.0 2019年11月14日
 * @see TrdConBond002Controller
 * @since 1.0
 */
@Controller
@PageId("TrdConBond002")
@RequestMapping("/invest/trade/conbond/TrdConBond002")
public class TrdConBond002Controller extends BaseController {
	@Autowired
	private TradeCommonService tradeCommonService;
    /**
     * trdConBond002Service
     */
    @Autowired
    private TrdConBond002Service trdConBond002Service;

    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;

    @Autowired(required = false)
    private InterfRiskRdsService<InterfRiskRdsDto> interfRiskRdsService;

    @Override
    public String init(Model model) {
        setSelectOptions(model);
        TrdConBondDto dto = new TrdConBondDto();
        String tradeId = this.getParam("busiCode");
        String workflowStatus = this.getParam("workflowStatus");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (StringUtil.equals(workflowStatus, DictCodeListExt1.交易流程状态_流程中.getValue())
            || (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotBlank(workflowId))) {
            dto = trdConBond002Service.getBussinessEntityByWorkflowId(
                workflowId);
            model.addAttribute("riskTrialRetDto", JsonUtils.object2Json(dto.getRiskTrialRetDto()));
            model.addAttribute("workflowId",workflowId);
        }
        else if (StringUtil.isNotEmpty(tradeId)) {
            dto = trdConBond002Service.getTrdConBondDto(tradeId);
        }
        else {
            String entrustId = this.getParam("entrustId");
            if (StringUtil.isNotEmpty(entrustId)) {
            	dto=trdConBond002Service.getEntrustInfo(entrustId);
                dto.setEntrustId(entrustId);
                if(StringUtil.isNotBlank(dto.getRemark())&&StringUtil.contains(dto.getRemark(),"退回原因")){
                    dto.setRemark("");
                }
            }
            dto.setPurchDate(CacheUtil.getAppCache(GlobalConstant.NOW_SYS_DATE, Date.class));
        }
        dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        if (StringUtil.isNotBlank(tradeId)) {
            model.addAttribute("tradeId", tradeId);
        }
//        else {
//            //zhujun BSBS-441@2019-05-20 兼容指令接收交易录单附件不显示问题
//            model.addAttribute("busiCode", dto.getBusiCode()==null?this.getParam("entrustId"):dto.getBusiCode());
//        }
        if (StringUtil.isNotBlank(workflowId)) {
            model.addAttribute("workflowId", workflowId);
        }
        else {
            model.addAttribute("workflowId", dto.getWorkflowId());
        }
        Date sysDate = DateUtil.platDateFromCache();
        String formatDate = DateUtil.format(sysDate);
        model.addAttribute("formatDate",formatDate);
        model.addAttribute("entity", dto);
        model.addAttribute("entity_trdInvestPersonInfo",
            dto.getTrdInvestPersonInfo() == null ? null : JsonUtils.object2Json(
                dto.getTrdInvestPersonInfo()));
        // 划款信息
        if (dto != null && !StringUtil.isEmptyList(dto.getBussTrdTransferOrder())) {
            model.addAttribute("entity_bussRepFinProductInfoDto",
                JsonUtils.object2Json(dto.getBussTrdTransferOrder().get(0)));
        }
        model.addAttribute("FINPROD_TYPE_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_交易监控.getValue());
        //add by lizhi 是否限制划款指令开关 2020年2月26日  start
        model.addAttribute("GEN_PAY_ORDERS_AUTO",CacheUtil.getSysParam(BussinessConstant.GEN_PAY_ORDERS_AUTO));
        //add by lizhi 是否限制划款指令开关 2020年2月26日  end
        PageElementConfigDto pageElementConfigDto = new PageElementConfigDto();
        pageElementConfigDto.setPageId("BaseRiskMathComm");
        Map<String, String> map = PageElementUtil.isPageElementShow(pageElementConfigDto); // 风险试算页面
        pageElementConfigDto.setPageId(this.getPageId());
        Map<String, String> isEleShow = PageElementUtil.isPageElementShow(pageElementConfigDto);
        isEleShow.putAll(map);
        model.addAttribute("isEleShow", isEleShow);
        return super.init(model);
    }

    private void setSelectOptions(Model model) {
        this.setSelectOptionToModel(model, DictCode.交易方向, DictCode.交易场所,
             DictCode.交易平台, DictCode.币种,
            DictCode.投资目的, DictCode.交易市场,  DictCode.是否,
            DictCode.申购方式,DictCode.申购类型,
            DictCode.WIND一级分类_含债券一级分类等);
        model.addAttribute("PURCH_MODLE_01", DictCodeListExt2.申购方式_网上申购.getValue());
        model.addAttribute("PURCH_MODLE_02", DictCodeListExt2.申购方式_线下申购.getValue());
        model.addAttribute("PURCH_TYPE_01", DictCodeListExt2.申购类型_优先配售.getValue());
        model.addAttribute("PURCH_TYPE_02", DictCodeListExt2.申购类型_普通申购.getValue());
        model.addAttribute("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
        model.addAttribute("COUNTER_TYPE_01", DictCodeListExt1.交易对手类型_市场机构.getValue());
    }

    /**
     * 查询委托信息
     *
     * @return 委托信息
     * @see
     * @since 1.0
     */
//    @ResponseBody
//    @RequestMapping("getEntrustInfo")
//    public BaseEtrInvestInfoDto getEntrustInfo(@RequestBody TrdConBondDto dto) {
//        BigDecimal wflPrinAmt = tradeCommonService.getWflPrinAmt(dto.getEntrustId(), DictCodeList.平安资产类型_债券.getValue());
//        BaseEtrInvestInfoDto baseDto=trdConBond002Service.getEntrustInfo(dto.getEntrustId());
//        baseDto.getEtrEntrust().setEntrustNum(ArithmeticUtil.subtract(baseDto.getEtrEntrust().getEntrustNum(), ArithmeticUtil.multiply(wflPrinAmt,new BigDecimal(100))));
//        return baseDto;
//    }

    /**
     * 查询证券托管户：联想框文本框
     *
     * @param params 画面参数
     * @return 证券托管户：联想框文本框
     * @see
     * @since 1.0
     */
    @RequestMapping("getSecManageAcctId")
    @ResponseBody
    public List<SelectOptionDto> getSecManageAcctId(@RequestParam Map<String, Object> params) {
        return trdConBond002Service.getSecManageAcctId(params);
    }

    /**
     * 资产代码：下拉框
     *
     * @param params 画面参数
     * @return 资产代码：下拉框
     * @see
     * @since 1.0
     */
    @RequestMapping("getFinprodId")
    @ResponseBody
    public Page<SelectOptionDto> getFinprodId(@RequestParam Map<String, Object> params) {
        return trdConBond002Service.getFinprodId(params);
    }

    /**
     * 保存提交前的数据检查
     *
     * @param dto 画面输入参数
     * @return 检查结果
     * @see
     * @since 1.0
     */
    @RequestMapping("check")
    @ResponseBody
    public AjaxResult check(@Validated({ValidateGroupEdit.class}) @RequestBody TrdConBondDto dto, BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }

        // 确认提醒
        if (result != null &&result.getErrorCount() == 0) {
        	String confirmMsg = "";
            String changeLineMark = "<br/>";
            //如果头寸不足时
            if (StringUtil.isNotEmpty(dto.getProAvailableCash())
                    && StringUtil.isNotEmpty(dto.getTradeAmt())
                    && dto.getProAvailableCash().compareTo(dto.getTradeAmt()) < 0){
            	 confirmMsg += "组合可用现金不足,当前最多可用金额为:<br/>" +
            			 dto.getProAvailableCash() +
                         "请确认是否继续？";
            }
            if(StringUtil.isNotEmpty(dto.getSecManageAcctId())&& StringUtil.equals(dto.getPurchType(), DictCodeListExt2.申购类型_优先配售.getValue())
            		&& StringUtil.equals(dto.getPurchModle(), DictCodeListExt2.申购方式_网上申购.getValue())
            		&&StringUtil.isNotEmpty(dto.getTradeMarketCash())&& StringUtil.isNotEmpty(dto.getTradeAmt())
                    && dto.getTradeMarketCash().compareTo(dto.getTradeAmt()) < 0){
                if (StringUtil.isNotBlank(confirmMsg)){
                    confirmMsg += changeLineMark;
                }
                confirmMsg += getMessage("TRD_C_0001");
            }
        	if (StringUtil.isNotEmpty(dto.getChlId())&&StringUtil.isNotEmpty(dto.getChannelAvailableCash())
                    && StringUtil.isNotEmpty(dto.getTradeAmt())
                    && dto.getChannelAvailableCash().compareTo(dto.getTradeAmt()) < 0){
        		if (StringUtil.isNotBlank(confirmMsg)){
                    confirmMsg += changeLineMark;
                }
        		confirmMsg += getMessage("TRD_C_0004",getMessage("invest.channelId"));
        	 }
        	if(StringUtil.isNotEmpty(confirmMsg)){
        		AjaxResult wr = new AjaxResult();
            	wr.setData(confirmMsg);
            	return wr;
        	}
        }
        return commonCheck(result, dto.getOperateType());  
    }

    /**
     * 保存提交
     *
     * @param dto
     * @return 画面输入参数
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("save")
    @ResponseBody
    AjaxResult save(@RequestBody TrdConBondDto dto)
        throws Exception {
    	List<TrdConBondDto> dtoList = new ArrayList<>();
		TrdConBondDto tempDto = new TrdConBondDto();
		BeanUtil.copyProperties(tempDto, dto);
		if (baseGetBusinessId != null) {
			BussinessCodeParameter codeParameter = new BussinessCodeParameter();
			codeParameter.setCommonBusinessExtType(DictCodeListExt2.fromDictCodeList(DictCodeListExt2.共通业务类型_可转债申购交易));
			tempDto.setBusiCode(baseGetBusinessId.buildBussinessCode(codeParameter));
		}
		else {
			tempDto.setBusiCode(IDGenerator.generate());
		}
        //----------add by jjpei 20200114 核风险版本 start----------
        if(dto.getOperateType().equals(BussinessConstant.SUBMIT)&& CommonUtil.isType(CacheUtil.getSysParam("pageRedisFlag"), BussinessDictCodeListRds.是否_是)){
            AjaxResult ar=interfRiskRdsService.checkRiskRds(new InterfRiskRdsDto(), BussinessDictCodeListRds.事件类型_投资交易.getValue(),
                    BussinessDictCodeListRds.接口系统ID_实占接口.getValue(),DictCodeList.平安资产类型_债券.getValue(),
                    (en)->interfRiskRdsService.sendRiskRdsData(tempDto,en));
            if (StringUtil.isNotEmpty(ar)) {
                return ar;
            }
        }
        //----------add by jjpei 20200114 核风险版本 end----------
        ProcessParameter<TrdConBondDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdConBondDto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        processParameter.setPageId(this.getPageId());
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            processParameter.setOperationType(OperationType.NEW);
        }
        else {
            processParameter.setOperationType(OperationType.MODIFY);
        }
        workFlowParameter.setPageId(this.getPageId());
        workFlowParameter.setElementId(this.getParam("elementId"));
        workFlowParameter.setBusinessCode((entity) -> entity.getBusiCode());
        workFlowParameter.setBusinessType(DictCodeListExt2.共通业务类型_可转债申购交易.getValue());
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(
            getMessage("TrdBondInvestTrade002.tradeId") + GlobalConstant.COLON + entity.getBusiCode(),
            getMessage("TrdBondInvestTrade002.finprodName") + GlobalConstant.COLON + entity.getFinprodName(),
            getMessage("TrdBondInvestTrade002.tradeDate") + GlobalConstant.COLON + DateUtil.format(entity.getTradeDate())));
    	trdConBond002Service.prepareEntity(tempDto);
        String msg = trdConBond002Service.start(processParameter, tempDto);
        return new AjaxResult(msg);
    }
    
    /**
     * 获取T日可用现金
     *
     * @param inputDto 画面参数
     * @return 计算结果
     * @see
     * @since 1.0
     */
    @RequestMapping("getProAvailableCash")
    @ResponseBody
    public Map<String, Object> getProAvailableCash(@RequestBody Map<String, Object> params) {
        return trdConBond002Service.getProAvailableCash(params);
    }

}
