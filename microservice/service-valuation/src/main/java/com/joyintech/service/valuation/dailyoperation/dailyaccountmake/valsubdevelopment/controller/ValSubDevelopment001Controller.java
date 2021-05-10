package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valsubdevelopment.controller;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valsubdevelopment.dto.ValSubDevelopment001Dto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valsubdevelopment.service.ValSubDevelopment001Service;
import com.joyintech.tams.common.base.entity.BokBussDetsub;
import com.joyintech.tams.valuation.common.auxiliary.dto.BokBookdetailCurrentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController("ValSubDevelopment001Ctl_dtx")
@RequestMapping("/ValSubDevelopment001")
public class ValSubDevelopment001Controller extends BaseController {
    @Autowired
    private ValSubDevelopment001Service valSubDevelopment001Service;

    @Override
    public String init(Model model) {
        return super.init(model);
    }

    /**
     * 查询账套模板
     */
    @ResponseBody
    @RequestMapping("selectBooksetId/{page}/{length}")
    public Page<SelectOptionDto> selectBooksetId(@RequestBody Map<String, Object> param,
                                                @PathVariable("page") int page, @PathVariable("length") int length){
        Page<SelectOptionDto> booksetId = valSubDevelopment001Service.selectBooksetId(page, length,param);
        return booksetId;
    }

    /**
     * 查询带扩展科目号
     * @return
     */
    @ResponseBody
    @RequestMapping("selectThirdSubjectId/{page}/{length}")
    public Page<SelectOptionDto> selectThirdSubjectId(@RequestBody Map<String, Object> param,
                                                 @PathVariable("page") int page, @PathVariable("length") int length){
        Page<SelectOptionDto> thirdSubjectId = valSubDevelopment001Service.selectThirdSubjectId(page, length,param);
        return thirdSubjectId;
    }

    // 删除业务明细科目表(BOK_BUSS_DETSUB)
    @ResponseBody
    @RequestMapping("deleteBokBussDetSub")
    public int deleteBokBussDetSub(@RequestBody Map<String, Object> param){
        return valSubDevelopment001Service.deleteBokBussDetSub(param);
    }

    // 插入删除的数据
    @ResponseBody
    @RequestMapping("insertBokBussDetSub")
    public int insertBokBussDetSub(@RequestBody BokBussDetsub sub){
        return valSubDevelopment001Service.insertBokBussDetSub(sub);
    }

    // 更新账务明细代码表(BOK_DETAIL_ID)
    @ResponseBody
    @RequestMapping("updateBokDetailId")
    public int updateBokDetailId(@RequestBody BokBookdetailCurrentDto dto){
        return valSubDevelopment001Service.updateBokDetailId(dto);
    }

    // 更新账务明细历史表(BOK_BOOKDETAIL_ALL)
    @ResponseBody
    @RequestMapping("updateBokBookDetailAll")
    public int updateBokBookDetailAll(@RequestBody BokBookdetailCurrentDto dto){
        return valSubDevelopment001Service.updateBokBookDetailAll(dto);
    }

    // 更新账务明细当前表(BOK_BOOKDETAIL_CURRENT)
    @ResponseBody
    @RequestMapping("updateBokBookDetailCurrent")
    public int updateBokBookDetailCurrent(@RequestBody BokBookdetailCurrentDto dto){
        return valSubDevelopment001Service.updateBokBookDetailCurrent(dto);
    }
}
