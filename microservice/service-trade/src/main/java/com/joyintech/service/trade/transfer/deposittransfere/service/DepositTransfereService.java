package com.joyintech.service.trade.transfer.deposittransfere.service;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.trade.transfer.deposittransfere.dao.DepositTransfereMapper;
import com.joyintech.service.trade.transfer.deposittransfere.dto.DepositTransfereService001Dto;
import com.joyintech.service.trade.transfer.deposittransfere.dto.DepositTransfereService002Dto;
import com.joyintech.tams.common.base.dao.PaTrdProductDealMapper;
import com.joyintech.tams.common.base.dao.PtlTradeMapper;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 存款类交易转仓Service
 * @author hepengli
 * @version 1.0 2019年12月14日
 * @see DepositTransfereService
 * @since 1.0
 */
@Service("backDepositTransfereService")
@Transactional(rollbackFor=Exception.class)
public class DepositTransfereService{

    @Autowired
    private DepositTransfereMapper depositTransfereMapper;

    @Autowired
    private PaTrdProductDealMapper paTrdProductDealMapper;

    @Autowired
    private PtlTradeMapper ptlTradeMapper;

    /**
     * 查询组合代码
     *
     * @param param
     * @return
     */
    public List<SelectOptionDto> selectPortoId(Map<String, Object> param) {
        param.put("PORTFOLIO_TYPE_01", DictCodeListExt1.组合类型_投资类组合.getValue());
        param.put("sysDate", DateUtil.getSysDate());
        param.put("ACCT_TYPE_A01",DictCodeListExt1.资金账户类型_产品托管户.getValue());
        return depositTransfereMapper.selectPortoId(param);
    }

    /**
     * 查询利息信息
     *
     * @param param
     * @return
     */
    public DepositTransfereService001Dto queryAssetInt(Map<String, Object> param) {
        return depositTransfereMapper.queryAssetInt(param);
    }

    /**
     * 获取登记编号
     *
     * @param map 查询参数
     * @return 登记编号
     * @see
     * @since 1.0
     */
    public String getProdRegistCode(Map<String, Object> map) {
        return depositTransfereMapper.getProdRegistCode(map);
    }

    /**
     * 插入业务表
     *
     * @return
     * @see
     * @since 1.0
     */
    public String doComplete(DepositTransfereService002Dto DepositTransfereService002Dto) {
        String updateUser = DepositTransfereService002Dto.getUpdateUser();
        Date updateTime = new Date();
        if (!StringUtil.isEmptyList(DepositTransfereService002Dto.getPaTrdProductDeals())) {
            for (PaTrdProductDeal paTrdProductDeal : DepositTransfereService002Dto.getPaTrdProductDeals()) {
                paTrdProductDeal.setUpdateUser(updateUser);
                paTrdProductDeal.setUpdateTime(updateTime);
                paTrdProductDealMapper.insertSelective(paTrdProductDeal);
            }
        }
        if (!StringUtil.isEmptyList(DepositTransfereService002Dto.getPtlTrade())) {
                    for (PtlTrade ptlTrade : DepositTransfereService002Dto.getPtlTrade()) {
                        ptlTrade.setUpdateUser(updateUser);
                        ptlTrade.setUpdateTime(updateTime);
                        ptlTradeMapper.insertSelective(ptlTrade);
                    }
            }
        return Boolean.TRUE.toString();
    }

}
