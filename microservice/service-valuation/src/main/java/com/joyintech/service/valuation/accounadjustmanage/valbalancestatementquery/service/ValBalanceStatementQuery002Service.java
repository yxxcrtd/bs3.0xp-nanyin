package com.joyintech.service.valuation.accounadjustmanage.valbalancestatementquery.service;

import com.github.pagehelper.PageInfo;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.accounadjustmanage.valbalancestatementquery.dao.ValBalanceStatementQuery002Mapper;
import com.joyintech.service.valuation.accounadjustmanage.valbalancestatementquery.dto.*;
import com.joyintech.tams.constant.DictCodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value="serviceValBalanceStatementQuery002Service")
public class ValBalanceStatementQuery002Service extends CommonExportService<ValBalanceStatementQuery001Dto> {

    @Autowired
    private ValBalanceStatementQuery002Mapper valBalanceStatementQuery002Mapper;

    public Page<ValBalanceStatementQuery002Dto> selectForPage(Map<String, Object> pageParam) throws Exception {
        List<ValBalanceStatementQuery002Dto> list = valBalanceStatementQuery002Mapper.selectAll(pageParam);
        return new Page(new PageInfo(list));
    }

    public Page<ValBalanceStatementQuery003Dto> selectForVouchPage(Map<String, Object> params) {
        List<ValBalanceStatementQuery003Dto> list = valBalanceStatementQuery002Mapper.selectForVouch(params);
        return new Page(new PageInfo(list));
    }

    public Page<ValBalanceStatementQuery004Dto> selectForTradePage(Map<String, Object> params) {
        params.put("busiTypeOrder", DictCodeList.业务类型_指令交易.getValue());
        List<ValBalanceStatementQuery004Dto> list = valBalanceStatementQuery002Mapper.selectForTrade(params);
        return new Page(new PageInfo(list));
    }

    public FinProductDto getFinprodByBoksetId(Map<String, Object> params) {
        return  valBalanceStatementQuery002Mapper.getFinprodByBoksetId(params);
    }
}
