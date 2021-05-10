package com.joyintech.tams.common.basicdata.marketdata.exchangerateinfo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.basicdata.marketdata.exchangerateinfo.dto.ComExchangeQuotationInfoDto;
import com.joyintech.tams.common.basicdata.marketdata.exchangerateinfo.service.ComExchangeQuotationInfoService;
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
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 汇率行情 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangzhao
 * @version 1.0 2018年8月9日
 * @see ComExchangeQuotationInfo002Controller
 * @since 1.0
 */

@Controller
@PageId("ComExchangeQuotationInfo002")
@RequestMapping("/common/basicdata/marketdata/exchangerateinfo/ComExchangeQuotationInfo002")
public class ComExchangeQuotationInfo002Controller extends BaseController {

    /**
     * 汇率行情service
     */
    @Autowired
    private ComExchangeQuotationInfoService comExchangeQuotationInfoService;

    /**
     * 初始化新建页面
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.货币对,DictCode.估值来源);
        String exchargeCode = getParam("exchargeCode");
        String tradeDate = getParam("tradeDate");
        String workflowId = getParam("workflowId");
        // 修改时
        if (StringUtil.isNotEmpty(exchargeCode)) {
            ComExchangeQuotationInfoDto comExchangeQuotationInfoDto = null;
            Map<String, Object> pageParam = new HashMap<>();
            pageParam.put("exchargeCode", exchargeCode);
            pageParam.put("tradeDate", tradeDate);
            List<ComExchangeQuotationInfoDto> selectAllList = comExchangeQuotationInfoService.selectAllList(
                pageParam);
            if (selectAllList != null && selectAllList.size() > 0) {
                comExchangeQuotationInfoDto = selectAllList.get(0);
            }
            model.addAttribute("entity", comExchangeQuotationInfoDto);
        }
        else if (StringUtil.isNotEmpty(workflowId)) {
            ComExchangeQuotationInfoDto comExchangeQuotationInfoDto = comExchangeQuotationInfoService.getBussinessEntityByWorkflowId(
                workflowId);
            model.addAttribute("entity", comExchangeQuotationInfoDto);
        }else{
        	ComExchangeQuotationInfoDto comExchangeQuotationInfoDto = new ComExchangeQuotationInfoDto();
        	comExchangeQuotationInfoDto.setQuotUnit(1L);
        	model.addAttribute("entity", comExchangeQuotationInfoDto);
        }
        return super.init(model);
    }

    /**
     * 保存时的check <一句话功能简述> <功能详细描述> 修改类型进行检查
     * 
     * @param dto 检验输入参数
     * @param result 错误参数
     * @return 检验结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) ComExchangeQuotationInfoDto dto, BindingResult result) {
        AjaxResult ajaxResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();
        if (!StringUtil.isNotEmpty(dto.getDatasourceType())){
            errorList.add(new ValidMessage("请输入数据来源！"));
        }
        if (!StringUtil.isNotEmpty(dto.getMidPrice()) && !StringUtil.isNotEmpty(dto.getClosePrice())){
            errorList.add(new ValidMessage("收盘价和中间价不能同时为空！"));
        }
        // 编辑时不走此校验
        if (!StringUtil.isNotEmpty(dto.getMarketType()) && StringUtil.equals("0",dto.getPageModel())){
            errorList.add(new ValidMessage("请输入行情类型！"));
        }
        if (StringUtil.isNotEmpty(dto.getMarketType()) && StringUtil.equals("01",dto.getMarketType()) && !StringUtil.isNotEmpty(dto.getDeadLine())){
            errorList.add(new ValidMessage("当行情类型为远期时，期限不能为空！"));
        }
        if (errorList.size()>0){
            ajaxResult.setErrorList(errorList);
            return ajaxResult;
        }
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 
     * 保存/修改
     * <功能详细描述>
     * @param dto 传入参数
     * @param br 传入的报错参数
     * @return 返回结果
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) ComExchangeQuotationInfoDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        WorkFlowParameter<ComExchangeQuotationInfoDto> params = new WorkFlowParameter<ComExchangeQuotationInfoDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getExchargeCode());
        params.setBusinessType(DictCodeList.共通业务类型_汇率行情信息.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
            entity -> "货币对" + "("
                      + SysDataUtil.getNameFromDataDict(DictCode.货币对,
                          entity.getExchargeCode())
                      + ")" + "-" + "生效日" + "(" + DateUtil.format(entity.getTradeDate()) + ")" + "收盘价" + "("
                      + entity.getClosePrice() + ")");
        // 给参数变成ProcessParameter而非WorkFlowParameter
        ProcessParameter<ComExchangeQuotationInfoDto> parameter = new ProcessParameter<ComExchangeQuotationInfoDto>();
        parameter.setWorkFlowParameter(params);
        // 系统页面表IS_NEED_WORKFLOW 是否走流程，01-是，02-否
        String msg = comExchangeQuotationInfoService.start(parameter, dto);
        return new AjaxResult(msg);
    }

}
