package com.joyintech.service.product.rddesgin.feasibilityanalysis.dao;

import com.joyintech.service.product.rddesgin.feasibilityanalysis.dto.FinProductFeaAnaDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * 产品研发设计-可行性分析
 *
 * @author guyunfei
 * @version 1.0 2019年9月24日
 */
@Repository
public interface FinProductFeaAnaMapper {

    /**
     * 可行性分析查询
     * @param params
     * @return
     */
    List<FinProductFeaAnaDto> selectForPage(Map<String,Object> params);

    /**
     * 根据产品需求编号查询产品需求数据
     * @param id
     * @return
     */
    FinProductFeaAnaDto queryById(String id);

    /**
     * 维护分析结论、可行需求分类
     * @param dto
     * @return
     */
    Integer update(FinProductFeaAnaDto dto);

    /**
     * 删除产品需求数据
     * @param requirementId
     * @return
     */
    Integer deleteAnalyse(String requirementId);
}
