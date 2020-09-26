/*
 * 文件名：XmlRelationInputParaDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.component.common.dto;

import java.io.Serializable;

import com.joyintech.framework.constant.DictCodeList;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.component.common.constant.BussinessDictCodeList;

/**
 * 数据字典转换规则 金融产品分类表字段映射关系
 * para
 *
 * @author etliu
 * @version 1.0 2018年12月8日
 * @see XmlRelationInputParaDto
 * @since 1.0
 */
public class XmlRelationInputParaDto implements Serializable {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -2009192927370942667L;

    /**
     * sysCode
     */
    private String sysCode = DictCodeList.系统代码_共通.getValue();

    /**
     * dictCode
     */
    private String dictCode;

    /**
     * itemCode
     */
    private String itemCode;

    public XmlRelationInputParaDto() {
    }

    public XmlRelationInputParaDto(BussinessDictCodeList dictCodeList) {
        this.dictCode = dictCodeList.getDictCode().getValue();
        this.itemCode = dictCodeList.getValue();
    }

    public XmlRelationInputParaDto(String dictCode, String itemCode) {
        this.dictCode = dictCode;
        this.itemCode = itemCode;
    }

    public XmlRelationInputParaDto(String sysCode, String dictCode, String itemCode) {
        this.sysCode = sysCode;
        this.dictCode = dictCode;
        this.itemCode = itemCode;
    }

    /**
     * 获取 sysCode
     *
     * @return sysCode.
     */
    public String getSysCode() {
        return sysCode;
    }

    /**
     * 设置 sysCode
     *
     * @param sysCode sysCode
     */
    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    /**
     * 获取 dictCode
     *
     * @return dictCode.
     */
    public String getDictCode() {
        return dictCode;
    }

    /**
     * 设置 dictCode
     *
     * @param dictCode dictCode
     */
    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    /**
     * 获取 itemCode
     *
     * @return itemCode.
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * 设置 itemCode
     *
     * @param itemCode itemCode
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    @Override
    public boolean equals(Object inputPara1) {
        if (inputPara1 == null || !(inputPara1 instanceof XmlRelationInputParaDto)) {
            return false;
        }

        XmlRelationInputParaDto inputParaDto1 = (XmlRelationInputParaDto) inputPara1;

        return StringUtil.equals(this.getSysCode(), inputParaDto1.getSysCode())
            && StringUtil.equals(this.getDictCode(), inputParaDto1.getDictCode())
            && StringUtil.equals(this.getItemCode(), inputParaDto1.getItemCode());
    }
}
