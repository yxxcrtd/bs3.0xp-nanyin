package com.joyintech.tams.invest.common.dto.common;

import com.joyintech.tams.common.base.entity.TrdProductDeal;

import java.util.Date;
import java.util.List;

public class TrdProductDealDto extends TrdProductDeal {

    //add by zhangnian 20200304 start
    private String inputValue1;
    private String inputValue2;
    private String inputValue3;
    //add by zhangnian 20200304 end
    private Date entrustDate;
    /**
     * 市场代码
     */
    private String finprodMarketId;

    /**
     * 交易对手名称
     */
    private String counterIdName;

    /**
     * 选择数据
     */
    private List<TrdProductDealDto> selectedItem;

    public String getInputValue1() {
        return inputValue1;
    }

    public void setInputValue1(String inputValue1) {
        this.inputValue1 = inputValue1;
    }

    public String getInputValue2() {
        return inputValue2;
    }

    public void setInputValue2(String inputValue2) {
        this.inputValue2 = inputValue2;
    }

    public String getInputValue3() {
        return inputValue3;
    }

    public void setInputValue3(String inputValue3) {
        this.inputValue3 = inputValue3;
    }

    public List<TrdProductDealDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<TrdProductDealDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public Date getEntrustDate() {
        return entrustDate;
    }

    public void setEntrustDate(Date entrustDate) {
        this.entrustDate = entrustDate;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getCounterIdName() {
        return counterIdName;
    }

    public void setCounterIdName(String counterIdName) {
        this.counterIdName = counterIdName;
    }
}
