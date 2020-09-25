/*
 * 文件名：RepFinProductPerIssRegist001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.debtreport.repfinproductperissregist.dto;

import java.util.Date;

import com.joyintech.tams.common.base.entity.RepProdIssue;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年11月20日
 * @see RepFinProductPerIssRegist001Dto
 * @since 1.0
 */
public class RepFinProductPerIssRegistDto extends RepProdIssue{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 数据日期
     */
    private Date dataDate;
    /**
     * 发行机构
     */
    private String issuerCode;
    /**
     * 产品名称
     */
    private String repFinprodName;
    
    /**
     * 理财产品代码
     */
    private String repFinprodInsId;
    
    public Date getDataDate() {
        return dataDate;
    }
    
    public String getDataDateStr() {
        return DateUtil.format(dataDate);
    }
    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }
    
    public String getIssuerCode() {
        return issuerCode;
    }
    
    public void setIssuerCode(String issuerCode) {
        this.issuerCode = issuerCode;
    }
    
    public String getRepFinprodName() {
        return repFinprodName;
    }

    
    public void setRepFinprodName(String repFinprodName) {
        this.repFinprodName = repFinprodName;
    }
    public String getRepRaiseVdateStr() {
        return DateUtil.format(super.getRepRaiseVdate());
    }
    public String getRepRaiseEnddateStr() {
        return DateUtil.format(super.getRepRaiseEnddate());
    }
    public String getRepPrdVdateStr() {
        return DateUtil.format(super.getRepPrdVdate());
    }
    public String getRepPrdMdateStr() {
        return DateUtil.format(super.getRepPrdMdate());
    }
    public String getRepIsConstracteStr() {
        if(super.getRepIsConstracte() != null 
            && !StringUtil.equals(super.getRepIsConstracte(), "")){
            if(StringUtil.equals(super.getRepIsConstracte(), DictCodeList.是否_是)){
                return "01 是";
            }
            else{
                return "02 否";
            }
        }
        return SysDataUtil.getNameFromDataDict(DictCode.是否, super.getRepIsConstracte());
    }
    public String getRepOpenModeStr(){
        if(super.getRepOpenMode() != null 
            && !StringUtil.equals(super.getRepOpenMode(), "")
            && (StringUtil.equals(super.getRepOpenMode(), DictCodeList.开放模式_高频开放.getValue())
                || StringUtil.equals(super.getRepOpenMode(), DictCodeList.开放模式_定期开放.getValue()))){
            return "01 有规律开放";
        }
        return SysDataUtil.getNameFromDataDict(DictCode.开放模式, super.getRepOpenMode());
    }
    
    public String getRepOpenCircleStr(){
        if(super.getRepOpenCircle() != null 
            && !StringUtil.equals(super.getRepOpenCircle(), "")){
            return super.getRepOpenCircle()+" "+SysDataUtil.getNameFromDataDict(DictCode.规律开放周期, super.getRepOpenCircle());
        }
        return SysDataUtil.getNameFromDataDict(DictCode.规律开放周期, super.getRepOpenCircle());
    }
    public String getRepHolidayOnFlagStr() {
        if(super.getRepHolidayOnFlag() != null 
            && !StringUtil.equals(super.getRepHolidayOnFlag(), "")){
            if(StringUtil.equals(super.getRepHolidayOnFlag(), DictCodeList.是否_是)){
                return "01 是";
            }
            else{
                return "02 否";
            }
        }
        return SysDataUtil.getNameFromDataDict(DictCode.是否, super.getRepHolidayOnFlag());
    }
    public String getRepOpenBussStr() {
        if(super.getRepOpenBuss() != null 
            && !StringUtil.equals(super.getRepOpenBuss(), "")){
            return super.getRepOpenBuss()+" "+SysDataUtil.getNameFromDataDict(DictCode.开放期业务, super.getRepOpenBuss());
        }
        return SysDataUtil.getNameFromDataDict(DictCode.开放期业务, super.getRepOpenBuss());
    }

    
    public String getRepFinprodInsId() {
        return repFinprodInsId;
    }

    
    public void setRepFinprodInsId(String repFinprodInsId) {
        this.repFinprodInsId = repFinprodInsId;
    }
    public String getRepFirstOpenDayStr() {
        return DateUtil.format(super.getRepFirstOpenDay());
    }
    public String getRepOpenCircleDaysStr() {
        return super.getRepOpenCircleDays()+"";
    }
}
