/*
 * 文件名：EtrInvestBondInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etrinvestbondinfo.controller;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.entrust.etrinvestbondinfo.dto.EtrInvestBondInfoDto;
import com.joyintech.service.entrust.etrinvestbondinfo.service.EtrInvestBondInfoService;

/**
 * 债券投资委托微服务controller
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年9月11日
 * @see EtrInvestBondInfoController
 * @since 1.0
 */
@RestController("EtrInvestBondInfoCtr_dtx")
@RequestMapping("/EtrInvestBondInfo")
public class EtrInvestBondInfoController {

    /**
     * 债券投资委托微服务service
     */
    @Autowired
    private EtrInvestBondInfoService etrInvestBondInfoService;
    
    /**
     * 
     * 债券投资委托分页查询
     * <功能详细描述>
     * @param param
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<EtrInvestBondInfoDto> selectForPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        String bondType = (String)param.get("bondType");
        if(StringUtil.isNotEmpty(bondType)){
            param.put("bondTypeList", StringUtil.split(bondType, GlobalConstant.COMMA));
        }
        return etrInvestBondInfoService.selectForPage(page,length,param);
    }
    /**
     * 
     * 获取组合代码联想文本框信息
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("getentrustId")
    public List<SuggestDto> getentrustId(@RequestBody Map<String, Object> map)
        throws Exception {
        List<SuggestDto> temp = etrInvestBondInfoService.getentrustId(map);
        return temp;
    }
    /**
     * 
     * 获取资产代码联想文本框信息
     * <功能详细描述>
     * @return 
     * @throws Exception
     * @see
     * @since 1.0
     */
    @GetMapping("getfinprodId")
    public List<SuggestDto> getfinprodId(String finprodId)
        throws Exception {
        List<SuggestDto> temp = etrInvestBondInfoService.getfinprodId(finprodId);
        return temp;
    }
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
        Page<SelectOptionDto> temp = etrInvestBondInfoService.getfinprodIdForAdd(page,length,param);
        return temp;
    }
    /**
     * 
     * 获取新建界面的组合代码联想下拉框框
     * <功能详细描述>
     * @param param 
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getentrustIdAdd/{page}/{length}")
    public Page<SelectOptionDto> getentrustIdAdd(@RequestBody Map<String, Object> param,@PathVariable("page") int page,
                                          @PathVariable("length") int length){
        Page<SelectOptionDto> temp = etrInvestBondInfoService.getentrustIdAdd(page,length,param);
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
    public List<ValidMessage> check(@RequestBody EtrInvestBondInfoDto dto){
        return etrInvestBondInfoService.check(dto);
    }
    /**
     * 撤回委托 
     * <功能详细描述>
     * @param dto 实体类
     * @return boolean
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("recall")
    public boolean recallDelegate(@RequestBody EtrInvestBondInfoDto dto) throws IllegalAccessException, InvocationTargetException{
        return etrInvestBondInfoService.recallDelegate(dto);
    }
    /**
     * 关闭委托 
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @GetMapping("close")
    public boolean closeDelegate(String entrustId,String closeWay) {
        return etrInvestBondInfoService.closeDelegate(entrustId, closeWay);
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
    public EtrInvestBondInfoDto selectByKey(String entrustId,String finprodId,String portfolioId) {
        return etrInvestBondInfoService.selectByKey(entrustId,finprodId,portfolioId);
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
    public void doComplete(@RequestBody EtrInvestBondInfoDto dto) throws IllegalAccessException, InvocationTargetException{
        etrInvestBondInfoService.doComplete(dto, dto.getOperateType());
    }

    @Transactional
    @DTXTransaction
    @PostMapping("doInsert")
    public void doInsert(@RequestBody EtrInvestBondInfoDto dto){
        etrInvestBondInfoService.doInsert(dto, dto.getOperateType());
    }
    /**
     * 
     * 删除投资委托
     * <功能详细描述>
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @GetMapping("delete")
    public boolean deleteDelegate(String entrustId) {
        return etrInvestBondInfoService.deleteDelegate(entrustId);
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
        return etrInvestBondInfoService.getAvailableNum(map);
    }
    /**
     * 
     * 组合可用现金查询
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getProAvailableCash")
    public Map<String, Object> getProAvailableCash(@RequestBody Map<String, Object> param) {
        return etrInvestBondInfoService.getProAvailableCash(param);
    }
    /**
     * 
     * 组合可用持仓查询
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getAssetAvailablePosition")
    public Map<String, Object> getAssetAvailablePosition(@RequestBody Map<String, Object> param) {
        return etrInvestBondInfoService.getAssetAvailablePosition(param);
    }
    
    /**
     * 查询估值净价
     * @author wangguiqi
     */
    @PostMapping("getClosePriceByPara")
    public BigDecimal getClosePriceByPara(@RequestBody Map<String, Object> param) {
        return etrInvestBondInfoService.getClosePriceByPara(param);
    }

    /**
     * 查询证券托管户：联想框文本框
     *
     * @param param 画面参数
     * @return 证券托管户：联想框文本框
     * @see
     * @since 1.0
     */
    @RequestMapping("getSecManageAcctId")
    public List<SuggestDto> getSecManageAcctId(@RequestBody Map<String, Object> param) {
        return etrInvestBondInfoService.getSecManageAcctId(param);
    }
    
    
    @RequestMapping("getStdPosition")
    public BigDecimal getStdPosition(@RequestBody Map<String,Object> param) {
    	return etrInvestBondInfoService.getStdPosition(param);
    }
}
