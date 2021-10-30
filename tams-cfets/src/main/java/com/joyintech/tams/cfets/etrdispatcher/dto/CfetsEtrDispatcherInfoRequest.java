package com.joyintech.tams.cfets.etrdispatcher.dto;

import com.joyintech.tams.business.common.dto.PageMode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 场外指令分发
 * 指令明细请求参数DTO
 * @author sjj
 * @version 1.0 2021年10月29日
 * @see CfetsEtrDispatcherInfoRequest
 * @since 1.0
 */
@ApiModel(value="指令明细请求参数",description="指令明细请求参数")
public class CfetsEtrDispatcherInfoRequest {

    @ApiModelProperty(name="entrustId",value="指令代码",example="字符串1",required = true)
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

