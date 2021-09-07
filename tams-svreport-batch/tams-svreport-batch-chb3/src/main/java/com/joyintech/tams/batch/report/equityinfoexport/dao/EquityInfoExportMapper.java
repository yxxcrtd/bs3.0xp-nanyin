package com.joyintech.tams.batch.report.equityinfoexport.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.report.submitmanagement.equityinforegist.dto.EquityInfoRegist001Dto;
import com.joyintech.tams.report.system.subinfobatchlog.dto.ChbSubinfoBatchLog;


@Repository
public interface EquityInfoExportMapper {

    /** 获取报送信息登记生成记录表中处理状态为04，前台导出的记录
     * @return
     */
    List<ChbSubinfoBatchLog> getBatchLog();
    
    /** 统计需要生成的报送信息登记总量
     * @param map
     * @return
     */
    int countExportNum(Map<String,Object> map);

    /**
     *
     * 分页查询导出数据
     * @param map 查询条件
     * @return 结果列表
     * @see
     * @since 1.0
     */
    List<EquityInfoRegist001Dto> selectAllDataByPage(Map<String,Object> map);
}
