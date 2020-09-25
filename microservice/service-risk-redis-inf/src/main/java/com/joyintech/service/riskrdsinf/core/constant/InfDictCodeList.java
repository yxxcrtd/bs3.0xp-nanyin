/*
 * 文件名：InfDictCodeList.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.core.constant;

import com.joyintech.framework.base.BaseDictCodeList;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.util.StringUtil;

/**
 * 接口数据字典明细
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月8日
 * @see InfDictCodeList
 * @since 1.0
 */
public enum InfDictCodeList implements BaseDictCodeList {
    /**
     * 操作类型_新增
     */
    操作类型_新增(
        InfDictCode.操作类型, "1", "新增"
    ),
    /**
     * 操作类型_修改
     */
    操作类型_修改(
        InfDictCode.操作类型, "2", "修改"
    ),
    /**
     * 操作类型_删除
     */
    操作类型_删除(
        InfDictCode.操作类型, "3", "删除"
    ),
    /**
     * 处理状态_成功
     */
    处理状态_成功(
        InfDictCode.处理状态, "0", "成功"
    ),
    /**
     * 处理状态_失败
     */
    处理状态_失败(
        InfDictCode.处理状态, "1", "失败"
    ),
    /**
    * 交易方向_产品收息
    */
    交易方向_产品收息(
       InfDictCode.交易方向, "T00", "产品收息"
   ),
    /**
    * 交易方向_付费
    */
    交易方向_付费(
       InfDictCode.交易方向, "T15", "付费"
   ),
    /**
    * 交易方向_正回购
    */
    交易方向_正回购(
       InfDictCode.交易方向, "T06", "正回购"
    ),
    /**
    * 交易方向_逆回购
    */
    交易方向_逆回购(
       InfDictCode.交易方向, "T07", "逆回购"
    ),
    /**
    * 交易方向_拆入
    */
    交易方向_拆入(
       InfDictCode.交易方向, "T16", "拆入"
    ),
    /**
    * 交易方向_拆出
    */
    交易方向_拆出(
       InfDictCode.交易方向, "T17", "拆出"
    ),
    /**
     * 事件类型_投资委托
     */
    事件类型_投资委托(
        InfDictCode.事件类型, "0", "投资委托"
    ),
    /**
     * 事件类型_投资交易
     */
    事件类型_投资交易(
        InfDictCode.事件类型, "1", "投资交易"
    ),
    /**
     * 事件类型_交易拆分
     */
    事件类型_交易拆分(
        InfDictCode.事件类型, "2", "交易拆分"
    ),
    /**
     * 信息类型_正常
     */
    信息类型_正常(
        InfDictCode.信息类型, "N", "正常"
    ),
    /**
     * 信息类型_错误
     */
    信息类型_错误(
        InfDictCode.信息类型, "E", "错误"
    ),
    /**
     * 信息码_提示
     */
    信息码_提示(
        InfDictCode.信息码, "P", "提示"
    ),
    /**
     * 信息码_警告
     */
    信息码_警告(
        InfDictCode.信息码, "W", "警告"
    ),
    /**
     * 信息码_拒绝
     */
    信息码_拒绝(
        InfDictCode.信息码, "R", "拒绝"
    ),
    /**
     * 投资性质_固定收益类
     */
    投资性质_固定收益类(
        InfDictCode.投资性质, "01", "固定收益类"
    ),
    /**
     * 投资性质_权益类
     */
    投资性质_权益类(
        InfDictCode.投资性质, "02", "权益类"
    ),
    /**
     * 投资性质_商品及金融衍生品类
     */
    投资性质_商品及金融衍生品类(
        InfDictCode.投资性质, "03", "商品及金融衍生品类"
    ),
    /**
     * 投资性质_混合类
     */
    投资性质_混合类(
        InfDictCode.投资性质, "04", "混合类"
    ),
    /**
     * 资产大类_项目类资产
     */
    资产大类_项目类资产(
        InfDictCode.资产大类, "06", "项目类资产"
    ),
    /**
     * 资产大类_债券质押式回购
     */
    资产大类_债券质押式回购(
        InfDictCode.资产大类, "12", "债券质押式回购"
    ),
    /**
     * 资产大类_拆借
     */
    资产大类_拆借(
        InfDictCode.资产大类, "21", "拆借"
    ),
    /**
     * 资产大类_非货币基金
     */
    资产大类_非货币基金(
        InfDictCode.资产大类, "85", "非货币基金"
    ),
    /**
     * 文件通知类型_通知文件路径
     */
    文件通知类型_通知文件路径(
        InfDictCode.文件通知类型, "file", "通知文件路径"
    ),
    /**
     * 文件通知类型_成功
     */
    文件通知处理状态_成功(
        InfDictCode.文件通知处理状态, "success", "成功"
    ),
    /**
     * 文件通知类型_失败
     */
    文件通知处理状态_失败(
        InfDictCode.文件通知处理状态, "fail", "失败"
    ),
    /**
     * 文件通知类型_反馈处理状态
     */
    文件通知类型_反馈处理状态(
        InfDictCode.文件通知类型, "reback", "反馈处理状态"
    ),;

    private IDictCode dictCode;

    private String value;

    private String codeName;

    private InfDictCodeList(IDictCode dictCode, String value, String codeName) {
        this.dictCode = dictCode;
        this.value = value;
        this.codeName = codeName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDictCode getDictCode() {
        return dictCode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCodeName() {
        return codeName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public static final boolean isType(String type, InfDictCodeList dict) {
        return StringUtil.equals(type, dict.getValue());
    }
}
