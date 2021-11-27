package com.joyintech.tams.svreport.chb.transmonthreport.service;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.ChbTransMonthDetailMapper;
import com.joyintech.tams.common.base.entity.ChbTransMonth;
import com.joyintech.tams.common.base.entity.ChbTransMonthTemplate;
import com.joyintech.tams.common.svreport.chb.constant.ProdConstants;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbCcyConvertUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.chb.monthreport.dao.ChbMonthPrdPositionMapper;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthPrdPosition;
import com.joyintech.tams.svreport.chb.transmonthreport.dao.ChbTransMonthReportSubRegistBatchMapper;
import com.joyintech.tams.svreport.chb.transmonthreport.dto.ChbTransMonthReportConstants;
import com.joyintech.tams.svreport.chb.transmonthreport.dto.ChbTransMonthReportSubDetailDto;
import com.joyintech.tams.svreport.chb.transmonthreport.dto.ChbTransMonthReportSubDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 理财产品转型情况月度统计表生成产品端报表数据处理类
 * 
 * @author dubaojun
 ** @version 1.0 2020年6月8日
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ChbTransMonthReportProdBatchService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ChbMonthPrdPositionMapper chbMonthPrdPositionMapper;

    @Autowired
    private ChbTransMonthDetailMapper chbTransMonthDetailMapper;

    @Autowired
    private ChbTransMonthReportSubRegistBatchMapper chbTransMonthReportSubRegistBatchMapper;

    @Value("${application.reportSelectOrDeleteCount}")
    public int reportSelectOrDeleteCount;

    /**
     * 生成产品端报表明细数据
     * 
     * @param report 生成参数
     */
    public void genMonthPrd(final ChbTransMonthReportSubDto report) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");

        Date firstday = report.getStartdate();
        Date lastday = report.getEnddate();

        ChbTransMonth chbTransMonth = new ChbTransMonth();
        chbTransMonth.setTermid(sdf.format(firstday));

        // 报表ID
        chbTransMonth.setReportuuid(UUID.randomUUID().toString());

        // 报表类型
        chbTransMonth.setReporttype(ChbTransMonthReportConstants.REPORT_TYPE_PROD);

        // 起止日期
        chbTransMonth.setStartdate(firstday);
        chbTransMonth.setEnddate(lastday);

        // 数据日期：批处理日期
        chbTransMonth.setStatusDate(report.getStatusDate());

        // 报表频率：月报
        chbTransMonth.setReportfrequ(ChbTransMonthReportConstants.REPORT_FREQU_M);

        // 发行机构代码取值
        chbTransMonth.setIssuePartyId(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_ISSUE_PARTY_ID));
        chbTransMonth.setIssuePartyName(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_ISSUE_PARTY_NAME));

        // 用户信息
        chbTransMonth.setOrgCode(CommonUtil.getOrgCode());
        chbTransMonth.setCreateDept(CommonUtil.getDeptCode());
        chbTransMonth.setCreateUser(CommonUtil.getUserName());
        chbTransMonth.setUpdateUser(CommonUtil.getUserName());

        // 数据状态
        chbTransMonth.setStatus(DictCodeListExt2.报送数据状态_新建.getValue());
        chbTransMonth.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());

        Map<String, ChbTransMonthReportSubDetailDto> result = new HashMap<String, ChbTransMonthReportSubDetailDto>();
        // 查询产品月报需要统计的项
        List<ChbTransMonthTemplate> listKey = chbTransMonthReportSubRegistBatchMapper
                .selectSubTemplatesByReporttype(ChbTransMonthReportConstants.REPORT_TYPE_PROD);
        for (ChbTransMonthTemplate k : listKey) {
            ChbTransMonthReportSubDetailDto detail = new ChbTransMonthReportSubDetailDto();
            detail.setTermid(chbTransMonth.getTermid());
            detail.setReporttype(chbTransMonth.getReporttype());
            detail.setPropertycode(k.getPropertycode());
            result.put(k.getPropertycode(), detail);
        }
        if (listKey.size() != 29) {
            logger.error("理财产品转型情况月度统计表模板配置错误，请检查");
            return;
        }

        ChbMonthPrdPosition prod = new ChbMonthPrdPosition();
        prod.setStartdate(firstday);
        prod.setEnddate(lastday);
        List<ChbMonthPrdPosition> listPrdPosition = chbMonthPrdPositionMapper.select(prod);

        ChbTransMonthReportSubDetailDto line1Sub1 = result.get(ChbTransMonthReportConstants.GMLCCP); // 1.1 公募理财产品
        ChbTransMonthReportSubDetailDto line1Sub2 = result.get(ChbTransMonthReportConstants.SMLCCP); // 1.2 私募理财产品
        ChbTransMonthReportSubDetailDto line2Sub1 = result.get(ChbTransMonthReportConstants.GDSYL); // 2.1 固定收益类
        ChbTransMonthReportSubDetailDto line2Sub2 = result.get(ChbTransMonthReportConstants.QYL); // 2.2 权益类
        ChbTransMonthReportSubDetailDto line2Sub3 = result.get(ChbTransMonthReportConstants.SPJJRYSPL); // 2.3 商品及金融衍生品类
        ChbTransMonthReportSubDetailDto line2Sub4 = result.get(ChbTransMonthReportConstants.HHL); // 2.4 混合类
        ChbTransMonthReportSubDetailDto line3Sub1 = result.get(ChbTransMonthReportConstants.FBSCP); // 3.1 封闭式
        ChbTransMonthReportSubDetailDto line3Sub2 = result.get(ChbTransMonthReportConstants.KFSCP); // 3.2 开放式
        ChbTransMonthReportSubDetailDto line3Sub2Sub1 = result.get(ChbTransMonthReportConstants.XJGLLCP); // 其中：3.2.1 现金管理类产品
        ChbTransMonthReportSubDetailDto line3Sub2Sub2 = result.get(ChbTransMonthReportConstants.FBQZBNYSDDQKFCP); // 3.2.2 封闭期在半年以上的定期开放式产品
        ChbTransMonthReportSubDetailDto line4Sub1Sub1 = result.get(ChbTransMonthReportConstants.FBSSGYYN); // 4.1.1 3个月（含）以内
        ChbTransMonthReportSubDetailDto line4Sub1Sub2 = result.get(ChbTransMonthReportConstants.FBSSLGY); // 4.1.2 3-6个月(含)
        ChbTransMonthReportSubDetailDto line4Sub1Sub3 = result.get(ChbTransMonthReportConstants.FBSLSGY); // 4.1.3 6-12个月（含）
        ChbTransMonthReportSubDetailDto line4Sub1Sub4 = result.get(ChbTransMonthReportConstants.FBSYSN); // 4.1.4 1-3年（含）
        ChbTransMonthReportSubDetailDto line4Sub1Sub5 = result.get(ChbTransMonthReportConstants.FBSSNYS); // 4.1.5 3年以上
        ChbTransMonthReportSubDetailDto line4Sub2Sub1 = result.get(ChbTransMonthReportConstants.KFSMR); // 4.2.1 每日
        ChbTransMonthReportSubDetailDto line4Sub2Sub2 = result.get(ChbTransMonthReportConstants.KFSQT); // 4.2.2 7天（含）以内
        ChbTransMonthReportSubDetailDto line4Sub2Sub3 = result.get(ChbTransMonthReportConstants.KFSQTYGY); // 4.2.3 7天-1个月（含）
        ChbTransMonthReportSubDetailDto line4Sub2Sub4 = result.get(ChbTransMonthReportConstants.KFSYSY); // 4.2.4 1-3个月（含）
        ChbTransMonthReportSubDetailDto line4Sub2Sub5 = result.get(ChbTransMonthReportConstants.KFSSLY); // 4.2.5 3-6个月(含)
        ChbTransMonthReportSubDetailDto line4Sub2Sub6 = result.get(ChbTransMonthReportConstants.KFSLSY); // 4.2.6 6-12个月（含）
        ChbTransMonthReportSubDetailDto line4Sub2Sub7 = result.get(ChbTransMonthReportConstants.KFSYSN); // 4.2.7 1-3年（含）
        ChbTransMonthReportSubDetailDto line4Sub2Sub8 = result.get(ChbTransMonthReportConstants.KFSSNYS); // 4.2.8 3年以上

        ChbTransMonthReportSubDetailDto lineSum1Sub3 = result.get(ChbTransMonthReportConstants.SUM_MJFS); // 1.3 合 计
        ChbTransMonthReportSubDetailDto lineSum2Sub5 = result.get(ChbTransMonthReportConstants.SUM_TZXZ); // 2.5 合 计
        ChbTransMonthReportSubDetailDto lineSum3Sub3 = result.get(ChbTransMonthReportConstants.SUM_CPYZMS); // 3.3 合 计
        ChbTransMonthReportSubDetailDto lineSum4Sub1Sub6 = result.get(ChbTransMonthReportConstants.SUM_FBSCP); // 4.1.6 合计
        ChbTransMonthReportSubDetailDto lineSum4Sub2Sub9 = result.get(ChbTransMonthReportConstants.SUM_KFSCP); // 4.2.9 合计
        ChbTransMonthReportSubDetailDto lineSum4Sub3 = result.get(ChbTransMonthReportConstants.SUM_QX); // 4.3 合计
        // 汇率
        Map<String, BigDecimal> rateMap = new HashMap<String, BigDecimal>();

        for (ChbMonthPrdPosition pos : listPrdPosition) {
            // 本期净募集金额
            if (pos.getCleanamtsum() == null) {
                pos.setCleanamtsum(BigDecimal.ZERO);
            }
            // 本期总募集金额
            if (pos.getDirtyamtsum() == null) {
                pos.setDirtyamtsum(BigDecimal.ZERO);
            }
            // 本期客户实现收益总额
            if (pos.getCustomeramt() == null) {
                pos.setCustomeramt(BigDecimal.ZERO);
            }
            // 期末余额
            if (pos.getEndamtsumOth() == null) {
                pos.setEndamtsumOth(BigDecimal.ZERO);
            }
            if (pos.getVdate() == null) {
                logger.error("产品信息错误：起息日为空（" + pos.getProdid() + ")");
                continue;
            }

            // DURATIONFLAG ='01'&&PROFIT_TYPE='03'，理财产品转型情况月度统计表
            if (ProdConstants.DURATIONFLAG_HOLD.equals(pos.getDurationflag())
                    && ProdConstants.PROFITTYPE_FBBFDSY.equals(pos.getProfitType())) { // 非保本
                // 将币种转换成人民币，统计其它行
                // 计算时若本位币不是人民币，则先按照产品折算成人民币保留2位小数后再进行计算其他指标数据；
                // 直接取币种来判断
                if (!ProdConstants.CCY_CNY.equals(pos.getCcy())) {// 外币
                    BigDecimal exchangeRate = ChbCcyConvertUtil.exchangeMoney(pos.getCcy(), ProdConstants.CCY_CNY,
                            chbTransMonth.getEnddate(), BigDecimal.ONE, rateMap, ProdConstants.EXCHANGE_SOURCE_SPJ);
                    if (exchangeRate == null) {
                        exchangeRate = BigDecimal.ONE;
                    }
                    pos.setDirtyamtsum(ArithmeticUtil.round(pos.getDirtyamtsum().multiply(exchangeRate), 2, RoundingMode.HALF_UP));
                    pos.setCleanamtsum(ArithmeticUtil.round(pos.getCleanamtsum().multiply(exchangeRate), 2, RoundingMode.HALF_UP));

                    pos.setEndamtsumOth(ArithmeticUtil.round(pos.getEndamtsumOth().multiply(exchangeRate), 2, RoundingMode.HALF_UP));
                    pos.setCustomeramt(ArithmeticUtil.round(pos.getCustomeramt().multiply(exchangeRate), 2, RoundingMode.HALF_UP));
                }
                /* 1 按募集方式划分 */
                // 第1行 1.1 公募理财产品
                if (ProdConstants.RAISEMODE_GM.equals(pos.getRaisemode())) { // 公募
                    calcMonthReportSubDetail(line1Sub1, pos);
                }
                // 第2行 1.2 私募理财产品
                else {
                    calcMonthReportSubDetail(line1Sub2, pos);
                }

                /* 2 按投资性质划分 */
                // 第4行 2.1 固定收益类
                if (ProdConstants.IVSTORIENTATION_GDSY.equals(pos.getIvstorientation())) { // 固定收益类
                    calcMonthReportSubDetail(line2Sub1, pos);
                }
                // 第5行 2.2 权益类
                else if (ProdConstants.IVSTORIENTATION_QYL.equals(pos.getIvstorientation())) { // 权益类
                    calcMonthReportSubDetail(line2Sub2, pos);
                }
                // 第6行 2.3 商品及金融衍生品类
                else if (ProdConstants.IVSTORIENTATION_SPJRP.equals(pos.getIvstorientation())) { // 商品及金融衍生品类
                    calcMonthReportSubDetail(line2Sub3, pos);
                }
                // 第7行 2.4 混合类
                else if (ProdConstants.IVSTORIENTATION_HHL.equals(pos.getIvstorientation())) { // 混合类
                    calcMonthReportSubDetail(line2Sub4, pos);
                }

                /* 3 按产品运作模式划分 */
                // 第9行 3.1 封闭式
                if (ProdConstants.OPERATEMODE_FBSJZX.equals(pos.getOperateMode())
                        || ProdConstants.OPERATEMODE_FBSFJZX.equals(pos.getOperateMode())) { // 封闭式
                    calcMonthReportSubDetail(line3Sub1, pos);
                }

                // 第10行 3.2 开放式
                else if (ProdConstants.OPERATEMODE_KFSJZX.equals(pos.getOperateMode())
                        || ProdConstants.OPERATEMODE_KFSFJZX.equals(pos.getOperateMode())) { // 开放式
                    calcMonthReportSubDetail(line3Sub2, pos);

                    // 第11行 其中：3.2.1 现金管理类产品
                    if ("01".equals(pos.getCashmanacate())) { // 现金管理类产品
                        calcMonthReportSubDetail(line3Sub2Sub1, pos);
                    }

                    // 第12行 3.2.2 封闭期在半年以上的定期开放式产品
                    if ("02".equals(pos.getOpenduration())) { // 封闭期在半年以上的定期开放式产品
                        calcMonthReportSubDetail(line3Sub2Sub2, pos);
                    }
                }

                /* 4 按期限划分 */
                // 4.1 封闭式产品
                if (ProdConstants.OPERATEMODE_FBSJZX.equals(pos.getOperateMode())
                        || ProdConstants.OPERATEMODE_FBSFJZX.equals(pos.getOperateMode())) { // 封闭式
                    // 第14行 4.1.1 3个月（含）以内
                    if (ProdConstants.TERMTYPE_U1D.equals(pos.getTermtype())
                            || ProdConstants.TERMTYPE_U7D.equals(pos.getTermtype())
                            || ProdConstants.TERMTYPE_U1M.equals(pos.getTermtype())
                            || ProdConstants.TERMTYPE_U3M.equals(pos.getTermtype())) { // 3个月以内
                        calcMonthReportSubDetail(line4Sub1Sub1, pos);
                    }
                    // 第15行 4.1.2 3-6个月(含)
                    else if (ProdConstants.TERMTYPE_U6M.equals(pos.getTermtype())) { // 3-6个月(含)
                        calcMonthReportSubDetail(line4Sub1Sub2, pos);
                    }
                    // 第16行 4.1.3 6-12个月（含）
                    else if (ProdConstants.TERMTYPE_U12M.equals(pos.getTermtype())) { // 6-12个月（含）
                        calcMonthReportSubDetail(line4Sub1Sub3, pos);
                    }
                    // 第17行 4.1.4 1-3年（含）
                    else if (ProdConstants.TERMTYPE_U3Y.equals(pos.getTermtype())) { // 1-3年（含）
                        calcMonthReportSubDetail(line4Sub1Sub4, pos);
                    }
                    // 第18行 4.1.5 3年以上
                    else if (ProdConstants.TERMTYPE_B3Y.equals(pos.getTermtype())) { // 3年以上
                        calcMonthReportSubDetail(line4Sub1Sub5, pos);
                    }
                }
                // 4.2 开放式产品
                else if (ProdConstants.OPERATEMODE_KFSJZX.equals(pos.getOperateMode())
                        || ProdConstants.OPERATEMODE_KFSFJZX.equals(pos.getOperateMode())) { // 开放式
                    // 第20行 4.2.1 每日
                    if (ProdConstants.TERMTYPE_U1D.equals(pos.getTermtype())) {
                        calcMonthReportSubDetail(line4Sub2Sub1, pos);
                    }
                    // 第21行 4.2.2 7天（含）以内
                    else if (ProdConstants.TERMTYPE_U7D.equals(pos.getTermtype())) {
                        calcMonthReportSubDetail(line4Sub2Sub2, pos);
                    }
                    // 第22行 4.2.3 7天-1个月（含）
                    else if (ProdConstants.TERMTYPE_U1M.equals(pos.getTermtype())) {
                        calcMonthReportSubDetail(line4Sub2Sub3, pos);
                    }
                    // 第23行 4.2.4 1-3个月（含）
                    else if (ProdConstants.TERMTYPE_U3M.equals(pos.getTermtype())) {
                        calcMonthReportSubDetail(line4Sub2Sub4, pos);
                    }
                    // 第24行 4.2.5 3-6个月(含)
                    else if (ProdConstants.TERMTYPE_U6M.equals(pos.getTermtype())) {
                        calcMonthReportSubDetail(line4Sub2Sub5, pos);
                    }
                    // 第25行 4.2.6 6-12个月（含）
                    else if (ProdConstants.TERMTYPE_U12M.equals(pos.getTermtype())) {
                        calcMonthReportSubDetail(line4Sub2Sub6, pos);
                    }
                    // 第26行 4.2.7 1-3年（含）
                    else if (ProdConstants.TERMTYPE_U3Y.equals(pos.getTermtype())) {
                        calcMonthReportSubDetail(line4Sub2Sub7, pos);
                    }
                    // 第27行 4.2.8 3年以上
                    else if (ProdConstants.TERMTYPE_B3Y.equals(pos.getTermtype())) {
                        calcMonthReportSubDetail(line4Sub2Sub8, pos);
                    }
                }
            } // 非保统计结束

        } // 产品头寸循环结束

        // 合计 1.按募集方式划分    1.3 合 计
        calcSumLine(lineSum1Sub3,  line1Sub1, line1Sub2);
        // 合计:2.按投资性质划分 2.5 合 计
        calcSumLine(lineSum2Sub5, line2Sub1, line2Sub2, line2Sub3, line2Sub4);
        // 合计:3.按产品运作模式划分   3.3 合 计
        calcSumLine(lineSum3Sub3, line3Sub1, line3Sub2);
        // 合计:4.1 封闭式产品 4.1.6 合计
        calcSumLine(lineSum4Sub1Sub6,line4Sub1Sub1, line4Sub1Sub2,
                line4Sub1Sub3, line4Sub1Sub4, line4Sub1Sub5);
        // 合计:4.2 开放式产品 4.2.9 合计
        calcSumLine(lineSum4Sub2Sub9, line4Sub2Sub1, line4Sub2Sub2,
                line4Sub2Sub3, line4Sub2Sub4, line4Sub2Sub5, line4Sub2Sub6, line4Sub2Sub7, line4Sub2Sub8);
        // 合计:4.按期限划分   4.3 合计
        calcSumLine(lineSum4Sub3, lineSum4Sub1Sub6, lineSum4Sub2Sub9);

        // 计算加权价格和产品只数
        for(ChbTransMonthReportSubDetailDto detailDto : result.values()) {
            calcTermsAndProdCount(detailDto);
        }

        // 遍历result，插入全部的数据
        // 删除时上一次已报送数据不能删除
        if (StringUtil.isNotEmpty(report.getWbsreportuuid())) {
            chbTransMonthReportSubRegistBatchMapper.deleteChbTransMonthDetail(chbTransMonth.getTermid(), chbTransMonth.getReporttype(),
                    report.getWbsreportuuid());
        }
        for (ChbTransMonthReportSubDetailDto v : result.values()) {
            v.setReportuuid(report.getReportuuid());
            chbTransMonthDetailMapper.insert(v);
        }

    }

    /**
     * 计算月度报表明细数据
     * 
     * @param detailDto 明细数据
     * @param pos 头寸数据
     *
     */
    private void calcMonthReportSubDetail(ChbTransMonthReportSubDetailDto detailDto, ChbMonthPrdPosition pos) {

        // 新产品
        if ("01".equals(pos.getIsFhxgcp())) {
            // 新产品余额
            detailDto.setNewBalance(ArithmeticUtil.doAdd(detailDto.getNewBalance(), pos.getEndamtsumOth()));
            // 新产品加权价格
            detailDto.setNewTerms(ArithmeticUtil.doAdd(detailDto.getNewTerms(), ArithmeticUtil.multiply(pos.getEndamtsumOth(), pos.getYieldE())));

        }else{
            // 老产品余额
            detailDto.setOldBalance(ArithmeticUtil.doAdd(detailDto.getOldBalance(), pos.getEndamtsumOth()));
            // 老产品加权价格
            detailDto.setOldTerms(ArithmeticUtil.doAdd(detailDto.getOldTerms(), ArithmeticUtil.multiply(pos.getEndamtsumOth(), pos.getYieldE())));
        }
    }

    /**
     * 计算合计项
     * 
     * @param sumLine 合计行
     * @param detailDtos 明细数据
     *
     */
    private void calcSumLine(ChbTransMonthReportSubDetailDto sumLine, ChbTransMonthReportSubDetailDto...detailDtos) {
        for (ChbTransMonthReportSubDetailDto detailDto : detailDtos) {
            // 第3列 新产品余额合计
            sumLine.setNewBalance(ArithmeticUtil.doAdd(sumLine.getNewBalance(), detailDto.getNewBalance()));
            // 第4列 新产品加权价格合计
            sumLine.setNewTerms(ArithmeticUtil.doAdd(sumLine.getNewTerms(), detailDto.getNewTerms()));
            // 第1列 老产品余额合计
            sumLine.setOldBalance(ArithmeticUtil.doAdd(sumLine.getOldBalance(), detailDto.getOldBalance()));
            // 第2列 老产品加权价格合计
            sumLine.setOldTerms(ArithmeticUtil.doAdd(sumLine.getOldTerms(), detailDto.getOldTerms()));

        }
    }

    /**
     * 计算产品加权价格和产品数量
     * 
     * @param detailDto 报表明细数据
     */
    private void calcTermsAndProdCount(ChbTransMonthReportSubDetailDto detailDto) {
        detailDto.setNewTerms(ArithmeticUtil.divide(detailDto.getNewTerms(), detailDto.getNewBalance(), 2));
        detailDto.setOldTerms(ArithmeticUtil.divide(detailDto.getOldTerms(), detailDto.getOldBalance(), 2));
    }

}
