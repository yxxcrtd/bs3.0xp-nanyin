/*
 * 文件名：FinProductEstablish001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年12月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productduration.establish.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.product.productduration.establish.dto.FinProductEstablish002Dto;
import com.joyintech.tams.invest.product.productduration.establish.dto.FinProductEstablishDto;
import com.joyintech.tams.invest.product.productduration.establish.service.FinProductEstablish001Service;
import com.joyintech.tams.invest.product.productduration.establish.service.FinProductEstablish002Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 产品成立
 * 
 * @author zhangliang
 * @version 1.0 2018年12月19日
 * @see FinProductEstablish001Controller
 * @since 1.0
 */
@Controller
@PageId(value = "FinProductEstablish001")
@RequestMapping("/invest/product/productduration/establish/FinProductEstablish001")
public class FinProductEstablish001Controller extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired
    private FinProductEstablish001Service finProductEstablish001Service;

    /**
     * {@linkplain}
     */
    @Autowired
    private FinProductEstablish002Service finProductEstablish002Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.有效状态);

        return super.init(model);
    }

    /**
     * 查询产品成立信息
     * 
     * @return 产品成立信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("selectForPage")
    public Page<FinProductEstablishDto> selectForPage() {
        return finProductEstablish001Service.selectForPage(RequestUtil.getPageParam());
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
    public ResponseEntity<byte[]> export()
        throws Exception {
        return finProductEstablish001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }

    /**
     * check
     * 
     * @param finProductEstablishDto check对象
     * @param br 验证信息
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
	public AjaxResult check(@Valid @RequestBody FinProductEstablish002Dto finProductEstablishDto, BindingResult br) {
		AjaxResult result = new AjaxResult();
		List<ValidMessage> errorList = new ArrayList<ValidMessage>();
		if (StringUtil.isNotEmpty(finProductEstablishDto.getFinprodId())) {
			int num = finProductEstablish001Service.selectDealAdd(
					finProductEstablishDto.getFinprodId().substring(4, finProductEstablishDto.getFinprodId().length()),
					finProductEstablishDto.getVdateStr());
			if (num > 0) {
				errorList.add(new ValidMessage("有后续交易，不能撤销!"));
			}
			if (!DictCodeListExt1.有效状态_有效.getValue().equals(finProductEstablishDto.getEffectStatus())) {
				errorList.add(new ValidMessage("只有有效的交易才能撤销!"));
			}
			if (finProductEstablishDto.getVdate() != null 
					&& DateUtil.format(finProductEstablishDto.getVdate()).compareTo(DateUtil.getSysDate()) < 0) {
				errorList.add(new ValidMessage("仅允许撤销确认日大于等于平台日期的交易!"));
			}
		}
		if (br != null && br.hasErrors()) {
			return new AjaxResult(br);
		}
		if (StringUtil.isEmptyList(errorList)) {
			if (StringUtil.equalsIgnoreCase(BussinessConstant.REVOKE,
					finProductEstablishDto.getOperateType())) {
                result.setData(this.getMessage("请确认是否撤销?"));
            }
			return result;
		} else {
			result.setErrorList(errorList);
			return result;
		}

	}

    /**
     * 撤销
     * 
     * @param inputDto 画面输入参数dto
     * @return 撤销结果
     * @throws Exception 异常信息
     * @see
     * @since 1.0
     */
    @RequestMapping("doRevoke")
    @ResponseBody
    AjaxResult doRevoke(@RequestBody FinProductEstablish002Dto inputDto)
        throws Exception {

        ProcessParameter<FinProductEstablish002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<FinProductEstablish002Dto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        processParameter.setPageId("FinProductEstablish002");
        processParameter.setOperationType(OperationType.REVOKE);

        workFlowParameter.setPageId("FinProductEstablish002");
        workFlowParameter.setElementId(inputDto.getElementId());
        workFlowParameter.setBusinessCode(inputDto.getTradeId());
        workFlowParameter.setBusinessType(DictCodeList.共通业务类型_收益型产品认申购管理.getValue());
		workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(
				getMessage("FinProductEstablish001.table.bussId") + GlobalConstant.OPEN_PARENTHESIS
						+ entity.getTradeId() + GlobalConstant.CLOSE_PARENTHESIS,
				getMessage("FinProductEstablish001.table.finprodMarketId") + GlobalConstant.OPEN_PARENTHESIS
						+ entity.getFinprodId() + GlobalConstant.CLOSE_PARENTHESIS,
				getMessage("FinProductEstablish001.table.vdate") + GlobalConstant.OPEN_PARENTHESIS
						+ DateUtil.format(entity.getVdate()) + GlobalConstant.CLOSE_PARENTHESIS));

        String msg = finProductEstablish002Service.start(processParameter, inputDto);

        return new AjaxResult(msg);
    }
}
