package com.joyintech.service.entrust.common.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.tams.invest.common.component.base.entrust.BaseEtrInvestInfoService;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;

@RestController("entrustwwcommonbaseCtr_dtx")
@RequestMapping("/common/entrustwwcommonbase")
public class EntrustWwCommonBaseController {
    /**
     * 投资委托 基础组件
     */
    @Autowired
    private BaseEtrInvestInfoService baseEtrInvestInfoServiceImpl;

    /**
     * 插入指令流程中的数据
     * @param entity
     * @return
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doInsertEtrFlow")
    public boolean doInsertEtrFlow(@RequestBody
                                           BaseEtrInvestInfoDto entity){
        try {
            baseEtrInvestInfoServiceImpl.newWwDelegateFlow(entity);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 插入指令流程中的数据
     * @param param
     * @return
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doDeleteEtrFlow")
    public boolean doDeleteEtrFlow(@RequestBody
                                           Map<String,Object> param){
        try {
            baseEtrInvestInfoServiceImpl.deleteWwDelegateFlow(param);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
