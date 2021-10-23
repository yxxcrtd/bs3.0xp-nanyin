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
public class ExportRequestDto extends BaseService {

    @ApiModelProperty(name="type",value="excel导出方式",example="checked|condition", required = true)
    private String type;
    
    @ApiModelProperty(name="head",value="excel导出表头",example="[{field:'id', name:'编号'}]", required = true)
    private List<BaseMap> header;

    @ApiModelProperty(name="data",value="导出数据",example="[{...}..] | {...}", required = true)
    private Object data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<BaseMap> getHeader() {
        return header;
    }

    public void setHeader(List<BaseMap> header) {
        this.header = header;
    }

    public Object getData() {
        return data;
    }

    public <T> T getRequestDto(Class clazz) {
        String str = JsonUtils.object2Json(data);
        return (T) JsonUtils.json2Object(str, clazz);
    }

    public void setData(Object data) {
        this.data = data;
    }
    
}
