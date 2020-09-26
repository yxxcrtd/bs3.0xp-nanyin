/*
 * 文件名：ValFinPayInterestStdInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.component.element.interf;

import java.text.ParseException;
import java.util.List;

import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.tams.valuation.component.common.dto.FinCashPlanDto;
import com.joyintech.tams.valuation.component.element.dto.ValFinPayInterestStdInfoDto;

/**
 * 付息信息业务组件接口
 * @author zhangnian
 * @version 1.0 2018年9月11日
 * @see ValFinPayInterestStdInfoService
 * @since 1.0
 */
public interface ValFinPayInterestStdInfoService {
    
    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "finPayInterestStdInfoDto";
    /**
     * 获取画面初始化需要的数据字典
     */
    IDictCode[] getInitDictCode();
    
    /**
     * 共同画面返回值
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    ValFinPayInterestStdInfoDto getFinPayInterestStdInfoDto(ValFinPayInterestStdInfoDto dto);
    
    /**
     * 业务画面初始化
     * <功能详细描述>
     * @param finProductId
     * @param vdate
     * @return
     * @see
     * @since 1.0
     */
    ValFinPayInterestStdInfoDto getFinPayInterestStdInfoByProductId(String finprodId,String vdate,String cashType,String finprodType);
    
    List<FinCashPlanDto> getFinCashPlanByProductId(String finprodId, String cashType, String finprodType, String finprodType2);
    
    /**
     * 生成付息计划
     * <功能详细描述>
     * @param is
     * @param scai
     * @param holidayList
     * @param flag
     * @param t
     * @return
     * @throws ParseException 
     * @see
     * @since 1.0
     */
     List<FinCashPlanDto> createAssetPayInteresPlan(ValFinPayInterestStdInfoDto dto) throws ParseException;
    
}
