package com.jixu.common.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

/** 
 * @ClassName 		Managers
 * @author  		Pan Wei 
 * @Date 			2016年9月8日 下午10:53:16 	
 * @Description		[消息日志,可以获取消息执行失败后的信息]  
 */

@Entity
@Table(name = "JXU_MsgLog", indexes={@Index(name="fx_portdate",columnList="LedgerDate,Portfolio")})
public class MsgLog extends BasicEntity {
	private long logid;
	private String msg;
	private String errorinfo;
	private int errorCode;
	private Date operationTime;
	private InterfaceSource source;;
	private boolean status;// 0--失败    1--成功   2--未执行
	private int operationType;
	private String portfolio;
	private Date ledgerDate;
	private boolean ingore;
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="MsgLog_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="LogID")
	public long getLogid() {
		return logid;
	}
	public void setLogid(long logid) {
		this.logid = logid;
	}
	
	@Column(name="msg", length=4000)
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = StringUtils.left(msg, 2500);
	}
	
	@Column(name="errorinfo", length=4000)
	public String getErrorinfo() {
		return errorinfo;
	}
	public void setErrorinfo(String errorinfo) {
		this.errorinfo = StringUtils.left(errorinfo, 2500);
	}
	
	@Column(name="operationTime")
	public Date getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sourceid")
	public InterfaceSource getSource() {
		return source;
	}
	
	public void setSource(InterfaceSource source) {
		this.source = source;
	}
	
	@Column(name="status",columnDefinition="integer",length=1)
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@Column(name="errorCode")
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	@Column(name="operationType")
	public int getOperationType() {
		return operationType;
	}
	public void setOperationType(int operationType) {
		this.operationType = operationType;
	}

	@Column(name="portfolio")
	public String getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(String portfolio) {
		this.portfolio = portfolio;
	}
	

	@Column(name="ledgerDate")
	public Date getLedgerDate() {
		return ledgerDate;
	}
	
	public void setLedgerDate(Date ledgerDate) {
		this.ledgerDate = ledgerDate;
	}
	
	@Column(name="ingore",columnDefinition="integer",length=1)
	public boolean isIngore() {
		return ingore;
	}
	public void setIngore(boolean ingore) {
		this.ingore = ingore;
	}
}
