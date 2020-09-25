package com.joyintech.tams.invest.basicdata.mstbondquotationinfo.validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.basicdata.mstbondquotationinfo.dto.MstBondQuotationInfoDto;
import com.joyintech.tams.invest.basicdata.mstbondquotationinfo.service.MstBondQuotationInfoService;
/**
 * 
 * @author wangzehui
 *
 */
@Component
public class MstBondQuotationInfoValidator implements BeanValidator {

    /**
     * MstBondQuotationInfoService
     */
    @Autowired
    private MstBondQuotationInfoService mstBondQuotationInfoService;
    
    @Override
    public List<ValidMessage> valid(Object value) {
        MstBondQuotationInfoDto dto = (MstBondQuotationInfoDto)value;
        Date platDate = DateUtil.platDateFromCache();
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            // 删除check
            List<MstBondQuotationInfoDto> list = dto.getSelectedItem();
            for (MstBondQuotationInfoDto mst : list) {
                
				/*// 判断估值日期是否大于平台日期
				 * if (mst.getValueDate().compareTo(platDate) < 0) { resultList.add(new
				 * ValidMessage(this.getMessage("BUSS_COM_E_0015",
				 * getMessage("MstBondQuotationInfo001.valueDate") +
				 * DateUtil.format(dto.getValueDate()), "平台日" + DateUtil.format(platDate)))); }
				 */
            	   //add wangwei 1 YUCHUBS-521债券估值行情、基金估值行情、计划类资产估值行情：功能优化
                //修改/删除时，仅能修改/删除导入日为平台日的数据
                if (StringUtil.isNotEmpty(mst.getImportDay()) && DateUtil.compareDate(DateUtil.format(mst.getImportDay()), DateUtil.format(platDate)) != 0) {
                    resultList.add(new ValidMessage("修改/删除时,仅能修改/删除导入日为平台日的数据!"));
                }
                
                if(mstBondQuotationInfoService.selectByKey(mst.getSecId(), DateUtil.format(mst.getValueDate()),mst.getValueSource()) == null){
                    resultList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0022")));
                }
            }

        }
        else {
            if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
				/*
				 * // 判断估值日期是否大于平台日期 if (dto.getValueDate().compareTo(platDate) < 0) {
				 * resultList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0018",
				 * getMessage("MstBondQuotationInfo001.valueDate") +
				 * DateUtil.format(dto.getValueDate()), "平台日" + DateUtil.format(platDate)))); }
				 */
                //add wangwei 1 YUCHUBS-521债券估值行情、基金估值行情、计划类资产估值行情：功能优化
            	//修改/删除时，仅能修改/删除导入日为平台日的数据
                if ( StringUtil.isNotEmpty(dto.getImportDay()) && DateUtil.compareDate(DateUtil.format(dto.getImportDay()), DateUtil.format(platDate))  != 0) {
                    resultList.add(new ValidMessage("修改/删除时,仅能修改/删除导入日为平台日的数据!"));
                }
            }
            else {
                // 保存check
//                Map<String, Object> map = new HashMap<String, Object>();
//                map.put("secId", dto.getSecId());
//                map.put("valueDate", dto.getValueDate());
                // 判断新建的数据是否存在
                if (mstBondQuotationInfoService.selectByKey(dto.getSecId(), DateUtil.format(dto.getValueDate()),dto.getValueSource()) != null) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(getMessage("MstBondQuotationInfo001.secId")).append(GlobalConstant.COLON).append(
                        dto.getSecId()).append(GlobalConstant.COMMA).append(getMessage("MstBondQuotationInfo001.valueDate")).append(
                            GlobalConstant.COLON).append(DateUtil.format(dto.getValueDate())).append(GlobalConstant.COMMA).append(
                                getMessage("MstBondQuotationInfo001.valueSource")).append(GlobalConstant.COLON).append(dto.getValueSourceStr());
                    resultList.add(
                        new ValidMessage("secId", getMessage("BUSS_COM_E_0006", sb.toString())));
                }

                // 判断估值日期是否大于平台日期
				/* add wangwei1 YUCHUBS-521债券估值行情、基金估值行情、计划类资产估值行情：功能优化
				 * 新建时，去掉估值日期与平台日期的判断
				 * if (StringUtil.isNotEmpty(dto.getValueDate()) &&
				 * DateUtil.compareTo(dto.getValueDate(),platDate) < 0) { resultList.add(new
				 * ValidMessage(this.getMessage("BUSS_COM_E_0004",
				 * getMessage("MstBondQuotationInfo001.valueDate") +
				 * DateUtil.format(dto.getValueDate()) , "平台日" + DateUtil.format(platDate)))); }
				 */
            }

        }
        return resultList;
    }

}
