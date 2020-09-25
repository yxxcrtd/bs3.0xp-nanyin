/*
 * 文件名：BaseMstIndexInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwei
 * 修改时间：2018年9月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.base.core.dto;
/**
 * 基准信息含基准成分操作Dto 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author huwei
 * @version 1.0 2018年9月3日
 * @see BaseMstIndexInfoDto
 * @since 1.0
 */
public class BaseMstIndexInfoDto {

    /**
     * 基准编号
     */
    private String indexId;

    /**
     * 基准类型，SAA、复合等
     */
    private String indexType;

    /**
     * 行情代码
     */
    private String componentId;

    /**
     * 行情类型
     */
    private String componentType;

    /**
     * 获取 indexId
     * 
     * @return indexId.
     */
    public String getIndexId() {
        return indexId;
    }

    /**
     * 设置 indexId
     * 
     * @param indexId indexId
     */
    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }

    /**
     * 获取 indexType
     * 
     * @return indexType.
     */
    public String getIndexType() {
        return indexType;
    }

    /**
     * 设置 indexType
     * 
     * @param indexType indexType
     */
    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    /**
     * 获取 componentId
     * 
     * @return componentId.
     */
    public String getComponentId() {
        return componentId;
    }

    /**
     * 设置 componentId
     * 
     * @param componentId componentId
     */
    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    /**
     * 获取 componentType
     * 
     * @return componentType.
     */
    public String getComponentType() {
        return componentType;
    }

    /**
     * 设置 componentType
     * 
     * @param componentType componentType
     */
    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

}
