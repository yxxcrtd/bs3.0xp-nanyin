package com.joyintech.tams.disclosure.information.config.dispoiquery.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.information.config.dispoiquery.dto.DisPoiQuery001Dto;
import com.joyintech.tams.disclosure.information.config.dispoiquery.service.DisPoiQuery001Service;
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
 * poi查询sql管理Controller
 *
 * @author kx
 */
@Controller
@PageId("DisPoiQuery001")
@RequestMapping("/disclosure/information/config/dispoiquery/DisPoiQuery001")
public class DisPoiQuery001Controller extends BaseController {

    @Autowired
    private DisPoiQuery001Service disPoiQuery001Service;


    /**
     * 重写init方法，加载页面
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.查询条件类型);
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
    public Page<DisPoiQuery001Dto> searchPage() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return disPoiQuery001Service.searchPage(param);
    }


    /**
     * 获取关联编号下拉框初始化数据
     *
     * @return
     */
    @RequestMapping("queryPoiIdList")
    @ResponseBody
    public List<SelectOptionDto> queryPoiIdList()  {
        Map<String, Object> param = RequestUtil.getPageParam();
        return disPoiQuery001Service.queryPoiIdList(param);
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
    public AjaxResult Check(@RequestBody @Validated({ValidateGroupEdit.class}) DisPoiQuery001Dto dto, BindingResult result) {
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
    public AjaxResult doDelete(@RequestBody DisPoiQuery001Dto dto) {
        AjaxResult result = new AjaxResult(this.getMessage("REPORT_COMMON_I_0007"));
        disPoiQuery001Service.doDelete(dto);
        return result;
    }


}
