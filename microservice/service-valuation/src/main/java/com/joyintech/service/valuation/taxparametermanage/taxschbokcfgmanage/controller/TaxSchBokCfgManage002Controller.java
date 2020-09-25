/*
 * 文件名：TaxScheme007.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.taxparametermanage.taxschbokcfgmanage.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.taxparametermanage.taxschbokcfgmanage.dto.TaxSchBokCfgManage002Dto;
import com.joyintech.service.valuation.taxparametermanage.taxschbokcfgmanage.service.TaxSchBokCfgManage002Service;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 税务方案 - 附加税则表
 *
 * @author zhangwei
 * @version 1.0 2018年10月10日
 * @see TaxSchBokCfgManage002Controller
 * @since 1.0
 */
@RestController(value = "taxSchBokCfgManage002Ctl_dtx")
@RequestMapping("/TaxSchBokCfgManage002")
public class TaxSchBokCfgManage002Controller extends BaseController {
    
    @Autowired
    private TaxSchBokCfgManage002Service taxSchBokCfgManage002Service;

    /**
     * 下拉框 ： 基础科目性质
     *
     * @return
     */
    @PostMapping("/getBaseSubjectNature/{page}/{length}")
    public Page<SelectOptionDto> getBaseSubjectNature(@RequestBody Map<String, Object> param,
                                                      @PathVariable("page") int page, @PathVariable("length") int length) {
        return taxSchBokCfgManage002Service.getBaseSubjectNature(page, length, param);
    }


    /**
     * 查询 资产应税信息
     *
     * @param configId
     * @return
     */
    @GetMapping("/findByPrimaryKey")
    public TaxSchBokCfgManage002Dto findBySchId(String configId) {
        return taxSchBokCfgManage002Service.findByPrimaryKey(configId);
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
    public void doComplete(@RequestBody TaxSchBokCfgManage002Dto dto, @PathVariable("action") String action) {
        taxSchBokCfgManage002Service.doComplete(dto, OperationType.valueOf(action));
    }
}
