package com.joyintech.service.product.rddesgin.feasibilityanalysis.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.product.rddesgin.feasibilityanalysis.dto.FinProductFeaAnaDto;
import com.joyintech.service.product.rddesgin.feasibilityanalysis.service.FinProductFeaAnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 产品研发设计-可行性分析
 *
 * @author guyunfei
 * @version 1.0 2019年9月24日
 */
@RestController("FinProductFeaAnaController_dtx")
@RequestMapping("/FinProductFeaAna")
public class FinProductFeaAnaController {

    @Autowired
    private FinProductFeaAnaService finProductFeaAnaService;

    /**
     * 画面查询方法
     * @param params
     * @param page
     * @param length
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectForPage/{page}/{length}")
    public Page<FinProductFeaAnaDto> selectForPage(@RequestBody Map<String,Object> params,
        @PathVariable("page") int page, @PathVariable("length") int length)throws Exception{
        return finProductFeaAnaService.selectForPage(page,length,params);
    }

    @ResponseBody
    @RequestMapping("/queryByRequirementId")
    public FinProductFeaAnaDto queryByRequirementId(String id){
        return finProductFeaAnaService.queryByRequirementId(id);
    }

    /**
     * 维护分析结论、可行需求分类
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    @Transactional
    @DTXTransaction
    public Integer update(@RequestBody FinProductFeaAnaDto dto){
        return finProductFeaAnaService.update(dto);
    }

    @ResponseBody
    @RequestMapping("/delete")
    @Transactional
    @DTXTransaction
    public Integer delete(String requirementId){
        return finProductFeaAnaService.delete(requirementId);
    }
}
