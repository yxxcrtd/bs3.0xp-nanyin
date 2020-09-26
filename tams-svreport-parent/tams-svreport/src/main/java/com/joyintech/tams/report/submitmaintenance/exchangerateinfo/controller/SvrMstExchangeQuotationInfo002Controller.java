package com.joyintech.tams.report.submitmaintenance.exchangerateinfo.controller;

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
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.submitmaintenance.exchangerateinfo.dto.SvrMstExchangeQuotationInfoDto;
import com.joyintech.tams.report.submitmaintenance.exchangerateinfo.service.SvrMstExchangeQuotationInfoMService;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 汇率行情 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangzhao
 * @version 1.0 2018年8月9日
 * @see SvrMstExchangeQuotationInfo002Controller
 * @since 1.0
 */

@Controller
@PageId("SvrMstExchangeQuotationInfo002")
@RequestMapping("/submitmaintenance/exchangerateinfo/SvrMstExchangeQuotationInfo002")
public class SvrMstExchangeQuotationInfo002Controller extends BaseController {

    /**
     * 汇率行情service
     */
    @Autowired
    private SvrMstExchangeQuotationInfoMService mstExchangeQuotationInfoMService;

    /**
     * 初始化新建页面
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.货币对);
        String exchargeCode = getParam("exchargeCode");
        String tradeDate = getParam("tradeDate");
//        String workflowId = getParam("workflowId");
        // 修改时
        if (StringUtil.isNotEmpty(exchargeCode)) {
            SvrMstExchangeQuotationInfoDto mstExchangeQuotationInfoDto = null;
            Map<String, Object> pageParam = new HashMap<>();
            pageParam.put("exchargeCode", exchargeCode);
            pageParam.put("tradeDate", tradeDate);
            List<SvrMstExchangeQuotationInfoDto> selectAllList = mstExchangeQuotationInfoMService.selectAllList(
                pageParam);
            if (selectAllList != null && selectAllList.size() > 0) {
                mstExchangeQuotationInfoDto = selectAllList.get(0);
            }
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
        ValidateGroupEdit.class}) SvrMstExchangeQuotationInfoDto dto, BindingResult result) {
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
    public AjaxResult save(SvrMstExchangeQuotationInfoDto dto)
        throws Exception {
        String msg = this.getMessage("REPORT_COMMON_I_0006");
        if("0".equals(dto.getPageModel())){
        	Map<String, Object> pageParam = new HashMap<String, Object>();
        	pageParam.put("exchargeCode", dto.getExchargeCode());
        	pageParam.put("tradeDate", dto.getTradeDateStr());
        	Page<SvrMstExchangeQuotationInfoDto> page = mstExchangeQuotationInfoMService.selectForPage(pageParam);
        	if(page!=null && page.getRoot()!=null && page.getRoot().size()>0){
        		msg = "已存在重复的数据!";
        	}else{
        		mstExchangeQuotationInfoMService.doComplete(dto,OperationType.NEW);
        	}
        }else if("1".equals(dto.getPageModel())){
        	mstExchangeQuotationInfoMService.doComplete(dto,OperationType.MODIFY);
        }
        return new AjaxResult(msg);
    }

}
