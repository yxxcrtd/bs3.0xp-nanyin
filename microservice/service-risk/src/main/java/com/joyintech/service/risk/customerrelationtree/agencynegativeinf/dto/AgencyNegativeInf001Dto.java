/**
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2019/12/9 0009
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.risk.customerrelationtree.agencynegativeinf.dto;

import com.joyintech.framework.base.BaseEntity;

import java.io.UnsupportedEncodingException;

/**
 * 机构负面信息查询
 * 〈功能详细描述〉
 * @author 汪海
 * @version 1.0 2019/12/9 0009
 * @see AgencyNegativeInf001Dto
 * @since 1.0
 */
public class AgencyNegativeInf001Dto extends BaseEntity {

    /**
     * 流水号
     */
    private String seqNo;
    /**
     * 对象类型
     */
    private String objType;

    /**
     * 机构代码
     */
    private String orgId;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 标题
     */
    private String title;

    /**
     * 正文
     */
    private String cominfo;

    /**
     * 正文byete数组
     */
    private byte[] cominfoBytes;

    /**
     * 网址链接
     */
    private String linkId;

    /**
     * 公告日期
     */
    private String pubDate;

    /**
     * 信息来源
     */
    private String source;

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCominfo() throws UnsupportedEncodingException {
        return cominfoBytes==null?"": new String(cominfoBytes, "GBK");
    }

    public void setCominfo(String cominfo) {
        this.cominfo = cominfo;
    }

    public byte[] getCominfoBytes() {
        return cominfoBytes;
    }

    public void setCominfoBytes(byte[] cominfoBytes) {
        this.cominfoBytes = cominfoBytes;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
