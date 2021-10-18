/*
 * 文件名：EtrBondFirstLBiddingService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年5月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.cfets.etrib.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.*;
import com.joyintech.tams.business.common.dto.PositionRuleInterParam;
import com.joyintech.tams.business.common.dto.PositionRulePtlData;
import com.joyintech.tams.business.common.service.PositionHttpRequest;
import com.joyintech.tams.business.common.util.PositionUtil;
import com.joyintech.tams.cfets.etrib.dao.CfetsEtrIbBondinfoMapper;
import com.joyintech.tams.cfets.etrib.dto.CfetsEtrIbBondPosinfo001Dto;
import com.joyintech.tams.cfets.etrib.dto.CfetsEtrIbBondinfo001Dto;
import com.joyintech.tams.cfets.etrib.dto.CfetsEtrIbBondinfo002Dto;
import com.joyintech.tams.cfets.etrib.dto.CfetsEtrTrdProductDealDto;
import com.joyintech.tams.common.base.dao.WflEtrEntrustAddMapper;
import com.joyintech.tams.common.base.dao.WflEtrEntrustMapper;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrEntrustAdd;
import com.joyintech.tams.common.base.entity.WflEtrEntrust;
import com.joyintech.tams.common.base.entity.WflEtrEntrustAdd;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.entrust.dto.EntrustCommonDto;
import com.joyintech.tams.invest.common.trade.service.impl.etrcash.EtrCommonCashPositionDealService;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 银行间债券指令
 * @author caiqun
 * @version 1.0 2021年10月14日
 * @see CfetsEtrIbBondinfoService
 * @since 1.0
 */
@Service("backEtrIbBondinfo001Service")
public class CfetsEtrIbBondinfoService extends BaseService{

    @Autowired
    private CfetsEtrIbBondinfoMapper cfetsetrIbBondinfoMapper;

    /**
     * 委托指令流程中Mapper
     */
    @Autowired
    private WflEtrEntrustMapper wflEtrEntrustMapper;

    /**
     * 委托指令附属表流程中Mapper
     */
    @Autowired
    private WflEtrEntrustAddMapper wflEtrEntrustAddMapper;



    /**
     * 平台日期
     */
    @Autowired(required = false)
    private IPlatformDate iPlatformDate;

    /**
     * 持仓信息查询
     *
     * @param page int
     * @param length int
     * @param pageParam int
     * @return
     * @see
     * @since 1.0
     */
    public Page<CfetsEtrIbBondinfo001Dto> findPositionsPage(int page, int length,
                                                       Map<String, Object> pageParam) {
        pageParam.put("sysDate", iPlatformDate.getSysDateStr());
        pageParam.put("POSITION_TYPE_03", BussinessDictCodeList.持仓类型_实际.getValue());
        pageParam.put("POSITION_TYPE_04", BussinessDictCodeList.持仓类型_质押.getValue());
        pageParam.put("POSITION_TYPE_05", BussinessDictCodeList.持仓类型_融入.getValue());
        pageParam.put("POSITION_TYPE_06", BussinessDictCodeList.持仓类型_融出.getValue());
        pageParam.put("VALUATION_SOURCE_CSD", BussinessDictCodeList.估值来源_中证登.getValue());
        pageParam.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        String portfolioId = (String)pageParam.get("portfolioId");
        if(StringUtil.isNotEmpty(portfolioId)){
            pageParam.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA,portfolioId));
        }
        String finprodId = (String)pageParam.get("finprodId");
        if(StringUtil.isNotEmpty(finprodId)){
            pageParam.put("finprodIdList", StringUtil.splitToList(GlobalConstant.COMMA,finprodId));
        }

        addCondionTradeMarket(pageParam); // 交易场所，仅限银行间指令
        pageParam.put("BOND_PRICE", BussinessConstant.BOND_PRICE);
        Page<CfetsEtrIbBondinfo001Dto> result = super.queryForPage(page, length, () -> cfetsetrIbBondinfoMapper.findPositionsPage(pageParam));

        //根据资产代码查询相关所有的交易信息
        List<CfetsEtrTrdProductDealDto> tradeList = cfetsetrIbBondinfoMapper.getTradeListByFinprodId(pageParam);
        if (result != null && !result.getRoot().isEmpty()) {
            for (CfetsEtrIbBondinfo001Dto dto : result.getRoot()) {
                //昨日持仓数量
                BigDecimal yesterDayPosAmount = dto.getCostPositionNum() == null ? BigDecimal.ZERO : dto.getCostPositionNum();
                //昨日综合成本
                BigDecimal yesterDaycostPosition = dto.getCostPosition() == null ? BigDecimal.ZERO : dto.getCostPosition();
                if (!StringUtil.isEmptyList(tradeList)) {
                    for (CfetsEtrTrdProductDealDto trdProductDeal : tradeList) {
                        if (trdProductDeal.getFinprodId().equals(dto.getFinprodId())
                                && trdProductDeal.getPortfolioId().equals(dto.getPortfolioId())) {
                            if (trdProductDeal.getPs().equals(DictCodeList.交易方向_买入.getValue())) {
                                BigDecimal shareAmt = trdProductDeal.getShareAmt() == null ? BigDecimal.ZERO : trdProductDeal.getShareAmt();
                                BigDecimal unitCprice = trdProductDeal.getUnitCprice() == null ? BigDecimal.ZERO : trdProductDeal.getUnitCprice();
                                if (ArithmeticUtil.doAdd(yesterDayPosAmount, shareAmt).compareTo(BigDecimal.ZERO) != 0) {
                                    //综合成本=昨日成本*昨日持仓数量+净价*份额
                                    yesterDaycostPosition = ArithmeticUtil.multiply(yesterDaycostPosition, yesterDayPosAmount);
                                    yesterDaycostPosition = ArithmeticUtil.doAdd(yesterDaycostPosition, ArithmeticUtil.multiply(shareAmt, unitCprice));
                                    //今日数量= 昨日持仓数量 + 份额
                                    yesterDayPosAmount = ArithmeticUtil.doAdd(yesterDayPosAmount, shareAmt);
                                    //单位成本= 综合成本 / 今日数量
                                    yesterDaycostPosition = ArithmeticUtil.divide(yesterDaycostPosition, yesterDayPosAmount, 12);
                                } else {
                                    yesterDayPosAmount = BigDecimal.ZERO;
                                    yesterDaycostPosition = BigDecimal.ZERO;
                                }
                            }
                            if (trdProductDeal.getPs().equals(DictCodeList.交易方向_卖出.getValue())) {
                                yesterDayPosAmount = yesterDayPosAmount.subtract(trdProductDeal.getShareAmt() == null ? new BigDecimal(0) : trdProductDeal.getShareAmt());
                            }
                        }
                    }
                }
                //单位持仓成本
                dto.setUnitCost(yesterDaycostPosition);
                //取实时行情
                String secCode = (String) pageParam.get("seccode");
                String url = pageParam.get("MarketInfoInterf_cmdsUrl")+"/rest/markinfo/sec/getMarkinfoBySecCode?seccode=" + secCode;
                Map<String, Object> map = getMarketInfo(url);
                if(StringUtil.isNotEmpty(map) && StringUtil.isNotEmpty(map.get("data"))) {
                    Map<String, Object> data = (Map<String, Object>)map.get("data");
                    if(StringUtil.isNotEmpty(data.get("latestCleanPrice"))) {
                        //最新净价取实时行情净价
                        dto.setPrice(new BigDecimal(data.get("latestCleanPrice").toString()).setScale(4, BigDecimal.ROUND_HALF_UP));
                        //持仓市值=持仓券面总额*最新净价/单位面值
                        dto.setPositionsPrice(dto.getShareAmt().multiply(dto.getPrice()).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP));
                    }
                }
                //持仓成本=持仓券面总额(万元)*持仓单位成本*100
                //dto.setPositionsAmt(ArithmeticUtil.multiply(dto.getShareAmt(), yesterDaycostPosition, new BigDecimal(100)));
                //持仓成本=持仓数量*持仓单位成本
                dto.setPositionsAmt(ArithmeticUtil.multiply(dto.getAmount(), yesterDaycostPosition).divide(new BigDecimal(10000)));
                //浮动盈亏 = 持仓市值-持仓成本
                dto.setFloatingPl(ArithmeticUtil.subtract(dto.getPositionsPrice(), dto.getPositionsAmt()));
            }
        }
        return result;

    }

    /**
     * 请求市场行情数据接口
     * @param url
     * @return
     */
    public Map<String,Object> getMarketInfo(String url){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(1000);
        factory.setReadTimeout(1000);
        RestTemplate template = new RestTemplate(factory);
        try {
            Map<String, Object> quoteMap = template.getForObject(url, Map.class);
            return quoteMap;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 只包含交易场所：01 银行间
     * @param pageParam 查询参数
     */
    private void addCondionTradeMarket(Map<String, Object> pageParam) {
        List<String> tradeMarketList = new ArrayList<>();
        tradeMarketList.add(DictCodeListExt1.交易场所_银行间.getValue());
        pageParam.put("tradeMarketList", tradeMarketList);
    }

    /**
     * 调用方式
     * PositionFutureManager.send(createMessage(dto, DictCodeListExt3.流程节点_审批生效.getValue(), DictCodeListExt3.系统动作_新建.getValue()));
     * 冲正方式
     * PositionFutureManager.revoke(dto.getEntrustId(), DictCodeListExt1.子系统代码_委托.getValue(), e);
     * 
     * 撤回
     * PositionRuleInterDto pDto = PositionUtil.initDto(dto.getEntrustId());
     * pDto.setSystemAct(DictCodeListExt3.系统动作_新建.getValue()); // 
     * pDto.setModelType(DictCodeListExt1.子系统代码_委托.getValue());// 委托
     * pDto.setFlowStatus(DictCodeListExt3.流程节点_流程退回.getValue());
     *
     *         // 调用头寸引擎
     *         PositionFutureManager.sendRevoke(pDto);
     *         
     * @param dto
     * @param flowStatus
     * @param systemAct
     * @return
     */
    public PositionRuleInterParam createMessage(CfetsEtrIbBondinfo002Dto dto, String flowStatus, String systemAct) {

        PositionRuleInterParam result = PositionUtil.initDto(dto.getEntrustId());
        try {
            // head
            result.setFlowStatus(flowStatus); // 
            result.setModelType(DictCodeListExt1.子系统代码_委托.getValue());

            if (StringUtil.isNotEmpty(dto.getBalanceCheck())){
                result.setBalanceCheck(dto.getBalanceCheck());
            }

            // body 
            result.setBusiType(DictCodeListExt3.头寸业务类型_现券交易.getValue()); // 业务类型：01债券一级投标，02现券交易，03债券行权交易，04债券存续期交易
            if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_行权_回售.getValue())){
                result.setBusiType(DictCodeListExt3.头寸业务类型_债券行权交易.getValue()); // 业务类型：01债券一级投标，02现券交易，03债券行权交易，04债券存续期交易    
            }

            result.setSettleSpeed(dto.getSettleSpreed()); // 清算速度 
            result.setTradePlace(dto.getTradeMarket()); // 上交所或深交所
            result.setDeliveryType("03"); // 结算方式
            result.setCcy(dto.getCcy()); // 币种
            result.setCdate(dto.getVdate()); // 开始日
            result.setBusiAct(DictCodeListExt3.业务动作_投资指令.getValue()); // 业务动作：01投资指令，02交易登记，03汇总确认，中标结果维护
            result.setPs(dto.getPs());
            result.setSystemAct(systemAct);
            result.setFinprodId(dto.getFinprodId());

            // ptlList
            PositionRulePtlData d = new PositionRulePtlData();
            d.setPortfolioId(dto.getPortfolioId()); // 组合代码
            d.setCashAcctId(EtrCommonCashPositionDealService.select组合托管户(dto.getPortfolioId(), dto.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue()));
            d.setCashAmount(dto.getFpriceAmt()); // 交易金额
            d.setSecAcctId(dto.getSecAcctId());
            d.setSecAmount(dto.getEntrustNum()); // 本方券面总额
            d.setTradeId(dto.getEntrustId()); // 指令或交易编号
            d.setEtrId(dto.getEntrustId());
            d.setEtrComplete(DictCodeList.是否_是.getValue());
            d.setInvAim(dto.getInvAim());
            d.setIntegrateSecAmount(dto.getIntAmt()); // 场内暂时为空，银行间会有
            result.setPtlList(Arrays.asList(d)); // 融入券券面总额

            return result;
        } catch (Exception e) {
            throw new RuntimeException("指令消息初始化失败", e);
        }
    }
    /**
     * 持仓信息查询
     *
     * @param page int
     * @param length int
     * @param pageParam int
     * @return
     * @see
     * @since 1.0
     */
    public Page<CfetsEtrIbBondPosinfo001Dto> findPositionsPageNew(int page, int length,
                                                               Map<String, Object> pageParam) {
        Page<CfetsEtrIbBondPosinfo001Dto> result = new Page<CfetsEtrIbBondPosinfo001Dto>();
        List<CfetsEtrIbBondPosinfo001Dto> resultList = new ArrayList<CfetsEtrIbBondPosinfo001Dto>();
        pageParam.put("sysDate", iPlatformDate.getSysDateStr());
        //调用头寸，查询当前日期的所有持仓数据
        Map<String, Object> map=new HashMap<>();
        //map.put("cdate",pageParam.get("sysDate"));
        map.put("cdate","2021-09-06");
        String m=PositionHttpRequest.query(map);
        List<Map> list = JsonUtils.json2Object(m, List.class);
        List<String> portfolioIdList=new ArrayList<>();//组合List
        List<String> finprodIdList=new ArrayList<>();//资产List
        for(Map t:list){
            portfolioIdList.add(t.get("portfolioId").toString());
            finprodIdList.add(t.get("finprodId").toString());
        }
        pageParam.put("portfolioIdList", portfolioIdList);
        pageParam.put("finprodIdList", finprodIdList);
        addCondionTradeMarket(pageParam); // 交易场所，仅限银行间指令

        //根据组合信息List，查询出所有的组合信息
        List<CfetsEtrIbBondPosinfo001Dto> portfolioInfoist=cfetsetrIbBondinfoMapper.selectPortfolioInfoist(pageParam);
        //根据资产信息List，查询出所有的资产信息
        List<CfetsEtrIbBondPosinfo001Dto> finprodInfoList=cfetsetrIbBondinfoMapper.selectFinprodInfoList(pageParam);

        //根据接口平台数据组装新的list
        for(Map t:list){
            String portfolioId=t.get("portfolioId").toString();
            String finprodId=t.get("finprodId").toString();
            CfetsEtrIbBondPosinfo001Dto cfetsEtrIbBondPosinfo001Dto=new CfetsEtrIbBondPosinfo001Dto();
            cfetsEtrIbBondPosinfo001Dto.setPortfolioId(portfolioId);
            for(CfetsEtrIbBondPosinfo001Dto prd:portfolioInfoist){
                if(StringUtil.equals(prd.getPortfolioId(),portfolioId)){
                    cfetsEtrIbBondPosinfo001Dto.setPortfolioName(prd.getPortfolioName());
                    break;
                }
            }
            for(CfetsEtrIbBondPosinfo001Dto fin:finprodInfoList){
                if(StringUtil.equals(fin.getFinprodId(),finprodId)){
                    cfetsEtrIbBondPosinfo001Dto.setFinprodMarketId(fin.getFinprodMarketId());
                    cfetsEtrIbBondPosinfo001Dto.setFinprodAbbr(fin.getFinprodAbbr());
                    cfetsEtrIbBondPosinfo001Dto.setSecType1(fin.getSecType1());
                    cfetsEtrIbBondPosinfo001Dto.setSecType1Name(SysDataUtil.getNameFromDataDict(DictCode.行内资产二级分类, fin.getSecType1()));
                    cfetsEtrIbBondPosinfo001Dto.setPrice(fin.getPrice());
                    break;
                }
            }
            List<Map> resultLists= (List<Map>) t.get("resultList");
            for(Map bt:resultLists){
                if(StringUtil.isNotEmpty(bt.get("hodingType"))){
                    if(StringUtil.equals(bt.get("hodingType").toString(),"A_004")){
                        cfetsEtrIbBondPosinfo001Dto.setEtrPledgePrin(new BigDecimal( bt.get("balance").toString()) );
                    }
                    if(StringUtil.equals(bt.get("hodingType").toString(),"A_026")){
                        cfetsEtrIbBondPosinfo001Dto.setEtrAvaPledgePrin(new BigDecimal( bt.get("balance").toString()) );
                    }
                }

            }

            resultList.add(cfetsEtrIbBondPosinfo001Dto);
        }
        result.setRoot(resultList);


        return result;

    }

    /**
     * 债券质押持仓信息查询
     *
     * @param page int
     * @param length int
     * @param pageParam int
     * @return
     * @see
     * @since 1.0
     */
    public Page<CfetsEtrIbBondinfo001Dto> findZqzyPositionsPage(int page, int length,
                                                                Map<String, Object> pageParam) {
        pageParam.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        pageParam.put("TRADE_MARKET_01", DictCodeListExt1.交易场所_银行间.getValue());
        pageParam.put("ENTRUST_STATUS_08", DictCodeListExt3.委托状态_流程中.getValue());
        String portfolioId = (String)pageParam.get("portfolioId");
        if(StringUtil.isNotEmpty(portfolioId)){
            pageParam.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA,portfolioId));
        }
        String finprodId = (String)pageParam.get("finprodId");
        if(StringUtil.isNotEmpty(finprodId)){
            pageParam.put("finprodIdList", StringUtil.splitToList(GlobalConstant.COMMA,finprodId));
        }
        return super.queryForPage(page, length, () -> cfetsetrIbBondinfoMapper.findZqzyPositionsPage(pageParam));
    }

    @Transactional
    public BaseEtrInvestInfoDto saveBaseEtrInvestInfoDto(CfetsEtrIbBondinfo002Dto dto) {
        BaseEtrInvestInfoDto entity = new BaseEtrInvestInfoDto();
        try {
            EntrustCommonDto entrustCommonDto = new EntrustCommonDto();
            EtrEntrust etrEntrust = new EtrEntrust();
            EtrEntrustAdd etrEntrustAdd = new EtrEntrustAdd();
            entrustCommonDto.setEtrEntrust(etrEntrust);
            entrustCommonDto.setEtrEntrustAdd(etrEntrustAdd);
            dto.setInvManagerId(LoginUserUtil.getUserName());
            dto.setTradeMarketMode(DictCodeListExt1.交易市场_二级市场.getValue());
            dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
            dto.setIsOutTrade(DictCodeList.是否_是.getValue());
            dto.setEntrustStatus(DictCodeListExt3.委托状态_流程中.getValue());
            dto.setRecStatus(DictCodeListExt3.委托状态_流程中.getValue());
            BeanUtil.copyProperties(etrEntrust, dto);
            etrEntrust.setEntrustDate(iPlatformDate.getSysDate());
            etrEntrust.setEntrustAmt(null);
            etrEntrust.setEntrustNum(dto.getEntrustNum().multiply(new BigDecimal(100))); //前台单位万元
            BeanUtil.copyProperties(etrEntrustAdd, dto);
            etrEntrustAdd.setAssetType(DictCodeList.平安资产类型_债券.getValue());
            etrEntrustAdd.setVdate(null);
            etrEntrustAdd.setMdate(null);
            //etrEntrustAdd.setOurEntNum(com.joyintech.framework.util.ArithmeticUtil.divide(dto.getEntrustOwnNum(), 100, 4));
            //etrEntrustAdd.setLendinEntNum(com.joyintech.framework.util.ArithmeticUtil.divide(dto.getEntrustIntegrateNum(), 100, 4));
            // 单位全价 fuyongsheng 20210917 edit start
            etrEntrustAdd.setFullPrice(dto.getUnitFprice());
            // 单位全价 fuyongsheng 20210917 edit end
            if (dto.getExerYield() != null) {
                etrEntrustAdd.setExerYield(dto.getExerYield().divide(new BigDecimal(100)));
            }
            if (dto.getMatureYield() != null) {
                etrEntrustAdd.setMatureYield(dto.getMatureYield().divide(new BigDecimal(100)));
            }

            if (StringUtil.equals(dto.getSettleSpreed(),
                    DictCodeList.清算速度_T_0.getValue())) {
                etrEntrustAdd.setSettleSpreed((long) 0);
            }
            if (StringUtil.equals(dto.getSettleSpreed(),
                    DictCodeList.清算速度_T_1.getValue())) {
                etrEntrustAdd.setSettleSpreed((long) 1);
            }
            entity.setEtrEntrust(etrEntrust);
            entity.setEtrEntrustAdd(etrEntrustAdd);
            if (entity != null) {
                WflEtrEntrust entrust = new WflEtrEntrust();
                WflEtrEntrustAdd entrustAdd = new WflEtrEntrustAdd();
                BeanUtil.copyPropertiesIgnoreNull(entrust, entity.getEtrEntrust());
                BeanUtil.copyPropertiesIgnoreNull(entrustAdd, entity.getEtrEntrustAdd());
                wflEtrEntrustMapper.insertSelective(entrust);
                wflEtrEntrustAddMapper.insertSelective(entrustAdd);
                createMessage(dto, DictCodeListExt3.流程节点_审批生效.getValue(), DictCodeListExt3.系统动作_新建.getValue());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return entity;
    }

}
