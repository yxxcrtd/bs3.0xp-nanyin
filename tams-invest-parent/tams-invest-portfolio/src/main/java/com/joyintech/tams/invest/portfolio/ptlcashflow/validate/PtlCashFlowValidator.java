/*
 * 文件名：PtlCashFlowValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlcashflow.validate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.portfolio.ptlcashflow.dto.PtlCashFlowDto;
import com.joyintech.tams.invest.portfolio.ptlcashflow.service.PtlCashFlowService;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年10月26日
 * @see PtlCashFlowValidator
 * @since 1.0
 */
@Component
public class PtlCashFlowValidator implements BeanValidator{
    @Autowired
    private PtlCashFlowService ptlCashFlowService;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        ArrayList<ValidMessage> resultList = new ArrayList<ValidMessage>();// 生成错误集合
        PtlCashFlowDto dto = (PtlCashFlowDto)value;
//        校验重复数据
        if(StringUtil.equals(dto.getOperateType(), "revoke")) {
         // 删除check
            Map<String, Object> map = new HashMap<String,Object>();
            map.put("busiNo", dto.getBusiNo()+"R");
            map.put("FINPROD_TYPE_TG_F23", DictCodeListExt1.金融产品类型_投管用_自定义现金流.getValue());
         // 删除check
            if(ptlCashFlowService.getEntity(map) != null){
                resultList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0053")));
            }
         // 业务发生日是否小于平台日
            if (dto.getSettleDate().compareTo(DateUtil.platDateFromCache()) < 0) {
                resultList.add(new ValidMessage(this.getMessage("PTL_COM_E_0008",
                    getMessage("PtlCashFlow001.settleDate") + DateUtil.format(dto.getSettleDate()),
                    "平台日" + DateUtil.format(DateUtil.platDateFromCache()))));
            }
            
        }
        else{
            if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
                if(StringUtil.equals(dto.getCustomCashType(), DictCodeList.自定义现金流类型_逾期利息.getValue())
                    && (dto.getIsClean() == null || "".equals(dto.getIsClean()))){
                    resultList.add(new ValidMessage("isClean",this.getMessage("FW_E_0006",
                        getMessage("PtlCashFlow002.isClean"))));
                }
                if(dto.getTradeAmt() != null && dto.getTradeAmt().compareTo(BigDecimal.valueOf(0))<=0){
                    resultList.add(new ValidMessage(this.getMessage("PTL_COM_E_0006")));
                }
            }
            if(!StringUtil.equals(dto.getType(),"getBussTrdTransferOrder") && (dto.getBussTrdTransferOrder() == null || dto.getBussTrdTransferOrder().size() ==0)){
                resultList.add(new ValidMessage(this.getMessage("PTL_COM_E_0007")));
            }
        }
        
        return resultList;
    }

}
