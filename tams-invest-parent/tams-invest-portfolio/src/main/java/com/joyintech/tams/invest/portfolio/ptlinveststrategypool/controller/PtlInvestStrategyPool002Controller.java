package com.joyintech.tams.invest.portfolio.ptlinveststrategypool.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.portfolio.ptlinveststrategypool.dto.PtlInvestStrategyPool001Dto;
import com.joyintech.tams.invest.portfolio.ptlinveststrategypool.dto.PtlInvestStrategyPoolDetailDto;
import com.joyintech.tams.invest.portfolio.ptlinveststrategypool.service.PtlInvestStrategyPool002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
/**
 * 
 * 投资策略池管理
 * 
 * @author wm
 * @version 1.0 2019年8月13日
 * @see PtlInvestStrategyPool002Controller
 * @since 1.0
 */
@Controller
@PageId("PtlInvestStrategyPool002")
@RequestMapping("/invest/portfolio/ptlinveststrategypool/PtlInvestStrategyPool002")
public class PtlInvestStrategyPool002Controller extends BaseController {
	   /**
     * 业务编号生成类
     */
    @Autowired(required =false)
    public BaseGetBusinessId baseGetBussinessId;
    /**
     * 投资策略池
     */
    @Autowired
    private PtlInvestStrategyPool002Service ptlInvestStrategyPool002Service;
    
	/**
	 * 界面初始化 {@inheritDoc}
	 */
	@Override
	@RequestMapping(PAGE_INIT)
	public String init(Model model) {
		super.setSelectOptionToModel(model, DictCode.行内资产二级分类, DictCode.行内资产三级分类,
				DictCode.行内资产四级分类, DictCode.行内资产五级分类, DictCode.投资策略类型, DictCode.评价指标);
		String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
		String strategyId = getParam("strategyId");
		if (!WorkFlowUtil.isNotFromToDoList() && StringUtils.isNotEmpty(workflowId)) {
			// 根据工作流与留痕处理提供的共通方法获取业务实体
			PtlInvestStrategyPool001Dto entity = ptlInvestStrategyPool002Service.getBussinessEntityByWorkflowId(workflowId);
			 // 设置到model中
            model.addAttribute("entity", entity);
            model.addAttribute("workflowId", workflowId);
		}
		else if(WorkFlowUtil.isNotFromToDoList() && StringUtils.isNotEmpty(strategyId)){
			PtlInvestStrategyPool001Dto entity =  ptlInvestStrategyPool002Service.getPtlInvestStrategyPool(strategyId);
			 // 设置到model中
            model.addAttribute("entity", entity);
		}
		return super.init(model);
	}
	
	/**
	 * 画面点击保存按钮时check
	 *
	 * @param dto
	 *            实体类
	 * @param result
	 * @return check结果
	 */
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody @Validated({ ValidateGroupEdit.class }) PtlInvestStrategyPool001Dto dto,
			BindingResult result) {
		return commonCheck(result, dto.getOperateType());
	}
	/**
     * 保存
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) PtlInvestStrategyPool001Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        dto.setCommonType(DictCodeList.共通业务类型_投资策略池管理.getValue());
        
        if(StringUtil.isEmpty(dto.getStrategyId())){
//            BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter(); 
//            bussinessCodeParameter.setCommonBusinessType(DictCodeList.共通业务类型_投资策略池管理);
//            dto.setStrategyId(baseGetBussinessId !=null ? baseGetBussinessId.buildBussinessCode(bussinessCodeParameter):IDGenerator.generate());
            
            //策略代码的生成规则改为TZCL+6位自增序号
            String strategyId = ptlInvestStrategyPool002Service.createStrategyId();
            dto.setStrategyId(strategyId);
            int index = 0;
            if (!StringUtil.isEmptyList(dto.getPtlInvestStrategyPoolDetailList())) {
                for (PtlInvestStrategyPoolDetailDto detail : dto.getPtlInvestStrategyPoolDetailList()) {
                    // 批次号
                    index++ ;
                    String batchId = "";
                    if (index >= 10) {
                        batchId = strategyId + "0" + index;
                    }
                    else {
                        batchId = strategyId + "00" + index;
                    }
                    detail.setBatchId(batchId);
                }
            }
        }
        //增加修改时逻辑 解决投资策略修改画面报错问题 chenxiaoqiu 20191206 
		else {
			String strategyId =dto.getStrategyId();
			int index = 0;
			String tempBatchId = "";
			if (!StringUtil.isEmptyList(dto.getPtlInvestStrategyPoolDetailList())) {
				for (PtlInvestStrategyPoolDetailDto detail : dto.getPtlInvestStrategyPoolDetailList()) {
					
					String batchId = "";
					//判断batchId是否为空
					if (!StringUtil.isNotEmpty(detail.getBatchId())) {
						//为空时判断是否第一条，从1开始
						if(index==0) {
							index = 1; 
						}
						//根据规则生成batchId
						if (index >= 10) {
							batchId = strategyId + "0" + index;
						} else {
							batchId = strategyId + "00" + index;
						}
						detail.setBatchId(batchId);
					} else {
						//不为空时取上一条策略明细代码最后一位数
						tempBatchId = detail.getBatchId();
						String substring = tempBatchId.substring(tempBatchId.lastIndexOf('0'));
						index = Integer.valueOf(substring)+1;
					}
				}
			}
		}
        ProcessParameter<PtlInvestStrategyPool001Dto> processParameter = new ProcessParameter<PtlInvestStrategyPool001Dto>();
        WorkFlowParameter<PtlInvestStrategyPool001Dto> params = new WorkFlowParameter<PtlInvestStrategyPool001Dto>();
        params.setPageId("PtlInvestStrategyPool002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getStrategyId());
        params.setBusinessType(DictCodeList.共通业务类型_投资策略池管理.getValue());
        // 设置流程业务说明, 策略池名称 + "(" + 组策略池名称+ ")" + "-"  + "策略池代码" + "("  + "策略池代码"+")"
        params.setComment(
            entity -> this.getMessage("PtlInvestStrategyPool002.strategyName") + "("
                      + entity.getStrategyName() + ")" + "-"
                      + this.getMessage("PtlInvestStrategyPool002.strategyId") + "("
                      + entity.getStrategyId()+ ")" );
        processParameter.setWorkFlowParameter(params);
        String msg = ptlInvestStrategyPool002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    /**
     * 根据金融产品代码查询分层金额明细
     *
     * @return 分层金额明细
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryInvestPoolDetail")
    public List<PtlInvestStrategyPoolDetailDto> queryLayAmtDetailByFinprodId() {
        if (StringUtil.isNotEmpty(getParam(GlobalConstant.WORKFLOW_ID))) {
            ProcessMark pro = ptlInvestStrategyPool002Service.getBusinessInfo(
                this.getParam(GlobalConstant.WORKFLOW_ID));
            if (pro != null) {
            	PtlInvestStrategyPool001Dto entity = JsonUtils.json2Object(
                    pro.getBusinessData(), PtlInvestStrategyPool001Dto.class);
                return entity.getPtlInvestStrategyPoolDetailList();
            }
        }else if(StringUtil.isNotEmpty(this.getParam("strategyId"))){
            return ptlInvestStrategyPool002Service.getPtlInvestStrategyPoolDetail(this.getParam("strategyId"));
        }
        return new ArrayList<PtlInvestStrategyPoolDetailDto>();
    }


    /**
     * 查询二级分类多选框
     * 
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryMulType2")
    public Page<SelectOptionDto> queryMulType2() {
        Map<String, Object> map = RequestUtil.getPageParam();
        return ptlInvestStrategyPool002Service.queryMulType2(map);
    }

    /**
     * 查询三级分类多选框
     * 
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryMulType3")
    public Page<SelectOptionDto> queryMulType3() {
        Map<String, Object> map = RequestUtil.getPageParam();
        return ptlInvestStrategyPool002Service.queryMulType3(map);
    }

    /**
     * 查询四级分类多选框
     * 
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryMulType4")
    public Page<SelectOptionDto> queryMulType4() {
        Map<String, Object> map = RequestUtil.getPageParam();
        return ptlInvestStrategyPool002Service.queryMulType4(map);
    }

    /**
     * 查询五级分类多选框
     * 
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryMulType5")
    public Page<SelectOptionDto> queryMulType5() {
        Map<String, Object> map = RequestUtil.getPageParam();
        return ptlInvestStrategyPool002Service.queryMulType5(map);
    }
}
