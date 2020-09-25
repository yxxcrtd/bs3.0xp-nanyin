package com.joyintech.tams.invest.period.trdsecoverdueduration.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.dto.period.trdsecoverdueduration.TrdSecOverdueDurationDto;
import com.joyintech.tams.invest.period.trdsecoverdueduration.validator.TrdSecOverdueDuration002DtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * @ClassName TrdSecOverdueDurationDto
 * @Description: TODO
 * @Author wangpeng
 * @Date 2019/12/12 0004
 * @Version V1.0
 **/
@PageId("TrdSecOverdueDuration002")
@ValidLogic(targetClass = TrdSecOverdueDuration002DtoValidator.class, groups = {ValidateGroupDelete.class, ValidateGroupEdit.class})
public class TrdSecOverdueDuration002Dto extends TrdSecOverdueDurationDto implements ProcessEntity{


    /**
	 * 
	 */
	private static final long serialVersionUID = 1498615083936417247L;

	/**
     * 操作类型
     */
    private String operateType;

    /**
     * 有效状态
     */
    private String statusValid;

    /**
     * 交易编号
     */
    private String busiCode;

    /**
     * 现金流类型
     */
    private String cashType;

    /**
     * 平安业务类型
     */
    private String paBussType;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;

    /**
     * 是否是通道
     */
    private boolean channel;

    /**
     * 证券托管户名称/通道名称
     */
    private String secAcctName;

    /**
     * 市场代码
     */
    private String finprodMarketId;

    /**
     * 产品代码
     */
    private String finprodMarketName;

    /**
     * 计息开始日
     */
    private String vDate;

    /**
     * 计息结束日
     */
    private String mDate;


    /**
     * 全价总额 理论本息总额
     */
//    private BigDecimal fpriceAmt;

    /**
     * 到账金额
     */
//    private BigDecimal tradeAmt;

    /**
     * 实收利息总额
     */
//    private BigDecimal intAmt;

    /**
     * 还本金额
     */
//    private BigDecimal prinAmt;

    /**
     * 理论应计利息
     */
    private BigDecimal intAmtTheory;

    /**
     * 理论还本金额
     */
    private BigDecimal prinAmtTheory;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 交易备注
     */
    private String remark;

    /**
     * 撤销理由
     */
    private String revokeReason;

    private String issuePartyId;

    /**
     * 交易场所
     */
    private String tradeMarket;
    /**
     * 利息信息明细
     * 
     */
    private List<FinAssetIntInfo> finAssetIntInfoList;
    
    /**
     * 通道费
     */
    private BigDecimal channelFee;

    /**
     * 通道性质
     */
    private String payType;
    
    /**
     * 通道收取方式
     */
    private String collectType;
    
    /**
     * 资产简称
     */
    private String finprodAbbr;

	
	/**
     * 页面元素
     */
    private String elementId;

    /**
     * code Name
     */
    private String statusValidString;
    
    /**
     * 附件上传
     */
    private List<UploadFile> uploadFileList;


	/**
     * 平安业务类型
     */
//    private String paBussTypeString;

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getStatusValid() {
		return statusValid;
	}

	public void setStatusValid(String statusValid) {
		this.statusValid = statusValid;
	}

	@PrimaryKey(Field.PK_1)
	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}

	public String getCashType() {
		return cashType;
	}

	public void setCashType(String cashType) {
		this.cashType = cashType;
	}

	public String getPaBussType() {
		return paBussType;
	}

	public void setPaBussType(String paBussType) {
		this.paBussType = paBussType;
	}

	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
		return bussTrdTransferOrder;
	}

	public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
		this.bussTrdTransferOrder = bussTrdTransferOrder;
	}

	public boolean isChannel() {
		return channel;
	}

	public void setChannel(boolean channel) {
		this.channel = channel;
	}

	public String getSecAcctName() {
		return secAcctName;
	}

	public void setSecAcctName(String secAcctName) {
		this.secAcctName = secAcctName;
	}

	public String getFinprodMarketId() {
		return finprodMarketId;
	}

	public void setFinprodMarketId(String finprodMarketId) {
		this.finprodMarketId = finprodMarketId;
	}

	public String getFinprodMarketName() {
		return finprodMarketName;
	}

	public void setFinprodMarketName(String finprodMarketName) {
		this.finprodMarketName = finprodMarketName;
	}

	public String getvDate() {
		return vDate;
	}

	public void setvDate(String vDate) {
		this.vDate = vDate;
	}

	public String getmDate() {
		return mDate;
	}

	public void setmDate(String mDate) {
		this.mDate = mDate;
	}

	public BigDecimal getIntAmtTheory() {
		return intAmtTheory;
	}

	public void setIntAmtTheory(BigDecimal intAmtTheory) {
		this.intAmtTheory = intAmtTheory;
	}

	public BigDecimal getPrinAmtTheory() {
		return prinAmtTheory;
	}

	public void setPrinAmtTheory(BigDecimal prinAmtTheory) {
		this.prinAmtTheory = prinAmtTheory;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRevokeReason() {
		return revokeReason;
	}

	public void setRevokeReason(String revokeReason) {
		this.revokeReason = revokeReason;
	}

	public String getIssuePartyId() {
		return issuePartyId;
	}

	public void setIssuePartyId(String issuePartyId) {
		this.issuePartyId = issuePartyId;
	}

	public String getTradeMarket() {
		return tradeMarket;
	}

	public void setTradeMarket(String tradeMarket) {
		this.tradeMarket = tradeMarket;
	}

	public List<FinAssetIntInfo> getFinAssetIntInfoList() {
		return finAssetIntInfoList;
	}

	public void setFinAssetIntInfoList(List<FinAssetIntInfo> finAssetIntInfoList) {
		this.finAssetIntInfoList = finAssetIntInfoList;
	}

	public BigDecimal getChannelFee() {
		return channelFee;
	}

	public void setChannelFee(BigDecimal channelFee) {
		this.channelFee = channelFee;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getCollectType() {
		return collectType;
	}

	public void setCollectType(String collectType) {
		this.collectType = collectType;
	}

	public String getFinprodAbbr() {
		return finprodAbbr;
	}

	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getStatusValidString() {
		return statusValidString;
	}

	public void setStatusValidString(String statusValidString) {
		this.statusValidString = statusValidString;
	}

    /**
     * 获取 PaBussTypeString
     *
     * @return PaBussTypeString
     */
    public String getPaBussTypeString() {
        if (getCashType() != null && getCashType().contains(DictCodeList.现金流类型_中间还本.getValue())) {
            return DictCodeList.现金流类型_中间还本.getCodeName();
        }
        else if (getCashType() != null && (getCashType().contains(DictCodeList.现金流类型_到期还本.getValue())
                 || getCashType().contains(DictCodeList.现金流类型_到期收息.getValue()))) {
            return DictCodeList.现金流类型_到期还本.getCodeName();
        }
        else {
            return DictCodeList.现金流类型_中间收息.getCodeName();
        }
    }

	/**
     * 取得 交易本金
     * @return 交易本金
     */
	@Override
	@NotEmpty(fieldName = "TrdSecOverdueDuration002.prinAmt",groups={ValidateGroupEdit.class})
    public BigDecimal getPrinAmt() {
        return super.getPrinAmt();
    }
    
	
	/**
     * 取得 实收利息
     * @return 实收利息
     */
	@Override
	@NotEmpty(fieldName = "TrdSecOverdueDuration002.intAmt",groups={ValidateGroupEdit.class})
    public BigDecimal getIntAmt() {
        return super.getIntAmt();
    }
	
	/**
     * 取得 全价
     * @return 全价
     */
//	@Override
//	@NotEmpty(fieldName = "TrdSecOverdueDuration002.fpriceAmt",groups={ValidateGroupEdit.class})
//    public BigDecimal getFpriceAmt() {
//        return super.getFpriceAmt();
//    }
	
	
	/**
     * 取得 实收本息和
     * @return 实收本息和
     */
	@Override
	@NotEmpty(fieldName = "TrdSecOverdueDuration002.tradeAmt",groups={ValidateGroupEdit.class})
    public BigDecimal getTradeAmt() {
        return super.getTradeAmt();
    }
	
	/**
     * 取得 实收罚息
     * @return 实收罚息
     */
	@Override
	@NotEmpty(fieldName = "TrdSecOverdueDuration002.penaltyIntamt",groups={ValidateGroupEdit.class})
    public BigDecimal getPenaltyIntamt() {
        return super.getPenaltyIntamt();
    }
	
	@Override
	public String getPortfolioId() {
		return super.getPortfolioId();
	}
	
	@Override
	public String getSecManageAcctId() {
		return super.getSecManageAcctId();
	}
	
	@Override
	@NotEmpty(fieldName = "TrdSecOverdueDuration002.isClean",groups={ValidateGroupEdit.class})
	public String getIsClean() {
		return super.getIsClean();
	}
	
	@Override
	@NotEmpty(fieldName = "TrdSecOverdueDuration002.tradeDate",groups={ValidateGroupEdit.class})
	public Date getTradeDate() {
		return super.getTradeDate();
	}
	
	@Override
	@PrimaryKey(PrimaryKey.Field.PK_2)
	@NotEmpty(fieldName = "TrdSecOverdueDuration002.finprodId",groups={ValidateGroupEdit.class})
	public String getFinprodId() {
		return super.getFinprodId();
	}
	
	@Override
	@PrimaryKey(PrimaryKey.Field.PK_3)
	public String getSecAcctId() {
		return super.getSecAcctId();
	}
	
	@PrimaryKey(PrimaryKey.Field.PK_4)
	public String getBranchStr() {
		if(super.getBjBranch()!=null){
			return super.getBjBranch().toString();
		}
		else if(super.getLiBranch()!=null){
			return super.getLiBranch().toString();
		}
		return null;
	}

	public List<UploadFile> getUploadFileList() {
		return uploadFileList;
	}

	public void setUploadFileList(List<UploadFile> uploadFileList) {
		this.uploadFileList = uploadFileList;
	}
	
}
