package com.orm.obj;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
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
@Table(name="JXC_COAA", 
	indexes={@Index(name="fx_acctisleaf",columnList="Chartnumber,Accntnum,Acctisleaf")})
public class Coaa extends BasicEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;

	private int accntcategory;
	private String accntnum;
	private String accntname;
	private int accntsubcategory;
	private String closetoaccntnum;
	private int acctLevel;
	private int acctisleaf;

	private String chartnum;
	
	//private String gen_detsub_falg;	//是否生成四级科目
	

	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="Coaa_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="ACCNTCATEGORY")
	public int getAccntcategory() {
		return accntcategory;
	}

	public void setAccntcategory(int accntcategory) {
		this.accntcategory = accntcategory;
	}

	@Column(name="ACCNTNAME")
	public String getAccntname() {
		return accntname;
	}

	public void setAccntname(String accntname) {
		this.accntname = accntname;
	}

	@Column(name="ACCNTNUM")
	public String getAccntnum() {
		return accntnum;
	}

	public void setAccntnum(String accntnum) {
		this.accntnum = accntnum;
	}

	@Column(name="ACCNTSUBCATEGORY")
	public int getAccntsubcategory() {
		return accntsubcategory;
	}

	public void setAccntsubcategory(int accntsubcategory) {
		this.accntsubcategory = accntsubcategory;
	}
	
	@Column(name="ACCTLEVEL")
	public int getAcctLevel() {
		return acctLevel;
	}

	public void setAcctLevel(int acctLevel) {
		this.acctLevel = acctLevel;
	}

	@Column(name="CLOSETOACCNTNUM")
	public String getClosetoaccntnum() {
		return closetoaccntnum;
	}

	public void setClosetoaccntnum(String closetoaccntnum) {
		this.closetoaccntnum = closetoaccntnum;
	}

	@Column(name="ACCTISLEAF")
	public int getAcctisleaf() {
		return acctisleaf;
	}

	public void setAcctisleaf(int acctisleaf) {
		this.acctisleaf = acctisleaf;
	}
	@Column(name="CHARTNUMBER")
	public String getChartnum() {
		return chartnum;
	}

	public void setChartnum(String chartnum) {
		this.chartnum = chartnum;
	}
}
