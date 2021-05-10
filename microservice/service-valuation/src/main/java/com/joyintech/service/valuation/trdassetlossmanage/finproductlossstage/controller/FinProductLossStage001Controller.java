package com.joyintech.service.valuation.trdassetlossmanage.finproductlossstage.controller;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.service.valuation.trdassetlossmanage.finproductlossstage.service.FinProductLossStage001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.joyintech.service.valuation.trdassetlossmanage.finproductlossstage.dto.FinProductLossStage001Dto;

import java.util.List;
import java.util.Map;

@RestController(value="serviceFinProductLossStage001Controller")
@RequestMapping("/FinProductLossStage001")
public class FinProductLossStage001Controller extends BaseController {

    @Autowired
    private FinProductLossStage001Service finProductLossStage001Service;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model,DictCode.减值阶段);
        return super.init(model);
    }

    /**
     * 画面查询方法
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage/{page}/{length}")
    public Page<FinProductLossStage001Dto> queryForPage(@RequestBody Map<String, Object> param,
                                                    @PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception {
        return finProductLossStage001Service.queryPageList(page, length, param);
    }

    /**
     * 查询所有数据，不进行分页
     */
    @ResponseBody
    @RequestMapping("getAllList")
    public List<FinProductLossStage001Dto> getAllList(@RequestBody Map<String, Object> params) throws Exception{
        return finProductLossStage001Service.getAllList(params);
    }

    /**
     * 查询资产名称
     */
    @ResponseBody
    @RequestMapping("getFinprodName/{page}/{length}")
    public Page<SelectOptionDto> getFinprodName(@RequestBody Map<String, Object> param,
                                                @PathVariable("page") int page, @PathVariable("length") int length){
        Page<SelectOptionDto> finprodName = finProductLossStage001Service.getFinprodName(page, length,param);
        return finprodName;
    }

    /**
     * 查询账套日期
     */
    @ResponseBody
    @RequestMapping("getBooksetDate")
    public FinProductLossStage001Dto getBooksetDate(@RequestBody FinProductLossStage001Dto dto){
        return finProductLossStage001Service.getBooksetDate(dto);
    }

    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping("deleteFinProLosSta")
    public int deleteFinProLosSta(@RequestBody FinProductLossStage001Dto dto){
        return finProductLossStage001Service.deleteFinProLosSta(dto);
    }

    /**
     * 查询上一条有效的的资产减值阶段对象
     */
    @ResponseBody
    @RequestMapping("getPrevValidObj")
    public List<FinProductLossStage001Dto> getPrevValidObj(@RequestBody FinProductLossStage001Dto dto){
        return finProductLossStage001Service.getPrevValidObj(dto);
    }

    /**
     * 查询下一条有效的的资产减值阶段对象
     */
    @ResponseBody
    @RequestMapping("getNextValidObj")
    public List<FinProductLossStage001Dto> getNextValidObj(@RequestBody FinProductLossStage001Dto dto){
        return finProductLossStage001Service.getNextValidObj(dto);
    }
}
