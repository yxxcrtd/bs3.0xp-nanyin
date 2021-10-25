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
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.business.common.dto.PositionBondExecuteParam;
import com.joyintech.tams.business.common.dto.PositionRulePtlData;
import com.joyintech.tams.business.common.pojo.BaseMap;
import com.joyintech.tams.business.common.service.PositionHttpRequest;
import com.joyintech.tams.business.common.util.PositionUtil;
import com.joyintech.tams.business.common.util.UserUtil;
import com.joyintech.tams.cfets.etrib.dao.CfetsEtrIbBondinfo002Mapper;
import com.joyintech.tams.cfets.etrib.dao.CfetsEtrIbBondinfoMapper;
import com.joyintech.tams.cfets.etrib.dto.*;
import com.joyintech.tams.common.base.dao.WflEtrEntrustAddMapper;
import com.joyintech.tams.common.base.dao.WflEtrEntrustMapper;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrEntrustAdd;
import com.joyintech.tams.common.base.entity.WflEtrEntrust;
import com.joyintech.tams.common.base.entity.WflEtrEntrustAdd;
import com.joyintech.tams.component.business.basicdata.dto.BusiFinProductRatingDto;
import com.joyintech.tams.component.business.basicdata.service.BusiProductRatingService;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.entrust.dto.EntrustCommonDto;
import com.joyintech.tams.invest.common.trade.service.impl.etrcash.EtrCommonCashPositionDealService;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 银行间债券指令
 * @author caiqun
 * @version 1.0 2021年10月14日
 * @see CfetsEtrIbBondinfoService
 * @since 1.0
 */
@Service("CfetsEtrIbBondinfoService")
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
    @Autowired
    private CfetsEtrIbBondinfo002Mapper cfetsEtrIbBondinfo002Mapper;

    /**
     * 产品评级
     **/
    @Autowired
    private BusiProductRatingService busiProductRatingService;
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
    public PositionBondExecuteParam createMessage(CfetsEtrIbBondinfo002Dto dto, String flowStatus, String systemAct) {

        PositionBondExecuteParam result = PositionUtil.initDto(dto.getEntrustId());
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
     * 指令信息查询
     *
     * @param page int
     * @param length int
     * @param pageParam int
     * @return
     * @see
     * @since 1.0
     */
    public Page<CfetsEtrIbBondinfo001Dto> findEtrinfoPage(CfetsEtrIbBondinfo001Dto dto) {
        Map<String, Object> pageParam = BeanUtil.object2Map(dto);
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
        String userId = UserUtil.getUserName();
        if (StringUtils.isNotEmpty(userId)) {
            pageParam.put("userId", UserUtil.getUserName());
            return super.queryForPage(dto.getPageMode().getPageNo(), dto.getPageMode().getPageSize(), () -> cfetsetrIbBondinfoMapper.findEtrinfoPage(pageParam));
        }else{
            return null;
        }

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

    public Page<CfetsEtrIbBondPosinfo001Dto> queryPositionsPage(int page, int length, CfetsEtrIbBondPosiPage001RequestDto requestDto) {
        if (!StringUtil.isNotBlank(requestDto.getCdate())){
            requestDto.setCdate(iPlatformDate.getSysDateStr());
        }
        // 1.分页查询列表
        Page<CfetsEtrIbBondPosinfo001Dto> result = super.queryForPage(page, length, () -> cfetsetrIbBondinfoMapper.queryPositionsPage(requestDto));
        return result;
    }

    /**
     * 银行间指令，头寸信息
     * @param pageParam
     * @return
     */
    public List<BaseMap> queryPositionInfo(CfetsEtrIbBondPosiInfoRequestDto requestDto) {
        // 卖空模式
        String flag = CacheUtil.getSysParam("SHORT_SELLING_SUPPORTED");

        // 条件非空校验
        posiInfoCheckCondition(requestDto);

        if (StringUtil.isEmpty(requestDto.getFinprodId()) || requestDto.getPortfolioIdList() == null || requestDto.getPortfolioIdList().size() == 0){
            return nullData(flag);
        }
        
        // 查询头寸信息
        Map map = cfetsetrIbBondinfoMapper.queryPositionInfo(requestDto);
        
        if (map != null){
            return assemble(flag , map);
        }

        /**
         * 返回空值数据格式
         */
        return nullData(flag);
    }

    private void posiInfoCheckCondition(CfetsEtrIbBondPosiInfoRequestDto requestDto) {
        if (requestDto == null){
            throw new RuntimeException("入参不空！");
        }

        if (StringUtil.isEmpty(requestDto.getCdate())){
            requestDto.setCdate(iPlatformDate.getSysDateStr());
        }
    }

    private List<BaseMap> nullData(String flag) {
        List<BaseMap> resp = new ArrayList<>();
        /*T日可用现金*/
        resp.add(BaseMap.newMap()
                .put("field", "t0kyxj")
                .put("name", "T日可用现金（元）")
                .put("amount", "0.00")
                .put("type", "xj"));
        /*T+1日可用现金*/
        resp.add(BaseMap.newMap()
                .put("field", "t1kyxj")
                .put("name", "T+1日可用现金（元）")
                .put("amount", "0.00")
                .put("type", "xj"));
        /*账户现金*/
        resp.add(BaseMap.newMap()
                .put("field", "zhxj")
                .put("name", "账户现金（元）")
                .put("amount", "0.00")
                .put("type", "xj"));

        if (StringUtil.equals(flag, "01") || StringUtil.equals(flag, "02")){
            // 卖空模式设置为模式一、模式二时显示此字段
            /*融入券卖空待购回持仓*/
            resp.add(BaseMap.newMap()
                    .put("field", "rrqmkdghcc")
                    .put("name", "融入券卖空待购回持仓（元）")
                    .put("amount", "0.00")
                    .put("type", "cc"));
        }

        if (StringUtil.equals(flag, "01") || StringUtil.equals(flag, "03")){
            // 卖空模式设置为模式一、模式三时显示此字段
            /*资产T日可用持仓*/
            resp.add(BaseMap.newMap()
                    .put("field", "zctrkycc")
                    .put("name", "资产T日可用持仓（元）")
                    .put("amount", "0.00")
                    .put("type", "cc"));
        }

        if (StringUtil.equals(flag, "01") || StringUtil.equals(flag, "03")){
            // 卖空模式设置为模式一、模式三时显示此字段
            /*资产T+1日可用持仓*/
            resp.add(BaseMap.newMap()
                    .put("field", "zct1rkycc")
                    .put("name", "资产T+1日可用持仓（元）")
                    .put("amount", "0.00")
                    .put("type", "cc"));
        }

        if (StringUtil.equals(flag, "02")){
            // 卖空模式设置为模式二时显示此字段
            /*本方券T日可用持仓*/
            resp.add(BaseMap.newMap()
                    .put("field", "bfqtrkycc")
                    .put("name", "本方券T日可用持仓（元）")
                    .put("amount", "0.00")
                    .put("type", "cc"));
        }

        if (StringUtil.equals(flag, "02")){
            // 卖空模式设置为模式二时显示此字段
            /*本方券T+1日可用持仓*/
            resp.add(BaseMap.newMap()
                    .put("field", "bfqt1rkycc")
                    .put("name", "本方券T+1日可用持仓（元）")
                    .put("amount", "0.00")
                    .put("type", "cc"));
        }

        if (StringUtil.equals(flag, "02")){
            // 卖空模式设置为模式二时显示此字段
            /*融入券T日可用持仓*/
            resp.add(BaseMap.newMap()
                    .put("field", "rrqtrkycc")
                    .put("name", "融入券T日可用持仓（元）")
                    .put("amount", "0.00")
                    .put("type", "cc"));
        }

        if (StringUtil.equals(flag, "02")){
            // 卖空模式设置为模式二时显示此字段
            /*融入券T+1日可用持仓*/
            resp.add(BaseMap.newMap()
                    .put("field", "rrqt1rkycc")
                    .put("name", "融入券T+1日可用持仓（元）")
                    .put("amount", "0.00")
                    .put("type", "cc"));
        }
        return resp;
    }

    private List<BaseMap> assemble(String flag, Map map) {
        // 初始化返回集合
        List<BaseMap> resp = new ArrayList<>();
        BaseMap bm = BaseMap.newMap(map);

        /*T日可用现金*/
        resp.add(BaseMap.newMap()
                .put("field", "T0KYXJ")
                .put("name", "T日可用现金（元）")
                .put("amount", bm.getBigDecimalStr("T0KYXJ", "0.00"))
                .put("type", "xj"));
        /*T+1日可用现金*/
        resp.add(BaseMap.newMap()
                .put("field", "T1KYXJ")
                .put("name", "T+1日可用现金（元）")
                .put("amount", bm.getBigDecimalStr("T1KYXJ", "0.00"))
                .put("type", "xj"));
        /*账户现金*/
        resp.add(BaseMap.newMap()
                .put("field", "ZHXJ")
                .put("name", "账户现金（元）")
                .put("amount", bm.getBigDecimalStr("ZHXJ", "0.00"))
                .put("type", "xj"));

        if (StringUtil.equals(flag, "01") || StringUtil.equals(flag, "02")){
            // 卖空模式设置为模式一、模式二时显示此字段
            /*融入券卖空待购回持仓*/
            resp.add(BaseMap.newMap()
                    .put("field", "RRQMKDGHCC")
                    .put("name", "融入券卖空待购回持仓（元）")
                    .put("amount", bm.getBigDecimalStr("RRQMKDGHCC", "0.00"))
                    .put("type", "cc"));
        }

        if (StringUtil.equals(flag, "01") || StringUtil.equals(flag, "03")){
            // 卖空模式设置为模式一、模式三时显示此字段
            /*资产T日可用持仓*/
            resp.add(BaseMap.newMap()
                    .put("field", "ZCTRKYCC")
                    .put("name", "资产T日可用持仓（元）")
                    .put("amount", bm.getBigDecimalStr("ZCTRKYCC", "0.00"))
                    .put("type", "cc"));
        }

        if (StringUtil.equals(flag, "01") || StringUtil.equals(flag, "03")){
            // 卖空模式设置为模式一、模式三时显示此字段
            /*资产T+1日可用持仓*/
            resp.add(BaseMap.newMap()
                    .put("field", "ZCT1RKYCC")
                    .put("name", "资产T+1日可用持仓（元）")
                    .put("amount", bm.getBigDecimalStr("ZCT1RKYCC", "0.00"))
                    .put("type", "cc"));
        }

        if (StringUtil.equals(flag, "02")){
            // 卖空模式设置为模式二时显示此字段
            /*本方券T日可用持仓*/
            resp.add(BaseMap.newMap()
                    .put("field", "BFQTRKYCC")
                    .put("name", "本方券T日可用持仓（元）")
                    .put("amount", bm.getBigDecimalStr("BFQTRKYCC", "0.00"))
                    .put("type", "cc"));
        }

        if (StringUtil.equals(flag, "02")){
            // 卖空模式设置为模式二时显示此字段
            /*本方券T+1日可用持仓*/
            resp.add(BaseMap.newMap()
                    .put("field", "BFQT1RKYCC")
                    .put("name", "本方券T+1日可用持仓（元）")
                    .put("amount", bm.getBigDecimalStr("BFQT1RKYCC", "0.00"))
                    .put("type", "cc"));
        }

        if (StringUtil.equals(flag, "02")){
            // 卖空模式设置为模式二时显示此字段
            /*融入券T日可用持仓*/
            resp.add(BaseMap.newMap()
                    .put("field", "RRQTRKYCC")
                    .put("name", "融入券T日可用持仓（元）")
                    .put("amount", bm.getBigDecimalStr("RRQTRKYCC", "0.00"))
                    .put("type", "cc"));
        }

        if (StringUtil.equals(flag, "02")){
            // 卖空模式设置为模式二时显示此字段
            /*融入券T+1日可用持仓*/
            resp.add(BaseMap.newMap()
                    .put("field", "RRQT1RKYCC")
                    .put("name", "融入券T+1日可用持仓（元）")
                    .put("amount", bm.getBigDecimalStr("RRQT1RKYCC", "0.00"))
                    .put("type", "cc"));
        }
        return resp;
    }

    /**
     * 获得债券信息
     *
     * @param secId
     * @return dto
     */
    public CfetsEtrFinProductDto getEtrFinProductInfo(String secId) {
        CfetsEtrFinProductDto dto = cfetsetrIbBondinfoMapper.getEtrFinProductInfo(secId);
        Map<String, Object> map = new HashMap<>();
        if (dto != null) {
            map.put("secId",secId);
            map.put("marketId",dto.getMarketId());
            //下一付息日若无，则显示为“—”
            if (StringUtil.isEmpty(dto.getLastPaydate())) {
                dto.setLastPaydate("--");
            }
            //下一行权日若无，则显示为“—”
            if (StringUtil.isEmpty(dto.getXqdate())) {
                dto.setXqdate("--");
            }
            //是否含权债
            if (StringUtil.isEmpty(dto.getXqFlg())) {
                dto.setXqFlg("无");
            }
            if (dto.getOtype().equals("01")) {
                dto.setZxGrade("");
            } else if (dto.getOtype().equals("02")) {
                dto.setZtGrade("");
            }
            //            获取信用评级结果
            List<BusiFinProductRatingDto> ztList = busiProductRatingService.getZtBestRatingByOrgId(map);
            if (ztList != null && ztList.size() > 0) {
//                遍历获取主体评级明细
                for (BusiFinProductRatingDto busdto : ztList) {
                    if (DictCodeListExt3.评级来源类型_外部.getValue().equals(busdto.getInOrOut())) {
//                        主体评级为主体评级明细的评级结果
                        dto.setZtGrade(busdto.getGradeResult());
                    }
                    if (DictCodeListExt3.评级来源类型_内部.getValue().equals(busdto.getInOrOut())) {
                        dto.setZtGrade(busdto.getGradeResult());
                    }
                }
            }

//            以标的市场代码查询最优先债项评级
            List<BusiFinProductRatingDto> zxList = busiProductRatingService.getZxBestRatingByMarketId(map);
            if (zxList != null && zxList.size() > 0) {
//                遍历获取债项评级明细
                for (BusiFinProductRatingDto busdto : zxList) {
                    if (DictCodeListExt3.评级来源类型_外部.getValue().equals(busdto.getInOrOut())) {
//                        主体评级为债项评级明细的评级结果
                        dto.setZxGrade(busdto.getGradeResult());
                    }
                    if (DictCodeListExt3.评级来源类型_内部.getValue().equals(busdto.getInOrOut())) {
                        dto.setZxGrade(busdto.getGradeResult());
                    }

                }
            }

//            估值日<=交易日取最大 估值来源=中债登
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                if (dto.getValueDate() != null) {
                    Date valueDate = sdf.parse(dto.getValueDate());
                    Date maxDate = cfetsetrIbBondinfoMapper.getMaxTradeDate(secId);
                    if (maxDate != null) {
                        if (maxDate.compareTo(valueDate) <= 0 && dto.getValueSource().equals(DictCodeList.估值来源_中债登_CBC.getValue())){
                            dto.setValueDate(dto.getValueDate());
                        }else {
                            dto.setValueDate(" ");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
//            调整后的计算结束日>交易日取最小 现金流类型=利息
            try {
                if (dto.getLastPaydate() != null) {
                    Date lastPaydate = sdf.parse(dto.getLastPaydate());
                    Date minDate = cfetsetrIbBondinfoMapper.getMinTradeDate(secId);
                    if (minDate != null) {
                        if (minDate.compareTo(lastPaydate) > 0 && dto.getRateType().equals("02")) {
                            dto.setLastPaydate(dto.getLastPaydate());
                        }else {
                            dto.setLastPaydate(" ");
                        }
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
//            现金流类型=利息
//            if (dto.getRateType() != null && dto.getRateType().equals("02")) {
//                dto.setRateType(dto.getRateType());
//            }else {
//                dto.setRateType(" ");
//            }

            dto.setXqFlg(dto.getXqFlg().equals("Y")?"是":"否");
//
//            if (dto.getXqFlg().equals("Y")) {
//                dto.setXqFlg("是");
//            }else if (dto.getXqFlg().equals("N")) {
//                dto.setXqFlg("否");
//            }

////            若为含权债，显示为含权债的行权方式；若有多个行权方式，用分号“;”隔开
//            String xqfangshi = "";
//            List<String> plan = cfetsetrIbBondinfoMapper.getEtrFinExerciseInfo(secId);
//            if (plan != null && plan.size() > 1) {
//                xqfangshi = StringUtil.joinString(plan,";");
//                dto.setXqFlg(xqfangshi);
//            }
        }
        return dto;
    }
    public Page<SelectOptionDto> getfinprodIdForAdd(CfetsSearchInfoDto dto) {
        Map<String, Object> param =new HashMap<>();
        param.put("search",dto.getSearch());
        param.put("assestType", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        param.put("TRADE_PLACE_01",DictCodeListExt1.交易场所_银行间.getValue());
        param.put("TRADE_PLACE_05",DictCodeListExt1.交易场所_机构间私募系统.getValue());
        param.put("TRADE_PLACE_09",DictCodeListExt1.交易场所_OTC场外.getValue());
        param.put("TRADE_PLACE_10",DictCodeListExt1.交易场所_境外交易场所.getValue());
        return super.queryForPage(dto.getPageMode().getPageNo(), dto.getPageMode().getPageSize(), ()->cfetsEtrIbBondinfo002Mapper.getfinprodIdForAdd(param));
    }
    public Page<SelectOptionDto> selectPortfolioIdList(CfetsSearchInfoDto dto) {
        Map<String, Object> param =new HashMap<>();
        param.put("search",dto.getSearch());
        return super.queryForPage(dto.getPageMode().getPageNo(), dto.getPageMode().getPageSize(), ()->cfetsEtrIbBondinfo002Mapper.selectPortfolioIdList(param));
    }
}
