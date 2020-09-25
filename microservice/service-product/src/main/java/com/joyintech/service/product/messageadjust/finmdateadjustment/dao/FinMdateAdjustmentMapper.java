/**
 * 
 */
package com.joyintech.service.product.messageadjust.finmdateadjustment.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinFloatAccrualSche;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.messageadjust.finmdateadjustment.dto.FinMdateAdjustmentDto;

/**
 * @author zhangliang
 *
 */
@Repository
public interface FinMdateAdjustmentMapper {
    
    /**
     * 查询到期日调整信息
     * @param map 入参
     * @return 到期日调整信息
     */
    List<FinMdateAdjustmentDto> queryAdjustMdateList(Map<String, Object> map);

    /**
     * 到期日调整新建产品代码下拉框检索逻辑
     * @param map 入参
     * @return 产品代码下拉框
     */
    List<SelectOptionDto> queryAdjustProdIdList(Map<String, Object> map);
    
    /**
     * 根据金融产品代码查询到期日调整信息
     * @param finprodId 金融产品代码
     * @return 到期日调整信息
     */
    FinMdateAdjustmentDto queryAdjustInfoByFinprodId(String finprodId);
    
    /**
     * 查询组合到期日
     * @param finprodId 金融产品代码
     * @return 组合到期日
     */
    String queryPortFolioMdateByFinprodId(String finprodId);
    
    /**
     * 查询是否有后续交易
     * @param map 参数
     * @return 交易个数
     */
    int queryDealCountByFinprodId(Map<String, Object> map);
    
    /**
     * 查询现金流代码
     * @param map 入参
     * @return 现金流代码
     */
    String queryCashIdByCashType(Map<String, Object> map);
    
    /**
     * 根据现金流代码删除开放计划
     * @param cashId 现金流代码
     * @return DB
     */
    int deleteOpenPlanByCashId(String cashId);
    
    /**
     * 根据现金流代码删除付息计划
     * @param cashId 现金流代码
     * @return DB
     */
    int deletePayPlanByCashId(String cashId);
    
    /**
     * 根据金融产品代码查询关联信息
     * @param map 金融产品代码
     * @return 关联信息
     */
    List<FinMdateAdjustmentDto> queryRelateInfoList(Map<String, Object> map);
    
    /**
     * 获取产品的最大调整批次
     * @param finprodId
     * @return 最大调整批次
     */
    int getMaxAdjustNum(@Param("finprodId")String finprodId);

    /**
     * 查询是否是存续产品
     * @param pageParam
     * @return
     */
    FinMdateAdjustmentDto queryProductByProdId(Map<String, Object> pageParam);
    /**
     * 查询计提计划
     * @param cashId
     * @return
     */
	List<FinFloatAccrualSche> queryAccrPlanByCashId(String cashId);
	
	/**
	 * 查询分层子产品信息
	 * <功能详细描述>
	 * @param finprodId
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<FinProduct> queryLayeredChildProductByProdid(String finprodId);
}
