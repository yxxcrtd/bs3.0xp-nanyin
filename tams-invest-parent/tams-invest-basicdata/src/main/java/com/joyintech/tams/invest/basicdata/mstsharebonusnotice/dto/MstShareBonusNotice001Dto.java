package com.joyintech.tams.invest.basicdata.mstsharebonusnotice.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.MstShareBonusNotice;
import com.joyintech.tams.invest.basicdata.mstsharebonusnotice.validator.MstShareBonusNoticeValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * @Classname zhuhanlu
 * @Description TODO
 * @author zhuhanlu 
 * @date 2020/8/6 16:32 
 * @version 1.0 
 */
@PageId("MstShareBonusNotice002")
@ValidLogic(targetClass = MstShareBonusNoticeValidator.class, groups = {ValidateGroupEdit.class, ValidateGroupDelete.class})
public class MstShareBonusNotice001Dto extends MstShareBonusNotice implements ITableRow, ProcessEntity {


    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 5143537191208289654L;

    /**
     * 选中的数据
     */
    private transient List<MstShareBonusNotice001Dto> selectedItem;
    /**
     * 操作类型
     */
    private String operateType;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String pageModel;


    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String elementId;


    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<MstShareBonusNotice001Dto> getSelectedItem() {
        return selectedItem;
    }



    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<MstShareBonusNotice001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }



    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }



    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }



    /**
     * 获取 pageModel
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }



    /**
     * 设置 pageModel
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }


    /**
     * 获取 elementId
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }



    /**
     * 设置 elementId
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }


    @Override
    @NotEmpty(fieldName = "MstShareBonusNotice001.sharesId", groups = {ValidateGroupEdit.class})
    @PrimaryKey(value= PrimaryKey.Field.PK_1)
    public String getSharesId() {
        return super.getSharesId();
    }


    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @Override
    public Date getDelDate() {
        return super.getDelDate();
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @Override
    public Date getDevidentDate() {
        return super.getDevidentDate();
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @Override
    @NotEmpty(fieldName = "MstShareBonusNotice001.regDate", groups = {ValidateGroupEdit.class})
    public Date getRegDate() {
        return super.getRegDate();
    }

    @PrimaryKey(value= PrimaryKey.Field.PK_2)
    public String getRegDateStr() {
        return DateUtil.format(super.getRegDate());
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @Override
    public Date getRedshareListingDate() {
        return super.getRedshareListingDate();
    }
}
