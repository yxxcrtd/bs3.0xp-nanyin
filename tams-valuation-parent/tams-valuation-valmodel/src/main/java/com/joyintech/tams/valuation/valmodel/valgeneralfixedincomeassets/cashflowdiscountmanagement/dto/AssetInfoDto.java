
package com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.cashflowdiscountmanagement.dto;

import java.util.List;

/**
 * 资产信息Dto 模型查询投管数据用
 * 
 * @author yuluan
 * @version 1.0 2021年6月30日
 * @see AssetInfoDto
 * @since 1.0
 */
public class AssetInfoDto {
    /**
     * 是否永续债
     */
    private String isSus;

    /**
     * 是否含权
     */
    private String isRight;

    /**
     * 是否含有利率调整权
     */
    private String isRateAdjust;

    /**
	 * 金融产品代码
	 */
	private String finprodId;
	/**
	 * 市场代码
	 */
	private String finprodMarketId;
	/**
	 * 金融产品简称
	 */
	private String finprodAbbr;
	/**
	 * 金融产品名称
	 */
	private String finprodName;
	/**
	 * 资产一级分类
	 */
	private String type8;
	/**
	 * 资产二级分类
	 */
	private String type9;
	/**
	 * 资产三级分类
	 */
	private String type10;
	/**
	 * 资产四级分类
	 */
	private String type11;
	/**
	 * 资产五级分类
	 */
	private String type12;
	/*一到五级分类List 查询时传参用 begin*/
	private List<String> type8List;
	private List<String> type9List;
	private List<String> type10List;
	private List<String> type11List;
	private List<String> type12List;
	/*一到五级分类List 查询时传参用 end*/
	private String search;
	private String initValue;
	public String getFinprodId() {
		return finprodId;
	}
	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}
	public String getFinprodMarketId() {
		return finprodMarketId;
	}
	public void setFinprodMarketId(String finprodMarketId) {
		this.finprodMarketId = finprodMarketId;
	}
	public String getFinprodAbbr() {
		return finprodAbbr;
	}
	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}
	public String getFinprodName() {
		return finprodName;
	}
	public void setFinprodName(String finprodName) {
		this.finprodName = finprodName;
	}
	public String getType8() {
		return type8;
	}
	public void setType8(String type8) {
		this.type8 = type8;
	}
	public String getType9() {
		return type9;
	}
	public void setType9(String type9) {
		this.type9 = type9;
	}
	public String getType10() {
		return type10;
	}
	public void setType10(String type10) {
		this.type10 = type10;
	}
	public String getType11() {
		return type11;
	}
	public void setType11(String type11) {
		this.type11 = type11;
	}
	public String getType12() {
		return type12;
	}
	public void setType12(String type12) {
		this.type12 = type12;
	}
	public List<String> getType8List() {
		return type8List;
	}
	public void setType8List(List<String> type8List) {
		this.type8List = type8List;
	}
	public List<String> getType9List() {
		return type9List;
	}
	public void setType9List(List<String> type9List) {
		this.type9List = type9List;
	}
	public List<String> getType10List() {
		return type10List;
	}
	public void setType10List(List<String> type10List) {
		this.type10List = type10List;
	}
	public List<String> getType11List() {
		return type11List;
	}
	public void setType11List(List<String> type11List) {
		this.type11List = type11List;
	}
	public List<String> getType12List() {
		return type12List;
	}
	public void setType12List(List<String> type12List) {
		this.type12List = type12List;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getInitValue() {
		return initValue;
	}
	public void setInitValue(String initValue) {
		this.initValue = initValue;
	}

    public String getIsSus() {
        return isSus;
    }

    public void setIsSus(String isSus) {
        this.isSus = isSus;
    }

    public String getIsRight() {
        return isRight;
    }

    public void setIsRight(String isRight) {
        this.isRight = isRight;
    }

    public String getIsRateAdjust() {
        return isRateAdjust;
    }

    public void setIsRateAdjust(String isRateAdjust) {
        this.isRateAdjust = isRateAdjust;
    }
}
