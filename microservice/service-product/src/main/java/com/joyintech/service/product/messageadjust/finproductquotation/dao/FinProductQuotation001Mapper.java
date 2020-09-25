package com.joyintech.service.product.messageadjust.finproductquotation.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.messageadjust.finproductquotation.dto.FinProductQuotation001Dto;
import com.joyintech.service.product.messageadjust.finproductquotation.dto.FinProductQuotation002Dto;
import com.joyintech.service.product.messageadjust.finproductquotation.dto.FinProductQuotation003Dto;
import com.joyintech.tams.common.base.entity.PrdLayerParam;
/**
 * 产品报价管理-查询微服务端mapper
 * @author wangkang
 *
 */
@Repository
public interface FinProductQuotation001Mapper {
    
    /**
     * 产品代码联想下拉框
     * 
     * @param map 参数map
     * @return 产品信息
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryProdIdInfoIssueSuc(Map<String, Object> map);
    
	/**
	 * 产品报价管理查询
	 * @param pageParam
	 * @return
	 */
    List<FinProductQuotation001Dto> selectForPage(Map<String, Object> pageParam);
    /**
     * 产品报价查询时产品代码联想下拉框
     * @param pageParam
     * @return
     */
    List<SelectOptionDto> getFinprodId(Map<String, Object> pageParam);
    
    /**
     * 产品报价查询时产品款型联想下拉框
     * @param pageParam
     * @return
     */
    List<SelectOptionDto> getProdType(Map<String, Object> pageParam);
    /**
     * 产品报价管理新建的产品代码联想下拉框
     * @param pageParam
     * @return
     */
    List<SelectOptionDto> getFinprodId2(@Param("finprodId")String finprodId);

    /**
     * 根据产品代码查询产品基本信息
     * 
     * @param param 参数
     * @return 产品基本信息
     * @see
     * @since 1.0
     */
    FinProductQuotation001Dto queryProdInfoByFinprodId(Map<String, Object> param);
    
    /**
     * 
     * 净值、货币产品生效日判断
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    int getValueDate(Map<String, Object> param);
    
    /**
     * 
     * 检查是否存在生效日的报价
     * 
     * @param param 入参
     * @return 报价存在个数
     * @see
     * @since 1.0
     */
    int checkEffDateQuoteExists(Map<String, Object> param);
    
    /**
     * 根据产品代码得到起息日和到期日 
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    FinProductQuotation001Dto getVdateAndMdate(@Param("finprodId")String finprodId);
    
    /**
     * 根据金融产品代码和报价日查询报价信息
     * 
     * @param param 入参
     * @return 报价信息
     * @see
     * @since 1.0
     */
    FinProductQuotation002Dto queryQuoteInfoByFinprodIdEffDate(Map<String, Object> param);
    
    /**
     * 净值，货币修改查看数据 
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    FinProductQuotation001Dto selectDetailOther(Map<String, Object> param);    
    
    /**
     * 根据金融产品代码查询分层信息
     * @param finprodId 金融产品代码
     * @param effDate 生效日期
     * @return 分层信息
     */
    List<PrdLayerParam> queryLayerDetailByFinprodId(@Param("finprodId")String finprodId, @Param("effDate")String effDate);
    
    /**
     * 根据金融产品代码和报价日查询分层报价信息
     * 
     * @param param 入参
     * @return 报价信息
     * @see
     * @since 1.0
     */
    List<FinProductQuotation003Dto> queryQuoteLayDetailInfoList(Map<String, Object> param);
}
