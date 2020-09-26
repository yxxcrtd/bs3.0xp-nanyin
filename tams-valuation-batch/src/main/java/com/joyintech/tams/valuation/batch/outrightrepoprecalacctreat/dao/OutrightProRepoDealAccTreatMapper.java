package com.joyintech.tams.valuation.batch.outrightrepoprecalacctreat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.valuation.batch.outrightrepoprecalacctreat.dto.BokDetailIdOutRightPro;
import com.joyintech.tams.valuation.batch.outrightrepoprecalacctreat.dto.FinDealPledgeOutRightPro;

public interface OutrightProRepoDealAccTreatMapper {

    /**
     * 查询付息区间内的买断式正回购质押品信息 查询项目 质押品.质押金融产品代码 质押品.质押金融产品类型 质押品.质押数量 标的类金融产品.交易场所 查询表 交易类金融产品 INNER
     * JOIN 质押品 ON 交易类金融产品.金融产品代码 = 质押品.金融产品代码 AND 交易类金融产品.金融产品类型 = 质押品.金融产品类型 AND 交易类金融产品.分支序号 =
     * 质押品.分支序号 LEFT JOIN 标的类金融产品 ON 质押品.质押金融产品代码 = 标的类金融产品.金融产品代码 AND 质押品.质押金融产品类型 =
     * 标的类金融产品.金融产品类型 查询条件 交易类金融产品.金融产品类型 = “债券买断式正回购” AND 交易类金融产品.金融产品代码 = 交易明细.金融产品代码
     * 
     * @param booksetId
     * @param booksetType
     * @return
     */
    public FinDealPledgeOutRightPro queryFinDealPledge(@Param("finprodId") String booksetId,
                                                       @Param("finprodType1") String finprodType1,
                                                       @Param("finprodType2") String finprodType2,
                                                       @Param("mpFinprodId") String mpFinprodId);

    /**
     * 账务明细代码查询 2.1 账务明细代码查询 查询项目 账务明细代码表.账务明细代码 case when 账务明细代码表.投资目的=“以摊余成本计量（AC）” then
     * “摊余成本类成本” case when 账务明细代码表.投资目的=“以公允价值计量且其变动计入当期损益（FVTPL）” then “公允价值损益类-成本” case when
     * 账务明细代码表.投资目的=“以公允价值计量且其变动计入其他综合收益（FVOCI）” then “综合收益类成本” end as 基础科目性质 查询表 账务明细代码表 查询条件
     * 账务明细代码表.账套代码 = 交易明细.账套代码 AND 账务明细代码表.业务明细代码= 买断式正回购质押品信息.质押金融产品代码 AND 账务明细代码表.业务明细类型=
     * 买断式正回购质押品信息.质押金融产品类型
     * 
     * @param booksetId
     * @param busiId
     * @param busiDetailType
     * @return
     */
    public List<BokDetailIdOutRightPro> queryBokDetailId(@Param("booksetId") String booksetId,
                                                         @Param("busiId") String busiId,
                                                         @Param("busiDetailType") String busiDetailType);

    /**
     * 买断式逆回购查询账务明细代码表
     * 
     * @param booksetId
     * @param busiId
     * @param busiDetailType
     * @return
     * @see
     * @since 1.0
     */
    public BokDetailIdOutRightPro queryBokDetailId2(@Param("booksetId") String booksetId,
                                                          @Param("busiId") String busiId,
                                                          @Param("busiDetailType") String busiDetailType,
                                                          @Param("invAim") String invAim);

}
