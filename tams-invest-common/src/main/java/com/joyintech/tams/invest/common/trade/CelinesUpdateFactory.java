package com.joyintech.tams.invest.common.trade;

import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.CreditLinesDealMapper;
import com.joyintech.tams.common.base.dao.CreditLinesMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.component.base.core.BaseGetBussinessId;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.risktrial.dto.CreditLinesDetail;
import com.joyintech.tams.invest.common.trade.dao.CreditLinesDealsMapper;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.dto.CreditLinesDealCommon;
import com.joyintech.tams.invest.common.trade.dto.FinProductCredit;
import com.joyintech.tams.invest.common.trade.service.ICreditLinesBussness;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

public class CelinesUpdateFactory implements BeanValidator,ICreditLinesBussness {

    @Autowired
    CreditLinesDealsMapper creditLinesMapper;

    List<CreditLines> carietiesCreList;//品种授信列表
    Boolean flagisNeedOccupyCrePs=false;//根据当前交易方向判断是否需要占用/释放授信
    Boolean flagarrisNeedOccupyCreCounter=false;//是否需要根据交易对手判断是否占用授信

    AllTradeCommonInputDto inputmodel;
    boolean isNotBottomflag;
    boolean isSave;
    Boolean isdeleteflag;
    // 是否空档期以是否有机构授信判断
    boolean isKDQ = false;
    String customerid = "";
    //授信共通model，方法返回参数
    CreditLinesDealCommon common =new CreditLinesDealCommon();
    private FinProductCredit finProductCredit;

    @Autowired(required = false)
    private BaseGetBussinessId baseGetBusinessId;

    @Autowired
    private CreditLinesMapper creditMapper;

    @Autowired
    private CreditLinesDealMapper creditLinesDealMapper;

    //默认构造
    public  CelinesUpdateFactory()
    {

    }
    //父类构造函数
    public  CelinesUpdateFactory(AllTradeCommonInputDto inputmodel, boolean isNotBottomflag, boolean isSave,Boolean isdeleteflag){
        this.inputmodel=inputmodel;
        this.isNotBottomflag=isNotBottomflag;
        this.isSave=isSave;
        this.isdeleteflag=isdeleteflag;
    }

    /**
     * 占用、释放授信额度参数check共通处理
     * Add by xkni 2019-09-12
     * @return
     */
    @Override
    public List<CreditLinesDealCommon> checkData() {

        //底层资产返回list
        List<CreditLinesDealCommon> list = new ArrayList<CreditLinesDealCommon>();
        //交易相关信息model
        TrdProductDeal model = inputmodel.getTrdProductDeal();
        // 交易方向处理为买入
        if(model.getPs() !=null && model.getPs() != ""){
            if(model.getPs().equals(DictCodeListExt1.交易方向_债转股.getValue())
                    || model.getPs().equals(DictCodeList.回购拆借方向_逆回购.getValue())
                    || (StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券借贷融入.getValue())
                    && !model.getPs().equals(DictCodeList.交易方向_到期.getValue()))
                    || (model.getPs().equals(DictCodeList.回购拆借方向_拆出.getValue())
                    &&StringUtil.equals(model.getInputValue1(), DictCodeList.平安资产类型_拆借.getValue()))
//                    || (StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_利率互换.getValue())
//                    		&& !model.getPs().equals(DictCodeList.交易方向_到期.getValue()))
                    ||model.getPs().equals(DictCodeListExt2.交易方向_新股中签.getValue())
                    ||model.getPs().equals(DictCodeListExt2.交易方向_配股.getValue())
                    ||model.getPs().equals(DictCodeListExt2.交易方向_增发.getValue())) {

                model.setPs(DictCodeList.交易方向_买入.getValue());
            }
        }
        // 交易方向处理为卖出
        if ((StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券借贷融入.getValue())
                || StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_利率互换.getValue()))
                && model.getPs().equals(DictCodeList.交易方向_到期.getValue())){
            model.setPs(DictCodeList.交易方向_卖出.getValue());
        }
        // 需要占用/释放授信 组
        String[] arrisNeedOccupyCrePs = {DictCodeList.交易方向_卖出.getValue(),DictCodeList.交易方向_行权_回售.getValue(),
                DictCodeList.交易方向_赎回到账.getValue(),DictCodeList.交易方向_提前支取.getValue(),
                DictCodeList.交易方向_行权_赎回.getValue(),DictCodeList.交易方向_买入.getValue(),
                DictCodeList.交易方向_申购.getValue(),DictCodeList.交易方向_还本到账.getValue()
                , DictCodeListExt2.交易方向_认购.getValue(),DictCodeList.回购拆借方向_逆回购.getValue()
                ,DictCodeListExt2.交易方向_买卖.getValue()// ,DictCodeList.交易方向_赎回确认.getValue()
        };
        // 需要根据交易对手判断是否占用授信 组
        String[] arrisNeedOccupyCreCounter = {DictCodeList.交易方向_买入.getValue(),DictCodeList.交易方向_卖出.getValue(),
                DictCodeList.交易方向_行权_回售.getValue(),DictCodeList.交易方向_申购.getValue(),DictCodeListExt2.交易方向_认购.getValue(),
                DictCodeList.交易方向_赎回到账.getValue(),DictCodeList.交易方向_还本到账.getValue(),DictCodeList.交易方向_赎回确认.getValue(),
                DictCodeListExt2.交易方向_买卖.getValue(),DictCodeList.回购拆借方向_逆回购.getValue(),DictCodeList.回购拆借方向_拆出.getValue()};

        // 判断当前交易方向是否需要占用/释放授信  true：需要 false：不需要
        flagisNeedOccupyCrePs = Arrays.asList(arrisNeedOccupyCrePs).contains(model.getPs());

        // 判断是否要根据交易对手占用/释放授信  true：需要 false：不需要
        flagarrisNeedOccupyCreCounter = Arrays.asList(arrisNeedOccupyCreCounter).contains(model.getPs());
        //债券,计划类,项目类,存款类不判断交易对手
        if (flagarrisNeedOccupyCreCounter && (StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())
                || StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())
                || StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())
                || StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_存款.getValue()))){
            flagarrisNeedOccupyCreCounter = false;
        }
        //根据资产代码查询债券基本信息
        finProductCredit = creditLinesMapper.getAssetInfoByFinId(model.getFinprodId(),model.getTradeDate(),BussinessDictCodeList.行权方式_回售.getValue());
        //部分债券类型不占用授信
        String[] arrzqtype = {BussinessDictCodeList.债券类型_国债.getValue(),BussinessDictCodeList.地方政府债.getValue(),BussinessDictCodeList.央行票据.getValue()};
        // 债券类型 占用/释放授信 true:不占用 false 占用
        Boolean flagisNeedOccupyCrezqtype = false;
        // 根据债券类型判断是否占用
        if(finProductCredit!=null
                && model.getFinprodType2() != null
                && StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())) {
            flagisNeedOccupyCrezqtype = Arrays.asList(arrzqtype).contains(finProductCredit.getType6());
        }
        //不考虑授信情况 直接返回
        // 授信开关关
        if(StringUtil.equals(BussinessDictCodeList.监控授信开关_关.getValue(), CacheUtil.getSysParam(BussinessConstant.ISCREDIT))
                // 交易方向不占用/释放
                ||!flagisNeedOccupyCrePs
                // 不应占用的债券类型 不占用/释放
                ||flagisNeedOccupyCrezqtype
                // 质押式正回购 || 买断式正回购 || 拆入|| 债券借贷融出 不占用/释放
                ||(model.getFinprodType2() != null &&
                (StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())
                        ||StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue())
                        ||StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_拆入.getValue())
                        ||StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_特拆拆入.getValue())
                        ||StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券借贷融出.getValue()))
                // add by zcw 衍生品暂时不考虑授信 start
                ||StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券借贷.getValue())
                ||StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券借贷融入.getValue())
                ||StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_即期.getValue())
                ||StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_远期.getValue())
                ||StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_掉期.getValue())
                ||StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_利率互换.getValue())
                ||StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_期权.getValue())
                // add by zcw 衍生品暂时不考虑授信 end
        )
                // 撤销交易直接返回
                || DictCodeListExt1.交易状态_撤销.getValue().equals(model.getTradeStatus())
            //TISHKBS-342 允许利率互换、拆借拆出，外汇即期远期掉期
//                StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_拆出.getValue()) ||
//                StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_利率互换.getValue()) ||
//                StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_远期.getValue()) ||
//                StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_即期.getValue()) ||
//                StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_掉期.getValue()) ||

        ){
            return list;
        }
        // 买入卖出交易对手为内部则判断为转仓 转仓交易不走授信
        if(model.getCounterId() != null && creditLinesMapper.isNbDeal(model.getCounterId(),DictCodeListExt1.交易对手类型_市场机构.getValue())<1 &&
                (DictCodeList.交易方向_买入.getValue().equals(model.getPs()) || DictCodeList.交易方向_卖出.getValue().equals(model.getPs()))) {
            return list;
        }
        // 若不是底层资产交易并且不是存续期交易，则需要根据交易对手判断是否占用授信
        if(isNotBottomflag && flagarrisNeedOccupyCreCounter){
            //根据交易方向、是否内部交易判断 是否占用授信
            int count =creditLinesMapper.isNbDeal(model.getCounterId(),DictCodeListExt1.交易对手类型_市场机构.getValue());
            if(count<1) {
                return list;
            }
        }
        return null;
    }

    /**
     * 资产授信、系列授信
     * Add by xkni 2019-09-12
     * @return
     */
    @Override
    public List<CreditLines> getAssetsCeLineList() {
        List<CreditLines> creLineslist=new ArrayList<>();
        TrdProductDeal model=inputmodel.getTrdProductDeal();
        // 回购不占资产/系列授信。
        if(!(model.getFinprodType2() != null &&
                (StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())
                        || StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue())))){
            //授信参数传值方式修改 start
            Map<String,Object> mapParm = new HashMap<>();
            mapParm.put("dto", model);
            mapParm.put("ps1", DictCodeList.交易方向_买入.getValue());
            mapParm.put("ps4", DictCodeList.交易方向_申购.getValue());
            mapParm.put("ps52", DictCodeListExt2.交易方向_认购.getValue());
            mapParm.put("creditmstidZC", BussinessDictCodeList.授信配置代码_资产授信.getValue());
            mapParm.put("creditmstidXL", BussinessDictCodeList.授信配置代码_系列授信.getValue());
            creLineslist=creditLinesMapper.getCeLinesInfoforasset(mapParm);
            //授信参数传值方式修改 end
            if(model.getFinprodType2() != null && (StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue()))
                    && !(StringUtil.equals(model.getPs(), DictCodeList.交易方向_还本到账.getValue()))){
                inputmodel.getTrdProductDeal().setFinprodId(model.getTradeId());
            }
        }
        return  creLineslist;
    }
    /**
     * 机构授信
     * Add by xkni 2019-09-12
     * @return
     */
    @Override
    public List<CreditLines> getOrgCeLineList(HashMap<String, Object> map) {
        TrdProductDeal model=inputmodel.getTrdProductDeal();
        List<CreditLines> creLineslist= (List<CreditLines>) map.get("creLineslist");
        //是否占用主体授信flag
        Boolean iscreditflag=false;
        for(CreditLines creditLinesAsset:creLineslist){
            if(StringUtil.equals(DictCodeList.是否_是.getValue(),creditLinesAsset.getCreditFlag())){
                iscreditflag=true;
            }
            if(model.getFinprodType2() != null && (StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue()))){
                iscreditflag=true;
            }
        }

        //b.处理机构授信、品种授信
        //a的查询结果为0 或者（若有数据  则判断 是否占用主体授信=是 则查询机构授信及机构品种授信）
        List<CreditLines> orgcreLineslist=new ArrayList<CreditLines>();
        Map<String, Object> mapParm = new HashMap<>();
        // 没有资产/系列授信 || 有资产/系列授信 指令授信是否占用机构授信为是
        if((creLineslist==null || creLineslist.size()==0)||
                (creLineslist!=null && creLineslist.size()>0 && iscreditflag)) {
            String cemodel = CacheUtil.getSysParam(BussinessConstant.CEMODEL);
            //如果是机构或者品种授信，交易对手代码转换成市场代码
            String counterId = "";
            if (isNotBottomflag) {
                //交易
                if (flagarrisNeedOccupyCreCounter) {
                    counterId = creditLinesMapper.getPartyIdBycounterId(model.getCounterId(),
                            DictCodeListExt1.交易对手类型_市场机构.getValue());
                }
                //存续期交易
                else {
                    if ((model.getFinprodType2() != null && (StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())
                            || StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue())))) {
                        //回购存续期交易根据Finprodid查询交易对手
                        counterId = creditLinesMapper.getPartyIdByFinprodId(model.getFinprodId());
                    }
                }
                //add 20191019  股票取标的对应的公司名称 、
                if(StringUtil.isNotEmpty(model.getFinprodType2())&&model.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_股票.getValue()))
                {
                    counterId=creditLinesMapper.getCounterIdbyFinprodId(model.getFinprodId());
                }
                model.setCounterId(counterId);
            }
            //授信参数传值方式修改 start

            mapParm.put("dto", model);
            mapParm.put("ps1", DictCodeList.交易方向_买入.getValue());
            mapParm.put("ps4", DictCodeList.交易方向_申购.getValue());
            mapParm.put("ps52", DictCodeListExt2.交易方向_认购.getValue());
            mapParm.put("creditmstidJG", BussinessDictCodeList.授信配置代码_机构授信.getValue());
            mapParm.put("fzq", DictCodeList.平安资产类型_债券.getValue());
            mapParm.put("fck", DictCodeList.平安资产类型_存款.getValue());
            mapParm.put("fxml", DictCodeList.平安资产类型_项目类资产.getValue());
            mapParm.put("fhg", DictCodeList.平安资产类型_质押式回购.getValue());
            //授信参数传值方式修改 end

            // 质押式逆回购||买断式逆回购
            if ((model.getFinprodType2() != null && (StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())
                    || StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue())))) {
                String repType = DictCodeList.回购类型_买断式回购.getValue();
                if (StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())) {
                    repType = inputmodel.getRepoType();
                    // repType 为空时（银行间质押式回购）默认为质押式协议回购
                    if (!StringUtil.isNotEmpty(repType)) {
                        repType = DictCodeList.回购类型_质押式协议回购.getValue();
                    }
                }
                //授信参数传值方式修改 start
                mapParm.put("repType", repType);
                //预占用授权额度管理
                orgcreLineslist = creditLinesMapper.getCeLinesInfoforOrg01Rep(mapParm);
                //授信参数传值方式修改 end
                // 拆借拆出
            }else if(model.getFinprodType2() != null && StringUtil.equals(model.getInputValue1(), DictCodeList.平安资产类型_拆借.getValue())) {
                // 目前只有普通拆解为拆出
                String lendType = DictCodeList.拆借类型_普通拆借.getValue();
                //授信参数传值方式修改 start
                mapParm.put("lendType", lendType);
                //预占用授权额度管理
                orgcreLineslist = creditLinesMapper.getCeLinesInfoforOrg01Lend(mapParm);
                //授信参数传值方式修改 end
            } else {
                //如果系统参数.授信模式==01
                if ("01".equals(cemodel)) {
                    orgcreLineslist = creditLinesMapper.getCeLinesInfoforOrg01(mapParm);
                }else {
                    orgcreLineslist = creditLinesMapper.getCeLinesInfoforOrg02(mapParm);
                }
            }
        }
        if (StringUtil.isEmptyList(orgcreLineslist) && iscreditflag){
            isKDQ = true;
            customerid = creditLinesMapper.getCustomerid(mapParm);
        }else{
            isKDQ = false;
            customerid = "";
        }
        return  orgcreLineslist;
    }

    /**
     * 品种授信
     * Add by xkni 2019-09-12
     * @return
     */
    @Override
    public List<CreditLines> getVarietiesCeLineList(HashMap<String, Object> map) {
        //参数传入
        List<CreditLines> list= (List<CreditLines>) map.get("creLineslist");
        if(list!=null && list.size()>0)
        {
            carietiesCreList=list;
        }
        return list;
    }


    /**
     * 占用、释放授信额度数据逻辑共通处理
     * Add by xkni 2019-09-12
     * @return
     */
    @Override
    public List<CreditLinesDealCommon> occupancyOrRelease(HashMap<String, Object> map) {
        List<CreditLines> creLineslist= (List<CreditLines>) map.get("creLineslist");
        //交易相关信息model
        TrdProductDeal model = inputmodel.getTrdProductDeal();
        List<CreditLinesDealCommon> list = new ArrayList<>();
        return tradeOccupancyOrRelease(model,common,list,creLineslist,isdeleteflag,isSave,isNotBottomflag,flagarrisNeedOccupyCreCounter);
    }

    private List<CreditLinesDealCommon> tradeOccupancyOrRelease(TrdProductDeal model, CreditLinesDealCommon common, List<CreditLinesDealCommon> list, List<CreditLines> creLineslist, Boolean isdeleteflag, boolean isSave, boolean isNotBottomflag,boolean flagarrisNeedOccupyCreCounter)
    {
        //依据行方实际业务需求，配置对于无授信的资产提交时是否进行监控；若不监控，则提交时不做判断
        boolean isCreditSoftControl = Boolean.parseBoolean(CacheUtil.getSysParam("IS_CREDIT_SOFTCONTROL"));
        if(isNotBottomflag){
            // 授信为Y 没有配置授信时
            if(creLineslist == null || creLineslist.size() < 1){
                if(!isSave||!isCreditSoftControl) {
                    if ("Y".equals(CacheUtil.getSysParam(BussinessConstant.ISNEEDCONTROLCREDIT))) {
                        common.setErrMessage(getMessage("BUSS_COM_CRE_001"));
                        list.add(common);
                    }
                    return list;
                }
            }
        }
        CreditLinesDeal creditLinesDeal=new CreditLinesDeal();
        //2.查询风险系数,资产类型
        RiskWeight rskDTO = new RiskWeight();
        Map<String, Object> params = new HashMap<>();
        if (StringUtil.equals(model.getInputValue1(), DictCodeList.平安资产类型_拆借.getValue())){
            params.put("type1", model.getInputValue1());
            params.put("type2", model.getInputValue3());
            params.put("tradedate", model.getTradeDate());
            rskDTO = creditLinesMapper.getRskByAssetType(params);
        } else if (StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())){
            params.put("type1", DictCodeList.平安资产类型_质押式回购.getValue());
            params.put("type2", model.getInputValue2());
            params.put("tradedate", model.getTradeDate());
            rskDTO = creditLinesMapper.getRskByAssetType(params);
        } else if (StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue())){
            params.put("type1", DictCodeList.平安资产类型_买断式回购.getValue());
            params.put("tradedate", model.getTradeDate());
            rskDTO = creditLinesMapper.getRskByAssetType(params);
        }  else if (StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_股票.getValue())){
            params.put("type1", DictCodeListExt2.平安资产类型_股票.getValue());
            params.put("tradedate", model.getTradeDate());
            rskDTO = creditLinesMapper.getRskByAssetType(params);
        } else {
            rskDTO=creditLinesMapper.getRsk(model.getFinprodId(),model.getTradeDate());
        }
        String type8=creditLinesMapper.getTypeByFinprodId(model.getFinprodId());
        //3.占用/释放授信额度
        //a.计算 占用/释放额度
        //如果 rskDTO.分类8 in （参数字典.平安资产类型.债券，参数字典.平安资产类型.买断式回购）
        //待处理额度
        BigDecimal todocredit_amt =BigDecimal.ZERO;


        if(StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue())){
            // 买断式回购逆回购买入保存时从质押品取
            if(StringUtil.equals(model.getPs(), DictCodeList.交易方向_买入.getValue()) && isSave){
                //买断式回购逆回购买入质押式抵押品表中取
                todocredit_amt=creditLinesMapper.getPledgeNum(model.getFinprodId());
            }
            // 逆回购交易流程中只能从这取到券面
            else{
                //待处理额度从质押式抵押品表中取
                todocredit_amt =model.getPrinAmt();
            }
        }else if(StringUtil.equals(type8, DictCodeList.平安资产类型_债券.getValue())){
            //最新面值
            BigDecimal newfaceValue=creditLinesMapper.getFaceValue(model);
            if(flagarrisNeedOccupyCreCounter || StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())){
                //待处理额度=最新面值*(参数.金融产品交易DTO.交易份额，债券数量)*rskDTO.风险权数
                //债券借贷没除100
                if (StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券借贷融入.getValue())){
                    todocredit_amt=(newfaceValue==null?BigDecimal.valueOf(100):newfaceValue).multiply(model.getShareAmt().divide(BigDecimal.valueOf(100)));
                } else {
                    if(isSave) {
                        todocredit_amt=(newfaceValue==null?BigDecimal.valueOf(100):newfaceValue).multiply(model.getShareAmt());

                    }else {
                        todocredit_amt=(newfaceValue==null?BigDecimal.valueOf(100):newfaceValue).multiply(model.getTradeAmt()).divide(BigDecimal.valueOf(100));
                    }
                }
            }else{
                if (StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券借贷融入.getValue())){
                    todocredit_amt=model.getShareAmt();
                } else {
                    if(isSave) {
                        todocredit_amt=model.getShareAmt().multiply(BigDecimal.valueOf(100));

                    }else {
                        todocredit_amt=model.getTradeAmt();
                    }
                }
            }
            if(model.getPs().equals(DictCodeList.回购拆借方向_逆回购.getValue())){
                todocredit_amt=model.getPrinAmt().multiply(newfaceValue==null?BigDecimal.valueOf(100):newfaceValue).divide(BigDecimal.valueOf(100));
            }
            //还本时按还本面值计算  20190905 add by xkni
            if(model.getPs().equals(DictCodeList.交易方向_还本到账.getValue()))
            {
                todocredit_amt=model.getPrinAmt();
            }
        }
        else if(StringUtil.equals(model.getInputValue1(), DictCodeList.平安资产类型_计划类资产_净值型.getValue())
                ||StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_股票.getValue())){
            todocredit_amt =model.getTradeAmt();
        }
        else if (StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_利率互换.getValue())){
            todocredit_amt=model.getShareAmt() == null?BigDecimal.ZERO:model.getShareAmt();
        }
        /*else if(StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_存款.getValue())||StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())||StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())||StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_基金.getValue())||StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())) {
            todocredit_amt =model.getPrinAmt();//本金金额
        }
        //j)	衍生品资产
        else if(StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_即期.getValue())||StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_远期.getValue())||StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_掉期.getValue())||StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_利率互换.getValue())) {
            //	todocredit_amt =model.getPrinAmt();//本币名义本金
        }*/
        else{
            //待处理额度=参数.金融产品交易DTO.交易本金*rskDTO.风险权数
            todocredit_amt = model.getPrinAmt() == null? BigDecimal.ZERO : model.getPrinAmt();
        }
        if(rskDTO != null && (rskDTO.getRiskWeight() != null)){
            todocredit_amt=todocredit_amt.multiply(rskDTO.getRiskWeight());
        }

        //b.根据交易方向&交易状态 判断占用/释放交易方向_卖出
        String[] arrps = {DictCodeList.交易方向_卖出.getValue(),DictCodeList.交易方向_行权_回售.getValue(),
                DictCodeList.交易方向_赎回到账.getValue(),DictCodeList.交易方向_提前支取.getValue(),
                DictCodeList.交易方向_行权_赎回.getValue(),DictCodeList.交易方向_还本到账.getValue()};
        // b.根据交易方向&交易状态 判断占用/释放交易方向_买入
        String[] arrps1 = {DictCodeList.交易方向_买入.getValue(),DictCodeList.交易方向_申购.getValue(),DictCodeListExt2.交易方向_认购.getValue()};
        // 释放
        Boolean flagps = Arrays.asList(arrps).contains(model.getPs());
        // 占用
        Boolean flagps1 = Arrays.asList(arrps1).contains(model.getPs());

        //交易方向为卖出初始状态时 或者买入撤销时候 要退回原占用的额度；交易方向为买入撤销时要释放原占用的额度  edit by xkni 20190830
        // 卖出初始 || 撤销买入 为释放
        if((DictCodeListExt1.交易状态_初始.getValue().equals(model.getTradeStatus()==null?DictCodeListExt1.交易状态_初始.getValue():model.getTradeStatus()) && flagps )
                ||(DictCodeListExt1.交易状态_撤销.getValue().equals(model.getTradeStatus()) && flagps1)){
            //待处理额度=待处理额度*-1(释放原占用)
            todocredit_amt=todocredit_amt.negate();
        }

        //已占用额度
        BigDecimal hapCreditAmt=BigDecimal.ZERO;
        //预占用额度
        BigDecimal prehapamt =BigDecimal.ZERO;
        //预占用额度
        BigDecimal pierceAmt =BigDecimal.ZERO;
        // 授信足够标识
        Boolean isEnoughCreditflag=true;
        List<String> seqnoList =this.seqnoList();
        List<CreditLinesDetail> listlist =new ArrayList<>();
        List<CreditLinesPierce> pierceList =
                creditLinesMapper.getPierceListByTradeDate(DateUtil.format(model.getTradeDate()));
        Map<String,List<CreditLinesPierce>> pierceMap = pierceList.stream().collect(
                JoyinCollectors.groupingBy(item->item.getCreditMstId()+":"+item.getCustomerId()));
        for(CreditLines creditLines:creLineslist){
            Map<String,Object> mapforprehapamt = new HashMap<>();
            mapforprehapamt.put("creditMstId",creditLines.getCreditMstId());
            mapforprehapamt.put("customerId",creditLines.getCustomerId());
            mapforprehapamt.put("tradedate",model.getTradeDate());
            //预占用额度
            prehapamt = creditLinesMapper.getPrehapamtForPingan(mapforprehapamt);
            //  hapCreditAmt=creditLines.getHapCreditAmt();
            // 已占用额度
            hapCreditAmt=creditLines.getHapCreditAmt().add(prehapamt==null?BigDecimal.ZERO:prehapamt);
            // 算上本次已占用额度=（已占用额度+待处理额度）>0?（已占用额度+待处理额度）:0
            hapCreditAmt = BigDecimal.ZERO.compareTo(hapCreditAmt.add(todocredit_amt)) < 0 ? hapCreditAmt.add(todocredit_amt):BigDecimal.ZERO;
            //华商个性化 算上底层资产占用额度 2020年12月15日 start
            if (StringUtil.isNotEmpty(pierceMap) && StringUtil.isNotEmpty(pierceMap.get(creditLines.getCreditMstId()+":"+creditLines.getCustomerId()))) {
                pierceAmt =  pierceMap.get(creditLines.getCreditMstId()+":"+creditLines.getCustomerId()).get(0).getCreditAmt();
            }
            //华商个性化 算上底层资产占用额度 2020年12月15日 end
            //新增只有买入或申购时候额度监控 by nixiukuan 20191009
            //修复【二级法人-机构授信】当交易额度超过机构授信总额度之后，没有占用授信额度BUG by jqq start
            if(StringUtil.isNotEmpty(creditLines.getPflsh()) && !creditLines.getPflsh().startsWith("空档期授信") && ArithmeticUtil.doAdd(hapCreditAmt,pierceAmt).compareTo(creditLines.getCreditAmt())>0 && flagps1 && !isSave){
                //提示 授信额度不够,并且直接返回提示语，不再进行下次循环
                isEnoughCreditflag=false;
                if(Boolean.parseBoolean(CacheUtil.getSysParam("IS_CREDIT_SOFTCONTROL"))) {
                	common.setErrMessage(getMessage("BUSS_COM_CRE_005"));
                }
                else {
                	common.setErrMessage(getMessage("BUSS_COM_CRE_002"));
                }
                list.add(common);
                return list;
            }
//            //批复号为空，未空档期，应该提示无授信
//            if((StringUtil.isEmpty(creditLines.getPflsh()) || creditLines.getPflsh().equals("空档期授信"))&&hapCreditAmt.compareTo(creditLines.getCreditAmt())>0 && flagps1 && !isSave){
//                isEnoughCreditflag=false;
//                common.setErrMessage(getMessage("BUSS_COM_CRE_001"));
//                list.add(common);
//                return list;
//            }
            //修复【二级法人-机构授信】当交易额度超过机构授信总额度之后，没有占用授信额度BUG by jqq end
            if(!creditLines.getCreditMstId().equals(BussinessDictCodeList.授信配置代码_资产授信.getValue())
                    &&!creditLines.getCreditMstId().equals(BussinessDictCodeList.授信配置代码_机构授信.getValue())
                    &&!creditLines.getCreditMstId().equals(BussinessDictCodeList.授信配置代码_系列授信.getValue())) {
                //额度检验
                listlist.add(this.checkOrgDetailAmt(seqnoList, creditLines, model, todocredit_amt,pierceAmt));
                //期限检验
                listlist.add(this.checkOrgDetailTerm(seqnoList, creditLines, model));
            }

        }
        for(CreditLinesDetail detail:listlist){
            if (StringUtil.equals(detail.getCheckresult(), "nopass")) {
                common.setCreditresult("nopass");
                isEnoughCreditflag = false;
                if(Boolean.parseBoolean(CacheUtil.getSysParam("IS_CREDIT_SOFTCONTROL"))) {
                	common.setErrMessage(getMessage("BUSS_COM_CRE_005"));
                }
                else {
                	common.setErrMessage(getMessage("BUSS_COM_CRE_002"));
                }
                break;
            } else {
                isEnoughCreditflag = true;
                common.setCreditresult("pass");
            }
        }
        if(common.getCreditresult()!=null&&common.getCreditresult().equals("nopass")) {
            //  common.setCreditLinesdetail(listlist);
            list.add(common);
        }
        //额度足够的情况
        if(isEnoughCreditflag){
            if(!isKDQ) {
                for (CreditLines creditLines : creLineslist) {
                    if (isSave) {
                        int count = creditLinesMapper.selectCreditDealCount(creditLines.getSeqno(), model.getTradeId());
                        if (count > 0) {
                            continue;
                        }
                    }
                    hapCreditAmt = creditLines.getHapCreditAmt();
                    // 超额释放时避免已使用金额为负值
                    if (BigDecimal.ZERO.compareTo(hapCreditAmt.add(todocredit_amt)) > 0) {
                        todocredit_amt = hapCreditAmt.negate();
                    }
                    //已占用额度=（已占用额度+待处理额度）>0?（已占用额度+待处理额度）:0
                    hapCreditAmt = BigDecimal.ZERO.compareTo(hapCreditAmt.add(todocredit_amt)) < 0 ? hapCreditAmt.add(todocredit_amt) : BigDecimal.ZERO;
                    //1.更新额度管理中 已占用额度
                    creditLines.setHapCreditAmt(hapCreditAmt);
                    creditLines.setUpdateTime(model.getUpdateTime());
                    creditLines.setUpdateUser(model.getUpdateUser());
                    if (isSave && !DictCodeListExt1.交易状态_撤销.getValue().equals(model.getTradeStatus())) {
                        creditLinesMapper.updateLinesByPrimaryKeySelective(creditLines);
                    }
                    if (isNotBottomflag) {
                        //2.0 存额度占用明细表
                        creditLinesDeal.setSeqno(creditLines.getSeqno());
                        creditLinesDeal.setTradeId(model.getTradeId());
                        //占用类型（实际占用、预占用）
                        creditLinesDeal.setCreditOccureType("01");
                        creditLinesDeal.setTradeAmt(todocredit_amt);
                        creditLinesDeal.setTradeDate(model.getTradeDate());
                        creditLinesDeal.setCreateDept(model.getCreateDept());
                        creditLinesDeal.setCreateTime(model.getCreateTime());
                        creditLinesDeal.setCreateUser(model.getCreateUser());
                        creditLinesDeal.setUpdateTime(model.getUpdateTime());
                        creditLinesDeal.setUpdateUser(model.getUpdateUser());
                        if (isSave && !DictCodeListExt1.交易状态_撤销.getValue().equals(model.getTradeStatus())) {
                            if (null != model.getTradeStatus() && (DictCodeListExt1.交易状态_撤销.getValue().equals(model.getTradeStatus()))) {
                                String tradeid = model.getTradeId();
                                if (DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue().equals(model.getFinprodType2())
                                        || DictCodeListExt1.金融产品类型_投管用_基金.getValue().equals(model.getFinprodType2())) {
                                    tradeid = model.getTradeId().substring(0, model.getTradeId().length() - 1);
                                }
                                //删除原占用的
                                creditLinesMapper.deleteLinesDealByTradeid(creditLines.getSeqno(), tradeid);
                            } else {
                                creditLinesMapper.insertLinesDeal(creditLinesDeal);
                            }
                        }
                        //
                        if (pierceAmt != null && pierceAmt.compareTo(BigDecimal.ZERO) > 0) {
                            //                        setCreditLinesSecPosition(model,isSave,pierceAmt,creditLines);
                        }
                    } else {
                        //2.1 存授信额度底层持仓关联表
                        CreditLinesDealCommon creditLinesDealCommon = new CreditLinesDealCommon();
                        creditLinesDeal.setTradeAmt(hapCreditAmt);
                        creditLinesDeal.setSeqno(creditLines.getSeqno());
                        creditLinesDeal.setCreditOccureType("01");
                        list.add(creditLinesDealCommon);
                    }
                }
            }else if(isKDQ && isSave){
                // 原授信占用 start
                for (CreditLines creditLines : creLineslist) {
                    if (isSave) {
                        int count = creditLinesMapper.selectCreditDealCount(creditLines.getSeqno(), model.getTradeId());
                        if (count > 0) {
                            continue;
                        }
                    }
                    hapCreditAmt = creditLines.getHapCreditAmt();
                    // 超额释放时避免已使用金额为负值
                    if (BigDecimal.ZERO.compareTo(hapCreditAmt.add(todocredit_amt)) > 0) {
                        todocredit_amt = hapCreditAmt.negate();
                    }
                    //已占用额度=（已占用额度+待处理额度）>0?（已占用额度+待处理额度）:0
                    hapCreditAmt = BigDecimal.ZERO.compareTo(hapCreditAmt.add(todocredit_amt)) < 0 ? hapCreditAmt.add(todocredit_amt) : BigDecimal.ZERO;
                    //1.更新额度管理中 已占用额度
                    creditLines.setHapCreditAmt(hapCreditAmt);
                    creditLines.setUpdateTime(model.getUpdateTime());
                    creditLines.setUpdateUser(model.getUpdateUser());
                    if (isSave && !DictCodeListExt1.交易状态_撤销.getValue().equals(model.getTradeStatus())) {
                        creditLinesMapper.updateLinesByPrimaryKeySelective(creditLines);
                    }
                    if (isNotBottomflag) {
                        //2.0 存额度占用明细表
                        creditLinesDeal.setSeqno(creditLines.getSeqno());
                        creditLinesDeal.setTradeId(model.getTradeId());
                        //占用类型（实际占用、预占用）
                        creditLinesDeal.setCreditOccureType("01");
                        creditLinesDeal.setTradeAmt(todocredit_amt);
                        creditLinesDeal.setTradeDate(model.getTradeDate());
                        creditLinesDeal.setCreateDept(model.getCreateDept());
                        creditLinesDeal.setCreateTime(model.getCreateTime());
                        creditLinesDeal.setCreateUser(model.getCreateUser());
                        creditLinesDeal.setUpdateTime(model.getUpdateTime());
                        creditLinesDeal.setUpdateUser(model.getUpdateUser());
                        if (isSave && !DictCodeListExt1.交易状态_撤销.getValue().equals(model.getTradeStatus())) {
                            if (null != model.getTradeStatus() && (DictCodeListExt1.交易状态_撤销.getValue().equals(model.getTradeStatus()))) {
                                String tradeid = model.getTradeId();
                                if (DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue().equals(model.getFinprodType2())
                                        || DictCodeListExt1.金融产品类型_投管用_基金.getValue().equals(model.getFinprodType2())) {
                                    tradeid = model.getTradeId().substring(0, model.getTradeId().length() - 1);
                                }
                                //删除原占用的
                                creditLinesMapper.deleteLinesDealByTradeid(creditLines.getSeqno(), tradeid);
                            } else {
                                creditLinesMapper.insertLinesDeal(creditLinesDeal);
                            }
                        }
                        //
                        if (pierceAmt != null && pierceAmt.compareTo(BigDecimal.ZERO) > 0) {
                            //                        setCreditLinesSecPosition(model,isSave,pierceAmt,creditLines);
                        }
                    } else {
                        //2.1 存授信额度底层持仓关联表
                        CreditLinesDealCommon creditLinesDealCommon = new CreditLinesDealCommon();
                        creditLinesDeal.setTradeAmt(hapCreditAmt);
                        creditLinesDeal.setSeqno(creditLines.getSeqno());
                        creditLinesDeal.setCreditOccureType("01");
                        list.add(creditLinesDealCommon);
                    }
                }
                // 原授信占用 end

                // 空档期授信
                // 空档期占用机构授信
                CreditLines creditLines = new CreditLines();
                String customerId = customerid;
                String seqNo = "";
                if (baseGetBusinessId != null) {
                    BussinessCodeParameter codeParameter = new BussinessCodeParameter();
//                    if(StringUtil.equals(model.getInputValue1(), DictCodeList.平安资产类型_拆借.getValue())
//                            ||StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())
//                            ||StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue())) {
//                        codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_机构授信);
//                    }else {
//                        codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_资产授信);
//                    }
                    codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_机构授信);
                    seqNo = baseGetBusinessId.buildBussinessCode(codeParameter);
                }
                else {
                    seqNo= IDGenerator.generate();
                }
                creditLines.setSeqno(seqNo);
                creditLines.setCreditAmt(BigDecimal.ZERO);
//                if(StringUtil.equals(model.getInputValue1(), DictCodeList.平安资产类型_拆借.getValue())
//                        ||StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())
//                        ||StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue())) {
//                    creditLines.setCreditMstId("0001");
//                    customerId = model.getCounterId();
//                    creditLines.setCustomerId(customerId);
//                    creditLines.setCreditFlag("Y");
//                }else {
//                    creditLines.setCreditMstId("0002");
//                    creditLines.setCustomerId(customerId);
//                    creditLines.setParentCustomerId(creditLines.getCustomerId());
//                    creditLines.setCreditFlag("N");
//                }
                creditLines.setCreditMstId("0001");
                creditLines.setCustomerId(customerId);
                creditLines.setCreditFlag("Y");
                creditLines.setVdate(model.getTradeDate());
                creditLines.setMdate(DateUtil.parse("2099-12-31"));
                creditLines.setPflsh("空档期授信"+IDGenerator.generate().substring(0, 30));
                creditLines.setCreateDept(model.getCreateDept());
                creditLines.setCreateTime(model.getCreateTime());
                creditLines.setCreateUser(model.getCreateUser());
                creditLines.setUpdateTime(model.getUpdateTime());
                creditLines.setUpdateUser(model.getUpdateUser());
                String institutionCode = "";
                if(MultilevelDataAuthorityUtil.isEnabled()){
                    institutionCode =  MultilevelDataAuthorityUtil.getOrganizationCode(LoginUserUtil.getUserName());
                }
                creditLines.setInstitutionCode(institutionCode);
                creditLines.setHapCreditAmt(todocredit_amt.add(extendCreditLinesDeal(creditLines.getSeqno(), creditLines.getVdate(), creditLines.getCreditMstId(),creditLines.getCustomerId(), institutionCode)));
                creditMapper.insert(creditLines);
                if(isNotBottomflag){
                    //2.0 存额度占用明细表
                    creditLinesDeal.setSeqno(seqNo);
                    creditLinesDeal.setTradeId(model.getTradeId());
                    //占用类型（实际占用、预占用）
                    creditLinesDeal.setCreditOccureType("01");
                    creditLinesDeal.setTradeAmt(todocredit_amt);
                    creditLinesDeal.setTradeDate(model.getTradeDate());
                    creditLinesDeal.setCreateDept(model.getCreateDept());
                    creditLinesDeal.setCreateTime(model.getCreateTime());
                    creditLinesDeal.setCreateUser(model.getCreateUser());
                    creditLinesDeal.setUpdateTime(model.getUpdateTime());
                    creditLinesDeal.setUpdateUser(model.getUpdateUser());
                    if(isSave){
                        if(isdeleteflag){
                            if(null != model.getTradeStatus() && BussinessDictCodeList.交易状态_初始.getValue().equals(model.getTradeStatus()) && flagps1){
                                //删除原占用的
                                creditLinesMapper.deleteLinesDealByTradeid(seqNo,model.getTradeId());
                            }
                        }else{
                            //插入一条记录
                            creditLinesMapper.insertLinesDeal(creditLinesDeal);
                        }
                    }
                }else{
                    //2.1 存授信额度底层持仓关联表
                    CreditLinesDealCommon creditLinesDealCommon=new CreditLinesDealCommon();
                    creditLinesDeal.setTradeAmt(hapCreditAmt);
                    creditLinesDeal.setSeqno(seqNo);
                    creditLinesDeal.setCreditOccureType("01");
                    list.add(creditLinesDealCommon);
                }
            }
        }
        return list;
    }

    /**
     * 授信额度底层持仓关联表
     *
     * @param model 交易
     * @param isSave 是否保存
     * @param pierceAmt 金额
     * @param creditLines 授信信息
     */
    private void setCreditLinesSecPosition(TrdProductDeal model, boolean isSave, BigDecimal pierceAmt, CreditLines creditLines) {
        CreditLinesSecPosition creditLinesSecPosition=new CreditLinesSecPosition();
        //2.0 存额度占用明细表
        creditLinesSecPosition.setSeqno(creditLines.getSeqno());
        //占用类型（实际占用、预占用）
        creditLinesSecPosition.setCreditOccureType("01");
        creditLinesSecPosition.setTradeAmt(pierceAmt);
        creditLinesSecPosition.setValDate(model.getTradeDate());
        creditLinesSecPosition.setFinprodId(model.getFinprodId());
        creditLinesSecPosition.setCreateDept(model.getCreateDept());
        creditLinesSecPosition.setCreateTime(model.getCreateTime());
        creditLinesSecPosition.setCreateUser(model.getCreateUser());
        creditLinesSecPosition.setUpdateTime(model.getUpdateTime());
        creditLinesSecPosition.setUpdateUser(model.getUpdateUser());
        if(isSave && !DictCodeListExt1.交易状态_撤销.getValue().equals(model.getTradeStatus())){
            if(null != model.getTradeStatus() && (DictCodeListExt1.交易状态_撤销.getValue().equals(model.getTradeStatus()))){
                //删除原占用的
                creditLinesMapper.deleteLinesSecPositionBySeqno(creditLines.getSeqno());
            }
            else
            {
//                creditLinesMapper.deleteLinesSecPositionBySeqno(creditLines.getSeqno());
                creditLinesMapper.insertLinesSecPosition(creditLinesSecPosition);
            }
        }
    }

    /**
     * 检查分项授信额度是否通过
     *
     * @param seqnoList
     * @param creLinesmst 分项授信记录
     * @param model 交易model
     * @param todocredit_amt 待处理额度
     * @return CreditLinesDetail 检验结果
     */
    private CreditLinesDetail checkOrgDetailAmt(List<String> seqnoList, CreditLines creLinesmst, TrdProductDeal model, BigDecimal todocredit_amt,BigDecimal pierceAmt) {

        CreditLinesDetail creditLinesDetailamt =new CreditLinesDetail();
        //查询机构的品种授信
        BigDecimal prehapamt =BigDecimal.ZERO;
        BigDecimal converttemp = new BigDecimal("100000000");
        //额度检验

        Map<String,Object> mapforprehapamt = new HashMap<>();
        mapforprehapamt.put("creditMstId",creLinesmst.getCreditMstId());
        mapforprehapamt.put("customerId",creLinesmst.getCustomerId());
        mapforprehapamt.put("tradedate",model.getTradeDate());
        //根据授信配置代码、机构代碼、日期查询预占用额度
        prehapamt = creditLinesMapper.getPrehapamtForPingan(mapforprehapamt);
        //额度
        //根据品种授信授信配置代码查询品种授信名称
        String mstName= creditLinesMapper.getMstName(creLinesmst.getCreditMstId());
        creditLinesDetailamt.setCredittype("["+getMessage("TrdBondInvestTradeCheck.creditamt")+"]"+mstName+"(亿元)");
        creditLinesDetailamt.setCheckscope(creLinesmst.getCreditAmt().divide(converttemp).toString());
        creditLinesDetailamt.setStockvalue(creLinesmst.getHapCreditAmt()==BigDecimal.ZERO?"":creLinesmst.getHapCreditAmt().divide(converttemp).toString());
        creditLinesDetailamt.setPrehapamt(prehapamt == null?"":prehapamt.divide(converttemp).toString());
        //债券属于此品种才显示本次交易信息

        if(!seqnoList.isEmpty()&&seqnoList.contains(creLinesmst.getCreditMstId())){
            creditLinesDetailamt.setThistrans(todocredit_amt.divide(converttemp).toString());
            if(creLinesmst.getCreditAmt().compareTo(
                    creLinesmst.getHapCreditAmt().add(todocredit_amt).add(prehapamt== null?BigDecimal.ZERO:prehapamt)
                            .add(pierceAmt== null?BigDecimal.ZERO:pierceAmt))>=0){
                creditLinesDetailamt.setCheckresult("pass");
            }else{
                creditLinesDetailamt.setCheckresult("nopass");
            }
        }

        return creditLinesDetailamt;
    }

    /**
     * 检查分项授信期限是否通过
     *
     * @param seqnoList
     * @param creLinesmst 分项授信记录
     * @param model 交易model
     * @return CreditLinesDetail 检验结果
     */
    private CreditLinesDetail checkOrgDetailTerm(List<String> seqnoList, CreditLines creLinesmst, TrdProductDeal model) {
        //期限
        CreditLinesDetail creditLinesDetailterm=new CreditLinesDetail();
        //期限检验
        //根据品种授信授信配置代码查询品种授信名称
        String mstName= creditLinesMapper.getMstName(creLinesmst.getCreditMstId());
        creditLinesDetailterm.setCredittype("["+getMessage("TrdBondInvestTradeCheck.creditterm")+"]"+mstName+"(年)");
        creditLinesDetailterm.setCheckscope(creLinesmst.getTerm()==null?"":creLinesmst.getTerm().toString());
        if(!seqnoList.isEmpty()&&seqnoList.contains(creLinesmst.getCreditMstId())){
            creditLinesDetailterm.setThistrans(DateUtil.format(finProductCredit.getFinProduct().getMdate()));
            //交易日期+批复的投资期限(年)〉=债券到期日(若为行权债，债券的含权条款为回售，则为回售日，其余均为到期日)
            if(DateUtil.compareTo(finProductCredit.getFinProduct().getMdate(), DateUtil.addYear(model.getTradeDate(),creLinesmst.getTerm().intValue()))<=0){
                creditLinesDetailterm.setCheckresult("pass");
            }else{
                creditLinesDetailterm.setCheckresult("nopass");
            }
        }

        return creditLinesDetailterm;
    }

    /**
     * 根据债券基本信息查询可能占用的品种授信
     * @return 品种授信seqno
     */
    private List<String> seqnoList(){
        List<String> seqnoList = new ArrayList<String>();
        List<CreditLines> varietiesCeLineList = carietiesCreList;
        if(varietiesCeLineList!=null&&varietiesCeLineList.size()>0)
        {
            varietiesCeLineList.forEach(item->seqnoList.add(item.getSeqno()));
        }
        return seqnoList;
    }
    /**
     * 占用、释放授信额度共通处理
     * @param inputmodel 交易model
     * @param isNotBottomflag 是否是底层资产交易
     * @param isSave 是否入库
     * @param isdeleteflag 是否是作废表示//TODO
     * @return
     */
    @Override
    @Transactional(rollbackFor=Exception.class, propagation = Propagation.REQUIRES_NEW)
    public List<CreditLinesDealCommon> isNeedOccupyCre(AllTradeCommonInputDto inputmodel, boolean isNotBottomflag, boolean isSave, Boolean isdeleteflag) {
        // 入参进行map组装
        List<CreditLinesDealCommon> list = new ArrayList<CreditLinesDealCommon>();
        List<CreditLines> creLineslist=new ArrayList<CreditLines>();
        // 授信列表map分为1.资产/系列授信；2.机构授信;3.品种授信
        HashMap<String, Object> creLinesMap=new HashMap<>();
        // 额度占用释放map
        HashMap<String, Object> map=new HashMap<>();//额度占用释放map
        // 判断是否占用释放授信
        list= checkData();
        // list is null 表示状态check通过
        if(list==null)
        {
            //资产，资产系列授信
            creLineslist=getAssetsCeLineList();
            creLinesMap.put("creLineslist",creLineslist);
            //机构授信
            creLineslist.addAll(getOrgCeLineList(creLinesMap));
            //添加品种授信列表
            List<CreditLines> varietiesCeLineList = carietiesCreList;
            if(varietiesCeLineList!=null&&varietiesCeLineList.size()>0) {
                creLineslist.addAll(varietiesCeLineList);
            }

        }
        else
        {
            //返回校验不通过的错误信息
            return  list;
        }

        // 额度占用或释放业务逻辑处理，数据入库及数据列表内部check
        map.put("creLineslist",filterNewCreditLines(creLineslist));
        list=this.occupancyOrRelease(map);
        return list;
    }

    @Override
    public List<ValidMessage> valid(Object value) {
        return null;
    }

    /**
     * 过滤多条授信最新一条，以CreditMstId&&CustomerId维度
     * @param creLineslist
     * @return
     */
    public List<CreditLines> filterNewCreditLines(List<CreditLines> creLineslist) {
        Map<String, CreditLines> creLinesMap = new HashMap<String, CreditLines>();
        for (CreditLines creditLines : creLineslist) {
            // map中没有此项授信 || 当前一条授信创建时间新于map中授信：重新添加||更新最新授信
            if (creLinesMap.get(creditLines.getCreditMstId()+"|"+creditLines.getCustomerId()) == null
                    || creditLines.getCreateTime().compareTo(creLinesMap.get(creditLines.getCreditMstId()+"|"+creditLines.getCustomerId()).getCreateTime()) >0) {
                creLinesMap.put(creditLines.getCreditMstId()+"|"+creditLines.getCustomerId(), creditLines);
            }
        }
        return new ArrayList<CreditLines>(creLinesMap.values());
    }

    /**
     * 授信继承
     * @param name
     */
    public BigDecimal extendCreditLinesDeal(String seqno, Date vdate, String creditMstId, String customerId, String orgno) {
        if(CacheUtil.getSysParam("IS_EXTEND_CREDIT").equals("true")){
            Map<String, Object> param=new HashMap<String, Object>();
            param.put("creditMstId", creditMstId);
            param.put("customerId", customerId);
            param.put("vdate", vdate);
            param.put("orgno", orgno);
            List<CreditLinesDeal> creditLinesDealList = creditLinesMapper.selectCreditLinesDeal(param);
            for(CreditLinesDeal creditLinesDeal : creditLinesDealList){
                creditLinesDeal.setSeqno(seqno);
                creditLinesDeal.setCreateTime(new Date());
                creditLinesDeal.setUpdateTime(new Date());
                creditLinesDealMapper.insert(creditLinesDeal);
            }
            BigDecimal res = creditLinesMapper.selectCreditLinesHap(param);
            return res == null? new BigDecimal("0") : res;
        }else{
            return new BigDecimal("0");
        }
    }

    /**
     * 判断是否是空档期
     * @param seqno
     * @param tradeId
     * @return
     */
    public int isKDQ(String creditmstid, String customerid, Date dealdate, String orgno) {
        return creditLinesMapper.isKDQ(creditmstid, customerid, dealdate, orgno);
    }
}
