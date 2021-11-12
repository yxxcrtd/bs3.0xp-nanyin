package com.joyintech.tams.report.submitmaintenance.salestransactionmaintenance.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
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
    SalesTransactionMaintenanceDto selectErroDetail(Map<String, Object> param);

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
     * 提供销售结算资金服务机构代码下拉框
     */
    List<SelectOptionDto> getInstitutionCode(Map<String,Object> map);

    /**
     * 销售结算资金交易流水号下拉框
     */
    List<SelectOptionDto> getTradeNo(Map<String,Object> map);

}
