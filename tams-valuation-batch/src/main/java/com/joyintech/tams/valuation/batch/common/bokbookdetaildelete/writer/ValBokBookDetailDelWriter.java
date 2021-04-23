package com.joyintech.tams.valuation.batch.common.bokbookdetaildelete.writer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.valuation.batch.common.accounting.dao.AccountingCommonMapper;
import com.joyintech.tams.valuation.batch.common.bokbookdetaildelete.dto.ValBokBookDetailDelResult;
import com.joyintech.tams.valuation.batch.vatdraw.dao.VatDrawCommonMapper;
import com.joyintech.tams.valuation.common.batch.losscommon.service.LossCommonService;

/**
 * 账务明细删除批处理 - 写入
 * 
 * @author chengzhong
 * @version 1.0 2018年5月7日
 * @see ValBokBookDetailDelWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValBokBookDetailDelWriter extends BaseWriter<NeedToWriteEntity> {

    /**
     * 减值准备共通方法
     */
    @Autowired
    private LossCommonService lossCommonService;

    @Autowired
    private VatDrawCommonMapper vatDrawCommonMapper;

    @Autowired
    private AccountingCommonMapper accountingCommonMapper;
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void write(List<? extends NeedToWriteEntity> items)
            throws Exception {
        // 获取process返回组合的list值 - 记账凭证list
        List<ValBokBookDetailDelResult> resultList = (List<ValBokBookDetailDelResult>)items;

        // 删除账务明细（当前表）
        accountingCommonMapper.deleteBookDetailCurrent(this.getParams());
        // 删除账务明细（历史表）
        accountingCommonMapper.deleteBookDetailAll(this.getParams());
        // 删除增值税账务明细
        vatDrawCommonMapper.deleteTaxBokDetail(this.getParams());
        // 删除增值税账务明细余额
        vatDrawCommonMapper.deleteTaxBokBalance(this.getParams());
        // 删除增值税汇总余额
        vatDrawCommonMapper.deleteTaxBokBalanceSum(this.getParams());
        // 删除增值税已抵扣增值额
        vatDrawCommonMapper.deleteTaxDeductedBalance(this.getParams());
        // 删除统计分析明细表（账务明细）
        accountingCommonMapper.deleteBokStatDetPosition(this.getParams());
        // 删除统计分析账套汇总表
        accountingCommonMapper.deleteBokStatBokPosition(this.getParams());
        // 删除删除账套估值汇总表（账套）
        accountingCommonMapper.deleteBokValSummary(this.getParams());
        // 删除账务明细辅助表
        accountingCommonMapper.deleteBokBookdetailCurrentExt(this.getParams());
        // 回补信息删除
        accountingCommonMapper.deleteBokClawbackInfo(this.getParams());
        // 回补信息明细删除
        accountingCommonMapper.deleteBokClawbackInfoDetail(this.getParams());
        //删除统计分析明细表（资产减值）
        lossCommonService.deleteBokStatLossPosiiton(this.getParams());
    }

}
