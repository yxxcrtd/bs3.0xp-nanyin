/*
 * 文件名：XmlRelationRecordDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.dto.xmlrelation;

import java.io.Serializable;

/**
 * 数据字典转换规则 金融产品分类表字段映射关系 record
 *
 * @author etliu
 * @version 1.0 2018年12月8日
 * @see XmlRelationRecordDto
 * @since 1.0
 */
public class XmlRelationRecordDto implements Serializable {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -4495430971898875589L;

    /**
     * id
     */
    private String id;

    /**
     * input
     */
    private XmlRelationInputDto input;

    /**
     * output
     */
    private XmlRelationOutputDto output;

    /**
     * 获取 id
     *
     * @return id.
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 id
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取 input
     *
     * @return input.
     */
    public XmlRelationInputDto getInput() {
        return input;
    }

    /**
     * 设置 input
     *
     * @param input input
     */
    public void setInput(XmlRelationInputDto input) {
        this.input = input;
    }

    /**
     * 获取 output
     *
     * @return output.
     */
    public XmlRelationOutputDto getOutput() {
        return output;
    }

    /**
     * 设置 output
     *
     * @param output output
     */
    public void setOutput(XmlRelationOutputDto output) {
        this.output = output;
    }

}
