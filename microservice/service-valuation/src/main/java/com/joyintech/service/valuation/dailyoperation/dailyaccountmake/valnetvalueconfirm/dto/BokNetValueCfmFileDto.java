/*
 * 文件名：ValFinprodTradeQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 
 * 产品资产查询Dto
 * @author 王耕耘
 * @version 1.0 2018年5月16日
 * @see BokNetValueCfmFileDto
 * @since 1.0
 */
@PageId("ValNetValueConfirm003")
public class BokNetValueCfmFileDto extends BaseEntity implements ProcessEntity {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 产品代码
     */
    private String finprodId;
    
    /**
     * 估值日期
     */
    private Date valDate;

    /**
     * 估值表余额表标志
     */
    private String valBalFlag;
    
    /**
     * 附件名称
     */
    private String fileId;

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 产品代码
     * @param finprodId 产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 估值日期
     * @return 估值日期
     */
    public Date getValDate() {
        return valDate;
    }
    
    public String getValDateS() {
        return getStringDate(valDate);
    }

    /**
     * 设置 估值日期
     * @param valDate 估值日期
     */
    public void setValDate(Date valDate) {
        this.valDate = valDate;
    }

    /**
     * 取得 估值表余额表标志
     * @return 估值表余额表标志
     */
    public String getValBalFlag() {
        return valBalFlag;
    }

    /**
     * 设置 估值表余额表标志
     * @param valBalFlag 估值表余额表标志
     */
    public void setValBalFlag(String valBalFlag) {
        this.valBalFlag = valBalFlag == null ? null : valBalFlag.trim();
    }
    
    public String getFileId() {
        return fileId;
    }
    
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**
     * 时间类型转换
     * @param date
     * @return
     */
    public String getStringDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
        }

}     				
      			
      