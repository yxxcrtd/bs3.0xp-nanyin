/*
 * 文件名：TrdSpotexInvestTradeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangguoxian
 * 修改时间：2019年7月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.trade.spotex.controller;

import java.math.BigDecimal;
import java.util.Map;

import com.joyintech.tams.invest.common.component.base.entrust.BaseEtrInvestInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.trade.spotex.dto.TrdSpotexInvestTradeDto;
import com.joyintech.service.trade.spotex.service.TrdSpotexInvestTradeService;
import com.joyintech.tams.common.base.entity.EtrFinishStatus;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;

/**
 * 外汇即期交易微服务Controller
 * 
 * @author wangguoxian
 * @version 1.0 2019年7月5日
 * @see TrdSpotexInvestTradeController
 * @since 1.0
 */
@RestController
@RequestMapping("/TrdSpotexInvestTrade")
public class TrdSpotexInvestTradeController {

    /**
     * TrdSpotexInvestTradeService
     */
    @Autowired
    private TrdSpotexInvestTradeService trdSportexInvestTradeService;
    
    /**
     * 投资委托组件
     */
    @Autowired(required = false)
    private BaseEtrInvestInfoService baseEtrInvestInfoService;

    /**
     * 分页查询
     * 
     * @param param 查询参数
     * @param page 页数
     * @param length 每页显示条数
     * @return 查询结果
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage/{page}/{length}")
    public Page<TrdSpotexInvestTradeDto> findPage(@RequestBody Map<String, Object> param,
                                                      @PathVariable("page") int page,
                                                      @PathVariable("length") int length)
        throws Exception {
        return trdSportexInvestTradeService.selectForPage(page, length, param);
    }
    
    /**
     * 
     * 查询交易详情
     * @param tradeId 交易编号
     * @return 交易详情
     * @see
     * @since 1.0
     */
    @RequestMapping("getDealInfo")
    public TrdSpotexInvestTradeDto getDealInfo(@RequestBody Map<String, Object> param) {
        return trdSportexInvestTradeService.getDealInfo(param);
    }
    
    /**
     * 查询投资委托DTO <功能详细描述>
     * 
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("getEtrustDto")
    public BaseEtrInvestInfoDto getEtrustDto(String entrustId) {
    	if (baseEtrInvestInfoService == null) {
    		return new BaseEtrInvestInfoDto();
	    }
	    BaseEtrInvestInfoDto resultDto = baseEtrInvestInfoService.queryActiveMandate(entrustId);
	    if (resultDto != null && resultDto.getEtrEntrust() != null) {
	        // 委托数量中扣除已完成额度
	        if (!StringUtil.isEmptyList(resultDto.getEtrFinishStatusList())) {
	            BigDecimal 已完成额度 = BigDecimal.ZERO;
	            for (EtrFinishStatus etrFinishStatus : resultDto.getEtrFinishStatusList()) {
	                已完成额度 = ArithmeticUtil.doAdd(已完成额度,etrFinishStatus.getFinishAmt());
	            }
	
	            resultDto.getEtrEntrust().setEntrustAmt(
	                    ArithmeticUtil.subtract(resultDto.getEtrEntrust().getEntrustAmt(), 已完成额度));
	        }
	    }
	    return resultDto;
    }
    
    /**
     * 
     * 查询组合可用现金
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("getProAvailableCash")
    public Map<String, Object> getProAvailableCash(@RequestBody Map<String, Object> param){
        return trdSportexInvestTradeService.getProAvailableCash(param);
    }
    
    /**
     * 根据交易编号查询流程中数据是否存在
     *
     * @param tradeId 交易编号
     * @return 流程中数据是否存在
     * @see
     * @since 1.0
     */
    @RequestMapping("isInFlowDataExisted/{tradeId}")
    public Boolean isInFlowDataExisted(@PathVariable("tradeId") String tradeId) {
        return trdSportexInvestTradeService.isInFlowDataExisted(tradeId);
    }
    
    /**
     * 根据交易编号查询该交易是否已被撤销
     *
     * @param tradeId 交易编号
     * @return 该交易是否已被撤销
     * @see
     * @since 1.0
     */
    @RequestMapping("isAlreadyRevoked/{tradeId}")
    public Boolean isAlreadyRevoked(@PathVariable("tradeId") String tradeId) {
        return trdSportexInvestTradeService.isAlreadyRevoked(tradeId);
    }

    /**
     * 根据委托ID查询剩余可用委托额度
     * @param entrustId 委托ID
     * @return 剩余可用委托额度
     */
    @RequestMapping("selAvailableEtrAmt/{entrustId}")
    public BigDecimal selAvailableEtrAmt(@PathVariable("entrustId") String entrustId){
        return trdSportexInvestTradeService.selAvailableEtrAmt(entrustId);
    }

}
