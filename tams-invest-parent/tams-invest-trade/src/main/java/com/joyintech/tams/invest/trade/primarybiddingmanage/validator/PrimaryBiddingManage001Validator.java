package com.joyintech.tams.invest.trade.primarybiddingmanage.validator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.trade.primarybiddingmanage.dto.PrimaryBiddingManageDto;
import com.joyintech.tams.invest.trade.primarybiddingmanage.dto.PrimaryBiddingRevokeDto;
import com.joyintech.tams.invest.trade.primarybiddingmanage.service.PrimaryBiddingManage003Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : 裴家俊
 * @version V1.0
 */
@Component
public class PrimaryBiddingManage001Validator implements BeanValidator {


    @Autowired
    private PrimaryBiddingManage003Service primaryBiddingManage003;

    @Autowired
    MstWorkflowCommonService mstWorkflowCommonService;
    @Override
    public List<ValidMessage> valid(Object value) {
        PrimaryBiddingRevokeDto dto = (PrimaryBiddingRevokeDto)value;
        List<ValidMessage> errorList = new ArrayList<>();
        //判断流程中是否存在相同的数据
        WflValidInputParameter param = new WflValidInputParameter();
        param.setBusinessDataPk2(dto.getCounterId());
        param.setBusinessDataPk1(dto.getFinprodId());
        param.setBusinessDataPk3(dto.getOperateType());
        if (StringUtil.equals(dto.getTenderconfirmstatus(), BussinessDictCodeList.确认状态_已确认.getValue())){
            param.setPageId("PrimaryBiddingManage002");
        }else {
            param.setPageId("PrimaryBiddingManage003");
        }
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
}
