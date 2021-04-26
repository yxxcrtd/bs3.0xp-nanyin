package com.joyintech.tams.valuation.batch.feeForwardDrawTask.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.batch.core.dto.SysBatchTaskDto;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResultWithBokStatFeePosition;
import com.joyintech.tams.valuation.batch.feeForwardDrawTask.dto.FeeForwadDrawConfigDto;
import com.joyintech.tams.valuation.batch.feeForwardDrawTask.dto.FeeForwadDrawReaderResult;
import com.joyintech.tams.valuation.common.constants.*;
import com.joyintech.tams.valuation.common.interf.base.util.MD5Util;
import com.joyintech.tams.valuation.common.utils.ValDateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.joyintech.calculation.config.NumberEnum;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.BeanUtil;
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
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.TreeNode;
import com.joyintech.tams.valuation.batch.common.drawbaseinfoselectcom.dto.DrawBaseInfoSelectComDTO;
import com.joyintech.tams.valuation.batch.common.finproduct.service.FinProductCommonService;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TradeInfoDto;
import com.joyintech.tams.valuation.common.utils.DBUtil;
import org.springframework.util.StringUtils;

/**
 * 账户维护费每日计提 processor 的Service
 * @author hpp
 *
 */
@Service
public class FeeForwadDrawProcessorService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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
        FeeForwadDrawReaderResult acctEntity = (FeeForwadDrawReaderResult)params.get(GlobalConstant.READER_RESULT);

        // 若reader传递的参数为空，不进行后续处理
        if (acctEntity == null) {
            return null;
        }


        SysBatchTaskDto taskInfo = (SysBatchTaskDto)params.get("BATCHTASK");

        // 定义返回值(复用产品端固定费用计提得返回结果DTO）
        BookGenerateResultWithBokStatFeePosition returnResult = new BookGenerateResultWithBokStatFeePosition();

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
        List<BokBooksetExt> listBooksetExt = accountingCommonService.getValBokBooksetExt(booksetIdNow, paramCodeList);
        //定义 变量.计提方式 = 返回值
        String calType = "";
        if (!CollectionUtils.isEmpty(listBooksetExt)) {
            // 获取第一条即可
            calType = listBooksetExt.get(0).getParamValue();
            if(StringUtils.isEmpty(calType)){
            	calType = DictCodeList.计提方式_每日新增.getValue();
            }
        }else{
        	calType = DictCodeList.计提方式_每日新增.getValue();
        }
        //待处理数据判空
        List<FeeForwadDrawConfigDto> listAccountMaintFeeBookDto = acctEntity.getListAccountMaintFeeBookReaderDto();

        if(listAccountMaintFeeBookDto == null || listAccountMaintFeeBookDto.size() == 0){
            return  returnResult;
        }
        //循环处理每条数据
        for (FeeForwadDrawConfigDto dto : listAccountMaintFeeBookDto) {

            Interval interval = intervalCalc(dto,happenDateNow);

            if(interval == null){
                continue;
            }

//            定义：		变量.基础科目性质_应付费用
//            if	计提明细.登记托管机构 = 数据字典.登记托管机构.登记托管机构_中央国债登记结算有限责任公司
//            变量.基础科目性质_应付费用 = 									应付中债登账户维护费
//else if			计提明细.登记托管机构 = 数据字典.登记托管机构.登记托管机构_上海清算所
//            变量.基础科目性质_应付费用 = 									应付上清所账户维护费
//else if			计提明细.登记托管机构 = 数据字典.登记托管机构.登记托管机构_中国证券登记结算有限责任公司
//            end if
            String baseSubjectNature = null;
            if(DictCodeListExt1.一次性费用类型_账户维护费.getValue().equals(dto.getFeeType())){
                if(DictCodeList.登记托管机构_中债_中央国债登记结算有限责任公司.getValue().equals(dto.getRegistOrg())) {
                    baseSubjectNature = DictCodeBaseSubjectNatrue.应付中债登账户维护费.getValue();
                }else if(DictCodeList.登记托管机构_上海清算所.getValue().equals(dto.getRegistOrg())){
                    baseSubjectNature = DictCodeBaseSubjectNatrue.应付上清所账户维护费.getValue();
                }else if(DictCodeList.登记托管机构_中国证券登记结算有限责任公司.getValue().equals(dto.getRegistOrg())){
                    logger.error("登记托管机构错误，中证登需求确认没有账户维护费：账套代码-"+dto.getBooksetId()+",费用类型-"+dto.getFeeType()+"，计提开始日："+DateUtil.format(dto.getPositionStartDate())+"，计提结束日："+DateUtil.format(dto.getPositionEndDate()));
                }else{
                    logger.error("登记托管机构不可为空，请确认预提费用配置是否正确：账套代码-"+dto.getBooksetId()+",费用类型-"+dto.getFeeType()+"，计提开始日："+DateUtil.format(dto.getPositionStartDate())+"，计提结束日："+DateUtil.format(dto.getPositionEndDate()));
                }
            }else{
                logger.error("系统当前支持账户维护费预提，其他费用类型请等待支持.");
                continue;
            }
            //调用共通设计->【详细设计-账务类共通方法设计.xlsx】->科目余额查询-期待方向SHEET
            SubjectBalance subjectBalance = this.getSubjectBalance(booksetIdNow,null, happenDateNow, bookDateNow,
                    DictCodeList.余额方向_贷方.getValue(),baseSubjectNature, null, taskInfo.getTaskPri());
            //定义 变量.应付费用本币余额 = 预提费用DTO.本币余额
            BigDecimal balance_bCcy = StringUtil.isNotEmpty(subjectBalance.getbAmt()) ? subjectBalance.getbAmt() : BigDecimal.ZERO;
            //定义 变量.应付费用余额 = 预提费用DTO.科目余额
            BigDecimal balance = StringUtil.isNotEmpty(subjectBalance.getBalance()) ? subjectBalance.getBalance() : BigDecimal.ZERO;
            //变量.当日预提费用
            BigDecimal ytFeeAmt = BigDecimal.ZERO;
            //变量.当日预提费用_本币
            BigDecimal ytFeeAmt_bCcy = BigDecimal.ZERO;
            if(DictCodeList.计提方式_区间倒减.getValue().equals(calType)){
//                变量.累计预提额 = 变量.计提区间.区间计提金额 *（批处理任务执行信息.入账日 - 变量.计提区间.区间开始日 +1）/ (变量.计提区间.区间结束日 - 变量.计提区间.区间开始日 + 1）
                BigDecimal intervalTotleYtFeeAmt = interval.feeAmt.multiply(new BigDecimal(ValDateUtil.getBetweenDayNumber(interval.startDate, bookDateNow) + 1)).divide(new BigDecimal(ValDateUtil.getBetweenDayNumber(interval.startDate, interval.endDate) + 1), NumberEnum.MONEY.getCode(), BigDecimal.ROUND_HALF_UP);
//                变量.当日预提费用 = 变量.累计预提额 - 变量.预提费用累计
                ytFeeAmt = intervalTotleYtFeeAmt.subtract(balance);
            }else{
                //如果计算日为区间截止日,需要做倒减处理
                if(ValDateUtil.isSameDay(interval.calcDate, interval.endDate)){
                    ytFeeAmt = interval.feeAmt.subtract(balance);
                }else{
                    ytFeeAmt = interval.feeAmt.divide(new BigDecimal(ValDateUtil.getBetweenDayNumber(interval.startDate,interval.endDate)+1),NumberEnum.MONEY.getCode(), BigDecimal.ROUND_HALF_UP);
                }
            }
//                变量.当日预提费用_本币 = 变量.当日预提费用
            ytFeeAmt_bCcy = ytFeeAmt;
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
            ad.setTableId(DictCodeListTableId.会计场景代码_交易费用计提.getValue());

            //accountAmountMap设置如下：调用共通场景元素赋值-计提类场景元素
            DrawBaseInfoSelectComDTO dbiscDto = new DrawBaseInfoSelectComDTO();
            BeanUtil.copyPropertiesIgnoreNull(dbiscDto, dto);
            dbiscDto.setoCcy(DictCodeList.币种_人民币_CNY.getValue());
            dbiscDto.setStandardCcy(DictCodeList.币种_人民币_CNY.getValue());//本位币
            dbiscDto.setBusiId(dto.getFinprodiId());
            dbiscDto.setBooksetDate(bookDateNow);
            dbiscDto.setTaskId(taskInfo.getTaskId());


            accountSceneMap = bookCommonLogic.accrualElementAssign(acctEntity, dbiscDto);

            //登记托管机构 DTO.登记托管机构
            accountSceneMap.put(BookConstant.TYPE_1, dto.getFeeType());
            accountSceneMap.put(BookConstant.REGIST_ORG, dto.getRegistOrg());
            //备注  DictCodeList.from(DictCode.会计场景代码, tableId).getCodeName()
            accountSceneMap.put(BookConstant.BOOK_REMARK, DictCodeListTableId.会计场景代码_交易费用计提.getCodeName());
            //应付费用  <- 变量.当日预提费用
            accountAmountMap.put(BookConstant.INTAMT, ytFeeAmt);
            //add jinmaodong 正常部需要生成，但是此处为了避免一个产品配置多个登记托管机构的预提费用计提出现的凭证被合并问题，故手动设置BOOK_ID
            accountSceneMap.put(BookConstant.BOOK_ID, generateBookId(
                    dto.getBooksetId(),DictCodeListTableId.会计场景代码_交易费用计提.getValue(),happenDateNow,dto.getBokDetailId(),
null,dto.getRegistOrg()
            ));

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
            BookGenerateResult bookGenerateResult = bookLogic.genAccount(ad, new SysBatchExecInfo());
            returnResult.getValBokBookdetailBatch().addAll(bookGenerateResult.getValBokBookdetailBatch());

            //3.3 统计分析费用汇总信息赋值
            //定义变量.当日统计分析费用明细汇总信息
            BokStatFeePosiiton bokStatFeePosiiton = new BokStatFeePosiiton();
            BigDecimal feeRate = BigDecimal.ZERO;
            //变量.当日统计分析费用明细汇总信息.当日费率 = 变量.费用利率
            bokStatFeePosiiton.setTdyFeeRate(feeRate);
            //变量.当日统计分析费用明细汇总信息.当日份额 = 变量.计费基数变更列表.变更金额 ※变量.计费基数变更列表取变更日最大的记录
            bokStatFeePosiiton.setTdyPosition(interval.feeAmt);
            //变量.当日统计分析费用明细汇总信息.费用类型 = DTO.现金流二级类型
            bokStatFeePosiiton.setFeeType(DictCodeListExt1.一次性费用类型_账户维护费.getValue());
            //变量.当日统计分析费用明细汇总信息.当日新增费用 = 变量.费用计提bookDetailCompareIntoTask
            bokStatFeePosiiton.setTdyIntincexpAdd(ytFeeAmt);
            //变量.当日统计分析费用明细汇总信息.金融产品类型 = DTO.金融产品类型
            bokStatFeePosiiton.setFinprodType(dto.getFinprodType());
            //变量.当日统计分析费用明细汇总信息.金融产品代码 = DTO.金融产品代码
            bokStatFeePosiiton.setFinprodId(dto.getFinprodiId());
            //变量.当日统计分析费用明细汇总信息.分支序号 = DTO.分支序号
            bokStatFeePosiiton.setBranch(dto.getBranch());
            //变量.当日统计分析费用明细汇总信息.分层代码 = DTO.分层代码
            bokStatFeePosiiton.setLayeringId(dto.getLayeringId());
            //变量.当日统计分析费用明细汇总信息.费用类型 = DTO.现金流类型

            //账套代码
            bokStatFeePosiiton.setBooksetId(acctEntity.getBooksetId());
            //发生日期
            bokStatFeePosiiton.setHappenDate(acctEntity.getHappenDate());
            //入账日期
            bokStatFeePosiiton.setBookDate(acctEntity.getBookDate());
            //账套日期
            bokStatFeePosiiton.setBooksetDate(acctEntity.getBookDate());

            returnResult.getBokStatFeePosiitonList().add(bokStatFeePosiiton);
        }

        return returnResult;
    }

    /***
     * 费用预提凭证的BOOKID生成
     * @param booksetId
     *  账套代码
     * @param tableId
     *  事件编号
     * @param happenDate
     *  发生日
     * @param bookDetailId
     *  账务明细代码
     * @param offsetFlag
     *  冲销标志
     * @param registOrg
     *  登记托管机构
     * @return
     */
    private String generateBookId(String booksetId,String tableId,Date happenDate,String bookDetailId,String offsetFlag,String registOrg) {

        // 费用预提：场景代码 + 发生日 + 账务明细代码 + 冲销标识 + 登记托管机构
        String bookId = booksetId + tableId + happenDate + bookDetailId + offsetFlag + registOrg;
        bookId = MD5Util.string2MD5(bookId);
        return bookId;
    }


//    public static void main(String args[]){
//        Date calcDate = DateUtil.parse("2021-04-01");
//        //计提开始日
//        Date calcStartDate = DateUtil.parse("2021-02-02");
//        //计提结束日
//        Date calcEndDate = DateUtil.parse("2021-12-01");
//        Date startDate = null;
//        Date endDate = null;
//        String payCycle = DictCodeList.频率_季.getValue();
//        //计提结束日 = 计提日期  ——> 变量.计提区间.区间结束日 = 计提开始日
//        if(calcDate.equals(calcStartDate)){
//            startDate = calcStartDate;
//        }
//        //计提结束日 = 计提日期  ——> 变量.计提区间.区间结束日 = 计提开始日
//        if(calcDate.equals(calcEndDate)){
//            endDate = calcEndDate;
//        }
//        //计提开始日 > 计提日期 || 计提结束日 < 计提日期  ——>     变量.计提区间 = NULL
//        if(calcDate.before(calcStartDate)||calcDate.after(calcEndDate)){
//            System.out.println("                   ");
//        }else{
//            //频率为月
////            区间开始日 = 计提日期->所在月第一天
////            区间结束日 = 计提日期->所在月最后一天
//            if(payCycle.equals(DictCodeList.频率_月.getValue())) {
//                startDate = ValDateUtil.getFirstDay(calcDate);
//                endDate = ValDateUtil.getLastDay(calcDate);
//            }else if(payCycle.equals(DictCodeList.频率_季.getValue())){
//                startDate = ValDateUtil.getFirstDayOfQuarter(calcDate);
//                endDate = ValDateUtil.getLastDayOfQuarter(calcDate);
//            }else if(payCycle.equals(DictCodeList.频率_年.getValue())) {
//                startDate = ValDateUtil.getFirstDayOfYear(calcDate);
//                endDate = ValDateUtil.getLastDayOfYear(calcDate);
//            }else{
//                System.out.println("费用预提配置信息配置错误，频率应为：月、季、年 中得一个");
//            }
////            IF	区间开始日 < 计提开始日
////                    区间开始日 = 计提开始日
////            END IF
////            IF	区间结束日 > 计提结束日
////                    区间结束日 = 计提结束日
////            END IF
//            if(startDate.before(calcStartDate)){
//                startDate = calcStartDate;
//            }
//            if(endDate.after(calcEndDate)){
//                endDate = calcEndDate;
//            }
//        }
//        System.out.println(DateUtil.format(startDate));
//        System.out.println(DateUtil.format(endDate));
//        System.out.println(ValDateUtil.getBetweenDayNumber(startDate,endDate)+1);
//    }

    /**
     * 实际区间计算
     * @param dto 预提费用配置信息
     * @param calcDate 计算日
     */
    private Interval intervalCalc(TrdAccountmfConfig dto, Date calcDate){
        Interval interval = new Interval();
        interval.calcDate = calcDate;
        interval.feeAmt = dto.getFeeAmtTotal();
        Date startDate = null;
        Date endDate = null;
        //支付频率
        String payCycle = dto.getPayCycle();
        //计提开始日
        Date calcStartDate = dto.getPositionStartDate();
        //计提结束日
        Date calcEndDate = dto.getPositionEndDate();
        //计提结束日 = 计提日期  ——> 变量.计提区间.区间结束日 = 计提开始日
        if(calcDate.equals(calcStartDate)){
            startDate = calcStartDate;
        }
        //计提结束日 = 计提日期  ——> 变量.计提区间.区间结束日 = 计提开始日
        if(calcDate.equals(calcEndDate)){
            endDate = calcEndDate;
        }
        //计提开始日 > 计提日期 || 计提结束日 < 计提日期  ——>     变量.计提区间 = NULL
        if(calcDate.before(calcStartDate)||calcDate.after(calcEndDate)){
            return null;
        }else{
            //频率为月
//            区间开始日 = 计提日期->所在月第一天
//            区间结束日 = 计提日期->所在月最后一天
            if(payCycle.equals(DictCodeList.频率_月.getValue())) {
                startDate = ValDateUtil.getFirstDay(calcDate);
                endDate = ValDateUtil.getLastDay(calcDate);
            }else if(payCycle.equals(DictCodeList.频率_季.getValue())){
                startDate = ValDateUtil.getFirstDayOfQuarter(calcDate);
                endDate = ValDateUtil.getLastDayOfQuarter(calcDate);
            }else if(payCycle.equals(DictCodeList.频率_年.getValue())) {
                startDate = ValDateUtil.getFirstDayOfYear(calcDate);
                endDate = ValDateUtil.getLastDayOfYear(calcDate);
            }else{
                logger.error("费用预提配置信息配置错误，频率应为：月、季、年 中得一个");
            }
//            IF	区间开始日 < 计提开始日
//                    区间开始日 = 计提开始日
//            END IF
//            IF	区间结束日 > 计提结束日
//                    区间结束日 = 计提结束日
//            END IF
            if(startDate.before(calcStartDate)){
                startDate = calcStartDate;
            }
            if(endDate.after(calcEndDate)){
                endDate = calcEndDate;
            }
        }
        interval.startDate = startDate;
        interval.endDate = endDate;
        return interval;
    }

    /**
     * 费用预提实际区间
     */
    private class Interval{
        /**
         * 区间开始日
         */
        private Date startDate;
        /**
         * 区间结束日
         */
        private Date endDate;
        /**
         * 区间费用
         */
        private BigDecimal feeAmt;
        /**
         * 计算日期
         */
        private Date calcDate;


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
     * 查询科目余额
     * @param booksetId
     * @param bokDetailId
     * @param happenDate
     * @param bookDate
     * @param expBalanceDirect
     * @param baseSubjectNature
     * @param branch
     * @param taskPri
     * @param batchSeqno
     * @return
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
        List<SubjectBalance> subjectBalanceList = accountingCommonService.getSubjectBalanceExpectDirection(searchDto, expBalanceDirect);

        // 获取科目余额
        if (subjectBalanceList != null && subjectBalanceList.size() > 0) {
            balance = subjectBalanceList.get(0);
        }

        return balance;
    }

}
