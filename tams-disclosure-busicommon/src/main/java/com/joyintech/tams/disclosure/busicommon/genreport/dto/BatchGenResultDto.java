/**
 * 
 */
package com.joyintech.tams.disclosure.busicommon.genreport.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述批量生成报表结果信息
 * @author Administrator
 *
 */
public class BatchGenResultDto {
	private String repId;     //报表规则id
	private String repName;  //报表规则名称
	private String prodId;  //产品id
	private String prodName; //产品名称
	private Date genDate;  //生成日期
	private String result;  //生成结果:S，生成成功，F：生成失败
	private String remark;  //结果摘要，如生成失败原因
	public String getRepId() {
		return repId;
	}
	public void setRepId(String repId) {
		this.repId = repId;
	}
	public String getRepName() {
		return repName;
	}
	public void setRepName(String repName) {
		this.repName = repName;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public Date getGenDate() {
		return genDate;
	}
	public void setGenDate(Date genDate) {
		this.genDate = genDate;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer sb = new StringBuffer();
//		sb.append("★★");
//		sb.append(repName+"生成：");
		sb.append("【产品："+prodName+"("+prodId+")】，");
		sb.append("S".equals(result)?"生成成功，":"生成失败，");
		sb.append(sdf.format(genDate)+"；");
		sb.append("<br>");
		if ( remark!=null && remark.length()>0 )
			sb.append(remark+"，");
		return sb.toString();
	}
	
}
