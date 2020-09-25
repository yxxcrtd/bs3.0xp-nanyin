/*
 * 文件名：FinProductBuildMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.product.productelement.build.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.FinBaseRuleInfo;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinFloatCalcRuleDetail;
import com.joyintech.tams.invest.common.valuationinterf.dto.BussFinProductFundAccountInfoDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.product.common.dto.BussFinProductInvestPeriodDto;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.product.messageadjust.finfloatmgtexpquotation.dto.FinFloatMgtExpQuotationDto;
import com.joyintech.service.product.common.dto.BussFinProductLayeredInfoDto;
import com.joyintech.service.product.common.dto.FinProductCommonInfoDto;
import com.joyintech.service.product.common.dto.FinProductFloatFeeDto;
import com.joyintech.service.product.productelement.build.dto.FinProductBuild001Dto;
import com.joyintech.service.product.productelement.build.dto.MstCustomerNatureDto;
import com.joyintech.service.product.productelement.scheduling.dao.FinProductSchedulingMapper;
import com.joyintech.service.product.common.dto.BussBenchMarkInfoDto;

/**
 * 产品新建管理
 *
 * @author zhangliang
 * @version 1.0 2018年9月17日
 * @see FinProductSchedulingMapper
 * @since 1.0
 */
@Repository
public interface FinProductBuildMapper {

	/**
	 * 查询产品所有数据
	 *
	 * @param map 参数map
	 * @return 产品所有数据
	 * @see
	 * @since 1.0
	 */
	List<FinProductBuild001Dto> selectForPage(Map<String, Object> map);

	/**
	 * 查询产品所有数据
	 *
	 * @param map 参数map
	 * @return 产品所有数据
	 * @see
	 * @since 1.0
	 */
	List<FinProductBuild001Dto> selectForLedger(Map<String, Object> map);
	
	List<FinBaseRuleInfo> getFinBaseRuleInfo(Map<String, Object> map);
	List<FinBaseRuleInfo> getFinBaseRuleInfo2(Map<String, Object> map);

	/**
	 * 组合代码联想下拉框
	 *
	 * @param param 参数map
	 * @return 组合信息
	 * @see
	 * @since 1.0
	 */
	List<SelectOptionDto> queryGroupIdInfo(Map<String, Object> map);


	/**
	 * 产品代码联想下拉框
	 *
	 * @param param 参数map
	 * @return 产品信息
	 * @see
	 * @since 1.0
	 */
	List<SelectOptionDto> queryProdIdInfo(Map<String, Object> map);

	/**
	 * @param finprodId 金融产品代码
	 * @return 查询个数
	 */
	int querySchedulingCountByFinprodId(@Param("finprodId") String finprodId);

	/**
     * @param finprodId 金融产品代码
     * @param finprodTypeProd 金融产品类型
     * @return 查询个数
     */
	int queryProdCountByFinprodId(@Param("finprodId") String finprodId, @Param("finprodTypeProd") String finprodTypeProd);

	/**
	 * 根据金融产品代码查询产品信息
	 * @param map 参数
	 * @return 产品信息
	 */
	FinProductCommonInfoDto queryProdInfoByFinprodId(Map<String, Object> map);
	List<BussFinProductFundAccountInfoDto> getFunInfo(Map<String, Object> map);
	List<BussFinProductLayeredInfoDto> getLayerParam(Map<String, Object> map);
	List<BussFinProductLayeredInfoDto> getLayerParamList(Map<String, Object> map);
	/**
     * 查询机构
     *
     * @param dto dto
     * @return 结
     * @since 1.0
     */
    public List<MstCustomerNatureDto> queryTrusteeId(Map<String, Object> map);
    FinProductCommonInfoDto queryProdInfoByProdId(Map<String, Object> map);
	List<BussFinProductLayeredInfoDto> getLayerParamFinprodIdList(Map<String, Object> map);
	
    List<FinFloatCalcRuleDetail> queryFloatFeeDetailByFinprodId(Map<String, Object> map);
    List<FinFloatCalcRuleDetail> queryFloatFeeDetailByFinprodId2(Map<String, Object> map);
    List<FinProductFloatFeeDto> queryLayerFloatFeeInfoByFinprodId(Map<String, Object> map);
    List<BussFinProductInvestPeriodDto> queryPeriodList(Map<String,Object> param);
    List<FinFloatMgtExpQuotationDto> getRuleDetail(Map<String,Object> param);
    List<BussBenchMarkInfoDto> selectByFinprodidList(Map<String, Object> param);
	/**
	 * 更新分层产品的发行状态
	 * @param finProductBuild001Dto
	 * @return
	 */
	int updateIssueStatusByPfinprodId(FinProductBuild001Dto finProductBuild001Dto);
	
	
	//检查预览条件
	int getPreview(@Param("finprodId") String finprodId);
	
	
	List<FinProductBuild001Dto> getTermType(Map<String, Object> map);

	List<FinProductBuild001Dto> getTermType02(Map<String, Object> map);
	
	List<FinProductBuild001Dto> getTermType03(Map<String, Object> map);
	
	String getOpenMode(@Param("finprodId") String finprodId);
	
	List<SelectOptionDto> queryPrdDesign(Map<String, Object> param);
	//根据组合代码查询付息周期
	FinCashPayRule  getPayCycle(Map<String, Object> map);
}
