/*
 * 文件名：FinProductModule002Mapper.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：何阳阳 修改时间：2018年8月31日 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.productduration.addreduce.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.product.addreduce.*;

/**
 * 产品存续期-强增强减
 *
 * @author 强成西
 * @version 1.0 2018年8月31日
 * @see FinProductAddReduceMapper
 * @since 1.0
 */
@Repository
public interface FinProductAddReduceMapper {

    List<FinProductAddReduceDto> selectForPage(Map<String, Object> pageParam);

    List<SelectOptionDto> getProdId(Map<String, Object> param);

    String getSaleDepartment(Map<String, Object> pageParam);

    BigDecimal getValueByDate(Map<String, Object> pageParam);
    
    BigDecimal getProductAmount(Map<String, Object> pageParam);

    ProductAddReduceBaseDto selectByTotalTradeId(Map<String, Object> pageParam);

    List<ReduceInfoDto> findReduceInfoData(Map<String, Object> param);

    List<ProductFeeInfo> findProdFeeData(Map<String, Object> param);

    List<TrdProductDeal> selectAllProductDeal(String totalTradeId);

    List<TrdProductDealAdd> selectAllDealAdds(List<TrdProductDeal> trdProductDeals);

    List<PaTrdProductDeal> selectAllPaDeals(List<TrdProductDeal> trdProductDeals);

    List<StlPayOrdersDto> selectAllOrders(String totalTradeId);
    
    List<SelectOptionDto> getProdId01(Map<String, Object> param);
    
    List<ReduceInfoDto> findReduceInfoDataNew(Map<String, Object> param);
}
