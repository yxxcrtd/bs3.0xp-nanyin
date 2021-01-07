package com.joyintech.tams.invest.common.component.business.basicdata.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.component.business.basicdata.BaseTradeFeeCommService;
import com.joyintech.tams.invest.common.component.business.basicdata.dao.BaseTradeFeeCommMapper;
import com.joyintech.tams.invest.common.component.business.basicdata.dto.BasicTradeFeeInputDto;
import com.joyintech.tams.invest.common.component.business.basicdata.dto.MstCommissionSetDto;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;

/**
 * 费用计算实现类
 * @author 裴家俊
 * @version 1.0 2019年3月28日
 * @see BaseTradeFeeCommServiceImpl
 * @since 1.0
 */
@Service
public class BaseTradeFeeCommServiceImpl implements BaseTradeFeeCommService {

    /**
     * @see BaseTradeFeeCommMapper
     */
    @Autowired
    private BaseTradeFeeCommMapper baseTradeFeeCommMapper;

	private static final List<String> 上海_深圳 = Arrays.asList(BussinessDictCodeList.交易场所_上海证券交易所.getValue(),
            BussinessDictCodeList.交易场所_深圳证券交易所.getValue());
    /**
     * 银行间费用计算
     */
    @Override
    public List<TrdTradeFeeDetail> innerBankFeeDeal(BasicTradeFeeInputDto inputDto) {
        List<TrdTradeFeeDetail>  feeConfigList=new ArrayList<TrdTradeFeeDetail>();
        if(!checkIsEmpty(inputDto)){
        	return feeConfigList;
        }
    	if(StringUtil.isNotEmpty(inputDto.getTradeMarket())&&StringUtil.equals(BussinessDictCodeList.交易场所_银行间.getValue(), inputDto.getTradeMarket())){
    		for(String feeType:inputDto.getFeeTypeList()){
    			//调用交易费用组件.费用配置查询
    			BasicTradeFeeInputDto searchDto=new BasicTradeFeeInputDto();
    			BeanUtil.copyPropertiesIgnoreNull(searchDto, inputDto);
    			//交易手续费不需要传入登记托管机构
    			if(StringUtil.equals(BussinessDictCodeList.费用类型_交易手续费.getValue(), feeType)){
    				searchDto.setRegistOrg(null);
    			}
    			searchDto.setFeeType(feeType);
    			searchDto.setSecAcctId(null);
                if (StringUtil.equals(searchDto.getIsGrade(), "0")) {
                    continue;
                }                    
    			List<MstCommissionSetDto> feeDetailList=baseTradeFeeCommMapper.selectFeeConfig(searchDto);
    			if(feeDetailList.size()<=0){
    				continue;
    			}
    			MstCommissionSetDto retFeeDetailDto=feeDetailList.get(0);
    			
    			//调用费用计算
    			TrdTradeFeeDetail feeCofigDetail=feeMathMethod(feeType,retFeeDetailDto,inputDto);
    			feeCofigDetail.setIsPayWithTrade(BussinessDictCodeList.是否_否.getValue());
    			feeConfigList.add(feeCofigDetail);
    		}
    	}
    	
        return feeConfigList;
    }
    
    /**
     * 交易所佣金费用计算
     */
    @Override
    public List<TrdTradeFeeDetail> bourseFeeDeal(BasicTradeFeeInputDto inputDto) {
    	 List<TrdTradeFeeDetail>  feeConfigList=new ArrayList<TrdTradeFeeDetail>();
         if(!checkIsEmpty(inputDto)){
         	return feeConfigList;
         }
         //add by wangpeng 20190816 债券优先股
    	if(StringUtil.equals(inputDto.getFinprodType2(), BussinessDictCodeList.平安资产类型_债券.getValue())
    			&&StringUtil.equals(inputDto.getType6(), DictCodeList.WIND一级分类_含债券一级分类等_优先股.getValue())){
    		inputDto.setFinprodType2(BussinessDictCodeList.平安资产类型_股票.getValue());
    	}
         //add end
    	if(StringUtil.isNotEmpty(inputDto.getTradeMarket())&&上海_深圳.contains(inputDto.getTradeMarket())){
    		for(String feeType:inputDto.getFeeTypeList()){
    			//调用交易费用组件.费用配置查询
    			BasicTradeFeeInputDto searchDto=new BasicTradeFeeInputDto();
    			BeanUtil.copyPropertiesIgnoreNull(searchDto, inputDto);
    			searchDto.setFeeType(feeType);
    			searchDto.setRegistOrg(null);
    			List<MstCommissionSetDto> feeDetailList=baseTradeFeeCommMapper.selectFeeConfig(searchDto);
				MstCommissionSetDto retFeeDetailDto=new MstCommissionSetDto();
    			if(feeDetailList.size()<=0){
    				searchDto.setType7(null);
					feeDetailList=baseTradeFeeCommMapper.selectFeeConfig(searchDto);
					for(int j=0;j<feeDetailList.size();j++){
						if(StringUtil.isNotEmpty(feeDetailList.get(j).getWindtypetwo())){
							feeDetailList.remove(j--);
						}
					}
					if(feeDetailList.size()<=0){
						searchDto.setType6(null);
						feeDetailList=baseTradeFeeCommMapper.selectFeeConfig(searchDto);
						for(int k=0;k<feeDetailList.size();k++){
							if(StringUtil.isNotEmpty(feeDetailList.get(k).getWindtypeone())){
								feeDetailList.remove(k--);
							}
						}
						if(feeDetailList.size()<=0){
							continue;
						}
					}
    			}
    			retFeeDetailDto=feeDetailList.get(0);
				if("Y".equals(retFeeDetailDto.getIsGrade())){
					BigDecimal obj=inputDto.getFpriceAmt();
					for(int i=0;i<feeDetailList.size();i++){
						if("N".equals(feeDetailList.get(i).getIsGrade())||!retFeeDetailDto.getEffDate().equals(feeDetailList.get(i).getEffDate()))feeDetailList.remove(i--);
					}
					if(feeDetailList.size()!=1){
						for(int i=0;i<feeDetailList.size();i++){
							retFeeDetailDto=feeDetailList.get(i);
							if(i+1<feeDetailList.size()){
								if(obj.compareTo(new BigDecimal(feeDetailList.get(i).getGradeAmount()))==0&&"N".equals(feeDetailList.get(i).getIsCludeStartamt())){
									retFeeDetailDto=feeDetailList.get(i-1);
									break;
								}else if(obj.compareTo(new BigDecimal(feeDetailList.get(i+1).getGradeAmount()))==0&&"Y".equals(feeDetailList.get(i+1).getIsCludeStartamt())){
									retFeeDetailDto=feeDetailList.get(i+1);
									break;
								}else if(obj.compareTo(new BigDecimal(feeDetailList.get(i).getGradeAmount()))>0&&obj.compareTo(new BigDecimal(feeDetailList.get(i+1).getGradeAmount()))<0){
									retFeeDetailDto=feeDetailList.get(i);
									break;
								}
							}
						}
					}
				}
    			//调用费用计算
    			TrdTradeFeeDetail feeCofigDetail=feeMathMethod(feeType,retFeeDetailDto,inputDto);
//    			feeCofigDetail.setIsPayWithTrade(BussinessDictCodeList.是否_是.getValue());
    			feeConfigList.add(feeCofigDetail);
    		}
    	}
    	
        return feeConfigList;
    }
    
    /**
     * 空值检查
     * @param inputDto
     * @return boolean
     */
    private boolean checkIsEmpty(BasicTradeFeeInputDto inputDto){
    	if(StringUtil.isBlank(inputDto.getTradeMarket())||StringUtil.isBlank(inputDto.getPs())||StringUtil.isEmptyList(inputDto.getFeeTypeList())){
    		return false;
    	}
//    	if(StringUtil.isNotEmpty(inputDto.getTradeMarket())&&StringUtil.equals(BussinessDictCodeList.交易场所_银行间.getValue(), inputDto.getTradeMarket())){
//    		if(StringUtil.isBlank(inputDto.getRegistOrg())){
//    			return false;
//    		}
//    	}
    	if(StringUtil.equals(BussinessDictCodeList.费用类型_佣金.getValue(), inputDto.getTradeMarket()) && StringUtil.isNotEmpty(inputDto.getFeeType())&&上海_深圳.contains(inputDto.getTradeMarket())){
    		if(StringUtil.isBlank(inputDto.getSecAcctId())){
    			return false;
    		}
    	}
    	return true;
    }
    /**
     * 费用计算
     * @param 
     * @return TrdTradeFeeDetail
     */
    private TrdTradeFeeDetail feeMathMethod(String feeType,MstCommissionSetDto retFeeDetailDto,BasicTradeFeeInputDto inputDto){
    	TrdTradeFeeDetail feeCofigDetail=new TrdTradeFeeDetail();
    	BigDecimal singleFee=BigDecimal.ZERO;
    	//费用计算
		if(BussinessDictCodeList.计费类型_固定金额.getValue().equals(retFeeDetailDto.getChargeType())){
			singleFee=retFeeDetailDto.getChargeValue();
		}
		if(BussinessDictCodeList.计费类型_固定费率.getValue().equals(retFeeDetailDto.getChargeType())){
			if(BussinessDictCodeList.计费基数_成交金额.getValue().equals(retFeeDetailDto.getChargeBasis())){
				if(StringUtil.isNotEmpty(inputDto.getFpriceAmt())){
					singleFee=ArithmeticUtil.round(ArithmeticUtil.multiply(inputDto.getFpriceAmt(),retFeeDetailDto.getChargeRate()),2, RoundingMode.HALF_UP);
				}
			}
			if(BussinessDictCodeList.计费基数_券面总额.getValue().equals(retFeeDetailDto.getChargeBasis())){
				if(StringUtil.isNotEmpty(inputDto.getPrinAmt())){
					singleFee=ArithmeticUtil.round(ArithmeticUtil.multiply(inputDto.getPrinAmt(),retFeeDetailDto.getChargeRate()),2,RoundingMode.HALF_UP);
				}
			}
			if(StringUtil.isNotEmpty(retFeeDetailDto.getFeeMini())&&singleFee.compareTo(retFeeDetailDto.getFeeMini())<=0){
				singleFee=retFeeDetailDto.getFeeMini();
			}
			if(StringUtil.isNotEmpty(retFeeDetailDto.getFeeMaxi())&&singleFee.compareTo(retFeeDetailDto.getFeeMaxi())>0){
				singleFee=retFeeDetailDto.getFeeMaxi();
			}
		}
		feeCofigDetail.setFeeType(feeType);
		feeCofigDetail.setIsPayWithTrade(retFeeDetailDto.getIsPayWithTrade());
		feeCofigDetail.setFeeAmt(singleFee);
		return feeCofigDetail;
    }

}
