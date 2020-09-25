package com.joyintech.service.trade.primarybiddingmanage.dto;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.tams.common.base.entity.BidOrder;

import java.util.List;

/**
 * @author: 裴家俊
 * @create: 2019-05-10 09:20
 **/
public class TrdBiddingManage003Dto extends BidOrder {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String finprodId;
	
	private String finprodAbbr;
	
	private String finprodMarketId;
	
	private String entrustId;
	
	private String tradeMarket;

	private String counterId;

    /**
     * 附件上传
     */
    private List<UploadFile> uploadFileList;

    /**
     * 分配明细
     */
    private List<BidOrderBidPriceDto> bidOrderBidPriceList;
    
    
   
    /**
     * 获取 uploadFileList
     *
     * @return uploadFileList
     */
    public List<UploadFile> getUploadFileList() {
        return uploadFileList;
    }

    /**
     * 设置 uploadFileList
     *
     * @param uploadFileList uploadFileList
     */
    public void setUploadFileList(List<UploadFile> uploadFileList) {
        this.uploadFileList = uploadFileList;
    }

	public List<BidOrderBidPriceDto> getBidOrderBidPriceList() {
		return bidOrderBidPriceList;
	}

	public void setBidOrderBidPriceList(List<BidOrderBidPriceDto> bidOrderBidPriceList) {
		this.bidOrderBidPriceList = bidOrderBidPriceList;
	}

	@Override
	public String getFinprodId() {
		return finprodId;
	}

	@Override
	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public String getFinprodAbbr() {
		return finprodAbbr;
	}

	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}

	public String getFinprodMarketId() {
		return finprodMarketId;
	}

	public void setFinprodMarketId(String finprodMarketId) {
		this.finprodMarketId = finprodMarketId;
	}

	public String getEntrustId() {
		return entrustId;
	}

	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}

	public String getTradeMarket() {
		return tradeMarket;
	}

	public void setTradeMarket(String tradeMarket) {
		this.tradeMarket = tradeMarket;
	}

    public String getCounterId() {
        return counterId;
    }

    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }
}
