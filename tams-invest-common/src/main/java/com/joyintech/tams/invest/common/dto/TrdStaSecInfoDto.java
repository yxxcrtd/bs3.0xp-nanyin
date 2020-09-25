package com.joyintech.tams.invest.common.dto;

import java.math.BigDecimal;

/**
 * @program: tams-invest-parent
 * @description: 已质押标准券信息Dto
 * @author: 陈志力
 * @create: 2019-01-24
 **/
public class TrdStaSecInfoDto {

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 金融产品代码
     */
    private String finProdId;

    /**
     * 证券账户
     */
    private String secAcctId;

    /**
     * 已质押券面总额
     */
    private BigDecimal pledgePrinAmt;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 按投资目的排序：摊余成本类->公允价值变动计入其他综合收益->公允价值变动计入当期损益
     */
    private Long invAimSort;

}
