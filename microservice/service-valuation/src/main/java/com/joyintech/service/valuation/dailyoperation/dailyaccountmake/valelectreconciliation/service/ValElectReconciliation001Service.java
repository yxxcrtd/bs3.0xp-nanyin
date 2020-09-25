package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valelectreconciliation.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valelectreconciliation.dao.ValElectReconciliation001Mapper;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valelectreconciliation.dto.ValElectReconciliation001Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;

@Service
public class ValElectReconciliation001Service extends CommonExportService<ValElectReconciliation001Dto> {

    @Autowired
    private ValElectReconciliation001Mapper valElectReconciliation001Mapper;

    /**
     * 电子对账页面table查询 <功能详细描述>
     * 
     * @param params
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<ValElectReconciliation001Dto> findPage(int page, int length,
                                                       Map<String, Object> params)
        throws Exception {
        params.put("INTER_TYPE_VAL", DictCodeListExt1.深圳通接口类型_估值表.getValue());
        params.put("INTER_TYPE_BALANCEL", DictCodeListExt1.深圳通接口类型_余额表.getValue());
        return super.queryForPage(page, length,
            () -> valElectReconciliation001Mapper.queryPageList(params));
    }

    /**
     * 页面产品下拉框查询方法 <功能详细描述>
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getFinprodId(int page, int length, Map<String, Object> map) {
        map.put("BOOKSET_TYPE_TEMPLATE", DictCodeList.账套类别_账套模板.getValue());
        return super.queryForPageForSelectReturn(page, length,
            () -> valElectReconciliation001Mapper.getFinprodId(map));
    }

    /**
     * 调整 <功能详细描述>
     * 
     * @param listdto
     * @return
     * @see
     * @since 1.0
     */
    public String adjustValue(List<ValElectReconciliation001Dto> listdto) {
        valElectReconciliation001Mapper.adjustValue(listdto);
        String msg = "操作成功结束！";
        return msg;
    }

}
