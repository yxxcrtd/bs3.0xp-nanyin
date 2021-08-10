/*
 * 文件名：RskValTableImportService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.valtblimport.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.dao.FinProductTypeMapper;
import com.joyintech.tams.common.base.dao.FinTradeProductMapper;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.xmlrelation.XmlRelationInputParaDto;
import com.joyintech.tams.invest.common.dto.xmlrelation.XmlRelationOutputDto;
import com.joyintech.tams.invest.common.util.ValItemValueGenUtil;
import com.joyintech.tams.invest.common.util.xmlrelation.XmlRelationUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.valtblimport.dao.RskValTableImportMapper;
import com.joyintech.service.risk.valtblimport.dto.RskValtblAssetSubDto;
import com.joyintech.service.risk.valtblimport.dto.ValType;
import com.joyintech.tams.common.base.dao.RskValtblAssetSubMapper;
import com.joyintech.tams.common.base.dao.RskValtblSumAssetSubMapper;
import com.joyintech.tams.common.base.entity.RskValtblAssetSub;
import com.joyintech.tams.common.base.entity.RskValtblSumAssetSub;


@Service("backRskValTableImportService")
public class RskValTableImportService {
	
    @Autowired
    private RskValtblAssetSubMapper rskValtblAssetSubMapper;
    @Autowired
    private RskValtblSumAssetSubMapper rskValtblSumAssetSubMapper;
    @Autowired
    private RskValTableImportMapper rskValTableImportMapper;
    @Autowired
    private FinTradeProductMapper finTradeProductMapper;
    @Autowired
    private FinProductTypeMapper finProductTypeMapper;
    /**
     * 金融产品类型 投管分类
     */
    public static final String KEY_FINPROD_TYPE_TG = "FINPROD_TYPE_TG";

    /**
     * 交易场所
     */
    public static final String KEY_TRADE_PLACE = "TRADE_PLACE";
    /**
     * 估值 资产类型
     */
    public static final String KEY_TYPE1 = "TYPE1";

    /**
     * 估值 核算分类
     */
    public static final String KEY_ACCOUNTING_TYPE = "ACCOUNTING_TYPE";

    /**
     * 估值 税务分类
     */
    public static final String KEY_TAX_ASSET_TYPE = "TAX_ASSET_TYPE";
    /**
     * 估值表保存
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void saveValTbl(RskValtblAssetSubDto dto){ 
        rskValTableImportMapper.deleteValTable(dto);
        rskValTableImportMapper.deleteSumValTable(dto);
        if(null!=dto.getSelectedItem()&&!dto.getSelectedItem().isEmpty()){
            for(RskValtblAssetSubDto entity:dto.getSelectedItem()){
                if(StringUtil.equals(entity.getValType().getDesc(), ValType.ASSET.getDesc())
                        && StringUtil.isNotEmpty(entity.getIsMatch()) && StringUtil.equals(entity.getIsMatch(), DictCodeListExt1.是否_否.getValue())){
                    continue;
                }
                if(StringUtil.equals(entity.getValType().getDesc(),ValType.ASSET.getDesc())){
                    RskValtblAssetSub record = new RskValtblAssetSub();
                    BeanUtil.copyPropertiesIgnoreNull(record, entity);
                    record.setPtlId(dto.getPtlId());
                    record.setValDate(dto.getValDate());
                    rskValtblAssetSubMapper.insert(record);
                }else{
                    RskValtblSumAssetSub record = new RskValtblSumAssetSub();
                    BeanUtil.copyPropertiesIgnoreNull(record, entity);
                    record.setPtlId(dto.getPtlId());
                    record.setValDate(dto.getValDate());
                    rskValtblSumAssetSubMapper.insert(record);
                }
            }
        }
    }
    
    /**
     *  估值表科目数量
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    public int selectCount(Map<String,Object> paramMap){
        return rskValTableImportMapper.selectCount(paramMap);
    }
    /**
     * 估值表保存
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void createRepoInfo(RskValtblAssetSubDto entity2){
        //根据资产代码，查询系统中是否已经存在
        List<FinTradeProduct> list= finTradeProductMapper.selectByFinprodId(entity2.getFinprodId());
        if(StringUtil.isEmptyList(list)){
            //若不在，则生成新的数据
            String code=entity2.getSubCode().substring(0, 8);
            String finprodType= null;
            String tradeMarket = null;
            String type6= null;
            String type7= null;
            String type8= null;
            String type9= null;
            String type10= null;
            String type11= null;
            String type12= null;
            if(StringUtil.isNotEmpty(code)){
                if(StringUtil.equals(code,"12020101")){
                    //上交所质押式逆回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue();//F03
                    tradeMarket=DictCodeListExt1.交易场所_上海证券交易所.getValue();
                    type6=DictCodeList.回购类型_质押式回购.getValue();
                    type7=DictCodeList.回购拆借方向_正回购.getValue();
                    type8=BussinessDictCodeList.平安资产类型_回购.getValue();

                }else if(StringUtil.equals(code,"12020102")){
                    //上交所买断式逆回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue();//F05
                    tradeMarket=DictCodeListExt1.交易场所_上海证券交易所.getValue();
                    type6=DictCodeList.回购类型_买断式回购.getValue();
                    type7=DictCodeList.回购拆借方向_逆回购.getValue();
                    type8=BussinessDictCodeList.平安资产类型_买断式回购.getValue();
                    type9=DictCodeListExt1.行内资产二级分类_拆放同业及买入返售.getValue();
                    type10=DictCodeListExt1.行内资产三级分类_买入返售.getValue();
                    type11=DictCodeListExt1.行内资产四级分类_债券买断式回购.getValue();
                    type12=DictCodeListExt1.行内资产五级分类_债券买断式回购.getValue();

                }else if(StringUtil.equals(code,"12020201")){
                    //银行间质押式逆回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue();//F03
                    tradeMarket=DictCodeListExt1.交易场所_银行间.getValue();
                    type6= DictCodeList.回购类型_质押式回购.getValue();
                    type7=DictCodeList.回购拆借方向_逆回购.getValue();
                    type8=BussinessDictCodeList.平安资产类型_回购.getValue();

                }else if(StringUtil.equals(code,"12020202")){
                    //银行间买断式逆回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue();//F05
                    tradeMarket=DictCodeListExt1.交易场所_银行间.getValue();
                    type6=DictCodeList.回购类型_买断式回购.getValue();
                    type7=DictCodeList.回购拆借方向_逆回购.getValue();
                    type8=BussinessDictCodeList.平安资产类型_买断式回购.getValue();
                    type9=DictCodeListExt1.行内资产二级分类_拆放同业及买入返售.getValue();
                    type10=DictCodeListExt1.行内资产三级分类_买入返售.getValue();
                    type11=DictCodeListExt1.行内资产四级分类_债券买断式回购.getValue();
                    type12=DictCodeListExt1.行内资产五级分类_债券买断式回购.getValue();

                }else if(StringUtil.equals(code,"12020301")){
                    //深交所质押式逆回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue();//F03
                    tradeMarket=DictCodeListExt1.交易场所_深圳证券交易所.getValue();
                    type6=DictCodeList.回购类型_质押式回购.getValue();
                    type7=DictCodeList.回购拆借方向_逆回购.getValue();
                    type8=BussinessDictCodeList.平安资产类型_回购.getValue();
                }else if(StringUtil.equals(code,"12020302")){
                    //深交所买断式逆回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue();//F05
                    tradeMarket=DictCodeListExt1.交易场所_深圳证券交易所.getValue();
                    type6=DictCodeList.回购类型_买断式回购.getValue();
                    type7=DictCodeList.回购拆借方向_逆回购.getValue();
                    type8=BussinessDictCodeList.平安资产类型_买断式回购.getValue();
                    type9=DictCodeListExt1.行内资产二级分类_拆放同业及买入返售.getValue();
                    type10=DictCodeListExt1.行内资产三级分类_买入返售.getValue();
                    type11=DictCodeListExt1.行内资产四级分类_债券买断式回购.getValue();
                    type12=DictCodeListExt1.行内资产五级分类_债券买断式回购.getValue();

                }else if(StringUtil.equals(code,"22020101")){
                    //上交所卖出质押式回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue();//F02
                    tradeMarket=DictCodeListExt1.交易场所_上海证券交易所.getValue();
                    type6=DictCodeList.回购类型_质押式回购.getValue();
                    type7=DictCodeList.回购拆借方向_正回购.getValue();
                    type8=BussinessDictCodeList.平安资产类型_回购.getValue();

                }else if(StringUtil.equals(code,"22020102")){
                    //上交所卖出买断式回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue();//F04
                    tradeMarket=DictCodeListExt1.交易场所_上海证券交易所.getValue();
                    type6=DictCodeList.回购类型_买断式回购.getValue();
                    type7=DictCodeList.回购拆借方向_正回购.getValue();
                    type8=BussinessDictCodeList.平安资产类型_买断式回购.getValue();
                    type9=DictCodeListExt1.行内资产二级分类_同业拆入及卖出回购.getValue();
                    type10=DictCodeListExt1.行内资产三级分类_卖出回购.getValue();
                    type11=DictCodeListExt1.行内资产四级分类_债券买断式回购.getValue();
                    type12=DictCodeListExt1.行内资产五级分类_债券买断式回购.getValue();

                }else if(StringUtil.equals(code,"22020201")){
                    //银行间卖出质押式回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue();//F02
                    tradeMarket=DictCodeListExt1.交易场所_银行间.getValue();
                    type6=DictCodeList.回购类型_质押式回购.getValue();
                    type7=DictCodeList.回购拆借方向_正回购.getValue();
                    type8=BussinessDictCodeList.平安资产类型_回购.getValue();

                }else if(StringUtil.equals(code,"22020202")){
                    //银行间卖出买断式回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue();//F04
                    tradeMarket=DictCodeListExt1.交易场所_银行间.getValue();
                    type6=DictCodeList.回购类型_买断式回购.getValue();
                    type7=DictCodeList.回购拆借方向_正回购.getValue();
                    type8=BussinessDictCodeList.平安资产类型_买断式回购.getValue();
                    type9=DictCodeListExt1.行内资产二级分类_同业拆入及卖出回购.getValue();
                    type10=DictCodeListExt1.行内资产三级分类_卖出回购.getValue();
                    type11=DictCodeListExt1.行内资产四级分类_债券买断式回购.getValue();
                    type12=DictCodeListExt1.行内资产五级分类_债券买断式回购.getValue();


                }else if(StringUtil.equals(code,"22020301")){
                    //深交所卖出质押式回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue();//F02
                    tradeMarket=DictCodeListExt1.交易场所_深圳证券交易所.getValue();
                    type6=DictCodeList.回购类型_质押式回购.getValue();
                    type7=DictCodeList.回购拆借方向_正回购.getValue();
                    type8=BussinessDictCodeList.平安资产类型_回购.getValue();

                }else if(StringUtil.equals(code,"22020302")){
                    //深交所卖出买断式回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue();//F04
                    tradeMarket=DictCodeListExt1.交易场所_深圳证券交易所.getValue();
                    type6=DictCodeList.回购类型_买断式回购.getValue();
                    type7=DictCodeList.回购拆借方向_正回购.getValue();
                    type8= BussinessDictCodeList.平安资产类型_买断式回购.getValue();
                    type9=DictCodeListExt1.行内资产二级分类_同业拆入及卖出回购.getValue();
                    type10=DictCodeListExt1.行内资产三级分类_卖出回购.getValue();
                    type11=DictCodeListExt1.行内资产四级分类_债券买断式回购.getValue();
                    type12=DictCodeListExt1.行内资产五级分类_债券买断式回购.getValue();

                }
                FinTradeProduct ftp=new FinTradeProduct();
                ftp.setFinprodId(entity2.getFinprodId());
                ftp.setBranch(0L);
                ftp.setFinprodType(finprodType);
                ftp.setFinprodType2(finprodType);
                ftp.setTradeMarket(tradeMarket);
                ftp.setCreateUser(CommonUtil.getUserName());
                ftp.setUpdateUser(CommonUtil.getUserName());
                ftp.setCreateDept(CommonUtil.getDeptCode());
                ftp.setCreateTime(DateUtil.now());
                ftp.setUpdateTime(DateUtil.now());
                ftp.setDataSource(DictCodeListExt1.生成方式_接口接入.getValue());
                finTradeProductMapper.insert(ftp);

                FinProductType fpt=new FinProductType();
                // 调用共通获取金融产品类型（估值核算）
                List<XmlRelationInputParaDto> inputParamList = new ArrayList<XmlRelationInputParaDto>();
                // 金融产品类型（投管分类）
                XmlRelationInputParaDto inputParaDto = new XmlRelationInputParaDto();
                inputParaDto.setDictCode(KEY_FINPROD_TYPE_TG);
                inputParaDto.setItemCode(finprodType);
                inputParamList.add(inputParaDto);
                // 交易场所
                if (StringUtil.isNotBlank(tradeMarket)) {
                    inputParaDto = new XmlRelationInputParaDto();
                    inputParaDto.setDictCode(KEY_TRADE_PLACE);
                    inputParaDto.setItemCode(tradeMarket);
                    inputParamList.add(inputParaDto);
                }


                XmlRelationOutputDto outputDto = XmlRelationUtil.getOutPut(inputParamList);
                if (StringUtil.isBlank(fpt.getType1())) {
                    fpt.setType1(XmlRelationUtil.getVALOutPutResultItemCode(outputDto, KEY_TYPE1));
                }
                if (StringUtil.isBlank(fpt.getType2())) {
                    fpt.setType2(XmlRelationUtil.getVALOutPutResultItemCode(outputDto, KEY_ACCOUNTING_TYPE));
                }
                if (StringUtil.isBlank(fpt.getType3())) {
                    fpt.setType3(XmlRelationUtil.getVALOutPutResultItemCode(outputDto, KEY_TAX_ASSET_TYPE));
                }
                fpt.setFinprodId(entity2.getFinprodId());
                fpt.setBranch(0L);
                fpt.setType6(type6);
                fpt.setType7(type7);
                fpt.setType8(type8);
                fpt.setType9(type9);
                fpt.setType10(type10);
                fpt.setType11(type11);
                fpt.setType12(type12);
                fpt.setCreateUser(CommonUtil.getUserName());
                fpt.setUpdateUser(CommonUtil.getUserName());
                fpt.setCreateDept(CommonUtil.getDeptCode());
                fpt.setCreateTime(DateUtil.now());
                fpt.setUpdateTime(DateUtil.now());
                finProductTypeMapper.insert(fpt);
            }
        }
    }
}
