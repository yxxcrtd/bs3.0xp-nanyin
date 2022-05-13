package com.joyintech.tams.svreport.chb.businessdatamaintain.chbprdbaseinfo.validator;

import com.joyintech.background.component.common.check.DataCheckCommonUtil;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.disclosure.common.common.dto.WflValidInputParameter;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbprdbaseinfo.dao.ChbPrdBaseInfoDao;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbprdbaseinfo.dto.ChbPrdBaseInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wubingbing
 * @date 2022-05-09 10:47
 */
@Component
public class ChbPrdBseInfoValidator implements BeanValidator {
    @Autowired
    private DataCheckCommonUtil chbDataCheckCommonUtil;

    @Autowired
    private ChbPrdBaseInfoDao chbPrdBaseInfoDao;
    @Override
    public List<ValidMessage> valid(Object value) {

        ChbPrdBaseInfoDto dto = (ChbPrdBaseInfoDto) value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if (dto.getOperateType().equals("submit")) {
            if (!StringUtil.isEmpty(dto.getProdId())
                    &&!StringUtil.isEmpty(dto.getIssuePartyId())
                    //新建
                    &&StringUtil.isEmpty(dto.getStatusValid())) {
                ChbPrdBaseInfoDto resultDto = chbPrdBaseInfoDao.selectByKey(dto.getProdId());
                if (null != resultDto) {
                    resultList.add(new ValidMessage("prodId", "产品借本信息已存在"));
                }
            }
            // add by zhangliang 20210126 中债check调用 start
            resultList = chbDataCheckCommonUtil.chbCheckCommon(resultList, dto, null, null, ValidMessage.class);
            Map<String, Object> errMap = new HashMap<>();
            List<ValidMessage> result = new ArrayList<>();
            resultList.forEach(validMessage -> {
                if (!errMap.containsKey(validMessage.getMessage())) {
                    result.add(validMessage);
                    errMap.put(validMessage.getMessage(), validMessage.getMessage());
                }
            });
            resultList.clear();
            resultList.addAll(result);
            //modify by tuqirong 2022-01-10 end 错误信息去重处理
            return resultList;
        }else if(dto.getOperateType().equals("issueStatus")){
            List<ChbPrdBaseInfoDto> selectedItem = dto.getSelectedItem();
            if (!StringUtil.isEmptyList(selectedItem)) {
                boolean effectStatusFlag = true;
                boolean issueStatusFlag = true;
                StringBuffer sb = new StringBuffer();
                for (ChbPrdBaseInfoDto chbPrdBaseInfoDto : selectedItem) {
                    if (!StringUtil.equals(chbPrdBaseInfoDto.getStatusValid(),
                            DictCodeListExt1.维护数据状态_有效.getValue())) {
                        if (effectStatusFlag) {
                            resultList.add(new ValidMessage(
                                    this.getMessage("CHB_PROD_E_0032", DictCodeListExt1.维护数据状态_有效.getCodeName())));
                            effectStatusFlag = false;
                        }
                    }
                    if (!StringUtil.equals(chbPrdBaseInfoDto.getIssuingStatus(),
                            DictCodeListExt1.发行状态_确认发行.getValue())) {
                        if (issueStatusFlag) {
                            resultList.add(new ValidMessage(
                                    this.getMessage("CHB_PROD_E_0033", DictCodeListExt1.发行状态_确认发行.getCodeName())));
                            issueStatusFlag = false;
                        }
                    }
                }
            }
            return resultList;
        }
        return null;
    }
}
