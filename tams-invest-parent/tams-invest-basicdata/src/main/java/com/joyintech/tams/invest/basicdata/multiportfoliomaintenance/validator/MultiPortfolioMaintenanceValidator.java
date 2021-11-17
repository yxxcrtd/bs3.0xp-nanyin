package com.joyintech.tams.invest.basicdata.multiportfoliomaintenance.validator;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.basicdata.multiportfoliomaintenance.dto.MultiPortfolioMaintenanceDto;
import com.joyintech.tams.invest.basicdata.multiportfoliomaintenance.service.MultiPortfolioMaintenance001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MultiPortfolioMaintenanceValidator implements BeanValidator {
    @Autowired
    private MultiPortfolioMaintenance001Service stlSecReconParaService;

    @Override
    public List<ValidMessage> valid(Object value) {

        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        MultiPortfolioMaintenanceDto dto = (MultiPortfolioMaintenanceDto) value;
        return resultList;
    }
}
