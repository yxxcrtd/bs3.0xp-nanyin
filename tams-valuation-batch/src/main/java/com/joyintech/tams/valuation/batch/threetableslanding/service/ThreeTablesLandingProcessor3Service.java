/*
 * 文件名：ExpReturnTaTradeBookProcessorService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangzhao
 * 修改时间：2018年5月17日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.threetableslanding.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.batch.threetableslanding.dao.ThreeTableLandingMapper;
import com.joyintech.tams.valuation.batch.threetableslanding.dto.OwnerEquityChangeDto;
import com.joyintech.tams.valuation.batch.threetableslanding.dto.ThreeTableLandingResultDto;
import com.joyintech.tams.valuation.batch.threetableslanding.dto.ValTaskTodoDto;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 三表落地reader
 * 
 * @author weipeng
 * @version 1.0 2020年5月8日
 * @see ThreeTablesLandingProcessor3Service
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ThreeTablesLandingProcessor3Service {

    @Autowired
    private ThreeTableLandingMapper threeTableLandingMapper;

    /**
     * process 数据处理
     * 
     * @param param 传递参数
     * @return BookGenerateResultWithBokposition
     * @see
     * @since 1.0
     */
    public ThreeTableLandingResultDto execute(Map<String, Object> param) {
        // 获取reader返回值
        ValTaskTodoDto acctEntity = (ValTaskTodoDto)param.get(GlobalConstant.READER_RESULT);
        // 进行批处理需要将结果集封装到result
        ThreeTableLandingResultDto result = new ThreeTableLandingResultDto();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("booksetId", acctEntity.getBooksetId());
        params.put("vdate", DateUtil.addDay(acctEntity.getHappenDate(), -1));// 期初
        // 查询表样
        Map<String, OwnerEquityChangeDto> dtoMap = new HashMap<String, OwnerEquityChangeDto>();
        initData(dtoMap, acctEntity.getBooksetId(), acctEntity.getHappenDate());
        // 本期期初
        params.put("earlysta", "01");
        List<OwnerEquityChangeDto> thisTermData = threeTableLandingMapper.selectAll(params);
        // 本期期末
        params.put("earlysta", "02");
        params.put("mdate", acctEntity.getHappenDate()); // 本期
        List<OwnerEquityChangeDto> thisTermData_end = threeTableLandingMapper.selectAll(params);
        formatData(thisTermData_end, true, dtoMap, thisTermData);
        List<OwnerEquityChangeDto> list = new ArrayList<OwnerEquityChangeDto>();
        list.addAll(dtoMap.values());
        list.sort(new Comparator<OwnerEquityChangeDto>() {

            @Override
            public int compare(OwnerEquityChangeDto arg0, OwnerEquityChangeDto arg1) {
                if (arg0.getSortNum() < arg1.getSortNum()) {
                    return -1;
                }
                return 1;
            }
        });
        result.setListOwnerEquityChangeDto(list);
        return result;
    }

    public void formatData(List<OwnerEquityChangeDto> dataList, boolean flag,
                           Map<String, OwnerEquityChangeDto> dtoMap,
                           List<OwnerEquityChangeDto> dataList2) {
        // ---------------------------------------六、期末理财产品净值--------------------------------------------
        // 实收资本
        BigDecimal qm_sszb = BigDecimal.ZERO;
        // 未分配利润
        BigDecimal qm_wfplr = BigDecimal.ZERO;
        // 所有者权益
        BigDecimal qm_syzqy = BigDecimal.ZERO;
        // add by zwq 2020-12-17 start
        // 其他綜合收益
        BigDecimal qm_otherincome = BigDecimal.ZERO;
        // add by zwq 2020-12-17 end
        // ---------------------------------------本期向产品份额持有人分配利润产生的产品净值变动（净值减少以“-”号填列） -
        // 应付利润-------------
        // 期初_应付利润
        BigDecimal bqxcpfe_wfplr_qc = BigDecimal.ZERO;
        // ---------------------------------------一、期初理财产品净值--------------------------------------------
        // 实收资本1
        BigDecimal qcsyzqy_sszb = BigDecimal.ZERO;
        // 未分配利润1
        BigDecimal qcsyzqy_wfplr = BigDecimal.ZERO;
        // 所有者权益1
        BigDecimal qcsyzqy_syzqy = BigDecimal.ZERO;
        // 其他綜合收益1
        BigDecimal qc_otherincome = BigDecimal.ZERO;
        // 期初
        for (OwnerEquityChangeDto dto2 : dataList2) {
            if (StringUtil.equals("4001", dto2.getSubjectNo())) {
                if (StringUtil.equals(DictCodeList.余额方向_贷方.getValue(), dto2.getBalFlag())) {
                    // 实收资本1 = 实收资本1 + 期初.本币余额 + 期初.本币结转余额贷方- 期初.本币结转余额借方
                    qcsyzqy_sszb = ArithmeticUtil.doAdd(qcsyzqy_sszb, dto2.getbAmt(),
                        ArithmeticUtil.subtract(dto2.getbCoCAmt(), dto2.getbCoDAmt()));
                }
                else {
                    // 实收资本1 = 实收资本1 + -1*期初.本币余额 + 期初.本币结转余额贷方- 期初.本币结转余额借方
                    qcsyzqy_sszb = ArithmeticUtil.doAdd(qcsyzqy_sszb,
                        ArithmeticUtil.multiply(dto2.getbAmt(), BigDecimal.valueOf(-1)),
                        ArithmeticUtil.subtract(dto2.getbCoCAmt(), dto2.getbCoDAmt()));
                }
            }
            // add by zwq 2020-12-17 start
            if (StringUtil.equals("4003",dto2.getSubjectNo())){
                if (StringUtil.equals(DictCodeList.余额方向_贷方.getValue(),dto2.getBalFlag())){
                    // 其他综合收益1 = 其他综合收益1 + 期初.本币余额
                    qc_otherincome = ArithmeticUtil.doAdd(qc_otherincome,dto2.getbAmt());
                }else {
                    // 其他综合收益1 = 其他综合收益1 + -1*期初.本币余额
                    qc_otherincome = ArithmeticUtil.doAdd(qc_otherincome,ArithmeticUtil.multiply(BigDecimal.valueOf(-1),dto2.getbAmt()));
                }
            }
            // add by zwq 2020-12-17 end

            String subject = dto2.getSubjectNo().substring(0, 1);

            if ("4".equals(subject) || "6".equals(subject)) {

            }
            else {
                if (StringUtil.equals(DictCodeList.科目类型_资产类.getValue(), dto2.getSubjectType())) {
                    qcsyzqy_syzqy = ArithmeticUtil.doAdd(qcsyzqy_syzqy, dto2.getbAmt());
                }
                else if (StringUtil.equals(DictCodeList.科目类型_负债类.getValue(),
                    dto2.getSubjectType())) {
                    qcsyzqy_syzqy = ArithmeticUtil.doAdd(qcsyzqy_syzqy,
                        ArithmeticUtil.multiply(dto2.getbAmt(), BigDecimal.valueOf(-1)));
                }
                else {
                    qcsyzqy_syzqy = ArithmeticUtil.doAdd(qcsyzqy_syzqy,
                        ArithmeticUtil.subtract(dto2.getbDAmt(), dto2.getbCAmt()));
                }
            }

            if (StringUtil.equals("2232", dto2.getSubjectNo())) {
                // 应付利润1 = 应付利润1 + 期初.本币余额 + 期初.本币结转余额贷方- 期初.本币结转余额借方
                bqxcpfe_wfplr_qc = ArithmeticUtil.doAdd(bqxcpfe_wfplr_qc, dto2.getbAmt(),
                    ArithmeticUtil.subtract(dto2.getbCoCAmt(), dto2.getbCoDAmt()));
            }
        }
        // 未分配利润1 = 所有者权益1 - 其他综合收益1 - 实收资本1
        qcsyzqy_wfplr = ArithmeticUtil.subtract(qcsyzqy_syzqy, ArithmeticUtil.doAdd(qc_otherincome,qcsyzqy_sszb));
        if (flag) {
            dtoMap.get("一、期初理财产品净值").settIncomeFund(qcsyzqy_sszb);          // 本期金额_实收基金
            dtoMap.get("一、期初理财产品净值").settUnQuarterProfit(qcsyzqy_wfplr);    // 本期金额_未分配利润
            dtoMap.get("一、期初理财产品净值").settOwnerEquityTot(qcsyzqy_syzqy);     // 本期金额_所有者权益合计
            dtoMap.get("一、期初理财产品净值").settOtherCompIncome(qc_otherincome);   // 本期金额_其他综合收益
        }
        else {
            dtoMap.get("一、期初理财产品净值").setyIncomeFund(qcsyzqy_sszb);          // 本期金额_实收基金
            dtoMap.get("一、期初理财产品净值").setyUnQuarterProfit(qcsyzqy_wfplr);    // 本期金额_未分配利润
            dtoMap.get("一、期初理财产品净值").setyOwnerEquityTot(qcsyzqy_syzqy);     // 本期金额_所有者权益合计
            dtoMap.get("一、期初理财产品净值").setyOtherCompIncome(qc_otherincome);   // 本期金额_其他综合收益
        }
        // ---------------------------------------二、本期经营活动产生的理财产品净值变动数 (本期综合收益总额)--------------------------------------------
        // 实收资本2
        BigDecimal bqjyhd_sszb = BigDecimal.ZERO;
        // 未分配利润2
        BigDecimal bqjyhd_wfplr = BigDecimal.ZERO;
        // 所有者权益2
        BigDecimal bqjyhd_syzqy = BigDecimal.ZERO;
        // 其他综合收益2
        BigDecimal bqjyhd_qtzhsy = BigDecimal.ZERO;
        // ---------------------------------------三、本期理财产品份额交易产生的理财产品净值变动数 (减少以“-”号填列)--------------------------------------------------
        // -实收资本3
        BigDecimal bqcpfe_sszb = BigDecimal.ZERO;
        // 未分配利润3
        BigDecimal bqcpfe_wfplr = BigDecimal.ZERO;
        // 所有者权益3
        BigDecimal bqcpfe_syzqy = BigDecimal.ZERO;
        // ---------------------------------------三、本期理财产品份额交易产生的理财产品净值变动数 (减少以“-”号填列)
        // 1.理财产品申购款------------------------------------------
        // -实收资本3_1
        BigDecimal cprsg_sszb = BigDecimal.ZERO;
        // 未分配利润3_1
        BigDecimal cprsg_wfplr = BigDecimal.ZERO;
        // 所有者权益3_1
        BigDecimal cprsg_syzqy = BigDecimal.ZERO;
        // ---------------------------------------三、本期理财产品份额交易产生的理财产品净值变动数 (减少以“-”号填列)
        // 2.理财产品赎回款-------------------------------------------
        // -实收资本3_2
        BigDecimal cpsh_sszb = BigDecimal.ZERO;
        // 未分配利润3_2
        BigDecimal cpsh_wfplr = BigDecimal.ZERO;
        // 所有者权益3_2
        BigDecimal cpsh_syzqy = BigDecimal.ZERO;
        // 其他综合收益3_2
        BigDecimal cpsh_qtzhsy = BigDecimal.ZERO;
        // ---------------------------------------四、本期向理财产品份额持有人分配利润产生的理财产品净值变动数--------------------------------------------------------
        // -实收资本
        // BigDecimal bqxcpfe_sszb_bq=BigDecimal.ZERO;
        // 未分配利润4
        BigDecimal bqxcpfe_wfplr_bq = BigDecimal.ZERO;
        // 所有者权益
        // BigDecimal bqxcpfe_syzqy_bq=BigDecimal.ZERO;
        // -实收资本4
        BigDecimal bqxcpfe_sszb = BigDecimal.ZERO;
        // 未分配利润4_1
        BigDecimal bqxcpfe_wfplr = BigDecimal.ZERO;
        // 所有者权益4
        BigDecimal bqxcpfe_syzqy = BigDecimal.ZERO;

        // add by zwq 2020-12-17 start
        // ---------------------------------------五、其他综合收益结转未分配利润         暂时没有值--------------------------------------------------------
        // 未分配利润5
        BigDecimal qtzhsy_wfplr = BigDecimal.ZERO;
        // 实收资本5
        BigDecimal qtzhsy_sszb = BigDecimal.ZERO;
        // 其他综合收益5
        BigDecimal qtzhsy_qita = BigDecimal.ZERO;
        // 所有者权益5
        BigDecimal qtzhsy_syzqy = BigDecimal.ZERO;
        // add by zwq 2020-12-17 end
        // --------------------------------------- 计算
        // --------------------------------------------------
        for (OwnerEquityChangeDto dto : dataList) {
            // ------------------二、本期经营活动产生的理财产品净值变动数 (本期综合收益总额)--------------
            // 实收资本
            bqjyhd_sszb = BigDecimal.ZERO;
            // 未分配利润
            if (StringUtil.equals("6011", dto.getSubjectNo())
                || StringUtil.equals("6101", dto.getSubjectNo())
                || StringUtil.equals("6111", dto.getSubjectNo())
                || StringUtil.equals("6112", dto.getSubjectNo())
                || StringUtil.equals("6302", dto.getSubjectNo())
                || StringUtil.equals("6412", dto.getSubjectNo())
                || StringUtil.equals("6403", dto.getSubjectNo())
                || StringUtil.equals("6404", dto.getSubjectNo())
                || StringUtil.equals("6406", dto.getSubjectNo())
                || StringUtil.equals("6409", dto.getSubjectNo())
                || StringUtil.equals("6410", dto.getSubjectNo())
                || StringUtil.equals("6407", dto.getSubjectNo())
                || StringUtil.equals("6408", dto.getSubjectNo())
                || StringUtil.equals("6411", dto.getSubjectNo())
                || StringUtil.equals("6605", dto.getSubjectNo())
                || StringUtil.equals("6702", dto.getSubjectNo())) {
                // 原币当日发生额贷方-原币当日发生额借方+原币结转当日发生额借方 - 原币结转当日发生额贷方
                bqjyhd_wfplr = ArithmeticUtil.doAdd(bqjyhd_wfplr,
                    ArithmeticUtil.subtract(
                        dto.getTdyOCAmt() == null ? BigDecimal.ZERO : dto.getTdyOCAmt(),
                        dto.getTdyODAmt() == null ? BigDecimal.ZERO : dto.getTdyODAmt()),
                    ArithmeticUtil.subtract(
                        dto.getTdyOCoDAmt() == null ? BigDecimal.ZERO : dto.getTdyOCoDAmt(),
                        dto.getTdyOCoCAmt() == null ? BigDecimal.ZERO : dto.getTdyOCoCAmt()));
                // 未分配利润2 = 未分配利润2 + 本币当日发生额贷方 - 本币当日发生额借方 + 本币结转当日发生额借方 - 本币结转当日发生额贷方
                BigDecimal temp1 = ArithmeticUtil.doAdd(bqjyhd_wfplr,ArithmeticUtil.subtract(
                        dto.getTdyBCAmt() == null ? BigDecimal.ZERO : dto.getTdyBCAmt(),
                        dto.getTdyBDAmt() == null ? BigDecimal.ZERO : dto.getTdyBDAmt()));
                bqjyhd_wfplr = ArithmeticUtil.doAdd(temp1,ArithmeticUtil.subtract(
                        dto.getTdyBCoDAmt() == null ? BigDecimal.ZERO : dto.getTdyBCoDAmt(),
                        dto.getTdyBCoCAmt() == null ? BigDecimal.ZERO : dto.getTdyBCoCAmt()));
            }

            // add by zwq 2020-12-17 start
            // 二、本期经营活动产生的理财产品净值变动数 (本期综合收益总额) -其他综合收益
            if(StringUtil.equals("4003", dto.getSubjectNo())){
                // 其他综合收益2 = 其他综合收益2 + 本币当日发生额贷方 - 本币当日发生额借方
                bqjyhd_qtzhsy = ArithmeticUtil.doAdd(bqjyhd_qtzhsy,ArithmeticUtil.subtract(dto.getTdyBCAmt(),dto.getTdyBDAmt()));
            }
            // add by zwq 2020-12-17 end

            // ---------------------------------------三、本期理财产品份额交易产生的理财产品净值变动数 (减少以“-”号填列)--------------------------------------------------
            if (StringUtil.equals("4001", dto.getSubjectNo())) {
                // -------------------------三、本期理财产品份额交易产生的理财产品净值变动数 (减少以“-”号填列)_实收资本--------------------------
                // SUM（本币当日发生额贷方）
                cprsg_sszb = ArithmeticUtil.doAdd(cprsg_sszb, dto.getTdyBCAmt());
                // -1*SUM（本币当日发生额借方）
                cpsh_sszb = ArithmeticUtil.doAdd(cpsh_sszb, dto.getTdyBDAmt());

            }
            // -------------------------三、本期理财产品份额交易产生的理财产品净值变动数 (减少以“-”号填列)_未分配利润--------------------------
            if (StringUtil.equals("4011", dto.getSubjectNo())) {
                // 本币申购未实现平准金贷
                cprsg_wfplr = ArithmeticUtil.doAdd(cprsg_wfplr,
                    ArithmeticUtil.subtract(dto.getTdyPurBCUlamt(), dto.getTdyPurBDUlamt()));
                // 本币赎回未实现平准金贷
                cpsh_wfplr = ArithmeticUtil.doAdd(cpsh_wfplr,
                    ArithmeticUtil.subtract(dto.getTdyRedBCUlamt(), dto.getTdyRedBDUlamt()));
            }
            // ------------------------- 四、本期向理财产品份额持有人分配利润产生的理财产品净值变动数
            // --------------------------------------------------
            if (StringUtil.equals("2232", dto.getSubjectNo())) {
                // 本期 原币余额 + 原币结转余额贷方- 原币结转余额借方
                bqxcpfe_wfplr_bq = ArithmeticUtil.doAdd(bqxcpfe_wfplr_bq, dto.getTdyBDAmt(),
                    ArithmeticUtil.subtract(dto.getbCoDAmt(), dto.getbCoCAmt()));
            }
        }
        // 本期-期初
        // bqxcpfe_wfplr = ArithmeticUtil.subtract(bqxcpfe_wfplr_bq, bqxcpfe_wfplr_qc);
        bqxcpfe_wfplr = ArithmeticUtil.subtract(bqxcpfe_wfplr_bq, qcsyzqy_wfplr);
        // 此数据恒负数
        if (bqxcpfe_wfplr.compareTo(BigDecimal.ZERO) >= 0) {
            bqxcpfe_wfplr = ArithmeticUtil.multiply(BigDecimal.valueOf(-1), bqxcpfe_wfplr);
        }
        cpsh_sszb = ArithmeticUtil.multiply(BigDecimal.valueOf(-1), cpsh_sszb);
        // --------------------------------------- 赋值
        // --------------------------------------------------
        if (flag) {
            dtoMap.get("二、本期经营活动产生的理财产品净值变动数 (本期综合收益总额)").settIncomeFund(bqjyhd_sszb);
            dtoMap.get("二、本期经营活动产生的理财产品净值变动数 (本期综合收益总额)").settUnQuarterProfit(bqjyhd_wfplr);
            dtoMap.get("二、本期经营活动产生的理财产品净值变动数 (本期综合收益总额)").settOtherCompIncome(bqjyhd_qtzhsy);
            bqjyhd_syzqy = ArithmeticUtil.doAdd(bqjyhd_qtzhsy, bqjyhd_wfplr);
            dtoMap.get("二、本期经营活动产生的理财产品净值变动数 (本期综合收益总额)").settOwnerEquityTot(bqjyhd_syzqy);

            // 实收资本
            bqcpfe_sszb = ArithmeticUtil.doAdd(cprsg_sszb, cpsh_sszb);
            dtoMap.get("三、本期理财产品份额交易产生的理财产品净值变动数 (减少以“-”号填列)").settIncomeFund(bqcpfe_sszb);
            dtoMap.get("其中：1.理财产品申购款").settIncomeFund(cprsg_sszb);
            dtoMap.get("2.理财产品赎回款").settIncomeFund(cpsh_sszb);

            // 未分配利润
            bqcpfe_wfplr = ArithmeticUtil.doAdd(cprsg_wfplr, cpsh_wfplr);
            dtoMap.get("三、本期理财产品份额交易产生的理财产品净值变动数 (减少以“-”号填列)").settUnQuarterProfit(bqcpfe_wfplr);
            dtoMap.get("其中：1.理财产品申购款").settUnQuarterProfit(cprsg_wfplr);
            dtoMap.get("2.理财产品赎回款").settUnQuarterProfit(cpsh_wfplr);

            // 所有者权益=实收资本+费分配利润
            bqcpfe_syzqy = ArithmeticUtil.doAdd(bqcpfe_sszb, bqcpfe_wfplr);
            dtoMap.get("三、本期理财产品份额交易产生的理财产品净值变动数 (减少以“-”号填列)").settOwnerEquityTot(bqcpfe_syzqy);

            cprsg_syzqy = ArithmeticUtil.doAdd(cprsg_sszb, cprsg_wfplr);
            dtoMap.get("其中：1.理财产品申购款").settOwnerEquityTot(cprsg_syzqy);

            cpsh_syzqy = ArithmeticUtil.doAdd(cpsh_sszb, cpsh_wfplr);
            dtoMap.get("2.理财产品赎回款").settOwnerEquityTot(cpsh_syzqy);

            dtoMap.get("四、本期向理财产品份额持有人分配利润产生的理财产品净值变动数").settIncomeFund(bqxcpfe_sszb);
            dtoMap.get("四、本期向理财产品份额持有人分配利润产生的理财产品净值变动数").settUnQuarterProfit(bqxcpfe_wfplr);
            // 本期经营活动产生的产品净值变动数（本期利润）_所有者权益 = 本期经营活动产生的产品净值变动数（本期利润）_实收资本+
            // 本期经营活动产生的产品净值变动数（本期利润）_未分配利润
            bqxcpfe_syzqy = ArithmeticUtil.doAdd(bqxcpfe_sszb, bqxcpfe_wfplr);
            dtoMap.get("四、本期向理财产品份额持有人分配利润产生的理财产品净值变动数").settOwnerEquityTot(bqxcpfe_syzqy);

            // add by zwq 2020-12-17 start
            dtoMap.get("四、本期向理财产品份额持有人分配利润产生的理财产品净值变动数").settIncomeFund(qtzhsy_sszb);
            dtoMap.get("四、本期向理财产品份额持有人分配利润产生的理财产品净值变动数").settUnQuarterProfit(qtzhsy_wfplr);
            dtoMap.get("四、本期向理财产品份额持有人分配利润产生的理财产品净值变动数").settOtherCompIncome(qtzhsy_qita);
            dtoMap.get("四、本期向理财产品份额持有人分配利润产生的理财产品净值变动数").settOwnerEquityTot(qtzhsy_syzqy);
            // add by zwq 2020-12-17 end
        }
        else {
            dtoMap.get("二、本期经营活动产生的理财产品净值变动数 (本期综合收益总额)").setyIncomeFund(bqjyhd_sszb);
            dtoMap.get("二、本期经营活动产生的理财产品净值变动数 (本期综合收益总额)").setyUnQuarterProfit(bqjyhd_wfplr);
            dtoMap.get("二、本期经营活动产生的理财产品净值变动数 (本期综合收益总额)").setyOtherCompIncome(bqjyhd_qtzhsy);
            bqjyhd_syzqy = ArithmeticUtil.doAdd(bqjyhd_sszb, bqjyhd_wfplr);
            dtoMap.get("二、本期经营活动产生的理财产品净值变动数 (本期综合收益总额)").setyOwnerEquityTot(bqjyhd_syzqy);

            // 实收资本
            bqcpfe_sszb = ArithmeticUtil.doAdd(cprsg_sszb, cpsh_sszb);
            dtoMap.get("三、本期理财产品份额交易产生的理财产品净值变动数 (减少以“-”号填列)").setyIncomeFund(bqcpfe_sszb);
            dtoMap.get("其中：1.理财产品申购款").setyIncomeFund(cprsg_sszb);
            dtoMap.get("2.理财产品赎回款").setyIncomeFund(cpsh_sszb);

            // 未分配利润
            bqcpfe_wfplr = ArithmeticUtil.doAdd(cprsg_wfplr, cpsh_wfplr);
            dtoMap.get("三、本期理财产品份额交易产生的理财产品净值变动数 (减少以“-”号填列)").setyUnQuarterProfit(bqcpfe_wfplr);
            dtoMap.get("其中：1.理财产品申购款").setyUnQuarterProfit(cprsg_wfplr);
            dtoMap.get("2.理财产品赎回款").setyUnQuarterProfit(cpsh_wfplr);

            // 所有者权益=实收资本+费分配利润
            bqcpfe_syzqy = ArithmeticUtil.doAdd(bqcpfe_sszb, bqcpfe_wfplr);
            dtoMap.get("三、本期理财产品份额交易产生的理财产品净值变动数 (减少以“-”号填列)").setyOwnerEquityTot(bqcpfe_syzqy);

            cprsg_syzqy = ArithmeticUtil.doAdd(cprsg_sszb, cprsg_wfplr);
            dtoMap.get("1.理财产品申购款").setyOwnerEquityTot(cprsg_syzqy);

            cpsh_syzqy = ArithmeticUtil.doAdd(cpsh_sszb, cpsh_wfplr);
            dtoMap.get("2.理财产品赎回款").setyOwnerEquityTot(cpsh_syzqy);

            dtoMap.get("四、本期向理财产品份额持有人分配利润产生的理财产品净值变动数").setyIncomeFund(bqxcpfe_sszb);
            dtoMap.get("四、本期向理财产品份额持有人分配利润产生的理财产品净值变动数").setyUnQuarterProfit(bqxcpfe_wfplr);
            // 本期经营活动产生的产品净值变动数（本期利润）_所有者权益 = 本期经营活动产生的产品净值变动数（本期利润）_实收资本+
            // 本期经营活动产生的产品净值变动数（本期利润）_未分配利润
            bqxcpfe_syzqy = ArithmeticUtil.doAdd(bqxcpfe_sszb, bqxcpfe_wfplr);
            dtoMap.get("四、本期向理财产品份额持有人分配利润产生的理财产品净值变动数").setyOwnerEquityTot(bqxcpfe_syzqy);

            // add by zwq 2020-12-17 start
            dtoMap.get("四、本期向理财产品份额持有人分配利润产生的理财产品净值变动数").setyIncomeFund(qtzhsy_sszb);
            dtoMap.get("四、本期向理财产品份额持有人分配利润产生的理财产品净值变动数").setyUnQuarterProfit(qtzhsy_wfplr);
            dtoMap.get("四、本期向理财产品份额持有人分配利润产生的理财产品净值变动数").setyOtherCompIncome(qtzhsy_qita);
            dtoMap.get("四、本期向理财产品份额持有人分配利润产生的理财产品净值变动数").setyOwnerEquityTot(qtzhsy_syzqy);
            // add by zwq 2020-12-17 end
        }
        qm_sszb = ArithmeticUtil.doAdd(qm_sszb, qcsyzqy_sszb, bqjyhd_sszb, bqcpfe_sszb,
            bqxcpfe_sszb,qtzhsy_sszb);
        qm_wfplr = ArithmeticUtil.doAdd(qm_wfplr, qcsyzqy_wfplr, bqjyhd_wfplr, bqcpfe_wfplr,
            bqxcpfe_wfplr,qtzhsy_wfplr);
        qm_syzqy = ArithmeticUtil.doAdd(qm_syzqy, qcsyzqy_syzqy, bqjyhd_syzqy, bqcpfe_syzqy,
            bqxcpfe_syzqy,qtzhsy_syzqy);

        // add by zwq 2020-12-17 start
        // 其他综合收益合计
        qm_otherincome = ArithmeticUtil.doAdd(qm_otherincome,qc_otherincome,bqjyhd_qtzhsy,qtzhsy_qita);
        // add by zwq 2020-12-17 end

        if (flag) {
            dtoMap.get("六、期末理财产品净值").settIncomeFund(qm_sszb);
            dtoMap.get("六、期末理财产品净值").settUnQuarterProfit(qm_wfplr);
            dtoMap.get("六、期末理财产品净值").settOwnerEquityTot(qm_syzqy);
            dtoMap.get("六、期末理财产品净值").settOtherCompIncome(qm_otherincome);
        }
        else {
            dtoMap.get("六、期末理财产品净值").setyIncomeFund(qm_sszb);
            dtoMap.get("六、期末理财产品净值").setyUnQuarterProfit(qm_wfplr);
            dtoMap.get("六、期末理财产品净值").setyOwnerEquityTot(qm_syzqy);
            dtoMap.get("六、期末理财产品净值").setyOtherCompIncome(qm_otherincome);
        }

    }

    /**
     * 初始化数据
     *
     * @param dtoMap
     * @return
     * @see
     * @since 1.0
     */
    public void initData(Map<String, OwnerEquityChangeDto> dtoMap, String booksetId,
                         Date happenDate) {
        OwnerEquityChangeDto newDto = new OwnerEquityChangeDto();
        newDto.setBooksetId(booksetId);
        newDto.setBalanceDate(happenDate);
        newDto.setSortNum(1);
        newDto.setProject("一、期初理财产品净值");
        newDto.settIncomeFund(BigDecimal.ZERO);
        newDto.settUnQuarterProfit(BigDecimal.ZERO);
        newDto.settOwnerEquityTot(BigDecimal.ZERO);
        newDto.setyIncomeFund(BigDecimal.ZERO);
        newDto.setyUnQuarterProfit(BigDecimal.ZERO);
        newDto.setyOwnerEquityTot(BigDecimal.ZERO);
        dtoMap.put("一、期初理财产品净值", newDto);

        newDto = new OwnerEquityChangeDto();
        newDto.setBooksetId(booksetId);
        newDto.setBalanceDate(happenDate);
        newDto.setSortNum(2);
        newDto.setProject("二、本期经营活动产生的理财产品净值变动数 (本期综合收益总额)");
        newDto.settIncomeFund(BigDecimal.ZERO);
        newDto.settUnQuarterProfit(BigDecimal.ZERO);
        newDto.settOwnerEquityTot(BigDecimal.ZERO);
        newDto.setyIncomeFund(BigDecimal.ZERO);
        newDto.setyUnQuarterProfit(BigDecimal.ZERO);
        newDto.setyOwnerEquityTot(BigDecimal.ZERO);
        dtoMap.put("二、本期经营活动产生的理财产品净值变动数 (本期综合收益总额)", newDto);

        newDto = new OwnerEquityChangeDto();
        newDto.setBooksetId(booksetId);
        newDto.setBalanceDate(happenDate);
        newDto.setSortNum(3);
        newDto.setProject("三、本期理财产品份额交易产生的理财产品净值变动数 (减少以“-”号填列)");
        newDto.settIncomeFund(BigDecimal.ZERO);
        newDto.settUnQuarterProfit(BigDecimal.ZERO);
        newDto.settOwnerEquityTot(BigDecimal.ZERO);
        newDto.setyIncomeFund(BigDecimal.ZERO);
        newDto.setyUnQuarterProfit(BigDecimal.ZERO);
        newDto.setyOwnerEquityTot(BigDecimal.ZERO);
        dtoMap.put("三、本期理财产品份额交易产生的理财产品净值变动数 (减少以“-”号填列)", newDto);

        newDto = new OwnerEquityChangeDto();
        newDto.setBooksetId(booksetId);
        newDto.setBalanceDate(happenDate);
        newDto.setSortNum(4);
        newDto.setProject("其中：1.理财产品申购款");
        newDto.settIncomeFund(BigDecimal.ZERO);
        newDto.settUnQuarterProfit(BigDecimal.ZERO);
        newDto.settOwnerEquityTot(BigDecimal.ZERO);
        newDto.setyIncomeFund(BigDecimal.ZERO);
        newDto.setyUnQuarterProfit(BigDecimal.ZERO);
        newDto.setyOwnerEquityTot(BigDecimal.ZERO);
        dtoMap.put("其中：1.理财产品申购款", newDto);

        newDto = new OwnerEquityChangeDto();
        newDto.setBooksetId(booksetId);
        newDto.setBalanceDate(happenDate);
        newDto.setSortNum(5);
        newDto.setProject("2.理财产品赎回款");
        newDto.settIncomeFund(BigDecimal.ZERO);
        newDto.settUnQuarterProfit(BigDecimal.ZERO);
        newDto.settOwnerEquityTot(BigDecimal.ZERO);
        newDto.setyIncomeFund(BigDecimal.ZERO);
        newDto.setyUnQuarterProfit(BigDecimal.ZERO);
        newDto.setyOwnerEquityTot(BigDecimal.ZERO);
        dtoMap.put("2.理财产品赎回款", newDto);

        newDto = new OwnerEquityChangeDto();
        newDto.setBooksetId(booksetId);
        newDto.setBalanceDate(happenDate);
        newDto.setSortNum(6);
        newDto.setProject("四、本期向理财产品份额持有人分配利润产生的理财产品净值变动数");
        newDto.settIncomeFund(BigDecimal.ZERO);
        newDto.settUnQuarterProfit(BigDecimal.ZERO);
        newDto.settOwnerEquityTot(BigDecimal.ZERO);
        newDto.setyIncomeFund(BigDecimal.ZERO);
        newDto.setyUnQuarterProfit(BigDecimal.ZERO);
        newDto.setyOwnerEquityTot(BigDecimal.ZERO);
        dtoMap.put("四、本期向理财产品份额持有人分配利润产生的理财产品净值变动数", newDto);

        // add by zwq 2020-12-17 start
        newDto = new OwnerEquityChangeDto();
        newDto.setBooksetId(booksetId);
        newDto.setBalanceDate(happenDate);
        newDto.setSortNum(7);
        newDto.setProject("五、其他综合收益结转未分配利润");
        newDto.settIncomeFund(BigDecimal.ZERO);
        newDto.settUnQuarterProfit(BigDecimal.ZERO);
        newDto.settOwnerEquityTot(BigDecimal.ZERO);
        newDto.setyIncomeFund(BigDecimal.ZERO);
        newDto.setyUnQuarterProfit(BigDecimal.ZERO);
        newDto.setyOwnerEquityTot(BigDecimal.ZERO);
        dtoMap.put("五、其他综合收益结转未分配利润", newDto);
        // add by zwq 2020-12-17 end

        newDto = new OwnerEquityChangeDto();
        newDto.setBooksetId(booksetId);
        newDto.setBalanceDate(happenDate);
        newDto.setSortNum(8);
        newDto.setProject("六、期末理财产品净值");
        newDto.settIncomeFund(BigDecimal.ZERO);
        newDto.settUnQuarterProfit(BigDecimal.ZERO);
        newDto.settOwnerEquityTot(BigDecimal.ZERO);
        newDto.setyIncomeFund(BigDecimal.ZERO);
        newDto.setyUnQuarterProfit(BigDecimal.ZERO);
        newDto.setyOwnerEquityTot(BigDecimal.ZERO);
        dtoMap.put("六、期末理财产品净值", newDto);

    }

}
