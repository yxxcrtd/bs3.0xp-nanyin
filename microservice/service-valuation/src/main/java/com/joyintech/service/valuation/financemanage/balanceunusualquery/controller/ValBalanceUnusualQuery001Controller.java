/*
 * 文件名：ValBalanceUnusualQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2019年1月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.financemanage.balanceunusualquery.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.service.valuation.financemanage.balanceunusualquery.dto.ValBalanceUnusualQuery001Dto;
import com.joyintech.service.valuation.financemanage.balanceunusualquery.service.ValBalanceUnusualQuery001Service;
import com.joyintech.tams.valuation.common.service.BaseCommonService;

/**
 * 存续产品平仓资产余额异常查询
 * 
 * @author HuangJian
 * @version 1.0 2019年1月31日
 * @see ValBalanceUnusualQuery001Controller
 * @since 1.0
 */

@RestController("backValBalanceUnusualQuery001Controller")
@RequestMapping("/ValBalanceUnusualQuery001")
public class ValBalanceUnusualQuery001Controller extends BaseController {
    
    @Autowired
    private ValAssetBook001Service valAssetBook001Service;
    
    @Autowired
    private ValBalanceUnusualQuery001Service valBalanceUnusualQuery001Service;

    
    @Autowired
    private BaseCommonService baseCommonService;

    
    /**
     * 分页数据查询
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
   
    @PostMapping("/findPage")
    public List<ValBalanceUnusualQuery001Dto> findPage(@RequestBody Map<String, Object> params)
        throws Exception {
        return valBalanceUnusualQuery001Service.getAllList(params);
    }
    
    
}
