package com.joyintech.service.trade.option.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.trade.option.dao.TrdOptionTradeMapper;
import com.joyintech.service.trade.option.dto.TrdOptionTradeDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 期权交易微服务Service
 */
@Service
public class TrdOptionTradeService  extends BaseService<TrdOptionTradeDto> {
    @Autowired
    private TrdOptionTradeMapper trdOptionTradeMapper;

    /**
     * 查询拆借交易信息
     *
     * @param page page
     * @param length length
     * @param pageParam 参数
     * @return 拆借交易信息
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<TrdOptionTradeDto> selectForPage(int page, int length, Map<String, Object> pageParam)
            throws Exception {
        pageParam.put("VALID_STATUS_01", DictCodeListExt1.交易流程状态_流程中.getValue());
        pageParam.put("VALID_STATUS_02", DictCodeListExt1.交易流程状态_有效.getValue());
        pageParam.put("VALID_STATUS_03", DictCodeListExt1.交易流程状态_撤销.getValue());
        pageParam.put("PS_T01", DictCodeList.交易方向_买入.getValue());
        pageParam.put("PS_T02", DictCodeList.交易方向_卖出.getValue());
        pageParam.put("FINPROD_TYPE_TG_F35", DictCodeListExt3.金融产品类型_投管用_期权.getValue());
        pageParam.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        pageParam.put("YES_NO_YES", DictCodeList.是否_是.getValue());
        pageParam.put("YES_NO_NO", DictCodeList.是否_否.getValue());
        pageParam.put("CASH_TYPE_1_02", DictCodeListExt1.现金流一级类型_利息.getValue());
        return this.queryForPage(page, length, () -> trdOptionTradeMapper.selectDeal(pageParam));
    }

    /**
     * 合约标的查询
     * @param param
     * @return
     */
    public List<SelectOptionDto> selectFinprodIdAbbr(Map<String, Object> param){
        return trdOptionTradeMapper.selectFinprodIdAbbr(param);
    }
    /**
     * 根据工作流代码查询交易编号
     * @param workflowId 工作流代码
     * @return 汇总交易编号
     */
    public String  selectTradeIdByWorkflowId(String workflowId){
        return trdOptionTradeMapper.selectTradeIdByWorkflowId(workflowId);
    }
    /**
     * 根据交易编号查询数据信息
     * @param tradeId 交易编号
     * @return 数据信息
     */
    public TrdOptionTradeDto getTrdOptionTradeDto(String tradeId, String BRANCH_0){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tradeId", tradeId);
        map.put("BRANCH_0", BRANCH_0);
        map.put("CASH_TYPE_1_02", DictCodeListExt1.现金流一级类型_利息.getValue());
        return trdOptionTradeMapper.getTrdOptionTradeDto(map);
    }

    /**
     * 根据交易编号查询流程中数据是否存在
     *
     * @param tradeId 交易编号
     * @return 流程中数据是否存在
     * @see
     * @since 1.0
     */
    public Boolean isInFlowDataExisted(String tradeId) {
        return trdOptionTradeMapper.selectInFlowDataCount(tradeId) > 0;
    }

    // 存在存续期交易存在时不能撤销check
    /**
     * 根据交易编号查询存续期数据条数
     *
     * @param tradeId 交易编号
     * @return 存续期数据是否存在
     * @see
     * @since 1.0
     */
    public Boolean selectDurationDataCount(String tradeId) {
        Map<String, Object> param =  new HashMap<String, Object>();
        param.put("tradeId", tradeId);
        param.put("tradeStatus", DictCodeListExt1.交易状态_初始.getValue());
        param.put("isCancel", DictCodeList.是否_否.getValue());
        return trdOptionTradeMapper.selectDurationDataCount(param) > 0;
    }

    /**
     * 根据交易编号查询该交易是否已被撤销
     *
     * @param tradeId 交易编号
     * @return 该交易是否已被撤销
     * @see
     * @since 1.0
     */
    public Boolean isAlreadyRevoked(String tradeId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("tradeId", tradeId);
        paramMap.put("YES_NO_YES", DictCodeList.是否_是.getValue());
        paramMap.put("TRADE_STATUS_03", DictCodeListExt1.交易流程状态_撤销.getValue());
        return trdOptionTradeMapper.selectRevokedDataCount(paramMap) > 0;
    }

    /**
     * 根据交易编号查询是否已做流出资金核对
     *
     * @param tradeId 交易编号
     * @return 该交易是否已做流出资金核对
     * @see
     * @since 1.0
     */
    public Boolean isAlreadyLqdPipedTrade(String tradeId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("tradeId", tradeId);
        return trdOptionTradeMapper.selectLqdPipedTrade(paramMap) > 0;
    }

    /**
     * 根据标的代码查询标的类型
     * @param targetId 标的代码
     * @return 标的类型
     */
    public String selectTargetType(String targetId) {

        return trdOptionTradeMapper.selectTargetType(targetId);
    }

    /**
     /**
     * 根据委托编号查询信息
     *
     * @param entrustId 委托编号
     * @return 数据
     * @see
     * @since 1.0
     */
    public TrdOptionTradeDto findTradeInfoByEntrustId(String entrustId) {
        return trdOptionTradeMapper.findTradeInfoByEntrustId(entrustId);
    }

    /**
     * 查询委托所做交易的期权费总额
     *
     * @param entrustId 委托编号
     * @return 数量
     * @see
     * @since 1.0
     */
    public BigDecimal getPrinAmt(String entrustId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        map.put("YES_NO_NO", DictCodeList.是否_否.getValue());
        map.put("entrustId", entrustId);
        return trdOptionTradeMapper.getPrinAmt(map);
    }
}
