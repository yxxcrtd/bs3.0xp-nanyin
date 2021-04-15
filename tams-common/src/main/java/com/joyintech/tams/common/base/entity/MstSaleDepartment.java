package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class MstSaleDepartment extends BaseEntity {
    /**
     * 营业部代码
     */
    private String saleDepid;

    /**
     * 营业部名称
     */
    private String saleDepname;

    /**
     * 所属券商
     */
    private String brokerId;

    /**
     * IP地址
     */
    private String ipAddr;

    /**
     * 端口
     */
    private String portNum;

    /**
     * 联系地址
     */
    private String contactAddr;

    /**
     * 联系电话
     */
    private String contactTel;

    /**
     * 传真号码
     */
    private String faxNo;

    /**
     * 备用IP地址
     */
    private String ipAddr2;

    /**
     * 备用端口
     */
    private String portNum2;

    /**
     * 申报单位
     */
    private String declareUnit;

    /**
     * 是否净价回报
     */
    private String isNetreturn;

    /**
     * 特殊处理业务
     */
    private String handleType;

    /**
     * 柜台接口类型
     */
    private String interType;

    /**
     * 上交所席位号
     */
    private String shSeatno;

    /**
     * 深交所席位号
     */
    private String szSeatno;

    /**
     * 柜台参数，柜台系统连接串
     */
    private String serverinfo;

    /**
     * 中金所席位号
     */
    private String zjSeatno;

    /**
     * 上期所席位号
     */
    private String sqSeatno;

    /**
     * 取得 营业部代码
     * @return 营业部代码
     */
    public String getSaleDepid() {
        return saleDepid;
    }

    /**
     * 设置 营业部代码
     * @param saleDepid 营业部代码
     */
    public void setSaleDepid(String saleDepid) {
        this.saleDepid = saleDepid == null ? null : saleDepid.trim();
    }

    /**
     * 取得 营业部名称
     * @return 营业部名称
     */
    public String getSaleDepname() {
        return saleDepname;
    }

    /**
     * 设置 营业部名称
     * @param saleDepname 营业部名称
     */
    public void setSaleDepname(String saleDepname) {
        this.saleDepname = saleDepname == null ? null : saleDepname.trim();
    }

    /**
     * 取得 所属券商
     * @return 所属券商
     */
    public String getBrokerId() {
        return brokerId;
    }

    /**
     * 设置 所属券商
     * @param brokerId 所属券商
     */
    public void setBrokerId(String brokerId) {
        this.brokerId = brokerId == null ? null : brokerId.trim();
    }

    /**
     * 取得 IP地址
     * @return IP地址
     */
    public String getIpAddr() {
        return ipAddr;
    }

    /**
     * 设置 IP地址
     * @param ipAddr IP地址
     */
    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr == null ? null : ipAddr.trim();
    }

    /**
     * 取得 端口
     * @return 端口
     */
    public String getPortNum() {
        return portNum;
    }

    /**
     * 设置 端口
     * @param portNum 端口
     */
    public void setPortNum(String portNum) {
        this.portNum = portNum == null ? null : portNum.trim();
    }

    /**
     * 取得 联系地址
     * @return 联系地址
     */
    public String getContactAddr() {
        return contactAddr;
    }

    /**
     * 设置 联系地址
     * @param contactAddr 联系地址
     */
    public void setContactAddr(String contactAddr) {
        this.contactAddr = contactAddr == null ? null : contactAddr.trim();
    }

    /**
     * 取得 联系电话
     * @return 联系电话
     */
    public String getContactTel() {
        return contactTel;
    }

    /**
     * 设置 联系电话
     * @param contactTel 联系电话
     */
    public void setContactTel(String contactTel) {
        this.contactTel = contactTel == null ? null : contactTel.trim();
    }

    /**
     * 取得 传真号码
     * @return 传真号码
     */
    public String getFaxNo() {
        return faxNo;
    }

    /**
     * 设置 传真号码
     * @param faxNo 传真号码
     */
    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo == null ? null : faxNo.trim();
    }

    /**
     * 取得 备用IP地址
     * @return 备用IP地址
     */
    public String getIpAddr2() {
        return ipAddr2;
    }

    /**
     * 设置 备用IP地址
     * @param ipAddr2 备用IP地址
     */
    public void setIpAddr2(String ipAddr2) {
        this.ipAddr2 = ipAddr2 == null ? null : ipAddr2.trim();
    }

    /**
     * 取得 备用端口
     * @return 备用端口
     */
    public String getPortNum2() {
        return portNum2;
    }

    /**
     * 设置 备用端口
     * @param portNum2 备用端口
     */
    public void setPortNum2(String portNum2) {
        this.portNum2 = portNum2 == null ? null : portNum2.trim();
    }

    /**
     * 取得 申报单位
     * @return 申报单位
     */
    public String getDeclareUnit() {
        return declareUnit;
    }

    /**
     * 设置 申报单位
     * @param declareUnit 申报单位
     */
    public void setDeclareUnit(String declareUnit) {
        this.declareUnit = declareUnit == null ? null : declareUnit.trim();
    }

    /**
     * 取得 是否净价回报
     * @return 是否净价回报
     */
    public String getIsNetreturn() {
        return isNetreturn;
    }

    /**
     * 设置 是否净价回报
     * @param isNetreturn 是否净价回报
     */
    public void setIsNetreturn(String isNetreturn) {
        this.isNetreturn = isNetreturn == null ? null : isNetreturn.trim();
    }

    /**
     * 取得 特殊处理业务
     * @return 特殊处理业务
     */
    public String getHandleType() {
        return handleType;
    }

    /**
     * 设置 特殊处理业务
     * @param handleType 特殊处理业务
     */
    public void setHandleType(String handleType) {
        this.handleType = handleType == null ? null : handleType.trim();
    }

    /**
     * 取得 柜台接口类型
     * @return 柜台接口类型
     */
    public String getInterType() {
        return interType;
    }

    /**
     * 设置 柜台接口类型
     * @param interType 柜台接口类型
     */
    public void setInterType(String interType) {
        this.interType = interType == null ? null : interType.trim();
    }

    /**
     * 取得 上交所席位号
     * @return 上交所席位号
     */
    public String getShSeatno() {
        return shSeatno;
    }

    /**
     * 设置 上交所席位号
     * @param shSeatno 上交所席位号
     */
    public void setShSeatno(String shSeatno) {
        this.shSeatno = shSeatno == null ? null : shSeatno.trim();
    }

    /**
     * 取得 深交所席位号
     * @return 深交所席位号
     */
    public String getSzSeatno() {
        return szSeatno;
    }

    /**
     * 设置 深交所席位号
     * @param szSeatno 深交所席位号
     */
    public void setSzSeatno(String szSeatno) {
        this.szSeatno = szSeatno == null ? null : szSeatno.trim();
    }

    /**
     * 取得 柜台参数，柜台系统连接串
     * @return 柜台参数，柜台系统连接串
     */
    public String getServerinfo() {
        return serverinfo;
    }

    /**
     * 设置 柜台参数，柜台系统连接串
     * @param serverinfo 柜台参数，柜台系统连接串
     */
    public void setServerinfo(String serverinfo) {
        this.serverinfo = serverinfo == null ? null : serverinfo.trim();
    }

    /**
     * 取得 中金所席位号
     * @return 中金所席位号
     */
    public String getZjSeatno() {
        return zjSeatno;
    }

    /**
     * 设置 中金所席位号
     * @param zjSeatno 中金所席位号
     */
    public void setZjSeatno(String zjSeatno) {
        this.zjSeatno = zjSeatno == null ? null : zjSeatno.trim();
    }

    /**
     * 取得 上期所席位号
     * @return 上期所席位号
     */
    public String getSqSeatno() {
        return sqSeatno;
    }

    /**
     * 设置 上期所席位号
     * @param sqSeatno 上期所席位号
     */
    public void setSqSeatno(String sqSeatno) {
        this.sqSeatno = sqSeatno == null ? null : sqSeatno.trim();
    }
}