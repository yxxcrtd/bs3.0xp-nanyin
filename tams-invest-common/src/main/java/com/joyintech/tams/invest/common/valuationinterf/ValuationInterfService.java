/*
 * 文件名：ValuationInterfService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2019年1月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.valuationinterf;

import com.jixu.common.type.ResultContainer;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.TrdCommDto;
import com.joyintech.tams.invest.common.util.ValItemValueGenUtil;
import com.joyintech.tams.invest.common.valuationinterf.dao.ValuationInterfMapper;
import com.joyintech.tams.invest.common.valuationinterf.dto.BokTradeBuildInputDto;
import com.joyintech.tams.invest.common.valuationinterf.dto.BussFinProductFundAccountInfoDto;
import com.joyintech.tams.invest.common.valuationinterf.dto.InterBorrowBaseInfoInitInputDto;
import com.joyintech.tams.invest.common.valuationinterf.dto.ProdOtherInfoInitInputDto;
import com.joyintech.tams.valuation.api.service.ValInterfCommonServiceApi;
import com.joyintech.tams.valuation.common.dto.ApiTrdCommDto;
import com.joyintech.tams.valuation.common.interf.base.dto.ChannelProdDeal;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.interf.base.service.dealcommon.DealCommonService;
import com.joyintech.tams.valuation.common.interf.common.ResultCode;
import com.joyintech.tams.valuation.common.interf.common.ResultObject;
import com.joyintech.tams.valuation.common.interf.val.dto.ProdInfoDto;
import org.jasypt.commons.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * 估值接口Service
 *
 * @author etliu
 * @version 1.0 2019年1月12日
 * @see ValuationInterfService
 * @since 1.0
 */
@Service
public class ValuationInterfService {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ValuationInterfService.class);

    /**
     * ValInterfCommonServiceApi
     */
    @Autowired
    private ValInterfCommonServiceApi valInterfCommonServiceApi;
    
    @Autowired
    private DealCommonService dealCommonService;
    
    /**
     * InterfCommonService
     */
    @Autowired
    private InterfCommonService interfCommonService;

    /**
     * ValuationInterfMapper
     */
    @Autowired
    private ValuationInterfMapper valuationInterfMapper;

    /**
     * FinProductMapper
     */
    @Autowired
    private FinProductMapper finProductMapper;

    /**
     * FinProductAddMapper
     */
    @Autowired
    private FinProductAddMapper finProductAddMapper;

    /**
     * PrdValParamMapper
     */
    @Autowired
    private PrdValParamMapper prdValParamMapper;
    
    /**
     * PtlPortfolioMapper
     */
    @Autowired
    private PtlPortfolioMapper ptlPortfolioMapper;
    
    /**
     * PaFinProductMapper
     */
    @Autowired
    private PaFinProductMapper paFinProductMapper;
    
    @Autowired
    private FinProductAddMapper finProductMapperAdd;
    
    /**
     * 根据组合代码，查询该组合下的产品
     *
     * @param portfolioId 组合代码
     * @return 该组合下的产品
     * @see
     * @since 1.0
     */
    public String selectFinprodIdByPortfolioId(String portfolioId) {
        return valuationInterfMapper.selectFinprodIdByPortfolioId(portfolioId);
    }
    
    /**
     * 产品其他信息初始化
     *
     * @param inputDto 输入参数
     * @return 执行结果
     * @see
     * @since 1.0
     */
    public ResultObject prodOtherInfoInit(ProdOtherInfoInitInputDto inputDto) {
    	ResultObject resultObject = new ResultObject(ResultCode.SUCCESS);
        
        // 组合新建
        if (StringUtil.equals("PtlManagementInfo002", inputDto.getPageId())) {
            // 新建非修改场合
            if (inputDto.isNewData()) {
                // 净值型、货币型投资组合创建生效时不调用接口【产品其他信息初始化】进行账套信息等相关信息的初始化
                if (StringUtil.equals(inputDto.getPtlPortfolio().getPortfolioType(), DictCodeListExt1.组合类型_子组合.getValue())
                    || StringUtil.equals(DictCodeList.收益类型_收益型.getValue(), inputDto.getPtlPortfolio().getProfitType())) {
                    logger.info("调用组合对应【估值产品其他信息初始化】接口处理开始-----------------------");
                    resultObject = this.prodOtherInfoInitToVal(inputDto);
                    logger.info("调用组合对应【估值产品其他信息初始化】接口处理结束-----------------------");
                    if (StringUtil.equals(resultObject.getResultCode(),ResultCode.FAILURE.getCode()) ){
                        return resultObject;
                    }
                }
                
                String finprodId = this.selectFinprodIdByPortfolioId(inputDto.getPtlPortfolio().getPortfolioId());
                if (StringUtil.isNotBlank(finprodId)) {
                    FinProduct finProduct = finProductMapper.selectByPrimaryKey(finprodId);
                    FinProductAdd finProductAdd = finProductAddMapper.selectByPrimaryKey(finprodId);
                    PrdValParam prdValParam = prdValParamMapper.selectByPrimaryKey(finprodId);
                    
                    ProdOtherInfoInitInputDto prodInputDto = new ProdOtherInfoInitInputDto();
                    prodInputDto.setPageId("FinProductBuild002");
                    prodInputDto.setFinProduct(finProduct);
                    prodInputDto.setFinProductAdd(finProductAdd);
                    prodInputDto.setPrdValParam(prdValParam);
                    prodInputDto.setProdAcctDataList(inputDto.getProdAcctDataList());
                    logger.info("调用组合下产品对应【估值产品其他信息初始化】接口处理开始-----------------------");
                    resultObject = this.prodOtherInfoInitToVal(prodInputDto);
                    logger.info("调用组合下产品对应【估值产品其他信息初始化】接口处理结束-----------------------");
                    // added by wangzhiang 净值型周期型产品，只核算母产品 20191016 start
                    if (prodInputDto.getFinProductAdd() != null && StringUtil.equals(DictCodeList.收益类型_净值型.getValue(),
                        inputDto.getPtlPortfolio().getProfitType()) && StringUtil.equals(
                        DictCodeList.处理模式_周期型.getValue(), prodInputDto.getFinProductAdd().getDealMode())) {
                        return resultObject;
                    }
                    // added by wangzhiang 净值型周期型产品，只核算母产品 20191016 end
                    //查询分层子产品
                    List<String> layerFinprodids = valuationInterfMapper.selectLayerFinprodIdByPfinprodId(finprodId);
                    if(!StringUtil.isEmptyList(layerFinprodids)){
                        for (String layerFinprodid : layerFinprodids) {
                            FinProduct layerfinProduct = finProductMapper.selectByPrimaryKey(layerFinprodid);
//                            FinProductAdd layerfinProductAdd = finProductAddMapper.selectByPrimaryKey(layerFinprodid);
                            PrdValParam layerprdValParam = prdValParamMapper.selectByPrimaryKey(layerFinprodid);
                            ProdOtherInfoInitInputDto layerprodInputDto = new ProdOtherInfoInitInputDto();
                            if (StringUtil.equals(DictCodeList.收益类型_收益型.getValue(),
                            		layerfinProduct.getProfitType())) {
                            	layerprodInputDto.setPageId("FinProductBuild002");
                            	FinProductAdd layerfinProductAdd = finProductAddMapper.selectByPrimaryKey(layerFinprodid);
                            	layerprodInputDto.setFinProductAdd(layerfinProductAdd);
                            } else {
                            	layerprodInputDto.setPageId("FinNetValueProductBuild002");
                            }
                            
                            layerprodInputDto.setFinProduct(layerfinProduct);
//                            prodInputDto.setFinProductAdd(layerfinProductAdd);
                            layerprodInputDto.setPrdValParam(layerprdValParam);
                            layerprodInputDto.setProdAcctDataList(inputDto.getProdAcctDataList());
                            layerprodInputDto.setIsLayer(DictCodeList.是否_是.getValue());
                            logger.info("调用母产品下子产品对应【估值产品其他信息初始化】接口处理开始-----------------------");
                            resultObject = this.prodOtherInfoInitToVal(layerprodInputDto);
                            logger.info("调用母产品下子产品对应【估值产品其他信息初始化】接口处理结束-----------------------");
                        }
                    }
                }
                
                return resultObject;
            }
            else {
                // 净值型、货币型投资组合创建生效时不调用接口【产品其他信息初始化】进行账套信息等相关信息的初始化
                if (StringUtil.equals(DictCodeList.收益类型_净值型.getValue(),
                    inputDto.getPtlPortfolio().getProfitType())
                    || StringUtil.equals(DictCodeList.收益类型_货币型.getValue(),
                        inputDto.getPtlPortfolio().getProfitType())) {
                    return resultObject;
                }
            }
        }
        // 产品新建
        else if (StringUtil.equals("FinProductBuild002", inputDto.getPageId()) 
            || StringUtil.equals("FinNetValueProductBuild002", inputDto.getPageId())) {
//            // 净值型、货币型产品成立生效时调用接口【产品其他信息初始化】进行初始化
//            if (StringUtil.equals(DictCodeList.收益类型_净值型.getValue(),
//                inputDto.getFinProduct().getProfitType())
//                || StringUtil.equals(DictCodeList.收益类型_货币型.getValue(),
//                    inputDto.getFinProduct().getProfitType())) {
//                return this.prodOtherInfoInitToVal(inputDto);
//            }
            
            // 产品新建时，如果组合还没有生效，则暂时不调用【产品其他信息初始化】接口，等组合生效时，自动调用组合下面产品的接口处理
            String portfolioId = inputDto.getFinProductAdd().getPortfolioId();
            PtlPortfolio ptlPortfolio = ptlPortfolioMapper.selectByPrimaryKey(portfolioId);
            if (ptlPortfolio == null) {
                logger.info("该产品对应的组合尚未创建成功，暂时不调用【产品其他信息初始化】接口，等组合生效时，自动调用组合下产品的接口处理-----------------------");
                return resultObject;
            }
        }
        
        // 其他场合正常调用接口处理
        resultObject = this.prodOtherInfoInitToVal(inputDto);
        
        return resultObject;
    }

    /**
     * 产品其他信息初始化
     *
     * @param inputDto 输入参数
     * @return 执行结果
     * @see
     * @since 1.0
     */
    private ResultObject prodOtherInfoInitToVal(ProdOtherInfoInitInputDto inputDto) {
        logger.info("调用【估值产品其他信息初始化】接口处理开始-----------------------");

        // 产品标的Dto
        ProdInfoDto prodInfoDto = new ProdInfoDto();
        // 金融产品账户信息Dto
        List<StlFinProductAcct> productAcctList = new ArrayList<StlFinProductAcct>();

        // 组合新建
        if (StringUtil.equals("PtlManagementInfo002", inputDto.getPageId())) {
            PtlPortfolio ptlPortfolio = inputDto.getPtlPortfolio();
            PtlValParam ptlValParam = inputDto.getPtlValParam();
            List<PtlCapacctInfo> prodAcctDataList = inputDto.getProdAcctDataList();
            // 判断组合是否挂了产品， 如果挂了产品，币种选择产品的币种   2020-01-09 begin 
            String ccy = null  ;
            /** modify by yanya on 20200121 子组合用母组合代码去查询，子组合代码是查询不到产品ID start**/
            String finprodId = null;
            if(StringUtil.equals(inputDto.getPtlPortfolio().getPortfolioType(), DictCodeListExt1.组合类型_子组合.getValue())
                && StringUtil.isNotEmpty(inputDto.getPtlPortfolio().getParentPortfolioId())) {
                //根据母组合查询
                finprodId = this.selectFinprodIdByPortfolioId(inputDto.getPtlPortfolio().getParentPortfolioId());
            }
            else {
                finprodId = this.selectFinprodIdByPortfolioId(inputDto.getPtlPortfolio().getPortfolioId());
            }
            /** modify by yanya on 20200121 子组合用母组合去查询，子组合查询不到产品ID end**/
            if (StringUtil.isNotBlank(finprodId)) {
                FinProduct finProduct = finProductMapper.selectByPrimaryKey(finprodId);
                if(null != finProduct ){
                	ccy = finProduct.getCcy() ;
                }
            }
            // 判断组合是否挂了产品， 如果挂了产品，币种选择产品的币种   2020-01-09 end
            prodInfoDto.setFinprodId(ptlPortfolio.getPortfolioId()); // 金融产品代码
            prodInfoDto.setFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue()); // 金融产品类型
            prodInfoDto.setFinprodAbbr(ptlPortfolio.getPortfolioName()); // 金融产品简称
            prodInfoDto.setFinprodName(ptlPortfolio.getPortfolioName()); // 金融产品名称
            prodInfoDto.setpFinprodId(null);
            prodInfoDto.setProfitType(ptlPortfolio.getProfitType()); // 收益类型
            prodInfoDto.setVdate(ptlPortfolio.getVdate()); // 起息日
            prodInfoDto.setMdate(ptlPortfolio.getMdate()); // 到期日
            prodInfoDto.setCcy(ccy); // 币种
            prodInfoDto.setCalendarId(null); // 交易日历
            prodInfoDto.setIsLayerAccount(DictCodeList.是否_是.getValue()); // 是否分层核算
            prodInfoDto.setPortfolioId(null); // 投资组合代码
            prodInfoDto.setIsAccount(DictCodeList.是否_是.getValue()); // 是否核算
            prodInfoDto.setIsTax(DictCodeList.是否_是.getValue()); // 是否计税
            prodInfoDto.setValuationTemplate(DictCodeListExt1.估值表模板_理财产品模板_系统推荐.getValue()); // 估值表模板 默认

            // 设置账套参数Map
            PrdValParam prdValParam = new PrdValParam();
            BeanUtil.copyPropertiesIgnoreNull(prdValParam, ptlValParam);
            this.genMapBooksetParam(prodInfoDto, prdValParam);
            
            /**ADD BY WUJIE ON 20191116 START**/
            //子组合核算信息初始化时：不活期存款计提、不计税、不分层
            if(StringUtil.equals(inputDto.getPtlPortfolio().getPortfolioType(), DictCodeListExt1.组合类型_子组合.getValue())){
            	prodInfoDto.setIsLayerAccount(DictCodeList.是否_否.getValue()); // 是否分层核算
            	prodInfoDto.setIsTax(DictCodeList.是否_否.getValue()); // 是否计税
            	prodInfoDto.getMapBooksetParam().put("DEPOSIT_ACCOUT", DictCodeList.是否_是.getValue());
            }
            /**ADD BY WUJIE ON 20191116 END**/

            // 获取金融产品账户信息
            productAcctList = this.genProductAcctList(prodAcctDataList,
                ptlPortfolio.getPortfolioId(), DictCodeListExt1.金融产品类型_投资组合.getValue());
        }
        // 产品新建
        else if (StringUtil.equals("FinProductBuild002", inputDto.getPageId())) {
            FinProduct finProduct = inputDto.getFinProduct();
            FinProductAdd finProductAdd = inputDto.getFinProductAdd();
            PrdValParam prdValParam = inputDto.getPrdValParam();
            String finprodType = finProduct.getFinprodType();
            if (StringUtil.isBlank(finprodType)) {
                finprodType = DictCodeListExt1.金融产品类型_理财产品.getValue();
            }
            //modify by jiangpeng on 20210401 TISHKBS-1952 用于处理产品编辑时覆盖 BOK_BOOKSET_CAPACCT_INFO(账套清算账户表)的问题
            prodInfoDto.setProductEdit(inputDto.isProductEdit());
            prodInfoDto.setFinprodId(finProduct.getFinprodId()); // 金融产品代码
            prodInfoDto.setFinprodType(finprodType); // 金融产品类型
            // 金融产品简称
            prodInfoDto.setFinprodAbbr(finProduct.getFinprodAbbr());
            if (StringUtil.isBlank(prodInfoDto.getFinprodAbbr())) {
                prodInfoDto.setFinprodAbbr(finProduct.getFinprodName());
            }
            prodInfoDto.setFinprodName(finProduct.getFinprodName()); // 金融产品名称
            prodInfoDto.setpFinprodId(finProduct.getpFinprodId());
            prodInfoDto.setProfitType(finProduct.getProfitType()); // 收益类型
            prodInfoDto.setVdate(finProduct.getVdate()); // 起息日
            prodInfoDto.setMdate(finProduct.getMdate()); // 到期日
            prodInfoDto.setCcy(finProduct.getCcy()); // 币种
            prodInfoDto.setCalendarId(finProduct.getCalendarId()); // 交易日历
            prodInfoDto.setIsLayerAccount(DictCodeList.是否_否.getValue()); // 是否分层核算
            prodInfoDto.setPortfolioId(finProductAdd.getPortfolioId()); // 投资组合代码
            prodInfoDto.setIsAccount(DictCodeList.是否_否.getValue()); // 是否核算
            prodInfoDto.setIsTax(DictCodeList.是否_否.getValue()); // 是否计税
            // 净值型、货币型产品成立生效时调用接口【产品其他信息初始化】进行初始化 是否核算 = Y  
            if (StringUtil.equals(DictCodeList.收益类型_净值型.getValue(),
                finProduct.getProfitType())
                || StringUtil.equals(DictCodeList.收益类型_货币型.getValue(),
                    finProduct.getProfitType())) {
                prodInfoDto.setPortfolioId(null); // 投资组合代码
                prodInfoDto.setIsAccount(DictCodeList.是否_是.getValue()); // 是否核算
                prodInfoDto.setIsTax(DictCodeList.是否_是.getValue()); // 是否计税
                PaFinProduct paFinProduct = paFinProductMapper.selectByPrimaryKey(finProduct.getFinprodId());
                if(paFinProduct != null && StringUtil.equals(DictCodeList.处理模式_分档.getValue(), paFinProduct.getDealMode())){
                    prodInfoDto.setIsLayerAccount(DictCodeList.是否_是.getValue()); // 是否分层核算
                }
            }
            
            prodInfoDto.setValuationTemplate(DictCodeListExt1.估值表模板_理财产品模板_系统推荐.getValue());  // 估值表模板 默认

            // 设置账套参数Map
            this.genMapBooksetParam(prodInfoDto, prdValParam);

            // // 获取金融产品账户信息
            // productAcctList = this.genProductAcctList(finProductAdd.getPortfolioId(),
            // finProduct.getFinprodId(), finprodType);
            
            // 净值型、货币型产品成立生效时调用接口【产品其他信息初始化】进行初始化 是否核算 = Y 产品账户信息
            if (StringUtil.equals(DictCodeList.收益类型_净值型.getValue(),
                finProduct.getProfitType())
                || StringUtil.equals(DictCodeList.收益类型_货币型.getValue(),
                    finProduct.getProfitType())) {
                // 获取金融产品账户信息
                productAcctList = this.genProductAcctList(finProductAdd.getPortfolioId(),
                    finProduct.getFinprodId(), finprodType, inputDto.getProdAcctDataList());
                if (productAcctList == null || productAcctList.isEmpty()) {
                    productAcctList = this.genFinProductAcctList(finProductAdd.getPortfolioId(),
                            finProduct.getFinprodId(), finprodType, inputDto.getFinProdAcctDataList());
                }
            }
        }
        // 产品净值分档产品新建
        else if (StringUtil.equals("FinNetValueProductBuild002", inputDto.getPageId())) {
            FinProduct finProduct = inputDto.getFinProduct();
            FinProductAdd finProductAdd = inputDto.getFinProductAdd();
            PrdValParam prdValParam = inputDto.getPrdValParam();
            String finprodType = finProduct.getFinprodType();
            if (StringUtil.isBlank(finprodType)) {
                finprodType = DictCodeListExt1.金融产品类型_理财产品.getValue();
            }
            //modify by jiangpeng on 20210401 TISHKBS-1952 用于处理产品编辑时覆盖 BOK_BOOKSET_CAPACCT_INFO(账套清算账户表)的问题
            prodInfoDto.setProductEdit(inputDto.isProductEdit());
            prodInfoDto.setFinprodId(finProduct.getFinprodId()); // 金融产品代码
            prodInfoDto.setFinprodType(finprodType); // 金融产品类型
            // 金融产品简称
            prodInfoDto.setFinprodAbbr(finProduct.getFinprodAbbr());
            if (StringUtil.isBlank(prodInfoDto.getFinprodAbbr())) {
                prodInfoDto.setFinprodAbbr(finProduct.getFinprodName());
            }
            prodInfoDto.setFinprodName(finProduct.getFinprodName()); // 金融产品名称
            prodInfoDto.setpFinprodId(finProduct.getpFinprodId());
            prodInfoDto.setProfitType(finProduct.getProfitType()); // 收益类型
            prodInfoDto.setVdate(finProduct.getVdate()); // 起息日
            prodInfoDto.setMdate(finProduct.getMdate()); // 到期日
            prodInfoDto.setCcy(finProduct.getCcy()); // 币种
            prodInfoDto.setCalendarId(finProduct.getCalendarId()); // 交易日历
            prodInfoDto.setIsLayerAccount(DictCodeList.是否_否.getValue()); // 是否分层核算
//            prodInfoDto.setPortfolioId(finProductAdd.getPortfolioId()); // 投资组合代码
            prodInfoDto.setIsAccount(DictCodeList.是否_否.getValue()); // 是否核算
            prodInfoDto.setIsTax(DictCodeList.是否_否.getValue()); // 是否计税
            prodInfoDto.setValuationTemplate(DictCodeListExt1.估值表模板_理财产品模板_系统推荐.getValue()); // 估值表模板
                                                                                             // 默认
            // 设置账套参数Map
            this.genMapBooksetParam(prodInfoDto, prdValParam);

            //modify by wm 分层产品不用获取资金账户信息 start 2019-08-01
            if ((StringUtil.equals(DictCodeList.收益类型_净值型.getValue(),
                    finProduct.getProfitType())
                    || StringUtil.equals(DictCodeList.收益类型_货币型.getValue(),
                    finProduct.getProfitType()))&&!StringUtil.equals(inputDto.getIsLayer(), DictCodeList.是否_是.getValue())) {
                // 获取金融产品账户信息
                productAcctList = this.genProductAcctList(finProductAdd.getPortfolioId(),
                        finProduct.getFinprodId(), finprodType, inputDto.getProdAcctDataList());
            }
            //modify by wm 分层产品不用获取资金账户信息 end 2019-08-01
        }
        // 产品报价新建
        else if (StringUtil.equals("FinProductQuotation002", inputDto.getPageId())) {
            FinProduct finProduct = inputDto.getFinProduct();
            String pFinprodId = finProduct.getpFinprodId(); // 母金融产品代码
            FinProductAdd finProductAdd = finProductAddMapper.selectByPrimaryKey(pFinprodId);
            PrdValParam prdValParam = prdValParamMapper.selectByPrimaryKey(pFinprodId);

            prodInfoDto.setFinprodId(finProduct.getFinprodId()); // 金融产品代码
            prodInfoDto.setFinprodType(finProduct.getFinprodType()); // 金融产品类型
            // 金融产品简称
            prodInfoDto.setFinprodAbbr(finProduct.getFinprodAbbr());
            if (StringUtil.isBlank(prodInfoDto.getFinprodAbbr())) {
                prodInfoDto.setFinprodAbbr(finProduct.getFinprodName());
            }
            prodInfoDto.setFinprodName(finProduct.getFinprodName()); // 金融产品名称
            prodInfoDto.setpFinprodId(finProduct.getpFinprodId());
            prodInfoDto.setProfitType(finProduct.getProfitType()); // 收益类型
            prodInfoDto.setVdate(finProduct.getVdate()); // 起息日
            prodInfoDto.setMdate(finProduct.getMdate()); // 到期日
            prodInfoDto.setCcy(finProduct.getCcy()); // 币种
            prodInfoDto.setCalendarId(finProduct.getCalendarId()); // 交易日历
            prodInfoDto.setIsLayerAccount(DictCodeList.是否_否.getValue()); // 是否分层核算
            prodInfoDto.setPortfolioId(finProductAdd.getPortfolioId()); // 投资组合代码
            prodInfoDto.setIsAccount(DictCodeList.是否_否.getValue()); // 是否核算
            prodInfoDto.setIsTax(DictCodeList.是否_否.getValue()); // 是否计税
            prodInfoDto.setValuationTemplate(DictCodeListExt1.估值表模板_理财产品模板_系统推荐.getValue());  // 估值表模板 默认

            // 设置账套参数Map
            this.genMapBooksetParam(prodInfoDto, prdValParam);

            // // 获取金融产品账户信息
            // productAcctList = this.genProductAcctList(finProductAdd.getPortfolioId(),
            // finProduct.getFinprodId(), finProduct.getFinprodType());
        }
        // 产品成立 周期型产品申购
        else if (StringUtil.equals("FinProductEstablish002", inputDto.getPageId())) {
            com.joyintech.tams.common.base.entity.FinTradeProduct finTradeProduct = inputDto.getFinTradeProduct();
            
            String finprodId = finTradeProduct.getFinprodId();
            FinProduct finProduct = finProductMapper.selectByPrimaryKey(finprodId);
            FinProductAdd finProductAdd = finProductAddMapper.selectByPrimaryKey(finprodId);
            PrdValParam prdValParam = prdValParamMapper.selectByPrimaryKey(finprodId);
            
            if (finProductAdd == null && StringUtil.isNotBlank(finProduct.getpFinprodId())) {
                finProductAdd = finProductAddMapper.selectByPrimaryKey(finProduct.getpFinprodId());
            }
            if (prdValParam == null && StringUtil.isNotBlank(finProduct.getpFinprodId())) {
                prdValParam = prdValParamMapper.selectByPrimaryKey(finProduct.getpFinprodId());
            }
            
            prodInfoDto.setFinprodId(finprodId); // 金融产品代码
            prodInfoDto.setFinprodType(DictCodeListExt1.金融产品类型_理财子产品.getValue()); // 金融产品类型
            
            if (finProduct != null) {
                prodInfoDto.setFinprodName(finProduct.getFinprodName() + finTradeProduct.getBranch()); // 金融产品名称
                // 金融产品简称
                if (StringUtil.isBlank(prodInfoDto.getFinprodAbbr())) {
                    prodInfoDto.setFinprodAbbr(prodInfoDto.getFinprodName());
                }
                else {
                    prodInfoDto.setFinprodAbbr(finProduct.getFinprodAbbr() + finTradeProduct.getBranch());
                }
                prodInfoDto.setpFinprodId(finProduct.getpFinprodId());
                prodInfoDto.setProfitType(finProduct.getProfitType()); // 收益类型
                prodInfoDto.setCalendarId(finProduct.getCalendarId()); // 交易日历
            }
            
            if (StringUtil.equals(DictCodeListExt1.金融产品类型_理财子产品.getValue(),
                prodInfoDto.getFinprodType()) && StringUtil.isBlank(prodInfoDto.getpFinprodId())) {
                prodInfoDto.setpFinprodId(finprodId);
            }
            
            prodInfoDto.setVdate(finTradeProduct.getVdate()); // 起息日
            prodInfoDto.setMdate(finTradeProduct.getMdate()); // 到期日
            prodInfoDto.setCcy(finTradeProduct.getCcy()); // 币种
            
            prodInfoDto.setIsLayerAccount(DictCodeList.是否_否.getValue()); // 是否分层核算
            if (finProductAdd != null) {
                prodInfoDto.setPortfolioId(finProductAdd.getPortfolioId()); // 投资组合代码
            }
            prodInfoDto.setIsAccount(DictCodeList.是否_否.getValue()); // 是否核算
            prodInfoDto.setIsTax(DictCodeList.是否_否.getValue()); // 是否计税
            prodInfoDto.setValuationTemplate(DictCodeListExt1.估值表模板_理财产品模板_系统推荐.getValue());  // 估值表模板 默认
            
            // 设置账套参数Map
            this.genMapBooksetParam(prodInfoDto, prdValParam);
        }

        // 币种如果为空时，默认人民币
        if (StringUtil.isBlank(prodInfoDto.getCcy())) {
            prodInfoDto.setCcy(DictCodeList.币种_人民币_CNY.getValue());
        }
        // 交易日历为空时，默认传中国法定节假日
        if (StringUtil.isBlank(prodInfoDto.getCalendarId())) {
            prodInfoDto.setCalendarId(BussinessConstant.CALENDARID_CHINA_HOLIDAYS);
        }

        ResultObject resultObject = valInterfCommonServiceApi.prodOtherInfoInit(prodInfoDto,
            productAcctList);

        logger.info("调用【估值产品其他信息初始化】接口处理金融产品代码：" + prodInfoDto.getFinprodId());
        logger.info("调用【估值产品其他信息初始化】接口处理结果代码：" + resultObject.getResultCode());
        logger.info("调用【估值产品其他信息初始化】接口处理结果描述：" + resultObject.getResultMsg());
        logger.info("调用【估值产品其他信息初始化】接口处理结束-----------------------");

        return resultObject;
    }

    /**
     * 设置账套参数Map
     *
     * @param prodInfoDto 产品标的Dto
     * @param prdValParam 理财产品估值参数
     * @see
     * @since 1.0
     */
    private void genMapBooksetParam(ProdInfoDto prodInfoDto, PrdValParam prdValParam) {
        if (prodInfoDto == null || prdValParam == null) {
            return;
        }

        // 账套参数Map
        Map<String, String> mapBooksetParam = new HashMap<String, String>();
        if (prdValParam != null) {
            // 单位净值/万份收益小数位
            Long VAL_DEC_DIGITS = prdValParam.getUnitvalueDec() != null ? prdValParam.getUnitvalueDec() : prdValParam.getMilionProfitDec();
            if (VAL_DEC_DIGITS != null) {
                mapBooksetParam.put("VAL_DEC_DIGITS", String.valueOf(VAL_DEC_DIGITS));
            }

            // 单位净值/万份收益尾数处理方式
            String VAL_DEC_TYPE = StringUtil.isNotBlank(
                prdValParam.getUnitvalueMode()) ? prdValParam.getUnitvalueMode() : prdValParam.getMilionProfitDecMode();
            if (StringUtil.isNotBlank(VAL_DEC_TYPE)) {
                if (StringUtil.equals(DictCodeList.尾数处理方式_四舍五入.getValue(),
                    VAL_DEC_TYPE)) {
                    VAL_DEC_TYPE = DictCodeList.估值舍入规则_四舍五入.getValue();
                }
                else {
                    VAL_DEC_TYPE = DictCodeList.估值舍入规则_舍位.getValue();
                }
                mapBooksetParam.put("VAL_DEC_TYPE", VAL_DEC_TYPE);
            }

            // 利率小数位
            Long YEILD_DEC_DIGITS = prdValParam.getRateDec();
            if (YEILD_DEC_DIGITS != null) {
                mapBooksetParam.put("YEILD_DEC_DIGITS", String.valueOf(YEILD_DEC_DIGITS));
            }

            // 利率处理方式
            String YEILD_DEC_TYPE = prdValParam.getRatevalueMode();
            if (StringUtil.isNotBlank(YEILD_DEC_TYPE)) {
                if (StringUtil.equals(DictCodeList.尾数处理方式_四舍五入.getValue(),
                    YEILD_DEC_TYPE)) {
                    YEILD_DEC_TYPE = DictCodeList.估值舍入规则_四舍五入.getValue();
                }
                else {
                    YEILD_DEC_TYPE = DictCodeList.估值舍入规则_舍位.getValue();
                }
                mapBooksetParam.put("YEILD_DEC_TYPE", YEILD_DEC_TYPE);
            }

            // 托管活期利息计提
            mapBooksetParam.put("DEPOSIT_ACCOUT", prdValParam.getIsCountingCurInt());
        }
        prodInfoDto.setMapBooksetParam(mapBooksetParam);
    }
    
    /**
     * 获取金融产品账户信息
     *
     * @param portfolioId 组合代码
     * @param finprodId 金融产品代码
     * @param finprodType 金融产品类型
     * @return 金融产品账户信息
     * @see
     * @since 1.0
     */
    private List<StlFinProductAcct> genProductAcctList(String portfolioId, String finprodId, String finprodType,
    		List<PtlCapacctInfo> acctDataList) {
        if (StringUtil.isBlank(portfolioId)) {
            return new ArrayList<StlFinProductAcct>();
        }
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("portfolioId", portfolioId);
        // 账户类型   只需要传估值 资金账户类型_托管账户、资金账户类型_结算备付金、资金账户类型_交易保证金 账户
        List<String> acctTypeList = new ArrayList<String>();
        acctTypeList.add(DictCodeListExt1.资金账户类型_产品托管户.getValue());
        acctTypeList.add(DictCodeListExt1.资金账户类型_结算备付金账户.getValue());
        acctTypeList.add(DictCodeListExt1.资金账户类型_交易保证金账户.getValue());
        acctTypeList.add(DictCodeListExt1.资金账户类型_券商资金账户.getValue());
        paramMap.put("acctTypeList", acctTypeList);
        List<PtlCapacctInfo> prodAcctDataList = new ArrayList<PtlCapacctInfo>();
        if (acctDataList != null && acctDataList.size() > 0) {
        	for (PtlCapacctInfo info : acctDataList) {
        		if (info.getPortfolioId().equals(portfolioId)
        				&& (DictCodeListExt1.资金账户类型_产品托管户.getValue().equals(info.getAcctType())
        						|| DictCodeListExt1.资金账户类型_结算备付金账户.getValue().equals(info.getAcctType())
        						|| DictCodeListExt1.资金账户类型_券商资金账户.getValue().equals(info.getAcctType())
        						|| DictCodeListExt1.资金账户类型_交易保证金账户.getValue().equals(info.getAcctType()))) {
        			prodAcctDataList.add(info);
        		}
        	}
        }
        
        return this.genProductAcctList(prodAcctDataList, finprodId, finprodType);
    }

    private List<StlFinProductAcct> genFinProductAcctList(String portfolioId, String finprodId, String finprodType,
    		List<BussFinProductFundAccountInfoDto> acctDataList) {
        if (StringUtil.isBlank(portfolioId)) {
            return new ArrayList<>();
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("portfolioId", portfolioId);
        // 账户类型   只需要传估值 资金账户类型_托管账户、资金账户类型_结算备付金、资金账户类型_交易保证金 账户
        List<String> acctTypeList = new ArrayList<String>();
        acctTypeList.add(DictCodeListExt1.资金账户类型_产品托管户.getValue());
        acctTypeList.add(DictCodeListExt1.资金账户类型_结算备付金账户.getValue());
        acctTypeList.add(DictCodeListExt1.资金账户类型_交易保证金账户.getValue());
        acctTypeList.add(DictCodeListExt1.资金账户类型_券商资金账户.getValue());
//        acctTypeList.add(DictCodeListExt1.资金账户类型_上清所DVP账户.getValue());
//        acctTypeList.add(DictCodeListExt1.资金账户类型_中债登DVP账户.getValue());
        paramMap.put("acctTypeList", acctTypeList);
        List<StlFinProductAcct> prodAcctDataList = new ArrayList<>();
        if (acctDataList != null && acctDataList.size() > 0) {
        	for (BussFinProductFundAccountInfoDto info : acctDataList) {
        		if (DictCodeListExt1.资金账户类型_产品托管户.getValue().equals(info.getAcctType())
        						|| DictCodeListExt1.资金账户类型_结算备付金账户.getValue().equals(info.getAcctType())
        						|| DictCodeListExt1.资金账户类型_券商资金账户.getValue().equals(info.getAcctType())
        						|| DictCodeListExt1.资金账户类型_交易保证金账户.getValue().equals(info.getAcctType())
//        						|| DictCodeListExt1.资金账户类型_上清所DVP账户.getValue().equals(info.getAcctType())
//        						|| DictCodeListExt1.资金账户类型_中债登DVP账户.getValue().equals(info.getAcctType())
        						) {
                    StlFinProductAcct stlFinProductAcct = new StlFinProductAcct();
                    stlFinProductAcct.setFinprodId(finprodId); // 金融产品代码
                    stlFinProductAcct.setFinprodType(finprodType); // 金融产品类型
                    stlFinProductAcct.setAcctNo(info.getAcctNo()); // 账号
                    stlFinProductAcct.setCcy(info.getCcy()); // 币种
                    stlFinProductAcct.setAcctType(info.getAcctType());
        		    prodAcctDataList.add(stlFinProductAcct);
        		}
        	}
        }
        return prodAcctDataList;
    }

    /**
     * 获取金融产品账户信息
     *
     * @param prodAcctDataList 查询到的投管账户列表
     * @param finprodId 金融产品代码
     * @param finprodType 金融产品类型
     * @return 金融产品账户信息
     * @see
     * @since 1.0
     */
    private List<StlFinProductAcct> genProductAcctList(List<PtlCapacctInfo> prodAcctDataList,
                                                       String finprodId, String finprodType) {
        // 金融产品账户信息
        List<StlFinProductAcct> productAcctList = new ArrayList<StlFinProductAcct>();

        if (!StringUtil.isEmptyList(prodAcctDataList)) {
            for (PtlCapacctInfo ptlCapacctInfo : prodAcctDataList) {
              //需要过滤查询出所有的待清算款资金账户中 modify BS1.9BSBS-208【净值型产品-新建】保存生效后资金账户缺失。 modify 2019-05-17 start
                //只需要传估值 资金账户类型_托管账户、资金账户类型_结算备付金资金账户类型_交易保证金 账户
                if(!(StringUtil.equals(DictCodeListExt1.资金账户类型_产品托管户.getValue(), ptlCapacctInfo.getAcctType()) 
                    || StringUtil.equals(DictCodeListExt1.资金账户类型_结算备付金账户.getValue(),ptlCapacctInfo.getAcctType())
                    || StringUtil.equals(DictCodeListExt1.资金账户类型_交易保证金账户.getValue(),ptlCapacctInfo.getAcctType())
                    || StringUtil.equals(DictCodeListExt1.资金账户类型_券商资金账户.getValue(),ptlCapacctInfo.getAcctType()))){
                    continue;
                }
                StlFinProductAcct stlFinProductAcct = new StlFinProductAcct();
                stlFinProductAcct.setFinprodId(finprodId); // 金融产品代码
                stlFinProductAcct.setFinprodType(finprodType); // 金融产品类型
                stlFinProductAcct.setAcctNo(ptlCapacctInfo.getAcctNo()); // 账号
                stlFinProductAcct.setCcy(ptlCapacctInfo.getCcy()); // 币种
                stlFinProductAcct.setAcctType(ptlCapacctInfo.getAcctType());
//                if (StringUtil.equals(DictCodeListExt1.资金账户类型_产品托管户.getValue(),
//                    ptlCapacctInfo.getAcctType())) {
//                    stlFinProductAcct.setAcctType(DictCodeListExt1.资金账户类型_产品托管户.getValue()); // 账户类型
//                }
//                else if (StringUtil.equals(DictCodeListExt1.资金账户类型_通道账户.getValue(),
//                    ptlCapacctInfo.getAcctType())) {
//                    stlFinProductAcct.setAcctType(DictCodeListExt1.资金账户类型_通道账户.getValue()); // 账户类型
//                }
//                else if (StringUtil.equals(DictCodeListExt1.资金账户类型_券商资金账户.getValue(),
//                    ptlCapacctInfo.getAcctType())) {
//                    stlFinProductAcct.setAcctType(DictCodeListExt1.资金账户类型_结算备付金账户.getValue()); // 账户类型
//                }
//                else {
//                    continue;
//                }
                //需要g过滤查询出所有的待清算款资金账户中 modify BS1.9BSBS-208【净值型产品-新建】保存生效后资金账户缺失。 modify 2019-05-17 start
                productAcctList.add(stlFinProductAcct);
            }
        }

        return productAcctList;
    }

    /**
     * 同业借款基本信息初始化
     *
     * @param inputDto 输入参数
     * @return 执行结果
     * @see
     * @since 1.0
     */
    public void interBorrowBaseInfoInit(InterBorrowBaseInfoInitInputDto inputDto,
    		List<FinCashPlan> planlist) {
    	/*同业借款基本信息初始化：功能取消
        logger.info("调用【同业借款基本信息初始化】接口处理开始-----------------------");
        FinProduct finProduct = inputDto.getFinProduct();
        FinProductType finProductType = inputDto.getFinProductType();
        FinRateInfo finRateInfo = inputDto.getFinRateInfo();
        PaFinCashCalcRule paFinCashCalcRule = inputDto.getPaFinCashCalcRule();

        // 同业借款标的信息Dto
        FinTradeProduct finTradeProduct = new FinTradeProduct();
        finTradeProduct.setFinprodId(finProduct.getFinprodId()); // 金融产品代码
        finTradeProduct.setFinprodType(finProduct.getFinprodType()); // 金融产品类型
        finTradeProduct.setProfitType(DictCodeList.收益类型_收益型.getValue()); // 收益类型
        finTradeProduct.setBranch(finProductType.getBranch()); // 分支序号
        finTradeProduct.setBranchType(null); // 分支类型 默认为空
        finTradeProduct.setChlAgrtId(finProduct.getChlFinprodId()); // 通道代码
        finTradeProduct.setPrin(finProduct.getIssueAmt()); // 名义本金
        finTradeProduct.setCcy(finProduct.getCcy()); // 本金币种
        finTradeProduct.setVdate(finProduct.getVdate()); // 起息日
        finTradeProduct.setMdate(finProduct.getMdate()); // 到期日
        finTradeProduct.setTerm(finProduct.getTermDays()); // 期限(天)
        finTradeProduct.setTermType(null); // 期限品种
        finTradeProduct.setIntType(finRateInfo.getIntType()); // 利率类型
        finTradeProduct.setIntRate(paFinCashCalcRule.getLoadRate()); // 固定利率
        finTradeProduct.setBasis(finRateInfo.getBasis()); // 计息基础
        finTradeProduct.setmPrinAmt(null); // 到期本金 默认空
        finTradeProduct.setmIntAmt(null); // 到期利息 默认空
        // finTradeProduct.setmTradeAmt(null); // 到期金额 默认空
        BigDecimal mTradeAmt = finProduct.getIssueAmt();
        if (planlist != null && planlist.size() > 0) {
        	for (FinCashPlan plan : planlist) {
        		mTradeAmt = ArithmeticUtil.doAdd(mTradeAmt, plan.getIntAmt());
        	}
        }
        finTradeProduct.setmTradeAmt(mTradeAmt);
        finTradeProduct.setoFinprodId(null); // 原金融产品代码
        // 原金融产品类型 默认空
        // 逾期交易编号 默认空
        finTradeProduct.setCapiIncomeFeature(DictCodeListExt1.本金收益特征_保本.getValue()); // 本金收益特征
        finTradeProduct.setTradeMarket(finProduct.getTradeMarket()); // 交易场所
        finTradeProduct.setCalendarId(finProduct.getCalendarId()); // 交易日历

        // 数据动作标志
        String dataActionSign = inputDto.getDataActionSign();

        ResultObject resultObject = valInterfCommonServiceApi.interBorrowBaseInfoInit(finTradeProduct,
            dataActionSign);

        logger.info("调用【同业借款基本信息初始化】接口处理金融产品代码：" + finTradeProduct.getFinprodId());
        logger.info("调用【同业借款基本信息初始化】接口处理结果代码：" + resultObject.getResultCode());
        logger.info("调用【同业借款基本信息初始化】接口处理结果描述：" + resultObject.getResultMsg());
        logger.info("调用【同业借款基本信息初始化】接口处理结束-----------------------");
        */
    }

    /**
     * 交易入账 入账信息生成 结算指令生成
     *
     * @param inputDto 输入参数
     * @return 执行结果
     * @see
     * @since 1.0
     */
    public ResultObject bokTradeBuild(BokTradeBuildInputDto inputDto) {
        logger.info("调用【交易入账】接口处理开始-----------------------");

//        // 测试代码，债券和存款交易接口已通，其他交易暂时不处理
//        if (!(StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券.getValue(),
//            inputDto.getTrdProductDeal().getFinprodType2())
//              || StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_存款.getValue(),
//                  inputDto.getTrdProductDeal().getFinprodType2()))) {
//            return null;
//        }

        String prodid = inputDto.getProdid();
        String prodFinprodType = inputDto.getProdFinprodType();
//        Date prodVdate = inputDto.getProdVdate();
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        BeanUtil.copyPropertiesIgnoreNull(trdProductDeal, inputDto.getTrdProductDeal());

        // 重新获取金融产品类型
        ValItemValueGenUtil.genFinprodType(inputDto.getTrdProductDeal());
        trdProductDeal.setFinprodType(inputDto.getTrdProductDeal().getFinprodType());
        // 重新获取业务类型
        ValItemValueGenUtil.genBusiType(inputDto.getTrdProductDeal());
        trdProductDeal.setBusiType(inputDto.getTrdProductDeal().getBusiType());

        // 指令信息传参的时候，估值使用传过来的指令信息 add by renping 20191117 start
        StlOrders stlOrders = null;
        if (inputDto.getStlPayOrders() != null) {
            stlOrders = new StlOrders();
            BeanUtil.copyPropertiesIgnoreNull(stlOrders, inputDto.getStlPayOrders());
            stlOrders.setSettleAmt(null);//modify wujie on 20200119 remark:不使用前提资金交收指令的结算金额
        }
        // 指令信息传参的时候，估值使用传过来的指令信息 add by renping 20191117 end
        ResultObject resultObject = valInterfCommonServiceApi.bokTradeBuild(prodid, prodFinprodType,
                trdProductDeal.getBranch(), trdProductDeal,stlOrders,inputDto.getPtlTradeList(),inputDto.getTrdFinProductBarter(),
                /** add by yanya on 20200113 增加金融产品交易附属信息DTO入参**/
                inputDto.getTrdProductDealAdd());

        logger.info("调用【交易入账】接口处理交易编号：" + trdProductDeal.getTradeId());
        logger.info("调用【交易入账】接口处理结果代码：" + resultObject.getResultCode());
        logger.info("调用【交易入账】接口处理结果描述：" + resultObject.getResultMsg());
        logger.info("调用【交易入账】接口处理结束-----------------------");

        return resultObject;
    }

    /**
     * 拆入到期_新增
     *
     * @param inputDto 输入参数
     * @return 执行结果
     * @see
     * @since 1.0
     */
    public ResultObject lendMdateAdd(BokTradeBuildInputDto inputDto) {
        logger.info("调用【拆入到期_新增】接口处理开始-----------------------");

        String prodid = inputDto.getProdid();
        String prodFinprodType = inputDto.getProdFinprodType();
//        Date prodVdate = inputDto.getProdVdate();
        
        List<TrdProductDeal> trdProductDealList = inputDto.getTrdProductDealList();
        String totalTradeId = null;
        Long branch = 0l;
        if (!StringUtil.isEmptyList(trdProductDealList)) {
            for (TrdProductDeal trdProductDeal : trdProductDealList) {
                // 重新获取金融产品类型
                ValItemValueGenUtil.genFinprodType(trdProductDeal);
                // 重新获取业务类型
                ValItemValueGenUtil.genBusiType(trdProductDeal);
                
                totalTradeId = trdProductDeal.getTotleTradeId();

                branch = trdProductDeal.getBranch();
            }
        }

        StlOrders stlOrders = null;
        if(inputDto.getStlPayOrders() != null){
            stlOrders =new StlOrders();
            BeanUtil.copyPropertiesIgnoreNull(stlOrders,inputDto.getStlPayOrders());
        }

        ResultObject resultObject = valInterfCommonServiceApi.lendMdateAdd(prodid, prodFinprodType,
                branch, trdProductDealList, inputDto.getTrdTradeFeeDetail(),inputDto.getPtlTradeList(),stlOrders);

        logger.info("调用【拆入到期_新增】接口处理汇总交易编号：" + totalTradeId);
        logger.info("调用【拆入到期_新增】接口处理结果代码：" + resultObject.getResultCode());
        logger.info("调用【拆入到期_新增】接口处理结果描述：" + resultObject.getResultMsg());
        logger.info("调用【拆入到期_新增】接口处理结束-----------------------");

        return resultObject;
    }

    /**
     * 拆入到期_撤销
     *
     * @param inputDto 输入参数
     * @return 执行结果
     * @see
     * @since 1.0
     */
    public ResultObject lendMdatDelete(BokTradeBuildInputDto inputDto) {
        logger.info("调用【拆入到期_撤销】接口处理开始-----------------------");

        String totleTradeId = inputDto.getTotleTradeId();

        ResultObject resultObject = valInterfCommonServiceApi.lendMdatDelete(totleTradeId);

        logger.info("调用【拆入到期_撤销】接口处理汇总交易编号：" + totleTradeId);
        logger.info("调用【拆入到期_撤销】接口处理结果代码：" + resultObject.getResultCode());
        logger.info("调用【拆入到期_撤销】接口处理结果描述：" + resultObject.getResultMsg());
        logger.info("调用【拆入到期_撤销】接口处理结束-----------------------");

        return resultObject;
    }

    /**
     * 结算指令交易入账
     *
     * @param inputDto 输入参数
     * @return 执行结果
     * @see
     * @since 1.0
     */
    public ResultObject stlOrdersBokTradeBuild(BokTradeBuildInputDto inputDto) {
        logger.info("调用【结算指令交易入账】接口处理开始-----------------------");
        
        String prodid = inputDto.getProdid();
        String prodFinprodType = inputDto.getProdFinprodType();
        Date prodVdate = inputDto.getProdVdate();
        StlPayOrders stlPayOrders = inputDto.getStlPayOrders();
        TrdProductDeal trdProductDeal = inputDto.getTrdProductDeal();
        
        StlOrders stlOrders = new StlOrders();
        BeanUtil.copyPropertiesIgnoreNull(stlOrders, stlPayOrders);
        stlOrders.setBusiType(DictCodeList.业务类型_指令交易.getValue());
        if (StringUtil.equals(DictCodeList.交易方向_银证转账.getValue(),
            trdProductDeal.getPs())) {
            stlOrders.setOrderType(DictCodeListExt1.指令类型_银证转账_银转证.getValue());
        }
        else if (StringUtil.equals(DictCodeList.交易方向_证银转账.getValue(),
            trdProductDeal.getPs())) {
            stlOrders.setOrderType(DictCodeListExt1.指令类型_银证转账_证转银.getValue());
        }

        ResultObject resultObject = valInterfCommonServiceApi.stlOrdersBokTradeBuild(prodid, prodFinprodType, prodVdate, stlOrders,inputDto.getPtlTradeList());

        logger.info("调用【结算指令交易入账】接口处理指令编号：" + stlOrders.getOrderId());
        logger.info("调用【结算指令交易入账】接口处理结果代码：" + resultObject.getResultCode());
        logger.info("调用【结算指令交易入账】接口处理结果描述：" + resultObject.getResultMsg());
        logger.info("调用【结算指令交易入账】接口处理结束-----------------------");

        return resultObject;
    }
    
    /**
     * 几许接口
     * @return
     */
    public ResultContainer runValInvest2Jixu(TrdCommDto trdCommDto) {
        logger.info("调用【jixu】接口处理开始-----------------------");
        ApiTrdCommDto apiTrdCommDto = new ApiTrdCommDto();
        // 获取组合的本币币种的托管户
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /**ADD BY WUJIE ON 20191119 END**/
        ResultContainer resultContainer = new ResultContainer();
        /**ADD BY zhangquan ON 20200106 END**/
        if(trdCommDto.getTrdProductDealAdd()!=null && trdCommDto.getTrdProductDeal()!=null
        		&& trdCommDto.getTrdProductDeal().getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_股票.getValue())
        		&& StringUtil.isNotEmpty(trdCommDto.getTrdProductDeal().getChlId())) {
        	resultContainer = channelStockTradeData(trdCommDto);
        	return resultContainer;
        }
        if(trdCommDto.getTrdProductDealAdd()!=null && trdCommDto.getTrdProductDeal()!=null
                && CommonUtils.isNotEmpty(trdCommDto.getTrdProductDeal().getPs())
        		&& trdCommDto.getTrdProductDeal().getPs().equals(DictCodeListExt1.交易方向_债转股.getValue())
        		&& StringUtil.isNotEmpty(trdCommDto.getTrdProductDeal().getChlId())) {
        	//变量.交易共通信息DTO = DTO
        	apiTrdCommDto = channelAccountInfo(trdCommDto);
        }else {
        	//BeanUtil.copyPropertiesIgnoreNull(变量.交易共通信息DTO, 输入.交易信息DTO);
        	BeanUtil.copyPropertiesIgnoreNull(apiTrdCommDto, trdCommDto);
        }
        if (apiTrdCommDto.getTrdProductDealAdd() != null) {
            paramMap.put("portfolioId", apiTrdCommDto.getTrdProductDealAdd().getPortfolioId());
        } else if (apiTrdCommDto.getTrdFinProductDealTotal() != null) {
            paramMap.put("portfolioId", apiTrdCommDto.getTrdFinProductDealTotal().getPortfolioId());
        }
        paramMap.put("ccy", DictCodeList.币种_人民币_CNY.getValue());
        paramMap.put("acctType", DictCodeListExt2.资金账户类型_托管户.getValue());
        PtlCapacctInfo ptlCapacctInfo = valuationInterfMapper.selectProdAcctDataEx(paramMap);
        //BeanUtil.copyPropertiesIgnoreNull(apiTrdCommDto, trdCommDto);
        apiTrdCommDto.setPtlCapacctInfo(ptlCapacctInfo);
        apiTrdCommDto.setNoneTxApply(true);
        /**ADD BY WUJIE ON 20191119 START**/
        apiTrdCommDto.setPtlTradeList(apiTrdCommDto.getPtlTradeList());
//        /**ADD BY WUJIE ON 20191119 END**/
//        ResultContainer resultContainer = new ResultContainer();
//        try {
        resultContainer = valInterfCommonServiceApi.runValInvest2Jixu(apiTrdCommDto);
        //      ResultContainer resultContainer = new ResultContainer();
        if (!resultContainer.isCorrectFlag()) {
            logger.info("调用【jixu】接口错误字段：" + resultContainer.getErrorCode());
            logger.info("调用【jixu】接口错误信息描述：" + resultContainer.getErrorMsg());
            logger.debug("调用【jixu】接口错误字段：" + resultContainer.getErrorCode());
            logger.debug("调用【jixu】接口错误信息描述：" + resultContainer.getErrorMsg());
        }
//        }catch (Exception e) {
//            resultContainer.setCorrectFlag(false);
//            e.printStackTrace();
//            logger.info("调用【jixu】接口发生异常:-----------------------");
//            logger.info(e.getMessage());
//        }
        logger.info("调用【jixu】接口处理结束-----------------------");
        return resultContainer;
    }
    
    /**通道股票交易数据  add by zhangquan 20200106 end*/
    public ResultContainer channelStockTradeData(TrdCommDto trdCommDto) {
    	//定义 变量.返回结果
    	ResultContainer resultContainer = new ResultContainer();
    	//定义 变量.通道代码=输入.交易信息DTO.金融产品交易.通道代码
    	String chlAgrtId = trdCommDto.getFinTradeProduct().getChlAgrtId();
    	//定义 变量.标的类金融产品附属DTO
    	FinProductAdd finProductAdd = new FinProductAdd();
    	//定义 变量.通道费信息DTO
    	TrdTradeFeeDetail chnanelFeeDto = new TrdTradeFeeDetail();
    	//2.处理详情
    	//2.1 通道的标的类金融产品附属表数据查询
    	finProductAdd = finProductMapperAdd.selectByPrimaryKey(chlAgrtId);
    	//2.2 获取通道费用信息
    	if(trdCommDto.getTradeFeeDetaillist()!=null && trdCommDto.getTradeFeeDetaillist().size()>0) {
    		for(TrdTradeFeeDetail trdTradeFeeDetail : trdCommDto.getTradeFeeDetaillist()) {
    			if(trdTradeFeeDetail.getFeeType().equals(DictCodeListExt1.一次性费用类型_通道费.getValue())) {
    				chnanelFeeDto = trdTradeFeeDetail;
    			}
    		}
    	}
    	//2.3 初始化通道核算信息
		if(finProductAdd.getPayType().equals(DictCodeListExt2.划款方式_逐笔划款.getValue())) {
			//定义 变量.通道交易DTO
			ChannelProdDeal channelProdDeal = new ChannelProdDeal();
			//交易编号
			channelProdDeal.setTradeId(trdCommDto.getTrdProductDeal().getbTradeId());
			//产品代码
			channelProdDeal.setProdId(trdCommDto.getTrdProductDealAdd().getPortfolioId());
			//产品类型
			channelProdDeal.setProductType(DictCodeListExt1.金融产品类型_投资组合.getValue());;
			//通道代码
			channelProdDeal.setChlId(chlAgrtId);
			//交易状态
			channelProdDeal.setTradeStatus(trdCommDto.getTrdProductDeal().getTradeStatus());
			//交易方向
			channelProdDeal.setPs(trdCommDto.getTrdProductDeal().getPs());
			//交易日
			channelProdDeal.setTradeDate(trdCommDto.getTrdProductDeal().getTradeDate());
			//交割日
			channelProdDeal.setSettleDate(trdCommDto.getTrdProductDeal().getSettleDate());
			//交易本金
			channelProdDeal.setPrinAmt(trdCommDto.getTrdProductDeal().getPrinAmt());
			//交易金额
			channelProdDeal.setTradeAmt(trdCommDto.getTrdProductDeal().getTradeAmt());
			//交易份额
			channelProdDeal.setShareAmt(trdCommDto.getTrdProductDeal().getShareAmt());
			//交易场所
			channelProdDeal.setTradeMarket(trdCommDto.getTrdProductDeal().getTradeMarket());
			//币种
			channelProdDeal.setCcy(trdCommDto.getTrdProductDeal().getCcy());
			//是否结清
			channelProdDeal.setIsClean(DictCode.是否.getValue());
			//关联系统交易编号
			channelProdDeal.setfTradeId(trdCommDto.getTrdProductDeal().getbTradeId());
			//交易拆分标志
			channelProdDeal.setDealSepFlag("00");
			//组合交易表List
			channelProdDeal.setPtlTradeList(trdCommDto.getPtlTradeList());
			//是否转仓交易
			channelProdDeal.setIsSwapTransaction(trdCommDto.getIsSwapTransaction());
			//通道费DTO
			BeanUtil.copyPropertiesIgnoreNull(channelProdDeal.getChannelFeeInfo(), chnanelFeeDto);
			ResultObject resultObject = valInterfCommonServiceApi.getChannelProdDeal(channelProdDeal);	
		}
		//2.4 通道股票交易入账
		//定义 变量.交易共通信息DTO
		ApiTrdCommDto apiTrdCommDto = new ApiTrdCommDto();
		//变量.交易共通信息DTO = DTO
		apiTrdCommDto = channelAccountInfo(trdCommDto);
		//定义 变量.核算主体Map
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("portfolioId", apiTrdCommDto.getTrdProductDealAdd().getPortfolioId());
		paramMap.put("ccy", DictCodeList.币种_人民币_CNY.getValue());
		paramMap.put("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
		//定义 变量.资金账户信息DTO
		PtlCapacctInfo ptlCapacctInfo = valuationInterfMapper.selectProdAcctDataEx(paramMap);
		//变量.交易共通信息DTO.组合账户 =变量.资金账户信息DTO
		apiTrdCommDto.setPtlCapacctInfo(ptlCapacctInfo);
		//变量.交易共通信息DTO.是否无事务调用 =true
		apiTrdCommDto.setNoneTxApply(true);
		//变量.交易共通信息DTO.组合交易表List = 变量.组合交易表List
		apiTrdCommDto.setPtlTradeList(apiTrdCommDto.getPtlTradeList());  
		
		resultContainer = valInterfCommonServiceApi.runValInvest2Jixu(apiTrdCommDto);
        return resultContainer;
    }
    
    /**通道套账信息处理   add by zhangquan 20200106 end*/
    public ApiTrdCommDto channelAccountInfo(TrdCommDto trdCommDto) {
    	//定义 变量.交易共通信息DTO
    	ApiTrdCommDto apiTrdCommDto = new ApiTrdCommDto();
    	//定义 变量.通道代码
    	String chlAgrtId = trdCommDto.getFinTradeProduct() !=null?
                trdCommDto.getFinTradeProduct().getChlAgrtId():trdCommDto.getTrdProductDeal().getChlId();
    	//2.处理详情
    	//定义 变量.组合通道交易List
    	List<PtlTrade> ptlTrade = new ArrayList<PtlTrade>();
    	//定义 变量.交易组合代码对应通道账套代码
    	String channelCode = null;
    	
    	//LOOP  输入.交易信息DTO.组合交易List  组合交易ENTITY
    	for(PtlTrade ptlTradeEntity: trdCommDto.getPtlTradeList()) {
    		//定义 变量.核算通道账套代码
    		String channelLedgerCode = ptlTradeEntity.getPortfolioId();
    		//组合信息DTO
    		PtlPortfolio ptlPortfolio = new PtlPortfolio();
    		ptlPortfolio = interfCommonService.getPtlPortfolo(channelLedgerCode);
    		//IF 数据字典：组合类型.子组合 eq  变量.组合信息DTO.组合类型 OR 数据字典：收益类型.预期收益 eq 变量.组合信息DTO.收益类型 
    		if(DictCodeListExt1.组合类型_子组合.getValue().equals(ptlPortfolio.getPortfolioType()) || 
    				DictCodeListExt2.收益类型_预期收益.getValue().equals(ptlPortfolio.getProfitType())) {
    			//变量.核算通道账套代码  = 变量.核算通道账套代码 + "_" + 变量.通道代码
    			channelLedgerCode = channelLedgerCode + "_" + chlAgrtId;
    		}else {
    			FinProduct finProduct = dealCommonService.queryFinProductByPortfolidId(ptlPortfolio.getPortfolioId());  
    			if(finProduct.getFinprodId() != null && finProduct!=null) {
    				//变量.核算通道账套代码 = DTO.金融产品代码
    				channelLedgerCode = finProduct.getFinprodId();
    			}
    			//变量.核算通道账套代码 =变量.核算通道账套代码 + "_" + 变量.通道代码
    			channelLedgerCode = channelLedgerCode+ "_" + chlAgrtId;
    		}
    		//IF  组合交易ENTITY.组合代码 eq 输入.交易信息DTO.金融产品交易附属表.投资组合代码
    		if(trdCommDto.getTrdProductDealAdd().getPortfolioId().equals(channelLedgerCode)) {
    			//变量.交易组合代码对应通道账套代码 = 变量.核算通道账套代码
    			channelCode = channelLedgerCode;
    		}
    		//定义 变量.通道组合交易DTO
    		PtlTrade ptlTradeDto = new PtlTrade();
    		BeanUtil.copyPropertiesIgnoreNull(ptlTradeDto, ptlTradeEntity);
    		//变量.通道组合交易DTO.组合代码 = 变量.核算通道账套代码
    		ptlTradeDto.setPortfolioId(channelLedgerCode);
    		//变量.组合通道交易List.add(变量.通道组合交易DTO)
    		ptlTrade.add(ptlTradeDto);
    	}
    	
    	//BeanUtil.copyPropertiesIgnoreNull(变量.交易共通信息DTO, 输入.交易信息DTO);
    	BeanUtil.copyPropertiesIgnoreNull(apiTrdCommDto, trdCommDto);
    	//变量.交易共通信息DTO.金融产品交易附属表.投资组合代码 =变量.交易组合代码对应通道账套代码
    	apiTrdCommDto.getTrdProductDealAdd().setPortfolioId(channelCode);
    	//变量.交易共通信息DTO.组合交易表List =变量.组合通道交易List
    	apiTrdCommDto.setPtlTradeList(ptlTrade);
    	//IF 变量.交易共通信息DTO.交易汇总表 IS NOT NULL
    	if(apiTrdCommDto.getTrdFinProductDealTotal() != null) {
    		//变量.交易信息DTO.交易汇总表.投资组合代码 =变量.交易组合代码对应通道账套代码
    		trdCommDto.getTrdFinProductDealTotal().setPortfolioId(channelCode);
    	}
        return apiTrdCommDto;
    }
    
    
    
    
}
