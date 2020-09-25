/**
 * 
 */
package com.jixu.journal.obj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.orm.obj.Voucher;
import com.orm.obj.VoucherDetails;

public class GLCOOABase implements Serializable {

	public GLCOOABase() {
		this.setVoucherDetails(new ArrayList<VoucherDetails>());
		this.setOffVoucherDetails(new ArrayList<VoucherDetails>());
		this.setBusiId("");
	}
	
	private static final long serialVersionUID = -9062153731588887720L;
	
	/**
	 * 会计事件场景编码
	 */
	private String tableID; 
	
	/**
	 * 资产类型
	 */
	private String busiDetailType="F10";
	
	/**
	 * 业务明细代码
	 */
	private String busiId;
	
	/**
	 * 表外账会计事件场景编码
	 */
	private String offtableID; 
	
	/**
	 * 收入/支出
	 * Long/Short
	 */
	private int longshort;

	private Voucher voucher;
	
	private List<VoucherDetails> voucherDetails;
	
	private List<VoucherDetails> offVoucherDetails;
	
	private boolean removeZeroFlag=true;
	
	/**
	 * 是否为估增反冲凭证
	 */
	private String offVouch;

	public Voucher getVoucher() {
		return voucher;
	}

	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}

	public List<VoucherDetails> getVoucherDetails() {
		return voucherDetails;
	}

	public void setVoucherDetails(List<VoucherDetails> voucherDetails) {
		this.voucherDetails = voucherDetails;
	}

	/**
	 * 获取 removeZeroFlag
	 */
	
	public boolean isRemoveZeroFlag() {
		return removeZeroFlag;
	}

	/**
	 * 设置 removeZeroFlag
	 */
	
	public void setRemoveZeroFlag(boolean removeZeroFlag) {
		this.removeZeroFlag = removeZeroFlag;
	}

	public String getTableID() {
		return tableID;
	}

	public void setTableID(String tableID) {
		this.tableID = tableID;
	}

	public List<VoucherDetails> getOffVoucherDetails() {
		return offVoucherDetails;
	}

	public void setOffVoucherDetails(List<VoucherDetails> offVoucherDetails) {
		this.offVoucherDetails = offVoucherDetails;
	}

	public String getOfftableID() {
		return offtableID;
	}

	public void setOfftableID(String offtableID) {
		this.offtableID = offtableID;
	}

	public String getBusiDetailType() {
		return busiDetailType;
	}

	public void setBusiDetailType(String busiDetailType) {
		this.busiDetailType = busiDetailType;
	}

	public String getBusiId() {
		return busiId;
	}

	public void setBusiId(String busiId) {
		this.busiId = busiId;
	}

	public int getLongshort() {
		return longshort;
	}

	public void setLongshort(int longshort) {
		this.longshort = longshort;
	}

	public String getOffVouch() {
		return offVouch;
	}

	public void setOffVouch(String offVouch) {
		this.offVouch = offVouch;
	}
}
