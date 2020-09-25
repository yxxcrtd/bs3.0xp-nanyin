/*
 * 文件名：BussFinProductRevalDocumentDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod.dto;

import java.util.List;

import javax.validation.Valid;

import com.joyintech.tams.common.base.entity.PrdFile;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.component.business.prod.validator.BussFinProductRevalDocumentValidator;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author qiangchengxi
 * @version 1.0 2018年10月19日
 * @see BussFinProductRevalDocumentDto
 * @since 1.0
 */
@ValidLogic(targetClass = BussFinProductRevalDocumentValidator.class)
public class BussFinProductRevalDocumentDto extends PrdFile {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 文件名
     */
    private String fileName;
    
    /**
     * 信息披露列表    
     */
    @Valid
    private List<BussFinProductRevalDocumentDto> finProductRevalDocumentDtoList;

    /**
     * {@inheritDoc}
     */
    @Override
    @Length(fieldName="BussFinProductRevalDocumentDto.remark", max = 300)
    public String getRemark() {
        return super.getRemark();
    }
    
    /**
     * 获取 fileName
     * 
     * @return fileName.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置 fileName
     * 
     * @param fileName fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    /**
     * 获取 finProductRevalDocumentDtoList
     * @return finProductRevalDocumentDtoList.
     */
    public List<BussFinProductRevalDocumentDto> getFinProductRevalDocumentDtoList() {
        return finProductRevalDocumentDtoList;
    }
    
    /**
     * 设置 finProductRevalDocumentDtoList
     * @param finProductRevalDocumentDtoList finProductRevalDocumentDtoList
     */
    public void setFinProductRevalDocumentDtoList(List<BussFinProductRevalDocumentDto> finProductRevalDocumentDtoList) {
        this.finProductRevalDocumentDtoList = finProductRevalDocumentDtoList;
    }
}
