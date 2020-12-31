/*
 * 文件名：EtrRepoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年12月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etrrepo.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.entrust.etrrepo.dto.EtrRepoDto;
import com.joyintech.service.entrust.etrrepo.service.EtrRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 回购投资委托
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年12月24日
 * @see EtrRepoController
 * @since 1.0
 */
@RestController("EtrRepoCtr_dtx")
@RequestMapping("/EtrRepo")
public class EtrRepoController {

    @Autowired
    private EtrRepoService etrRepoService;
    
    /**
     * 
     * 项目类委托分页查询
     * <功能详细描述>
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<EtrRepoDto> selectForPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        String assetType1 = (String)param.get("assetType1");
        if(StringUtil.isNotEmpty(assetType1)){
            param.put("assetType1List", StringUtil.split(assetType1, GlobalConstant.COMMA));
        }
        return etrRepoService.selectForPage(page,length,param);
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
        List<SuggestDto> temp = etrRepoService.getentrustId(map);
        return temp;
    }
    
    /**
     * 
     * 获取新建界面的组合代码联想下拉框框
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getentrustIdAdd/{page}/{length}")
    @ResponseBody
    public Page<SelectOptionDto> getentrustIdAdd(@RequestBody Map<String, Object> map, @PathVariable("page") int page, @PathVariable("length") int length){
        Page<SelectOptionDto> temp = etrRepoService.getentrustIdAdd(page,length,map);
        return temp;
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
    public boolean recallDelegate(@RequestBody EtrRepoDto dto) throws IllegalAccessException, InvocationTargetException{
        return etrRepoService.recallDelegate(dto);
    }
    /**
     * 关闭委托 
     * 
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @GetMapping("close")
    public boolean closeDelegate(String entrustId,String closeWay) {
        return etrRepoService.closeDelegate(entrustId, closeWay);
    }
    
    /**
     * 
     * 工作流完成方法
     * <功能详细描述>
     * @throws InvocationTargetException
     * @throws IllegalAccessException 
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public void doComplete(@RequestBody EtrRepoDto dto) throws IllegalAccessException, InvocationTargetException{
        etrRepoService.doComplete(dto, dto.getOperateType());
    }

    @Transactional
    @DTXTransaction
    @PostMapping("doInsert")
    public void doInsert(@RequestBody EtrRepoDto dto){
        etrRepoService.doInsert(dto, dto.getOperateType());
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
    public EtrRepoDto selectByKey(String entrustId) {
        return etrRepoService.selectByKey(entrustId);
    }
    /**
     * 
     * 组合可用现金查询
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getProAvailableCash")
    @ResponseBody
    public Map<String, Object> getProAvailableCash(@RequestBody Map<String, Object> param) {
        return etrRepoService.getProAvailableCash(param);
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
        return etrRepoService.selectWflAmt(map);
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
        return etrRepoService.getSecManageAcctId(param);
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
    public Map<String, BigDecimal> selectAmt(@RequestBody Map<String, Object> map){
        return etrRepoService.selectAmt(map);
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
    public BigDecimal selectStandardAmt(@RequestBody Map<String, Object> map){
        return etrRepoService.selectStandardAmt(map);
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
    public List<ValidMessage> check(@RequestBody EtrRepoDto dto){
        return etrRepoService.check(dto);
    }


    /**
     * 查询 通道现金
     *
     * @param map
     * @return
     */
    @ResponseBody
    @PostMapping("queryChlCash")
    public BigDecimal queryChlCash(@RequestBody Map<String, Object> map){
        return etrRepoService.queryChlCash(map);
    }

    /**
     * 查询 划款方式
     * @param map
     * @return
     */
    @ResponseBody
    @PostMapping("queryPayType")
    public String queryPayType(@RequestBody Map<String, Object> map){
        return etrRepoService.queryPayType(map);
    }

    /**
     * 查询质押券的到期日
     * @param finprodId
     * @return
     */
    @PostMapping("selectRepoMdate")
    public Date selectRepoMdate(@RequestBody Map<String,Object> param){
        return etrRepoService.selectRepoMdate(param);
    }
}
