/*
 * 文件名：PtlInvPortfolioStrategy003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangJin
 * 修改时间：2019年10月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlinvportfoliostrategy.controller;

import java.io.IOException;
import java.util.List;

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
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.PtlIncomeIndexFloat;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.portfolio.ptlinvportfoliostrategy.dto.PtlIncomeIndexDto;
import com.joyintech.tams.invest.portfolio.ptlinvportfoliostrategy.service.PtlInvPortfolioStrategy001Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 评价指标编辑
 * 
 * @author zhangJin
 * @version 1.0 2019年10月15日
 * @see PtlInvPortfolioStrategy003Controller
 * @since 1.0
 */
@Controller
@PageId("PtlInvPortfolioStrategy003")
@RequestMapping("/invest/portfolio/ptlinvportfoliostrategy/PtlInvPortfolioStrategy003")
public class PtlInvPortfolioStrategy003Controller extends BaseController {

    @Autowired
    private PtlInvPortfolioStrategy001Service ptlInvPortfolioStrategy001Service;

    /**
     * 界面初始化 {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.投资策略类型, DictCode.评价指标, DictCode.评价指标收益目标类型,
            DictCode.是或否, DictCode.利率行情);
        PtlIncomeIndexDto entity = (PtlIncomeIndexDto)JsonUtils.json2Object(this.getParam("data"),
            PtlIncomeIndexDto.class);
        entity = ptlInvPortfolioStrategy001Service.dealEditData(entity);
        model.addAttribute(GlobalConstant.ENTITY, entity);
        if (StringUtil.isEmptyList(entity.getFloatList())) {
            model.addAttribute("floatList", "");
        }
        else {
            model.addAttribute("floatList", entity.getFloatList());
        }
        return super.init(model);
    }

    /**
     * 画面点击保存按钮时check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @throws IOException
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ ValidateGroupEdit.class }) PtlIncomeIndexDto dto, BindingResult result)
        throws IOException {
        AjaxResult ajax = new AjaxResult();
        if (result.hasErrors()) {
            return new AjaxResult(result);
        }
        // 拆分浮动类型的表格数据
        PtlIncomeIndexDto entity = new PtlIncomeIndexDto();
        BeanUtil.copyPropertiesIgnoreNull(entity, dto);
        if (!StringUtil.isEmpty(entity.getIncomeType())
            && StringUtil.equals(entity.getIncomeType(), "02")) {
            List<PtlIncomeIndexFloat> floatList = dto.getFloatList();
            StringBuilder incomeTarget = new StringBuilder();
            StringBuilder incomeTargetCode = new StringBuilder();
            for (PtlIncomeIndexFloat detail : floatList) {
                incomeTarget.append(
                    SysDataUtil.getNameFromDataDict(DictCode.利率行情, detail.getBaseRuleId())).append(
                        "*").append(detail.getCoefficient().toString()).append("+");

                incomeTargetCode.append(detail.getBaseRuleId()).append("*").append(
                    detail.getCoefficient().toString()).append("+");
            }
            incomeTarget.append(dto.getSpreadRate()).append("%");
            incomeTargetCode.append(dto.getSpreadRate());
            entity.setIncomeTarget(incomeTarget.toString());
            entity.setIncomeTargetCode(incomeTargetCode.toString());
        }
        else {
            entity.setIncomeTargetCode(entity.getIncomeTarget());
        }
        ajax.setData(entity);
        return ajax;
    }
}
