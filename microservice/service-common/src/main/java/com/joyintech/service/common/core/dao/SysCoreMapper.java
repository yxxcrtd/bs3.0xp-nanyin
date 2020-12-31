/*
 * 文件名：SysCoreMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：刘二通
 * 修改时间：2018年11月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.common.core.dao;

import com.joyintech.service.common.core.dto.AutoCouponDto;
import com.joyintech.service.common.core.dto.InfChinamutualfunddescriptionDto;
import com.joyintech.service.common.core.dto.InterfPayhsrcsecinfoDto;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.base.core.dto.WflValidInputParameter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 刘二通
 * @version 1.0 2018年11月4日
 * @see SysCoreMapper
 * @since 1.0
 */
@Repository
public interface SysCoreMapper {
    /**
     * 利用币种与交易日查询汇率行情表中最新的收盘价
     *
     * @param paramMap 参数
     * @return 汇率行情表中最新的收盘价
     * @see
     * @since 1.0
     */
    BigDecimal selectClosePrice(Map<String, Object> paramMap);

    /**
     * 资产是否存在交易
     *
     * @param finprodId 金融产品代码
     * @return 交易笔数
     * @see
     * @since 1.0
     */
    int isTransInAsset(String finprodId);
    
    Date getMaxTradeDate(String finprodId);
    
    List<InterfPayhsrcsecinfoDto> selectWindBondAsset(Map<String,Object> map);
    
    List<InfChinamutualfunddescriptionDto> selectWindFundAsset(Map<String,Object> map);
    
    InterfPayhsrcsecinfoDto selectWindBondAssetBySecId(@Param("secid") String secid);
    
    InterfCmfunddescrip selectWindFundAssetByfInfoWindcode(@Param("fInfoWindcode") String fInfoWindcode);
    /**
     * wind债券行权计划
     * <功能详细描述>
     * @param secid
     * @return
     * @see
     * @since 1.0
     */
    List<InterfPayhsrcsecoption>  selectWindBondExercise(@Param("secid") String secid);
    
    /**
     * 债券付息计划
     * <功能详细描述>
     * @param secid
     * @return
     * @see
     * @since 1.0
     */
    List<InterfPayhsrcsecschedule> selectWindBondSchedule(@Param("secid") String secid);
    
    /**
     * 中国货币市场基金份额结转方式
     * <功能详细描述>
     * @param sInfoWindcode
     * @return
     * @see
     * @since 1.0
     */
    InfCmoneymarketfscarryoverm selectWindFundMoneymarketfscarryoverm(@Param("sInfoWindcode") String sInfoWindcode);

    /**
     * 获取WIND_中国债券特殊条款(债券)
     * <功能详细描述>
     * @param secid
     * @return
     * @see
     * @since 1.0
     */
    InterfCbondspecialconditions selectInterfCbondspecialconditions(String secid);

    /**
     * 获取WIND_浮息债报价表(债券)
     * <功能详细描述>
     * @param secid
     * @return
     * @see
     * @since 1.0
     */
    List<InterfFloatcouprate> selectInterfFloatingcouponsrate(String secid);

    /**
     * 获取Wind 债券分类板块(债券)
     * <功能详细描述>
     * @param secid
     * @return
     * @see
     * @since 1.0
     */
    InterfCbondindustrywind selectInterfCbondindustrywind(String secid);

    /**
     * 获取中国债券基本资料(债券)
     * <功能详细描述>
     * @param secid
     * @return
     * @see
     * @since 1.0
     */
    InterfCbonddescription selectInterfCbonddescription(String secid);
    /**
     * 查询是否存在债券市值行情count
     * @param sInfoWindcode(新增)
     * @return
     */
    List<Integer> selectMstBondValuationInfoCount(@Param("finprodMarketId") String finprodMarketId);
    
	/**
     * 获取债券估值行情信息
     * <功能详细描述>
     * @param secid
     * @return
     * @see
     * @since 1.0
     */
    List<InterfPayhsrccbvlt> selectInterfPayhsrccbvlt(Map<Object,Object> map);
	/**
     * 自动加券(基金 获取wind代码)
     * 根据前台代码获取wind代码
     * @param finprodMarketId
     * @return
     * @see
     * @since 1.0
     */
    List<FinProduct> getFwindCode(String finprodMarketId);
	
	   /**
     * 查询资讯_市场行情数据INTERF_BOND_MARKET_INFO
     * @param finprodMarketId
     * @param systemDate
     * @param date
     * @return
     */
	List<InterfBondMarketInfo> selectInterfBondMarketInfo(Map<Object,Object> map);
	/**
     * 判断当前日期是否存在回购标准券折算率 (FIN_STDSEC_CONVERSE_RADIO)
     * @param finprodMarketId
     * @return
     */
    List<Integer> selectFinStdsecConverseRadioCount(Map<Object,Object> map);
    /**
     * 查询资讯_回购标准折算率(INTERF_STDSEC_CON_RADIO)
     * @param finprodMarketId
     * @return
     */
    List<InterfStdsecConRadio> selectInterfStdsecConRadio(Map<Object,Object> map);
    /**
   	 * 查询是否存在场外行情信息-基金加券
   	 * @param finprodMarketId
   	 * @return
   	 */
   	List<Integer> selectFinProductValuationCount(@Param("finprodMarketId") String finprodMarketId);
   	/**
     * 查询资讯_基金净值list-基金加券
     * @param sInfoWindcode
     * @return
     */
   	List<InterfCmfundnav> selectInterfCmfundnav(Map<Object,Object> map);
   	/**
   	 * 查询是否存在场外行情信息-基金加券(INTERF_F_MARKET_INFO)
   	 * @param finprodMarketId
   	 * @return
   	 */
   	List<Integer> selectMstFundMarketInfoCount(@Param("finprodMarketId") String finprodMarketId);
   	/**
	  * 查询资讯_基金市场行情list-基金加券
	  * @param sInfoWindcode
	  * @return
	  */
   	List<InterfFMarketInfo> selectInterfFMarketInfo(Map<Object,Object> map);
   	/**
	   * 查询客户信息附属表list-债券中债
	   * @param sInfoWindcode
	   * @return
	   */
	List<MstCustomerInfoAdd> selectMstCustomerInfoAdd(@Param("customerid") String customerid);
	 /**
	   * 查询资讯_债券评级信息表list
	   * @param sInfoWindcode
	   * @return
	   */
	List<InterfPayhsrcsecrating> selectInterfPayhsrcsecrating(@Param("secid") String secid);
	/**
	   * 查询资讯_债券评级中主体评级结果
	   * @param sInfoWindcode
	   * @return
	   */
	List<InterfPayhsrcsecrating> selectRating(Map<Object,Object> map);

    /**
     * 自动加券 
     * 投资委托根据资产代码查询入库表之后的金融产品代码信息
     * @param finprodMarketId
     * @return
     * @see
     * @since 1.0
     */
	AutoCouponDto selectAutoFinProd(String finprodMarketId);

	// 资产五级分类联动检索 add by renping 2019-07-11 start
	/**
	 * 判断资产是否重复
	 * <功能详细描述>
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<FinProductTypeConfig> getAssetFiveType(FinProductTypeConfig param);
	// 资产五级分类联动检索 add by renping 2019-07-11 end

	/**
	 * 判断是走正常流程新建的交易，还是未走流程直接入库的交易
	 * @param parameter
	 * @return
	 */
	int isProcessData(WflValidInputParameter parameter);

	/**
	 * 债券资产是否存在行权交易
	 *
	 * @param finprodId 金融产品代码
	 * @return 交易笔数
	 * @see
	 * @since 1.0
	 */
	int isBondExerciseAsset(String finprodId);
}
