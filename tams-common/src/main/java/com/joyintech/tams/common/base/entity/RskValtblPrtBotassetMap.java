package com.joyintech.tams.common.base.entity;

public class RskValtblPrtBotassetMap extends RskValtblPrtBotassetMapKey {
    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 科目名称
     */
    private String subName;

    /**
     * 科目类型
     */
    private String subClass;

    /**
     * 资产来源
     */
    private String assetSource;
    
    /**
     * 估值表科目对应债券市场代码
     */
    private String tblMarketId;

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
     * 取得 科目名称
     * @return 科目名称
     */
    public String getSubName() {
        return subName;
    }

    /**
     * 设置 科目名称
     * @param subName 科目名称
     */
    public void setSubName(String subName) {
        this.subName = subName == null ? null : subName.trim();
    }

    /**
     * 取得 科目类型
     * @return 科目类型
     */
    public String getSubClass() {
        return subClass;
    }

    /**
     * 设置 科目类型
     * @param subClass 科目类型
     */
    public void setSubClass(String subClass) {
        this.subClass = subClass == null ? null : subClass.trim();
    }

    /**
     * 取得 资产来源
     * @return 资产来源
     */
    public String getAssetSource() {
        return assetSource;
    }

    /**
     * 设置 资产来源
     * @param assetSource 资产来源
     */
    public void setAssetSource(String assetSource) {
        this.assetSource = assetSource == null ? null : assetSource.trim();
    }

	public String getTblMarketId() {
		return tblMarketId;
	}

	public void setTblMarketId(String tblMarketId) {
		this.tblMarketId = tblMarketId;
	}
}