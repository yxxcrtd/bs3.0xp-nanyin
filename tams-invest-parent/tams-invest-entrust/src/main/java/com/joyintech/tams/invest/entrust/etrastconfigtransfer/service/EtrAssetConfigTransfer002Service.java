package com.joyintech.tams.invest.entrust.etrastconfigtransfer.service;

import com.alibaba.fastjson.JSONArray;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.asset.dto.AssetGenerateEntry;
import com.joyintech.tams.invest.common.asset.util.AssetCommonUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.entrust.common.controller.EtrCommonController;
import com.joyintech.tams.invest.entrust.etrastconfigtransfer.dto.EtrAssetConfigDto;
import com.joyintech.tams.invest.entrust.etrexchangetrade.dto.EtrExchangeTrade001Dto;
import com.joyintech.tams.invest.entrust.etrexchangetrade.dto.EtrExchangeTradeDzImportDto;
import com.joyintech.tams.invest.entrust.etrexchangetrade.dto.EtrExchangeTradeGsImportDto;
import com.joyintech.tams.invest.entrust.etrexchangetrade.service.EtrExchangeTrade001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;


/**
 * 资产配置快速转仓指令service
 * 产品信息查询
 * @author 裴家俊
 * @version 2021年7月20日
 * @see EtrAssetConfigTransfer002Service
 * @since 1.0
 */

@Service
public class EtrAssetConfigTransfer002Service extends BaseService<EtrAssetConfigDto> {

    @Autowired
    private EtrExchangeTrade001Service etrExchangeTrade001Service;

    private final static String[] KEY_ARRAY=new String[]{"valDate","portfolioId","entrustData","prinAmt","ps","calculateField","yield","fprice"};

    public List<EtrAssetConfigDto> getAstPosition(Map<String, Object> param) {
        param.put(KEY_ARRAY[0],DateUtil.format(DateUtil.platDateFromCache()));
        List<EtrAssetConfigDto> result=  restTemplate.postForList(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrAssetConfigTransfer/getAstPosition", param, EtrAssetConfigDto.class);

        for(EtrAssetConfigDto dto:result){
            Map<String, Object> costParam = new HashMap<>();
            costParam.put("sysDate",DateUtil.format(DateUtil.platDateFromCache()));
            costParam.put("portfolioId",dto.getPortfolioId());
            costParam.put("finprodId",dto.getFinprodId());
            EtrCommonController etrCommonController = SpringContextUtil.getBean(
                    EtrCommonController.class);
            dto.setBuyCost(etrCommonController.getCostPrice(costParam));
        }
        return result;
    }

    /**
     * 查询产品规模及成立日信息等
     * @param paramMap
     * @return
     */
    public Map selectPortfolioInfo(Map<String, Object> paramMap) {
        paramMap.put(KEY_ARRAY[0],DateUtil.format(DateUtil.platDateFromCache()));
        AssetGenerateEntry astEntry=AssetCommonUtil.getAssetInfoDb(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue()+ GlobalConstant.UNDER_LINE+ (StringUtil.isNotEmpty(paramMap.get(KEY_ARRAY[1]))?paramMap.get(KEY_ARRAY[1]).toString():null), obj->obj);
        Map astMap=null!=astEntry?BeanUtil.object2Map(astEntry):new HashMap<>();
        List<BigDecimal> 产品规模List=AssetCommonUtil.getAstNetVal(Arrays.asList(paramMap.get(KEY_ARRAY[1]).toString()),DateUtil.platDateFromCache(),null,item->(BigDecimal)item.get(AssetCommonUtil.产品净资产));
        astMap.put(AssetCommonUtil.产品净资产,!StringUtil.isEmptyList(产品规模List)?产品规模List.get(0):null);
        return astMap;
    }

    /**
     * 试算转仓资产
     * @param paramDto
     * @return
     */
    public  List<EtrAssetConfigDto> trialPosition(EtrAssetConfigDto paramDto) {
        List<EtrAssetConfigDto> retList=new ArrayList<>();
        EtrAssetConfigDto retDto=new EtrAssetConfigDto();
        BeanUtil.copyPropertiesIgnoreNull(retDto,paramDto);
        List<BigDecimal> 估价净价List= AssetCommonUtil.getBondValuation(Arrays.asList(paramDto.getFinprodId()), DateUtil.platDateFromCache(),null, item->(BigDecimal)item.get(AssetCommonUtil.估价净价));
        BigDecimal 估价净价=!StringUtil.isEmptyList(估价净价List)?估价净价List.get(0):null;
        if(StringUtil.isNotEmpty(估价净价)) {
            //交易净价
            BigDecimal 交易净价 = calTradePrice(paramDto.getBuyCost(), 估价净价);
            retDto.setPrice(交易净价);
            retDto.setNetCprice(估价净价);
            //价格偏离度
            retDto.setDeviation(ArithmeticUtil.divide(ArithmeticUtil.subtract(交易净价, 估价净价), 估价净价, 6));
            //持有期收益率与全价
            calYieldPrice(retDto);
        }
        //获取转入资产的登记托管机构
        String registOrg=AssetCommonUtil.getAssetInfoDb(paramDto.getFinprodId(), obj->obj.getRegistOrg());
        paramDto.getPortfolioValuationList().stream().forEach(item->{
            EtrAssetConfigDto desDto=new EtrAssetConfigDto();
            BeanUtil.copyPropertiesIgnoreNull(desDto,retDto);
            desDto.setPortfolioId(item.getPortfolioId());
            List<String> 证券账户List=AssetCommonUtil.getStlAccount(Arrays.asList(item.getPortfolioId()),obj->StringUtil.equals((String)obj.get(AssetCommonUtil.登记机构),registOrg),obj->(String)obj.get(AssetCommonUtil.证券账号));
            desDto.setSecAcctId(!StringUtil.isEmptyList(证券账户List)?证券账户List.get(0):null);
            //转出数量与占比
            if(StringUtil.isNotEmpty(retDto.getPrice())) {
                desDto.setEntrustNum(ArithmeticUtil.divide(ArithmeticUtil.multiply(item.getTdyAssetNetvalue(), new BigDecimal(9)), retDto.getPrice(), 6));
                desDto.setRadio(new BigDecimal(0.09));
            }
            retList.add(desDto);
        });
        return retList;
    }


    /**
     * 快速生成指令组装数据
     * @param paramDto
     * @return
     */
    public  Map<String,Object> assembleImportData(EtrAssetConfigDto paramDto) {
        JSONArray jsonArrays = new JSONArray();
        paramDto.getSelectedItem().stream().forEach(item->{
            int 序号=paramDto.getSelectedItem().indexOf(item)+1;
            EtrAssetConfigDto desItem=new EtrAssetConfigDto();
            BeanUtil.copyPropertiesIgnoreNull(desItem,item);
            //买入方
            item.setIndexId(String.valueOf(序号*2-1));
            item.setPs(DictCodeList.交易方向_买入.getValue());
            jsonArrays.add(assembleCommonDataByTMK(item));
            //卖出方
            desItem.setIndexId(String.valueOf(序号*2));
            desItem.setPs(DictCodeList.交易方向_卖出.getValue());
            desItem.setPortfolioId(paramDto.getPortfolioId());
            jsonArrays.add(assembleCommonDataByTMK(desItem));
        });
        Map<String,Object> retMap=new HashMap<>();
        if(StringUtil.isNotEmpty(jsonArrays)){
            retMap.put(KEY_ARRAY[2],JSONArray.toJSONString(jsonArrays));
        }
        return retMap;
    }

    private  Object[] assembleCommonDataByTMK(EtrAssetConfigDto item){
        if(StringUtil.equals(DictCodeListExt1.交易场所_银行间.getValue(),item.getTradeMarket())){
            item.setFinprodId(AssetCommonUtil.getAssetInfoDb(item.getFinprodId(),obj->obj.getFinprodMarketId()));
            return assembleCommonDataByIB(item,EtrAssetConfigDto.class);
        }
        if(StringUtil.equals(DictCodeListExt1.交易场所_深圳证券交易所.getValue(),item.getTradeMarket())){
            EtrExchangeTradeDzImportDto etrDto=new EtrExchangeTradeDzImportDto();
            BeanUtil.copyPropertiesIgnoreNull(etrDto,item);
            etrDto.setSeqNo(item.getIndexId());
            etrDto.setTradePlat(DictCodeList.交易平台_大宗交易平台.getValue());
            etrDto.setDeclaType(DictCodeListExt3.申报类型_不指定.getValue());
            return assembleCommonDataByDZGS(etrDto);
        }
        if(StringUtil.equals(DictCodeListExt1.交易场所_上海证券交易所.getValue(),item.getTradeMarket())){
            EtrExchangeTradeGsImportDto etrDto=new EtrExchangeTradeGsImportDto();
            BeanUtil.copyPropertiesIgnoreNull(etrDto,item);
            etrDto.setSeqNo(item.getIndexId());
            etrDto.setTradePlat(DictCodeList.交易平台_固定收益平台.getValue());
            etrDto.setDeclaType(DictCodeListExt3.申报类型_指定对手方成交申报.getValue());
            etrDto.setSettleSpeed(DictCodeList.清算速度_T_1.getValue());
            return assembleCommonDataByDZGS(etrDto);
        }
        return null;
    }

    /**
     * 银行间指令字段
     * @param item
     * @param cls
     */
    private <T> Object[] assembleCommonDataByIB(T item,Class<T> cls){
        //根据银行间指令导入模板excel来限定
        Object[] 组装数据=AssetCommonUtil.parseEntryToArray(new Object[21],item,cls);
        组装数据[1]=DateUtil.format(DateUtil.platDateFromCache(),DateUtil.DEFAULT_DATE_FORMAT_EN);//委托开始日
        组装数据[2]=DateUtil.format(DateUtil.platDateFromCache(),DateUtil.DEFAULT_DATE_FORMAT_EN);//委托终止日
        组装数据[6]= DictCodeList.币种_人民币_CNY.getValue();//币种
        组装数据[8]= DictCodeListExt2.价格模式_指定价格.getValue();//价格模式
        组装数据[10]= StringUtil.EMPTY;//行权收益率
        组装数据[11]= StringUtil.EMPTY;//到期收益率
        组装数据[12]= StringUtil.EMPTY;//最低成交金额
        组装数据[13]= DictCodeList.清算速度_T_0.getValue();//清算速度
        组装数据[16]= DictCodeList.交易平台_外汇交易中心.getValue();//交易平台
        return 组装数据;
    }

    /**
     * 大宗与固收(深交所，上交所)指令字段
     * @param item
     */
    private <T extends EtrExchangeTrade001Dto> Object[] assembleCommonDataByDZGS(T item){
        Map<String, Object> param = new HashMap<>();
        param.put(KEY_ARRAY[1], item.getPortfolioId());
        List<SelectOptionDto> ptlSecAcctList = etrExchangeTrade001Service.getPtlSecAcctInfo(param);
        item.setAcctNo(!StringUtil.isEmptyList(ptlSecAcctList)?ptlSecAcctList.get(0).getId():null);
        item.setFinprodType2(AssetCommonUtil.getAssetInfoDb(item.getFinprodId(),obj->obj.getFinprodType2()));
        item.setPriceModel(DictCodeListExt2.价格模式_限价指令.getValue());
        item.setEntrustPrice(item.getPrice());
        item.setTargetType(DictCodeListExt3.目标类型_数量.getValue());
        item.setEntrustNum(ArithmeticUtil.divide(item.getEntrustNum(),new BigDecimal(100),4));
        item.setVdate(DateUtil.platDateFromCache());
        item.setMdate(DateUtil.platDateFromCache());
        etrExchangeTrade001Service.calEntrustNumOrPrice(item,null);//指令金额
        //todo 组合券商与席位号
        return Arrays.asList(item).toArray(new Object[Arrays.asList(item).size()]);
    }

    /**
     * 计算交易净价
     * @param 买入成本
     * @param 估价净价
     * @return
     */
    private BigDecimal calTradePrice(BigDecimal 买入成本,BigDecimal 估价净价){
        //若估值净价小于买入成本，则交易净价取估值净价
        if(BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(估价净价,买入成本))>0){
            return 估价净价;
        }else{
            BigDecimal 净价偏离=ArithmeticUtil.divide(ArithmeticUtil.subtract(估价净价,买入成本),估价净价,6);
            //如果净价偏离度大于1.85%，交易净价取估值净价*0.9815
            if(BigDecimal.ZERO.compareTo(ArithmeticUtil.subtract(净价偏离,new BigDecimal(0.0185)))<0){
                return ArithmeticUtil.multiply(估价净价,new BigDecimal(0.9815));
            }else{
                return 买入成本;
            }
        }
    }

    /**
     * 计算到期收益率，全价等(跟需求确认即持有期收益率即到期收益率)
     * @param t
     * @return
     */
    private void calYieldPrice(EtrAssetConfigDto t){
        if(!StringUtil.isNotEmpty(t)){
            return;
        }
        Map<String, Object> param =  BeanUtil.object2Map(t);
        param.put(KEY_ARRAY[3], AssetCommonUtil.getFaceVal(t.getFinprodId()));//面值
        param.put(KEY_ARRAY[4], DictCodeList.交易方向_买入.getValue());
        param.put(KEY_ARRAY[5], DictCodeListExt3.债券计算模式_单位净价.getValue());
        Map<String, BigDecimal> result = etrExchangeTrade001Service.calcPriceAndYield(param);
        if(!CollectionUtils.isEmpty(result)){
            t.setYield(result.getOrDefault(KEY_ARRAY[6],null));
            t.setFprice(result.getOrDefault(KEY_ARRAY[7],null));
        }
    }
}
