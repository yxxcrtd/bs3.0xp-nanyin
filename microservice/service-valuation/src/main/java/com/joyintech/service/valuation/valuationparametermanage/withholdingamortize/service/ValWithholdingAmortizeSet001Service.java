package com.joyintech.service.valuation.valuationparametermanage.withholdingamortize.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.valuationparametermanage.withholdingamortize.dao.ValWithholdingAmortizeSet001Mapper;
import com.joyintech.service.valuation.valuationparametermanage.withholdingamortize.dto.ValWithholdingAmortizeSet001Dto;
import com.joyintech.service.valuation.valuationparametermanage.withholdingamortize.dto.ValWithholdingAmortizeSet002Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListTableId;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author tanuan
 * @version 1.0 2019年11月29日
 * @see ValWithholdingAmortizeSet001Service
 * @since 1.0
 */
@Service("seriveValWithholdingAmortizeSet001Service")
public class ValWithholdingAmortizeSet001Service extends CommonExportService<ValWithholdingAmortizeSet001Dto> {

    @Autowired
    private ValWithholdingAmortizeSet001Mapper valWithholdingAmortizeSet001Mapper;

    /**
     * 账套代码
     * 
     * @param pageParam
     * @return page
     */
    public Page<SelectOptionDto> getBooksetIdNew(int page, int length, Map<String, Object> param) {
        param.put("booksetType", DictCodeList.账套类别_理财账套.getValue());
        return super.queryForPageForSelectReturn(page, length,
            () -> valWithholdingAmortizeSet001Mapper.getBooksetIdForSelect(param));
    }

    /**
     * 列表查询 - 分页
     * 
     * @param page
     * @param length
     * @param params
     * @return page
     */
    public Page<ValWithholdingAmortizeSet001Dto> queryPageList(int page, int length,
                                                               Map<String, Object> params) {
        return super.queryForPage(page, length,
            () -> valWithholdingAmortizeSet001Mapper.selectList(params));
    }

    /**
     * check
     * 
     * @param param
     * @return list
     */
    public List<SelectOptionDto> check(ValWithholdingAmortizeSet002Dto param) {
        Map<String, Object> params = new HashMap<>();
        params.put("finprodId", param.getFinprodId());
        params.put("tableId", DictCodeListTableId.会计场景代码_费用摊销.getValue());
        return valWithholdingAmortizeSet001Mapper.check(params);
    }

}
