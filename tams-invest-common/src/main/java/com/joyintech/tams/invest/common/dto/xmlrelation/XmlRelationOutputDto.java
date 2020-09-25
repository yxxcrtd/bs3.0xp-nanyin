/*
 * 文件名：XmlRelationOutputDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.dto.xmlrelation;

import java.io.Serializable;
import java.util.List;

/**
 * 数据字典转换规则 金融产品分类表字段映射关系 output
 *
 * @author etliu
 * @version 1.0 2018年12月10日
 * @see XmlRelationOutputDto
 * @since 1.0
 */
public class XmlRelationOutputDto implements Serializable  {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -9021345013320849652L;
    /**
     * resultDtoList
     */
    private List<XmlRelationOutputResultDto> resultDtoList;

    /**
     * 获取 resultDtoList
     *
     * @return resultDtoList.
     */
    public List<XmlRelationOutputResultDto> getResultDtoList() {
        return resultDtoList;
    }

    /**
     * 设置 resultDtoList
     *
     * @param resultDtoList resultDtoList
     */
    public void setResultDtoList(List<XmlRelationOutputResultDto> resultDtoList) {
        this.resultDtoList = resultDtoList;
    }

}
