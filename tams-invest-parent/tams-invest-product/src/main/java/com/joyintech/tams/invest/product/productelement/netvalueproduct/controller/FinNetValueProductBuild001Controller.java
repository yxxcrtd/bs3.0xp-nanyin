/*
 * 文件名：FinProductBuild001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.netvalueproduct.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.productelement.netvalueproduct.dto.FinNetValueProductBuild001Dto;
import com.joyintech.tams.invest.product.productelement.netvalueproduct.dto.FinNetValueProductBuild002Dto;
import com.joyintech.tams.invest.product.productelement.netvalueproduct.service.FinNetValueProductBuild001Service;
import com.joyintech.tams.invest.product.productelement.netvalueproduct.service.FinNetValueProductBuild002Service;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 产品新建管理
 * 
 * @author zhangliang
 * @version 1.0 2018年9月17日
 * @see FinNetValueProductBuild001Controller
 * @since 1.0
 */
@Controller
@PageId(value = "FinNetValueProductBuild001")
@RequestMapping("/invest/product/productelement/netvalueproduct/FinNetValueProductBuild001")
public class FinNetValueProductBuild001Controller extends BaseController {

	/**
	 * {@linkplain}
	 */
	@Autowired
	private FinNetValueProductBuild001Service finNetValueProductBuild001Service;

	/**
	 * {@linkplain}
	 */
	@Autowired
	private FinNetValueProductBuild002Service finNetValueProductBuild002Service;
	
	@Override
	@RequestMapping(PAGE_INIT)
	public String init(Model model) {
		setSelectOptionToModel(model,
				new DictCode[] { DictCode.收益类型, DictCode.处理模式, DictCode.目标客户类型, DictCode.排期发行方式, DictCode.有效状态,
						DictCode.投资性质, DictCode.募集方式, DictCode.开放类型, DictCode.产品收益标识, DictCode.产品系列, DictCode.发行状态,
						DictCode.发送状态/* , DictCode.目标客户一级分类, DictCode.目标客户二级分类 */ });

		model.addAttribute("statusValidE", DictCodeListExt1.有效状态_有效.getCodeName());
		model.addAttribute("issueStatusC", DictCodeListExt1.发行状态_确认发行.getValue());
		model.addAttribute("issueStatusCName", DictCodeListExt1.发行状态_确认发行.getCodeName());
		model.addAttribute("vdateStr", DateUtil.format(DateUtil.addMonthDate(DateUtil.platDateFromCache(), -1)));
		model.addAttribute("vdateEnd", DateUtil.format(DateUtil.platDateFromCache()));
		
		model.addAttribute("YES", DictCodeList.是否_是.getValue());

		// 画面元素是否显示
		PageElementConfigDto pageElementConfigDto = new PageElementConfigDto();
		pageElementConfigDto.setPageId(this.getPageId());
		Map<String, String> isEleShow = PageElementUtil.isPageElementShow(pageElementConfigDto); 
		model.addAttribute("isEleShow", isEleShow);
		//期次管理方式 
		FinNetValueProductBuild001Dto finNetValueProductBuild001dto= new FinNetValueProductBuild001Dto();
		finNetValueProductBuild001dto.setPreview(finNetValueProductBuild001Service
				.getPERIODIC_MANAGE_INFORMATION(DictCodeListExt1.子系统代码_投管.getValue(), BussinessConstant.PERIODIC_MANAGE_INFORMATION));
		
		model.addAttribute("entity",finNetValueProductBuild001dto);
		return super.init(model);
	}

	/**
	 * 组合代码联想下拉框
	 * 
	 * @return 组合代码列表
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("queryGroupIdInfo")
	public List<SelectOptionDto> queryGroupIdInfo() {
		return finNetValueProductBuild001Service.queryGroupIdInfo(RequestUtil.getPageParam());
	}

	/**
	 * 组合代码联想下拉框
	 * 
	 * @return 组合代码列表
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("queryGroupIdInfoJz")
	public Page<SelectOptionDto> queryGroupIdInfoJz() {
		return finNetValueProductBuild001Service.queryGroupIdInfoJz(RequestUtil.getPageParam());
	}

	/**
	 * 产品代码联想下拉框
	 * 
	 * @return 产品代码列表
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("queryProdIdInfo")
	public Page<SelectOptionDto> queryProdIdInfo() {
		return finNetValueProductBuild001Service.queryProdIdInfo(RequestUtil.getPageParam());
	}

	/**
	 * 修改、删除相关check
	 * 
	 * @param FinNetValueProductBuild001Dto check对象
	 * @param br                            验证信息
	 * @return 验证结果
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody @Valid FinNetValueProductBuild001Dto FinNetValueProductBuild001Dto,
			BindingResult br) {
		if (br.hasErrors()) {
			AjaxResult ar = new AjaxResult(br);
			if (StringUtil.equals(FinNetValueProductBuild001Dto.getOperateType(), "issueStatus")) {
				List<ValidMessage> errorList = ar.getErrorList();
				// 流程中数据判断
				for (ValidMessage validMessage : errorList) {
					if (StringUtil.indexOf(validMessage.getMessage(), this.getMessage("COMMON_E_0012").substring(0,
							this.getMessage("COMMON_E_0012").length() - 3)) >= 0) {
						// 由于PK值的设定，此处特殊处理
						validMessage.setMessage(StringUtil.splitToList(",", validMessage.getMessage()).get(0));
					}
				}
			}
			return ar;
		}
		// modify by wm 增加请删除完产品后，删除对应组合！提示信息 start 2019-08-01
		if (StringUtil.equals("delete", FinNetValueProductBuild001Dto.getOperateType())) {
			AjaxResult result = commonCheck(br, FinNetValueProductBuild001Dto.getOperateType());
			// 提示信息检查
			List<JoyinMessage> joyinMessages = new ArrayList<>();
			joyinMessages.add(new JoyinMessage(this.getMessage("请删除完产品后，删除对应组合！"), MessageType.提示消息));
			result.setJoyinMessages(joyinMessages);
			return result;
		}
		else {
			return new AjaxResult();
		}
		


	}

	/**
	 * 页面删除
	 * 
	 * @param dto    费用品种
	 * @param result result
	 * @return 删除结果
	 * @throws Exception Exception
	 */
	@ResponseBody
	@RequestMapping(ACTION_DELETE)
	public AjaxResult delete(@RequestBody FinNetValueProductBuild001Dto dto, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			return new AjaxResult(result);
		}

		// 业务代码生成参数
		BussinessCodeParameter parameter = new BussinessCodeParameter();
		parameter.setCommonBusinessType(DictCodeList.共通业务类型_产品要素管理);
		ProcessParameter<FinNetValueProductBuild002Dto> processParameter = new ProcessParameter<>();
		WorkFlowParameter<FinNetValueProductBuild002Dto> params = new WorkFlowParameter<>();
		params.setPageId(dto.getPageId());
		params.setElementId(dto.getElementId());
		// 金融产品代码作为业务编号
		params.setBusinessCode(item -> {
			return item.getFinprodMarketId();
		});
		params.setBusinessType(DictCodeList.共通业务类型_产品要素管理.getValue());

		// 设置流程业务说明, entity即传入的dto
		params.setComment(entity -> {
			return WorkFlowUtil.concatComment(getMessage("FinProductBuild001.table.prodId"),
					GlobalConstant.OPEN_PARENTHESIS, entity.getFinprodMarketId(), GlobalConstant.CLOSE_PARENTHESIS,
					getMessage("FinProductBuild001.table.prodAbbr"), GlobalConstant.OPEN_PARENTHESIS,
					entity.getFinprodAbbr(), GlobalConstant.CLOSE_PARENTHESIS,
					getMessage("FinProductBuild001.table.vdate"), GlobalConstant.OPEN_PARENTHESIS,
					DateUtil.format(entity.getVdate()), GlobalConstant.CLOSE_PARENTHESIS);
		});
		processParameter.setWorkFlowParameter(params);
		String msg = finNetValueProductBuild002Service.start(processParameter,
				dto.getSelectedItem().toArray(new FinNetValueProductBuild002Dto[dto.getSelectedItem().size()]));
		return new AjaxResult(msg);
	}

	/**
	 * 画面查詢方法
	 * 
	 * @return 所有數據
	 * @throws Exception 異常
	 */
	@ResponseBody
	@RequestMapping("selectForPage")
	public Page<FinNetValueProductBuild001Dto> selectForPage() {
		return finNetValueProductBuild001Service.selectForPage(RequestUtil.getPageParam());
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
	@RequestMapping("export")
	public ResponseEntity<byte[]> export() throws Exception {
		return finNetValueProductBuild001Service.export(this.getPageId(), RequestUtil.getParameters(getRequest()));
	}

	/**
	 * 净值化产品基本信息-发送销售接口状态
	 *
	 * @param FinNetValueProductBuild001Dto
	 * @return 发送结果
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("sendNetValueInfoToSale")
	public AjaxResult sendNetValueInfoToInterf(
			@RequestBody FinNetValueProductBuild001Dto finNetValueProductBuild001Dto) {
		// 或许选择推送的数据，循环调用推送销售系统方法
		List<FinNetValueProductBuild002Dto> dtoList = finNetValueProductBuild001Dto.getSelectedItem();
		AjaxResult ar = new AjaxResult();
		if (dtoList.size() > 0) {
			List<ValidMessage> errorList = ar.getErrorList();
			boolean sendFlag = true;// 默认状态为推送销售系统
			for (FinNetValueProductBuild002Dto finNetValueProductBuild002Dto : dtoList) {
				// 查询是否已经推送销售系统
				if (DictCodeList.发送状态_发送成功.getValue().equals(finNetValueProductBuild002Dto.getSendStatus())) {
					errorList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0032", "未发送和发送失败")));
					sendFlag = false;
					return ar;
				}
			}
			if (sendFlag) {
				List<String> finProdIds = new ArrayList<>();
				for (FinNetValueProductBuild002Dto model : dtoList) {
					finProdIds.add(model.getFinprodId());
				}
				// 推送净值型产品要素信息至销售系统
				Map<String, String> map = finNetValueProductBuild001Service.sendNetValueInfoToSale(finProdIds);
				errorList.add(new ValidMessage(map.get("msg")));
			}
		}
		return ar;
	}
}
