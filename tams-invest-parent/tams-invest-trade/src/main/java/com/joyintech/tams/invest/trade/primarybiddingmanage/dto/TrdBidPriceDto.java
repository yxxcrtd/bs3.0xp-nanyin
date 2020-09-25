package com.joyintech.tams.invest.trade.primarybiddingmanage.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.BidOrder;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.invest.trade.primarybiddingmanage.validator.PrimaryBidPriceValidator;
import com.joyintech.tams.invest.trade.primarybiddingmanage.validator.PrimaryBiddingManage003Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author: 裴家俊
 * @create: 2019-05-10 09:20
 **/
@PageId("PrimaryBiddingManage003")
@ValidLogic(targetClass = PrimaryBidPriceValidator.class, groups = {ValidateGroupEdit.class})
public class TrdBidPriceDto extends BidOrder implements ProcessEntity {
	
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
     * 人员信息
     */
    @Valid
    private TrdInvestPersonInfo002Dto trdInvestPersonInfo;

    /**
     * 分配明细
     */
    private List<BidOrderBidPriceDto> bidOrderBidPriceList;

    /**
     * 投标券面总额汇总
     */
    private BigDecimal allBidPrinAmt;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 选择数据
     */
    private List<TrdBidPriceDto> selectedItem;

    /**
     * 要素id
     */
    private String elementId;

    /**
     * 目的和意义以及可能会用到的地方
     */
    private String pageModel;
    
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getBidId() {
        return StringUtil.isNotEmpty(super.getBidId())
                ?super.getBidId():StringUtil.EMPTY;
    }

    @Override
    @NotEmpty(fieldName = "是否中标", groups = ValidateGroupEdit.class)
    public String getIsWin() {
        return super.getIsWin();
    }
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

	public TrdInvestPersonInfo002Dto getTrdInvestPersonInfo() {
		return trdInvestPersonInfo;
	}

	public void setTrdInvestPersonInfo(TrdInvestPersonInfo002Dto trdInvestPersonInfo) {
		this.trdInvestPersonInfo = trdInvestPersonInfo;
	}
    public String getCounterId() {
        return counterId;
    }

    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }

    public BigDecimal getAllBidPrinAmt() {
        return allBidPrinAmt;
    }

    public void setAllBidPrinAmt(BigDecimal allBidPrinAmt) {
        this.allBidPrinAmt = allBidPrinAmt;
    }

    public List<BidOrderBidPriceDto> getBidOrderBidPriceList() {
		return bidOrderBidPriceList;
	}

	public void setBidOrderBidPriceList(List<BidOrderBidPriceDto> bidOrderBidPriceList) {
		this.bidOrderBidPriceList = bidOrderBidPriceList;
	}

	public String getFinprodId() {
		return finprodId;
	}


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

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public List<TrdBidPriceDto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<TrdBidPriceDto> selectedItem) {
		this.selectedItem = selectedItem;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}

}
