package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valbasicsubadjust.controller;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valaccounthandle.service.ValAccountHandle001Service;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valbasicsubadjust.dto.ValBasicSubAdjust001Dto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valbasicsubadjust.service.ValBasicSubAdjust001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController("ValBasicSubAdjust001Ctl_dtx")
@RequestMapping("/ValBasicSubAdjust001")
public class ValBasicSubAdjust001Controller extends BaseController {
    @Autowired
    private ValBasicSubAdjust001Service valBasicSubAdjust001Service;
    @Autowired
    private ValAccountHandle001Service valAccountHandle001Service;

    @Override
    public String init(Model model) {
        return super.init(model);
    }

    /**
     * 查询账套代码
     */
    @ResponseBody
    @RequestMapping("selectBooksetIds/{page}/{length}")
    public Page<SelectOptionDto> selectBooksetIds(@RequestBody Map<String, Object> param,
                                                 @PathVariable("page") int page, @PathVariable("length") int length){
        Page<SelectOptionDto> booksetIds = valAccountHandle001Service.getBookSetId2(page,length,param);
        return booksetIds;
    }

    /**
     * 查询科目号
     */
    @ResponseBody
    @RequestMapping("selectSubjectNo/{page}/{length}")
    public Page<SelectOptionDto> selectSubjectNo(@RequestBody Map<String, Object> param,
                                                 @PathVariable("page") int page, @PathVariable("length") int length){
        Page<SelectOptionDto> subjectNo = valBasicSubAdjust001Service.selectSubjectNo(page, length,param);
        return subjectNo;
    }

    /**
     * 查询基础科目性质
     */
    @ResponseBody
    @RequestMapping("selectBaseSubjectNature/{page}/{length}")
    public Page<SelectOptionDto> selectBaseSubjectNature(@RequestBody Map<String, Object> param,
                                                 @PathVariable("page") int page, @PathVariable("length") int length){
        Page<SelectOptionDto> baseSubjectNature = valBasicSubAdjust001Service.selectBaseSubjectNature(page, length,param);
        return baseSubjectNature;
    }

    /**
     * 账务历史表数据更新
     */
    @ResponseBody
    @RequestMapping("updateBokBookdetailAll")
    public int updateBokBookdetailAll(@RequestBody ValBasicSubAdjust001Dto dto){
        return valBasicSubAdjust001Service.updateBokBookdetailAll(dto);
    }

    /**
     * 账务当前表数据更新
     */
    @ResponseBody
    @RequestMapping("updateBokBookdetailCurrent")
    public int updateBokBookdetailCurrent(@RequestBody ValBasicSubAdjust001Dto dto){
        return valBasicSubAdjust001Service.updateBokBookdetailCurrent(dto);
    }

    /**
     * 明细科目数据更新
     */
    @ResponseBody
    @RequestMapping("updateBokSubjectDetail")
    public int updateBokSubjectDetail (@RequestBody ValBasicSubAdjust001Dto dto){
        return valBasicSubAdjust001Service.updateBokSubjectDetail(dto);
    }
}
