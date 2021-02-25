package com.joyintech.service.trade.transfer.planratetransfer.service;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.trade.transfer.planratetransfer.dao.TrdPlanRateInvestTradeTransferMapper;
import com.joyintech.service.trade.transfer.planratetransfer.dto.PlanTransferDto;
import com.joyintech.service.trade.transfer.planratetransfer.dto.PlanTransferDtos;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @package com.joyintech.service.report.hxbank.resolute.planratetransfer.service
 * @author: Xu Zhihao
 * @date: 2019/12/18 10:14
 * @version: V1.0
 * @description:
 */
@Service("backTrdPlanRateInvestTradeTransferService")
public class TrdPlanRateInvestTradeTransferService {

    @Autowired
    private TrdPlanRateInvestTradeTransferMapper trdPlanRateInvestTradeTransferMapper;

    @Autowired
    private TrdProductDealMapper trdProductDealMapper;

    @Autowired
    private PaTrdProductDealMapper paTrdProductDealMapper;

    @Autowired
    private TrdProductDealAddMapper trdProductDealAddMapper;

    @Autowired
    private StlPayOrdersMapper stlPayOrdersMapper;
    @Autowired
    private PtlTradeMapper ptlTradeMapper;

    /**
     * 查询组合代码
     *
     * @param param
     * @return
     */
    public List<SelectOptionDto> selectPortoId(Map<String, Object> param) {
        return trdPlanRateInvestTradeTransferMapper.selectPortoId(param);
    }

    /**
     * 查询利息信息
     *
     * @param param
     * @return
     */
    public PlanTransferDto queryAssetInt(Map<String, Object> param) {
        return trdPlanRateInvestTradeTransferMapper.queryAssetInt(param);
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
        return trdPlanRateInvestTradeTransferMapper.getProdRegistCode(map);
    }

    /**
     * 插入业务表
     *
     * @return
     * @see
     * @since 1.0
     */
    public String doComplete(PlanTransferDtos planTransferDtos) {
        String updateUser = planTransferDtos.getUpdateUser();
        Date updateTime = new Date();
//        List<BaseTradeInfoDto> baseTradeInfoDtos = planTransferDto.getBaseTotalTradeInfoDto().getBaseTradeInfoDtoList();
//        List<BaseTradeProductDealDto> baseTradeProductDealDtos = planTransferDtos.getFinProductTradeDto().getBaseTradeProductDealDtos();
        if (!StringUtil.isEmptyList(planTransferDtos.getPaTrdProductDeals())) {
            for (PaTrdProductDeal paTrdProductDeal : planTransferDtos.getPaTrdProductDeals()) {
                paTrdProductDeal.setUpdateUser(updateUser);
                paTrdProductDeal.setUpdateTime(updateTime);
                paTrdProductDealMapper.insertSelective(paTrdProductDeal);
            }
        }
        if (!StringUtil.isEmptyList(planTransferDtos.getPtlTrade())) {
//            baseTradeProductDealDtos.forEach(baseTradeInfoDto -> {
//            for (BaseTradeProductDealDto baseTradeInfoDto : baseTradeProductDealDtos) {
//
//                if (baseTradeInfoDto.getTrdProductDeal() != null) {
//                    baseTradeInfoDto.getTrdProductDeal().setUpdateUser(updateUser);
//                    baseTradeInfoDto.getTrdProductDeal().setUpdateTime(updateTime);
//                    trdProductDealMapper.insertSelective(baseTradeInfoDto.getTrdProductDeal());
//                }
//
//                if (baseTradeInfoDto.getTrdProductDealAdd() != null) {
//                    baseTradeInfoDto.getTrdProductDealAdd().setUpdateUser(updateUser);
//                    baseTradeInfoDto.getTrdProductDealAdd().setUpdateTime(updateTime);
//                    trdProductDealAddMapper.insertSelective(baseTradeInfoDto.getTrdProductDealAdd());
//                }

//                if (baseTradeInfoDto.getPaTrdProductDeal() != null) {
//                    baseTradeInfoDto.getPaTrdProductDeal().setUpdateUser(updateUser);
//                    baseTradeInfoDto.getPaTrdProductDeal().setUpdateTime(updateTime);
//                    paTrdProductDealMapper.insertSelective(baseTradeInfoDto.getPaTrdProductDeal());
//                }

//                if (!StringUtil.isEmptyList(baseTradeInfoDto.getPtlTrade())) {
//                    baseTradeInfoDto.getPtlTrade().forEach(ptlTrade -> {
                    for (PtlTrade ptlTrade : planTransferDtos.getPtlTrade()) {
                        ptlTrade.setUpdateUser(updateUser);
                        ptlTrade.setUpdateTime(updateTime);
                        ptlTradeMapper.insertSelective(ptlTrade);
                    }
//                    });
//                }
//                if (!StringUtil.isEmptyList(baseTradeInfoDto.getStlPayOrdersList())) {
////                    baseTradeInfoDto.getStlPayOrdersList().forEach(stlPayOrdersDto -> {
//                    for (StlPayOrdersDto stlPayOrdersDto : baseTradeInfoDto.getStlPayOrdersList()) {
//
//                        stlPayOrdersDto.setUpdateUser(updateUser);
//                        stlPayOrdersDto.setUpdateTime(updateTime);
//                        stlPayOrdersMapper.insertSelective(stlPayOrdersDto);
            }
////                    });
//                }
//            }
//            });
//        }
        return Boolean.TRUE.toString();
    }

}
