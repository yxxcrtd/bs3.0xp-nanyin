/*
 * 文件名：TrdCommandDispatcher001Mapper.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：wangkang 修改时间：2018年9月12日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.period.trdqueryduration.controller;

import java.util.List;
import java.util.Map;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.period.trdqueryduration.dto.TrdQueryDurationDto;
import com.joyintech.service.period.trdqueryduration.service.TrdQueryDuration001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;

/**
 * 微服务端存续期交易查询controller
 * 
 * @author wangyang1
 */
@RestController("backTrdQueryDuration001Controller")
@RequestMapping("/TrdQueryDuration001")
public class TrdQueryDuration001Controller {

    /**
     * 微服务流程中交易查询service
     */
    @Autowired
    private TrdQueryDuration001Service trdQueryDuration001Service;

    /**
     * 流程中的搜索
     * 
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<TrdQueryDurationDto> selectForPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                   @PathVariable("length") int length)
        throws Exception {
        return trdQueryDuration001Service.findPage(page, length, param);
    }

    /**
     * 证券账户合作资产联想文本框
     *
     * @return page
     * @throws Exception
     */
    @PostMapping("getSecAcctIdAndChlIds/{page}/{length}")
    public Page<SelectOptionDto> getSecAcctIdAndChlIds(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                    @PathVariable("length") int length)
            throws Exception {
        return trdQueryDuration001Service.getSecAcctIdAndChlIds(page, length, param);
    }

    /**
     * 资产代码联想文本框
     * 
     * @param finprodMarketId
     * @return
     * @throws Exception
     */
    @GetMapping("/getFinprodMarketIds")
    public List<SuggestDto> getFinprodMarketIds(String finprodMarketId)
        throws Exception {
        return trdQueryDuration001Service.getFinprodMarketIds(finprodMarketId);
    }

    /**
     * 组合代码联想文本框
     *
     * @param portfolioId
     * @return
     * @throws Exception
     */
    @PostMapping("getPortfolioIds/{page}/{length}")
    public Page<SelectOptionDto> getPortfolioId(@RequestBody Map<String,Object> map, @PathVariable("page") int page, @PathVariable("length") int length){
        return trdQueryDuration001Service.getPortfolioIds(page, length, map);
    }
    
    
    /**
     * 获取资产的type6
     * 
     * @param finprodMarketId
     * @return
     * @throws Exception
     */
    @GetMapping("/getType6")
    public String getType6(String finprodId)
        throws Exception {
        return trdQueryDuration001Service.getType6(finprodId);
    }
}
