/*
 * 文件名：AssetCommon002Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.asset.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.common.base.entity.AssetTypeConfig;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinExercisePlan;
import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductTypeConfig;
import com.joyintech.tams.common.base.entity.MstCustomerInfoAdd;
import com.joyintech.tams.invest.common.asset.dto.AssetCommonInfoDto;
import com.joyintech.tams.invest.common.dto.mock.asset.MockAssetInfoDto;
import com.joyintech.tams.invest.common.dto.mock.asset.MockFinCashPlanDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 *  资产端共通
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月25日
 * @see AssetCommon002Mapper
 * @since 1.0
 */
@Repository
public interface AssetCommon002Mapper {

    /**
     * 获取资产id联想文本框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SuggestDto> getAssetId(Map<String, Object> param);

    /**
     * 获取资产联想下拉框
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getAssetIdOption(Map<String, Object> map);
    /**
     * 交易日历下拉框
     *
     * @param param 参数map
     * @return 交易日历
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryCalendarInfoList(Map<String, Object> map);

    /**
     * 资产提供行
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getTradeOpponent(Map<String, Object> map);

    /**
     * 客户信息
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getCustomerName(Map<String, Object> map);

    AssetCommonInfoDto getAssetInfoToRateAdj(Map<String, Object> param);

    List<FinProduct> selectAllAsset();
    List<SelectOptionDto> getPflshList(Map<String, Object> param);
    /**
     *
     * 实际授信主体
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getActualCreditProvid(Map<String, Object> param);

    /**
     * 删除授信额度表
     * <功能详细描述>
     * @param creditLines
     * @param finprodId
     * @see
     * @since 1.0
     */
    Integer deleteCreditLinesByFinprodId(@Param("customerId") String customerId, @Param("finprodId") String finprodId);

    /**
     * 删除系列授信表
     * <功能详细描述>
     * @param creditLines
     * @see
     * @since 1.0
     */
    Integer deleteCreditAssetpoolDetailByFinprodId(String finprodId);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param seqno
     * @return
     * @see
     * @since 1.0
     */
    String getCreditAssetpoolSeqno(String seqno);

    /**
     * 判断资产是否重复
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    Integer isRepeated(Map<String, Object> param);

    MstCustomerInfoAdd getRepInfoByIssuer(Map<String, Object> param);

    //根据资产三级分类检索五级分类  add by yjm 2019-10-08 start
    /**
     * 根据资产三级分类检索五级分类
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<FinProductTypeConfig> getAssetThreeType(FinProductTypeConfig param);
    //根据资产三级分类检索五级分类  add by yjm 2019-10-08 end

    // 根据type6,7,8查询是否自动生成委外组合 add by wangpeng 2019-09-24 start
    /**
     * 根据type6,7,8查询是否自动生成委外组合
     * @param map
     * @return
     */
	List<AssetTypeConfig> selectAssetTypeConfig(Map<String, Object> map);
	// 根据type6,7,8查询是否自动生成委外组合 add by wangpeng 2019-09-24 end
    /**
     * 获取资产到期日
     * yjm 20191014
     * @param finprodId
     * @return
     */
    AssetCommonInfoDto getMdateByFinproId(@Param("finprodId") String finprodId);
    
    /**
	*
	* 金融产品基本信息查询
	* <功能详细描述>
	* @param param
	* @return
	* @see
	* @since 1.0
	*/
	   List<MockAssetInfoDto> selectAsset(Map<String, Object> param);
   
	   /**
	   *
	   * 基本信息批量查询
	   * <功能详细描述>
	   * @param param
	   * @return
	   * @see
	   * @since 1.0
	   */
	  List<MockAssetInfoDto> selectAssetjb(Map<String, Object> param);
  
	  /**
	  *
	  * 现金流计算规则批量查询
	  * <功能详细描述>
	  * @param param
	  * @return
	  * @see
	  * @since 1.0
	  */
	  List<FinCashCalcRule> selectAssetxjl(Map<String, Object> param);
 
	 /**
	 *
	 * 现金流计划批量查询
	 * <功能详细描述>
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<MockFinCashPlanDto> selectAssetjh(Map<String, Object> param);
	
	/**
	 *
	 * 行权计划批量查询
	 * <功能详细描述>
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<FinExercisePlan> selectAssetxq(Map<String, Object> param);
	
	/**
	 *
	 * 现金流支付规则批量查询
	 * <功能详细描述>
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<FinCashPayRule> selectxjlZf(Map<String, Object> param);
	
	/**
	 *
	 * 基金开放计划批量查询
	 * <功能详细描述>
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<FinOpenPlan> selectFundOpenjh(Map<String, Object> param);
}
