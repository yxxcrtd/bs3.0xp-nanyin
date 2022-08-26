package com.joyintech.tams.disclosure.information.config.repprodinfo.controller;

import com.google.common.collect.Lists;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeExt1;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.disclosure.information.config.repprodinfo.dto.ProdInfoDto;
import com.joyintech.tams.disclosure.information.config.repprodinfo.service.ProdInfoService;
import com.joyintech.tams.systemtools.service.CommonPlatformDateService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wubingbing
 * @date 2022-05-09 11:15
 */
@Controller
@PageId("ProdInfo003")
@RequestMapping("/disclosure/information/config/repprodinfo/ProdInfo003")
public class ProdInfo003Contreoller extends BaseController {
    @Autowired
    private ProdInfoService prodInfoService;

    @Autowired
    private CommonPlatformDateService commonPlatformDateService;

    @Override
    public String init(Model model) {
       // this.setSelectOptionToModel(model, DictCode.发行状态);
        this.setSelectOptionToModel(model, DictCodeExt1.信披_发行状态);
        model.addAttribute("prodIdStr", this.getParam("prodIdStr"));
        model.addAttribute("sysDateStr", DateUtil.format(commonPlatformDateService.getSysDate(), "yyyy-MM-dd"));
        return super.init(model);
    }

    /**
     * 发行状态管理查询
     *
     * @return 发行状态管理列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryForList")
    public List<ProdInfoDto> queryForList() {
        return prodInfoService.queryForList(this.getParam("prodIdStr"));
    }

    /**
     * 发行状态逻辑check
     *
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody ProdInfoDto dto) {
        AjaxResult ar = new AjaxResult();
        List<ValidMessage> validList = new ArrayList<>();
        // 平台日
        String sysDateStr = DateUtil.format(commonPlatformDateService.getSysDate(), "yyyy-MM-dd");
        if (!StringUtil.isEmptyList(dto.getSelectedItem())) {
            for (ProdInfoDto entity : dto.getSelectedItem()) {
                if (StringUtil.isEmpty(entity.getIssuingStatusNew())) {
                    //NYLCXP-976:新发行状态是空直接提交时，提示内容有误 update by zhouqiang
                    validList.add(new ValidMessage("新发行状态不能为空！"));
                } else {
                    //若新发行状态为“发行成功”需要校验，所选数据中的子产品成立日期和平台日是否相等，若不相等，需要给出提示“子产品成立日期与平台日不相等，请确认”，
                    if (StringUtils.equals(DictCodeListExt1.发行状态_发行成功.getValue(), entity.getIssuingStatusNew())
                            && !StringUtils.equals(sysDateStr, entity.getSetDateStr())) {
                        validList.add(new ValidMessage(this.getMessage(entity.getProdName()+"【"+entity.getProdId()+"】产品，子产品成立日期与平台日不相等，请确认")));
                    }
                }
                if (StringUtil.isNotEmpty(entity.getIssueStatusRemark())) {
                    if (entity.getIssueStatusRemark().length() > 256) {
                        validList.add(new ValidMessage(this.getMessage("FW_E_0007",
                                this.getMessage("备注长度过大"), 256)));
                    }
                }
            }
        }

        if (StringUtil.isEmptyList(validList)) {
            String btnName = "";
            if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_SAVE, dto.getOperateType())) {
                btnName = "SysCommon.save";
            } else if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_SUBMIT, dto.getOperateType())) {
                btnName = "SysCommon.submit";
            }
            ar.setData(this.getMessage("COMMON_C_0001", this.getMessage(btnName)));
        } else {
            ar.setErrorList(validList);
        }

        return ar;
    }

    @ResponseBody
    @RequestMapping("updateIssuingStatus")
    @Transactional
    public AjaxResult updateIssuingStatus(@RequestBody ProdInfoDto dto) {
        AjaxResult result = new AjaxResult();
        try {
            JoyinMessage joyinMessage = new JoyinMessage("修改成功", MessageType.提示消息);
            dto.getSelectedItem().forEach(e -> {
                if (StringUtils.equals(DictCodeListExt1.发行状态_确认发行.getValue(), e.getIssueStatus())) {
                    prodInfoService.updateIssuingStatus(e);
                }
            });
            result.setJoyinMessages(Lists.newArrayList(joyinMessage));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
