package com.joyintech.tams.disclosure.information.config.dispoiquery.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.disclosure.information.config.dispoiquery.validator.DisPoiQuery001Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

/**
 * poi查询sql
 *
 * @author kx
 */
@PageId("DisPoiQuery002")
@ValidLogic(targetClass = DisPoiQuery001Validator.class, groups = {ValidateGroupEdit.class, ValidateGroupDelete.class})
public class DisPoiQuery001Dto extends BaseEntity implements ProcessEntity {
    /**
     * 主键
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    private String id;

    /**
     * 关联编号
     */
    private String poiId;

    /**
     * 关联类型(02-主表,01-子表)
     */
    private String poiType;

    /**
     * 序号
     */
    private String poiOrder;

    /**
     * 查询条件sql(sql参照阈值验证)
     */
    private String poiSql;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 选中的数据
     */
    private List<DisPoiQuery001Dto> selectedItem;

    /**
     * 关联类型文字版
     */
    private String poiTypeStr;


    /**
     * 取得 主键
     *
     * @return 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 取得 关联编号
     *
     * @return 关联编号
     */
    public String getPoiId() {
        return poiId;
    }

    /**
     * 设置 关联编号
     *
     * @param poiId 关联编号
     */
    public void setPoiId(String poiId) {
        this.poiId = poiId == null ? null : poiId.trim();
    }

    /**
     * 取得 关联类型(02-主表,01-子表)
     *
     * @return 关联类型(02 - 主表, 01 - 子表)
     */
    public String getPoiType() {
        return poiType;
    }

    /**
     * 设置 关联类型(02-主表,01-子表)
     *
     * @param poiType 关联类型(02-主表,01-子表)
     */
    public void setPoiType(String poiType) {
        this.poiType = poiType == null ? null : poiType.trim();
    }

    /**
     * 取得 序号
     *
     * @return 序号
     */
    public String getPoiOrder() {
        return poiOrder;
    }

    /**
     * 设置 序号
     *
     * @param poiOrder 序号
     */
    public void setPoiOrder(String poiOrder) {
        this.poiOrder = poiOrder == null ? null : poiOrder.trim();
    }

    /**
     * 取得 查询条件sql(sql参照阈值验证)
     *
     * @return 查询条件sql(sql参照阈值验证)
     */
    public String getPoiSql() {
        return poiSql;
    }

    /**
     * 设置 查询条件sql(sql参照阈值验证)
     *
     * @param poiSql 查询条件sql(sql参照阈值验证)
     */
    public void setPoiSql(String poiSql) {
        this.poiSql = poiSql == null ? null : poiSql.trim();
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public List<DisPoiQuery001Dto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<DisPoiQuery001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getPoiTypeStr() {
        return poiTypeStr;
    }

    public void setPoiTypeStr(String poiTypeStr) {
        this.poiTypeStr = poiTypeStr;
    }
}