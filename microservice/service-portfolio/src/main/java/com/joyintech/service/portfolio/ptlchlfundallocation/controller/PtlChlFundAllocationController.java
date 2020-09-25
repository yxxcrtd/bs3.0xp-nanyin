/*
 * 文件名：PtlDvpFundAllocationController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2019年3月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.portfolio.ptlchlfundallocation.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.annotation.DTXTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.portfolio.ptlchlfundallocation.dto.PtlChlFundAllocationDto;
import com.joyintech.service.portfolio.ptlchlfundallocation.service.PtlChlFundAllocationService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.component.base.group.BasePtlSecPositionInfoService;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;

/**
 * 通道资金划拨微服务
 *
 * @author etliu
 * @version 1.0 2019年3月29日
 * @see PtlChlFundAllocationController
 * @since 1.0
 */
@RestController("ptlChlFundAllocationController_dtx")
@RequestMapping("/PtlChlFundAllocation")
public class PtlChlFundAllocationController {

    /**
     * PtlChlFundAllocationService
     */
    @Autowired
    private PtlChlFundAllocationService ptlChlFundAllocationService;
    /**
    * 组合可用现金和实际现金查询更新组件
    */
   @Autowired(required = false)
   private BasePtlCashPositionInfoService basePtlCashPositionInfoService;

    /**
     * 画面查询方法
     * 
     * @param param 请求参数
     * @param page 页面
     * @param length 长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<PtlChlFundAllocationDto> selectAll(@RequestBody Map<String, Object> param,
                                                   @PathVariable("page") int page,
                                                   @PathVariable("length") int length)
        throws Exception {
        return ptlChlFundAllocationService.findPage(page, length, param);
    }

    /**
     * 查询收款账号
     * 
     * @param map 请求参数
     * @return 收款账号集合
     * @see
     * @since 1.0
     */
    @PostMapping("/getCounterAcctNo")
    public List<SelectOptionDto> getCounterAcctNo(@RequestBody Map<String, Object> map) {
        return ptlChlFundAllocationService.getCounterAcctNo(map);
    }

    /**
     * 查询付款账号
     * 
     * @param map 请求参数
     * @return 付款账号集合
     * @see
     * @since 1.0
     */
    @PostMapping("/getMyAcctNo")
    public List<SelectOptionDto> getMyAcctNo(@RequestBody Map<String, Object> map) {
        return ptlChlFundAllocationService.getMyAcctNo(map);
    }
    
    /**
     * 查询交易编号(根据指令编号)
     * 
     * @param map 请求参数
     * @return 交易编号
     * @see
     * @since 1.0
     */
    @PostMapping("/getTradeId")
    public String getTradeId(@RequestBody Map<String, Object> map) {
        return ptlChlFundAllocationService.getTradeId(map);
    }

    /**
     *  获取账户余额
     * <功能详细描述>
     * @param map  请求参数
     * @return 账户余额
     * @see
     * @since 1.0
     */
    @PostMapping("/getAmount")
    public BigDecimal getAmount(@RequestBody Map<String, Object> map) {
        if (basePtlCashPositionInfoService != null) {
            //转账日期
            Date cdate = DateUtil.parse((String)map.get("cdate"));
            String payType = (String)map.get("payType");
            String calcAcctName = (String)map.get("calcAcctName");
            
            PtlCashPositionFlowDto dto = new PtlCashPositionFlowDto();
            dto.setPortfolioId((String)map.get("portfolioId"));
            dto.setHodingType(DictCodeList.持仓类型_可用.getValue());
            dto.setCdate(cdate);
            dto.setCcy((String)map.get("ccy"));
            dto.setAcctType(null);
            dto.setAcctId((String)map.get("acctId"));
            if (StringUtil.equals(DictCodeList.交易方向_通道划款.getValue(), payType) && 
                StringUtil.equals("counterAcctNo", calcAcctName)) {
                dto.setChlFinprodId((String) map.get("chlId"));

            } else if (StringUtil.equals(DictCodeList.交易方向_通道回款.getValue(), payType) && 
                StringUtil.equals("myAcctNo", calcAcctName)){
                dto.setChlFinprodId((String) map.get("chlId"));
            }
            
          return basePtlCashPositionInfoService.queryUsableCashPosition(dto);  
        }
        
        return BigDecimal.ZERO;
    }

    
    /**
     * 代办处理
     * 
     * @param entity 资金划拨实体类
     * @return 处理结果
     * @see
     * @since 1.0
     */
    @RequestMapping("/doComplete")
    @Transactional
    @DTXTransaction
    public int doComplete(@RequestBody PtlChlFundAllocationDto entity) {
        if (StringUtil.equals(entity.getOperateType(), "NEW")) {
            return ptlChlFundAllocationService.insertData(entity);
        }
        else {
            return ptlChlFundAllocationService.revokeData(entity);
        }
    }

    @RequestMapping("/doInsert")
    @Transactional
    @DTXTransaction
    public String doInsert(@RequestBody PtlChlFundAllocationDto entity) {
        return ptlChlFundAllocationService.doInsert(entity);
    }
}
