/*
 * 文件名：EtrBrowDepoInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etrbrowdepoinfo.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年9月13日
 * @see EtrBrowDepoInfoDto
 * @since 1.0
 */
public class EtrBrowDepoInfoDto extends EtrEntrust {
    
    /**
     * 指定交易员
     */
    private String trader;
    
    /**
     * 委托关闭方式
     */
    private String CloseWay;
    
    /**
     * 选中的数据
     */
    private transient List<EtrBrowDepoInfoDto> selectedItem;
    
    /**
     * 操作类型
     */
    private String operateType;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -1164891656947671931L;
    
    /**
     * 期限天数，到期日-起息日
     */
    private Long termDays;
    
    /**
     * 组合名称
     */
    private String portfolioName;
    
    /**
     * 资产类型
     */
    private String finprodType2;
    
    //资产代码
    private String finprodMarketId;
    
    /**
     * 资产名称
     */
    private String finprodName;
    
    /**
     * 付息频率
     */
    private  String payFreq;
    
    /**
     * 已完成额度
     */
    private long finishAmt;
    
    /**交易完成度
     * 
     */
    private long transactioncom;
    
    /**
     * 起息日
     */
    private Date dateStart;
    
    /**
     * 到期日
     */
    private Date dateEnd;
    
    /**
     * 投资人员信息
     */
    private BussEtrInvestPersonInfoDto bussEtrInvestPersonInfoDto;
    
    /**
     * 
     */
    private List<SysUploadFileInfo> fileArray;
    
    /**新增资产二、三、四级分类	王贵奇	2019/8/7 开始*/   
    
    /**
     * 资产二级类型
     */
    private String assetType1;    
    
    /**
     * 资产三级类型
     */
    private String assetType10;   
    
    /**
     * 资产四级类型
     */
    private String assetType11;

    /**
     * 是否终止协议
     */
    private String isAgreementEnd;

    public String getAssetType1() {
		return assetType1;
	}

	public void setAssetType1(String assetType1) {
		this.assetType1 = assetType1;
	}

	public String getAssetType10() {
		return assetType10;
	}

	public void setAssetType10(String assetType10) {
		this.assetType10 = assetType10;
	}

	public String getAssetType11() {
		return assetType11;
	}

	public void setAssetType11(String assetType11) {
		this.assetType11 = assetType11;
	}

	/**新增资产二、三、四级分类	王贵奇	2019/8/7 结束*/


	/**
     * 获取 portfolioName
     * @return portfolioName.
     */
    public String getPortfolioName() {
        return portfolioName;
    }

    
    /**
     * 设置 portfolioName
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    
    /**
     * 获取 finprodType2
     * @return finprodType2.
     */
    public String getFinprodType2() {
        return finprodType2;
    }

    
    /**
     * 设置 finprodType2
     * @param finprodType2 finprodType2
     */
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }

    
    /**
     * 获取 finprodId
     * @return finprodId.
     */
    public String getFinprodId() {
        return super.getFinprodId();
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
     * 获取 payFreq
     * @return payFreq.
     */
    public String getPayFreq() {
        return payFreq;
    }

    
    /**
     * 设置 payFreq
     * @param payFreq payFreq
     */
    public void setPayFreq(String payFreq) {
        this.payFreq = payFreq;
    }

    
    /**
     * 获取 finishAmt
     * @return finishAmt.
     */
    public long getFinishAmt() {
        return finishAmt;
    }

    
    /**
     * 设置 finishAmt
     * @param finishAmt finishAmt
     */
    public void setFinishAmt(long finishAmt) {
        this.finishAmt = finishAmt;
    }

    
    /**
     * 获取 transactioncom
     * @return transactioncom.
     */
    public long getTransactioncom() {
        return transactioncom;
    }

    
    /**
     * 设置 transactioncom
     * @param transactioncom transactioncom
     */
    public void setTransactioncom(long transactioncom) {
        this.transactioncom = transactioncom;
    }

    
    /**
     * 获取 serialversionuid
     * @return serialversionuid.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }


    
    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }


    
    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }


    
    /**
     * 获取 trader
     * @return trader.
     */
    public String getTrader() {
        return trader;
    }


    
    /**
     * 设置 trader
     * @param trader trader
     */
    public void setTrader(String trader) {
        this.trader = trader;
    }


    
    /**
     * 获取 closeWay
     * @return closeWay.
     */
    public String getCloseWay() {
        return CloseWay;
    }


    
    /**
     * 设置 closeWay
     * @param closeWay closeWay
     */
    public void setCloseWay(String closeWay) {
        CloseWay = closeWay;
    }


    
    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<EtrBrowDepoInfoDto> getSelectedItem() {
        return selectedItem;
    }


    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<EtrBrowDepoInfoDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    
    
    
    /**
     * 获取 dateStart
     * @return dateStart.
     */
    public Date getDateStart() {
        return dateStart;
    }


    
    /**
     * 设置 dateStart
     * @param dateStart dateStart
     */
    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }


    
    /**
     * 获取 dateEnd
     * @return dateEnd.
     */
    public Date getDateEnd() {
        return dateEnd;
    }


    
    /**
     * 设置 dateEnd
     * @param dateEnd dateEnd
     */
    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }


    /**
     * 获取 termDays
     * @return termDays.
     */
    public Long getTermDays() {
        return termDays;
    }


    
    /**
     * 设置 termDays
     * @param termDays termDays
     */
    public void setTermDays(Long termDays) {
        this.termDays = termDays;
    }


    
    /**
     * 获取 bussEtrInvestPersonInfoDto
     * @return bussEtrInvestPersonInfoDto.
     */
    public BussEtrInvestPersonInfoDto getBussEtrInvestPersonInfoDto() {
        return bussEtrInvestPersonInfoDto;
    }


    
    /**
     * 设置 bussEtrInvestPersonInfoDto
     * @param bussEtrInvestPersonInfoDto bussEtrInvestPersonInfoDto
     */
    public void setBussEtrInvestPersonInfoDto(BussEtrInvestPersonInfoDto bussEtrInvestPersonInfoDto) {
        this.bussEtrInvestPersonInfoDto = bussEtrInvestPersonInfoDto;
    }


    
    /**
     * 获取 fileArray
     * @return fileArray.
     */
    public List<SysUploadFileInfo> getFileArray() {
        return fileArray;
    }


    
    /**
     * 设置 fileArray
     * @param fileArray fileArray
     */
    public void setFileArray(List<SysUploadFileInfo> fileArray) {
        this.fileArray = fileArray;
    }


    
    /**
     * 获取 finprodMarketId
     * @return finprodMarketId.
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }


    
    /**
     * 设置 finprodMarketId
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getIsAgreementEnd() {
        return isAgreementEnd;
    }

    public void setIsAgreementEnd(String isAgreementEnd) {
        this.isAgreementEnd = isAgreementEnd;
    }
}
