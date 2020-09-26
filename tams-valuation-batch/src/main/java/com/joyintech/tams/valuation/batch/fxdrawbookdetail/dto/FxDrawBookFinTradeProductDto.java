package com.joyintech.tams.valuation.batch.fxdrawbookdetail.dto;
/**
 * @author yanya
 * @create 2019-12-12 16:43
 */

import com.joyintech.tams.common.base.entity.FinTradeProductAdd;

/**
 * @ClassName FxDrawBookFinTradeProductDto
 * @Description: 交易类金融产品dt0
 * @Author yanya
 * @Date 2019/12/23
 * @Version V1.0
 **/
public class FxDrawBookFinTradeProductDto extends FinTradeProductAdd {

    private static final long serialVersionUID = -1629940304641449713L;

    /**
     * 截止日是否冲回
     */
    private String isRushBack;


    /**
     * 获取 截止日是否冲回
     * @return
     */
    public String getIsRushBack() {
        return isRushBack;
    }

    /**
     * 设置 截止日是否冲回
     * @param isRushBack
     */
    public void setIsRushBack(String isRushBack) {
        this.isRushBack = isRushBack;
    }
}
