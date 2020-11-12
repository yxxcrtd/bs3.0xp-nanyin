/*
 * 文件名：FinStageManagementController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年3月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.service.asset.common.dto.FinStageManagementDto;
import com.joyintech.service.asset.common.service.FinStageManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 期次管理
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年3月12日
 * @see FinStageManagementController
 * @since 1.0
 */
@RestController("FinStageManagementController_dtx")
@RequestMapping("/FinStageManagement")
public class FinStageManagementController {

    @Autowired
    private FinStageManagementService finStageManagementService;
    
    @PostMapping("getRates")
    public Map<String,Object> getRates(@RequestBody Map<String, Object> map){
        return finStageManagementService.getRates(map);
    }
    
    @PostMapping("getfinStageManagementByProductId")
    public List<FinStageManagementDto> getfinStageManagementByProductId(@RequestBody Map<String, Object> param) throws Exception {
        return finStageManagementService.getfinStageManagementByProductId(param);
    }

    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public void insert(@RequestBody FinStageManagementDto dto) throws Exception {
        finStageManagementService.insert(dto);
    }

    /**
     * 校验有没子期次做过到期，如果有，则不允许修改到期日
     * @param dto
     * @return
     * @throws Exception
     */
    @PostMapping("isCheckMature")
    public boolean isCheckMature(@RequestBody FinStageManagementDto dto) throws Exception{
        return finStageManagementService.isCheckMature(dto);
    }

    @PostMapping("isWorkFlowCheck")
    public List<ProcessMark> isWorkFlowCheck(@RequestBody Map<String, Object> map) throws Exception{
        return finStageManagementService.isWorkFlowCheck(map);
    }
    /**
     * 根据产品代码得到起息日和到期日 add by yujiaping 2020-11-10
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("getVdateAndMdate")
    public FinStageManagementDto getVdateAndMdate(String finprodId){
        return finStageManagementService.getVdateAndMdate(finprodId);
    }
    
}
