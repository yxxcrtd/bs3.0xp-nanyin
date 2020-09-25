/*
 * 文件名：RepFinProductPerDeclareDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年11月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.debtreport.repfinproductperdeclare.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.RepProdDeclare;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 个人产品申报登记
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年11月21日
 * @see RepFinProductPerDeclareDto
 * @since 1.0
 */
public class RepFinProductPerDeclareDto extends RepProdDeclare{
    
     private List<RepFinProductPerDeclareDto> selectedItem;
    /**
     * 取得 投资者类型
     * @return 投资者类型
     */
    public String getRepInvestorTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.目标客户类型, super.getRepInvestorType());
    }
    public String getRepInvestorTypeStr1() {
        return super.getRepInvestorType()+" "+SysDataUtil.getNameFromDataDict(DictCode.目标客户类型, super.getRepInvestorType());
    }
    /**
     * 取得 资金投向地区
     * @return 资金投向地区
     */
    public String getRepInvestAreaStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.资金投向地区, super.getRepInvestArea());
    }
    public String getRepInvestAreaStr1() {
        return super.getRepInvestArea()+" "+SysDataUtil.getNameFromDataDict(DictCode.资金投向地区, super.getRepInvestArea());
    }
    /**
     * 取得 产品投资国家或地区
     * @return 产品投资国家或地区
     */
    public String getRepInvestCountryStr() {
    	if(super.getRepInvestCountry() != null){
            String str = "";
            List<String> list = StringUtil.splitToList(GlobalConstant.COMMA,super.getRepInvestCountry());
            for(int i=0; i<list.size(); i++){
                if(i == list.size()-1){
                    str = str +SysDataUtil.getNameFromDataDict(DictCode.所属国家或地区, list.get(i));
                }
                else{
                    str = str +SysDataUtil.getNameFromDataDict(DictCode.所属国家或地区, list.get(i))+",";
                }
            }
            return str;
        }
        else{
            return null;
        }
        //return SysDataUtil.getNameFromDataDict(DictCode.所属国家或地区, super.getRepInvestCountry());
    }
    public String getRepInvestCountryStr1() {
    	if(super.getRepInvestCountry() != null){
            String str = "";
            List<String> list = StringUtil.splitToList(GlobalConstant.COMMA,super.getRepInvestCountry());
            for(int i=0; i<list.size(); i++){
                if(i == list.size()-1){
                    str = str +list.get(i)+" "+SysDataUtil.getNameFromDataDict(DictCode.所属国家或地区, list.get(i));
                }
                else{
                    str = str +list.get(i)+" "+SysDataUtil.getNameFromDataDict(DictCode.所属国家或地区, list.get(i))+",";
                }
            }
            return str;
        }
        else{
            return null;
        }
        //return SysDataUtil.getNameFromDataDict(DictCode.所属国家或地区, super.getRepInvestCountry());
    }
    /**
     * 取得 理财业务服务模式
     * @return 理财业务服务模式
     */
    public String getRepFinanSerModelStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.理财业务服务模式, super.getRepFinanSerModel());
    }
    public String getRepFinanSerModelStr1() {
        return super.getRepFinanSerModel()+" "+SysDataUtil.getNameFromDataDict(DictCode.理财业务服务模式, super.getRepFinanSerModel());
    }
    /**
     * 取得 产品运作模式
     * @return 产品运作模式
     */
    public String getRepOperateModeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.产品运作模式, super.getRepOperateMode());
    }
    public String getRepOperateModeStr1() {
        return super.getRepOperateMode()+" "+SysDataUtil.getNameFromDataDict(DictCode.产品运作模式, super.getRepOperateMode());
    }
    /**
     * 取得 会计核算方式
     * @return 会计核算方式
     */
    public String getRepBookModeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.会计核算方式, super.getRepBookMode());
    }
    public String getRepBookModeStr1() {
        return super.getRepBookMode()+" "+SysDataUtil.getNameFromDataDict(DictCode.会计核算方式, super.getRepBookMode());
    }
    /**
     * 取得 产品资产配置方式
     * @return 产品资产配置方式
     */
    public String getRepCmmodeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.产品资产配置方式, super.getRepCmmode());
    }
    public String getRepCmmodeStr1() {
        return super.getRepCmmode()+" "+SysDataUtil.getNameFromDataDict(DictCode.产品资产配置方式, super.getRepCmmode());
    }
    /**
     * 取得 产品管理模式
     * @return 产品管理模式
     */
    public String getRepPmmodeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.产品管理模式, super.getRepPmmode());
    }
    public String getRepPmmodeStr1() {
        return super.getRepPmmode()+" "+SysDataUtil.getNameFromDataDict(DictCode.产品管理模式, super.getRepPmmode());
    }
    /**
     * 取得 产品定价方式
     * @return 产品定价方式
     */
    public String getRepPricedModeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.产品定价方式, super.getRepPricedMode());
    }
    public String getRepPricedModeStr1() {
        return super.getRepPricedMode()+" "+SysDataUtil.getNameFromDataDict(DictCode.产品定价方式, super.getRepPricedMode());
    }
    /**
     * 取得 投资资产类型
     * @return 投资资产类型
     */
    public String getRepInvAssetTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.资金实际投向, super.getRepInvAssetType());
    }
    public String getRepInvAssetTypeStr1() {
        return super.getRepInvAssetType()+" "+SysDataUtil.getNameFromDataDict(DictCode.资金实际投向, super.getRepInvAssetType());
    }
    /**
     * 取得 合作模式
     * @return 合作模式
     */
    public String getRepContModeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.合作模式, super.getRepContMode());
    }
    public String getRepContModeStr1() {
        return super.getRepContMode()+" "+SysDataUtil.getNameFromDataDict(DictCode.合作模式, super.getRepContMode());
    }
    /**
     * 取得 是否有预期收益率
     * @return 是否有预期收益率
     */
    /*public String getRepIsYieldStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, super.getRepIsYield());
    }*/
    
    /**
     * 取得 募集币种
     * @return 募集币种
     */
    public String getRepRaiseCcyStr() {
    	if(super.getRepRaiseCcy() != null){
            String str = "";
            List<String> list = StringUtil.splitToList(GlobalConstant.COMMA,super.getRepRaiseCcy());
            for(int i=0; i<list.size(); i++){
                if(i == list.size()-1){
                    str = str +SysDataUtil.getNameFromDataDict(DictCode.币种, list.get(i));
                }
                else{
                    str = str +SysDataUtil.getNameFromDataDict(DictCode.币种, list.get(i))+",";
                }
            }
            return str;
        }
        else{
            return null;
        }
        //return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getRepCashPrinCcy());
    }
    /**
     * 取得 兑付本金币种
     * @return 兑付本金币种
     */
    public String getRepCashPrinCcyStr() {
    	if(super.getRepCashPrinCcy() != null){
            String str = "";
            List<String> list = StringUtil.splitToList(GlobalConstant.COMMA,super.getRepCashPrinCcy());
            for(int i=0; i<list.size(); i++){
                if(i == list.size()-1){
                    str = str +SysDataUtil.getNameFromDataDict(DictCode.币种, list.get(i));
                }
                else{
                    str = str +SysDataUtil.getNameFromDataDict(DictCode.币种, list.get(i))+",";
                }
            }
            return str;
        }
        else{
            return null;
        }
        //return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getRepCashPrinCcy());
    }
    
    /**
     * 取得 兑付收益币种
     * @return 兑付收益币种
     */
    public String getRepCashProfCcyStr() {
    	if(super.getRepCashProfCcy() != null){
            String str = "";
            List<String> list = StringUtil.splitToList(GlobalConstant.COMMA,super.getRepCashProfCcy());
            for(int i=0; i<list.size(); i++){
                if(i == list.size()-1){
                    str = str +SysDataUtil.getNameFromDataDict(DictCode.币种, list.get(i));
                }
                else{
                    str = str +SysDataUtil.getNameFromDataDict(DictCode.币种, list.get(i))+",";
                }
            }
            return str;
        }
        else{
            return null;
        }
    	//return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getRepCashProfCcy());
    }
    
    /**
     * 取得 投资本金到账日
     * @return 投资本金到账日
     */
    public String getRepInvestPrinDateStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.投资本金_收益_到账日, super.getRepInvestPrinDate());
    }
    public String getRepInvestPrinDateStr1() {
        return super.getRepInvestPrinDate()+" "+SysDataUtil.getNameFromDataDict(DictCode.投资本金_收益_到账日, super.getRepInvestPrinDate());
    }
    /**
     * 取得 投资收益到账日
     * @return 投资收益到账日
     */
    public String getRepInvestProfDateStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.投资本金_收益_到账日, super.getRepInvestProfDate());
    }
    public String getRepInvestProfDateStr1() {
        return super.getRepInvestProfDate()+" "+SysDataUtil.getNameFromDataDict(DictCode.投资本金_收益_到账日, super.getRepInvestProfDate());
    }
    /**
     * 取得 产品风险等级
     * @return 产品风险等级
     */
    public String getRepRiskLevelStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.产品风险等级, super.getRepRiskLevel());
    }
    public String getRepRiskLevelStr1() {
        return super.getRepRiskLevel()+" "+SysDataUtil.getNameFromDataDict(DictCode.产品风险等级, super.getRepRiskLevel());
    }
    
    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<RepFinProductPerDeclareDto> getSelectedItem() {
        return selectedItem;
    }

    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<RepFinProductPerDeclareDto> selectedItem) {
        this.selectedItem = selectedItem;
    }
    /**
     * 取得 是否有预期收益率
     * @return 是否有预期收益率
     */
    public String getRepIsYieldStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.是否, super.getRepIsYield());
    }
    public String getRepIsYieldStr1(){
    	if(StringUtil.isNotEmpty(super.getRepIsYield())){
    		if(StringUtil.equals(super.getRepIsYield(), "Y")){
    			super.setRepIsYield("01");
    			return super.getRepIsYield()+" "+SysDataUtil.getNameFromDataDict(DictCode.有无, super.getRepIsYield());
    		}
    		else{
    			super.setRepIsYield("02");
    			return super.getRepIsYield()+" "+SysDataUtil.getNameFromDataDict(DictCode.有无, super.getRepIsYield());
    		}
    	}
    	else{
    		return "";
    	}
        
    }
    /**
     * 取得 是否有收益率测算依据
     * @return 是否有收益率测算依据
     */
    public String getRepIsYieldBasisStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.是否, super.getRepIsYieldBasis());
    }
    public String getRepIsYieldBasisStr1(){
    	if(StringUtil.isNotEmpty(super.getRepIsYieldBasis())){
    		if(StringUtil.equals(super.getRepIsYieldBasis(), "Y")){
    			super.setRepIsYieldBasis("01");
    			return super.getRepIsYieldBasis()+" "+SysDataUtil.getNameFromDataDict(DictCode.有无, super.getRepIsYieldBasis());
    		}
    		else{
    			super.setRepIsYieldBasis("02");
    			return super.getRepIsYieldBasis()+" "+SysDataUtil.getNameFromDataDict(DictCode.有无, super.getRepIsYieldBasis());
    		}
    	}
    	else{
    		return "";
    	}
    }
    /**
     * 取得 投资者风险偏好
     * @return 投资者风险偏好
     */
    public String getRepRiskTypeStr(){
        if(super.getRepRiskType() != null){
            String str = "";
            List<String> list = StringUtil.splitToList(GlobalConstant.COMMA,super.getRepRiskType());
            for(int i=0; i<list.size(); i++){
                if(i == list.size()-1){
                    str = str +SysDataUtil.getNameFromDataDict(DictCode.投资风险偏好, list.get(i));
                }
                else{
                    str = str +SysDataUtil.getNameFromDataDict(DictCode.投资风险偏好, list.get(i))+",";
                }
            }
            return str;
        }
        else{
            return null;
        }
    }
    public String getRepRiskTypeStr1(){
        if(super.getRepRiskType() != null){
            String str = "";
            List<String> list = StringUtil.splitToList(GlobalConstant.COMMA,super.getRepRiskType());
            for(int i=0; i<list.size(); i++){
                if(i == list.size()-1){
                    str = str +list.get(i)+" "+SysDataUtil.getNameFromDataDict(DictCode.投资风险偏好, list.get(i));
                }
                else{
                    str = str +list.get(i)+" "+SysDataUtil.getNameFromDataDict(DictCode.投资风险偏好, list.get(i))+",";
                }
            }
            return str;
        }
        else{
            return null;
        }
    }
    /**
     * 发型机构提前终止权标识
     * @return 发型机构提前终止权标识
     */
    public String getRepTermRightFlagStr(){
    	if(StringUtil.isNotEmpty(super.getRepTermRightFlag())){
    		if(StringUtil.equals(super.getRepTermRightFlag(), "Y")){
    			super.setRepTermRightFlag("01");
    			return SysDataUtil.getNameFromDataDict(DictCode.有无, super.getRepTermRightFlag());
    		}
    		else{
    			super.setRepTermRightFlag("02");
    			return SysDataUtil.getNameFromDataDict(DictCode.有无, super.getRepTermRightFlag());
    		}
    	}
    	else{
    		return "";
    	}
        //return SysDataUtil.getNameFromDataDict(DictCode.有无, super.getRepTermRightFlag());
    }
    public String getRepTermRightFlagStr1(){
    	if(StringUtil.isNotEmpty(super.getRepTermRightFlag())){
    		if(StringUtil.equals(super.getRepTermRightFlag(), "Y")){
    			super.setRepTermRightFlag("01");
    			return super.getRepTermRightFlag()+" "+SysDataUtil.getNameFromDataDict(DictCode.有无, super.getRepTermRightFlag());
    		}
    		else{
    			super.setRepTermRightFlag("02");
    			return super.getRepTermRightFlag()+" "+SysDataUtil.getNameFromDataDict(DictCode.有无, super.getRepTermRightFlag());
    		}
    	}
    	else{
    		return "";
    	}
    	/*if(StringUtil.isNotEmpty(super.getRepTermRightFlag())){
    		return super.getRepTermRightFlag()+" "+SysDataUtil.getNameFromDataDict(DictCode.有无, super.getRepTermRightFlag());
    	}else{
    		return "";
    	}*/
        
    }
    /**
     * 客户赎回权标识
     * @return 客户赎回权标识
     */
    public String getRepCusRedeemFlagStr(){
    	if(StringUtil.isNotEmpty(super.getRepCusRedeemFlag())){
    		if(StringUtil.equals(super.getRepCusRedeemFlag(), "Y")){
    			super.setRepTermRightFlag("01");
    			return SysDataUtil.getNameFromDataDict(DictCode.有无, super.getRepCusRedeemFlag());
    		}
    		else{
    			super.setRepCusRedeemFlag("02");
    			return SysDataUtil.getNameFromDataDict(DictCode.有无, super.getRepCusRedeemFlag());
    		}
    	}
    	else{
    		return "";
    	}
        //return SysDataUtil.getNameFromDataDict(DictCode.有无, super.getRepCusRedeemFlag());
    }
    public String getRepCusRedeemFlagStr1(){
    	if(StringUtil.isNotEmpty(super.getRepCusRedeemFlag())){
    		if(StringUtil.equals(super.getRepCusRedeemFlag(), "Y")){
    			super.setRepCusRedeemFlag("01");
    			return super.getRepTermRightFlag()+" "+SysDataUtil.getNameFromDataDict(DictCode.有无, super.getRepCusRedeemFlag());
    		}
    		else{
    			super.setRepCusRedeemFlag("02");
    			return super.getRepCusRedeemFlag()+" "+SysDataUtil.getNameFromDataDict(DictCode.有无, super.getRepCusRedeemFlag());
    		}
    	}
    	else{
    		return "";
    	}
    	/*if(StringUtil.isNotEmpty(super.getRepCusRedeemFlag())){
    		return super.getRepCusRedeemFlag()+" "+SysDataUtil.getNameFromDataDict(DictCode.有无, super.getRepCusRedeemFlag());
    	}else{
    		return "";
    	}*/
        
    }
    /**
     * 产品增信标识
     * @return 产品增信标识
     */
    public String getRepCreditFlagStr(){
    	if(StringUtil.isNotEmpty(super.getRepCreditFlag())){
    		if(StringUtil.equals(super.getRepCreditFlag(), "Y")){
    			super.setRepCreditFlag("01");
    			return SysDataUtil.getNameFromDataDict(DictCode.有无, super.getRepCreditFlag());
    		}
    		else{
    			super.setRepCreditFlag("02");
    			return SysDataUtil.getNameFromDataDict(DictCode.有无, super.getRepCreditFlag());
    		}
    	}
    	else{
    		return "";
    	}
        //return SysDataUtil.getNameFromDataDict(DictCode.有无, super.getRepCreditFlag());
    }
    public String getRepCreditFlagStr1(){
    	if(StringUtil.isNotEmpty(super.getRepCreditFlag())){
    		if(StringUtil.equals(super.getRepCreditFlag(), "Y")){
    			super.setRepCreditFlag("01");
    			return super.getRepCreditFlag()+" "+SysDataUtil.getNameFromDataDict(DictCode.有无, super.getRepCreditFlag());
    		}
    		else{
    			super.setRepCreditFlag("02");
    			return super.getRepCreditFlag()+" "+SysDataUtil.getNameFromDataDict(DictCode.有无, super.getRepCreditFlag());
    		}
    	}
    	else{
    		return "";
    	}
        //return super.getRepCreditFlag()+" "+SysDataUtil.getNameFromDataDict(DictCode.有无, super.getRepCreditFlag());
    }
    /**
     * 产品增信机构类型
     * @return 产品增信机构类型
     */
    public String getRepCreditTypeStr(){
    	if(super.getRepRiskType() != null){
            String str = "";
            List<String> list = StringUtil.splitToList(GlobalConstant.COMMA,super.getRepRiskType());
            for(int i=0; i<list.size(); i++){
                if(i == list.size()-1){
                    str = str +SysDataUtil.getNameFromDataDict(DictCode.产品增信机构类型, list.get(i));
                }
                else{
                    str = str +SysDataUtil.getNameFromDataDict(DictCode.产品增信机构类型, list.get(i))+",";
                }
            }
            return str;
        }
        else{
            return null;
        }
        //return SysDataUtil.getNameFromDataDict(DictCode.有无, super.getRepCreditType());
    }
    public String getRepCreditTypeStr1(){
    	if(super.getRepRiskType() != null){
            String str = "";
            List<String> list = StringUtil.splitToList(GlobalConstant.COMMA,super.getRepRiskType());
            for(int i=0; i<list.size(); i++){
                if(i == list.size()-1){
                    str = str +list.get(i)+" "+SysDataUtil.getNameFromDataDict(DictCode.产品增信机构类型, list.get(i));
                }
                else{
                    str = str +list.get(i)+" "+SysDataUtil.getNameFromDataDict(DictCode.产品增信机构类型, list.get(i))+",";
                }
            }
            return str;
        }
        else{
            return null;
        }
        //return SysDataUtil.getNameFromDataDict(DictCode.有无, super.getRepCreditType());
    }
    /**
     * 产品增信形式
     * @return 产品增信形式
     */
    public String getRepCreditModeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.产品增信形式, super.getRepCreditMode());
    }
    public String getRepCreditModeStr1(){
    	if(StringUtil.isNotEmpty(super.getRepCreditMode())){
    		return super.getRepCreditMode()+" "+SysDataUtil.getNameFromDataDict(DictCode.产品增信形式, super.getRepCreditMode());
    	}
    	else{
    		return "";
    	}
    }
    /**
     * 产品收益类型
     * @return 产品收益类型 
     */
    public String getRepProfitTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.收益类型, super.getRepProfitType());
    }
    public String getRepProfitTypeStr1(){
    	if(StringUtil.isNotEmpty(super.getRepProfitType())){
    		return super.getRepProfitType()+" "+SysDataUtil.getNameFromDataDict(DictCode.收益类型, super.getRepProfitType());
    	}else{
    		return "";
    	}
        
    }
    /**
     * 募集起始日期（从）
     */
    public String getRepRaisePstrdateStr(){
    	return DateUtil.format(super.getRepRaisePstrdate());
    }

    /**
     * 募集起始日期（到）
     */
    public String getRepRaisePenddateStr(){
    	return DateUtil.format(super.getRepRaisePenddate());
    }
    
    public String getRepTermStr(){
    	return SysDataUtil.getNameFromDataDict(DictCode.产品期限, super.getRepTerm());
    }
    public String getRepTermStr1(){
    	return super.getRepTerm()+" "+SysDataUtil.getNameFromDataDict(DictCode.产品期限, super.getRepTerm());
    }
    
    public String getRepOutDepCtryStr(){
    	if(super.getRepOutDepCtry() != null){
            String str = "";
            List<String> list = StringUtil.splitToList(GlobalConstant.COMMA,super.getRepOutDepCtry());
            for(int i=0; i<list.size(); i++){
                if(i == list.size()-1){
                    str = str +SysDataUtil.getNameFromDataDict(DictCode.所属国家或地区, list.get(i));
                }
                else{
                    str = str +SysDataUtil.getNameFromDataDict(DictCode.所属国家或地区, list.get(i))+",";
                }
            }
            return str;
        }
        else{
            return null;
        }
    }
    
    public String getRepSaleAreaStr(){
    	if(super.getRepSaleArea() != null){
            String str = "";
            List<String> list = StringUtil.splitToList(GlobalConstant.COMMA,super.getRepSaleArea());
            for(int i=0; i<list.size(); i++){
                if(i == list.size()-1){
                    str = str +SysDataUtil.getNameFromDataDict(DictCode.行政区划_省, list.get(i));
                }
                else{
                    str = str +SysDataUtil.getNameFromDataDict(DictCode.行政区划_省, list.get(i))+",";
                }
            }
            return str;
        }
        else{
            return null;
        }
    }
}
