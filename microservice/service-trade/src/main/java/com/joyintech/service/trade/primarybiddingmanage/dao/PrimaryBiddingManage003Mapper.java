
package com.joyintech.service.trade.primarybiddingmanage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.trade.primarybiddingmanage.dto.BidOrderBidPriceDto;
import com.joyintech.service.trade.primarybiddingmanage.dto.TrdBiddingManage003Dto;

/**
 * @author 裴家俊
 * @version 1.0 2018年9月7日
 * @see PrimaryBiddingManage003Mapper
 * @since 1.0
 */
@Repository
public interface PrimaryBiddingManage003Mapper {
	
	 List<BidOrderBidPriceDto> findBidPrice(Map<String, Object> param);
	 
	TrdBiddingManage003Dto getBussinessEntityByBidId(Map<String, Object> param);

    List<BidOrderBidPriceDto> getBidDetailByBidId(String bidId);

    String findProcessStatusByWorkflowId(String workflowId);

    Integer findTradeIdByBidId(String bidId);
}
