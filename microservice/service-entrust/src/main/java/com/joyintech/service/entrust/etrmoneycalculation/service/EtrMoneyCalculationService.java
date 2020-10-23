package com.joyintech.service.entrust.etrmoneycalculation.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.*;
import com.joyintech.service.entrust.etrmoneycalculation.dao.EtrMoneyCalculationMapper;
import com.joyintech.service.entrust.etrmoneycalculation.dto.EtrMoneyCalculationDto;
import com.joyintech.service.entrust.etrmoneycalculation.dto.RatingDto;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.risk.dto.RiskNameListDto;
import com.joyintech.tams.invest.common.risk.service.RiskNamListCommonValidatorService;
import com.joyintech.tams.invest.common.trade.CeLinesUpdate;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service("backEtrMoneyCalculationService")
public class EtrMoneyCalculationService  extends BaseService<EtrMoneyCalculationDto> {
    @Autowired
    private EtrMoneyCalculationMapper etrMoneyCalculationMapper;


    /**
     * 占用、释放授信额度共通处理 类
     * 风险试算时获取授信管理中授信使用条件使用
     * Add 20190827 by xkni
     */
    @Autowired(required = false)
    private CeLinesUpdate ceLinesUpdate;
    /**
     *
     * 可投金额试算-> 选择资产代码后查询出相关属性项内容
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public EtrMoneyCalculationDto getFinprodMsg(String finprodId) {
        EtrMoneyCalculationDto dto=new EtrMoneyCalculationDto();

        dto= etrMoneyCalculationMapper.getFinprodMsg(finprodId);
        //当前为债券时获取 债项评级和主体评级结果值， 规则：获取每项最新评级的结果
        List<RatingDto> ratingDtoList= etrMoneyCalculationMapper.getRatingList(finprodId);
        if(!StringUtil.isEmptyList(ratingDtoList))
        {
            //先分组再获取每项分组中评选日期最新的一项结果
            Map<String, List<RatingDto>> groupList = ratingDtoList.stream().collect(Collectors.groupingBy(RatingDto::getGradeType));
            if(groupList!=null &&groupList.size()>0)
            {
                for (Map.Entry<String, List<RatingDto>> list:groupList.entrySet())
                {

                    if(list.getValue().get(0).getGradeType().equals(DictCodeList.评级类型_主体评级.getValue())) {
                        dto.setBondSubjectRating(SysDataUtil.getDataDictItem(DictCode.评级结果.getValue(), list.getValue().stream().collect(Collectors.maxBy(Comparator.comparing(RatingDto::getGradeDate))).get().getGradeResult()).getItemValue());
                    }
                    else if(list.getValue().get(0).getGradeType().equals(DictCodeList.评级类型_债项评级.getValue()))
                    {
                        dto.setBondRating(SysDataUtil.getDataDictItem(DictCode.评级结果.getValue(), list.getValue().stream().collect(Collectors.maxBy(Comparator.comparing(RatingDto::getGradeDate))).get().getGradeResult()).getItemValue());
                    }
                }
            }
        }

        return dto;
    }


    public EtrMoneyCalculationDto getFinprodByFinprod(String finprodId)
    {
        return etrMoneyCalculationMapper.getFinprodByFinprod(finprodId);
    }

    /**
     *
     * 可投金额试算-> 计算
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public Page<EtrMoneyCalculationDto> selectForPage(int page, int length, Map<String, Object> param) {

        String portfolioId = (String)param.get("portfolioId");
        String finprodType = (String)param.get("finprodType");
        String finprodId = (String)param.get("finprodId");
        String platDate=(String)param.get("platDate");//平台日期
        List<EtrMoneyCalculationDto> retunlist=new ArrayList<>();

        // 2.根据相关条件获取授信列表数据
        EtrMoneyCalculationDto etrMoneyCalculationDto= getFinprodByFinprod(finprodId);
        List<CreditLines> cList=new ArrayList<>();
        if(etrMoneyCalculationDto!=null) {
            cList = getCreditLinesList(etrMoneyCalculationDto, finprodType,platDate);
            cList.forEach(item ->
            {
                EtrMoneyCalculationDto retunModel=new EtrMoneyCalculationDto();
                retunModel.setPortfoliopackage("-");
                if (item.getCreditMstId().equals(BussinessDictCodeList.授信配置代码_机构授信.getValue())) {
                    retunModel.setTargetName(BussinessDictCodeList.授信配置代码_机构授信.getCodeName());

                } else if (item.getCreditMstId().equals(BussinessDictCodeList.授信配置代码_系列授信.getValue())) {
                    retunModel.setTargetName("资产"+BussinessDictCodeList.授信配置代码_系列授信.getCodeName());
                } else if (item.getCreditMstId().equals(BussinessDictCodeList.授信配置代码_资产授信.getValue())) {
                    retunModel.setTargetName(BussinessDictCodeList.授信配置代码_资产授信.getCodeName());
                } else {
                    //品种授信 根据CreditMstId 获取对应名称
                    String  creditName= etrMoneyCalculationMapper.getCreditName(item.getCreditMstId());
                    retunModel.setTargetName("机构分项授信-"+creditName);
                }
                retunModel.setSumMoney(String.valueOf(item.getCreditAmt()).contains(".")?String.valueOf(item.getCreditAmt()):String.valueOf(item.getCreditAmt())+".00");// 可投总金额
                retunModel.setUseMoney(String.valueOf(item.getHapCreditAmt()).contains(".")?String.valueOf(item.getHapCreditAmt()):String.valueOf(item.getHapCreditAmt())+".00");//已投金额
                retunModel.setSurplusMoney(String.valueOf(item.getCreditAmt().subtract(item.getHapCreditAmt())).contains(".")?String.valueOf(item.getCreditAmt().subtract(item.getHapCreditAmt())):String.valueOf(item.getCreditAmt().subtract(item.getHapCreditAmt()))+".00");//剩余可投金额


                retunModel.setFinprodId(finprodId);
                retunModel.setFinprodType(finprodType);
                retunModel.setPortfolioId(portfolioId);
                retunlist.add(retunModel);
            });

            }
        if(cList == null || cList.size() < 1){
            EtrMoneyCalculationDto dto=new EtrMoneyCalculationDto();
            dto.setFinprodId(finprodId);
            dto.setFinprodType(finprodType);
            dto.setPortfolioId(portfolioId);
            dto.setTargetName("授信额度");
            dto.setPortfoliopackage("-");
            if("Y".equals(CacheUtil.getSysParam(BussinessConstant.ISNEEDCONTROLCREDIT))){
                dto.setSumMoney("0.00");// 可投总金额
                dto.setUseMoney("0.00");//已投金额
                dto.setSurplusMoney("0.00");//剩余可投金额

            }
            else
            {
                dto.setSumMoney("Max");// 可投总金额
                dto.setUseMoney("-");//已投金额
                dto.setSurplusMoney("Max");//剩余可投金额
            }
            retunlist.add(dto);
        }

        // 3.根据相关条件获取资产名单,机构名单
        RiskNamListCommonValidatorService riskNamListCommonValidatorService = SpringContextUtil.getBean(
                RiskNamListCommonValidatorService.class);
        List<RiskNameListDto> riskNamList = riskNamListCommonValidatorService.getNameListByFinprodId(finprodId.contains(finprodType)?finprodId:finprodType+"_"+finprodId,platDate);
        if(!StringUtil.isEmptyList(riskNamList))
        {
            riskNamList.forEach(item->{

                //如在名单中为MAX=999999999999，否则为0
                EtrMoneyCalculationDto retunModel=new EtrMoneyCalculationDto();
                retunModel.setPortfoliopackage("-");
                //名单主体类型 01：机构名单 02：资产名单
                if (!StringUtil.isEmpty(item.getListDetailType())&&(item.getListDetailType().equals(DictCodeListExt2.名单主体明细类型_托管人.getValue()) ||item.getListDetailType().equals(DictCodeListExt2.名单主体明细类型_发行人.getValue())||item.getListDetailType().equals(DictCodeListExt2.名单主体明细类型_管理人.getValue())||item.getListDetailType().equals(DictCodeListExt2.名单主体明细类型_融资人.getValue()))) {
                    retunModel.setTargetName("机构名单");
                    retunModel.setSumMoney("Max");// 可投总金额  Max

                } else if(!StringUtil.isEmpty(item.getListDetailType())&&item.getListDetailType().equals(DictCodeListExt2.名单主体明细类型_资产.getValue())){
                    retunModel.setTargetName("资产名单");
                    retunModel.setSumMoney("Max");// 可投总金额  Max
                }

               /* //名单类型 01：白名单 02：黑名单
                if (!StringUtil.isEmpty(item.getListType()) &&(!item.getListType().equals("01")||item.getListType().equals("02"))) {
                    retunModel.setSumMoney(new BigDecimal(0));// 可投总金额
                }
                else
                {
                    retunModel.setSumMoney(new BigDecimal("999999999999"));// 可投总金额  Max
                }*/

                retunModel.setUseMoney("-");//已投金额
                retunModel.setSurplusMoney(retunModel.getSumMoney());//剩余可投金额=可投总金额

                retunModel.setFinprodId(finprodId);
                retunModel.setFinprodType(finprodType);
                retunModel.setPortfolioId(portfolioId);
                //只有在资产名单或机构名单中才加入可投金额试算列表，否则不存入列表

                if(retunModel.getTargetName().equals("资产名单")||retunModel.getTargetName().equals("机构名单")) {
                    retunlist.add(retunModel);
                }

            });

        }

        //不在名单中可投、总投为0
       if(StringUtil.isEmptyList(riskNamList))
        {
            EtrMoneyCalculationDto dto=new EtrMoneyCalculationDto();
            convertDto(dto,finprodId,finprodType,portfolioId,"资产名单");
            retunlist.add(dto);
            EtrMoneyCalculationDto orgdto=new EtrMoneyCalculationDto();
            convertDto(orgdto,finprodId,finprodType,portfolioId,"机构名单");
            retunlist.add(orgdto);

        }
        else if(!StringUtil.isEmptyList(riskNamList)&&riskNamList.size()==1)
        {
            EtrMoneyCalculationDto dto=new EtrMoneyCalculationDto();

            if(riskNamList.get(0).getListDetailType().equals(DictCodeListExt2.名单主体明细类型_资产.getValue()))
            {
                convertDto(dto,finprodId,finprodType,portfolioId,"机构名单");
                retunlist.add(dto);
            }
            else
            {
                convertDto(dto,finprodId,finprodType,portfolioId,"资产名单");
                retunlist.add(dto);
            }
        }

        // .根据相关条件获取资产名单,机构名单end

        Page<EtrMoneyCalculationDto> etrMoneyCalculationDetailDtoPage = new Page<EtrMoneyCalculationDto>();
        etrMoneyCalculationDetailDtoPage.setRoot(retunlist);
        etrMoneyCalculationDetailDtoPage.setTotal(retunlist.size());
        etrMoneyCalculationDetailDtoPage.setRecords(retunlist.size());
        return etrMoneyCalculationDetailDtoPage;
    }

      public  EtrMoneyCalculationDto convertDto(EtrMoneyCalculationDto dto,String finprodId,String finprodType,String portfolioId,String type)
      {
          if(type.equals("资产名单"))
          {
              dto.setTargetName("资产名单");
          }
          if(type.equals("机构名单"))
          {
              dto.setTargetName("机构名单");
          }
          dto.setPortfoliopackage("-");
          dto.setSumMoney("0.00");// 可投总金额
          dto.setUseMoney("-");//已投金额
          dto.setSurplusMoney("0.00");//剩余可投金额=可投总金额
          dto.setFinprodId(finprodId);
          dto.setFinprodType(finprodType);
          dto.setPortfolioId(portfolioId);
          return  dto;
      }
    //获取授信列表
    public  List<CreditLines> getCreditLinesList (EtrMoneyCalculationDto etrMoneyCalculationDto,String finprodType,String platDate)
    {
        AllTradeCommonInputDto inputmodel =new AllTradeCommonInputDto();
        TrdProductDeal model =new TrdProductDeal();
        model.setPs(DictCodeList.交易方向_买入.getValue());
        model.setFinprodId(etrMoneyCalculationDto.getFinprodId().contains(finprodType)?etrMoneyCalculationDto.getFinprodId():finprodType+"_"+etrMoneyCalculationDto.getFinprodId());
        if(StringUtil.isNotEmpty(etrMoneyCalculationDto.getPublisher()))
        {
            model.setCounterId( etrMoneyCalculationDto.getPublisher());
        }
        if(StringUtil.isNotEmpty(etrMoneyCalculationDto.getManager()))
        {
            model.setCounterId(etrMoneyCalculationDto.getManager());
        }
        model.setFinprodType2(finprodType);
        model.setTradeDate(DateUtil.parse( platDate));

        List<CreditLines> creditLinesList=new ArrayList<>();
        creditLinesList=ceLinesUpdate.getCreLineslist(model,creditLinesList,inputmodel,false,true);
        return creditLinesList;
    }
}
