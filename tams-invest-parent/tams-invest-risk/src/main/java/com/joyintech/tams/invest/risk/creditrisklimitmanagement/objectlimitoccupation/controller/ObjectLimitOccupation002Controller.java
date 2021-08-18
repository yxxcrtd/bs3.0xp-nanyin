package com.joyintech.tams.invest.risk.creditrisklimitmanagement.objectlimitoccupation.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.systemtools.interf.IPlatformDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.objectlimitoccupation.dto.ObjectLimitOccupationDto;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.objectlimitoccupation.service.ObjectLimitOccupation001Service;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.objectlimitoccupation.service.ObjectLimitOccupation002Service;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.singlelimitoccupation.controller.SingleLimitOccupation001Controller;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 主体额度占用调整
 * 
 * @author chengzhong
 * @version 1.0 2020年02月26日
 * @see SingleLimitOccupation001Controller
 * @since 1.0
 */
@Controller
@PageId("ObjectLimitOccupation002")
@RequestMapping("/invest/risk/creditrisklimitmanagement/objectlimitoccupation/ObjectLimitOccupation002")
public class ObjectLimitOccupation002Controller extends BaseController {

	/**
	 * SingleLimitOccupation002Service
	 */
	@Autowired(required = false)
	private ObjectLimitOccupation002Service objectLimitOccupation002Service;

	
	@Autowired
    private IPlatformDate iPlatformDate;

	/**
	 * ObjectLimitOccupation001Service
	 */
	@Autowired
	private ObjectLimitOccupation001Service objectLimitOccupation001Service;

	
	/**
	 * 初始化的查询条件
	 */
	private static final IDictCode[] DICT_CODES = new DictCode[] { DictCode.额度分类, DictCode.额度调整类型 ,
			DictCode.期限种类};

	@Override
	public String init(Model model) {
		setSelectOptionToModel(model, DICT_CODES);
		String workflowId = getParam("workflowId");
		// 待办跳转
		if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
			model.addAttribute("workflowId", workflowId);
			ObjectLimitOccupationDto dto = objectLimitOccupation002Service.getBussinessEntityByWorkflowId(workflowId);
			model.addAttribute("entity", dto);
		} else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(this.getParam("seqno"))) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("seqno", this.getParam("seqno"));
			Page<ObjectLimitOccupationDto>  list = objectLimitOccupation001Service.selectHistory(param);
			ObjectLimitOccupationDto dto = new ObjectLimitOccupationDto();
			if (list != null && list.getRoot() != null && list.getRoot().size() > 0) {
				dto = list.getRoot().get(0);
			}
			model.addAttribute(GlobalConstant.ENTITY, dto);
		} else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isEmpty(this.getParam("seqno"))) {
			ObjectLimitOccupationDto dto = new ObjectLimitOccupationDto();
//			dto.setCustomerId(StringUtil.isEmpty(this.getParam("customerId")) ? null : this.getParam("customerId"));
//			dto.setCreditType(StringUtil.isEmpty(this.getParam("creditType")) ? null : this.getParam("creditType"));
//			dto.setTerm(new BigDecimal(this.getParam("term")));
//			dto.setTermUnit(StringUtil.isEmpty(this.getParam("termUnit")) ? null : this.getParam("termUnit"));
//			dto.setCreditMstId(StringUtil.isEmpty(this.getParam("creditMstId")) ? null : this.getParam("creditMstId"));
//			dto.setCcy(StringUtil.isEmpty(this.getParam("ccy")) ? null : this.getParam("ccy"));
			dto.setCdate(iPlatformDate.getSysDate());
			dto.setCreateUser(SecurityUtil.getLoginUser().getUserName());
			model.addAttribute(GlobalConstant.ENTITY, dto);
		}
		return super.init(model);
	}

	/**
	 * 保存按钮点击时的check
	 * 
	 * @return check结果
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody @Validated({ ValidateGroupEdit.class }) ObjectLimitOccupationDto dto,
			BindingResult br) {
		if (br.hasErrors()) {
			return new AjaxResult(br);
		}
		String confirmMsg = "";
		if((StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)
				|| StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT))
				&& dto.getAjustType().equals(DictCodeListExt3.额度调整类型_额度扣减.getValue())) {
			//查询授信额度管理
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("customerId", dto.getCustomerId());
			param.put("creditType", dto.getCreditType());
			param.put("term", dto.getTerm());
			param.put("termUnit", dto.getTermUnit());
			//可用金额
			BigDecimal amt = BigDecimal.ZERO;
			List<CreditLines> list = objectLimitOccupation002Service.selectCreditLine(param);
			if (list != null && list.size() > 0) {
				amt = ArithmeticUtil.subtract(list.get(0).getCreditAmt(), list.get(0).getHapCreditAmt());
			} else {
				return commonCheck(br, dto.getOperateType());
			}
			//查询调整金额
//			String platDate = iPlatformDate.getSysDateStr();
			param.put("ptDate", DateUtil.format(dto.getCdate()));
			Page<ObjectLimitOccupationDto> selectForPage = objectLimitOccupation001Service.selectForPage(param);
			if (selectForPage != null && selectForPage.getRoot() != null
					&& selectForPage.getRoot().size() > 0) {
				amt = ArithmeticUtil.doAdd(amt, selectForPage.getRoot().get(0).getTotleamount());
			}
			//可用金额小于调整金额是提醒
			if (ArithmeticUtil.subtract(amt, dto.getAmount()).compareTo(BigDecimal.ZERO) < 0) {
				confirmMsg += getMessage("RSK_E_0090") + "<br/>";
			}
		}
		
		if (confirmMsg.length() > 0) {
			AjaxResult wr = new AjaxResult();
			wr.setData(confirmMsg);
			return wr;
		} else {
			return super.commonCheck(br, dto.getOperateType());
		}
	}

	/**
	 * 保存
	 * 
	 * @param dto
	 * @throws Exception
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping(PAGE_SAVE)
	public AjaxResult save(@RequestBody @Validated({ ValidateGroupEdit.class }) ObjectLimitOccupationDto dto,
			BindingResult br) throws Exception {
		if (br.hasErrors()) {
			return new AjaxResult(br);
		}
		if (StringUtil.isEmpty(dto.getSeqno())) {
			dto.setSeqno(IDGenerator.generate());
		}
		// BussinessCodeParameter parameter = new BussinessCodeParameter();
		// parameter.setCommonBusinessType(DictCodeListExt3.共通业务类型_单笔资产额度校验规则);
		ProcessParameter<ObjectLimitOccupationDto> processParameter = new ProcessParameter<>();
		WorkFlowParameter<ObjectLimitOccupationDto> params = new WorkFlowParameter<>();
		params.setPageId(this.getPageId());
		params.setElementId(dto.getElementId());
		// 授权代码作为业务编号
		String businessCode = dto.getSeqno();
		params.setBusinessCode(businessCode);
		params.setBusinessType(DictCodeListExt3.共通业务类型_主体额度占用调整.getValue());

		// 设置流程业务说明, entity即传入的dto
		params.setComment(entity -> {
			return WorkFlowUtil.concatComment(getMessage("ObjectLimitOccupation001.customerId"),
					GlobalConstant.OPEN_PARENTHESIS, dto.getCustomerId(), GlobalConstant.CLOSE_PARENTHESIS,
					getMessage("ObjectLimitOccupation001.creditType"), GlobalConstant.OPEN_PARENTHESIS,
					entity.getCreditType(), GlobalConstant.CLOSE_PARENTHESIS);
		});
		processParameter.setWorkFlowParameter(params);
		String msg = objectLimitOccupation002Service.start(processParameter, dto);
		return new AjaxResult(msg);
	}
}
