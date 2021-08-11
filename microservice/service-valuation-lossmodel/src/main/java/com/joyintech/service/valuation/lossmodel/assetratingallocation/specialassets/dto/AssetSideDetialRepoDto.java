package com.joyintech.service.valuation.lossmodel.assetratingallocation.specialassets.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.systemtools.util.SysDatadictUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 负债端品种与期限分布明细Model
 * 
 */
public class AssetSideDetialRepoDto extends BaseEntity implements Comparable<AssetSideDetialRepoDto>{
	private static Map<String, String> prodTypeMap = new LinkedHashMap<String, String>();
	private static Map<String, String> assetTypeMap = new LinkedHashMap<String, String>();
	static {
		prodTypeMap.put("01", "非净值型现金管理类产品");
		prodTypeMap.put("02", "非净值型封闭式预期收益型");
		prodTypeMap.put("03", "净值型现金管理类产品");
		prodTypeMap.put("04", "净值型定期开放式产品");
		prodTypeMap.put("05", "封闭净值型");
		prodTypeMap.put("06", "产品端");
		prodTypeMap.put("07", "融资负债");
		prodTypeMap.put("08", "合计");
		assetTypeMap.put("100", "债券类资产");	
		assetTypeMap.put("101", "债券类资产小计");
		assetTypeMap.put("200", "同业投资及货币市场工具");
		assetTypeMap.put("201", "同业投资及货币市场工具小计");
		assetTypeMap.put("300",	"非标准化债权类资产");
		assetTypeMap.put("301",	"非标准化债权类资产小计");
		assetTypeMap.put("400",	"权益类投资");
		assetTypeMap.put("401",	"权益类投资小计");
		assetTypeMap.put("500",	"私募基金");
		assetTypeMap.put("501",	"私募基金小计");
		assetTypeMap.put("600",	"金融衍生品、商品类及另类资产");
		assetTypeMap.put("601",	"金融衍生品、商品类及另类资产小计");
		assetTypeMap.put("700",	"资产管理产品");
		assetTypeMap.put("701",	"资产管理产品小计");
		assetTypeMap.put("800",	"委托类投资");
		assetTypeMap.put("801",	"委托类投资小计");
		assetTypeMap.put("900",	"其他资产");
		assetTypeMap.put("901",	"其他资产小计");
		assetTypeMap.put("910",	"合计");

	}
	/**
	 * 查询日期
	 */
	private Date querydate;
	/**
	 * 产品类型
	 */
	private String prodtype;
	
	private List<String> prodtypeList;
	
	private List<String> prodIdList;
	
	private String assettype;
	private String assettypeTwo;
	private String assettypeThree;
	private String assettypeFour;
	private String assettypeFive;
	/**
	 * G06规模
	 */
	private BigDecimal scaleG06;
	/**
	 * 人行规模
	 */
	private BigDecimal scalePbc;
	/**
	 * 面值
	 */
	private BigDecimal value;
	/**
	 * 占比
	 */
	private BigDecimal ratio;
	/**
	 * 平均久期
	 */
	private BigDecimal avgDuration;
	/**
	 * 平均到期收益率
	 */
	private BigDecimal avgMyield;
	/**
	 * 平均估值收益率
	 */
	private BigDecimal avgGyield;
	/**
	 * 平均票面收益率
	 */
	private BigDecimal avgYield;
	/**
	 * 剩余0到一个月期限内规模
	 */
	private BigDecimal underOneM;
	/**
	 * 剩余一到三个月期限内规模
	 */
	private BigDecimal underThreeM;
	/**
	 * 剩余三到六个月期限内规模
	 */
	private BigDecimal underSixM;
	/**
	 * 剩余六个月到一年期限内规模
	 */
	private BigDecimal underOneY;
	/**
	 * 剩余一年到两年期限内规模
	 */
	private BigDecimal underTwoY;
	/**
	 * 剩余两年年到三年期限内规模
	 */
	private BigDecimal underThreeY;
	/**
	 * 大于三年
	 */
	private BigDecimal afterThreeY;
	/**
	 * 无固定期限
	 */
	private BigDecimal noFixedTerm;
	/**
	 * 资产代码
	 */
	private String assetcode;
	/**
	 * 份额
	 */
	private BigDecimal shareamt;
	
	private String deadline;
	
	public Date getQuerydate() {
		return querydate;
	}
	public void setQuerydate(Date querydate) {
		this.querydate = querydate;
	}
	public String getProdtype() {
		return prodtype;
	}
	public void setProdtype(String prodtype) {
		this.prodtype = prodtype;
	}
	public List<String> getProdtypeList() {
		return prodtypeList;
	}
	public void setProdtypeList(List<String> prodtypeList) {
		this.prodtypeList = prodtypeList;
	}
	public List<String> getProdIdList() {
		return prodIdList;
	}
	public void setProdIdList(List<String> prodIdList) {
		this.prodIdList = prodIdList;
	}
	public String getAssettype() {
		return assettype;
	}
	public String getAssettypeStr() {
		return assetTypeMap.get(this.getAssettype());
	}
	public void setAssettype(String assettype) {
		this.assettype = assettype;
	}
	public String getAssettypeTwo() {
		return assettypeTwo;
	}
	public String getAssettypeTwoStr() {
		return SysDatadictUtil.getNameFromDataDict(DictCode.行内资产二级分类, this.getAssettypeTwo());
	}
	public void setAssettypeTwo(String assettypeTwo) {
		this.assettypeTwo = assettypeTwo;
	}
	public String getAssettypeThree() {
		return assettypeThree;
	}
	public String getAssettypeThreeStr() {
		return SysDatadictUtil.getNameFromDataDict(DictCode.行内资产三级分类, this.getAssettypeThree());
	}
	public void setAssettypeThree(String assettypeThree) {
		this.assettypeThree = assettypeThree;
	}
	public String getAssettypeFour() {
		return assettypeFour;
	}
	public String getAssettypeFourStr() {
		return SysDatadictUtil.getNameFromDataDict(DictCode.行内资产四级分类, this.getAssettypeFour());
	}
	public void setAssettypeFour(String assettypeFour) {
		this.assettypeFour = assettypeFour;
	}
	public String getAssettypeFive() {
		return assettypeFive;
	}
	public String getAssettypeFiveStr() {
		return SysDatadictUtil.getNameFromDataDict(DictCode.行内资产五级分类, this.getAssettypeFive());
	}
	public void setAssettypeFive(String assettypeFive) {
		this.assettypeFive = assettypeFive;
	}
	public BigDecimal getScaleG06() {
		return scaleG06;
	}
	public void setScaleG06(BigDecimal scaleG06) {
		this.scaleG06 = scaleG06;
	}
	public BigDecimal getScalePbc() {
		return scalePbc;
	}
	public void setScalePbc(BigDecimal scalePbc) {
		this.scalePbc = scalePbc;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public BigDecimal getRatio() {
		return ratio;
	}
	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}
	public BigDecimal getAvgDuration() {
		return avgDuration;
	}
	public String getAvgDurationStr() {
		return avgDuration==null?"--":avgDuration.toPlainString();
	}
	public void setAvgDuration(BigDecimal avgDuration) {
		this.avgDuration = avgDuration;
	}
	public BigDecimal getAvgMyield() {
		return avgMyield;
	}
	public void setAvgMyield(BigDecimal avgMyield) {
		this.avgMyield = avgMyield;
	}
	public BigDecimal getAvgGyield() {
		return avgGyield;
	}
	public void setAvgGyield(BigDecimal avgGyield) {
		this.avgGyield = avgGyield;
	}
	public BigDecimal getAvgYield() {
		return avgYield;
	}
	public void setAvgYield(BigDecimal avgYield) {
		this.avgYield = avgYield;
	}
	public BigDecimal getUnderOneM() {
		return underOneM;
	}
	public void setUnderOneM(BigDecimal underOneM) {
		this.underOneM = underOneM;
	}
	public BigDecimal getUnderThreeM() {
		return underThreeM;
	}
	public void setUnderThreeM(BigDecimal underThreeM) {
		this.underThreeM = underThreeM;
	}
	public BigDecimal getUnderSixM() {
		return underSixM;
	}
	public void setUnderSixM(BigDecimal underSixM) {
		this.underSixM = underSixM;
	}
	public BigDecimal getUnderOneY() {
		return underOneY;
	}
	public void setUnderOneY(BigDecimal underOneY) {
		this.underOneY = underOneY;
	}
	public BigDecimal getUnderTwoY() {
		return underTwoY;
	}
	public void setUnderTwoY(BigDecimal underTwoY) {
		this.underTwoY = underTwoY;
	}
	public BigDecimal getUnderThreeY() {
		return underThreeY;
	}
	public void setUnderThreeY(BigDecimal underThreeY) {
		this.underThreeY = underThreeY;
	}
	public BigDecimal getAfterThreeY() {
		return afterThreeY;
	}
	public void setAfterThreeY(BigDecimal afterThreeY) {
		this.afterThreeY = afterThreeY;
	}
	public BigDecimal getNoFixedTerm() {
		return noFixedTerm;
	}
	public void setNoFixedTerm(BigDecimal noFixedTerm) {
		this.noFixedTerm = noFixedTerm;
	}
	public String getAssetcode() {
		return assetcode;
	}
	public void setAssetcode(String assetcode) {
		this.assetcode = assetcode;
	}
	public BigDecimal getShareamt() {
		return shareamt;
	}
	public void setShareamt(BigDecimal shareamt) {
		this.shareamt = shareamt;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	@Override
	public int compareTo(AssetSideDetialRepoDto arg0) {
		if(arg0.getAssettype()==null){
			return 1;
		}
		if(this.getAssettype()==null){
			return -1;
		}
		if(this.getAssettype().compareTo(arg0.getAssettype())==0){
			if(this.getAssettypeTwo().compareTo(arg0.getAssettypeTwo())==0){
				if(this.getAssettypeThree().compareTo(arg0.getAssettypeThree())==0){
					if(this.getAssettypeFour().compareTo(arg0.getAssettypeFour())==0){
						return this.getAssettypeFive().compareTo(arg0.getAssettypeFive());
					}else{
						return this.getAssettypeFour().compareTo(arg0.getAssettypeFour());
					}
				}else{
					return this.getAssettypeThree().compareTo(arg0.getAssettypeThree());
				}
			}else{
				return this.getAssettypeTwo().compareTo(arg0.getAssettypeTwo());
			}
		}else{
			return this.getAssettype().compareTo(arg0.getAssettype());
		}
	}
}
