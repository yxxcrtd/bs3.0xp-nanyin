package com.joyintech.tams.invest.common.risk.service;

import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.EtrEntrustMapper;
import com.joyintech.tams.common.base.dao.FinProductTypeMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealMapper;
import com.joyintech.tams.common.base.dao.WflTrdProductDealMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.trade.dao.CreditLinesDealsMapper;
import com.joyintech.tams.invest.common.trade.dto.FinProductCredit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  授信公共校验
 * 〈功能详细描述〉
 * @author wangyang1
 * @version 1.0 2020年4月2日
 * @see RiskCreditListCommonValidatorService
 * @since 1.0
 */
@Service
public class RiskCreditListCommonValidatorService {
 
    @Autowired
    private CreditLinesDealsMapper creditLinesMapper;
    
    @Autowired
    private FinProductTypeMapper finProductTypeMapper;
     
    private FinProductCredit finProductCredit;
    
    private Boolean isNotBottomflag = true;
    
    private boolean isSave = true;
    
    private boolean flagarrisNeedOccupyCreCounter =false;
    
    @Autowired
    private TrdProductDealMapper trdProductDealMapper;
    
    @Autowired
    private WflTrdProductDealMapper wflTrdProductDealMapper;
    
    /**
     * 委托指令Mapper
     */
    @Autowired
    private EtrEntrustMapper etrEntrustMapper;
    
//    public RiskCreditListCommonDto 授信是否匹配(TrdProductDeal model)
//    { 
//        RiskCreditListCommonDto dto = new RiskCreditListCommonDto();
//        //入参进行map组装
//        List<CreditLinesDealCommon> list = new ArrayList<CreditLinesDealCommon>();
//        CreditLinesDealCommon common = new CreditLinesDealCommon();
//        List<CreditLines> creLineslist=new ArrayList<CreditLines>();
//        HashMap<String, Object> creLinesMap=new HashMap<>();//授信列表map分为1.资产/系列授信；2.机构授信;3.品种授信
//        list= checkData(model,common);
//        if(list==null)
//        {
//            //list is null 表示状态check通过
//            //check 通过 执行获取授信列表操作
//            creLineslist=getAssetsCeLineList(model);//资产，资产系列
//            creLinesMap.put("creLineslist",creLineslist);
//            creLineslist.addAll(getOrgCeLineList(creLinesMap,model));//机构授信
////            List<CreditLines> varietiesCeLineList = carietiesCreList;
////            if(varietiesCeLineList!=null&&varietiesCeLineList.size()>0) {
////                //添加品种授信列表
////                creLineslist.addAll(varietiesCeLineList);
////            }
//        }
//        else
//        {
//            //返回校验不通过的错误信息
//            dto.setCreditLinesDealCommonlist(list);
//            return dto;
//        } 
//        list = new ArrayList<CreditLinesDealCommon>();
//        if (CollectionUtils.isEmpty(creLineslist)) {
//            common.setErrMessage("授信额度不足，是否确认提交?");
//            common.setStatus("01");
//            list.add(common);
//            dto.setCreditLinesDealCommonlist(list);
//            return dto; 
//        } 
//        // 额度占用或释放业务逻辑处理，数据入库及数据列表内部check 
//        CreditLinesDeal creditLinesDeal=new CreditLinesDeal();
//        //2.查询风险系数,资产类型
//        RiskWeight rskDTO= null;
//        Map<String, Object> params = new HashMap<>();
//        if (!(StringUtil.equals(model.getInputValue1(), DictCodeList.平安资产类型_拆借.getValue())
//            ||StringUtil.equals(model.getInputValue1(), DictCodeListExt2.平安资产类型_外汇即期.getValue())
//            ||StringUtil.equals(model.getInputValue1(), DictCodeListExt2.平安资产类型_利率互换.getValue())
//            ||StringUtil.equals(model.getInputValue1(), DictCodeListExt2.平安资产类型_外汇掉期.getValue())
//            ||StringUtil.equals(model.getInputValue1(), DictCodeList.平安资产类型_质押式回购.getValue())
//            ||StringUtil.equals(model.getInputValue1(), DictCodeList.平安资产类型_买断式回购.getValue()))) {
//             rskDTO = creditLinesMapper.getRsk(model.getFinprodId(),model.getTradeDate());
//        } else if (StringUtil.equals(model.getInputValue1(), DictCodeList.平安资产类型_拆借.getValue())){
//            params.put("type1", model.getInputValue1());
//            params.put("type2", model.getInputValue3());
//            params.put("tradedate", model.getTradeDate());
//            rskDTO = creditLinesMapper.getRskByAssetType(params);
//        } else if (StringUtil.equals(model.getInputValue1(), DictCodeList.平安资产类型_质押式回购.getValue())
//            && StringUtil.equals(model.getPs(), DictCodeList.回购拆借方向_逆回购.getValue())){
//            params.put("type1", DictCodeList.平安资产类型_质押式回购.getValue());
//            params.put("type2", model.getInputValue2());
//            params.put("tradedate", model.getTradeDate());
//            rskDTO = creditLinesMapper.getRskByAssetType(params);
//        } else if (StringUtil.equals(model.getInputValue1(), DictCodeList.平安资产类型_买断式回购.getValue())
//            && StringUtil.equals(model.getPs(), DictCodeList.回购拆借方向_逆回购.getValue())){
//            params.put("type1", DictCodeList.平安资产类型_买断式回购.getValue());
//            params.put("tradedate", model.getTradeDate());
//            rskDTO = creditLinesMapper.getRskByAssetType(params);
//        }
//        String type8=creditLinesMapper.getTypeByFinprodId(model.getFinprodId());
//        //3.占用/释放授信额度
//        //a.计算 占用/释放额度
//        //如果 rskDTO.分类8 in （参数字典.平安资产类型.债券，参数字典.平安资产类型.买断式回购）
//        //待处理额度
//        BigDecimal todocredit_amt =BigDecimal.ZERO; 
//        if(StringUtil.equals(type8, DictCodeList.平安资产类型_债券.getValue())){
//            //最新面值
//            BigDecimal newfaceValue=creditLinesMapper.getFaceValue(model);
//            if(flagarrisNeedOccupyCreCounter){
//                
//                //待处理额度=最新面值*(参数.金融产品交易DTO.交易份额，债券数量)*rskDTO.风险权数
//                if (StringUtil.equals(model.getInputValue1(), DictCodeListExt2.平安资产类型_债券借贷.getValue())){
//                    todocredit_amt=(newfaceValue==null?BigDecimal.valueOf(100):newfaceValue).multiply(model.getShareAmt()).multiply(BigDecimal.valueOf(100));
//                } else {
//                    todocredit_amt=(newfaceValue==null?BigDecimal.valueOf(100):newfaceValue).multiply(model.getTradeAmt()).divide(BigDecimal.valueOf(100));
//                }
//            }else{
//                if (StringUtil.equals(model.getInputValue1(), DictCodeListExt2.平安资产类型_债券借贷.getValue())){
//                    todocredit_amt=model.getShareAmt().multiply(BigDecimal.valueOf(10000));
//                } else {
//                    todocredit_amt=model.getTradeAmt();
//                }
//            } 
//            if(model.getPs().equals(DictCodeList.回购拆借方向_逆回购.getValue())){
//                todocredit_amt=model.getPrinAmt().multiply(newfaceValue==null?BigDecimal.valueOf(100):newfaceValue).divide(BigDecimal.valueOf(100));
//            }
//            //还本时按还本面值计算
//            if(model.getPs().equals(DictCodeList.交易方向_还本到账.getValue()))
//            {
//                todocredit_amt=model.getPrinAmt();
//            }
//        }else if(StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue())){
//            //验证
//            if(!isSave){
//                todocredit_amt =model.getPrinAmt();
//            } //存续期交易
//            else{
//                //待处理额度从质押式抵押品表中取
//                todocredit_amt=creditLinesMapper.getPledgeNum(model.getFinprodId());
//            }
//        }else if(StringUtil.equals(model.getInputValue1(), DictCodeList.平安资产类型_计划类资产_净值型.getValue())
//            ||StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_股票.getValue())){ 
//                todocredit_amt =model.getTradeAmt(); 
//        }
//        else{
//            //待处理额度=参数.金融产品交易DTO.交易本金*rskDTO.风险权数
//            todocredit_amt=model.getPrinAmt();
//        }
//        if(rskDTO != null && (rskDTO.getRiskWeight() != null)){
//            todocredit_amt=todocredit_amt.multiply(rskDTO.getRiskWeight());
//        }
//        //b.根据交易方向&交易状态 判断占用/释放交易方向_卖出
//        String[] arrps = {DictCodeList.交易方向_卖出.getValue(),DictCodeList.交易方向_行权_回售.getValue(),
//                DictCodeList.交易方向_赎回确认.getValue(),DictCodeList.交易方向_提前支取.getValue(),
//                DictCodeList.交易方向_行权_赎回.getValue(),DictCodeList.交易方向_还本到账.getValue()};
//        String[] arrps1 = {DictCodeList.交易方向_买入.getValue(),DictCodeList.交易方向_申购.getValue(),DictCodeList.回购拆借方向_逆回购.getValue(),DictCodeListExt2.交易方向_认购.getValue()};
//        //股票处理
//        if(model.getPs().equals(DictCodeListExt2.交易方向_新股中签.getValue())||model.getPs().equals(DictCodeListExt2.交易方向_配股.getValue())||model.getPs().equals(DictCodeListExt2.交易方向_增发.getValue()))
//        {
//            model.setPs(DictCodeList.交易方向_买入.getValue());
//        }
//        Boolean flagps = Arrays.asList(arrps).contains(model.getPs());
//        Boolean flagps1 = Arrays.asList(arrps1).contains(model.getPs());
//        //交易方向为卖出初始状态时 或者买入撤销时候 要退回原占用的额度；交易方向为买入撤销时要释放原占用的额度   
//        if((DictCodeListExt1.交易状态_初始.getValue().equals(model.getTradeStatus()==null?DictCodeListExt1.交易状态_初始.getValue():model.getTradeStatus()) && flagps )
//                ||(DictCodeListExt1.交易状态_撤销.getValue().equals(model.getTradeStatus()) && flagps1)){
//            //待处理额度=待处理额度*-1(释放原占用)
//            todocredit_amt=todocredit_amt.negate();
//        }
//        //已占用额度
//        BigDecimal hapCreditAmt=BigDecimal.ZERO;
//        BigDecimal prehapamt =BigDecimal.ZERO;
//        Boolean isEnoughCreditflag=true;
////        List<String> seqnoList =this.seqnoList();
//        List<String> seqnoList =new ArrayList<>();
//        List<CreditLinesDetail> listlist =new ArrayList<>();
//        for(CreditLines creditLines:creLineslist){
//            Map<String,Object> mapforprehapamt = new HashMap<>();
//            mapforprehapamt.put("creditMstId",creditLines.getCreditMstId());
//            mapforprehapamt.put("customerId",creditLines.getCustomerId());
//            mapforprehapamt.put("tradedate",model.getTradeDate());
//            //预占用额度
//            prehapamt = creditLinesMapper.getPrehapamtForPingan(mapforprehapamt);
//          //  hapCreditAmt=creditLines.getHapCreditAmt();
//            hapCreditAmt=creditLines.getHapCreditAmt().add(prehapamt==null?BigDecimal.ZERO:prehapamt);
//            //已占用额度=（已占用额度+待处理额度）>0?（已占用额度+待处理额度）:0
//            hapCreditAmt = BigDecimal.ZERO.compareTo(hapCreditAmt.add(todocredit_amt)) < 0 ? hapCreditAmt.add(todocredit_amt):BigDecimal.ZERO;
//            //新增只有买入或申购时候额度监控 by nixiukuan 20191009
//            if(hapCreditAmt.compareTo(creditLines.getCreditAmt())>0 && flagps1){
//                //提示 授信额度不够,并且直接返回提示语，不再进行下次循环
//                isEnoughCreditflag=false; 
//                common.setErrMessage("授信额度不足，是否确认提交?");
//                common.setStatus("01");
//                list.add(common);
//                dto.setCreditLinesDealCommonlist(list);
//                return dto; 
//            }
//
//            if(!creditLines.getCreditMstId().equals(BussinessDictCodeList.授信配置代码_资产授信.getValue())&&!creditLines.getCreditMstId().equals(BussinessDictCodeList.授信配置代码_机构授信.getValue())&&!creditLines.getCreditMstId().equals(BussinessDictCodeList.授信配置代码_系列授信.getValue())) {
//                //额度检验
//                listlist.add(this.checkOrgDetailAmt(seqnoList, creditLines, model, todocredit_amt));
//                //期限检验
//                listlist.add(this.checkOrgDetailTerm(seqnoList, creditLines, model));
//            } 
//        } 
//        for(CreditLinesDetail detail:listlist){
//            if (StringUtil.equals(detail.getCheckresult(), "nopass")) {
//                common.setCreditresult("nopass");
//                isEnoughCreditflag = false;
//                common.setErrMessage("授信额度不足，是否确认提交?");
//                common.setStatus("01");
//                break;
//            } else {
//                isEnoughCreditflag = true;
//                common.setStatus("01");
//                common.setCreditresult("pass");
//            }
//        }
//        if(common.getCreditresult()!=null&&common.getCreditresult().equals("nopass")) {
//         //  common.setCreditLinesdetail(listlist);
//            list.add(common);
//        }
////        delLinesDeal(model, list, creLineslist, creditLinesDeal, todocredit_amt,
////            isEnoughCreditflag);
//        dto.setDealModel(model);
//        dto.setTodocreditAmt(todocredit_amt);
//        dto.setCreditLinesDealCommonlist(list);
//        dto.setCreLineslist(creLineslist);
//        dto.setCreditLinesDeal(creditLinesDeal);
//        dto.setIsEnoughCreditflag(isEnoughCreditflag);
//        return dto;
//    }
//
//
//    /**
//     * 授信占用额度
//     * <功能详细描述>
//     * @param model
//     * @param list
//     * @param creLineslist
//     * @param creditLinesDeal
//     * @param todocredit_amt
//     * @param isEnoughCreditflag
//     * @see
//     * @since 1.0
//     */
//    @Deprecated
//    public List<CreditLinesDealCommon> delLinesDeal(RiskCreditListCommonDto dto,String tradeId) {
//        TrdProductDeal model = dto.getDealModel();
//        List<CreditLinesDealCommon> list = dto.getCreditLinesDealCommonlist();
//        List<CreditLines> creLineslist = dto.getCreLineslist();
//        CreditLinesDeal creditLinesDeal=dto.getCreditLinesDeal();
//        BigDecimal todocredit_amt = dto.getTodocreditAmt(); 
//        Boolean isEnoughCreditflag = dto.getIsEnoughCreditflag();
//        BigDecimal hapCreditAmt;
//        //额度足够的情况
////        if(isEnoughCreditflag){
//            for(CreditLines creditLines:creLineslist){
//                if (null != tradeId && !"".equals(tradeId)){
//                    int count = creditLinesMapper.selectCreditDealCount(creditLines.getSeqno(), tradeId);
//                    if (count > 0) {
//                        continue;
//                    }
//                }
//                hapCreditAmt=creditLines.getHapCreditAmt();
//                //已占用额度=（已占用额度+待处理额度）>0?（已占用额度+待处理额度）:0
//                hapCreditAmt = BigDecimal.ZERO.compareTo(hapCreditAmt.add(todocredit_amt)) < 0 ? hapCreditAmt.add(todocredit_amt):BigDecimal.ZERO;
//                //1.更新额度管理中 已占用额度
//                creditLines.setHapCreditAmt(hapCreditAmt);
//                creditLines.setUpdateTime(model.getUpdateTime());
//                creditLines.setUpdateUser(model.getUpdateUser());
//                if(isSave){ 
//                    creditLinesMapper.updateLinesByPrimaryKeySelective(creditLines);
//                }
//                if(isNotBottomflag){
//                    //2.0 存额度占用明细表
//                    creditLinesDeal.setSeqno(creditLines.getSeqno());
//                    if (null != tradeId && !"".equals(tradeId)){
//                        creditLinesDeal.setTradeId(tradeId);
//                    } else {
//                        creditLinesDeal.setTradeId(model.getFinprodId()+"&"+ DateUtil.format(DateUtil.now(), DateUtil.DATETIME_FORMAT_WITH_SSS_YEAR1));
//                    }
//                    //占用类型（实际占用、预占用）
//                    creditLinesDeal.setCreditOccureType("01");
//                    creditLinesDeal.setTradeAmt(todocredit_amt);
//                    creditLinesDeal.setTradeDate(model.getTradeDate());
//                    creditLinesDeal.setCreateDept(model.getCreateDept());
//                    creditLinesDeal.setCreateTime(model.getCreateTime());
//                    creditLinesDeal.setCreateUser(model.getCreateUser());
//                    creditLinesDeal.setUpdateTime(model.getUpdateTime());
//                    creditLinesDeal.setUpdateUser(model.getUpdateUser());
//                    if(isSave){
//                        if(null != model.getTradeStatus() && (DictCodeListExt1.交易状态_撤销.getValue().equals(model.getTradeStatus()))){
//                            //删除原占用的
//                            creditLinesMapper.deleteLinesDealByTradeid(creditLines.getSeqno(),model.getTradeId());
//                        }
//                        else
//                        {
//                            creditLinesMapper.insertLinesDeal(creditLinesDeal);
//                        }
//                    }
//                }else{
//                    //2.1 存授信额度底层持仓关联表
//                    CreditLinesDealCommon creditLinesDealCommon=new CreditLinesDealCommon();
//                    creditLinesDeal.setTradeAmt(hapCreditAmt);
//                    creditLinesDeal.setSeqno(creditLines.getSeqno());
//                    creditLinesDeal.setCreditOccureType("01");
//                    list.add(creditLinesDealCommon);
//                }
//            }
////        }
//        return list;
//    }
//      
//    
//    /**
//     * 占用、释放授信额度参数check共通处理 
//     * @return
//     */ 
//    @Deprecated
//    public List<CreditLinesDealCommon> checkData(TrdProductDeal model,CreditLinesDealCommon common) { 
//        //底层资产返回list
//        List<CreditLinesDealCommon> list = new ArrayList<CreditLinesDealCommon>();
//        //交易相关信息model 
//        if(!StringUtil.isNotEmpty(model.getPs()) || model.getPs().equals(DictCodeListExt1.交易方向_债转股.getValue())
//            || (model.getPs().equals(DictCodeListExt1.拆借资产类型_拆出.getValue())
//                &&StringUtil.equals(model.getInputValue1(), DictCodeList.平安资产类型_拆借.getValue()))
//            || (model.getPs().equals(DictCodeListExt2.回购拆借方向_融入.getValue())
//                &&StringUtil.equals(model.getInputValue1(), DictCodeListExt2.平安资产类型_债券借贷.getValue()))
//            || (StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_利率互换.getValue()))) {
//            model.setPs(DictCodeList.交易方向_买入.getValue());
//        }
//        //只有交易方向在下列情况下才有可能占用/释放授信
//        String[] arrisNeedOccupyCrePs = {DictCodeList.交易方向_卖出.getValue(),DictCodeList.交易方向_行权_回售.getValue(),
//                DictCodeList.交易方向_赎回确认.getValue(),DictCodeList.交易方向_提前支取.getValue(),
//                DictCodeList.交易方向_行权_赎回.getValue(),DictCodeList.交易方向_买入.getValue(),
//                DictCodeList.交易方向_申购.getValue(),DictCodeList.交易方向_还本到账.getValue()
//                , DictCodeListExt2.交易方向_认购.getValue(),DictCodeList.回购拆借方向_逆回购.getValue(),DictCodeListExt2.交易方向_买卖.getValue()
//                 };
//        //只有交易方向在下列情况下才需要根据交易对手判断是否占用授信
//        String[] arrisNeedOccupyCreCounter = {DictCodeList.交易方向_买入.getValue(),DictCodeList.交易方向_卖出.getValue(),
//                DictCodeList.交易方向_行权_回售.getValue(),DictCodeList.交易方向_申购.getValue(),DictCodeListExt2.交易方向_认购.getValue(),
//                DictCodeList.交易方向_赎回确认.getValue(),DictCodeListExt2.交易方向_买卖.getValue()};
//        //具体相关业务逻辑处理
//        //股票处理
//        if(model.getPs().equals(DictCodeListExt2.交易方向_新股中签.getValue())||model.getPs().equals(DictCodeListExt2.交易方向_配股.getValue())||model.getPs().equals(DictCodeListExt2.交易方向_增发.getValue()))
//        {
//            model.setPs(DictCodeList.交易方向_买入.getValue());
//        }
//        //判断当前交易方向是否需要占用/释放授信
//        Boolean flagisNeedOccupyCrePs = Arrays.asList(arrisNeedOccupyCrePs).contains(model.getPs());
//        Boolean flagarrisNeedOccupyCreCounter = Arrays.asList(arrisNeedOccupyCreCounter).contains(model.getPs());
//        //根据资产代码查询债券基本信息
//        finProductCredit = creditLinesMapper.getAssetInfoByFinId(model.getFinprodId(),model.getTradeDate(),BussinessDictCodeList.行权方式_回售.getValue());
//        //部分债券类型不占用授信
//        String[] arrzqtype = {BussinessDictCodeList.债券类型_国债.getValue(),BussinessDictCodeList.地方政府债.getValue(),BussinessDictCodeList.央行票据.getValue()};
//        Boolean flagisNeedOccupyCrezqtype = false;
//        if(finProductCredit!=null) {
//            flagisNeedOccupyCrezqtype=  Arrays.asList(arrzqtype).contains(finProductCredit.getType6());
//        } 
//        //不占用授信情况 直接返回 
//        if(!flagisNeedOccupyCrePs
//                ||(model.getFinprodType2() != null && (StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())||flagisNeedOccupyCrezqtype||
//                StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue())
//                ||StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券借贷融出.getValue())))){
//            common.setErrMessage(null);
//            common.setStatus("02");
//            list.add(common);
//            return list;
//        }
//        // 买入卖出交易对手为内部则判断为转仓 不走授信
//        if(model.getCounterId() != null && creditLinesMapper.isNbDeal(model.getCounterId(),DictCodeListExt1.交易对手类型_市场机构.getValue())<1 && 
//        		(DictCodeList.交易方向_买入.getValue().equals(model.getPs()) || DictCodeList.交易方向_卖出.getValue().equals(model.getPs()))) {
//        	return list;
//        }
//        //若不是底层资产交易并且不是存续期交易，则需要根据交易对手判断是否占用授信
//        if(isNotBottomflag && flagarrisNeedOccupyCreCounter){
//            //根据交易方向、是否内部交易判断 是否占用授信 
//            int count =creditLinesMapper.isNbDeal(model.getCounterId(),DictCodeListExt1.交易对手类型_市场机构.getValue());
//            if(count<1) {
//                common.setErrMessage(null);
//                common.setStatus("02");
//                list.add(common);
//                return list;
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 资产授信、系列授信
//     * Add by xkni 2019-09-12
//     * @return
//     */ 
//    @Deprecated
//    public List<CreditLines> getAssetsCeLineList(TrdProductDeal model) {
//        List<CreditLines> creLineslist=new ArrayList<>(); 
//        if(!(model.getFinprodType2() != null && (StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())))){
//            //授信参数传值方式修改 start
//            //股票处理
//            if(model.getPs().equals(DictCodeListExt2.交易方向_新股中签.getValue())||model.getPs().equals(DictCodeListExt2.交易方向_配股.getValue())||model.getPs().equals(DictCodeListExt2.交易方向_增发.getValue()))
//            {
//                model.setPs(DictCodeList.交易方向_买入.getValue());
//            }
//            Map<String,Object> mapParm = new HashMap<>();
//            mapParm.put("dto", model);
//            mapParm.put("ps1", DictCodeList.交易方向_买入.getValue());
//            mapParm.put("ps4", DictCodeList.交易方向_申购.getValue());
//            mapParm.put("ps52", DictCodeListExt2.交易方向_认购.getValue());
//            mapParm.put("creditmstidZC", BussinessDictCodeList.授信配置代码_资产授信.getValue());
//            mapParm.put("creditmstidXL", BussinessDictCodeList.授信配置代码_系列授信.getValue());
//            creLineslist=creditLinesMapper.getCeLinesInfoforasset(mapParm);
//            //授信参数传值方式修改 end
//        }
//        return  creLineslist;
//    }
//    
//    /**
//     * 机构授信
//     * Add by xkni 2019-09-12
//     * @return
//     */ 
//    @Deprecated
//    public List<CreditLines> getOrgCeLineList(HashMap<String, Object> map,TrdProductDeal model) { 
//        List<CreditLines> creLineslist= (List<CreditLines>) map.get("creLineslist");
//        //是否占用主体授信flag
//        Boolean iscreditflag=false;
//        for(CreditLines creditLinesAsset:creLineslist){
//            if(StringUtil.equals(DictCodeList.是否_是.getValue(),creditLinesAsset.getCreditFlag())){
//                iscreditflag=true;
//            }
//        } 
//        
//        //b.处理机构授信、品种授信
//        //a的查询结果为0 或者（若有数据  则判断 是否占用主体授信=是 则查询机构授信及机构品种授信）
//        List<CreditLines> orgcreLineslist=new ArrayList<CreditLines>(); 
//        if((creLineslist==null || creLineslist.size()==0)||
//                (creLineslist!=null && creLineslist.size()>0 && iscreditflag)) {
//            String cemodel = CacheUtil.getSysParam(BussinessConstant.CEMODEL);
//            //如果是机构或者品种授信，交易对手代码转换成市场代码
//            String counterId = "";
//            if (isNotBottomflag) {
//                //交易
//                if (isNotBottomflag) {
//                    counterId = creditLinesMapper.getPartyIdBycounterId(model.getCounterId(),
//                            DictCodeListExt1.交易对手类型_市场机构.getValue());
//                }
//                //存续期交易
//                else {
//                    if ((model.getFinprodType2() != null && (StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())
//                            || StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue())))) {
//                        //回购存续期交易根据Finprodid查询交易对手
//                        counterId = creditLinesMapper.getPartyIdByFinprodId(model.getFinprodId());
//                    }
//                }
//                //add 20191019  股票取标的对应的公司名称 、
//                if(StringUtil.isNotEmpty(model.getFinprodType2())&&model.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_股票.getValue()))
//                {
//                    counterId=creditLinesMapper.getCounterIdbyFinprodId(model.getFinprodId());
//                }
//                if (StringUtil.isNotEmpty(counterId)) {
//                   model.setCounterId(counterId);
//                } 
//            }
//            //授信参数传值方式修改 start
//            //股票处理
//            if(model.getPs().equals(DictCodeListExt2.交易方向_新股中签.getValue())||model.getPs().equals(DictCodeListExt2.交易方向_配股.getValue())||model.getPs().equals(DictCodeListExt2.交易方向_增发.getValue()))
//            {
//                model.setPs(DictCodeList.交易方向_买入.getValue());
//            }
//            Map<String, Object> mapParm = new HashMap<>();
//            mapParm.put("dto", model);
//            mapParm.put("ps1", DictCodeList.交易方向_买入.getValue());
//            mapParm.put("ps4", DictCodeList.交易方向_申购.getValue());
//            mapParm.put("ps52", DictCodeListExt2.交易方向_认购.getValue());
//            mapParm.put("creditmstidJG", BussinessDictCodeList.授信配置代码_机构授信.getValue());
//            mapParm.put("fzq", DictCodeList.平安资产类型_债券.getValue());
//            mapParm.put("fck", DictCodeList.平安资产类型_存款.getValue());
//            mapParm.put("fxml", DictCodeList.平安资产类型_项目类资产.getValue());
//            mapParm.put("fhg", DictCodeList.平安资产类型_质押式回购.getValue());
//            //授信参数传值方式修改 end
//            //如果系统参数.授信模式==01 
//            if ("01".equals(cemodel)) {
//                if ((model.getFinprodType2() != null && (StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())
//                        || StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue())))) {
//                    String repType = DictCodeList.回购类型_买断式回购.getValue();
//                    if (StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())) {
//                        repType = model.getRemark();
//                        if (!StringUtil.isNotEmpty(repType)) {
//                            repType = DictCodeList.回购类型_质押式协议回购.getValue();
//                        }
//                    }
//                    //授信参数传值方式修改 start
//                    mapParm.put("repType", repType);
//                    //预占用授权额度管理
//                    orgcreLineslist = creditLinesMapper.getCeLinesInfoforOrg01Rep(mapParm);
//                    //授信参数传值方式修改 end
//                } else {
//                    orgcreLineslist = creditLinesMapper.getCeLinesInfoforOrg01(mapParm);
//                }
//            } else {
//                if ((model.getFinprodType2() != null && (StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())
//                        || StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue())))) {
//                    String repType = DictCodeList.回购类型_买断式回购.getValue();
//                    if (StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())) {
//                        repType = model.getRemark();
//                        if (!StringUtil.isNotEmpty(repType)) {
//                            repType = DictCodeList.回购类型_质押式协议回购.getValue();
//                        }
//                    }
//                    //授信参数传值方式修改 start
//                    mapParm.put("repType", repType);
//                    orgcreLineslist = creditLinesMapper.getCeLinesInfoforOrg01Rep(mapParm);
//                    //授信参数传值方式修改 end
//                } else {
//                    orgcreLineslist = creditLinesMapper.getCeLinesInfoforOrg02(mapParm);
//                }
//            }
//        }
//        return  orgcreLineslist;
//    }
// 
//    
//    /**
//     * 检查分项授信额度是否通过
//     *
//     * @param seqnoList
//     * @param creLinesmst 分项授信记录
//     * @param model 交易model
//     * @param todocredit_amt 待处理额度
//     * @return CreditLinesDetail 检验结果
//     */
//    @Deprecated
//    private CreditLinesDetail checkOrgDetailAmt(List<String> seqnoList, CreditLines creLinesmst, TrdProductDeal model, BigDecimal todocredit_amt) {
//
//        CreditLinesDetail creditLinesDetailamt =new CreditLinesDetail();
//        //查询机构的品种授信
//        BigDecimal prehapamt =BigDecimal.ZERO;
//        BigDecimal converttemp = new BigDecimal("100000000");
//        //额度检验
//
//        Map<String,Object> mapforprehapamt = new HashMap<>();
//        mapforprehapamt.put("creditMstId",creLinesmst.getCreditMstId());
//        mapforprehapamt.put("customerId",creLinesmst.getCustomerId());
//        mapforprehapamt.put("tradedate",model.getTradeDate());
//        //根据授信配置代码、机构代碼、日期查询预占用额度
//        prehapamt = creditLinesMapper.getPrehapamtForPingan(mapforprehapamt);
//        //额度
//        //根据品种授信授信配置代码查询品种授信名称
//        String mstName= creditLinesMapper.getMstName(creLinesmst.getCreditMstId());
////        creditLinesDetailamt.setCredittype("["+getMessage("TrdBondInvestTradeCheck.creditamt")+"]"+mstName+"(亿元)");
//        creditLinesDetailamt.setCheckscope(creLinesmst.getCreditAmt().divide(converttemp).toString());
//        creditLinesDetailamt.setStockvalue(creLinesmst.getHapCreditAmt()==BigDecimal.ZERO?"":creLinesmst.getHapCreditAmt().divide(converttemp).toString());
//        creditLinesDetailamt.setPrehapamt(prehapamt == null?"":prehapamt.divide(converttemp).toString());
//        //债券属于此品种才显示本次交易信息
//
//        if(!seqnoList.isEmpty()&&seqnoList.contains(creLinesmst.getCreditMstId())){
//            creditLinesDetailamt.setThistrans(todocredit_amt.divide(converttemp).toString());
//            if(creLinesmst.getCreditAmt().compareTo(creLinesmst.getHapCreditAmt().add(todocredit_amt).add(prehapamt== null?BigDecimal.ZERO:prehapamt))>=0){
//                creditLinesDetailamt.setCheckresult("pass");
//            }else{
//                creditLinesDetailamt.setCheckresult("nopass");
//            }
//        } 
//        return creditLinesDetailamt;
//    }
//
//    /**
//     * 检查分项授信期限是否通过
//     *
//     * @param seqnoList
//     * @param creLinesmst 分项授信记录
//     * @param model 交易model
//     * @return CreditLinesDetail 检验结果
//     */
//    @Deprecated
//    private CreditLinesDetail checkOrgDetailTerm(List<String> seqnoList, CreditLines creLinesmst, TrdProductDeal model) {
//        //期限
//        CreditLinesDetail creditLinesDetailterm=new CreditLinesDetail();
//        //期限检验
//        //根据品种授信授信配置代码查询品种授信名称
//        String mstName= creditLinesMapper.getMstName(creLinesmst.getCreditMstId());
////        creditLinesDetailterm.setCredittype("["+getMessage("TrdBondInvestTradeCheck.creditterm")+"]"+mstName+"(年)");
//        creditLinesDetailterm.setCheckscope(creLinesmst.getTerm()==null?"":creLinesmst.getTerm().toString());
//        if(!seqnoList.isEmpty()&&seqnoList.contains(creLinesmst.getCreditMstId())){
//            creditLinesDetailterm.setThistrans(DateUtil.format(finProductCredit.getFinProduct().getMdate()));
//            //交易日期+批复的投资期限(年)〉=债券到期日(若为行权债，债券的含权条款为回售，则为回售日，其余均为到期日)
//            if(DateUtil.compareTo(finProductCredit.getFinProduct().getMdate(), DateUtil.addYear(model.getTradeDate(),creLinesmst.getTerm().intValue()))<=0){
//                creditLinesDetailterm.setCheckresult("pass");
//            }else{
//                creditLinesDetailterm.setCheckresult("nopass");
//            }
//        }
//
//        return creditLinesDetailterm;
//    }
//    
//    /**
//     * <一句话功能简述>
//     * <功能详细描述>
//     * @param model
//     * @return
//     * @see
//     * @since 1.0
//     */
//    private String getRepoType(TrdProductDeal model) {
//        FinProductTypeKey typeKey = new FinProductTypeKey();
//        typeKey.setFinprodId(model.getFinprodId());
//        typeKey.setBranch(model.getBranch());
//        FinProductType finProductType = finProductTypeMapper.selectByPrimaryKey(typeKey);
//        return finProductType.getType6();
//    }
    
    public void deleteCredit(String tradeId){
        //根据交易编号查找
        List<CreditLinesDeal> creditLinesDeallist = creditLinesMapper.selectCreditDealByTradeId(tradeId);
        if (null != creditLinesDeallist && creditLinesDeallist.size() != 0){
            for (CreditLinesDeal creditLinesDeal : creditLinesDeallist) {
                //删除授信额度关联交易
                creditLinesMapper.deleteLinesDealByTradeid(creditLinesDeal.getSeqno(),tradeId);
                String entrustId = creditLinesMapper.selectEntrustIdByTradeid(tradeId);
            	CreditLinesDeal credit = null;
            	if(entrustId != null) {
                	Map<String,Object> map = new HashMap<>(); 
                    map.put("seqno", creditLinesDeal.getSeqno());
                    map.put("tradeid", entrustId);
                    // 指令交易
                    credit = creditLinesMapper.getCeLinesInfo(map);
            	}
                // 从指令做的交易切指令交易存在时，返回原指令交易明细额度。不修改总额度
                if(StringUtil.isNotEmpty(entrustId) && credit != null){
                	BigDecimal tradeAmt = creditLinesDeal.getTradeAmt();
                	EtrEntrust etrEntrust = etrEntrustMapper.selectByPrimaryKey(entrustId);
                	
                    //2.查询风险系数,资产类型
                	RiskWeight rskDTO = new RiskWeight();
                    Map<String, Object> params = new HashMap<>();
                    
                    if (StringUtil.equals(etrEntrust.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_股票.getValue())){
                        params.put("type1", DictCodeListExt2.平安资产类型_股票.getValue());
                        params.put("tradedate", creditLinesDeal.getTradeDate());
                        rskDTO = creditLinesMapper.getRskByAssetType(params);
                    }
                	
                	// 股票因价格原因可能需要修改总额度
                	if(etrEntrust!= null && DictCodeListExt1.金融产品类型_投管用_股票.getValue().equals(etrEntrust.getFinprodType2())) {
                     	BigDecimal entrustNum = etrEntrust.getEntrustNum();
                        if (entrustNum == null) {
                        }else {
                        	TrdProductDeal trdProductDeal = trdProductDealMapper.selectByPrimaryKey(tradeId);
                        	WflTrdProductDealKey wflTrdProductDealKey = new WflTrdProductDealKey();
                        	wflTrdProductDealKey.setTradeId(tradeId);
                        	wflTrdProductDealKey.setWorkflowStatus("01");
                    		WflTrdProductDeal wflTrdProductDeal = wflTrdProductDealMapper.selectByPrimaryKey(wflTrdProductDealKey);

                        	if (trdProductDeal != null || wflTrdProductDeal != null) {
                        		if(trdProductDeal != null) {
                                	tradeAmt =trdProductDeal.getShareAmt().multiply(etrEntrust.getPrice());
                        		}
                        		if(wflTrdProductDeal != null) {
                                	tradeAmt =wflTrdProductDeal.getShareAmt().multiply(etrEntrust.getPrice());
                        		}
                                if(rskDTO != null && (rskDTO.getRiskWeight() != null)){
                                	tradeAmt=tradeAmt.multiply(rskDTO.getRiskWeight());
                                }
                                CreditLines creditLinesdto = creditLinesMapper.selectCeLinesInfoBySeqno(creditLinesDeal.getSeqno());
                                //更新额度管理中 已占用额度
                                if (null != creditLinesdto){ 
                                    creditLinesdto.setUpdateTime(new Date());
                                    creditLinesdto.setUpdateUser(LoginUserUtil.getUserName());
                                    creditLinesdto.setHapCreditAmt(creditLinesdto.getHapCreditAmt().subtract(creditLinesDeal.getTradeAmt()).add(tradeAmt));
                                    creditLinesMapper.updateLinesByPrimaryKeySelective(creditLinesdto);
                                }
                        	}
                        }
                    }
                	
                    //3.更新原指令交易额度占用明细数据
                    CreditLinesDeal creditLinesDealOld=new CreditLinesDeal();
                    creditLinesDealOld.setTradeAmt(tradeAmt);
                    creditLinesDealOld.setUpdateTime(new Date());
                    creditLinesDealOld.setUpdateUser(LoginUserUtil.getUserName());
                    creditLinesDealOld.setSeqno(credit.getSeqno());
                    creditLinesDealOld.setTradeId(entrustId);
                    creditLinesMapper.updateLinesDealByPrimaryKey(creditLinesDealOld);
                }else {
                    CreditLines creditLinesdto = creditLinesMapper.selectCeLinesInfoBySeqno(creditLinesDeal.getSeqno());
                    //更新额度管理中 已占用额度
                    if (null != creditLinesdto){
                        creditLinesdto.setHapCreditAmt(creditLinesdto.getHapCreditAmt().subtract(creditLinesDeal.getTradeAmt()));
                        creditLinesdto.setUpdateTime(new Date());
                        creditLinesdto.setUpdateUser(LoginUserUtil.getUserName());
                        creditLinesMapper.updateLinesByPrimaryKeySelective(creditLinesdto);
                    }
                }
            }
        }
    }
    
	/**
	 * 根据前置交易标号查交易编号
	 * @param bTradeId
	 * @return
	 */
	public List<String> selectTradeidByBTtradid(String bTradeId){
		return creditLinesMapper.selectTradeidByBTtradid(bTradeId);
	}
}
