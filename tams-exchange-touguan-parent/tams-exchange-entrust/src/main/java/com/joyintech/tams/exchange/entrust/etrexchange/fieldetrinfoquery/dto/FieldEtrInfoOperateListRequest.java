package com.joyintech.tams.exchange.entrust.etrexchange.fieldetrinfoquery.dto;

import com.joyintech.tams.business.common.dto.PageMode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 场内指令查询
 * 指令操作记录请求参数DTO
 * @author msk
 * @version 1.0 2021年12月22日
 * @see FieldEtrInfoOperateListRequest
 * @since 1.0
 */
@ApiModel(value="指令操作记录请求参数",description="指令操作记录请求参数")
public class FieldEtrInfoOperateListRequest {

    @ApiModelProperty(name="entrustId",value="指令代码",example="A1",required = true)
    private String entrustId;

    @ApiModelProperty(name="pageMode",value="分页对象")
    private PageMode pageMode;

    public String getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    public PageMode getPageMode() {
        return pageMode;
    }

    public void setPageMode(PageMode pageMode) {
        this.pageMode = pageMode;
    }
}

