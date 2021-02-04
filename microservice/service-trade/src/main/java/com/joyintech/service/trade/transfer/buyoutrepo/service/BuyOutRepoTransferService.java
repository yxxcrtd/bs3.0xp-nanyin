package com.joyintech.service.trade.transfer.buyoutrepo.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.trade.transfer.buyoutrepo.dao.BuyOutRepoTransferMapper;
import com.joyintech.service.trade.transfer.buyoutrepo.dto.BuyOutRepoTransfer003Dto;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 买断式回购转仓Service
 * @author liuwei
 * @version 1.0 2020年1月6日
 * @see BuyOutRepoTransferService
 * @since 1.0
 */
@Service("backBuyOutRepoTransferService")
@Transactional(rollbackFor=Exception.class)
public class BuyOutRepoTransferService extends BaseService<BuyOutRepoTransfer003Dto> {

    /**
     * 注入买断式回购转仓转仓mapper
     */
    @Autowired
    private BuyOutRepoTransferMapper buyOutRepoTransferMapper;

    /**
     * 获取交易界面
     * @param tradeId 交易编号
     * @return 交易信息
     * @see
     * @since 1.0
     */
    public BuyOutRepoTransfer003Dto getTradeInfo(String tradeId){
        Map<String,Object> map = new HashMap<>();
        map.put("tradeId",tradeId);
        map.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        map.put("YESORNO_Y", DictCodeListExt1.是否_是.getValue());
        return buyOutRepoTransferMapper.getTradeInfo(map);
    }

    /**
     * 获取转仓信息—组合代码
     * @return 联想文本框实体类集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getPortfolioId(Map<String,Object> map){
        return buyOutRepoTransferMapper.getPortfolioId(map);
    }

    /**
     * 获取债券数量
     * @return 债券数量
     * @see
     * @since 1.0
     */
    public BigDecimal selectPosition(@RequestBody Map<String,Object> map){
        return buyOutRepoTransferMapper.selectPosition(map);
    }

    /**
     * 获取待转仓产品组合持有债券总持仓（针对债券和交易）
     * @return 持仓值
     *
     * @see
     * @since 1.0
     */
    public BigDecimal getBondAmount(Map<String,Object> map){
        return buyOutRepoTransferMapper.getBondAmount(map);
    }

    /**
     * 获取待转仓产品组合总持仓（针对交易）
     * @return 持仓值
     *
     * @see
     * @since 1.0
     */
    public BigDecimal getTradeAmount(Map<String,Object> map){
        return buyOutRepoTransferMapper.getTradeAmount(map);
    }

    /**
     * 根据交易编号删除金融产品分类表
     * @param tradeId 交易编号
     * @return Db
     */
    public int deleteFinProductTypeByTradeId(String tradeId){
        return buyOutRepoTransferMapper.deleteFinProductTypeByTradeId(tradeId);
    }
}
