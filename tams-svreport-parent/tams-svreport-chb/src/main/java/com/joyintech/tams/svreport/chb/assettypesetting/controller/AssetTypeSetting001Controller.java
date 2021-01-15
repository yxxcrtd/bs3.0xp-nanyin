package com.joyintech.tams.svreport.chb.assettypesetting.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.svreport.chb.assettypesetting.dto.AssetTypeSetting001Dto;
import com.joyintech.tams.svreport.chb.assettypesetting.service.AssetTypeSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@PageId("AssetTypeSetting001")
@RequestMapping("/chb/assettypesetting/AssetTypeSetting001")
public class AssetTypeSetting001Controller extends BaseController {
    @Autowired
    private AssetTypeSettingService assetTypeSettingService;

    /**
     * 初始化 controller
     * @param model
     * @return
     */
    @Override
    public String init(Model model) {
        super.setSelectOptionToModel(model, DictCode.宁波资产一级分类,
                DictCode.宁波资产二级分类,DictCode.宁波资产三级分类,DictCode.宁波业务类型);
        return super.init(model);
    }

    /**
     * 分页查询
     * @return
     */
    @ResponseBody
    @RequestMapping("selectForPage")
    public Page<AssetTypeSetting001Dto> selectForPage() {
        Page<AssetTypeSetting001Dto> page = assetTypeSettingService.selectForPage(RequestUtil.getPageParam());
        return page;
    }

    /**
     * 点击左侧列表数据事件
     * @return
     */
    @ResponseBody
    @RequestMapping("selectById")
    public Page<AssetTypeSetting001Dto> selectById() {
        Page<AssetTypeSetting001Dto> page = assetTypeSettingService.selectById(RequestUtil.getPageParam());
        return page;
    }

    /**
     * 共通校验
     * @param dto
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) AssetTypeSetting001Dto dto, BindingResult result) {
        return commonCheck(result, null);
    }

    /**
     * 删除(左侧全删全插)
     * @param dto
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = ACTION_DELETE, method = RequestMethod.POST)
    public AjaxResult doDelete(@RequestBody AssetTypeSetting001Dto dto) throws Exception {
        assetTypeSettingService.doDelete(dto);
        return new AjaxResult("删除成功");
    }

    /**
     * 删除右侧列表数据
     * @param dto
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("doExDelete")
    public AjaxResult doExDelete(@RequestBody AssetTypeSetting001Dto dto) throws Exception {
        assetTypeSettingService.doExDelete(dto);
        return new AjaxResult("删除成功");
    }
}
