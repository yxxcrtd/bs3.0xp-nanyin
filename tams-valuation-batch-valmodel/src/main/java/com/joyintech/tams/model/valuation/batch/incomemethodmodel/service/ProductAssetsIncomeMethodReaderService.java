package com.joyintech.tams.model.valuation.batch.incomemethodmodel.service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.constant.ModelDictCodeList;
import com.joyintech.tams.model.valuation.batch.calculation.constant.GlobalConstant;
import com.joyintech.tams.model.valuation.batch.incomemethodmodel.dao.IncomeMethodMapper;
import com.joyintech.tams.model.valuation.batch.incomemethodmodel.dto.*;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchParams;
import com.joyintech.tams.valuation.common.dto.modelDto.*;
import com.joyintech.tams.valuation.component.batch.service.ModParamsService;
import com.joyintech.tams.valuation.component.batch.service.ModValuationCommonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description:收益法估值批处理Service
 * @auther: yuzj
 * @date: 2021/7/6
 * @param:
 * @return:
 */
@Service
public class ProductAssetsIncomeMethodReaderService {
    @Autowired
    private IncomeMethodMapper incomeMethodMapper;
    //引入共通service
    @Autowired
    private ModValuationCommonService modValuationCommonService;
    @Autowired
    private ModParamsService modParamsService;

    /**
     * @Description:获取待处理资产信息List
     * @auther: yuzj
     * @date: 2021/7/5
     * @param:
     * @return:
     */
    public List<ProductAssetsIncomeMethodReaderDto> selectProductAssets(Map<String, Object> params){
        Date plateDate = (Date)params.get("PLATE_DATE");//平台日
        List<ProductAssetsIncomeMethodReaderDto> productAssetsIncomeMethodReaderDtoList = new ArrayList<ProductAssetsIncomeMethodReaderDto>();
        List<ProductAssetsIncomeMethodReaderDto> preReaderDtoList = new ArrayList<ProductAssetsIncomeMethodReaderDto>();
        //1.查询资产映射（返回资产分类五级分类集合）
        List<String> fiveTypeList = new ArrayList<String>();
        fiveTypeList = selectFiveTypeList();

        //2.调用【资产基本信息查询-估值模型】方法查询有持仓的资产
        preReaderDtoList = selectIncomeMethodDtoList(fiveTypeList,params);

        //过滤（帅选出符合查询条件的资产信息集合）
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)params.get("BATCH_INFO");
        List<ModValBatchParams> listBatch = modParamsService.selectByBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
        //1.读取需要进行处理的资产
        //资产代码集合
        List<String> finprodIdList = new ArrayList<String>();
        if (listBatch != null && listBatch.size() > 0) {
            for (ModValBatchParams params1 : listBatch) {
                finprodIdList.add(params1.getFinprodId());
            }
        }

        for(ProductAssetsIncomeMethodReaderDto preInComeDto: preReaderDtoList){
            if(finprodIdList.contains(preInComeDto.getFinprodId())){
                productAssetsIncomeMethodReaderDtoList.add(preInComeDto);
            }
        }

        //3.设定资产其他相关信息-数据组装
        for(int i=0;i<productAssetsIncomeMethodReaderDtoList.size();i++){
            String isSus = productAssetsIncomeMethodReaderDtoList.get(i).getIsSus();            // 是否永续
            String assetType5 = productAssetsIncomeMethodReaderDtoList.get(i).getAssetType5();  // 资产五级分类_优先股
            if((GlobalConstant.YES.equals(productAssetsIncomeMethodReaderDtoList.get(i).getIsRight()) && GlobalConstant.YES.equals(isSus) && !ModelDictCodeList.资产五级分类_优先股.getValue().equals(assetType5))
                    || (GlobalConstant.NO.equals(isSus) && !ModelDictCodeList.资产五级分类_优先股.getValue().equals(assetType5))
            ){
                //3.0过滤含权永续债
                productAssetsIncomeMethodReaderDtoList.remove(i);
                i--;
            }else{
                //3.1组装 预期股息增长率，调用【估值模型股息增长率配置】
                String stockIntrate = incomeMethodMapper.selectStockIntrateByFinprodId(productAssetsIncomeMethodReaderDtoList.get(i).getFinprodId());
                if(StringUtils.isNotEmpty(stockIntrate)){
                    productAssetsIncomeMethodReaderDtoList.get(i).setStockIntarte(new BigDecimal(stockIntrate));
                }
                //3.2组装-资产现金流计划集合
                productAssetsIncomeMethodReaderDtoList.get(i).setFinCashPlanDtoList(modValuationCommonService.getCashFlowPlanInfoByFinprodId(productAssetsIncomeMethodReaderDtoList.get(i).getFinprodId()));

                //3.3组装-现金流计算规则集合
                productAssetsIncomeMethodReaderDtoList.get(i).setFinCashCalcRuleDtoList(modValuationCommonService.getCashFlowCalRulesByFinprodId(productAssetsIncomeMethodReaderDtoList.get(i).getFinprodId()));

                //3.4组装-现金流支付规则集合
                List<ModCashFlowPayRulesDto> modCashFlowPayRulesList = modValuationCommonService.getCashFlowPayRulesByFinprodId(productAssetsIncomeMethodReaderDtoList.get(i).getFinprodId());
                productAssetsIncomeMethodReaderDtoList.get(i).setModCashFlowPayRulesList(modCashFlowPayRulesList);

            }
        }
        return productAssetsIncomeMethodReaderDtoList;
    }


    /**
     * @Description:根据数据字典.模型映射类型.资产分类+模型证券品种.债券+数据字典.模型证券品种细分类(部分值)
     * 获取资产分类的五级分类集合 List<String>
     * @auther: yuzj
     * @date: 2021/7/7
     * @param:
     * @return:
     */
    public List<String> selectFiveTypeList(){
        //待传参数 DictCode.资产类型;
        List<String> assetsFiveTypeList = new ArrayList<String>();
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_他行发行的同业存单.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_国债.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_地方政府债.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_央票.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_政策性金融债.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_政策性金融次级债.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_铁道债.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_金融债.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_金融机构次级债.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_企业债.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_公司债.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_交易所私募债.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_短融.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_超短融.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_中票.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_银行间私募债.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_交易商协会ABN.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_银保监会主管ABS.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_证监会主管ABS.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_外国债券.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_理财直接融资工具.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_其他理财直接融资工具.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_上市银行优先股.getValue()); // input2 上市银行优先股  =可在 基础数据资产信息映射关系表查
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_非上市银行优先股.getValue()); // input2 非上市银行优先股 =可在 基础数据资产信息映射关系表查
        assetsFiveTypeList = incomeMethodMapper.selectFiveTypeList(ModelDictCodeList.模型_映射类型_资产分类映射.getValue(),
                ModelDictCodeList.模型_证券品种_债券.getValue(),assetsFiveTypeList);
        return assetsFiveTypeList;
    }

    /**
     * @Description:调用【资产基本信息查询-估值模型】方法查询有持仓的资产
     * @auther: yuzj
     * @date: 2021/7/7
     * @param:
     * @return:
     */
    public List<ProductAssetsIncomeMethodReaderDto>  selectIncomeMethodDtoList(List<String> fiveTypeList, Map<String, Object> mapParam){
        List<ProductAssetsIncomeMethodReaderDto> productAssetsIncomeMethodReaderDtoList = new ArrayList<ProductAssetsIncomeMethodReaderDto>();
        //获取共同中的资产信息
        ModAssetInfoParamsDto paraMap = new ModAssetInfoParamsDto();
        Date plateDate = (Date)mapParam.get("PLATE_DATE");//平台日
        paraMap.setAssetFiveList(fiveTypeList);
        paraMap.setSearchDate(plateDate);//查询日=平台日
        List<ModAssetInfoDto> modAssetInfoDtoList = modValuationCommonService.selectAssetInfo(paraMap);
        for(ModAssetInfoDto modAssetInfoDto:modAssetInfoDtoList){
            ProductAssetsIncomeMethodReaderDto inDto = new ProductAssetsIncomeMethodReaderDto();
            inDto.setFinprodId(modAssetInfoDto.getFinprodId());//产品代码
            inDto.setFinprodAbbr(modAssetInfoDto.getFinprodAbbr());//产品简称
            inDto.setFinprodMarketId(modAssetInfoDto.getFinprodMarketId());//市场代码
            inDto.setVdate(modAssetInfoDto.getVdate());//起息日
            inDto.setMdate(modAssetInfoDto.getMdate());//到期日
            inDto.setIssuer(modAssetInfoDto.getIssuer());//发行人
            inDto.setFinancie(modAssetInfoDto.getFinancier());//融资人
            inDto.setSponsor(modAssetInfoDto.getSponsor());//担保人
            inDto.setIsSus(modAssetInfoDto.getIsSus());//是否永续
            inDto.setIsRight(modAssetInfoDto.getIsRight());//是否含权
            inDto.setConShareId(modAssetInfoDto.getConFinprodId());//转股代码
            inDto.setConvdate(modAssetInfoDto.getConVdate());//转股起始日
            inDto.setPbondvalue(modAssetInfoDto.getConPrice());//转股价格
            inDto.setActualCreditProvid(modAssetInfoDto.getActualCreaditProvid());//实际授信主体
            inDto.setAssetType1(modAssetInfoDto.getAssetOne());//资产一级分类
            inDto.setAssetType2(modAssetInfoDto.getAssetTwo());//资产二级分类
            inDto.setAssetType3(modAssetInfoDto.getAssetThree());//资产三级分类
            inDto.setAssetType4(modAssetInfoDto.getAssetFour());//资产四级分类
            inDto.setAssetType5(modAssetInfoDto.getAssetFive());//资产五级分类
            inDto.setLatestTradeDate(modAssetInfoDto.getLastTradeInDate());//最近买入交易发生日
            inDto.setLatestAverPrice(modAssetInfoDto.getLastTradeAvgPrice());//最近买入交易日交易均价
            inDto.setIsFinLicenceIssuer(modAssetInfoDto.getIssueFinLicence());//发行人是否持有金融牌照
            inDto.setIsFinLicenceFinancie(modAssetInfoDto.getFinancierFinLicence());//融资人是否持有金融牌照
            inDto.setIsFinLicenceSponsor(modAssetInfoDto.getSponsorFinLicence());//担保人是否持有金融牌照
            inDto.setIsFinLicenceActualCredit(modAssetInfoDto.getActualCreditFinLicence());//实际授信主体是否持有金融牌照
            inDto.setIssuePrice(modAssetInfoDto.getIssuePrice());//发行价
            inDto.setCouponSpecies(modAssetInfoDto.getCouponSpecies());//息票品种
            productAssetsIncomeMethodReaderDtoList.add(inDto);
        }
        return productAssetsIncomeMethodReaderDtoList;
    }




}
