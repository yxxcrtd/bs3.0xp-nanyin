/*
 * 文件名：XmlRelationInputDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.dto.xmlrelation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.util.StringUtil;

/**
 * 数据字典转换规则 金融产品分类表字段映射关系 input
 *
 * @author etliu
 * @version 1.0 2018年12月10日
 * @see XmlRelationInputDto
 * @since 1.0
 */
public class XmlRelationInputDto extends Object implements Serializable {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 9008725087374641483L;
    
    /**
     * paraDtoList
     */
    private List<XmlRelationInputParaDto> paraDtoList;

	/**
     * 获取 paraDtoList
     *
     * @return paraDtoList.
     */
    public List<XmlRelationInputParaDto> getParaDtoList() {
        return paraDtoList;
    }

    /**
     * 设置 paraDtoList
     *
     * @param paraDtoList paraDtoList
     */
    public void setParaDtoList(List<XmlRelationInputParaDto> paraDtoList) {
        this.paraDtoList = paraDtoList;
    }

    @Override
    public boolean equals(Object input1) {
        if (input1 == null || !(input1 instanceof XmlRelationInputDto)) {
            return false;
        }

        XmlRelationInputDto inputParam = (XmlRelationInputDto) input1;
        
        // 删除比较对象list中参数值为空的项目
        
        List<XmlRelationInputParaDto> otherParaDtoList = new ArrayList<XmlRelationInputParaDto>();
        for (XmlRelationInputParaDto tempParaDto : inputParam.getParaDtoList()) {
            if (StringUtil.isNotBlank(tempParaDto.getItemCode())) {
                otherParaDtoList.add(tempParaDto);
            }
        }
        
        List<XmlRelationInputParaDto> thisParaDtoList = new ArrayList<XmlRelationInputParaDto>();
        for (XmlRelationInputParaDto tempParaDto : this.getParaDtoList()) {
            if (StringUtil.isNotBlank(tempParaDto.getItemCode())) {
                thisParaDtoList.add(tempParaDto);
            }
        }
        
        if (StringUtil.isEmptyList(otherParaDtoList)
            || thisParaDtoList.size() != otherParaDtoList.size()) {
            return false;
        }

        for (XmlRelationInputParaDto xmlRelationInputParaDto : thisParaDtoList) {
            if (!otherParaDtoList.contains(xmlRelationInputParaDto)) {
                return false;
            }
        }

        return true;
    }
}
