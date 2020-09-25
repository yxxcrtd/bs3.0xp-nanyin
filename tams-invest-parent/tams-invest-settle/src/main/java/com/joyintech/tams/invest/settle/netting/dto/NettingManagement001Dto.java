package com.joyintech.tams.invest.settle.netting.dto;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.settle.dvporder.DvpOrdersManagementDto;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * @author 姚轶文
 * @create 2019- 11-08 14:48
 */
public class NettingManagement001Dto extends DvpOrdersManagementDto implements ProcessEntity {

    private String totle;//TOTLE_TRADE_ID; //业务编号
    private String trade;//TRADE_DATE; // 确认日
    private String settle; //SETTLE_DATE; //支付日
    private String porid; //PORTFOLIO_ID; //组合代码
    private String pormame; //PORTFOLIO_NAME;//组合名称
    private String ordertype; //ORDER_TYPE; //业务类型
    private String pay; //PAY_TYPE; //划款类型
    private String amt; //SETTLE_AMT; //划款金额
    private String ccy; //币种
    private String remark ; //备注
    private String state;//处理状态
    private String account; // 募集兑付户
    private String trusteeship; //产品托管户
    private String ps; //交易方向
    private String dataType;

    private String orderTypeString; //前端显示的业务类型
    private String payString; //前端列表显示的划款类型
    private String raiseAcctNo;

    public String getPayString() {
        return SysDataUtil.getNameFromDataDict(DictCode.平安划款类型, getPay());
      // return payString;
    }

    public void setPayString(String payString) {
        this.payString = SysDataUtil.getNameFromDataDict(DictCode.平安划款类型, getPay());
    }

    public String getOrderTypeString() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易方向, getOrdertype());
        //return orderTypeString;
    }

    public void setOrderTypeString(String orderTypeString) {
        this.orderTypeString = SysDataUtil.getNameFromDataDict(DictCode.交易方向, getOrdertype());
    }

    /**
     * 划款类型
     *
     * @return
     */
    /*
    public String getPayTypeString() {
        // 一体化对应etliu start
//      return SysDataUtil.getNameFromDataDict(DictCode.划款类型, super.getPayType());
//    	return SysDataUtil.getNameFromDataDict(DictCode.平安划款类型, super.getPayType());
        return SysDataUtil.getNameFromDataDict(DictCode.交易方向, getPayType());
        // 一体化对应etliu end
    }
*/
    /**
     * 是否发送
     *
     * @return
     */
    public String getIsNeedSendString() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否, super.getIsNeedSend());
    }

    /**
     * 币种
     *
     * @return
     */
    public String getCcyString() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
    }

    /**
     * 登记
     *
     * @return
     */
    public String getRegistOrgString() {
        return SysDataUtil.getNameFromDataDict(DictCode.登记托管机构, super.getRegistOrg());
    }

    /**
     * 平安业务类型
     *
     * @return
     */
    public String getPaBussTypeString() {
        return SysDataUtil.getNameFromDataDict(DictCode.平安业务类型, super.getPaBussType());
    }

    /**
     * 处理状态
     *
     * @return
     */
    public String getDealStatusString() {
        return SysDataUtil.getNameFromDataDict(DictCode.处理状态, super.getDealStatus());
    }

    /**
     * 发送状态
     *
     * @return
     */
    public String getSendStatusString() {
        return SysDataUtil.getNameFromDataDict(DictCode.成功失败标志, super.getSendStatus());
    }

    public String getTotle() {
        return totle;
    }

    public void setTotle(String totle) {
        this.totle = totle;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public String getSettle() {
        return settle;
    }

    public void setSettle(String settle) {
        this.settle = settle;
    }

    public String getPorid() {
        return porid;
    }

    public void setPorid(String porid) {
        this.porid = porid;
    }

    public String getPormame() {
        return pormame;
    }

    public void setPormame(String pormame) {
        this.pormame = pormame;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    @Override
    public String getCcy() {
        return ccy;
    }

    @Override
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTrusteeship() {
        return trusteeship;
    }

    public void setTrusteeship(String trusteeship) {
        this.trusteeship = trusteeship;
    }

    @Override
    public String getPs() {
        return ps;
    }

    @Override
    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getRaiseAcctNo() {
        return raiseAcctNo;
    }

    public void setRaiseAcctNo(String raiseAcctNo) {
        this.raiseAcctNo = raiseAcctNo;
    }


/*
    // 数据字典.平安业务类型
    public String getPaBizTypeString() {
        return SysDataUtil.getNameFromDataDict(DictCode.平安业务类型, getPaBizType());
    }

    // 数据字典.划款类型
    public String getPayTypeString() {
        // 一体化对应etliu start
//        return SysDataUtil.getNameFromDataDict(DictCode.划款类型, getPayType());
        return SysDataUtil.getNameFromDataDict(DictCode.平安划款类型, getPayType());
        // 一体化对应etliu end
    }
    */

}
