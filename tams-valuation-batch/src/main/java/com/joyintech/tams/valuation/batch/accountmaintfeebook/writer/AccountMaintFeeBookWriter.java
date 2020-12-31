package com.joyintech.tams.valuation.batch.accountmaintfeebook.writer;

import java.util.List;

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
public class AccountMaintFeeBookWriter extends BaseWriter<NeedToWriteEntity>{

    /**
     * 记账凭证生成类
     */
    @Autowired
    private BookAccountGenLogic bookAccountGenLogic;

    @Autowired
    private AccountingMethodMapper accountingMethodMapper;

    /**
     * 账务汇总表（账套）表,数据库操作
     */
    @Autowired
    private BokStatDetPosiitonMapper bokSummaryTableMapper;

	@SuppressWarnings("unchecked")
	@Override
	public void write(List<? extends NeedToWriteEntity> items) throws Exception {
		// 获取process返回组合的list值 - 记账凭证list
        List<BookGenerateResult> resultList = (List<BookGenerateResult>)items;
        // 循环记账凭证list，对每个记账凭证进行操作
        for (BookGenerateResult bookGenerateResult : resultList) {
            // 插入记账凭证主表信息 和 插入记账凭证明细表信息
            bookAccountGenLogic.insertKeepAccount(bookGenerateResult);
        }

        // 循环记账凭证list，对每个记账凭证进行操作
        for (BookGenerateResult bookGenerateResult : resultList) {
            // 插入【账务汇总表（账套）】表
           this.insertBokSummary(bookGenerateResult.getValBokStatDetPosiitonBatch());
        }
	}

	/**
     * 将数据，插入【账务汇总表（账套）】表
     * @param bokSummaryList
     * @return
     * @see
     * @since 1.0
     */
    private int insertBokSummary(List<BokStatDetPosiiton> bokSummaryList){
        int sucessNum = 0;
        //数据存在
        if(bokSummaryList!=null && !bokSummaryList.isEmpty()){
            //循环列表进行插入
            for(BokStatDetPosiiton vo: bokSummaryList){
                //不包含【处理序列】的主键删除
                accountingMethodMapper.deleteBokStatDetPosiiton(vo);
                //插入
                sucessNum = sucessNum + bokSummaryTableMapper.insert(vo);
            }
        }
        return sucessNum;
    }
    
}
