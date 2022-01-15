package com.joyintech.tams.report.submitmaintenance.salestransactionmaintenance.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.report.submitmaintenance.salestransactionmaintenance.dto.SalesTransactionMaintenanceDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * 销售结算资金交易信息维护
 */
@Repository
public interface SalesTransactionMaintenanceMapper {
    /**
     * 分页查询
     */
    List<SalesTransactionMaintenanceDto> selectForPage(Map<String, Object> param);

    /**
     * 双击查看详细信息查询，修改信息查询 <功能详细描述>info
     *
     */
    SalesTransactionMaintenanceDto selectDetail(Map<String, Object> param);

    /**
     * 双击查看详细信息查询，修改信息查询 <功能详细描述>erro
     *
     */
    List<SalesTransactionMaintenanceDto> selectErroDetail(Map<String, Object> param);

    /**
     * 查询新增数据主键是否重复
     */
    int getRegistExcelList( Map<String,Object> param );

    /**
     * 根据主键删除erro
     * @param
     * @return
     */
    int deleteByPrimaryKey(@Param("institutionCode") String institutionCode, @Param("tradeNo") String tradeNo);

    /**
     * 根据主键删除info
     * @param
     * @return
     */
    int deleteInfo(@Param("institutionCode") String institutionCode, @Param("tradeNo") String tradeNo);

    /**
     * 新建插入
     * @param
     * @return
     */
    int insert(SalesTransactionMaintenanceDto dto);

    /**
     * 修改
     * @param
     * @return
     */
    int update(SalesTransactionMaintenanceDto dto);

    /**
     * 删除err
     * @param
     * @return
     */
    int deleteErr(@Param("registSeqno") String registSeqno);

    /**
     * 提供销售结算资金服务机构代码下拉框
     */
    List<SuggestDto> getInstitutionCode(Map<String, Object> map);

    /**
     * 产品登记编码下拉框
     */
    List<SuggestDto> getPrdRegistCode(Map<String, Object> map);
    /**
     * 交易流水下拉框
     */
    List<SelectOptionDto> getTradeNo(Map<String, Object> pageParam);

    /**
     * 提供销售结算资金服务机构代码、产品登记编码与销售结算资金账号相等时，销售结算资金交易流水号不能重复
     */
    int selectTradeNo(@Param("institutionCode")String institutionCode,@Param("prdRegistCode")String prdRegistCode,@Param("accountNo")String accountNo,@Param("tradeNo")String tradeNo);

}
