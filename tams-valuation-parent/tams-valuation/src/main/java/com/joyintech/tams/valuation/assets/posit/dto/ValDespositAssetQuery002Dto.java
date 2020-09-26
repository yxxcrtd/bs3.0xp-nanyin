package com.joyintech.tams.valuation.assets.posit.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.valuation.assets.posit.validator.ValDespositAssetQuery002CheckValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 详情 修改 新建
 * 
 * @author HuangJian
 * @version 1.0 2018年10月30日
 * @see ValDespositAssetQuery002Dto
 * @since 1.0
 */
@PageId("ValDespositAssetQuery002")
@ValidLogic(targetClass = ValDespositAssetQuery002CheckValidator.class)
public class ValDespositAssetQuery002Dto extends BaseEntity implements ProcessEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 资产代码
     */
    @PrimaryKey(value = Field.PK_1)
    @NotEmpty(fieldName = "ValDespositAsset.finprodId")
    private String finprodId;

    /**
     * 存款类型
     */
    @NotEmpty(fieldName = "ValDespositAsset.finprodType")
    private String finprodType;

    /**
     * 资产名称
     */
    @NotEmpty(fieldName = "ValDespositAsset.finprodName")
    private String finprodName;

    /**
     * 起息日
     */
    @NotEmpty(fieldName = "ValDespositAsset.vdate")
    private Date vdate;

    /**
     * 到期日
     */
    @NotEmpty(fieldName = "ValDespositAsset.mdate")
    private Date mdate;

    /**
     * 本金收益特征
     */
    @NotEmpty(fieldName = "ValDespositAsset.capiIncomeFeature")
    private String capiIncomeFeature;

    /**
     * 境内外
     */
    @NotEmpty(fieldName = "ValDespositAsset.blnArea")
    private String blnArea;

    /**
     * 币种
     */
    @NotEmpty(fieldName = "ValDespositAsset.ccy")
    private String ccy;

    /**
     * 存款本金
     */
    @NotEmpty(fieldName = "ValDespositAsset.issueAmt")
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal issueAmt;

    /**
     * 资产类型
     */
    @NotEmpty(fieldName = "ValDespositAsset.type1")
    private String type1;

    /**
     * 付息频率
     */
    @NotEmpty(fieldName = "ValDespositAsset.payCycle")
    private String payCycle;

//    /**
//     * 付息营业日准则
//     */
//    @NotEmpty(fieldName = "ValDespositAsset.tdayRule")
//    private String tdayRule;

    /**
     * 付息截止日营业日准则
     */
    @NotEmpty(fieldName = "ValDespositAsset.endTdayRule")
    private String endTdayRule;

    /**
     * 付息支付日营业日准则
     */
    @NotEmpty(fieldName = "ValDespositAsset.payTdayRule")
    private String payTdayRule;
    
    /**
     * 推算方式
     */
    @NotEmpty(fieldName = "ValDespositAsset.calcRule")
    private String calcRule;

    /**
     * 首次付息日
     */
    // @NotEmpty(fieldName = "ValDespositAsset.firstPayDate")
    private Date firstPayDate;

    /**
     * 画面元素
     */
    private String elementId;

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
     * 期限
     */
    private Long term;

    /**
     * 核算分类
     */
    private String type2;

    /**
     * 交易日历
     */
    @NotEmpty(fieldName = "ValDespositAsset.calendarId")
    private String calendarId;

    /**
     * 初始利率
     */

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal basicRate;

    /**
     * 利率类型
     */
    @NotEmpty(fieldName = "ValDespositAsset.calcType")
    private String calcType;

    /**
     * 计息基础
     */
    @NotEmpty(fieldName = "ValDespositAsset.basis")
    private String basis;

    /**
     * 存款机构
     */
    private String issuer;

    /**
     * 交易场所
     */
    @NotEmpty(fieldName = "ValDespositAsset.tradeMarket")
    private String tradeMarket;

    /**
     * 是新建还是修改操作 Y：修改 N：新建
     */
    private String isEdit;

    /**
     * 备注
     */
    private String remark;

    /**
     * table1
     */
    private List<DateTableDto> table1;

    /**
     * table2
     */
    private List<DateTableDto> table2;
    
    /**modify by dongjun on 20181203 start **/
    /**
     * table3
     */
    private List<DateTableDto> table3;
    
    /**
     * 获取 table3
     * @return table3.
     */
    public List<DateTableDto> getTable3() {
        return table3;
    }
    
    /**
     * 设置 table3
     * @param table3 table3
     */
    public void setTable3(List<DateTableDto> table3) {
        this.table3 = table3;
    }
    
    /**modify by dongjun on 20181203 end **/

    /**
     * 取得 资产代码
     * 
     * @return 资产代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 资产代码
     * 
     * @param finprodId 资产代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 取得 存款类型
     * 
     * @return 存款类型
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 存款类型
     * 
     * @param finprodType 存款类型
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    /**
     * 取得 资产名称
     * 
     * @return 资产名称
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 资产名称
     * 
     * @param finprodName 资产名称
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    /**
     * 取得 起息日
     * 
     * @return 起息日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 起息日
     * 
     * @param vdate 起息日
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 到期日
     * 
     * @return 到期日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 到期日
     * 
     * @param mdate 到期日
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 本金收益特征
     * 
     * @return 本金收益特征
     */
    public String getCapiIncomeFeature() {
        return capiIncomeFeature;
    }

    /**
     * 设置 本金收益特征
     * 
     * @param capiIncomeFeature 本金收益特征
     */
    public void setCapiIncomeFeature(String capiIncomeFeature) {
        this.capiIncomeFeature = capiIncomeFeature;
    }

    /**
     * 取得 境内外
     * 
     * @return 境内外
     */
    public String getBlnArea() {
        return blnArea;
    }

    /**
     * 设置 境内外
     * 
     * @param blnArea 境内外
     */
    public void setBlnArea(String blnArea) {
        this.blnArea = blnArea;
    }

    /**
     * 取得 币种
     * 
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * 
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 取得 存款本金
     * 
     * @return 存款本金
     */
    public BigDecimal getIssueAmt() {
        return issueAmt;
    }

    /**
     * 设置 存款本金
     * 
     * @param issueAmt 存款本金
     */
    public void setIssueAmt(BigDecimal issueAmt) {
        this.issueAmt = issueAmt;
    }

    /**
     * 取得 资产类型
     * 
     * @return 资产类型
     */
    public String getType1() {
        return type1;
    }

    /**
     * 设置 资产类型
     * 
     * @param type1 资产类型
     */
    public void setType1(String type1) {
        this.type1 = type1;
    }

    /**
     * 取得 付息频率
     * 
     * @return 付息频率
     */
    public String getPayCycle() {
        return payCycle;
    }

    /**
     * 设置 付息频率
     * 
     * @param payCycle 付息频率
     */
    public void setPayCycle(String payCycle) {
        this.payCycle = payCycle;
    }

//    /**
//     * 取得 付息营业日准则
//     * 
//     * @return 付息营业日准则
//     */
//    public String getTdayRule() {
//        return tdayRule;
//    }
//
//    /**
//     * 设置 付息营业日准则
//     * 
//     * @param tdayRule 付息营业日准则
//     */
//    public void setTdayRule(String tdayRule) {
//        this.tdayRule = tdayRule;
//    }

    
    
    /**
     * 取得 推算方式
     * 
     * @return 推算方式
     */
    public String getCalcRule() {
        return calcRule;
    }

    public String getEndTdayRule() {
		return endTdayRule;
	}

	public void setEndTdayRule(String endTdayRule) {
		this.endTdayRule = endTdayRule;
	}

	public String getPayTdayRule() {
		return payTdayRule;
	}

	public void setPayTdayRule(String payTdayRule) {
		this.payTdayRule = payTdayRule;
	}

	/**
     * 设置 推算方式
     * 
     * @param calcRule 推算方式
     */
    public void setCalcRule(String calcRule) {
        this.calcRule = calcRule;
    }

    /**
     * 取得 首次付息日
     * 
     * @return 首次付息日
     */
    public Date getFirstPayDate() {
        return firstPayDate;
    }

    /**
     * 设置 首次付息日
     * 
     * @param firstPayDate 首次付息日
     */
    public void setFirstPayDate(Date firstPayDate) {
        this.firstPayDate = firstPayDate;
    }

    /**
     * 取得 期限
     * 
     * @return 期限
     */
    public Long getTerm() {
        return term;
    }

    /**
     * 设置 期限
     * 
     * @param term 期限
     */
    public void setTerm(Long term) {
        this.term = term;
    }

    /**
     * 取得 核算分类
     * 
     * @return 核算分类
     */
    public String getType2() {
        return type2;
    }

    /**
     * 设置 核算分类
     * 
     * @param type2 核算分类
     */
    public void setType2(String type2) {
        this.type2 = type2;
    }

    /**
     * 取得 交易日历
     * 
     * @return 交易日历
     */
    public String getCalendarId() {
        return calendarId;
    }

    /**
     * 设置 交易日历
     * 
     * @param calendarId 交易日历
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    /**
     * 取得 初始利率
     * 
     * @return 初始利率
     */
    public BigDecimal getBasicRate() {
        return basicRate;
    }

    /**
     * 设置 初始利率
     * 
     * @param basicRate 初始利率
     */
    public void setBasicRate(BigDecimal basicRate) {
        this.basicRate = basicRate;
    }

    /**
     * 取得 利率类型
     * 
     * @return 利率类型
     */
    public String getCalcType() {
        return calcType;
    }

    /**
     * 设置 利率类型
     * 
     * @param calcType 利率类型
     */
    public void setCalcType(String calcType) {
        this.calcType = calcType;
    }

    /**
     * 取得 计息基础
     * 
     * @return 计息基础
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 计息基础
     * 
     * @param basis 计息基础
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }

    /**
     * 取得 存款机构
     * 
     * @return 存款机构
     */
    public String getIssuer() {
        return issuer;
    }

    /**
     * 设置 存款机构
     * 
     * @param issuer 存款机构
     */
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    /**
     * 取得 交易场所
     * 
     * @return 交易场所
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 交易场所
     * 
     * @param tradeMarket 交易场所
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    /**
     * 起息日
     * 
     * @return getVdateFt
     * @see
     * @since 1.0
     */
    public String getVdateFt() {
        return DateUtil.format(vdate);
    }

    /**
     * 付息日
     * 
     * @return getMdateFt
     * @see
     * @since 1.0
     */
    public String getMdateFt() {
        return DateUtil.format(mdate);
    }

    /**
     * 首次付息日
     * 
     * @return getFirstPayDateFt
     * @see
     * @since 1.0
     */
    public String getFirstPayDateFt() {
        return DateUtil.format(firstPayDate);
    }

    /**
     * 获取 isEdit
     * 
     * @return isEdit.
     */
    public String getIsEdit() {
        return isEdit;
    }

    /**
     * 设置 isEdit
     * 
     * @param isEdit isEdit
     */
    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
    }

    /**
     * 获取 remark
     * 
     * @return remark.
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 remark
     * 
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取 table1
     * 
     * @return table1.
     */
    public List<DateTableDto> getTable1() {
        return table1;
    }

    /**
     * 设置 table1
     * 
     * @param table1 table1
     */
    public void setTable1(List<DateTableDto> table1) {
        this.table1 = table1;
    }

    /**
     * 获取 table2
     * 
     * @return table2.
     */
    public List<DateTableDto> getTable2() {
        return table2;
    }

    /**
     * 设置 table2
     * 
     * @param table2 table2
     */
    public void setTable2(List<DateTableDto> table2) {
        this.table2 = table2;
    }

}
