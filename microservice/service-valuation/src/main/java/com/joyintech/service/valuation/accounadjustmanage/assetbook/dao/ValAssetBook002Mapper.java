/*
 * 文件名：ValAssetBook002Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huangjian
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.assetbook.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.dto.ValAcctStandardDto;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.dto.ValAssetbook002Dto;
import com.joyintech.tams.common.base.entity.BokBooksetPlan;
import com.joyintech.tams.common.base.entity.BokSchemeInfo;
import com.joyintech.tams.common.base.entity.BokValScheme;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author huangjian
 * @version 1.0 2018年5月10日
 * @see ValAssetBook002Mapper
 * @since 1.0
 */
@Repository(value="serviceValAssetBook002Mapper")
public interface ValAssetBook002Mapper {

    /**
     * 获取会计准则名称
     * 
     * @return List<ValAcctStandardDto>
     * @see
     * @since 1.0
     */
    List<ValAcctStandardDto> getAcctStandard();

    /**
     * 验证帐套编码是否存在
     *
     * @param id 账套代码
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean checkBooksetId(String id);
    
    /**
     * 验证帐套名称是否存在
     *
     * @param id 账套代码
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean checkBooksetName(ValAssetbook002Dto dto);
    

    /**
     * 获取本位币 <功能详细描述>
     * 
     * @return List<String>
     * @see
     * @since 1.0
     */
    List<String> getStandardCcy();

    /**
     * 通过账套代码查询账套信息
     * @param booksetId 账套代码
     * @return ValAssetbook002Dto
     * @see
     * @since 1.0
     */
    ValAssetbook002Dto getBookSetById(@Param("booksetId") String booksetId);

    /**
     * 下拉框：所属估值方案
     * @param pageParam
     * @return
     */
	List<BokValScheme> selectScheme(Map<String, Object> pageParam);

	/**
	 * 修改账套估值方案
	 * @param params
	 * @return
	 */
	int updateBooksetPlan(BokBooksetPlan bokBooksetPlan);

	/**
	 * 修改账套估值方案
	 * @param params
	 * @return
	 */
	int insertBooksetPlan(BokBooksetPlan bokBooksetPlan);

	/**
	 * 查询账套生成凭证数
	 * @param booksetId
	 * @return
	 */
	int queryVoucherCount(@Param("booksetId") String booksetId);

	int getDataInWorklow(ProcessMark processMark);
	
	List<BokSchemeInfo> selectTaxScheme(Map<String, Object> pageParam);
	
	List<BokSchemeInfo> selectShadowPriceScheme(Map<String, Object> pageParam);
}
