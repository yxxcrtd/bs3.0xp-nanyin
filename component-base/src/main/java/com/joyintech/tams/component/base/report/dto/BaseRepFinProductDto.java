/*
 * 文件名：BaseRepFinProductDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwei
 * 修改时间：2018年9月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.base.report.dto;

import java.util.List;

/**
 * 产品中债报备Dto 
 *〈一句话功能简述〉 
 *〈功能详细描述〉
 * @author huwei
 * @version 1.0 2018年9月4日
 * @see BaseRepFinProductDto
 * @since 1.0
 */
public class BaseRepFinProductDto {

    /**
     * List<金融产品代码>
     */
    private List<String> repFinprodIds;

    /**
     * 获取 repFinprodIds
     * 
     * @return repFinprodIds.
     */
    public List<String> getRepFinprodIds() {
        return repFinprodIds;
    }

    /**
     * 设置 repFinprodIds
     * 
     * @param repFinprodIds repFinprodIds
     */
    public void setRepFinprodIds(List<String> repFinprodIds) {
        this.repFinprodIds = repFinprodIds;
    }

}
