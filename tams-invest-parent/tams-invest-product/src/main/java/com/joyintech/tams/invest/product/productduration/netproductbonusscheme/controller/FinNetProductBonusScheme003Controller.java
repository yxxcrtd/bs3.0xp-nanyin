package com.joyintech.tams.invest.product.productduration.netproductbonusscheme.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.productduration.netproductbonusscheme.dto.FinNetProductBonusSchemeDto;
import com.joyintech.tams.invest.product.productduration.netproductbonusscheme.service.FinNetProductBonusSchemeService;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 净值型分红方案登记controller
 *
 * @author liuwei
 * @version 1.0 2018年9月7日
 * @see FinNetProductBonusScheme003Controller
 * @since 1.0
 */
@Controller
@PageId("FinNetProductBonusScheme003")
@RequestMapping("/invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme003")
public class FinNetProductBonusScheme003Controller extends BaseController {
    /**
     * 净值型分红方案service
     */
    @Autowired(required=false)
    private FinNetProductBonusSchemeService finNetProductBonusSchemeService;

    /**
     * 平台日期组件接口
     */
    @Autowired(required=false)
    private IPlatformDate iPlatformDate;

    /**
     * 与日期有关日期组件
     */
    @Autowired(required=false)
    private DateCalService dateCalService;

//    /**
//     * 业务编号共通组件
//     */
//    @Autowired(required=false)
//    private BaseGetBusinessId baseGetBussinessId;
    /**
     * 业务标号生成共通
     */
    @Autowired(required=false)
    private BaseGetBusinessId baseGetBusinessId;
    /**
     * 净值型分红方案登记界面的初始化
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.分红方案, DictCode.币种,DictCode.是否);
        String tradeId = getParam("tradeId");
        String bonusPlanId = this.getParam("bonusPlanId");
        String workflowId = this.getParam("workflowId");
        Date date = iPlatformDate.getSysDate();
        String iPlatformDateStr = iPlatformDate.getSysDateStr();
        String bonuScalcFlag = DictCodeList.分红方案_按单位分红.getValue();
        if (!StringUtil.isNotEmpty(tradeId) && WorkFlowUtil.isNotFromToDoList()) {
        	FinNetProductBonusSchemeDto finNetProductBonusSchemeInfo = new FinNetProductBonusSchemeDto();
            //finNetProductBonusSchemeInfo.setTradeId(bonusPlanId);
            finNetProductBonusSchemeInfo.setBonusPlanId(bonusPlanId);
            //finNetProductBonusSchemeInfo.setDealType("bonusScheRegister");
            ProcessMark processMark;
			try {				
				processMark = WorkflowContext.getProcessMarkService().getParameterByAnnotation(finNetProductBonusSchemeInfo);
				processMark.setPageId("FinNetProductBonusScheme002");
				finNetProductBonusSchemeInfo = finNetProductBonusSchemeService.getBussinessEntityByPk(processMark);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String operateType = getParam("operateType");
            finNetProductBonusSchemeInfo.setOperateType(operateType);
            model.addAttribute("entity", finNetProductBonusSchemeInfo);
        } 
        else if (StringUtil.isNotEmpty(tradeId) && WorkFlowUtil.isNotFromToDoList()) {
        	//根据交易id带出红利再投要素
//        	Map<String,Object> map = new HashMap<>();
//        	map.put("tradeId", tradeId);
//        	map.put("ps", "C8");
//            FinNetProductBonusSchemeDto finNetProductBonusSchemeInfo = finNetProductBonusSchemeService.selectDetailInfo(map);
        	FinNetProductBonusSchemeDto finNetProductBonusSchemeInfo = new FinNetProductBonusSchemeDto();
            finNetProductBonusSchemeInfo.setTradeId(tradeId);
            ProcessMark processMark;
			try {				
				processMark = WorkflowContext.getProcessMarkService().getParameterByAnnotation(finNetProductBonusSchemeInfo);
				processMark.setPageId("FinNetProductBonusScheme003");
				finNetProductBonusSchemeInfo = finNetProductBonusSchemeService.getBussinessEntityByPk(processMark);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String operateType = getParam("operateType");
            finNetProductBonusSchemeInfo.setOperateType(operateType);
            model.addAttribute("isFromToDoList", "N");
            model.addAttribute("entity", finNetProductBonusSchemeInfo);
        } 
        else if (!WorkFlowUtil.isNotFromToDoList()&&StringUtil.isNotBlank(workflowId)) {
            // 根据工作流与留痕处理提供的共通方法获取业务实体
            FinNetProductBonusSchemeDto finNetProductBonusSchemeInfo = finNetProductBonusSchemeService.getBussinessEntityByWorkflowId(
                    workflowId);
            // 设置到model中
            model.addAttribute("entity", finNetProductBonusSchemeInfo);
            model.addAttribute("isFromToDoList", "Y");
        }
        model.addAttribute("iPlatformDateStr",iPlatformDateStr);
        model.addAttribute("iPlatformDate",DateUtil.format(date));
        model.addAttribute("iPlatformDatee",DateUtil.format(DateUtil.addDays(date,1)));
        model.addAttribute("bonuScalcFlag",bonuScalcFlag);
        return super.init(model);
    }

    /**
     * 保存按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) FinNetProductBonusSchemeDto dto, BindingResult result) {
        if(result.getAllErrors().isEmpty() && 
            (dto.getOperateType() != null && !StringUtil.equals("", dto.getOperateType()))) {
            List<JoyinMessage> joyinMessages = new ArrayList<>();
//            if(dto.getActTotalAmt()==null){
//                JoyinMessage ms = new JoyinMessage(getMessage("FIN_PROD_C_0081"), MessageType.错误消息);
//                joyinMessages.add(ms);
//            }
            AjaxResult wr = commonCheck(result, dto.getOperateType());
            wr.setJoyinMessages(joyinMessages);
            return wr;
        }
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 保存操作
     *
     * @param dto dto
     * @param br BindingResult
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody FinNetProductBonusSchemeDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
        	BussinessCodeParameter parameter = new BussinessCodeParameter();
            parameter.setCommonBusinessType(DictCodeList.共通业务类型_净值型产品分红方案管理);
            String busiCode = baseGetBusinessId.buildBussinessCode(parameter);
            dto.setTradeId(busiCode);
        }
        ProcessParameter<FinNetProductBonusSchemeDto> processParameter = new ProcessParameter<FinNetProductBonusSchemeDto>();
        WorkFlowParameter<FinNetProductBonusSchemeDto> params = new WorkFlowParameter<FinNetProductBonusSchemeDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getTradeId());
        params.setBusinessType(DictCodeList.共通业务类型_净值型产品分红方案管理.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> "产品代码" + "(" + dto.getFinprodMarketId() + ")-" 
                + "分红方案编号" + "(" + dto.getBonusPlanId() + ")-分红金额确认");
        
        processParameter.setWorkFlowParameter(params);
        String msg = finNetProductBonusSchemeService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    @ResponseBody
    @RequestMapping("doRevoke")
    public AjaxResult doRevoke(@RequestBody FinNetProductBonusSchemeDto dto, BindingResult br) throws Exception{
    	if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<FinNetProductBonusSchemeDto> processParameter = new ProcessParameter<FinNetProductBonusSchemeDto>();
        WorkFlowParameter<FinNetProductBonusSchemeDto> params = new WorkFlowParameter<FinNetProductBonusSchemeDto>();
        
        FinNetProductBonusSchemeDto finNetProductBonusSchemeInfo = new FinNetProductBonusSchemeDto();
        finNetProductBonusSchemeInfo.setTradeId(dto.getTradeId());
        ProcessMark processMark;
		try {				
			processMark = WorkflowContext.getProcessMarkService().getParameterByAnnotation(finNetProductBonusSchemeInfo);
			processMark.setPageId("FinNetProductBonusScheme003");
			finNetProductBonusSchemeInfo = finNetProductBonusSchemeService.getBussinessEntityByPk(processMark);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getTradeId());
        params.setBusinessType(DictCodeList.共通业务类型_净值型产品分红方案管理.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> "产品代码" + "(" + dto.getFinprodMarketId() + ")-" 
                + "分红方案编号" + "(" + dto.getBonusPlanId() + ")-分红金额确认交易撤销");
        processParameter.setPageId(this.getPageId());
        processParameter.setOperationType(OperationType.REVOKE);
        finNetProductBonusSchemeInfo.setDealType("bonusAmtConfirm");
        finNetProductBonusSchemeInfo.setOperateType("delete");
        processParameter.setWorkFlowParameter(params);
        String msg = finNetProductBonusSchemeService.start(processParameter, finNetProductBonusSchemeInfo);
        return new AjaxResult(msg);
    }
}
