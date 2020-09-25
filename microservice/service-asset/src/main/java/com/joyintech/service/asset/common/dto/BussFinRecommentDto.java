/*
 * 文件名：BussFinRecommentDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.asset.common.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.FinProductRecomment;

/**
 * 资产推荐方及费率信息
 * 
 * @author renping
 * @version 1.0 2019年10月10日
 * @see BussFinRecommentDto
 * @since 1.0
 */
public class BussFinRecommentDto extends FinProductRecomment{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 19012045654111L;

    /**
     * 资产推荐方及费率信息
     */
    private List<FinProductRecomment> finProductRecommentList;

    /**
     * 获取 finProductRecommentList
     * 
     * @return finProductRecommentList.
     */
    public List<FinProductRecomment> getFinProductRecommentList() {
        return finProductRecommentList;
    }

    /**
     * 设置 finProductRecommentList
     * 
     * @param finProductRecommentList finProductRecommentList
     */
    public void setFinProductRecommentList(List<FinProductRecomment> finProductRecommentList) {
        this.finProductRecommentList = finProductRecommentList;
    }
}
