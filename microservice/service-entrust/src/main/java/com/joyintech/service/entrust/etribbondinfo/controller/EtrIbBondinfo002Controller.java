/*
 * 文件名：EtrIbBondinfo002Controller.java
 */
 
package com.joyintech.service.entrust.etribbondinfo.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.entrust.common.service.WFlowCommonService;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrEntrustAdd;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.tams.invest.common.entrust.dto.EntrustCommonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.joyintech.service.entrust.etribbondinfo.dto.EtrIbBondinfo002Dto;
import com.joyintech.service.entrust.etribbondinfo.service.EtrIbBondinfo002Service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 债券投资委托微服务controller
 */
@RestController("EtrIbBondinfo002Ctr_dtx")
@RequestMapping("EtrIbBondinfo002")
public class EtrIbBondinfo002Controller {

    /**
     * 债券投资委托微服务service
     */
    @Autowired
    private EtrIbBondinfo002Service etrIbBondinfo002Service;

    /**
     * 
     * 获取新建界面的资产代码联想下拉框框
     * <功能详细描述>
     * @param finprodId
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("getfinprodIdForAdd/{page}/{length}")
    public Page<SelectOptionDto> getfinprodIdForAdd(@RequestBody Map<String, Object> param,@PathVariable("page") int page,
                                                    @PathVariable("length") int length)
        throws Exception {
        Page<SelectOptionDto> temp = etrIbBondinfo002Service.getfinprodIdForAdd(page,length,param);
        return temp;
    }

    /**
     * 
     * 操作前校验
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("check")
    public List<ValidMessage> check(@RequestBody EtrIbBondinfo002Dto dto){
        return etrIbBondinfo002Service.check(dto);
    }

    /**
     * 
     * 通过主键查询数据
     * <功能详细描述>
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selectByKey")
    public EtrIbBondinfo002Dto selectByKey(String entrustId,String finprodId,String portfolioId) {
        return etrIbBondinfo002Service.selectByKey(entrustId,finprodId,portfolioId);
    }
    /**
     * 
     * 工作流完成方法
     * <功能详细描述>
     * @param map
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public void doComplete(@RequestBody EtrIbBondinfo002Dto dto) throws IllegalAccessException, InvocationTargetException{
        WFlowCommonService.doCompleteFlow(dto.getWorkflowId());
        
        etrIbBondinfo002Service.doComplete(dto, dto.getOperateType());
    }

    @Transactional
    @DTXTransaction
    @PostMapping("doInsert")
    public void doInsert(@RequestBody EtrIbBondinfo002Dto dto){
        WFlowCommonService.doInsertFlow(dto.getOperateType(), etrIbBondinfo002Service.initBaseEtrInvestInfoDto(dto));
        
        etrIbBondinfo002Service.doInsert(dto, dto.getOperateType());
    }

    @Transactional
    @DTXTransaction
    @PostMapping("doUpdate")
    public void doUpdate(@RequestBody EtrIbBondinfo002Dto dto){
        WFlowCommonService.doUpdateFlow(dto.getOperateType(), etrIbBondinfo002Service.initBaseEtrInvestInfoDto(dto));

        etrIbBondinfo002Service.doInsert(dto, dto.getOperateType());
    }

    @Transactional
    @DTXTransaction
    @PostMapping("doBack")
    public void doBack(@RequestBody EtrIbBondinfo002Dto dto){
        WFlowCommonService.doBackFlow(dto.getWorkflowId(), dto.getEntrustId());
    }
    /**
     * 
     * 可用面值计算
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getAvailableNum")
    public BigDecimal getAvailableNum(@RequestBody Map<String, Object> map){
        return etrIbBondinfo002Service.getAvailableNum(map);
    }

    /**
     * 查询估值净价
     * @author wangguiqi
     */
    @PostMapping("getClosePriceByPara")
    public BigDecimal getClosePriceByPara(@RequestBody Map<String, Object> param) {
        return etrIbBondinfo002Service.getClosePriceByPara(param);
    }

    /**
     * 查询行权价
     * @author wangguiqi
     */
    @PostMapping("getExercisePricebyPara")
    public BigDecimal getExercisePricebyPara(@RequestBody Map<String, Object> param) {
        return etrIbBondinfo002Service.getExercisePricebyPara(param);
    }
}
