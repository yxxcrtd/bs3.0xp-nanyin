package com.joyintech.tams.cfets.cfetsetrinfoquery.dto;

import com.joyintech.tams.business.common.dto.PageMode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 银行间指令查询
 * 指令操作记录请求参数DTO
 * @author sjj
 * @version 1.0 2021年11月25日
 * @see CfetsEtrInfoOperateListRequest
 * @since 1.0
 */
@ApiModel(value="指令操作记录请求参数",description="指令操作记录请求参数")
public class CfetsEtrInfoOperateListRequest {

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

