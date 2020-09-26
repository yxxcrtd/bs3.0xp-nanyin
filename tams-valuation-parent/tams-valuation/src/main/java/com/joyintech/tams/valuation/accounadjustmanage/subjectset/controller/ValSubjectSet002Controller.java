/*
 * 文件名：ValSubjectSet002.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.subjectset.controller;

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
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.dao.BokSubjectMapper;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.accounadjustmanage.subjectset.dto.ValBokSubjectDto;
import com.joyintech.tams.valuation.accounadjustmanage.subjectset.service.ValSubjectSet001Service;

/**
 * 模板示例<br>
 * 账套管理
 * 
 * @author 何阳阳
 * @version 1.0 2018年3月13日
 * @see ValSubjectSet002Controller
 * @since 1.0
 */
@Controller
@PageId("ValSubjectSet002")
@RequestMapping("/valuation/accounadjustmanage/subjectset/ValSubjectSet002")
public class ValSubjectSet002Controller extends BaseController {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    @Autowired
    private ValSubjectSet001Service valSubjectSet001Service;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
//    @Autowired
//    private BokSubjectMapper valBokSubjectMapper;
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.余额方向, DictCode.是否, DictCode.科目类型);
        String booksetId = getParam("booksetId");
        String subjectNo = getParam("subjectNo");
        if (StringUtil.isNotEmpty(booksetId) && StringUtil.isNotEmpty(subjectNo)) {
            ValBokSubjectDto valBokSubjectDto = valSubjectSet001Service.selectValBokSubject(
                booksetId, subjectNo);
            model.addAttribute("entity", valBokSubjectDto);
        }
        return super.init(model);
    }
    /**
     * 保存按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @param operateType 操作类型
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) ValBokSubjectDto dto, BindingResult result) {
        return commonCheck(result, "save");
    }
    /**
     * 保存科目
     * 
     * @param dto 保存的数据
     * @param br check结果
     * @return 保存结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) ValBokSubjectDto dto, BindingResult br){
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        
        valSubjectSet001Service.save(dto);
//        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
//            valBokSubjectMapper.updateByPrimaryKey(dto);
//        }
//        else{
//            valBokSubjectMapper.insert(dto);
//        }
        return new AjaxResult(getMessage("COMMON_I_0011"));
    }
}
