package com.joyintech.tams.invest.batch.susprdfutureopenplangen.relyingonresources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.batch.susprdfutureopenplangen.relyingonresources.dto.BussFinProductAnapleroticPlanDto;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
/**
 * 回补信息验证类
 * 〈功能详细描述〉
 * @author LiJun
 * @version 1.0 2019年4月22日
 * @see BussFinProductAnapleroticPlanValidator
 * @since 1.0
 */
@Component
public class BussFinProductAnapleroticPlanValidator implements BeanValidator {
    /**
     * {@linkplain}
     */
    @Autowired
    private IPlatformDate iPlatformDate;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object paramObject) {
    	BussFinProductAnapleroticPlanDto dto = (BussFinProductAnapleroticPlanDto) paramObject;
        List<ValidMessage> validList = new ArrayList<ValidMessage>();
        if(StringUtil.equals("save", dto.getOperateType()) || StringUtil.equals("submit", dto.getOperateType())){

            //回补形式
            if(!StringUtil.isNotEmpty(dto.getAnapleroticType())){
                validList.add(new ValidMessage("anapleroticType", this.getMessage("BUSS_COM_E_0038",
                        this.getMessage("FinProductAnapleroticInfo.anapleroticType"))));
            }
            //回补方式
            if(!StringUtil.isNotEmpty(dto.getAnapleroticRule())){
                validList.add(new ValidMessage("anapleroticRule", this.getMessage("BUSS_COM_E_0038",
                        this.getMessage("FinProductAnapleroticInfo.anapleroticRule"))));
            }
            //回补比例
//            if(null ==dto.getAnapleroticRatio()){
//                validList.add(new ValidMessage("anapleroticRatio", this.getMessage("BUSS_COM_E_0038",
//                    this.getMessage("FinProductAnapleroticInfo.anapleroticRatio"))));
//            }
//            else {
//                BigDecimal oneHundred = new BigDecimal("100");
//                //回补比例 大于0小于等于100
//                if(!(BigDecimal.ZERO.compareTo(dto.getAnapleroticRatio())<0 && oneHundred.compareTo(dto.getAnapleroticRatio()) >= 0)){
//                    validList.add(new ValidMessage("anapleroticRatio", this.getMessage("BUSS_COM_E_0011",
//                        this.getMessage("FinProductAnapleroticInfo.anapleroticRatio"),"0","100(含)")));
//                }
//            }
            //首次回补日不能为空
            if(dto.getFirstAnapleroticDate() == null && !StringUtil.equals(dto.getParentId(), "FinProductModule002")){
                validList.add(new ValidMessage("firstAnapleroticDate", this.getMessage("BUSS_COM_E_0038",
                        this.getMessage("FinProductAnapleroticInfo.firstAnapleroticDate"))));
            }
            //回补结束日不能为空
            if(dto.getAnapleroticEndDate() == null && !StringUtil.equals(dto.getParentId(), "FinProductModule002")){
                validList.add(new ValidMessage("anapleroticEndDate", this.getMessage("BUSS_COM_E_0038",
                        this.getMessage("FinProductAnapleroticInfo.anapleroticEndDate"))));
            }
            //首次回补日必须大于等于平台日
            if(dto.getFirstAnapleroticDate() != null &&DateUtil.compareTo(dto.getFirstAnapleroticDate(),iPlatformDate.getSysDate())<0){
                validList.add(new ValidMessage("firstAnapleroticDate", this.getMessage("BUSS_COM_E_0004",
                        this.getMessage("FinProductAnapleroticInfo.firstAnapleroticDate"),
                        "平台日")));
            }
            //回补结束日必须大于等于平台日
            if(dto.getAnapleroticEndDate() != null &&DateUtil.compareTo(dto.getAnapleroticEndDate(),iPlatformDate.getSysDate())<0){
                validList.add(new ValidMessage("anapleroticEndDate", this.getMessage("BUSS_COM_E_0004",
                        this.getMessage("FinProductAnapleroticInfo.anapleroticEndDate"),
                        "平台日")));
            }
            //回补结束日必须大于等于首次回补日
            if(dto.getFirstAnapleroticDate() != null &&dto.getAnapleroticEndDate() != null
                    &&DateUtil.compareTo(dto.getAnapleroticEndDate(),dto.getFirstAnapleroticDate())<0){
                validList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0004",
                        this.getMessage("FinProductAnapleroticInfo.anapleroticEndDate"),
                        this.getMessage("FinProductAnapleroticInfo.firstAnapleroticDate"))));
            }
            //计费基数
            if(!StringUtil.isNotEmpty(dto.getAnapleroticBasic())){
                validList.add(new ValidMessage("anapleroticBasic", this.getMessage("BUSS_COM_E_0038",
                        this.getMessage("FinProductAnapleroticInfo.anapleroticBasic"))));
            }
            //资产净值日
//            if(!StringUtil.isNotEmpty(dto.getAstVluDateType())){
//                validList.add(new ValidMessage("astVluDateType", this.getMessage("BUSS_COM_E_0038",
//                    this.getMessage("FinProductAnapleroticInfo.astVluDateType"))));
//            }

            //首次回补日必须大于等于平台日期以及生效日
            if(StringUtil.isEmpty(dto.getIsList())){
                //产品端新建的时候不提示生效日
                if(!"01".equals(dto.getApplyType())){
                    if(DateUtil.compareTo(dto.getFirstAnapleroticDate(),dto.getEffDate())<0){
                        validList.add(new ValidMessage("firstAnapleroticDate", this.getMessage("BUSS_COM_E_0004",
                                this.getMessage("FinProductAnapleroticInfo.firstAnapleroticDate"),
                                this.getMessage("FinProductAnapleroticInfo.effDate"))));
                    }

                }
            }
            //分档产品时，适用款型必填
            if(StringUtil.equals(dto.getDealMode(), DictCodeList.处理模式_分档.getValue())&&StringUtil.isEmpty(dto.getProdStyle())){
                validList.add(new ValidMessage("prodStyle", this.getMessage("BUSS_COM_E_0038",
                        this.getMessage("BussFinProductAnapleroticInfo.prodStyle"))));
            }
            if(StringUtil.equals(dto.getApplyType(), DictCodeList.关联范围_产品.getValue())&&
                    !StringUtil.equals(dto.getOpenMode(), DictCodeList.开放模式_高频开放.getValue())
                    &&!StringUtil.isNotEmpty(dto.getFirstAnapleroticCycleBegDate()) && !StringUtil.equals(dto.getParentId(), "FinProductModule002")){
                validList.add(new ValidMessage("firstAnapleroticCycleBegDate", this.getMessage("BUSS_COM_E_0038",
                        this.getMessage("FinProductAnapleroticInfo.firstAnapleroticCycleBegDate"))));
            }
            if(StringUtil.equals(dto.getApplyType(), DictCodeList.关联范围_产品.getValue())&&
                    StringUtil.isNotEmpty(dto.getProdStyleList())
                    &&StringUtil.equals(DictCodeList.处理模式_分档.getValue(), dto.getDealMode())){
                if(dto.getProdStyleList().contains(dto.getProdStyle())){
                    validList.add(new ValidMessage("款型"+dto.getProdStyle()+"已设置业绩回补"));
                }
            }
            //是否回补如果为空代表有回补信息
            if (StringUtil.isNotEmpty(dto.getEffDate())) {
                //根据需求将生效日校验 由不能小于平台日 改为 生效日需大于平台日 add by wangcheng 20191030
                if (DateUtil.compareTo(dto.getEffDate(), iPlatformDate.getSysDate()) <= 0&&!StringUtil.equals(dto.getApplyType(), DictCodeList.关联范围_产品.getValue())) {
                    validList.add(new ValidMessage("effDate", this.getMessage("生效日需大于平台日！")));
                }
            }
            else{
                //增加生效日必填校验 add by wangcheng 20191030
                if(!StringUtil.equals(dto.getApplyType(), DictCodeList.关联范围_产品.getValue())){
                    validList.add(new ValidMessage("effDate", this.getMessage("BUSS_COM_E_0038",
                            this.getMessage("FinProductAnapleroticInfo.effDate"))));
                }
            }

            if(StringUtil.isNotEmpty(dto.getFinprodId())&&!StringUtil.equals(dto.getApplyType(), DictCodeList.关联范围_产品.getValue())){
                boolean flagFloat = false;
                boolean flagFee = false;
                String finProdIdTmp = "";
                if(StringUtil.isNotEmpty(dto.getProdStyle())){
                    finProdIdTmp = dto.getProdStyle();
                }
                else{
                    finProdIdTmp = dto.getFinprodId();
                }                    
                if(StringUtil.equals(dto.getAnapleroticRule(), DictCodeListExt1.回补规则_优先使用浮动管理费回补.getValue())
                        &&!flagFee&&!flagFloat){
                    validList.add(new ValidMessage(getMessage("FIN_PROD_E_0221")));
                }

            }
            //回补业绩基准校验
            if (StringUtil.isNotEmpty(dto.getRateType())
                    &&StringUtil.equals(dto.getRateType(), DictCodeList.利率类型_浮动利率.getValue())) {
                if (!StringUtil.isNotEmpty(dto.getResetType())) {
                    validList.add(new ValidMessage("resetType",
                            getMessage("BUSS_COM_E_0038", getMessage("BussBenchMarkInfo.resetType"))));
                }
            }
            if(StringUtil.isEmpty(dto.getRateType())){
                validList.add(new ValidMessage("rateType",
                        getMessage("BUSS_COM_E_0038", getMessage("BussBenchMarkInfo.rateType"))));
            }
            else if (StringUtil.isNotEmpty(dto.getRateType())) {
                if (StringUtil.equals(dto.getRateType(), DictCodeList.利率类型_固定利率.getValue())) {
                    if (!StringUtil.isNotEmpty(dto.getRate())) {
                        validList.add(new ValidMessage("rate",
                                getMessage("BUSS_COM_E_0038", getMessage("BussBenchMarkInfo.rate"))));
                    }
                }
                else if (StringUtil.equals(dto.getRateType(),
                        DictCodeList.利率类型_浮动利率.getValue())) {
                    if (!StringUtil.isNotEmpty(dto.getIndexId())) {
                        validList.add(new ValidMessage("indexId", getMessage("BUSS_COM_E_0038",
                                getMessage("BussBenchMarkInfo.indexId"))));
                    }
                    if (!StringUtil.isNotEmpty(dto.getCoefficient())) {
                        validList.add(new ValidMessage("coefficient", getMessage("BUSS_COM_E_0038",
                                getMessage("BussBenchMarkInfo.msg.coefficient"))));
                    }
                    if (!StringUtil.isNotEmpty(dto.getSpreadRate())) {
                        validList.add(new ValidMessage("spreadRate", getMessage("BUSS_COM_E_0038",
                                getMessage("BussBenchMarkInfo.msg.spreadRate"))));
                    }
                    if (!StringUtil.isNotEmpty(dto.getResetType())) {
                        validList.add(new ValidMessage("resetType",
                                getMessage("BUSS_COM_E_0038", getMessage("BussBenchMarkInfo.resetType"))));
                    }
                }
            }
            
            /*fixed BS-1486 bug 【净值化产品管理】-【新建】：回补新建功能存在问题*/
            if(StringUtil.isNotEmpty(dto.getOperationType()) && StringUtil.equals(dto.getOperationType(), DictCodeList.开放类型_封闭式.getValue())){
            	 if(DateUtil.compareTo(dto.getFirstAnapleroticDate(),DateUtil.addDay(dto.getmDate(), -1))<0
            			 || DateUtil.compareTo(dto.getFirstAnapleroticDate(),dto.getmDate())>0){
            		 validList.add(new ValidMessage("firstAnapleroticDate","封闭式产品首次回补日必须为产品到期日-1自然日或产品到期日！"));
            	 }
            }
        }
        return validList;
    }

}