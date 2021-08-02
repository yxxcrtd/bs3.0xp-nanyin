package com.joyintech.tams.invest.basicdata.finbankquotationinfo.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.constant.DictCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 银行间行情信息
 */
@Controller
@PageId("FinBankQuotationInfo001")
@RequestMapping("/invest/basicdata/finbankquotationinfo/FinBankQuotationInfo001")
public class FinBankQuotationInfo001Controller extends BaseController {

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.质押式回购_交易品种);
        this.setSelectOptionToModel(model, DictCode.买断式回购_交易品种);
        return super.init(model);

    }
    /**
     *
     * 页面查询，表格分页
     * <功能详细描述>
     * @return Page
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
//    @ResponseBody
//    @RequestMapping("queryPage")
//    public Page<FinBankQuotationInfoDto> selectAll()
//            throws Exception {
//        Page<FinBankQuotationInfoDto> page = new Page<FinBankQuotationInfoDto>();
//        return page;
//    }

}

