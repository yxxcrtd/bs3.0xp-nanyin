package com.joyintech.tams.disclosure.information.config.dispoireportmodelmanagechildren.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.information.config.dispoireportmodelmanagechildren.dto.PoiReportModelManageChildren001Dto;
import com.joyintech.tams.disclosure.information.config.dispoireportmodelmanagechildren.service.PoiReportModelManageChildren001Service;
import com.joyintech.tams.disclosure.information.config.dispoireportmodelmanagechildren.service.PoiReportModelManageChildren002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


/**
 * 报表子表新建Controller
 */
@Controller
@PageId("PoiReportModelManageChildren002")
@RequestMapping("/disclosure/information/config/dispoireportmodelmanagechildren/PoiReportModelManageChildren002")
public class PoiReportModelManageChildren002Controller extends BaseController {

    @Autowired
    private PoiReportModelManageChildren002Service poiReportModelManageChildren002Service;

    @Autowired
    private PoiReportModelManageChildren001Service poiReportModelManageChildren001Service;


    /**
     * 重写init方法，加载页面
     */
    @Override
    public String init(Model model) {
        Map<String, Object> map = RequestUtil.getPageParam();
        this.setSelectOptionToModel(model, DictCode.poi模板类型, DictCode.poi表格类型, DictCode.poi表格样式,
                DictCode.poi图表类型, DictCode.poi表格宽度, DictCode.维护数据状态);
        //pageMode不为空且其不为新建时
        if (map.containsKey(GlobalConstant.DETAIL_PAGE_MODEL_KEY) &&
                !StringUtil.equals(map.get(GlobalConstant.DETAIL_PAGE_MODEL_KEY), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            PoiReportModelManageChildren001Dto entity = poiReportModelManageChildren002Service.selectById(map);
            model.addAttribute("entity", entity);
            model.addAttribute("subDefualt", entity.getSubDefualt());
        }
        return super.init(model);
    }

    /**
     * 保存按钮点击时的check
     *
     * @param dto
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult Check(@RequestBody @Validated({ValidateGroupEdit.class}) PoiReportModelManageChildren001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }


    /**
     * 获取模板编号下拉框初始化数据
     *
     * @return
     */
    @RequestMapping("queryTabNumList")
    @ResponseBody
    public List<SelectOptionDto> queryTabNumList() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return poiReportModelManageChildren001Service.queryTabNumList(param);
    }


    /**
     * 保存提交
     *
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping("doSave")
    public AjaxResult doSave(@RequestBody PoiReportModelManageChildren001Dto dto) {
        poiReportModelManageChildren002Service.doSave(dto);
        return new AjaxResult(this.getMessage("REPORT_COMMON_I_0006"));
    }


}
