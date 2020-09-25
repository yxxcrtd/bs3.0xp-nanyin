package com.joyintech.tams.batch.disclosure.informationdisclourse.infodiscautoreveal.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.RepFtpDetailMsg;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.ftpDetail.dto.FtpDetailDto;

public class InfoDiscAutoRevealResult extends NeedToWriteEntity {
	private FtpDetailDto ftpDetailDto;
	
	private List<RepFtpDetailMsg> repFtpDetailMsgsList;

	/**
    * ID
    */
   private String id;

   /**
    * 报表编号
    */
   private String repId;

   /**
    * 鎶ヨ〃鍚嶇О
    */
   private String reportName;

   /**
    * 鎶ヨ〃绫诲埆
    */
   private String reportCategory;

   /**
    * 报表名称
    */
   private String prodName;

   /**
    * 浜у搧缂栧彿
    */
   private String prodid;

   /**
    * 缁撴潫鏃�
    */
   private Date endDate;

   /**
    * 寮�濮嬫棩
    */
   private Date startDate;

   /**
    * 鐢熸垚鏂囦欢鍚嶇О
    */
   private String sysFilename;

   /**
    * 鐢熸垚鏂囦欢璺緞
    */
   private String sysFilepath;

   /**
    * 鐢熸垚鏂囦欢鏃堕棿
    */
   private Date sysFilenameDate;

   /**
    * 浜哄伐鐢熸垚璺緞
    */
   private String filepath;

   /**
    * 浜哄伐涓婁紶鏃堕棿
    */
   private Date time;

   /**
    * 浜哄伐鏂囦欢鍚嶇О
    */
   private String filename;

   /**
    * 闄勪欢鍚嶇О
    */
   private String annex;

   /**
    * 闄勪欢璺緞
    */
   private String annexPath;

   /**
    * 纭鐘舵��
    */
   private String confirmStatus;

   /**
    * 纭浜�
    */
   private String confirmUser;

   /**
    * 纭鏃堕棿
    */
   private Date confirmDate;

   /**
    * 鎶湶鐘舵��
    */
   private String revealStatus;

   /**
    * 鎶湶鏃堕棿
    */
   private Date revealDate;

   /**
    * 鎶湶鎴鏃�
    */
   private Date revealEndDate;

   /**
    * 鎶湶浜�
    */
   private String revealUser;

   /**
    * 鏄惁鑷姩鎶湶
    */
   private String isReveal;

   /**
    * 鏄惁鑷姩纭
    */
   private String isConfirm;

   /**
    * 涓婁紶璇存槑
    */
   private String detail;

   /**
    * 鎶湶娓犻亾
    */
   private String revealChannel;
   
   
   

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRepId() {
		return repId;
	}

	public void setRepId(String repId) {
		this.repId = repId;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getReportCategory() {
		return reportCategory;
	}

	public void setReportCategory(String reportCategory) {
		this.reportCategory = reportCategory;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdid() {
		return prodid;
	}

	public void setProdid(String prodid) {
		this.prodid = prodid;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getSysFilename() {
		return sysFilename;
	}

	public void setSysFilename(String sysFilename) {
		this.sysFilename = sysFilename;
	}

	public String getSysFilepath() {
		return sysFilepath;
	}

	public void setSysFilepath(String sysFilepath) {
		this.sysFilepath = sysFilepath;
	}

	public Date getSysFilenameDate() {
		return sysFilenameDate;
	}

	public void setSysFilenameDate(Date sysFilenameDate) {
		this.sysFilenameDate = sysFilenameDate;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getAnnex() {
		return annex;
	}

	public void setAnnex(String annex) {
		this.annex = annex;
	}

	public String getAnnexPath() {
		return annexPath;
	}

	public void setAnnexPath(String annexPath) {
		this.annexPath = annexPath;
	}

	public String getConfirmStatus() {
		return confirmStatus;
	}

	public void setConfirmStatus(String confirmStatus) {
		this.confirmStatus = confirmStatus;
	}

	public String getConfirmUser() {
		return confirmUser;
	}

	public void setConfirmUser(String confirmUser) {
		this.confirmUser = confirmUser;
	}

	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	public String getRevealStatus() {
		return revealStatus;
	}

	public void setRevealStatus(String revealStatus) {
		this.revealStatus = revealStatus;
	}

	public Date getRevealDate() {
		return revealDate;
	}

	public void setRevealDate(Date revealDate) {
		this.revealDate = revealDate;
	}

	public Date getRevealEndDate() {
		return revealEndDate;
	}

	public void setRevealEndDate(Date revealEndDate) {
		this.revealEndDate = revealEndDate;
	}

	public String getRevealUser() {
		return revealUser;
	}

	public void setRevealUser(String revealUser) {
		this.revealUser = revealUser;
	}

	public String getIsReveal() {
		return isReveal;
	}

	public void setIsReveal(String isReveal) {
		this.isReveal = isReveal;
	}

	public String getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(String isConfirm) {
		this.isConfirm = isConfirm;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getRevealChannel() {
		return revealChannel;
	}

	public void setRevealChannel(String revealChannel) {
		this.revealChannel = revealChannel;
	}
	
	public FtpDetailDto getFtpDetailDto() {
		return ftpDetailDto;
	}

	public void setFtpDetailDto(FtpDetailDto ftpDetailDto) {
		this.ftpDetailDto = ftpDetailDto;
	}

	public List<RepFtpDetailMsg> getRepFtpDetailMsgsList() {
		return repFtpDetailMsgsList;
	}

	public void setRepFtpDetailMsgsList(List<RepFtpDetailMsg> repFtpDetailMsgsList) {
		this.repFtpDetailMsgsList = repFtpDetailMsgsList;
	}
}
