
package com.joyintech.tams.invest.trade.trdstockconnect.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.InterfTradStockdeal;
import com.joyintech.tams.invest.trade.trdstockconnect.dto.TrdStockConnectDto;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;

@Repository
public interface TrdStockConnectMapper {
	/**
	 * 查询港股通交易
	 * 
	 * @param pageParam 参数
	 * @return 港股通交易列表
	 */
	List<TrdStockConnectDto> selectDeal(Map<String, Object> pageParam);


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
    TrdStockConnectDto getTrdProductDealDto(Map<String, Object> params);
    
    String getRegistOrg(Map<String, Object> param);
    
	/*BigDecimal getTongDaoCash(Map<String, Object> map);*/

    TrdStockConnectDto selectEtrByKey(String entrustId);


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


	Date queryWorkDay(Map<String, Object> map);


	List<String> isPayWithTrade(Map<String, Object> map);


	BigDecimal getSettleRate(Map<String, Object> param);


	List<SuggestDto> getFinprodIdNew(Map<String, Object> params);


	Date getSpringFestivaDate(Map<String, Object> map);
}