package com.joyintech.tams.settle.batch.datagather.reader.repo;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.settle.batch.datagather.dto.SettleTransDataDto;
import com.joyintech.tams.settle.batch.datagather.reader.StlDataGatherReader;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author 何阳阳
 * @date 2020/7/30
 * @see StlDataGatherRepoBaseReader
 * @since 1.0
 */
public abstract class StlDataGatherRepoBaseReader extends StlDataGatherReader {

    @Override
    protected void setSearchParam(Map<String, Object> searchParam) {
        // 业务类型
        searchParam.put("busiType", DictCodeList.业务类型_债券质押式回购交易.getValue());
        // 交易方向列表
        searchParam.put("psList", getPSList());
    }

    @Override
    protected boolean isSecType(String secType) {
        return StringUtil.equals(DictCodeListExt1.证券品种_回购.getValue(), secType);
    }

    protected abstract List<String> getPSList();

    protected SettleTransDataDto[] splitTransData(SettleTransDataDto transDataDto) {
        SettleTransDataDto[] dataDtos = new SettleTransDataDto[2];
        // 还本到账
        SettleTransDataDto dto1 = new SettleTransDataDto();
        // 收息到账
        SettleTransDataDto dto2 = new SettleTransDataDto();
        try {
            BeanUtil.copyProperties(dto1, transDataDto);
            // 交易金额
            dto1.setTradeAmt(transDataDto.getShareAmt());
            // 全价总额
            dto1.setFpriceAmt(transDataDto.getShareAmt());
            // 利息总额
            dto1.setIntAmt(BigDecimal.ZERO);
            // 经手费
            dto1.setJsfAmt(transDataDto.getShareAmt());
            // 过户费
            dto1.setGhfAmt(transDataDto.getShareAmt());
            // 证管费
            dto1.setZgfAmt(transDataDto.getShareAmt());
            // 手续费
            dto1.setSxfAmt(transDataDto.getShareAmt());
            // 结算费
            dto1.setSettleFee(transDataDto.getShareAmt());
            // 交易方向
            dto1.setPs(DictCodeList.交易方向_还本到账.getValue());

            // 交易份额
            dto2.setShareAmt(BigDecimal.ZERO);
            // 交易本金
            dto2.setPrinAmt(BigDecimal.ZERO);
            // 交易金额
            dto2.setTradeAmt(transDataDto.getIntAmt());
            // 全价总额
            dto2.setFpriceAmt(transDataDto.getIntAmt());
            // 经手费
            dto2.setJsfAmt(BigDecimal.ZERO);
            // 过户费
            dto2.setGhfAmt(BigDecimal.ZERO);
            // 证管费
            dto2.setZgfAmt(BigDecimal.ZERO);
            // 手续费
            dto2.setSxfAmt(BigDecimal.ZERO);
            // 结算费
            dto2.setSettleFee(BigDecimal.ZERO);
            // 交易方向
            dto2.setPs(DictCodeList.交易方向_收息到账.getValue());

        } catch (IllegalAccessException | InvocationTargetException e) {
        }
        dataDtos[0] = dto1;
        dataDtos[1] = dto2;
        return dataDtos;
    }
}
