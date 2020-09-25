/*
 * 文件名：ChannelAssetValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年3月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.channel.validate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.invest.asset.channel.dto.ChannelAssetDto;
import com.joyintech.tams.invest.asset.channel.service.ChannelAssetService;

/**
 * 通道资产业务验证
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年3月19日
 * @see ChannelAssetValidator
 * @since 1.0
 */
@Component
public class ChannelAssetValidator implements BeanValidator{

    @Autowired
    private CommonBusinessService commonBusinessService;
    
    @Autowired
    private ChannelAssetService channelAssetService;
    
    @Override
    public List<ValidMessage> valid(Object value) {
        ChannelAssetDto dto = (ChannelAssetDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            if(null!=dto.getSelectedItem()&&!dto.getSelectedItem().isEmpty()){
                for(ChannelAssetDto entity : dto.getSelectedItem()){
                    if(channelAssetService.isTransInAsset(entity.getFinprodId())!=0){
                        resultList.add(new ValidMessage("",
                            getMessage("资产存在交易,不可删除!")));
                        break;
                    }
                }
            }
        }
        
        /**
         * 市场代码不可重复
         */
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            if(StringUtil.isNotEmpty(dto.getFinprodMarketId())){
                Map<String,Object> param = new HashMap<>();
                param.put("finprodMarketId", dto.getFinprodMarketId());
                param.put("F28", DictCodeListExt1.金融产品类型_投管用_通道.getValue());
                if(channelAssetService.isRepeated(param)>0){
                    resultList.add(new ValidMessage("finprodMarketId",
                        getMessage("BUSS_COM_E_0006", getMessage("invest.channelId"),getMessage("ChannelAsset002.finprodIdBak"))));
                }
            }
            //资金账户不能重复
            if (StringUtil.isNotEmpty(dto.getBusiAcctNo())){
                Map<String,Object> map = new HashMap<>();
                map.put("busiAcctNo", dto.getBusiAcctNo());
                map.put("finprodMarketId", dto.getFinprodMarketId());
                map.put("F28", DictCodeListExt1.金融产品类型_投管用_通道.getValue());
                map.put("pageMode", GlobalConstant.DETAIL_PAGE_MODEL_NEW);
                if(channelAssetService.isReBusiAcctNo(map)>0){
                	//update by yantianyuan 20200721 格式化字符串缺少参数 start
                    resultList.add(new ValidMessage("busiAcctNo",
                            getMessage("BUSS_COM_E_0006", getMessage("ChannelAsset002.busiAcctNo"),(String)map.get("busiAcctNo"))));
                    //update by yantianyuan 20200721 格式化字符串缺少参数 end 
//                    resultList.add(new ValidMessage("busiAcctNo",
//                            getMessage("BUSS_COM_E_0006", getMessage("ChannelAsset002.busiAcctNo"))));
                }
            }
            if(StringUtil.isNotEmpty(dto.getFinprodName())){
                /**
                 * 通道名称不可重复
                 */
                Map<String,Object> map = new HashMap<>();
                map.put("finprodName", dto.getFinprodName());
                map.put("F28", DictCodeListExt1.金融产品类型_投管用_通道.getValue());
                if(channelAssetService.isRepeated(map)>0){
                    resultList.add(new ValidMessage("finprodName",
                        getMessage("BUSS_COM_E_0006", getMessage("invest.channelId"),getMessage("ChannelAsset002.finprodNameBak"))));
                }
            }
        }
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
            if(channelAssetService.isTransInAsset(dto.getFinprodId())!=0){
                resultList.add(new ValidMessage(
                    getMessage("FIN_ASSET_E_23", dto.getFinprodName())));
            }
            //资金账户不能重复
            if (StringUtil.isNotEmpty(dto.getBusiAcctNo())){
                Map<String,Object> map = new HashMap<>();
                map.put("busiAcctNo", dto.getBusiAcctNo());
                map.put("finprodMarketId", dto.getFinprodMarketId());
                map.put("F28", DictCodeListExt1.金融产品类型_投管用_通道.getValue());
                map.put("pageMode", GlobalConstant.DETAIL_PAGE_MODEL_EDIT);
                if(channelAssetService.isReBusiAcctNo(map)>0){
                    resultList.add(new ValidMessage("busiAcctNo",
                            getMessage("BUSS_COM_E_0006", getMessage("ChannelAsset002.busiAcctNo"))));
                }
            }
        }
        if(null!=dto.getVdate()&&null!=dto.getMdate()){
            if(DateUtil.compareDate(dto.getVdate(), dto.getMdate())!=-1){
                resultList.add(new ValidMessage("vdate",
                    getMessage("BUSS_COM_E_0035", getMessage("ChannelAsset002.vdate"),getMessage("ChannelAsset002.mdate"))));
            }
            if(StringUtil.isNotEmpty(dto.getFinprodId())){
                Date maxTradeDate = commonBusinessService.getMaxTradeDate(dto.getFinprodId());
                if(null!=maxTradeDate){
                    if(DateUtil.compareDate(dto.getMdate(), maxTradeDate)<1){
                        resultList.add(new ValidMessage("mdate",
                            getMessage("BUSS_COM_E_0035", getMessage("ChannelAsset002.mdate"),"最大交易日")));
                    }
                }
            }
        }

        // 附加税税率  add by zoujiamin 20200908 增加非负数校验
        if (StringUtil.isNotEmpty(dto.getAdtTaxRate())) {
            if(dto.getAdtTaxRate().compareTo(BigDecimal.ZERO)==-1)
                resultList.add(new ValidMessage("adtTaxRate",
                        this.getMessage("BUSS_COM_E_0004", this.getMessage("ChannelAsset002.adtTaxRate"),"0")));
        }

        // 增值税税率 add by zoujiamin 20200908 增加非负数校验
        if (StringUtil.isNotEmpty(dto.getVatTaxRate())) {
            if(dto.getVatTaxRate().compareTo(BigDecimal.ZERO)==-1)
                resultList.add(new ValidMessage("vatTaxRate",
                        this.getMessage("BUSS_COM_E_0004", this.getMessage("ChannelAsset002.vatTaxRate"),"0")));
        }
        
        return resultList;
    }
    
}
