package com.orm.obj;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.jixu.common.pojo.BasicEntity;

/** 
 * @ClassName 		Coaahdr
 * @author  		Pan Wei 
 * @Date 			2015年12月8日 下午4:08:03 	
 * @Description		[Coaa(科目),CoaaGroup(总账组),Coaahdr(会计科目表),总账栏四表在GlCoaaMap(总账栏科目匹配表)做匹配，决定了每笔金额匹配到哪个科目。投资组合选择会计科目表，证券(或者是直接在某些类型的交易界面，
 					例如换汇)选择总账组,总账组可以为每个总账栏配置科目，从而生成总账栏科目匹配表。每笔金额都能通过以上方式找到匹配的科目]  
 */

@Entity 
@Table(name="JXC_COAAHDR") 
public class Coaahdr extends BasicEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	// primary key
	private String id;

	// fields
	private String chartname;

	// collections
	private Set<Coaa> coaa;

	public Coaahdr() {
		// TODO Auto-generated constructor stub
	}
	
	public Coaahdr(String id) {
		this.id=id;
	}

	@Id
	@GeneratedValue(generator = "myID")
	@GenericGenerator(name = "myID", strategy = "com.jixu.common.func.GenCustomTBID_Joyin", parameters={
			@Parameter(name = "seq_name", value = "JXC_COA_ID_SEQ"),
			@Parameter(name = "short_name", value = "COA")})
	@Column(name="CHARTNUM")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="CHARTNAME")
	public String getChartname() {
		return chartname;
	}

	public void setChartname(String chartname) {
		this.chartname = chartname;
	}

	@OneToMany(mappedBy = "chartnum", cascade = CascadeType.REMOVE)
	public Set<Coaa> getCoaa() {
		return coaa;
	}

	public void setCoaa(Set<Coaa> coaa) {
		this.coaa = coaa;
	}

	
}
