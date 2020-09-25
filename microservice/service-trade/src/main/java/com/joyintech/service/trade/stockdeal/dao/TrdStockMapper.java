/*
 * 文件名：TrdStockMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年7月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.trade.stockdeal.dao;

import java.util.List;
import java.util.Map;


import com.joyintech.tams.common.base.entity.InterfTradStockdeal;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.trade.stockdeal.dto.TrdStockDto;

@Repository
public interface TrdStockMapper {
	/**
	 * 查询股票交易
	 * 
	 * @param pageParam 参数
	 * @return 股票交易列表
	 */
	List<TrdStockDto> selectDeal(Map<String, Object> pageParam);


	/**
     * 查询证券托管户：联想框文本框
     *
     * @param param 画面参数
     * @return 证券托管户：联想框文本框
     * @see
     * @since 1.0
     */
    List<SuggestDto> getSecManageAcctId(Map<String, Object> param);
    
   
    /**
     *	通道
     *
     * @param param 画面参数
     * @return 通道信息
     * @see
     * @since 1.0
     */
/*    List<SuggestDto> getChannel(Map<String, Object> param);
    List<String> getChannelInfo(Map<String, Object> param);*/
    
    /**
     * 资产代码
     * @param param
     * @return
     */
    List<SelectOptionDto> getFinprodId(Map<String, Object> param);
    
    
    /**
     * 实体类Dto
     * @param params
     * @return
     */
    TrdStockDto getTrdProductDealDto(Map<String, Object> params);
    
    String getRegistOrg(Map<String, Object> param);
    
	/*BigDecimal getTongDaoCash(Map<String, Object> map);*/

	TrdStockDto selectEtrByKey(String entrustId);


	/**
	 * 查询债券cstp交易下拉框
	 * @param param 输入参数
	 * @return
	 */
	List<SuggestDto> queryRepoCstpInfoList(Map<String, Object> param);

	/**
	 * 根据成交单号查询CSTP交易信息
	 *
	 * @param fedealno 成交单号
	 * @return CSTP交易信息
	 * @see
	 * @since 1.0
	 */
	InterfTradStockdeal queryRepoCstpInfoByNo(@Param("fedealno") String fedealno);
}