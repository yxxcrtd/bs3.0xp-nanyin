package com.joyintech.tams.valuation.batch.common.accountingmethod.entity;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.common.base.entity.BokStatBokPosition;
import com.joyintech.tams.common.base.entity.BokStatFeePosiiton;

/**
 * 记账凭证生成结果(带【账务汇总表（账套）】 列表,带【统计分析明细表（费用）】数据)的
 * 
 * @author Administrator
 *
 */
public class BookGenerateResultWithBokStatFeePosition extends BookGenerateResult {

	/**
	 * 【统计分析明细表（费用）】 列表
	 */
	private List<BokStatFeePosiiton> bokStatFeePosiitonList = new ArrayList<BokStatFeePosiiton>();
	
	/** add by yanya on 20190924 邮储个性化：净值产品记录年化收益率R 根据ycbranch20181218版本合并 start **/
    private BokStatBokPosition bokStatBokPosition;

    public BokStatBokPosition getBokStatBokPosition() {
        return bokStatBokPosition;
    }

    public void setBokStatBokPosition(BokStatBokPosition bokStatBokPosition) {
        this.bokStatBokPosition = bokStatBokPosition;
    }
    /** add by yanya on 20190924 邮储个性化：净值产品记录年化收益率R 根据ycbranch20181218版本合并 end **/

	public List<BokStatFeePosiiton> getBokStatFeePosiitonList() {
		return bokStatFeePosiitonList;
	}

	public void setBokStatFeePosiitonList(List<BokStatFeePosiiton> bokStatFeePosiitonList) {
		this.bokStatFeePosiitonList = bokStatFeePosiitonList;
	}

}
