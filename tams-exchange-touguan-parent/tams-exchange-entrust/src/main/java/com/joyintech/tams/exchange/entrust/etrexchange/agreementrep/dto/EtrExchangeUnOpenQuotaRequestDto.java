/*
 * 文件名：EtrExchangeTradeDto002.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2020年7月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.exchange.entrust.etrexchange.agreementrep.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.business.common.dto.PageMode;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.systemtools.util.SysDatadictUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author lby
 * @version 1.0 2020年9月17日
 * @see EtrExchangeUnOpenQuotaRequestDto
 * @since 1.0
 */
@ApiModel(value="非公开行情查询请求参数",description="非公开行情查询请求参数")
public class EtrExchangeUnOpenQuotaRequestDto {
    @ApiModelProperty(name="pageMode",value="分页对象")
    private PageMode pageMode;

    public PageMode getPageMode() {
        return pageMode;
    }

    public void setPageMode(PageMode pageMode) {
        this.pageMode = pageMode;
    }
}
