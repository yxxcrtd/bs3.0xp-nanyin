/*
 * 文件名：TrdPlanNetInvestTrade001Service.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：xiexinting
 * 修改时间：2018年12月2日 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.primarybiddingmanage.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.trade.primarybiddingmanage.dto.BidOrderBidPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.BidOrderBidPriceMapper;
import com.joyintech.tams.common.base.dao.BidOrderMapper;
import com.joyintech.tams.common.base.dao.EtrEntrustMapper;
import com.joyintech.tams.common.base.entity.BidOrder;
import com.joyintech.tams.common.base.entity.BidOrderBidPrice;
import com.joyintech.tams.common.base.entity.BidOrderBidPriceKey;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;
import com.joyintech.tams.invest.trade.primarybiddingmanage.dto.PrimaryBiddingManageDto;
import com.joyintech.tams.invest.trade.primarybiddingmanage.dto.PrimaryBiddingPortfolioDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author sjj
 * @version 1.0 2019年05月08日
 * @see PrimaryBiddingManage002Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PrimaryBiddingManage002Service extends BaseService<PrimaryBiddingManageDto> implements BaseProcessService<PrimaryBiddingManageDto> {

    @Autowired
    private BidOrderMapper bidOrderMapper;

    @Autowired
    private BidOrderBidPriceMapper bidOrderBidPriceMapper;
    @Autowired
    private EtrEntrustMapper etrEntrustMapper;

    @Autowired
    private BaseEtrCashPositionCommService baseEtrCashPositionCommService;


    /**
     * 根据画面参数获取entity <功能详细描述>
     * 
     * @param map 参数
     * @return 返回结果值
     * @see
     * @since 1.0
     */
    public PrimaryBiddingManageDto getMyEntity(Map<String, Object> map) {
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/PrimaryBiddingManage002/getEntityByMap", map,
            PrimaryBiddingManageDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void doComplete(PrimaryBiddingManageDto entity, OperationType type) {
        // 汇总确认流程结束调用
        if (!type.equals(OperationType.REVOKE) && !type.equals(OperationType.DELETE)) {
            // 金融产品交易信息DTO.金融产品交易Entity -> 投标指令表
            BidOrder bidOrder = new BidOrder();
            bidOrder.setBidDate(entity.getBiddate());
            BeanUtil.copyPropertiesIgnoreNull(bidOrder, entity);
            if (null != bidOrder.getWinPrice()) {
                bidOrder.setWinPrice(bidOrder.getWinPrice().divide(BigDecimal.valueOf(100)));
            }

            // bussCommonMapperDelegateService.insertBatch(BidOrderMapper.class, bidOrder);
            bidOrderMapper.insertSelective(bidOrder);

            // 金融产品交易信息DTO.金融产品交易Entity -> 投标指令表
            if (!StringUtil.isEmptyList(entity.getPrimaryBiddingPortfolioDtolist())) {
                for (PrimaryBiddingPortfolioDto primaryBiddingPortfolioDto : entity.getPrimaryBiddingPortfolioDtolist()) {

                    //更新交易对手
                    EtrEntrust etrEntrust = new EtrEntrust();
                    etrEntrust.setEntrustId(primaryBiddingPortfolioDto.getEntrustId());
                    etrEntrust.setCounterTrader(entity.getTrdInvestPersonInfo002Dto().getCounterTrader());
                    etrEntrust.setContactInfo(entity.getTrdInvestPersonInfo002Dto().getContactInfo());
                    etrEntrustMapper.updateByPrimaryKeySelective(etrEntrust);

                	BidOrderBidPrice bidOrderBidPrice = new BidOrderBidPrice();
                    bidOrderBidPrice.setBidId(entity.getBidId());
                    BeanUtil.copyPropertiesIgnoreNull(bidOrderBidPrice, primaryBiddingPortfolioDto);
                    bidOrderBidPrice.setCreateUser(bidOrder.getCreateUser());
                    bidOrderBidPrice.setUpdateUser(bidOrder.getUpdateUser());
                    bidOrderBidPrice.setCreateDept(bidOrder.getCreateDept());
                    bidOrderBidPrice.setCreateTime(bidOrder.getCreateTime());
                    bidOrderBidPrice.setUpdateTime(bidOrder.getUpdateTime());
                    if (null != bidOrderBidPrice.getWinPrice()) {
                        bidOrderBidPrice.setWinPrice(bidOrderBidPrice.getWinPrice().divide(BigDecimal.valueOf(100)));
                    }
                    if (null != bidOrderBidPrice.getBidPrice()) {
                        bidOrderBidPrice.setBidPrice(bidOrderBidPrice.getBidPrice().divide(BigDecimal.valueOf(100)));
                    }

                    // 批量插入
                    // bussCommonMapperDelegateService.insertBatch(BidOrderBidPriceMapper.class,
                    // bidOrderBidPrice);
                    bidOrderBidPriceMapper.insertSelective(bidOrderBidPrice);
                }
            }
        }// 撤销
        else {
            // 根据投标编号查询指令确认状态
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("bidId", entity.getBidId());
            PrimaryBiddingManageDto primaryBiddingManageOriginalDto = restTemplate.postForObject(
                ServiceApplication.TRADE_SERVICE_NAME + "/PrimaryBiddingManage002/getPrimaryBiddingManageOriginal", map,
                PrimaryBiddingManageDto.class);
            // 投标指令
            BidOrder bidOrder = new BidOrder();
            bidOrder.setBidId(entity.getBidId());
            // 已确认状态修改成未确认，删除投标指令、投标指令标位表数据
            if (StringUtil.equals(primaryBiddingManageOriginalDto.getTenderconfirmstatus(),
                BussinessDictCodeList.确认状态_已确认.getValue())) {
                // bussCommonMapperDelegateService.deleteByPrimaryKey(BidOrderMapper.class, bidOrder.getBidId());
                bidOrderMapper.deleteByPrimaryKey(bidOrder.getBidId());
                if (!StringUtil.isEmptyList(entity.getPrimaryBiddingPortfolioDtolist())) {
                    // 投标指令标位表
                    for (PrimaryBiddingPortfolioDto primaryBiddingPortfolioDto : entity.getPrimaryBiddingPortfolioDtolist()) {
                        BidOrderBidPriceKey bidOrderBidPriceKey = new BidOrderBidPriceKey();
                        bidOrderBidPriceKey.setBidId(entity.getBidId());
                        bidOrderBidPriceKey.setEntrustId(primaryBiddingPortfolioDto.getEntrustId());
                        // UPDATE BY LEIPENG 债券一级投标已确认状态进行撤销流程，变为未确认状态，再进行汇总确认，会报错
                        if (null != primaryBiddingPortfolioDto.getBidPrice()
                            && primaryBiddingPortfolioDto.getBidPrice().compareTo(BigDecimal.ZERO) != 0) {
                            bidOrderBidPriceKey.setBidPrice(
                                primaryBiddingPortfolioDto.getBidPrice().divide(BigDecimal.valueOf(100)));
                        }
                        BidOrderBidPrice bidOrderBidPrice = bidOrderBidPriceMapper.selectByPrimaryKey(bidOrderBidPriceKey);
                        BigDecimal 投标券面总额 = bidOrderBidPrice.getTheWinPrinAmt();
                        BigDecimal 实际中标券面总额 = bidOrderBidPrice.getWinPrinAmt();
                        if(投标券面总额!=null && 实际中标券面总额 != null){
                            baseEtrCashPositionCommService.executeBidResultsMaintenanceExecute(bidOrderBidPrice);
                        }
                        bidOrderBidPriceMapper.deleteByPrimaryKey(bidOrderBidPriceKey);
                    }
                }
            }
            // 其他状态
            else {
                // 确认状态
                String tenderConfirmStatus = "";
                if (StringUtil.equals(primaryBiddingManageOriginalDto.getTenderconfirmstatus(),
                    BussinessDictCodeList.确认状态_已中标.getValue())) {
                    tenderConfirmStatus = null;
                }
                bidOrder.setIsWin(tenderConfirmStatus);
                bidOrder.setFinprodId(entity.getFinprodId());
                bidOrder.setBidDate(entity.getBiddate());
                // 更新投标指令表
                // bussCommonMapperDelegateService.updateByPrimaryKey(BidOrderMapper.class, bidOrder);
                bidOrderMapper.updateByPrimaryKey(bidOrder);
                // 更新投标指令标位表
                if (!StringUtil.isEmptyList(entity.getPrimaryBiddingPortfolioDtolist())) {
                    // 投标指令标位表
                    for (PrimaryBiddingPortfolioDto primaryBiddingPortfolioDto : entity.getPrimaryBiddingPortfolioDtolist()) {
                        BidOrderBidPrice bidOrderBidPrice = new BidOrderBidPrice();
                        bidOrderBidPrice.setBidId(entity.getBidId());
                        bidOrderBidPrice.setEntrustId(primaryBiddingPortfolioDto.getEntrustId());
                        // UPDATE BY LEIPENG 债券一级投标已确认状态进行撤销流程，变为未确认状态，再进行汇总确认，会报错
                        if (null != primaryBiddingPortfolioDto.getBidPrice()
                            && primaryBiddingPortfolioDto.getBidPrice().compareTo(BigDecimal.ZERO) != 0) {
                            bidOrderBidPrice.setBidPrice(
                                primaryBiddingPortfolioDto.getBidPrice().divide(BigDecimal.valueOf(100)));
                        }
                        bidOrderBidPrice.setIsWin(tenderConfirmStatus);
                        // bussCommonMapperDelegateService.updateByPrimaryKey(BidOrderBidPriceMapper.class, bidOrderBidPrice);
                        bidOrderBidPriceMapper.updateByPrimaryKey(bidOrderBidPrice);
                    }
                }
            }
        }
    }

    // @Override
    // public void doInsert(PrimaryBiddingManageDto entity, OperationType operationType) {
    // // 新建交易
    // if (!operationType.equals(OperationType.REVOKE) && !operationType.equals(OperationType.DELETE)) {
    // //插入投标指令标位表
    // this.handleBussFlowData(entity);
    // }
    // // 撤销交易
    // else {
    // this.doRevokeInsert(entity);
    // }
    // }

    /**
     * 提交时登录投标指令标位表
     *
     * @param entity 画面参数dto
     * @see
     * @since 1.0
     */
    // private void handleBussFlowData(PrimaryBiddingManageDto entity) {
    //
    // if (!StringUtil.equals("submit", entity.getOperateType())) {
    // return;
    // }
    // // 金融产品交易信息DTO.金融产品交易Entity -> 投标指令表
    // BidOrder bidOrder = new BidOrder();
    // BeanUtil.copyPropertiesIgnoreNull(bidOrder, entity);
    //
    // // 金融产品交易信息DTO.金融产品交易Entity -> 投标指令表
    // BidOrderBidPrice bidOrderBidPrice =new BidOrderBidPrice();
    // BeanUtil.copyPropertiesIgnoreNull(bidOrderBidPrice, entity);
    // // 批量插入
    // bussCommonMapperDelegateService.insertBatch(BidOrderMapper.class, bidOrder,
    // BidOrderBidPriceMapper.class, bidOrderBidPrice);
    // }

    /**
     * 获取交易对手名称
     * @param counterId
     * @return
     */
    public String getCounterName(String counterId){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("counterId", counterId);
        return restTemplate.postForObject(
                ServiceApplication.TRADE_SERVICE_NAME + "/PrimaryBiddingManage002/getCounterName",
                map, String.class);
    }

    /**
     * 调整投标券面总额
     * @param dto
     * @return
     */
    public PrimaryBiddingManageDto adjustAmtBid(PrimaryBiddingManageDto dto){
        Map<String, Object> map = new HashMap<>();
        map.put("allBidPrinAmt",dto.getAllBidPrinAmt());
        String entrustId = "";
        if(StringUtil.isNotEmpty(dto.getEntrustId())){
            entrustId = dto.getEntrustId();
        }
        else{
//            for (PrimaryBiddingPortfolioDto primaryBiddingPortfolioDto : dto.getPrimaryBiddingPortfolioDtolist()) {
//                entrustId += primaryBiddingPortfolioDto.getEntrustId() + ",";
//            }
            entrustId = StringUtil.joinString(dto.getPrimaryBiddingPortfolioDtolist().stream().map(PrimaryBiddingPortfolioDto::getEntrustId).collect(Collectors.toList()), GlobalConstant.COMMA);
        }
        map.put("entrustId",entrustId);
        map.put("finprodId",dto.getFinprodId());
        map.put("counterId",dto.getTrdInvestPersonInfo002Dto().getCounterId());


        PrimaryBiddingManageDto myEntity = this.getMyEntity(map);

        //投标组合列表 进行排序
        List<PrimaryBiddingPortfolioDto> primaryBiddingPortfolioDtos = myEntity.getPrimaryBiddingPortfolioDtolist();
        //调用调整券面总额方法
        List<PrimaryBiddingPortfolioDto> primaryBiddingPortfolioDtoList = getPrimaryBiddingPortfolioDtos(dto, primaryBiddingPortfolioDtos);

        PrimaryBiddingManageDto primaryBiddingManageDto = new PrimaryBiddingManageDto();
        primaryBiddingManageDto.setPrimaryBiddingPortfolioDtolist(primaryBiddingPortfolioDtoList);
        //封装汇总投标列表
        List<PrimaryBiddingPortfolioDto> primaryBiddingAllDtolist = new ArrayList<>();
        Map<BigDecimal, List<PrimaryBiddingPortfolioDto>> collect = primaryBiddingPortfolioDtoList.stream().collect(Collectors.groupingBy(PrimaryBiddingPortfolioDto::getBidPrice));
        for (BigDecimal bigDecimal : collect.keySet()){
            List<PrimaryBiddingPortfolioDto> value = collect.get(bigDecimal);
            BigDecimal total = BigDecimal.ZERO;
            PrimaryBiddingPortfolioDto allDtoList = new PrimaryBiddingPortfolioDto();
            for (PrimaryBiddingPortfolioDto primaryBiddingPortfolioDto : value) {
                total = total.add(primaryBiddingPortfolioDto.getBidPrinAmt());
            }
            allDtoList.setHzallBidPrinAmt(total);
            allDtoList.setBidPrice(bigDecimal);
            primaryBiddingAllDtolist.add(allDtoList);
        }
        primaryBiddingManageDto.setPrimaryBiddingAllDtolist(primaryBiddingAllDtolist);
        return primaryBiddingManageDto;
    }

    /**
     * 调整券面总额方法
     * @param dto
     * @param primaryBiddingPortfolioDtos
     * @return
     */
    private List<PrimaryBiddingPortfolioDto> getPrimaryBiddingPortfolioDtos(PrimaryBiddingManageDto dto, List<PrimaryBiddingPortfolioDto> primaryBiddingPortfolioDtos) {
        Collections.sort(primaryBiddingPortfolioDtos, new Comparator<PrimaryBiddingPortfolioDto>() {
            @Override
            public int compare(PrimaryBiddingPortfolioDto o1, PrimaryBiddingPortfolioDto o2) {
            	 int i = o1.getBidPrice().compareTo(o2.getBidPrice());
                 if(i == 0){
                    return o1.getBidPrinAmt().compareTo(o2.getBidPrinAmt());
                }
                return i;
            }
        });
        BigDecimal subtract = dto.getAllBidPrinAmt().subtract(dto.getBidAdjustAmt());
        List<PrimaryBiddingPortfolioDto> primaryBiddingPortfolioDtoList = new ArrayList<>();
        BigDecimal allBidPrinAmt = dto.getAllBidPrinAmt();
        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < primaryBiddingPortfolioDtos.size(); i++) {
            //若选择按比例分配，将投标量调整额与汇总投标券面总额的差值按比例针对每条数据进行同比例削减
            BigDecimal bidPriceAmt = primaryBiddingPortfolioDtos.get(i).getBidPrinAmt();
            if (StringUtil.equals(dto.getAdjustStrategy(), DictCodeListExt3.调整策略_按比例分配.getValue())){
//                BigDecimal divide = bidPriceAmt.divide(allBidPrinAmt,2);
                //算出所占比例
                BigDecimal v = ArithmeticUtil.multiply(ArithmeticUtil.divide(bidPriceAmt, allBidPrinAmt, 8), subtract);
                //调整最终值 = 投标券面总额 - （所占比例 * 差值）
                BigDecimal resultAmt = bidPriceAmt.subtract(ArithmeticUtil.round(v, 2, RoundingMode.HALF_UP));
                if (i == primaryBiddingPortfolioDtos.size() - 1) {// 最后一条倒减
                    primaryBiddingPortfolioDtos.get(i).setBidPrinAmt(ArithmeticUtil.subtract(dto.getBidAdjustAmt(), total));
                } else {
                    primaryBiddingPortfolioDtos.get(i).setBidPrinAmt(resultAmt);
                }
                total = ArithmeticUtil.doAdd(total, resultAmt);
                primaryBiddingPortfolioDtoList.add(primaryBiddingPortfolioDtos.get(i));
            }else {
                //若选择价格优先分配，依次从上往下保留每条数据，直到列表中投标券面等于投标量调整额为止，将多余的信息删除掉；
                // 若取至某条数据导致总和大于投标量调整额，则将该数据的投标券面削减至和值等于投标量调整额；
                if (dto.getBidAdjustAmt().compareTo(ArithmeticUtil.doAdd(total,bidPriceAmt)) >=0){
                    primaryBiddingPortfolioDtos.get(i).setBidPrinAmt(bidPriceAmt);
                    total = ArithmeticUtil.doAdd(total,bidPriceAmt);
                    primaryBiddingPortfolioDtoList.add(primaryBiddingPortfolioDtos.get(i));
                }
                else if(BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(dto.getBidAdjustAmt(),total))!=0) {
                    primaryBiddingPortfolioDtos.get(i).setBidPrinAmt(ArithmeticUtil.subtract(dto.getBidAdjustAmt(),total));
                    total = dto.getBidAdjustAmt();
                    primaryBiddingPortfolioDtoList.add(primaryBiddingPortfolioDtos.get(i));
                }
            }
        }
        return primaryBiddingPortfolioDtoList;
    }
}
