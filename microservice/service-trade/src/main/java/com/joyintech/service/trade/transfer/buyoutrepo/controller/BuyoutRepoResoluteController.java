package com.joyintech.service.trade.transfer.buyoutrepo.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.trade.transfer.buyoutrepo.dto.BuyoutRepoResolute001Dto;
import com.joyintech.service.trade.transfer.buyoutrepo.service.BuyoutRepoResoluteService;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.base.report.asset.BaseRepAssetDeptInfoService;
import com.joyintech.tams.component.base.report.asset.BaseRepBankBackAssetInfoService;
import com.joyintech.tams.component.base.report.asset.BaseRepDlRepoInfoService;
import com.joyintech.tams.component.base.report.trade.BaseRepTradeInfoService;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.component.base.group.BasePtlSecPositionInfoService;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.PtlPositionHoldDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 买断式回购交易拆分
 * @author liuwei
 * @version 1.0 2019年11月20日
 * @see BuyoutRepoResoluteController
 * @since 1.0
 */
@RestController("BuyoutRepoResoluteCtr_dtx")
@RequestMapping("/BuyoutRepoResolute")
public class BuyoutRepoResoluteController {

    /**
     * 组合可用现金和实际现金查询更新组件
     */
    @Autowired
    private BasePtlCashPositionInfoService basePtlCashPositionInfoServiceImpl;

    /**
     * 组合可用持仓和实际持仓查询更新组件接口
     */
    @Autowired
    private BasePtlSecPositionInfoService basePtlSecPositionInfoServiceImpl;

    /**
     * 注入买断式回购交易拆分Service
     */
    @Autowired
    private BuyoutRepoResoluteService buyoutRepoResoluteService;

    /**
     * 交易报送信息组件
     */
    @Autowired
    private BaseRepTradeInfoService baseRepTradeInfoService;

    /**
     * 资产负债信息报送组件
     */
    @Autowired
    private BaseRepAssetDeptInfoService baseRepAssetDeptInfoService;

    /**
     * BaseRepBankBackAssetInfoService
     */
    @Autowired
    private BaseRepBankBackAssetInfoService baseRepBankBackAssetInfoService;

    /**
     * BaseRepBankBackAssetInfoService
     */
    @Autowired
    private BaseRepDlRepoInfoService baseRepDlRepoInfoService;

    /**
     * 资产代码带出的信息
     * @param map 资产代码
     * @return 资产类投资交易实体类
     * @see
     * @since 1.0
     */
    @PostMapping("selectTradeInfo")
    public BuyoutRepoResolute001Dto selectTradeInfo(@RequestBody Map<String,Object> map){
        return buyoutRepoResoluteService.selectTradeInfo(map);
    }

    /**
     * 查询已拆分金额
     *
     * @return 拆分前交易编号
     * @see
     * @since 1.0
     */
    @GetMapping("getDistAmt")
    public BigDecimal getDistAmt(String tradeId){
        return buyoutRepoResoluteService.getDistAmt(tradeId);
    }

    /**
     * 获取拆分信息—组合代码
     * @return 联想文本框实体类集合
     * @see
     * @since 1.0
     */
    @PostMapping("getPortfolioId")
    public List<SelectOptionDto> getPortfolioId(@RequestBody Map<String,Object> map){
        return buyoutRepoResoluteService.getPortfolioId(map);
    }

    /**
     * 查询托管划款指令
     * <功能详细描述>
     * @param tradeId 查询条件
     * @see
     * @since 1.0
     */
    @GetMapping("selectStlPayOrder")
    public StlPayOrders selectStlPayOrder(String tradeId){
        return buyoutRepoResoluteService.selectStlPayOrder(tradeId);
    }

    /**
     * 查询组合托管户
     * @return 组合托管户
     * @see
     * @since 1.0
     */
    @PostMapping("getAccountInfo")
    public String getAccountInfo(@RequestBody Map<String,Object> map){
        return buyoutRepoResoluteService.getAccountInfo(map);
    }

    /**
     * 根据工作流代码获取交易编号
     * @param workflowId 工作流代码
     * @return Db
     */
    @Transactional
    @DTXTransaction
    @GetMapping("selectWflTrdProductDeal")
    public List<WflTrdProductDeal> selectWflTrdProductDeal(String workflowId){
        return buyoutRepoResoluteService.selectWflTrdProductDeal(workflowId);
    }

    /**
     * 根据交易编号删除质押品信息
     * @param tradeId 交易编号
     * @return Db
     */
    @Transactional
    @DTXTransaction
    @GetMapping("deleteFinDealPledgeByTradeId")
    public int deleteFinDealPledgeByTradeId(String tradeId){
        return buyoutRepoResoluteService.deleteFinDealPledgeByTradeId(tradeId);
    }

    /**
     * 根据交易编号删除交易类金融产品
     * @param tradeId 交易编号
     * @return Db
     */
    @Transactional
    @DTXTransaction
    @GetMapping("deleteFinTradeProductByTradeId")
    public int deleteFinTradeProductByTradeId(String tradeId){
        return buyoutRepoResoluteService.deleteFinTradeProductByTradeId(tradeId);
    }

    /**
     * 调用基础组件.【交易报送组件】将交易报送信息Entity插入交易报送信息表
     *
     * @param repTradeList 交易报送信息Entity列表
     * @return 成功/失败
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insertRepTrade")
    public boolean insertRepTrade(@RequestBody List<RepTrade> repTradeList) {
        return baseRepTradeInfoService.insert(repTradeList);
    }

    /**
     * 调用资产负债信息报送组件
     *
     * @param repAssetDeptList 资产负债报送信息Entity列表
     * @return 成功/失败
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insertRepAssetDebt")
    public boolean insertRepAssetDebt(@RequestBody List<RepAssetDept> repAssetDeptList) {
        return baseRepAssetDeptInfoService.insert(repAssetDeptList);
    }

    /**
     * 调用负债(同业拆入卖出回购)报送信息组件
     *
     * @param repLiabilitiesList 负债(同业拆入卖出回购)报送信息Entity
     * @return 成功/失败
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insertRepLiabilities")
    public boolean insertRepLiabilities(@RequestBody List<RepLiabilities> repLiabilitiesList) {
        return baseRepBankBackAssetInfoService.insert(repLiabilitiesList);
    }

    /**
     * 调用负债(拆放同业及买入返售)报送信息组件
     *
     * @param repDlRepoInfoList 负债(同业拆入卖出回购)报送信息Entity
     * @return 成功/失败
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insertRepDlRepoInfo")
    public boolean insertRepDlRepoInfo(@RequestBody List<RepAssetInterLend> repDlRepoInfoList) {
        return baseRepDlRepoInfoService.insert(repDlRepoInfoList);
    }

    /**
     * 调用组合可用持仓和实际持仓查询更新组件
     *
     * @return 结果
     */
    @PostMapping("insertPosition")
    @Transactional
    @DTXTransaction
    public boolean insertPosition(@RequestBody PtlPositionHoldDto ptlPositionHoldDto) {
        if (ptlPositionHoldDto != null) {
            if (ptlPositionHoldDto.getPtlSecPositionFlows() != null
                    && !ptlPositionHoldDto.getPtlSecPositionFlows().isEmpty()) {
                for (PtlSecPositionFlowDto ptlSecPositionFlow : ptlPositionHoldDto.getPtlSecPositionFlows()) {
                    basePtlSecPositionInfoServiceImpl.inertSecPositionFlow(ptlSecPositionFlow);
                }
            }
            if (ptlPositionHoldDto.getPtlCashPositionFlows() != null
                    && !ptlPositionHoldDto.getPtlCashPositionFlows().isEmpty()) {
                for (PtlCashPositionFlowDto ptlCashPositionFlow : ptlPositionHoldDto.getPtlCashPositionFlows()) {
                    basePtlCashPositionInfoServiceImpl.insertCashPositionFlow(ptlCashPositionFlow);
                }
            }
        }
        return true;
    }

    /**
     * 根据画面条件检索可融出券信息
     *
     *
     * @param param 画面参数
     * @return List<PledgeBondInfoDto>
     */
    @PostMapping("selectAvaPledgeBondDetailList")
    public List<PledgeBondInfoDto> selectAvaPledgeBondDetailList(@RequestBody Map<String, Object> param) {
        return buyoutRepoResoluteService.selectAvaPledgeBondDetailList(param);
    }

    /**
     * 根据交易编号删除组合持仓流水（持仓类型为融入融出）
     *
     * @param bizCode 交易编号
     * @return List<PtlSecPositionFlowDto>
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @GetMapping("deletePtlSecPositionFlow")
    public long deletePtlSecPositionFlow(String tradeId) {
        return buyoutRepoResoluteService.deletePtlSecPositionFlow(tradeId);
    }
}
