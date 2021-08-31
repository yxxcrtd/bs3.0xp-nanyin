/*
 * 文件名：FinPlanAssetCValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年1月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.basicdata.asset.stock.valid;

//import com.joyintech.component.svrreport.chb.chbcommoncheck.ChbDataCheckCommonUtil;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.basicdata.asset.stock.dto.FinStockInfoDto;
import com.joyintech.tams.valuation.basicdata.asset.stock.service.FinStockInfoService;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.tams.valuation.component.business.service.CommonBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2019年1月7日
 * @see FinStockInfoValidator
 * @since 1.0
 */
@Component
public class FinStockInfoValidator implements BeanValidator {
    /**
     * 各种业务共通组件Service
     */
    @Autowired
    private CommonBusinessService commonBusinessService;
    /**
     * finStockInfoService
     */
    @Autowired
    private FinStockInfoService finStockInfoService;

    @Autowired
    MstWorkflowCommonService mstWorkflowCommonService;

//    @Autowired
//    private ChbDataCheckCommonUtil chbDataCheckCommonUtil;

    /**
     * {@inheritDoc}
     */
    /**
     * 校验
     * @param value 页面数据
     * @return 校验结果
     */
    public List<ValidMessage> valid(Object value) {
        FinStockInfoDto dto = (FinStockInfoDto) value;

        List<ValidMessage> errorList = new ArrayList<>();

            // 判断是否选中数据
           if (StringUtil.equals(dto.getOperateType(), "delete")){//删除时的交易判断
                for(FinStockInfoDto e:dto.getSelectedItem()) {
                    //判断是否存在交易
                    if (commonBusinessService.isTransInAsset(e.getFinprodId())>0){
                        errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0043")));
                    }
//                    if(finStockInfoService.getBasicInfoByFinprodId(e.getFinprodId()) == null){
//                        errorList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0022")));
//                    }
                }
            }
            else{
                //上市日期和退市日期
                if(dto.getVdate() != null && dto.getMdate() != null
                    && dto.getVdate().compareTo(dto.getMdate()) >= 0){
                    errorList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0035",
                        getMessage("FinStockInfo002.vDate") + DateUtil.format(dto.getVdate()),
                        getMessage("FinStockInfo002.mDate")+DateUtil.format(dto.getMdate()))));
                }

               if((dto.getMinSaleUnit() != null)&&(dto.getMinSaleUnit() < 100 || dto.getMinSaleUnit() >5000)){
                   errorList.add(new ValidMessage(this.getMessage("FIN_ASSET_E_0099",
                           this.getMessage("FinStockInfo002.minSaleUnit"))));
               }
//               if(dto.getTradeMarket() != null && StringUtil.equals(dto.getTradeMarket(),"06")){
//                   if(StringUtil.isEmpty(dto.getShOrZh())){
//                       errorList.add(new ValidMessage(this.getMessage("FIN_ASSET_E_0088",
//                               this.getMessage("FinStockInfo001.shOrZh"))));
//                   }
//               }
               if(StringUtil.equals(dto.getTradeMarket(), DictCodeListExt1.交易场所_上海证券交易所.getValue())){
                   if(StringUtil.equals(dto.getBoard(), DictCodeList.上市板_中小企业板.getValue())
                        || StringUtil.equals(dto.getBoard(), DictCodeList.上市板_创业板.getValue())){
                       errorList.add(new ValidMessage("交易场所选择上海证券交易所时，上市板不可选择中小企业板、创业板！"));
                   }
               }
               else if(StringUtil.equals(dto.getTradeMarket(), DictCodeListExt1.交易场所_深圳证券交易所.getValue())){
                   if(StringUtil.equals(dto.getBoard(), DictCodeList.上市板_科创板.getValue())){
                       errorList.add(new ValidMessage("交易场所选择深交所时，上市板不可选择科创板！"));
                   }
               }

               //通过资产代码查询数据，验证资产代码的唯一性
               Map<String,Object> map = new HashMap<String,Object>();
               if(dto.getFinprodMarketId() != null && !"".equals(dto.getFinprodMarketId())) {
                   map.put("finprodMarketId", dto.getFinprodMarketId());
                   if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
                       FinStockInfoDto param = finStockInfoService.findEntity(map);
                       if (param != null) {
                           errorList.add(
                                   new ValidMessage(this.getMessage("BUSS_COM_I_0017", getMessage("FinStockInfo001.finprodMarketId")
                                           + "(" + dto.getFinprodMarketId() + ")")));
                       }
                   }
                   //if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
                       WflValidInputParameter parameter = new WflValidInputParameter();
//	                   		dto.setFinprodMarketId(dto.getFinprodMarketId().substring(0,dto.getFinprodMarketId().indexOf(".")));
                       parameter.setPageId("FinStockInfo002");
                       parameter.setBusinessDataPk1(dto.getFinprodMarketId());
                       boolean isInWfl = mstWorkflowCommonService.isInWflProcess(parameter);
                       if(isInWfl){
                           errorList.add(new ValidMessage(getMessage("REPORT_COMMON_E_0021", "资产代码", dto.getFinprodMarketId())));
//	                           	Map<String, Object> parammap= new HashMap<String, Object>();
//	                           	parammap.put("pageId", "FinStockInfo002");
//	                           	parammap.put("pk1", dto.getFinprodIdStr());
//	                           	String username =finStockInfoService.getIsInWorkFlowReturnUser(parammap);
//	                           	errorList.add(new ValidMessage(getMessage("FIN_ASSET_STOCK_2", username)));
                       }
                   //}
                   FinStockInfoDto finStockInfoDto = finStockInfoService.getShares(map);
                   if(StringUtil.isNotEmpty(finStockInfoDto)){
	                   if (StringUtil.isNotEmpty(finStockInfoDto.getcShares()) && StringUtil.isNotEmpty(finStockInfoDto.getsShares())) {
	                       BigDecimal cShares = new BigDecimal(finStockInfoDto.getcShares());
	                       BigDecimal sShares = new BigDecimal(finStockInfoDto.getsShares());
	                       if (cShares.compareTo(sShares) > 0) {
	                           errorList.add(
	                                   new ValidMessage(
	                                           this.getMessage(this.getMessage("BUSS_COM_E_0035",
	                                                   getMessage("FinStockInfo002.cShares") + cShares,
	                                                   getMessage("FinStockInfo002.sShares") + sShares))
	                                   ));
	                       }
	                   }
                   }
                       //总股本大于流通股本的check
//                       Page<FinStockInfoDto> page = finStockInfoService.selectForPage(map);
//                       List<FinStockInfoDto> list =page.getRoot();
//                       for (int i=0;i<list.size();i++){
//                           FinStockInfoDto finStockInfoDto=list.get(i);
//                           if( StringUtil.isNotEmpty(finStockInfoDto.getcShares())&& StringUtil.isNotEmpty(finStockInfoDto.getsShares())) {
//                               BigDecimal cShares=new BigDecimal(finStockInfoDto.getcShares());
//                               BigDecimal sShares=new BigDecimal(finStockInfoDto.getsShares());
//                               if (cShares.compareTo(sShares) > 0) {
//                                   errorList.add(
//                                           new ValidMessage(
//                                                   this.getMessage(this.getMessage("BUSS_COM_E_0035",
//                                                           getMessage("FinStockInfo002.cShares") +cShares ,
//                                                           getMessage("FinStockInfo002.sShares")+sShares))
//                                           ));
//                               }
//                           }
//                       }


                   //总股本大于等于流通股本check
               }
               // errorList = chbDataCheckCommonUtil.chbCheckCommon(errorList, value, null, "1302", ValidMessage.class);
            }

        return errorList;
    }

}
