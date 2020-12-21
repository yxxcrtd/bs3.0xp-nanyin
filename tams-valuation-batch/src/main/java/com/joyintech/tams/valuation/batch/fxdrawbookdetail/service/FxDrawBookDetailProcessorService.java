package com.joyintech.tams.valuation.batch.fxdrawbookdetail.service;
/**
 * @author yanya
 * @create 2019-12-12 10:05
 */

import com.joyintech.calculation.CalculatorFactory;
import com.joyintech.calculation.bonds.base.param.SecCalYearDayCountOutputParameter;
import com.joyintech.calculation.bonds.base.param.SecCalYearInputParameter;
import com.joyintech.calculation.bonds.constant.*;
import com.joyintech.calculation.bonds.interest.BasisIntCalculator;
import com.joyintech.calculation.bonds.interest.param.BasisIntInputParameter;
import com.joyintech.calculation.bonds.interest.param.BasisIntOutputParameter;
import com.joyintech.calculation.config.NumberEnum;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.calculation.util.BasicUtil;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.base.entity.SysBatchTask;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListTableId;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalance;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalanceSearchDto;
import com.joyintech.tams.valuation.batch.common.accounting.service.AccountingCommonService;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookAccountGenLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.AccountDriver;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResultWithBokSummary;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.TreeNode;
import com.joyintech.tams.valuation.batch.common.base.entity.extcommon.ValBokBookdetailBatch;
import com.joyintech.tams.valuation.batch.common.calculation.entity.CalCommonResult;
import com.joyintech.tams.valuation.batch.common.calculation.entity.PrincipalInfo;
import com.joyintech.tams.valuation.batch.common.calculation.entity.QuoteInfo;
import com.joyintech.tams.valuation.batch.common.calculation.service.CalculationCommonService;
import com.joyintech.tams.valuation.batch.common.drawbaseinfoselectcom.dto.DrawBaseInfoSelectComDTO;
import com.joyintech.tams.valuation.batch.common.drawbaseinfoselectcom.dto.DrawBaseInfoSelectComResult;
import com.joyintech.tams.valuation.batch.common.finproduct.service.FinProductCommonService;
import com.joyintech.tams.valuation.batch.fxdrawbookdetail.dao.FxDrawBookDetailMapper;
import com.joyintech.tams.valuation.batch.fxdrawbookdetail.dto.FxDrawBookFinTradeProductDto;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetCashCalcRule;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetCashPlan;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetSearchDto;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.DictCodeBaseSubjectNatrue;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.constants.ValDictCodeList;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * @ClassName OverdueInterestCalBookService
 * @Description: 逾期罚息计提处理service
 * @Author yanya
 * @Date 2019/12/23
 * @Version V1.0
 **/
@Service
@Scope("prototype")
public class FxDrawBookDetailProcessorService {

    /**
     * 逾期罚息计提mapper
     */
    @Autowired
    FxDrawBookDetailMapper fxDrawBookDetailMapper;

    /**
     * 标的共通方法service
     */
    @Autowired
    private FinProductCommonService finProductCommonService;

    /**
     * 计算类共通
     */
    @Autowired
    private CalculationCommonService calculationCommonService;

    /**
     * 公用方法services
     */
    @Autowired
    private AccountingCommonService accountingCommonService;

    /**
     * 记账公共类
     */
    @Autowired
    private BookCommonLogic bookCommonLogic;

    /**
     * 记账引擎
     */
    @Autowired
    private BookAccountGenLogic bookLogic;

    /**
     * 逾期罚息计提处理service执行方法
     * @param params
     * @return
     */
    public BookGenerateResultWithBokSummary execute(Map<String, Object> params) {

        // 获取reader返回值
        DrawBaseInfoSelectComResult acctEntity = (DrawBaseInfoSelectComResult) params.get(
                GlobalConstant.READER_RESULT);
        // 获取待处理任务信息
        SysBatchTask batchTask = (SysBatchTask) params.get("BATCHTASK");
        Long taskPri = batchTask.getTaskPri();//批处理参数.任务优先级
        // 定义返回值
        BookGenerateResultWithBokSummary returnResult = new BookGenerateResultWithBokSummary();

        // 账务明细若为空赋初始值
        if (returnResult.getValBokBookdetailBatch() == null) {
            returnResult.setValBokBookdetailBatch(new ArrayList<ValBokBookdetailBatch>());
        }

        String booksetIdNow = acctEntity.getBooksetId();// 账套代码
        String bokDetailIdNow = acctEntity.getBokDetailId();// 账务明细代码
        TreeNode bookSetTreeNodeNow = acctEntity.getBookSetTreeNode();// 会计场景
        String busiIdNow = acctEntity.getBusiId();//业务明细代码
        String busiDetailTypeNow = acctEntity.getBusiDetailType();//业务明细类型
        Long branchNow = acctEntity.getBranch();//分支序号
        Date happenDateNow = acctEntity.getHappenDate();// 发生日期
        Date bookDateNow = acctEntity.getBookDate();//入账日
        String baseSubjectNatureNow = acctEntity.getBaseSubjectNature();//基础科目性质

        //1.数据准备
        //1.1 变量定义
        //定义 变量.应收罚息 = 0
        //BigDecimal overDueInterest = BigDecimal.ZERO;
        //定义 变量.应收罚息余额 = 0
        //BigDecimal overDueBalance = BigDecimal.ZERO;

        //定义 变量.罚息利率 = 0
        //BigDecimal overDueRate = BigDecimal.ZERO;
        //定义 变量.罚息计息基础

        //1.2 查询交易类标的附属表
        List<FxDrawBookFinTradeProductDto> tradeList = this.getTradeProductAdd(busiIdNow, busiDetailTypeNow, branchNow);
        FxDrawBookFinTradeProductDto tradeDto = null;
        if(!StringUtil.isEmptyList(tradeList)) {
            //定义 变量.交易类标的信息
            tradeDto = tradeList.get(0);
        }

        //标的相关信息
        //1.3 查询：现金流计算规则、现金流计划
        Map<String, Object> targetInfoMap = this.getTargetInfo(busiIdNow, busiDetailTypeNow, branchNow,
                DictCodeListExt1.现金流二级类型_罚息.getValue(), null, true);
        //现金流计划
        List<TargetCashPlan> targetCashPlanList = (List<TargetCashPlan> ) targetInfoMap.get(ValDictCodeList.标的_现金流计划.getValue());
        //现金流计算规则
        List<TargetCashCalcRule> targetCashCalcRuleList = (List<TargetCashCalcRule> ) targetInfoMap.get(ValDictCodeList.标的_现金流计算规则.getValue());
        //IF 变量.现金流计划List IS NULL OR 变量.现金流计划List.SIZE==0
        if(StringUtil.isEmptyList(targetCashPlanList)) {
            //return null
            return null;
        }
        ////定义 变量.现金流计划DTO  = 变量.现金流计划List.get(0)
        TargetCashPlan targetCashPlan = targetCashPlanList.get(0);
        //IF 变量.现金流计划DTO.起息日 > 计提类账务处理结果.发生日 OR  变量.现金流计划DTO.到期日< 计提类账务处理结果.发生日
        if(DateUtil.compareDate(targetCashPlan.getVdate(), happenDateNow) > 0 || DateUtil.compareDate(targetCashPlan.getMdate(), happenDateNow) < 0) {
            //return null
            return null;
        }
        //IF 变量.现金流计算规则List IS NULL OR 变量.现金流计算规则List.SIZE==0
        if(StringUtil.isEmptyList(targetCashCalcRuleList)) {
            //return null
            return null;
        }
        //变量.罚息利率 = 变量.现金流计算规则List.get（生效日最大的记录）.生效利率
        //overDueRate = targetCashCalcRuleList.get(targetCashCalcRuleList.size() - 1).getYield();
        //overDueRate = getTargetCashCalcRuleLatest(happenDateNow, targetCashCalcRuleList).getYield();
        //1.4 查询：计息基础
        Map<String, Object> calBasicTargetInfoMap = this.getTargetInfo(busiIdNow, busiDetailTypeNow, null,
                DictCodeListExt1.现金流二级类型_利息.getValue(), happenDateNow, false);
        //定义 变量.资产报价列表  = 返回结果.get("RST_CASH_CALCRULE")
        List<TargetCashCalcRule> calBasisTargetCashCalcRuleList = (List<TargetCashCalcRule> ) calBasicTargetInfoMap.get(ValDictCodeList.标的_现金流计算规则.getValue());
        //IF 变量.资产报价列表 IS NULL
         if(StringUtil.isEmptyList(calBasisTargetCashCalcRuleList)) {
            //return null
            return null;
        }
        //变量.罚息计息基础= 变量.资产报价列表 .get（生效日最大的记录）.计算基础
        //String overDueBasis = calBasisTargetCashCalcRuleList.get(calBasisTargetCashCalcRuleList.size() - 1).getBasis();
        TargetCashCalcRule targetCashCalcRule = getTargetCashCalcRuleLatest(happenDateNow, calBasisTargetCashCalcRuleList);
        String overDueBasis = targetCashCalcRule==null?null:targetCashCalcRule.getBasis();
        //1.5 科目余额查询

        //定义 变量.其他应收款科目余额  = 返回值.科目余额.原币余额
        BigDecimal qtyskBalance = this.getSubjectBalance(booksetIdNow, bokDetailIdNow, happenDateNow, bookDateNow,
                branchNow, baseSubjectNatureNow, taskPri, DictCodeList.余额方向_借方.getValue());

        //调用共通设计->【详细设计-账务类共通方法设计.xlsx】->科目余额查询-期待方向SHEET
        //变量.应收罚息余额 = 返回值.科目余额.原币余额
        BigDecimal ysfxBalance = this.getSubjectBalance(booksetIdNow, bokDetailIdNow, happenDateNow, bookDateNow,
                branchNow, DictCodeBaseSubjectNatrue.逾期资产应计利息.getValue(), taskPri, DictCodeList.余额方向_借方.getValue());

//        // 获取计息基础天数
//        BigDecimal basisDay = new BigDecimal(calculationCommonService.calcInterestBasisDays(
//                new InterestBasisDaysParameter(overDueBasis)));

        //2.处理交易
        List<DrawBaseInfoSelectComDTO> listDrawBaseInfoSelectComDTONow = acctEntity.getListDrawBaseInfoSelectComDTO(); // 计提基础信息列表
        //2.1 逾期计提
        if(!StringUtil.isEmptyList(listDrawBaseInfoSelectComDTONow)) {
            //for（计提明细 ：计提类账务处理结果.计提明细列表）
            for(DrawBaseInfoSelectComDTO dto : listDrawBaseInfoSelectComDTONow) {
                // 初始化定义 - 记账场景要素管理
                AccountDriver ad = new AccountDriver();
                //ad.账套代码=计提类账务处理结果.账套代码;
                //ad.交易编号=计提明细.交易编号;
                ad.setTreeNode(bookSetTreeNodeNow);// 塞入记账场景树节点 = 会计场景
                // 初始化定义 - 存放该笔记账的场景要素 map定义
                Map<String, Object> accountSceneMap = new HashMap<>();
                // 初始化定义 - 存放该笔记账的相关金额要素 map定义
                Map<String, BigDecimal> accountAmountMap = new HashMap<>();
                // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
                Map<String, Object> accountCurrencyMap = new HashMap<>();
                Map<String, Map<String, Object>> accountNatrueMap = new HashMap<>();
                /**
                 * IF 变量.交易类标的信息 IS NOT NULL
                 * 	AND 数据字典：是否.是 eq 变量.交易类标的信息.截止日是否冲回
                 * 	 AND 变量.应收罚息余额
                 * 	AND 变量.现金流计划DTO.到期 = 计提类账务处理结果.发生日
                 */
                if(tradeDto != null && DictCodeList.是否_是.getValue().equals(tradeDto.getIsRushBack())
                    && ysfxBalance != null && ysfxBalance.compareTo(BigDecimal.ZERO) > 0
                    && DateUtil.compareDate(targetCashPlan.getMdate(), happenDateNow) == 0) {
                    //ad.会计场景代码="逾期款罚息冲正"
                    ad.setTableId(DictCodeListTableId.会计场景代码_逾期款罚息冲正.getValue());
                    //定义 变量.冲正利息 = 变量.应收罚息余额
                    BigDecimal czlx = ysfxBalance;
                    // 调用共通场景元素赋值-计提类场景元素
                    accountSceneMap = bookCommonLogic.accrualElementAssign(acctEntity, dto);
                    //备注 <- "逾期款罚息冲正"
                    accountSceneMap.put(BookConstant.BOOK_REMARK, "逾期款罚息冲正");

                    // 记账金额元素map塞值
                    // 应收利息 = 变量.冲正利息
                    accountAmountMap.put(BookConstant.INTAMT, czlx);//
                    // 记账金额元素币种信息塞值
                    accountCurrencyMap.put(BookConstant.INTAMT, dto.getoCcy());// 应计利息 值塞入当前交易币种

                }
                //ELSE IF 变量.现金流计划Dto.到期日 > 计提类账务处理结果.发生日
                else if(DateUtil.compareDate(targetCashPlan.getMdate(), happenDateNow) > 0){
                    //ad.会计场景代码="逾期款罚息计提"
                    ad.setTableId(DictCodeListTableId.会计场景代码_逾期款罚息计提.getValue());
                    // 调用共通场景元素赋值-计提类场景元素
                    accountSceneMap = bookCommonLogic.accrualElementAssign(acctEntity, dto);
                    //备注 <- "逾期款罚息每日计提"
                    accountSceneMap.put(BookConstant.BOOK_REMARK, "逾期款罚息每日计提");

                    //变量.应收罚息 = 变量.其他应收款科目余额*变量.利率/变量.计息基础
                    //BigDecimal ysfx = ArithmeticUtil.divide(ArithmeticUtil.multiply(ysfxBalance, overDueRate), basisDay);
                    BigDecimal ysfx = this.getFxjtLx(happenDateNow, qtyskBalance, overDueBasis, tradeDto, targetCashCalcRuleList, targetCashPlan,busiDetailTypeNow);
                    // 记账金额元素map塞值
                    // 应收利息 = 变量.冲正利息
                    accountAmountMap.put(BookConstant.INTAMT, ysfx);
                    // 记账金额元素币种信息塞值
                    accountCurrencyMap.put(BookConstant.INTAMT, dto.getoCcy());// 应计利息 值塞入当前交易币种
                }
                //accountNatrueMap设置如下：
                Map<String, Object> accountNatrueBranchMap = new HashMap<>();
                //accountNatrueBranchMap.put(分支序号, 交易明细.分支序号);
                accountNatrueBranchMap.put(BookConstant.BRANCH, dto.getBranch());
                //accountNatrueMap.put("逾期资产应计利息", accountNatrueBranchMap);
                accountNatrueMap.put(DictCodeBaseSubjectNatrue.逾期资产应计利息.getValue(), accountNatrueBranchMap);
                
                accountSceneMap.put(BookConstant.IS_ACC_BALANCE,DictCodeList.是否_否.getValue());

                // map塞入场景要素管理中
                ad.setAccountSceneMap(accountSceneMap);// 记账场景要素
                ad.setAccountAmountMap(accountAmountMap);// 记账金额要素
                ad.setAccountCurrencyMap(accountCurrencyMap);// 记账金额币种要素
                ad.setAccountNatrueMap(accountNatrueMap);//基础科目性质

                // 记账凭证生成
                BookGenerateResult resultNowNow = bookLogic.genAccount(ad, new SysBatchExecInfo());
                // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
                returnResult.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());
            }
        }
        return returnResult;
    }

    /**
     * 查询交易类金融产品附属表
     * @param finprodId
     * @param finprodType
     * @param branch
     * @return
     */
    private List<FxDrawBookFinTradeProductDto> getTradeProductAdd(String finprodId, String finprodType, Long branch) {
        Map<String,Object> map = new HashMap<>();
        map.put("finprodId", finprodId);
        map.put("finprodType", finprodType);
        map.put("branch", branch);
        return fxDrawBookDetailMapper.selectFinTradeProductAdd(map);
    }

    /**
     * 查询标的相关信息
     * @param finprodId
     * @param finprodType
     * @param branch
     * @param cashType2
     * @param happenDate
     * @param qryTargetCashPlanFlag
     * @return
     */
    private Map<String, Object> getTargetInfo(String finprodId, String finprodType, Long branch,
                                              String cashType2, Date happenDate, boolean qryTargetCashPlanFlag) {
        // 定义返回值
        Map<String, Object> returnMap = new HashMap<String, Object>();
        // 查询类型
        List<TargetSearchDto> queryCriteria = new ArrayList<TargetSearchDto>();
        TargetSearchDto 现金流计划 = new TargetSearchDto();
        if(qryTargetCashPlanFlag) {
            // 传入[标的_现金流计划]Dto
            现金流计划.setQueryType(ValDictCodeList.标的_现金流计划.getValue());//查询类型
            现金流计划.setFinprodId(finprodId);//金融产品ID
            现金流计划.setFinprodType(finprodType);//金融产品类型
            现金流计划.setBranch(branch);//分支序号
            现金流计划.setCashType2(cashType2);//现金流二级类型
            queryCriteria.add(现金流计划);
        }
        // 传入[标的_现金流计算规则]Dto
        TargetSearchDto 现金流计算规则 = new TargetSearchDto();
        现金流计算规则.setQueryType(ValDictCodeList.标的_现金流计算规则.getValue());//查询类型
        现金流计算规则.setFinprodId(finprodId);//金融产品ID
        现金流计算规则.setFinprodType(finprodType);//金融产品类型
        if(branch != null) {
            现金流计算规则.setBranch(branch);//分支序号
        }
        现金流计算规则.setCashType2(cashType2);//现金流二级类型
        if(happenDate != null) {
            现金流计算规则.setEffdateEnd(happenDate);//生效日期 - 结束
        }
        queryCriteria.add(现金流计算规则);

        // 查询标的信息  调用共通设计->【详细设计-标的类共通方法设计.xlsx】->标的信息查询SHEET
        Map<TargetSearchDto, Object> targetInfoMap = finProductCommonService.getTargetInfo(finprodId, queryCriteria);

        // 有返回值数据
        if (!MapUtils.isEmpty(targetInfoMap)) {
            if(qryTargetCashPlanFlag) {
                returnMap.put(ValDictCodeList.标的_现金流计划.getValue(), targetInfoMap.get(现金流计划)); // 标的_现金流计划list
            }
            returnMap.put(ValDictCodeList.标的_现金流计算规则.getValue(), targetInfoMap.get(现金流计算规则)); // 标的_现金流计算规则list
        }

        return returnMap;
    }

    /**
     * 调用公用方法：【科目余额查询 - 期待方向】，获取科目余额信息中的科目余额数据
     *
     * @param booksetId 账套代码
     * @param bokDetailId 账务明细代码
     * @param happenDate 发生日
     * @param bookDate 入账日
     * @param branch 分支序号
     * @param baseSubjectNature 基础科目性质
     * @param taskPri 任务优先级
     * @param expBalanceDirect 期待余额方向
     * @return BigDecimal 科目余额
     * @see
     * @since 1.0
     */
    private BigDecimal getSubjectBalance(String booksetId, String bokDetailId, Date happenDate, Date bookDate,
                                             Long branch, String baseSubjectNature, Long taskPri, String expBalanceDirect) {
        // 定义返回实体类
        SubjectBalance subjectBalance = null;
        // 塞入要查询的基础科目性质
        List<String> baseSubjectNatureList = null;
        if (baseSubjectNature != null && baseSubjectNature.length() > 0) {
            baseSubjectNatureList = new ArrayList<String>();
            baseSubjectNatureList.add(baseSubjectNature);
        }
        // 设值科目余额查询参数Dto
        SubjectBalanceSearchDto searchDto = new SubjectBalanceSearchDto();
        searchDto.setBooksetId(booksetId);//账套代码
        searchDto.setBokDetailId(bokDetailId);//账务明细代码
        searchDto.setHappenDate(happenDate);//发生日
        searchDto.setBookDate(bookDate);//入账日
        searchDto.setTaskPri(taskPri);//任务优先级
        searchDto.setBranch(branch);//分支序号
        searchDto.setBaseSubjectNatureList(baseSubjectNatureList);//基础科目性质
        // 调用公用方法：【科目余额查询 - 期待方向】,获取科目余额信息
        List<SubjectBalance> subjectBalanceList = accountingCommonService.getSubjectBalanceExpectDirection(
                searchDto, expBalanceDirect);
        // 获取科目余额
        if (subjectBalanceList != null && subjectBalanceList.size() > 0) {
            subjectBalance = subjectBalanceList.get(0);
            return subjectBalance.getBalance();
            //return ArithmeticUtil.round(subjectBalance.getBalance(), NumberEnum.HIGH_PREC_NUM);
        }
//        else {
//            subjectBalance = new SubjectBalance();
//        }
//        return subjectBalance;
        return null;
    }

    /**
     * 获取计息每日计提
     * @param happenDate 计提日
     * @param jxjs 计息基数
     * @param basisStr 罚息计息基础
     * @param tradeDto 交易标的
     * @param targetCashCalcRuleList 现金计算规则List
     * @param cashPlan 现金流计划dto
     * @return
     */
    private BigDecimal getFxjtLx(Date happenDate, BigDecimal jxjs, String basisStr, FxDrawBookFinTradeProductDto tradeDto,
                                 List<TargetCashCalcRule> targetCashCalcRuleList, TargetCashPlan cashPlan,String finprodType) {
        //1.获取参数
        //1.1 定义变量
        //定义 变量.应收利息BigDecimal
        BigDecimal yslx = null;
        // 定义 变量.现金流计算规则DTO=NULL TargetCashCalcRule
        TargetCashCalcRule targetCashCalcRule = null;
        //1.2 数据准备
        //LOOP 输入.现金流计算规则List 现金流计算规则ENTITY
        for(TargetCashCalcRule rule : targetCashCalcRuleList) {
            //IF 现金流计算规则ENTITY.生效日 > 输入.计提日
            if(DateUtil.compareDate(rule.getEffDate(), happenDate) > 0) {
                continue;
            }
            //IF 变量.现金流计算规则DTO IS NULL OR 变量.现金流计算规则DTO.生效日 < 现金流计算规则ENTITY.生效日
            if(targetCashCalcRule == null || DateUtil.compareDate(targetCashCalcRule.getEffDate(), rule.getEffDate()) < 0) {
                targetCashCalcRule = rule;
            }
        }
        //IF  变量.现金流计算规则DTO IS NULL
        if(targetCashCalcRule == null) {
            return yslx;
        }
        //变量.现金流计算规则DTO.计息基础 = 输入.罚息计息基础
        targetCashCalcRule.setBasis(basisStr);

        //2.处理详情
//        String finprodType =  cashPlan.getFinprodType();
        //IF 数据字典：金融产品类型.同业借款 eq 输入.现金流信息.金融产品类型 OR 数据字典：金融产品类型.存款 eq 输入.现金流信息.金融产品类型
        if(DictCodeListExt1.金融产品类型_同业借款.getValue().equals(finprodType)
            || DictCodeListExt1.金融产品类型_存款.getValue().equals(finprodType)) {
            //定义 变量.报价ENTITY quoteInfo
            QuoteInfo quoteInfo = new QuoteInfo();
            //变量.报价ENTITY.生效日 =变量.现金流计算规则DTO.生效日
            quoteInfo.setIssueDate(targetCashCalcRule.getEffDate());
            //变量.报价ENTITY.利率 =	变量.现金流计算规则DTO.生效利率
            quoteInfo.setInterest(targetCashCalcRule.getYield());
            //变量.报价ENTITY.计息基础 =变量.现金流计算规则DTO.计息基础
            quoteInfo.setAccountBasis(targetCashCalcRule.getBasis());

            //定义 变量.本金ENTITY PrincipalInfo
            PrincipalInfo principalInfo = new PrincipalInfo();

            //变量.本金ENTITY.变动日 = 输入.计提日
            principalInfo.setChangeDate(happenDate);
            //变量.本金ENTITY.本金金额 =	输入.计息基数
            principalInfo.setPrinAmount(jxjs);
            //调用共通设计->【详细设计-计算类共通方法设计.xlsx】->通用计算SHEET
            CalCommonResult calCommonResult = calculationCommonService.calCommon(
                    happenDate, DateUtil.addDay(happenDate, 1),  Arrays.asList(principalInfo), Arrays.asList(quoteInfo));
            // 变量.应收利息 = 返回值.总利息金额
            yslx = calCommonResult.getTotalInterestAmt();
        }
        //ELSE 数据字典：金融产品类型.计划类资产 eq 输入.现金流信息.金融产品类型 OR 数据字典：金融产品类型.债券 eq 输入.现金流信息.金融产品类型
        else if(DictCodeListExt1.金融产品类型_计划类资产.getValue().equals(finprodType)
                || DictCodeListExt1.金融产品类型_债券.getValue().equals(finprodType)) {

            //定义 变量.处理计息基础 = 变量.现金流计算规则DTO.计息基础
            String dealBasis = targetCashCalcRule.getBasis();
            //定义 变量.计息基础DTO	Basis
            Basis basis = this.getBasis(dealBasis);
            //定义 变量.利息分配方式DTO	AllocationType
            AllocationType allocationType = this.getAllocationType(dealBasis);
            //贴现零息债券计息基础先按照默认值A/365处理，项目有实际业务再确认是增加计息基础还是修改债券资料
            if(DictCodeListExt1.金融产品类型_债券.getValue().equals(finprodType)
                    && basis == null){
                basis = Basis.A_365;
            }
            // 对于非A/A的计息基础
            if(!Basis.A_A.equals(basis)) {
                //定义 变量.计息基础算法DTOBasisIntCalculator	= CalculatorFactory.createBasisIntCalculator()
                BasisIntCalculator basisCal = CalculatorFactory.createBasisIntCalculator();
                //定义 变量.计息基础计算器输入参数DTO BasisIntInputParameter
                BasisIntInputParameter basisInput = new BasisIntInputParameter();
                //变量.计息基础计算器输入参数DTO.计算日 =	BasicUtil.dateToLocal(输入.计提日).plusDays(1)
                basisInput.setCalDate(BasicUtil.dateToLocal(happenDate).plusDays(1));
                //变量.计息基础计算器输入参数DTO.计算开始日 =BasicUtil.dateToLocal(输入.计提日)
                basisInput.setCalStartDate(BasicUtil.dateToLocal(happenDate));
                //IF Basis._30_360 eq 变量.计息基础DTO
                if (Basis._30_360.equals(basis)) {
                    //变量.计息基础计算器输入参数DTO.区间计息起始日 =	BasicUtil.dateToLocal(输入.现金流计划DTO.起息日)
                    basisInput.setSecIntStartDate(BasicUtil.dateToLocal(cashPlan.getVdate()));
                }
                //变量.计息基础计算器输入参数DTO.计息基础 =	变量.计息基础DTO
                basisInput.setBasis(basis);
                //定义 变量.计息基础计算结果DTO	BasisIntOutputParameter = 变量.计息基础算法DTO.calculate(变量.计息基础计算器输入参数DTO)
                BasisIntOutputParameter basisOutput = basisCal.calculate(basisInput);
                //变量.应收利息 = 变量.计息基础计算结果DTO.应计利息 * 输入.计息基数 * 变量.现金流计算规则DTO.生效利率
                yslx = ArithmeticUtil.multiply(basisOutput.getInterest(), jxjs, targetCashCalcRule.getYield());
            }
            else {
                //定义 变量.区间理论起始日	LocalDate = BasicUtil.dateToLocal(输入.现金流计划DTO.起息日）
                LocalDate theoryStartDate = BasicUtil.dateToLocal(cashPlan.getVdate());
                //定义 变量.区间理论结束日	LocalDate =BasicUtil.dateToLocal(输入.现金流计划DTO.到期日）
                LocalDate theoryEndDate = BasicUtil.dateToLocal(cashPlan.getMdate());
                //定义 变量.计息年度计算参数DTO	SecCalYearInputParameter
                SecCalYearInputParameter secInput = new SecCalYearInputParameter();
                //变量.计息年度计算参数DTO.计算日 =	BasicUtil.dateToLocal(输入.计提日).plusDays(1)
                // modify by jiangpeng on 20200730 跑批倒数第二天报错 计息日等于到期日故删去 plusDays(1)
                secInput.setCalDate(BasicUtil.dateToLocal(happenDate));
                //变量.计息年度计算参数DTO.息票品种 =Variety.附息
                secInput.setVariety(Variety.附息);
                //变量.计息年度计算参数DTO.推算方式 = SecIntSchCalMethod.起息日向后
                secInput.setCalMethod(SecIntSchCalMethod.起息日向后);
                //变量.计息年度计算参数DTO.起息日（债券起始日） =	输入.现金流计划DTO.起息日
                secInput.setStartDate(BasicUtil.dateToLocal(cashPlan.getVdate()));
                //变量.计息年度计算参数DTO.到期日（债券到期日） =	输入.现金流计划DTO.到期日
                secInput.setEndDate(BasicUtil.dateToLocal(cashPlan.getMdate()));
                //定义 变量.计息年度天数输出DTO SecCalYearDayCountOutputParameter
                //变量.计息年度天数输出DTO = CalculatorFactory.createSecCalYearDayCountCalculator().calculate(变量.计息年度计算参数DTO)
                SecCalYearDayCountOutputParameter yearDayCount = CalculatorFactory.createSecCalYearDayCountCalculator().calculate(secInput);
                //定义 变量.计息基础算法DTO BasisIntCalculator = CalculatorFactory.createBasisIntCalculator()
                BasisIntCalculator basisCal = CalculatorFactory.createBasisIntCalculator();
                //定义 变量.计息基础计算器输入参数DTO
                BasisIntInputParameter basisInput = new BasisIntInputParameter();
                //变量.计息基础计算器输入参数DTO.计算日 =	BasicUtil.dateToLocal(输入.计提日).plusDays(1)
                basisInput.setCalDate(BasicUtil.dateToLocal(happenDate).plusDays(1));
                //变量.计息基础计算器输入参数DTO.计算开始日 =	BasicUtil.dateToLocal(输入.计提日)
                basisInput.setCalStartDate(BasicUtil.dateToLocal(happenDate));
                //变量.计息基础计算器输入参数DTO.计息基础 =	变量.计息基础DTO
                basisInput.setBasis(basis);
                //变量.计息基础计算器输入参数DTO.付息频率 =	IntFrequency.年付
                basisInput.setYearIntRate(IntFrequency.年付);
                //变量.计息基础计算器输入参数DTO.利息分配方式 = 变量.利息分配方式DTO
                basisInput.setAllocationType(allocationType);
                //变量.计息基础计算器输入参数DTO.计息年度天数 = 变量.计息年度天数输出DTO.计息年度天数
                basisInput.setYearTotalDays((int) yearDayCount.getDay());

                /** 平均分配A/A 特殊处理 modify by yanya on 20200330 **/
                if(Basis.A_A.equals(basis) && AllocationType.平均分配.equals(allocationType)) {
                    //变量.计息基础计算器输入参数DTO.理论计息周期天数 =BasicUtil.days(发生日, 发生日+1年)
                    basisInput.setCycleDays(BasicUtil.days(BasicUtil.dateToLocal(DateUtil.addYear(happenDate, 1)),
                            BasicUtil.dateToLocal(happenDate)));
                }
                else {
                    //变量.计息基础计算器输入参数DTO.理论计息周期天数 =BasicUtil.days(变量.区间理论结束日, 变量.区间理论起始日)
                    basisInput.setCycleDays(BasicUtil.days(theoryEndDate, theoryStartDate));
                }

                //定义 变量.计息基础计算结果DTO	BasisIntOutputParameter
                //变量.计息基础计算结果DTO =变量.计息基础算法DTO.calculate(变量.计息基础计算器输入参数DTO)
                BasisIntOutputParameter basisOutput = basisCal.calculate(basisInput);
                //变量.应收利息 = 变量.计息基础计算结果DTO.应计利息 * 输入.计息基数 * 变量.现金流计算规则DTO.生效利率
                yslx = ArithmeticUtil.multiply(basisOutput.getInterest(), jxjs, targetCashCalcRule.getYield());
            }
        }
        //变量.应收利息 = ArithmeticUtil.round(变量.应收利息,NumberEnum.MONEY)
        return ArithmeticUtil.round(yslx, NumberEnum.MONEY);
    }

    /**
     * 将计息基础 转为 算法能识别得计息基础
     * @param basisString
     * @return
     */
    private Basis getBasis(String basisString) {
        Basis basis = null;
        if (DictCodeList.计息基础_A_360.getValue().equals(basisString)) {
            basis = Basis.A_360;
        } else if (DictCodeList.计息基础_A_365.getValue().equals(basisString)) {
            basis = Basis.A_365;
        } else if (DictCodeList.计息基础_A_A_平均分配.getValue().equals(basisString)
                || DictCodeList.计息基础_A_A_按实际天数分配.getValue().equals(basisString)) {
            basis = Basis.A_A;
        } else if (DictCodeList.计息基础_A_365F.getValue().equals(basisString)) {
            basis = Basis.A_365F;
        } else if (DictCodeList.计息基础_30_360.getValue().equals(basisString)) {
            basis = Basis._30_360;
        } else if (DictCodeListExt1.计息基础_A_A_D.getValue().equals(basisString)) {
            basis = Basis.A_A_D;
        }
        return basis;
    }

    /**
     * 获取利息分配方式dto
     * @param basisStr
     * @return
     */
    private AllocationType getAllocationType(String basisStr) {
        // 根据计息基础确定利息分配方式
        AllocationType allocationType = null;
        if (DictCodeList.计息基础_A_A_平均分配.getValue().equals(basisStr)) {
            allocationType = AllocationType.平均分配;
        }
        else if (DictCodeList.计息基础_A_A_按实际天数分配.getValue().equals(basisStr)) {
            allocationType = AllocationType.按实际天数分配;
        }
        else {
            allocationType = AllocationType.按实际天数分配;
        }
        return allocationType;
    }

    /**
     * 取最新的一条计算规则
     * @param happenDate
     * @param targetCashCalcRuleList
     * @return
     */
    private TargetCashCalcRule getTargetCashCalcRuleLatest(Date happenDate, List<TargetCashCalcRule> targetCashCalcRuleList) {
        if(!StringUtil.isEmptyList(targetCashCalcRuleList)) {
            //默认取最新的一条
            int num = targetCashCalcRuleList.size() - 1;
            for (int i = 0; i < targetCashCalcRuleList.size(); i++) {
                TargetCashCalcRule targetCashCalcRule = targetCashCalcRuleList.get(i);
                if (targetCashCalcRule.getEffDate().compareTo(happenDate) > 0) {
                    num = i - 1;
                    break;
                }
            }
            return targetCashCalcRuleList.get(num);
        }
        return null;
    }
}
