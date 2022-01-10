package com.joyintech.tams.exchange.common.bean.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author swwan
 * @date 2022/1/8 - 15:10
 */
@ApiModel(value = "场内交易系统上下文", description = "场内交易系统上下文")
public class ExchangeContextResponse {

    @ApiModelProperty(value = "用户编号")
    private String userId;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "平台日")
    private Date platDate;

    @ApiModelProperty(value = "是否是上交所交易日 0：是；1：否")
    private String shTradingDay;

    @ApiModelProperty(value = "是否是深交所交易日 0：是；1：否")
    private String szTradingDay;

    @ApiModelProperty(value = "是否启动公平交易开关 01：开；02：关")
    private String fairMode;

    @ApiModelProperty(value = "未接收指令的公平处理模式 01：是；02：否")
    private String fairModeWithUnacceptInstruction;

    @ApiModelProperty(value = "委托价格不优于指令价格时的公平处理模式 01：禁止；02：剔除并继续")
    private String fairModeEntrustPriceBestThanInstruction;

    @ApiModelProperty(value = "闭市后是否禁止下场内交易指令 Y：禁止 N：不禁止")
    private String tradeTimeSwitch;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getPlatDate() {
        return platDate;
    }

    public void setPlatDate(Date platDate) {
        this.platDate = platDate;
    }

    public String getShTradingDay() {
        return shTradingDay;
    }

    public void setShTradingDay(String shTradingDay) {
        this.shTradingDay = shTradingDay;
    }

    public String getSzTradingDay() {
        return szTradingDay;
    }

    public void setSzTradingDay(String szTradingDay) {
        this.szTradingDay = szTradingDay;
    }

    public String getFairMode() {
        return fairMode;
    }

    public void setFairMode(String fairMode) {
        this.fairMode = fairMode;
    }

    public String getFairModeWithUnacceptInstruction() {
        return fairModeWithUnacceptInstruction;
    }

    public void setFairModeWithUnacceptInstruction(String fairModeWithUnacceptInstruction) {
        this.fairModeWithUnacceptInstruction = fairModeWithUnacceptInstruction;
    }

    public String getFairModeEntrustPriceBestThanInstruction() {
        return fairModeEntrustPriceBestThanInstruction;
    }

    public void setFairModeEntrustPriceBestThanInstruction(String fairModeEntrustPriceBestThanInstruction) {
        this.fairModeEntrustPriceBestThanInstruction = fairModeEntrustPriceBestThanInstruction;
    }

    public String getTradeTimeSwitch() {
        return tradeTimeSwitch;
    }

    public void setTradeTimeSwitch(String tradeTimeSwitch) {
        this.tradeTimeSwitch = tradeTimeSwitch;
    }
}
