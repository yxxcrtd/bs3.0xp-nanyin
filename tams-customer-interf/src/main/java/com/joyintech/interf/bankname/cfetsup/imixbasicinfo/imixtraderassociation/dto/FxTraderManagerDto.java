/*
 * 文件名：MstCaptialAccountDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年8月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixtraderassociation.dto;

import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixtraderassociation.validator.FxTraderManagerValidator;
import com.joyintech.tams.common.base.entity.MstIndexInfo;

import java.util.Date;
import java.util.List;

/**
 * 外汇交易中心交易员管理实体类
 * 
 * @author zhangjun
 * @version 1.0 2020年1月5日
 * @since 1.0
 * 
 */
@ValidLogic(targetClass = FxTraderManagerValidator.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
public class FxTraderManagerDto extends MstIndexInfo implements ITableRow{
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    private String seqno;
    
    /**
     *用户id
     */
    private String staffid;
    /**
     *外汇交易中心交易员
     */
    private String cstptrader;
    /**
     *是否有效
     */
    private String effectflag;
    /**
     *创建人
     */
    private String creatorid;
    /**
     *创建时间
     */
    private Date createdtime;
    /**
     *最后修改人
     */
    private String lastmodifierid;
    /**
     *最后修改时间
     */
    private Date lastmodifiedtime;
    /**
     *外汇交易中心交易员id
     */
    private String cstptraderid;
    /**
     *
     */
    private String staffcode;
    /**
     *
     */
    private String staffname;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 操作类型
     */
    private String pageModel;

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public String getSeqno() {
        return seqno;
    }

    public String getStaffid() {
        return staffid;
    }

    public String getCstptrader() {
        return cstptrader;
    }

    public String getEffectflag() {
        return effectflag;
    }

    public String getCreatorid() {
        return creatorid;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public String getLastmodifierid() {
        return lastmodifierid;
    }

    public Date getLastmodifiedtime() {
        return lastmodifiedtime;
    }

    public String getCstptraderid() {
        return cstptraderid;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid;
    }

    public void setCstptrader(String cstptrader) {
        this.cstptrader = cstptrader;
    }

    public void setEffectflag(String effectflag) {
        this.effectflag = effectflag;
    }

    public void setCreatorid(String creatorid) {
        this.creatorid = creatorid;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public void setLastmodifierid(String lastmodifierid) {
        this.lastmodifierid = lastmodifierid;
    }

    public void setLastmodifiedtime(Date lastmodifiedtime) {
        this.lastmodifiedtime = lastmodifiedtime;
    }

    public void setCstptraderid(String cstptraderid) {
        this.cstptraderid = cstptraderid;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getStaffcode() {
        return staffcode;
    }

    public void setStaffcode(String staffcode) {
        this.staffcode = staffcode;
    }

    public String getStaffname() {
        return staffname;
    }

    public void setStaffname(String staffname) {
        this.staffname = staffname;
    }
}
