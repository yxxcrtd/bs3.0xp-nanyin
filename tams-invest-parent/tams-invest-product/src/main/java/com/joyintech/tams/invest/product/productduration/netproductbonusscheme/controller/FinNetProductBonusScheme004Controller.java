/*
 * 文件名：FinNetProductBonusScheme004Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年1月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
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
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
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
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zqz
 * @version 1.0 2019年1月8日
 * @see FinNetProductBonusScheme004Controller
 * @since 1.0
 */
@Controller
@PageId("FinNetProductBonusScheme004")
@RequestMapping("/invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme004")
public class FinNetProductBonusScheme004Controller extends BaseController {

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
        	FinNetProductBonusSchemeDto finNetProductBonusSchemeInfo = finNetProductBonusSchemeService.selectInfo(bonusPlanId);
            finNetProductBonusSchemeInfo.setActBonusDate(date);
            if(StringUtil.equals(finNetProductBonusSchemeInfo.getBonuScalcFlag(),DictCodeList.分红方案_按单位分红.getValue())
                && finNetProductBonusSchemeInfo.getUnitBonusVlu() != null
                && finNetProductBonusSchemeInfo.getTdyUnitValue() != null){

                finNetProductBonusSchemeInfo.setNetvalue(finNetProductBonusSchemeInfo.getTdyUnitValue().subtract(
                    finNetProductBonusSchemeInfo.getUnitBonusVlu()));

            }
            else{
                Map<String,Object> map = new HashMap<>();
                map.put("finprodId",finNetProductBonusSchemeInfo.getFinprodId());
                map.put("vluRefDate",DateUtil.format(finNetProductBonusSchemeInfo.getVluRefDate()));
                FinNetProductBonusSchemeDto dto = finNetProductBonusSchemeService.getTdyUnitValueAndTdyShareAmt(map);
                if(dto != null){
                    BigDecimal tdyAssetAmt = dto.getTdyAssetAmt();
                    if(tdyAssetAmt != null && finNetProductBonusSchemeInfo.getTotalAmt() != null
                        && finNetProductBonusSchemeInfo.getTdyShareAmt() != null){
                        finNetProductBonusSchemeInfo.setNetvalue((tdyAssetAmt.subtract(finNetProductBonusSchemeInfo.getTotalAmt()))
                            .divide(finNetProductBonusSchemeInfo.getTdyShareAmt(),2,RoundingMode.HALF_UP));//update zhangweiqi 2019-3-27 保留两位小数
                    }
                }
            }

            ProcessMark processMark;
            FinNetProductBonusSchemeDto temp1 =  new FinNetProductBonusSchemeDto();

            try {
                temp1.setFinprodId(finNetProductBonusSchemeInfo.getFinprodId());
                temp1.setDealType("bonusScheRegister");
                temp1.setTradeId(bonusPlanId);
                processMark = WorkflowContext.getProcessMarkService().getParameterByAnnotation(temp1);
                processMark.setPageId("FinNetProductBonusScheme002");
                temp1 = finNetProductBonusSchemeService.getBussinessEntityByPk(processMark);
            } catch (Exception e) {
                e.printStackTrace();
            }
            finNetProductBonusSchemeInfo.setTdyUnitValue(temp1.getTdyUnitValue());
            finNetProductBonusSchemeInfo.setNetvalue(temp1.getNetvalue());


            model.addAttribute("entity", finNetProductBonusSchemeInfo);
            Map<String,Object> param0 = new HashMap<String,Object>();
            param0.put("finprodId", finNetProductBonusSchemeInfo.getFinprodId());
            String bonusMode = finNetProductBonusSchemeService.queryBonusMode(param0);
            model.addAttribute("bonusMode", bonusMode);
        } else if (StringUtil.isNotEmpty(tradeId) && WorkFlowUtil.isNotFromToDoList()) {        	
        	//根据交易id带出现金分红要素
//        	Map<String,Object> map = new HashMap<>();
//        	map.put("tradeId", tradeId);
//        	map.put("ps", "C9");
//          FinNetProductBonusSchemeDto finNetProductBonusSchemeInfo = finNetProductBonusSchemeService.selectDetailInfo(map);
        	FinNetProductBonusSchemeDto finNetProductBonusSchemeInfo = new FinNetProductBonusSchemeDto();
            finNetProductBonusSchemeInfo.setTradeId(tradeId);
            
            
            ProcessMark processMark;
			try {
				
				processMark = WorkflowContext.getProcessMarkService().getParameterByAnnotation(finNetProductBonusSchemeInfo);
				processMark.setPageId("FinNetProductBonusScheme004");
				finNetProductBonusSchemeInfo = finNetProductBonusSchemeService.getBussinessEntityByPk(processMark);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(finNetProductBonusSchemeInfo.getBussTrdTransferOrder()!=null 
	                && finNetProductBonusSchemeInfo.getBussTrdTransferOrder().size()>0){
	                model.addAttribute("entity_bussRepFinProductInfoDto",
	                    JsonUtils.object2Json(finNetProductBonusSchemeInfo.getBussTrdTransferOrder().get(0)));
	       }   
//            if(StringUtil.equals(finNetProductBonusSchemeInfo.getBonuScalcFlag(),DictCodeList.分红方案_按单位分红.getValue())
//                && finNetProductBonusSchemeInfo.getUnitBonusVlu() != null
//                && finNetProductBonusSchemeInfo.getTdyUnitValue() != null){
//                finNetProductBonusSchemeInfo.setNetvalue(finNetProductBonusSchemeInfo.getTdyUnitValue().subtract(
//                    finNetProductBonusSchemeInfo.getUnitBonusVlu()));
//            }else{
//                Map<String,Object> param = new HashMap<>();
//                param.put("finprodId",finNetProductBonusSchemeInfo.getFinprodId());
//                param.put("vluRefDate",DateUtil.format(finNetProductBonusSchemeInfo.getVluRefDate()));
//                FinNetProductBonusSchemeDto dto = finNetProductBonusSchemeService.getTdyUnitValueAndTdyShareAmt(param);
//                if(dto != null){
//                    BigDecimal tdyAssetAmt = dto.getTdyAssetAmt();
//                    if(tdyAssetAmt != null && finNetProductBonusSchemeInfo.getTotalAmt() != null
//                        && finNetProductBonusSchemeInfo.getTdyShareAmt() != null){
//                        finNetProductBonusSchemeInfo.setNetvalue((tdyAssetAmt.subtract(finNetProductBonusSchemeInfo.getTotalAmt()))
//                            .divide(finNetProductBonusSchemeInfo.getTdyShareAmt()));
//                    }
//                }
//            }
			String operateType = getParam("operateType");
            finNetProductBonusSchemeInfo.setOperateType(operateType);
            model.addAttribute("entity", finNetProductBonusSchemeInfo);
            model.addAttribute("isFromToDoList", "N");
        } 
        else if (!WorkFlowUtil.isNotFromToDoList()&&StringUtil.isNotBlank(workflowId)) {
            // 根据工作流与留痕处理提供的共通方法获取业务实体
            FinNetProductBonusSchemeDto finNetProductBonusSchemeInfo = finNetProductBonusSchemeService.getBussinessEntityByWorkflowId(
                    workflowId);
            // 设置到model中
            model.addAttribute("entity", finNetProductBonusSchemeInfo);
            if(finNetProductBonusSchemeInfo.getBussTrdTransferOrder()!=null 
                && finNetProductBonusSchemeInfo.getBussTrdTransferOrder().size()>0){
                model.addAttribute("entity_bussRepFinProductInfoDto",
                    JsonUtils.object2Json(finNetProductBonusSchemeInfo.getBussTrdTransferOrder().get(0)));
            }    
            model.addAttribute("isFromToDoList", "Y");
        }
        model.addAttribute("iPlatformDateStr",iPlatformDateStr);
        model.addAttribute("iPlatformDate",DateUtil.format(date));
        model.addAttribute("iPlatformDatee",DateUtil.format(DateUtil.addDays(date,1)));
        model.addAttribute("bonuScalcFlag",bonuScalcFlag);
        model.addAttribute("workflowId",workflowId);
        model.addAttribute("PS_C9",DictCodeList.交易方向_产品现金分红.getValue());
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
        //可用持仓流水、可用现金流水处理
        //finNetProductBonusSchemeService.insertPosition(dto);        
        
        ProcessParameter<FinNetProductBonusSchemeDto> processParameter = new ProcessParameter<FinNetProductBonusSchemeDto>();
        WorkFlowParameter<FinNetProductBonusSchemeDto> params = new WorkFlowParameter<FinNetProductBonusSchemeDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getTradeId());
        params.setBusinessType(DictCodeList.共通业务类型_净值型产品分红方案管理.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> "产品代码" + "(" + dto.getFinprodMarketId() + ")-" 
                + "分红方案编号" + "(" + dto.getBonusPlanId() + ")-现金分红");
        
        processParameter.setWorkFlowParameter(params);
        String msg = finNetProductBonusSchemeService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    @ResponseBody
    @RequestMapping("doRevoke")
    public AjaxResult doRevoke(@RequestBody FinNetProductBonusSchemeDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }

        ProcessParameter<FinNetProductBonusSchemeDto> processParameter = new ProcessParameter<FinNetProductBonusSchemeDto>();
        WorkFlowParameter<FinNetProductBonusSchemeDto> params = new WorkFlowParameter<FinNetProductBonusSchemeDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        
//        BussinessCodeParameter parameter = new BussinessCodeParameter();
//        parameter.setCommonBusinessType(DictCodeList.共通业务类型_净值型产品分红方案管理);
//        String busiCode = baseGetBusinessId.buildBussinessCode(parameter); 
        
        params.setBusinessCode(dto.getTradeId());
        params.setBusinessType(DictCodeList.共通业务类型_净值型产品分红方案管理.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> "产品代码" + "(" + dto.getFinprodMarketId() + ")-" 
                + "分红方案编号" + "(" + dto.getBonusPlanId() + ")-现金分红交易撤销");
        processParameter.setPageId(this.getPageId());
        processParameter.setOperationType(OperationType.REVOKE);
        processParameter.setWorkFlowParameter(params);
        String msg = finNetProductBonusSchemeService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    /**
     * 
     * 查询期次子产品
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findCycleDataTableInfo")
    public Page<FinNetProductBonusSchemeDto> findCycleDataTableInfo(){
    	Page<FinNetProductBonusSchemeDto> page =  new Page<FinNetProductBonusSchemeDto>();
    	String workflowId = this.getParam("workflowId");
        if (StringUtil.isNotEmpty(workflowId)) {
            FinNetProductBonusSchemeDto finNetProductBonusSchemeInfo = finNetProductBonusSchemeService.getBussinessEntityByWorkflowId(
            		this.getParam(GlobalConstant.WORKFLOW_ID));
            if (finNetProductBonusSchemeInfo != null) {
            	page.setRoot(finNetProductBonusSchemeInfo.getListCycleProductInfo());
                return page;
            } 
        }
        page.setRoot(finNetProductBonusSchemeService.findCycleDataTableInfo(RequestUtil.getPageParam()));
        return page;
    }
}
