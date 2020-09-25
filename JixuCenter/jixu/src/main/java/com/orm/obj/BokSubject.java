package com.orm.obj;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jixu.common.pojo.BasicEntity;

/** 
 * @ClassName 		BkCashFlow
 * @author  		Pan Wei 
 * @Date 			2015年12月8日 下午4:08:03 	
 * @Description		[实收实付表，现金的结转都被记录在该表]  
 */

@Entity 
@Table(name="BOK_SUBJECT")
public class BokSubject extends BasicEntity implements Serializable {
	
	private static final long serialVersionUID = -6871576828290479367L;

	//复合主键
	private BokSubjectPK bokSubjectPK;

	//科目名称
	private String subject_name;
	
	//上级科目号
	private String fssubject_id;
	
	//余额方向
	private String bal_flag;
	
	//科目类型
	private String subject_type;
	
	//科目级别
	private int subject_level;
	
	//是否生成四级科目
	private String gen_detsub_flag;
	
	//基础科目性质
	private String base_subject_nature;
	
	//投资目的
	private String inv_aim;

	//证券管理户代码
	private String sec_manage_acct_id;
	
	@Id
	public BokSubjectPK getBokSubjectPK() {
		return bokSubjectPK;
	}

	public void setBokSubjectPK(BokSubjectPK bokSubjectPK) {
		this.bokSubjectPK = bokSubjectPK;
	}

	@Column(name="SUBJECT_NAME")
	public String getSubject_name() {
		return subject_name;
	}

	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}

	@Column(name="FSUBJECT_ID")
	public String getFssubject_id() {
		return fssubject_id;
	}

	public void setFssubject_id(String fssubject_id) {
		this.fssubject_id = fssubject_id;
	}

	@Column(name="BAL_FLAG")
	public String getBal_flag() {
		return bal_flag;
	}

	public void setBal_flag(String bal_flag) {
		this.bal_flag = bal_flag;
	}

	@Column(name="SUBJECT_TYPE")
	public String getSubject_type() {
		return subject_type;
	}

	public void setSubject_type(String subject_type) {
		this.subject_type = subject_type;
	}

	@Column(name="SUBJECT_LEVEL")
	public int getSubject_level() {
		return subject_level;
	}

	public void setSubject_level(int subject_level) {
		this.subject_level = subject_level;
	}

	@Column(name="GEN_DETSUB_FLAG")
	public String getGen_detsub_flag() {
		return gen_detsub_flag;
	}

	public void setGen_detsub_flag(String gen_detsub_flag) {
		this.gen_detsub_flag = gen_detsub_flag;
	}

	@Column(name="BASE_SUBJECT_NATURE")
	public String getBase_subject_nature() {
		return base_subject_nature;
	}

	public void setBase_subject_nature(String base_subject_nature) {
		this.base_subject_nature = base_subject_nature;
	}

	@Column(name="INV_AIM")
	public String getInv_aim() {
		return inv_aim;
	}

	public void setInv_aim(String inv_aim) {
		this.inv_aim = inv_aim;
	}

	@Column(name="SEC_MANAGE_ACCT_ID")
	public String getSec_manage_acct_id() {
		return sec_manage_acct_id;
	}

	public void setSec_manage_acct_id(String sec_manage_acct_id) {
		this.sec_manage_acct_id = sec_manage_acct_id;
	}
}
