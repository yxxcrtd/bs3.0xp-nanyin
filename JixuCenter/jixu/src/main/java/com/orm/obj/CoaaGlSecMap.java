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

@Entity 
@Table(name="JXC_COAAGLSECMAP") 
public class CoaaGlSecMap extends BasicEntity implements Serializable {

	private static final long serialVersionUID = -2365784994269629546L;

	private int id;
	
	private String assetType;
	
	private int rpSecType;
	
	private String purpose;
	
	private String longCur;
	
	private String shortCur;
	
	private int glgoup;

	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="JXC_COAAGLSECMAP_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="ASSETTYPE")
	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	@Column(name="RPSECTYPE")
	public int getRpSecType() {
		return rpSecType;
	}

	public void setRpSecType(int rpSecType) {
		this.rpSecType = rpSecType;
	}

	@Column(name="PURPOSE")
	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	@Column(name="LONGCUR")
	public String getLongCur() {
		return longCur;
	}

	public void setLongCur(String longCur) {
		this.longCur = longCur;
	}

	@Column(name="SHORTCUR")
	public String getShortCur() {
		return shortCur;
	}

	public void setShortCur(String shortCur) {
		this.shortCur = shortCur;
	}

	@Column(name="GLGROUP")
	public int getGlgoup() {
		return glgoup;
	}

	public void setGlgoup(int glgoup) {
		this.glgoup = glgoup;
	}
}
