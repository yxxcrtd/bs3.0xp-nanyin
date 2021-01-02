package com.joyintech.tams.invest.portfolio.feeprovisionsetting.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.portfolio.feepay.dto.InvestFeePay002Dto;
import com.joyintech.tams.invest.portfolio.feeprovisionsetting.dto.FeeProvisionSetting001Dto;
import com.joyintech.tams.invest.portfolio.feeprovisionsetting.service.FeeProvisionSetting002Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.invest.portfolio.feeprovisionsetting.service.FeeProvisionSetting001Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

import java.io.File;
import java.util.*;

/**
 * 费用预提设置 controller层
 * @author
 *
 */
@Controller
@PageId("FeeProvisionSetting001")
@RequestMapping("/invest/portfolio/feeprovisionsetting/FeeProvisionSetting001")
public class FeeProvisionSetting001Controller extends BaseController{

	@Autowired
	private FeeProvisionSetting001Service feeProvisionSetting001Service;

	@Autowired
	private FeeProvisionSetting002Service feeProvisionSetting002Service;

	@Autowired
	private IPlatformDate iPlatformDate;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String init(Model model) {
		this.setSelectOptionToModel(model,DictCode.有效状态);
		return super.init(model);
	}

	/**
	 * 查询费用预提
	 *
	 * @return 费用预提信息
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("selectForPage")
	public Page<FeeProvisionSetting001Dto> selectForPage() {
		return feeProvisionSetting001Service.selectForPage(RequestUtil.getPageParam());
	}

	/**
	 * 导出信息excel
	 *
	 * @return excel
	 * @throws Exception 异常
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping(ACTION_EXPORT)
	/*public ResponseEntity<byte[]> export()
			throws Exception {
		return feeProvisionSetting001Service.export(this.getPageId(),
				RequestUtil.getParameters(getRequest()));
	}*/
	public ResponseEntity<byte[]> export()
			throws Exception {
		 String datas = this.getParam("datas");
		 List<FeeProvisionSetting001Dto> param1; 
		 if (datas == null) {
			 return feeProvisionSetting001Service.export(this.getPageId(),RequestUtil.getPageParam());
		 }
		 param1 = JsonUtils.json2List(datas, FeeProvisionSetting001Dto.class);
		 File outFile =  feeProvisionSetting001Service.makeFile(
	                "/com/joyintech/tams/invest/portfolio/feeprovisionsetting/conf/export_conf.xml",
	                param1);
	            return ExportUtil.export(
	                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
	                                     + DateUtil.format(DateUtil.platDateFromCache())
	                                     + SystemGlobalConstant.CLOSE_PARENTHESIS
	                                     + SystemGlobalConstant.POINT
	                                     + FileNameUtil.getFileExtName(outFile),
	                outFile, true);
	    }

	/**
	 * 证券账户账号联想文本框
	 * @param param String
	 * @return List
	 * @throws Exception Exception
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("/getSecAcctId")
	public Page<SelectOptionDto> getSecAcctId(@RequestParam Map<String, Object> param) {
		return feeProvisionSetting001Service.getSecAcctId(param);
	}

	/**
	 * 撤销check
	 *
	 * @param dto 费用新建信息
	 * @param br 验证消息
	 * @return 验证结果
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@Valid @RequestBody FeeProvisionSetting001Dto dto, BindingResult br) {
		if (br != null && br.hasErrors()) {
			AjaxResult ar = new AjaxResult(br);
			List<ValidMessage> errorList = ar.getErrorList();
			// 流程中数据判断
			for (ValidMessage validMessage : errorList) {
				if (StringUtil.indexOf(validMessage.getMessage(),
						this.getMessage("COMMON_E_0012").substring(0,
								this.getMessage("COMMON_E_0012").length() - 3)) >= 0) {
					validMessage.setMessage(this.getMessage(
							"COMMON_E_0012", dto.getSeqNo() + "," + dto.getSecAcctId()));
				}
			}
			return ar;
		}
		// 验证消息
		Set<String> message = new HashSet<String>();
//		a.有效状态为撤销或流程中的数据，均不允许删除
//		b.预提开始日<=平台日时不允许删除
		if(StringUtil.equals(dto.getDealStatus(), DictCodeListExt1.有效状态_撤销.getValue()) || StringUtil.equals(dto.getDealStatus(), DictCodeListExt1.有效状态_流程中.getValue())){
			message.add("有效状态为撤销或流程中的数据，均不允许撤销!");
		}
		Date platDate = iPlatformDate.getSysDate(); // 平台日期
		if(dto.getPositionStartDate().getTime() <= platDate.getTime()){
			message.add("计提开始日<=平台日时不允许撤销!");
		}
		AjaxResult ar = new AjaxResult();
		if(CollectionUtils.isNotEmpty(message)){
			List<ValidMessage> errorList = new ArrayList<>();
			message.forEach(action->{
				errorList.add(new ValidMessage(action));
			});
			ar.setErrorList(errorList);
		}
		if(ar.getErrorList().isEmpty()){
			ar.setData(this.getMessage("COMMON_C_0001", this.getMessage("SysCommon.revoke")));
		}
		return ar;
	}

	/**
	 * 撤销
	 *
	 * @param dto 画面输入参数dto
	 * @return 撤销结果
	 * @throws Exception 异常信息
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("doRevoke")
	@ResponseBody
	AjaxResult doRevoke(@RequestBody FeeProvisionSetting001Dto dto) throws Exception {

		dto.setDealStatus(DictCodeListExt1.有效状态_流程中.getValue());
		feeProvisionSetting002Service.doUpdate(dto);	//设置状态为流程中
		dto.setActPositionEndDate(dto.getPositionEndDate());  //实际计提结束日等于计提结束日
		ProcessParameter<FeeProvisionSetting001Dto> processParameter = new ProcessParameter<>();
		WorkFlowParameter<FeeProvisionSetting001Dto> params = new WorkFlowParameter<>();
		params.setElementId(dto.getElementId());
		params.setPageId("FeeProvisionSetting002");
		params.setBusinessCode(dto.getSeqNo());
		params.setBusinessType(DictCodeListExt1.金融产品类型_预提费用.getValue());

		// 设置工作流注解
		params.setComment(
				entity -> this.getMessage("FeeProvisionSetting001.seqNo") + "(" + entity.getSeqNo()
						+ ")" + this.getMessage("FeeProvisionSetting001.secAcctId") + "("
						+ entity.getSecAcctId() + ")"
		);

		processParameter.setWorkFlowParameter(params);

		// 调用工作流事件
		String msg = feeProvisionSetting002Service.start(processParameter, dto);
		// 返回工作流错误信息
		return new AjaxResult(msg);
	}

	/**
	 * 撤销check
	 *
	 * @param dto 费用新建信息
	 * @param br 验证消息
	 * @return 验证结果
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("checkUpdate")
	public AjaxResult checkUpdate(@Valid @RequestBody FeeProvisionSetting001Dto dto, BindingResult br) {
		if (br != null && br.hasErrors()) {
			AjaxResult ar = new AjaxResult(br);
			List<ValidMessage> errorList = ar.getErrorList();
			// 流程中数据判断
			for (ValidMessage validMessage : errorList) {
				if (StringUtil.indexOf(validMessage.getMessage(),
						this.getMessage("COMMON_E_0012").substring(0,
								this.getMessage("COMMON_E_0012").length() - 3)) >= 0) {
					validMessage.setMessage(this.getMessage(
							"COMMON_E_0012", dto.getSeqNo() + "," + dto.getSecAcctId()));
				}
			}
			return ar;
		}
		// 验证消息
		Set<String> message = new HashSet<String>();
//		a.有效状态为撤销或流程中的数据，均不允许删除
//		b.预提开始日<=平台日时不允许删除
		if(StringUtil.equals(dto.getDealStatus(), DictCodeListExt1.有效状态_撤销.getValue()) || StringUtil.equals(dto.getDealStatus(), DictCodeListExt1.有效状态_流程中.getValue())){
			message.add("有效状态为撤销或流程中的数据，均不允许修改!");
		}
		Date platDate = iPlatformDate.getSysDate(); // 平台日期
		if(dto.getPositionStartDate().getTime() <= platDate.getTime()){
			message.add("计提开始日<=平台日时不允许修改!");
		}
		AjaxResult ar = new AjaxResult();
		if(CollectionUtils.isNotEmpty(message)){
			List<ValidMessage> errorList = new ArrayList<>();
			message.forEach(action->{
				errorList.add(new ValidMessage(action));
			});
			ar.setErrorList(errorList);
		}
		if(ar.getErrorList().isEmpty()){
			ar.setData(this.getMessage("COMMON_C_0001", this.getMessage("SysCommon.modify")));
		}
		return ar;
	}
}
