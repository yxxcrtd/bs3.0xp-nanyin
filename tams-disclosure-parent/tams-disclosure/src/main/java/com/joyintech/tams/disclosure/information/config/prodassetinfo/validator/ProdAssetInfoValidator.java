package com.joyintech.tams.disclosure.information.config.prodassetinfo.validator;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.disclosure.information.config.prodassetinfo.dto.ProdAssetInfoDto;
import com.joyintech.tams.disclosure.information.config.prodassetinfo.service.ProdAssetInfo002Service;
import com.joyintech.tams.disclosure.information.config.prodpartner.dto.ProdPartnerDto;
import com.joyintech.tams.disclosure.information.config.prodpartner.service.ProdPartner002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ProdAssetInfoValidator implements BeanValidator {


    @Autowired
    private ProdAssetInfo002Service prodAssetInfo002Service;

    @Override
    public List<ValidMessage> valid(Object value) {
        ProdAssetInfoDto dto = (ProdAssetInfoDto)value;
        Date platDate = DateUtil.platDateFromCache();
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        //先验证数据
        if(!StringUtil.isNotEmpty(dto.getEffDate()) || !StringUtil.isNotEmpty(dto.getEffEndDate())
                || !StringUtil.isNotEmpty(dto.getFixPreRatio()) ||!StringUtil.isNotEmpty(dto.getEquityPreRatio())
                || !StringUtil.isNotEmpty(dto.getFdPreRatio()) ||!StringUtil.isNotEmpty(dto.getMixPreRatio())
                || !StringUtil.isNotEmpty(dto.getFixAfterRatio()) ||!StringUtil.isNotEmpty(dto.getEquityAfterRatio())
                || !StringUtil.isNotEmpty(dto.getFdAfterRatio()) ||!StringUtil.isNotEmpty(dto.getMixAfterRatio())){
            return resultList;
        }
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            //删除check

        }else{
                //保存check
                /*if (prodPartner002Service.checkPartnerExistByProdId(dto.getProdId()) > 0) {
                    resultList.add(new ValidMessage("该产品已经维护了合作机构！"));
                }*/
                if(DateUtil.compareDate(platDate,dto.getEffDate())>0){
                    resultList.add(new ValidMessage("生效日要大于等于平台日期！"));
                }
                if(DateUtil.compareDate(platDate,dto.getEffEndDate())>0){
                    resultList.add(new ValidMessage("生效日截止日期要大于等于平台日期！"));
                }
                //生效日截止日期要大于等于生效日
                if(DateUtil.compareDate(dto.getEffDate(),dto.getEffEndDate())>0){
                    resultList.add(new ValidMessage("生效日截止日期要大于等于生效日！"));
                }
                //校验同一个产品不能有两条数据的生效区间范围有重叠
                //查询产品所有的资产组合数据
                List<ProdAssetInfoDto> prodAssetInfoDtos=prodAssetInfo002Service.selectProdAssetInfoByProdId(dto);
                if(prodAssetInfoDtos.size()>0){
                    for (ProdAssetInfoDto prodAssetInfoDto : prodAssetInfoDtos) {
                        if(!((DateUtil.compareDate(dto.getEffDate(),prodAssetInfoDto.getEffDate())<0 && DateUtil.compareDate(dto.getEffEndDate(),prodAssetInfoDto.getEffDate())<0)
                        || (DateUtil.compareDate(dto.getEffDate(),prodAssetInfoDto.getEffEndDate())>0 && DateUtil.compareDate(dto.getEffEndDate(),prodAssetInfoDto.getEffEndDate())>0))){
                            resultList.add(new ValidMessage("生效区间范围有重叠！"+"{"+DateUtil.format(prodAssetInfoDto.getEffDate(),"yyyyMMdd")
                                    +"-"+DateUtil.format(prodAssetInfoDto.getEffEndDate(),"yyyyMMdd")+"}"));
                            break;
                        }
                    }
                }
                //穿透前固定收益类资产占总资产比例+穿透前权益类资产占总资产比例+穿透前商品及金融衍生品类资产占总资产比例+穿透前混合类资产占总资产比例,不能大于100
                if(dto.getFixPreRatio().add(dto.getEquityPreRatio()).add(dto.getFdPreRatio()).add(dto.getMixPreRatio()).compareTo(new BigDecimal(100))>0){
                    resultList.add(new ValidMessage("各类资产穿透前占比总和不能大于100！"));
                }
                //穿透后固定收益类资产占总资产比例+穿透后权益类资产占总资产比例+穿透后商品及金融衍生品类资产占总资产比例+穿透后混合类资产占总资产比例,不能大于100
                if(dto.getFixAfterRatio().add(dto.getEquityAfterRatio()).add(dto.getFdAfterRatio()).add(dto.getMixAfterRatio()).compareTo(new BigDecimal(100))>0){
                    resultList.add(new ValidMessage("各类资产穿透后占比总和不能大于100！"));
                }
            //编辑check
            if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
                if (DateUtil.compareDate(dto.getEffDate(),platDate)<=0) {
                    resultList.add(new ValidMessage("生效日小于等于平台日！"));
                }
                if (DateUtil.compareDate(dto.getEffEndDate(),platDate)<=0) {
                    resultList.add(new ValidMessage("生效截止日小于等于平台日！"));
                }
            }
        }
        return resultList;
    }
}
