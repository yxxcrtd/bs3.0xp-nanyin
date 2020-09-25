/*
 * 文件名：EtrMoneyCalculationService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：可投金额试算service
 * 修改人：nixiukuan
 * 修改时间：2019年9月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrmoneycalculation.service;


import com.jixu.common.util.JsonUtil;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.component.business.common.controller.BaseRiskMathCommController;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.riskspeed.RiskSpeedService;
import com.joyintech.tams.invest.common.riskspeed.dto.IRiskSpeed;
import com.joyintech.tams.invest.common.riskspeed.dto.RiskSpeedDto;
import com.joyintech.tams.invest.common.riskspeed.impl.RiskSpeedImplService;
import com.joyintech.tams.invest.common.risktrial.dto.InvestTradeTrialInput;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.entrust.etrmoneycalculation.dto.EtrMoneyCalculationDto;
import com.joyintech.tams.invest.entrust.etrmoneycalculation.dto.IRiskSpeedDto;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;

/**
 * 〈可投金额试算service〉
 * 〈功能详细描述〉
 * @author nixiukuan
 * @version 1.0 2019年9月26日
 * @see EtrMoneyCalculationService
 * @since 1.0
 */
@Service
public class EtrMoneyCalculationService extends CommonExportService<EtrMoneyCalculationDto>  {

/*    @Autowired
    private BaseRiskMathCommController riskMathCommController;*/
    @Autowired
    private RiskSpeedImplService riskSpeedImplService;

    /**
     * 导出excel
     */
	@Override
    protected void buildExportParam(ExportParam exportParam) {

        exportParam.dataSource((String) exportParam.getSelectParam().get("tablelist"));
        exportParam.config("/com/joyintech/tams/invest/entrust/etrmoneycalculation/conf/export_conf.xml")
            .dataSource(DataSourceType.MASERT);
    }
	

    
    /**
     * 设置单元格值
     * {@inheritDoc}
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(StringUtil.equals(propName,"transactioncom")){
            cell.setCellValue(value+"%");
        }
    }
    @Override
    public Page<EtrMoneyCalculationDto> selectPageData(Map<String, Object> selectParam) {
        return calculation(selectParam);
    }
    /**
     * 计算

     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<EtrMoneyCalculationDto> calculation(Map<String,Object>param) {
        List<EtrMoneyCalculationDto> etrMoneyCalculationDtoList=new ArrayList<>();
        String portfolioId = (String)param.get("portfolioId");//组合代码
        String finprodType = (String)param.get("finprodType");//资产类型
        String finprodId = (String)param.get("finprodId");//资产代码
        String platDate=(String)param.get("platDate");//平台日期
        //风险指标试算
        RiskSpeedDto iRiskSpeedDto=new RiskSpeedDto();
        iRiskSpeedDto.setPortfolioId(portfolioId);
        iRiskSpeedDto.setAssetType(finprodType);
        iRiskSpeedDto.setAssetCode(finprodId.contains(finprodType)?finprodId:finprodType+"_"+finprodId);
        iRiskSpeedDto.setValDate(platDate);
        List<RiskSpeedDto> canCastAmtList = riskSpeedImplService.getCanCastAmt(iRiskSpeedDto);
        if(!StringUtil.isEmptyList(canCastAmtList))
        {
            canCastAmtList.forEach(item-> {
                EtrMoneyCalculationDto dto = new EtrMoneyCalculationDto();
                dto.setTargetName(item.getIndName());
                dto.setPortfoliopackage(item.getPackageName());
                //限制投资期限类、投资范围类  满足条件 可投总金额为Max
                if(StringUtil.isNotEmpty(item.getIndType()) && (item.getIndType().equals(DictCodeListExt1.指标类型_限制投资_期限类.getValue())||item.getIndType().equals(DictCodeListExt1.指标类型_禁止投资_资产类型.getValue())))
                {
                    dto.setSumMoney("Max");//可投总金额
                    dto.setUseMoney("-");
                    dto.setSurplusMoney("Max");
                    convertModel(dto, param);
                    etrMoneyCalculationDtoList.add(dto);
                }

                if(StringUtil.isNotEmpty(item.getTotalAmt()) &&StringUtil.isNotEmpty(item.getHasAmt())&&(item.getIndType().equals(DictCodeListExt1.指标类型_限制投资_金额类.getValue())||item.getIndType().equals(DictCodeListExt1.指标类型_限制投资_比例类.getValue()))) {
                    dto.setSumMoney(item.getTotalAmt().toString().contains(".")?item.getTotalAmt().toString():item.getTotalAmt().toString()+".00");//可投总金额
                    dto.setUseMoney(item.getHasAmt().toString().contains(".")?item.getHasAmt().toString():item.getHasAmt().toString()+".00");
                    dto.setSurplusMoney(String.valueOf (new BigDecimal(dto.getSumMoney()).subtract(new BigDecimal(dto.getUseMoney()))));

                    convertModel(dto, param);
                    etrMoneyCalculationDtoList.add(dto);
                }
            });
        }
       /* List<RiskTrialRetDto> list=new ArrayList<>();
        list=getRiskList(portfolioId,finprodType,finprodId);
        if(!StringUtil.isEmptyList(list))
        {
           list.forEach(item->{
               EtrMoneyCalculationDto dto=new EtrMoneyCalculationDto();
               dto.setTargetName("风险限额指标");
               dto.setPortfoliopackage(item.getIndName());
               //从违规json中获取最大的限制金额threshold_1
               Map<String, Object> stringObjectMap = JsonUtil.stringToMap(item.getFoulCond());
               Map<String, Object> threshold_1Map= (Map<String, Object>) stringObjectMap.get("threshold_1");
               BigDecimal money=new BigDecimal(threshold_1Map.get("value").toString());// 金额或者百分比的值
               String op=threshold_1Map.get("op").toString();//比较符
               boolean addFlag=false;// 当前值是否可加入列表进行返回
               if(item.getIndType().equals(DictCodeListExt1.指标类型_限制投资_金额类.getValue()) &&op.contains(">"))
               {
                   String unit=(String) threshold_1Map.get("unit");
                   if(unit.equals(DictCodeListExt1.单位_元.getValue()))
                   {
                       dto.setSumMoney(money);//可投总金额
                   }
                   else if(unit.equals(DictCodeListExt1.单位_万元.getValue()))
                   {
                       dto.setSumMoney(money.multiply(new BigDecimal(10000)));//可投总金额
                   }
                   dto.setUseMoney(new BigDecimal(0));
                   dto.setSurplusMoney(dto.getSumMoney());
                   addFlag=true;
               }
               else if(item.getIndType().equals(DictCodeListExt1.指标类型_限制投资_比例类.getValue())&&op.contains(">"))
               {
                   if(StringUtil.isNotEmpty(item.getInvestedAmountExpr()) && item.getInvestedAmountExpr().contains("/"))
                   {
                       dto.setSumMoney(money.multiply( new BigDecimal(item.getInvestedAmountExpr().split("/")[1])));//可投总金额   (表达式中分母部分 * 百分比)
                       dto.setUseMoney(new BigDecimal(item.getInvestedAmountExpr().split("/")[0]));//已使用去表达式中分子部分
                       dto.setSurplusMoney(dto.getSumMoney().subtract(dto.getUseMoney()));
                       addFlag=true;
                   }

               }
               else if(item.getIndType().equals(DictCodeListExt1.指标类型_限制投资_期限类.getValue()))
               {
                   dto.setSumMoney(new BigDecimal("999999999999"));//可投总金额
                   dto.setUseMoney(new BigDecimal(0));
                   dto.setSurplusMoney(dto.getSumMoney());
                   addFlag=true;
               }
               else if(item.getIndType().equals(DictCodeListExt1.指标类型_禁止投资_资产类型.getValue()))
               {
                   dto.setSumMoney(new BigDecimal(0));//可投总金额
                   dto.setUseMoney(new BigDecimal(0));
                   dto.setSurplusMoney(dto.getSumMoney());
                   addFlag=true;
               }
               if(addFlag) {
                   dto.setPortfolioId(portfolioId);
                   dto.setFinprodId(finprodId);
                   convertModel(dto, param);
                   etrMoneyCalculationDtoList.add(dto);
               }
           });
        }*/



        //风险指标试算end
        Page<EtrMoneyCalculationDto> etrMoneyCalculationDtoPage = restTemplate.postForPage(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrMoneyCalculation/calculation", param,
                EtrMoneyCalculationDto.class);
        etrMoneyCalculationDtoPage.getRoot().forEach(item->
                {
                    convertModel(item,param);
                }
        );
        etrMoneyCalculationDtoList.addAll( etrMoneyCalculationDtoPage.getRoot());
        etrMoneyCalculationDtoPage.setRoot(etrMoneyCalculationDtoList);
        return  etrMoneyCalculationDtoPage;
    }

    //获取 风险指标试算
    public  List<RiskTrialRetDto> getRiskList(String portfolioId , String finprodType , String finprodId)
    {
        List<RiskTrialRetDto> riskTrialRetDtoList=new ArrayList<>();
        InvestTradeTrialInput param=new InvestTradeTrialInput();
        param.setAssetCode(finprodId);
        param.setAssetType(finprodType);
        //param.setAssetSecType("02");
        param.setValDate(DateUtil.getSysDate());
        param.setPortfolioCode(portfolioId);
        param.setTradeDirection(DictCodeList.交易方向_买入.getValue());
        param.setTimePoint(DictCodeListExt1.监督时分_交易监控.getValue());
       // return  riskMathCommController.rickMath(param);
       return  null;
    }

    private  EtrMoneyCalculationDto convertModel(EtrMoneyCalculationDto dto,Map<String,Object>param)
    {
        String portfolioId = (String)param.get("portfolioId");//组合名称
        String finprodType = (String)param.get("finprodType");//资产代码
        String finprodId = (String)param.get("finprodId");//资产类型
        String bondOneType =(String)param.get("bondOneType");  //债券一级分类
        String bondTwoType =(String)param.get("bondTwoType");  //债券二级分类
        String publisher =(String)param.get("publisher");  //发行人
        String bondRating =(String)param.get("bondRating");  //债项评级
        String bondSubjectRating =(String)param.get("bondSubjectRating");  //主体评级
        String fundType =(String)param.get("fundType");  //基金类型
        String fundTwoType =(String)param.get("fundTwoType");  //基金二级类型
        String manager =(String)param.get("manager");  //管理人
        String tradingPlace =(String)param.get("tradingPlace");  //交易场所
        String board =(String)param.get("board");  //上市板
        String repIndustry =(String)param.get("repIndustry");  //行业
        String haveMoney =(String)param.get("haveMoney");  //可投金额
        if(finprodType.equals(DictCodeListExt1.金融产品类型_投管用_债券.getValue())) {
            dto.setFinprodType(DictCodeListExt1.金融产品类型_投管用_债券.getCodeName());
        }
        else if(finprodType.equals(DictCodeListExt1.金融产品类型_投管用_基金.getValue()))
        {
            dto.setFinprodType(DictCodeListExt1.金融产品类型_投管用_基金.getCodeName());
        }
        else if(finprodType.equals(DictCodeListExt1.金融产品类型_投管用_股票.getValue()))
        {
            dto.setFinprodType(DictCodeListExt1.金融产品类型_投管用_股票.getCodeName());
        }
        dto.setPortfolioId(portfolioId);
        dto.setFinprodId(finprodId);
        dto.setBondOneType(bondOneType);
        dto.setBondTwoType(bondTwoType);
        dto.setPublisher(publisher);
        dto.setBondRating(bondRating);
        dto.setBondSubjectRating(bondSubjectRating);
        dto.setFundType(fundType);
        dto.setFundTwoType(fundTwoType);
        dto.setManager(manager);
        dto.setTradingPlace(tradingPlace);
        dto.setBoard(board);
        dto.setRepIndustry(repIndustry);
        dto.setHaveMoney(haveMoney);
        return dto;
    }
    /**
     *
     * 可投金额试算-> 选择资产代码后查询出相关属性项内容
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public EtrMoneyCalculationDto getFinprodMsg(String finprodId,String finprodType) {

        EtrMoneyCalculationDto etrMoneyCalculationDto=new EtrMoneyCalculationDto();
        if(StringUtil.isEmpty(finprodId))
        {
            return  etrMoneyCalculationDto;
        }
        ResponseEntity<EtrMoneyCalculationDto> etrMoneyCalculationDtoResponseEntity = restTemplate.postForEntity(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrMoneyCalculation/getFinprodMsg", finprodId,
                EtrMoneyCalculationDto.class);
         etrMoneyCalculationDto= etrMoneyCalculationDtoResponseEntity.getBody();
        if(etrMoneyCalculationDto==null)
        {
            return  null;
        }
        //债券一级分类
        if(StringUtil.isNotEmpty(etrMoneyCalculationDto.getBondOneType()) )
        {
            if(finprodType.equals(DictCodeListExt1.金融产品类型_投管用_债券.getValue())) {
                etrMoneyCalculationDto.setBondOneType(SysDataUtil.getDataDictItem(DictCode.行内资产二级分类.getValue(), etrMoneyCalculationDto.getBondOneType()).getItemValue());
            }
            else if(finprodType.equals(DictCodeListExt1.金融产品类型_投管用_基金.getValue()))
            {
                etrMoneyCalculationDto.setFundType(SysDataUtil.getDataDictItem(DictCode.行内资产二级分类.getValue(), etrMoneyCalculationDto.getBondOneType()).getItemValue());
            }
        }
        // 债券二级分类
        if(StringUtil.isNotEmpty(etrMoneyCalculationDto.getBondTwoType()))
        {
            if(finprodType.equals(DictCodeListExt1.金融产品类型_投管用_债券.getValue())) {
                etrMoneyCalculationDto.setBondTwoType(SysDataUtil.getDataDictItem(DictCode.行内资产三级分类.getValue(), etrMoneyCalculationDto.getBondTwoType()).getItemValue());
            }
            else if(finprodType.equals(DictCodeListExt1.金融产品类型_投管用_基金.getValue()))
            {
                etrMoneyCalculationDto.setFundTwoType(SysDataUtil.getDataDictItem(DictCode.行内资产三级分类.getValue(), etrMoneyCalculationDto.getBondTwoType()).getItemValue());
            }
        }

        //交易场所
        if(StringUtil.isNotEmpty(etrMoneyCalculationDto.getTradingPlace())) {
            etrMoneyCalculationDto.setTradingPlace(SysDataUtil.getDataDictItem(DictCode.交易场所.getValue(), etrMoneyCalculationDto.getTradingPlace()).getItemValue());
        }
        //上市版
        if(StringUtil.isNotEmpty(etrMoneyCalculationDto.getBoard())) {
            etrMoneyCalculationDto.setBoard(SysDataUtil.getDataDictItem(DictCode.上市板.getValue(), etrMoneyCalculationDto.getBoard()).getItemValue());
        }
        //行业
        if(StringUtil.isNotEmpty(etrMoneyCalculationDto.getRepIndustry())) {
            etrMoneyCalculationDto.setRepIndustry(SysDataUtil.getDataDictItem(DictCode.机构所属行业一级分类.getValue(), etrMoneyCalculationDto.getRepIndustry()).getItemValue());
        }

        return  etrMoneyCalculationDto ;
    }

    /**
     *
     * 可投金额试算-> 根据资产类型查询对应的资产代码
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getFinprodByType(String finprodType) {
        List<SelectOptionDto> selectOptionDtoList=new ArrayList<>();
        Map<String, Object> param=new HashMap<>();
        param.put("finprodType",finprodType);
        List<SuggestDto> suggestDtoList = restTemplate.postForList(
                ServiceApplication.COMMON_SERVICE_NAME + "/CommonSelect/getFinprodByType", param,
                SuggestDto.class);
        suggestDtoList.forEach(item->{
            SelectOptionDto selectOptionDto=new SelectOptionDto();
            selectOptionDto.setId(item.getCode());
            selectOptionDto.setText(item.getName());
            selectOptionDtoList.add(selectOptionDto);
        });
        return  selectOptionDtoList;
    }
}
