/*
 * 文件名：FileDisposeResultEntitys.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：lihuadong
 * 修改时间：2017年2月6日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.interf.interf.common.dto;

import java.util.List;

/**
 * 文件处理结果
 * 
 * @author zhangchao
 * @version 1.0 2017年2月6日
 * @see FileDisposeResultEntitys
 * @since 1.0
 */
public class FileDisposeResults {

    /**
     * 文件成功数
     */
    private Integer fileSuccendState;

    /**
     * 文件失败数
     */
    private Integer fileDefeatState;

    /**
     * 结果消息
     */
    private String message;

    /**
     * 文件处理结果LIST
     */
    private List<PerFileDisposeResult> fileResultList;

    /**
     * 获取 message
     * 
     * @return message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置 message
     * 
     * @param message message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取 fileSuccendState
     * 
     * @return fileSuccendState.
     */
    public Integer getFileSuccendState() {
        return fileSuccendState;
    }

    /**
     * 设置 fileSuccendState
     * 
     * @param fileSuccendState fileSuccendState
     */
    public void setFileSuccendState(Integer fileSuccendState) {
        this.fileSuccendState = fileSuccendState;
    }

    /**
     * 获取 fileDefeatState
     * 
     * @return fileDefeatState.
     */
    public Integer getFileDefeatState() {
        return fileDefeatState;
    }

    /**
     * 设置 fileDefeatState
     * 
     * @param fileDefeatState fileDefeatState
     */
    public void setFileDefeatState(Integer fileDefeatState) {
        this.fileDefeatState = fileDefeatState;
    }

    /**
     * 获取 fileResultList
     * 
     * @return fileResultList.
     */
    public List<PerFileDisposeResult> getFileResultList() {
        return fileResultList;
    }

    /**
     * 设置 fileResultList
     * 
     * @param fileResultList fileResultList
     */
    public void setFileResultList(List<PerFileDisposeResult> fileResultList) {
        this.fileResultList = fileResultList;
    }

}
