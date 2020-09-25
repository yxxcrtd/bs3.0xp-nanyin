package com.joyintech.service.entrust.etrseclend.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.invest.common.dto.common.BussRepoSecDto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 债券借贷投资委托微服务端dto
 *
 * @author yjm
 * @date 2019/6/5 0005 14:51
 */
public class EtrSecLendDto extends EtrEntrust {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 2694221941024278255L;

    /**
     * 文件上传dto
     */
    private List<SysUploadFileInfo> fileArray;

    /**
     * 标的债券代码
     */
    private String targetFinprodId;
    /**
     * 标的债券名称
     */
    private String targetFinprodName;
    /**
     * 投资人员信息DTO
     */
    private BussEtrInvestPersonInfoDto etrInvestPersonInfo;

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
    private transient List<EtrSecLendDto> selectedItem;
    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 组合名称
     */
    private String portfolioName;


    /**
     * 借贷方向
     */
    private String busiPs;
    /**
     * 清算速度
     */
    private String settleSpeed;
    
    /**
     * 券面总额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal totalAmt;
    /**
     * 完成额度
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal finishAmt;

    /**
     * 交易完成度
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal transactioncom;

    /**
     * 按钮id
     */
    private String elementId;
    /**
     * 投资处室
     */
    private String room;

    /**
     * 证券托管户
     */
    private String secAcctId;
    /**
     * 期限类型
     */
    private String termType;

    /**
     * 交易日历
     */
    private String calendarId;
    /**
     * 借贷费率
     */
    private BigDecimal busiRate;
    /**
     * 标的债券可以持仓
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal objectAvailablePosition;

    /**
     * 添加质押券信息
     */
    private BussRepoSecDto bussRepoSec;

    /**
     * 获取 fileArray
     *
     * @return fileArray.
     */
    public List<SysUploadFileInfo> getFileArray() {
        return fileArray;
    }


    /**
     * 设置 fileArray
     *
     * @param fileArray fileArray
     */
    public void setFileArray(List<SysUploadFileInfo> fileArray) {
        this.fileArray = fileArray;
    }

    /**
     * 获取 trader
     *
     * @return trader.
     */
    public String getTrader() {
        return trader;
    }


    /**
     * 获取 etrInvestPersonInfo
     *
     * @return etrInvestPersonInfo.
     */
    public BussEtrInvestPersonInfoDto getEtrInvestPersonInfo() {
        return etrInvestPersonInfo;
    }


    /**
     * 设置 etrInvestPersonInfo
     *
     * @param etrInvestPersonInfo etrInvestPersonInfo
     */
    public void setEtrInvestPersonInfo(BussEtrInvestPersonInfoDto etrInvestPersonInfo) {
        this.etrInvestPersonInfo = etrInvestPersonInfo;
    }


    /**
     * 设置 trader
     *
     * @param trader trader
     */
    public void setTrader(String trader) {
        this.trader = trader;
    }


    /**
     * 获取 selectedItem
     *
     * @return selectedItem.
     */
    public List<EtrSecLendDto> getSelectedItem() {
        return selectedItem;
    }


    /**
     * 设置 selectedItem
     *
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<EtrSecLendDto> selectedItem) {
        this.selectedItem = selectedItem;
    }


    /**
     * 获取 elementId
     *
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }


    /**
     * 设置 elementId
     *
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    /**
     * 获取 room
     *
     * @return room.
     */
    public String getRoom() {
        return room;
    }


    /**
     * 设置 room
     *
     * @param room room
     */
    public void setRoom(String room) {
        this.room = room;
    }


    /**
     * 获取 closeWay
     *
     * @return closeWay.
     */
    public String getCloseWay() {
        return CloseWay;
    }


    /**
     * 设置 closeWay
     *
     * @param closeWay closeWay
     */
    public void setCloseWay(String closeWay) {
        CloseWay = closeWay;
    }


    /**
     * 获取 operateType
     *
     * @return operateType.
     */
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


    /**
     * 获取 portfolioName
     *
     * @return portfolioName.
     */
    public String getPortfolioName() {
        return portfolioName;
    }


    /**
     * 设置 portfolioName
     *
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }
    /**
     * 获取 finishAmt
     *
     * @return finishAmt.
     */
    public BigDecimal getFinishAmt() {
        return finishAmt;
    }


    /**
     * 设置 finishAmt
     *
     * @param finishAmt finishAmt
     */
    public void setFinishAmt(BigDecimal finishAmt) {
        this.finishAmt = finishAmt;
    }


    /**
     * 获取 transactioncom
     *
     * @return transactioncom.
     */
    public BigDecimal getTransactioncom() {
        return transactioncom;
    }


    /**
     * 设置 transactioncom
     *
     * @param transactioncom transactioncom
     */
    public void setTransactioncom(BigDecimal transactioncom) {
        this.transactioncom = transactioncom;
    }


    
    /**
     * 获取 targetFinprodId
     * @return targetFinprodId.
     */
    public String getTargetFinprodId() {
        return targetFinprodId;
    }


    
    /**
     * 设置 targetFinprodId
     * @param targetFinprodId targetFinprodId
     */
    public void setTargetFinprodId(String targetFinprodId) {
        this.targetFinprodId = targetFinprodId;
    }


    
    /**
     * 获取 targetFinprodName
     * @return targetFinprodName.
     */
    public String getTargetFinprodName() {
        return targetFinprodName;
    }


    
    /**
     * 设置 targetFinprodName
     * @param targetFinprodName targetFinprodName
     */
    public void setTargetFinprodName(String targetFinprodName) {
        this.targetFinprodName = targetFinprodName;
    }


    
    /**
     * 获取 busiPs
     * @return busiPs.
     */
    public String getBusiPs() {
        return busiPs;
    }


    
    /**
     * 设置 busiPs
     * @param busiPs busiPs
     */
    public void setBusiPs(String busiPs) {
        this.busiPs = busiPs;
    }
    
    /**
     * 获取 totalAmt
     * @return totalAmt.
     */
    public BigDecimal getTotalAmt() {
        return totalAmt;
    }
    
    /**
     * 设置 totalAmt
     * @param totalAmt totalAmt
     */
    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }

    
    
    /**
     * 获取 busiRate
     * @return busiRate.
     */
    public BigDecimal getBusiRate() {
        return busiRate;
    }


    
    /**
     * 设置 busiRate
     * @param busiRate busiRate
     */
    public void setBusiRate(BigDecimal busiRate) {
        this.busiRate = busiRate;
    }


    /**
     * 获取 secAcctId
     * @return secAcctId.
     */
    public String getSecAcctId() {
        return secAcctId;
    }


    
    /**
     * 设置 secAcctId
     * @param secAcctId secAcctId
     */
    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }


    
    /**
     * 获取 termType
     * @return termType.
     */
    public String getTermType() {
        return termType;
    }


    
    /**
     * 设置 termType
     * @param termType termType
     */
    public void setTermType(String termType) {
        this.termType = termType;
    }

    
    /**
     * 获取 calendarId
     * @return calendarId.
     */
    public String getCalendarId() {
        return calendarId;
    }


    
    /**
     * 设置 calendarId
     * @param calendarId calendarId
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }



    
    /**
     * 获取 objectAvailablePosition
     * @return objectAvailablePosition.
     */
    public BigDecimal getObjectAvailablePosition() {
        return objectAvailablePosition;
    }


    
    /**
     * 设置 objectAvailablePosition
     * @param objectAvailablePosition objectAvailablePosition
     */
    public void setObjectAvailablePosition(BigDecimal objectAvailablePosition) {
        this.objectAvailablePosition = objectAvailablePosition;
    }


    
    /**
     * 获取 settleSpeed
     * @return settleSpeed.
     */
    public String getSettleSpeed() {
        return settleSpeed;
    }


    
    /**
     * 设置 settleSpeed
     * @param settleSpeed settleSpeed
     */
    public void setSettleSpeed(String settleSpeed) {
        this.settleSpeed = settleSpeed;
    }

    public BussRepoSecDto getBussRepoSec() {
        return bussRepoSec;
    }

    public void setBussRepoSec(BussRepoSecDto bussRepoSec) {
        this.bussRepoSec = bussRepoSec;
    }
}



