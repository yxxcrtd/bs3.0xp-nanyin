
package com.joyintech.service.trade.primarybiddingmanage.service;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.trade.primarybiddingmanage.dao.PrimaryBiddingManage003Mapper;
import com.joyintech.service.trade.primarybiddingmanage.dto.BidOrderBidPriceDto;
import com.joyintech.service.trade.primarybiddingmanage.dto.TrdBiddingManage003Dto;
import com.joyintech.tams.common.base.dao.BidOrderBidPriceMapper;
import com.joyintech.tams.common.base.dao.BidOrderMapper;
import com.joyintech.tams.common.base.dao.EtrEntrustMapper;
import com.joyintech.tams.common.base.entity.BidOrderBidPrice;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;

/**
 * 债券交易Service
 *
 * @author 裴家俊
 * @version 1.0 2018年9月7日
 * @see PrimaryBiddingManage003Service
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
public class PrimaryBiddingManage003Service extends BaseService<BidOrderBidPriceDto> {

    @Autowired
    private PrimaryBiddingManage003Mapper primaryBiddingManage003Mapper;
    
    @Autowired
    private BidOrderMapper bidOrderMapper;

    @Autowired
    private BidOrderBidPriceMapper bidOrderBidPriceMapper;

    @Autowired
    private EtrEntrustMapper etrEntrustMapper;
    
    @Autowired(required = false)
    private BaseEtrCashPositionCommService baseEtrCashPositionCommService;

    public TrdBiddingManage003Dto getBussinessEntityByBidId(Map<String, Object> param) {
        return primaryBiddingManage003Mapper.getBussinessEntityByBidId(param);
    }

//    public Page<BidOrderBidPriceDto> findBidPrice(int page, int length, Map<String, Object> param)
//            throws Exception {
//            return super.queryForPage(page, length, () -> primaryBiddingManage003Mapper.findBidPrice(param));
//        }

    public List<BidOrderBidPriceDto> findBidPrice(Map<String, Object> param)
        throws Exception {
        return primaryBiddingManage003Mapper.findBidPrice(param);
    }

    public List<BidOrderBidPriceDto> getBidDetailByBidId(String bidId)
        throws Exception {
        return primaryBiddingManage003Mapper.getBidDetailByBidId(bidId);
    }

    /**
     * 插入
     * @param entity
     * @return
     * @since 1.0
     */
    public void insert(TrdBiddingManage003Dto entity) {
        for (BidOrderBidPriceDto bidOrderBidPriceDto : entity.getBidOrderBidPriceList()) {
            bidOrderBidPriceDto.setBidPrice(bidOrderBidPriceDto.getBidPrice().divide(BigDecimal.valueOf(100)));
            bidOrderBidPriceDto.setWinPrice(bidOrderBidPriceDto.getWinPrice().divide(BigDecimal.valueOf(100)));
        }
    	bidOrderMapper.insert(entity);
    }
    
   
    /**
     * 更新
     * @param entity
     * @return
     * @since 1.0
     */
    public void update(TrdBiddingManage003Dto entity) {
    	if(null!=entity){
    	    if (null != entity.getWinPrice()){
                entity.setWinPrice(entity.getWinPrice().divide(BigDecimal.valueOf(100)));
            }
    		bidOrderMapper.updateByPrimaryKeySelective(entity);
            if(!StringUtil.isEmptyList(entity.getBidOrderBidPriceList())){
                for(BidOrderBidPriceDto dto:entity.getBidOrderBidPriceList()){
                    dto.setBidId(entity.getBidId());
                    dto.setIsWin(entity.getIsWin());
                    if (null != dto.getWinPrice()){
                        dto.setWinPrice(dto.getWinPrice().divide(BigDecimal.valueOf(100)));
                    }
                    if (null != dto.getBidPrice()){
                        dto.setBidPrice(dto.getBidPrice().divide(BigDecimal.valueOf(100)));
                    }
                    if(!StringUtil.isNotEmpty(dto.getWinPrinAmt())){
                        dto.setWinPrinAmt(dto.getTheWinPrinAmt());
                    }
                    if("01".equals(entity.getWinType())) {
                        String[] bidStrArray = dto.getBidPriceStr().split(",");
                        for (String bidStr : bidStrArray) {
                            if(!StringUtil.isNotEmpty(bidStr))
                                continue;
                            dto.setBidPrice(new BigDecimal(bidStr).divide(BigDecimal.valueOf(100)));
                            bidOrderBidPriceMapper.updateByPrimaryKeySelective(dto);
                        }
                    }
                    else{
                        bidOrderBidPriceMapper.updateByPrimaryKeySelective(dto);
                    }
                }
            }else {
                //如果未中标
                BidOrderBidPrice record = new BidOrderBidPrice();
                record.setBidId(entity.getBidId());
                record.setIsWin(entity.getIsWin());
                bidOrderBidPriceMapper.updateWinByBidId(record);
                List<BidOrderBidPrice> bidOrderBidPrices = bidOrderBidPriceMapper.selectByBidId(record);
                for (BidOrderBidPrice bidOrderBidPrice : bidOrderBidPrices) {
                    //如果是未中标修改委托状态为已完成
                    if ("N".equals(bidOrderBidPrice.getIsWin())){
                        EtrEntrust etrEntrust = new EtrEntrust();
                        etrEntrust.setEntrustId(bidOrderBidPrice.getEntrustId());
                        etrEntrust.setEntrustStatus(DictCodeList.委托状态_已完成.getValue());
                        etrEntrustMapper.updateByPrimaryKeySelective(etrEntrust);
                    }
                }
            }
            //add by xqh 调用债券一级投标中标结果维护（实时头寸）共通
            //dto转换
            BaseTradeProductDealDto finProductTradeDto=new BaseTradeProductDealDto();
            if(entity.getBidOrderBidPriceList()!=null && entity.getBidOrderBidPriceList().size()>0){
                entity.getBidOrderBidPriceList().forEach(item->{
                    //投标券面总额
                    baseEtrCashPositionCommService.executeBidResultsMaintenanceExecute(item);
                });
            }
//            finProductTradeDto.getTrdProductDeal().setTradeDate(entity.getBidDate());
//            finProductTradeDto.getTrdProductDeal().setCounterId(entity.getCounterId());
//          object.bidId=entity.bidId;  投标编号
//        	object.bidDate=entity.bidDate; 投标日期
//        	object.finprodAbbr=entity.finprodAbbr; 
//        	object.finprodMarketId=entity.finprodMarketId; 
//        	object.tradeMarket=entity.tradeMarket;
//          object.counterId=entity.counterId;
    	}

    }

    public String findProcessStatusByWorkflowId(String workflowId){
        return primaryBiddingManage003Mapper.findProcessStatusByWorkflowId(workflowId);
    }

    public Boolean findTradeIdByBidId(String bidId){
        return primaryBiddingManage003Mapper.findTradeIdByBidId(bidId) == 0;
    }

    public EtrEntrust selectInfoByEntrustId(Map<String, Object> map){
        return etrEntrustMapper.selectByPrimaryKey(map.get("entrustId").toString());
    }
}
