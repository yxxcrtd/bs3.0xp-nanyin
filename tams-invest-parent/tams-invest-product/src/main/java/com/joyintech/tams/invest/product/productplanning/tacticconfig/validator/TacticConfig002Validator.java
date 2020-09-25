package com.joyintech.tams.invest.product.productplanning.tacticconfig.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.TacticConfigOptDetail;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.product.productplanning.tacticconfig.dto.TacticConfig002Dto;
import com.joyintech.tams.invest.product.productplanning.tacticconfig.service.TacticConfig002Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 战术配置优化提交check
 * 20200116 yaozhenguo
 * @author Administrator
 *
 */
@Component
public class TacticConfig002Validator implements BeanValidator {

	@Autowired
	private TacticConfig002Service tacticConfig002Service;
	@Autowired
	private MstWorkflowCommonService mstWorkflowCommonService;

	@Override
	public List<ValidMessage> valid(Object value) {
		TacticConfig002Dto entity = (TacticConfig002Dto) value;
		// 验证信息
		List<ValidMessage> validList = new ArrayList<ValidMessage>();
		// 流程中判断
		WflValidInputParameter wflValidInputParameterIssue = new WflValidInputParameter();
		wflValidInputParameterIssue.setPageId(entity.getPageId());
		wflValidInputParameterIssue.setWorkflowId(entity.getWorkflowId());
		wflValidInputParameterIssue.setBusinessDataPk2(entity.getPkFlag());
		if (WorkFlowUtil.isNotFromToDoList()) {
			if (mstWorkflowCommonService.isInWflProcess(wflValidInputParameterIssue)) {
				validList.add(new ValidMessage(getMessage("FIN_PROD_E_0077", "")));
				return validList;
			}
		}
		if (StringUtil.isNotEmpty(entity.getVdate()) && StringUtil.isNotEmpty(entity.getEdate())) {
			// 修正期限截止日必须大于起始日
			if (DateUtil.compareDate(entity.getVdate(), entity.getEdate()) >= 0) {
				validList.add(new ValidMessage("edate", getMessage("BUSS_COM_E_0004",
						getMessage("TacticConfig001.edate"), getMessage("TacticConfig001.vdate"))));
			} else {
				// 修正期限连续性,修改不验证
				TacticConfig002Dto dto = tacticConfig002Service.selectByTacticConfigCode(entity.getTacticConfigCode());
				if (StringUtil.isNotEmpty(dto)) {
					if (DateUtil.compareDate(entity.getVdate(), dto.getVdate()) != 0
							|| DateUtil.compareDate(entity.getEdate(), dto.getEdate()) != 0) {
						validList.add(new ValidMessage(getMessage("TacticConfig002.cor.no")));
					}
				} else {
					TacticConfig002Dto tacticConfig002Dto = tacticConfig002Service.selectFirstTacticConfigOpt();
					if (StringUtil.isNotEmpty(tacticConfig002Dto)) {
						if (DateUtil.compareDate(entity.getVdate(),
								DateUtil.addDay(tacticConfig002Dto.getEdate(), 1)) != 0) {
							validList.add(new ValidMessage("vdate", getMessage("TacticConfig002.vdate.continuity")));
						}
					}
				}
			}
		}

		// 战术配置明细校验
		BigDecimal theoConfigRateTotal = new BigDecimal(0);
		BigDecimal corRatioTotal = new BigDecimal(0);
		if (!StringUtil.isEmptyList(entity.getTacticConfigOptDetails())) {
			List<TacticConfigOptDetail> tacticConfigOptDetails = entity.getTacticConfigOptDetails();
			// 资产大类不能重复
			/*Map<String, TacticConfigOptDetail> goalInfoMap1 = tacticConfigOptDetails.stream()
					.collect(Collectors.toMap(TacticConfigOptDetail::getAssetType, a -> a, (k1, k2) -> k1));
			if (goalInfoMap1.size() != tacticConfigOptDetails.size()) {
				validList.add(new ValidMessage(this.getMessage("TacticConfig001.assetType.repeat")));
			}*/
			for (int i = 0; i < tacticConfigOptDetails.size(); i++) {
				// 资产大类必填
				if (StringUtil.isEmpty(tacticConfigOptDetails.get(i).getAssetType())) {
					validList.add(new ValidMessage("tacticConfigOptDetails[" + (i) + "].assetType",
							this.getMessage("FIN_PROD_E_0001", this.getMessage("TacticConfig001.assetType"))));
				}
				// 理论配置比例
				if (!StringUtil.isNotEmpty(tacticConfigOptDetails.get(i).getTheoConfigRate())) {
					validList.add(new ValidMessage("tacticConfigOptDetails[" + (i) + "].theoConfigRate",
							this.getMessage("FIN_PROD_E_0001", this.getMessage("TacticConfig001.theoConfigRate"))));
				}
				// 理论配置比例
				if (!StringUtil.isNotEmpty(tacticConfigOptDetails.get(i).getTheoConfigRate())) {
					validList.add(new ValidMessage("tacticConfigOptDetails[" + (i) + "].theoConfigRate",
							this.getMessage("FIN_PROD_E_0001", this.getMessage("TacticConfig001.theoConfigRate"))));
				} else {
					theoConfigRateTotal = theoConfigRateTotal.add(tacticConfigOptDetails.get(i).getTheoConfigRate());
				}
				// 样本平均收益率
				if (!StringUtil.isNotEmpty(tacticConfigOptDetails.get(i).getSamleAverRate())) {
					validList.add(new ValidMessage("tacticConfigOptDetails[" + (i) + "].samleAverRate",
							this.getMessage("FIN_PROD_E_0001", this.getMessage("TacticConfig001.samleAverRate"))));
				}
				// 波动率
				if (!StringUtil.isNotEmpty(tacticConfigOptDetails.get(i).getVix())) {
					validList.add(new ValidMessage("tacticConfigOptDetails[" + (i) + "].vix",
							this.getMessage("FIN_PROD_E_0001", this.getMessage("TacticConfig001.vix"))));
				}
				// 修正场景
				if (!StringUtil.isNotEmpty(tacticConfigOptDetails.get(i).getCorScene())) {
					validList.add(new ValidMessage("tacticConfigOptDetails[" + (i) + "].corScene",
							this.getMessage("FIN_PROD_E_0001", this.getMessage("TacticConfig001.corScene"))));
				}
				// 修正比例
				if (!StringUtil.isNotEmpty(tacticConfigOptDetails.get(i).getCorRate())) {
					validList.add(new ValidMessage("tacticConfigOptDetails[" + (i) + "].corRate",
							this.getMessage("FIN_PROD_E_0001", this.getMessage("TacticConfig001.corRate"))));
				}
				// 修正后占比
				if (!StringUtil.isNotEmpty(tacticConfigOptDetails.get(i).getCorRatio())) {
					validList.add(new ValidMessage("tacticConfigOptDetails[" + (i) + "].corRatio",
							this.getMessage("FIN_PROD_E_0001", this.getMessage("TacticConfig001.corRatio"))));
				} else {
					corRatioTotal = corRatioTotal.add(tacticConfigOptDetails.get(i).getCorRatio());
				}
			}
			// 修正后占比总和为100%
			if (corRatioTotal.compareTo(new BigDecimal(100)) != 0) {
				validList.add(new ValidMessage(this.getMessage("TacticConfig002.corRatio.total")));
			}
			if (theoConfigRateTotal.compareTo(new BigDecimal(100)) != 0) {
				validList.add(new ValidMessage(this.getMessage("TacticConfig001.theoConfigRate.total")));
			}
		}
		return validList;
	}

}
