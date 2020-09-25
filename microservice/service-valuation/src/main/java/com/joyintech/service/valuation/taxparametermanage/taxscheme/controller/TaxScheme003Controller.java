/*
 * 文件名：TaxScheme003.java
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
import com.joyintech.service.valuation.taxparametermanage.taxscheme.dto.TaxScheme003Dto;
import com.joyintech.service.valuation.taxparametermanage.taxscheme.service.TaxScheme003Service;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 税务方案 - 增值税则表
 * 
 * @author zhangwei
 * @version 1.0 2018年10月10日
 * @see TaxScheme003Controller
 * @since 1.0
 */
//@RestController(value="serviceTaxScheme003Controller")
@RestController("taxScheme003Ctl_dtx")
@RequestMapping("/TaxScheme003")
public class TaxScheme003Controller extends BaseController {

    @Autowired
    private TaxScheme003Service taxScheme003Service;

    @GetMapping("/findByPrimaryKey")
    public TaxScheme003Dto findBySchId(String seqNo) {
        return taxScheme003Service.findByPrimaryKey(seqNo);
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
    @PostMapping(value = "/doComplete/{action}")
    public void doComplete(@RequestBody TaxScheme003Dto dto, @PathVariable("action") String action) {
       taxScheme003Service.doComplete(dto, OperationType.valueOf(action));
    }
    
	/**
	 * 增值税则表check(去除本金收益特征不能为空的校验后添加本方法)
	 * 
	 * @return
	 * @throws Exception
	 */
    @PostMapping("/checkVatTax")
	public List<TaxScheme003Dto> checkVatTax(@RequestBody Map<String, Object> param) throws Exception {
		return taxScheme003Service.checkVatTax(param);
	}
}
