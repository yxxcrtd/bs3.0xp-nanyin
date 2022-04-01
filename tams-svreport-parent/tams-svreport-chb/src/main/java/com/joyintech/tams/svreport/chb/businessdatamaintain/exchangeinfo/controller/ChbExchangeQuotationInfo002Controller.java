package com.joyintech.tams.svreport.chb.businessdatamaintain.exchangeinfo.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.svreport.chb.businessdatamaintain.exchangeinfo.dto.ChbExchangeQuotationInfoDto;
import com.joyintech.tams.svreport.chb.businessdatamaintain.exchangeinfo.service.ChbExchangeQuotationInfoService;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 汇率行情 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangzhao
 * @version 1.0 2018年8月9日
 * @see ChbExchangeQuotationInfo002Controller
 * @since 1.0
 */

@Controller
@PageId("ChbExchangeQuotationInfo002")
@RequestMapping("/chb/businessdatamaintain/exchangeinfo/ChbExchangeQuotationInfo002")
public class ChbExchangeQuotationInfo002Controller extends BaseController {

    /**
     * 汇率行情service
     */
    @Resource
    private ChbExchangeQuotationInfoService chbExchangeQuotationInfoService;

    /**
     * 初始化新建页面
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.货币对,DictCode.估值来源);
        String exchargeCode = getParam("exchargeCode");
        String tradeDate = getParam("tradeDate");
        // 修改时
        if (StringUtil.isNotEmpty(exchargeCode)) {
            ChbExchangeQuotationInfoDto mstExchangeQuotationInfoDto = null;
            Map<String, Object> pageParam = new HashMap<>();
            pageParam.put("exchargeCode", exchargeCode);
            pageParam.put("tradeDate", tradeDate);
            List<ChbExchangeQuotationInfoDto> selectAllList = chbExchangeQuotationInfoService.selectAllList(
                pageParam);
            if (selectAllList != null && selectAllList.size() > 0) {
                mstExchangeQuotationInfoDto = selectAllList.get(0);
            }
            model.addAttribute("entity", mstExchangeQuotationInfoDto);
        }
        ChbExchangeQuotationInfoDto mstExchangeQuotationInfoDto = new ChbExchangeQuotationInfoDto();
        mstExchangeQuotationInfoDto.setQuotUnit(1L);
        model.addAttribute("entity", mstExchangeQuotationInfoDto);
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
        ValidateGroupEdit.class}) ChbExchangeQuotationInfoDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 
     * 保存/修改
     * <功能详细描述>
     * @param dto 传入参数
     * @return 返回结果
     * @throws WorkFlowException 抛出异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) ChbExchangeQuotationInfoDto dto,BindingResult br) throws WorkFlowException {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        return chbExchangeQuotationInfoService.save(dto);
    }

}
