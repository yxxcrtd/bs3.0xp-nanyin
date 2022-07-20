package com.joyintech.tams.disclosure.information.config.repInvestAccount.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RepInvestAccount;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.information.config.repInvestAccount.validator.RepInvestAccountValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;
@PageId("RepInvestAccount002")
@ValidLogic(targetClass = RepInvestAccountValidator.class, groups = { ValidateGroupEdit.class, ValidateGroupDelete.class })
public class RepInvestAccountDto extends RepInvestAccount implements ITableRow, ProcessEntity {

    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 页面类型
     */
    private String pageModel;
    /**
     * 要素id
     */
    private String elementId;
    /**
     * 选中的数据列表
     */
    private transient List<RepInvestAccountDto> selectedItem;
    /**
     * 产品主代码
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    private String prodId;
    /**
     * 产品主代码
     */
    private String prodName;
    /**
     * 账户类型：托管账户
     */
    private String accountType;
    /**
     * 账号
     */
    private String accountNum;
    /**
     * 账户名称
     */
    private String accountName;
    /**
     * 开户单位
     */
    private String accountOpener;
    /**
     * 数据状态
     */
    private String status;
    /**
     * 错误信息
     */
    private String errmsg;

    private String oaccountNum;

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public List<RepInvestAccountDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<RepInvestAccountDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountOpener() {
        return accountOpener;
    }

    public void setAccountOpener(String accountOpener) {
        this.accountOpener = accountOpener;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getOaccountNum() {
        return oaccountNum;
    }

    public void setOaccountNum(String oaccountNum) {
        this.oaccountNum = oaccountNum;
    }

    public String getStatusStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.维护数据状态, this.getStatus());
    }

    public String getUpdateTimeStr() {
        return this.getUpdateTime()==null?null: DateUtil.format(this.getUpdateTime(), "yyyyMMddHHmmss");
    }
}
