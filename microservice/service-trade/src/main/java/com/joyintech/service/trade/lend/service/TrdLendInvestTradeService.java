/*
 * 文件名：TrdLendInvestTradeService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.trade.lend.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.trade.lend.dao.TrdLendInvestTradeMapper;
import com.joyintech.service.trade.lend.dto.TrdLendInvestTradeDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 拆借交易微服务Service
 */
@Service
public class TrdLendInvestTradeService extends BaseService<TrdLendInvestTradeDto> {

    /**
     * TrdLendInvestTradeMapper
     */
    @Autowired
    private TrdLendInvestTradeMapper trdLendInvestTradeMapper;

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
    public Page<TrdLendInvestTradeDto> selectForPage(int page, int length,
                                                     Map<String, Object> pageParam)
        throws Exception {
        pageParam.put("VALID_STATUS_01", DictCodeListExt1.交易流程状态_流程中.getValue());
        pageParam.put("VALID_STATUS_02", DictCodeListExt1.交易流程状态_有效.getValue());
        pageParam.put("VALID_STATUS_03", DictCodeListExt1.交易流程状态_撤销.getValue());
//        pageParam.put("BRANCH_0", CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
        pageParam.put("YES_NO_YES", DictCodeList.是否_是.getValue());
        pageParam.put("YES_NO_NO", DictCodeList.是否_否.getValue());
        pageParam.put("FINPROD_TYPE_TG_F08", DictCodeListExt1.金融产品类型_投管用_拆入.getValue());
        pageParam.put("FINPROD_TYPE_TG_F09", DictCodeListExt1.金融产品类型_投管用_拆出.getValue());
        pageParam.put("FINPROD_TYPE_TG_F25", DictCodeListExt1.金融产品类型_投管用_特拆拆入.getValue());
        pageParam.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        pageParam.put("PINAN_BUSINESS_TYPE_14", DictCodeListExt1.平安业务类型_普通拆入.getValue());
        pageParam.put("PINAN_BUSINESS_TYPE_15", DictCodeListExt1.平安业务类型_特拆拆入.getValue());
        pageParam.put("PINAN_BUSINESS_TYPE_16", DictCodeListExt1.平安业务类型_普通拆出.getValue());

        return this.queryForPage(page, length,
            () -> trdLendInvestTradeMapper.selectDeal(pageParam));
    }

    public TrdLendInvestTradeDto getTrdBondInvestTradeDto(String tradeId, String BRANCH_0) {
        return trdLendInvestTradeMapper.getTrdLendInvestTradeDto(tradeId, BRANCH_0);
    }

    /**
     * 根据工作流代码查询交易编号
     *
     * @param workflowId 工作流代码
     * @return 交易编号
     * @see
     * @since 1.0
     */
    public String selectTradeIdByWorkflowId(String workflowId) {
        return trdLendInvestTradeMapper.selectTradeIdByWorkflowId(workflowId);
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
        return trdLendInvestTradeMapper.selectInFlowDataCount(tradeId) > 0;
    }

    // 存在存续期交易存在时不能撤销check增加 add by renping 20191017 start 
    /**
     * 根据交易编号查询存续期数据条数
     *
     * @param tradeId 交易编号
     * @return 存续期数据是否存在
     * @see
     * @since 1.0
     */
    public Boolean selectDurationDataCount(String tradeId) {
        Map<String, Object> param =  new HashMap<>();
        param.put("tradeId", tradeId);
        param.put("tradeStatus", DictCodeListExt1.交易状态_初始.getValue());
        param.put("isCancel", DictCodeList.是否_否.getValue());
        return trdLendInvestTradeMapper.selectDurationDataCount(param) > 0;
    }
    // 存在存续期交易存在时不能撤销check增加 add by renping 20191017 end 

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
        return trdLendInvestTradeMapper.selectRevokedDataCount(paramMap) > 0;
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
        return trdLendInvestTradeMapper.selectLqdPipedTrade(paramMap) > 0;
    }
}
