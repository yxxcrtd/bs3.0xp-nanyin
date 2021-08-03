package com.joyintech.tams.invest.basicdata.mstfuturespriceinfo.valid;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.basicdata.mstfundbonusnotice.dto.MstFundBonusNotice001Dto;
import com.joyintech.tams.invest.basicdata.mstfuturespriceinfo.dto.MstFuturesPriceInfoDto;
import com.joyintech.tams.invest.basicdata.mstfuturespriceinfo.service.MstFuturesPriceInfoService;
import com.joyintech.framework.constant.GlobalConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname MstFuturesPriceInfoValidation
 * @Description TODO
 * @author zhuhanlu 
 * @date 2020/11/27 9:29 
 * @version 1.0 
 */
@Component
public class MstFuturesPriceInfoValidator implements BeanValidator {
    @Autowired
    MstFuturesPriceInfoService mstFuturesPriceInfoService;



    @Override
    public List<ValidMessage> valid(Object o) {
        MstFuturesPriceInfoDto dto = (MstFuturesPriceInfoDto) o;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        List<MstFuturesPriceInfoDto> list = new ArrayList<MstFuturesPriceInfoDto>();
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("futuresId",dto.getFuturesId());
            map.put("tradeDate", DateUtil.format(dto.getTradeDate(), "yyyy-MM-dd"));
            MstFuturesPriceInfoDto mstFuturesPriceInfoDto  = mstFuturesPriceInfoService.selectByPrimaryKey(map);
            if (mstFuturesPriceInfoDto!= null){
                resultList.add(new ValidMessage("futuresId", getMessage(
                        "MST_FUTURESPRICE_001", getMessage("MstFuturesPriceInfo002.futuresId")
                        , getMessage("MstFuturesPriceInfo002.tradeDate"))));
            }
        }
        return resultList;
    }
}
