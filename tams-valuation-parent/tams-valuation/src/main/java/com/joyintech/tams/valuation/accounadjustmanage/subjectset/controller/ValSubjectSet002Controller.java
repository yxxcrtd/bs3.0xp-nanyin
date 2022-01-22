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

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.accounadjustmanage.assistaccout.dto.ValAssistHandDto;
import com.joyintech.tams.valuation.accounadjustmanage.assistaccout.service.ValAssistAccounting001Service;
import com.joyintech.tams.valuation.accounadjustmanage.subjectset.dto.ValBokSubjectDto;
import com.joyintech.tams.valuation.accounadjustmanage.subjectset.service.ValSubjectSet001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    @Autowired
    private ValAssistAccounting001Service valAssistAccounting001Service;
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
//    @Autowired
//    private BokSubjectMapper valBokSubjectMapper;
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.余额方向, DictCode.是否, DictCode.科目类型,DictCode.币种,
                DictCode.辅助核算_证券大类,DictCode.辅助核算_证券分类,DictCode.辅助核算_交易市场,DictCode.辅助核算_发行方式,DictCode.辅助核算_交易对手类型,
                DictCode.辅助核算_账户类型,DictCode.辅助核算_费用类型,DictCode.辅助核算_交易属性,DictCode.辅助核算_税费类型
        );
        String booksetId = getParam("booksetId");
        String subjectNo = getParam("subjectNo");
        if (StringUtil.isNotEmpty(booksetId) && StringUtil.isNotEmpty(subjectNo)) {
            ValBokSubjectDto valBokSubjectDto = valSubjectSet001Service.selectValBokSubject(
                booksetId, subjectNo);
//            ValAssistHandDto dto=new ValAssistHandDto();
//            dto.setBooksetId(valBokSubjectDto.getBooksetId());
//            dto.setSubjectInternalCode(valBokSubjectDto.getSubjectInternalCode());
//            dto.setStatus("Y");
//            List<ValAssistHandDto> hands=valAssistAccounting001Service.findHandsByCondition(dto);
//            String handstr="";
//            hands.forEach(i->{
//                handstr=handstr+i.getHandCode();
//            });
//            valBokSubjectDto.setHands(handstr);

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
        //处理辅助核算项


        valSubjectSet001Service.save(dto);
//        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
//            valBokSubjectMapper.updateByPrimaryKey(dto);
//        }
//        else{
//            valBokSubjectMapper.insert(dto);
//        }
        return new AjaxResult(getMessage("COMMON_I_0011"));
    }

    @ResponseBody
    @RequestMapping("findHandsByCondition")
    public List<ValAssistHandDto> findHandsByCondition() {
        return valAssistAccounting001Service.findHandsByCondition(RequestUtil.getPageParam());
    }
}
