/*
 * 文件名：TaxScheme006.java
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
import com.joyintech.service.valuation.taxparametermanage.taxscheme.dto.TaxScheme006Dto;
import com.joyintech.service.valuation.taxparametermanage.taxscheme.service.TaxScheme006Service;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 税务方案 - 附加税则表
 * 
 * @author zhangwei
 * @version 1.0 2018年10月10日
 * @see TaxScheme006Controller
 * @since 1.0
 */
//@RestController(value="serviceTaxScheme006Controller")
@RestController("taxScheme006Ctl_dtx")
@RequestMapping("/TaxScheme006")
public class TaxScheme006Controller extends BaseController {


    @Autowired
    private TaxScheme006Service taxScheme006Service;

    /**
     * 下拉框 ： 项目
     * 
     * @return
     * @throws Exception
     */
    @PostMapping("findProject")
    public List<SelectOptionDto> findProject(@RequestBody Map<String, Object> param)
        throws Exception {
        return taxScheme006Service.findProject(param);
    }

    /**
     * 下拉框 ： 项目
     * 
     * @return
     * @throws Exception
     */
    @PostMapping("findValue")
    public String findValue(@RequestBody Map<String, Object> param)
        throws Exception {
        return taxScheme006Service.findValue(param);
    }
    
    /**
     * 查询 资产应税信息
     * @param seqNo
     * @return
     */
    @GetMapping("/findByPrimaryKey")
    public TaxScheme006Dto findBySchId(String seqNo) {
        return taxScheme006Service.findByPrimaryKey(seqNo);
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
    public void doComplete(@RequestBody TaxScheme006Dto dto, @PathVariable("action") String action) {
        taxScheme006Service.doComplete(dto, OperationType.valueOf(action));
    }
}
