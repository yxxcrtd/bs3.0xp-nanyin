package com.joyintech.tams.report.submitmaintenance.salerideninfo.dto;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.ChbSalerIdenInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.submitmaintenance.salerideninfo.validate.SalerIdenInfoValid;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

@ValidLogic(targetClass = SalerIdenInfoValid.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
public class SalerIdenInfoDto extends ChbSalerIdenInfo implements ProcessEntity {
    private String operateType;//操作类型
    private String elementId;//页面元素
    private String pageModel;//页面模型
    private String disabledThing;//置灰控制
    private String erroOrInfo;//来自erro还是info
    /**
     * 原始数据
     */
    private String orgData;
    /**
     * 错误信息
     */
    private String errMsg;

    /**
     * 选中的数据
     */
    private transient List<SalerIdenInfoDto> selectedItem;

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public List<SalerIdenInfoDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<SalerIdenInfoDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getStatusStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.维护数据状态, this.getStatus());
    }

    public String getSalerSexStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.性别_中债, this.getSalerSex());
    }

    public String getSalerCredentTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.销售人员证件类型, this.getSalerCredentType());
    }

    public String getSalerAreaTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.销售区域_中债, this.getSalerAreaType());
    }

    public String getSalerDegreeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.销售人员学位, this.getSalerDegree());
    }

    public String getSalerEduLevelStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.销售人员学历, this.getSalerEduLevel());
    }

    public String getSalerLevleStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.销售人员专业技术职称, this.getSalerLevle());
    }

    public String getSalerRiskLevelStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.可销售产品风险等级, this.getSalerRiskLevel());
    }

    public String getSalerStatusStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.销售人员当前状态, this.getSalerStatus());
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public String getDisabledThing() {
        return disabledThing;
    }

    public void setDisabledThing(String disabledThing) {
        this.disabledThing = disabledThing;
    }

    public String getErroOrInfo() {
        return erroOrInfo;
    }

    public void setErroOrInfo(String erroOrInfo) {
        this.erroOrInfo = erroOrInfo;
    }

    public String getOrgData() {
        return orgData;
    }

    public void setOrgData(String orgData) {
        this.orgData = orgData;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getSalerBirthdayStr() {
        return DateUtil.format(this.getSalerBirthday());
    }
}