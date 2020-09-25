///*
// * 文件名：MstTradeOpponent002Mapper.java
// * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
// * 描述：理财资管管理系统V2.0
// * 修改人：fanshuanghu
// * 修改时间：2018年8月23日
// * 修改内容：新建
// * 系统名称：理财资管管理系统V2.0
// */
//
package com.joyintech.service.product.common.dao;
//
//import java.util.List;
//import java.util.Map;
//
//import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
//
//import com.joyintech.tams.common.base.entity.MstCounterSecurityAcct;
//import com.joyintech.tams.common.base.entity.StlCapacctInfo;
//import com.joyintech.service.product.common.dto.FinCashDto;
//import com.joyintech.service.product.common.dto.MstTradeOpponentDto;
//import com.joyintech.service.product.common.dto.StlCapacctDto;
//
//
///**
// * 交易对手新建
// * @author fanshuanghu
// * @version 1.0 2018年8月23日
// * @see MstTradeOpponent002Mapper
// * @since 1.0
// */
@Repository
public interface PrdMstTradeOpponent002Mapper {
//    /**
//     * 数据插入交易对手账户表
//     * @param dto 交易对手账户信息
//     * @return 是否插入成功
//     * @see
//     * @since 1.0
//     */
//    int insertCounterAcct(StlCapacctDto dto);
//    /**
//     * 获取币种信息
//     * @param acctNo 网店账号
//     * @return 币种
//     * @see
//     * @since 1.0
//     */
//    String selectCcy(@Param("acctNo") String acctNo);
//    /**
//     * 插入到标的类金融产品表
//     * @param dto 交易对手实体类
//     * @return 是否成功
//     * @see
//     * @since 1.0
//     */
//    int insertFinProduct(MstTradeOpponentDto dto);
//    /**
//     * 插入到金融产品分类表
//     * @param dto 交易对手实体类
//     * @return 是否成功
//     * @see
//     * @since 1.0
//     */
//    int insertFinProductType(MstTradeOpponentDto dto);
//    /**
//     * 现金流信息表
//     * @param dto 现金流信息实体类
//     * @return 是否成功
//     * @see
//     * @since 1.0
//     */
//    int insertFinCashInfo(FinCashDto dto);
//    /**
//     * 现金流利率信息表
//     * @param dto 现金流信息实体类
//     * @return 是否成功
//     * @see
//     * @since 1.0
//     */
//    int insertFinRateInfo(FinCashDto dto);
//    /**
//     * 计算规则表
//     * @param dto 现金流信息实体类
//     * @return 是否成功
//     * @see
//     * @since 1.0
//     */
//    int insertFinCashCalcRule(FinCashDto dto);
//    /**
//     * 资产管理计划报送信息(通道计划)表
//     * @param dto 交易对手实体类
//     * @return 是否成功
//     * @see
//     * @since 1.0
//     */
//    int insertRepAssetMngPlan(MstTradeOpponentDto dto);
//    /**
//     * 界面初始化查询
//     * @param map  交易对手账号
//     * @return 交易对手信息
//     * @see
//     * @since 1.0
//     */
//    MstTradeOpponentDto selectCounterInfo(Map<String, Object> map);
//    /**
//     * 查找账号信息
//     * @param map 账号
//     * @return 账号实体类列表
//     *
//     * @see
//     * @since 1.0
//     */
//    List<StlCapacctDto> selectStlCapacctInfo(Map<String, Object> map);
//    /**
//     * 查找交易对手的证券账号信息
//     * @param map 账号
//     * @return 证券账号实体类列表
//     *
//     * @see
//     * @since 1.0
//     */
//    List<MstCounterSecurityAcct> selectSecurityAcctByCounterId(Map<String, Object> map);
//    /**
//     * 查找现金流信息
//     * @param map 现金流代码
//     * @return 现金流信息
//     * @see
//     * @since 1.0
//     */
//    List<FinCashDto> selectFinCashInfo(Map<String, Object> map);
//    /**
//     * 获取现金流二级类型
//     * @param map 费用代码
//     * @return 现金流二级类型
//     * @see
//     * @since 1.0
//     */
//
//    String selectCashType2(Map<String, Object> map);
//
//    int checkCounterId(@Param("counterId") String counterId);
//    int checkCounterName(@Param("counterName") String counterName);
//    /**
//     *
//     * 插入金融产品扩展表
//     * <功能详细描述>
//     * @param entity
//     * @return
//     * @see
//     * @since 1.0
//     */
//    int insertPaFinProduct(MstTradeOpponentDto entity);
//    /**
//     * 获取交易对手账户信息（组合资金账户）
//     * @param map 请求参数
//     * @return 账户信息
//     * @see
//     * @since 1.0
//     */
//    List<StlCapacctInfo> getCountInfo(Map<String, Object> map);
}
