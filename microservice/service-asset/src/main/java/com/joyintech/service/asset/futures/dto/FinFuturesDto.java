/*
 * 文件名：FinFuturesDto.java
 * 版权：Copyright JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：广州银行poc
 * 修改人：余飞
 * 修改时间：2020年11月09日
 * 修改内容：新建
 * 系统名称：广州银行poc
 */

package com.joyintech.service.asset.futures.dto;

import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 期货类标的信息 〈功能详细描述〉
 *
 * @author 余飞
 * @version 2020年11月09日
 * @see FinFuturesDto
 * @since 1.0
 */

public class FinFuturesDto extends FinProduct {

    /**
     * 资产一级分类,期货类型
     */
    private String type6;

    /**
     * 期货合约代码前缀
     */
    private String futuresContractPre;

    /**
     * 期货合约标的
     */
    private String futuresContractId;

    /**
     * 合约代码不加前缀
     */
    private String contractCode;

    /**
     * 最后交易日
     */
    private Date lastTradeDate;

    /**
     * 最后交割日
     */
    private Date lastSettleDate;

    /**
     * 交割方式
     */
    private String deliveryMethod;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 要素id
     */
    private String elementId;

    /**
     * 目的和意义以及可能会用到的地方
     */
    private String pageModel;

    /**
     * 期货类型
     */
    private String futuresType;

    /**
     * 选择数据
     */
    private List<FinFuturesDto> selectedItem;

    /**
     *
     */
    private List<SysUploadFileInfo> fileArray;
    
    /**
     * 合约乘数
     */
    private BigDecimal contractMultiplier;
    

    public BigDecimal getContractMultiplier() {
		return contractMultiplier;
	}

	public void setContractMultiplier(BigDecimal contractMultiplier) {
		this.contractMultiplier = contractMultiplier;
	}


    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public List<FinFuturesDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<FinFuturesDto> selectedItem) {
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

    public String getType6() {
        return type6;
    }

    public void setType6(String type6) {
        this.type6 = type6;
    }

    public String getFuturesContractPre() {
        return futuresContractPre;
    }

    public void setFuturesContractPre(String futuresContractPre) {
        this.futuresContractPre = futuresContractPre;
    }

    public String getFuturesContractId() {
        return futuresContractId;
    }

    public void setFuturesContractId(String futuresContractId) {
        this.futuresContractId = futuresContractId;
    }

    public Date getLastTradeDate() {
        return lastTradeDate;
    }

    public void setLastTradeDate(Date lastTradeDate) {
        this.lastTradeDate = lastTradeDate;
    }

    public Date getLastSettleDate() {
        return lastSettleDate;
    }

    public void setLastSettleDate(Date lastSettleDate) {
        this.lastSettleDate = lastSettleDate;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getFuturesType() {
        return futuresType;
    }

    public void setFuturesType(String futuresType) {
        this.futuresType = futuresType;
    }

    public List<SysUploadFileInfo> getFileArray() {
        return fileArray;
    }

    public void setFileArray(List<SysUploadFileInfo> fileArray) {
        this.fileArray = fileArray;
    }
}
