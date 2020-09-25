package com.joyintech.tams.invest.basicdata.exchangerateinfo.controller;

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
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.basicdata.exchangerateinfo.dto.MstExchangeQuotationInfoDto;
import com.joyintech.tams.invest.basicdata.exchangerateinfo.service.MstExchangeQuotationInfoService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 汇率行情 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangzhao
 * @version 1.0 2018年8月9日
 * @see MstExchangeQuotationInfo002Controller
 * @since 1.0
 */

@Controller
@PageId("MstExchangeQuotationInfo002")
@RequestMapping("/invest/basicdata/exchangerateinfo/MstExchangeQuotationInfo002")
public class MstExchangeQuotationInfo002Controller extends BaseController {

    /**
     * 汇率行情service
     */
    @Autowired
    private MstExchangeQuotationInfoService mstExchangeQuotationInfoService;

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
            MstExchangeQuotationInfoDto mstExchangeQuotationInfoDto = null;
            Map<String, Object> pageParam = new HashMap<>();
            pageParam.put("exchargeCode", exchargeCode);
            pageParam.put("tradeDate", tradeDate);
            List<MstExchangeQuotationInfoDto> selectAllList = mstExchangeQuotationInfoService.selectAllList(
                pageParam);
            if (selectAllList != null && selectAllList.size() > 0) {
                mstExchangeQuotationInfoDto = selectAllList.get(0);
            }
            model.addAttribute("entity", mstExchangeQuotationInfoDto);
        }
        else if (StringUtil.isNotEmpty(workflowId)) {
            MstExchangeQuotationInfoDto mstExchangeQuotationInfoDto = mstExchangeQuotationInfoService.getBussinessEntityByWorkflowId(
                workflowId);
            model.addAttribute("entity", mstExchangeQuotationInfoDto);
        }else{
        	MstExchangeQuotationInfoDto mstExchangeQuotationInfoDto = new MstExchangeQuotationInfoDto();
        	mstExchangeQuotationInfoDto.setQuotUnit(1L);
        	model.addAttribute("entity", mstExchangeQuotationInfoDto);
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
        ValidateGroupEdit.class}) MstExchangeQuotationInfoDto dto, BindingResult result) {
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
        ValidateGroupEdit.class}) MstExchangeQuotationInfoDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        WorkFlowParameter<MstExchangeQuotationInfoDto> params = new WorkFlowParameter<MstExchangeQuotationInfoDto>();
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
        ProcessParameter<MstExchangeQuotationInfoDto> parameter = new ProcessParameter<MstExchangeQuotationInfoDto>();
        parameter.setWorkFlowParameter(params);
        // 系统页面表IS_NEED_WORKFLOW 是否走流程，01-是，02-否
        String msg = mstExchangeQuotationInfoService.start(parameter, dto);
        return new AjaxResult(msg);
    }

}
