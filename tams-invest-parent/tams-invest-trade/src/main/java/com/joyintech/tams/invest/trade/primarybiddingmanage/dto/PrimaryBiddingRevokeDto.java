package com.joyintech.tams.invest.trade.primarybiddingmanage.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.trade.primarybiddingmanage.validator.PrimaryBiddingManage001Validator;
import com.joyintech.tams.invest.trade.primarybiddingmanage.validator.PrimaryBiddingManage002Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@PageId("PrimaryBiddingManage002")
@ValidLogic(targetClass = PrimaryBiddingManage001Validator.class, groups = {ValidateGroupEdit.class})
public class PrimaryBiddingRevokeDto extends EtrEntrust implements ProcessEntity{
	
	/**
     * 投标编号
     */
    private String bidId;
    
    /**
     * 有效状态
     */
    private String validStatus;
	
    /**
     * 有效状态
     */
    private String validStatusText;
    
    /**
     * 确认状态
     */
    private String tenderconfirmstatus;
	
    /**
     * 确认状态
     */
    private String tenderconfirmstatusText;
    
    /**
     * 金融产品全称
     */
    private String finprodName;
    /**
     * 市场代码
     */
    private String finprodMarketId;
    /**
     * 金融产品简称
     */
    private String finprodAbbr;

    /**
     * 金融产品代码
     */
    private String finprodId;
    /**
     * 交易对手
     */
    private String counterId;
    /**
     * 投标日
     */
    private Date biddate;
    
    /**
     * 投标券面总额汇总
     */
    private BigDecimal allBidPrinAmt;
    
    /**
     * 投标标位
     */
    private BigDecimal bidprice;
    
    /**
     * 投标标位
     */
    private String bidpriceStr;
    
    /**
     * 组合名称
     */
    private String portfolioName;
    
    /**
     * 交易对手名称
     */
    private String counterIdText;
    
    /**
     * 投标组合列表
     */
    private List<PrimaryBiddingPortfolioDto> primaryBiddingPortfolioDtolist;
    
    /**
     * 汇总投标列表
     */
    private List<PrimaryBiddingPortfolioDto> primaryBiddingAllDtolist;
    
    /**
     * 人员信息
     */
    @Valid
    private TrdInvestPersonInfo002Dto trdInvestPersonInfo002Dto;
    
    /**
     * 画面模式
     */
    private String pageModel;
    
    /**
     * 操作类型
     */
    private String operateType;
    
    /**
     * 获取 bidId
     *
     * @return bidId
     */
    public String getBidId() {
		return bidId;
	}
    /**
     * 设置 bidId
     *
     * @return bidId bidId
     */
	public void setBidId(String bidId) {
		this.bidId = bidId;
	}

	/**
     * 获取 validStatus
     *
     * @return validStatus
     */
    public String getValidStatus() {
        return validStatus;
    }

    /**
     * 设置 validStatus
     *
     * @param validStatus validStatus
     */
    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus;
    }
    
    /**
     * 获取 ccyText
     *
     * @return validStatusText
     */
    public String getValidStatusText() {
        validStatusText = SysDataUtil.getNameFromDataDict(BussinessDictCode.交易流程状态, validStatus);
        return validStatusText;
    }
    
    /**
     * 设置 validStatusText
     *
     * @param validStatusText validStatusText
     */
    public void setValidStatusText(String validStatusText) {
        this.validStatusText = validStatusText;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    /**
     * 获取 tenderconfirmstatus
     *
     * @return tenderconfirmstatus
     */
	public String getTenderconfirmstatus() {
		return tenderconfirmstatus;
	}

	/**
     * 设置 tenderconfirmstatus
     *
     * @param tenderconfirmstatus tenderconfirmstatus
     */
	public void setTenderconfirmstatus(String tenderconfirmstatus) {
		this.tenderconfirmstatus = tenderconfirmstatus;
	}

	/**
     * 获取 tenderconfirmstatusText
     *
     * @return tenderconfirmstatusText
     */
	public String getTenderconfirmstatusText() {
		tenderconfirmstatusText = SysDataUtil.getNameFromDataDict(BussinessDictCode.确认状态, tenderconfirmstatus);
        return tenderconfirmstatusText;
	}
	
	/**
     * 设置 tenderconfirmstatusText
     *
     * @param tenderconfirmstatusText tenderconfirmstatusText
     */
	public void setTenderconfirmstatusText(String tenderconfirmstatusText) {
		this.tenderconfirmstatusText = tenderconfirmstatusText;
	}
    
	/**
     * 获取 finprodName
     * @return finprodName.
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 finprodName
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    /**
     * 获取 finprodAbbr
     * @return finprodAbbr
     */
    public String getFinprodAbbr() {
		return finprodAbbr;
	}
    
    /**
     * 设置 finprodAbbr
     * @param finprodAbbr finprodAbbr
     */
	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}
	/**
     * 获取 biddate
     * @param biddate
     */
	public Date getBiddate() {
		return biddate;
	}
	
	/**
     * 设置 biddate
     * @param biddate biddate
     */
	public void setBiddate(Date biddate) {
		this.biddate = biddate;
	}
	
	/**
     * 获取 allBidPrinAmt
     * @param allBidPrinAmt
     */
	public BigDecimal getAllBidPrinAmt() {
		return allBidPrinAmt;
	}
	
	/**
     * 设置 allBidPrinAmt
     * @param allBidPrinAmt allBidPrinAmt
     */
	public void setAllBidPrinAmt(BigDecimal allBidPrinAmt) {
		this.allBidPrinAmt = allBidPrinAmt;
	}
	public BigDecimal getBidprice() {
		return bidprice;
	}
	public void setBidprice(BigDecimal bidprice) {
		this.bidprice = bidprice;
	}
	public String getBidpriceStr() {
		return bidpriceStr;
	}
	public void setBidpriceStr(String bidpriceStr) {
		this.bidpriceStr = bidpriceStr;
	}
	public String getPortfolioName() {
		return portfolioName;
	}
	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}
	
	public List<PrimaryBiddingPortfolioDto> getPrimaryBiddingPortfolioDtolist() {
		return primaryBiddingPortfolioDtolist;
	}
	public void setPrimaryBiddingPortfolioDtolist(List<PrimaryBiddingPortfolioDto> primaryBiddingPortfolioDtolist) {
		this.primaryBiddingPortfolioDtolist = primaryBiddingPortfolioDtolist;
	}
	public List<PrimaryBiddingPortfolioDto> getPrimaryBiddingAllDtolist() {
		return primaryBiddingAllDtolist;
	}
	public void setPrimaryBiddingAllDtolist(List<PrimaryBiddingPortfolioDto> primaryBiddingAllDtolist) {
		this.primaryBiddingAllDtolist = primaryBiddingAllDtolist;
	}

    /**
     * 获取 trdInvestPersonInfo002Dto
     *
     * @return trdInvestPersonInfo002Dto
     */
	public TrdInvestPersonInfo002Dto getTrdInvestPersonInfo002Dto() {
		return trdInvestPersonInfo002Dto;
	}
	
	/**
	 * 设置 trdInvestPersonInfo002Dto
	 *
	 * @param trdInvestPersonInfo002Dto trdInvestPersonInfo002Dto
	 */
	public void setTrdInvestPersonInfo002Dto(TrdInvestPersonInfo002Dto trdInvestPersonInfo002Dto) {
		this.trdInvestPersonInfo002Dto = trdInvestPersonInfo002Dto;
	}
	public String getPageModel() {
		return pageModel;
	}
	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}
	/**
     * 获取 operateType
     *
     * @return operateType
     */
    @PrimaryKey(PrimaryKey.Field.PK_3)
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     *
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getCounterIdText() {
		return counterIdText;
	}
	public void setCounterIdText(String counterIdText) {
		this.counterIdText = counterIdText;
	}

    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getFinprodId() {
        return finprodId;
    }

    @Override
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    @Override
    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getCounterId() {
        return counterId;
    }

    @Override
    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }
}
