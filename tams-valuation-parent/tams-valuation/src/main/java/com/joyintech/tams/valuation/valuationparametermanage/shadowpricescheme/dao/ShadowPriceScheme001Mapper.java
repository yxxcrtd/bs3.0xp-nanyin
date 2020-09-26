/*
 * 文件名：ShadowPriceScheme001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.valuationparametermanage.shadowpricescheme.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.tams.valuation.valuationparametermanage.shadowpricescheme.dto.ShadowPriceScheme001Dto;
import com.joyintech.tams.common.base.entity.BokShadowPriceDetail;
/**
 * 影子定价左侧mapper
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lishicheng
 * @version 1.0 2018年11月22日
 * @see ShadowPriceScheme001Mapper
 * @since 1.0
 */
@Repository
public interface ShadowPriceScheme001Mapper {
    /**
     * 左侧影子方案查询
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     */
	List<ShadowPriceScheme001Dto> findShadowPrice(Map<String, Object> pageParam);
    /**
     * 左侧影子方案新建
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     */
	void insert(ShadowPriceScheme001Dto dto);
	/**
	 * 左侧影子方案修改
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param dto
	 */
	void updateByPrimaryKeySelective(ShadowPriceScheme001Dto dto);
	/**
	 *  修改时根据方案id查询
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param schName
	 * @return
	 */
	ShadowPriceScheme001Dto selectSchemeBySchId(String schId);
	/**
	 * 左侧影子方案代码删除
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param schName
	 * @return
	 */
	int deleteShadowPriceSch(String schId);
	/**
	 * 影子定价方案删除验证
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param params
	 * @return
	 */
	List<ShadowPriceScheme001Dto> findScheme(Map<String, Object> params);
	/**
	  * 判断是否在流程中的数据
	  * <一句话功能简述>
	  * <功能详细描述>
	  * @param processMark
	  * @return
	  */
	 List<String> getBusinessDataInFlow(ProcessMark processMark);
	
	 /**
	  * 查询默认方案
	  * @return 查询默认方案
	  * @see
	  * @since 1.0
	  */
	 ShadowPriceScheme001Dto getDefaultScheme(@Param("schemeType") String schemeType,
	                                          @Param("isDefault") String isDefault);
	 
	 /**
	  * 复制方案代码对应的明细
	  * 将新的方案代码赋值
	  * @param schId 当前的方案代码
	  * @param orgSchId 被复制的方案代码
	  * @return 复制明细
	  * @see
	  * @since 1.0
	  */
	 List<BokShadowPriceDetail> getShadowPriceDetialCopy(@Param("schId") String schId,
	                                                     @Param("orgSchId") String orgSchId);
	 
	 /**
	  * 影子方案明细批量入库
	  * @param dto
     */
	 int insertBatch(List<BokShadowPriceDetail> list);
}
