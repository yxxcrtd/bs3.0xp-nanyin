/*
 * 文件名：TaxScheme007.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.taxparametermanage.taxscheme.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.taxparametermanage.taxscheme.dto.TaxScheme007Dto;
import com.joyintech.service.valuation.taxparametermanage.taxscheme.service.TaxScheme001Service;
import com.joyintech.service.valuation.taxparametermanage.taxscheme.service.TaxScheme007Service;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 税务方案 - 会计场景计税配置
 *
 * @author zhangwei
 * @version 1.0 2018年10月10日
 * @see TaxScheme007Controller
 * @since 1.0
 */
//@RestController(value = "serviceTaxScheme007Controller")
@RestController("taxScheme007Ctl_dtx")
@RequestMapping("/TaxScheme007")
public class TaxScheme007Controller extends BaseController {

    @Autowired
    private TaxScheme007Service taxScheme007Service;

    /**
     * 下拉框 ： 基础科目性质
     *
     * @return
     */
    @PostMapping("/getBaseSubjectNature/{page}/{length}")
    public Page<SelectOptionDto> getBaseSubjectNature(@RequestBody Map<String, Object> param,
                                                      @PathVariable("page") int page,
                                                      @PathVariable("length") int length) {
        return taxScheme007Service.getBaseSubjectNature(page, length, param);
    }

    /**
     * 下拉框 ： 基础科目性质
     *
     * @return
     */
    @PostMapping("/getBaseSubjectNature")
    public List<SelectOptionDto> getBaseSubjectNature(@RequestBody Map<String, Object> param) {
        return taxScheme007Service.getBaseSubjectNature(param);
    }

    /**
     * 查询 会计场景计税配置 - 关联
     *
     * @param configId
     * @return
     */
    @GetMapping("/findByPrimaryKey")
    public TaxScheme007Dto findBySchId(String seqNo) {
        return taxScheme007Service.findByPrimaryKey(seqNo);
    }

    /**
     * 查询 会计场景计税配置
     *
     * @param configId
     * @return
     */
    @GetMapping("/getConfigInfoByKey")
    public TaxScheme007Dto getConfigInfoByKey(String configId) {
        return taxScheme007Service.getConfigInfoByKey(configId);
    }

    /**
     * 查询 会计场景计税配置
     *
     * @param configId
     * @return
     */
    @PostMapping("/getConfigInfo/{page}/{length}")
    public Page<SelectOptionDto> getConfigInfo(@RequestBody Map<String, Object> param,
                                               @PathVariable("page") int page,
                                               @PathVariable("length") int length) {
        return taxScheme007Service.getConfigInfo(page, length, param);
    }

    /**
     * 流程完成后的操作
     *
     * @param dto 业务实体
     * @return 画面地址
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping(value = "doComplete/{action}")
    public void doComplete(@RequestBody TaxScheme007Dto dto,
                           @PathVariable("action") String action) {
        taxScheme007Service.doComplete(dto, OperationType.valueOf(action));
    }
}
