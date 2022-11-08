package com.joyintech.tams.disclosure.information.config.subbalanceinfopro.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.BokBalance;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.information.config.proelementadj.validator.ProElementAdjValidator;
import com.joyintech.tams.disclosure.information.config.subbalanceinfopro.validator.SubBalanceInfoProValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.List;

@PageId("SubBalanceInfoPro002")
@ValidLogic(targetClass = SubBalanceInfoProValidator.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
public class SubBalanceInfoPro001Dto extends BokBalance implements ITableRow, ProcessEntity {
    /**
     * 序列
     */
    private static final long serialVersionUID = -2744914232496735261L;
    /**
     * excle行
     */
    private String excelNum;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 选中的数据
     */
    private transient List<SubBalanceInfoPro001Dto> selectedItem;
    /**
     * 元素ID
     */
    private String elementId;

    /**
     * 产品代码(名称)
     */
    private  String prodId;

    private String status;

    private String balanceDateStr;


    /**
     * 产品名称
     */
    private String prodName;

    private String subjectName;

    @NotEmpty(fieldName = "产品名称", groups = {ValidateGroupEdit.class})
    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public List<SubBalanceInfoPro001Dto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<SubBalanceInfoPro001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getProdId() {
        return prodId;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getStatusStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.维护数据状态,this.getStatus());
    }


    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getBooksetId() {
        return super.getBooksetId();
    }

    public void setBalanceDateStr(String balanceDateStr) {
        this.balanceDateStr = balanceDateStr;
    }

    @PrimaryKey(PrimaryKey.Field.PK_2)
    @NotEmpty(fieldName = "余额日期", groups = {ValidateGroupEdit.class})
    public String getBalanceDateStr() {
        if (StringUtil.isNotEmpty(this.balanceDateStr)) {
            return this.balanceDateStr;
        } else {
            return DateUtil.format(super.getBalanceDate());
        }
    }
//    @PrimaryKey(PrimaryKey.Field.PK_2)
//    public String getTradeDateStr() {
//        return DateUtil.format(getTradeDate());
//    }
//
//
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_3)
    @NotEmpty(fieldName = "科目号", groups = {ValidateGroupEdit.class})
    public String getSubjectNo() {
        return super.getSubjectNo();
    }

    @Override
    @NotEmpty(fieldName = "原币币种", groups = {ValidateGroupEdit.class})
    public String getoCcy() {
        return super.getoCcy();
    }

    @Override
    @NotEmpty(fieldName = "原币余额", groups = {ValidateGroupEdit.class})
    public BigDecimal getoAmt() {
        return super.getoAmt();
    }

    @Override
    public BigDecimal getTdyODAmt() {
        return super.getTdyODAmt();
    }

    public String getUpdateTimeStr() {
        return this.getUpdateTime()==null?null: DateUtil.format(this.getUpdateTime(), "yyyy-MM-dd HH:mm:ss");
    }

    public String getDataStatusStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.维护数据状态,super.getStatus());
    }

    public String getExcelNum() {
        return excelNum;
    }

    public void setExcelNum(String excelNum) {
        this.excelNum = excelNum;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
