package com.orm.obj;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jixu.common.pojo.BasicEntity;

/** 
 * @ClassName 		SecurityIDType
 * @author  		Pan Wei 
 * @Date 			2017年2月8日 下午5:20:47 	
 * @Description		[尚未使用该表]  
 */

@Entity 
@Table(name="JXS_SecurityIDType") 
public class SecurityIDType extends BasicEntity {
	private int id;
	private String secIDTypeName;
	private String idDesc;
	private int fieldLength;
	private boolean duplicateFlag;
	private boolean allowSpaceFlag;
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="SecurityIDType_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="secIDTypeName")
	public String getSecIDTypeName() {
		return secIDTypeName;
	}
	public void setSecIDTypeName(String secIDTypeName) {
		this.secIDTypeName = secIDTypeName;
	}
	
	@Column(name="idDesc")
	public String getIdDesc() {
		return idDesc;
	}
	public void setIdDesc(String idDesc) {
		this.idDesc = idDesc;
	}
	@Column(name="fieldLength")
	public int getFieldLength() {
		return fieldLength;
	}
	public void setFieldLength(int fieldLength) {
		this.fieldLength = fieldLength;
	}
	
	@Column(name="duplicateFlag",columnDefinition="integer",length=1)
	public boolean isDuplicateFlag() {
		return duplicateFlag;
	}
	public void setDuplicateFlag(boolean duplicateFlag) {
		this.duplicateFlag = duplicateFlag;
	}
	
	@Column(name="allowSpaceFlag",columnDefinition="integer",length=1)
	public boolean isAllowSpaceFlag() {
		return allowSpaceFlag;
	}
	public void setAllowSpaceFlag(boolean allowSpaceFlag) {
		this.allowSpaceFlag = allowSpaceFlag;
	}
}
