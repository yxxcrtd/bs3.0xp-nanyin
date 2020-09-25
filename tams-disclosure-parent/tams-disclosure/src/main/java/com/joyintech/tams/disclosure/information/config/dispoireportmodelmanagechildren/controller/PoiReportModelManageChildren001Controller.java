package com.joyintech.tams.disclosure.information.config.dispoireportmodelmanagechildren.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.information.config.dispoireportmodelmanagechildren.dto.PoiReportModelManageChildren001Dto;
import com.joyintech.tams.disclosure.information.config.dispoireportmodelmanagechildren.service.PoiReportModelManageChildren001Service;
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
 * 报表子表Controller
 *
 * @author kx
 */
@Controller
@PageId("PoiReportModelManageChildren001")
@RequestMapping("/disclosure/information/config/dispoireportmodelmanagechildren/PoiReportModelManageChildren001")
public class PoiReportModelManageChildren001Controller extends BaseController {

    @Autowired
    private PoiReportModelManageChildren001Service poiReportModelManageChildren001Service;

    /**
     * 重写init方法，加载页面
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.poi模板类型, DictCode.poi表格类型, DictCode.poi表格样式,
                DictCode.poi图表类型, DictCode.poi表格宽度, DictCode.维护数据状态);
        return super.init(model);
    }

    /**
     * 业务画面分页查询事件
     * <功能详细描述>
     *
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("searchPage")
    public Page<PoiReportModelManageChildren001Dto> searchPage() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return poiReportModelManageChildren001Service.searchPage(param);
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
     * 编辑删除前的check
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
     * 根据主键删除数据
     *
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping("delete")
    public AjaxResult doDelete(@RequestBody PoiReportModelManageChildren001Dto dto) {
        AjaxResult result = new AjaxResult(this.getMessage("REPORT_COMMON_I_0007"));
        poiReportModelManageChildren001Service.doDelete(dto);
        return result;
    }


}
