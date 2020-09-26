package com.joyintech.tams.batch.report.equitybusiinfoexport.dao;

import com.joyintech.tams.report.submitmanagement.equitybusiinforegist.dto.EquityBusiInfoRegist001Dto;
import com.joyintech.tams.report.system.subinfobatchlog.dto.ChbSubinfoBatchLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 投资者明细信息数据归档批处理mapper
 *
 * @author zhangmingming
 * @version 1.0 2020年01月13日
 * @see EquityBusiInfoExportMapper
 * @since 1.0
 */
@Repository
public interface EquityBusiInfoExportMapper {

    List<ChbSubinfoBatchLog> getBatchLog();
    int countExportNum(Map<String,Object> map);

    /**
     *
     * 分页查询导出数据
     * @param map 查询条件
     * @return 结果列表
     * @see
     * @since 1.0
     */
    List<EquityBusiInfoRegist001Dto> selectAllDataByPage(Map<String,Object> map);
}
