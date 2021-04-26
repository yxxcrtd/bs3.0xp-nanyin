/*
 * 文件名：FinProductTradeServiceImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年8月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.component.base.trade.impl;

import java.util.*;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.tams.common.base.entity.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.MstFileCommonService;
import com.joyintech.tams.invest.common.component.base.trade.BaseTradeProductDealService;
import com.joyintech.tams.invest.common.component.base.trade.impl.dao.BaseTradeProductDealMapper;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealRevokeDto;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.tams.invest.common.dto.MstFileOutputParameter;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;

/**
 * 金融产品交易
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年8月30日
 * @see BaseTradeProductDealServiceImpl
 * @since 1.0
 */
@Service
public class BaseTradeProductDealServiceImpl implements BaseTradeProductDealService {
    /**
     *
     */
    @Autowired
    private BaseTradeProductDealMapper baseTradeProductDealMapper;
    /**
     * 金融产品交易
     */
    @Autowired
    private TrdProductDealMapper trdProductDealMapper;

    /**
     * 金融产品交易附属表
     */
    @Autowired
    private TrdProductDealAddMapper trdProductDealAddMapper;

    /**
     * 划款指令表
     */
    @Autowired
    private StlPayOrdersMapper stlPayOrdersMapper;

//    /**
//     * 交易资金指令表
//     */
//    @Autowired
//    private StlOrdersMapper stlOrdersMapper;

    /**
     * 金融产品实物交易表
     */
    @Autowired
    private TrdFinProductBarterMapper trdFinProductBarterMapper;

    /**
     * 交易费用明细表
     */
    @Autowired
    private TrdTradeFeeDetailMapper trdTradeFeeDetailMapper;

    /**
     * 框架共通组件.附件组件
     */
    @Autowired(required=false)
    private MstFileCommonService mstFileCommonService;

    @Autowired
    private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;

    /**
     * 组合交易表
     */
    @Autowired
    private PtlTradeMapper ptlTradeMapper;

    /**
     * 金融产品交易新建
     */
    @Override
    public boolean insert(List<BaseTradeProductDealDto> baseTradeProductDealList) {



        for(BaseTradeProductDealDto baseTradeProductDealDto : baseTradeProductDealList){
            //金融产品汇总表
            if(baseTradeProductDealDto.getTrdFinProductDealTotal()!=null){
                trdFinProductDealTotalMapper.insert(baseTradeProductDealDto.getTrdFinProductDealTotal());
            }

            //金融产品交易
            trdProductDealMapper.insert(baseTradeProductDealDto.getTrdProductDeal());

            //金融产品交易附属表
            trdProductDealAddMapper.insert(baseTradeProductDealDto.getTrdProductDealAdd());
            //划款指令表
            if(baseTradeProductDealDto.getStlPayOrdersList() != null){
                for(StlPayOrders stlPayOrders:baseTradeProductDealDto.getStlPayOrdersList()){
                    stlPayOrdersMapper.insert(stlPayOrders);
                }
            }
//            //交易资金指令表
//            if(baseTradeProductDealDto.getStlOrdersList() != null){
//                for(StlOrders stlOrders:baseTradeProductDealDto.getStlOrdersList()){
//                    stlOrdersMapper.insert(stlOrders);
//                }
//            }

            //金融产品实物交易表
            if(baseTradeProductDealDto.getTrdFinProductBarterList() != null){
                for(TrdFinProductBarter trdFinProductBarter:baseTradeProductDealDto.getTrdFinProductBarterList()){
                    trdFinProductBarterMapper.insert(trdFinProductBarter);
                }
            }

            //交易费用明细表
            if(baseTradeProductDealDto.getTrdTradeFeeDetailList() != null){
                for(TrdTradeFeeDetail TrdTradeFeeDetail:baseTradeProductDealDto.getTrdTradeFeeDetailList()){
                    trdTradeFeeDetailMapper.insert(TrdTradeFeeDetail);
                }
            }

            //组合交易表
     /*       if(baseTradeProductDealDto.getPtlTrade() != null){
                for(PtlTrade ptlTrade:baseTradeProductDealDto.getPtlTrade()){
                    ptlTradeMapper.insert(ptlTrade);
                }
            }*/

            //框架共通组件.附件组件
            mstFileCommonService.updateBusinessId(baseTradeProductDealDto.getMstFileInputParameter());

        }

        return true;
    }


    /**
     * 金融产品交易撤销
     */
    @Override
    public boolean revoke(List<BaseTradeProductDealRevokeDto> baseTradeProductDealRevokeList) {
        //List<金融产品交易信息DTO>
        //List<BaseTradeProductDealDto> baseTradeProductDealList = new ArrayList<BaseTradeProductDealDto>();

        for(BaseTradeProductDealRevokeDto baseTradeProductDealRevokeDto:baseTradeProductDealRevokeList){
            //
            //BaseTradeProductDealRevokeDto baseTradeProductDealRevoke =new BaseTradeProductDealRevokeDto();
            //金融产品交易实体
        	//modify by wm 当组合下挂子组合时，母组合不能投资，故查不到，现去除是否可投资字段  start
//            List<BaseTradeProductDealDto> baseTradeProductDeal = baseTradeProductDealMapper.selectByTradeId(baseTradeProductDealRevokeDto.getpTradeId());
            List<BaseTradeProductDealDto> baseTradeProductDeal = baseTradeProductDealMapper.selectByTradeIdForRevoke(baseTradeProductDealRevokeDto.getpTradeId());
            //modify by wm 当组合下挂子组合时，母组合不能投资，故查不到，现去除是否可投资字段  end
            //
            MstFileOutputParameter mstFileOutputParameter = mstFileCommonService.getFileInfoByBusiId(baseTradeProductDealRevokeDto.getTrdProductDeal().getTradeId());
//            //将有效数据的是否撤销改为已撤销
//            TrdProductDeal trdProductDeals = baseTradeProductDeal.getTrdProductDeal();
//            trdProductDeals.setIsCancel(DictCodeList.是否_是.getValue());
//            trdProductDealMapper.updateByPrimaryKey(trdProductDeals);
//            //
            //将MstFileOutputParameter转成MstFileInputParameter
            if(baseTradeProductDeal != null && baseTradeProductDeal.size()>0) {
                MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
                mstFileInputParameter.setFileList(mstFileOutputParameter.getUploadFileList());
                mstFileInputParameter.setBussinessId(baseTradeProductDeal.get(0).getTrdProductDeal().getTradeId());
                baseTradeProductDealRevokeDto.setMstFileInputParameter(mstFileInputParameter);

                //金融产品交易撤销信息DTO.金融产品交易Entity.交易编号       =  金融产品交易信息DTO.金融产品交易Entity.交易编号+"R"
                //金融产品交易撤销信息DTO.金融产品交易Entity.交易状态       = 数据字典缓存.交易状态.撤销
                //金融产品交易撤销信息DTO.金融产品交易Entity.原交易编号     = 金融产品交易信息DTO.金融产品交易Entity.交易编号
                //金融产品交易撤销信息DTO.金融产品交易Entity.撤销说明       = 输入参数撤销交易DTO.撤销理由
                baseTradeProductDeal.get(0).getTrdProductDeal().setpTradeId(baseTradeProductDeal.get(0).getTrdProductDeal().getTradeId());
                baseTradeProductDeal.get(0).getTrdProductDeal().setTradeId(baseTradeProductDeal.get(0).getTrdProductDeal().getTradeId() + "R");
                if(baseTradeProductDeal.get(0).getTrdProductDeal().getTotleTradeId()!=null && StringUtils.isNotEmpty(baseTradeProductDeal.get(0).getTrdProductDeal().getTotleTradeId())){
                baseTradeProductDeal.get(0).getTrdProductDeal().setTotleTradeId(baseTradeProductDeal.get(0).getTrdProductDeal().getTotleTradeId() +
                        "R");}
                //金融产品交易撤销信息DTO.金融产品交易Entity.撤销创建、更新时间 add by lishuping 20190902 start
                baseTradeProductDeal.get(0).getTrdProductDeal().setCreateTime(new Date());
                baseTradeProductDeal.get(0).getTrdProductDeal().setUpdateTime(new Date());
                //金融产品交易撤销信息DTO.金融产品交易Entity.撤销创建、更新时间 add by lishuping 20190902 end
                baseTradeProductDeal.get(0).getTrdProductDeal().setTradeStatus(DictCodeListExt1.交易状态_撤销.getValue());
                baseTradeProductDeal.get(0).getTrdProductDeal().setCancelRemark(baseTradeProductDealRevokeDto.getCancelRemark());
                //金融产品交易
                baseTradeProductDealRevokeDto.setTrdProductDeal(baseTradeProductDeal.get(0).getTrdProductDeal());

                //金融产品交易撤销信息DTO.金融产品交易附属表Entity.交易编号    =金融产品交易撤销信息DTO.金融产品交易Entity.交易编号
                //金融产品交易附属表
                baseTradeProductDealRevokeDto.setTrdProductDealAdd(baseTradeProductDeal.get(0).getTrdProductDealAdd());
                baseTradeProductDealRevokeDto.getTrdProductDealAdd().setTradeId(baseTradeProductDealRevokeDto.getTrdProductDeal().getTradeId());

                //金融产品交易撤销信息DTO.划款指令表Entity.指令编号    =金融产品交易信息DTO.划款指令表Entity.指令编号+"R"
                //金融产品交易撤销信息DTO.划款指令表Entity.交易编号   =金融产品交易撤销信息DTO.金融产品交易Entity.交易编号
                // 、金融产品交易撤销信息DTO.划款指令表Entity.指令状态  =数据字典缓存.指令状态.撤销
                //金融产品交易撤销信息DTO.划款指令表Entity.原指令编号   =金融产品交易信息DTO.划款指令表Entity.指令编号
                for (StlPayOrders stlPayOrders : baseTradeProductDeal.get(0).getStlPayOrdersList()) {
                    stlPayOrders.setOrderId(stlPayOrders.getOrderId() + "R");
                    stlPayOrders.setpTradeId(stlPayOrders.getTradeId());
                    String tradeId = baseTradeProductDealRevokeDto.getTrdProductDeal().getTradeId();
                    if (tradeId.lastIndexOf('R') == tradeId.length() - 1) {
                        stlPayOrders.setTradeId(tradeId);
                    } else {
                        stlPayOrders.setTradeId(tradeId + "R");
                    }

                    stlPayOrders.setOrderStatus(DictCodeListExt1.指令状态_撤销.getValue());
                }
                //划款指令表
                baseTradeProductDealRevokeDto.setStlPayOrdersList(baseTradeProductDeal.get(0).getStlPayOrdersList());

//            //金融产品交易撤销信息DTO.交易资金指令表Entity.指令编号   =金融产品交易信息DTO.交易资金指令表Entity.指令编号+"R"
//            //金融产品交易撤销信息DTO.交易资金指令表Entity.交易编号  =金融产品交易撤销信息DTO.金融产品交易Entity.交易编号
//            //金融产品交易撤销信息DTO.交易资金指令表Entity.指令状态  =数据字典缓存.指令状态.撤销
//            //金融产品交易撤销信息DTO.交易资金指令表Entity.原指令编号 =  金融产品交易信息DTO.交易资金指令表Entity.指令编号
//            for(StlOrders stlOrders:baseTradeProductDeal.get(0).getStlOrdersList()){
//                stlOrders.setOrderId(stlOrders.getOrderId()+"R");
//                stlOrders.setpOrderId(stlOrders.getTradeId());
//                stlOrders.setTradeId(baseTradeProductDealRevokeDto.getTrdProductDeal().getTradeId());
//                stlOrders.setStatus(DictCodeListExt1.指令状态_撤销.getValue());
//            }
//            //交易资金指令表
//            baseTradeProductDealRevokeDto.setStlOrdersList(baseTradeProductDeal.get(0).getStlOrdersList());

                //金融产品交易撤销信息DTO.金融产品实物交易表Entity.交易编号  =金融产品交易撤销信息DTO.金融产品交易Entity.交易编号
                for (TrdFinProductBarter trdFinProductBarter : baseTradeProductDeal.get(0).getTrdFinProductBarterList()) {
                    trdFinProductBarter.setTradeId(baseTradeProductDealRevokeDto.getTrdProductDeal().getTradeId());
                }
                //金融产品实物交易表
                baseTradeProductDealRevokeDto.setTrdFinProductBarterList(baseTradeProductDeal.get(0).getTrdFinProductBarterList());

                //金融产品交易撤销信息DTO.交易费用明细表Entity.交易编号   =金融产品交易撤销信息DTO.金融产品交易Entity.交易编号
                for (TrdTradeFeeDetail trdTradeFeeDetail : baseTradeProductDeal.get(0).getTrdTradeFeeDetailList()) {
                    trdTradeFeeDetail.setTradeId(baseTradeProductDealRevokeDto.getTrdProductDeal().getTradeId());
                }
                //交易费用明细表
                baseTradeProductDealRevokeDto.setTrdTradeFeeDetailList(baseTradeProductDeal.get(0).getTrdTradeFeeDetailList());

                // 一体化对应etliu start
                // 所有交易生效后调用的共通方法
                AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
                inputDto.setProdid(baseTradeProductDeal.get(0).getTrdProductDealAdd().getPortfolioId());
                inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
                inputDto.setTrdProductDeal(baseTradeProductDeal.get(0).getTrdProductDeal());
                // 通道相关接口增加 qiangchengxi start
                inputDto.setTrdProductDealAdd(baseTradeProductDeal.get(0).getTrdProductDealAdd());
                // 通道相关接口增加 qiangchengxi end
                inputDto.setActPortfolioId(baseTradeProductDealRevokeDto.getActPortfolioId());
                inputDto.setPtlTradeList(baseTradeProductDeal.get(0).getPtlTrade());
                if (baseTradeProductDealRevokeDto.getStlPayOrdersList() != null && !baseTradeProductDealRevokeDto.getStlPayOrdersList().isEmpty()) {
                    inputDto.setStlPayOrders(baseTradeProductDealRevokeDto.getStlPayOrdersList().get(0));
                }
                AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
                // 一体化对应etliu end
            }
        }
        //金融产品交易撤销信息-金融产品交易集合
        List<TrdProductDeal> trdProductDealList = new ArrayList<>();
        for(BaseTradeProductDealRevokeDto baseTradeProductDealRevokeDto : baseTradeProductDealRevokeList){
            //金融产品交易插入
            trdProductDealMapper.insert(baseTradeProductDealRevokeDto.getTrdProductDeal());

            //金融产品交易附属表插入
            trdProductDealAddMapper.insert(baseTradeProductDealRevokeDto.getTrdProductDealAdd());

            //划款指令表插入
            if(baseTradeProductDealRevokeDto.getStlPayOrdersList() != null){
                for(StlPayOrders stlPayOrders:baseTradeProductDealRevokeDto.getStlPayOrdersList()){
                    stlPayOrdersMapper.insert(stlPayOrders);
                }
            }

//            //交易资金指令表插入
//            for(StlOrders stlOrders:baseTradeProductDealRevokeDto.getStlOrdersList()){
//                stlOrdersMapper.insert(stlOrders);
//            }

            //金融产品实物交易表插入
            for(TrdFinProductBarter trdFinProductBarter:baseTradeProductDealRevokeDto.getTrdFinProductBarterList()){
                trdFinProductBarterMapper.insert(trdFinProductBarter);
            }

            //交易费用明细表插入
            for(TrdTradeFeeDetail TrdTradeFeeDetail:baseTradeProductDealRevokeDto.getTrdTradeFeeDetailList()){
                trdTradeFeeDetailMapper.insert(TrdTradeFeeDetail);
            }

            //框架共通组件.附件组件
            mstFileCommonService.updateBusinessId(baseTradeProductDealRevokeDto.getMstFileInputParameter());

            //金融产品交易实体放入集合
            baseTradeProductDealRevokeDto.getTrdProductDeal().setTradeId(baseTradeProductDealRevokeDto.getTrdProductDeal().getpTradeId());
//            baseTradeProductDealRevokeDto.getTrdProductDeal().setpTradeId("");
            trdProductDealList.add(baseTradeProductDealRevokeDto.getTrdProductDeal());
        }
        //更新金融产品交易信息，是否已撤销 = 数据字典缓存.是否.是
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("isCancle", DictCodeList.是否_是.getValue());
        map.put("trdProductDealList", trdProductDealList);
        baseTradeProductDealMapper.updateTrdProDeal(map);

        return true;
    }


    /**
     * 金融产品交易查询
     */
    @Override
    public List<BaseTradeProductDealDto> select(List<String> tradeIdList) {
        List<BaseTradeProductDealDto> baseTradeProductDealList = new ArrayList<BaseTradeProductDealDto>();
        for(String tradeId:tradeIdList){
            List<BaseTradeProductDealDto> baseTradeProductDealDto =new ArrayList<>();
            baseTradeProductDealDto = baseTradeProductDealMapper.selectByTradeId(tradeId);
            // 针对回购，需要但素设置assetType2
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("tradeId", tradeId);
            List<String> repoAssetType2List = baseTradeProductDealMapper.selectRepoAssetType2(map);
            if (repoAssetType2List != null && repoAssetType2List.size() > 0) {
            	for(BaseTradeProductDealDto entity : baseTradeProductDealDto){
                    entity.setAssetType2(repoAssetType2List.get(0));
                }
            }
            MstFileOutputParameter mstFileOutputParameter = mstFileCommonService.getFileInfoByBusiId(tradeId);
            for(BaseTradeProductDealDto entity : baseTradeProductDealDto){
                entity.setMstFileOutputParameter(mstFileOutputParameter);
            }
            baseTradeProductDealList.addAll(baseTradeProductDealDto);
        }

        return baseTradeProductDealList;
    }


    /**
     * 金融产品汇总交易查询
     */
    @Override
    public List<BaseTradeProductDealDto> selectTotalDeal(List<String> tradeIdList) {
        List<BaseTradeProductDealDto> baseTradeProductDealList = new ArrayList<BaseTradeProductDealDto>();
        for(String tradeId:tradeIdList){
            List<BaseTradeProductDealDto> baseTradeProductDealDto =new ArrayList<>();
            baseTradeProductDealDto = baseTradeProductDealMapper.selectByTotalTradeId(tradeId);
            MstFileOutputParameter mstFileOutputParameter = mstFileCommonService.getFileInfoByBusiId(tradeId);
            for(BaseTradeProductDealDto entity : baseTradeProductDealDto){
                entity.setMstFileOutputParameter(mstFileOutputParameter);
            }
            baseTradeProductDealList.addAll(baseTradeProductDealDto);
        }

        return baseTradeProductDealList;
    }


    /**
     * 金融产品汇总交易查询
     */
    @Override
    public List<TrdProductDeal> selectTrdProductDealsByTotalTradeId(String totalTradeId) {
        List<TrdProductDeal> trdProductDealList = new ArrayList<TrdProductDeal>();
        trdProductDealList = baseTradeProductDealMapper.selectTrdProductDealsByTotalTradeId(totalTradeId);
        return trdProductDealList;
    }
    /**
     * 金融产品汇总交易查询
     */
    @Override
    public List<TrdProductDeal> selectTrdProductChlDealsByTotalTradeId(String totalTradeId) {
        List<TrdProductDeal> trdProductDealList = new ArrayList<TrdProductDeal>();
        trdProductDealList = baseTradeProductDealMapper.selectTrdProductChlDealsByTotalTradeId(totalTradeId);
        return trdProductDealList;
    }

}
