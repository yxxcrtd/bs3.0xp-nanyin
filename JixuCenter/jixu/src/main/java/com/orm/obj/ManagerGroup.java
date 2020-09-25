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
 * @ClassName 		ManagerGroup
 * @author  		Pan Wei 
 * @Date 			2016年9月8日 下午10:53:04 	
 * @Description		[经理组表,下面是经理表]  
 */

@Entity 
@Table(name="JXS_ManagerGroup") 
public class ManagerGroup extends BasicEntity implements Serializable{

	private static final long serialVersionUID = 2669995631395230820L;
	private int id;
	private String groupname;
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="ManagerGroup_ID_SEQ", allocationSize=1, initialValue=6)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="groupname")
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
}