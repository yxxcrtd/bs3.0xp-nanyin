package com.joyintech.interf.bankname.cfetsup.job.dto;

/*import com.sun.beans.decoder.ValueObject;
*/
import java.io.Serializable;


/**
 * 外汇债券信息表VO
 * @author wot_weipingwen_zy
 *
 */
public class FxImixBondinfoDto  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3688729650424226576L;
	/** 流水号 */
	private String seqno;
	/**债券代码*/
	private String  secid;
	/**是否特殊债 */
	private String  specialdebt;
	/**创建人 */
	private String  creatorid;
	/**创建日期 */
	private java.util.Date  createdtime;
	/**更新人 */
	private String  lastmodifierid;
	/**更新日期 */
	private java.util.Date   lastmodifiedtime;
	public String getSeqno() {
		return seqno;
	}
	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}
	public String getSecid() {
		return secid;
	}
	public void setSecid(String secid) {
		this.secid = secid;
	}
	public String getSpecialdebt() {
		return specialdebt;
	}
	public void setSpecialdebt(String specialdebt) {
		this.specialdebt = specialdebt;
	}
	public String getCreatorid() {
		return creatorid;
	}
	public void setCreatorid(String creatorid) {
		this.creatorid = creatorid;
	}
	
	public String getLastmodifierid() {
		return lastmodifierid;
	}
	public void setLastmodifierid(String lastmodifierid) {
		this.lastmodifierid = lastmodifierid;
	}
	public java.util.Date getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(java.util.Date createdtime) {
		this.createdtime = createdtime;
	}
	public java.util.Date getLastmodifiedtime() {
		return lastmodifiedtime;
	}
	public void setLastmodifiedtime(java.util.Date lastmodifiedtime) {
		this.lastmodifiedtime = lastmodifiedtime;
	}
	
	

}
