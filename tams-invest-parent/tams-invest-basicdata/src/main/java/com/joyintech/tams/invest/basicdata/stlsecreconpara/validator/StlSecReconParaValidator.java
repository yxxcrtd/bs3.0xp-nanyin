package com.joyintech.tams.invest.basicdata.stlsecreconpara.validator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.basicdata.stlsecreconpara.dto.StlSecReconParaDto;
import com.joyintech.tams.invest.basicdata.stlsecreconpara.service.StlSecReconParaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StlSecReconParaValidator implements BeanValidator {
    @Autowired
    private StlSecReconParaService stlSecReconParaService;

    @Override
    public List<ValidMessage> valid(Object value) {

        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        StlSecReconParaDto dto = (StlSecReconParaDto) value;
        // 提交或修改或保存时检查dto是否已入库
        if (StringUtil.equals(dto.getOperateType(), "new")) {
            if (stlSecReconParaService.checkParaIsExist(dto)) {
                resultList.add(new ValidMessage("对账参数已存在！"));
            }
        }
        return resultList;
    }
}
