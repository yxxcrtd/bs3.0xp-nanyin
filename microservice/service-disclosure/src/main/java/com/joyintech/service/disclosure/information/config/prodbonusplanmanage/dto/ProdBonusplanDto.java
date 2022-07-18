package com.joyintech.service.disclosure.information.config.prodbonusplanmanage.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ProdBonusplanDto extends BaseEntity {
    /**
     * 分红计划编号
     */
    private String bonusPlanId;

    /**
     * 产品编号
     */
    private String prodId;

    /**
     * 分红方式：数据字典code:DIS/BONUS_MODE,01-份额分红后净值为1、02-到期兑付
     */
    private String bonusMode;

    /**
     * 收益分配基准日
     */
    private Date settDate;

    /**
     * 红利发放日
     */
    private Date paymentDate;

    /**
     * 业绩报酬
     */
    private String achievement;

    /**
     * 超额业绩报酬收取比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal excessAchievementProp;

    /**
     * 业绩基准
     */
    private String achievementBasis;

    /**
     * 业绩比较基准比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal achievementBasisProp;

    /**
     * 数据状态：数据字典，系统模块-DIS，字典code：DATA_STATUS:E-有效，D-删除
     */
    private String dataStatus;

    /**
     * 数据来源:数据字典code:DATA_SOURCE
     */
    private String dataSource;



    //以下为自定义字段

    private Map<String, Object> param;

    private String updateDateStr;

    private String prodName;

    private String dataStatusName;

    /**
     * 收益分配基准日
     */
    private String settDateStr;

    /**
     * 红利发放日
     */
    private String paymentDateStr;

    private String bonusModeName;

    /**
     * 产品编号拼接
     */
    private String prodIdArr;

    private String operationType ;

    /**
     * 选中的数据
     */
    private transient List<ProdBonusplanDto> selectedItem;
    /**
     * 元素ID
     */
    private String elementId;

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public List<ProdBonusplanDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<ProdBonusplanDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getProdIdArr() {
        return prodIdArr;
    }

    public void setProdIdArr(String prodIdArr) {
        this.prodIdArr = prodIdArr;
    }

    public String getSettDateStr() {
        return settDateStr;
    }

    public void setSettDateStr(String settDateStr) {
        this.settDateStr = settDateStr;
    }

    public String getPaymentDateStr() {
        return paymentDateStr;
    }

    public void setPaymentDateStr(String paymentDateStr) {
        this.paymentDateStr = paymentDateStr;
    }

    public String getBonusModeName() {
        return bonusModeName;
    }

    public void setBonusModeName(String bonusModeName) {
        this.bonusModeName = bonusModeName;
    }

    public Map<String, Object> getParam() {
        return param;
    }

    public void setParam(Map<String, Object> param) {
        this.param = param;
    }

    public String getUpdateDateStr() {
        return updateDateStr;
    }

    public void setUpdateDateStr(String updateDateStr) {
        this.updateDateStr = updateDateStr;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getDataStatusName() {
        return dataStatusName;
    }

    public void setDataStatusName(String dataStatusName) {
        this.dataStatusName = dataStatusName;
    }

    /**
     * 取得 分红计划编号
     * @return 分红计划编号
     */
    public String getBonusPlanId() {
        return bonusPlanId;
    }

    /**
     * 设置 分红计划编号
     * @param bonusPlanId 分红计划编号
     */
    public void setBonusPlanId(String bonusPlanId) {
        this.bonusPlanId = bonusPlanId == null ? null : bonusPlanId.trim();
    }

    /**
     * 取得 产品编号
     * @return 产品编号
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品编号
     * @param prodId 产品编号
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 分红方式：数据字典code:DIS/BONUS_MODE,01-份额分红后净值为1、02-到期兑付
     * @return 分红方式：数据字典code:DIS/BONUS_MODE,01-份额分红后净值为1、02-到期兑付
     */
    public String getBonusMode() {
        return bonusMode;
    }

    /**
     * 设置 分红方式：数据字典code:DIS/BONUS_MODE,01-份额分红后净值为1、02-到期兑付
     * @param bonusMode 分红方式：数据字典code:DIS/BONUS_MODE,01-份额分红后净值为1、02-到期兑付
     */
    public void setBonusMode(String bonusMode) {
        this.bonusMode = bonusMode == null ? null : bonusMode.trim();
    }

    /**
     * 取得 收益分配基准日
     * @return 收益分配基准日
     */
    public Date getSettDate() {
        return settDate;
    }

    /**
     * 设置 收益分配基准日
     * @param settDate 收益分配基准日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSettDate(Date settDate) {
        this.settDate = settDate;
    }

    /**
     * 取得 红利发放日
     * @return 红利发放日
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * 设置 红利发放日
     * @param paymentDate 红利发放日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * 取得 业绩报酬
     * @return 业绩报酬
     */
    public String getAchievement() {
        return achievement;
    }

    /**
     * 设置 业绩报酬
     * @param achievement 业绩报酬
     */
    public void setAchievement(String achievement) {
        this.achievement = achievement == null ? null : achievement.trim();
    }

    /**
     * 取得 超额业绩报酬收取比例
     * @return 超额业绩报酬收取比例
     */
    public BigDecimal getExcessAchievementProp() {
        return excessAchievementProp;
    }

    /**
     * 设置 超额业绩报酬收取比例
     * @param excessAchievementProp 超额业绩报酬收取比例
     */
    public void setExcessAchievementProp(BigDecimal excessAchievementProp) {
        this.excessAchievementProp = excessAchievementProp;
    }

    /**
     * 取得 业绩基准
     * @return 业绩基准
     */
    public String getAchievementBasis() {
        return achievementBasis;
    }

    /**
     * 设置 业绩基准
     * @param achievementBasis 业绩基准
     */
    public void setAchievementBasis(String achievementBasis) {
        this.achievementBasis = achievementBasis == null ? null : achievementBasis.trim();
    }

    /**
     * 取得 业绩比较基准比例
     * @return 业绩比较基准比例
     */
    public BigDecimal getAchievementBasisProp() {
        return achievementBasisProp;
    }

    /**
     * 设置 业绩比较基准比例
     * @param achievementBasisProp 业绩比较基准比例
     */
    public void setAchievementBasisProp(BigDecimal achievementBasisProp) {
        this.achievementBasisProp = achievementBasisProp;
    }

    /**
     * 取得 数据状态：数据字典，系统模块-DIS，字典code：DATA_STATUS:E-有效，D-删除
     * @return 数据状态：数据字典，系统模块-DIS，字典code：DATA_STATUS:E-有效，D-删除
     */
    public String getDataStatus() {
        return dataStatus;
    }

    /**
     * 设置 数据状态：数据字典，系统模块-DIS，字典code：DATA_STATUS:E-有效，D-删除
     * @param dataStatus 数据状态：数据字典，系统模块-DIS，字典code：DATA_STATUS:E-有效，D-删除
     */
    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus == null ? null : dataStatus.trim();
    }

    /**
     * 取得 数据来源:数据字典code:DATA_SOURCE
     * @return 数据来源:数据字典code:DATA_SOURCE
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * 设置 数据来源:数据字典code:DATA_SOURCE
     * @param dataSource 数据来源:数据字典code:DATA_SOURCE
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }


}