/*
 * 文件名：EtrBondFirstBiddingController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年5月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etrbondfirstlbidding.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.EtrEntrust;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.entrust.etrbondfirstlbidding.dto.EtrBondFirstLBiddingDto;
import com.joyintech.service.entrust.etrbondfirstlbidding.service.EtrBondFirstLBiddingService;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author gejiaao
 * @version 1.0 2019年7月4日
 * @see EtrBondFirstLBiddingController
 * @since 1.0
 */
@RestController("EtrBondFirstLBiddingCtr_dtx")
@RequestMapping("/EtrBondFirstLBidding")
public class EtrBondFirstLBiddingController {
    
    @Autowired
    private EtrBondFirstLBiddingService service;
    
    /**
     * 
     * 检索分页数据。返回类型是Service的泛型。
     * <功能详细描述>
     * @param pageParam int
     * @param page int
     * @param length int
     * @return Page<EtrFundDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<EtrBondFirstLBiddingDto> selectForPage(@RequestBody Map<String, Object> pageParam,@PathVariable("page") int page,
                                          @PathVariable("length") int length)throws Exception {
        return service.selectForPage(page,length,pageParam);
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
        return service.getentrustId(map);
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
        return service.getfinprodId(finprodId);
    }
    /**
     * 
     * 获取新建界面的资产代码联想下拉框框
     * <功能详细描述>
     * @param
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("getfinprodIdForAdd/{page}/{length}")
    public Page<SelectOptionDto> getfinprodIdForAdd(@RequestBody Map<String, Object> param,@PathVariable("page") int page,
                                                    @PathVariable("length") int length)
        throws Exception {
        return service.getfinprodIdForAdd(page,length,param);
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
    Page<SelectOptionDto> getentrustIdAdd(@RequestBody Map<String, Object> param,@PathVariable("page") int page,
                                          @PathVariable("length") int length){
        return service.getentrustIdAdd(page,length,param);
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
    public List<ValidMessage> check(@RequestBody EtrBondFirstLBiddingDto dto){
        return service.check(dto);
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
    public boolean recallDelegate(@RequestBody EtrBondFirstLBiddingDto dto) throws IllegalAccessException, InvocationTargetException{
        return service.recallDelegate(dto);
    }
    /**
     * 关闭委托 
     * 
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @GetMapping("close")
    public boolean closeDelegate(String entrustId,String closeWay) {
        return service.closeDelegate(entrustId, closeWay);
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
    public EtrBondFirstLBiddingDto selectByKey(String entrustId,String finprodId,String portfolioId) {
        return service.selectByKey(entrustId,finprodId,portfolioId);
    }
    /**
     * 
     * 工作流完成方法
     * <功能详细描述>
     * @param
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public void doComplete(@RequestBody EtrBondFirstLBiddingDto dto) throws IllegalAccessException, InvocationTargetException{
        service.doComplete(dto, dto.getOperateType());
    }

    @Transactional
    @DTXTransaction
    @PostMapping("doInsert")
    public void doInsert(@RequestBody EtrBondFirstLBiddingDto dto){
        service.doInsert(dto, dto.getOperateType());
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
        return service.deleteDelegate(entrustId);
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
    /*@PostMapping("getAvailableNum")
    public BigDecimal getAvailableNum(@RequestBody Map<String, Object> map){
        return service.getAvailableNum(map);
    }*/
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
        return service.getProAvailableCash(param);
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
    /*@PostMapping("getAssetAvailablePosition")
    public Map<String, Object> getAssetAvailablePosition(@RequestBody Map<String, Object> param) {
        return service.getAssetAvailablePosition(param);
    }*/

    /**
     * 获取交易对手名称
     * @param counterId
     * @return
     */
    @GetMapping("getCounterName")
    public String getCounterName(String counterId){
        return service.getCounterName(counterId);
    }
    /**
     * 验证是否截标
     * @param dto
     * @return
     */
    @PostMapping("checkIsBidClose")
    public Integer checkIsBidClose(@RequestBody EtrBondFirstLBiddingDto dto){
        return service.checkIsBidClose(dto);
    }
    /**
     *
     * 获取新建界面的主承销商下拉框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getMainConsignee/{page}/{length}")
    Page<SelectOptionDto> getMainConsignee(@RequestBody Map<String, Object> param,@PathVariable("page") int page,
                                          @PathVariable("length") int length){
        return service.getMainConsignee(page,length,param);
    }
}
