/*
 * 文件名：FinDepositTargetValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.depo.validator;

import com.joyintech.component.svrreport.chb.chbcommoncheck.ChbDataCheckCommonUtil;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.asset.depo.dto.FinDepositTargetDto;
import com.joyintech.tams.invest.asset.depo.service.FinDepositTargetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  存款类资产--业务验证
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月30日
 * @see FinDepositTargetValidator
 * @since 1.0
 */
@Component
public class FinDepositTargetValidator implements BeanValidator{

    @Autowired
    private CommonBusinessService commonBusinessService;
    @Autowired(required = false)
    private FinDepositTargetService finDepositTargetService;
	/**
     * 共通Mapper的通用代理方案
     */
    @Autowired
    private BussCommonMapperDelegateService bussCommonMapperDelegateService;

    @Autowired
    private ChbDataCheckCommonUtil chbDataCheckCommonUtil;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        FinDepositTargetDto dto = (FinDepositTargetDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            if(null!=dto.getSelectedItem()&&!dto.getSelectedItem().isEmpty()){
                for(FinDepositTargetDto entity : dto.getSelectedItem()){
                    if(commonBusinessService.isTransInAsset(entity.getFinprodId())!=0){
                        resultList.add(new ValidMessage("",
                            getMessage("资产存在交易,不可删除!")));
                        break;
                    }
                }
            }
        }
        else{
            //modify by mashukai 20201126 ZGBS-28 存款标的提交成功，审批时退回，退回的数据修改后不能保存 start
        	//有效状态资产不能保存  zhangyf 20190809 start
//        	if(StringUtil.equals(dto.getOperateType(), "save") && StringUtil.isNotEmpty(dto.getFinprodId())) {
//        		resultList.add(new ValidMessage("",getMessage("有效状态的资产可以提交，不能保存！")));
//        	}
        	//有效状态资产不能保存  zhangyf 20190809 end
            //modify by mashukai 20201126 ZGBS-28 存款标的提交成功，审批时退回，退回的数据修改后不能保存 end
            if(StringUtil.equals(dto.getCreditFlag(), DictCodeList.是否_是.getValue())
                && (dto.getActualCreditProvid() == null || StringUtil.equals(dto.getActualCreditProvid(), ""))){
                resultList.add(
                    new ValidMessage("actualCreditProvid",this.getMessage("FW_E_0006", getMessage("FinPlanAssetC002.actualCreditProvid"))));
            }
            //新建，校验资产代码是否有重复 zhangyf 20191022 start
			if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)
					&& StringUtil.isNotEmpty(dto.getFinprodMarketId())){
				// 组装金融产品代码
				FinDepositTargetDto finprodIdDto = finDepositTargetService.getFinprodId(dto);
				// 根据金融产品代码（主键）查询
				FinProduct finProductEntry = bussCommonMapperDelegateService
						.selectBySinglePrimaryKey(FinProductMapper.class, finprodIdDto.getFinprodId(), FinProduct.class);
				if(null != finProductEntry) {
					// 资产代码已存在
					resultList.add(new ValidMessage("",
							this.getMessage("BUSS_COM_I_0017", getMessage("FinDepositTarget002.finprodId"))));
				}
			}
			//新建，校验资产代码是否有重复 zhangyf 20191022 end
        }
        if(null!=dto.getReferRate()&&null!=dto.getFinInterestFactorDto().getLoadRate()){
            if(dto.getReferRate().compareTo(dto.getFinInterestFactorDto().getLoadRate())==1){
                resultList.add(new ValidMessage("",
                    getMessage("BUSS_COM_E_0004",getMessage("FinInterestFactor.loadRate"), getMessage("FinDepositTarget002.referRate"))));
            }
            
        }
        if(null!=dto.getVdate()&&null!=dto.getMdate()){
            if(DateUtil.compareDate(dto.getVdate(), dto.getMdate())!=-1){
                resultList.add(new ValidMessage("vdate",
                    getMessage("BUSS_COM_E_0035", getMessage("FinDepositTarget002.vdate"),getMessage("FinDepositTarget002.mdate"))));
            }
            if(StringUtil.isNotEmpty(dto.getFinprodId())){
                Date maxTradeDate = commonBusinessService.getMaxTradeDate(dto.getFinprodId());
                if(null!=maxTradeDate){
                    if(DateUtil.compareDate(dto.getMdate(), maxTradeDate)<1){
                        resultList.add(new ValidMessage("mdate",
                            getMessage("BUSS_COM_E_0035", getMessage("FinDepositTarget002.mdate"),"最大交易日")));
                    }
                }
            }
        }
        //存款类型:资产类型选择存放同业时必填，其他情况不可填
        if(StringUtil.equals(dto.getType6(), DictCodeList.存款类资产类型_存放同业.getValue())){
            if(StringUtil.isEmpty(dto.getType7())){
                resultList.add(new ValidMessage("type7",
                    getMessage("COMMON_E_0057", getMessage("FinDepositTarget002.type7"))));
            }
        }
        if (!StringUtil.equals(dto.getOperateType(), "delete") && StringUtil.equals(DictCodeList.是否_中债_是.getValue(),
                dto.getBussRepDepositInfoDto().getRepIsStruct())) {
            if (StringUtil.isEmpty(dto.getBussRepDepositInfoDto().getRepTargetType())) {
                resultList.add(new ValidMessage("bussRepDepositInfoDto.repTargetType",
                        getMessage("FIN_PROD_E_0099", "结构性存款挂钩标的类别")));
            }
            if (StringUtil.isEmpty(dto.getBussRepDepositInfoDto().getRepTarget())) {
                resultList.add(new ValidMessage("bussRepDepositInfoDto.repTarget",
                        getMessage("FIN_PROD_E_0099", "结构性存款挂钩标的")));
            }
        }
		// 是否展示资产四五级分类
		if (StringUtil.equals(CacheUtil.getSysParam("IS_SHOW_TYPE"), "01") &&  (StringUtil.equals(dto.getOperateType(), "save") ||  StringUtil.equals(dto.getOperateType(), "submit"))) {
			if (StringUtil.isEmpty(dto.getType11())) {
				resultList.add(new ValidMessage("type11",
						getMessage("COMMON_E_0057", getMessage("FinDepositTarget002.type11"))));
			}
			if (StringUtil.isEmpty(dto.getType12())) {
				resultList.add(new ValidMessage("type12",
						getMessage("COMMON_E_0057", getMessage("FinDepositTarget002.type12"))));
			}
		}
		if(StringUtil.equals(dto.getType6(), DictCodeList.存款类资产类型_同业借款.getValue())
        ||StringUtil.equals(dto.getType6(), DictCodeList.存款类资产类型_存放同业.getValue())){
            if(null==dto.getMdate()){
                resultList.add(new ValidMessage("mdate",
                        getMessage("COMMON_E_0057", getMessage("FinDepositTarget002.mdate"))));
            }
            if(null==dto.getIssueAmt()){
                resultList.add(new ValidMessage("issueAmt",
                        getMessage("COMMON_E_0057", getMessage("FinDepositTarget002.issueAmt"))));
            }
        }
		//add by caiqun 20200727 start  BS-1118【银行模式】【投资交易-存款类标的】新建标的时，选择挂一次性划款通道，提交成功。控制只能选择逐笔划款通道
		if(StringUtil.isNotEmpty(dto.getBussParticipantInfoDto())
            && StringUtil.isNotEmpty(dto.getBussParticipantInfoDto().getBussParticipantInfoDtoList())
                && dto.getBussParticipantInfoDto().getBussParticipantInfoDtoList().size()>0
            && DictCodeListExt2.划款方式_一次性划款.getValue().equals(dto.getBussParticipantInfoDto().getBussParticipantInfoDtoList().get(0).getPayType())){
            resultList.add(new ValidMessage("",getMessage("只能使用逐笔划款通道！")));
        }
        // add by caiqun 20200727 end  BS-1118【银行模式】【投资交易-存款类标的】新建标的时，选择挂一次性划款通道，提交成功。控制只能选择逐笔划款通道

//        if (dto.getOperateType() != null && !StringUtil.equals(dto.getOperateType(), "delete")) {
//            resultList = chbDataCheckCommonUtil.chbCheckCommon(resultList, dto, null, dto.getType6(), ValidMessage.class);
//        }

        return resultList;
    }

}
