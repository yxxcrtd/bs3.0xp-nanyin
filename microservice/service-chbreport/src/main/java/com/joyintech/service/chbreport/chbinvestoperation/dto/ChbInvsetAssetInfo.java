package com.joyintech.service.chbreport.chbinvestoperation.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName ChbInvsetAssetInfo
 * @Description: 非标投资运行报告变动资产信息
 * @Author yanya
 * @Date 2021-04-25
 * @Version V1.0
 **/
public class ChbInvsetAssetInfo extends BaseEntity {

    private static final long serialVersionUID = 7786306168011741782L;
    /**
     * 行内资产负债编码
     */
    private String assetCode;

    /**
     * 资产名称
     */
    private String assetName;

    /**
     * 资产类型
     */
    private String assetType;

    /**
     * 资产/负债二级类别 参考数据字典:ASSET_TYPE_SECOND_LEVEL
     */
    private String assetType2;

    /**
     * 融资人
     */
    private String financie;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 收益率/年利率
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal assetYield;

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getAssetType2() {
        return assetType2;
    }

    public void setAssetType2(String assetType2) {
        this.assetType2 = assetType2;
    }

    public String getFinancie() {
        return financie;
    }

    public void setFinancie(String financie) {
        this.financie = financie;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public BigDecimal getAssetYield() {
        return assetYield;
    }

    public void setAssetYield(BigDecimal assetYield) {
        this.assetYield = assetYield;
    }
}
