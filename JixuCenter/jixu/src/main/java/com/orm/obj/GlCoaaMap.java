package com.orm.obj;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jixu.common.pojo.BasicEntity;

/** 
 * @ClassName 		Coaa
 * @author  		Pan Wei 
 * @Date 			2015年12月8日 下午4:08:03 	
 * @Description		[Coaa(科目),CoaaGroup(总账组),Coaahdr(会计科目表),总账栏四表在GlCoaaMap(总账栏科目匹配表)做匹配，决定了每笔金额匹配到哪个科目。投资组合选择会计科目表，证券(或者是直接在某些类型的交易界面，
 					例如换汇)选择总账组,总账组可以为每个总账栏配置科目，从而生成总账栏科目匹配表。每笔金额都能通过以上方式找到匹配的科目]  
 */


@Entity 
@Table(name="JXC_GL_COAA_MAP") 
public class GlCoaaMap extends BasicEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// fields
	private int id;
	private String chartNum;
	private int groupNum;
	private int columnNum;
	private String acctNum;
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="GlCoaaMap_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="CHARTNUM")
	public String getChartNum() {
		return chartNum;
	}
	public void setChartNum(String chartNum) {
		this.chartNum = chartNum;
	}
	
	@Column(name="GROUPNUM")
	public int getGroupNum() {
		return groupNum;
	}
	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}
	
	@Column(name="COLUMNNUM")
	public int getColumnNum() {
		return columnNum;
	}
	public void setColumnNum(int columnNum) {
		this.columnNum = columnNum;
	}
	
	@Column(name="ACCT_NUM")
	public String getAcctNum() {
		return acctNum;
	}
	public void setAcctNum(String acctNum) {
		this.acctNum = acctNum;
	}
	
	
}
