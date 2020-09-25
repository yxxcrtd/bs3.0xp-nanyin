package com.joyintech.tams.invest.trade.primarybiddingmanage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.trade.primarybiddingmanage.dto.PrimaryBiddingPortfolioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.trade.common.dto.EtrInvestInfoDto;
import com.joyintech.tams.invest.trade.common.service.TradeCommonService;
import com.joyintech.tams.invest.trade.primarybiddingmanage.dto.PrimaryBiddingManageDto;
import com.joyintech.tams.invest.trade.primarybiddingmanage.service.PrimaryBiddingManage002Service;
import com.joyintech.tams.invest.trade.primarybiddingmanage.validator.PrimaryBiddingManage002Validator;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

@Controller
@PageId("PrimaryBiddingManage002")
@RequestMapping("/invest/trade/primarybiddingmanage/PrimaryBiddingManage002")
public class PrimaryBiddingManage002Controller extends BaseController {

    @Autowired
    PrimaryBiddingManage002Service primaryBiddingManage002Service;

    @Autowired
    private TradeCommonService tradeCommonService;

    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;

    @Autowired
    private PrimaryBiddingManage002Validator primaryBiddingManage002Validator;

    @Override
    public String init(Model model) {
        super.setSelectOptionToModel(model, BussinessDictCode.交易场所, DictCode.调整策略);
        Map<String, Object> map = RequestUtil.getPageParam();
        PrimaryBiddingManageDto entity = null;
        String workflowId = (String)map.get(GlobalConstant.WORKFLOW_ID);
        if (StringUtil.isNotEmpty(workflowId)) {
            entity = primaryBiddingManage002Service.getBussinessEntityByWorkflowId(workflowId);
        }
        else {
            entity = primaryBiddingManage002Service.getMyEntity(map);
            // 人员信息
            EtrInvestInfoDto etrInvestInfoDto = tradeCommonService.findEtrInfo(
                entity.getPrimaryBiddingPortfolioDtolist().get(0).getEntrustId());
            if (etrInvestInfoDto != null) {
                TrdInvestPersonInfo002Dto info002Dto = new TrdInvestPersonInfo002Dto();
                info002Dto.setCounterId(etrInvestInfoDto.getEtrEntrust().getCounterId());
                info002Dto.setCounterTrader(etrInvestInfoDto.getEtrEntrust().getCounterTrader());
                info002Dto.setContactInfo(etrInvestInfoDto.getEtrEntrust().getContactInfo());
                info002Dto.setIsOutTrade(etrInvestInfoDto.getEtrEntrust().getIsOutTrade());
                info002Dto.setTrader(LoginUserUtil.getUserName());
                info002Dto.setTraderDept(LoginUserUtil.getDeptCode());
                entity.setTrdInvestPersonInfo002Dto(info002Dto);
            }
        }
        if (entity.getTrdInvestPersonInfo002Dto() != null) {
            model.addAttribute("trdInvestPersonInfo002Dto", JsonUtils.object2Json(entity.getTrdInvestPersonInfo002Dto()));
        }
        model.addAttribute(GlobalConstant.ENTITY, entity);
        return super.init(model);
    }

    /**
     * 调整投标量
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("adjustAmtBid")
    public PrimaryBiddingManageDto adjustAmtBid(@RequestBody PrimaryBiddingManageDto inputDto){
        return primaryBiddingManage002Service.adjustAmtBid(inputDto);
    }

    /**
     * 获取确认撤销流程参数
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping("getRevokeParam")
    public PrimaryBiddingManageDto getRevokeParam(@RequestBody PrimaryBiddingManageDto inputDto) {
        PrimaryBiddingManageDto entity = null;
        Map<String, Object> map = new HashMap<>();
        map.put("finprodId", inputDto.getFinprodId());
        map.put("counterId", inputDto.getCounterId());
        map.put("allBidPrinAmt", inputDto.getAllBidPrinAmt());
        map.put("bidId", inputDto.getBidId());
        map.put("entrustId", inputDto.getEntrustId());
        entity = primaryBiddingManage002Service.getMyEntity(map);
        entity.setBidId(inputDto.getBidId());
        // 人员信息
        EtrInvestInfoDto etrInvestInfoDto = tradeCommonService.findEtrInfo(
            entity.getPrimaryBiddingPortfolioDtolist().get(0).getEntrustId());
        if (etrInvestInfoDto != null) {
            TrdInvestPersonInfo002Dto info002Dto = new TrdInvestPersonInfo002Dto();
            info002Dto.setCounterId(etrInvestInfoDto.getEtrEntrust().getCounterId());
            info002Dto.setCounterTrader(etrInvestInfoDto.getEtrEntrust().getCounterTrader());
            info002Dto.setContactInfo(etrInvestInfoDto.getEtrEntrust().getContactInfo());
            info002Dto.setIsOutTrade(etrInvestInfoDto.getEtrEntrust().getIsOutTrade());
            info002Dto.setTrader(LoginUserUtil.getUserName());
            info002Dto.setTraderDept(LoginUserUtil.getDeptCode());
            entity.setTrdInvestPersonInfo002Dto(info002Dto);
        }
        return entity;
    }

    /**
     * 画面点击保存按钮时check
     * 
     * @param dto 实体类
     * @param result
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) PrimaryBiddingManageDto dto,
                            BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 画面点击保存按钮时check
     *
     * @param dto 实体类
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("checkSure")
    public AjaxResult checkSure(@RequestBody PrimaryBiddingManageDto dto) {
        AjaxResult ajaxResult = new AjaxResult();
        List<ValidMessage> valid = primaryBiddingManage002Validator.checkSure(dto);
        ajaxResult.setErrorList(valid);
        return ajaxResult;

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
    AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) PrimaryBiddingManageDto dto)
        throws Exception {

        // 投标编码自动生成：YJTB+YYYYMMDD+8位自增序数
        if (StringUtil.isBlank(dto.getBidId())) {
            if (baseGetBusinessId != null) {
                BussinessCodeParameter codeParameter = new BussinessCodeParameter();
                codeParameter.setSeqNo("8888");
                dto.setBidId(baseGetBusinessId.buildBussinessCode(codeParameter));
            }
            else {
                dto.setBidId(IDGenerator.generate());
            }
        }

        ProcessParameter<PrimaryBiddingManageDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<PrimaryBiddingManageDto> workFlowParameter = new WorkFlowParameter<>();
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
        workFlowParameter.setBusinessCode((entity) -> entity.getBidId());
        workFlowParameter.setBusinessType(BussinessDictCodeList.共同业务类型_中标结果确认.getValue());
        String counterName = primaryBiddingManage002Service.getCounterName(dto.getTrdInvestPersonInfo002Dto().getCounterId());
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(
            getMessage("PrimaryBiddingManage002.bidid") + GlobalConstant.COLON + entity.getBidId(),
            getMessage("PrimaryBiddingManage002.finprodid") + GlobalConstant.COLON + entity.getFinprodId(), getMessage(
                "PrimaryBiddingManage001.counterParty") + GlobalConstant.COLON + counterName));

        String msg = primaryBiddingManage002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
