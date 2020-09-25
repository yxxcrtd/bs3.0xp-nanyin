/*
 * 文件名：TaxScheme005.java
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.taxparametermanage.taxscheme.dto.TaxScheme005Dto;
import com.joyintech.service.valuation.taxparametermanage.taxscheme.service.TaxScheme005Service;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 税务方案 - 资产应信息
 * 
 * @author zhangwei
 * @version 1.0 2018年10月10日
 * @see TaxScheme005Controller
 * @since 1.0
 */
//@RestController(value="serviceTaxScheme005Controller")
@RestController("taxScheme005Ctl_dtx")
@RequestMapping("/TaxScheme005")
public class TaxScheme005Controller extends BaseController {

    @Autowired
    private TaxScheme005Service taxScheme005Service;

    /**
     * 下拉框 ： 资产代码
     * 
     * @return
     * @throws Exception
     */
    @PostMapping("/findAsset")
    public List<SelectOptionDto> findAsset(@RequestBody Map<String, Object> param)
        throws Exception {
        return taxScheme005Service.findAsset(param);
    }

    /**
     * 通过资产代码，获取资产信息
     * 
     * @return
     * @throws Exception
     */
    @PostMapping("getAssetInfo")
    public TaxScheme005Dto getAssetInfo(@RequestBody Map<String, Object> param)
        throws Exception {
        return taxScheme005Service.getAssetInfo(param);
    }
    
    /**
     * 查询 资产应税信息
     * @param seqNo
     * @return
     */
    @GetMapping("/findByPrimaryKey")
    public TaxScheme005Dto findBySchId(String seqNo) {
        return taxScheme005Service.findByPrimaryKey(seqNo);
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
    public void doComplete(@RequestBody TaxScheme005Dto dto, @PathVariable("action") String action) {
        taxScheme005Service.doComplete(dto, OperationType.valueOf(action));
    }
}
