package com.joyintech.tams.invest.period.trdsecoverdueduration.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdbondduration.dto.TrdBondDuration002ProcessDto;
import com.joyintech.tams.invest.period.trdbondduration.service.TrdBondDuration002Service;
import com.joyintech.tams.invest.period.trdseclendduration.dto.TrdSecLendDuration001Dto;
import com.joyintech.tams.invest.period.trdsecoverdueduration.dto.TrdSecOverdueDuration002Dto;
import com.joyintech.tams.invest.period.trdsecoverdueduration.dto.TrdSecOverdueDuration001Dto;
import com.joyintech.tams.invest.period.trdsecoverdueduration.service.TrdSecOverdueDuration002Service;
import com.joyintech.tams.invest.period.trdsecoverdueduration.service.TrdSecOverdueDuration001Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.joyintech.tams.invest.period.common.service.PeriodCommonService.change现金流类型To交易方向;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TrdSecOverdueDurationController001
 * @Description: TODO
 * @Author wangpeng
 * @Date 2019/12/4 0004
 * @Version V1.0
 **/
@Controller
@PageId("TrdSecOverdueDuration002")
@RequestMapping("/invest/period/trdsecoverdueduration/TrdSecOverdueDuration002")
public class TrdSecOverdueDuration002Controller  extends BaseController {

    @Autowired
    private TrdSecOverdueDuration002Service trdSecOverdueDuration002Service;

    @Autowired
    private BaseGetBusinessId baseGetBusinessIdImpl;

    @Autowired
    private PeriodCommonService periodCommonService;

    @Autowired
    private CommonBusinessService commonBusinessService;

    @Override
    public String init(Model model) {
    	TrdSecOverdueDuration002Dto entity = null;
         Map<String, Object> params = RequestUtil.getPageParam();
         String statusValid = (String) params.get("statusValid");
         params.put("statusValid", statusValid);
         String busiCode = this.getParam("busiCode");
         Map<String, Object> map  = new HashMap<>();
         if (RequestUtil.getParam("isFromCheck") != null && Boolean.valueOf(RequestUtil.getParam("isFromCheck"))
            && StringUtil.isNotEmpty(params.get("bizCode"))) {
            String workflowId = AllTradeCommonUtil.selectWorkflowId((String) params.get("bizCode"));
            params.put("workflowId", workflowId);
            params.put("statusValid", DictCodeListExt1.画面数据状态_流程中.getValue());
         }
         if (StringUtil.isNotEmpty(busiCode) && WorkFlowUtil.isNotFromToDoList() && DictCodeListExt1.画面数据状态_有效.getValue().equals(statusValid)) {
            entity = trdSecOverdueDuration002Service.findEffectiveData(busiCode);
            entity.setBussTrdTransferOrder(periodCommonService.selectPayOrders(busiCode));
         }else if ((!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(params.get("workflowId")))
                 || DictCodeListExt1.画面数据状态_流程中.getValue().equals(params.get("statusValid"))) {
             String workflowId = (String) params.get("workflowId");
             if (StringUtil.isNotEmpty(workflowId)) {
                 entity = trdSecOverdueDuration002Service.getBussinessEntityByWorkflowId(workflowId);
                 model.addAttribute("workflowId",workflowId);
                 if(entity != null) {
                     model.addAttribute("isTodo", commonBusinessService.isBackCheckNode(entity.getBusiCode()));
                     model.addAttribute("deal", JsonUtils.object2Json(trdSecOverdueDuration002Service.getTrdProductDeal(entity)));
                     //子组合信息不为空时，反显子组合信息
                     if(!StringUtil.isEmptyList(entity.getChildPortfolioInfo())){
                    	 for(FinAssetIntInfo fin:entity.getChildPortfolioInfo()){
                    		 if(map.containsKey(fin.getPortfolioIdInvAim())){
                    			 continue;
                    		 }
                    		 List<FinAssetIntInfo> childList = new ArrayList<>();
                    		 for(FinAssetIntInfo child:entity.getChildPortfolioInfo()){
                    			 if(StringUtil.equals(fin.getPortfolioIdInvAim(), child.getPortfolioIdInvAim())){
                    				 childList.add(child);
                    			 }
                    		 }
                        	 map.put(fin.getPortfolioIdInvAim(), childList);
                         }
                     }
                 }
             }
         }
         else {
             entity = trdSecOverdueDuration002Service.selectNewBasicdata(params);
             if (entity != null) {
                 model.addAttribute("deal", JsonUtils.object2Json(trdSecOverdueDuration002Service.getTrdProductDeal(entity)));
                 entity.setFinAssetIntInfoList(trdSecOverdueDuration002Service.findAssetIntInfo(params));
             }
         }
         if (entity != null) {
        	 if (entity.getBussTrdTransferOrder() != null && !entity.getBussTrdTransferOrder().isEmpty()) {
               model.addAttribute("entity_bussTrdTransferOrderDto",
                   JsonUtils.object2Json(entity.getBussTrdTransferOrder()));
           }
         }
         model.addAttribute("childPortfolioInfo", map);
         model.addAttribute(GlobalConstant.ENTITY, entity);
         this.setSelectOptionToModel(model, DictCode.是否,DictCode.是否,DictCode.平安划款类型,DictCode.币种,DictCode.投资目的,DictCode.平安资产类型);
         model.addAttribute("CASH_CLASS_TYPE01", DictCodeList.现金流类型_中间收息.getValue());
         model.addAttribute("CASH_CLASS_TYPE02", DictCodeList.现金流类型_中间还本.getValue());
         
         model.addAttribute("COLLECT_TYPE_01", DictCodeListExt2.收取方式_回款前扣除.getValue());
         model.addAttribute("IS_CAL_LOADRATE", CacheUtil.getSysParam(BussinessConstant.IS_CAL_LOADRATE));
         model.addAttribute("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
         model.addAttribute("FINPROD_TYPE_TG_F07", DictCodeListExt1.金融产品类型_存款.getValue());
         model.addAttribute("FINPROD_TYPE_TG_F18", DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
         model.addAttribute("FINPROD_TYPE_TG_F17", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
         model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
         model.addAttribute("formTodoList", !WorkFlowUtil.isNotFromToDoList());
         model.addAttribute("acctNoList", JsonUtils.object2Json(periodCommonService.queryAcctList(null)));
    	return super.init(model);
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
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) TrdSecOverdueDuration002Dto dto,
                            BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 保存
     *
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) TrdSecOverdueDuration002Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<TrdSecOverdueDuration002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdSecOverdueDuration002Dto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_资产存续期交易);
        dto.setBusiCode(baseGetBusinessIdImpl.buildBussinessCode(parameter));
        params.setBusinessCode(dto.getBusiCode());
        params.setBusinessType(DictCodeListExt3.共通业务类型_已到期资产存续期.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> WorkFlowUtil.concatComment(
                this.getMessage("TrdBondDuration001.finprodName") + GlobalConstant.OPEN_PARENTHESIS + entity.getFinprodName()
                + GlobalConstant.CLOSE_PARENTHESIS,
        "资产类型" + GlobalConstant.OPEN_PARENTHESIS + entity.getPaBussTypeString() + GlobalConstant.CLOSE_PARENTHESIS,
        this.getMessage(
                "TrdBondDuration001.busiCode") + GlobalConstant.OPEN_PARENTHESIS + entity.getBusiCode() + GlobalConstant.CLOSE_PARENTHESIS));
        processParameter.setWorkFlowParameter(params);
        if (BussinessConstant.SUBMIT.equals(dto.getOperateType())) {
        	dto.setBaseTotalTradeInfoDto(trdSecOverdueDuration002Service.genFinProductTradeDto2(dto));
        }
        String msg = trdSecOverdueDuration002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    /**
     * 撤销
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("revoke")
    public AjaxResult revoke(TrdSecOverdueDuration002Dto dto, BindingResult br)
        throws Exception {
        /*if (br.hasErrors()) {
            return new AjaxResult(br);
        }*/
        ProcessParameter<TrdSecOverdueDuration002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdSecOverdueDuration002Dto> params = new WorkFlowParameter<>();
        params.setPageId(RequestUtil.getParam("pageId"));
        params.setElementId(dto.getElementId());

        params.setBusinessCode(dto.getBusiCode());
        params.setBusinessType(DictCodeListExt3.共通业务类型_已到期资产存续期.getValue());

     // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> WorkFlowUtil.concatComment(
                this.getMessage("TrdBondDuration001.finprodName") + GlobalConstant.OPEN_PARENTHESIS + entity.getFinprodName()
                + GlobalConstant.CLOSE_PARENTHESIS,
        "资产类型" + GlobalConstant.OPEN_PARENTHESIS + entity.getPaBussTypeString() + GlobalConstant.CLOSE_PARENTHESIS,
        this.getMessage(
                "TrdBondDuration001.busiCode") + GlobalConstant.OPEN_PARENTHESIS + entity.getBusiCode() + GlobalConstant.CLOSE_PARENTHESIS));
        processParameter.setWorkFlowParameter(params);
        TrdSecOverdueDuration002Dto entity =  trdSecOverdueDuration002Service.findSecOverdueData(BeanUtil.object2Map(dto)); 
        entity.setRevokeReason(dto.getRevokeReason());
        entity.setBussTrdTransferOrder(periodCommonService.selectPayOrders(dto.getBusiCode()));
//        entity.setFinAssetIntInfoList(trdSecOverdueDuration002Service.findAssetIntInfo(trdBondDuration002Service.genParams(entity)));
//        entity.setBaseTotalTradeInfoDto(trdSecOverdueDuration002Service.genFinProductTradeDto2(entity));
        String msg = trdSecOverdueDuration002Service.start(processParameter, entity);
        return new AjaxResult(msg);
    }


}
