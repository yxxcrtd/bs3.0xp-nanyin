/*
 * 文件名：ProdValuationDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.valscheme.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.tams.common.base.entity.BokValTableData;

public class ProdValuationDto {

    /**
     * 科目号
     */
    private String subjectNo;

    /**
     * 科目名称
     */
    private String subjectName;

    /**
     * 科目级别
     */
    private Long subjectLevel;

    /**
     * 上级科目号
     */
    private String fsubjectId;

    /**
     * 科目类型
     */
    private String subjectType;

    /**
     * 原币币种
     */
    private String oCcy;

    /**
     * 汇率（原币对本币）
     */
    private BigDecimal exRate;


    /**
     * 数量
     */
    private BigDecimal num;

    /**
     * 单位成本
     */
    private BigDecimal unitCost;

    /**
     * 原币成本
     */
    private BigDecimal cost;

    /**
     * 本币成本
     */
    private BigDecimal bAmtCost;

    /**
     * 本币成本占净值比
     */
    private BigDecimal costPercent;

    /**
     * 行情价格
     */
    private BigDecimal price;

    /**
     * 原币估值
     */
    private BigDecimal value;

    /**
     * 本币估值
     */
    private BigDecimal bAmtValue;

    /**
     * 本币估值占净值比
     */
    private BigDecimal valuePercent;

    /**
     * 本币估值增值
     */
    private BigDecimal valueIncrement;

    /**
     * 影子价格估值
     */
    private BigDecimal shadowPriceValue;

    /**
     * 余额方向
     */
    private String balFlag;

    /**
     * 收益类型
     */
    private String profitType;

    /**
     * 行情日期
     */
    private Date marketDate;

    /**
     * 明细区分
     */
    private String detailDist;

    /**
     * 分层代码
     */
    private String layeringId;


    /**
     * 向上汇总方式--全部 11，仅成本 10，仅市值 01
     */
    private String rollUpType;


    public ProdValuationDto() {

    }

    /**
     * 构造方法重载
     * 
     * @param subjectNo 科目号
     * @param profitType 收益类型
     */
    public ProdValuationDto(String subjectNo, String profitType) {
        this.subjectNo = subjectNo;
        this.cost = BigDecimal.ZERO;
        this.value = BigDecimal.ZERO;
        this.valueIncrement = BigDecimal.ZERO;
        this.profitType = profitType;
        this.shadowPriceValue = BigDecimal.ZERO;
    }

    /**
     * 净值年/季/月/周/日 增长率 构造方法
     * 
     * @param subjectNo 科目代码
     * @param prodTotUnitNet 产品累计单位净值
     * @param prodTotNetTime 上一(年/季/月/周/天)末日产品累计净值
     */
    public ProdValuationDto(String subjectNo, BigDecimal prodTotUnitNet,
                            BigDecimal prodTotNetTime) {
        // 科目代码 <- 科目代码
        this.subjectNo = subjectNo;
        // 科目名称 <- (变量.产品累计单位净值-变量.上一季末日产品累计净值)/变量.上一季末日产品累计净值*100 保留4位小数
        this.subjectName = ArithmeticUtil.multiply(4, null,
            ArithmeticUtil.divide(ArithmeticUtil.subtract(prodTotUnitNet, prodTotNetTime),
                prodTotNetTime),
            new BigDecimal(100)).toString();
        // 其他项目为空
    }

    public String getSubjectNo() {
        return subjectNo;
    }

    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Long getSubjectLevel() {
        return subjectLevel;
    }

    public void setSubjectLevel(Long subjectLevel) {
        this.subjectLevel = subjectLevel;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getCostPercent() {
        return costPercent;
    }

    public void setCostPercent(BigDecimal costPercent) {
        this.costPercent = costPercent;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValuePercent() {
        return valuePercent;
    }

    public void setValuePercent(BigDecimal valuePercent) {
        this.valuePercent = valuePercent;
    }

    public BigDecimal getValueIncrement() {
        return valueIncrement;
    }

    public void setValueIncrement(BigDecimal valueIncrement) {
        this.valueIncrement = valueIncrement;
    }

    public BigDecimal getShadowPriceValue() {
        return shadowPriceValue;
    }

    public void setShadowPriceValue(BigDecimal shadowPriceValue) {
        this.shadowPriceValue = shadowPriceValue;
    }

    public String getFsubjectId() {
        return fsubjectId;
    }

    public void setFsubjectId(String fsubjectId) {
        this.fsubjectId = fsubjectId;
    }

    public String getBalFlag() {
        return balFlag;
    }

    public void setBalFlag(String balFlag) {
        this.balFlag = balFlag;
    }

    /**
     * 获取 收益类型
     * 
     * @return 收益类型.
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 收益类型
     * 
     * @param profitType 收益类型
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    /**
     * 获取 marketDate
     * 
     * @return marketDate.
     */
    public Date getMarketDate() {
        return marketDate;
    }

    /**
     * 设置 marketDate
     * 
     * @param marketDate marketDate
     */
    public void setMarketDate(Date marketDate) {
        this.marketDate = marketDate;
    }
    
    /**
     * 获取 数量（模板对应字段）
     * 
     * @return 数量（模板对应字段）
     */
    public BigDecimal getNumAmt() {
        return this.num;
    }

    /**
     * 获取 市价
     * 
     * @return 市价
     */
    public BigDecimal getClosePrice() {
        return this.price;
    }

    /**
     * 获取 市值
     * 
     * @return 市值
     */
    public BigDecimal getMarketValue() {
        return this.value;
    }

    /**
     * 获取 明细区分
     * 
     * @return 明细区分
     */
    public String getDetailDist() {
        return detailDist;
    }

    /**
     * 设置 明细区分
     * 
     * @param detailDist 明细区分
     */
    public void setDetailDist(String detailDist) {
        this.detailDist = detailDist;
    }
    
    
    /**
     * 获取 分层代码
     * @return 分层代码
     */
    public String getLayeringId() {
        return layeringId;
    }

    
    /**
     * 设置 分层代码
     * @param layeringId 分层代码
     */
    public void setLayeringId(String layeringId) {
        this.layeringId = layeringId;
    }


    /**
     * 获取 向上汇总方式
     *
     * @return 向上汇总方式
     */
    public String getRollUpType() {
        return rollUpType;
    }

    /**
     * 设置 向上汇总方式
     *
     * @param rollUpType 向上汇总方式
     */
    public void setRollUpType(String rollUpType) {
        this.rollUpType = rollUpType;
    }

    public String getoCcy() {
        return oCcy;
    }

    public void setoCcy(String oCcy) {
        this.oCcy = oCcy;
    }

    public BigDecimal getExRate() {
        return exRate;
    }

    public void setExRate(BigDecimal exRate) {
        this.exRate = exRate;
    }

    public BigDecimal getbAmtCost() {
        return bAmtCost;
    }

    public void setbAmtCost(BigDecimal bAmtCost) {
        this.bAmtCost = bAmtCost;
    }

    public BigDecimal getbAmtValue() {
        return bAmtValue;
    }

    public void setbAmtValue(BigDecimal bAmtValue) {
        this.bAmtValue = bAmtValue;
    }

    /**
     * 实体合到估值表
     * @param seqNo
     * @param booksetId
     * @param booksetName
     * @param valDate
     * @param detailDist
     * @return
     * @see
     * @since 1.0
     */
    public BokValTableData toBokValTableData(String seqNo, String booksetId, String booksetName,
                                             Date valDate, String detailDist) {
        BokValTableData b = new BokValTableData();
        BeanUtil.copyPropertiesIgnoreNull(b, this);
        b.setSeqNo(seqNo);
        b.setBooksetId(booksetId);
        b.setBooksetName(booksetName);
        b.setValDate(valDate);
        if (StringUtils.isNotEmpty(detailDist)) {
            b.setDetailDist(detailDist);
        }
        b.setNumAmt(this.getNum());
        b.setClosePrice(this.getPrice());
        //b.setMarketValue(this.getbAmtValue());
        b.setMarketValDate(this.getMarketDate());


        //设计变更：多币种表样对应-----------增加的字段
        // 原币币种 ===> 表样.币币种
        b.setoCcy(this.getoCcy());
        // 汇率(原币对本币) ===> 表样.汇率
        b.setExchangeRate(this.getExRate());
        // 原币成本 ===> 表样.原币成本
        b.setoCost(this.getCost());
        // 原币估值 ===> 表样.原币估值
        b.setoMarketValue(this.getValue());

        //设计变更：多币种表样对应-----------修改的字段
        // 本币成本【原：成本】 ===> 表样.本币成本
        b.setCost(this.getbAmtCost());
        // 本币成本占净值比【原：成本占净值比】 ===> 表样.成本占净值比(代码中已进行计算)
        b.setCostPercent(this.getCostPercent());
        // 本币估值【原：估值】 ===>表样.本币估值
        b.setMarketValue(this.getbAmtValue());
        // 本币估值占净值比【原：估值占净值比】 ===>表样.本币估值占净值比(代码中已进行计算)
        b.setValuePercent(this.getValuePercent());
        // 本币估值增值【原：估值增值】 ===>表样.本币估值增值(代码中已进行计算)
        b.setValueIncrement(this.getValueIncrement());

        return b;
    }

    /**
     * 相加合计 更新
     * 
     * @param dto
     * @see
     * @since 1.0
     */
    public void addUpdate(ProdValuationDto dto) {
        // 原币成本 += 变量.科目明细.原币成本
        this.setCost(ArithmeticUtil.doAdd(this.getCost(), dto.getCost()));
        // 本币成本 += 变量.科目明细.本币成本
        this.setbAmtCost(ArithmeticUtil.doAdd(this.getbAmtCost(), dto.getbAmtCost()));
        // 原币估值 += 变量.科目明细.原币估值
        this.setValue(ArithmeticUtil.doAdd(this.getValue(), dto.getValue()));
        // 本币估值 += 变量.科目明细.本币估值
        this.setbAmtValue(ArithmeticUtil.doAdd(this.getbAmtValue(), dto.getbAmtValue()));
        // 本币估值增值 += 变量.科目明细.本币估值增值
        this.setValueIncrement(ArithmeticUtil.doAdd(this.getValueIncrement(), dto.getValueIncrement()));
        // 影子价格估值 += 变量.科目明细.影子价格估值
        this.setShadowPriceValue(ArithmeticUtil.doAdd(this.getShadowPriceValue(), dto.getShadowPriceValue()));
    }

    /**
     * 相减合计 更新
     * 
     * @param dto
     * @see
     * @since 1.0
     */
    public void subUpdate(ProdValuationDto dto) {
        // 原币成本 -= 变量.科目明细.原币成本
        this.setCost(ArithmeticUtil.subtract(this.getCost(), dto.getCost()));
        // 本币成本 -= 变量.科目明细.本币成本
        this.setbAmtCost(ArithmeticUtil.subtract(this.getbAmtCost(), dto.getbAmtCost()));
        // 原币估值 -= 变量.科目明细.原币估值
        this.setValue(ArithmeticUtil.subtract(this.getValue(), dto.getValue()));
        // 本币估值 -= 变量.科目明细.本币估值
        this.setbAmtValue(ArithmeticUtil.subtract(this.getbAmtValue(), dto.getbAmtValue()));
        // 本币估值增值 -= 变量.科目明细.本币估值增值
        this.setValueIncrement(ArithmeticUtil.subtract(this.getValueIncrement(), dto.getValueIncrement()));
        // 影子价格估值 -= 变量.科目明细.影子价格估值
        this.setShadowPriceValue(ArithmeticUtil.subtract(this.getShadowPriceValue(), dto.getShadowPriceValue()));
    }
}
