package com.joyintech.service.basicdata.mstsharebonusnotice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.MstShareBonusNotice;
import com.joyintech.workflow.engine.annotation.PrimaryKey;

import java.util.Date;
import java.util.List;

/**
 * @Classname MstShareBounsNoticeDto
 * @Description 股票分红公告
 * @author zhuhanlu 
 * @date 2020/8/6 17:03 
 * @version 1.0 
 */
public class MstShareBounsNoticeDto extends MstShareBonusNotice {


    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 5143537191208289654L;

    /**
     * 选中的数据
     */
    private transient List<MstShareBounsNoticeDto> selectedItem;
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
    public List<MstShareBounsNoticeDto> getSelectedItem() {
        return selectedItem;
    }



    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<MstShareBounsNoticeDto> selectedItem) {
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
    @PrimaryKey(value= PrimaryKey.Field.PK_2)
    public Date getRegDate() {
        return super.getRegDate();
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @Override
    public Date getRedshareListingDate() {
        return super.getRedshareListingDate();
    }


}
