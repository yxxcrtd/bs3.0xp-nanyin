package com.joyintech.tams.invest.common.trade.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.CreditTagInfo;

/**
 * 授信共通第一步结果信息
 * {主要包含共通需要查询到的值}
 */
public class CreditComInfoOneStepDto extends CreditTagInfo {
    /**
     * 期限的具体天数
     */
    private BigDecimal termNum;
    /**
     * 交易日期
     */
    private Date tradeDate;
    /**
     * 扣减对象类型
     */
    private String deductObjType;
    /**
     * 扣减对象
     */
    private String deductObj;
    /**
     * 期限种类
     */
    private String termUnit;
    /**
     * 暂存机构额度的批复号
     */
    private List<String> pflshList;


    public List<String> getPflshList() {
        return pflshList;
    }

    public void setPflshList(List<String> pflshList) {
        this.pflshList = pflshList;
    }

    /**
     * 获取 期限的具体天数
     *
     * @return termNum 期限的具体天数
     */
    public BigDecimal getTermNum() {
        return this.termNum;
    }

    /**
     * 设置 期限的具体天数
     *
     * @param termNum 期限的具体天数
     */
    public void setTermNum(BigDecimal termNum) {
        this.termNum = termNum;
    }

    /**
     * 获取 交易日期
     *
     * @return tradeDate 交易日期
     */
    public Date getTradeDate() {
        return this.tradeDate;
    }

    /**
     * 设置 交易日期
     *
     * @param tradeDate 交易日期
     */
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }


    /**
     * 获取 扣减对象
     *
     * @return deductObj 扣减对象
     */
    public String getDeductObj() {
        return this.deductObj;
    }

    /**
     * 设置 扣减对象
     *
     * @param deductObj 扣减对象
     */
    public void setDeductObj(String deductObj) {
        this.deductObj = deductObj;
    }

    /**
     * 获取 扣减对象类型
     *
     * @return deductObjType 扣减对象类型
     */
    public String getDeductObjType() {
        return this.deductObjType;
    }

    /**
     * 设置 扣减对象类型
     *
     * @param deductObjType 扣减对象类型
     */
    public void setDeductObjType(String deductObjType) {
        this.deductObjType = deductObjType;
    }

    public String getTermUnit() {
        return termUnit;
    }

    public void setTermUnit(String termUnit) {
        this.termUnit = termUnit;
    }
}
