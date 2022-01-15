package com.joyintech.tams.valuation.trademanage.bond.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.trademanage.bond.dto.TrdBondTrade001Dto;
import com.joyintech.tams.valuation.trademanage.finprodtradequery.dto.ValFinprodTradeQuery001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TrdBondTrade001Service extends CommonExportService<TrdBondTrade001Dto> implements BaseProcessService<TrdBondTrade001Dto> {

    /**
     * 检索债券信息
     *
     * @param pageParam 查询参数
     * @return
     * @see
     * @since 1.0
     */
    public Page<TrdBondTrade001Dto> findPage(Map<String, Object> pageParam) {

        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/TrdBondTrade001/findPage",
                pageParam,TrdBondTrade001Dto.class);
    }

    /**
     * 不分页
     *
     * @param params
     * @return
     */
    public List<TrdBondTrade001Dto> queryPageList(Map<String, Object> params) {
        return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdBondTrade001/queryPageList", params,
                TrdBondTrade001Dto.class);
    }

    @Override
    public void doComplete(TrdBondTrade001Dto trdBondTrade001Dto, OperationType operationType) {
        // 删除操作
        if(OperationType.DELETE.equals(operationType)) {
            restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/TrdBondTrade001/delete", trdBondTrade001Dto, Integer.class);
        }
    }
}
