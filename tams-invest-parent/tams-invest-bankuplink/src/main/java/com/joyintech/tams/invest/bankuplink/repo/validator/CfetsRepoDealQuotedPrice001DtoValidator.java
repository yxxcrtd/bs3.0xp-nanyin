/*
 * 文件名：CfetsBondDeal.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2020年5月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.bankuplink.repo.validator;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.EtrEntrustMapper;
import com.joyintech.tams.common.base.dao.MstCfetsCustomerInfoMapper;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.bankuplink.repo.dto.CfetsRepoDealQuotedPrice001Dto;
import com.joyintech.tams.invest.bankuplink.repo.dto.CfetsRepoDealQuotedPrice003Dto;
import com.joyintech.tams.invest.bankuplink.repo.service.CfetsRepoDealQuotedPrice001Service;
import com.joyintech.tams.invest.bankuplink.repo.service.CfetsRepoDealQuotedPrice002Service;
import com.joyintech.tams.invest.bankuplink.repo.service.CfetsRepoDealQuotedPrice003Service;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.PtlAllocationDto;
import com.joyintech.tams.invest.common.util.QuotaPageModelUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 现券对话报价验证类
 * 
 * @author zhangliang
 * @version 1.0 2020年5月14日
 * @see CfetsRepoDealQuotedPrice001DtoValidator
 * @since 1.0
 */
@Component
public class CfetsRepoDealQuotedPrice001DtoValidator implements BeanValidator {
    
    /**
     * {@linkplain}
     */
    @Autowired
    private CfetsRepoDealQuotedPrice001Service cfetsRepoDealQuotedPrice001Service;

    /**
     * {@linkplain}
     */
    @Autowired
    private CfetsRepoDealQuotedPrice002Service cfetsRepoDealQuotedPrice002Service;

    @Autowired
    private CfetsRepoDealQuotedPrice003Service cfetsRepoDealQuotedPrice003Service;

    /**
     * {@linkplain}
     */
    @Autowired
    private MstCfetsCustomerInfoMapper mstCfetsCustomerInfoMapper;

    /**
     * {@linkplain}
     */
    @Autowired
    private IPlatformDate iPlatformDate;
    
    /**
     * {@linkplain}
     */
    @Autowired
    private EtrEntrustMapper etrEntrustMapper;


    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        CfetsRepoDealQuotedPrice001Dto dto = (CfetsRepoDealQuotedPrice001Dto)value;
        // 验证信息
        List<ValidMessage> validList = new ArrayList<ValidMessage>();
        CfetsRepoDealQuotedPrice001Dto cfetsRepoDealQuotedPrice001Dto = null;
        String ps = dto.getPs();String isDzh = dto.getIsDzh();
        validList = 质押券必填(dto);
        // 校验回购期限
        if (dto.getTerm() <= 0) {
            validList.add(new ValidMessage("term", this.getMessage("CFETS_COMMON_R_018")));
        }
        //多组合check
        if(StringUtil.equals(isDzh,"Y")){
            List<PtlAllocationDto> ptlAllocations = dto.getPtlAllocationList();
            if(null==ptlAllocations||ptlAllocations.size()==0){
                validList.add(new ValidMessage("",getMessage("组合分配中必须存在至少一条指令")));
            }else{
                List<String> entrustIdList = new ArrayList<>();
                BigDecimal fpriceAmtTotal = new BigDecimal(0);
                //最大或最小回购利率
                BigDecimal maxOrminprice = ptlAllocations.get(0).getPrice();
                for(PtlAllocationDto item : ptlAllocations) {
                    if(null==item.getFpriceAmt()){
                        validList.add(new ValidMessage("",getMessage("组合分配中交易金额不可为空")));
                        return validList;
                    }
                    fpriceAmtTotal = ArithmeticUtil.doAdd(fpriceAmtTotal, item.getFpriceAmt());
                    entrustIdList.add(item.getEntrustId());
                    if(StringUtil.equals(ps, BussinessDictCodeList.回购拆借方向_正回购.getValue())){
                        if(maxOrminprice.compareTo(item.getPrice())>=0){
                            maxOrminprice = item.getPrice();
                        }
                    }
                    else if(StringUtil.equals(ps, BussinessDictCodeList.回购拆借方向_逆回购.getValue())){
                        if(maxOrminprice.compareTo(item.getPrice())<=0){
                            maxOrminprice = item.getPrice();
                        }
                    }
                }
                if(null!=dto.getDealAmt()&&dto.getDealAmt().compareTo(fpriceAmtTotal)!=0){
                    validList.add(new ValidMessage("",getMessage("报价交易金额和各指令分配后交易金额相等")));
                    return validList;
                }
                //报价价格要优于所有分配后交易金额大于0的指令的价格。并根据参数“超委托价格校验开关”来确定是硬阻挡还是软阻挡。
                if (StringUtil.equals("01",CacheUtil.getSysParam("ibuFlag_repoOverEntrustPrice"))) {
                    if(StringUtil.equals(ps, BussinessDictCodeList.回购拆借方向_正回购.getValue())
                            && dto.getPrice() != null
                            && dto.getPrice().compareTo(maxOrminprice) == 1){
                        validList.add(new ValidMessage("price", this.getMessage("CFETS_COMMON_R_014",
                                maxOrminprice.toPlainString())));
                    }
                    else if(StringUtil.equals(ps, BussinessDictCodeList.回购拆借方向_逆回购.getValue())
                            && dto.getPrice() != null
                            && dto.getPrice().compareTo(maxOrminprice) == -1){
                        validList.add(new ValidMessage("price", this.getMessage("CFETS_COMMON_R_015",
                                maxOrminprice.toPlainString())));
                    }
                }
                //分配列表  最新剩余额度
                Map<String,Object> entrustIdMap = new HashMap<>();
                entrustIdMap.put("entrustIdList",entrustIdList);
                //选择的委托  汇总条件是否满足
                //两条以上才需要check
                if(ptlAllocations.size()>1){
                    List<CfetsRepoDealQuotedPrice001Dto> qualifiedByentrustIds = cfetsRepoDealQuotedPrice001Service.getQualifiedByentrustIds(entrustIdMap);
                    if(!StringUtil.isEmptyList(qualifiedByentrustIds)){
                        String msg="";
                        boolean termFlag = true;boolean psFlag = true; boolean settleSpreedFlag = true; boolean mysideFlag = true;
                        for(int i=0;i<qualifiedByentrustIds.size()-1;i++){
                            CfetsRepoDealQuotedPrice001Dto currentObj = qualifiedByentrustIds.get(i);
                            CfetsRepoDealQuotedPrice001Dto nextObj = qualifiedByentrustIds.get(i+1);
                            if(termFlag&&currentObj.getTerm()!=nextObj.getTerm()){termFlag=false;msg += "回购期限&";}
                            if(psFlag&&!StringUtils.equals(currentObj.getPs(),nextObj.getPs())){psFlag=false;msg += "交易方向&";}
                            if(settleSpreedFlag&&!StringUtils.equals(currentObj.getSettleSpreed(),nextObj.getSettleSpreed())){settleSpreedFlag=false;msg += "清算速度&";}
                            if(mysideFlag&&!StringUtils.equals(currentObj.getMySide(),nextObj.getMySide())){mysideFlag=false;msg += "交易账户&";}
                        }
                        if(!(termFlag&&psFlag&&settleSpreedFlag&&mysideFlag)){
                            //“所选指令不满足汇总条件，【回购期限&交易方向&清算速度&交易账户】不同
                            if (StringUtil.endsWith(msg, "&")) {
                                msg = StringUtil.substring(msg, 0, msg.length() - 1);
                            }
                            validList.add(new ValidMessage("",getMessage("所选指令不满足汇总条件，["+msg+"]不同")));
                            return validList;
                        }
                    }
                }
                List<PtlAllocationDto> newestPtlAllocations =cfetsRepoDealQuotedPrice003Service.selectPortfolioInit(entrustIdMap);
                //委托基本信息判断
                for(PtlAllocationDto ptlAllocationDto : newestPtlAllocations){
                    委托基本信息(validList,ptlAllocationDto.getEntrustStatus(),ptlAllocationDto.getVdate(),ptlAllocationDto.getMdate());
                    委托完成度(validList,dto.getEntrustId(),dto.getQuoteId());
                    if(null!=validList&&validList.size()>0){return validList;}
                }
                if (StringUtil.equals("01",CacheUtil.getSysParam("ibuFlag_repoOverEntrustAvaAmt"))) {
                    for(PtlAllocationDto item : ptlAllocations) {
                        boolean isBreak = false;
                        for(PtlAllocationDto item2 :newestPtlAllocations){
                            if(StringUtil.equals(item.getEntrustId(),item2.getEntrustId())
                            &&null!=item.getFpriceAmt()&&null!=item2.getAvaNum()
                            &&item2.getAvaNum().compareTo(item.getFpriceAmt())<0){
                                isBreak = true;
                                validList.add(new ValidMessage("",getMessage("各指令分配后交易金额要小于等于指令剩余额度!指令代码:"+item.getEntrustId()+";剩余额度:"+item2.getAvaNum())));
                                break;
                            }
                        }
                        if(isBreak){break;}
                    }
                }
                //正回购方向时，要校验组合分配中各指令的交易金额小于等于对应组合的质押券折算总金额
                List<CfetsRepoDealQuotedPrice003Dto> pledgeBondList = dto.getPledgeBondList();
                if(null!=pledgeBondList&&StringUtil.equals(ps, BussinessDictCodeList.回购拆借方向_正回购.getValue())){
                    List<CfetsRepoDealQuotedPrice003Dto> pledgeBondNewList = new ArrayList<>();
                    Map<String, List<CfetsRepoDealQuotedPrice003Dto>> map = pledgeBondList.stream().collect(Collectors.groupingBy(CfetsRepoDealQuotedPrice003Dto::getPortfolioId));
                    for (Map.Entry<String, List<CfetsRepoDealQuotedPrice003Dto>> entry : map.entrySet()) {
                        boolean isBreak = false;
                        CfetsRepoDealQuotedPrice003Dto item = new CfetsRepoDealQuotedPrice003Dto();
                        BigDecimal pledgePrinTotal = BigDecimal.ZERO;
                        for (CfetsRepoDealQuotedPrice003Dto cfetsRepoDealQuotedPrice003Dto : entry.getValue()) {
                            pledgePrinTotal = ArithmeticUtil.doAdd(pledgePrinTotal,ArithmeticUtil.multiply(ArithmeticUtil.multiply(cfetsRepoDealQuotedPrice003Dto.getPledgePrin(),cfetsRepoDealQuotedPrice003Dto.getPledgeRate()),new BigDecimal(100)));
                        }
                        for(PtlAllocationDto ptlAllocationDto : ptlAllocations) {
                            if(StringUtil.equals(entry.getKey(),ptlAllocationDto.getPortfolioId())&&pledgePrinTotal.compareTo(ptlAllocationDto.getFpriceAmt())<0){
                                isBreak = true;
                                validList.add(new ValidMessage("",getMessage("组合分配中各指令的交易金额小于等于对应组合的质押券折算总金额!组合代码:"+item.getPortfolioId()+";折算总额度:"+pledgePrinTotal)));
                                break;
                            }
                        }
                        if(isBreak){break;}
                    }
                }
            }
        }
        else{
            if(StringUtil.isEmpty(dto.getPortfolioId())){
                validList.add(new ValidMessage("",
                        getMessage("COMMON_E_0057", getMessage("CfetsRepoDealQuotedPrice002.portfolioId"))));
            }
            if (StringUtil.isNotEmpty(dto.getEntrustId())) {
                EtrEntrust etrEntrust = etrEntrustMapper.selectByPrimaryKey(dto.getEntrustId());
                if (etrEntrust != null) {
                    委托基本信息(validList,etrEntrust.getEntrustStatus(),etrEntrust.getVdate(),etrEntrust.getMdate());
                    委托完成度(validList,dto.getEntrustId(),dto.getQuoteId());
                }
            }
            else {
                // 判断是否可以无委托报价
                // 委托为空的场合只有新建模式且系统参数为软校验
                if ((StringUtil.equals(dto.getQuotaPageModel(), QuotaPageModelUtil.MODEL_NEW)||StringUtil.equals(dto.getQuotaPageModel(), QuotaPageModelUtil.MODEL_EDIT))
                        && !StringUtil.equals(CacheUtil.getSysParam("ibuFlag_startDialogueQuoteWithNoEntrust"), "02")) {
                    validList.add(new ValidMessage("entrustId", this.getMessage("CFETS_COMMON_E_001")));
                }
            }
        }
        // 3) 当前报价组合代码以及本币交易对手均需存在有效的21位机构码
        String mySide = dto.getMySide();
        if (StringUtil.isNotEmpty(mySide)
            && mstCfetsCustomerInfoMapper.selectByPrimaryKey(mySide) == null) {
            validList.add(new ValidMessage("mySide", this.getMessage("CFETS_COMMON_R_017")));
        }
        String counterId = dto.getCounterId();
        if (StringUtil.isNotEmpty(counterId)
            && mstCfetsCustomerInfoMapper.selectByPrimaryKey(counterId) == null) {
            validList.add(new ValidMessage("counterId", this.getMessage("CFETS_COMMON_R_008")));
        }
        // 校验押品列表
        if (StringUtil.isEmptyList(dto.getPledgeBondList())) {
        }
        else {
            // 4) 对于跨托管，中债登交易金额和上清所交易金额可手工修改，修改后联动交易金额；
            boolean repOrg = false;
            String initOrg = dto.getPledgeBondList().get(0).getRepRegistOrg();
            for (CfetsRepoDealQuotedPrice003Dto dto2 : dto.getPledgeBondList()) {

                // 校验押品中的本次质押券面和折算比例
                if (dto2.getPledgePrin() == null) {
                    validList.add(new ValidMessage("pledgeBondList",
                        this.getMessage("CFETS_COMMON_R_019", dto2.getBondCode())));
                }
                else if (dto2.getPledgePrin().compareTo(new BigDecimal(0)) <= 0) {
                	validList.add(new ValidMessage("pledgeBondList", "本次质押券面必须大于0！"));
                }
                if (dto2.getPledgeRate() == null) {
                    validList.add(new ValidMessage("pledgeBondList",
                        this.getMessage("CFETS_COMMON_R_020", dto2.getBondCode())));
                }
                else if (dto2.getPledgeRate().compareTo(new BigDecimal(0)) <= 0) {
                	validList.add(new ValidMessage("pledgeBondList", "折算比例必须大于0！"));
                }
                if (!repOrg && !StringUtil.equals(dto2.getRepRegistOrg(), initOrg)) {
                    // 跨托管
                    repOrg = true;
                    break;
                }
            }
            if (repOrg) {
                // 跨托管校验中债交易金额和上清交易金额
                if (dto.getCbDealAmt() == null) {
                    validList.add(
                        new ValidMessage("cbDealAmt", this.getMessage("CFETS_COMMON_R_010")));
                }
                if (dto.getScDealAmt() == null) {
                    validList.add(
                        new ValidMessage("scDealAmt", this.getMessage("CFETS_COMMON_R_011")));
                }
            }
        }

        // 5) 需校验质押券券面总额必须为十万的整数倍，且不超过100亿
        BigDecimal pledgePrinTotal = dto.getPledgePrinTotal();
        if (pledgePrinTotal != null) {
            // 将券面总额除10
            BigDecimal total1 = ArithmeticUtil.divide(pledgePrinTotal, new BigDecimal(10), 2);
            if (new BigDecimal(pledgePrinTotal.intValue()).compareTo(pledgePrinTotal) != 0
                || new BigDecimal(total1.intValue()).compareTo(total1) != 0) {
                validList.add(
                    new ValidMessage("pledgePrinTotal", this.getMessage("CFETS_COMMON_R_012")));
            }
            else if (pledgePrinTotal.compareTo(new BigDecimal(10)) == -1
                     || pledgePrinTotal.compareTo(new BigDecimal(1000000)) == 1) {
                validList.add(
                    new ValidMessage("pledgePrinTotal", this.getMessage("CFETS_COMMON_R_012")));
            }
        }

        // 6) 交易方向为正回购，需校验交易金额需小于等于折后金额，若不满足，提示：“交易金额需小于等于折后金额；”
        if (StringUtil.equals(ps, BussinessDictCodeList.回购拆借方向_正回购.getValue())) {
            if (dto.getDealAmt() != null && dto.getShareCtAmt() != null
                && dto.getDealAmt().compareTo(dto.getShareCtAmt()) == 1) {
                validList.add(new ValidMessage("dealAmt", this.getMessage("CFETS_COMMON_R_013")));
            }
            // 7) 交易方向为正回购，价格模式为限价，需校验回购利率不能大于委托指令的回购利率，若不满足，
            // 提示：“报价回购利率大于委托指令回购利率XX”(XX表示具体的回购利率)；（参数开关控制是否继续操作）
            if (!StringUtil.equals(isDzh,"Y")&&cfetsRepoDealQuotedPrice001Dto != null && cfetsRepoDealQuotedPrice001Dto.getPrice() != null && dto.getPrice() != null
                && dto.getPrice().compareTo(cfetsRepoDealQuotedPrice001Dto.getPrice()) == 1) {
                if (StringUtil.equals("01",
                    CacheUtil.getSysParam("ibuFlag_repoOverEntrustPrice"))) {
                    validList.add(new ValidMessage("price", this.getMessage("CFETS_COMMON_R_014",
                        cfetsRepoDealQuotedPrice001Dto.getPrice().toPlainString())));
                    return validList;
                }
            }
            //功能描述: 做报价时，组合没有挂对应的证券账户，做出提示!
            if(!StringUtil.isEmptyList(dto.getPledgeBondList())){
                for (CfetsRepoDealQuotedPrice003Dto dto2 : dto.getPledgeBondList()) {
                    Map<String,Object> secAcctIdMap = new HashMap<>();
                    secAcctIdMap.put("portfolioId",StringUtil.equals(isDzh,"Y")?dto2.getPortfolioId():dto.getPortfolioId());
                    secAcctIdMap.put("finprodId",dto2.getFinprodId());
                    String secAcctId = cfetsRepoDealQuotedPrice002Service.qryPortfolioSecAcctId(secAcctIdMap);
                    if(StringUtil.isEmpty(secAcctId)){
                        validList.add(new ValidMessage(this.getMessage("组合没有挂对应证券账户,无法提交!")));
                        return validList;
                    }
                }
            }
            // 校验质押券的到期日是否小于报价到期结算日
            /*if(!StringUtil.isEmptyList(dto.getPledgeBondList())){
                for (CfetsRepoDealQuotedPrice003Dto dto2 : dto.getPledgeBondList()) {
                    if(DateUtil.compareDate(dto2.getMdate(), dto.getmSettleDate()) == -1){
                        validList.add(new ValidMessage(this.getMessage("CFETS_COMMON_R_029",
                            dto2.getBondCode())));
                    }
                }
            }*/
        }
        else if (StringUtil.equals(ps, BussinessDictCodeList.回购拆借方向_逆回购.getValue())) {
            // 8) 交易方向为逆回购，价格模式为限价，需校验回购利率不能小于委托指令的回购利率，
            // 若不满足，提示：“报价回购利率小于委托指令回购利率XX”(XX表示具体的回购利率)；（参数开关控制是否继续操作）
            if (!StringUtil.equals(isDzh,"Y")&&cfetsRepoDealQuotedPrice001Dto != null && dto.getPrice() != null
            	&& cfetsRepoDealQuotedPrice001Dto.getPrice() != null
                && dto.getPrice().compareTo(cfetsRepoDealQuotedPrice001Dto.getPrice()) == -1) {
                if (StringUtil.equals("01",
                    CacheUtil.getSysParam("ibuFlag_repoOverEntrustPrice"))) {
                    validList.add(new ValidMessage("price", this.getMessage("CFETS_COMMON_R_015",
                        cfetsRepoDealQuotedPrice001Dto.getPrice().toPlainString())));
                    return validList;
                }
            }
            
            // 逆回购报价是否占用同业额度
/*            if (!StringUtil.equals(CacheUtil.getSysParam("ibuFlag_repoSendPledgeQuoteQuery"), "N")) {
                String msg = cfetsRepoDealQuotedPrice001Service.checkPledgeCreditAmtLimit(dto);
                if (!StringUtil.equals(CacheUtil.getSysParam("ibuFlag_repoPledgeQuoteOverCanContinue"), "02")
                    && StringUtil.isNotEmpty(msg)) {
                    validList.add(new ValidMessage(msg));
                }
            }*/
            
            // 校验质押券的到期日是否小于报价到期结算日
            if(!StringUtil.isEmptyList(dto.getPledgeBondList())){
                for (CfetsRepoDealQuotedPrice003Dto dto2 : dto.getPledgeBondList()) {
                    if(DateUtil.compareDate(dto2.getMdate(), dto.getmSettleDate()) == -1){
                        validList.add(new ValidMessage(this.getMessage("CFETS_COMMON_R_029",
                            dto2.getBondCode())));
                    }
                }
            }
        }
        // 9) 正逆回购，需实时校验该笔报价的交易金额需要<=委托剩余报价额度，
        // 若不满足，提示：“交易金额需要小于或等于该笔委托的剩余报价额度XX”，（XX为具体的剩余报价额度）；（参数开关控制是否继续操作）
        //if (StringUtil.equals(ps, BussinessDictCodeList.回购拆借方向_逆回购.getValue())) {
        if(!StringUtil.equals(isDzh,"Y")){
            if (cfetsRepoDealQuotedPrice001Dto != null && dto.getDealAmt() != null){
                //查找可用额度
                Map<String,Object> paramUsedAmt = new HashMap<>();
                paramUsedAmt.put("entrustId",cfetsRepoDealQuotedPrice001Dto.getEntrustId());
                paramUsedAmt.put("quoteId",dto.getQuoteId());
                BigDecimal usedAmt = cfetsRepoDealQuotedPrice002Service.selectUsedAmt(paramUsedAmt);
                usedAmt = null==usedAmt?new BigDecimal(0):usedAmt;
                if (StringUtil.equals("01",CacheUtil.getSysParam("ibuFlag_repoOverEntrustAvaAmt"))) {
                    if(StringUtil.equals(ps, BussinessDictCodeList.回购拆借方向_逆回购.getValue())){
                        if(dto.getDealAmt().compareTo(ArithmeticUtil.subtract(cfetsRepoDealQuotedPrice001Dto.getEntrustNum(),usedAmt)) == 1){
                                validList.add(new ValidMessage("dealAmt", this.getMessage("CFETS_COMMON_R_016",
                                        cfetsRepoDealQuotedPrice001Dto.getAvaNum().toPlainString())));
                                return validList;
                        }
                    }
                    else if(StringUtil.equals(ps, BussinessDictCodeList.回购拆借方向_正回购.getValue())){
                        if(null!=dto.getPledgePrinTotal()&&ArithmeticUtil.multiply(dto.getPledgePrinTotal(),new BigDecimal(10000)).compareTo(ArithmeticUtil.subtract(cfetsRepoDealQuotedPrice001Dto.getEntrustNum(),usedAmt)) == 1){
                            validList.add(new ValidMessage("pledgePrinTotal", this.getMessage("CFETS_COMMON_R_016",
                                    cfetsRepoDealQuotedPrice001Dto.getAvaNum().toPlainString())));
                            return validList;
                        }
                    }
                }
            }
        }
        //}
        return validList;
    }

    private List<ValidMessage> 质押券必填(CfetsRepoDealQuotedPrice001Dto dto) {
        List<ValidMessage> validList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(dto.getPs(), BussinessDictCodeList.回购拆借方向_逆回购.getValue())
        &&(StringUtil.isEmpty(dto.getQuoteRoleType())||StringUtil.equals(dto.getQuoteRoleType(),BussinessDictCodeList.银行间上行本方角色_询价方.getValue()))
        &&(StringUtil.equals(dto.getQuotaPageModel(), QuotaPageModelUtil.MODEL_NEW)
        ||(StringUtil.equals(dto.getQuotaPageModel(),QuotaPageModelUtil.MODEL_EDIT)&&(StringUtil.equals(dto.getTradeStatus(), BussinessDictCodeList.银行间上行报价状态_已修改.getValue())||StringUtil.equals(dto.getTradeStatus(), BussinessDictCodeList.银行间上行报价状态_已新增.getValue()))))) {
        } else {
            if (null == dto.getPledgePrinTotal()) {
                validList.add(new ValidMessage("pledgePrinTotal", this.getMessage("请输入券面总额合计。")));
            }
            if (null == dto.getShareCtAmt()) {
                validList.add(new ValidMessage("shareCtAmt", this.getMessage("请输入质押券折算总金额。")));
                return validList;
            }
            if (StringUtil.isEmptyList(dto.getPledgeBondList())) {
                //CFETS质押式回购新建界面，逆回购时，质押券信息栏可以不添加质押券，券面总额合计和质押券折算总金额字段不需要设置为必填项
                    validList.add(
                            new ValidMessage("pledgeBondList", this.getMessage("CFETS_COMMON_R_009")));
            }
        }
        return validList;
    }

    private void 委托完成度(List<ValidMessage> validList,String entrustId,String quoteId){
        CfetsRepoDealQuotedPrice001Dto cfetsRepoDealQuotedPrice001Dto = cfetsRepoDealQuotedPrice002Service.queryEntrustInfoByEntrustId(
                entrustId, quoteId);
        if (cfetsRepoDealQuotedPrice001Dto != null) {
            if (StringUtil.equals(cfetsRepoDealQuotedPrice001Dto.getEntrustStatus(),
                    BussinessDictCodeList.委托状态_已接收.getValue())
                    && cfetsRepoDealQuotedPrice001Dto.getTransactioncom().compareTo(new BigDecimal(100)) >= 0) {
                validList.add(
                        new ValidMessage("entrustId", this.getMessage("CFETS_COMMON_R_023")));
            }
        }
    }

    private void 委托基本信息(List<ValidMessage> validList, String entrustStatus, Date vdate, Date mdate){
        if (StringUtil.equals(entrustStatus,
                BussinessDictCodeList.委托状态_已关闭.getValue())
                || StringUtil.equals(entrustStatus,
                BussinessDictCodeList.委托状态_已撤回.getValue())) {
            validList.add(
                    new ValidMessage("entrustId", this.getMessage("CFETS_COMMON_E_007")));
        }
        // 校验委托完成度
        if (!StringUtil.equals(entrustStatus,
                BussinessDictCodeList.委托状态_已接收.getValue())) {
            validList.add(
                    new ValidMessage("entrustId", this.getMessage("CFETS_COMMON_E_002")));
        }
        String platDateStr = iPlatformDate.getSysDateStr();
        if ((DateUtil.compareDate(vdate, DateUtil.parse(platDateStr)) == 1)
                || (DateUtil.compareDate(mdate,
                DateUtil.parse(platDateStr)) == -1)) {
            validList.add(
                    new ValidMessage("entrustId", this.getMessage("CFETS_COMMON_E_003")));
        }
    }
}
