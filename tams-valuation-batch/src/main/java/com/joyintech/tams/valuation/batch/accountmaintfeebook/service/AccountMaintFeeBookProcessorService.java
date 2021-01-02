package com.joyintech.tams.valuation.batch.accountmaintfeebook.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.joyintech.calculation.config.NumberEnum;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.BokBooksetExt;
import com.joyintech.tams.common.base.entity.BokStatDetPosiiton;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListTableId;
import com.joyintech.tams.valuation.batch.accountmaintfeebook.dto.AccountMaintFeeBookDto;
import com.joyintech.tams.valuation.batch.accountmaintfeebook.dto.AccountMaintFeeBookResult;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalance;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalanceSearchDto;
import com.joyintech.tams.valuation.batch.common.accounting.service.AccountingCommonService;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookAccountGenLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.AccountDriver;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.TreeNode;
import com.joyintech.tams.valuation.batch.common.drawbaseinfoselectcom.dto.DrawBaseInfoSelectComDTO;
import com.joyintech.tams.valuation.batch.common.drawbaseinfoselectcom.dto.DrawBaseInfoSelectComResult;
import com.joyintech.tams.valuation.batch.common.finproduct.service.FinProductCommonService;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TradeInfoDto;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.DataIndexSequenceType;
import com.joyintech.tams.valuation.common.constants.DictCodeBaseSubjectNatrue;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.constants.ValDictCodeList;
import com.joyintech.tams.valuation.common.utils.DBUtil;

/**
 * 账户维护费每日计提 processor 的Service
 * @author hpp
 *
 */
@Service
public class AccountMaintFeeBookProcessorService {

    /**
     * 标的类共通
     */
    @Autowired
    private FinProductCommonService finProductCommonService;

    /**
     * 批处理账务相关公用方法
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
     * DBUtil
     */
    @Autowired
    private DBUtil dBUtil;
    
    /**
     * process 数据处理
     * 
     * @param params 参数
     * @return BookGenerateResult
     */
    @SuppressWarnings("unchecked")
    public BookGenerateResult execute(Map<String, Object> params) {
    	// 获取reader返回值
        AccountMaintFeeBookResult acctEntity = (AccountMaintFeeBookResult)params.get(GlobalConstant.READER_RESULT);

        // 若reader传递的参数为空，不进行后续处理
        if (acctEntity == null) {
            return null;
        }

        // 获取从 task 拿到的参数
        SysBatchExecInfo batchExecInfo = (SysBatchExecInfo)params.get("BATCHINFO"); // 待处理任务信息

        // 定义返回值
        BookGenerateResult returnResult = new BookGenerateResult();

        // 获取从reader中拿到的值
        String booksetIdNow = acctEntity.getBooksetId();// 账套代码
        Date happenDateNow = acctEntity.getHappenDate();// 发生日期
        Date bookDateNow = acctEntity.getBookDate();// 入账日期
        TreeNode bookSetTreeNodeNow = acctEntity.getBookSetTreeNode();// 会计场景

        //1、获取计提参数
        // 账套核算参数查询
        List<String> paramCodeList = new ArrayList<String>();
        // TOCAL_ACCR_TYPE 账套配置表参数项_预提费用计提方式
        paramCodeList.add(ValDictCodeList.账套配置表参数项_预提费用计提方式.getValue());
        // 调用账务类共通（DTO.账套代码, 参数项：如下）
        List<BokBooksetExt> listBooksetExt = accountingCommonService.getValBokBooksetExt(
                booksetIdNow, paramCodeList);
        //定义 变量.计提方式 = 返回值
        String calType = "";
        if (!CollectionUtils.isEmpty(listBooksetExt)) {
            // 获取第一条即可
            calType = listBooksetExt.get(0).getParamValue();
            if(!StringUtil.isNotEmpty(calType)){
            	calType = DictCodeList.计提方式_每日新增.getValue();
            }
        }else{
        	calType = DictCodeList.计提方式_每日新增.getValue();
        }

        List<AccountMaintFeeBookDto> listAccountMaintFeeBookDto = acctEntity.getListAccountMaintFeeBookReaderDto();
        if(listAccountMaintFeeBookDto == null || listAccountMaintFeeBookDto.size() == 0){
            return  returnResult;
        }

        for (AccountMaintFeeBookDto dto : listAccountMaintFeeBookDto) {
            //2、调用系统共通方法
            // 2.1 交易类标的信息查询
            TradeInfoDto dealInfoForTarget = this.getTargetInfo(dto.getBusiId(),DictCodeListExt1.金融产品类型_预提费用.getValue(),0L);
            // 上述返回结果为空
            if (dealInfoForTarget == null || dealInfoForTarget.getVdate().after(acctEntity.getHappenDate())
                    || dealInfoForTarget.getMdate().before(acctEntity.getHappenDate())) {
                continue;
            }

            //2.2 变量.预提费用余额
            //调用共通设计->【详细设计-账务类共通方法设计.xlsx】->科目余额查询-期待方向SHEET
            SubjectBalance subjectBalance = this.getSubjectBalance(booksetIdNow,
                    acctEntity.getBokDetailId(), happenDateNow, bookDateNow,
                    DictCodeList.余额方向_贷方.getValue(), DictCodeBaseSubjectNatrue.预提费用.getValue(), null, dto.getTaskPri());
            //定义 变量.预提费用本币余额 = 预提费用DTO.本币余额
            BigDecimal ytfyBalance_BCcy = StringUtil.isNotEmpty(subjectBalance.getbAmt()) ? subjectBalance.getbAmt() : BigDecimal.ZERO;
            //定义 变量.预提费用余额 = 预提费用DTO.科目余额
            BigDecimal ytfyBalance = StringUtil.isNotEmpty(subjectBalance.getBalance()) ? subjectBalance.getBalance() : BigDecimal.ZERO;
            //名义本金
            BigDecimal prin = dealInfoForTarget.getPrin();


            //2.3 调用共通
            // 变量.昨日统计分析账务明细汇总信息 = 调用共通方法[账务类共通方法设计 - 统计分析账务明细汇总查询]
            BokStatDetPosiiton bokSummaryTableYestdy = accountingCommonService.getCountAnalysis(
	            DateUtil.addDay(acctEntity.getHappenDate(), -1),
	            DateUtil.addDay(acctEntity.getHappenDate(), -1), acctEntity.getBookDate(),
	            acctEntity.getBooksetId(), acctEntity.getBokDetailId(), null, null);
            if(bokSummaryTableYestdy == null){
                bokSummaryTableYestdy = new BokStatDetPosiiton();
            }

            //3、计算当日预提费用
            //变量.当日预提费用
            BigDecimal ytFeeAmt = BigDecimal.ZERO;
            //变量.当日预提费用_本币
            BigDecimal ytFeeAmt_BCcy = BigDecimal.ZERO;
            //DTO.入账日-标的信息.起息日）
            BigDecimal A =new BigDecimal(DateUtil.getBetweenDayNumber(dealInfoForTarget.getVdate(), bookDateNow) + 1);
            //(标的信息.到期日-标的信息.起息日+1）
            BigDecimal B = new BigDecimal(DateUtil.getBetweenDayNumber(dealInfoForTarget.getVdate(), dealInfoForTarget.getMdate()));
            // if 变量.计提方式 = 【数据字典】.计提方式."区间倒减"
            if (DictCodeList.计提方式_区间倒减.getValue().equals(calType)) {
            	//变量.累计预提额 = 标的信息.名义本金*（DTO.入账日-标的信息.起息日+1）/(标的信息.到期日-标的信息.起息日+1） 调用金额共通方法设置2位有效小数位
                BigDecimal addMoney = ArithmeticUtil.divide(ArithmeticUtil.multiply(prin, A), B, NumberEnum.MONEY.getCode());
                //变量.当日预提费用 = 变量.累计预提额 - 变量.预提费用累计
                ytFeeAmt = ArithmeticUtil.subtract(addMoney, ytfyBalance);
                //变量.当日预提费用_本币 = 变量.当日预提费用
                ytFeeAmt_BCcy = ytFeeAmt;
            } else {
                //if(DTO.入账日 = 标的信息.到期日)
                if(DateUtil.compareDate(DateUtil.format(bookDateNow, "yyyy-MM-dd"), 
                		DateUtil.format(DateUtil.addDay(dealInfoForTarget.getMdate(), -1),"yyyy-MM-dd")) == 0){
                    //变量.当日待摊费用 = 变量.待摊费用余额
                	//变量.当日预提费用 = 标的信息.名义本金 - 变量.预提费用余额
                	ytFeeAmt = ArithmeticUtil.subtract(prin, ytfyBalance);;
                    //变量.当日预提费用_本币 = 变量.当日预提费用
                	ytFeeAmt_BCcy = ytFeeAmt;
                } else {
                	//变量.当日预提费用 = 标的信息.名义本金/(标的信息.到期日-标的信息.起息日+1） 调用金额共通方法设置2位有效小数位
                	ytFeeAmt = ArithmeticUtil.divide(prin, B, NumberEnum.MONEY.getCode());
                	//变量.当日预提费用_本币 = 变量.当日预提费用 调用金额共通方法设置2位有效小数位
                	ytFeeAmt_BCcy = ytFeeAmt;
                }
            }

            //4、生成记账凭证
            AccountDriver ad = new AccountDriver();
            // 初始化定义 - 存放该笔记账的场景要素 map定义
            Map<String, Object> accountSceneMap = new HashMap<>();
            // 初始化定义 - 存放该笔记账的相关金额要素 map定义
            Map<String, BigDecimal> accountAmountMap = new HashMap<>();
            // 初始化定义 - 存放交易金额对应的币种 map定义
            Map<String, Object> accountCurrencyMap = new HashMap<>();
            
            ad.setTreeNode(bookSetTreeNodeNow);// 塞入记账场景树节点 = 会计场景
            //tableId = 变量.待摊费用计提.场景代码
            ad.setTableId(DictCodeListTableId.会计场景代码_费用预提.getValue());
            
            //accountAmountMap设置如下：
            // 调用共通场景元素赋值-计提类场景元素
            DrawBaseInfoSelectComResult dbiscResult = new DrawBaseInfoSelectComResult();
            BeanUtil.copyPropertiesIgnoreNull(dbiscResult, acctEntity);
            DrawBaseInfoSelectComDTO dbiscDto = new DrawBaseInfoSelectComDTO();
            BeanUtil.copyPropertiesIgnoreNull(dbiscDto, dto);
            dbiscDto.setoCcy("CNY");
            dbiscDto.setStandardCcy("CNY");//本位币
            accountSceneMap = bookCommonLogic.accrualElementAssign(dbiscResult, dbiscDto);
            //登记托管机构 DTO.登记托管机构
            accountSceneMap.put(BookConstant.REGIST_ORG, dealInfoForTarget.getRegistOrg());
            //TYPE_1 <- 标的信息.TYPE_1
            accountSceneMap.put(BookConstant.TYPE_1, dealInfoForTarget.getType1());
            //备注  DictCodeList.from(DictCode.会计场景代码, tableId).getCodeName()
            accountSceneMap.put(BookConstant.BOOK_REMARK, "费用预提");
            
            //待摊费用  <- 变量.当日预提费用
            accountAmountMap.put(BookConstant.YTFEEAMT, ytFeeAmt);
            //待摊费用_本位币  <- 变量.当日预提费用_本位币
            accountAmountMap.put(BookConstant.YTFEEAMT, ytFeeAmt_BCcy);

            //accountCurrencyMap设置如下：
            // 因为与记账金额元素map的key及数量一致，故采用循环
            for (String keyNow : accountAmountMap.keySet()) {
                accountCurrencyMap.put(keyNow, dbiscDto.getoCcy());// 币种 - 交易明细.交易币种
            }
            // 本位币设置
            accountCurrencyMap.put(BookConstant.B_CCY, dbiscDto.getStandardCcy());
            
            ad.setAccountSceneMap(accountSceneMap);
            ad.setAccountAmountMap(accountAmountMap);
            ad.setAccountCurrencyMap(accountCurrencyMap);
            // 记账凭证生成 : 调用记账引擎公共记账方法，生成记账凭证
            BookGenerateResult bookGenerateResult = bookLogic.genAccount(ad, batchExecInfo);
            returnResult.getValBokBookdetailBatch().addAll(bookGenerateResult.getValBokBookdetailBatch());

            //5. 统计分析账务明细汇总信息赋值
            // 定义当日统计分析账务明细汇总信息
            BokStatDetPosiiton bokSummaryTableNew = new BokStatDetPosiiton();
            //变量.当日统计分析账务明细汇总信息.当前持仓 =  标的信息.名义本金 - 变量.预提费用余额 - 变量.当日预提费用
            bokSummaryTableNew.setPosAmt(ArithmeticUtil.subtract(prin, ytFeeAmt.add(ytfyBalance)));
            //变量.当日统计分析账务明细汇总信息.当日发生应计利息 = 变量.当日预提费用
            bokSummaryTableNew.setTdyIntincexpAdd(ytFeeAmt);
            //变量.当日统计分析账务明细汇总信息.当日累计应计利息余额 = 变量.昨日统计分析账务明细汇总信息.当日累计应计利息余额 + 变量.当日预提费用
            BigDecimal tdyintincexpNow = ArithmeticUtil.doAdd(bokSummaryTableYestdy.getTdyIntincexp(), ytFeeAmt);
            bokSummaryTableNew.setTdyIntincexp(tdyintincexpNow);
            //变量.当日统计分析账务明细汇总信息.当日份额 =  标的信息.名义本金 - 变量.预提费用余额 - 变量.当日预提费用
            bokSummaryTableNew.setTdyPosition(ArithmeticUtil.subtract(prin, ytFeeAmt.add(ytfyBalance)));
            //变量.当日统计分析账务明细汇总信息.当日成本 = 标的信息.名义本金 - 变量.预提费用余额 - 变量.当日预提费用
            bokSummaryTableNew.setTdyCostAmt(ArithmeticUtil.subtract(prin, ytFeeAmt.add(ytfyBalance)));
            //变量.当日统计分析账务明细汇总信息.起息日 = 变量.标的信息.起息日
            bokSummaryTableNew.setVdate(dealInfoForTarget.getVdate());
            //变量.当日统计分析账务明细汇总信息.到期日 = 变量.标的信息.实际到期日
            bokSummaryTableNew.setMdate(dealInfoForTarget.getMdate());
            //变量.当日统计分析账务明细汇总信息.金融产品类型 = 计提明细.业务明细类型
            bokSummaryTableNew.setFinprodType(DictCodeListExt1.金融产品类型_预提费用.getValue());
            //变量.当日统计分析账务明细汇总信息.剩余期限 = 0
            bokSummaryTableNew.setEndDays1(BigDecimal.ZERO);
            //变量.当日统计分析账务明细汇总信息.剩余存续期 = 0
            bokSummaryTableNew.setEndDays2(BigDecimal.ZERO);
            //变量.当日统计分析账务明细汇总信息.业务明细代码 = 计提明细.业务明细代码
            bokSummaryTableNew.setBusiId(dto.getBusiId());
            //变量.当日统计分析账务明细汇总信息.币种= 计提类账务处理结果.原币币种
            bokSummaryTableNew.setCcy(dbiscDto.getoCcy());
            //变量.当日统计分析账务明细汇总信息.当日发生应计利息_本位币 = 变量.当日预提费用_本币
            bokSummaryTableNew.setTdyIntincexpAddB(subjectBalance.getbAmt());
            //变量.当日统计分析账务明细汇总信息.当日累计应计利息余额_本位币 =变量.昨日统计分析账务明细汇总信息.当日累计应计利息余额_本位币 + 变量.当日预提费_本币
            bokSummaryTableNew.setTdyIntincexpB(ArithmeticUtil.doAdd(bokSummaryTableYestdy.getTdyIntincexpB(),ytFeeAmt_BCcy));
            //变量.当日统计分析账务明细汇总信息.当日成本_本位币= 变量.预提费用本币余额 - 变量.当日预提费用_本币
            bokSummaryTableNew.setTdyCostAmtB(ArithmeticUtil.subtract(ytfyBalance_BCcy, ytFeeAmt_BCcy));
            //变量.当日统计分析账务明细汇总信息.本位币= 计提类账务处理结果.本位币币种
            bokSummaryTableNew.setbCcy(dbiscDto.getStandardCcy());
            
            // 账套代码
            bokSummaryTableNew.setBooksetId(acctEntity.getBooksetId());
            // 发生日期
            bokSummaryTableNew.setHappenDate(acctEntity.getHappenDate());
            // 入账日期
            bokSummaryTableNew.setBookDate(acctEntity.getBookDate());
            // 账务明细代码
            bokSummaryTableNew.setBokDetailId(acctEntity.getBokDetailId());
            // 账套日期
            bokSummaryTableNew.setBooksetDate(dto.getBooksetDate());
            // 处理序列
            bokSummaryTableNew.setBookSummaryOrder(
                new BigDecimal(dBUtil.getSequenceIndex(DataIndexSequenceType.账务汇总表_处理序列)));
            
            returnResult.getValBokStatDetPosiitonBatch().add(bokSummaryTableNew);
        }

        return returnResult;
    }

    /**
     * 调用共通方法：[标的类共通方法-查询交易信息]，获取标的交易信息
     * 
     * @param finprodId 金融产品代码【必输项】
     * @param finprodType 金融产品类型【必输项】
     * @param branch 分支序号
     * @return ValFinProductDeal 标的查询
     * @see
     * @since 1.0
     */
    private TradeInfoDto getTargetInfo(String finprodId, String finprodType, Long branch) {
        // 定义返回值 - 标的交易信息
        TradeInfoDto tradeInfoDtoForReturn = null;

        // 调用共通方法：[标的类共通方法 - 交易信息查询]，获取标的交易信息
        List<TradeInfoDto> tradeInfoDtoList = finProductCommonService.getTradeInfoDtoList(finprodId, finprodType, branch);

        // 当查询结果不为空
        if (tradeInfoDtoList != null && tradeInfoDtoList.size() > 0) {
            // 默认取第一条标的信息
            tradeInfoDtoForReturn = tradeInfoDtoList.get(0);
        }

        return tradeInfoDtoForReturn;
    }
    
    /**
     * 调用公用方法：【科目余额查询 - 期待方向】，获取科目余额信息中的科目余额数据
     * 
     * @param booksetIdNow 账套代码
     * @param bokDetailId 账务明细代码
     * @param happenDateNow 发生日
     * @param bookDateNow 入账日
     * @param expBalanceDirect 期待余额方向
     * @param baseSubjectNature 基础科目性质
     * @param branch 分支号
     * @param taskPri 任务优先级
     * @return SubjectBalance 科目余额
     * @see
     * @since 1.0
     */
    private SubjectBalance getSubjectBalance(String booksetId, String bokDetailId,
                                             Date happenDate, Date bookDate,
                                             String expBalanceDirect, String baseSubjectNature,
                                             Long branch, Long taskPri) {
        // 定义返回的科目余额
        SubjectBalance balance = new SubjectBalance();

        // 塞入要查询的基础科目性质
        List<String> baseSubjectNatureList = null;
        if (baseSubjectNature != null && baseSubjectNature.length() > 0) {
            baseSubjectNatureList = new ArrayList<String>();
            baseSubjectNatureList.add(baseSubjectNature);
        }
        
        //设值科目余额查询参数Dto
        SubjectBalanceSearchDto searchDto = new SubjectBalanceSearchDto();
        searchDto.setBooksetId(booksetId);
        searchDto.setBokDetailId(bokDetailId);
        searchDto.setHappenDate(happenDate);
        searchDto.setBookDate(bookDate);
        searchDto.setTaskPri(taskPri);
        searchDto.setBaseSubjectNatureList(baseSubjectNatureList);
        // 调用公用方法：【科目余额查询-期待方向 】,获取科目余额信息
        List<SubjectBalance> subjectBalanceList = accountingCommonService.getSubjectBalanceExpectDirection(
            searchDto, expBalanceDirect);

        // 获取科目余额
        if (subjectBalanceList != null && subjectBalanceList.size() > 0) {
            balance = subjectBalanceList.get(0);
        }

        return balance;
    }
    
}
