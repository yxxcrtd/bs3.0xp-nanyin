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
 * @ClassName 		RoleType
 * @author  		Pan Wei 
 * @Date 			2016年12月31日 下午11:45:11 	
 * @Description		[该表包括了证券类型和证券行业的内容和层级关系。通过fathernode字段找到父级字段的方式，运用迭代来确认层级关系]  
 */

@Entity 
@Table(name="JXS_RPSecType") 
public class RPSecType extends BasicEntity implements Serializable{

	private static final long serialVersionUID = -5379250141694275984L;
	private int id;
	private int tablecode;
	private int grouplevel;
	private String typename;
	private int fathernode;
	private int status;
	private Set<IssueInfo> issueinfo;
	private Set<Issuer> issuer;
	
	public RPSecType(int id) {
		this.setId(id);
	}
	
	public RPSecType() {
	}
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="RPSecType_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="tablecode")
	public int getTablecode() {
		return tablecode;
	}
	public void setTablecode(int tablecode) {
		this.tablecode = tablecode;
	}
	
	@Column(name="grouplevel")
	public int getGrouplevel() {
		return grouplevel;
	}
	
	public void setGrouplevel(int grouplevel) {
		this.grouplevel = grouplevel;
	}
	
	@Column(name="typename")
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	
	@Column(name="fathernode")
	public int getFathernode() {
		return fathernode;
	}
	public void setFathernode(int fathernode) {
		this.fathernode = fathernode;
	}
	
	@Column(name="status")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@OneToMany(mappedBy = "rpSecType", cascade = CascadeType.REMOVE)
	public Set<IssueInfo> getIssueinfo() {
		return issueinfo;
	}
	public void setIssueinfo(Set<IssueInfo> issueinfo) {
		this.issueinfo = issueinfo;
	}
	
	@OneToMany(mappedBy = "rpSecType", cascade = CascadeType.REMOVE)
	public Set<Issuer> getIssuer() {
		return issuer;
	}
	public void setIssuer(Set<Issuer> issuer) {
		this.issuer = issuer;
	}
}
