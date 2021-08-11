package com.joyintech.service.valuation.accounadjustmanage.valbalancestatementquery.controller;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.accounadjustmanage.valbalancestatementquery.dto.FinProductDto;
import com.joyintech.service.valuation.accounadjustmanage.valbalancestatementquery.dto.ValBalanceStatementQuery002Dto;
import com.joyintech.service.valuation.accounadjustmanage.valbalancestatementquery.dto.ValBalanceStatementQuery003Dto;
import com.joyintech.service.valuation.accounadjustmanage.valbalancestatementquery.dto.ValBalanceStatementQuery004Dto;
import com.joyintech.service.valuation.accounadjustmanage.valbalancestatementquery.service.ValBalanceStatementQuery002Service;
import com.joyintech.tams.common.base.dao.BokBussDetsubMapper;
import com.joyintech.tams.common.base.entity.BokBussDetsub;
import com.joyintech.tams.common.base.entity.BokBussDetsubKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController(value="serviceValBalanceStatementQuery002Controller")
@RequestMapping("ValBalanceStatementQuery002")
public class ValBalanceStatementQuery002Controller extends BaseController {

    @Autowired
    private BokBussDetsubMapper bokBussDetsubMapper;

    @Autowired
    private ValBalanceStatementQuery002Service valBalanceStatementQuery002Service;

    /**
     * 根据科目查询对应资产信息
     *
     * @param params
     * @return
     */
    @PostMapping("/getSubnameTail")
    public BokBussDetsub getSubnameTail(@RequestBody Map<String, Object> params) {
        BokBussDetsubKey bokBussDetsubKey=new BokBussDetsubKey();
        bokBussDetsubKey.setBooksetId((String) params.get("booksetId"));
        bokBussDetsubKey.setDetailSubjectNo((String) params.get("subjectNo"));
        return bokBussDetsubMapper.selectByPrimaryKey(bokBussDetsubKey);
    }

    /**
     * 根据账套查询对应信息
     *
     * @param params
     * @return
     */
    @PostMapping("/getFinprodByBoksetId")
    public FinProductDto getFinprodByBoksetId(@RequestBody Map<String, Object> params) {
        return valBalanceStatementQuery002Service.getFinprodByBoksetId(params);
    }

    /**
     * 余额流水信息查询
     *
     * @param params
     * @return
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<ValBalanceStatementQuery002Dto> findPage(@RequestBody Map<String,Object> params,
                                                         @PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception {
        Page<ValBalanceStatementQuery002Dto> pages = valBalanceStatementQuery002Service.selectForPage(params);
        return pages;
    }

    /**
     * 凭证信息查询
     *
     * @param params
     * @return
     */
    @PostMapping("/selectForVouchPage/{page}/{length}")
    public Page<ValBalanceStatementQuery003Dto> selectForVouchPage(@RequestBody Map<String,Object> params,
                                                                   @PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception {
        Page<ValBalanceStatementQuery003Dto> pages = valBalanceStatementQuery002Service.selectForVouchPage(params);
        return pages;
    }

    /**
     * 交易信息查询
     *
     * @param params
     * @return
     */
    @PostMapping("/selectForTradePage/{page}/{length}")
    public Page<ValBalanceStatementQuery004Dto> selectForTradePage(@RequestBody Map<String,Object> params,
                                                                   @PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception {
        Page<ValBalanceStatementQuery004Dto> pages = valBalanceStatementQuery002Service.selectForTradePage(params);
        return pages;
    }
}
