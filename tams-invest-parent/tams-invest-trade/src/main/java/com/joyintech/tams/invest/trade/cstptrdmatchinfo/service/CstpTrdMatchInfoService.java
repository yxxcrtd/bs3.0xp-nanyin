package com.joyintech.tams.invest.trade.cstptrdmatchinfo.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.trade.entrust.EtrFinishStatusDto;
import com.joyintech.tams.invest.trade.cstp.dto.CstpSelectDto;
import com.joyintech.tams.invest.trade.cstptrdmatchinfo.dto.CstpTrdMatchInfoDto;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName CstpTrdMatchInfoService
 * @Description: TODO
 * @Author wangxuelei
 * @Date 2020/1/9 0009
 * @Version V1.0
 **/
@Service
@Transactional(rollbackFor=Exception.class)
public class CstpTrdMatchInfoService extends CommonExportService<CstpTrdMatchInfoDto> {

    @Autowired
    private TrdMatchInfoMapper trdMatchInfoMapper;
    @Autowired
    private InterfTradSecdealMapper interfTradSecdealMapper;
    @Autowired
    private EtrFinishStatusMapper etrFinishStatusMapper;
    @Autowired
    private TrdProductDealPreMapper trdProductDealPreMapper;
    @Autowired
    private TrdProductDealAddPreMapper trdProductDealAddPreMapper;
    @Autowired
    private InterfOpicsTradRepoMapper interfOpicsTradRepoMapper;

    public Page<CstpTrdMatchInfoDto> findPage(Map<String,Object> map){
        if(map.containsKey("trdMatchTyp")&&StringUtil.isNotBlank(map.get("trdMatchTyp").toString())){
            String[] trdMatchTyp = map.get("trdMatchTyp").toString().split(",");
            map.replace("trdMatchTyp", trdMatchTyp);
        }else{
            map.replace("trdMatchTyp", null);
        }
        if(map.containsKey("portfolioId")&&StringUtil.isNotBlank(map.get("portfolioId").toString())){
            String[] portfolioId = map.get("portfolioId").toString().split(",");
            map.replace("portfolioId", portfolioId);
        }else{
            map.replace("portfolioId", null);
        }
        if(map.containsKey("finprodType2")&&StringUtil.isNotBlank(map.get("finprodType2").toString())){
            String finprodType2Old = (String)map.get("finprodType2");
            List<String> list1=new ArrayList<>();
            List<String> list2=new ArrayList<>();
            List<String> list3=new ArrayList<>();
            if (StringUtil.isNotEmpty(finprodType2Old) || StringUtil.isNotEmpty(map.get("ps"))) {
                List<String> finprodType2ListOld = StringUtil.splitToList(GlobalConstant.COMMA, finprodType2Old);
                for(String finprodType2 : finprodType2ListOld){
	                //              //为不同资产类型赋查询条件
	                if(StringUtil.equals(DictCodeList.平安资产类型_债券.getValue(), finprodType2)){
	                	list1.add(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
	                }
	                else if(StringUtil.equals(DictCodeList.平安资产类型_基金.getValue(), finprodType2)){
	                	list1.add(DictCodeListExt1.金融产品类型_投管用_基金.getValue());
	                }
	                else if(StringUtil.equals(DictCodeList.平安资产类型_计划类资产_利率型.getValue(), finprodType2)){
	                	list3.add(DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
	                	list2.add(DictCodeList.收益类型_收益型.getValue());
	                }
	                else if(StringUtil.equals(DictCodeList.平安资产类型_计划类资产_净值型.getValue(), finprodType2)){
	                	list3.add(DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
	                	list2.add(DictCodeList.收益类型_净值型.getValue());
	                }
	                else if(StringUtil.equals(DictCodeList.平安资产类型_计划类资产_货币型.getValue(), finprodType2)){
	                	list3.add(DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
	                	list2.add(DictCodeList.收益类型_货币型.getValue());
	                }
	                else if(StringUtil.equals(DictCodeList.平安资产类型_存款.getValue(), finprodType2)){
	                	list1.add(DictCodeListExt1.金融产品类型_存款.getValue());
	                }
	                else if(StringUtil.equals(DictCodeList.平安资产类型_项目类资产.getValue(), finprodType2)){
	                	list1.add(DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
	                }
	                else if(StringUtil.equals(DictCodeList.平安资产类型_质押式回购.getValue(), finprodType2)){
	                	list1.add(DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue());
	                	list1.add(DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue());
	                }
	                else if(StringUtil.equals(DictCodeList.平安资产类型_买断式回购.getValue(), finprodType2)){
	                	list1.add(DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue());
	                	list1.add(DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue());
	                }
	                else if(StringUtil.equals(DictCodeList.平安资产类型_拆借.getValue(), finprodType2)){
	                	list1.add(DictCodeListExt1.金融产品类型_投管用_拆入.getValue());
	                	list1.add(DictCodeListExt1.金融产品类型_投管用_拆出.getValue());
	                }
	
	
	                else if(StringUtil.equals(DictCodeListExt2.平安资产类型_股票交易.getValue(), finprodType2)){
	                	list1.add(DictCodeListExt1.金融产品类型_投管用_拆入.getValue());
	                	list1.add(DictCodeListExt1.金融产品类型_投管用_拆出.getValue());
	                }
	                else if(StringUtil.equals(DictCodeListExt2.平安资产类型_外汇即期.getValue(), finprodType2)){
	                	list1.add(DictCodeListExt1.金融产品类型_投管用_即期.getValue());
	                }
	                else if(StringUtil.equals(DictCodeListExt2.平安资产类型_外汇远期.getValue(), finprodType2)){
	                	list1.add(DictCodeListExt1.金融产品类型_投管用_远期.getValue());
	                }
	                else if(StringUtil.equals(DictCodeListExt2.平安资产类型_外汇掉期.getValue(), finprodType2)){
	                	list1.add(DictCodeListExt1.金融产品类型_投管用_掉期.getValue());
	                }
	                else if(StringUtil.equals(DictCodeListExt2.平安资产类型_利率互换.getValue(), finprodType2)){
	                	list1.add(DictCodeListExt1.金融产品类型_投管用_利率互换.getValue());
	                }
	                else if(StringUtil.equals(DictCodeListExt2.平安资产类型_债券借贷.getValue(), finprodType2)){
	                	list1.add(DictCodeListExt1.金融产品类型_投管用_债券借贷融入.getValue());
	                	list1.add(DictCodeListExt1.金融产品类型_投管用_债券借贷融出.getValue());
	                }
	                else if(StringUtil.equals(DictCodeListExt2.平安资产类型_股票.getValue(), finprodType2)){
	                	list1.add(DictCodeListExt1.金融产品类型_投管用_股票.getValue());
	                }
	                else if(StringUtil.equals(BussinessDictCodeList.金融产品类型_投管用_一级投标债券.getValue(), finprodType2)){
	                	list1.add(BussinessDictCodeList.金融产品类型_投管用_一级投标债券.getValue());
	                }
	                //交易方向为利率互换 转换
	                if(StringUtil.isNotEmpty((String)map.get("ps"))){
	                	if( StringUtil.equals((String)map.get("ps") , DictCode.互换方式.getValue()+DictCodeListExt2.互换方式_收取固定.getValue()) ){
	                		map.put("ps", DictCodeListExt2.互换方式_收取固定.getValue());
	                		map.put("IRSps", DictCodeListExt2.互换方式_收取固定.getValue());
	                    }
	                    if( StringUtil.equals((String)map.get("ps") , DictCode.互换方式.getValue()+DictCodeListExt2.互换方式_收取浮动.getValue()) ){
	                    	map.put("ps", DictCodeListExt2.互换方式_收取浮动.getValue());
	                    	map.put("IRSps", DictCodeListExt2.互换方式_收取浮动.getValue());
	                    }
	                    map.put("PS_T01", DictCodeList.交易方向_买入.getValue());
	                }
                }
            }
            
            map.put("finprodType2ListPre", list1);

            String[] finprodType2 = map.get("finprodType2").toString().split(",");
            map.replace("finprodType2", finprodType2);
        }else{
            map.replace("finprodType2", null);
            map.put("finprodType2ListPre", null);
        }
        if(map.containsKey("execid")&&StringUtil.isNotBlank(map.get("execid").toString())){
            String[] execid = map.get("execid").toString().split(",");
            map.replace("execid", execid);
        }else{
            map.replace("execid", null);
        }
        Page<CstpTrdMatchInfoDto> cstpSelectDtoPage = restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME
                + "/CSTPTrdMatchInfo/findPage", map, CstpTrdMatchInfoDto.class);
        setValue(cstpSelectDtoPage);
        return cstpSelectDtoPage;
    }


    private void setValue( Page<CstpTrdMatchInfoDto> cstpSelectDtoPage){
        if(CollectionUtils.isNotEmpty(cstpSelectDtoPage.getRoot())){
            for (CstpTrdMatchInfoDto dto:cstpSelectDtoPage.getRoot()) {
                if(StringUtil.equals(dto.getTradeType(),"02")&&StringUtil.equals(dto.getFinprodType(),"F07")){
                    if(dto.getPrinAmt()!=null && dto.getUnitCprice()!=null &&dto.getActCapDays()!=null){
                        BigDecimal multiply = ArithmeticUtil.multiply(dto.getPrinAmt(), dto.getUnitCprice());
                        BigDecimal multiply1 = ArithmeticUtil.multiply(ArithmeticUtil.divide(multiply, new BigDecimal(100), 8), new BigDecimal(dto.getActCapDays()));
                        BigDecimal divide = ArithmeticUtil.divide(multiply1, 365, 2);
                        dto.setUnitInt(divide);
                        dto.setIntAmt(divide);
                        dto.setMdateSettleAmt(ArithmeticUtil.doAdd(divide,dto.getPrinAmt()));
                    }
                }
            }
        }
    }
    /** @MethodName: cstp交易匹配确认保存
    * @Description: TODO
    * @Param: []
    * @Return: void
    * @Author: wangxuelei
    * @Date: 2020/1/13 0013
    **/
    public void saveTrdMatchInfoByCstp(CstpTrdMatchInfoDto cstpTrdMatchInfoDto){
        if(CollectionUtils.isNotEmpty(cstpTrdMatchInfoDto.getTrdMatchInfos())){
            for (TrdMatchInfo info:cstpTrdMatchInfoDto.getTrdMatchInfos()) {
                info.setSeqno(UUID.randomUUID().toString().replaceAll("-", ""));
                finishEntrustAmt(info,false);
//                updateInterfForZhaiJuan(info,true);
                trdMatchInfoMapper.insertSelective(info);
            }
        }
    }
    /** @MethodName: 匹配确认取消匹配
    * @Description: TODO
    * @Param: [cstpTrdMatchInfoDto]
    * @Return: void
    * @Author: wangxuelei
    * @Date: 2020/1/13 0013
    **/
    public void deleteTrdMatchInfoByCstp(CstpTrdMatchInfoDto cstpTrdMatchInfoDto){
        if(CollectionUtils.isNotEmpty(cstpTrdMatchInfoDto.getTrdMatchInfos())){
            for (TrdMatchInfo info:cstpTrdMatchInfoDto.getTrdMatchInfos()) {
                finishEntrustAmt(info,true);
//                updateInterfForZhaiJuan(info,false);
                trdMatchInfoMapper.deleteByPrimaryKey(info.getSeqno());
            }
        }
    }
    /** @MethodName: 较少cstp的可匹配额度
    * @Description: TODO
    * @Param: [cstpTrdMatchInfoDto]
    * @Return: void
    * @Author: wangxuelei
    * @Date: 2020/1/13 0013
    **/
    public void updateInterfForZhaiJuanForSecdeal(TrdMatchInfo cstpTrdMatchInfoDto, Boolean type){
        InterfTradSecdeal interfTradSecdeal = interfTradSecdealMapper.selectByPrimaryKey(cstpTrdMatchInfoDto.getCstpTradeId());
        InterfTradSecdeal record = new InterfTradSecdeal();
        if(interfTradSecdeal!=null){
            BigDecimal subtract = new BigDecimal(0);
            record.setDealno(cstpTrdMatchInfoDto.getCstpTradeId());
            BigDecimal prinamt = interfTradSecdeal.getPrinamt();
            if(type){//匹配确认
                 subtract = ArithmeticUtil.subtract(prinamt, cstpTrdMatchInfoDto.getMatchNum());
            }else{//取消匹配
                TrdMatchInfo trdMatchInfo = trdMatchInfoMapper.selectByPrimaryKey(cstpTrdMatchInfoDto.getSeqno());
                subtract =  ArithmeticUtil.doAdd(prinamt,trdMatchInfo.getMatchNum());
            }
            if(subtract.compareTo(BigDecimal.ZERO)==-1||subtract.compareTo(BigDecimal.ZERO)==0){//小于0的话
                record.setPrinamt(BigDecimal.ZERO);
            }else{
                record.setPrinamt(subtract);
            }
            interfTradSecdealMapper.updateByPrimaryKeySelective(record);
        }
    }

    public void updateInterfForZhaiJuanForOpic(TrdMatchInfo cstpTrdMatchInfoDto, Boolean type){
        InterfOpicsTradRepo interfOpicsTradRepo = interfOpicsTradRepoMapper.selectByPrimaryKey(cstpTrdMatchInfoDto.getCstpTradeId());
        InterfOpicsTradRepo record = new InterfOpicsTradRepo();
        if(interfOpicsTradRepo!=null){
            BigDecimal subtract = new BigDecimal(0);
            record.setDealno(cstpTrdMatchInfoDto.getCstpTradeId());
            BigDecimal repoamt = interfOpicsTradRepo.getRepoamt();
            if(type){//匹配确认
                subtract = ArithmeticUtil.subtract(repoamt, cstpTrdMatchInfoDto.getMatchNum());
            }else{//取消匹配
                TrdMatchInfo trdMatchInfo = trdMatchInfoMapper.selectByPrimaryKey(cstpTrdMatchInfoDto.getSeqno());
                subtract =  ArithmeticUtil.doAdd(repoamt,trdMatchInfo.getMatchNum());
            }
            if(subtract.compareTo(BigDecimal.ZERO)==-1||subtract.compareTo(BigDecimal.ZERO)==0){//小于0的话
                record.setRepoamt(BigDecimal.ZERO);
            }else{
                record.setRepoamt(subtract);
            }
            interfOpicsTradRepoMapper.updateByPrimaryKeySelective(record);
        }
    }


    private Boolean finishEntrustAmt(TrdMatchInfo dto,boolean isCancel){
        List<EtrFinishStatusDto> baseEtrInvestInfoDtos = new ArrayList<>();
        EtrFinishStatusDto status = new EtrFinishStatusDto();
        if(StringUtil.isNotBlank(dto.getEntrustId())){
            status.setEntrustId(dto.getEntrustId());
            status.setTradeId(dto.getCstpTradeId());
            status.setFinishDate(dto.getTradeDate());
            status.setFinishAmt(dto.getMatchNum());
            status.setTrader(LoginUserUtil.getUserName());
            baseEtrInvestInfoDtos.add(status);
        }else{
            TrdProductDealPreKey key = new TrdProductDealPreKey();
            key.setTradeId(dto.getPreTradeId());
            key.setWorkflowStatus(DictCodeListExt1.交易流程状态_有效.getValue());
            TrdProductDealPre trdProductDealPre = trdProductDealPreMapper.selectByPrimaryKey(key);
            TrdProductDealAddPre trdProductDealAddPre = trdProductDealAddPreMapper.selectByPrimaryKey(dto.getPreTradeId());
            EtrFinishStatusKey etrKey = new EtrFinishStatusKey();
            etrKey.setEntrustId(trdProductDealAddPre.getEntrustId());
            etrKey.setTradeId(dto.getPreTradeId());
            EtrFinishStatus etrFinishStatus = etrFinishStatusMapper.selectByPrimaryKey(etrKey);
            if(trdProductDealPre!=null && StringUtils.isNotEmpty(trdProductDealAddPre.getEntrustId())) {
                status.setEntrustId(trdProductDealAddPre.getEntrustId());
                status.setTradeId(dto.getCstpTradeId());
                status.setFinishDate(dto.getTradeDate());
                status.setFinishAmt(dto.getMatchNum());
                status.setEntFinishAmt(etrFinishStatus != null ? etrFinishStatus.getEntFinishAmt() : BigDecimal.ZERO);
                status.setTrader(LoginUserUtil.getUserName());
                baseEtrInvestInfoDtos.add(status);
            }
        }
        if (!baseEtrInvestInfoDtos.isEmpty()) {
            if (isCancel) {
                return restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/common/entrustcommonbase/cancelEntrust", baseEtrInvestInfoDtos, Boolean.class);
            } else {
                return restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/common/entrustcommonbase/finishEntrust", baseEtrInvestInfoDtos, Boolean.class);
            }
        }
        return Boolean.TRUE;
    }
    
    /**
     * 导出 
     */
     @Override
     protected void buildExportParam(ExportParam exportParam) {
         exportParam.config(
                 "/com/joyintech/tams/invest/trade/cstptrdmatchinfo/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
     }
     
     /**
      * {@inheritDoc}
      */
     @Override
     public Page<CstpTrdMatchInfoDto> selectPageData(Map<String, Object> map) {
         return findPage(map);
     }

}
