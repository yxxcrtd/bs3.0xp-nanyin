/*
 * 文件名：FinFundTargetDto.java
 * 版权：Copyright JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：广州银行poc
 * 修改人：余飞
 * 修改时间：2020年11月09日
 * 修改内容：新建
 * 系统名称：广州银行poc
 */

package com.joyintech.tams.invest.asset.futures.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.asset.futures.valid.FinFuturesValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 期货标的信息 〈功能详细描述〉
 *
 * @author yufei
 * @version 1.0 2020年11月09日
 * @see FinFuturesDto
 * @since 1.0
 */

@PageId("FinFutures002")
@ValidLogic(targetClass = FinFuturesValidator.class, groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class FinFuturesDto extends FinProduct implements ProcessEntity {

    /**
     * 资产一级分类,期货类型
     */
    private String type6;

    /**
     * 期货合约代码前缀
     */
    private String futuresContractPre;

    /**
     * 期货合约代码后缀
     */
    private String chlFinprodId;

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

	public List<SysUploadFileInfo> getFileArray() {
        return fileArray;
    }

    public void setFileArray(List<SysUploadFileInfo> fileArray) {
        this.fileArray = fileArray;
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

    @Override
    @NotEmpty(fieldName = "FinFutures001.finprodMarketId", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "FinFutures001.finprodMarketId", max = 32, groups = {
        ValidateGroupEdit.class})
    @PrimaryKey(value = PrimaryKey.Field.PK_1)
    public String getFinprodMarketId() {
//        String secId = super.getFinprodMarketId();
//        if (StringUtil.isNotEmpty(secId) && secId.contains(".")){
//            return secId.substring(0,secId.indexOf("."));
//        }
//        return secId;
        return super.getFinprodMarketId();
    }

    @NotEmpty(fieldName = "FinFutures001.tradeMarket", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "FinFutures001.tradeMarket", max = 32, groups = {ValidateGroupEdit.class})
    public String getTradeMarket() {
        return super.getTradeMarket();
    }

    @NotEmpty(fieldName = "FinFutures001.ccy", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "FinFutures001.ccy", max = 32, groups = {ValidateGroupEdit.class})
    public String getCcy() {
        return super.getCcy();
    }

    public String getType6() {
        return type6;
    }

    public void setType6(String type6) {
        this.type6 = type6;
    }

    @NotEmpty(fieldName = "FinFutures001.futuresContractPre", groups = {ValidateGroupEdit.class})
    public String getFuturesContractPre() {
        return futuresContractPre;
    }

    public void setFuturesContractPre(String futuresContractPre) {
        this.futuresContractPre = futuresContractPre;
    }

    @NotEmpty(fieldName = "FinFutures001.futuresContractId", groups = {ValidateGroupEdit.class})
    public String getFuturesContractId() {
        return futuresContractId;
    }

    public void setFuturesContractId(String futuresContractId) {
        this.futuresContractId = futuresContractId;
    }

    @NotEmpty(fieldName = "FinFutures001.lastTradeDate", groups = {ValidateGroupEdit.class})
    public Date getLastTradeDate() {
        return lastTradeDate;
    }

    public void setLastTradeDate(Date lastTradeDate) {
        this.lastTradeDate = lastTradeDate;
    }

    @NotEmpty(fieldName = "FinFutures001.lastSettleDate", groups = {ValidateGroupEdit.class})
    public Date getLastSettleDate() {
        return lastSettleDate;
    }

    public void setLastSettleDate(Date lastSettleDate) {
        this.lastSettleDate = lastSettleDate;
    }

    @NotEmpty(fieldName = "FinFutures001.deliveryMethod", groups = {ValidateGroupEdit.class})
    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getFuturesContractIdStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.合约标的, getFuturesContractId());
    }

    public String getFuturesType() {
        return futuresType;
    }

    public void setFuturesType(String futuresType) {
        this.futuresType = futuresType;
    }

    @NotEmpty(fieldName = "FinFutures001.finprodMarketId", groups = {ValidateGroupEdit.class})
    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getFuturesTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.期货类型, getFuturesType());
    }

    public String getTradeMarketStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.交易场所, getTradeMarket());
    }

    public String getDeliveryMethodStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.交割方式, getDeliveryMethod());
    }

    @Override
    public String getChlFinprodId() {
        return chlFinprodId;
    }

    @Override
    public void setChlFinprodId(String chlFinprodId) {
        this.chlFinprodId = chlFinprodId;
    }
}
