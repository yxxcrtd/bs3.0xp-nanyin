package com.joyintech.tams.invest.common.autocoupon.dao;

import com.joyintech.tams.common.base.entity.MstExchargeQuote;
import com.joyintech.tams.common.base.entity.PtlCapacctInfo;
import com.joyintech.tams.common.base.entity.StlDvpOrders;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author zhangnian
 * @ClassName CommonAutoCouponForBatchMapper
 * @description TODO
 * @date 2020/10/14 0014 11:48
 **/
@Repository
public interface CommonAutoCouponForBatchMapper {
    Map<String, Object> getAuthorizeLimitAmt(Map<String, String> params);

    Boolean isBackCheckNode(Map<String, Object> params);

    BigDecimal getAuthorizeLimitAmtForFuZongCai(Map<String, String> params);

    List<StlPayOrders> selectStlPayOrdersList(Map<String, Object> params);

    List<StlDvpOrders> selectStlDvpOrdersList(Map<String, Object> params);

    Boolean checkPortChlAcctNoSame(@Param("portfolioId") String portfolioId, @Param("chlId") String chlId);

    // 资产类型改为授权类型 add by renping 2019-08-19  start
    /**
     * 汇率行情查询
     * @param map 参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    MstExchargeQuote getExchangeRate(Map<String, Object> map);

    int deletePledgeBond(Map<String, Object> params);
    // 资产类型改为授权类型 add by renping 2019-08-19  end

    void insertNewFileInfo(Map<String, Object> params);

    BigDecimal selectFaceValue(Map<String, Object> params);

    /**
     *
     * 组合资金账户信息
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    List<PtlCapacctInfo> selectPtlCapacctInfo(Map<String, Object> params);

    /**
     *查询证券账户信息
     * @param params
     * @return
     */
    String querySecAcctId(Map<String, Object> params);

    /**  20200730 njl add
     *判断债券wind接过来的有没有维护过
     * @param finprodId
     * @return int
     */
    int selectBondNum(@Param("finprodId") String finprodId);

    int selectFuturesNum(@Param("finprodId") String finprodId);




    /**  20200730 njl add
     *判断基金wind接过来的有没有维护过
     * @param finprodId
     * @return int
     */
    int selectFundNum(@Param("finprodId") String finprodId);
} 

