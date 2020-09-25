package com.joyintech.tams.invest.period.trdprojint.controller;

import javax.validation.Valid;

import com.joyintech.framework.util.RequestUtil;
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
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.period.trdprojint.dto.TrdProjectDurationIntDto;
import com.joyintech.tams.invest.period.trdprojint.service.TrdProjectDurationInterest001Service;

/**
 * @program: tams-invest-parent
 * @description: 买断利率维护
 * @author: 强成西
 * @create: 2018-10-23 13:44
 **/
@Controller
@PageId("TrdProjectDurationInterest002")
@RequestMapping("/invest/period/trdprojint/TrdProjectDurationInterest002")
public class TrdProjectDurationInterest002Controller extends BaseController {

    @Autowired
    private TrdProjectDurationInterest001Service TrdProjectDurationInterest001Service;

    /**
     * 需要初始化的下拉菜单
     */
    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.项目类一级分类};

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DICT_CODES);
        model.addAttribute("INT_ASSET_TYPE", DictCodeList.共通业务类型_项目类资产存续期.getValue());
        TrdProjectDurationIntDto dto = new TrdProjectDurationIntDto();
        dto.setFinprodId(RequestUtil.getParam("finprodId"));
        dto.setAssetType(RequestUtil.getParam("assetType"));
        dto.setAssetId(RequestUtil.getParam("assetId"));
        dto.setAssetName(RequestUtil.getParam("assetName"));
        dto.setvDate(RequestUtil.getParam("vDate"));
        dto.setmDate(RequestUtil.getParam("mDate"));
        dto.setPayCycle(RequestUtil.getParam("payCycle"));
        model.addAttribute("entity", dto);
        return super.init(model);
    }

    /**
     * 画面点击保存按钮时check
     *
     * @param dto 实体类
     * @param result
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid TrdProjectDurationIntDto dto, BindingResult result) {
        return commonCheck(result, "save");
    }

    /**
     * 画面点击保存按钮时check
     *
     * @param dto 实体类
     * @param result
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) TrdProjectDurationIntDto dto,
                           BindingResult result) {
        if (result.hasErrors()) {
            return commonCheck(result, "save");
        }
        // TrdProjectDurationInterest001Service.save(dto);
        return new AjaxResult(getMessage("COMMON_E_0010", dto.getAssetId() + getMessage("TrdProjectDurationInterest002.rate")));
    }

}
