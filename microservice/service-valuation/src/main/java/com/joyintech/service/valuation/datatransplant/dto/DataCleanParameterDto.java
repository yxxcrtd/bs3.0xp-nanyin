/*
 * 文件名：DataCleanParameterDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年11月12日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.datatransplant.dto;

import java.util.Date;

/**
 * 数据移植 - 数据清空条件实体类
 * 
 * @author yangjian
 * @version 1.0 2018年11月12日
 * @see DataCleanParameterDto
 * @since 1.0
 */
public class DataCleanParameterDto {

    /**
     * 账套代码（数组）
     */
    private String[] booksetIdArr;

    /**
     * 默模版类型
     */
    private String modelType;

    /**
     * 删除开始时间
     */
    private Date deleteDateBegin;

    /**
     * 删除结束时间
     */
    private Date deleteDateEnd;

    /**
     * 创建用户
     */
    private String createUser;

    /**
     * 更新用户
     */
    private String updatUser;

    /**
     * 获取 booksetIdArr
     * 
     * @return booksetIdArr.
     */
    public String[] getBooksetIdArr() {
        return booksetIdArr;
    }

    /**
     * 设置 booksetIdArr
     * 
     * @param booksetIdArr booksetIdArr
     */
    public void setBooksetIdArr(String[] booksetIdArr) {
        this.booksetIdArr = booksetIdArr;
    }

    /**
     * 获取 modelType
     * 
     * @return modelType.
     */
    public String getModelType() {
        return modelType;
    }

    /**
     * 设置 modelType
     * 
     * @param modelType modelType
     */
    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    /**
     * 获取 deleteDateBegin
     * 
     * @return deleteDateBegin.
     */
    public Date getDeleteDateBegin() {
        return deleteDateBegin;
    }

    /**
     * 设置 deleteDateBegin
     * 
     * @param deleteDateBegin deleteDateBegin
     */
    public void setDeleteDateBegin(Date deleteDateBegin) {
        this.deleteDateBegin = deleteDateBegin;
    }

    /**
     * 获取 deleteDateEnd
     * 
     * @return deleteDateEnd.
     */
    public Date getDeleteDateEnd() {
        return deleteDateEnd;
    }

    /**
     * 设置 deleteDateEnd
     * 
     * @param deleteDateEnd deleteDateEnd
     */
    public void setDeleteDateEnd(Date deleteDateEnd) {
        this.deleteDateEnd = deleteDateEnd;
    }

    /**
     * 获取 createUser
     * 
     * @return createUser.
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置 createUser
     * 
     * @param createUser createUser
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取 updatUser
     * 
     * @return updatUser.
     */
    public String getUpdatUser() {
        return updatUser;
    }

    /**
     * 设置 updatUser
     * 
     * @param updatUser updatUser
     */
    public void setUpdatUser(String updatUser) {
        this.updatUser = updatUser;
    }

}
