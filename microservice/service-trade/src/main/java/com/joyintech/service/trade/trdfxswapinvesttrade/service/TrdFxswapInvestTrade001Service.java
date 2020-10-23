/*
 * 文件名：TrdFxswapInvestTrade001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年6月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.trade.trdfxswapinvesttrade.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.trade.trdfxswapinvesttrade.dao.TrdFxswapInvestTrade001Mapper;
import com.joyintech.service.trade.trdfxswapinvesttrade.dto.TrdFxswapInvestTrade001Dto;
import com.joyintech.tams.common.base.dao.PaTrdProductDealMapper;
import com.joyintech.tams.common.base.dao.PtlTradeMapper;
import com.joyintech.tams.common.base.dao.WflPaTrdProductDealMapper;
import com.joyintech.tams.common.base.dao.WflStlPayOrdersMapper;
import com.joyintech.tams.common.base.dao.WflTrdProductDealAddMapper;
import com.joyintech.tams.common.base.dao.WflTrdProductDealMapper;
import com.joyintech.tams.common.base.dao.WflTrdProductDealOthMapper;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;

/**
 * 外汇掉期投资交易Service
 * 
 * @author renping
 * @version 1.0 2019年6月25日
 * @see TrdFxswapInvestTrade001Service
 * @since 1.0
 */
@Service("backTrdFxswapInvestTrade001Service")
@Transactional(rollbackFor = Exception.class)
public class TrdFxswapInvestTrade001Service extends BaseService<TrdFxswapInvestTrade001Dto> {

    /**
     * 划款指令业务流程Mapper
     */
    @Autowired
    private WflPaTrdProductDealMapper wflPaTrdProductDealMapper;

    /**
     * 金融产品交易业务流程其他信息Mapper
     */
    @Autowired
    private WflStlPayOrdersMapper wflStlPayOrdersMapper;

    /**
     * 金融产品交易业务流程Mapper
     */
    @Autowired
    private WflTrdProductDealMapper wflTrdProductDealMapper;

    /**
     * 金融产品交易附加业务流程Mapper
     */
    @Autowired
    private WflTrdProductDealAddMapper wflTrdProductDealAddMapper;

    /**
     * 金融产品交易业务流程其他信息Mapper
     */
    @Autowired
    private WflTrdProductDealOthMapper wflTrdProductDealOthMapper;

    /**
     * 组合交易表处理Mapper
     */
    @Autowired
    private PtlTradeMapper ptlTradeMapper;

    /**
     * 金融产品交易Mapper
     */
    @Autowired
    private PaTrdProductDealMapper paTrdProductDealMapper;

    /**
     * 组合可用现金和实际现金Service
     */
    @Autowired(required = false)
    private BasePtlCashPositionInfoService basePtlCashPositionInfoService;

    /**
     * 注入外汇掉期投资交易mapper
     */
    @Autowired
    private TrdFxswapInvestTrade001Mapper trdFxswapInvestTrade001Mapper;

    /**
     * 分页查询
     * 
     * @param map 查询参数
     * @return 外汇掉期投资交易实体类
     * @see
     * @since 1.0
     */
    public Page<TrdFxswapInvestTrade001Dto> selectForPage(int page, int length,
                                                          Map<String, Object> map) {
        map.put("validStatus_02", DictCodeListExt1.交易流程状态_有效.getValue());
        map.put("validStatus_01", DictCodeListExt1.交易流程状态_流程中.getValue());
        map.put("validStatus_03", DictCodeListExt1.交易流程状态_撤销.getValue());
        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_掉期.getValue());
        map.put("tradeFlowStatus", DictCodeListExt1.交易流程状态_流程中.getValue());
        map.put("psBuy", DictCodeList.交易方向_买入.getValue());
        return super.queryForPage(page, length,
            () -> trdFxswapInvestTrade001Mapper.selectForPage(map));
    }

    /**
     * 由交易编号带出交易信息
     * 
     * @param tradeId 交易编号
     * @param tradeStatus 交易状态
     * @return 交易信息
     * @see
     * @since 1.0
     */
    public TrdFxswapInvestTrade001Dto getDealInfo(String tradeId, String tradeStatus) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tradeId", tradeId);
        map.put("tradeStatus", tradeStatus);
        Page<TrdFxswapInvestTrade001Dto> dtoList = this.selectForPage(1, 1, map);
        if (dtoList != null && dtoList.getRoot() != null && dtoList.getRoot().size() > 0) {
            return dtoList.getRoot().get(0);
        }
        return null;
    }

    /**
     * 由委托代码带出交易信息
     * 
     * @param entrustId 委托代码
     * @return 交易信息
     * @see
     * @since 1.0
     */
    public TrdFxswapInvestTrade001Dto getDealInfoByEntrustId(String entrustId) {
        return trdFxswapInvestTrade001Mapper.getDealInfoByEntrustId(entrustId);
    }

    /**
     * 查询组合交易货币与对应货币账户可用现金
     * 
     * @param map 查询参数
     * @return 数值集合
     * @see
     * @since 1.0
     */
    public List<SuggestDto> available(Map<String, Object> map) {
        SuggestDto sDto = new SuggestDto();
        sDto.setCode("T");
        List<SuggestDto> list = new ArrayList<SuggestDto>();
        list.add(sDto);
        PtlCashPositionFlowDto dto = new PtlCashPositionFlowDto();
        dto.setPortfolioId(map.get("portfolioId").toString());
        dto.setCdate(DateUtil.parse(map.get("tradeDate").toString()));
        if (StringUtil.isNotEmpty(map.get("dealCur"))) {
            dto.setCcy(map.get("dealCur").toString());
            BigDecimal s = basePtlCashPositionInfoService.queryUsableCashPosition(dto);
            sDto.setName(s.toString());
        }
        sDto = new SuggestDto();
        sDto.setCode("T1");
        list.add(sDto);
        if (StringUtil.isNotEmpty(map.get("corCcy"))) {
            dto.setCcy(map.get("corCcy").toString());
            BigDecimal s = basePtlCashPositionInfoService.queryUsableCashPosition(dto);
            sDto.setName(s.toString());
        }
        return list;
    }

    /**
     * 查询交易员
     * 
     * @param map 委托代码，接受状态
     * @return 交易员
     * @see
     * @since 1.0
     */
    public String getTrader(Map<String, Object> map) {
        return trdFxswapInvestTrade001Mapper.getTrader(map);
    }

    /**
     * 获取账号代码
     * 
     * @return 账号
     * @see
     * @since 1.0
     */
    public String getAcctNo(Map<String, Object> map) {
        map.put("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
        return trdFxswapInvestTrade001Mapper.getAcctNo(map);
    }

    /**
     * 新建交易
     * 
     * @param dto 外汇掉期投资交易实体类
     * @return 是否成功
     * @see
     * @since 1.0
     */
    public int insert(TrdFxswapInvestTrade001Dto dto) {
        ptlTradeMapper.insert(dto.getPtlTrade());
        if (StringUtil.isNotEmpty(dto.getPtlTrade()) && StringUtil.isNotEmpty(dto.getPortfolioId())
            && !StringUtil.equals(dto.getPtlTrade().getPortfolioId(), dto.getPortfolioId())) {
            dto.getPtlTrade().setPortfolioId(dto.getPortfolioId());
            ptlTradeMapper.insert(dto.getPtlTrade());
        }
        else if (StringUtil.isNotEmpty(dto.getPtlTrade())
                 && StringUtil.isEmpty(dto.getPortfolioId())) {
            String pPortfolioId = AllTradeCommonUtil.exchangePortfolio(
                dto.getPtlTrade().getPortfolioId());
            if (!StringUtil.equals(dto.getPortfolioId(), pPortfolioId)) {
                dto.getPtlTrade().setPortfolioId(pPortfolioId);
                ptlTradeMapper.insert(dto.getPtlTrade());
            }
        }
        paTrdProductDealMapper.insert(dto.getPaTrdProductDeal());
        return 1;
    }

    /**
     * 撤销组合交易表和金融产品扩展表数据
     * 
     * @param dto 外汇掉期投资交易实体类
     * @return 是否成功
     * @see
     * @since 1.0
     */
    public int undo(TrdFxswapInvestTrade001Dto dto) {
        // 共通查询中没有组合交易表，所以撤销时挂了，先删除 2019-09-04 start
//         trdFxswapInvestTrade001Mapper.updatePtlTrade(dto.getPtlTrade());
//         trdFxswapInvestTrade001Mapper.updateTrdProduct(dto.getPaTrdProductDeal());
         // 共通查询中没有组合交易表，所以撤销时挂了，先删除 2019-09-04 end
        return 1;
    }

    /**
     * 作废清空流程表数据
     * 
     * @param workflowId 工作流ID
     * @see
     * @since 1.0
     */
    public void doDelete(String workflowId) {
        wflPaTrdProductDealMapper.deleteByWorkflowId(workflowId);
        wflStlPayOrdersMapper.deleteByWorkflowId(workflowId);
        wflTrdProductDealMapper.deleteByWorkflowId(workflowId);
        wflTrdProductDealAddMapper.deleteByWorkflowId(workflowId);
        wflTrdProductDealOthMapper.deleteByWorkflowId(workflowId);
    }

    /**
     * 获取划款指令实体类
     * 
     * @param tradeId 交易编号
     * @return 还款指令实体类
     * @see
     * @since 1.0
     */
    public List<StlPayOrders> stlInfo(String tradeId) {
        return trdFxswapInvestTrade001Mapper.stlInfo(tradeId);
    }

    /**
     * 获取到期交易交易编号
     * 
     * @param tradeId 交易编号
     * @return 交易编号
     * @see
     * @since 1.0
     */
    public String getTradeIdDur(String tradeId) {
        Map<String, Object> map = new HashMap<>();
        map.put("tradeId", tradeId);
        map.put("ps", DictCodeList.交易方向_到期.getValue());
        map.put("isCancel", DictCodeList.是否_否.getValue());
        return trdFxswapInvestTrade001Mapper.getTradeIdDur(map);
    }
}
