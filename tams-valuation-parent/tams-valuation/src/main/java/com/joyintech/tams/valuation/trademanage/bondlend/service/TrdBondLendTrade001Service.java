package com.joyintech.tams.valuation.trademanage.bondlend.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.trademanage.bondlend.dto.TrdBondLendTrade001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TrdBondLendTrade001Service extends CommonExportService<TrdBondLendTrade001Dto> implements BaseProcessService<TrdBondLendTrade001Dto> {

    /**
     * 检索债券信息
     *
     * @param pageParam 查询参数
     * @return
     * @see
     * @since 1.0
     */
    public Page<TrdBondLendTrade001Dto> findPage(Map<String, Object> pageParam) {

        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/TrdBondLendTrade001/findPage",
                pageParam,TrdBondLendTrade001Dto.class);
    }

    /**
     * 不分页
     *
     * @param params
     * @return
     */
    public List<TrdBondLendTrade001Dto> queryPageList(Map<String, Object> params) {
        return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdBondLendTrade001/queryPageList", params,
                TrdBondLendTrade001Dto.class);
    }

    @Override
    public void doComplete(TrdBondLendTrade001Dto TrdBondLendTrade001Dto, OperationType operationType) {
        // 删除操作
        if(OperationType.DELETE.equals(operationType)) {
            restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/TrdBondLendTrade001/delete", TrdBondLendTrade001Dto, Integer.class);
        }
    }
}
