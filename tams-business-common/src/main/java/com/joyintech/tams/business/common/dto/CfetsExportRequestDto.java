package com.joyintech.tams.business.common.dto;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.business.common.pojo.BaseMap;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

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

    public <T> T getDto(Class clazz) {
        Map pageMode = (Map) params.get("pageMode");
        if(pageMode != null) pageMode.put("pageSize", 500000); // 设置导出条数
        return (T) BeanUtil.mapToObject(params, clazz);
    }

    public List<BaseMap> getData() {
        return data;
    }

    public void setData(List<BaseMap> data) {
        this.data = data;
    }
}
