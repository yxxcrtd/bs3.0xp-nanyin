/*
 * 文件名：ValIncomeStatisticsQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2018年10月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.bankaccount.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.bankaccount.dto.AccountDetailsQuery001Dto;
import com.joyintech.service.valuation.bankaccount.dto.ValIncomeStatisticsQuery001Dto;
import com.joyintech.service.valuation.bankaccount.service.AccountDetailsQuery001Service;
import com.joyintech.service.valuation.bankaccount.service.ValIncomeStatisticsQuery001Service;
import com.joyintech.tams.constant.DictCode;

/**
 * 银行统计查询
 * 
 * @author HuangJian
 * @version 1.0 2018年10月31日
 * @see ValBankAccountQuery001Controller
 * @since 1.0
 */
@RestController(value="serviceValBankAccountQuery001Controller")
@RequestMapping("/ValBankAccountQuery001")
public class ValBankAccountQuery001Controller extends BaseController {

    /**
     * accountDetailsQuery001Service
     */
    @Autowired
    private AccountDetailsQuery001Service accountDetailsQuery001Service;

    /**
     * valIncomeStatisticsQuery001Service
     */
    @Autowired
    private ValIncomeStatisticsQuery001Service valIncomeStatisticsQuery001Service;
    
    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        // 科目名称
        this.setSelectOptionToModel(model, DictCode.科目名称);
        // 业务类别
        this.setSelectOptionToModel(model, DictCode.业务类别);
        // this.setSelectOptionToModel(model, DictCode.频率);
//        model.addAttribute("DATE_DIMENSION", JsonUtils.objectArray2Json(
//            valIncomeStatisticsQuery001Service.getDateDimension().toArray()));
//        model.addAttribute("PRODUCT_DIMENSION", JsonUtils.objectArray2Json(
//            valIncomeStatisticsQuery001Service.getProductDimension().toArray()));
        this.setSelectOptionToModel(model, DictCode.产品维度);
        this.setSelectOptionToModel(model, DictCode.日期维度);
        return super.init(model);
    }

    
    /**
     * 检索账套代码(只取理财账套)
     * 
     * @return 账套代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
//    @ResponseBody
//    @RequestMapping("/getProduct")
//    public Page<SelectOptionDto> getProduct()
//        throws Exception {
//        Map<String, Object> map = RequestUtil.getPageParam();
//        return valAssetReclassification001Service.getBookSetId(map);
//    }
    
    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/findIncomeStatisticsPage/{page}/{length}")
    public Page<ValIncomeStatisticsQuery001Dto> findIncomeStatisticsPage(@RequestBody Map<String, Object> params,
    		@PathVariable("page") int page, @PathVariable("length") int length)throws Exception {
        return valIncomeStatisticsQuery001Service.findPage(page,length,params);
    }

    /**
     * 查询check
     * 
     * @param dto ValIncomeStatisticsQuery001Dto
     * @param result BindingResult
     * @return AjaxResult
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("checkIncomeStatistics")
    @ResponseBody
    public AjaxResult checkIncomeStatistics(@Valid @RequestBody ValIncomeStatisticsQuery001Dto dto,
                                            BindingResult result)
        throws Exception {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        return new AjaxResult();
    }

    /**
     * 账务明细-查询前校验
     * 
     * @param dto
     * @param result
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid AccountDetailsQuery001Dto dto,
                            BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        return new AjaxResult();
    }

    /**
     * 账务明细查询
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/findAccountDetailsPage/{page}/{length}")
    public Page<AccountDetailsQuery001Dto> findAccountDetailsPage(@RequestBody Map<String, Object> params,
    		@PathVariable("page") int page, @PathVariable("length") int length)throws Exception {
        return accountDetailsQuery001Service.findAccountDetailsPage(page,length,params);
    }

}
