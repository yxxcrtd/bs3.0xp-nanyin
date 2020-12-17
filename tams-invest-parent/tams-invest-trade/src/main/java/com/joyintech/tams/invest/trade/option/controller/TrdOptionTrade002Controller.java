package com.joyintech.tams.invest.trade.option.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.*;
import com.joyintech.tams.component.business.riskrds.dto.InterfRiskRdsDto;
import com.joyintech.tams.component.business.riskrds.service.InterfRiskRdsService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.trade.lend.dto.TrdLendInvestTradeDto;
import com.joyintech.tams.invest.trade.option.dto.TrdOptionTrade002Dto;
import com.joyintech.tams.invest.trade.option.service.TrdOptionTrade001Service;
import com.joyintech.tams.invest.trade.option.service.TrdOptionTrade002Service;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

/**
 * 期权交易新增
 */
@Controller
@PageId("TrdOptionTrade002")
@RequestMapping("/invest/trade/option/TrdOptionTrade002")
public class TrdOptionTrade002Controller extends BaseController {
    @Autowired
    private TrdOptionTrade002Service trdOptionTrade002Service;

    @Autowired
    private InterfRiskRdsService<InterfRiskRdsDto> interfRiskRdsService;

    /**
     * 初始化界面
     * @param model
     * @return
     */
    @Override
    public String init(Model model) {
        setSelectOptions(model);
        String totleTradeId = this.getParam("totleTradeId");
        if(StringUtil.isEmpty(totleTradeId)){
            totleTradeId = this.getParam("tradeId");
        }
        String workflowStatus = this.getParam("workflowStatus");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String entrustId = this.getParam("entrustId");
        TrdOptionTrade002Dto dto = new TrdOptionTrade002Dto();

        if (StringUtil.equals(workflowStatus, DictCodeListExt1.交易流程状态_流程中.getValue())
                || (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotBlank(workflowId))) {
            dto = trdOptionTrade002Service.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("workflowId",workflowId);
            model.addAttribute("riskTrialRetDto", JsonUtils.object2Json(dto.getRiskTrialRetDto()));
            if (StringUtil.isBlank(dto.getTotleTradeId())) {
                // 根据工作流代码查询交易编号
                dto.setTotleTradeId(trdOptionTrade002Service.selectTradeIdByWorkflowId(workflowId));
            }
        }
        else if (StringUtil.isNotEmpty(totleTradeId)) {
            dto = trdOptionTrade002Service.getTrdOptionTradeDto(totleTradeId);
        }
        else if(StringUtil.isNotEmpty(entrustId)){
            dto = trdOptionTrade002Service.getEntrustInfo(entrustId);
        }

        if (StringUtil.isNotBlank(totleTradeId)) {
            model.addAttribute("totleTradeId", totleTradeId);
        }
        else {
            model.addAttribute("totleTradeId", dto.getTotleTradeId());
        }
        if (StringUtil.isNotBlank(workflowId)) {
            model.addAttribute("workflowId", workflowId);
        }
        else {
            model.addAttribute("workflowId", dto.getWorkflowId());
        }

        model.addAttribute("entity", dto);
        model.addAttribute("entity_trdInvestPersonInfo",
                dto.getTrdInvestPersonInfo() == null ? null : JsonUtils.object2Json(
                        dto.getTrdInvestPersonInfo()));
        // 划款信息
        if (dto != null && !StringUtil.isEmptyList(dto.getBussTrdTransferOrder())) {
            model.addAttribute("entity_bussRepFinProductInfoDto",
                    JsonUtils.object2Json(dto.getBussTrdTransferOrder().get(0)));
        }
        model.addAttribute("businessId",dto.getTotleTradeId());
        model.addAttribute("entrustId", entrustId);
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

    /**
     * 数据字典值
     * @param model
     */
    private void setSelectOptions(Model model) {
        this.setSelectOptionToModel(model, DictCode.交易方向, DictCode.结构类型,DictCode.期权费支付类型,DictCode.币种, DictCode.行权方式, DictCode.交易场所, DictCode.持有目的);
        model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_交易监控.getValue());
        model.addAttribute("FINPROD_TYPE_TG_F35", DictCodeListExt3.金融产品类型_投管用_期权.getValue());
        model.addAttribute("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
        model.addAttribute("PS_T01", DictCodeList.交易方向_买入.getValue());
        model.addAttribute("STRUCTURE_TYPE_01", DictCodeListExt3.结构类型_看涨.getValue());
        model.addAttribute("STRUCTURE_TYPE_02", DictCodeListExt3.结构类型_看跌.getValue());
        model.addAttribute("STRUCTURE_TYPE_03", DictCodeListExt3.结构类型_二元看涨.getValue());
        model.addAttribute("STRUCTURE_TYPE_04", DictCodeListExt3.结构类型_看涨鲨鱼鳍.getValue());
        model.addAttribute("STRUCTURE_TYPE_05", DictCodeListExt3.结构类型_双向鲨鱼鳍.getValue());
        model.addAttribute("OPT_FEE_PAY_TYPE_01", DictCodeListExt3.期权费支付类型_先付期权费.getValue());
        model.addAttribute("OPT_FEE_PAY_TYPE_02", DictCodeListExt3.期权费支付类型_后付期权费.getValue());
    }

    /**
     * 保存前校验
     * @param dto
     * @param result
     * @return
     */
    @RequestMapping("check")
    @ResponseBody
    public AjaxResult check(@Valid @RequestBody TrdOptionTrade002Dto dto, BindingResult result) {
        if(StringUtil.isNotEmpty(result) && result.getErrorCount()>0){
            AjaxResult res= new AjaxResult(result);
            return res;
        }
        // 可用现金软校验
        if (dto.getAvalibleCash() == null ) {
            AjaxResult ar = new AjaxResult();
            ar.setData(this.getMessage("TRD_C_0002"));
            return ar;
        }
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 保存
     * @param dto
     * @return
     * @throws Exception
     */
    @RequestMapping("save")
    @ResponseBody
    AjaxResult save(@RequestBody TrdOptionTrade002Dto dto)
            throws Exception {
        if (StringUtil.isBlank(dto.getTotleTradeId())) {
            dto.setTotleTradeId(trdOptionTrade002Service.gen交易编号());
        }
        if(StringUtil.isBlank(dto.getTradeId())){
            dto.setTradeId(trdOptionTrade002Service.gen交易编号());
        }
        //----------add by jjpei 20200114 核风险版本 start----------
        if(dto.getOperateType().equals(BussinessConstant.SUBMIT)&& CommonUtil.isType(CacheUtil.getSysParam("pageRedisFlag"), BussinessDictCodeListRds.是否_是)){
            AjaxResult ar=interfRiskRdsService.checkRiskRds(new InterfRiskRdsDto(), BussinessDictCodeListRds.事件类型_投资交易.getValue(),
                    BussinessDictCodeListRds.接口系统ID_实占接口.getValue(),DictCodeListExt3.平安资产类型_期权.getValue(),
                    (en)->interfRiskRdsService.sendRiskRdsData(dto,en));
            if (StringUtil.isNotEmpty(ar)) {
                return ar;
            }
        }
        //----------add by jjpei 20200114 核风险版本 end----------
        ProcessParameter<TrdOptionTrade002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdOptionTrade002Dto> workFlowParameter = new WorkFlowParameter<>();
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
        workFlowParameter.setBusinessCode(dto.getTotleTradeId());
        workFlowParameter.setBusinessType(DictCodeListExt3.共通业务类型_期权交易.getValue());
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(
                getMessage("TrdOptionTrade001.totleTradeId") + GlobalConstant.OPEN_PARENTHESIS + entity.getTotleTradeId() + GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("TrdOptionTrade001.contractType") + GlobalConstant.OPEN_PARENTHESIS + SysDataUtil.getNameFromDataDict(DictCode.结构类型, entity.getContractType()) + GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("TrdOptionTrade001.tradeDate")+ GlobalConstant.OPEN_PARENTHESIS + DateUtil.format(entity.getTradeDate()) + GlobalConstant.CLOSE_PARENTHESIS));
        String msg = trdOptionTrade002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
