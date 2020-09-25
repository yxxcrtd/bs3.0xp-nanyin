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
 * @ClassName 		ExchangeCfg
 * @author  		Pan Wei 
 * @Date 			2015年12月8日 下午4:08:03 	
 * @Description		[证券交易所]  
 */

@Entity 
@Table(name="JXM_EXCHANGECFG") 
public class ExchangeCfg extends BasicEntity implements Serializable{

	private static final long serialVersionUID = -3122479452116510163L;

	private String code;
	
	private String name;
	
	private Set<IssueInfo> issueinfo;

	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="ExchangeCfg_ID_SEQ", allocationSize=1, initialValue=261)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "exchangeCode", cascade = CascadeType.REMOVE)
	public Set<IssueInfo> getIssueinfo() {
		return issueinfo;
	}

	public void setIssueinfo(Set<IssueInfo> issueinfo) {
		this.issueinfo = issueinfo;
	}
}
