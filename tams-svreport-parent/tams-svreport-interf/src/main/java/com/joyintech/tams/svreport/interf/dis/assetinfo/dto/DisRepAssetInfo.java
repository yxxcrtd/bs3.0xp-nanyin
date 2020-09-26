package com.joyintech.tams.svreport.interf.dis.assetinfo.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class DisRepAssetInfo extends BaseEntity {
    /**
     * 资产代码
     */
    private String assetId;

    /**
     * 市场代码
     */
    private String marketId;

    /**
     * 资产名称
     */
    private String assetName;

    /**
     * 发行人
     */
    private String issuer;

    /**
     * 发行日
     */
    private Date idate;

    /**
     * 发行价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal issuePrice;

    /**
     * 发行规模
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal issueAmt;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 实际到期日
     */
    private Date actmdate;

    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 登记托管机构
     */
    private String registOrg;

    /**
     * 导入方法
     */
    private String inputType;

    /**
     * 导入用户
     */
    private String inputUser;

    /**
     * 适配器ID
     */
    private String adapterId;

    /**
     * null
     */
    private String assetType;

    /**
     * 融资人
     */
    private String fixAncier;

    /**
     * 资产一级分类
     */
    private String assetTypeOne;

    /**
     * 资产二级分类
     */
    private String assetTypeTwo;

    /**
     * 资产三级分类
     */
    private String assetTypeThree;

    /**
     * 业务类型
     */
    private String assetBusiType;

    /**
     * 是否标准资产
     */
    private String isStandAsset;

    /**
     * null
     */
    private String rateType;

    /**
     * 资产类型-信批
     */
    private String assetTypeDis;

    /**
     * 信披资产一级类型
     */
    private String disAssetTypeone;
    
    /**
     * 信披资产二级类型
     */
    private String disAssetTypeytwo;
    
    /**
     * 信披资产三级类型
     */
    private String disAssetTypethree;
    
    /**
     * 取得 资产代码
     * @return 资产代码
     */
    public String getAssetId() {
        return assetId;
    }

    /**
     * 设置 资产代码
     * @param assetId 资产代码
     */
    public void setAssetId(String assetId) {
        this.assetId = assetId == null ? null : assetId.trim();
    }

    /**
     * 取得 市场代码
     * @return 市场代码
     */
    public String getMarketId() {
        return marketId;
    }

    /**
     * 设置 市场代码
     * @param marketId 市场代码
     */
    public void setMarketId(String marketId) {
        this.marketId = marketId == null ? null : marketId.trim();
    }

    /**
     * 取得 资产名称
     * @return 资产名称
     */
    public String getAssetName() {
        return assetName;
    }

    /**
     * 设置 资产名称
     * @param assetName 资产名称
     */
    public void setAssetName(String assetName) {
        this.assetName = assetName == null ? null : assetName.trim();
    }

    /**
     * 取得 发行人
     * @return 发行人
     */
    public String getIssuer() {
        return issuer;
    }

    /**
     * 设置 发行人
     * @param issuer 发行人
     */
    public void setIssuer(String issuer) {
        this.issuer = issuer == null ? null : issuer.trim();
    }

    /**
     * 取得 发行日
     * @return 发行日
     */
    public Date getIdate() {
        return idate;
    }

    /**
     * 设置 发行日
     * @param idate 发行日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setIdate(Date idate) {
        this.idate = idate;
    }

    /**
     * 取得 发行价
     * @return 发行价
     */
    public BigDecimal getIssuePrice() {
        return issuePrice;
    }

    /**
     * 设置 发行价
     * @param issuePrice 发行价
     */
    public void setIssuePrice(BigDecimal issuePrice) {
        this.issuePrice = issuePrice;
    }

    /**
     * 取得 发行规模
     * @return 发行规模
     */
    public BigDecimal getIssueAmt() {
        return issueAmt;
    }

    /**
     * 设置 发行规模
     * @param issueAmt 发行规模
     */
    public void setIssueAmt(BigDecimal issueAmt) {
        this.issueAmt = issueAmt;
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 起息日
     * @return 起息日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 起息日
     * @param vdate 起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 到期日
     * @return 到期日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 到期日
     * @param mdate 到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 实际到期日
     * @return 实际到期日
     */
    public Date getActmdate() {
        return actmdate;
    }

    /**
     * 设置 实际到期日
     * @param actmdate 实际到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setActmdate(Date actmdate) {
        this.actmdate = actmdate;
    }

    /**
     * 取得 交易场所
     * @return 交易场所
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 交易场所
     * @param tradeMarket 交易场所
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket == null ? null : tradeMarket.trim();
    }

    /**
     * 取得 登记托管机构
     * @return 登记托管机构
     */
    public String getRegistOrg() {
        return registOrg;
    }

    /**
     * 设置 登记托管机构
     * @param registOrg 登记托管机构
     */
    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg == null ? null : registOrg.trim();
    }

    /**
     * 取得 导入方法
     * @return 导入方法
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * 设置 导入方法
     * @param inputType 导入方法
     */
    public void setInputType(String inputType) {
        this.inputType = inputType == null ? null : inputType.trim();
    }

    /**
     * 取得 导入用户
     * @return 导入用户
     */
    public String getInputUser() {
        return inputUser;
    }

    /**
     * 设置 导入用户
     * @param inputUser 导入用户
     */
    public void setInputUser(String inputUser) {
        this.inputUser = inputUser == null ? null : inputUser.trim();
    }

    /**
     * 取得 适配器ID
     * @return 适配器ID
     */
    public String getAdapterId() {
        return adapterId;
    }

    /**
     * 设置 适配器ID
     * @param adapterId 适配器ID
     */
    public void setAdapterId(String adapterId) {
        this.adapterId = adapterId == null ? null : adapterId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * 设置 null
     * @param assetType null
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType == null ? null : assetType.trim();
    }

    /**
     * 取得 融资人
     * @return 融资人
     */
    public String getFixAncier() {
        return fixAncier;
    }

    /**
     * 设置 融资人
     * @param fixAncier 融资人
     */
    public void setFixAncier(String fixAncier) {
        this.fixAncier = fixAncier == null ? null : fixAncier.trim();
    }

    /**
     * 取得 资产一级分类
     * @return 资产一级分类
     */
    public String getAssetTypeOne() {
        return assetTypeOne;
    }

    /**
     * 设置 资产一级分类
     * @param assetTypeOne 资产一级分类
     */
    public void setAssetTypeOne(String assetTypeOne) {
        this.assetTypeOne = assetTypeOne == null ? null : assetTypeOne.trim();
    }

    /**
     * 取得 资产二级分类
     * @return 资产二级分类
     */
    public String getAssetTypeTwo() {
        return assetTypeTwo;
    }

    /**
     * 设置 资产二级分类
     * @param assetTypeTwo 资产二级分类
     */
    public void setAssetTypeTwo(String assetTypeTwo) {
        this.assetTypeTwo = assetTypeTwo == null ? null : assetTypeTwo.trim();
    }

    /**
     * 取得 资产三级分类
     * @return 资产三级分类
     */
    public String getAssetTypeThree() {
        return assetTypeThree;
    }

    /**
     * 设置 资产三级分类
     * @param assetTypeThree 资产三级分类
     */
    public void setAssetTypeThree(String assetTypeThree) {
        this.assetTypeThree = assetTypeThree == null ? null : assetTypeThree.trim();
    }

    /**
     * 取得 业务类型
     * @return 业务类型
     */
    public String getAssetBusiType() {
        return assetBusiType;
    }

    /**
     * 设置 业务类型
     * @param assetBusiType 业务类型
     */
    public void setAssetBusiType(String assetBusiType) {
        this.assetBusiType = assetBusiType == null ? null : assetBusiType.trim();
    }

    /**
     * 取得 是否标准资产
     * @return 是否标准资产
     */
    public String getIsStandAsset() {
        return isStandAsset;
    }

    /**
     * 设置 是否标准资产
     * @param isStandAsset 是否标准资产
     */
    public void setIsStandAsset(String isStandAsset) {
        this.isStandAsset = isStandAsset == null ? null : isStandAsset.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getRateType() {
        return rateType;
    }

    /**
     * 设置 null
     * @param rateType null
     */
    public void setRateType(String rateType) {
        this.rateType = rateType == null ? null : rateType.trim();
    }

    /**
     * 取得 资产类型-信批
     * @return 资产类型-信批
     */
    public String getAssetTypeDis() {
        return assetTypeDis;
    }

    /**
     * 设置 资产类型-信批
     * @param assetTypeDis 资产类型-信批
     */
    public void setAssetTypeDis(String assetTypeDis) {
        this.assetTypeDis = assetTypeDis == null ? null : assetTypeDis.trim();
    }

	public String getDisAssetTypeone() {
		return disAssetTypeone;
	}

	public void setDisAssetTypeone(String disAssetTypeone) {
		this.disAssetTypeone = disAssetTypeone;
	}

	public String getDisAssetTypeytwo() {
		return disAssetTypeytwo;
	}

	public void setDisAssetTypeytwo(String disAssetTypeytwo) {
		this.disAssetTypeytwo = disAssetTypeytwo;
	}

	public String getDisAssetTypethree() {
		return disAssetTypethree;
	}

	public void setDisAssetTypethree(String disAssetTypethree) {
		this.disAssetTypethree = disAssetTypethree;
	}
}