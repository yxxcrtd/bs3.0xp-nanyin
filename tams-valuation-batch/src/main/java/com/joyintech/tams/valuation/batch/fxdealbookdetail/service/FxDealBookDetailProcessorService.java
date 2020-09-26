package com.joyintech.tams.valuation.batch.fxdealbookdetail.service;
/**
 * @author yanya
 * @create 2019-12-12 14:10
 */

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListTableId;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalance;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalanceSearchDto;
import com.joyintech.tams.valuation.batch.common.accounting.service.AccountingCommonService;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookAccountGenLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.AccountDriver;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.TreeNode;
import com.joyintech.tams.valuation.batch.common.base.entity.extcommon.ValBokBookdetailBatch;
import com.joyintech.tams.valuation.batch.common.dealinfoselectcom.dto.FinProductDealInfoComDTO;
import com.joyintech.tams.valuation.batch.common.dealinfoselectcom.dto.FinProductDealInfoResult;
import com.joyintech.tams.valuation.batch.common.finproduct.service.FinProductCommonService;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetFinProduct;
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
import java.util.*;

/**
 * @ClassName FxDealBookDetailProcessorService
 * @Description: 罚息交易账务处理
 * @Author yanya
 * @Date 2019/12/24
 * @Version V1.0
 **/
@Service
@Scope("prototype")
public class FxDealBookDetailProcessorService {

    /**
     * 标的共通方法service
     */
    @Autowired
    private FinProductCommonService finProductCommonService;

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
     * 公用方法services
     */
    @Autowired
    private AccountingCommonService accountingCommonService;

    /**
     * 数据处理
     * @param param
     * @return
     */
    public BookGenerateResult execute(Map<String, Object> param) {
        //获取reader返回值
        FinProductDealInfoResult acctEntity = (FinProductDealInfoResult)param.get(GlobalConstant.READER_RESULT);

        // 若reader传递的参数或交易明细为空，不进行后续处理
        if (acctEntity == null || acctEntity.getListFinProductDealInfoComDTO() == null || acctEntity.getListFinProductDealInfoComDTO().isEmpty()) {
            return null;
        }

        // 获取待处理任务信息
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)param.get("BATCHINFO");

        // 获取从reader中拿到的值
//        String booksetIdNow = acctEntity.getBooksetId();// 账套代码
//        String bokDetailIdNow = acctEntity.getBokDetailId();// 账务明细代码
        // String invAimNow = acctEntity.getInvAim();// 投资目的
        TreeNode bookSetTreeNodeNow = acctEntity.getBookSetTreeNode();// 会计场景
//        Date happenDateNow = acctEntity.getHappenDate();// 发生日期
//        Date bookDateNow = acctEntity.getBookDate();// 入账日期
        List<FinProductDealInfoComDTO> listFinProductDealInfoComDTONow = acctEntity.getListFinProductDealInfoComDTO(); // 交易基础信息列表
        Long taskPri = listFinProductDealInfoComDTONow.get(0).getTaskPri();//任务优先级（交易基础信息列表的任务优先级都是一致的，故取第一个）
//        String finprodTypeNow = listFinProductDealInfoComDTONow.get(0).getFinprodType();//金融产品类型（取第一个）


        // 定义此process的返回变量
        BookGenerateResult rtnResult = new BookGenerateResult();

        // 账务明细若为空赋初始值
        if (rtnResult.getValBokBookdetailBatch() == null) {
            rtnResult.setValBokBookdetailBatch(new ArrayList<ValBokBookdetailBatch>());
        }
        //1.数据准备
        //1.1数据准备
        //定义  变量.其他应收款科目余额Map
//        Map<String, BigDecimal> balanceMap = new HashMap<>();
        //定义  变量.应收罚息科目余额Map
        Map<String, BigDecimal> fsSubjectBalanceMap = new HashMap<>();
        //定义 变量.交易类标的信息Map	Key:金融产品代码+金融产品类型+分支序号，Value:交易类标的信息
        Map<String, TargetFinProduct> targetFinProductMap = new HashMap<>();
        //3.处理结果
        for(FinProductDealInfoComDTO dealDto : listFinProductDealInfoComDTONow) {
            //定义		变量.交易类标的信息 = 变量.交易类标的信息Map.get(交易明细.金融产品代码+交易明细.金融产品类型+交易明细.分支序号)
            String targetFinProductKey = dealDto.getFinprodId().trim() + dealDto.getFinprodType().trim()
                    + dealDto.getBranch().toString().trim();
            TargetFinProduct targetFinProduct = targetFinProductMap.get(targetFinProductKey);
            //3.1 交易类标的信息查询
            if(targetFinProduct == null) {
                Map<String, Object> targetInfoMap = this.getTargetInfo(dealDto.getFinprodId(), dealDto.getFinprodType(), dealDto.getBranch());
                //定义	变量.交易类标的信息
                targetFinProduct = (TargetFinProduct) targetInfoMap.get(ValDictCodeList.标的_信息.getValue());
                if (targetFinProduct == null) {
                    continue;
                }
                targetFinProductMap.put(targetFinProductKey, targetFinProduct);
            }

            // 组装记账元素
            // 初始化定义 - 记账场景要素管理
            AccountDriver ad = new AccountDriver();
            // 初始化定义 - 存放该笔记账的场景要素 map定义
            // 记账场景要素map塞值,(调用共通方法-交易类场景元素赋值)
            Map<String, Object> accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealDto);;
            // 初始化定义 - 存放该笔记账的相关金额要素 map定义
            Map<String, BigDecimal> accountAmountMap = new HashMap<>();
            // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
            Map<String, Object> accountCurrencyMap = new HashMap<>();

            Map<String, Map<String, Object>> accountNatrueMap = new HashMap<>();

            // 设置账务引擎参数
            ad.setTreeNode(bookSetTreeNodeNow);// 塞入记账场景树节点 = 会计场景

            //accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealDto);
            //3.2 交易入账处理
            //if（交易方向 = "买入"）
            if(DictCodeList.交易方向_买入.getValue().equals(dealDto.getPs())) {
                //ad.会计场景代码="逾期资产罚息转入"
                ad.setTableId(DictCodeListTableId.会计场景代码_逾期资产罚息转入.getValue());
                accountSceneMap.put(BookConstant.BOOK_REMARK, "逾期资产罚息转入交易登账");

                //accountAmountMap设置金融如下
                //实收金额 <- 交易明细.交易金额
                accountAmountMap.put(BookConstant.DEALAMT, dealDto.getTradeAmt());
                //accountCurrencyMap设置金融如下
                //实收金额 <- 交易明细.交易币种
                accountCurrencyMap.put(BookConstant.DEALAMT, dealDto.getCcy());
                //accountNatrueMap设置如下：
                Map<String, Object> accountNatrueBranchMap = new HashMap<>();
                //accountNatrueBranchMap.put(分支序号, 交易明细.分支序号);
                accountNatrueBranchMap.put(BookConstant.BRANCH, dealDto.getBranch());
                accountNatrueMap.put(DictCodeBaseSubjectNatrue.逾期资产应计利息.getValue(), accountNatrueBranchMap);

                //全局变量的更新
                //IF 变量.应收罚息科目余额Map.get(交易明细.分支序号) == NULL
                if(fsSubjectBalanceMap.get(dealDto.getBranch().toString().trim()) == null) {
                    //调用共通设计->【详细设计-账务类共通方法设计.xlsx】->科目余额查询-期待方向SHEET
                    BigDecimal balance =  this.getDealSubjectBalance(dealDto.getBooksetId(), dealDto.getBokDetailId(), dealDto.getBookDate(),
                            dealDto.getHappenDate(), dealDto.getBranch(), DictCodeBaseSubjectNatrue.逾期资产应计利息.getValue(),
                            taskPri, DictCodeList.余额方向_借方.getValue());
                    //变量.应收罚息科目余额Map.put(交易明细.分支序号,DTO.科目余额)
                    fsSubjectBalanceMap.put(dealDto.getBranch().toString().trim(), balance);
                }
                //变量.应收罚息科目余额Map.put(交易明细.分支序号,变量.应收罚息科目余额Map.get(交易明细.分支序号)+ 交易明细.交易金额)
                BigDecimal addBalance = ArithmeticUtil.doAdd(dealDto.getTradeAmt(), fsSubjectBalanceMap.get(dealDto.getBranch().toString().trim()));
                fsSubjectBalanceMap.put(dealDto.getBranch().toString().trim(), addBalance);
            }
            //if（交易方向 = "卖出"）
            else if(DictCodeList.交易方向_卖出.getValue().equals(dealDto.getPs())) {
                //ad.会计场景代码="逾期资产罚息转出"
                ad.setTableId(DictCodeListTableId.会计场景代码_逾期资产罚息转出.getValue());
                accountSceneMap.put(BookConstant.BOOK_REMARK, "逾期资产罚息转出交易登账");
                //accountAmountMap设置金融如下
                //实收金额 <- 交易明细.交易金额
                accountAmountMap.put(BookConstant.DEALAMT, dealDto.getTradeAmt());
                //accountCurrencyMap设置金融如下
                //实收金额 <- 交易明细.交易币种
                accountCurrencyMap.put(BookConstant.DEALAMT, dealDto.getCcy());

                //定义 变量.应收罚息科目余额 = 交易明细.利息总额
                BigDecimal ysfxSubjectBalance = dealDto.getIntAmt();
                
                /**MODIFY BY WUJIE ON 20200212 START
                //IF([交易明细].[是否结清] == 'Y'){
                if (DictCodeList.是否_是.getValue().equals(dealDto.getIsClean())) {
                    //IF 变量.应收罚息科目余额Map.get(交易明细.分支序号) == NULL
                    if(fsSubjectBalanceMap.get(dealDto.getBranch().toString().trim()) == null) {
                        //调用共通设计->【详细设计-账务类共通方法设计.xlsx】->科目余额查询-期待方向SHEET
                        ysfxSubjectBalance = this.getDealSubjectBalance(dealDto.getBooksetId(), dealDto.getBokDetailId(), dealDto.getBookDate(),
                                dealDto.getHappenDate(), dealDto.getBranch(), DictCodeBaseSubjectNatrue.逾期资产应计利息.getValue(),
                                taskPri, DictCodeList.余额方向_借方.getValue());
                        //变量.应收罚息科目余额Map.put(交易明细.分支序号,变量.应收罚息科目余额)
                        fsSubjectBalanceMap.put(dealDto.getBranch().toString().trim(), ysfxSubjectBalance);
                    }
                }
                **/
                //IF 变量.应收罚息科目余额Map.get(交易明细.分支序号) == NULL
                if(fsSubjectBalanceMap.get(dealDto.getBranch().toString().trim()) == null) {
                	//变量.应收罚息科目余额Map.put(交易明细.分支序号,变量.应收罚息科目余额)
                    fsSubjectBalanceMap.put(dealDto.getBranch().toString().trim(), this.getDealSubjectBalance(dealDto.getBooksetId(), dealDto.getBokDetailId(), dealDto.getBookDate(),
                            dealDto.getHappenDate(), dealDto.getBranch(), DictCodeBaseSubjectNatrue.逾期资产应计利息.getValue(),
                            taskPri, DictCodeList.余额方向_借方.getValue()));
                }
                BigDecimal 应收罚息科目余额 = ArithmeticUtil.doAdd(fsSubjectBalanceMap.get(dealDto.getBranch().toString().trim()));
                //IF([交易明细].[是否结清] == 'Y'){
                if (DictCodeList.是否_是.getValue().equals(dealDto.getIsClean())) {
                	ysfxSubjectBalance = 应收罚息科目余额;
                }
                else{
                	//应收利息余额 - 实付金额 < 0
                	BigDecimal 差额 = ArithmeticUtil.doSub(应收罚息科目余额,dealDto.getIntAmt());
                	if(差额.compareTo(BigDecimal.ZERO)<0){
                		ysfxSubjectBalance = 应收罚息科目余额;
                	}
                }
                /**MODIFY BY WUJIE ON 20200212 END**/
                
                //应付罚息 <-	变量.应收罚息科目余额
                accountAmountMap.put(BookConstant.INTAMT, ysfxSubjectBalance);
                accountCurrencyMap.put(BookConstant.INTAMT, dealDto.getCcy());
                //定义 变量.罚息利差 = 交易明细.利息总额  - 变量.应收罚息科目余额
                BigDecimal fxlc = ArithmeticUtil.doSub(dealDto.getIntAmt(), ysfxSubjectBalance);
                //IF 变量.罚息利差 > 0
                if(fxlc != null && fxlc.compareTo(BigDecimal.ZERO) > 0){
                    accountAmountMap.put(BookConstant.DSCLOSS_C, fxlc);
                    accountCurrencyMap.put(BookConstant.DSCLOSS_C, dealDto.getCcy());
                }
                //ELSE
                else {
                    //accountAmountMap.put(BookConstant.DSCLOSS_D, fxlc.negate());
                    accountAmountMap.put(BookConstant.DSCLOSS_D, ArithmeticUtil.multiply(fxlc, new BigDecimal(-1)));
                    accountCurrencyMap.put(BookConstant.DSCLOSS_D, dealDto.getCcy());
                }
                //accountNatrueMap设置如下：
                Map<String, Object> accountNatrueBranchMap = new HashMap<>();
                //accountNatrueBranchMap.put(分支序号, 交易明细.分支序号);
                accountNatrueBranchMap.put(BookConstant.BRANCH, dealDto.getBranch());
                accountNatrueMap.put(DictCodeBaseSubjectNatrue.逾期资产应计利息.getValue(), accountNatrueBranchMap);
                //全局变量的更新
                //变量.应收罚息科目余额Map.put(交易明细.分支序号,变量.应收罚息科目余额Map.get(交易明细.分支序号)-变量.应收罚息科目余额)
                BigDecimal subBalance = ArithmeticUtil.doSub(fsSubjectBalanceMap.get(dealDto.getBranch().toString().trim()), ysfxSubjectBalance);
                fsSubjectBalanceMap.put(dealDto.getBranch().toString().trim(), subBalance);
            }
            //if（交易方向 = "收息到账"）
            else if(DictCodeList.交易方向_收息到账.getValue().equals(dealDto.getPs())) {
                //ad.会计场景代码="逾期资产罚息收款"
                ad.setTableId(DictCodeListTableId.会计场景代码_逾期资产罚息收款.getValue());
                accountSceneMap.put(BookConstant.BOOK_REMARK, "逾期资产罚息收款交易登账");
                //实收金额 <- 交易明细.交易金额
                accountAmountMap.put(BookConstant.DEALAMT, dealDto.getTradeAmt());
                //accountCurrencyMap设置金融如下
                //实收金额 <- 交易明细.交易币种
                accountCurrencyMap.put(BookConstant.DEALAMT, dealDto.getCcy());
                //定义 变量.冲减应收罚息 = 交易明细.利息金额
                BigDecimal cjysfx = dealDto.getIntAmt();
                if (DictCodeList.是否_是.getValue().equals(dealDto.getIsClean())) {
                    //调用共通方法：科目余额汇总查询
                    SubjectBalanceSearchDto searchDto = new SubjectBalanceSearchDto();
                    searchDto.setBooksetId(dealDto.getBooksetId());// 账套代码
                    searchDto.setBookDate(dealDto.getBookDate());// 入账日
                    searchDto.setHappenDate(DateUtil.addDay(dealDto.getMdate(), -1));// 发生日 = 交易明细.到期日 - 1
                    searchDto.setBokDetailId(dealDto.getBokDetailId());// 账务明细代码
                    searchDto.setBaseSubjectNatureList(Arrays.asList(DictCodeBaseSubjectNatrue.逾期资产应计利息.getValue()));// 基础科目性质
                    searchDto.setBranch(dealDto.getBranch());//分支序号
                    //根据分支序号取查询
                    searchDto.setBranchHandleFlag("02");
                    Map<String, BigDecimal> baseMap =  this.getSubjectBalanceCollection(searchDto, DictCodeList.余额方向_借方.getValue(), null);
                    cjysfx = baseMap.get(dealDto.getCcy()) == null ? BigDecimal.ZERO : baseMap.get(dealDto.getCcy());
                }
                //应付罚息 <-	变量.冲减应收罚息
                accountAmountMap.put(BookConstant.INTAMT, cjysfx);
                accountCurrencyMap.put(BookConstant.INTAMT, dealDto.getCcy());
                //定义 变量.罚息利差 = 交易明细.利息总额  - 变量.冲减应收罚息
                BigDecimal fxlc = ArithmeticUtil.doSub(dealDto.getIntAmt(), cjysfx);

                //IF 变量.罚息利差 > 0
                if(fxlc != null && fxlc.compareTo(BigDecimal.ZERO) > 0){
                    accountAmountMap.put(BookConstant.DSCLOSS_C, fxlc);
                    accountCurrencyMap.put(BookConstant.DSCLOSS_C, dealDto.getCcy());
                }
                //ELSE
                else {
                    //accountAmountMap.put(BookConstant.INCOMEAMT_D, fxlc.negate());
                    accountAmountMap.put(BookConstant.DSCLOSS_D, ArithmeticUtil.multiply(fxlc, new BigDecimal(-1)));
                    accountCurrencyMap.put(BookConstant.DSCLOSS_D, dealDto.getCcy());
                }
                //accountNatrueMap设置如下：
                Map<String, Object> accountNatrueBranchMap = new HashMap<>();
                //accountNatrueBranchMap.put(分支序号, 交易明细.分支序号);
                accountNatrueBranchMap.put(BookConstant.BRANCH, dealDto.getBranch());
                accountNatrueMap.put(DictCodeBaseSubjectNatrue.逾期资产应计利息.getValue(), accountNatrueBranchMap);
                //全局变量的更新
                //变量.应收罚息科目余额Map.put(交易明细.分支序号,变量.应收罚息科目余额Map.get(交易明细.分支序号)- 变量.冲减应收罚息)
                BigDecimal subBalance = ArithmeticUtil.doSub(fsSubjectBalanceMap.get(dealDto.getBranch().toString().trim()), cjysfx);
                fsSubjectBalanceMap.put(dealDto.getBranch().toString().trim(), subBalance);

            }
            // map塞入场景要素管理中
            ad.setAccountSceneMap(accountSceneMap);// 记账场景要素
            ad.setAccountAmountMap(accountAmountMap);// 记账金额要素
            ad.setAccountCurrencyMap(accountCurrencyMap);// 记账金额币种要素
            ad.setAccountNatrueMap(accountNatrueMap);//// 基础科目性质
            // 记账凭证生成
            BookGenerateResult resultNowNow = bookLogic.genAccount(ad, batchExecuteInfo);
            // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
            rtnResult.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());
        }

        return rtnResult;
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
    private Map<String, Object> getTargetInfo(String finprodId, String finprodType, Long branch) {
        // 定义返回值
        Map<String, Object> returnMap = new HashMap<String, Object>();
        List<TargetSearchDto> queryCriteria = new ArrayList<TargetSearchDto>();
        TargetSearchDto 交易标的信息 = new TargetSearchDto();
        // 传入[标的_交易标的信息
        交易标的信息.setQueryType(ValDictCodeList.标的_信息.getValue());//查询类型
        交易标的信息.setFinprodId(finprodId);//金融产品ID
        交易标的信息.setFinprodType(finprodType);//金融产品类型
        交易标的信息.setBranch(branch);//分支序号
        queryCriteria.add(交易标的信息);

        // 查询标的信息  调用共通设计->【详细设计-标的类共通方法设计.xlsx】->标的信息查询SHEET
        Map<TargetSearchDto, Object> targetInfoMap = finProductCommonService.getTargetInfo(finprodId, queryCriteria);

        // 有返回值数据
        if (!MapUtils.isEmpty(targetInfoMap)) {
            returnMap.put(ValDictCodeList.标的_信息.getValue(), targetInfoMap.get(交易标的信息)); // 标的_现金流计算规则list
        }

        return returnMap;
    }


    /**
     * 查询科目余额
     * @param booksetId
     * @param bokDetailId
     * @param bookDate
     * @param happenDate
     * @param branch
     * @param baseSubjectNature
     * @param taskPri
     * @param expBalanceDirect
     * @return
     */
    private BigDecimal getDealSubjectBalance(String booksetId, String bokDetailId, Date bookDate, Date happenDate,
                                             Long branch, String baseSubjectNature,Long taskPri, String expBalanceDirect) {
        SubjectBalanceSearchDto searchDto = new SubjectBalanceSearchDto();
        searchDto.setBooksetId(booksetId);// 账套代码
        searchDto.setBookDate(bookDate);// 入账日
        searchDto.setHappenDate(happenDate);// 发生日
        searchDto.setBokDetailId(bokDetailId);// 账务明细代码
        searchDto.setBaseSubjectNatureList(Arrays.asList(baseSubjectNature));// 基础科目性质
        searchDto.setBranch(branch);//分支序号
        if(taskPri != null) {
            searchDto.setTaskPri(taskPri);//任务优先级
        }
        List<SubjectBalance> subjectLatestBalanceList = accountingCommonService.getSubjectBalanceExpectDirection(searchDto, expBalanceDirect);
        //定义变量.当日计算成本科目数量
        if(!StringUtil.isEmptyList(subjectLatestBalanceList)){
            return subjectLatestBalanceList.get(0).getBalance();
        }
        return BigDecimal.ZERO;
    }


    /**
     * 科目余额汇总查询 （相当于根据科目类别对所有的科目余额进行余额和数量的总处理）
     * 从账务共通拷贝过来，去除不按分支序号查询
     * @return resultMap 余额Map(币种，科目余额)
     * @param searchDto 科目余额查询参数Dto
     * @param expectedBalFalg --期待余额方向
     * @param bccySum 是否原币汇总 Y:是  N:否
     * @see
     * @since 1.0
     */
    public Map<String, BigDecimal> getSubjectBalanceCollection(SubjectBalanceSearchDto searchDto,
                                                               String expectedBalFalg,String bccySum) {
        // 查询账务明细当前表
        searchDto.setTaskPri(null);// 批处理优先级设置为null
        List<SubjectBalance> list = accountingCommonService.getSubjectLatestBalance(searchDto);

        // 定义 出参.余额Map
        Map<String, BigDecimal> resultMap = new HashMap<>();

        //如果传值为NULL，默认汇总币种为原币
        bccySum = bccySum == null ? DictCodeList.是否_是.getValue() : bccySum;
        // list1中不存在同一key后面不同值的情况，可直接放入map中
        for (SubjectBalance item : list) {
            //定义 变量.汇总币种 =科目余额DTO.原币
            String sumCcy =item.getOCcy();
            //IF '否“ == 参数.是否原币汇总
            if(DictCodeList.是否_否.getValue().equals(bccySum)){
                //变量.汇总币种 = 科目余额DTO.本位币
                sumCcy = item.getbCcy();
            }

            // 定义 变量.科目余额 = 出参.余额Map.get(币种) != null
            BigDecimal subjectBalance = (resultMap.get(sumCcy) != null) ? resultMap.get(
                    sumCcy) : null;
            // 定义 变量.方向是否一致flag，true一致，false不一致
            boolean balFlag = item.getBalFlagHappenDate().equals(expectedBalFalg);
            // if（变量.科目余额 != null)
            if (subjectBalance != null) {
                if(DictCodeList.是否_否.getValue().equals(bccySum)){
                    // 科目余额 = 科目余额 + (余额方向 == 参数.余额方向 ? 科目余额 ：本币科目余额 * -1）
                    subjectBalance = balFlag ? ArithmeticUtil.doAdd(subjectBalance,
                            item.getbAmt()) : ArithmeticUtil.doAdd(subjectBalance,
                            ArithmeticUtil.multiply(item.getbAmt(), new BigDecimal("-1")));
                }else{
                    // 科目余额 = 科目余额 + (余额方向 == 参数.余额方向 ? 科目余额 ： 科目余额 * -1）
                    subjectBalance = balFlag ? ArithmeticUtil.doAdd(subjectBalance,
                            item.getBalance()) : ArithmeticUtil.doAdd(subjectBalance,
                            ArithmeticUtil.multiply(item.getBalance(), new BigDecimal("-1")));

                }
            }
            else {
                if(DictCodeList.是否_否.getValue().equals(bccySum)){
                    // 科目余额 = 科目余额 + (余额方向 == 参数.余额方向 ? 科目余额 ： 本币科目余额 * -1）
                    subjectBalance = balFlag ? item.getbAmt() : ArithmeticUtil.multiply(
                            item.getbAmt(), new BigDecimal("-1"));
                }
                else{
                    // 科目余额 = 科目余额 + (余额方向 == 参数.余额方向 ? 科目余额 ： 科目余额 * -1）
                    subjectBalance = balFlag ? item.getBalance() : ArithmeticUtil.multiply(
                            item.getBalance(), new BigDecimal("-1"));
                }
            }

            // 更新 resultMap
            resultMap.put(sumCcy, subjectBalance);
        }
        // 返回 出参.余额Map
        return resultMap;
    }

}
