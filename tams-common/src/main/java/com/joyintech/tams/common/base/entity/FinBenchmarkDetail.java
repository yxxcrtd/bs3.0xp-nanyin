package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class FinBenchmarkDetail extends BaseEntity {
    /**
     * 基准代码
     */
    private String bmCode;

    /**
     * 生效日期
     */
    private Date effDate;

    /**
     * 利率类型
     */
    private String intType;

    /**
     * 基准编号
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bmNumber;

    /**
     * 基准类型，利率行情、复合基准等
     */
    private String bmType;

    /**
     * 重置规则，期初、期末等
     */
    private String resetType;

    /**
     * 初始利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal initRate;

    /**
     * 基准利率 参照数据字典
     */
    private String basicRate;

    /**
     * 调整系数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal coefficient;

    /**
     * 利差
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal spreadRate;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 取得 基准代码
     * @return 基准代码
     */
    public String getBmCode() {
        return bmCode;
    }

    /**
     * 设置 基准代码
     * @param bmCode 基准代码
     */
    public void setBmCode(String bmCode) {
        this.bmCode = bmCode == null ? null : bmCode.trim();
    }

    /**
     * 取得 生效日期
     * @return 生效日期
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * 设置 生效日期
     * @param effDate 生效日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    /**
     * 取得 利率类型
     * @return 利率类型
     */
    public String getIntType() {
        return intType;
    }

    /**
     * 设置 利率类型
     * @param intType 利率类型
     */
    public void setIntType(String intType) {
        this.intType = intType == null ? null : intType.trim();
    }

    /**
     * 取得 基准编号
     * @return 基准编号
     */
    public BigDecimal getBmNumber() {
        return bmNumber;
    }

    /**
     * 设置 基准编号
     * @param bmNumber 基准编号
     */
    public void setBmNumber(BigDecimal bmNumber) {
        this.bmNumber = bmNumber;
    }

    /**
     * 取得 基准类型，利率行情、复合基准等
     * @return 基准类型，利率行情、复合基准等
     */
    public String getBmType() {
        return bmType;
    }

    /**
     * 设置 基准类型，利率行情、复合基准等
     * @param bmType 基准类型，利率行情、复合基准等
     */
    public void setBmType(String bmType) {
        this.bmType = bmType == null ? null : bmType.trim();
    }

    /**
     * 取得 重置规则，期初、期末等
     * @return 重置规则，期初、期末等
     */
    public String getResetType() {
        return resetType;
    }

    /**
     * 设置 重置规则，期初、期末等
     * @param resetType 重置规则，期初、期末等
     */
    public void setResetType(String resetType) {
        this.resetType = resetType == null ? null : resetType.trim();
    }

    /**
     * 取得 初始利率
     * @return 初始利率
     */
    public BigDecimal getInitRate() {
        return initRate;
    }

    /**
     * 设置 初始利率
     * @param initRate 初始利率
     */
    public void setInitRate(BigDecimal initRate) {
        this.initRate = initRate;
    }

    /**
     * 取得 基准利率 参照数据字典
     * @return 基准利率 参照数据字典
     */
    public String getBasicRate() {
        return basicRate;
    }

    /**
     * 设置 基准利率 参照数据字典
     * @param basicRate 基准利率 参照数据字典
     */
    public void setBasicRate(String basicRate) {
        this.basicRate = basicRate == null ? null : basicRate.trim();
    }

    /**
     * 取得 调整系数
     * @return 调整系数
     */
    public BigDecimal getCoefficient() {
        return coefficient;
    }

    /**
     * 设置 调整系数
     * @param coefficient 调整系数
     */
    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }

    /**
     * 取得 利差
     * @return 利差
     */
    public BigDecimal getSpreadRate() {
        return spreadRate;
    }

    /**
     * 设置 利差
     * @param spreadRate 利差
     */
    public void setSpreadRate(BigDecimal spreadRate) {
        this.spreadRate = spreadRate;
    }

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 金融产品类型
     * @return 金融产品类型
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 金融产品类型
     * @param finprodType 金融产品类型
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType == null ? null : finprodType.trim();
    }

    /**
     * 取得 分支序号
     * @return 分支序号
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * @param branch 分支序号
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }
}