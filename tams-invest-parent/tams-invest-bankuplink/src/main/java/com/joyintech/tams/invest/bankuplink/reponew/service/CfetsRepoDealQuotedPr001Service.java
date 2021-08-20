package com.joyintech.tams.invest.bankuplink.reponew.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.dao.FinProductAddMapper;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.dao.PtlPortfolioMapper;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.component.business.trade.BussRepoSecService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.bankuplink.cstp.dto.CstpSelectDto;
import com.joyintech.tams.invest.bankuplink.repo.dto.CfetsRepoDealQuotedPrice001Dto;
import com.joyintech.tams.invest.bankuplink.repo.dto.CfetsRepoDealQuotedPrice003Dto;
import com.joyintech.tams.invest.bankuplink.repo.service.CfetsRepoDealQuotedPrice002Service;
import com.joyintech.tams.invest.bankuplink.repo.service.CfetsRepoDealQuotedPrice003Service;
import com.joyintech.tams.invest.bankuplink.repo.service.CfetsRepoDealQuotedPrice004Service;
import com.joyintech.tams.invest.bankuplink.reponew.dto.CfetsRepoDealQuotedPr001Dto;
import com.joyintech.tams.invest.bankuplink.reponew.dto.EtrFinProductCommonDto;
import com.joyintech.tams.invest.bankuplink.reponew.dto.EtrFinProductDto;
import com.joyintech.tams.invest.bankuplink.reponew.dto.RepoQuoteInfoDto;
import com.joyintech.tams.invest.common.component.base.group.BasePtlSecPositionInfoService;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.trade.dto.EtrSecPositionDto;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * CFETS质押式回购
 * 
 * @author chenjiaxing
 * @version 1.0 2021年8月7日
 * @see CfetsRepoDealQuotedPr001Service
 * @since 1.0
 */
@Service
public class CfetsRepoDealQuotedPr001Service extends CommonExportService<CfetsRepoDealQuotedPr001Dto> {
    /**
     * 日期相关
     */
    @Autowired
    private DateCalService dateCalService;
    @Autowired
    private PtlPortfolioMapper ptlPortfolioMapper;
    @Autowired(required = false)
    private BaseEtrCashPositionCommService baseEtrCashPositionCommService;
    @Autowired
    private FinProductMapper finProductMapper;
    @Autowired(required = false)
    private FinProductAddMapper finProductAddMapper;
    /**
     * {@linkplain}
     */
    @Autowired
    private IPlatformDate iPlatformDate;

    @Autowired
    private BasePtlSecPositionInfoService basePtlSecPositionInfoService;

    /**
     * 外汇交易中心行情接口地址
     */
    @Value("${MarketInfoInterf.cmdsUrl}")
    private String MarketInfoInterf_cmdsUrl;

    @Autowired(required = false)
    private CommonBusinessService commonBusinessService;
    /**
     * 根据查询条件查询委托列表
     *
     * @param param 入参
     * @return 委托列表
     * @see
     * @since 1.0
     */
    public List<CfetsRepoDealQuotedPr001Dto> queryZlzxList(Map<String, Object> param) {
        // 多选下拉框值处理
        param.put("platDate", iPlatformDate.getSysDateStr());
        param.put("loginUserId", LoginUserUtil.getUserName());
        // 投资经理
        Object invManagerIdObj = param.get("invManagerId_zlgl");
        if (invManagerIdObj != null) {
            param.put("invManagerIdList",
                    StringUtil.splitToList(GlobalConstant.COMMA, (String)invManagerIdObj));
        }
        else {
            param.put("invManagerIdList", new ArrayList<>());
        }
        // 组合代码
        Object portfolioIdObj = param.get("portfolioId_zlgl");
        if (portfolioIdObj != null) {
            param.put("portfolioIdList",
                    StringUtil.splitToList(GlobalConstant.COMMA, (String)portfolioIdObj));
            param.put("portfolioId",null);
        }
        else {
            param.put("portfolioIdList", new ArrayList<>());
        }

        // 资产代码
        Object assetIdObj = param.get("assetId_zlgl");
        if (assetIdObj != null) {
            param.put("assetIdList",
                    StringUtil.splitToList(GlobalConstant.COMMA, (String)assetIdObj));
            param.put("assetId",null);
        }
        else {
            param.put("assetIdList", new ArrayList<>());
        }
        List<CfetsRepoDealQuotedPr001Dto> list = restTemplate.postForList(
                ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsRepoDealQuotedPr001/queryZlzxList",
                param, CfetsRepoDealQuotedPr001Dto.class);
        return list;
    }

    /**
     * 根据查询条件查询委托列表
     *
     * @param param 入参
     * @return 委托列表
     * @see
     * @since 1.0
     */
    public List<CfetsRepoDealQuotedPr001Dto> queryZyqglList(Map<String, Object> param) {
        param.put("plateDate", iPlatformDate.getSysDateStr());
        param.put("userId", SecurityUtil.getLoginUser().getUserName());
        // 组合代码
        Object portfolioIdObj = param.get("portfolioId_zyqgl");
        if (portfolioIdObj != null) {
            param.put("portfolioIdList",
                    StringUtil.splitToList(GlobalConstant.COMMA, (String)portfolioIdObj));
            param.put("portfolioId",null);
        }
        else {
            param.put("portfolioIdList", new ArrayList<>());
        }
        if (StringUtil.isNotEmpty(param.get("secAcctId_zyqgl"))) {
            param.put("secAcctId", StringUtil.splitToList(",", (String)param.get("secAcctId_zyqgl")));
        }
        String keywords = "";
        if(param.containsKey("keywords")){
            keywords =(String) param.get("keywords");
            param.remove("keywords");
        }
        List<CfetsRepoDealQuotedPr001Dto> list = restTemplate.postForList(
                ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsRepoDealQuotedPr001/queryZyqglList",
                param, CfetsRepoDealQuotedPr001Dto.class);
        if(StringUtil.isNotEmpty(keywords)&&!StringUtil.isEmptyList(list)){
            list = (List<CfetsRepoDealQuotedPr001Dto>) getKeyWordsList(list,keywords,(String) param.get("keycols"));
        }
        return list;
    }

    /**
     * 现券报价查询-按指定条件分页查询<br />
     * 根据传入的不为空的变量进行查询，以分页形式返回查询结果，需支持数据权限
     *
     * @param param 入参
     * @return Page<BondQuoteInfoDto>
     */
    public List<RepoQuoteInfoDto> queryWtdhbjList(Map<String, Object> param) {
        User user = SecurityUtil.getLoginUser();
        if (user == null) {
            return Collections.emptyList();
        }
        param.put("loginUserId", user.getUserName());
        Map<String,Object> paramUser = new HashMap<>();
        paramUser.put("userId",user.getUserName());
        paramUser.put("rolePos","03");
        boolean traDirector = commonBusinessService.isTraDirector(paramUser);
        param.put("isAdmin", user.isAdmin()||traDirector);
        // 待回复报价是否所有交易员可见（01：不允许，02：允许）
        param.put("unAnswerFlag", CacheUtil.getSysParam("ibuFlag_allTraderCanShowUnAnswerQuote"));
        //是否获取质押券信息
        param.put("isGetBondCode", "Y");

        String keywords = "";
        if(param.containsKey("keywords")){
            keywords =(String) param.get("keywords");
            param.remove("keywords");
        }
        List<RepoQuoteInfoDto> list = restTemplate.postForList(
                ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsRepoDealQuotedPr001/queryWtdhbjList",
                param, RepoQuoteInfoDto.class);
        if(StringUtil.isNotEmpty(keywords)&&!StringUtil.isEmptyList(list)){
            list = (List<RepoQuoteInfoDto>) getKeyWordsList(list,keywords,(String) param.get("keycols"));
        }
        return list;
    }
    /**
     * 成交查询
     *
     * @param param 入参
     * @return 委托列表
     * @see
     * @since 1.0
     */
    public List<CstpSelectDto> gencjcxTableParam(Map<String, Object> param) {
        if(param.get("portfolioId") != null && StringUtil.isNotEmpty(param.get("portfolioId").toString())){
            String[] portfolioId = param.get("portfolioId").toString().split(",");
            param.replace("portfolioId", portfolioId);
        }else{
            param.replace("portfolioId", null);
        }
        if(param.get("matchStatus") != null && StringUtil.isNotEmpty(param.get("matchStatus").toString())){
            String[] matchStatus = param.get("matchStatus").toString().split(",");
            param.replace("matchStatus", matchStatus);
        }else{
            param.replace("matchStatus", null);
        }
        if(param.get("tradeDateBegin") != null && !param.get("tradeDateBegin").toString().isEmpty()){
            param.replace("tradeDateBegin",param.get("tradeDateBegin").toString().replace("-", ""));
            param.replace("tradeDateEnd",param.get("tradeDateEnd").toString().replace("-", ""));
        }else{
            param.replace("tradeDateBegin",null);
            param.replace("tradeDateEnd",null);
        }
        param.put("IS_CANCEL", BussinessDictCodeList.是否_否.getValue());
        param.put("MATCH", BussinessDictCodeList.匹配状态_已匹配.getValue());
        param.put("UNMATCH", BussinessDictCodeList.匹配状态_未匹配.getValue());
        String keywords = "";
        if(param.containsKey("keywords")){
            keywords =(String) param.get("keywords");
            param.remove("keywords");
        }
        param.put("loginUserId", LoginUserUtil.getUserName());
        List<CstpSelectDto>list = restTemplate.postForList(ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsRepoDealQuotedPr001/getCstpQryTableList",
                param, CstpSelectDto.class);
        if(StringUtil.isNotEmpty(keywords)&&!StringUtil.isEmptyList(list)){
            list = (List<CstpSelectDto>) getKeyWordsList(list,keywords,(String) param.get("keycols"));
        }
        // 质押式回购成交回报中质押券面总额合计字段改为质押券券面总额，字段单位调整为万元。
        if (!StringUtil.isEmptyList(list)) {
            for (CstpSelectDto cstpSelectDto : list) {
                cstpSelectDto.setLastqty(ArithmeticUtil.divide(cstpSelectDto.getLastqty(),
                        BigDecimal.valueOf(10000), 2));
            }
        }
        return list;
    }
    private List<?> getKeyWordsList(List<?> list,String keywords,String keycols){
        if(StringUtil.isEmptyList(list)){
            return list;
        }
        if(!StringUtil.isNotEmpty(keywords)){
            return list;
        }
        if(!StringUtil.isNotEmpty(keycols)){
            return list;
        }
        List <String> keycolList = StringUtil.splitToList(GlobalConstant.COMMA,keycols);
        List<Object> containsKeyList = new ArrayList<>();
        for(Object cstpSelectDto:list){
            Map<String, Object> map = JsonUtils.object2Map(cstpSelectDto);
            for(String col:keycolList){
                Object value = map.get(col);
                if(StringUtil.isNotEmpty(value)&&value.toString().contains(keywords)){
                    containsKeyList.add(cstpSelectDto);
                    break;
                }
            }
        }
        return containsKeyList;
    }
    /**
     * 查询质押式回购实时行情
     * @param pageParam
     * @return
     */
    public Map<String, Object> findQuotations(Map<String, Object> pageParam) {
        String termType = (String) pageParam.get("termType");
        String MarketType = (String) pageParam.get("MarketType");
        String url = MarketInfoInterf_cmdsUrl+"/rest/markinfo/getRepoMarkinfo?termType=" + termType + "&MarketType=" + MarketType;
        return getMarketInfo(url);
    }
    /**
     * 持仓信息
     * @param paramMap
     * @return
     */
    public EtrFinProductDto findPositionInfo(Map<String, Object> paramMap) {
        EtrFinProductDto dto = new EtrFinProductDto();
        //银行间日历
        paramMap.put("calId", "03");
        Map<String, Object> mapRst = getPortfolioAllocationValues(paramMap);
        dto.setZhkyxj_t(mapRst.get("proAvailableCash") == null ? BigDecimal.ZERO : (BigDecimal) mapRst.get("proAvailableCash"));
        dto.setZhkyxj_t1(mapRst.get("proAvailableCashT1") == null ? BigDecimal.ZERO : (BigDecimal) mapRst.get("proAvailableCashT1"));
        dto.setTghkyxj_t(mapRst.get("secManageAvailablePosition") == null ? BigDecimal.ZERO : (BigDecimal) mapRst.get("secManageAvailablePosition"));
        dto.setTghkyxj_t1(mapRst.get("secManageAvailablePositionT1") == null ? BigDecimal.ZERO : (BigDecimal) mapRst.get("secManageAvailablePositionT1"));
        dto.setTdkyxj_t(mapRst.get("channelAvailable") == null ? BigDecimal.ZERO : (BigDecimal) mapRst.get("channelAvailable"));
        dto.setTdkyxj_t1(mapRst.get("channelAvailableT1") == null ? BigDecimal.ZERO : (BigDecimal) mapRst.get("channelAvailableT1"));
        return dto;
    }
    public PtlPortfolio findPortfolio(String counterId) {
        return ptlPortfolioMapper.selectByPrimaryKey(counterId);
    }
    /**
     * 获取组合分配相关数据
     *
     * @param param
     * @return
     */
    public Map<String, Object> getPortfolioAllocationValues(Map<String, Object> param) {
        // 获取平台日
        Date tradeDate = DateUtil.platDateFromCache();
        // 交易日历
        String calId = (String) param.get("calId");
        Date tradeDateNext = DateUtil.addDay(tradeDate, 1);
        if (StringUtil.isNotEmpty(calId)) {
            List<String> holidayList = dateCalService.getDealCalendarHoliday(DateUtil.parse(DateUtil.format(tradeDate)), DateUtil.addDay(DateUtil.parse(DateUtil.format(tradeDate)), 15), calId);
            tradeDateNext = dateCalService.calculationTradingDay(DateUtil.parse(DateUtil.format(tradeDate)), 1, holidayList, "1");
        }
        Map<String, Object> result = new HashMap<>();

        if(!StringUtil.isNotEmpty(param.get("finprodId"))){
            /**
             * 当单选组合时，使用组合的币种
             */
            PtlPortfolio ptlPortfolio = findPortfolio(param.get("portfolioId") + "");
            if(ptlPortfolio != null && StringUtil.isNotEmpty(ptlPortfolio.getCcy())){
                param.put("ccy", ptlPortfolio.getCcy());
            }
        }
        // 组合信息没有币种 导致入参币种为空 取不到现金  现如果为空 默认人民币
        if(!StringUtil.isNotEmpty(param.get("ccy"))){
            param.put("ccy", DictCodeList.币种_人民币_CNY.getValue());
        }
        // 可用现金
        Map<String, Object> cash = getProAvailableCash(param);
        //组合T日可用现金
        result.put("proAvailableCash",cash.get("cashT"));
        //组合T+1日可用现金
        result.put("proAvailableCashT1", cash.get("cashT1"));
        //托管户T日可用现金
        result.put("secManageAvailablePosition",cash.get("cashT3"));
        //托管户T+1日可用现金
        result.put("secManageAvailablePositionT1", cash.get("cashTT1"));
        //通道可用现金
        if (StringUtil.isNotEmpty(param.get("chlId"))) {
            //通道T日可用现金
            result.put("channelAvailable", cash.get("cashT2"));
            //通道T+1日可用现金
            result.put("channelAvailableT1",cash.get("cashT21"));
        }
        if (StringUtil.isNotEmpty((String)param.get("finprodId"))) {
            // 资产可用券面总额
            EtrSecPositionDto etrSecPositionDto= new EtrSecPositionDto();
            etrSecPositionDto.setCdate(tradeDate);
            etrSecPositionDto.setFinprodId((String)param.get("finprodId"));
            etrSecPositionDto.setPortfolioId((String)param.get("portfolioId"));

            etrSecPositionDto.setCcy(DictCodeList.币种_人民币_CNY.getValue());

            etrSecPositionDto.setHodingType(DictCodeListExt1.持仓类型_委托指令可用.getValue());
            result.put("assetAvailableEntrustAmt", getAssetAvailablePosition(etrSecPositionDto).divide(new BigDecimal(100)));
            // 资产持仓券面总额
            PtlSecPositionFlowDto ptlSecPositionFlowDto = new PtlSecPositionFlowDto();
            ptlSecPositionFlowDto.setCdate(tradeDate);
            ptlSecPositionFlowDto.setPortfolioId((String)param.get("portfolioId"));
//            if (StringUtil.isBlank(ptlSecPositionFlowDto.getPortfolioId())) {
//                result.put("parentPortfolioId",etrCommonService.selectParentPortfolioIdByPortfolioId(
//                        ptlSecPositionFlowDto.getPortfolioId()));
//            }
            ptlSecPositionFlowDto.setCcy(DictCodeList.币种_人民币_CNY.getValue());
            ptlSecPositionFlowDto.setHodingType(DictCodeList.持仓类型_实际.getValue());
            ptlSecPositionFlowDto.setFinprodId((String)param.get("finprodId"));
            ptlSecPositionFlowDto.setEntrust(true);
            BigDecimal amount03 = basePtlSecPositionInfoService.queryOtherSecPosition(ptlSecPositionFlowDto).divide(new BigDecimal(100));
            ptlSecPositionFlowDto.setHodingType(DictCodeList.持仓类型_融入.getValue());
            BigDecimal amount05 = basePtlSecPositionInfoService.queryOtherSecPosition(ptlSecPositionFlowDto).divide(new BigDecimal(100));
            ptlSecPositionFlowDto.setHodingType(DictCodeList.持仓类型_融出.getValue());
            BigDecimal amount06 = basePtlSecPositionInfoService.queryOtherSecPosition(ptlSecPositionFlowDto).divide(new BigDecimal(100));
            BigDecimal assetPositionEntrustAmt = ArithmeticUtil.doAdd(amount03, amount05);
            assetPositionEntrustAmt = ArithmeticUtil.subtract(assetPositionEntrustAmt, amount06);
            result.put("assetPositionEntrustAmt", assetPositionEntrustAmt);
        } else {
            result.put("assetAvailableEntrustAmt", BigDecimal.ZERO);
            result.put("assetPositionEntrustAmt", BigDecimal.ZERO);
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
     * 组合指令可用现金查询
     *
     * @param param
     * @return cashT 组合T日可用, cashT1 组合T+1日可用, cashT3 托管户可用, cashT2 通道可用， cashT4 交易所资金账户可用
     * @see
     * @since 1.0
     */
    public Map<String, Object> getProAvailableCash(Map<String, Object> param) {
        PtlCashPositionFlowDto dto = new PtlCashPositionFlowDto();
        Date sysdate = DateUtil.parse(DateUtil.getSysDate());
        if (StringUtil.isNotEmpty(param.get("acctId"))){
            dto.setAcctId((String)param.get("acctId"));
        }
        if (StringUtil.isNotEmpty(param.get("finprodId"))){
            FinProduct finProduct = finProductMapper.selectByPrimaryKey((String)param.get("finprodId"));
            List<String> holidayList = new ArrayList<>();
            if (Objects.nonNull(finProduct)) {
                holidayList = dateCalService.getDealCalendarHoliday(sysdate,DateUtil.addDay(sysdate, 15),finProduct.getCalendarId());
            }
            Date tradeDay = dateCalService.calculationTradingDay(DateUtil.parse(DateUtil.format(sysdate).toString()), 1, holidayList, "1");
            param.put("sysDateT1", DateUtil.format(tradeDay));
        }else {
            String calId = (String)param.get("calId");
            Date vdate = DateUtil.parse((String)param.get("vdate"));
            if(StringUtil.isNotEmpty(calId)&&vdate!=null){
                List<String> holidayList = dateCalService.getDealCalendarHoliday(DateUtil.parse(DateUtil.format(vdate)),DateUtil.addDay(DateUtil.parse(DateUtil.format(vdate)), 15),calId);
                Date tradeDay = dateCalService.calculationTradingDay(DateUtil.parse(DateUtil.format(vdate)), 1, holidayList, "1");
                param.put("sysDateT1",DateUtil.format(tradeDay));
            }
        }

        dto.setCdate(sysdate);
        List<String> acctTypeList = new ArrayList<>();
        acctTypeList.add(DictCodeListExt1.资金账户类型_产品托管户.getValue());
        dto.setPortfolioId((String)param.get("portfolioId"));
        dto.setCcy((String)param.get("ccy"));
        Map<String, Object> result = new HashMap<>();
        dto.setAcctType(acctTypeList);

        if (baseEtrCashPositionCommService != null) {
            BigDecimal cashT = BigDecimal.ZERO;
            //期货保证金单独处理
            if(param.containsKey("acctType")&&StringUtil.equals(param.get("acctType"),DictCodeListExt1.资金账户类型_期货资金账户.getValue())){
                acctTypeList.clear();
                acctTypeList.add(DictCodeListExt1.资金账户类型_期货资金账户.getValue());
                BigDecimal cashQH = baseEtrCashPositionCommService.qryCommandAvailableCash(dto);
                cashT = ArithmeticUtil.doAdd(cashT, cashQH == null ? BigDecimal.ZERO : cashQH);
            }else{
                BigDecimal cashT3 = baseEtrCashPositionCommService.qryCommandAvailableCash(dto);
                result.put("cashT3", cashT3); // 托管户可用
                cashT = ArithmeticUtil.doAdd(cashT, cashT3 == null ? BigDecimal.ZERO : cashT3);

                acctTypeList.clear();
                acctTypeList.add(DictCodeListExt1.资金账户类型_券商资金账户.getValue());
                BigDecimal cashT4 = baseEtrCashPositionCommService.qryCommandAvailableCash(dto);
                result.put("cashT4", cashT4);// 交易所资金账户可用
                cashT = ArithmeticUtil.doAdd(cashT, cashT4 == null ? BigDecimal.ZERO : cashT4);
            }

            if (StringUtil.isNotEmpty(param.get("chlFinprodId"))) {
                FinProductAdd finProductAdd = finProductAddMapper.selectByPrimaryKey((String)param.get("chlFinprodId"));
                result.put("payType", "02");
                if (finProductAdd != null) {
                    result.put("payType", finProductAdd.getPayType());
                }
                dto.setAcctType(null);
                dto.setChlFinprodId((String)param.get("chlFinprodId"));
                BigDecimal cashT2 = baseEtrCashPositionCommService.qryCommandAvailableCash(dto);
                result.put("cashT2", cashT2 == null ? BigDecimal.ZERO : cashT2);// 通道可用
                //组合T日可用现金不加通道可用现金
                //cashT = ArithmeticUtil.doAdd(cashT, cashT2);
            }
            result.put("cashT", cashT);// 组合T日可用

            if (StringUtil.isNotEmpty(param.get("sysDateT1"))) {
                PtlCashPositionFlowDto dto2 = new PtlCashPositionFlowDto();
                Date sysdateT1 = DateUtil.parse((String)param.get("sysDateT1"));
                dto2.setCdate(sysdateT1);
                acctTypeList.clear();
                acctTypeList.add(DictCodeListExt1.资金账户类型_券商资金账户.getValue());
                acctTypeList.add(DictCodeListExt1.资金账户类型_产品托管户.getValue());
                dto2.setAcctType(acctTypeList);
                dto2.setPortfolioId((String)param.get("portfolioId"));
                dto2.setCcy((String)param.get("ccy"));
                BigDecimal cashT1 = baseEtrCashPositionCommService.qryCommandAvailableCash(dto2);
                if (StringUtil.isNotEmpty(param.get("chlFinprodId"))) {
                    FinProductAdd finProductAdd = finProductAddMapper.selectByPrimaryKey((String)param.get("chlFinprodId"));
                    result.put("payType", "02");
                    if (finProductAdd != null) {
                        result.put("payType", finProductAdd.getPayType());
                    }
                    dto2.setAcctType(null);
                    //组合T日可用现金不加通道可用现金
                    dto2.setChlFinprodId((String)param.get("chlFinprodId"));
                    BigDecimal cashT2 = baseEtrCashPositionCommService.qryCommandAvailableCash(dto2);
                    result.put("cashT21", cashT2 == null ? BigDecimal.ZERO : cashT2);// 通道T+1可用
                }
                result.put("cashT1", cashT1 == null ? result.get("cashT") : cashT1);// 组合T+1日可用

                acctTypeList.clear();
                acctTypeList.add(DictCodeListExt1.资金账户类型_产品托管户.getValue());
                BigDecimal cashTT1 = baseEtrCashPositionCommService.qryCommandAvailableCash(dto2);//组合托管户T+1可用
                result.put("cashTT1", cashTT1 == null ? BigDecimal.ZERO : cashTT1);// 组合托管户T+1可用

            }
        }
        return result;
    }

    public BigDecimal getAssetAvailablePosition(EtrSecPositionDto dto) {
        if (baseEtrCashPositionCommService != null) {
            dto.setHodingType(DictCodeListExt1.持仓类型_委托指令可用.getValue());
            return baseEtrCashPositionCommService.qryAvailablePosition(dto);
        }
        return null;
    }

    /**
     * 组合现金信息
     * @param paramMap
     * @return
     */
    public EtrFinProductCommonDto findCashAmountInfo(Map<String, Object> paramMap) {
        EtrFinProductCommonDto dto = new EtrFinProductCommonDto();

        Map<String, Object> result = new HashMap<>();
        /**
         * 当单选组合时，使用组合的币种
         */
        PtlPortfolio ptlPortfolio = ptlPortfolioMapper.selectByPrimaryKey(paramMap.get("portfolioId") + "");
        if(ptlPortfolio != null && StringUtil.isNotEmpty(ptlPortfolio.getCcy())){
            paramMap.put("ccy", ptlPortfolio.getCcy());
        }
        // 可用现金
        Map<String, Object> cash = this.getProAvailableCash(paramMap);
        //组合T日可用现金
        result.put("proAvailableCash",cash.get("cashT"));
        //组合T+1日可用现金
        result.put("proAvailableCashT1", cash.get("cashT1"));
        //托管户T日可用现金
        result.put("secManageAvailablePosition",cash.get("cashT3"));
        //托管户T+1日可用现金
        result.put("secManageAvailablePositionT1", cash.get("cashTT1"));

        //通道可用现金
        if (StringUtil.isNotEmpty(paramMap.get("chlId"))) {
            //通道T日可用现金
            result.put("channelAvailable", cash.get("cashT2"));
            //通道T+1日可用现金
            result.put("channelAvailableT1",cash.get("cashT21"));
        }
        dto.setT1Cash(result.get("proAvailableCashT1") == null ? BigDecimal.ZERO : (BigDecimal) result.get("proAvailableCashT1"));
        dto.setTgAcctCash(result.get("secManageAvailablePosition") == null ? BigDecimal.ZERO : (BigDecimal) result.get("secManageAvailablePosition"));
        dto.setSecAcctCash(cash.get("cashT4") == null ? BigDecimal.ZERO : (BigDecimal) cash.get("cashT4"));
        return dto;
    }

    /**
     * 回购信息
     * @param paramMap
     * @return
     */
    public EtrFinProductDto findhginfo(Map<String, Object> paramMap) {
        EtrFinProductDto dto = restTemplate.postForObject(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIbRepo001/getEtrFinProductInfo", paramMap,
                EtrFinProductDto.class);
        return null;
    }

    /**
     * 根据组合代码查询证券托管户
     * @param map
     * @return
     */
    public List<SelectOptionDto> getSecAcctId(Map<String, Object> map) {
        List<SuggestDto> tempList = restTemplate.postForList(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrNewStock/getSecAcctId",map, SuggestDto.class);
        List<SelectOptionDto> resultList = new ArrayList<SelectOptionDto>();
        if (!StringUtil.isEmptyList(tempList)) {
            for (SuggestDto suggestDto : tempList) {
                SelectOptionDto selectOptionDto = new SelectOptionDto();
                selectOptionDto.setId(suggestDto.getCode());
                selectOptionDto.setText(suggestDto.getName());
                if (StringUtil.isNotBlank((String) map.get("portfolioId"))) {
                    selectOptionDto.setParentId((String) map.get("portfolioId"));
                }
                resultList.add(selectOptionDto);
            }
        }

        return resultList;
    }
}
