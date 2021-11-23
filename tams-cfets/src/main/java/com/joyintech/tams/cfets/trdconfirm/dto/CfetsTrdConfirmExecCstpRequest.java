package com.joyintech.tams.cfets.trdconfirm.dto;

import com.joyintech.tams.business.common.dto.PageMode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 银行间交易成交确认
 * 成交确认-执行与Cstp成交分页查询条件DTO
 * @author
 * @version 1.0 2021年10月26日
 * @see CfetsTrdConfirmExecCstpRequest
 * @since 1.0
 */
@ApiModel(value="查询成交确认-执行与Cstp成交分页请求数据",description="查询成交确认-执行与Cstp成交分页请求数据")
public class CfetsTrdConfirmExecCstpRequest {
    @ApiModelProperty(name="exeid",value="成交编号",example="A1")
    private String exeid;

    @ApiModelProperty(name="busiCode",value="业务编号",example="A1")
    private String busiCode;

    @ApiModelProperty(name="pageMode",value="分页对象")
    private PageMode pageMode;

    public String getBusiCode() {
        return busiCode;
    }

    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    public PageMode getPageMode() {
        return pageMode;
    }

    public void setPageMode(PageMode pageMode) {
        this.pageMode = pageMode;
    }

    public String getExeid() {
        return exeid;
    }

    public void setExeid(String exeid) {
        this.exeid = exeid;
    }
}

