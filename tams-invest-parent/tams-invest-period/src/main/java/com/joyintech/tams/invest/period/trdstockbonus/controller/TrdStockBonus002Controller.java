/**
 * 
 */
package com.joyintech.tams.invest.period.trdstockbonus.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.trdstockbonus.DividDetailInfo;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdstockbonus.dto.TrdStockBonus002Dto;
import com.joyintech.tams.invest.period.trdstockbonus.service.TrdStockBonus002Service;
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

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author panbin
 * @version 2019年6月24日
 * @see TrdStockBonus002Controller
 */
@PageId("TrdStockBonus002")
@RequestMapping("/invest/period/trdstockbonus/TrdStockBonus002")
@Controller
public class TrdStockBonus002Controller extends BaseController{

	@Autowired
	private TrdStockBonus002Service trdStockBonus002Service;
	
	@Autowired
    private PeriodCommonService periodCommonService;
	 /**
     * 业务编号共通
     */
    @Autowired
    private BaseGetBusinessId baseGetBusinessId;
	/**
	 * 日期组件
	 */
	@Autowired
    private IPlatformDate iPlatformDate;
	
	@Override
	public String init(Model model) {
		//获取画面所有参数
		Map<String,Object> param = RequestUtil.getPageParam();
		setSelectOptionToModel(model, DictCode.股票分红类型,DictCode.通道类型);
	    //获取业务类型
	    String type = (String)param.get("type");
	    String statusValid = (String)param.get("statusValid");
	    //获取数据id
	    String busiCode = (String) param.get("busiCode");
	    TrdStockBonus002Dto dto002 = null;
		if (StringUtil.equals(DictCodeListExt1.交易流程状态_流程中.getValue(), statusValid)
				|| !WorkFlowUtil.isNotFromToDoList()) {
			String workflowId = (String)param.get(GlobalConstant.WORKFLOW_ID);
			if(StringUtil.isNotEmpty(workflowId)){
				model.addAttribute("workflowId",workflowId);
				dto002 = trdStockBonus002Service.getBussinessEntityByWorkflowId(workflowId);
			}else{
				// 获取股票汇总信息
				dto002 = trdStockBonus002Service.findProcessInfo(param);
				// 获取分红明细信息
				List<FinAssetIntInfo> dividInfoList = trdStockBonus002Service.findProcessDividInfo(busiCode);
//				// 获取划款指令list
//				for (DividDetailInfo ddInfo : dividInfoList) {
//					ddInfo.setStlPayOrders(trdStockBonus002Service.findStlPayOrdInfo(ddInfo.getTradeId()));
//				}
				dto002.setDividDetailInfoList(dividInfoList);
			}

		} else if (StringUtil.isNotEmpty(statusValid)) {
			// 有效、撤销数据
			dto002 = trdStockBonus002Service.findEffectiveInfo(param);
//			// 获取分红明细信息
			List<FinAssetIntInfo> dividInfoList = trdStockBonus002Service.findDividDetailInfo(busiCode);
//			// 获取划款指令list
//			for (DividDetailInfo ddInfo : dividInfoList) {
//				ddInfo.setStlPayOrders(trdStockBonus002Service.findStlPayOrdInfo(ddInfo.getTradeId()));
//			}
			dto002.setDividDetailInfoList(dividInfoList);
		}
	    else{
	    	//新建
	    	dto002 = new TrdStockBonus002Dto();
	    	Date date = iPlatformDate.getSysDate();
	    	Calendar calendar = Calendar.getInstance();
	    	calendar.setTime(date);
	    	//前一天
	    	calendar.add(Calendar.DAY_OF_MONTH, -1);
	    	Date preDay = calendar.getTime();
	    	//后一天
	    	calendar.setTime(date);
	    	calendar.add(Calendar.DAY_OF_MONTH, 1);
	    	Date nextDay = calendar.getTime();
	    	//除权除息日
	    	dto002.setTradeDate(date);
	    	//派息日
	    	dto002.setSettleDate(date);
	    	//权益登记日
	    	dto002.setRegDate(preDay);
	    	//红股上市日
	    	dto002.setRedStockDate(nextDay);
	    }
	    
	    model.addAttribute(GlobalConstant.ENTITY, dto002);
	    model.addAttribute("type", type);
	    model.addAttribute("STOCK_DEVISION_TYPE01", DictCodeList.股票分红类型_现金分红.getValue());
	    model.addAttribute("STOCK_DEVISION_TYPE02", DictCodeList.股票分红类型_送转.getValue());
	    model.addAttribute("STOCK_DEVISION_TYPE03", DictCodeList.股票分红类型_分红送转.getValue());
	    model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
        model.addAttribute("formTodoList", !WorkFlowUtil.isNotFromToDoList());
//        model.addAttribute("acctNoList", JsonUtils.object2Json(periodCommonService.queryAcctList(null)));
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
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) TrdStockBonus002Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
	
    
    /**
     * 新建页面，查询分红明细
     *
     * @return 
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findividInfo")
    public AjaxResult findividInfo() {
        AjaxResult ar = new AjaxResult();
        ar.setData(trdStockBonus002Service.findividInfo(RequestUtil.getPageParam()));
        return ar;
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
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) TrdStockBonus002Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<TrdStockBonus002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdStockBonus002Dto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessExtType(DictCodeListExt2.fromDictCodeList(DictCodeListExt2.共通业务类型_股票分红交易));
		if(StringUtil.isEmpty(dto.getBusiCode())){
			dto.setBusiCode(baseGetBusinessId.buildBussinessCode(parameter));
		}
        dto.setTotleTradeId(dto.getBusiCode());
		dto.setBusiType(DictCodeListExt2.共通业务类型_股票分红交易.getValue());
        params.setBusinessCode(dto.getBusiCode());
        params.setBusinessType(DictCodeListExt2.共通业务类型_股票分红交易.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> WorkFlowUtil.concatComment(
                this.getMessage("TrdStockBonus001.tradeId"),
                GlobalConstant.OPEN_PARENTHESIS + entity.getBusiCode()
                        + GlobalConstant.CLOSE_PARENTHESIS,
                this.getMessage("TrdStockBonus001.finprodMarketName"),
                GlobalConstant.OPEN_PARENTHESIS + entity.getFinprodAbbr()
                        + GlobalConstant.CLOSE_PARENTHESIS,
				this.getMessage("TrdStockBonus001.bonusMode"),
				GlobalConstant.OPEN_PARENTHESIS + entity.getPsText()
						+ GlobalConstant.CLOSE_PARENTHESIS));
        processParameter.setWorkFlowParameter(params);

        String msg = trdStockBonus002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
