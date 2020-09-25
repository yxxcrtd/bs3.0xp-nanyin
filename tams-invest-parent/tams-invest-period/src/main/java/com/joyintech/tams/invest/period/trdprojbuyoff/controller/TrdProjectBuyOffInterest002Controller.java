package com.joyintech.tams.invest.period.trdprojbuyoff.controller;

import java.util.Arrays;

import javax.validation.Valid;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
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
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.period.trdprojbuyoff.dto.TrdProjectBuyOffInterest001Dto;
import com.joyintech.tams.invest.period.trdprojbuyoff.service.TrdProjectBuyOffInterest001Service;

/**
 * @program: tams-invest-parent
 * @description: 买断利率维护
 * @author: 强成西
 * @create: 2018-10-23 13:44
 **/
@Controller
@PageId("TrdProjectBuyOffInterest002")
@RequestMapping("/invest/period/trdprojbuyoff/TrdProjectBuyOffInterest002")
public class TrdProjectBuyOffInterest002Controller extends BaseController {

    @Autowired
    private TrdProjectBuyOffInterest001Service trdProjectBuyOffInterest001Service;

    @Autowired
    private IPlatformDate platformDateImpl;

    /**
     * 需要初始化的下拉菜单
     */
    private static final IDictCode[] DICT_CODES = new DictCode[] {
        DictCode.项目类一级分类};

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DICT_CODES);
        String finprodId = RequestUtil.getParam("finprodId");
        String effDate = RequestUtil.getParam("effDate");
        if (StringUtil.isNotEmpty(finprodId) && StringUtil.isNotEmpty(effDate)) {
            TrdProjectBuyOffInterest001Dto dto = trdProjectBuyOffInterest001Service.findBuyOffInfo(finprodId, effDate);
            model.addAttribute(GlobalConstant.ENTITY, dto);
        } else {
            model.addAttribute("sysDate", platformDateImpl.getSysDateStr());
        }
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
    public AjaxResult check(@RequestBody @Valid TrdProjectBuyOffInterest001Dto dto,
                            BindingResult result) {
        AjaxResult ajaxResult = commonCheck(result, "save");
        if (!result.hasErrors()) {
            int count = trdProjectBuyOffInterest001Service.selectCountForMatch(dto);
            if (count > 0) {
                JoyinMessage message = new JoyinMessage(this.getMessage("TrdProjectBuyOffInterest002.error.exists"),
                    MessageType.确认消息);
                ajaxResult.setJoyinMessages(Arrays.asList(message));
            }
        }
        return ajaxResult;
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
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) TrdProjectBuyOffInterest001Dto dto, BindingResult result) {
        if (result.hasErrors()) {
            return commonCheck(result, "save");
        }
        trdProjectBuyOffInterest001Service.save(dto);
        return new AjaxResult(getMessage("COMMON_E_0010",
            dto.getAssetId() + getMessage("TrdProjectBuyOffInterest002.rate")));
    }

}
