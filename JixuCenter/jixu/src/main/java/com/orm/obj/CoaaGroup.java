package com.orm.obj;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jixu.common.pojo.BasicEntity;

/** 
 * @ClassName 		CoaaGroup
 * @author  		Pan Wei 
 * @Date 			2015年12月8日 下午4:08:03 	
 * @Description		[Coaa(科目),CoaaGroup(总账组),Coaahdr(会计科目表),总账栏四表在GlCoaaMap(总账栏科目匹配表)做匹配，决定了每笔金额匹配到哪个科目。投资组合选择会计科目表，证券(或者是直接在某些类型的交易界面，
 					例如换汇)选择总账组,总账组可以为每个总账栏配置科目，从而生成总账栏科目匹配表。每笔金额都能通过以上方式找到匹配的科目]  
 */

@Entity 
@Table(name="JXC_CoaaGroup") 
public class CoaaGroup extends BasicEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private int groupNum;
	private String groupName;
	
	private Set<PortfolioHdr> portfolio;
	
	public CoaaGroup() {
		// TODO Auto-generated constructor stub
	}
	
	public CoaaGroup(int groupNum) {
		this.groupNum=groupNum;
	}
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="CoaaGroup_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="groupNum")
	public int getGroupNum() {
		return groupNum;
	}
	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}
	
	@Column(name="groupName")
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	@OneToMany(mappedBy = "coaaGroup", cascade = CascadeType.REMOVE)
	public Set<PortfolioHdr> getPortfolio() {
		return portfolio;
	}
	public void setPortfolio(Set<PortfolioHdr> portfolio) {
		this.portfolio = portfolio;
	}
}
