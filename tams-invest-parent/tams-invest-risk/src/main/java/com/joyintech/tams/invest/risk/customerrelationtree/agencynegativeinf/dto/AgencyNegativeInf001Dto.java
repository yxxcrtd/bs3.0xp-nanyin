/**
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2019/12/9 0009
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.risk.customerrelationtree.agencynegativeinf.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.systemtools.util.SysDatadictUtil;

import java.util.Arrays;
import java.util.stream.Collectors;

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
     * 关联机构类型
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

    public String getObjTypeName(){
        return Arrays.asList(this.objType.split(",")).stream()
                .map(s -> SysDatadictUtil.getNameFromDataDict(DictCode.客户性质, s))
                        .collect(Collectors.joining("、", "", ""));
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

    public String getCominfo() {
        return cominfo;
    }

    public void setCominfo(String cominfo) {
        this.cominfo = cominfo;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getPubDate() {
        if(pubDate != null){
            return pubDate.substring(0, 10);
        }else{
            return pubDate;
        }
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
