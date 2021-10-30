package com.joyintech.tams.cfets.etrdispatcher.dto;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 场外指令分发
 * 自动分发请求参数DTO
 * @author sjj
 * @version 1.0 2021年10月26日
 * @see CfetsEtrDispatcherTraderAuthListResponse
 * @since 1.0
 */
@ApiModel(value="分发权限配置交易员返回数据",description="分发权限配置交易员返回数据")
public class CfetsEtrDispatcherTraderAuthListResponse {

    @ApiModelProperty(name="userName",value="用户名",example="字符串1")
    private String userName;

    @ApiModelProperty(name="realName",value="真实姓名",example="字符串1")
    private String realName;

    @ApiModelProperty(name="finprodType2",value="投资品",example="F01")
    private String finprodType2;

    @ApiModelProperty(name="otherfinprodType2",value="投资品",example="字符串1")
    private String otherfinprodType2;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getFinprodType2() {
        return finprodType2;
    }

    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }

    /**
     * 获取 otherFinprodType2
     * @return otherFinprodType2.
     */
    public String getOtherFinprodType2() {
        if (StringUtil.equals(this.getFinprodType2(), BussinessDictCodeList.金融产品类型_投管用_一级投标债券.getValue())){
            return SysDataUtil.getNameFromDataDict(DictCode.平安资产类型, BussinessDictCodeList.平安资产类型_一级投标债券.getValue());
        }
        return SysDataUtil.getNameFromDataDict(DictCode.平安资产类型, this.getFinprodType2());
    }

    public void setOtherfinprodType2(String otherfinprodType2) {
        this.otherfinprodType2 = otherfinprodType2;
    }
}

