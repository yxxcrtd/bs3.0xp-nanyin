package com.orm.obj;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jixu.common.pojo.BasicEntity;
import com.jixu.common.pojo.Users;

/** 
 * @ClassName 		UserPortfolioMap
 * @author  		Pan Wei 
 * @Date 			2016年9月15日 下午11:07:52 	
 * @Description		[用户可以操作的投资组合]  
 */

@Entity 
@Table(name="JXS_UserPortfolioMap") 
public class UserPortfolioMap extends BasicEntity implements Serializable{

	private static final long serialVersionUID = -1342641513453094600L;
	private int id;
	private Users users;
	private String portfolio;
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="UserPortfolioMap_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="UserID")
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	
	@Column(name="Portfolio")
	public String getPortfolio() {
		return portfolio;
	}
	public void setPortfolio(String portfolio) {
		this.portfolio = portfolio;
	}
}
