/*
 * 文件名：RskValStructureDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年12月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.penetrationmanagement.rskvalstructure.dto;

import com.joyintech.tams.common.base.entity.RskValtblStructMapDtl;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2018年12月11日
 * @see RskValStructureDto
 * @since 1.0
 */
public class RskValStructureDto extends RskValtblStructMapDtl{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 映射名称
     */
    private String structName;

    
    public String getStructName() {
        return structName;
    }

    
    public void setStructName(String structName) {
        this.structName = structName;
    }

    
}
