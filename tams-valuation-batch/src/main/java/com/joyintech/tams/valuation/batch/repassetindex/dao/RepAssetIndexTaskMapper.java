/*
 * 文件名：ProfitDistributionBookMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.repassetindex.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.BokBalance;
import com.joyintech.tams.common.base.entity.BokStatDetPosiiton;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.valuation.batch.repassetindex.dto.BokBookset;

/**
 * 
 * 
 * @author 
 * @version 1.0 
 * @see RepAssetIndexTaskMapper
 * @since 1.0
 */
@Repository
public interface RepAssetIndexTaskMapper {
    
	/**
	 * 查询指定帐套的核算主体和TA产品
	 * @param params
	 * @return
	 */
    List<BokBookset>  selectBokBookset(Map<String, Object> params);
    
    /**
     * 取统计分析明细(账务明细)
     * @param dto
     * @return
     */
    List<BokStatDetPosiiton> selectBokStatDetPosiiton(BokStatDetPosiiton dto);
    
    /**
     * 取科目余额
     * @param dto
     * @return
     */
    List<BokBalance> selectBokBalance(BokBalance dto);
    
    /**
     * 取金融产品数据
     * @param finProduct
     * @return
     */
    FinProduct  selectFinProduct(FinProduct finProduct);
    
    /**
     * 取金融产品分类数据
     * 
     */
    FinProductType selectFinProductType(FinProductType finProductType);
    
     /**
      * 取估值增值
      * 
      */
      BigDecimal  getValueIncrement(Map<String, Object> params);
      
      /**
       * 删除资产指标表
       * 
       * @param param
       * @return
       */
      int deleteRepAssetIndex(Map<String, Object> param);
      
      
      String  getprodId(String fprodId);
      /**
       * 取统计分析明细(账务明细) add by wm 根据估值提供最新方法修改，老方法会查询出重复数据
       * @param dto
       * @return
       */
      List<BokStatDetPosiiton> selectBokStatDetPosiitonNew(BokStatDetPosiiton dto);
}
