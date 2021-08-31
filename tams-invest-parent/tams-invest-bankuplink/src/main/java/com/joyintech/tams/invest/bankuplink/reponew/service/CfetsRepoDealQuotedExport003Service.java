package com.joyintech.tams.invest.bankuplink.reponew.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.bankuplink.cstp.dto.CstpSelectDto;
import com.joyintech.tams.invest.bankuplink.reponew.dto.CfetsRepoDealQuotedPr001Dto;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * CFETS质押式回购
 * 
 * @author chenjiaxing
 * @version 1.0 2021年8月7日
 * @see CfetsRepoDealQuotedExport003Service
 * @since 1.0
 */
@Service
public class CfetsRepoDealQuotedExport003Service extends CommonExportService<CfetsRepoDealQuotedPr001Dto> {
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(StringUtil.equals(propName,"lstmntdate")){
            if(value instanceof Date){
                cell.setCellValue(DateUtil.format((Date)value));
            }else if (value instanceof Long){
                cell.setCellValue(DateUtil.format(new Date((Long) value)));
            }
        }
    }
}
