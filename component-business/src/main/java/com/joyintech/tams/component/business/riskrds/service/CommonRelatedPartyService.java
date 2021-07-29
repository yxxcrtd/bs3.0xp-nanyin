/*
 * 文件名：CommonRelatedPartyService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年10月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.riskrds.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.joyintech.tams.systemtools.interf.IPlatformDate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.framework.base.BaseDictCodeList;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.dao.FinProductAddMapper;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.FinProductTypeKey;
import com.joyintech.tams.common.base.entity.RskRelatedParty;
import com.joyintech.tams.component.business.riskrds.constant.InterfUtil;
import com.joyintech.tams.component.business.riskrds.constant.PageFieldToInterfFactory;
import com.joyintech.tams.component.business.riskrds.dto.InterfRiskRdsDto;
import com.joyintech.tams.component.business.riskrds.dto.TradeBaseDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.redisinit.dto.IRiskRds;
import com.joyintech.tams.invest.common.riskinfrds.util.service.EntrustCompleteService;
import com.joyintech.tams.invest.common.trade.CreditLinesUpdate;
import com.joyintech.tams.invest.common.trade.CreditLinesUpdateImpl;
import com.joyintech.tams.invest.common.trade.dao.CreditLinesUpdateMapper;

/**
 * 取得关联方信息
 * 
 * @author zhangrui1
 * @version 1.0 2020年10月15日
 * @see CommonRelatedPartyService
 * @since 1.0
 */
@Service
public class CommonRelatedPartyService {

    public static final String RELATEDPARTY = "relatedParty";

    public static final String ISRELATEDPARTY = "isRelatedParty";

    public static final String ISRELATEDPARTYCEORG = "isRelatedPartyCeOrg";

    public static final String RELATEDPARTYAMT = "relatedPartyAmt";

    public static final BigDecimal NUM_100 = new BigDecimal(100);

    public static final String[] IGNORE_PS = {DictCodeList.交易方向_收息.getValue(), DictCodeList.交易方向_现金分红.getValue(), DictCodeList.交易方向_除权登记.getValue(),
                                                DictCodeList.回购拆借方向_拆入.getValue(),DictCodeList.回购拆借方向_正回购.getValue(),DictCodeList.回购拆借方向_融入.getValue()};

    private CommonRelatedPartyService() {
        // private the construct
    }

    /**
     * 取平台日
     * 
     * @return 平台日
     */
    public static final Date getSysDate() {
        IPlatformDate service = SpringContextUtil.getApplicationContext().getBean(
                IPlatformDate.class);
        return service.getSysDate();
    }

    /*public static final Map<String, String> getRelatedParty(Collection<String> arg) {
        if (CollectionUtils.isEmpty(arg)) {
            return new HashMap<>();
        }
        return getRelatedParty(arg.toArray(new String[arg.size()]));
    }*/

    public static final Map<String, String> getRelatedParty(List<Map<String, Object>> paramList) {
        if (paramList == null || paramList.size() == 0) {
            return new HashMap<>();
        }
        // 调用关联方管理服务查询关联方
        List<RskRelatedParty> resList = queryInfoByPrimaryKey(paramList);
        if (CollectionUtils.isEmpty(resList)) {
            return new HashMap<>();
        }
        // 抽出信息中的社会统一信用代码
        return resList.stream().collect(Collectors.toMap(RskRelatedParty::getInstituteName,
            RskRelatedParty::getInstituteCode));
    }

    public static final List<RskRelatedParty> queryInfoByPrimaryKey(List<Map<String, Object>> paramList) {
        List<RskRelatedParty> resList = new ArrayList<>();
        // 分批次查询，每次20条
        CommonUtil.doBatchList(paramList, 20, list -> {
            // 调用关联方管理服务查询关联方
            List<RskRelatedParty> infoList = joyinRestTemplate().postForList(
                ServiceApplication.RISK_SERVICE_NAME + "/RskRelatedParty/queryInfoByPrimaryKeyForRisk",
                list, RskRelatedParty.class);
            if (!CollectionUtils.isEmpty(infoList)) {
                resList.addAll(infoList);
            }
        });
        return resList;
    }

    public static final JoyinRestTemplate joyinRestTemplate() {
        return SpringContextUtil.getApplicationContext().getBean(
            EntrustCompleteService.class).getJoyinRestTemplate();
    }

    /**
     * 机构授信交易标识
     */
    public static final String CREDIT_KEY = "creditKey";

    /**
     * 机构授信目标机构
     */
    public static final String CREDIT_ORG = "customerId";

    /**
     * 机构授信目标额度
     */
    public static final String CREDIT_AMT = "amount";

    /**
     * 计算机构授信额度信息
     * 
     * @param data 交易信息
     * @return 机构授信额度信息
     */
    public static final void doCredit(Map<String, Object> data, IRiskRds base) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        if (data != null) {
            dataList.add(data);
        }
        doCredit(dataList, base);
    }

    /**
     * 计算机构授信额度信息
     * 
     * @param data 交易信息
     * @param base 场景：委托/交易/存续期
     * @return 机构授信额度信息
     */
    public static final void doCredit(List<Map<String, Object>> data, IRiskRds base) {
        // 取得所有交易对手
        Set<String> orgSet = collectCounterId(data);
        // 授信接口
        CreditLinesUpdate creditLinesUpdate = SpringContextUtil.getBean(
            CreditLinesUpdateImpl.class);
        // 计算机构授信
        List<Map<String, Object>> creditList = creditLinesUpdate.calcRelatedOccupyAmt(
            arrayToList(data, base));
        // 机构授信存在，取出所有授信机构
        if (!CollectionUtils.isEmpty(creditList)) {
            creditList.forEach(credit -> orgSet.add((String)credit.get(CREDIT_ORG)));
        }
        //获取所有交易对手以及相应的交易时间
        List<Map<String, Object>> queryOrgset=getCounterIdAndTradeDate(data,base);
        // 机构授信存在，取出所有授信机构
        if(!CollectionUtils.isEmpty(creditList)){
        	for(Map<String, Object> map:creditList){
        		Map<String, Object> mapResult=new HashMap<String, Object>();
                Object customerId = CommonUtil.findValue(map,CREDIT_ORG);
                if (customerId != null) {
                	mapResult.put("customerId", customerId);
                }
                Object queryDate = CommonUtil.findValue(map, "tradeDate");
                if (queryDate != null) {
                	if(queryDate instanceof Long){
                		mapResult.put("queryDate", DateUtil.format(new Date(Long.parseLong(queryDate.toString()))));
                	}else{
                		mapResult.put("queryDate", queryDate);
                	}
                }
                queryOrgset.add(mapResult);
        	}
        }
        // 取得机构中得关联方
        Map<String, String> relatedPartyList = getRelatedParty(queryOrgset);
        // 处理关联方相关信息
        doRelatedParty(data, creditList, relatedPartyList);
    }

    /**
     * 处理关联方相关信息
     * 
     * @param dataList 报文
     * @param creditList 机构授信信息
     * @param relatedPartyList 关联方列表
     */
    private static final void doRelatedParty(List<Map<String, Object>> dataList,
                                             List<Map<String, Object>> creditList,
                                             Map<String, String> relatedPartyList) {
        if (!CollectionUtils.isEmpty(dataList)) {
            // 循环处理每笔报文
            dataList.forEach(data -> pickCredit(data, creditList, relatedPartyList));
        }
    }

    /**
     * 判断交易对手是否是关联方
     * 
     * @param data 报文
     * @param relatedPartyList 关联方列表
     */
    private static final void pickRelatedParty(Map<String, Object> data,
                                               Map<String, String> relatedPartyList) {
        Object obj = CommonUtil.findValue(data, PageFieldToInterfFactory.INF_COUNTERID);
        if (containsOrg(relatedPartyList, obj)) {
            data.put(RELATEDPARTY, relatedPartyList.get(obj));
            data.put(ISRELATEDPARTY, BussinessDictCodeList.是否_是.getValue());
        }
    }

    /**
     * 判断机构是否是关联方
     * 
     * @param relatedPartyList 关联方列表
     * @param obj 机构
     * @return true : 机构是关联方
     */
    private static boolean containsOrg(Map<String, String> relatedPartyList, Object obj) {
        return relatedPartyList != null && relatedPartyList.containsKey(obj);
    }

    /**
     * 设置关联方相关信息
     * 
     * @param data 报文体
     * @param creditList 机构授信信息
     * @param relatedPartyList 关联方列表
     */
    private static final void pickCredit(Map<String, Object> data,
                                         List<Map<String, Object>> creditList,
                                         Map<String, String> relatedPartyList) {
        Object creditKey = data.get(CREDIT_KEY);
        // 设置交易对手关联方信息
        pickRelatedParty(data, relatedPartyList);
        if (CollectionUtils.isEmpty(creditList)) {
            return;
        }
        // 债转股、基金转换等两笔交易使用交易方向区分
        String ps = creditKey != null
                    && StringUtil.length(creditKey.toString()) > 32 ? StringUtil.substring(
            creditKey.toString(), 32) : "";
        // 设置关联方机构授信信息
        for (Map<String, Object> credit : creditList) {
            if (!StringUtil.startsWith((String)credit.get(CREDIT_KEY), (String)creditKey)
                || !containsOrg(relatedPartyList, credit.get(CREDIT_ORG))) {
                continue;
            }
            // 如果授信机构是关联方
            Object obj = credit.get(CREDIT_AMT);
            // 取绝对值
            if (StringUtil.isNotEmpty(obj)) {
                data.put(RELATEDPARTYAMT + ps, new BigDecimal(obj.toString()).abs());
                data.put(ISRELATEDPARTYCEORG + ps, BussinessDictCodeList.是否_是.getValue());
            }
        }
    }

    /**
     * 收集所有报文得交易对手
     * 
     * @param data 报文列表
     * @return 交易对手列表
     */
    public static final Set<String> collectCounterId(List<Map<String, Object>> data) {
        Set<String> relatedPartySet = new HashSet<>();
        if (!CollectionUtils.isEmpty(data)) {
            for (Map<String, Object> map : data) {
                Object obj = CommonUtil.findValue(map, PageFieldToInterfFactory.INF_COUNTERID);
                if (obj != null) {
                    relatedPartySet.add(obj.toString());
                }
            }
        }
        return relatedPartySet;
    }
    public static final List<Map<String, Object>> getCounterIdAndTradeDate(List<Map<String, Object>> data, IRiskRds base) {
        List<Map<String, Object>> list=new ArrayList<>();
        if (!CollectionUtils.isEmpty(data)) {
            for (Map<String, Object> map : data) {
            	Map<String, Object> mapResult=new HashMap<String, Object>();
                Object customerId = CommonUtil.findValue(map, PageFieldToInterfFactory.INF_COUNTERID);
                if (customerId != null) {
                	mapResult.put("customerId", customerId);
                }
                Object queryDate = CommonUtil.findValue(map, getTradeDateKey(map,base));
                if (queryDate != null) {
                	if(queryDate instanceof Long){
                		mapResult.put("queryDate", DateUtil.format(new Date(Long.parseLong(queryDate.toString()))));
                	}else{
                		mapResult.put("queryDate", queryDate);
                	}
                }
                list.add(mapResult);
            }
        }
        return list;
    }

    /**
     * 处理交易信息
     * 
     * @param data 交易信息数组
     * @param base 场景：委托/交易/存续期
     * @return 交易信息列表
     */
    private static List<Map<String, Object>> arrayToList(List<Map<String, Object>> data,
                                                         IRiskRds base) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Set<String> assetSet = new HashSet<>();
        Map<String, BigDecimal> netPriceMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(data)) {
            data.forEach(map -> {
                if (isAsset(base, DictCodeList.平安资产类型_债券)
                    || isAsset(base, DictCodeList.平安资产类型_基金)
                    || isAsset(base, DictCodeList.平安资产类型_计划类资产_净值型)
                    || isAsset(base, DictCodeList.平安资产类型_计划类资产_货币型)
                    || isAsset(base, DictCodeListExt3.平安资产类型_权益类资产)
                    || isAsset(map, DictCodeListExt1.金融产品类型_投管用_债券)
                    || isAsset(map, DictCodeListExt1.金融产品类型_投管用_基金)) {
                    Object finprodId = map.get(InterfUtil.finprodId);
                    if (StringUtil.isNotEmpty(finprodId) && map.get("netPrice") != null && !isAsset(base, DictCodeList.平安资产类型_债券)) {
                        netPriceMap.put(finprodId.toString(),new BigDecimal(map.get("netPrice").toString()));
                    }
                    if (StringUtil.isNotEmpty(finprodId) && (map.get("netPrice") == null || isAsset(base, DictCodeList.平安资产类型_债券))) {
                        assetSet.add((String)finprodId);
                    }
                }
                try {
                    TradeBaseDto finDto = (TradeBaseDto) JsonUtils.map2entity(map,TradeBaseDto.class);
                    map.put("tradeId",StringUtil.isNotEmpty(finDto.getTotleTradeId())?finDto.getTotleTradeId():StringUtil.isNotEmpty(finDto.getTradeId())?finDto.getTradeId():finDto.getBusiCode());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            netPriceMap.putAll(getNetPrice(new ArrayList<>(assetSet)));
            for (Map<String, Object> map : data) {
                Object ps = CommonUtil.findValue(map,"ps");
                Object pingAnCodeSin = CommonUtil.findValue(map,"pingAnCodeSin");
                if (ps != null && Arrays.asList(IGNORE_PS).contains(ps.toString()) || pingAnCodeSin != null && Arrays.asList(IGNORE_PS).contains(pingAnCodeSin.toString())){
                    continue;
                }
                mapList.addAll(doReverse(map, base, netPriceMap));
            }
        }
        return mapList;
    }

    protected static Map<String, BigDecimal> getNetPrice(List<String> assetSet) {
        if (CollectionUtils.isEmpty(assetSet)) {
            return new HashMap<>();
        }
        Date sysDate = getSysDate();
        CreditLinesUpdateMapper mapper = SpringContextUtil.getApplicationContext().getBean(
                CreditLinesUpdateMapper.class);
        // 查询债券可用面值|基金净值等
        List<Map<String, Object>> resList = mapper.selectNetPrice(assetSet, sysDate);
        Map<String, BigDecimal> resMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(resList)) {
            resList.forEach(map -> {
                Object val = map.get("VAL");
                Object key = map.get("KEY");
                if (StringUtil.isNotEmpty(key) && StringUtil.isNotEmpty(val)) {
                    resMap.put(key.toString(), new BigDecimal(val.toString()));
                }
            });
        }
        return resMap;
    }

    private static final List<Map<String, Object>> doReverse(Map<String, Object> map,
                                                             IRiskRds base,
                                                             Map<String, BigDecimal> netPriceMap) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        String creditKey = IDGenerator.generate(32);
        String ps = (String)map.get(InterfUtil.ps);
        map.put(CREDIT_KEY, creditKey);
        if (CommonUtil.isType(ps, DictCodeListExt1.交易方向_债转股)) {
            // 债转股需要生成反向交易信息
            // 卖出方向-债券卖出
            Map<String, Object> sellMap = new HashMap<>();
            // 设置元数据
            sellMap.putAll(map);
            sellMap.put(CREDIT_KEY, creditKey + BussinessDictCodeList.交易方向_卖出.getValue());
            sellMap.put(PageFieldToInterfFactory.INF_PS, BussinessDictCodeList.交易方向_卖出.getValue());
            mapList.add(doAny(sellMap, base, netPriceMap));
            // 买入方向-股票买入
            Map<String, Object> buyMap = new HashMap<>();
            // 设置元数据
            buyMap.putAll(map);
            buyMap.put(CREDIT_KEY, creditKey + BussinessDictCodeList.交易方向_买入.getValue());
            buyMap.put(PageFieldToInterfFactory.INF_PS, BussinessDictCodeList.交易方向_买入.getValue());
            buyMap.put(InterfUtil.finprodId, map.get("shareFinprodId"));
            IRiskRds newBase = new InterfRiskRdsDto();
            newBase.setAssetType(DictCodeListExt2.平安资产类型_股票.getValue());
            newBase.setEventType(base.getEventType());
            newBase.setEventStatus(base.getEventStatus());
            newBase.setInfCode(base.getInfCode());
            mapList.add(doAny(buyMap, newBase, netPriceMap));
        }
        else if (CommonUtil.isType(ps, DictCodeListExt2.交易方向_转换申请)
                 || CommonUtil.isType(ps, DictCodeListExt2.交易方向_转换赎回申请)) {
            // 基金转换等需要生成反向交易信息
            Map<String, Object> sellMap = new HashMap<>();
            // 设置元数据
            sellMap.putAll(map);
            sellMap.put(CREDIT_KEY, creditKey + BussinessDictCodeList.交易方向_卖出.getValue());
            mapList.add(doAny(sellMap, base, netPriceMap));
            // 买入方向-股票买入
            Map<String, Object> buyMap = new HashMap<>();
            // 设置元数据
            buyMap.putAll(map);
            buyMap.put(CREDIT_KEY, creditKey + BussinessDictCodeList.交易方向_买入.getValue());
            buyMap.put(InterfUtil.finprodId, map.get("changeToFinprodId"));
            mapList.add(doAny(buyMap, base, netPriceMap));
        }
        else {
            mapList.add(doAny(map, base, netPriceMap));
        }
        return mapList;
    }

    protected static Map<String, Object> doAny(Map<String, Object> map, IRiskRds base,
                                               Map<String, BigDecimal> netPriceMap) {
        BigDecimal value = netPriceMap.get(map.get(InterfUtil.finprodId));
        Map<String, Object> creditMap = new CreditMap() //
                .copyFromMap(CREDIT_KEY, map) // 主键
                .copyTradeId(map)
                .copyEntrustId(map)
                .copyFinprodId(map) // 资产代码
                .copyCounterId(map) // 交易对手
                .copyTgBusiType(map)
                .copyPsPayment(map)
                .copySaleCode(map)
                .copyTradeDate(map, getTradeDateKey(map, base)) // 设置交易日期
                .putTradeAmt(getTradeAmt(map, base, value)) // 交易金额
                .putShareAmt(map)
                .putNetPrice(value)
                .setTradeStatus(getTradeStatus(base)) // 交易状态
                .copyPs(map) // 交易方向
                .copyCreditCheckType(map)
                .toMap();
        Object finprodType2 = map.get(InterfUtil.finprodType2);
        if ((isAsset(base, DictCodeList.平安资产类型_质押式回购) || StringUtil.equals(finprodType2, DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue()))){
            creditMap.put(CreditMap.KEY_FINPRODID,"");
            creditMap.put("tgBusiType",DictCodeList.平安资产类型_质押式回购.getValue());
            creditMap.put("psPayment",DictCodeList.回购类型_质押式协议回购.getValue());
            creditMap.put("saleCode",DictCodeList.回购拆借方向_逆回购.getValue());
        }else if ((isAsset(base, DictCodeList.平安资产类型_买断式回购) || StringUtil.equals(finprodType2, DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue()))){
            creditMap.put(CreditMap.KEY_FINPRODID,"");
            creditMap.put("tgBusiType",DictCodeList.平安资产类型_买断式回购.getValue());
            creditMap.put("psPayment",DictCodeList.回购拆借方向_逆回购.getValue());
        }else if ((isAsset(base, DictCodeList.平安资产类型_拆借) || StringUtil.equals(finprodType2, DictCodeListExt1.金融产品类型_投管用_拆出.getValue()))){
            creditMap.put(CreditMap.KEY_FINPRODID,"");
            creditMap.put("tgBusiType",DictCodeList.平安资产类型_拆借.getValue());
            creditMap.put("psPayment",DictCodeList.回购拆借方向_拆出.getValue());
        }else if ((isAsset(base, DictCodeListExt2.平安资产类型_债券借贷) || StringUtil.equals(finprodType2, DictCodeListExt1.金融产品类型_投管用_债券借贷融出.getValue()))){
            creditMap.put(CreditMap.KEY_FINPRODID,"");
            creditMap.put("tgBusiType",DictCodeListExt2.平安资产类型_债券借贷.getValue());
            creditMap.put("psPayment",DictCodeList.回购拆借方向_融出.getValue());
        }
        return creditMap;
    }

    /**
     * 判断交易状态
     * 
     * @param base 场景：委托/交易/存续期
     * @return 交易状态
     */
    protected static String getTradeStatus(IRiskRds base) {
        return CommonUtil.isType(base.getInfCode(), BussinessDictCodeListRds.接口系统ID_实占释放批量接口)
            || CommonUtil.isType(base.getInfCode(), BussinessDictCodeListRds.接口系统ID_预占释放批量接口)
            || CommonUtil.isType(base.getInfCode(), BussinessDictCodeListRds.接口系统ID_实占释放接口)
            || CommonUtil.isType(base.getInfCode(), BussinessDictCodeListRds.接口系统ID_预占释放接口)
            ? BussinessDictCodeList.交易状态_撤销.getValue() 
            : BussinessDictCodeList.交易状态_初始.getValue();
    }

    /**
     * 取金额
     * 
     * @param map 画面内容
     * @param base 场景
     * @param value 票面|净值等
     * @return 金额
     */
    protected static BigDecimal getTradeAmt(Map<String, Object> map, IRiskRds base,
                                            BigDecimal value) {
        if (isEventType(base, BussinessDictCodeListRds.事件类型_投资交易)) {
            // 存续期
            if (StringUtil.equals(base.getEventStatus(),
                BussinessDictCodeListRds.事件类型_投资交易_存续期.getValue())) {
                return calcPeriodAmt(map, base);
            }
            // 交易
            return calcTradeAmt(map, base, value);
        }
        else {
            // 判断是否来自场内交易指令
            if (isExchange(map)) {
                return calcExchangeAmt(map, base, value);
            }
            // 委托
            else {
                return calcEntrustAmt(map, base, value);
            }
        }
    }

    /**
     * 取得机构授信参数交易日期关键字
     * 
     * @param map 画面内容
     * @param base 场景
     * @return 交易日期KEY
     */
    protected static String getTradeDateKey(Map<String, Object> map, IRiskRds base) {
        if (!isEventType(base, BussinessDictCodeListRds.事件类型_投资交易)) {
            return "vdate";
        }
        String settleDate = "settleDate";
        Object finprodType2 = map.get(InterfUtil.finprodType2);
        // 存续期
        if (CommonUtil.isType(base.getEventStatus(), BussinessDictCodeListRds.事件类型_投资交易_存续期)) {
            // 债券 | 买断式逆回购 | 质押式逆回购
            if ((isAsset(base, DictCodeList.平安资产类型_债券) && (isPs(map, DictCodeList.交易方向_行权_赎回))
                 || (isAsset(base, DictCodeList.平安资产类型_质押式回购) && StringUtil.equals(
                     finprodType2, DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue()))
                 || (isAsset(base, DictCodeList.平安资产类型_买断式回购) && StringUtil.equals(
                     finprodType2, DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue())))) {
                return settleDate;
            }
            if (isPs(map, DictCodeList.交易方向_到期)
                     && (isAsset(base, DictCodeList.平安资产类型_计划类资产_利率型)
                         || isAsset(base, DictCodeList.平安资产类型_项目类资产))) {
                return "arrivalDate";
            }
            return CreditMap.KEY_TRADEDATE;
        }
        // 交易
        // 债券
        if (isAsset(base, DictCodeList.平安资产类型_债券)
            && (isPs(map, DictCodeList.交易方向_卖出) || isPs(map, DictCodeList.交易方向_行权_回售))) {
            return settleDate;
        }
        if (isAsset(base, DictCodeList.平安资产类型_债券) &&  StringUtil.equals(map.get("windtypeone"),
            DictCodeList.WIND一级分类_含债券一级分类等_可转债.getValue())) {
            return "purchDate";
        }
        if (isAsset(base, DictCodeListExt2.平安资产类型_一级市场债) && CommonUtil.isType(
            base.getEventStatus(), BussinessDictCodeList.共同业务类型_中标结果维护)) {
            return "payDate";
        }
        if (isAsset(base, DictCodeListExt2.平安资产类型_一级市场债)
            && CommonUtil.isType(
            base.getEventStatus(), BussinessDictCodeList.共同业务类型_中标结果确认)) {
            return "biddate";
        }
        return CreditMap.KEY_TRADEDATE;
    }

    /**
     * 计算存续期金额
     * 
     * @param map 画面内容
     * @param base 场景
     * @return 机构授信接口调用参数-金额
     */
    protected static BigDecimal calcPeriodAmt(Map<String, Object> map, IRiskRds base) {
        Object tradeAmtObj = map.get(CreditMap.KEY_TRADEAMT);
        BigDecimal tradeAmt = StringUtil.isNotEmpty(tradeAmtObj) ? new BigDecimal(tradeAmtObj.toString()) : null;
        Object finprodType2 = map.get(InterfUtil.finprodType2);
        // 质押式回购
        if (StringUtil.equals(finprodType2, DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())) {
            CreditLinesUpdateMapper creditLinesUpdateMapper = SpringContextUtil.getBean(CreditLinesUpdateMapper.class);
            FinProductAddMapper finProductAddMapper = SpringContextUtil.getBean(FinProductAddMapper.class);
            Map<String,Object> bussMap = (Map<String, Object>)map.get("bussRepoSec");
            List<Map<String,Object>> pledgeList;
            if (bussMap != null && bussMap.get("pledgeBondList") != null) {
                pledgeList = (List<Map<String, Object>>) bussMap.get("pledgeBondList");
            }else {
                //查询标的券券面总额及最新面值
                Map<String,Object> param = new HashMap<>();
                param.put("finprodId",map.get("finprodId"));
                pledgeList = creditLinesUpdateMapper.findPledgeBondInfo(param);
            }
            if (!StringUtil.isEmptyList(pledgeList)){
                List<String> assetTypeList = Arrays.asList(CacheUtil.getSysParam(BussinessConstant.CREDIT_ASSET_TYPE_F03).split(","));
                // 质押券面总额
                BigDecimal totalAmt = BigDecimal.ZERO;
                BigDecimal supAmt = BigDecimal.ZERO;
                for (Map<String,Object> objMap : pledgeList){
                    // 质押券面总额
                    totalAmt = totalAmt.add(new BigDecimal(objMap.get("pledgePrin").toString()));
                    // 统计需要扣减授信的券面总额
                    String finprodId = String.valueOf(objMap.get("finprodId"));
                    // 查询资产类型
                    FinProductTypeKey key = new FinProductTypeKey();
                    key.setFinprodId(finprodId);
                    key.setBranch(0L);
                    FinProductType finProductType = creditLinesUpdateMapper.selectByPrimaryKey(key);
                    FinProductAdd finProductAdd = finProductAddMapper.selectByPrimaryKey(finprodId);
                    String astType = finProductType.getType10();
                    if (StringUtil.equals(finProductAdd.getIsSecBond(),DictCodeList.是否_是.getValue())){
                        astType = astType.concat("_") + DictCodeList.是否_是.getValue();
                    }
                    // 资产类型不在【不扣减授信参数】列表
                    if (!assetTypeList.contains(astType)){
                        supAmt = supAmt.add(new BigDecimal(objMap.get("pledgePrin").toString()));
                    }

                }
                return  supAmt.multiply(CommonUtil.getBigDecimal(map, "theoryPrin", null)).divide(totalAmt, RoundingMode.HALF_UP).setScale(2,RoundingMode.HALF_UP);
            }

        }
        if(StringUtil.equals(finprodType2, DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue())){
            map.put("ps",DictCodeList.交易方向_到期.getValue());
            // 先判断标的券类型是否需要扣减授信额度
            List<String> assetTypeList = Arrays.asList(CacheUtil.getSysParam(BussinessConstant.CREDIT_ASSET_TYPE_F03).split(","));
            CreditLinesUpdateMapper creditLinesUpdateMapper = SpringContextUtil.getBean(CreditLinesUpdateMapper.class);
            FinProductAddMapper finProductAddMapper = SpringContextUtil.getBean(FinProductAddMapper.class);
            //查询标的券券面总额及最新面值
            Map<String,Object> param = new HashMap<>();
            param.put("finprodId",map.get("finprodId"));
            List<Map<String,Object>> pledgeList = creditLinesUpdateMapper.findPledgeBondInfo(param);
            if (!StringUtil.isEmptyList(pledgeList)){
                // 查询资产类型
                FinProductTypeKey key = new FinProductTypeKey();
                key.setFinprodId(String.valueOf(pledgeList.get(0).get("finprodId")));
                key.setBranch(0L);
                FinProductType finProductType = creditLinesUpdateMapper.selectByPrimaryKey(key);
                FinProductAdd finProductAdd = finProductAddMapper.selectByPrimaryKey(String.valueOf(pledgeList.get(0).get("finprodId")));
                String astType = finProductType.getType10();
                if (StringUtil.equals(finProductAdd.getIsSecBond(),DictCodeList.是否_是.getValue())){
                    astType = astType.concat("_") + DictCodeList.是否_是.getValue();
                }
                // 资产类型在【不扣减授信参数】列表
                if (assetTypeList.contains(astType)){
                    return BigDecimal.ZERO;
                }
                return CommonUtil.getBigDecimal(pledgeList.get(0), "pledgePrin", null);
            }
            return BigDecimal.ZERO;
        }
        // 债券借贷
        if ((isAsset(base, DictCodeListExt2.平安资产类型_债券借贷) && isPs(map, DictCodeList.回购拆借方向_融出))
                || StringUtil.equals(finprodType2, DictCodeListExt1.金融产品类型_投管用_债券借贷融出.getValue())) {
            return CommonUtil.getBigDecimal(map, CreditMap.KEY_SHAREAMT, null);
        }
        // 拆借
        if ((isAsset(base, DictCodeList.平安资产类型_拆借) && StringUtil.equals(map.get("pingAnCodeSin"), DictCodeList.回购拆借方向_拆出.getValue()))
                || StringUtil.equals(finprodType2, DictCodeListExt1.金融产品类型_投管用_拆出.getValue())) {
            return CommonUtil.getBigDecimal(map, "prinAmt", null);
        }
        // 债券-还本到期
        if (isAsset(base, DictCodeList.平安资产类型_债券)
            && (isPs(map, DictCodeList.交易方向_还本) || isPs(map, DictCodeList.交易方向_到期))) {
            return CommonUtil.getBigDecimal(map, "prinAmtTheory", null);
        }
        // 债券-行权赎回
        if (isAsset(base, DictCodeList.平安资产类型_债券) && isPs(map, DictCodeList.交易方向_行权_赎回)) {
            return CommonUtil.getBigDecimal(map, "fPriceAmt", null);
        }
        // 存款
        if (isAsset(base, DictCodeList.平安资产类型_存款)
            && (isPs(map, DictCodeListExt1.平安业务类型_存款提前支取)
                || isPs(map, DictCodeListExt1.平安业务类型_存款到期))) {
            BigDecimal amt = CommonUtil.getBigDecimal(map, "earlyWithdrawalAmt", null);
            if (null == amt) {
                return CommonUtil.getBigDecimal(map, "remainPrinAmt", null);
            }
            return amt;
        }
        // 计划类利率型
        if ((isAsset(base, DictCodeList.平安资产类型_计划类资产_利率型)
             || isAsset(base, DictCodeList.平安资产类型_项目类资产))
            && (isPs(map, DictCodeList.交易方向_到期) || isPs(map, DictCodeList.交易方向_还本))) {
            return CommonUtil.getBigDecimal(map, "returnAmount", null);
        }
        return tradeAmt;
    }

    /**
     * 计算场内指令金额
     * 
     * @param map 画面内容
     * @param base 场景
     * @param value 债券面值|基金净值
     * @return 机构授信接口调用参数-金额
     */
    protected static BigDecimal calcExchangeAmt(Map<String, Object> map, IRiskRds base,
                                                BigDecimal value) {
        BigDecimal faceValue = value == null ? NUM_100 : value;
        Object tradeAmtObj = map.get("entrustAmt");
        Object shareAmtObj = map.get("entrustNum");
        BigDecimal entrustAmt = StringUtil.isNotEmpty(tradeAmtObj) ? new BigDecimal(
            tradeAmtObj.toString()) : null;
        BigDecimal entrustNum = StringUtil.isNotEmpty(shareAmtObj) ? new BigDecimal(
            shareAmtObj.toString()) : null;
        // 债券
        if ((isAsset(base, DictCodeList.平安资产类型_债券) || isAsset(map, DictCodeListExt1.金融产品类型_投管用_债券))
            && (isPs(map, DictCodeList.交易方向_买入) || isPs(map, DictCodeList.交易方向_申购)
                || isPs(map, DictCodeListExt3.交易方向_配债))
            && entrustNum != null) {
            return ArithmeticUtil.multiply(entrustNum, faceValue);
        }
        return entrustAmt;
    }

    protected static BigDecimal calcTradeAmt(Map<String, Object> map, IRiskRds base,
                                             BigDecimal value) {
        BigDecimal faceValue = null == value ? NUM_100 : value;
        BigDecimal lastPrice = null == value ? BigDecimal.ONE : value;
        Object tradeAmtObj = map.get(CreditMap.KEY_TRADEAMT);
        Object shareAmtObj = map.get(CreditMap.KEY_SHAREAMT);
        Object prinAmtObj = map.get(CreditMap.KEY_PRINAMT);
        BigDecimal tradeAmt = StringUtil.isNotEmpty(tradeAmtObj) ? new BigDecimal(tradeAmtObj.toString()) : null;
        BigDecimal shareAmt = StringUtil.isNotEmpty(shareAmtObj) ? new BigDecimal(shareAmtObj.toString()) : null;
        Object finprodType2 = map.get(InterfUtil.finprodType2);
        // 质押式回购
        if (StringUtil.equals(finprodType2, DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue()) || isAsset(base, DictCodeList.平安资产类型_质押式回购)) {
            Map<String,Object> bussMap = (Map<String, Object>)map.get("bussRepoSec");
            if (bussMap != null){
                List<Map<String,Object>> pledgeList = (List<Map<String, Object>>) bussMap.get("pledgeBondList");
                if (!StringUtil.isEmptyList(pledgeList)){
                    List<String> assetTypeList = Arrays.asList(CacheUtil.getSysParam(BussinessConstant.CREDIT_ASSET_TYPE_F03).split(","));
                    // 质押券面总额
                    BigDecimal totalAmt = BigDecimal.ZERO;
                    BigDecimal supAmt = BigDecimal.ZERO;
                    for (Map<String,Object> objMap : pledgeList){
                        // 质押券面总额
                        totalAmt = totalAmt.add(new BigDecimal(objMap.get("pledgePrin").toString()));
                        // 统计需要扣减授信的券面总额
                        String finprodId = String.valueOf(objMap.get("finprodId"));
                        CreditLinesUpdateMapper creditLinesUpdateMapper = SpringContextUtil.getBean(CreditLinesUpdateMapper.class);
                        FinProductAddMapper finProductAddMapper = SpringContextUtil.getBean(FinProductAddMapper.class);
                        // 查询资产类型
                        FinProductTypeKey key = new FinProductTypeKey();
                        key.setFinprodId(finprodId);
                        key.setBranch(0L);
                        FinProductType finProductType = creditLinesUpdateMapper.selectByPrimaryKey(key);
                        FinProductAdd finProductAdd = finProductAddMapper.selectByPrimaryKey(finprodId);
                        String astType = finProductType.getType10();
                        if (StringUtil.equals(finProductAdd.getIsSecBond(),DictCodeList.是否_是.getValue())){
                            astType = astType.concat("_") + DictCodeList.是否_是.getValue();
                        }
                        // 资产类型不在【不扣减授信参数】列表
                        if (!assetTypeList.contains(astType)){
                            supAmt = supAmt.add(new BigDecimal(objMap.get("pledgePrin").toString()));
                        }

                    }
                    return  supAmt.multiply(CommonUtil.getBigDecimal(map, "prinAmt", null)).divide(totalAmt, RoundingMode.HALF_UP).setScale(2,RoundingMode.HALF_UP);
                }
            }
        }
        // 买断式回购
        if(StringUtil.equals(finprodType2, DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue()) || isAsset(base, DictCodeList.平安资产类型_买断式回购)){
            // 先判断标的券类型是否需要扣减授信额度
            List<String> assetTypeList = Arrays.asList(CacheUtil.getSysParam(BussinessConstant.CREDIT_ASSET_TYPE_F03).split(","));
            CreditLinesUpdateMapper creditLinesUpdateMapper = SpringContextUtil.getBean(CreditLinesUpdateMapper.class);
            FinProductAddMapper finProductAddMapper = SpringContextUtil.getBean(FinProductAddMapper.class);
            // 查询资产类型
            FinProductTypeKey key = new FinProductTypeKey();
            key.setFinprodId(String.valueOf(map.get("finprodId")));
            key.setBranch(0L);
            FinProductType finProductType = creditLinesUpdateMapper.selectByPrimaryKey(key);
            FinProductAdd finProductAdd = finProductAddMapper.selectByPrimaryKey(String.valueOf(map.get("finprodId")));
            String astType = finProductType.getType10();
            if (StringUtil.equals(finProductAdd.getIsSecBond(),DictCodeList.是否_是.getValue())){
                astType = astType.concat("_") + DictCodeList.是否_是.getValue();
            }
            // 资产类型在【不扣减授信参数】列表
            if (assetTypeList.contains(astType)){
                return BigDecimal.ZERO;
            }
            tradeAmtObj = map.get("secTotalPrin");
            tradeAmt = StringUtil.isNotEmpty(tradeAmtObj) ? new BigDecimal(tradeAmtObj.toString()) : null;
            if (shareAmt != null) {
                return ArithmeticUtil.multiply(faceValue, shareAmt);
            }
            else if (tradeAmt != null) {
                return ArithmeticUtil.multiply(faceValue,
                        ArithmeticUtil.divide(tradeAmt, NUM_100, 2));
            }
        }
        // 债券借贷
        if ((isAsset(base, DictCodeListExt2.平安资产类型_债券借贷) || StringUtil.equals(finprodType2, DictCodeListExt1.金融产品类型_投管用_债券借贷融出.getValue()))) {
            return ArithmeticUtil.multiply(faceValue, shareAmt, NUM_100);
        }
        // 拆借
        if ((isAsset(base, DictCodeList.平安资产类型_拆借) || StringUtil.equals(finprodType2, DictCodeListExt1.金融产品类型_投管用_拆出.getValue()))) {
            tradeAmtObj = CommonUtil.findValue(map, "prin");
            return StringUtil.isNotEmpty(tradeAmtObj) ? new BigDecimal(tradeAmtObj.toString()) : null;
        }
        // 债券
        if (isAsset(base, DictCodeList.平安资产类型_债券) && null != shareAmt) {
            return ArithmeticUtil.multiply(faceValue, shareAmt);
        }else if (isAsset(base, DictCodeList.平安资产类型_债券) && null != prinAmtObj){
            return new BigDecimal(prinAmtObj.toString());
        }else if (isAsset(base, DictCodeList.平安资产类型_债券)){
            return tradeAmt;
        }
        // 中标结果维护
        if (isAsset(base, DictCodeListExt2.平安资产类型_一级市场债)
            && CommonUtil.isType(base.getEventStatus(), BussinessDictCodeList.共同业务类型_中标结果维护)) {
            @SuppressWarnings("rawtypes")
            List list = (List)map.get("bidOrderBidPriceList");
            BigDecimal winPrimAmt = BigDecimal.ZERO;
            if (!StringUtil.isEmptyList(list)) {
                for (Object item : list) {
                    Map<String, Object> tMap = CommonUtil.toMap(item);
                    winPrimAmt = ArithmeticUtil.doAdd(winPrimAmt,
                        CommonUtil.getBigDecimal(tMap, "winPrinAmt", BigDecimal.ZERO));
                }
            }
            return winPrimAmt;
        }
        // 中标结果确认
        if (isAsset(base, DictCodeListExt2.平安资产类型_一级市场债)
            && CommonUtil.isType(base.getEventStatus(), BussinessDictCodeList.共同业务类型_中标结果确认)) {
            tradeAmtObj = map.get("adjustBallotnum");
            return StringUtil.isNotEmpty(tradeAmtObj) ? new BigDecimal(tradeAmtObj.toString()) : null;
        }
        // 基金
        if (isAsset(base, DictCodeList.平安资产类型_基金) && shareAmt != null) {
            return ArithmeticUtil.multiply(lastPrice, shareAmt);
        }
        // 非标债权类
        if (isAsset(base, DictCodeList.平安资产类型_项目类资产) || isAsset(base, DictCodeList.平安资产类型_存款)
            || isAsset(base, DictCodeList.平安资产类型_计划类资产_利率型)) {
            tradeAmtObj = map.get("prinAmt");
            return StringUtil.isNotEmpty(tradeAmtObj) ? new BigDecimal(tradeAmtObj.toString()) : null;
        }
        // 权益类
        if (isAsset(base, DictCodeListExt3.平安资产类型_权益类资产)) {
            return tradeAmt;
        }
        return tradeAmt;
    }

    /**
     * 计算委托金额
     * 
     * @param map 画面内容
     * @param base 场景
     * @param value 债券面值|基金净值
     * @return 机构授信接口调用参数-金额
     */
    protected static BigDecimal calcEntrustAmt(Map<String, Object> map, IRiskRds base,
                                               BigDecimal value) {
        BigDecimal faceValue = value == null ? NUM_100 : value;
        Object tradeAmtObj = map.get("entrustAmt");
        Object shareAmtObj = map.get("entrustNum");
        BigDecimal entrustAmt = StringUtil.isNotEmpty(tradeAmtObj) ? new BigDecimal(
            tradeAmtObj.toString()) : null;
        BigDecimal entrustNum = StringUtil.isNotEmpty(shareAmtObj) ? new BigDecimal(
            shareAmtObj.toString()) : null;
        // 债券一级指令 | 债券二级指令 | 基金
        if (isAsset(base, DictCodeListExt2.平安资产类型_一级市场债)) {
            return entrustNum;
        }
        // 债券 | 买断式回购
        if ((isAsset(base, DictCodeList.平安资产类型_债券)) && entrustNum != null) {
            return ArithmeticUtil.multiply(faceValue,
                ArithmeticUtil.divide(entrustNum, NUM_100, 2));
        }
        // 债券借贷
        else if (isAsset(base, DictCodeListExt2.平安资产类型_债券借贷)) {
            Object totalAmtObj = map.get("totalAmt");
            BigDecimal totalAmt = StringUtil.isNotEmpty(totalAmtObj) ? new BigDecimal(
                totalAmtObj.toString()) : BigDecimal.ZERO;
            return ArithmeticUtil.multiply(faceValue, totalAmt, NUM_100);
        }
        // 买断式回购
        else if (isAsset(base, DictCodeList.平安资产类型_买断式回购) && entrustAmt != null) {
            // 先判断标的券类型是否需要扣减授信额度
            List<String> assetTypeList = Arrays.asList(CacheUtil.getSysParam(BussinessConstant.CREDIT_ASSET_TYPE_F03).split(","));
            CreditLinesUpdateMapper creditLinesUpdateMapper = SpringContextUtil.getBean(CreditLinesUpdateMapper.class);
            FinProductAddMapper finProductAddMapper = SpringContextUtil.getBean(FinProductAddMapper.class);
            // 查询资产类型
            FinProductTypeKey key = new FinProductTypeKey();
            String finprodId = "";
            if (map.get("finprodId") != null){
                finprodId = String.valueOf(map.get("finprodId"));
            }
            if (StringUtil.isEmpty(finprodId) && map.get("finprodMarketId") != null){
                finprodId = DictCodeListExt1.金融产品类型_投管用_债券.getValue()+ "_" + map.get("finprodMarketId");
            }
            key.setFinprodId(finprodId);
            key.setBranch(0L);
            FinProductType finProductType = creditLinesUpdateMapper.selectByPrimaryKey(key);
            FinProductAdd finProductAdd = finProductAddMapper.selectByPrimaryKey(finprodId);
            if (finProductType == null || finProductAdd == null){
                return BigDecimal.ZERO;
            }
            String astType = finProductType.getType10();
            if (StringUtil.equals(finProductAdd.getIsSecBond(),DictCodeList.是否_是.getValue())){
                astType = astType.concat("_") + DictCodeList.是否_是.getValue();
            }
            // 资产类型在【不扣减授信参数】列表
            if (assetTypeList.contains(astType)){
                return BigDecimal.ZERO;
            }
            return ArithmeticUtil.multiply(faceValue,
                ArithmeticUtil.divide(entrustAmt, NUM_100, 2));
        }
        return entrustAmt;
    }

    public static boolean isAsset(IRiskRds base, BaseDictCodeList dict) {
        return CommonUtil.isType(base.getAssetType(), dict);
    }

    public static boolean isAsset(Map<String, Object> map, BaseDictCodeList dict) {
        return CommonUtil.isType((String)CommonUtil.findValue(map, InterfUtil.finprodType2), dict);
    }

    public static boolean isPs(Map<String, Object> map, BaseDictCodeList dict) {
        return CommonUtil.isType((String)CommonUtil.findValue(map, InterfUtil.ps), dict);
    }

    public static boolean isEventType(IRiskRds base, BaseDictCodeList dict) {
        return CommonUtil.isType(base.getEventType(), dict);
    }

    /**
     * 是否场内指令
     * 
     * @param map 画面内容
     * @return true : 场内指令
     */
    public static boolean isExchange(Map<String, Object> map) {
        return StringUtil.equals(map.get("fromEtrExchange"), DictCodeList.是否_是.getValue());
    }

    static class CreditMap {

        protected static final String KEY_FINPRODID = "finprodId";

        protected static final String KEY_COUNTERID = "counterId";

        protected static final String KEY_TRADEAMT = "tradeAmt";

        protected static final String KEY_SHAREAMT = "shareAmt";

        protected static final String KEY_NETPRICE = "netPrice";

        protected static final String KEY_PRINAMT = "prinAmt";

        protected static final String KEY_TRADEDATE = "tradeDate";

        protected static final String KEY_PS = "ps";

        protected static final String CREDIT_CHECK_TYPE = "creditCheckType";

        protected static final String KEY_TRADESTATUS = "tradeStatus";

        protected static final String TG_BUSI_TYPE = "tgBusiType";

        protected static final String PS_PAYMENT = "psPayment";

        protected static final String SALE_CODE = "saleCode";

        protected static final String TRADE_ID = "tradeId";

        protected static final String ENTRUST_ID = "entrustId";

        private Map<String, Object> toMap;

        CreditMap() {
            toMap = new HashMap<>();
        }

        CreditMap copyFromMap(String toKey, Map<String, Object> fromMap, String fromKey) {
            toMap.put(toKey, CommonUtil.findValue(fromMap, fromKey));
            return this;
        }

        CreditMap copyFromMap(String toKey, Map<String, Object> fromMap) {
            toMap.put(toKey, CommonUtil.findValue(fromMap, toKey));
            return this;
        }

        CreditMap copyFinprodId(Map<String, Object> fromMap, String fromKey) {
            return copyFromMap(KEY_FINPRODID, fromMap, fromKey);
        }

        CreditMap copyCounterId(Map<String, Object> fromMap, String fromKey) {
            return copyFromMap(KEY_COUNTERID, fromMap, fromKey);
        }

        CreditMap copyTradeAmt(Map<String, Object> fromMap, String fromKey) {
            return copyFromMap(KEY_TRADEAMT, fromMap, fromKey);
        }

        CreditMap copyShareAmt(Map<String, Object> fromMap, String fromKey) {
            return copyFromMap(KEY_SHAREAMT, fromMap, fromKey);
        }

        CreditMap copyTradeDate(Map<String, Object> fromMap, String fromKey) {
            return copyFromMap(KEY_TRADEDATE, fromMap, fromKey);
        }

        CreditMap copyPs(Map<String, Object> fromMap, String fromKey) {
            return copyFromMap(KEY_PS, fromMap, fromKey);
        }

        CreditMap copyFinprodId(Map<String, Object> fromMap) {
            return copyFromMap(KEY_FINPRODID, fromMap, KEY_FINPRODID);
        }

        CreditMap copyCounterId(Map<String, Object> fromMap) {
            return copyFromMap(KEY_COUNTERID, fromMap, KEY_COUNTERID);
        }

        CreditMap copyTradeAmt(Map<String, Object> fromMap) {
            return copyFromMap(KEY_TRADEAMT, fromMap, KEY_TRADEAMT);
        }

        CreditMap copyShareAmt(Map<String, Object> fromMap) {
            return copyFromMap(KEY_SHAREAMT, fromMap, KEY_TRADEAMT);
        }

        CreditMap copyTradeDate(Map<String, Object> fromMap) {
            return copyFromMap(KEY_TRADEDATE, fromMap, KEY_TRADEDATE);
        }

        CreditMap copyPs(Map<String, Object> fromMap) {
            return copyFromMap(KEY_PS, fromMap, KEY_PS);
        }
        CreditMap copyCreditCheckType(Map<String, Object> fromMap) {
            return copyFromMap(CREDIT_CHECK_TYPE, fromMap, CREDIT_CHECK_TYPE);
        }
        @SuppressWarnings("unchecked")
        <V> V getFromMap(String toKey, Function<String, V> func) {
            Object obj = toMap.get(toKey);
            if (func != null && obj != null) {
                return func.apply(obj.toString());
            }
            return (V)obj;
        }

        BigDecimal getTradeAmt() {
            return getFromMap(KEY_TRADEAMT, BigDecimal::new);
        }

        BigDecimal getShareAmt() {
            return getFromMap(KEY_SHAREAMT, BigDecimal::new);
        }

        Date getTradeDate() {
            return getFromMap(KEY_TRADEDATE, DateUtil::parse);
        }

        String getFinprodId() {
            return getFromMap(KEY_FINPRODID, str -> str);
        }

        String getCounterId() {
            return getFromMap(KEY_COUNTERID, str -> str);
        }

        String getPs() {
            return getFromMap(KEY_PS, str -> str);
        }

        CreditMap putToMap(String toKey, Object value) {
            toMap.put(toKey, value);
            return this;
        }

        CreditMap putTradeAmt(Object value) {
            return putToMap(KEY_TRADEAMT, value);
        }
        CreditMap putShareAmt(Map<String, Object> fromMap) {
            return copyFromMap(KEY_SHAREAMT, fromMap,KEY_SHAREAMT);
        }

        CreditMap putNetPrice(Object value) {
            return putToMap(KEY_NETPRICE, value);
        }


        CreditMap copyTgBusiType(Map<String, Object> fromMap) {
            return copyFromMap(TG_BUSI_TYPE, fromMap, TG_BUSI_TYPE);
        }
        CreditMap copyPsPayment(Map<String, Object> fromMap) {
            return copyFromMap(PS_PAYMENT, fromMap, PS_PAYMENT);
        }
        CreditMap copySaleCode(Map<String, Object> fromMap) {
            return copyFromMap(SALE_CODE, fromMap, SALE_CODE);
        }
        CreditMap setTradeStatus(String tradeStatus) {
            return putToMap(KEY_TRADESTATUS, tradeStatus);
        }
        CreditMap copyTradeId(Map<String, Object> fromMap){
            return copyFromMap(TRADE_ID, fromMap,TRADE_ID);
        }
        CreditMap copyEntrustId(Map<String, Object> fromMap){
            return copyFromMap(ENTRUST_ID, fromMap,ENTRUST_ID);
        }

        Map<String, Object> toMap() {
            return toMap;
        }
    }
}
