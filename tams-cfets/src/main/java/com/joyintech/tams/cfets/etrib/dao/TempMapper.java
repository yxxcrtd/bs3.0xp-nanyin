package com.joyintech.tams.cfets.etrib.dao;

import com.joyintech.tams.business.common.pojo.BaseMap;
import com.joyintech.tams.cfets.etrib.dto.CfetsEtrIbBondPosinfo001Dto;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public interface TempMapper {

    /**
     * 查询前一日持仓指标
     * @param baseMap
     * @return
     */
    List<BaseMap> selectLastCostAmt(BaseMap baseMap);

    /**
     * 当日交易数据查询
     * @param baseMap
     * @return
     */
    List<BaseMap> selectCurrTrdData(BaseMap baseMap);
    /**
     * 交易日历的节假日取得
     *
     * @param paraMap 参数
     * @return 节假日列表
     */
    @Select("<script>" +
            "   select " +
            "    to_char(CDATE, 'yyyy-MM-dd') as HOLIDATE " +
            "    from " +
            "            VAL_VW_MST_HOLIDAY " +
            "    where " +
            "    IS_HOLIDAY > 0 " +
            "   <if test=\"startDate != null\"> " +
            "    and CDATE >= #{startDate, jdbcType=DATE} " +
            "   </if> " +
            "   <if test=\"endDate != null\"> " +
            "    and CDATE &lt;= #{endDate, jdbcType=DATE} " +
            "   </if> " +
            "   <if test=\"dealCalId != null and dealCalId != ''\"> " +
            "    and TRADE_CALENDAR_ID = #{dealCalId} " +
            "   </if>" +
            "</script>")
    @Options(useCache = false, flushCache = Options.FlushCachePolicy.TRUE)
    List<String> getDealCalendarHoliday(Map<String, Object> paraMap);
    
    /**
     *
     * 查询指令可用现金
     * <功能详细描述>
     * @param portfolioId
     * @param acctId
     * @param chlFinprodId
     * @param hodingType
     * @param cdate
     * @param ccy
     * @param acctTypes
     * @return
     * @see
     * @since 1.0
     */
    @Select("<script>" +
            "   select " +
            "   sum(t.amount) as amount  " +
            "   from ETR_CASH_POSITION t " +
            "   where t.portfolio_id = #{portfolioId,jdbcType=VARCHAR} and t.cdate = #{tradeDate,jdbcType=DATE} " +
            "</script>")
    @Options(useCache = false, flushCache = Options.FlushCachePolicy.TRUE)
    BigDecimal selectCommandAvailableCash(Map<String, Object> map);

    @Select("<script>" +
            "   select " +
            "   sum(t.amount) as amount  " +
            "   from PTL_SEC_POSITION t " +
            "   where t.portfolio_id = #{portfolioId,jdbcType=VARCHAR} and " +
            "   t.FINPROD_ID = #{finprodId,jdbcType=VARCHAR} and t.cdate = #{tradeDate,jdbcType=DATE} " +
            "</script>")
    BigDecimal selectCommandAvailablePosition(Map<String, Object> map);

    /**
     * 持仓信息,分页查询
     * @param pageParam
     * @return
     */
    List<CfetsEtrIbBondPosinfo001Dto> queryPositionsPage(Map<String, Object> pageParam);

    Map findPositionInfo(Map<String, Object> pageParam);
}
