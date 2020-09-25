package com.joyintech.tams.invest.trade.primarybiddingmanage.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;

import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.trade.primarybiddingmanage.dto.PrimaryBiddingManageDto;
import com.joyintech.tams.invest.trade.primarybiddingmanage.dto.PrimaryBiddingRevokeDto;
import com.joyintech.tams.invest.trade.primarybiddingmanage.dto.TrdBiddingManage003Dto;
import com.joyintech.tams.invest.trade.primarybiddingmanage.service.PrimaryBiddingManage001Service;
import com.joyintech.tams.invest.trade.primarybiddingmanage.service.PrimaryBiddingManage002Service;
import com.joyintech.tams.invest.trade.primarybiddingmanage.service.PrimaryBiddingManage003Service;
import com.joyintech.tams.invest.trade.primarybiddingmanage.validator.PrimaryBiddingManage003Validator;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author sjj
 * @version 1.0 2019年05月07日
 * @see PrimaryBiddingManage001Controller
 * @since 1.0
 */

@Controller
@PageId("PrimaryBiddingManage001")
@RequestMapping("/invest/trade/primarybiddingmanage/PrimaryBiddingManage001")
public class PrimaryBiddingManage001Controller extends BaseController {
	
	@Autowired
    PrimaryBiddingManage001Service primaryBiddingManage001Service;
	
	@Autowired
    PrimaryBiddingManage002Service primaryBiddingManage002Service;
    @Autowired
    PrimaryBiddingManage003Service primaryBiddingManage003Service;
    @Autowired
    PrimaryBiddingManage003Validator primaryBiddingManage003Validator;
    @Autowired(required = false)
    private MstWorkflowCommonService mstWorkflowCommonService;
    @Override
    public String init(Model model) {
    	this.setSelectOptionToModel(model, BussinessDictCode.确认状态,BussinessDictCode.交易流程状态, DictCode.截标状态);
    	model.addAttribute("confirmstatus01", BussinessDictCodeList.确认状态_未确认.getValue());
    	model.addAttribute("confirmstatus02", BussinessDictCodeList.确认状态_已确认.getValue());
    	model.addAttribute("confirmstatus03", BussinessDictCodeList.确认状态_未中标.getValue());
    	model.addAttribute("confirmstatus04", BussinessDictCodeList.确认状态_已中标.getValue());
    	model.addAttribute("isCloseBid", CacheUtil.getSysParam(BussinessConstant.IS_CLOSE_BID)==null?"02": CacheUtil.getSysParam(BussinessConstant.IS_CLOSE_BID));
        return super.init(model);
    }
    
    /**
     * 查询
     *
     * @return Page<PrimaryBiddingManageDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<PrimaryBiddingManageDto> findPage() throws Exception {
        return primaryBiddingManage001Service.findPage(RequestUtil.getPageParam());
    }
    /**
     * 截标
     *
     * @return Page<PrimaryBiddingManageDto>泛型的page实体类
     */
    @RequestMapping("closeOrCancelBid")
    @ResponseBody
    public AjaxResult closeOrCancelBid(@RequestBody PrimaryBiddingManageDto inputDto) {
        String msg = primaryBiddingManage001Service.closeOrCancelBid(inputDto);
        return new AjaxResult(msg);
    }
    /**
     * 截标check
     *
     * @return Page<PrimaryBiddingManageDto>泛型的page实体类
     */
    @RequestMapping("closeBidCheck")
    @ResponseBody
    public AjaxResult closeBidCheck(@RequestBody PrimaryBiddingManageDto inputDto) {
        AjaxResult aj = new AjaxResult();
        aj.setData(getMessage("BUSS_COM_C_0032",getMessage("PrimaryBiddingManage001.bidClose")));
        if(mstWorkflowCommonService != null){
            WflValidInputParameter wflParam = new WflValidInputParameter();
            wflParam.setPageId("EtrBondFirstLBidding002");
            wflParam.setBusinessDataPk2(inputDto.getFinprodMarketId());
            if(mstWorkflowCommonService.isInWflProcess(wflParam)){
                aj.setData(getMessage("TRD_PRIMARY_BIDDING_008"));
            }
        }
        return aj;
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
        return primaryBiddingManage001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }

    /**
     * 画面点击保存按钮时check
     *
     * @param dto 实体类
     * @param result
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("checkRevoke")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) PrimaryBiddingRevokeDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 画面点击保存按钮时check
     *
     * @param dto 实体类
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("checkRevoke003")
    public AjaxResult checkRevoke003(@RequestBody TrdBiddingManage003Dto dto) {
        AjaxResult ajaxResult = new AjaxResult();
        List<ValidMessage> valid = primaryBiddingManage003Validator.valid(dto);
        ajaxResult.setErrorList(valid);
        return ajaxResult;
    }
    /**
     * 撤销
     * @param inputDto 画面输入参数dto
     * @return 撤销结果
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("doRevoke")
    @ResponseBody
    AjaxResult doRevoke(@RequestBody PrimaryBiddingManageDto inputDto)
        throws Exception {

        ProcessParameter<PrimaryBiddingManageDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<PrimaryBiddingManageDto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        if(StringUtil.equals(inputDto.getTenderconfirmstatus(),BussinessDictCodeList.确认状态_已确认.getValue())){
        	processParameter.setPageId("PrimaryBiddingManage002");
        	workFlowParameter.setPageId("PrimaryBiddingManage002");
        }else{
        	processParameter.setPageId("PrimaryBiddingManage003");
        	workFlowParameter.setPageId("PrimaryBiddingManage003");
        }
        processParameter.setOperationType(OperationType.REVOKE);

//        workFlowParameter.setPageId("PrimaryBiddingManage002");
        workFlowParameter.setElementId(this.getParam("elementId"));
        workFlowParameter.setBusinessCode((entity) -> entity.getBidId());
        workFlowParameter.setBusinessType(BussinessDictCodeList.共同业务类型_中标结果确认.getValue());
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(
                getMessage("PrimaryBiddingManage002.bidid") + GlobalConstant.COLON + entity.getBidId(),
                getMessage("PrimaryBiddingManage002.finprodid") + GlobalConstant.COLON + entity.getFinprodId()));
        String msg = primaryBiddingManage002Service.start(processParameter, inputDto);

        return new AjaxResult(msg);
    }

    /**
     * 撤销
     * @param inputDto 画面输入参数dto
     * @return 撤销结果
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("doRevoke003")
    @ResponseBody
    AjaxResult doRevoke003(@RequestBody TrdBiddingManage003Dto inputDto)
        throws Exception {
        ProcessParameter<TrdBiddingManage003Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdBiddingManage003Dto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        processParameter.setPageId("PrimaryBiddingManage003");
        workFlowParameter.setPageId("PrimaryBiddingManage003");
        processParameter.setOperationType(OperationType.REVOKE);
//        workFlowParameter.setPageId("PrimaryBiddingManage002");
        workFlowParameter.setElementId(this.getParam("elementId"));
        workFlowParameter.setBusinessCode((entity) -> entity.getBidId());
        workFlowParameter.setBusinessType(BussinessDictCodeList.共同业务类型_中标结果维护.getValue());
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(
            getMessage("PrimaryBiddingManage002.bidid") + GlobalConstant.COLON + entity.getBidId(),
            getMessage("PrimaryBiddingManage002.finprodid") + GlobalConstant.COLON + entity.getFinprodId()));
        String msg = primaryBiddingManage003Service.start(processParameter, inputDto);

        return new AjaxResult(msg);
    }
}
