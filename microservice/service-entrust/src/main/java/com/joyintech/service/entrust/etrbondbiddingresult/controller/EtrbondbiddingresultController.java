package com.joyintech.service.entrust.etrbondbiddingresult.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.entrust.etrbondbiddingresult.dto.EtrbondbiddingresultDto;
import com.joyintech.service.entrust.etrbondbiddingresult.service.EtrbondbiddingresultService;
import com.joyintech.tams.common.base.entity.FinProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController("etrbondbiddingresultCtl_dtx")
@RequestMapping("/Etrbondbiddingresult")
public class EtrbondbiddingresultController {

    @Autowired
    private EtrbondbiddingresultService etrbondbiddingresultService;

    /**
     * 使用给定的acctName检索分页数据。返回类型是Service的泛型。 <功能详细描述>
     *
     * @param pageParam int
     * @param page int
     * @param length int
     * @return Page<EtrFundDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<EtrbondbiddingresultDto> selectForPage(@RequestBody Map<String, Object> pageParam, @PathVariable("page") int page,
                                                       @PathVariable("length") int length)
        throws Exception {
        return etrbondbiddingresultService.selectForPage(page, length, pageParam);
    }

    /**
     * 通过原金融产品代码查询产品信息
     *
     * @param map
     * @return
     */
    @PostMapping("/findFinProductByOFinprodId")
    public List<FinProduct> findFinProductByOFinprodId(@RequestBody Map<String, Object> map) {
        return etrbondbiddingresultService.findFinProductByOFinprodId(map);
    }

    /**
     * 修改交易ID通过委托ID和中标标位
     *
     * @param map
     * @return
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/updateTradeId")
    public void updateTradeId(@RequestBody Map<String, Object> map) {
        etrbondbiddingresultService.updateTradeId(map);
    }

    /**
     * 通过交易id和委托id及标位获取需要冻结的头寸
     *
     * @param map
     * @return
     */
    @PostMapping("/getisFrozenPosition")
    public BigDecimal getisFrozenPosition(@RequestBody Map<String, Object> map) {
        return etrbondbiddingresultService.getisFrozenPosition(map);
    }

    @Transactional
    @DTXTransaction
    @PostMapping("/deleteTradeId")
    public void deleteTradeId(@RequestBody Map<String, Object> map) {
        etrbondbiddingresultService.deleteTradeId(map);
    }

    @PostMapping("/checkFrozenPosition")
    public Integer checkFrozenPosition(@RequestBody Map<String, Object> map) {
        return etrbondbiddingresultService.checkFrozenPosition(map);
    }

}
