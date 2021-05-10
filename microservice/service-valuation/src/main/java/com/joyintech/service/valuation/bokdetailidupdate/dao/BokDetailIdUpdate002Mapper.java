package com.joyintech.service.valuation.bokdetailidupdate.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.bokdetailidupdate.dto.BokBussDetsubDto;
import com.joyintech.service.valuation.bokdetailidupdate.dto.BokDetailIdUpdate003Dto;
import com.joyintech.tams.common.base.entity.BokBooksetExt;

@Repository("serviceBokDetailIdUpdate002Mapper")
public interface BokDetailIdUpdate002Mapper {
	
	public List<BokBooksetExt> queryBokBooksetExtList(Map<String,Object> map);
	
	/**
	 * 查询账务当前表四级科目相关信息
	 * @param map
	 * @return
	 */
	public List<BokBussDetsubDto> queryBokBussDetsubDtoList(Map<String,Object> map);
	
	/**
	 * 四级科目更新-账务明细历史表
	 * @param list
	 * @return
	 */
	int updateDetailSubjectNo1(@Param("list") List<BokBussDetsubDto> list);
	
	/**
	 * 四级科目更新-账务明细历史表
	 * @param list
	 * @return
	 */
	int updateDetailSubjectNo2(@Param("list") List<BokBussDetsubDto> list);
	
	
	
	/**
	 * 增值税已抵扣增值额
	 * @param map
	 * @return
	 */
	public int updateTaxDeductedBalance(Map<String,Object> map);
	
	/**
	 * 增值税待抵扣增值额
	 * @param map
	 * @return
	 */
	public int updateTaxTurnedBalance(Map<String,Object> map);
	
	/**
	 * 增值税账务明细余额
	 * @param map
	 * @return
	 */
	public int updateTaxBokBalance(Map<String,Object> map);
	
	
	/**
	 * 增值税账务明细流水
	 * @param map
	 * @return
	 */
	public int updateTaxBokDetail(Map<String,Object> map);
	
	/**
	 * 统计分析明细表（账务明细）
	 * @param map
	 * @return
	 */
	public int updateBokStatDetPosiiton(Map<String,Object> map);
	
	
	/**
	 * 账务明细辅助表
	 * @param map
	 * @return
	 */
	public int updateBokBookdetailCurrentExt(Map<String,Object> map);
	
	
	
	/**
	 * 账务明细（历史表）
	 * @param map
	 * @return
	 */
	public int updateBokBookdetailAll(Map<String,Object> map);
	
	
	
	
	
	
	
	
	
	/**
	 * 手工凭证交易明细
	 * @param map
	 * @return
	 */
	public int updateTrdVouchDealDetail(Map<String,Object> map);
	
	
	/**
	 * 核算分层信息
	 * @param map
	 * @return
	 */
	public int updateBokAcctLayeringInfo(Map<String,Object> map);
	
	
	
	/**
	 * 账务明细（当前表）
	 * @param map
	 * @return
	 */
	public int updateBokBookdetailCurrent(Map<String,Object> map);
	
	/**
	 * 账务明细代码表删除
	 * @param map
	 * @return
	 */
	public int deleteBokDetailId(Map<String,Object> map);
	/**
	 * 账务明细代码入库
	 * @param map
	 * @return
	 */
	public int insertBokDetailId(BokDetailIdUpdate003Dto dto);
	
	/**
	 * 交易入账明细表
	 * @param map
	 * @return
	 */
	public int updateBokDealAccountingDetail(Map<String,Object> map);
	
	/**
	 * 结算指令-本方账户更新
	 * @param dto
	 * @return
	 */
	public int updateStlOrders1(BokDetailIdUpdate003Dto dto);
	/**
	 * 结算指令-对方账户更新
	 * @param dto
	 * @return
	 */
	public int updateStlOrders2(BokDetailIdUpdate003Dto dto);
	/**
	 * 资金账户信息
	 * @param finprodType
	 * @param booksetId
	 * @return
	 */
	List<SuggestDto> getBooksetCapacctInfo(@Param("finprodType") String finprodType,
            @Param("booksetId") String booksetId);
	
	 List<SuggestDto> getAcctLayeringInfo(@Param("booksetId") String booksetId,
             @Param("finprodType") String finprodType);   
	 
	 
	 List<SuggestDto> getFinProduct(@Param("finprodType") String finprodType);
	 
	 List<SuggestDto> getFinTradeProduct(@Param("finprodType") String finprodType);
}
