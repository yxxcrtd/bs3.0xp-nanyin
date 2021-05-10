package com.joyintech.service.valuation.trdassetlossmanage.finproductlossstage.controller;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.trdassetlossmanage.finproductlossstage.dto.FinProductLossStage001Dto;
import com.joyintech.service.valuation.trdassetlossmanage.finproductlossstage.service.FinProductLossStage002Service;
import com.joyintech.tams.constant.DictCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController(value="serviceFinProductLossStage002Controller")
@RequestMapping("/FinProductLossStage002")
public class FinProductLossStage002Controller extends BaseController {

    @Autowired
    private FinProductLossStage002Service finProductLossStage002Service;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model,DictCode.减值阶段);
        return super.init(model);
    }

    /**
     * 插入数据
     */
    @ResponseBody
    @RequestMapping("saveFinProLosSta")
    public int saveFinProLosSta(@RequestBody FinProductLossStage001Dto dto){
        return finProductLossStage002Service.saveFinProLosSta(dto);
    }

    /**
     * 更新失效日期
     */
    @ResponseBody
    @RequestMapping("updateInvalidDate")
    public int updateInvalidDate(@RequestBody FinProductLossStage001Dto dto){
        return finProductLossStage002Service.updateInvalidDate(dto);
    }

    /**
     * 编辑
     */
    @ResponseBody
    @RequestMapping("editFinProLosSta")
    public int editFinProLosSta(@RequestBody FinProductLossStage001Dto dto){
        return finProductLossStage002Service.editFinProLosSta(dto);
    }

}
