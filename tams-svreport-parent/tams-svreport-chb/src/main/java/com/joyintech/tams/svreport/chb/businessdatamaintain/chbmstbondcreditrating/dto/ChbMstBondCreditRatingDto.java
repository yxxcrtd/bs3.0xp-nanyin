package com.joyintech.tams.svreport.chb.businessdatamaintain.chbmstbondcreditrating.dto;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;

import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbmstbondcreditrating.validator.ChbMstBondCreditRatingValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * 债券信用评级
 * @TableName MST_BOND_CREDIT_RATING
 */
@ValidLogic(targetClass = ChbMstBondCreditRatingValidator.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
public class ChbMstBondCreditRatingDto extends MstBondCreditRating implements ProcessEntity {

    /**
     * 数据来源 
     */
    private String dataSource;


    /**
     * 状态
     */
    private String status;

    /**
     * 债券名称
     */
    private String oName;

    /**
     * 描述
     */
    private String des;
    /**
     * 操作类型
     */
    private String operateType;

    private List<ChbMstBondCreditRatingDto> selectedItem;

    /**
     * 目的和意义以及可能会用到的地方
     */
    private String pageModel;

    /**
     * 要素id
     */
    private String elementId;


    private static final long serialVersionUID = 1L;

    /**
     * 债券代码（市场代码）
     */
    @NotEmpty(fieldName = "ChbMstBondCreditRating001.oCode",groups = {ValidateGroupEdit.class})
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getoCode() {
        return super.getoCode();
    }


    /**
     * 评级机构
     */
    @NotEmpty(fieldName = "ChbMstBondCreditRating001.gradeOrgId",groups = {ValidateGroupEdit.class})
    public String getGradeOrgId() {
        return super.getGradeOrgId();
    }


    /**
     * 评级日期
     */
    @NotEmpty(fieldName = "ChbMstBondCreditRating001.gradeDate",groups = {ValidateGroupEdit.class})
    public String getGradeDateStr() {
        return DateUtil.format(super.getGradeDate());
    }

    public void setGradeDateStr(String gradeDateStr){
        try {
            super.setGradeDate(DateUtil.parseDate(gradeDateStr,"yyyy-MM-dd"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }



    /**
     * 评级类型
     */
    @NotEmpty(fieldName = "ChbMstBondCreditRating001.shortLongTerm",groups = {ValidateGroupEdit.class})
    public String getShortLongTerm() {
        return super.getShortLongTerm();
    }


    /**
     * 评级结果
     */
    @NotEmpty(fieldName = "ChbMstBondCreditRating001.gradeResult",groups = {ValidateGroupEdit.class})
    public String getGradeResult() {
        return super.getGradeResult();
    }

    /**
     * 数据来源 
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * 数据来源 
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 债券名称
     */
    public String getoName() {
        return oName;
    }


    /**
     * 评级对象
     */
    @NotEmpty(fieldName = "ChbMstBondCreditRating001.oType",groups = {ValidateGroupEdit.class})
    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getoType() {
        return super.getoType();
    }


    @NotEmpty(fieldName = "ChbMstBondCreditRating001.source",groups = {ValidateGroupEdit.class})
    public String getSource() {
        return super.getSource();
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public List<ChbMstBondCreditRatingDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<ChbMstBondCreditRatingDto> selectedItem) {
        this.selectedItem = selectedItem;
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

    public String getGradeOrgIdStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.评级机构,getGradeOrgId());
    }



    public String getShortLongTermStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.长期短期,getShortLongTerm());
    }



    public String getGradeResultStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.评级结果,getGradeResult());
    }


    public String getSourceStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.评级来源类型,getSource());
    }


    public String getDataSourceStr() {
        return null!=dataSource&&dataSource.equals("D")?"新建导入":"接口";
    }


    public String getStatusStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.维护数据状态,status);
    }



    public String getoTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.评级类型,getoType());
    }



    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getupdateTimeStr(){
        return DateUtil.format(getUpdateTime());
    }
}