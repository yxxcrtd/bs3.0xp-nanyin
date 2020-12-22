/*
 * 文件名：ProductReportProcessorService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：Apr 10, 2019
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.batch.psbc.productreport.service;

import com.google.common.collect.Lists;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.batch.psbc.productreport.dao.ProductReportProcessorMapper;
import com.joyintech.tams.batch.psbc.productreport.dto.ProductReportDto;
import com.joyintech.tams.batch.psbc.productreport.dto.ProductReportResult;
import com.joyintech.tams.batch.psbc.productreport.dto.RepProdMessageDto;
import com.joyintech.tams.batch.psbc.productreport.dto.RepProdStatusDto;
import com.joyintech.tams.common.base.entity.ChbBotAstPosition;
import com.joyintech.tams.common.svreport.chb.util.ChbCcyConvertUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.busicommon.common.util.ArithmeticUtil;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthAssetPosition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * 理财报表生成批处理 ProcessorService
 *
 * @author jiangpeng
 * @version 1.0 2020/12/05
 * @see ProductReportProcessorService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ProductReportProcessorService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductReportProcessorMapper productReportProcessorMapper;

    public ProductReportResult execute(ProductReportDto productReportDto) {
        // 返回集
        ProductReportResult result = new ProductReportResult();
        // 理财产品状态表
        RepProdStatusDto repProdStatusDto = new RepProdStatusDto();
        // 理财产品信息表
        RepProdMessageDto repProdMessageDto = new RepProdMessageDto();
        // 是否生成理财产品状态表
        String isRepProdStatus = "Y";
        // 是否生成理财产品信息表
        String isRepProdMessage = "Y";
        // 变量.账套
        String bookSetId = productReportDto.getBooksetId();
        // 变量.入账日
        Date bookDate = productReportDto.getBookDate();
        // 返回集.账套代码
        result.setBookSetId(productReportDto.getBooksetId());
        // 返回集.入账日
        result.setBookDate(productReportDto.getBookDate());
        // 报表生成配置表 查询参数
        List<String> list = Arrays.asList(DictCodeListExt3.报表生成配置表_理财产品信息表.getValue(),
                DictCodeListExt3.报表生成配置表_理财产品状态表.getValue());
        // 查询参数赋值
        Map<String, Object> param = new HashMap<>();
        // 账套
        param.put("bookSetId", bookSetId);
        // 月初
        param.put("startDate", DateUtil.getFirstDay(bookDate));
        // 月末
        param.put("endDate", bookDate);
        // 下月初
        param.put("nextMonthDate", DateUtil.addDay(bookDate,1));
        // 理财产品
        param.put("finprodType", DictCodeListExt1.金融产品类型_理财产品.getValue());
        // 入账日
        param.put("bookDate", bookDate);
        // 交易方向
        List<String> psList = Arrays.asList(DictCodeList.交易方向_产品到期.getValue(),
                DictCodeList.交易方向_提前终止.getValue());
        param.put("ps", psList);
        // 查询报表生成配置表
        List<Map<String, Object>> mapList = productReportProcessorMapper.selectRepBatchConfig(list);
        for (Map<String, Object> map : mapList) {
            switch ((String) map.get("REP_CODE")) {
                case "REP_PROD_STATUS":
                    isRepProdStatus = (String) map.get("IS_RUN");
                    break;
                case "REP_PROD_MESSAGE":
                    isRepProdMessage = (String) map.get("IS_RUN");
                    break;
                default:
                    logger.info("理财报表生成批处理:报表生成配置表 未配置");
            }
        }
        // 生成理财产品状态表
        if (DictCodeList.是否_是.getValue().equals(isRepProdStatus)) {
            repProdStatusDto = findRepProdStatusDto(param);
        }
        // 生成理财产品信息表
        if (DictCodeList.是否_是.getValue().equals(isRepProdMessage)) {
            repProdMessageDto = findRepProdMessageDto(param);
        }
        // 理财产品状态表存入返回集
        result.setRepProdStatusDto(repProdStatusDto);
        // 理财产品信息表存入返回集
        result.setRepProdMessageDto(repProdMessageDto);
        // 返回值
        return result;
    }

    /**
     * 获取 理财产品状态
     *
     * @param param bookSetId 账套 bookDate  入账日
     * @return 理财产品状态
     */
    private RepProdStatusDto findRepProdStatusDto(Map<String, Object> param) {
        // 交易方向
        List<String> ps = Arrays.asList(DictCodeList.交易方向_产品到期.getValue(),
                DictCodeList.交易方向_提前终止.getValue());
        param.put("ps", ps);
        // 查询理财产品状态
        List<RepProdStatusDto> repProdStatusDtoList = productReportProcessorMapper.selectRepProdStatusDto(param);
        // 结果为空,结束
        if (repProdStatusDtoList.isEmpty()) {
            return null;
        }
        RepProdStatusDto dto = repProdStatusDtoList.get(0);

        // 查询产品对应的资产二级分类为“非标准化债权类”资产的持仓市值
        // 变量.持仓市值
        BigDecimal bigDecimal = BigDecimal.ZERO;
        Date firstday = (Date) param.get("startDate");
        Date lastday = (Date) param.get("endDate");
        // 获取资产持仓
        List<ChbMonthAssetPosition> listAssetPosition = productReportProcessorMapper.selectAssetposition(param);
        // 获取穿透底层持仓
        List<ChbMonthAssetPosition> positionList = Lists.newArrayList();
        Map<String, BigDecimal> rateMap = new HashMap<>();
        for (ChbMonthAssetPosition p1 : listAssetPosition) {
            p1.setAssetvalueA(ChbCcyConvertUtil.changeCnyMoney(p1.getCcy(),
                    p1.getAssetvalueA(), lastday, rateMap));
            p1.setAssetvalueB(ChbCcyConvertUtil.changeCnyMoney(p1.getCcy(),
                    p1.getAssetvalueB(), lastday, rateMap));
            p1.setAssetvalueA(p1.getAssetvalueA().setScale(2, RoundingMode.HALF_UP));
            p1.setAssetvalueB(p1.getAssetvalueB().setScale(2, RoundingMode.HALF_UP));
            p1.setStartdate(firstday);
            if (p1.getChlId() != null && !"05".equals(p1.getTradeType())) {
                if ("01".equals(p1.getIsUnderDeal())) { //排除非首层交易
                    p1.setAssetvalueB(p1.getAssetvalueA());
                    p1.setAssetvalueA(null);
                }
            } else {
                if ("01".equals(p1.getTradeType())) {    //产品直投交易
                    p1.setAssetvalueB(p1.getAssetvalueA());
                    positionList.add(p1);
                } else if ("02".equals(p1.getTradeType())) { //产品买通道
                    positionList.add(p1);
                    //查询通道底层交易
                    ChbMonthAssetPosition p = new ChbMonthAssetPosition();
                    p.setChlId(p1.getAssetCode());
                    p.setProdId(p1.getProdId());
                    p.setBookdate(p1.getBookdate());
                    p.setEnddate(p1.getEnddate());

                    List<ChbMonthAssetPosition> listChlUnderDeal = this.genMonthChlPosition(p);
                    for (ChbMonthAssetPosition p11 : listChlUnderDeal) {
                        p11.setStartdate(firstday);
                        p11.setOldAssetCode(p1.getAssetCode());
                        p11.setAssetvalueB(p11.getAssetvalueB().setScale(2, RoundingMode.HALF_UP));
                        positionList.add(p11);
                    }
                } else if ("05".equals(p1.getTradeType())) { //产品购买母资管计划
                    ChbBotAstPosition p = new ChbBotAstPosition();
                    p.setPosDate(p1.getEnddate());
                    p.setPlanAssetId(p1.getAssetCode());
                    p.setAccuamt(p1.getAssetvalueB());
                    p1.setAssetvalueB(null);
                    if (StringUtil.equals(p1.getIsUnderDeal(), "02"))//不为底层才插A列值
                        positionList.add(p1);
                    //查询资管计划底层交易
                    //穿透后资产价值取值控制
                    List<ChbBotAstPosition> listPlanUnderDeal = this.genMonthPlanPosition(p);
                    for (ChbBotAstPosition p11 : listPlanUnderDeal) {
                        ChbMonthAssetPosition m = new ChbMonthAssetPosition();
                        m.setProdId(p1.getProdId());
                        m.setAssetCode(p11.getAssetCode());
                        m.setAssetvalueA(null);
                        m.setAssetvalueB(p11.getAccuamt());
                        m.setAssetvalueB(m.getAssetvalueB().setScale(2, RoundingMode.HALF_UP));
                        m.setStartdate(firstday);
                        m.setOldAssetCode(p1.getAssetCode());
                        positionList.add(m);
                    }
                }
            }
        }
        // 过滤非标准化债权类
        for (ChbMonthAssetPosition p : positionList) {
            if (!productReportProcessorMapper.findFinprodType(p.getAssetCode()).isEmpty()) {
                if (StringUtil.isNotEmpty(p.getAssetvalueA())) {
                    bigDecimal = ArithmeticUtil.doAdd(bigDecimal, p.getAssetvalueA());
                } else if (StringUtil.isNotEmpty(p.getAssetvalueB())) {
                    bigDecimal = ArithmeticUtil.doAdd(bigDecimal, p.getAssetvalueB());
                }
            }
        }
        // 变量.理财产品状态.非标债权资产持仓比例 = 变量.持仓市值/变量.理财产品状态.非标债权资产持仓比例
        dto.setNonStandBondRatio(ArithmeticUtil.divide(
                ArithmeticUtil.divide(bigDecimal, dto.getNonStandBondRatio(), 6), new BigDecimal(100)));

        if (StringUtil.isNotEmpty(dto.getCcyPurchase())) {
            String[] ccyPurchase = StringUtil.split(dto.getCcyPurchase(), GlobalConstant.COMMA);
            // 累计申购金额
            dto.setRepApplyCny(new BigDecimal(ccyPurchase[1]));
        }
        //累计兑付金额 产品成立至统计日累计赎回金额+现金分红金额+提前赎回金额+到期金额+提前终止金额
        dto.setRepCashCny(BigDecimal.ZERO);
        // 交易方向
        List<String> psList = Arrays.asList(DictCodeList.交易方向_产品赎回.getValue(),
                DictCodeList.交易方向_产品现金分红.getValue(), DictCodeList.交易方向_提前赎回.getValue(),
                DictCodeList.交易方向_产品到期.getValue(), DictCodeList.交易方向_提前终止.getValue());
        param.put("psList", psList);
        Map<String, Object> resultMap = productReportProcessorMapper.selectTradeAmt(param);
        if (resultMap != null && !resultMap.isEmpty()) {
            dto.setRepCashCny(StringUtil.isNotEmpty(resultMap.get("TRADE_AMT")) ? (BigDecimal) resultMap.get("TRADE_AMT") : BigDecimal.ZERO);
        }

        //累计兑付收益金额 产品成立至统计日累计赎回/到期/提前终止交易利息金额+现金分红金额
        dto.setRepCashProfitCny(BigDecimal.ZERO);
        psList = Arrays.asList(DictCodeList.交易方向_产品赎回.getValue(),
                DictCodeList.交易方向_产品到期.getValue(), DictCodeList.交易方向_提前终止.getValue(),
                DictCodeList.交易方向_产品现金分红.getValue());
        param.put("psList", psList);
        resultMap = productReportProcessorMapper.selectTradeAmt(param);
        if (resultMap != null && !resultMap.isEmpty()) {
            dto.setRepCashProfitCny(StringUtil.isNotEmpty(resultMap.get("TRADE_AMT")) ? (BigDecimal) resultMap.get("TRADE_AMT") : BigDecimal.ZERO);
        }

        // 定义 变量.产品统计日状态 存续/终止(1/0)
        String prodStatus = "1";
        if (!productReportProcessorMapper.selectProdStatus(param).isEmpty()) {
            prodStatus = "0";
        }
        dto.setProdStatus(prodStatus);
        dto.setcDate((Date) param.get("bookDate"));

        return dto;
    }

    /**
     * 获取 理财产品信息
     *
     * @param param bookSetId 账套  bookDate  入账日
     * @return 理财产品信息
     */
    private RepProdMessageDto findRepProdMessageDto(Map<String, Object> param) {
        // 查询理财产品信息
        List<RepProdMessageDto> repProdMessageDtoList = productReportProcessorMapper.selectRepProdMessageDto(param);
        // 结果为空,结束
        if (repProdMessageDtoList.isEmpty()) {
            return null;
        }
        // 返回结果
        RepProdMessageDto repProdMessageDto = repProdMessageDtoList.get(0);
        repProdMessageDto.setcDate((Date) param.get("bookDate"));
        repProdMessageDto.setIsNew(DictCodeList.是否_是.getValue());

        return repProdMessageDto;
    }


    private List<ChbBotAstPosition> genMonthPlanPosition(ChbBotAstPosition pos) {
        List<ChbBotAstPosition> list = new ArrayList<ChbBotAstPosition>();
        //查询底层持仓
        List<ChbBotAstPosition> listUnderDeal = productReportProcessorMapper.selectPlanDeal(pos);
        for (ChbBotAstPosition p11 : listUnderDeal) {
            if ("02".equals(p11.getIsfinaplan())) { //不是资管计划
                //计算穿透资产价值
                p11.setAccuamt(p11.getAccuamt().divide(p11.getTotalNum(), 18, RoundingMode.HALF_UP).multiply(p11.getPosAmt()));

                p11.setAccuamt(p11.getAccuamt().setScale(6, RoundingMode.HALF_UP));
                list.add(p11);
            } else {
                p11.setAccuamt(p11.getAccuamt().multiply(p11.getPosNum().divide(p11.getTotalNum(), 18, RoundingMode.HALF_UP)));
                p11.setPlanAssetId(p11.getAssetCode());
                list.addAll(genMonthPlanPosition(p11));
            }
        }
        return list;
    }

    private List<ChbMonthAssetPosition> genMonthChlPosition(ChbMonthAssetPosition pos) {
        List<ChbMonthAssetPosition> list = new ArrayList<>();
        Map<String, BigDecimal> rateMap = new HashMap<>();
        // 获取是否中债子系统(01：是 02：否)
        List<ChbMonthAssetPosition> listUnderDeal = productReportProcessorMapper.selectChlDealByCommon(pos);
        for (ChbMonthAssetPosition p11 : listUnderDeal) {
            p11.setPrincipalRatingDimension(pos.getPrincipalRatingDimension());
            if ("01".equals(p11.getIsUnderDeal())) { //底层交易
                p11.setAssetvalueA(null);
                //计算穿透资产价值
                p11.setAssetvalueB(ChbCcyConvertUtil.changeCnyMoney(p11.getCcy(),
                        p11.getAssetvalueB(), pos.getEnddate(), rateMap));
                list.add(p11);
            } else {
                list.addAll(genMonthChlPosition(p11));
            }
        }
        return list;
    }
}
