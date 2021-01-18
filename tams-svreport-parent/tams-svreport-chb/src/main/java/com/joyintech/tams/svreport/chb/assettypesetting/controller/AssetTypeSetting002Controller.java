package com.joyintech.tams.svreport.chb.assettypesetting.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
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

import java.util.List;

@Controller
@PageId("AssetTypeSetting002")
@RequestMapping("/chb/assettypesetting/AssetTypeSetting002")
public class AssetTypeSetting002Controller extends BaseController {
    @Autowired
    private AssetTypeSettingService assetTypeSettingService;

    /**
     * 初始化新建页面
     * @param model
     * @return
     */
    @Override
    public String init(Model model) {
        super.setSelectOptionToModel(model, DictCode.宁波资产一级分类,DictCode.宁波资产二级分类,
                DictCode.宁波资产三级分类,DictCode.宁波业务类型,DictCode.资产_负债类别,DictCode.资产_负债二级类别);
        String pageModel = this.getParam("pageModel");
        String typeuuid = this.getParam("typeuuid");
        if (pageModel.equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
        }
        if (pageModel.equals(GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
            AssetTypeSetting001Dto dto = assetTypeSettingService.selectEntityBySearchMap(typeuuid);
            model.addAttribute(GlobalConstant.ENTITY,dto);
        }
        return super.init(model);
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
     * 新建保存
     * @param dto
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = PAGE_SAVE, method = RequestMethod.POST)
    public AjaxResult doAdd(@RequestBody @Validated({
            ValidateGroupEdit.class})AssetTypeSetting001Dto dto , BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return new AjaxResult();
        }
        assetTypeSettingService.doSave(dto);
        return new AjaxResult("提交成功");
    }

    /**
     * 分页查询
     * @return
     */
    @ResponseBody
    @RequestMapping("findExPage")
    public List<AssetTypeSetting001Dto> findExPage() {
        List<AssetTypeSetting001Dto> page = assetTypeSettingService.findExPage(RequestUtil.getPageParam());
        return page;
    }

}
