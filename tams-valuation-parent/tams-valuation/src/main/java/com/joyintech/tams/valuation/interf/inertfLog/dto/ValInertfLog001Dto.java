/*
 * 文件名：ValInertfLog001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年6月14日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.inertfLog.dto;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 接口日志
 * 〈功能详细描述〉
 * @author HuangJian
 * @version 1.0 2018年6月14日
 * @see ValInertfLog001Dto
 * @since 1.0
 */
public class ValInertfLog001Dto extends BaseEntity {
    /**
     * Y:是;N:否
     */
    private String isCheck;

    /**
     * 校验内容
     */
    private String detail;

    /**
     * 备注
     */
    private String remark;
    
    /**
     * 模板名称
     */
    private String tmpName;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 处理日期
     */
    private String exDate;

    /**
     * 错误所在文件行号
     */
    private String num;
    
    /**
     * 获取 isCheck
     * @return isCheck.
     */
    public String getIsCheck() {
        return isCheck;
    }

    public String getIsCheckName() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否, isCheck);
    }
    /**
     * 获取 detail
     * @return detail.
     */
    public String getDetail() {
        return detail;
    }

    
    /**
     * 设置 detail
     * @param detail detail
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    
    /**
     * 获取 remark
     * @return remark.
     */
    public String getRemark() {
        return remark;
    }

    
    /**
     * 设置 remark
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    
    /**
     * 获取 tmpName
     * @return tmpName.
     */
    public String getTmpName() {
        return tmpName;
    }

    
    /**
     * 设置 tmpName
     * @param tmpName tmpName
     */
    public void setTmpName(String tmpName) {
        this.tmpName = tmpName;
    }

    
    /**
     * 获取 fileName
     * @return fileName.
     */
    public String getFileName() {
        return fileName;
    }

    
    /**
     * 设置 fileName
     * @param fileName fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    
    /**
     * 获取 exDate
     * @return exDate.
     */
    public String getExDate() {
        return exDate;
    }

    
    /**
     * 设置 exDate
     * @param exDate exDate
     */
    public void setExDate(Date exDate) {
        this.exDate = DateUtil.format(exDate);
    }

    
    /**
     * 获取 num
     * @return num.
     */
    public String getNum() {
        return num;
    }

    
    /**
     * 设置 num
     * @param num num
     */
    public void setNum(Long num) {
        this.num = num.toString();
    }

    
    /**
     * 设置 isCheck
     * @param isCheck isCheck
     */
    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }

    
   
    
}
