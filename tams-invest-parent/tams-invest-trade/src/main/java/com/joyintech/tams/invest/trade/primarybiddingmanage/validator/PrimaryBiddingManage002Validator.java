package com.joyintech.tams.invest.trade.primarybiddingmanage.validator;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.trade.primarybiddingmanage.dto.BidOrderBidPriceDto;
import com.joyintech.tams.invest.trade.primarybiddingmanage.dto.PrimaryBiddingManageDto;
import com.joyintech.tams.invest.trade.primarybiddingmanage.dto.TrdBiddingManage003Dto;
import com.joyintech.tams.invest.trade.primarybiddingmanage.service.PrimaryBiddingManage001Service;
import com.joyintech.tams.invest.trade.primarybiddingmanage.service.PrimaryBiddingManage003Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : 裴家俊
 * @version V1.0
 */
@Component
public class PrimaryBiddingManage002Validator implements BeanValidator {


    @Autowired
    private PrimaryBiddingManage001Service primaryBiddingManage001;

    @Autowired
    private PrimaryBiddingManage003Service primaryBiddingManage003;
    @Autowired
    MstWorkflowCommonService mstWorkflowCommonService;
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;
    @Override
    public List<ValidMessage> valid(Object value) {
        PrimaryBiddingManageDto dto = (PrimaryBiddingManageDto)value;
        List<ValidMessage> errorList = new ArrayList<>();
        //判断流程中是否存在相同的数据
        WflValidInputParameter param = new WflValidInputParameter();
        param.setBusinessDataPk1(dto.getFinprodId());
        param.setBusinessDataPk2(dto.getCounterId());
        param.setBusinessDataPk3(dto.getOperateType());
        param.setPageId("PrimaryBiddingManage002");
        String processStatus = "";
        if (null != dto.getWorkflowId()){
            processStatus = primaryBiddingManage003.findProcessStatusByWorkflowId(dto.getWorkflowId());
        }
        if(null != mstWorkflowCommonService &&
            mstWorkflowCommonService.isInWflProcess(param) &&
                !"BACK".equals(processStatus)){
            errorList.add(
                new ValidMessage(this.getMessage("BUSS_COM_MDATE_0002")));
        }
        return errorList;
    }

    public List<ValidMessage> checkSure(Object value) {
        PrimaryBiddingManageDto dto = (PrimaryBiddingManageDto)value;
        List<ValidMessage> errorList = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("finprodId", dto.getFinprodId());
        map.put("counterId", dto.getCounterId());
        if (null == dto.getBidId()){
            List<PrimaryBiddingManageDto> primaryBiddingManageDtos = primaryBiddingManage001.findDataByFinprodIdAndCounterId(map);
            if (!primaryBiddingManageDtos.isEmpty()){
                for (PrimaryBiddingManageDto primaryBiddingManageDto : primaryBiddingManageDtos) {
                    if (null != primaryBiddingManageDto.getBidId()){
                        errorList.add(new ValidMessage(this.getMessage("TRD_PRIMARYBIDDINGMANAGE_005")));
                    }
                }
            }
        }

        return errorList;
    }

}
