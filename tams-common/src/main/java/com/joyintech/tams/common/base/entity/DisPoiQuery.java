package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class DisPoiQuery extends BaseEntity {
    /**
     * 主键
     */
    private String id;

    /**
     * 查询条件编号
     */
    private String poiId;

    /**
     * 查询条件类型(02-主表,01-子表)
     */
    private String poiType;

    /**
     * 模板序号
     */
    private String poiOrder;

    /**
     * 查询条件sql(sql参照阈值验证)
     */
    private String poiSql;

    /**
     * 取得 主键
     * @return 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 主键
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 取得 查询条件编号
     * @return 查询条件编号
     */
    public String getPoiId() {
        return poiId;
    }

    /**
     * 设置 查询条件编号
     * @param poiId 查询条件编号
     */
    public void setPoiId(String poiId) {
        this.poiId = poiId == null ? null : poiId.trim();
    }

    /**
     * 取得 查询条件类型(02-主表,01-子表)
     * @return 查询条件类型(02-主表,01-子表)
     */
    public String getPoiType() {
        return poiType;
    }

    /**
     * 设置 查询条件类型(02-主表,01-子表)
     * @param poiType 查询条件类型(02-主表,01-子表)
     */
    public void setPoiType(String poiType) {
        this.poiType = poiType == null ? null : poiType.trim();
    }

    /**
     * 取得 模板序号
     * @return 模板序号
     */
    public String getPoiOrder() {
        return poiOrder;
    }

    /**
     * 设置 模板序号
     * @param poiOrder 模板序号
     */
    public void setPoiOrder(String poiOrder) {
        this.poiOrder = poiOrder == null ? null : poiOrder.trim();
    }

    /**
     * 取得 查询条件sql(sql参照阈值验证)
     * @return 查询条件sql(sql参照阈值验证)
     */
    public String getPoiSql() {
        return poiSql;
    }

    /**
     * 设置 查询条件sql(sql参照阈值验证)
     * @param poiSql 查询条件sql(sql参照阈值验证)
     */
    public void setPoiSql(String poiSql) {
        this.poiSql = poiSql == null ? null : poiSql.trim();
    }
}