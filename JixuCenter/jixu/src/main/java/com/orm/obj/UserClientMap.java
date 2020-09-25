package com.orm.obj;

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
 * @ClassName 		UserClientMap
 * @author  		Pan Wei 
 * @Date 			2017年1月3日 上午10:02:42 	
 * @Description		[用户和客户的关系表。匹配了某个用户可以操作哪几个客户，具体关系到投资组合，还需要关联用户-投资组合表]  
 */

@Entity 
@Table(name="JXS_UserClientMap") 
public class UserClientMap extends BasicEntity {
	private int id;
	private Users users;
	private PortfolioClient client;
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="UserClientMap_ID_SEQ", allocationSize=1, initialValue=25)
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
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ClientID")
	public PortfolioClient getClient() {
		return client;
	}
	public void setClient(PortfolioClient client) {
		this.client = client;
	}
}
