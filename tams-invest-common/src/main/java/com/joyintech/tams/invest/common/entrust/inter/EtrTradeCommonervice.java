package com.joyintech.tams.invest.common.entrust.inter;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.*;
import com.joyintech.tams.business.common.pojo.BaseMap;
import com.joyintech.tams.business.common.service.PositionHttpRequest;
import com.joyintech.tams.common.base.dao.FinProductAddMapper;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.position.*;
import com.joyintech.tams.invest.common.entrust.dao.EtrTrdCommonMapper;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 公共方法service
 */
@Service("etrTradeCommonervice")
public class EtrTradeCommonervice extends BaseService {

    public static final String FLOW_TYPE_ETR = "etr";
    public static final String FLOW_TYPE_TRD = "trd";

    @Autowired(required = false)
    private BasePtlCashPositionInfoService basePtlCashPositionInfoService;
    @Autowired
    private IPlatformDate iPlatformDate;
    @Autowired
    private EtrTrdCommonMapper etrTrdCommonMapper;
    @Autowired(required = false)
    private BaseEtrCashPositionCommService baseEtrCashPositionCommService;

    @Autowired(required = false)
    private FinProductAddMapper finProductAddMapper;

    @Autowired
    private FinProductMapper finProductMapper;


    /**
     * 场内指令，头寸信息
     * @return
     */
    public List<BaseMap> queryPositionInfo(PositionCashSecRequest requestDto) {

        // 条件非空校验
        posiInfoCheckCondition(requestDto);

        if (requestDto.getPtlList() == null || requestDto.getPtlList().size() == 0){
            return nullData();
        }


        // 从原投管系统取
        BigDecimal portfolioCash = BigDecimal.ZERO; // 组合现金可用
        BigDecimal canCash = BigDecimal.ZERO; // 现金可用
        BigDecimal realCash = BigDecimal.ZERO; // 现金可取
        BigDecimal canSec = BigDecimal.ZERO; // 可用持仓
        BigDecimal realSec = BigDecimal.ZERO; // 持仓数量
        List<PositionCashSecDto> list = requestDto.getPtlList();
        String flowType= requestDto.getFlowType();
        flowType=StringUtil.equals(FLOW_TYPE_ETR,flowType)?"01":"02";

        if (list != null) {
            //查询组合下所有现金账户

            List<PositionCashSecDto> sumPortfolioAcctNoList = new ArrayList<>();
            for (PositionCashSecDto dto : list){
                if(StringUtil.isEmpty(dto.getCdate())){
                    dto.setCdate(iPlatformDate.getSysDateStr());
                }
                List<String> acctNoList=etrTrdCommonMapper.getPtlAcctInfo(dto.getPortfolioId());
                if(!CollectionUtils.isEmpty(acctNoList)){
                    for(String acctNo:acctNoList){
                        PositionCashSecDto cashSecDto = new PositionCashSecDto();
                        BeanUtil.copyPropertiesIgnoreNull(cashSecDto, dto); //复制资产信息
                        cashSecDto.setAcctId(acctNo);
                        sumPortfolioAcctNoList.add(cashSecDto);
                    }
                }
            }
            //组合资金可用 1、老资金相关表
            if(!CollectionUtils.isEmpty(sumPortfolioAcctNoList)){
                for (PositionCashSecDto dto : sumPortfolioAcctNoList){
                    PositionCashSecData data=querySingleCash(dto);
                    if(data!=null&&data.getCashT()!=null){
                        portfolioCash=portfolioCash.add(data.getCashT());
                    }
                }
                //组合资金可用 2、新头寸引擎
                BigDecimal newPortfolioAmount=queryPosition(sumPortfolioAcctNoList,flowType, BussinessDictCodeList.持仓类型_可用.getValue());
                if(newPortfolioAmount!=null){
                    portfolioCash=portfolioCash.add(newPortfolioAmount);
                }
            }
            //资金可用
            //筛选出有资金账户的组合
            List<PositionCashSecDto> sumAcctNoList = new ArrayList<>();
            for (PositionCashSecDto dto : list){
                if(StringUtil.isNotEmpty(dto.getAcctId())){
                    PositionCashSecDto cashSecDto = new PositionCashSecDto();
                    BeanUtil.copyPropertiesIgnoreNull(cashSecDto, dto); //复制资产信息
                    sumAcctNoList.add(cashSecDto);
                }
            }
            //资金可用金额
            if(!CollectionUtils.isEmpty(sumAcctNoList)&&sumAcctNoList.size()>0){
                //1、老资金相关表
                for (PositionCashSecDto dto : list){
                    PositionCashSecData data=querySingleCash(dto);
                    if(data!=null&&data.getCashT()!=null){
                        canCash=canCash.add(data.getCashT());
                        realCash=realCash.add(data.getRealCash());
                    }
                }
                //组合资金可用 2、新头寸引擎
                BigDecimal newCashAmount=queryPosition(sumAcctNoList,flowType, BussinessDictCodeList.持仓类型_可用.getValue());
                if(newCashAmount!=null){
                    canCash=canCash.add(newCashAmount);
                }
                BigDecimal newRealCashAmount=queryPosition(sumAcctNoList,flowType, BussinessDictCodeList.持仓类型_实际.getValue());
                if(newRealCashAmount!=null){
                    realCash=realCash.add(newRealCashAmount);
                }
            }
            //持仓可用
            flowType=StringUtil.equals("etr",flowType)?"03":"04";
            //筛选出有资金账户的组合
            List<PositionCashSecDto> secList = new ArrayList<>();
            for (PositionCashSecDto dto : list){
                if(StringUtil.isNotEmpty(dto.getFinprodId())&&StringUtil.isNotEmpty(dto.getSecAcctId())){
                    PositionCashSecDto cashSecDto = new PositionCashSecDto();
                    BeanUtil.copyPropertiesIgnoreNull(cashSecDto, dto); //复制资产信息
                    secList.add(cashSecDto);
                }
            }
            //资金可用金额
            if(!CollectionUtils.isEmpty(secList)&&secList.size()>0){
                BigDecimal newSecAmount=queryPosition(secList,flowType, BussinessDictCodeList.持仓类型_可用.getValue());
                if(newSecAmount!=null){
                    canSec=canSec.add(newSecAmount);
                }
                BigDecimal newRealSecAmount=queryPosition(secList,flowType, BussinessDictCodeList.持仓类型_实际.getValue());
                if(newRealSecAmount!=null){
                    realSec=realSec.add(newRealSecAmount);
                }
            }


        }
        return assemble(portfolioCash,canCash,realCash,canSec,realSec);
    }

    /**
     * 场内指令，头寸信息
     * @return
     */
    public List<PositionCashSecSingleResp> queryPositionInfoSingle(PositionCashSecRequest requestDto) {

        List<PositionCashSecSingleResp> respList=new ArrayList<>();
        // 条件非空校验
        posiInfoCheckCondition(requestDto);

        if (requestDto.getPtlList() == null || requestDto.getPtlList().size() == 0){
            return respList;
        }
        List<PositionCashSecDto> list = requestDto.getPtlList();
        if (list != null) {

            String flowType = requestDto.getFlowType();
            flowType = StringUtil.equals("etr", flowType) ? "01" : "02";
            for (PositionCashSecDto dto : list) {
                // 从原投管系统取
                BigDecimal portfolioCash = BigDecimal.ZERO; // 组合现金可用
                BigDecimal canCash = BigDecimal.ZERO; // 现金可用
                BigDecimal realCash = BigDecimal.ZERO; // 现金可取
                BigDecimal canSec = BigDecimal.ZERO; // 可用持仓
                BigDecimal realSec = BigDecimal.ZERO; // 持仓数量
                if (StringUtil.isEmpty(dto.getCdate())) {
                    dto.setCdate(iPlatformDate.getSysDateStr());
                }
                //查询组合下所有现金账户

                List<PositionCashSecDto> sumPortfolioAcctNoList = new ArrayList<>();
                List<String> acctNoList = etrTrdCommonMapper.getPtlAcctInfo(dto.getPortfolioId());
                if (!CollectionUtils.isEmpty(acctNoList)) {
                    for (String acctNo : acctNoList) {
                        PositionCashSecDto cashSecDto = new PositionCashSecDto();
                        BeanUtil.copyPropertiesIgnoreNull(cashSecDto, dto); //复制资产信息
                        cashSecDto.setAcctId(acctNo);
                        sumPortfolioAcctNoList.add(cashSecDto);
                    }
                }
                //组合资金可用 1、老资金相关表
                if (!CollectionUtils.isEmpty(sumPortfolioAcctNoList)) {
                    for (PositionCashSecDto tempDto : sumPortfolioAcctNoList) {
                        PositionCashSecData data = querySingleCash(tempDto);
                        if (data != null && data.getCashT() != null) {
                            portfolioCash = portfolioCash.add(data.getCashT());
                        }
                    }
                    //组合资金可用 2、新头寸引擎
                    BigDecimal newPortfolioAmount = queryPosition(sumPortfolioAcctNoList, flowType, BussinessDictCodeList.持仓类型_可用.getValue());
                    if (newPortfolioAmount != null) {
                        portfolioCash = portfolioCash.add(newPortfolioAmount);
                    }
                }
                //资金可用
                //筛选出有资金账户的组合
                List<PositionCashSecDto> sumAcctNoList = new ArrayList<>();
                if (StringUtil.isNotEmpty(dto.getAcctId())) {
                    PositionCashSecDto cashSecDto = new PositionCashSecDto();
                    BeanUtil.copyPropertiesIgnoreNull(cashSecDto, dto); //复制资产信息
                    sumAcctNoList.add(cashSecDto);
                }
                //资金可用金额
                //1、老资金相关表
                //组合资金可用 2、新头寸引擎
                if (!CollectionUtils.isEmpty(sumAcctNoList)) {
                    PositionCashSecData data = querySingleCash(dto);
                    if (data != null && data.getCashT() != null) {
                        canCash = canCash.add(data.getCashT());
                        realCash = realCash.add(data.getRealCash());
                    }
                    BigDecimal newCashAmount = queryPosition(sumAcctNoList, flowType, BussinessDictCodeList.持仓类型_可用.getValue());
                    if (newCashAmount != null) {
                        canCash = canCash.add(newCashAmount);
                    }
                    BigDecimal newRealCashAmount = queryPosition(sumAcctNoList, flowType, BussinessDictCodeList.持仓类型_实际.getValue());
                    if (newRealCashAmount != null) {
                        realCash = realCash.add(newRealCashAmount);
                    }
                }

                //持仓可用
                flowType = StringUtil.equals("etr", flowType) ? "03" : "04";
                //筛选出有资金账户的组合
                List<PositionCashSecDto> secList = new ArrayList<>();
                if (StringUtil.isNotEmpty(dto.getFinprodId()) && StringUtil.isNotEmpty(dto.getSecAcctId())) {
                    PositionCashSecDto cashSecDto = new PositionCashSecDto();
                    BeanUtil.copyPropertiesIgnoreNull(cashSecDto, dto); //复制资产信息
                    secList.add(cashSecDto);
                }
                //资金可用金额
                if (!CollectionUtils.isEmpty(secList) && secList.size() > 0) {
                    BigDecimal newSecAmount = queryPosition(secList, flowType, BussinessDictCodeList.持仓类型_可用.getValue());
                    if (newSecAmount != null) {
                        canSec = canSec.add(newSecAmount);
                    }
                    BigDecimal newRealSecAmount = queryPosition(secList, flowType, BussinessDictCodeList.持仓类型_实际.getValue());
                    if (newRealSecAmount != null) {
                        realSec = realSec.add(newRealSecAmount);
                    }
                }
                PositionCashSecSingleResp resp = new PositionCashSecSingleResp();
                resp.setPortfolioId(dto.getPortfolioId());
                List<BaseMap> baseMapList = assemble(portfolioCash, canCash, realCash, canSec, realSec);
                resp.setBaseMapList(baseMapList);
                respList.add(resp);
            }
        }
        return respList;
    }

    private void posiInfoCheckCondition(PositionCashSecRequest req) {
        if (req == null){
            throw new RuntimeException("入参不空！");
        }
    }

    private List<BaseMap> nullData() {
        List<BaseMap> resp = new ArrayList<>();

        /*组合资金可用*/
        resp.add(BaseMap.newMap()
                .put("field", "porUsableCash")
                .put("name", "组合资金可用（元）")
                .put("type", "xj")
                .put("amount", "--"));

        /*账户资金可用*/
        resp.add(BaseMap.newMap()
                .put("field", "acctNoUsableCash")
                .put("name", "账户资金可用（元）")
                .put("type", "xj")
                .put("amount", "--"));
        /*账户资金可取*/
        resp.add(BaseMap.newMap()
                .put("field", "acctNoCanCash")
                .put("name", "账户资金可取（元）")
                .put("type", "xj")
                .put("amount", "--"));
        /*可用数量*/
        resp.add(BaseMap.newMap()
                .put("field", "usableAmount")
                .put("name", "可用数量（股）")
                .put("type", "cc")
                .put("amount", "--"));
        /*持仓数量*/
        resp.add(BaseMap.newMap()
                .put("field", "usableSec")
                .put("name", "持仓数量（股）")
                .put("type", "cc")
                .put("amount", "--"));


        return resp;
    }
    private List<BaseMap> assemble(BigDecimal portfolioCash, BigDecimal canCash,BigDecimal realCash,BigDecimal canSec, BigDecimal realSec) {
        String porUsableCash="porUsableCash",acctNoUsableCash="acctNoUsableCash",acctNoCanCash="acctNoCanCash",usableAmount="usableAmount",usableSec="usableSec";
        // 初始化返回集合
        List<BaseMap> resp = new ArrayList<>();
        BaseMap bm = BaseMap.newMap(new HashMap());
        /**
         * 若老投管现金存在需累计
         */
        if (portfolioCash != null){
            bm.put(porUsableCash, portfolioCash);
        }else{
            bm.put(porUsableCash, 0);
        }
        if (canCash != null){
            bm.put(acctNoUsableCash, canCash);
        }else{
            bm.put(acctNoUsableCash, 0);
        }
        if (realCash != null){
            bm.put(acctNoCanCash, realCash);
        }else{
            bm.put(acctNoCanCash, 0);
        }
        if (canSec != null){
            bm.put(usableAmount, canSec);
        }else{
            bm.put(usableAmount, 0);
        }
        if (realSec != null){
            bm.put(usableSec, realSec);
        }else{
            bm.put(usableSec, 0);
        }

        /*组合资金可用*/
        resp.add(BaseMap.newMap()
                .put("field",porUsableCash)
                .put("name", "组合资金可用（元）")
                .put("type", "xj")
                .put("amount", bm.get(porUsableCash)));

        /*账户资金可用*/
        resp.add(BaseMap.newMap()
                .put("field", acctNoUsableCash)
                .put("name", "账户资金可用（元）")
                .put("type", "xj")
                .put("amount", bm.get(acctNoUsableCash)));
        /*账户资金可取*/
        resp.add(BaseMap.newMap()
                .put("field",acctNoCanCash)
                .put("name", "账户资金可取（元）")
                .put("type", "xj")
                .put("amount", bm.get(acctNoUsableCash)));
        /*可用数量*/
        resp.add(BaseMap.newMap()
                .put("field", "usableAmount")
                .put("name", "可用数量（股）")
                .put("type", "cc")
                .put("amount",bm.get(usableAmount)==null?0:bm.get(usableAmount)));
        /*持仓数量*/
        resp.add(BaseMap.newMap()
                .put("field", "usableSec")
                .put("name", "持仓数量（股）")
                .put("type", "cc")
                .put("amount", bm.get(usableSec)==null?0:bm.get(usableSec)));

        return resp;
    }
    private String addToStr(BigDecimal a, BigDecimal b){
        if (a == null && b== null){
            return "--";
        }
        return decimalToStr(ArithmeticUtil.doAdd(a,b),"0.00");
    }
    /**
     * 返回前端格式保留小数位
     * @param b
     * @param pattern
     * @return
     */
    private String decimalToStr(BigDecimal b, String pattern){
        if (b == null){
            return "--";
        }
        DecimalFormat df1 = new DecimalFormat(pattern);
        return df1.format(b);
    }

    public PositionCashSecData querySingleCash(PositionCashSecDto dto){
        // 从原投管系统取
        PositionCashSecData positionCashSecData=new PositionCashSecData();
        BigDecimal cashT = BigDecimal.ZERO; // 指令t日可用
        BigDecimal realCash = BigDecimal.ZERO; // 账户现金
        String calId = StringUtil.equals(dto.getTradeMarket(), DictCodeListExt1.交易场所_上海证券交易所.getValue())?DictCodeList.交易日历_上海交易所日历.getValue():DictCodeList.交易日历_深圳交易所日历.getValue();
        BaseMap paramMap = BaseMap.newMap()
                .put("finprodId", dto.getFinprodId())
                .put("portfolioId", dto.getPortfolioId())
                .put("ccy", DictCodeList.币种_人民币_CNY.getValue())
                .put("vdate", dto.getCdate())
                .put("calId", calId)
                .put("acctId",dto.getAcctId())
                ;

        Map<String, Object> mapRst =getProAvailableCashExchange(paramMap);
        if (mapRst != null){ // 指令t日可用，指令t1日可用
            cashT = ArithmeticUtil.doAdd(cashT, BaseMap.newMap(mapRst).getBigDecimal("cashT"));
        }
        PtlCashPositionFlowDto positionQueryDto = new PtlCashPositionFlowDto();
        positionQueryDto.setPortfolioId(dto.getPortfolioId());
        positionQueryDto.setCcy(DictCodeList.币种_人民币_CNY.getValue());
        positionQueryDto.setCdate(iPlatformDate.getSysDate());
        // 实际现金（账户现金）
        realCash = ArithmeticUtil.doAdd(realCash, basePtlCashPositionInfoService.queryUsableCashPosition(positionQueryDto));
        positionCashSecData.setCashT(cashT);
        positionCashSecData.setRealCash(realCash);
        return positionCashSecData;
    }
    /**
     * 从头寸引擎查询现金持仓余额
     * @param ptList
     * @return
     */
    public  BigDecimal queryPosition(List<PositionCashSecDto> ptList,String flowType,String positionType) {
        Map<String, Object> map=new HashMap<>();
        BigDecimal sumAmount=BigDecimal.ZERO;
        map.put("ptlList",buildPosition(ptList,flowType,positionType));
//        try {
//                String msg= PositionHttpRequest.queryPosition(map);
//                Map message = JsonUtils.json2Object(msg, Map.class);
//                if (message != null&&StringUtil.equals("success", message.get("code"))) {
//                    List<Map> list=JsonUtils.json2List(message.get("data").toString(),Map.class);
//                    for(Map tempMap:list){
//                        sumAmount=sumAmount.add(new BigDecimal(tempMap.get("AMOUNT").toString()));
//                    }
//                }
//            } catch (IOException e) {
//         }
        return sumAmount;
    }
    /**
     * 从头寸引擎查询现金持仓余额
     * @param ptlList
     * @return
     */
    private  List<PositionQueryDto> buildPosition(List<PositionCashSecDto>  ptlList, String flowType, String positionType) {
        if (StringUtil.isNotEmpty(ptlList)) {
            List<PositionQueryDto> list = new ArrayList<>();
            for (PositionCashSecDto temp : ptlList) {
                PositionQueryDto dto = new PositionQueryDto();
                dto.setPortfolioId(temp.getPortfolioId());
                dto.setCcy(temp.getCcy());
                dto.setFlowType(flowType);
                dto.setPositionType(positionType);
                //现金 01 指令现金 02 指令持仓 03 交易现金 04 交易持仓
                if (StringUtil.equals(flowType, "01") || StringUtil.equals(flowType, "03")) {
                    dto.setCashAcctId(temp.getAcctId());
                }
                //持仓
                if ((StringUtil.equals(flowType, "02") || StringUtil.equals(flowType, "04"))&&StringUtil.isNotEmpty(positionType)) {
                    dto.setSecAcctId(temp.getSecAcctId());
                    dto.setFinprodId(temp.getFinprodId());
                }
                list.add(dto);
            }
            return list;
        }
        return null;
    }

    /**
     * 组合指令可用现金查询
     *
     * @param param
     * @return cashT 组合T日可用, cashT1 组合T+1日可用, cashT3 托管户可用, cashT2 通道可用， cashT4 交易所资金账户可用
     * @see
     * @since 1.0
     */
    public Map<String, Object> getProAvailableCashExchange(Map<String, Object> param) {
        PtlCashPositionFlowDto dto = new PtlCashPositionFlowDto();
        Date sysdate = DateUtil.parse(DateUtil.getSysDate());
        if (StringUtil.isNotEmpty(param.get("acctId"))){
            dto.setAcctId((String)param.get("acctId"));
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
        }

        return result;
    }
    /**
     * 交易日历的节假日取得
     *
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param dealCalId 交易日历代码
     * @return 节假日列表
     */
    public List<String> getDealCalendarHoliday(Date startDate, Date endDate, String dealCalId) {
        Map<String, Object> paraMap = new HashMap<>();
        int days = StringUtil.toInt(CacheUtil.getSysParam(BussinessConstant.HOLIDAY_POSTPONE_DAYS));
        paraMap.put("startDate", DateUtil.addDay(startDate, -days));
        paraMap.put("endDate", DateUtil.addDay(endDate, days));
        paraMap.put("dealCalId", dealCalId);
        return etrTrdCommonMapper.getDealCalendarHoliday(paraMap);
    }

    /**
     * 交易日推算
     *
     * @param beforeAdjustDate 调整前日期
     * @param days 调整天数
     * @param holidayList 节假日列表
     * @param flag 是否跨月 1：是、2：否
     * @return 调整后日期
     * @exception Exception 异常
     */
    public Date calculationTradingDay(Date beforeAdjustDate, int days,
                                      List<String> holidayList, String flag) {
        // 交易日计数
        int dayCount = 0;
        // 调整后非跨月日期
        Date noCrossDate = null;
        // 调整后日期
        Date afterAdjustDate = beforeAdjustDate;

        while (dayCount < days) {
            // 中间参数.调整后日期 = 临时变量.调整后日期 + 1日
            Date tempAfterAdjustDate = DateUtil.addDay(afterAdjustDate, 1);
            while (holidayList.indexOf(DateUtil.format(tempAfterAdjustDate)) > -1) {
                tempAfterAdjustDate = DateUtil.addDay(tempAfterAdjustDate, 1);
            }

            if (StringUtil.equals(DictCodeList.是否_否.getValue(), flag) && !StringUtil.equals(
                    DateUtil.format(tempAfterAdjustDate, "yyyyMM"),
                    DateUtil.format(beforeAdjustDate, "yyyyMM"))) {
                noCrossDate = afterAdjustDate;
                return noCrossDate;
            }
            else {
                afterAdjustDate = tempAfterAdjustDate;
                dayCount++ ;
            }
        }
        return afterAdjustDate;
    }
    //可用现金头寸
    public BigDecimal selectAccAvailableCash(PositionCashSecDto dto){
        BigDecimal cashT = BigDecimal.ZERO; // 指令t日可用
        String calId = StringUtil.equals(dto.getTradeMarket(), DictCodeListExt1.交易场所_上海证券交易所.getValue())?DictCodeList.交易日历_上海交易所日历.getValue():DictCodeList.交易日历_深圳交易所日历.getValue();
        BaseMap paramMap = BaseMap.newMap()
                .put("finprodId", dto.getFinprodId())
                .put("portfolioId", dto.getPortfolioId())
                .put("ccy", DictCodeList.币种_人民币_CNY.getValue())
                .put("vdate", dto.getCdate())
                .put("calId", calId)
                .put("acctId",dto.getAcctId())
                ;
        Map<String, Object> mapRst =getProAvailableCashExchange(paramMap);
        if (mapRst != null){ // 指令t日可用，指令t1日可用
            cashT = ArithmeticUtil.doAdd(cashT, BaseMap.newMap(mapRst).getBigDecimal("cashT"));
        }
        //头寸引擎
        List<PositionCashSecDto> sumAcctNoList = new ArrayList<>();
        sumAcctNoList.add(dto);
        BigDecimal newCashAmount = queryPosition(sumAcctNoList, "01", BussinessDictCodeList.持仓类型_可用.getValue());
        if (newCashAmount != null) {
            cashT = cashT.add(newCashAmount);
        }
        return cashT;
    }

    //持仓
    public BigDecimal selectAvailableNum(PositionCashSecDto dto){
        if (StringUtil.isEmpty(dto.getCcy())) {
            dto.setCcy(DictCodeList.币种_人民币_CNY.getValue());
        }
        BigDecimal canSec = BigDecimal.ZERO; // 可用持仓
        List<PositionCashSecDto> secList=new ArrayList<>();
        secList.add(dto);
        BigDecimal newSecAmount=queryPosition(secList,"02", dto.getHoldingType());
        if(newSecAmount!=null){
            canSec=canSec.add(newSecAmount);
        }
        return canSec;
    }


}
