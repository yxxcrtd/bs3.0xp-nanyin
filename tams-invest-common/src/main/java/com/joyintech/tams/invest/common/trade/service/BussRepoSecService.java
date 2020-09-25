/*
 * 文件名：TrdRepoTradeService003.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年12月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.common.trade.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.FinTradeProductAddMapper;
import com.joyintech.tams.common.base.entity.FinTradeProductAdd;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.dto.common.BussRepoSecDto;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import com.joyintech.tams.invest.common.service.MapperDelegateService;
import com.joyintech.tams.invest.common.trade.dao.BussRepoSecMapper;
import com.joyintech.tams.invest.common.trade.dto.PledgeInsertDto;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 质押劵管理共通组件-微服务Service
 */
@Service
public class BussRepoSecService {

    @Autowired
    private IPlatformDate platformDateImpl;

    /**
     * TrdRepoTradeMapper
     */
    @Autowired
    private BussRepoSecMapper bussRepoSecMapper;

    /**
     * MapperDelegateService
     */
    @Autowired
    private MapperDelegateService mapperDelegateService;
    @Autowired
    private FinTradeProductAddMapper finTradeProductAddMapper;

    /**
     * 债券代码：下拉框
     *
     * @param param 画面参数
     * @return 债券代码：下拉框
     */
    public List<SelectOptionDto> getBondCode(Map<String, Object> param) {
        param.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        param.put("windtypeone", StringUtil.splitToList(GlobalConstant.COMMA, (String) param.get("bussRepoSec.bondType")));
        return bussRepoSecMapper.getBondCode(param);
    }

    /**
     * 债券质押回购多组合新增
     * 张庚林
     * 20190723
     */   
    public PledgeBondInfoDto portfolioName(Map<String, Object> param) {
        return bussRepoSecMapper.portfolioName(param);
    }

    /**
     * 通过entrustId/bizCode检索已质押券信息
     *
     * @param params 画面参数
     * @return List<BussRepoSecDto>
     */
    public BussRepoSecDto selectBussRepoSec(Map<String, Object> params) {
        BussRepoSecDto bussRepoSecDto = new BussRepoSecDto();
        String ps = (String) params.get("ps");
        String entrustId = (String) params.get("entrustId");
        String bussBondType =(String) params.get("bussBondType");
        List<String> entrustIdList = StringUtil.splitToList(GlobalConstant.COMMA, entrustId);
        List<PledgeBondInfoDto> pledgeList = new ArrayList<>();
        new ArrayList<>();
        if (DictCodeList.回购拆借方向_正回购.getValue().equals(ps)||DictCodeList.回购拆借方向_融入.getValue().equals(ps)) {
            params.put("POSITION_TYPE_01", DictCodeList.持仓类型_可用.getValue());
            params.put("POSITION_TYPE_03", DictCodeList.持仓类型_实际.getValue());
            params.put("POSITION_TYPE_04", DictCodeList.持仓类型_质押.getValue());
            params.put("POSITION_TYPE_05", DictCodeList.持仓类型_融入.getValue());
            params.put("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
            params.put("YES_OR_NO_N", DictCodeList.是否_否.getValue());
            params.put("plateDate", platformDateImpl.getSysDateStr());
            if(entrustIdList.size()>1){
                params.put("entrustIdList", entrustIdList);
                pledgeList = bussRepoSecMapper.selectBussRepoSecPtlSellRepo(params);
            }else{
                pledgeList = bussRepoSecMapper.selectBussRepoSecSellRepo(params);
            }
        } else if (DictCodeList.回购拆借方向_逆回购.getValue().equals(ps)||DictCodeList.回购拆借方向_融出.getValue().equals(ps)) {
            params.put("plateDate", platformDateImpl.getSysDateStr());
            params.put("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
            params.put("YES_OR_NO_N", DictCodeList.是否_否.getValue());
            params.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
            if(entrustIdList.size()>1){
                params.put("entrustIdList", entrustIdList);
                pledgeList = bussRepoSecMapper.selectBussRepoSecPtlAntiRepo(params);
            }else{
                pledgeList = bussRepoSecMapper.selectBussRepoSecAntiRepo(params);
            }
        }
        BigDecimal totalPrinAmt = BigDecimal.ZERO;
        for (PledgeBondInfoDto pbi : pledgeList) {
            totalPrinAmt = ArithmeticUtil.doAdd(totalPrinAmt, ArithmeticUtil.multiply(pbi.getPledgePrin(), pbi.getPledgeRatio()).divide(new BigDecimal("100")));
        }
        bussRepoSecDto.setPledgeBondList(pledgeList);
        bussRepoSecDto.setTotalPrinAmt(totalPrinAmt);
        //债券借贷交易设置质押劵置换安排字段信息
        if(StringUtils.isNotBlank(bussBondType)){
            FinTradeProductAdd finTradeProductAdd = new FinTradeProductAdd();
            finTradeProductAdd.setFinprodId((String) params.get("bizCode"));
            finTradeProductAdd.setBranch(InterfCommonConstant.分支序号_默认);
            finTradeProductAdd = finTradeProductAddMapper.selectByPrimaryKey(finTradeProductAdd);
            if(finTradeProductAdd!=null){
            bussRepoSecDto.setExchangePledge(finTradeProductAdd.getExchangePledge());
            }
        }
        return bussRepoSecDto;
    }

    /**
     * 根据画面条件检索可质押券信息
     *
     * @param params 画面参数
     * @return List<PledgeBondInfoDto>
     */
    public List<PledgeBondInfoDto> selectAvaPledgeBondDetailList(Map<String, Object> params) {
        String ps = (String) params.get("ps");
        String bondType = (String) params.get("bondType");
        String tradeMarket = (String) params.get("tradeMarket");
        if(StringUtil.isNotEmpty(bondType)){
            params.put("bondTypeArr", StringUtil.split(bondType,","));
        }
        List<PledgeBondInfoDto> resultList = new ArrayList<>();
        if (DictCodeList.回购拆借方向_正回购.getValue().equals(ps)||DictCodeList.回购拆借方向_融入.getValue().equals(ps)) {
            params.put("POSITION_TYPE_01", DictCodeList.持仓类型_可用.getValue());
            params.put("POSITION_TYPE_03", DictCodeList.持仓类型_实际.getValue());
            params.put("POSITION_TYPE_04", DictCodeList.持仓类型_质押.getValue());
            params.put("POSITION_TYPE_05", DictCodeList.持仓类型_融入.getValue());
            params.put("POSITION_TYPE_06", DictCodeList.持仓类型_融出.getValue());
            params.put("tradeMarket",tradeMarket);
            params.put("plateDate", platformDateImpl.getSysDateStr());
            params.put("repRegistOrg", StringUtil.splitToList(GlobalConstant.COMMA, (String) params.get("repRegistOrg")));
            params.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
            resultList = bussRepoSecMapper.selectAvaPledgeBondDetailListSellRepo(params);
            if (params.get("isWithInvAim") != null && (Boolean) params.get("isWithInvAim") && resultList != null && !resultList.isEmpty()) {
                // 用融入的持仓（投资目的999999）减去卖空和卖空可用的持仓，并且最后结果没有卖空的数据
                List<PledgeBondInfoDto> resultList1 = new ArrayList<>();
                PledgeBondInfoDto 融入券 = null;
                PledgeBondInfoDto 卖空 = null;
                for (PledgeBondInfoDto pledgeBondInfoDto : resultList) {
                    if ("999999".equals(pledgeBondInfoDto.getInvAim())) {
                        融入券 = pledgeBondInfoDto;
                    }
                    if (DictCodeListExt1.投资目的_卖空.getValue().equals(pledgeBondInfoDto.getInvAim())) {
                        卖空 = pledgeBondInfoDto;
                    }

                    if (!DictCodeListExt1.投资目的_卖空.getValue().equals(pledgeBondInfoDto.getInvAim())) {
                        resultList1.add(pledgeBondInfoDto);
                    }
                }
                if (融入券 != null && 卖空 != null) {
                    融入券.setAvaPledgePrin(ArithmeticUtil.doAdd(融入券.getAvaPledgePrin(), 卖空.getAvaPledgePrin()));
                }
                return resultList;
            }
        } else if (DictCodeList.回购拆借方向_逆回购.getValue().equals(ps)||DictCodeList.回购拆借方向_融出.getValue().equals(ps)) {
            params.put("plateDate", platformDateImpl.getSysDateStr());
            params.put("repRegistOrg", StringUtil.splitToList(GlobalConstant.COMMA, (String) params.get("repRegistOrg")));
            params.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
            params.put("tradeMarket",tradeMarket);
            resultList = bussRepoSecMapper.selectAvaPledgeBondDetailListAntiRepo(params);
        }
        return resultList;
    }

    /**
     * 撤销委托/回购交易时，删除回购质押品信息
     *
     * @param param 删除参数
     * @return successCount
     * @see
     * @since 1.0
     */
    public int deletePledgeBond(Map<String, Object> param) {
        String pageFrom = (String) param.get("pageFrom");
        String bizCode = (String) param.get("bizCode");
        if (StringUtil.isNotEmpty(pageFrom) && StringUtil.isNotEmpty(bizCode)) {
            return bussRepoSecMapper.deletePledgeBond(param);
        } else {
            return 0;
        }
    }

    public int insert(PledgeInsertDto pledgeInsertDto) {
        List<Integer> result =  mapperDelegateService.insertAutoClassifyBatch(pledgeInsertDto.getEtrPledgeList(),
                pledgeInsertDto.getFinDealPledgeList());
        int ret = 0;
        for (Integer integer : result) {
            ret = ret + integer;
        }
        return ret;
    }

    /**
     * 质押券置换换出列表按投资目的获取数据
     * nixiukuan
     * 20191129
     */
    public List<PledgeBondInfoDto> selectOutPledgeBondDetailList(Map<String, Object> param) {
        List<PledgeBondInfoDto> resultList = new ArrayList<>();
        param.put("POSITION_TYPE_04", DictCodeList.持仓类型_质押.getValue());
        param.put("plateDate", platformDateImpl.getSysDateStr());
        resultList=bussRepoSecMapper.selectOutPledgeBondDetailList(param);
        return resultList;
    }
}
