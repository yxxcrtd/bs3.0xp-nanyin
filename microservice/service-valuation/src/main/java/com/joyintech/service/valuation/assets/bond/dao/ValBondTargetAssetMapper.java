/*
 * 文件名：ValBondTargetAssetMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.assets.bond.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.assets.bond.dto.ValBondTargetAssetDto;
import com.joyintech.service.valuation.assets.bondasset.dto.ValBondassetquery001Dto;
import com.joyintech.service.valuation.component.common.dto.InterfPayhsrcsecinfoDto;
import com.joyintech.service.valuation.component.common.dto.ValMstCustomerNatureDto;
import com.joyintech.tams.common.base.entity.FinProductTypeConfig;
import com.joyintech.tams.common.base.entity.InterfCbonddescription;
import com.joyintech.tams.common.base.entity.InterfFloatcouprate;
import com.joyintech.tams.common.base.entity.InterfPayhsrcsecoption;
import com.joyintech.tams.common.base.entity.InterfPayhsrcsecrating;
import com.joyintech.tams.common.base.entity.InterfPayhsrcsecschedule;
import com.joyintech.tams.common.base.entity.MstCalendarInfo;
import com.joyintech.tams.common.base.entity.MstCustomerInfoAdd;

/**
 * 债券标的信息
 * @author zhangnian
 * @version 1.0 2018年9月20日
 * @see ValBondTargetAssetMapper
 * @since 1.0
 */
@Repository
public interface ValBondTargetAssetMapper {

    //List<FinBondTargetAssetDto> queryForPage(Map<String, Object> pageParam);
    /**
     * 
     * 页面初始化查询列表
     * @param params 参数    
     * @return List<ValBondassetquery001Dto>
     * @see
     * @since 1.0
     */
    List<ValBondTargetAssetDto> queryPageList(Map<String, Object> params);
    
    ValBondTargetAssetDto getBasicInfo(@Param("finprodId") String finprodId
                                       // 债券新建新增快捷录入功能 add by renping 20191010 start
                                       , @Param("finprodType") String finprodType);
                                       // 债券新建新增快捷录入功能 add by renping 20191010 end
    
    int selectCountByfinprodMarketId(@Param("finprodMarketId") String finprodMarketId);

    /**
     * 获取主体评级机构数据
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<ValBondTargetAssetDto> getZTPJ(Map<String, Object> param);

    /**
     * 获取债项评级机构数据
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<ValBondTargetAssetDto> getZXPJ(Map<String, Object> param);
    /**
     * 获取新建界面的以及市场投标债券代码联想下拉框
     * 
     * @author wangguiqi
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getfinprodIdForAdd(Map<String, Object> param);

	int isHqTrade(@Param("finprodId") String finprodId);
	
	
	InterfPayhsrcsecinfoDto selectWindBondAssetBySecId(@Param("secid") String secid);
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
     * 获取WIND_浮息债报价表(债券)
     * <功能详细描述>
     * @param secid
     * @return
     * @see
     * @since 1.0
     */
    List<InterfFloatcouprate> selectInterfFloatingcouponsrate(String secid);
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
     * 日历信息--用于wind 自动加券
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    List<MstCalendarInfo> getMstCalendarInfo();
    String getIssuer(@Param("linkId1") String linkId1);
    /**
     * 通过主键查询方法 <一句话功能简述> <功能详细描述>
     * 
     * @param param map
     * @return MstCustomerNatureDto
     * @see
     * @since 1.0
     */
    ValMstCustomerNatureDto selectByPrimaryKey(Map<String, Object> param);
    /**
     * 通过主键修改方法
     * @param record MstCustomerNatureDto
     * @return int
     */
    int updateByPrimaryKey(ValMstCustomerNatureDto record);
	/**
	   * 查询资讯_债券评级中主体评级结果
	   * @param sInfoWindcode
	   * @return
	   */
	List<InterfPayhsrcsecrating> selectRating(Map<Object,Object> map);
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
     * 根据资产三级分类检索五级分类
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<FinProductTypeConfig> getAssetThreeType(FinProductTypeConfig param);
    /**
     * 债券下拉框初始化
     * 
     * @return
     * @see
     * @since 1.0
     */
    List<ValBondTargetAssetDto> getBondId(Map<String, Object> params);
    List<SelectOptionDto> getCustomer(Map<String, Object> param);
	/**
	 * 交易日历下拉框
	 * 
	 * @param param
	 *            参数map
	 * @return 交易日历
	 * @see
	 * @since 1.0
	 */
	List<SelectOptionDto> queryCalendarInfoList(Map<String, Object> map);
}
