package com.joyintech.service.product.riskLevelRating.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.riskLevelRating.dto.RiskLevelRating001Dto;
import com.joyintech.service.product.riskLevelRating.dto.RiskLevelRating002Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 风险等级评定mapper
 *
 * @author jyl
 * time 2020-01-02
 */
@Repository
public interface RiskLevelRatingMapper {
    /**
     * 产品风险等级评定信息查询
     *
     * @param param
     * @return List
     */
    List<RiskLevelRating001Dto> findPage(Map<String, Object> param);

    /**
     * 产品代码下拉框信息查询
     *
     * @param param
     * @return List
     */
    List<SelectOptionDto> findId(Map<String, Object> param);

    /**
     * 资产评定列表获取
     *
     * @param param
     * @return List
     */
    List<RiskLevelRating002Dto> findList(Map<String, Object> param);

    /**
     * 校验单产品在某一天是否存在风险等级评价信息
     *
     * @param param
     * @return int
     */
    int onlyOne(Map<String, Object> param);

    /**
     * 向产品风险等级评定表中插入数据
     *
     * @param dto
     */
    void insertRisk(RiskLevelRating001Dto dto);

    /**
     * 向产品资产风险等级评定表中插入数据
     *
     * @param dto
     */
    void insertPrdRiskLevel(RiskLevelRating001Dto dto);

    /**
     * 修改产品风险等级评定表
     *
     * @param dto
     */
    void updateRiskLevel(RiskLevelRating001Dto dto);

    /**
     * 修改产品资产风险等级评定表
     *
     * @param dto
     */
    void updatePrdRiskLevel(RiskLevelRating001Dto dto);

    /**
     * 删除产品风险等级评定表
     *
     * @param seqNo
     */
    void deleteRiskLevel(String seqNo);

    /**
     * 删除产品资产风险等级评定表
     *
     * @param seqNo
     */
    void deletePrdRiskLevel(String seqNo);

    /**
     * 获取流水号
     *
     * @param param
     * @return String
     */
    String selectNo(Map<String, Object> param);

    /**
     * 获取调整后资产风险等级列表
     *
     * @param seqNo
     * @return List
     */
    List<RiskLevelRating002Dto> selectAjustRisk(String seqNo);
}
