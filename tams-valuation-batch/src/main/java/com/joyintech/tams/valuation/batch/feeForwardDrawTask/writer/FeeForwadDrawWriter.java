package com.joyintech.tams.valuation.batch.feeForwardDrawTask.writer;

import java.util.List;

import com.joyintech.tams.common.base.dao.BokStatBokPositionMapper;
import com.joyintech.tams.common.base.dao.BokStatFeePosiitonMapper;
import com.joyintech.tams.common.base.entity.BokStatFeePosiiton;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResultWithBokStatFeePosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.dao.BokStatDetPosiitonMapper;
import com.joyintech.tams.common.base.entity.BokStatDetPosiiton;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookAccountGenLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.dao.AccountingMethodMapper;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;

@Service
@Scope("prototype")
public class FeeForwadDrawWriter extends BaseWriter<NeedToWriteEntity>{

    /**
     * 记账凭证生成类
     */
    @Autowired
    private BookAccountGenLogic bookAccountGenLogic;

    @Autowired
    private AccountingMethodMapper accountingMethodMapper;

    /**
     * 【统计分析明细表（费用）】数据操作
     */
    @Autowired
    private BokStatFeePosiitonMapper bokStatFeePosiitonMapper;

	@SuppressWarnings("unchecked")
	@Override
	public void write(List<? extends NeedToWriteEntity> items) throws Exception {
		// 获取process返回组合的list值 - 记账凭证list
        List<BookGenerateResultWithBokStatFeePosition> resultList = (List<BookGenerateResultWithBokStatFeePosition>)items;
        // 循环记账凭证list，对每个记账凭证进行操作
        for (BookGenerateResultWithBokStatFeePosition bookGenerateResult : resultList) {
            // 插入记账凭证主表信息 和 插入记账凭证明细表信息
            bookAccountGenLogic.insertKeepAccount(bookGenerateResult);
        }

        // 循环记账凭证list，对每个记账凭证进行操作
        for (BookGenerateResultWithBokStatFeePosition bookGenerateResult : resultList) {
            // 插入【账务汇总表（账套）】表
           this.insertBokStatFeePosition(bookGenerateResult.getBokStatFeePosiitonList());
        }
	}

    /**
     * 插入【统计分析明细表（费用）】数据
     *
     * @param bokStatFeePosiitonList
     * @return
     */
    private int insertBokStatFeePosition(List<BokStatFeePosiiton> bokStatFeePosiitonList) {

        int sucessNum = 0;

        // 数据存在
        if (bokStatFeePosiitonList != null && !bokStatFeePosiitonList.isEmpty()) {
            // 循环列表进行插入
            for (BokStatFeePosiiton vo : bokStatFeePosiitonList) {
                // 根据主键判断是否存在
                BokStatFeePosiiton haveVo = bokStatFeePosiitonMapper.selectByPrimaryKey(vo);

                // 已经存在，进行更新操作
                if (haveVo != null) {
                    sucessNum = sucessNum + bokStatFeePosiitonMapper.updateByPrimaryKey(vo);
                }
                // 不存在，进行插入操作
                else {
                    sucessNum = sucessNum + bokStatFeePosiitonMapper.insert(vo);
                }
            }
        }

        return sucessNum;
    }

}
