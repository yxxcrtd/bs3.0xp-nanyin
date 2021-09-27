/*
 * 文件名：EtrIbBondinfo002Service.java
 */

package com.joyintech.tams.invest.bankuplink.bond.service;


import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.PtlPortfolioMapper;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.bankuplink.bond.dto.BondQuoteInfoDto;
import com.joyintech.tams.invest.bankuplink.bond.dto.CfetsBondDealDialogueQuoteDto;
import com.joyintech.tams.invest.bankuplink.cstp.dto.CstpSelectDto;
import com.joyintech.tams.invest.bankuplink.reponew.dto.EtrFinProductCommonDto;
import com.joyintech.tams.invest.bankuplink.reponew.service.CfetsRepoDealQuotedPr001Service;
import com.joyintech.tams.invest.common.component.base.group.BasePtlSecPositionInfoService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.exportpdf.service.CommonExportPdfService;
import com.joyintech.tams.invest.common.trade.dto.EtrSecPositionDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 债券投资委托画面端service 〈功能详细描述〉
 */
@Service
public class CfetsBondDealQuotedPriceSingleService extends CommonExportService<BondQuoteInfoDto> implements BaseProcessService<CfetsBondDealDialogueQuoteDto> {


    @Autowired
    private BasePtlSecPositionInfoService basePtlSecPositionInfoService;

    @Autowired
    private PtlPortfolioMapper ptlPortfolioMapper;

    @Autowired
    private CfetsRepoDealQuotedPr001Service cfetsRepoDealQuotedPr001Service;

    @Autowired
    private CommonExportPdfService commonExportPdfService;

    @Override
    public void doComplete(CfetsBondDealDialogueQuoteDto cfetsBondDealDialogueQuoteDto, OperationType operationType) {

    }

    /**
     * 获取新建界面的资产代码联想下拉框框
     *
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getfinprodIdForAdd(Map<String, Object> param) {
        return restTemplate.postForPage(ServiceApplication.BANKUPLINK_SERVICE_NAME
                        + "/CfetsBondDealQuotedPrice/getfinprodIdForAdd"
                , param,
                SelectOptionDto.class);
    }


    /**
     * 组合现金信息
     * @param paramMap
     * @return
     */
    public EtrFinProductCommonDto calCanBuyAmount(Map<String, Object> paramMap) {
        EtrFinProductCommonDto dto = new EtrFinProductCommonDto();
        //银行间日历
        if(!StringUtil.isNotEmpty(paramMap.get("calId"))){
            paramMap.put("calId", "03");
        }

        // 获取平台日
        Date tradeDate = DateUtil.platDateFromCache();
        paramMap.put("sysDate",DateUtil.format(tradeDate));

        if(!StringUtil.isNotEmpty(paramMap.get("finprodId"))){
            /**
             * 当单选组合时，使用组合的币种
             */
            PtlPortfolio ptlPortfolio = ptlPortfolioMapper.selectByPrimaryKey(paramMap.get("portfolioId") + "");
            if(ptlPortfolio != null && StringUtil.isNotEmpty(ptlPortfolio.getCcy())){
                paramMap.put("ccy", ptlPortfolio.getCcy());
            }
        }
        // 可用现金
        Map<String, Object> cash = cfetsRepoDealQuotedPr001Service.getProAvailableCash(paramMap);
        // 组合T日可用现金
        BigDecimal proAvailableCash = cash.get("cashT")==null?BigDecimal.ZERO: (BigDecimal)cash.get("cashT");
        // 净价
        BigDecimal cprice = !StringUtil.isNotEmpty(paramMap.get("price"))?new BigDecimal(100): new BigDecimal(paramMap.get("price").toString());

        // 可买数量
        BigDecimal canBuyAmount = BigDecimal.ZERO;
        if(StringUtil.isNotEmpty(paramMap.get("price"))&&cprice!=null&&BigDecimal.ZERO.compareTo(cprice)!=0){
            canBuyAmount = ArithmeticUtil.divide(proAvailableCash,cprice,2, RoundingMode.HALF_UP);
        }

        dto.setCanBuyAmount(canBuyAmount);
        return dto;
    }


    /**
     * 银行间债券持仓信息
     * @param paramMap
     * @return
     */
    public EtrFinProductCommonDto findSecAmountInfo(Map<String, Object> paramMap) {
        EtrFinProductCommonDto dto = new EtrFinProductCommonDto();
        //银行间日历
        if(!StringUtil.isNotEmpty(paramMap.get("calId"))){
            paramMap.put("calId", "03");
        }

        // 获取平台日
        Date tradeDate = DateUtil.platDateFromCache();
        paramMap.put("sysDate",DateUtil.format(tradeDate));
        Map<String, Object> result = new HashMap<>();

        if(!StringUtil.isNotEmpty(paramMap.get("finprodId"))){
            /**
             * 当单选组合时，使用组合的币种
             */
            PtlPortfolio ptlPortfolio = ptlPortfolioMapper.selectByPrimaryKey(paramMap.get("portfolioId") + "");
            if(ptlPortfolio != null && StringUtil.isNotEmpty(ptlPortfolio.getCcy())){
                paramMap.put("ccy", ptlPortfolio.getCcy());
            }
        }
        // 可用现金
        Map<String, Object> cash = cfetsRepoDealQuotedPr001Service.getProAvailableCash(paramMap);
        // 组合T日可用现金
        BigDecimal proAvailableCash = cash.get("cashT")==null?BigDecimal.ZERO: (BigDecimal)cash.get("cashT");
        // 净价
        BigDecimal cprice = paramMap.get("cprice")==null?new BigDecimal(100): (BigDecimal)cash.get("cprice");

        // 可买数量
        BigDecimal canBuyAmount = null;
        if(cprice!=null&&BigDecimal.ZERO.compareTo(cprice)!=0){
            canBuyAmount = ArithmeticUtil.divide(proAvailableCash,cprice,2,RoundingMode.HALF_UP);
        }

        // 成本价
        BigDecimal costPrice = this.getCostPrice(paramMap);

        if (StringUtil.isNotEmpty((String)paramMap.get("finprodId"))) {
            // 资产可用券面总额
            PtlSecPositionFlowDto ptlSecPositionFlowDto= new PtlSecPositionFlowDto();
            ptlSecPositionFlowDto.setCdate(tradeDate);
            ptlSecPositionFlowDto.setFinprodId((String)paramMap.get("finprodId"));
            ptlSecPositionFlowDto.setPortfolioId((String)paramMap.get("portfolioId"));

            ptlSecPositionFlowDto.setCcy(DictCodeList.币种_人民币_CNY.getValue());

            ptlSecPositionFlowDto.setHodingType(DictCodeList.持仓类型_可用.getValue());
            result.put("canSellAmount", cfetsRepoDealQuotedPr001Service.getAssetAvailablePosition(ptlSecPositionFlowDto));
            // 资产持仓券面总额
//            PtlSecPositionFlowDto ptlSecPositionFlowDto = new PtlSecPositionFlowDto();
            ptlSecPositionFlowDto.setCdate(tradeDate);
            ptlSecPositionFlowDto.setPortfolioId((String)paramMap.get("portfolioId"));

            ptlSecPositionFlowDto.setCcy(DictCodeList.币种_人民币_CNY.getValue());
            ptlSecPositionFlowDto.setHodingType(DictCodeList.持仓类型_实际.getValue());
            ptlSecPositionFlowDto.setFinprodId((String)paramMap.get("finprodId"));
            ptlSecPositionFlowDto.setEntrust(true);
            BigDecimal amount03 = basePtlSecPositionInfoService.queryOtherSecPosition(ptlSecPositionFlowDto);
            ptlSecPositionFlowDto.setHodingType(DictCodeList.持仓类型_融入.getValue());
            BigDecimal amount05 = basePtlSecPositionInfoService.queryOtherSecPosition(ptlSecPositionFlowDto);
            ptlSecPositionFlowDto.setHodingType(DictCodeList.持仓类型_融出.getValue());
            BigDecimal amount06 = basePtlSecPositionInfoService.queryOtherSecPosition(ptlSecPositionFlowDto);
            BigDecimal assetPositionEntrustAmt = ArithmeticUtil.doAdd(amount03, amount05);
            assetPositionEntrustAmt = ArithmeticUtil.subtract(assetPositionEntrustAmt, amount06);
            result.put("positionAmount", assetPositionEntrustAmt);
        } else {
            result.put("canSellAmount", BigDecimal.ZERO);
            result.put("positionAmount", BigDecimal.ZERO);
        }

        dto.setCanBuyAmount(canBuyAmount);
        dto.setCostPrice(costPrice);
        dto.setPositionAmount(result.get("positionAmount") == null ? BigDecimal.ZERO : (BigDecimal) result.get("positionAmount"));
        dto.setCanSellAmount(result.get("canSellAmount") == null ? BigDecimal.ZERO : (BigDecimal) result.get("canSellAmount"));
        dto.setCashAmount(proAvailableCash);
        return dto;
    }

    /**
     * 根据资产代码 组合代码 日期 获取债券成本价
     * @param paramMap
     * @return
     */
    public  BigDecimal getCostPrice(Map<String, Object> paramMap){
        return restTemplate.postForObject(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIbBondinfo001/getCostPrice", paramMap,
                BigDecimal.class);
    }

    public ResponseEntity<byte[]> exportPdf(String pageId, Map param){
        List<CstpSelectDto> datas = selectExportPdf(param);
        if(!StringUtil.isEmptyList(datas)){
            //汇总交易金额
            CstpSelectDto dto = new CstpSelectDto();
            dto.setPortfolioId("合计");
            BigDecimal dirtyprice = datas.stream().map(CstpSelectDto::getDirtyprice).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal lastqty = datas.stream().map(CstpSelectDto::getLastqty).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal settlcurramt2 = datas.stream().map(CstpSelectDto::getSettlcurramt2).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal accruedinteresttotalamt = datas.stream().map(CstpSelectDto::getAccruedinteresttotalamt).reduce(BigDecimal.ZERO, BigDecimal::add);
            dto.setDirtyprice(dirtyprice);
            dto.setLastqty(lastqty);
            dto.setSettlcurramt2(settlcurramt2);
            dto.setAccruedinteresttotalamt(accruedinteresttotalamt);
            datas.add(dto);
        }
        return commonExportPdfService.exportPdf(pageId,getConfigFile(pageId),datas);
    }

    /**
     * 导出pdf数据 add by zengchengfa 20210803
     * @param param
     * @return
     */
    public List<CstpSelectDto> selectExportPdf(Map param){
        return restTemplate.postForList(ServiceApplication.BANKUPLINK_SERVICE_NAME
                        + "/CfetsBondDealQuotedPrice/selectExportPdf",
                param,CstpSelectDto.class);
    }

    /**
     * 获取配置文件绝对路径 add by zengchengfa 20210803
     * @return
     */
    public String getConfigFile(String pageId){
        String basePath = "com/joyintech/tams/invest/bankuplink/bond/conf/export_conf_pdf.xml";
        String tempPath = CacheUtil.getSysParam("access_exportfilepath");
        String tempConfigFilePath= tempPath + "/" + pageId + '_' + "export_conf_pdf.xml";
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(basePath);
        // 在临时目录下生成配置文件
        commonExportPdfService.createTempConfigFile(in, tempPath, tempConfigFilePath);
        return tempConfigFilePath;
    }
}
