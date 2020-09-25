package com.joyintech.service.portfolio.portfolioanalysis.ptlrepopledgesearch.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.portfolio.portfolioanalysis.ptlrepopledgesearch.dto.PtlRepoPledgeSearchDto;
import com.joyintech.service.portfolio.portfolioanalysis.ptlrepopledgesearch.dto.PtlSubstitutionRepoPledgeDto;
import com.joyintech.service.portfolio.portfolioanalysis.ptlrepopledgesearch.service.PtlRepoPledgeDetailService;
import com.joyintech.service.portfolio.portfolioanalysis.ptlrepopledgesearch.service.PtlRepoPledgeSearchService;
import com.joyintech.service.portfolio.portfolioanalysis.ptlrepopledgesearch.service.PtlSubstitutionRepoPledgeService;
import com.joyintech.tams.common.base.entity.FinPledgeDisplaceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @program: tams-invest-parent
 * @description: PtlRepoPledgeSearchController
 * @author: liuwei
 * @create: 2018-11-28 14:09
 **/
@RestController
@RequestMapping("/PtlRepoPledgeSearch")
public class PtlRepoPledgeSearchController {

    /**
     * 计划类资产（净值型）Service
     */
    @Autowired
    private PtlRepoPledgeSearchService ptlRepoPledgeSearchService;

    //质押券置换查询service
    @Autowired
    private PtlSubstitutionRepoPledgeService ptlSubstitutionRepoPledgeService;

    //质押券置换明细查询service
    @Autowired
    private PtlRepoPledgeDetailService ptlRepoPledgeDetailService;
    /**
     * 组合代码的联想下拉框事件
     *
     * @param map 输入参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @PostMapping("getCounterId")
    public List<SelectOptionDto> getCounterId(@RequestBody Map<String,Object> map){
        return ptlRepoPledgeSearchService.getCounterId(map);
    }

    /**
     * 资产代码的联想下拉框事件
     *
     * @param map 输入参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @PostMapping("getFinprodMarketId")
    public List<SelectOptionDto> getFinprodMarketId(@RequestBody Map<String,Object> map){
        return ptlRepoPledgeSearchService.getFinprodMarketId(map);
    }

    /**
     * 画面查询方法
     *
     * @param param 画面参数
     * @param page 页码数
     * @param length 页面长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<PtlRepoPledgeSearchDto> selectAll(@RequestBody Map<String, Object> param,
                                                  @PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception {
        return ptlRepoPledgeSearchService.selectForPage(page, length, param);
    }

    /**
     * 画面查询方法->质押券置换查询
     *
     * @param param 画面参数
     * @param page 页码数
     * @param length 页面长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("substitutionFindPage/{page}/{length}")
    public Page<PtlSubstitutionRepoPledgeDto> substitutionFindPage(@RequestBody Map<String, Object> param,
                                                                   @PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception {
        return ptlSubstitutionRepoPledgeService.substitutionFindPage(page, length, param);
    }
    /**
     * 画面查询方法->质押券置换明细查询
     *
     * @param param 画面参数
     * @param page 页码数
     * @param length 页面长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("getPledgeDetail/{page}/{length}")
    public Page<FinPledgeDisplaceDetail> getPledgeDetail(@RequestBody Map<String, Object> param,
                                                         @PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception {
        return ptlRepoPledgeDetailService.getPledgeDetail(page, length, param);
    }


}
