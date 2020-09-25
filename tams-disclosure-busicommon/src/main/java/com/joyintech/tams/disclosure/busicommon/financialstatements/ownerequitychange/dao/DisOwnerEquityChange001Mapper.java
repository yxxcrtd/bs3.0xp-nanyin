/*
 * 文件名：OwnerEquityChange001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangzhao
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.disclosure.busicommon.financialstatements.ownerequitychange.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.disclosure.busicommon.common.dto.BokBalance;
import com.joyintech.tams.disclosure.busicommon.financialstatements.ownerequitychange.dto.DisOwnerEquityChange001Dto;
import com.joyintech.tams.disclosure.busicommon.financialstatements.valprofitquery.dto.DisProfitCalculationDto;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhangzhao
 * @version 1.0 2018年9月18日
 * @see OwnerEquityChange001Mapper
 * @since 1.0
 */
@Repository
public interface DisOwnerEquityChange001Mapper {


    /**
     * 查询数据
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<DisOwnerEquityChange001Dto> selectAll(Map<String, Object> pageParam);

    /**
     * 获取当前系统平台日期
     *
     * @return 当前系统平台日期
     * @see
     * @since 1.0
     */
    Date getNowSysDate();
    
    /**
     * 本期收入
     * @return
     */
    List<BigDecimal>   selectAmt(Map<String, Object> pageParam);
    
    /**
     * 本期收入
     * @return
     */
    List<BokBalance>   selectAmt1(Map<String, Object> pageParam);
    
    
    /**
     * 本期收入本位币
     * @return
     */
    List<BokBalance>   selectAmtbAmt(Map<String, Object> pageParam);
    
    /**
     * 本期支出
     */
    List<BigDecimal>   selectAmtZc(Map<String, Object> pageParam);
    
    /**
     * 本期支出
     */
    List<BokBalance>   selectAmtZc1(Map<String, Object> pageParam);
    
    /**
     * 本期支出本位币
     */
    List<BokBalance>   selectAmtZcbAmt(Map<String, Object> pageParam);
    
    /**
     * 查询表样
     */
    List<DisProfitCalculationDto> selectProfitCalculationDtoList();
    
    /**
     * 余额查询原币
     */
    List<BokBalance>  selectAll1(Map<String, Object> pageParam);
    
    
    /**
     * 余额查询本位币
     */
    List<BokBalance>  selectAllBamt(Map<String, Object> pageParam);
    
    
     /**一体化所有者权益变动表 add by dongxiangyang  20200212*/
    List<BokBalance>  selectAll1Yth(Map<String, Object> pageParam);
    
    /*** 查询表样  add  by  dongxiangyang  20200212 */
    List<DisProfitCalculationDto> selectProfitCalculationDtoListYth();

    /**
     * 查询产品起息日
     */
    String  getVdate(String  prodId);
}
