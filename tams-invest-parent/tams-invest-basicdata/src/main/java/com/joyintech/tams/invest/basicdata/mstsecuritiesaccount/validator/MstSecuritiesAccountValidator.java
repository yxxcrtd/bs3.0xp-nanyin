/*
 * 文件名：MstSecuritiesAccountValidate.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年9月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.basicdata.mstsecuritiesaccount.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.basicdata.mstsecuritiesaccount.dto.MstSecuritiesAccountDto;
import com.joyintech.tams.invest.basicdata.mstsecuritiesaccount.service.MstSecuritiesAccountService;

/**
 * 证券账户管理校验
 * 〈功能详细描述〉
 * @author zhangjun
 * @version 1.0 2018年9月14日
 * @see MstSecuritiesAccountValidator
 * @since 1.0
 */
@Component
public class MstSecuritiesAccountValidator implements BeanValidator{
    /**
     * 证券账户Service
     */
    @Autowired
    private MstSecuritiesAccountService mstSecuritiesAccountService;

    /**
     * 校验
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        MstSecuritiesAccountDto dto = (MstSecuritiesAccountDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            // 删除check
            List<MstSecuritiesAccountDto> list = dto.getSelectedItem();
            List<MstSecuritiesAccountDto> tempList =new ArrayList<MstSecuritiesAccountDto>();
            if(null!=list) {
            	tempList = mstSecuritiesAccountService.checkAllCodeIsUse(list);
            }else {
            	MstSecuritiesAccountDto tempData=new MstSecuritiesAccountDto();
            	tempData.setSecAcctId(dto.getSecAcctId());
            	List<MstSecuritiesAccountDto> oldList=new ArrayList<MstSecuritiesAccountDto>();
            	oldList.add(tempData);
            	tempList = mstSecuritiesAccountService.checkAllCodeIsUse(oldList);
            }
            //判断组合表中是否连着这个证券账户
            if (tempList.size() > 0) {
                for (MstSecuritiesAccountDto tempDto : tempList) {
                    resultList.add(new ValidMessage("", getMessage("COMMON_E_0030",
                        getMessage("MstSecuritiesAccount001.secAcctId"),
                        tempDto.getSecAcctId())));
                       
                }
            }
            return resultList;
        }
        else  if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
        }
        else {// 新建校验
              // 判断新建的证券账户账号数据是否存在
            if (mstSecuritiesAccountService.checkBySecAcctId(dto) > 0) {
                StringBuffer sb = new StringBuffer();
                sb.append(getMessage("MstSecuritiesAccount001.secAcctId")).append(GlobalConstant.COLON).append(dto.getSecAcctId());
                resultList.add(
                    new ValidMessage("secAcctId", getMessage("BUSS_COM_I_0017", sb.toString())));
            }
//            FileDto filedto=mstSecuritiesAccountService.selectFileByBusinessId(dto.getSecAcctId());
//            if(filedto == null){
//                resultList.add(
//                    new ValidMessage("请点击上传文件"));
//            }
            if(StringUtil.isNotEmpty(dto.getRemark())) {
            	StringBuffer sb = new StringBuffer("");
            	String str=dto.getRemark();
            	String chinese = "[\u4e00-\u9fa5]";
                for (int i = 0; i < str.length(); i++) {
                    String tempStr = str.substring(i, i + 1);
                    sb.append(tempStr);
                    if (tempStr.matches(chinese)) {
                        // 遇到中文加空格
                        sb.append(" ");
                    }
                }
                if(sb.length()>1000) {
                	resultList.add(new ValidMessage("remark","备注过长"));
                }
            }
            
        }
		
		//双录校验
        if(StringUtil.equals(dto.getIsDoubleInput(),DictCodeList.是否_是.getValue())){
            if(!StringUtil.equals(dto.getRegistOrgContrast(),dto.getRegistOrg())){
                resultList.add( new ValidMessage("registOrg", "托管机构二次录入信息核对不一致"));
            }
            if(!StringUtil.equals(dto.getRecAcctIdContrast(),dto.getRecAcctId())){
                resultList.add( new ValidMessage("recAcctId", "回款账户二次录入信息核对不一致"));
            }
            if(!StringUtil.equals(dto.getTrdAcctIdContrast(),dto.getTrdAcctId())){
                resultList.add( new ValidMessage("trdAcctId", "资金结算账户二次录入信息核对不一致"));
            }
            if(!StringUtil.equals(dto.getTradePlaceContrast(),dto.getTradePlace())){
                resultList.add( new ValidMessage("tradePlace", "交易场所二次录入信息核对不一致"));
            }
        }

        return resultList;
    }

}