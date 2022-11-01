package com.joyintech.tams.disclosure.information.config.repprodinfo.dto;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeExt1;
import com.joyintech.tams.disclosure.information.config.performance.dto.Performance001Dto;
import com.joyintech.tams.disclosure.information.config.repprodinfo.validator.ProdInfoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

@PageId("ProdInfo001")
@ValidLogic(targetClass = ProdInfoValidator.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
public class ProdInfoDto extends RepProdInfo implements ITableRow, ProcessEntity {
    /**
     * excle行
     */
    private String excelNum;

    private String editFlag;
    /**
     *  业绩基准变更表
     */
    private transient List<ProdBasicChangeDto> basicChangeList;

    /**
     *  业绩基准变更表
     */
    private transient List<Performance001Dto> performaceList;

    //母产品相关字段

    /**
     * 若为分级产品，此处填写母产品的产品名称（全称）
     */
    @NotEmpty(fieldName = "产品名称")
    private String  pProdName ;

    /*    *//**
     * 母产品   若为分级产品，此处填写母产品的产品简称
     *//*
    private String  prodAbbr ;

    *//**
     * 母产品   产品主代码
     *//*
    private String  pProdId ;*/

    /**
     * 母产品   产品成立日期
     */
    @NotEmpty(fieldName = "产品成立日")
    private Date pSetDate;

    /**
     * 新发行状态
     */
    private  String issuingStatusNew ;


    // 字典或格式转换 相关字段
    private String updateDateStr;

    private String statusName;

    private String setDateStr;

    private String pdStatusName ;

    private String pSetDateStr;

    private String raiseTypeName;

    private String prodTypeName;

    private String issuingWayName;

    private String openFreqName;

    private String  profitTypeName;

    private String isPeriodName;

    private String isGradeName;

    private String isIndexName;

    private String natureInvestName;


    private String raiseStrdateStr;


    private String raiseEnddateStr ;


    private String  mdateStr;

    /**
     * 发行状态 01 发行成功 02 发行失败 03 确认发行
     */
    private String issueStatusStr;

    /**
     * 选中的数据
     */
    private transient List<ProdInfoDto> selectedItem;


    // 以下为 流程相关字段
    private String operateType ;

    /**
     * 元素ID
     */
    private String elementId;

    private String pageModel;

    // 其他字段
    /**
     *  同步母产品标识
     *  true 同步更新母产品信息
     *  false 不同步母产品信息
     */
    private boolean isSynchFlag = false;


    public String getNatureInvestName() {
        // return natureInvestName;
        return SysDataUtil.getNameFromDataDict(DictCodeExt1.信披_投资性质,getNatureInvest());
    }

    public void setNatureInvestName(String natureInvestName) {
        this.natureInvestName = natureInvestName;
    }

    public boolean isSynchFlag() {
        return isSynchFlag;
    }

    public void setSynchFlag(boolean synchFlag) {
        isSynchFlag = synchFlag;
    }

    public List<ProdBasicChangeDto> getBasicChangeList() {
        return basicChangeList;
    }

    public void setBasicChangeList(List<ProdBasicChangeDto> basicChangeList) {
        this.basicChangeList = basicChangeList;
    }

    public String getIssueStatusStr() {
        // return issueStatusStr;
        return SysDataUtil.getNameFromDataDict(DictCodeExt1.信披_发行状态,getIssueStatus());

    }

    public void setIssueStatusStr(String issueStatusStr) {
        this.issueStatusStr = issueStatusStr;
    }

    public String getIssuingStatusNew() {
        return issuingStatusNew;
    }

    public void setIssuingStatusNew(String issuingStatusNew) {
        this.issuingStatusNew = issuingStatusNew;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }


    public String getMdateStr() {
        if(StringUtils.isNotBlank(this.mdateStr)){
            return this.mdateStr ;
        }else{
            return this.mdateStr = this.getMdate()==null?null: DateUtil.format(this.getMdate(), "yyyy-MM-dd");
        }
    }

    public void setMdateStr(String mdateStr) {
        this.mdateStr = mdateStr;
    }

    public String getRaiseStrdateStr() {
        if(StringUtils.isNotBlank(this.raiseStrdateStr)){
            return this.raiseStrdateStr ;
        }else{
            return this.raiseStrdateStr = this.getRaiseStrdate()==null?null: DateUtil.format(this.getRaiseStrdate(), "yyyy-MM-dd");
        }
    }

    public void setRaiseStrdateStr(String raiseStrdateStr) {
        this.raiseStrdateStr = raiseStrdateStr;
    }

    public String getRaiseEnddateStr() {
        if(StringUtils.isNotBlank(this.raiseEnddateStr)){
            return this.raiseEnddateStr ;
        }else{
            return this.raiseEnddateStr = this.getRaiseEnddate()==null?null: DateUtil.format(this.getRaiseEnddate(), "yyyy-MM-dd");
        }
    }

    public void setRaiseEnddateStr(String raiseEnddateStr) {
        this.raiseEnddateStr = raiseEnddateStr;
    }

    public Date getpSetDate() {
        return pSetDate;
    }

    public void setpSetDate(Date pSetDate) {
        this.pSetDate = pSetDate;
    }

    public String getSetDateStr() {
        // return setDateStr;
        if(StringUtils.isNotBlank(this.setDateStr)){
            return this.setDateStr ;
        }else{
            return this.setDateStr = this.getSetDate()==null?null: DateUtil.format(this.getSetDate(), "yyyy-MM-dd");
        }

    }

    public void setSetDateStr(String setDateStr) {
        this.setDateStr = setDateStr;
    }

    public String getPdStatusName() {
        // return pdStatusName;
        return SysDataUtil.getNameFromDataDict(DictCodeExt1.信披_产品状态,getPdStatus());
    }

    public void setPdStatusName(String pdStatusName) {
        this.pdStatusName = pdStatusName;
    }

    public String getpSetDateStr() {
        if(StringUtils.isNotBlank(this.pSetDateStr)){
            return this.pSetDateStr ;
        }else{
            return this.pSetDateStr = this.getpSetDate()==null?null: DateUtil.format(this.getpSetDate(), "yyyy-MM-dd");
        }
    }

    public void setpSetDateStr(String pSetDateStr) {
        this.pSetDateStr = pSetDateStr;
    }

    public String getRaiseTypeName() {
        return SysDataUtil.getNameFromDataDict(DictCodeExt1.信披_募集方式,getRaiseType());

    }

    public void setRaiseTypeName(String raiseTypeName) {
        this.raiseTypeName = raiseTypeName;
    }

    public String getProdTypeName() {
        return SysDataUtil.getNameFromDataDict(DictCodeExt1.信披_产品类型,getProdType());
    }

    public void setProdTypeName(String prodTypeName) {
        this.prodTypeName = prodTypeName;
    }

    public String getIssuingWayName() {
        //return issuingWayName;
        return SysDataUtil.getNameFromDataDict(DictCodeExt1.发行方式_信批,getIssuingWay());

    }

    public void setIssuingWayName(String issuingWayName) {
        this.issuingWayName = issuingWayName;
    }

    public String getOpenFreqName() {
        return SysDataUtil.getNameFromDataDict(DictCodeExt1.开放频率,getOpenFreq());
    }

    public void setOpenFreqName(String openFreqName) {
        this.openFreqName = openFreqName;
    }

    public String getProfitTypeName() {
        return SysDataUtil.getNameFromDataDict(DictCodeExt1.信披_产品运作方式,getProfitType());

    }

    public void setProfitTypeName(String profitTypeName) {
        this.profitTypeName = profitTypeName;
    }

    public String getIsPeriodName() {
        return SysDataUtil.getNameFromDataDict(DictCode.是或否,getIsPeriod());

    }

    public void setIsPeriodName(String isPeriodName) {
        this.isPeriodName = isPeriodName;
    }

    public String getIsGradeName() {
        return SysDataUtil.getNameFromDataDict(DictCode.是或否,getIsGrade());

    }

    public void setIsGradeName(String isGradeName) {
        this.isGradeName = isGradeName;
    }

    public String getIsIndexName() {
        return SysDataUtil.getNameFromDataDict(DictCode.是或否,getIsIndex());

    }

    public void setIsIndexName(String isIndexName) {
        this.isIndexName = isIndexName;
    }

    public String getpProdName() {
        return pProdName;
    }

    public void setpProdName(String pProdName) {
        this.pProdName = pProdName;
    }


    public String getStatusName() {
        if(StringUtils.isNotBlank(this.statusName)){
            return this.statusName ;
        }else{
            return this.statusName = SysDataUtil.getNameFromDataDict(DictCode.维护数据状态, this.getStatus());
        }
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }



    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public List<ProdInfoDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<ProdInfoDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getUpdateDateStr() {
        if(StringUtils.isNotBlank(this.updateDateStr)){
            return this.updateDateStr ;
        }else{
            return this.updateDateStr = this.getUpdateTime()==null?null: DateUtil.format(this.getUpdateTime(), "yyyy-MM-dd hh:mm:ss");
        }
    }

    public void setUpdateDateStr(String updateDateStr) {
        this.updateDateStr = updateDateStr;
    }

    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getProdId() {
        return super.getProdId();
    }

    public String getExcelNum() {
        return excelNum;
    }

    public void setExcelNum(String excelNum) {
        this.excelNum = excelNum;
    }

    //序号
    private String number;

    private String zzqj;

    private String zzcf;

    private String zz500;

    private String hs300;

    public Date expDate;
    public Date effDate;

    public void setZzqj(String zzqj) {
        this.zzqj = zzqj;
    }

    public void setZzcf(String zzcf) {
        this.zzcf = zzcf;
    }

    public void setZz500(String zz500) {
        this.zz500 = zz500;
    }

    public void setHs300(String hs300) {
        this.hs300 = hs300;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getZzqj() {
        return zzqj;
    }

    public String getZzcf() {
        return zzcf;
    }

    public String getZz500() {
        return zz500;
    }

    public String getHs300() {
        return hs300;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public Date getEffDate() {
        return effDate;
    }

    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    private String expDateStr;

    public void setExpDateStr(String expDateStr) {
        this.expDateStr = expDateStr;
    }

    public void setEffDateStr(String effDateStr) {
        this.effDateStr = effDateStr;
    }


    public String getExpDateStr() {
        if (StringUtil.isNotEmpty(this.expDateStr)) {
            return this.expDateStr;
        } else {
            return DateUtil.format(this.getExpDate());
        }
    }

    private String effDateStr;

    public String getEffDateStr() {
        if (StringUtil.isNotEmpty(this.effDateStr)) {
            return this.effDateStr;
        } else {
            return DateUtil.format(this.getEffDate());
        }
    }

    public List<Performance001Dto> getPerformaceList() {
        return performaceList;
    }

    public void setPerformaceList(List<Performance001Dto> performaceList) {
        this.performaceList = performaceList;
    }


    private String dataStatus;

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getEditFlag() {
        return editFlag;
    }

    public void setEditFlag(String editFlag) {
        this.editFlag = editFlag;
    }
}