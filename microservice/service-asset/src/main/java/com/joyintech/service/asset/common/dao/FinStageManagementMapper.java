/*
 * 文件名：FinStageManagementMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年3月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.base.entity.ProcessMark;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.asset.common.dto.FinStageManagementDto;


/**
 *  期次管理
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年3月12日
 * @see FinStageManagementMapper
 * @since 1.0
 */
@Repository
public interface FinStageManagementMapper {

    Map<String,Object> getRates(Map<String,Object> map);
    
    List<FinStageManagementDto> getfinStageManagementByProductId(Map<String,Object> map);
    //删除标的类型--add by 裴家俊  2019/03/29--start--
    void deleteFinProductType(@Param("finprodId") String finprodId);
    //删除标的类型--add by 裴家俊  2019/03/29--end--
    //删除标的信息
    void deleteFinProduct(@Param("finprodId") String finprodId);
    //删除现金流信息
    void deleteFinCashInfo(@Param("finprodId") String finprodId);
    //删除现金流计算规则信息
    void deleteFinCashCalRule(@Param("finprodId") String finprodId);
    //删除现金流计算规则扩展信息
    void deletePaFinCashCalRule(@Param("finprodId") String finprodId);
    //删除现金流计划
    void deleteFinCashPlan(@Param("finprodId") String finprodId);
    //删除现金流利率信息
    void deleteFinRateInfo(@Param("finprodId") String finprodId);
    //删除金融产品买断利率
    void deletepaFinProductBuyoutRate(@Param("finprodId") String finprodId);
    //删除标的附属
    void deleteFinProductAdd(@Param("finprodId") String finprodId);
    //查询是否已做到期交易
    int isCheckMature(@Param("finprodId") String finprodId, @Param("vdate") Date vdate);
    /**
     * 将资产投资性质字段 FIN_PRODUCT_ADD里存值
     * <功能详细描述>
     * @param finprodId
     * @param investNature
     * @return
     * @see
     * @since 1.0
     */
    int updateInvestNatureByPFinprodId(@Param("finprodId")String finprodId);
    //**add by xr for TISHKBS
    void deleteFinCashPayRule(@Param("finprodId") String finprodId);

    List<ProcessMark> isWorkFlowCheck(Map<String, Object> map);
    /**
     * 根据产品代码得到起息日和到期日add by yujiaping 2020-11-10
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    FinStageManagementDto getVdateAndMdate(@Param("finprodId")String finprodId);
}
