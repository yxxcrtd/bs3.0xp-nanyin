/*
 * 文件名：LiquidityPredictReportMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：weipeng
 * 修改时间：2019年8月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.investmentevaluate.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.investmentevaluate.dto.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 投资评价管理mapper
 * 
 * @author jyl
 * @version 1.0 2019年10月11日
 * @see FinInvestmentEvaluateMapper
 * @since 1.0
 */
@Repository
public interface FinInvestmentEvaluateMapper {

    /**
     * 评价日资产配置情况查询
     * 
     * @param
     * @param
     * @return
     * @see
     * @since 1.0
     */
    List<FinInvestmentEvaluateDto> selectPage2Data(Map<String, Object> pageParam);

    /**
     * 净值表现查询
     * @param pageParam
     * @return
     */
    List<BokPositionPrdNetvalueDto> findPage3(Map<String, Object> pageParam);
/**
 *金融产品信息+组合代码名称+区间开始日结束日
 */
FinInvestmentEvaluate002Dto searchFinancialProductInformationDto(Map<String, Object> pageParam);
/**
*组合信息查询
*/
List<FinInvestmentEvaluate003Dto> portfolioInfo(Map<String, Object> pageParam);
/**
 * 组合估值信息列表
 */
List<FinInvestmentEvaluate004Dto> findProduct7(Map<String, Object> pageParam);
/**
 * 策略执行有效性
 */
List<FinInvestmentEvaluate005Dto> findPage7(Map<String, Object> pageParam);
    /**
     * 策略执收益来源
     */
    List<FinInvestmentEvaluate006Dto> findPage8(Map<String, Object> pageParam);
/**
 * 组合
  */
List<SelectOptionDto> findPort(Map<String, Object> pageParam);
/**
 * 策略代码、名称
 */
List<SelectOptionDto> findStrategyId(Map<String, Object> pageParam);
/**
 * 收益指标目标
 */
List<FinInvestmentEvaluate007Dto> info7(Map<String, Object> pageParam);
/**
* 基准明细
*/
List<FinInvestmentEvaluate008Dto> info8(Map<String, Object> pageParam);
}
