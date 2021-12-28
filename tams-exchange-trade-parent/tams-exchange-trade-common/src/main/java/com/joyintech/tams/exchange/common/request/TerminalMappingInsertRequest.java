package com.joyintech.tams.exchange.common.request;

import com.joyintech.tams.exchange.common.db.domain.TerminalMapping;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * @author swwan
 * @date 2021/12/22 - 16:06
 */
@ApiModel(value = "添加账户终端映射信息请求对象", description = "添加账户终端映射信息请求对象")
public class TerminalMappingInsertRequest extends BaseRequest {

    @ApiModelProperty(required = true, name = "账户终端映射信息列表", value = "terminalMappingList", example = "")
    @NotEmpty(message = "账户终端映射信息列表不可为空")
    private List<TerminalMapping> terminalMappingList;

    public List<TerminalMapping> getTerminalMappingList() {
        return terminalMappingList;
    }

    public void setTerminalMappingList(List<TerminalMapping> terminalMappingList) {
        this.terminalMappingList = terminalMappingList;
    }
}
