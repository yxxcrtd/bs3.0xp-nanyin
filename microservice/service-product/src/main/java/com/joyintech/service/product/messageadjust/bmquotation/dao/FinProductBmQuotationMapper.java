/*
 * 文件名：FinProductBmQuotationMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.messageadjust.bmquotation.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.joyintech.service.product.messageadjust.bmquotation.dto.BussBenchMarkInfoDto;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.messageadjust.bmquotation.dto.BenchmarkRate;
import com.joyintech.service.product.messageadjust.bmquotation.dto.FinProductBmQuotationDto;

/**
 * 产品业绩基准报价管理
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年10月11日
 * @see FinProductBmQuotationMapper
 * @since 1.0
 */
@Repository
public interface FinProductBmQuotationMapper {
    /**
     * 产品代码联想下拉框
     * @param pageParam
     * @return
     */
    List<SelectOptionDto> getFinprodMarketIdList(Map<String, Object> param);
    /**
     * 产品报价管理查询
     * @param pageParam
     * @return
     */
    List<FinProductBmQuotationDto> selectForPage(Map<String, Object> pageParam);
    /**
     * 基准来源 = 利率行情时
     * 从利率行情表中取得基准利率
     * @param string 
     * @return
     * @see
     * @since 1.0
     */
    BigDecimal getIntRate(Map<String,Object> map);
    /**
     * 基准类型 = 复合基准时
     * 查询出该基准代码所对应的所有行情信息
     * @return
     * @see
     * @since 1.0
     */
    List<BenchmarkRate> getBenchmarkRate(Map<String, Object> param);
    /**
     * 新建-产品代码联想下拉框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getFinprodMarketIdToNew(Map<String, Object> param);
    /**
     * 查询是否为复合基准
     * <功能详细描述>
     * @param indexIdSource
     * @return
     * @see
     * @since 1.0
     */
    String selectIndexSource(String indexIdSource);
    /**
     * 根据基准代码和生效日查询数据
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    List<FinProductBmQuotationDto> selectByFinprodIdAndEffDate(FinProductBmQuotationDto dto);
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    FinProductBmQuotationDto getMdate(String finprodId);
    /**
     * 判断生效日后是否发生了浮动管理费计提、支付交易
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    List<Integer> getCount(Map<String,Object> param);

    int updateBaseRuleInfo(BussBenchMarkInfoDto bussBenchMarkInfoDto);
}
