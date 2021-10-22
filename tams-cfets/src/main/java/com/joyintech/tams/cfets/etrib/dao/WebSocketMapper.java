package com.joyintech.tams.cfets.etrib.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface WebSocketMapper {

    @Select("<script>" +
            "   select count(1) COUNT from ${tableName} t " +
            "   where t.portfolio_id = #{portfolioId,jdbcType=VARCHAR} " +
            "</script>")
    Map select(Map<String, Object> map);
    
    @Select("<script>" +
            "   select " +
            "   sum(t.amount) as amount  " +
            "   from PTL_SEC_POSITION t " +
            "   where t.portfolio_id = #{portfolioId,jdbcType=VARCHAR} and " +
            "   t.FINPROD_ID = #{finprodId,jdbcType=VARCHAR} and t.cdate = #{tradeDate,jdbcType=DATE} " +
            "</script>")
    int update(Map<String, Object> map);

    @Select("<script>" +
            "   select " +
            "   sum(t.amount) as amount  " +
            "   from PTL_SEC_POSITION t " +
            "   where t.portfolio_id = #{portfolioId,jdbcType=VARCHAR} and " +
            "   t.FINPROD_ID = #{finprodId,jdbcType=VARCHAR} and t.cdate = #{tradeDate,jdbcType=DATE} " +
            "</script>")
    int insert(Map<String, Object> map);
}
