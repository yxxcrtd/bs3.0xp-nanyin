package com.joyintech.interf.bankname.cfetsup.constant;

import com.joyintech.framework.base.intf.DataDict;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.dict.dto.SysDataDictItem;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * 上行接口数据字典明细枚举
 *
 * @author zcf
 * @version 1.0
 * @date 2020-01-08
 */
public enum FxDictCodeList {

    /**
     * 质押式回购报价交易方向 RS:正回购 RB:逆回购
     */
    交易方向_正回购(FxDictCode.交易方向,"RS","正回购"),
    /**
     * 交易方向 RS:正回购 RB:逆回购
     */
    交易方向_逆回购(FxDictCode.交易方向,"RB","逆回购"),
    /**
     * 结算方式_见券付款
     */
    结算方式_见券付款(FxDictCode.结算方式,"01","见券付款"),
    /**
     * 结算方式_见款付券
     */
    结算方式_见款付券(FxDictCode.结算方式,"02","见款付券"),
    /**
     * 结算方式_券款对付
     */
    结算方式_券款对付(FxDictCode.结算方式,"03","券款对付"),
    /**
     * 结算方式_双边全额清算
     */
    结算方式_双边全额清算(FxDictCode.结算方式,"BIL-SETT","双边全额清算"),
    /**
     * 结算方式_集中净额清算
     */
    结算方式_集中净额清算(FxDictCode.结算方式,"CENT-SETT","集中净额清算"),
    /**
     * 结算方式_差额清算
     */
    结算方式_差额清算(FxDictCode.结算方式,"DIF-SETT","差额清算"),
    /**
     * 本方角色_询价方
     */
    本方角色_询价方(FxDictCode.本方角色,"0","询价方"),
    /**
     * 本方角色_确认方
     */
    本方角色_确认方(FxDictCode.本方角色,"1","确认方"),
    /**
     * 交易状态_待回复
     */
    交易状态_待回复(FxDictCode.交易状态,"0","待回复"),
    /**
     * 交易状态_已成交
     */
    交易状态_已成交(FxDictCode.交易状态,"1","已成交"),
    /**
     * 交易状态_已过期
     */
    交易状态_已过期(FxDictCode.交易状态,"2","已过期"),
    /**
     * 交易状态_已拒绝
     */
    交易状态_已拒绝(FxDictCode.交易状态,"3","已拒绝"),
    /**
     * 发送状态_成功
     */
    发送状态_成功(FxDictCode.发送状态,"0","成功"),
    /**
     * 发送状态_失败
     */
    发送状态_失败(FxDictCode.发送状态,"1","失败"),
    /**
     * 发送状态_再次发送成功
     */
    发送状态_再次发送成功(FxDictCode.发送状态,"2","再次发送成功"),
    /**
     * 发送状态_再次发送成功
     */
    有效状态_新建(FxDictCode.有效状态,"A","新建"),
    /**
     * 发送状态_再次发送成功
     */
    有效状态_有效(FxDictCode.有效状态,"E","有效"),
    /**
     * 发送状态_再次发送成功
     */
    有效状态_删除(FxDictCode.有效状态,"D","删除"),
    /**
     * 清算类型_02
     */
    清算类型_净额清算(FxDictCode.清算类型,"0","清算类型_净额清算"),
    /**
     * 清算类型_02
     */
    清算类型_全额清算(FxDictCode.清算类型,"1","清算类型_全额清算"),

    /**
     * 共通业务类型_质押式回购报价
     */
    共通业务类型_质押式回购报价(FxDictCode.共通业务类型,"QUOTE01","共通业务类型_质押式回购报价"),

    /**
     * 共通业务类型_现券买卖对话报价
     */
    共通业务类型_现券买卖对话报价(FxDictCode.共通业务类型,"QUOTE02","共通业务类型_现券买卖对话报价"),
    /**
     * 共通业务类型_现券买卖对话报价
     */
    共通业务类型_现券买卖请求报价(FxDictCode.共通业务类型,"QUOTE03","共通业务类型_现券买卖请求报价"),

    /**
     * 现券买卖对话报价交易方向
     */
    交易方向_买入(FxDictCode.交易方向_现券,"P","买入"),
    /**
     * 现券买卖对话报价交易方向
     */
    交易方向_卖出(FxDictCode.交易方向_现券,"S","卖出"),

    /**
     * 发送类型
     */
    发送类型_发送类型1(FxDictCode.发送类型,"1","发送类型1"),

    发送类型_发送类型2(FxDictCode.发送类型,"2","发送类型2"),

    /**
     * 清算类型
     */
    清算类型_清算类型1(FxDictCode.清算类型_现券,"1","清算类型1"),

    清算类型_清算类型2(FxDictCode.清算类型_现券,"2","清算类型2"),

    /**
     *  回复报价_操作类型
     */
    回复报价_操作类型_成交(FxDictCode.回复报价_操作类型,"1","成交"),
    回复报价_操作类型_终止(FxDictCode.回复报价_操作类型,"2","终止"),
    ;
    /**
     * 字典代码
     */
    private FxDictCode dictCode;

    /**
     * 数据代码
     */
    private String itemCode;

    /**
     * 参考值
     */
    private String itemValue;

    FxDictCodeList(FxDictCode dictCode,String itemCode,String itemValue){
        this.dictCode = dictCode;
        this.itemCode = itemCode;
        this.itemValue = itemValue;
    }

    /**
     * 获得数据字典详细类
     *
     * @param value 数据字典值
     * @param dictCode 数据字典DictCode
     * @return DictCodeList
     * @see
     * @since 1.0
     */
    public static FxDictCodeList from(FxDictCode dictCode, String value) {
        FxDictCodeList[] values = FxDictCodeList.values();
        for (FxDictCodeList code : values) {
            if (StringUtil.equals(value, code.getItemCode()) && code.getDictCode() == dictCode) {
                return code;
            }
        }
        return null;
    }

    public static List<SelectOptionDto> fromDataBase(String dictCode){
        DataDict dataDict = CacheUtil.getDictCode(dictCode);
        List<? extends DataDictItem> list = dataDict.getItems();
        List<SelectOptionDto> list1 = new ArrayList<>();
        list.forEach(item ->{
            SelectOptionDto dto = new SelectOptionDto();
            dto.setText(item.getItemValue());
            dto.setId(item.getItemCode());
            list1.add(dto);
        });
        return list1;
    }
    /**
     * 获取数据字典明细List
     *
     * @param fxDictCode
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public static List<SelectOptionDto> fromDictCode(FxDictCode fxDictCode) {
        List<SelectOptionDto> result = new ArrayList<>();
        FxDictCodeList[] values = FxDictCodeList.values();
        for (FxDictCodeList code : values) {
            if (code.getDictCode().getCode().equals(fxDictCode.getCode())) {
                SelectOptionDto option = new SelectOptionDto();
                option.setId(code.getItemCode());
                option.setText(code.getItemValue());
                result.add(option);
            }
        }
        return result;
    }

    public FxDictCode getDictCode() {
        return dictCode;
    }

    public void setDictCode(FxDictCode dictCode) {
        this.dictCode = dictCode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }
}
