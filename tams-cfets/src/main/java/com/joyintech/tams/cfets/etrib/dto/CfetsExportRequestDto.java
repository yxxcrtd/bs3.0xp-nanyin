package com.joyintech.tams.cfets.etrib.dto;

import com.github.pagehelper.ISelect;
import com.joyintech.background.component.common.pack.common.util.JsonUtils;
import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.business.common.pojo.BaseMap;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * excel导出请求参数
 */
@ApiModel(value="excel导出请求参数",description="excel导出请求参数")
public class CfetsExportRequestDto {

    @ApiModelProperty(name="params",value="条件",example="{...}", required = true)
    private BaseMap params;
    
    @ApiModelProperty(name="header",value="excel导出表头",example="[{colCode:'id', colName:'编号'}]", required = true)
    private List<BaseMap> header;

    @ApiModelProperty(name="data",value="导出数据",example="[{...}..] | {...}", required = true)
    private List<BaseMap> data;

    public BaseMap getParams() {
        return params;
    }

    public void setParams(BaseMap params) {
        this.params = params;
    }

    public List<BaseMap> getHeader() {
        return header;
    }

    public void setHeader(List<BaseMap> header) {
        this.header = header;
    }

    public <T> T getRequestDto(Class clazz) {
        String str = JsonUtils.object2Json(params);
        return (T) JsonUtils.json2Object(str, clazz);
    }

    public List<BaseMap> getData() {
        return data;
    }

    public void setData(List<BaseMap> data) {
        this.data = data;
    }
}
