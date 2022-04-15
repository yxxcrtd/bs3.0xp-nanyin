package com.joyintech.tams.svreport.chb.businessdatamaintain.chbboksubject.validator;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbboksubject.dto.ChbBokSubjectDto;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbboksubject.service.ChbBokSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author wubingbing
 * @date 2022-03-28 14:42
 */
@Component
public class ChbBokSubjectDtoValidator implements BeanValidator {
    @Autowired
    private ChbBokSubjectService bokSubjectService;
    @Override
    public List<ValidMessage> valid(Object o) {
        ChbBokSubjectDto dto = (ChbBokSubjectDto)o;
        Date platDate = DateUtil.platDateFromCache();
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            // 删除check
            List<ChbBokSubjectDto> list = dto.getSelectedItem();
            for (ChbBokSubjectDto mst : list) {

                /*// 判断估值日期是否大于平台日期
                 * if (mst.getValueDate().compareTo(platDate) < 0) { resultList.add(new
                 * ValidMessage(this.getMessage("BUSS_COM_E_0015",
                 * getMessage("MstBondQuotationInfo001.valueDate") +
                 * DateUtil.format(dto.getValueDate()), "平台日" + DateUtil.format(platDate)))); }
                 */
                //add wangwei 1 YUCHUBS-521债券估值行情、基金估值行情、计划类资产估值行情：功能优化
                //修改/删除时，仅能修改/删除导入日为平台日的数据
//                if (StringUtil.isNotEmpty(mst.getImportDay()) && DateUtil.compareDate(DateUtil.format(mst.getImportDay()), DateUtil.format(platDate)) != 0) {
//                    resultList.add(new ValidMessage("修改/删除时,仅能修改/删除导入日为平台日的数据!"));
//                }

//                if(bokSubjectService.selectByKey(mst.getSecId(), DateUtil.format(mst.getValueDate()),mst.getValueSource()) == null){
//                    resultList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0022")));
//                }
//                if(StringUtil.isNotEmpty(mst.getProcessState())){
//                    resultList.add(new ValidMessage("流程中不能修改!"));
//                }
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
                //修改和删除的校验在估值子系统中使用软校验，即提示：请确认是否修改/删除
//                if ( StringUtil.isNotEmpty(dto.getImportDay()) && DateUtil.compareDate(DateUtil.format(dto.getImportDay()), DateUtil.format(platDate))  != 0) {
//                    resultList.add(new ValidMessage("修改/删除时,仅能修改/删除导入日为平台日的数据!"));
//                }
//                if(StringUtil.isNotEmpty(dto.getProcessState())){
//                    resultList.add(new ValidMessage("流程中不能修改!"));
//                }
            }
            else {
                // 保存check
                 //判断新建的数据是否存在
                if (StringUtils.hasText(dto.getSubjectNo()) && bokSubjectService.isExists(dto.getBooksetId(),dto.getSubjectNo()) > 0) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(getMessage("BokSubject.subjectNo")).append(GlobalConstant.COLON).append(dto.getSubjectNo());
                    resultList.add( new ValidMessage("subjectNo", getMessage("CHB_BUSS_E_0006", sb.toString())));
                }

            }

        }
        return resultList;
    }

}
