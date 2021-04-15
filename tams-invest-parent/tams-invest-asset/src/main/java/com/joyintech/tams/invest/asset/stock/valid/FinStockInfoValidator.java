/*
 * 文件名：FinPlanAssetCValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年1月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.asset.stock.valid;

//import com.joyintech.component.svrreport.chb.chbcommoncheck.ChbDataCheckCommonUtil;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.asset.fund.dto.FinFundTargetDto;
import com.joyintech.tams.invest.asset.stock.service.FinStockInfoService;
import com.joyintech.tams.invest.asset.stock.dto.FinStockInfoDto;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
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
                //中债,3个投资阶段选其他,备注必填
                if((dto.getBussRepEquityInfoDto().getRepSharesType().equals(DictCodeListExt2.股票类型_其他.getValue())||
                    dto.getBussRepEquityInfoDto().getRepPartyType1().equals(DictCodeListExt2.发行机构类型_按规模划分_其他.getValue())
                    || dto.getBussRepEquityInfoDto().getRepPartyType2().equals(DictCodeListExt2.发行机构类型_按经济类型划分_其他.getValue())
                || dto.getBussRepEquityInfoDto().getRepPartyType3().equals(DictCodeListExt2.发行机构类型_按技术领域划分_其他.getValue()))
                    && StringUtil.isEmpty(dto.getBussRepEquityInfoDto().getRepRemark())){
                    errorList.add(new ValidMessage(this.getMessage("BUSS_COM_CRE_003")));
                }
                //中债的行业必填
                if(StringUtil.isEmpty(dto.getBussRepEquityInfoDto().getRepIndustry()) || StringUtil.isEmpty(dto.getBussRepEquityInfoDto().getRepIndustryLevel2())){
                    errorList.add(
                        new ValidMessage("bussRepEquityInfoDto.repIndustry",this.getMessage("FW_E_0006", getMessage("BussRepFundInfo.repIndustry"))));
                }
               //中债的2级行业必填
               if(StringUtil.isEmpty(dto.getBussRepEquityInfoDto().getRepIndustryLevel2())){
                   errorList.add(
                       new ValidMessage("bussRepEquityInfoDto.repIndustryLevel2",this.getMessage("FW_E_0006", getMessage("BussRepFundInfo.repIndustryLevel2"))));
               }

               //中债的股票类型必填
               if(StringUtil.isEmpty(dto.getBussRepEquityInfoDto().getRepSharesType())){
                   errorList.add(
                       new ValidMessage("bussRepEquityInfoDto.repSharesType",this.getMessage("FW_E_0006", getMessage("BussRepEquityInfo.repSharesType"))));
               }
               //中债的企业类型（按规模）必填
               if(StringUtil.isEmpty(dto.getBussRepEquityInfoDto().getRepPartyType1())){
                   errorList.add(
                       new ValidMessage("bussRepEquityInfoDto.repPartyType1",this.getMessage("FW_E_0006", getMessage("BussRepEquityInfo.repPartyType1"))));
               }
               //中债的企业类型（按经济）必填
               if(StringUtil.isEmpty(dto.getBussRepEquityInfoDto().getRepPartyType2())){
                   errorList.add(
                       new ValidMessage("bussRepEquityInfoDto.repPartyType2",this.getMessage("FW_E_0006", getMessage("BussRepEquityInfo.repPartyType2"))));
               }
               //中债的企业类型（按技术）必填
               if(StringUtil.isEmpty(dto.getBussRepEquityInfoDto().getRepPartyType3())){
                   errorList.add(
                       new ValidMessage("bussRepEquityInfoDto.repPartyType3",this.getMessage("FW_E_0006", getMessage("BussRepEquityInfo.repPartyType3"))));
               }
               //通过资产代码查询数据，验证资产代码的唯一性
               Map<String,Object> map = new HashMap<String,Object>();
             //  if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
           //        dto.setFinprodMarketId(dto.getFinprodMarketId()+dto.getTradeMarketId());
          //     }
               if(dto.getFinprodMarketId() != null && !"".equals(dto.getFinprodMarketId())) {
                   map.put("finprodMarketId",dto.getFinprodMarketId()+dto.getTradeMarketId());
                   FinStockInfoDto param = finStockInfoService.findEntity(map);
                   if (param != null) {
                       if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
                           errorList.add(
                                   new ValidMessage(this.getMessage("BUSS_COM_I_0017", getMessage("FinStockInfo001.finprodMarketId")
                                           + "(" + dto.getFinprodMarketId()+dto.getTradeMarketId() + ")")));
                       }
                       if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
                    	   WflValidInputParameter parameter = new WflValidInputParameter();
//	                   		dto.setFinprodMarketId(dto.getFinprodMarketId().substring(0,dto.getFinprodMarketId().indexOf(".")));
	                   	    parameter.setPageId("FinStockInfo002");
	                   	    parameter.setBusinessDataPk1(dto.getFinprodIdStr());
	                   	    boolean isInWfl = mstWorkflowCommonService.isInWflProcess(parameter);
                            if(isInWfl){
	                           	Map<String, Object> parammap= new HashMap<String, Object>();
	                           	parammap.put("pageId", "FinStockInfo002");
	                           	parammap.put("pk1", dto.getFinprodIdStr());
	                           	String username =finStockInfoService.getIsInWorkFlowReturnUser(parammap);
	                           	errorList.add(new ValidMessage(getMessage("FIN_ASSET_STOCK_2", username)));
	                        }
                       }
                   }
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
