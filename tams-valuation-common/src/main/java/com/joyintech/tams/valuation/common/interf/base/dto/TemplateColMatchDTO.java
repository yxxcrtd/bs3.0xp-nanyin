/*
 * 文件名：TemplateColMatchDTO.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：chenggaoling
 * 修改时间：2018年9月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.common.interf.base.dto;

/**
 * 模版与DTO字段匹配DTO
 * 
 * @author chenggaoling
 * @version 1.0 2018年9月3日
 * @see TemplateColMatchDTO
 * @since 1.0
 */
public class TemplateColMatchDTO {

    /**
     * 模版中文名称
     */
    private String tmpCName;
    /**
     * 模版英文名称
     */
    private String tmpEName;
    /**
     * DTO名称
     */
    private String dtoName;
    /**
     * 模版字段中文名
     */
    private String tmpColCName;
    /**
     * 模版字段英文名
     */
    private String tmpColEName;
    /**
     * DTO字段中文名
     */
    private String dtoColCName;
    /**
     * DTO字段英文名
     */
    private String dtoColEName;
    /**
     * 模版数据下标
     */
    private Integer index;
    
    /**
     * 获取 tmpCName
     * @return tmpCName.
     */
    public String getTmpCName() {
        return tmpCName;
    }
    
    /**
     * 设置 tmpCName
     * @param tmpCName tmpCName
     */
    public void setTmpCName(String tmpCName) {
        this.tmpCName = tmpCName;
    }
    
    /**
     * 获取 tmpEName
     * @return tmpEName.
     */
    public String getTmpEName() {
        return tmpEName;
    }
    
    /**
     * 设置 tmpEName
     * @param tmpEName tmpEName
     */
    public void setTmpEName(String tmpEName) {
        this.tmpEName = tmpEName;
    }
    
    /**
     * 获取 dtoName
     * @return dtoName.
     */
    public String getDtoName() {
        return dtoName;
    }
    
    /**
     * 设置 dtoName
     * @param dtoName dtoName
     */
    public void setDtoName(String dtoName) {
        this.dtoName = dtoName;
    }
    
    /**
     * 获取 tmpColCName
     * @return tmpColCName.
     */
    public String getTmpColCName() {
        return tmpColCName;
    }
    
    /**
     * 设置 tmpColCName
     * @param tmpColCName tmpColCName
     */
    public void setTmpColCName(String tmpColCName) {
        this.tmpColCName = tmpColCName;
    }
    
    /**
     * 获取 tmpColEName
     * @return tmpColEName.
     */
    public String getTmpColEName() {
        return tmpColEName;
    }
    
    /**
     * 设置 tmpColEName
     * @param tmpColEName tmpColEName
     */
    public void setTmpColEName(String tmpColEName) {
        this.tmpColEName = tmpColEName;
    }
    
    /**
     * 获取 dtoColCName
     * @return dtoColCName.
     */
    public String getDtoColCName() {
        return dtoColCName;
    }
    
    /**
     * 设置 dtoColCName
     * @param dtoColCName dtoColCName
     */
    public void setDtoColCName(String dtoColCName) {
        this.dtoColCName = dtoColCName;
    }
    
    /**
     * 获取 dtoColEName
     * @return dtoColEName.
     */
    public String getDtoColEName() {
        return dtoColEName;
    }
    
    /**
     * 设置 dtoColEName
     * @param dtoColEName dtoColEName
     */
    public void setDtoColEName(String dtoColEName) {
        this.dtoColEName = dtoColEName;
    }

    
    /**
     * 获取 index
     * @return index.
     */
    public Integer getIndex() {
        return index;
    }

    
    /**
     * 设置 index
     * @param index index
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

}
