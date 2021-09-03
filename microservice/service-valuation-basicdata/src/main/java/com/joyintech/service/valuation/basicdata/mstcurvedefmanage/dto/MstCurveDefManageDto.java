
package com.joyintech.service.valuation.basicdata.mstcurvedefmanage.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import java.math.BigDecimal;
import java.util.List;

/**
 * 收益率曲线管理
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author songjun
 * @version 1.0 2021-06-16
 * @see MstCurveDefManageDto
 * @since 1.0
 */
@PageId("MstCurveDefManage001")
public class MstCurveDefManageDto extends BaseEntity implements ITableRow{
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 5143537191208289654L;


    /**
     * 曲线代码
     */
    private String curveCode;
    /**
     * 曲线类型,收益率曲线，利率互换曲线，外汇隐含利率曲线
     */
    private String curveType;
    /**
     * 货币对
     */
    private String curPair;

    /**
     * 人民币利率,shibor，shibor3m利率互换收盘曲线，fr007利率互换收盘曲线
     */
    private String cnyRateId;
    /**
     * 即期汇率类型，即期询价报价均值，中间价
      */
    private String spotRateType;
    /**
     * 远/掉期点,掉期点
     */
    private String spotForwardType;
    /**
     *
     */
    private String baseIntCode;
    /**
     * 曲线类型 数据字典 CURVE_TYPE
     * 01	SP即期
     * 02	MA远期
     * 03	FS远期的即期
     * 04	FM远期的到期
     */
    private String curveIntType;
    /**
     * 时刻，比如16:30
     */
    private  String dataTime;
    /**
     * 价格类型,报买,报卖,均值
     */
    private String priceType;
    /**
     * N
     */
    private BigDecimal n;
    /**
     * K
     */
    private BigDecimal K;
    /**
     * 基准曲线资产类型，国债，中央票据,短期融资债（aaa）
     */
    private String baseAssetType;
    /**
     *  收益率曲线类型,即期，远期，到期
     */
    private String intCurveType;

    /**
     * 曲线来源
     */
    private String valueSource;
    /**
     * 录入方式，接口、手工等
     */
    private String inputType;
    /**
     * 备注
     */
    private String remark;
//    /**
//     * 状态 生效 e 失效 d
//     */
//    private String effStatus;
    /**
     * 曲线名称
     */
    private String curveName;

    /**
     * 币种
     */
    private String ccy;
    /**
     * 插值方式
     */
    private String interMethod;
    /**
     * 日算规则
     */
     private String dayRules;
    /**
     * 日历
     */
    private String calender;
    /**
     * 付息日规则
     */
    private String interestDateRule;
    /**
     * 计息方式
     */
    private String interestCalMethod;
    /**
     * 付息频率
     */
    private String interestFreq;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String pageModel;


    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String elementId;

    private List<MstCurveDefManageDto> selectedItem;

    public String getCurveCode() {
        return curveCode;
    }

    public void setCurveCode(String curveCode) {
        this.curveCode = curveCode;
    }

    public String getCurveType() {
        return curveType;
    }

    public void setCurveType(String curveType) {
        this.curveType = curveType;
    }

    public String getCurPair() {
        return curPair;
    }

    public void setCurPair(String curPair) {
        this.curPair = curPair;
    }

    public String getCnyRateId() {
        return cnyRateId;
    }

    public void setCnyRateId(String cnyRateId) {
        this.cnyRateId = cnyRateId;
    }

    public String getSpotRateType() {
        return spotRateType;
    }

    public void setSpotRateType(String spotRateType) {
        this.spotRateType = spotRateType;
    }

    public String getSpotForwardType() {
        return spotForwardType;
    }

    public void setSpotForwardType(String spotForwardType) {
        this.spotForwardType = spotForwardType;
    }

    public String getBaseIntCode() {
        return baseIntCode;
    }

    public void setBaseIntCode(String baseIntCode) {
        this.baseIntCode = baseIntCode;
    }

    public String getCurveIntType() {
        return curveIntType;
    }

    public void setCurveIntType(String curveIntType) {
        this.curveIntType = curveIntType;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public BigDecimal getN() {
        return n;
    }

    public void setN(BigDecimal n) {
        this.n = n;
    }

    public BigDecimal getK() {
        return K;
    }

    public void setK(BigDecimal k) {
        K = k;
    }

    public String getBaseAssetType() {
        return baseAssetType;
    }

    public void setBaseAssetType(String baseAssetType) {
        this.baseAssetType = baseAssetType;
    }

    public String getIntCurveType() {
        return intCurveType;
    }

    public void setIntCurveType(String intCurveType) {
        this.intCurveType = intCurveType;
    }

    public String getValueSource() {
        return valueSource;
    }

    public void setValueSource(String valueSource) {
        this.valueSource = valueSource;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

//    public String getEffStatus() {
//        return effStatus;
//    }
//
//    public void setEffStatus(String effStatus) {
//        this.effStatus = effStatus;
//    }

    public String getCurveName() {
        return curveName;
    }

    public void setCurveName(String curveName) {
        this.curveName = curveName;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getInterMethod() {
        return interMethod;
    }

    public void setInterMethod(String interMethod) {
        this.interMethod = interMethod;
    }

    public String getDayRules() {
        return dayRules;
    }

    public void setDayRules(String dayRules) {
        this.dayRules = dayRules;
    }

    public String getCalender() {
        return calender;
    }

    public void setCalender(String calender) {
        this.calender = calender;
    }

    public String getInterestDateRule() {
        return interestDateRule;
    }

    public void setInterestDateRule(String interestDateRule) {
        this.interestDateRule = interestDateRule;
    }

    public String getInterestCalMethod() {
        return interestCalMethod;
    }

    public void setInterestCalMethod(String interestCalMethod) {
        this.interestCalMethod = interestCalMethod;
    }

    public String getInterestFreq() {
        return interestFreq;
    }

    public void setInterestFreq(String interestFreq) {
        this.interestFreq = interestFreq;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public List<MstCurveDefManageDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<MstCurveDefManageDto> selectedItem) {
        this.selectedItem = selectedItem;
    }
}
