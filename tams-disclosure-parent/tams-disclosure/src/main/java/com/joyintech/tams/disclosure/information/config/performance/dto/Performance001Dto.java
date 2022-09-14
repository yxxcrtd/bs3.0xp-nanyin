package com.joyintech.tams.disclosure.information.config.performance.dto;


import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RepProdBasicChange;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.information.config.performance.validator.Performance001Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.Date;
import java.util.List;

@PageId("Performance002")
@ValidLogic(targetClass = Performance001Validator.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
public class Performance001Dto extends RepProdBasicChange implements ProcessEntity {

    /**
     * 失效日
     */
    private Date expDate;


    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String elementId;

    /**
     * 选中的数据
     */
    private  List<Performance001Dto> selectedItem;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String pageModel;

    private String zzqj;

    private String zzcf;

    private String zz500;

    private String hs300;

    public Date expDateO;
    public Date effDateO;
    public String effDateOStr;
    public String expDateOStr;


    private String workflowId;


    public String getWorkflowId() {
        return workflowId;
    }


    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public String getUpdateTimeStr() {
        return this.getUpdateTime()==null?null: DateUtil.format(this.getUpdateTime(), "yyyy-MM-dd HH:mm:ss");
    }



    public Date getExpDateO() {
        return expDateO;
    }

    public void setExpDateO(Date expDateO) {
        this.expDateO = expDateO;
    }

    public Date getEffDateO() {
        return effDateO;
    }

    public void setEffDateO(Date effDateO) {
        this.effDateO = effDateO;
    }

    public String getEffDateOStr() {
        return effDateOStr;
    }

    public void setEffDateOStr(String effDateOStr) {
        this.effDateOStr = effDateOStr;
    }

    public String getExpDateOStr() {
        return expDateOStr;
    }

    public void setExpDateOStr(String expDateOStr) {
        this.expDateOStr = expDateOStr;
    }

    /**
     * excle行
     */
    private String excelNum;

    public String getExcelNum() {
        return excelNum;
    }

    public void setExcelNum(String excelNum) {
        this.excelNum = excelNum;
    }

    @NotEmpty(fieldName = "业绩比较基准生效截止日",groups={ValidateGroupEdit.class})
    public Date getExpDate() {
        return expDate;
    }

    private String expDateStr;

    public void setExpDateStr(String expDateStr) {
        this.expDateStr = expDateStr;
    }

    public void setEffDateStr(String effDateStr) {
        this.effDateStr = effDateStr;
    }

    @PrimaryKey(PrimaryKey.Field.PK_3)
    public String getExpDateStr() {
        if (StringUtil.isNotEmpty(this.expDateStr)) {
            return this.expDateStr;
        } else {
            return DateUtil.format(this.getExpDate());
        }
    }

    private String effDateStr;
    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getEffDateStr() {
        if (StringUtil.isNotEmpty(this.effDateStr)) {
            return this.effDateStr;
        } else {
            return DateUtil.format(super.getEffDate());
        }
    }
    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public List<Performance001Dto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<Performance001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    @NotEmpty(fieldName = "中债新综合全价指数收益率【系数】",groups={ValidateGroupEdit.class})
    public String getZzqj() {
        return zzqj;
    }

    public void setZzqj(String zzqj) {
        this.zzqj = zzqj;
    }

    @NotEmpty(fieldName = "中债新综合财富指数（1-3年）收益率【系数】",groups={ValidateGroupEdit.class})
    public String getZzcf() {
        return zzcf;
    }

    public void setZzcf(String zzcf) {
        this.zzcf = zzcf;
    }

    @NotEmpty(fieldName = "中证500指数收益率【系数】",groups={ValidateGroupEdit.class})
    public String getZz500() {
        return zz500;
    }

    public void setZz500(String zz500) {
        this.zz500 = zz500;
    }

    @NotEmpty(fieldName = "沪深300指数收益率【系数】",groups={ValidateGroupEdit.class})
    public String getHs300() {
        return hs300;
    }

    public void setHs300(String hs300) {
        this.hs300 = hs300;
    }

    public String getZzqjStr(){
        return String.format("%.2f",Double.parseDouble(this.getZzqj())).toString();
    }
    public String getZzcfStr(){
        return String.format("%.2f",Double.parseDouble(this.getZzcf())).toString();
    }
    public String getZz500Str(){
        return String.format("%.2f",Double.parseDouble(this.getZz500())).toString();
    }
    public String getHs300Str(){
        return String.format("%.2f",Double.parseDouble(this.getHs300())).toString();
    }

    public String getStatusStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.维护数据状态, super.getStatus());
    }

    //    public String getUpdateUserStr(){
//        return SecurityUtil.getLoginUser().getUserName();
//    }
    private String updateUser;



//    @Override
//    public String getUpdateUser() {
//        return this.getUpdateUser();
//    }
//
//    @Override
//    public void setUpdateUser(String updateUser) {
//        this.setUpdateUser(updateUser);
//    }
}
