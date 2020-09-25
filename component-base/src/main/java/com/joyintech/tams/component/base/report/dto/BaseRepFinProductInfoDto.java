/*
 * 文件名：BaseRepFinProductInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwei
 * 修改时间：2018年9月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.base.report.dto;

import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.RepProdDeclare;
import com.joyintech.tams.common.base.entity.RepProdIssue;

/**
 * 产品端中债报备信息组件Dto 
 * 〈一句话功能简述〉 
 * 〈功能详细描述〉
 * @author huwei
 * @version 1.0 2018年9月4日
 * @see BaseRepFinProductInfoDto
 * @since 1.0
 */
public class BaseRepFinProductInfoDto {

    /**
     * 产品申报报送信息Entity
     */
    private RepProdDeclare repProdDeclare;

    /**
     * 产品发行报送信息Entity
     */
    private RepProdIssue repProdIssue;
    
    /**
     * 标的类金融产品Entity
     */
    private FinProduct finProduct;
    
    
    public FinProduct getFinProduct() {
		return finProduct;
	}

	public void setFinProduct(FinProduct finProduct) {
		this.finProduct = finProduct;
	}

	/**
     * 获取 repProdDeclare
     * 
     * @return repProdDeclare.
     */
    public RepProdDeclare getRepProdDeclare() {
        return repProdDeclare;
    }

    /**
     * 设置 repProdDeclare
     * 
     * @param repProdDeclare repProdDeclare
     */
    public void setRepProdDeclare(RepProdDeclare repProdDeclare) {
        this.repProdDeclare = repProdDeclare;
    }

    /**
     * 获取 repProdIssue
     * 
     * @return repProdIssue.
     */
    public RepProdIssue getRepProdIssue() {
        return repProdIssue;
    }

    /**
     * 设置 repProdIssue
     * 
     * @param repProdIssue repProdIssue
     */
    public void setRepProdIssue(RepProdIssue repProdIssue) {
        this.repProdIssue = repProdIssue;
    }

}
