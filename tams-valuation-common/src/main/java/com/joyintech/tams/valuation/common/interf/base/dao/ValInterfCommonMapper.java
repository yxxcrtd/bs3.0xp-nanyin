/*
 * 文件名：ValInterfCommonMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年7月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.BokAcctLayeringInfo;
import com.joyintech.tams.common.base.entity.BokBookset;
import com.joyintech.tams.common.base.entity.BokBooksetCapacctInfo;
import com.joyintech.tams.common.base.entity.BokBooksetExt;
import com.joyintech.tams.common.base.entity.BokBooksetPlan;
import com.joyintech.tams.common.base.entity.BokBooksetRe;
import com.joyintech.tams.common.base.entity.BokSceneconfig;
import com.joyintech.tams.common.base.entity.BokScenesubject;
import com.joyintech.tams.common.base.entity.BokSubject;
import com.joyintech.tams.common.base.entity.BokTabledict;
import com.joyintech.tams.common.base.entity.BokTablelist;
import com.joyintech.tams.common.base.entity.FinRateInfo;
import com.joyintech.tams.common.base.entity.Prdbokstl;
import com.joyintech.tams.common.base.entity.StlOrders;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.valuation.common.interf.base.dto.LayerPrdInfo;

/**
 * 接口共通类Mapper
 * 
 * @author weipeng
 * @version 1.0 2018年6月21日
 * @see ValInterfCommonMapper
 * @since 1.0
 */
@Repository
public interface ValInterfCommonMapper {

    /**
     * 根据账套代码查询账套配置
     * 
     * @param booksetId
     * @return List<ValBokBooksetExt> 账套配置结果集
     * @see
     * @since 1.0
     */
    List<BokBooksetExt> queryBooksetExtByBooksetId(@Param("booksetId") String booksetId);

    /**
     * 根据账套代码查询账套科目
     * 
     * @param booksetId 账套代码
     * @return List<ValBokSubject> 账套科目结果集
     * @see
     * @since 1.0
     */
    List<BokSubject> querySubjectByBooksetId(@Param("booksetId") String booksetId);

    /**
     * 根据账套代码查询会计场景
     * 
     * @param booksetId 账套代码
     * @return List<ValBokTablelist> 会计场景结果集
     * @see
     * @since 1.0
     */
    List<BokTablelist> queryTablelistByBooksetId(@Param("booksetId") String booksetId);

    /**
     * 根据账套代码查询会计元素
     * 
     * @param booksetId 账套代码
     * @return List<ValBokTabledict> 会计元素结果集
     * @see
     * @since 1.0
     */
    List<BokTabledict> queryTabledictByBooksetId(@Param("booksetId") String booksetId);

    /**
     * 根据账套代码查询场景科目关系
     * 
     * @param booksetId 账套代码
     * @return List<ValBokTabledict> 场景科目关系结果集
     * @see
     * @since 1.0
     */
    List<BokScenesubject> queryScenesubjectByBooksetId(@Param("booksetId") String booksetId);

    /**
     * 根据账套代码查询场景分支配置
     * 
     * @param booksetId 账套代码
     * @return List<ValBokTabledict> 场景分支配置结果集
     * @see
     * @since 1.0
     */
    List<BokSceneconfig> querySceneconfigByBooksetId(@Param("booksetId") String booksetId);

    /**
     * 根据账套代码查询账套估值方案
     * 
     * @param booksetId 账套代码
     * @return List<ValBokTabledict> 账套估值方案结果集
     * @see
     * @since 1.0
     */
    List<BokBooksetPlan> queryValPlanByBooksetId(@Param("booksetId") String booksetId);

    /**
     * 根据账套代码查询账套配置
     * 
     * @param booksetId
     * @return List<ValBokBooksetExt> 账套配置结果集
     * @see
     * @since 1.0
     */
    BokBookset queryBooksetByProdId(@Param("prodId") String prodId);
    
    /**
     * 更新接口状态
     * 
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    int updateInterfActionLogStatus(Map<String, Object> params);

    /**
     * 撤销交易对应的原交易编号查询
     * 
     * @param trdProductDeal 金融产品交易Dto
     * @return 交易编号
     */
    String getFTradeId(TrdProductDeal trdProductDeal);
    
    /**
     * 认购确认交易对应的认购申请交易编号
     * @param params
     * @return
     */
    String getBTradeId(Map<String, Object> params);

    /**
     * 资金账户类型查询
     * 
     * @param params
     * @return List<BokBooksetCapacctInfo> 资金账户类型结果集
     * @see
     * @since 1.0
     */
    List<BokBooksetCapacctInfo> selectCapAcctType(Map<String, Object> params);
    
    /**
     * 查询现金流利率信息表
     * 
     * @param params
     * @return
     */
    List<FinRateInfo> queryFinCashIntInfo(Map<String, Object> params);

    /**
     * 分层结构子产品分层信息初始化 查询数据库已经存在的账套信息
     * 
     * @param params
     * @return
     */
    List<BokBookset> querryBokinfo(Map<String, Object> params);

    /**
     * 已有产品信息查询
     * 
     * @param finprodType 金融产品类型
     * @return 分层产品信息
     */
    List<LayerPrdInfo> queryLayerPrdInfo();

    /**
     * 根据账套ID查询核算分层信息 add by wujie on 20181027
     * 
     * @param booksetId
     * @return
     */
    List<BokAcctLayeringInfo> queryAcctLayeringInfoByBoksetId(@Param("booksetId") String booksetId);

    /**
     * 撤销指令交易对应的原交易编号查询 2018/11/14 chendijun
     * 
     * @param stlOrders 结算指令Dto
     * @return orderId 指令编号
     * @see
     * @since 1.0
     */
    String queryPOrderId(StlOrders stlOrders);

    /**
     * 账套估值方案
     * 
     * @param map Map<String,Object>
     * @return BokBooksetPlan
     * @see
     * @since 1.0
     */
    BokBooksetPlan initProductValuationScheme(Map<String, Object> map);
    
    /**
     * 根据条件获取【金融产品交易】中的【交易编号】
     * @param totleTradeId
     * @return
     */
    List<TrdProductDeal> getTradeIdByFilter(@Param("totleTradeId") String totleTradeId);
    /**
     * 查询产品信息
     * add by wujie on 20190102
     * @return
     */
    List<Prdbokstl> queryPrdbokstl(Map<String, Object> params);
    
    /**
     * 查询产品账套、托管户信息
     * add by wujie on 20190102
     * @return
     */
    List<Prdbokstl> queryBokAcctSubjectInfo(Map<String, Object> params);
    /**
     * 根据条件获取【金融产品交易】中的【交易编号】
     * @param totleTradeId
     * @return
     */
    List<TrdProductDeal> getFeeDealByFilter(@Param("totleTradeId") String totleTradeId);
    
    /**
     * 接口【交易信息_删除】-【结算指令扩展表】删除
     * @param tradeId 交易编号
     * @return
     */
    int delStlOrdersExt(String tradeId);
    
    /**
     * 接口【交易信息_删除】-【结算指令表】删除
     * @param tradeId 交易编号
     * @return
     */
    int delStlOrders(String tradeId);
    
    /**
     * 接口【交易信息_删除】-【交易费用明细表】删除
     * @param tradeId 交易编号
     * @return
     */
    int delTrdTradeFeeDetail(String tradeId);
    
    /**
     * 接口【交易信息_删除】-【金融产品交易附属表】删除
     * @param tradeId 交易编号
     * @return
     */
    int delTrdProductDealAdd(String tradeId);
    
    /**
     * 接口【交易信息_删除】-【金融产品交易】删除
     * @param tradeId 交易编号
     * @return
     */
    int delTrdProductDeal(String tradeId);    
    
    /**
     * 接口【交易信息_删除】-【金融产品实物交易】删除
     * @param tradeId 交易编号
     * @return
     */
    int delTrdFinProductBarter(String tradeId);  

    /**
     * 查询待删除交易List
     * @param fTradeId 原交易编号
     * @param tradeStatus 交易状态
     * @return
     */
    List<TrdProductDeal> queryToDelDeal(@Param("fTradeId")String fTradeId,@Param("tradeStatus")String tradeStatus);
    
    /**
     * 查询待删除通道交易List
     * @param fTradeId 原交易编号
     * @param tradeStatus 交易状态
     * @return
     */
    List<TrdProductDeal> queryToDelChlDeal(@Param("finprodId")String finprodId);
    
    /**
     * 查询账套税务配置
     * @param params
     * @return
     */
    int queryBokSchemeInfo(@Param("schemeType") String schemeType,@Param("schId") String schId);
    
    /**核算到期日调整 新增 add by chendijun  2019/05/30 start**/
    /**
     * 查询产品作为核算主体时的账套信息
     * 
     * @param prodId 金融产品ID
     * @param prodType 金融产品类型
     * @return 账套信息
     * @see
     * @since 1.0
     */
    BokBookset queryBooksetAsAcct(@Param("prodId") String prodId,@Param("prodType") String prodType);
    
    /**
     * 
     * 查询子账套信息
     * <功能详细描述>
     * @param booksetId 账套代码
     * @return 子账套集合
     * @see
     * @since 1.0
     */
    List<BokBooksetRe> queryBokBooksetReList(@Param("booksetId") String booksetId);
    /**
     * 
     * 更新账套结束日
     * <功能详细描述>
     * @param BokBooksetRe
     * @see
     * @since 1.0
     */
    void updateBooksetAsAcct(Map<String, Object> params);
    
    /**
     * 
     * 更新子账套对应核算分层信息到期日
     * <功能详细描述>
     * @param params
     * @see
     * @since 1.0
     */
    void updateAcctLayeringInfo(Map<String, Object> params);
    
    /**核算到期日调整 新增 add by chendijun  2019/05/30 end**/
    
    /**
     * 判断交易是否入账List
     * @param tradeId 交易编号
     * @return
     */
    TrdProductDeal queryAccountDeal(@Param("tradeId")String tradeId,@Param("tradeStatus")String tradeStatus);
}
