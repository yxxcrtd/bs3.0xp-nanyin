package com.joyintech.service.portfolio.ptlsubfundsmange.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.tams.invest.common.dto.common.TrdCommonParam;
import com.joyintech.tams.invest.common.dto.common.TrdCommonWflParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.portfolio.ptlsubfundsmange.dto.PtlSubFundsMange002Dto;
import com.joyintech.service.portfolio.ptlsubfundsmange.dto.PtlSubFundsMangeDto;
import com.joyintech.service.portfolio.ptlsubfundsmange.service.PtlSubFundsMange002Service;
import com.joyintech.service.portfolio.ptlsubfundsmange.service.PtlSubFundsMangeService;
import com.joyintech.tams.common.base.entity.FinProduct;

@RestController("ptlSubFundsMangeController_dtx")
@RequestMapping("/PtlSubFundsMange")
public class PtlSubFundsMangeController {


    @Autowired
    private PtlSubFundsMangeService ptlSubFundsMangeService;

    @Autowired
    private PtlSubFundsMange002Service ptlSubFundsMange002Service;

    /**
     * 产品简称的下拉框
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/queryProdNameInfo")
    public List<SelectOptionDto> queryProdNameInfo(@RequestBody Map<String, Object> param) {
        return ptlSubFundsMangeService.queryProdNameInfo(param);
    }

    @PostMapping("/doComplete")
    @Transactional
    @DTXTransaction
    public String doComplete(@RequestBody TrdCommonParam trdCommonParam) {
        return ptlSubFundsMangeService.doComplete(trdCommonParam);
    }
    @Transactional
    @DTXTransaction
    @PostMapping("/doInsert")
    public String doInsert(@RequestBody TrdCommonWflParam trdCommonWflParam) {
        return ptlSubFundsMangeService.doInsert(trdCommonWflParam);
    }

    /**
     * 产品代码的下拉框
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/queryProdIdInfo")
    public List<SelectOptionDto> queryProdIdInfo(@RequestBody Map<String, Object> param) {
        return ptlSubFundsMangeService.queryProdIdInfo(param);
    }

    /**
     * <一句话功能简述> <功能详细描述>
     *
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/findIncomeStatisticsPage")
    public List<PtlSubFundsMangeDto> findIncomeStatisticsPage(@RequestBody Map<String, Object> params) throws Exception {
        return ptlSubFundsMangeService.findPage(params);
    }


    /**
     * 通过产品代码，获取产品名称
     *
     * @param pageParam
     * @return
     */

    @PostMapping("/queryProdName")
    public FinProduct queryProdName(@RequestBody Map<String, Object> pageParam) {
        return ptlSubFundsMange002Service.queryProdName(pageParam);
    }

    /**
     * 检查：数据存在有效或者流程中的数据
     *
     * @param pageParam
     * @return
     */
    @PostMapping("/queryDataEffOrInWorkFlowCount")
    public int queryDataEffOrInWorkFlowCount(@RequestBody Map<String, Object> pageParam) {
        return ptlSubFundsMange002Service.queryDataEffOrInWorkFlowCount(pageParam);
    }

    /**
     * 获取资金分配主信息
     *
     * @param pageParam
     * @return
     */
    @PostMapping("/queryPtlSubFundsWithStatue")
    public PtlSubFundsMange002Dto queryPtlSubFundsWithStatue(@RequestBody Map<String, Object> pageParam) {
        return ptlSubFundsMange002Service.queryPtlSubFundsWithStatue(pageParam);
    }

    /**
     * 获取子组合资金分配明细列表
     *
     * @param pageParam
     * @return
     */
    @PostMapping("/selectPortfolioDetail")
    public List<PtlSubFundsMange002Dto> selectPortfolioDetail(@RequestBody Map<String, Object> pageParam) {
        return ptlSubFundsMange002Service.selectPortfolioDetail(pageParam);
    }

    /**
     * 更新组合现金流水中拆分标识
     *
     * @param dto
     * @return
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/updatePtlCashPositionFlowBySeqnos")
    public int updatePtlCashPositionFlowBySeqnos(@RequestBody Map<String, Object> pageParam) {
        return ptlSubFundsMange002Service.updatePtlCashPositionFlowBySeqnos(pageParam);
    }

    /**
     * <一句话功能简述> <功能详细描述>
     * 导出查询
     *
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/findIncomeStatisticsPageList")
    public List<PtlSubFundsMangeDto> findIncomeStatisticsPageList(@RequestBody Map<String, Object> params
    ) throws Exception {
        return ptlSubFundsMangeService.findPageList(params);
    }

}
