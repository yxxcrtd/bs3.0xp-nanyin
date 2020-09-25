package com.joyintech.service.valuation.valuationparametermanage.withholdingamortize.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.valuationparametermanage.withholdingamortize.dto.ValWithholdingAmortizeSet001Dto;
import com.joyintech.service.valuation.valuationparametermanage.withholdingamortize.dto.ValWithholdingAmortizeSet002Dto;
import org.springframework.stereotype.Repository;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author tanxuan
 * @version 1.0 2019年11月29日
 * @see ValWithholdingAmortizeSet002Mapper
 * @since 1.0
 */
@Repository("serviceValWithholdingAmortizeSet002Mapper")
public interface ValWithholdingAmortizeSet002Mapper {
    /**
     * 查询
     * 
     * @param pageParam
     * @return valWithholdingAmortizeSet002Dto
     */
    ValWithholdingAmortizeSet002Dto selectByPrimaryKey(Map<String, Object> map);
    /**
     * 获取业务编号
     * 
     * @param pageParam
     * @return list
     */
    List<SelectOptionDto> getFinprodId(Map<String, Object> params);

    /**
     * 通过业务编号，获取信息
     * 
     * @param pageParam
     * @return valWithholdingAmortizeSet001Dto
     */
    ValWithholdingAmortizeSet001Dto getFinprodInfo(Map<String, Object> pageParam);
    
    /**
     * 获取交易日期
     * 
     * @param pageParam
     * @return valWithholdingAmortizeSet001Dto
     */
    ValWithholdingAmortizeSet001Dto getTradeDate(Map<String, Object> pageParam);

    // void insert1(ValWithholdingAmortizeSet002Dto entity);
    // void insert2(ValWithholdingAmortizeSet002Dto entity);
    // void insert3(ValWithholdingAmortizeSet002Dto entity);
    // void insert4(ValWithholdingAmortizeSet002Dto entity);
    /**
     * 更新
     * 
     * @param entity
     * @return 无
     */
    int update(ValWithholdingAmortizeSet002Dto entity);
    /**
     * 删除BokDetailId
     * 
     * @param entity
     * @return 无
     */
    int deleteBokDetailId(ValWithholdingAmortizeSet002Dto entity);
    /**
     * 删除FinProductTyp
     * 
     * @param entity
     * @return 无
     */
    int deleteFinProductType(ValWithholdingAmortizeSet002Dto entity);
    /**
     * 删除FinTradeProductAdd
     * 
     * @param entity
     * @return 无
     */
    int deleteFinTradeProductAdd(ValWithholdingAmortizeSet002Dto entity);
    /**
     * 删除FinTradeProduct
     * 
     * @param entity
     * @return 无
     */
    int deleteFinTradeProduct(ValWithholdingAmortizeSet002Dto entity);

}
