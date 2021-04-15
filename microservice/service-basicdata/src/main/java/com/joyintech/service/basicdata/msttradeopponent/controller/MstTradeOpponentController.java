/*
 * 文件名：MstTradeOpponent001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年7月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.msttradeopponent.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.msttradeopponent.dto.FinCashDto;
import com.joyintech.service.basicdata.msttradeopponent.dto.MstTradeOpponentDto;
import com.joyintech.service.basicdata.msttradeopponent.dto.StlCapacctDto;
import com.joyintech.service.basicdata.msttradeopponent.service.BasicMstTradeOpponentService;
import com.joyintech.service.basicdata.msttradeopponent.service.MstTradeOpponent001Service;
import com.joyintech.tams.common.base.dao.MstFeeinfoMapper;
import com.joyintech.tams.common.base.entity.MstFeeinfo;
import com.joyintech.tams.common.base.entity.StlCapacctInfo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 交易对手管理
 * @author fanshuanghu
 * @version 1.0 2018年8月30日
 * @see MstTradeOpponentController
 * @since 1.0
 */
@RestController("MstTradeOpponentCtr_dtx")
@RequestMapping("/MstTradeOpponent")
public class MstTradeOpponentController {
    /**
     * 注入交易对手service
     */
    @Autowired
    private MstTradeOpponent001Service mstTradeOpponent001Service;
    /**
     * 注入交易对手service002
     */
    @Autowired
    private BasicMstTradeOpponentService basicMstTradeOpponentService;
    /**
     * 费用信息
     */
    @Autowired
    private MstFeeinfoMapper mstFeeinfoMapper;
    /**
     * 获取交易对手类型
     * @param map 查询条件
     * @return 下拉框实体类集合
     * @see
     * @since 1.0
     */
    @PostMapping("/getCounterType")
    public List<SelectOptionDto> getCounterType(@RequestBody Map<String, Object> map){
        return mstTradeOpponent001Service.getCounterType(map);
    }
    /**
     * 分页查询
     * @param pageParam 分页查询条件
     * @return page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<MstTradeOpponentDto> selectForPage(@RequestBody Map<String, Object> pageParam,@PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        Page<MstTradeOpponentDto> dto = mstTradeOpponent001Service.selectForPage(page,length,pageParam);
        return dto;
    }
    
    /**
     * 获取账号信息
     * @see
     * @since 1.0
     * @return 账号实体类
     */
    @PostMapping("/getAccount")
    public List<SelectOptionDto> getAccount(@RequestBody Map<String,Object> map){
//        if(map.get("initValue") != null){
//            map.put("acctNo", map.get("initValue").toString().split("_")[0]);
//            map.put("ccy", map.get("initValue").toString().split("_")[0]);
//        }
        return mstTradeOpponent001Service.getAccount(map);
    }
    /**
     * 获取费用品种
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @PostMapping("/getFeeId")
    public List<SelectOptionDto> getFeeId(@RequestBody Map<String,Object> map){
        return mstTradeOpponent001Service.getFeeId(map);
    }
    
    @GetMapping("getOfficeId")
    public List<SelectOptionDto> getOfficeId(String param){
        return mstTradeOpponent001Service.getOfficeId(param);
    }
    
    
    @PostMapping("getMarketId/{page}/{length}")
    public Page<SelectOptionDto> getMarketId(@RequestBody Map<String, Object> map, 
    		@PathVariable("page") int page, @PathVariable("length") int length){
        return mstTradeOpponent001Service.getMarketId(map, page, length);
    }
    
    /**
     * 获取账号类型
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @GetMapping("/getAccountType")
    public List<SelectOptionDto> getAccountType(){
        return mstTradeOpponent001Service.getAccountType();
    }
    @GetMapping("/getAccountTypeInfo")
    public List<SelectOptionDto> getAccountTypeInfo(){
        return mstTradeOpponent001Service.getAccountTypeInfo();
    }
    
    @GetMapping("/getCustomerIdMarket")
    public List<SelectOptionDto> getCustomerIdMarket(String param){
        if("null".equals(param)){
            param = "";
        }
        return mstTradeOpponent001Service.getCustomerIdMarket(param);
    }
    /**
     * 获取管理人
     * @return 下拉框实体类
     * @see
     * @since 1.0
     */
    @GetMapping("/getCustomerId")
    public List<SelectOptionDto> getCustomerId(String param){
        if("null".equals(param)){
            param = "";
        }
        return mstTradeOpponent001Service.getCustomerId(param);
    }
    @GetMapping("getCounterName")
    public List<SuggestDto> getCounterName(String param){
        return mstTradeOpponent001Service.getCounterName(param);
    }
    
    @GetMapping("getCounterId")
    public List<SelectOptionDto> getCounterId(String param){
        if("null".equals(param)){
            param = "";
        }
        return mstTradeOpponent001Service.getCounterId(param);
    }
    /**
     * 获取托管人
     * @return 下拉框实体类
     * @see
     * @since 1.0
     */
    @GetMapping("/getCustomerId1")
    public List<SelectOptionDto> getCustomerId1(String param){
        if("null".equals(param)){
            param = "";
        }
        return mstTradeOpponent001Service.getCustomerId1(param);
    }
    /**
     * 统计
     * <功能详细描述>
     * @param counterId id
     * @return 统计结果
     * @see
     * @since 1.0
     */
    @PostMapping("/selectCounter")
    public int selectCounter(@RequestBody String counterId) {
        return mstTradeOpponent001Service.selectCounter(counterId);
    }
    /**
     * 统计金融产品
     * <功能详细描述>
     * @param dto 参数
     * @return 统计结果
     * @see
     * @since 1.0
     */
    @PostMapping("/selectFinProduct")
    public int selectFinProduct(@RequestBody MstTradeOpponentDto dto) {
        return mstTradeOpponent001Service.selectFinProduct(dto);
    }
    /**
     * 
     * 001工作流完成
     * <功能详细描述>
     * @param map
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete001")
    public void doComplete001(@RequestBody MstTradeOpponentDto entity){
        String type = entity.getOperateType();
        mstTradeOpponent001Service.doComplete(entity,type);
    }
    /**
     * 
     * 002工作流完成
     * <功能详细描述>
     * @param map
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete002")
    public void doComplete002(@RequestBody MstTradeOpponentDto entity){
        String type = entity.getOperateType();
        basicMstTradeOpponentService.doComplete(entity,type);
    }
    @PostMapping("selectCounterInfo")
    public MstTradeOpponentDto selectCounterInfo(@RequestBody Map<String,Object> map){
        return basicMstTradeOpponentService.selectCounterInfo(map);
    }
    @PostMapping("selectStlCapacctInfo")
    public List<StlCapacctDto> selectStlCapacctInfo(@RequestBody Map<String,Object> map){
        return basicMstTradeOpponentService.selectStlCapacctInfo(map);
    }
    @PostMapping("getCctInfo")
    public StlCapacctInfo getCctInfo(@RequestBody Map<String,Object> map){
        return mstTradeOpponent001Service.getCctInfo(map);
    }
    
    @PostMapping("selectFinCashInfo")
    public List<FinCashDto> selectFinCashInfo(@RequestBody Map<String,Object> map){
        return basicMstTradeOpponentService.selectFinCashInfo(map);
    }
    @GetMapping("checkCounterId")
    public int checkCounterId(String counterId){
        return basicMstTradeOpponentService.checkCounterId(counterId);
    }
    //检查交易对手名字是否重复
    @GetMapping("checkcounterName")
    public int checkcounterName(String counterId,String counterName){
    	Map<String,Object> map = new HashMap<>();
    	if(StringUtils.isNotEmpty(counterId)&&!"null".equals(counterId)) {
    		map.put("counterId", counterId);
    	}
    	if(StringUtils.isNotEmpty(counterName)&&!"null".equals(counterName)) {
    		map.put("counterName", counterName);
    	}
    	List<String> list = basicMstTradeOpponentService.checkcounterName(map);
    	if(list!=null && list.size()>0) {
    		return 1;
    	}
    	return 0;
    }
    @GetMapping("checkCounterName")
    public int checkCounterName(String linkId){
        return basicMstTradeOpponentService.checkCounterName(linkId);
    }

    /**
     * 校验交易对手名字 是否重复
     * 重写方法  联社模式下会走拦截器。
     * @param linkId
     * @return
     */
    @GetMapping("checkCounterName1")
    public int checkCounterName1(String counterName){
        return basicMstTradeOpponentService.checkCounterName1(counterName);
    }
    /**
     * 获取费用信息
     * @param feeId 费用代码
     * @return 费用信息实体类
     * @see
     * @since 1.0
     */
    @GetMapping("getFeeInfo")
    public MstFeeinfo getFeeInfo(String feeId){
        return mstFeeinfoMapper.selectByPrimaryKey(feeId);
    }
    /**
     * 获取交易对手账户信息（组合资金账户）
     * @param map 请求参数
     * @return 账户信息
     * @see
     * @since 1.0
     */
    @PostMapping("getCountInfo")
    public List<StlCapacctInfo> getCountInfo(@RequestBody Map<String,Object> map){
        return basicMstTradeOpponentService.getCountInfo(map);
    }
    
    /**
     * 删除交易对手下账户信息前的检测
     * @param mstCounterAcctKey
     * @return
     */
    @PostMapping("isExistHistoryDeal")
    public int isExistHistoryDeal(@RequestBody Map<String,Object> map){
        return basicMstTradeOpponentService.isExistHistoryDeal(map);
    }


    /**
     *
     * @param map
     * @return
     */
    @PostMapping("selectCounterIdByCustomer")
    public List<MstTradeOpponentDto> selectCounterIdByCustomer(@RequestBody Map<String,Object> map){
        return mstTradeOpponent001Service.selectCounterIdByCustomer(map);
    }

}
