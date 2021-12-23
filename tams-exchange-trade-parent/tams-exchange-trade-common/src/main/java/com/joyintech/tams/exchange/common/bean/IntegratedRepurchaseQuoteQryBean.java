package com.joyintech.tams.exchange.common.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @ClassName IntegratedRepurchaseQuoteQryBean
 * @Description TODO
 * @Author liujianping
 * @Date 2020/9/21
 * @Version
 **/
@ApiModel(value = "质押式协议回购行情请求报文", description = "质押式协议回购行情请求报文")
public class IntegratedRepurchaseQuoteQryBean {
    @ApiModelProperty(required = true, name = "actionIn", value = "行情类别（0-公开行情，1-非公开行情）")
    private int actionIn;

    @ApiModelProperty(name = "entrustProp", value = "委托属性")
    @NotBlank(message = "委托属性不能为空")
    private String entrustProp;

    @ApiModelProperty(required = true, name = "accountId", value = "网点账号")
    @NotBlank(message = "网点账号不能为空")
    private String accountId;

    @ApiModelProperty(name = "pageSize", value = "记录数")
    private int pageSize;


    public IntegratedRepurchaseQuoteQryBean(int actionIn, String entrustProp, String accountId, int pageSize) {
        this.actionIn = actionIn;
        this.entrustProp = entrustProp;
        this.accountId = accountId;
        this.pageSize = pageSize;
    }

    public int getActionIn() {
        return actionIn;
    }

    public void setActionIn(int actionIn) {
        this.actionIn = actionIn;
    }

    public String getEntrustProp() {
        return entrustProp;
    }

    public void setEntrustProp(String entrustProp) {
        this.entrustProp = entrustProp;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
