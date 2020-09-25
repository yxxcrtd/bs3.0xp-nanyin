/*
 * 文件名：Interf150005Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.prodquote.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.interf.prodquote.entity.Interf150005InEntity;
import com.joyintech.service.interf.prodquote.entity.Interf150005InLvlEntity;
import com.joyintech.service.interf.prodquote.entity.ParamMapEntity;

/**
 * 报价接口150005-查询
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月9日
 * @see Interf150005Mapper
 * @since 1.0
 */
@Repository
public interface Interf150005Mapper {

    /**
     * 查询净值型货币型产品估值信息
     * 
     * @param param 查询参数
     * @return 估值信息列表
     * @see
     * @since 1.0
     */
    Interf150005InEntity selectInfoFromVal(ParamMapEntity param);

    /**
     * 查询产品是否分层
     * 
     * @param finProdId 金融产品代码
     * @return 是否分层
     * @see
     * @since 1.0
     */
    String selectIsLay(@Param("finProdId") String finProdId);

    /**
     * 收益型产品信息取得
     * 
     * @param param 查询参数
     * @return 产品信息
     * @see
     * @since 1.0
     */
    Interf150005InEntity selectInfoFromCal(ParamMapEntity param);

    /**
     * 查询收益型分层产品报价信息
     * 
     * @param param 查询参数
     * @return 产品分层报价信息列表
     * @see
     * @since 1.0
     */
    List<Interf150005InLvlEntity> selectIsLayInfo(ParamMapEntity param);

    /**
     * 查询收益型非分层产品报价信息
     * 
     * @param param 查询参数
     * @return 产品报价信息列表
     * @see
     * @since 1.0
     */
    List<Interf150005InLvlEntity> selectNoLayInfo(ParamMapEntity param);

    /**
     * 取得费率
     * 
     * @param param 查询参数
     * @return 费率
     * @see
     * @since 1.0
     */
    String selectFee(ParamMapEntity param);
}
