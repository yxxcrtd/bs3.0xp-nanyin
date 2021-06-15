/*
 * 文件名：EtrBoutRepoController.java
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etribboutrepo.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.entrust.etribboutrepo.dto.EtrIbBoutRepo002Dto;
import com.joyintech.service.entrust.etribboutrepo.service.EtrIbBoutRepo002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 买断式回购委托
 * 〈功能详细描述〉
 * @see EtrIbBoutRepo002Controller
 * @since 1.0
 */
@RestController("trIbBoutRepo002Ctr_dtx")
@RequestMapping("EtrIbBoutRepo002")
public class EtrIbBoutRepo002Controller {

    @Autowired
    private EtrIbBoutRepo002Service etrIbBoutRepo002Service;

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
    public void doComplete(@RequestBody EtrIbBoutRepo002Dto dto) throws IllegalAccessException, InvocationTargetException{
        etrIbBoutRepo002Service.doComplete(dto, dto.getOperateType());
    }

    @Transactional
    @DTXTransaction
    @PostMapping("doInsert")
    public void doInsert(@RequestBody EtrIbBoutRepo002Dto dto){
        etrIbBoutRepo002Service.doInsert(dto, dto.getOperateType());
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
    public EtrIbBoutRepo002Dto selectByKey(String entrustId) {
        return etrIbBoutRepo002Service.selectByKey(entrustId);
    }

    /**
     * 
     * 查询流程中的质押额度
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectWflAmt")
    public BigDecimal selectWflAmt(@RequestBody Map<String, Object> map){
        return etrIbBoutRepo002Service.selectWflAmt(map);
    }
    /**
     * 
     * 查询交易中的质押额度
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectAmt")
    public BigDecimal selectAmt(@RequestBody Map<String, Object> map){
        return etrIbBoutRepo002Service.selectAmt(map);
    }
    /**
     * 
     * 查询标准券额度
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectStandardAmt")
    public Map<String, Object> selectStandardAmt(@RequestBody Map<String, Object> map){
        return etrIbBoutRepo002Service.selectStandardAmt(map);
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
    public List<ValidMessage> check(@RequestBody EtrIbBoutRepo002Dto dto){
        return etrIbBoutRepo002Service.check(dto);
    }
}
