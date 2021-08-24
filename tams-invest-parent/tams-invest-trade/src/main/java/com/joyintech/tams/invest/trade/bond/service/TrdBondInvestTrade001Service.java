/*
 * 文件名：TrdBondInvestTrade001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Zane.Y
 * 修改时间：2018年9月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.bond.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.invest.common.exportpdf.service.CommonExportPdfService;
import com.joyintech.tams.invest.trade.bond.dto.TrdBondInvestTradePdfDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.trade.bond.dto.TrdBondInvestTradeDto;

@Service
public class TrdBondInvestTrade001Service extends CommonExportService<TrdBondInvestTradeDto> {

    /**
     * add by zengchengfa 20210803导出pdf共通
     */
    @Autowired
    private CommonExportPdfService commonExportPdfService;

    /**
     * 检索债券信息
     *
     * @param pageParam 查询参数
     * @return
     * @see
     * @since 1.0
     */
    public Page<TrdBondInvestTradeDto> findPage(Map<String, Object> pageParam) {
        // 多选下拉框值处理
        // 组合代码
        Object portfolioIdObj = pageParam.get("portfolioId");
        if (portfolioIdObj != null) {
            pageParam.put("portfolioIdList",
                StringUtil.splitToList(GlobalConstant.COMMA, (String)portfolioIdObj));
        }
        else {
            pageParam.put("portfolioIdList", new ArrayList<>());
        }

        //多选下拉框处理（合作资产）
        Object chlFinprodIdObj = pageParam.get("chlFinprodId");
        if (chlFinprodIdObj != null){
            pageParam.put("chlFinprodIdList",
                    StringUtil.splitToList(GlobalConstant.COMMA,(String) chlFinprodIdObj));
        }else {
            pageParam.put("chlFinprodIdList",new ArrayList<>());
        }

        pageParam.put("loginUserName", LoginUserUtil.getUserName());
        //配置单位 edit by yeming 20210802 start
        Page<TrdBondInvestTradeDto> page = restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME + "/TrdBondInvestTrade/findPage",
                pageParam,TrdBondInvestTradeDto.class);
        for(TrdBondInvestTradeDto dto:page.getRoot()){
            if(dto.getTradeAmt() != null) {
                dto.setTradeAmt(dto.getTradeAmt().divide(new BigDecimal(10000), 4, RoundingMode.HALF_UP));//转成万元
            }
            if(dto.getOwnTradeAmt() != null) {
                dto.setOwnTradeAmt(dto.getOwnTradeAmt().divide(new BigDecimal(10000), 4, RoundingMode.HALF_UP));//转成万元
            }
            if(dto.getIntegrateTradeAmt() != null) {
                dto.setIntegrateTradeAmt(dto.getIntegrateTradeAmt().divide(new BigDecimal(10000), 4, RoundingMode.HALF_UP));//转成万元
            }
        }
        return page;
        //配置单位 edit by yeming 20210802 end
//        return restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME + "/TrdBondInvestTrade/findPage",
//            pageParam,TrdBondInvestTradeDto.class);
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/trade/bond/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<TrdBondInvestTradeDto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }

    /**
     * 获取交易代码
     * @param map 请求参数
     * @return 联想文本框实体类
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getEntrustIdByType(Map<String,Object> map){
        return restTemplate.postForList(
                ServiceApplication.COMMON_SERVICE_NAME + "/CommonSelect/getEntrustId", map,
                SuggestDto.class);
    }

    /**
     * 导出pdf数据 add by zengchengfa 20210803
     * @param param
     * @return
     */
    public List<TrdBondInvestTradePdfDto> selectExportPdf(Map param){
        return restTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME + "/TrdBondInvestTrade/selectExportPdf",
                param,TrdBondInvestTradePdfDto.class);
    }

    /**
     * 导出pdf add by zengchengfa 20210803
     * @param pageId 画面id
     * @param param 参数
     * @return
     */
    public ResponseEntity<byte[]> exportPdf(String pageId, Map param){
        List<TrdBondInvestTradePdfDto> datas = selectExportPdf(param);
        if(!StringUtil.isEmptyList(datas)){
            //汇总交易金额
            TrdBondInvestTradePdfDto dto = new TrdBondInvestTradePdfDto();
            dto.setPortfolioId("合计");
            if(pageId.equalsIgnoreCase("TrdBondInvestTradeEx001")){
                //场内交易
                BigDecimal entrustAmtSum = datas.stream().map(TrdBondInvestTradePdfDto::getEntrustAmt).reduce(BigDecimal.ZERO, BigDecimal::add);
                BigDecimal entrustNumSum = datas.stream().map(TrdBondInvestTradePdfDto::getEntrustNum).reduce(BigDecimal.ZERO, BigDecimal::add);
                BigDecimal shareAmtSum = datas.stream().map(TrdBondInvestTradePdfDto::getShareAmt).reduce(BigDecimal.ZERO, BigDecimal::add);
                BigDecimal tradeAmtSum = datas.stream().map(TrdBondInvestTradePdfDto::getTradeAmt).reduce(BigDecimal.ZERO, BigDecimal::add);
                dto.setEntrustNum(entrustNumSum);
                dto.setEntrustAmt(entrustAmtSum);
                dto.setShareAmt(shareAmtSum);
                dto.setTradeAmt(tradeAmtSum);
            }else{
                BigDecimal fpriceAmt = datas.stream().map(TrdBondInvestTradePdfDto::getFpriceAmt).reduce(BigDecimal.ZERO, BigDecimal::add);
                BigDecimal shareAmt = datas.stream().map(TrdBondInvestTradePdfDto::getShareAmt).reduce(BigDecimal.ZERO, BigDecimal::add);
                BigDecimal tradeAmt = datas.stream().map(TrdBondInvestTradePdfDto::getTradeAmt).reduce(BigDecimal.ZERO, BigDecimal::add);
                BigDecimal intAmt = datas.stream().map(TrdBondInvestTradePdfDto::getIntAmt).reduce(BigDecimal.ZERO, BigDecimal::add);
                dto.setFpriceAmt(fpriceAmt);
                dto.setShareAmt(shareAmt);
                dto.setTradeAmt(tradeAmt);
                dto.setIntAmt(intAmt);
            }
            datas.add(dto);
        }
        return commonExportPdfService.exportPdf(pageId,getConfigFile(pageId),datas);
    }

    /**
     * 获取配置文件绝对路径 add by zengchengfa 20210803
     * @return
     */
    public String getConfigFile(String pageId){
        String basePath = this.getClass().getClassLoader().getResource("com/joyintech/tams/invest/trade/bond/conf/").toString();
        if(pageId.equalsIgnoreCase("TrdBondInvestTradeEx001")){
            //场内
            return basePath + "export_conf_pdf002.xml";
        }else{
            return basePath + "export_conf_pdf001.xml";
        }
    }
}
