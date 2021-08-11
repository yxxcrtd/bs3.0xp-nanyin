
package com.joyintech.service.valuation.valmodel.valgeneralfixedincomeassets.cashflowdiscountmanagement.dao;
import com.joyintech.service.valuation.valmodel.valgeneralfixedincomeassets.cashflowdiscountmanagement.dto.AssetInfoDto;
import com.joyintech.service.valuation.valmodel.valgeneralfixedincomeassets.cashflowdiscountmanagement.dto.CashFlowDiscount001Dto;
import com.joyintech.service.valuation.valmodel.valgeneralfixedincomeassets.cashflowdiscountmanagement.dto.ModAseetMappingDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author 刘华明
 * @version 1.0 2021年7月1日
 * @see CashFlowDiscount001Mapper
 * @since 1.0
 */
@Repository(value="serviceCashFlowDiscount001Mapper")
public interface CashFlowDiscount001Mapper {
    /**
     * 插入
     * @param record
     * @return
     */
    int insert(CashFlowDiscount001Dto record);

    /**
     * 更新
     * @param record
     * @return
     */
    int update(CashFlowDiscount001Dto record);

    int deleteByPrimaryKey(String seqno);

    int checkData(CashFlowDiscount001Dto dto);

    /**
     * 查询数据 <一句话功能简述> <功能详细描述>
     *
     * @param map
     * @return List<CashFlowDiscount001Dto>
     * @see
     * @since 1.0
     */
    List<CashFlowDiscount001Dto> getCashFlowDiscountList(Map<String, Object> map);

    /**
     * 导出
     * @param map
     * @return
     */
    List<CashFlowDiscount001Dto> getForExcel(Map<String, Object> map);

    List<ModAseetMappingDto> selectByModel(ModAseetMappingDto record);

    List<AssetInfoDto> selectAssetNoClassify(AssetInfoDto assetInfoDto);
}
