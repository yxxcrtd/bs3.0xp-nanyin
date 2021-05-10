package com.joyintech.service.valuation.trdassetlossmanage.trdassetlossbalance.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.trdassetlossmanage.trdassetlossbalance.dao.TrdAssetLossBalance001Mapper;
import com.joyintech.service.valuation.trdassetlossmanage.trdassetlossbalance.dto.TrdAssetLossBalance001Dto;
import com.joyintech.service.valuation.trdassetlossmanage.trdassetlossbalance.dto.TrdAssetLossBalance002Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("backTrdAssetLossBalance002Service")
public class TrdAssetLossBalance002Service extends CommonExportService<TrdAssetLossBalance002Dto> {

    @Autowired
    private TrdAssetLossBalance001Mapper trdAssetLossBalance001Mapper;


    /**
     * 明细查询
     */
    public Page<TrdAssetLossBalance002Dto> queryDetailList(int page, int length, Map<String, Object> params) throws Exception {
        return this.queryForPage(page,length,
                () -> trdAssetLossBalance001Mapper.queryDetailList(params));
    }

}
