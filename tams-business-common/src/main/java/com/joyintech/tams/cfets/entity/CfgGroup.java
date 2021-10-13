package com.joyintech.tams.cfets.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="配置分组",description="配置分组")
public class CfgGroup {

	@ApiModelProperty(required = true, name="gpcode",value="分组编码",example="DB001")
	private String gpcode;
	
	@ApiModelProperty(required = true, name="gpname",value="分组名称",example="分组1")
	private String gpname;
	
	@ApiModelProperty(required = false, name="gptype1",value="一级分类",example="")
	private String gptype1;
	
	@ApiModelProperty(required = false, name="gptype2",value="二级分类",example="")
	private String gptype2;
	
	@ApiModelProperty(required = true, name="gpdesc",value="参数值",example="")
	private String gpdesc;
	
	@ApiModelProperty(required = false, name="gpimage",value="图标",example="")
	private String gpimage;
	
	@ApiModelProperty(required = true, name="status",value="状态（0：不可用，1：可用）",example="0")
	private Integer status;
	
	@ApiModelProperty(required = false, name="mdtime",value="更新时间(yyyy-MM-dd HH:mm:ss)",example="")
	private String mdtime;
	
	@ApiModelProperty(required = false, name="mduser",value="更新人员",example="")
	private String mduser;
	
	@ApiModelProperty(required = false, name="remark1",value="备注1",example="")
	private Integer remark1;
		
	@ApiModelProperty(required = false, name="remark2",value="备注2",example="")
	private String remark2;
	
	@ApiModelProperty(required = false, name="remark3",value="备注3",example="")
	private String remark3;

	@ApiModelProperty(required = false, name="seqno",value="排序号",example="")
	private Integer seqno;

	public String getGpcode() {
		return gpcode;
	}

	public void setGpcode(String gpcode) {
		this.gpcode = gpcode;
	}

	public String getGpname() {
		return gpname;
	}

	public void setGpname(String gpname) {
		this.gpname = gpname;
	}

	public String getGpdesc() {
		return gpdesc;
	}

	public void setGpdesc(String gpdesc) {
		this.gpdesc = gpdesc;
	}

	public String getGpimage() {
		return gpimage;
	}

	public void setGpimage(String gpimage) {
		this.gpimage = gpimage;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMdtime() {
		return mdtime;
	}

	public void setMdtime(String mdtime) {
		this.mdtime = mdtime;
	}

	public String getMduser() {
		return mduser;
	}

	public void setMduser(String mduser) {
		this.mduser = mduser;
	}

	public Integer getRemark1() {
		return remark1;
	}

	public void setRemark1(Integer remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public Integer getSeqno() {
		return seqno;
	}

	public void setSeqno(Integer seqno) {
		this.seqno = seqno;
	}

	public String getGptype1() {
		return gptype1;
	}

	public void setGptype1(String gptype1) {
		this.gptype1 = gptype1;
	}

	public String getGptype2() {
		return gptype2;
	}

	public void setGptype2(String gptype2) {
		this.gptype2 = gptype2;
	}
}
