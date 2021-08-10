package com.joyintech.tams.invest.basicdata.mstfuturespriceinfo.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.basicdata.mstfundbonusnotice.validator.MstFundBonusNoticeValidator;
import com.joyintech.tams.invest.basicdata.mstfuturespriceinfo.valid.MstFuturesPriceInfoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 * @Classname MstFuturesPriceInfoDto
 * @Description TODO
 * @author zhuhanlu 
 * @date 2020/11/26 17:42 
 * @version 1.0 
 */
@PageId("MstFuturesPriceInfo002")
@ValidLogic(targetClass = MstFuturesPriceInfoValidator.class, groups = {ValidateGroupEdit.class, ValidateGroupDelete.class})

public class MstFuturesPriceInfoDto extends BaseEntity implements ITableRow, ProcessEntity {

    /**
     * 页面元素
     */
    private String elementId;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 页面类型
     */
    private String pageModel;

    /**
     * 合约代码
     */
    private String futuresId;
    
    /**
     * 合约代码(包含市场代码后缀)
     */
    private String futuresIdWithMarket;

    /**
     * Wind代码
     */
    private String windCode;

    /**
     * 行情日期
     */
    private Date tradeDate;

    /**
     * 前结算价
     */
    private BigDecimal preSettlePrice;

    /**
     * 开盘价
     */
    private BigDecimal openPrice;

    /**
     * 收盘价
     */
    private BigDecimal closePrice;

    /**
     * 最高价
     */
    private BigDecimal highPrice;

    /**
     * 最低价
     */
    private BigDecimal lowPrice;

    /**
     * 结算价
     */
    private BigDecimal settlePrice;


    /**
     * 选中的数据
     */
    private transient List<MstFuturesPriceInfoDto> selectedItem;



    @PrimaryKey(value= PrimaryKey.Field.PK_1)
    @NotEmpty(fieldName = "MstFuturesPriceInfo002.futuresId",groups={ValidateGroupEdit.class})
    public String getFuturesId() {
        return futuresId;
    }

    public void setFuturesId(String futuresId) {
        this.futuresId = futuresId;
    }

    //@NotEmpty(fieldName = "MstFuturesPriceInfo002.windCode",groups={ValidateGroupEdit.class})
    public String getWindCode() {
        return windCode;
    }

    public void setWindCode(String windCode) {
        this.windCode = windCode;
    }

    @NotEmpty(fieldName = "MstFuturesPriceInfo002.tradeDate",groups={ValidateGroupEdit.class})
    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    @NotEmpty(fieldName = "MstFuturesPriceInfo002.preSettlePrice",groups={ValidateGroupEdit.class})
    public BigDecimal getPreSettlePrice() {
        return preSettlePrice;
    }

    public void setPreSettlePrice(BigDecimal preSettlePrice) {
        this.preSettlePrice = preSettlePrice;
    }

    @NotEmpty(fieldName = "MstFuturesPriceInfo002.openPrice",groups={ValidateGroupEdit.class})
    public BigDecimal getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(BigDecimal openPrice) {
        this.openPrice = openPrice;
    }

    @NotEmpty(fieldName = "MstFuturesPriceInfo002.closePrice",groups={ValidateGroupEdit.class})
    public BigDecimal getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(BigDecimal closePrice) {
        this.closePrice = closePrice;
    }

    @NotEmpty(fieldName = "MstFuturesPriceInfo002.highPrice",groups={ValidateGroupEdit.class})
    public BigDecimal getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(BigDecimal highPrice) {
        this.highPrice = highPrice;
    }

    @NotEmpty(fieldName = "MstFuturesPriceInfo002.lowPrice",groups={ValidateGroupEdit.class})
    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    @NotEmpty(fieldName = "MstFuturesPriceInfo002.settlePrice",groups={ValidateGroupEdit.class})
    public BigDecimal getSettlePrice() {
        return settlePrice;
    }

    public void setSettlePrice(BigDecimal settlePrice) {
        this.settlePrice = settlePrice;
    }

    public List<MstFuturesPriceInfoDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<MstFuturesPriceInfoDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    @PrimaryKey(value= PrimaryKey.Field.PK_3)
    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    @PrimaryKey(value= PrimaryKey.Field.PK_2)
    public String getTradeDateStr(){
        return DateUtil.format(this.getTradeDate(), "yyyy-MM-dd");
    }
    
    public String getPreSettlePriceStr() {
    	return BigDecToString(getPreSettlePrice());
    }
    
    public String getOpenPriceStr() {
    	return BigDecToString(this.getOpenPrice());
    }
    
    public String getClosePriceStr() {
    	return BigDecToString(this.getClosePrice());
    	
    }
    
    public String getHighPriceStr() {
    	return BigDecToString(this.getHighPrice());
    }
    
    public String getLowPriceStr() {
    	return BigDecToString(this.getLowPrice());
    }
    
    public String getSettlePriceStr() {
    	return BigDecToString(this.getSettlePrice());
    }
    
    private String BigDecToString(BigDecimal number) {
    	if(number != null) {
    		BigDecimal temp = number.setScale(2,BigDecimal.ROUND_HALF_UP);
    		DecimalFormat decimalFormat = new DecimalFormat("0.00#");
    		return decimalFormat.format(temp);
    	}
    	return "";
    }

    /**
     * 获取 futuresIdWithMarket
     * @return futuresIdWithMarket.
     */
    public String getFuturesIdWithMarket() {
        return futuresIdWithMarket;
    }

    /**
     * 设置 futuresIdWithMarket
     * @param futuresIdWithMarket futuresIdWithMarket
     */
    public void setFuturesIdWithMarket(String futuresIdWithMarket) {
        this.futuresIdWithMarket = futuresIdWithMarket;
    }
    
}
