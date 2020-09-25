package com.joyintech.service.riskrds.limitmanagement.rsksupervisionindicatorparam.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.RskIsupIndicatorParaDtl;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author liuwei
 * @version 1.0 2018年10月23日
 * @see RskSupervisionIndicatorParamDto
 * @since 1.0
 */
public class RskSupervisionIndicatorParamDto extends RskIsupIndicatorParaDtl {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -2009172338461713663L;

    /**
     * 参数名称
     */
    private String paraName;

    // ----add by 裴家俊 20190506-------

    /**
     * 有效日期
     */
    private Date validDate;

    /**
     * 主动提醒日期
     */
    private Date notifyDate;

    private List<RskIsupIndicatorParaDtl> rskIsupIndicatorParaDtlList;

    /**
     * 是否禁(可)投类
     */
    private String isForbid;

    /**
     * 禁可(投)可选项
     */
    private String forbidEnum;

    private String paraType;

    /**
     * 是否更新redis
     */
    private String isUpRedis;
    /**
     * 参数数据类型
     */
    private String paramDataType;
    /**
     * 获取 paraName
     *
     * @return paraName
     */
    public String getParaName() {
        return paraName;
    }

    /**
     * 设置 paraName
     *
     * @param paraName paraName
     */
    public void setParaName(String paraName) {
        this.paraName = paraName;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public Date getNotifyDate() {
        return notifyDate;
    }

    public void setNotifyDate(Date notifyDate) {
        this.notifyDate = notifyDate;
    }

    public List<RskIsupIndicatorParaDtl> getRskIsupIndicatorParaDtlList() {
        return rskIsupIndicatorParaDtlList;
    }

    public void setRskIsupIndicatorParaDtlList(List<RskIsupIndicatorParaDtl> rskIsupIndicatorParaDtlList) {
        this.rskIsupIndicatorParaDtlList = rskIsupIndicatorParaDtlList;
    }

    public String getIsForbid() {
        return isForbid;
    }

    public void setIsForbid(String isForbid) {
        this.isForbid = isForbid;
    }

    public String getForbidEnum() {
        return forbidEnum;
    }

    public void setForbidEnum(String forbidEnum) {
        this.forbidEnum = forbidEnum;
    }

    public String getParaType() {
        return paraType;
    }

    public void setParaType(String paraType) {
        this.paraType = paraType;
    }
    public String getIsUpRedis() {
        return isUpRedis;
    }

    public void setIsUpRedis(String isUpRedis) {
        this.isUpRedis = isUpRedis;
    }
    public String getParamDataType() {
        return paramDataType;
    }

    public void setParamDataType(String paramDataType) {
        this.paramDataType = paramDataType;
    }
}
