package com.joyintech.tams.valuation.common.interf.base.dto;
/**
 * @author yanya
 * @create 2020-01-01 11:40
 */

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.TrdProductDeal;

/**
 * @ClassName ChannelFeeTradeDto
 * @Description: 通道费用支付Dto
 * @Author Administrator
 * @Date 2020/1/4
 * @Version V1.0
 **/
public class ChannelFeeTradeDto extends BaseEntity {


    private static final long serialVersionUID = -7588809475389060303L;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型（估值核算），F16:理财产品  F18:投资组合  F22:理财子产品
     */
    private String finprodType;

    /**
     * 分支序号 产品类型 为 "理财子产品" 时 分支序号为必填项
     */
    private Long branch;

    /**
     * 金融产品交易
     */
    TrdProductDeal trdProductDeal;

    /**
     * 获取 金融产品代码 finprodId
     * @return
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码 finprodId
     * @param finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 金融产品类型 finprodType
     * @return
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 金融产品类型 finprodType
     * @param finprodType
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    /**
     * 获取 分支序号 branch
     * @return
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号 branch
     * @param branch
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 获取 金融产品交易 trdProductDeal
     * @return
     */
    public TrdProductDeal getTrdProductDeal() {
        return trdProductDeal;
    }

    /**
     * 设置 金融产品交易 trdProductDeal
     * @param trdProductDeal
     */
    public void setTrdProductDeal(TrdProductDeal trdProductDeal) {
        this.trdProductDeal = trdProductDeal;
    }
}
