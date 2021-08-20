package com.joyintech.service.bankuplink.reponew.service;

import com.joyintech.background.component.uplink.interbank.business.dto.RepoQuoteExtDto;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.*;
import com.joyintech.service.bankuplink.cstp.dto.CstpSelectDto;
import com.joyintech.service.bankuplink.ibu.dto.RepoQuoteInfoDto;
import com.joyintech.service.bankuplink.ibu.service.IbuComponentCommonService;
import com.joyintech.service.bankuplink.reponew.dao.CfetsRepoDealQuotedPr001Mapper;
import com.joyintech.service.bankuplink.reponew.dto.CfetsRepoDealQuotedPr001Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author chenjiaxing
 * @version 1.0 2021年8月7日
 * @see CfetsRepoDealQuotedPr001Service
 * @since 1.0
 */
@Service("backCfetsRepoDealQuotedPr001Service")
public class CfetsRepoDealQuotedPr001Service extends BaseService {
    /**
     * 注入Mapper
     */
    @Autowired
    private CfetsRepoDealQuotedPr001Mapper cfetsRepoDealQuotedPr001Mapper;

    /**
     * {@linkplain}
     */
    @Autowired
    private IbuComponentCommonService ibuComponentCommonService;

    /**
     * 委托指令查询
     *
     * @param param 入参
     * @return 委托指令
     * @see
     * @since 1.0
     */
    public List<CfetsRepoDealQuotedPr001Dto> queryZlzxList(Map<String, Object> param) throws Exception {
        param.put("entrustStatusUnAccept", BussinessDictCodeList.委托状态_待接收.getValue());
        param.put("entrustStatusAccept", BussinessDictCodeList.委托状态_已接收.getValue());
        param.put("entrustStatusComple", BussinessDictCodeList.委托状态_已完成.getValue());
        param.put("FINPROD_TYPE2_Z", BussinessDictCodeList.金融产品类型_债券质押式正回购.getValue());
        param.put("FINPROD_TYPE2_N", BussinessDictCodeList.金融产品类型_债券质押式逆回购.getValue());
        param.put("ps03", BussinessDictCodeList.回购拆借方向_正回购.getValue());
        param.put("ps04", BussinessDictCodeList.回购拆借方向_逆回购.getValue());
        param.put("tradeMarket", BussinessDictCodeList.交易场所_银行间.getValue());
        param.put("secAcctSource01", BussinessDictCodeList.账户来源_理财.getValue());
        param.put("secAcctSource02", BussinessDictCodeList.账户来源_自营.getValue());
        param.put("calendarId", CacheUtil.getSysParam("ibuVar_calendarId"));
        List<CfetsRepoDealQuotedPr001Dto> pageResult = cfetsRepoDealQuotedPr001Mapper.queryZlzxList(param);
        return pageResult;
    }

    public List<CfetsRepoDealQuotedPr001Dto> queryZyqglList(Map<String, Object> pageParam)
            throws Exception {
        pageParam.put("POSITION_TYPE_01", DictCodeList.持仓类型_可用.getValue());
        pageParam.put("POSITION_TYPE_03", DictCodeList.持仓类型_实际.getValue());
        pageParam.put("POSITION_TYPE_04", DictCodeList.持仓类型_质押.getValue());
        pageParam.put("POSITION_TYPE_05", DictCodeList.持仓类型_融入.getValue());
        pageParam.put("POSITION_TYPE_06", DictCodeList.持仓类型_融出.getValue());
        pageParam.put("POSITION_TYPE_08", DictCodeListExt1.持仓类型_预占用.getValue());
        pageParam.put("POSITION_TYPE_11", DictCodeListExt1.持仓类型_卖空.getValue());
        pageParam.put("POSITION_TYPE_12", DictCodeListExt1.持仓类型_卖空可用.getValue());

        pageParam.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        pageParam.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());

        pageParam.put("TRADEPLACE01", DictCodeListExt1.交易场所_银行间.getValue());
        pageParam.put("TRADEPLACE03", DictCodeListExt1.交易场所_上海证券交易所.getValue());
        pageParam.put("TRADEPLACE04", DictCodeListExt1.交易场所_深圳证券交易所.getValue());
        List<CfetsRepoDealQuotedPr001Dto> pageResult = cfetsRepoDealQuotedPr001Mapper.queryZyqglList(pageParam);
        return pageResult;
    }

    /**
     * 分页查询
     * @param map 请求参数
     * @return 分页查询结果
     * @throws Exception
     * @see
     * @since 1.0
     */
    public List<CstpSelectDto> getCstpQryTableList(Map<String,Object> map) throws Exception{
        if(StringUtil.isNotEmpty((String)map.get("finprodType"))){
            switch ((String)map.get("finprodType")){
                case "T01": map.replace("finprodType", "BOND");
                    break;
                case "T02": {
                    map.put("marketindicator", "9");
                    map.remove("finprodType");
                };
                break;
                case "T03":
                {
                    map.put("marketindicator", "10");
                    map.remove("finprodType");
                }
                ;
                break;
                case "T04":
                {
                    map.put("marketindicator", "8");
                    map.remove("finprodType");
                }
                ;
                break;
                case "T05":
                {
                    map.put("marketindicator", "2");
                    map.remove("finprodType");
                }
                ;
                break;
            }
        }
        if(StringUtil.isNotEmpty((String)map.get("ps"))){
            map.replace("ps", StringUtil.splitToList(",", (String)map.get("ps")));
        }
        map.put("TRADE_STATUS", BussinessDictCodeList.交易状态_初始.getValue());
        map.put("PARTMATCH", BussinessDictCodeList.匹配状态_部分匹配.getValue());
        map.put("acctType",BussinessDictCodeList.资金账户类型_托管账户.getValue());
        return cfetsRepoDealQuotedPr001Mapper.getCstpQryTableList(map);
    }

    public List<RepoQuoteInfoDto> queryWtdhbjList(Map<String, Object> param) {
        List<RepoQuoteInfoDto> bondQuoteInfoDtoList = new ArrayList<>();
        // 查询条件转换
        List<RepoQuoteExtDto> bondQuoteExtDtoList = ibuComponentCommonService.getRepoDayOfferTableList(
                param);
        bondQuoteExtDtoList.forEach(x -> {
            bondQuoteInfoDtoList.add(this.dtoConvert(x));
        });

        return bondQuoteInfoDtoList;
    }

    /**
     * 实体类属性转换
     *
     * @param bondQuoteExtDto 组件返回dto
     * @return 画面显示dto
     * @see
     * @since 1.0
     */
    private RepoQuoteInfoDto dtoConvert(RepoQuoteExtDto bondQuoteExtDto) {
        RepoQuoteInfoDto bondQuoteInfoDto = new RepoQuoteInfoDto();
        bondQuoteInfoDto.setEntrustId(bondQuoteExtDto.getEntrustId());
        bondQuoteInfoDto.setQuoteId(bondQuoteExtDto.getQuoteId());
        bondQuoteInfoDto.setQuoteRoleType(bondQuoteExtDto.getQuoteRoleType());
        bondQuoteInfoDto.setPs(bondQuoteExtDto.getRepoPs());
        bondQuoteInfoDto.setQuoteType(bondQuoteExtDto.getQuoteType());
        bondQuoteInfoDto.setTradeStatus(bondQuoteExtDto.getTradeStatus());
        bondQuoteInfoDto.setSendStatus(bondQuoteExtDto.getSendStatus());
        bondQuoteInfoDto.setOuPartyId(bondQuoteExtDto.getOuPartyId());
        bondQuoteInfoDto.setOuTraderId(bondQuoteExtDto.getOuTraderId());
        bondQuoteInfoDto.setCpPartyId(
                bondQuoteExtDto.getCpPartyId() != null ? bondQuoteExtDto.getCpPartyId() : null);
        bondQuoteInfoDto.setCpTrader(
                bondQuoteExtDto.getCpTraderId() != null ? bondQuoteExtDto.getCpTraderId() : null);
        // 本方交易员姓名，对手方交易员姓名,本方机构名称,对手方机构名称
        bondQuoteInfoDto.setOuTraderName(bondQuoteExtDto.getOuTraderName());
        bondQuoteInfoDto.setOuPartyName(bondQuoteExtDto.getOuPartyName());
        bondQuoteInfoDto.setCpTraderName(bondQuoteExtDto.getCpTraderName());
        bondQuoteInfoDto.setCpPartyName(bondQuoteExtDto.getCpPartyName());
        bondQuoteInfoDto.setRate(
                ArithmeticUtil.multiply(bondQuoteExtDto.getRate(), new BigDecimal(100)));
        bondQuoteInfoDto.setFpriceAmt(bondQuoteExtDto.getFpriceAmt());
        bondQuoteInfoDto.setShareAmt(bondQuoteExtDto.getShareAmt());
        bondQuoteInfoDto.setTerm(
                bondQuoteExtDto.getTerm() == null ? 0L : bondQuoteExtDto.getTerm());
        bondQuoteInfoDto.setvSettleDate(bondQuoteExtDto.getvSettleDate());
        bondQuoteInfoDto.setmSettleDate(bondQuoteExtDto.getmSettleDate());
        bondQuoteInfoDto.setTermType(bondQuoteExtDto.getTermType());// 期限品种
        bondQuoteInfoDto.setSettleSpeed(bondQuoteExtDto.getSettleSpeed());
        bondQuoteInfoDto.setCfetsQuoteId(bondQuoteExtDto.getCfetsQuoteId());
        bondQuoteInfoDto.setfTradeId(bondQuoteExtDto.getExecid());
        bondQuoteInfoDto.setValidStatus(bondQuoteExtDto.getValidStatus());
        bondQuoteInfoDto.setWorkflowId(bondQuoteExtDto.getWorkflowId());
        bondQuoteInfoDto.setIsDzh(bondQuoteExtDto.getIsDzh());
        bondQuoteInfoDto.setSettType(bondQuoteExtDto.getSettType());
        bondQuoteInfoDto.setSettCcy(bondQuoteExtDto.getSettCcy());
        bondQuoteInfoDto.setCcy(bondQuoteExtDto.getCcy());
        bondQuoteInfoDto.setmIntAmt(bondQuoteExtDto.getmIntAmt());
        bondQuoteInfoDto.setmTradeAmt(bondQuoteExtDto.getmTradeAmt());
        bondQuoteInfoDto.setBondCode(bondQuoteExtDto.getBondCode());
        bondQuoteInfoDto.setBondAbbrName(bondQuoteExtDto.getBondAbbrName());
        bondQuoteInfoDto.setPledgeRate(bondQuoteExtDto.getPledgeRate().multiply(new BigDecimal("100")));
        bondQuoteInfoDto.setPrin(bondQuoteExtDto.getPrin());
        bondQuoteInfoDto.setExchangerate(new BigDecimal("1"));
        bondQuoteInfoDto.setLstmntdate(bondQuoteExtDto.getUpdateTime());


        return bondQuoteInfoDto;
    }
}
