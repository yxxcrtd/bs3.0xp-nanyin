
package com.joyintech.service.risk.stresstestmanage.riskfactor.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.tams.common.base.entity.CreditAssetpool;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * @author chenyunlong
 * @version 1.0 2019年8月24日
 * @see RiskFactor001Dto
 * @since 1.0
 */
public class RiskFactor001Dto extends CreditAssetpool implements ITableRow, ProcessEntity {

    private String type1;
    private String type2;
    private String ftrid;
    private String ftrname;
    private String ftrtype;
    private String ftrtype2;
    private String ftrvaluestruct;
    private String handletype;
    private String ftrstatus;
    private String isvisible;
    private String createuser;
    private String createdept;
    private Date createtime;
    private String updateuser;
    private Date updatetime;
    private String valueexetype;
    private String effstatus;
    private String remark;
    private String effstatus1;
    private List<RiskFactor001Dto> selectedItem;
    private String boost;
    private String configid;
    private String sdate;
    private String edate;
    private String ftrvaluestruct1;
    private String type1code;
    private String type2code;

    public String getFtrvaluestruct1() {
        return ftrvaluestruct1;
    }

    public void setFtrvaluestruct1(String ftrvaluestruct1) {
        this.ftrvaluestruct1 = ftrvaluestruct1;
    }

    public String getType2code() {
        return type2code;
    }

    public void setType2code(String type2code) {
        this.type2code = type2code;
    }

    public String getType1code() {
        return type1code;
    }

    public void setType1code(String type1code) {
        this.type1code = type1code;
    }



    /**
     * 获取 sdate
     *
     * @return sdate.
     */
    public String getSdate() {
        return sdate;
    }


    /**
     * 设置 sdate
     *
     * @param sdate sdate
     */
    public void setSdate(String sdate) {
        this.sdate = sdate;
    }


    /**
     * 获取 edate
     *
     * @return edate.
     */
    public String getEdate() {
        return edate;
    }


    /**
     * 设置 edate
     *
     * @param edate edate
     */
    public void setEdate(String edate) {
        this.edate = edate;
    }


    /**
     * 获取 configid
     *
     * @return configid.
     */
    public String getConfigid() {
        return configid;
    }


    /**
     * 设置 configid
     *
     * @param configid configid
     */
    public void setConfigid(String configid) {
        this.configid = configid;
    }

    /**
     * configid
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 979450231371811110L;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 画面处理模式
     */
    private String pageModel;

    /**
     * 按钮ID - 工作流参数
     */
    private String elementId;
    /**
     * 画面Id - 工作流参数
     */
    private String pageId;


    /**
     * 获取 operateType
     *
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }


    /**
     * 设置 operateType
     *
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }


    /**
     * 获取 pageModel
     *
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }


    /**
     * 设置 pageModel
     *
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }


    /**
     * 获取 elementId
     *
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }


    /**
     * 设置 elementId
     *
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }


    /**
     * 获取 pageId
     *
     * @return pageId.
     */
    public String getPageId() {
        return pageId;
    }


    /**
     * 设置 pageId
     *
     * @param pageId pageId
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }


    /**
     * 获取 serialversionuid
     *
     * @return serialversionuid.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }


    /**
     * 获取 boost
     *
     * @return boost.
     */
    public String getBoost() {
        return boost;
    }


    /**
     * 设置 boost
     *
     * @param boost boost
     */
    public void setBoost(String boost) {
        this.boost = boost;
    }


    /**
     * 获取 selectedItem
     *
     * @return selectedItem.
     */
    public List<RiskFactor001Dto> getSelectedItem() {
        return selectedItem;
    }


    /**
     * 设置 selectedItem
     *
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<RiskFactor001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }


    /**
     * 获取 effstatus1
     *
     * @return effstatus1.
     */
    public String getEffstatus1() {
        return effstatus1;
    }


    /**
     * 设置 effstatus1
     *
     * @param effstatus1 effstatus1
     */
    public void setEffstatus1(String effstatus1) {
        this.effstatus1 = effstatus1;
    }

    /**
     * 获取 type1
     *
     * @return type1.
     */
    public String getType1() {
        return type1;
    }

    /**
     * 设置 type1
     *
     * @param type1 type1
     */
    public void setType1(String type1) {
        this.type1 = type1;
    }

    /**
     * 获取 type2
     *
     * @return type2.
     */
    public String getType2() {
        return type2;
    }

    /**
     * 设置 type2
     *
     * @param type2 type2
     */
    public void setType2(String type2) {
        this.type2 = type2;
    }

    /**
     * 获取 ftrid
     *
     * @return ftrid.
     */
    public String getFtrid() {
        return ftrid;
    }

    /**
     * 设置 ftrid
     *
     * @param ftrid ftrid
     */
    public void setFtrid(String ftrid) {
        this.ftrid = ftrid;
    }

    /**
     * 获取 ftrname
     *
     * @return ftrname.
     */
    public String getFtrname() {
        return ftrname;
    }

    /**
     * 设置 ftrname
     *
     * @param ftrname ftrname
     */
    public void setFtrname(String ftrname) {
        this.ftrname = ftrname;
    }

    /**
     * 获取 ftrtype
     *
     * @return ftrtype.
     */
    public String getFtrtype() {
        return ftrtype;
    }

    /**
     * 设置 ftrtype
     *
     * @param ftrtype ftrtype
     */
    public void setFtrtype(String ftrtype) {
        this.ftrtype = ftrtype;
    }

    public String getFtrtype2() {
        return ftrtype2;
    }

    public void setFtrtype2(String ftrtype2) {
        this.ftrtype2 = ftrtype2;
    }

    /**
     * 获取 ftrvaluestruct
     *
     * @return ftrvaluestruct.
     */
    public String getFtrvaluestruct() {
        return ftrvaluestruct;
    }

    /**
     * 设置 ftrvaluestruct
     *
     * @param ftrvaluestruct ftrvaluestruct
     */
    public void setFtrvaluestruct(String ftrvaluestruct) {
        this.ftrvaluestruct = ftrvaluestruct;
    }

    /**
     * 获取 handletype
     *
     * @return handletype.
     */
    public String getHandletype() {
        return handletype;
    }

    /**
     * 设置 handletype
     *
     * @param handletype handletype
     */
    public void setHandletype(String handletype) {
        this.handletype = handletype;
    }

    /**
     * 获取 ftrstatus
     *
     * @return ftrstatus.
     */
    public String getFtrstatus() {
        return ftrstatus;
    }

    /**
     * 设置 ftrstatus
     *
     * @param ftrstatus ftrstatus
     */
    public void setFtrstatus(String ftrstatus) {
        this.ftrstatus = ftrstatus;
    }

    /**
     * 获取 isvisible
     *
     * @return isvisible.
     */
    public String getIsvisible() {
        return isvisible;
    }

    /**
     * 设置 isvisible
     *
     * @param isvisible isvisible
     */
    public void setIsvisible(String isvisible) {
        this.isvisible = isvisible;
    }

    /**
     * 获取 createuser
     *
     * @return createuser.
     */
    public String getCreateuser() {
        return createuser;
    }

    /**
     * 设置 createuser
     *
     * @param createuser createuser
     */
    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    /**
     * 获取 createdept
     *
     * @return createdept.
     */
    public String getCreatedept() {
        return createdept;
    }

    /**
     * 设置 createdept
     *
     * @param createdept createdept
     */
    public void setCreatedept(String createdept) {
        this.createdept = createdept;
    }

    /**
     * 获取 createtime
     *
     * @return createtime.
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置 createtime
     *
     * @param createtime createtime
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取 updateuser
     *
     * @return updateuser.
     */
    public String getUpdateuser() {
        return updateuser;
    }

    /**
     * 设置 updateuser
     *
     * @param updateuser updateuser
     */
    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
    }

    /**
     * 获取 updatetime
     *
     * @return updatetime.
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 设置 updatetime
     *
     * @param updatetime updatetime
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 获取 valueexetype
     *
     * @return valueexetype.
     */
    public String getValueexetype() {
        return valueexetype;
    }

    /**
     * 设置 valueexetype
     *
     * @param valueexetype valueexetype
     */
    public void setValueexetype(String valueexetype) {
        this.valueexetype = valueexetype;
    }

    /**
     * 获取 effstatus
     *
     * @return effstatus.
     */
    public String getEffstatus() {
        return effstatus;
    }

    /**
     * 设置 effstatus
     *
     * @param effstatus effstatus
     */
    public void setEffstatus(String effstatus) {
        this.effstatus = effstatus;
    }

    /**
     * 获取 remark
     *
     * @return remark.
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 remark
     *
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }


}
