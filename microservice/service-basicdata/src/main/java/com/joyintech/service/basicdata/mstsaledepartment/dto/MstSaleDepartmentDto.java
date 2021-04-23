/*
 * 文件名：MstCaptialAccountDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年8月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.mstsaledepartment.dto;

import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
/**
 * 
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author fuxj
 * @version 1.0 2020年3月18日
 * @see MstSaleDepartmentDto
 * @since 1.0
 */
public class MstSaleDepartmentDto extends BaseEntity implements ITableRow{
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /*营业部代码*/private String saledepid;
    /*营业部名称*/private String saledepname;
    /*所属券商*/private String brokerid;
    /*所属券商名称*/private String brokername;
    /*IP地址*/private String ipaddr;
    /*端口*/private String portnum;
    /*联系地址*/private String contactaddr;
    /*联系电话*/private String contacttel;
    /*传真号码*/private String faxno;
    /*备用IP地址*/private String ipaddr2;
    /*备用端口*/private String portnum2;
    /*申报单位*/private String declareunit;
    /*是否净价回报*/private String isnetreturn;
    /*特殊处理业务*/private String handletype;
    /*柜台接口类型*/private String intertype;
    /*上交所席位号*/private String shseatno;
    /*深交所席位号*/private String szseatno;
    /*柜台参数*/private String serverinfo;
    /*多条记录列表*/private transient List<MstSaleDepartmentDto> selectedItem;
    /*中金所席位号*/private String zjseatno;
    /*上期所席位号*/private String sqseatno;
    
    
	public String getSaledepid() {
		return saledepid;
	}

	public void setSaledepid(String saledepid) {
		this.saledepid = saledepid;
	}

	public String getSaledepname() {
		return saledepname;
	}

	public void setSaledepname(String saledepname) {
		this.saledepname = saledepname;
	}

	public String getBrokerid() {
		return brokerid;
	}

	public void setBrokerid(String brokerid) {
		this.brokerid = brokerid;
	}

	public String getIpaddr() {
		return ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	public String getPortnum() {
		return portnum;
	}

	public void setPortnum(String portnum) {
		this.portnum = portnum;
	}

	public String getContactaddr() {
		return contactaddr;
	}

	public void setContactaddr(String contactaddr) {
		this.contactaddr = contactaddr;
	}

	public String getContacttel() {
		return contacttel;
	}

	public void setContacttel(String contacttel) {
		this.contacttel = contacttel;
	}

	public String getFaxno() {
		return faxno;
	}

	public void setFaxno(String faxno) {
		this.faxno = faxno;
	}

	public String getIpaddr2() {
		return ipaddr2;
	}

	public void setIpaddr2(String ipaddr2) {
		this.ipaddr2 = ipaddr2;
	}

	public String getPortnum2() {
		return portnum2;
	}

	public void setPortnum2(String portnum2) {
		this.portnum2 = portnum2;
	}

	public String getDeclareunit() {
		return declareunit;
	}

	public void setDeclareunit(String declareunit) {
		this.declareunit = declareunit;
	}

	public String getIsnetreturn() {
		return isnetreturn;
	}

	public void setIsnetreturn(String isnetreturn) {
		this.isnetreturn = isnetreturn;
	}

	public String getHandletype() {
		return handletype;
	}

	public void setHandletype(String handletype) {
		this.handletype = handletype;
	}

	public String getIntertype() {
		return intertype;
	}

	public void setIntertype(String intertype) {
		this.intertype = intertype;
	}

	public String getShseatno() {
		return shseatno;
	}

	public void setShseatno(String shseatno) {
		this.shseatno = shseatno;
	}

	public String getSzseatno() {
		return szseatno;
	}

	public void setSzseatno(String szseatno) {
		this.szseatno = szseatno;
	}

	public String getServerinfo() {
		return serverinfo;
	}

	public void setServerInfo(String serverinfo) {
		this.serverinfo = serverinfo;
	}

	public List<MstSaleDepartmentDto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<MstSaleDepartmentDto> selectedItem) {
		this.selectedItem = selectedItem;
	}

	public String getBrokername() {
		return brokername;
	}

	public void setBrokername(String brokername) {
		this.brokername = brokername;
	}

	public String getZjseatno() {
		return zjseatno;
	}

	public void setZjseatno(String zjseatno) {
		this.zjseatno = zjseatno;
	}

	public String getSqseatno() {
		return sqseatno;
	}

	public void setSqseatno(String sqseatno) {
		this.sqseatno = sqseatno;
	}
}
