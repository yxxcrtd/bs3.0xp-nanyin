/*
 * 文件名：MstCoinFundQuotationInfoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年8月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstcoinfundquotationinfo.validate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.basicdata.mstcoinfundquotationinfo.dto.MstCoinFundQuotationInfo001Dto;
import com.joyintech.tams.invest.basicdata.mstcoinfundquotationinfo.service.MstCoinFundQuotationInfo001Service;

/**
 * 实体类判断
 * 
 * @author fanshuanghu
 * @version 1.0 2018年8月3日
 * @see MstCoinFundQuotationInfoValidator
 * @since 1.0
 */
@Transactional
@Component
public class MstCoinFundQuotationInfoValidator implements BeanValidator {

    /**
     * 注入实体类的mapper
     */
    @Autowired
    private MstCoinFundQuotationInfo001Service mstCoinFundQuotationInfo001Service;

    /**
     * 传入参数检查 {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        MstCoinFundQuotationInfo001Dto dto = (MstCoinFundQuotationInfo001Dto)value;// 获取实体类
        ArrayList<ValidMessage> errorList = new ArrayList<>();// 生成错误集合
        Date sysDate = DateUtil.platDateFromCache();// 获取平台日期
            // 判断操作类型
            if (StringUtil.equals(dto.getOperateType(), "delete")) {
                // 判断是否获取到删除的项目
                if (dto.getSelectedItem() != null || dto.getSelectedItem().size() > 0) {
                    // 遍历选择的项目
                    for (MstCoinFundQuotationInfo001Dto cur : dto.getSelectedItem()) {
					/*
					 * // 判断估值时间是否小于平台时间 if (cur.getValDate().compareTo(sysDate) < 0) { // 添加错误信息
					 * errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0015",
					 * getMessage("MstCoinFundQuotationInfo002.valuationTime"),
					 * getMessage("MstCoinFundQuotationInfo002.date")))); }
					 */
                    	  //add wangwei 1 YUCHUBS-521债券估值行情、基金估值行情、计划类资产估值行情：功能优化
                        //修改/删除时，仅能修改/删除导入日为平台日的数据
                    	//删除时小于平台日的不能删除 by 赵东东 20191209 start 
                        if (StringUtil.isNotEmpty(cur.getImportDay()) && DateUtil.compareDate(DateUtil.format(cur.getImportDay()), DateUtil.format(sysDate))  < 0) {
                        	errorList.add(new ValidMessage("导入日期小于平台日，不能删除"));
                        }
                      //删除时小于平台日的不能删除 by 赵东东 20191209 end
                        //BS-198   start
                      //修改/删除时，仅能修改/删除导入日为平台日的数据
                        if (StringUtil.isNotEmpty(cur.getImportDay()) && DateUtil.compareDate(DateUtil.format(cur.getImportDay()), DateUtil.format(sysDate))  != 0) {
                        	errorList.add(new ValidMessage("修改/删除时,仅能修改/删除导入日为平台日的数据!"));
                        }
                      //BS-198   end
                    }
                }
                // 判断操作类型
            }
            else if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
                // 判断是否存在空值，直接返回交给注解处理
            if (dto.getFinprodId() == null || dto.getFinprodId().isEmpty()
                || dto.getValDate() == null || dto.getValuation() == null || dto.getcShares() == null) {

                }
                else if (dto.getFinprodType().isEmpty()) {
                    errorList.add(new ValidMessage(getMessage("FW_E_0006",
                        getMessage("MstCoinFundQuotationInfo002.finprodType"))));
                }
                else if (mstCoinFundQuotationInfo001Service.selectByIdAndValDate(dto.getFinprodId(),
                DateUtil.format(dto.getValDate())) != null
                     && !mstCoinFundQuotationInfo001Service.selectByIdAndValDate(
                         dto.getFinprodId(), DateUtil.format(dto.getValDate())).isEmpty()) {
                    // 拼接错误信息
                    StringBuffer sb = new StringBuffer();
                    sb.append(getMessage("MstCoinFundQuotationInfo001.finprodId")).append(
                        GlobalConstant.COLON).append(dto.getFinprodMarketId()).append(
                            GlobalConstant.COMMA).append(
                                getMessage("MstCoinFundQuotationInfo002.valuationTime")).append(
                                GlobalConstant.COLON).append(DateUtil.format(dto.getValDate()));
                    // 添加错误信息
                    errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0006", sb.toString())));
                }
                else {
                    // 判断估值时间是否小于平台时间
                /*if (dto.getValDate().compareTo(sysDate) < 0) {
                    errorList.add(new ValidMessage(getMessage(
                        "BUSS_COM_E_0004", getMessage("MstCoinFundQuotationInfo002.valuationTime")
                                           + DateUtil.format(dto.getValDate()),
                            DateUtil.format(sysDate))));
                }*/
                    }
                }
        else if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
                if (dto.getValuation() == null) {
                    // 判断估值时间是否小于平台时间
                }
                //add wangwei 1 YUCHUBS-521债券估值行情、基金估值行情、计划类资产估值行情：功能优化
                //修改/删除时，仅能修改/删除导入日为平台日的数据
                if (StringUtil.isNotEmpty(dto.getImportDay()) && DateUtil.compareDate(DateUtil.format(dto.getImportDay()), DateUtil.format(sysDate))  != 0) {
                	errorList.add(new ValidMessage("修改/删除时,仅能修改/删除导入日为平台日的数据!"));
                }
			/*
			 * else if (dto.getValDate().compareTo(sysDate) < 0) { errorList.add(new
			 * ValidMessage(getMessage( "BUSS_COM_E_0018",
			 * getMessage("MstCoinFundQuotationInfo002.valuationTime") +
			 * DateUtil.format(dto.getValDate()), DateUtil.format(sysDate))));
			 * 
			 * // ValidMessage(this.getMessage("BUSS_COM_E_0004", //
			 * getMessage("MstBondQuotationInfo001.valueDate") + //
			 * DateUtil.format(dto.getValueDate()) , // "平台日" +
			 * DateUtil.format(DateUtil.parse((String)SecurityUtil.getFromSession( //
			 * GlobalConstant.SESSION_SYS_DATE)))))); }
			 */
            }
        return errorList;
    }
}
