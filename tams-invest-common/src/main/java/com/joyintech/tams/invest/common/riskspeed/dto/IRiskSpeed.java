/*
 * 文件名：IPtlProd.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.riskspeed.dto;

import com.joyintech.tams.constant.DictCodeListExt2;

import java.util.List;

/**
 * 风险接口
 * 〈功能详细描述〉
 * @author 裴家俊
 * @version 1.0 2019年10月11日
 * @see IRiskSpeed
 * @since 1.0
 */
public interface IRiskSpeed extends IRiskBase{

    /**
     * 取得 资产代码
     * @return 资产代码
     */
    public String getAssetCode();

    /**
     * 取得 试算日期
     * @return 试算日期
     */
    public String getValDate();

    /**
     * 取得 资产二级类型
     * @return 资产二级类型
     */
    public String getAssetSecType() ;

    /**
     * 取得 资产三级类型
     * @return 资产三级类型
     */
    public String getAssetTrdType() ;

    /**
     * 阈值方向
     * @return
     */
    default String getThreshDirection(){
        return DictCodeListExt2.运算符_大于.getValue();
    }

    /**
     * 取得各指标结果
     * @return
     */
    List<IRiskSpeed> getResultList();

    /**
     * 设置 各指标结果
     * @return
     */
    void setResultList(List<IRiskSpeed> mapList);

    /**
     * 取得 指标名称
     * @return 指标名称
     */
    String getIndName();
    /**
     * 取得 组合、组合包名称
     * @return 指标名称
     */
    String getPackageName();
    void  setPackageName(String packageName);
    /**
     * 取得 指标类型
     * @return 指标类型
     */
    String getIndType();
    void setIndType(String indType);

    /**
     * 取得 指标名称
     * @return 指标名称
     */
    void setIndName(String indCode);

    /**
     * 取得 指标代码
     * @return 指标代码
     */
    String getIndCode() ;

    /**
     * 取得 指标代码
     * @return 指标代码
     */
    void setIndCode(String indCode) ;

    /**
     * 取得 违规条件
     * @return 违规条件
     */
    String getFoulCond() ;

    /**
     * 取得 违规条件
     * @return 违规条件
     */
    void setFoulCond(String foulCode) ;

    /**
     * 取得 总可投金额
     * @return 总可投金额
     */
    Object getTotalAmt();
    /**
     * 设置 总可投金额
     * @return
     */
    void setTotalAmt(Object val) ;

    /**
     * 取得 已投金额
     * @return 已投金额
     */
    Object getHasAmt();
    /**
     * 设置 已投金额
     * @return
     */
    void setHasAmt(Object val) ;


}
