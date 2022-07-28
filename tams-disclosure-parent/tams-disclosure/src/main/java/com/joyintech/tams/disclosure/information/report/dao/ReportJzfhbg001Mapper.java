
package com.joyintech.tams.disclosure.information.report.dao;

import com.joyintech.tams.common.base.entity.DisProdBonusplan;
import com.joyintech.tams.common.base.entity.RepProdIndex;
import com.joyintech.tams.disclosure.information.report.dto.ReportJzfhbg001Dto;
import com.joyintech.tams.disclosure.information.report.dto.ReportMzjz001Dto;
import com.joyintech.tams.disclosure.information.report.dto.ReportOpenPlanDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author zhengbaosheng
 * @version 1.0 2022年07月14日
 * @see ReportJzfhbg001Mapper
 * @since 1.0
 */
@Repository(value="ReportJzfhbg001Mapper")
public interface ReportJzfhbg001Mapper {
    
    /**
     * 根据产品代码查询公告信息
     * @param params 参数
     * @return ReportFxgg001Dto
     * @see
     * @since 1.0
     */
    ReportJzfhbg001Dto getProdInfo(Map<String, Object> params);

    /**
     * 根据业绩分配基准日查询分红计划
     * @param searchParam
     * @return
     */
    DisProdBonusplan getBonusPlanBySettDate(Map<String, Object> searchParam);

    /**
     * 根据业绩分配基准日查询净值数据
     * @param searchParam
     * @return
     */
    RepProdIndex getIndexBySettDate(Map<String, Object> searchParam);

    /**
     * 根据业绩分配基准日查询上次分红计划表
     * @param searchParam
     * @return
     */
    DisProdBonusplan getLastBonusInfo(Map<String, Object> searchParam);

    /**
     * 查询后续开放计划
     * @param searchParam
     * @return
     */
    List<ReportOpenPlanDto> getOpenPlanList(Map<String, Object> searchParam);

    DisProdBonusplan checkGenerateFlag(Map<String, Object> searchParam);
}
