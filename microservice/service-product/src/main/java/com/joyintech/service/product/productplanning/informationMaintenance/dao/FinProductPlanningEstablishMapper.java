package com.joyintech.service.product.productplanning.informationMaintenance.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.productplanning.informationMaintenance.dto.FinProductPlanning2DetailDto;
import com.joyintech.service.product.productplanning.informationMaintenance.dto.FinProductPlanning2Dto;
import com.joyintech.service.product.productplanning.informationMaintenance.dto.FinProductPlanning3Dto;
import com.joyintech.service.product.productplanning.informationMaintenance.dto.FinProductPlanningDto;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
/**
 * 产品规划信息维护微服务
 * @author 代国富
 *20191228
 */
@Repository
public interface FinProductPlanningEstablishMapper {
    /**
     * 新增页面的查询
     *
     * @param paramMap 参数
     * @return 画面分页数据
     * @see
     * @since 1.0
     */
    List<FinProductPlanning2DetailDto> selectPageData(Map<String, Object> paramMap);
    /**
     * 查询页面的查询
     * @param paramMap 参数
     * @return 画面分页数据
     */
    List<FinProductPlanning2DetailDto> findPageAll(Map<String, Object> paramMap);
    /**
     * 	根据系列id 查name
     * @param
     * @param param
     * @return
     */
//    List<FinProductPlanningDto> queryPrdPlanSerName(Map<String, Object> param);
    /**
     * 产品规划下拉框查询
     */
    List<SelectOptionDto> querySeriesNamess(Map<String, Object> paramMap);

    /**
     * 产品规划点击修改按钮获取数据
     */
    FinProductPlanning2Dto getEntityByNo(String planningNo);

    /**
     * 列表数据
     * @param planningNo
     * @return
     */
   List<FinProductPlanning2DetailDto> getEntityDetailByNo(String planningNo);

    /**
     * 查询目标资产规模
     * @param map
     * @return
     */
   List<FinProductPlanning3Dto> getTargetSize(Map<String, Object> map);

    /**
     * 查询时间冲突的数量
     * @param params
     * @return
     */
    int getConflictCount(Map<String, Object> params);
    /**
     * 查询时间重叠的数量
     * @param params
     * @return
     */
    int getOverlapCount(Map<String, Object> params);

    /**
     * 修改产品规划
     * @param finProductPlanning2Dto
     * @return
     */
    Integer updateByPrimaryKey(FinProductPlanning2Dto finProductPlanning2Dto);

    /**
     * 删除详情
     * @param param
     * @return
     */
    Integer deletePlanDetail(Map<String, Object> param);

    /**
     * 插入详情
     * @param finProductPlanning2DetailDto
     * @return
     */
    Integer insert(FinProductPlanning2DetailDto finProductPlanning2DetailDto);

    /**
     * 插入产品计划
     * @param finProductPlanning2Dto
     * @return
     */
    Integer insertInfo(FinProductPlanning2Dto finProductPlanning2Dto);

    /**
     * 删除产品计划
     * @param param
     * @return
     */
    Integer deleteInfoPlan(Map<String, Object> param);
    /**
     * 刪除操作修改狀態
     */
    Integer updateValidStatus(Map<String, Object> param);

    /**
     * 点击+号操作
     * 根据系列编号查询是否存在该数据
     * @param
     * @return
     */
   Integer findCountByNo(String planningSeriesNo);
    /**
     * 点击+号操作
     * 根据系列名称查询是否存在该数据
     * @param
     * @return
     */
   Integer findCountByName(String planningSeriesName);
    /**
     * 修改页面查询到期份额
     * @param
     * @return
     */
    Long findProductsDue(Map<String, Object> pageParam);
}
