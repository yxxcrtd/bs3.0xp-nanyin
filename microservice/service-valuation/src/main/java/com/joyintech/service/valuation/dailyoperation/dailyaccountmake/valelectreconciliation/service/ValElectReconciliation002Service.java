package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valelectreconciliation.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valelectreconciliation.dao.ValElectReconciliation002Mapper;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valelectreconciliation.dto.ValElectReconciliation002Dto;
import com.joyintech.tams.constant.DictCodeListExt1;

@Service
public class ValElectReconciliation002Service extends CommonExportService<ValElectReconciliation002Dto> {

    @Autowired
    private ValElectReconciliation002Mapper valElectReconciliation001Mapper;

    /**
     * 对账明细table查询 <功能详细描述>
     * 
     * @param params
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<ValElectReconciliation002Dto> findPage(int page, int length,
                                                       Map<String, Object> params)
        throws Exception {
        params.put("INTER_TYPE_BALANCE", DictCodeListExt1.深圳通接口类型_余额表.getValue());
        return super.queryForPage(page, length,
            () -> valElectReconciliation001Mapper.queryPageList(params));
    }

}
