package com.joyintech.service.entrust.etrtransactiondetail.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.entrust.etrinvestallinfo.dto.EtrInvestAllInfoDto;
import com.joyintech.service.entrust.etrtransactiondetail.dto.EtrTransactionDetailDto;
import com.joyintech.service.entrust.etrtransactiondetail.dto.TrdProductDealDetailDto;

@Repository
public interface EtrTransactionDetailMapper {
	 /**
     * 
     * 获取组合代码联想文本框信息
     * <功能详细描述>
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    List<SuggestDto> getentrustId(@Param("entrustId") String entrustId);
    /**
     * 
     * 获取资产代码联想文本框信息
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    List<SuggestDto> getfinprodId(@Param("finprodId") String finprodId);
    
	/**
	 * 
	 * 查询所有场内交易指令信息 <功能详细描述>
	 * 
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<EtrTransactionDetailDto> select(Map<String, Object> param);
	

	/**
	 * 查询指令成交明细记录
	 * 
	 * @param param
	 * @return
	 */
	List<TrdProductDealDetailDto> selectTransDetailPage(Map<String, Object> param);
}
