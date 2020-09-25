/*
 * 文件名：RepTradeRegistDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年12月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.report.debtreport.reptraderegist.dto;
import com.joyintech.tams.common.base.entity.RepTrade;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 交易要素实体类
 * @author qiangchengxi
 * @version 1.0 2018年12月29日
 * @see RepTradeRegistDto
 * @since 1.0
 */
public class RepTradeRegistDto extends RepTrade{
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 7470533937194577358L;
    /**
     * 金融产品代码
     */
    private String finprodId;
    /**
     * 金融产品名称
     */
    private String finprodName;
    /**
     * 资金流动类型数据字典
     * @return 资金流动类型
     * @see
     * @since 1.0
     */
    public String getRepMoneyFlowTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.资金流动类型, super.getRepMoneyFlowType());
    }
    
    /**
     * 币种对应的字符串
     * @return 币种中文
     * @see
     * @since 1.0
     */
    public String getCcyStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getRepCcy());
    }
    /**
     * 获取 finprodId
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }
    
    /**
     * 设置 finprodId
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }
    
    /**
     * 获取 finprodName
     * @return finprodName.
     */
    public String getFinprodName() {
        return finprodName;
    }
    
    /**
     * 设置 finprodName
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }
}
