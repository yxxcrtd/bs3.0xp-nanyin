/*
 * 文件名：FinProductPayInt001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.payint.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.joyintech.framework.util.*;
import com.joyintech.tams.component.business.settle.BussSettleOrderCommService;
import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
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
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.product.payint.dto.FinProductPayInt001Dto;
import com.joyintech.tams.invest.product.payint.dto.FinProductPayInt003Dto;
import com.joyintech.tams.invest.product.payint.service.FinProductPayInt001Service;
import com.joyintech.tams.invest.product.payint.service.FinProductPayInt003Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.service.LeaveMarkProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 产品中间付息查询
 *
 * @author etliu
 * @version 1.0 2018年12月29日
 * @see FinProductPayInt001Controller
 * @since 1.0
 */
@Controller
@PageId("FinProductPayInt001")
@RequestMapping("/invest/product/payint/FinProductPayInt001")
public class FinProductPayInt001Controller extends BaseController {

    /**
     * FinProductPayInt001Service
     */
    @Autowired
    private FinProductPayInt001Service finProductPayInt001Service;
    /**
     * FinProductPayInt003Service
     */
    @Autowired
    private FinProductPayInt003Service finProductPayInt003Service;


    @Autowired
    private IPlatformDate platformDateImpl;
    
    /**
     * mstWorkflowCommonService工作流组件
     */
    @Autowired(required = false)
    private LeaveMarkProcessService leaveMarkProcessService;
    /**
     * 划款指令组件
     */
    @Autowired
    private BussSettleOrderCommService bussSettleOrderCommService;
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
    	 this.setSelectOptionToModel1(model, DictCode.画面数据状态);
        //this.setSelectOptionToModel(model, DictCode.有效状态);
        model.addAttribute("PAGE_DATA_STATUS_01", DictCodeListExt1.画面数据状态_待处理.getValue());
        model.addAttribute("PAGE_DATA_STATUS_YX", DictCodeListExt1.画面数据状态_有效.getValue());
        model.addAttribute("PAGE_DATA_STATUS_06", DictCodeListExt1.画面数据状态_撤销.getValue());

        Map<String, Object> map = RequestUtil.getPageParam();
        if(StringUtil.isNotEmpty(map.get("remType"))) {
            model.addAttribute("finprodId", (String)map.get("finprodId"));

            model.addAttribute("remType", map.get("remType"));
//            String quaryDate = (String)map.get("mdate");
//            model.addAttribute("quaryDate", quaryDate+" - "+ DateUtil.addDay(quaryDate, Integer.parseInt((String)map.get("days"))));
        }

        return super.init(model);
    }

    /**
     * 产品代码下拉框
     * @return 下拉框列表
     * @throws Exception Exception
     */
    @RequestMapping("queryProdIdInfo")
    @ResponseBody
    public Page<SelectOptionDto> queryProdIdInfo()
            throws Exception {
        return finProductPayInt001Service.queryProdIdInfo(RequestUtil.getPageParam());
    }

    /**
     * 组合代码下拉框
     * @param param 过滤参数
     * @return 下拉框列表
     * @throws Exception Exception
     */
    @RequestMapping("selectPortfolioId")
    @ResponseBody
    public Page<SelectOptionDto> selectPortfolioId()
            throws Exception {
        return finProductPayInt001Service.selectPortfolioId(RequestUtil.getPageParam());
    }

    /**
     * 分页查询
     *
     * @return Page<FinProductPayInt001Dto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<FinProductPayInt001Dto> findPage() throws Exception {
        return finProductPayInt001Service.findPage(RequestUtil.getPageParam());
    }

    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return finProductPayInt001Service.export(this.getPageId(), RequestUtil.getPageParam());
    }
    /**
     * check
     * 
     * @param finProductExpire001Dto check对象
     * @param br 验证信息
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@Valid @RequestBody FinProductPayInt001Dto finProductPayInt001Dto,
                            BindingResult br) {
        if (br != null && br.hasErrors()) {
            return new AjaxResult(br);
        }
		AjaxResult result = new AjaxResult();
		List<ValidMessage> errorList = new ArrayList<ValidMessage>();
		if (!DictCodeListExt1.画面数据状态_有效.getValue().equals(finProductPayInt001Dto.getValidStatus())) {
			errorList.add(new ValidMessage("只有有效的交易才能撤销!"));
		}
		//update by leipeng 20190628 【收益型产品中间收息】撤销的交易在代办未审核，却可以再次撤销。
		if(finProductPayInt001Dto.getPayDate().compareTo(platformDateImpl.getSysDate())<0) {
        	ValidMessage validMessage = new ValidMessage("","支付日不能小于平台日期");
                errorList.add(validMessage);
        }

        
        if (WorkFlowUtil.isNotFromToDoList()) {
        	
	        	ProcessMark processMark = new ProcessMark();
	            processMark.setBusinessDataPk1(finProductPayInt001Dto.getFinprodId());
	            processMark.setBusinessDataPk2(finProductPayInt001Dto.getBussId());
	            
	            List<String> resultList =leaveMarkProcessService.getBusinessDataInFlow(processMark);
	            
	            if (!StringUtil.isEmptyList(resultList)) {
	            	errorList.add(new ValidMessage(this.getMessage("COMMON_E_0012", finProductPayInt001Dto.getBussId())));
	            }
        	}
        if (StringUtil.isEmptyList(errorList)) {
			if (StringUtil.equalsIgnoreCase(BussinessConstant.REVOKE,
					finProductPayInt001Dto.getOperateType())) {
                result.setData(this.getMessage("请确认是否撤销?"));
            }
			return result;
		}else {
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
    AjaxResult doRevoke(@RequestBody FinProductPayInt003Dto inputDto)
        throws Exception {

        ProcessParameter<FinProductPayInt003Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<FinProductPayInt003Dto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setPageId("FinProductPayInt003");
        processParameter.setOperationType(OperationType.REVOKE);
        inputDto.setTradeId(inputDto.getBussId());
        //划款指令
        if (inputDto != null && StringUtil.isEmptyList(inputDto.getBussTrdTransferOrder())){
            List<BussTrdTransferOrderBase> orderDtolist = bussSettleOrderCommService.selectOrdersByBizCode(inputDto.getBussId()) ;
            if (!StringUtil.isEmptyList(orderDtolist)){
                List<BussTrdTransferOrderDto> bussTrdTransferOrderDtoList = new ArrayList<BussTrdTransferOrderDto>();
                BussTrdTransferOrderDto bussTrdTransferOrderDto = new BussTrdTransferOrderDto();
                BeanUtil.copyPropertiesIgnoreNull(bussTrdTransferOrderDto, orderDtolist.get(0));
                bussTrdTransferOrderDtoList.add(bussTrdTransferOrderDto);
                inputDto.setBussTrdTransferOrder(bussTrdTransferOrderDtoList);
            }
        }
        workFlowParameter.setPageId("FinProductPayInt003");
        workFlowParameter.setElementId(inputDto.getElementId());
        workFlowParameter.setBusinessCode(inputDto.getBussId());
        workFlowParameter.setBusinessType(DictCodeList.共通业务类型_收益型产品中间付息管理.getValue());
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(
            getMessage("FinProductPayInt001.table.bussId") + GlobalConstant.OPEN_PARENTHESIS
                                                                            + entity.getBussId()
                                                                            + GlobalConstant.CLOSE_PARENTHESIS,
            getMessage("FinProductPayInt001.table.prodId") + GlobalConstant.OPEN_PARENTHESIS
                                                                                                                +entity.getFinprodId()
                                                                                                                + GlobalConstant.CLOSE_PARENTHESIS,
            getMessage(
                "FinProductPayInt001.table.prinAmt") + GlobalConstant.OPEN_PARENTHESIS + entity.getPrinAmt() + GlobalConstant.CLOSE_PARENTHESIS));
        processParameter.setWorkFlowParameter(workFlowParameter);
        String msg = finProductPayInt003Service.start(processParameter, inputDto);

        return new AjaxResult(msg);
    }
    /**
     * 
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public void setSelectOptionToModel1(Model model, IDictCode... dictCodes){
    	for (IDictCode dictCode : dictCodes) {
            SelectDto selectDto =getSelectDataFromDataDic(dictCode, null);
            if (selectDto != null && !StringUtil.isEmptyList(selectDto.getOptions())) {
                List<SelectOptionDto> options = selectDto.getOptions();
                SelectOptionDto option1 = options.get(0);
                SelectOptionDto option2 = options.get(1);
                SelectOptionDto option3 = options.get(2);
                SelectOptionDto option5 = options.get(3);
                options.remove(option1);
                options.remove(option2);
                options.remove(option3);
                options.remove(option5);
                model.addAttribute(dictCode.getValue(), JsonUtils.objectArray2Json(options.toArray()));
                model.addAttribute(dictCode.getValue() + "_default", selectDto.getInitValue());
            }
        }
    }
}
