package com.joyintech.tams.disclosure.information.config.dispoiquery.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.information.config.dispoiquery.dto.DisPoiQuery001Dto;
import com.joyintech.tams.disclosure.information.config.dispoiquery.service.DisPoiQuery001Service;
import com.joyintech.tams.disclosure.information.config.dispoiquery.service.DisPoiQuery002Service;
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
 * poi查询sql管理新建Controller
 *
 * @author kx
 */
@Controller
@PageId("DisPoiQuery002")
@RequestMapping("/disclosure/information/config/dispoiquery/DisPoiQuery002")
public class DisPoiQuery002Controller extends BaseController {

    @Autowired
    private DisPoiQuery002Service disPoiQuery002Service;

    @Autowired
    private DisPoiQuery001Service disPoiQuery001Service;

    /**
     * 重写init方法，加载页面
     */
    @Override
    public String init(Model model) {
        Map<String, Object> map = RequestUtil.getPageParam();
        this.setSelectOptionToModel(model, DictCode.查询条件类型);
        //pageMode不为空且其不为新建时
        if(map.containsKey(GlobalConstant.DETAIL_PAGE_MODEL_KEY)&&
                !StringUtil.equals(map.get(GlobalConstant.DETAIL_PAGE_MODEL_KEY), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            DisPoiQuery001Dto entity = disPoiQuery002Service.selectById(map);
            model.addAttribute("entity", entity);
            model.addAttribute("poiSql", entity.getPoiSql());
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
    public AjaxResult Check(@RequestBody @Validated({ValidateGroupEdit.class}) DisPoiQuery001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
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
     * 保存提交
     *
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping("doSave")
    public AjaxResult doSave(@RequestBody DisPoiQuery001Dto dto) {
        disPoiQuery002Service.doSave(dto);
        return new AjaxResult(this.getMessage("REPORT_COMMON_I_0006"));
    }


}
