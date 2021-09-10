package com.joyintech.tams.valuation.valmodel.convertibleorexchangeableassets.bintreeconfig.validator;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.tams.valuation.valmodel.convertibleorexchangeableassets.bintreeconfig.dto.ModBintreeConfigDto;
import com.joyintech.tams.valuation.valmodel.convertibleorexchangeableassets.bintreeconfig.service.ModBintreeConfig002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 投资者明细信息管理-验证类
 * @author qiangchengxi
 *
 */
@Transactional
@Component
public class ModBintreeConfig002Validator implements BeanValidator {

	@Autowired
	MstWorkflowCommonService mstWorkflowCommonService;

	@Autowired
	ModBintreeConfig002Service modBintreeConfig002Service;

	@Override
	public List<ValidMessage> valid(Object value) {
		ArrayList<ValidMessage> errorList = new ArrayList<>();
		ModBintreeConfigDto dto = (ModBintreeConfigDto)value;
		if (StringUtil.equals(dto.getOperateType(), "delete")){//删除时的交易判断
			for(ModBintreeConfigDto entity : dto.getSelectedItem()) {
				WflValidInputParameter parameter = new WflValidInputParameter();
				parameter.setPageId("ModBintreeConfig002");
				parameter.setBusinessDataPk1(entity.getFinprodId());
				parameter.setWorkflowId(entity.getWorkflowId());
				boolean isInWfl = mstWorkflowCommonService.isInWflProcess(parameter);
				if (isInWfl) {
					errorList.add(new ValidMessage(getMessage("REPORT_COMMON_E_0021", "资产代码", entity.getFinprodId())));
				}
			}
		}
		else {
			WflValidInputParameter parameter = new WflValidInputParameter();
			parameter.setPageId("ModBintreeConfig002");
			parameter.setBusinessDataPk1(dto.getFinprodId());
			parameter.setWorkflowId(dto.getWorkflowId());
			boolean isInWfl = mstWorkflowCommonService.isInWflProcess(parameter);
			if (isInWfl) {
				errorList.add(new ValidMessage(getMessage("REPORT_COMMON_E_0021", "资产代码", dto.getFinprodId())));
			}
			else {
				if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
					ModBintreeConfigDto oldDto = modBintreeConfig002Service.selectByFinprodId(dto);
					if(oldDto != null) {
						errorList.add(new ValidMessage("该"+oldDto.getFinprodId()+"，不可重复配置！"));
					}
				}
			}
		}
		if("01".equals(dto.getStockBordsttus())){//上市
			if(dto.getStockId()==null||"".equals(dto.getStockId())){
				errorList.add(new ValidMessage("当标的公司上市状态为上市时，标的股票代码不能为空！"));
			}
		}else if("02".equals(dto.getStockBordsttus())){//未上市
			if(dto.getCompOrg()==null||"".equals(dto.getCompOrg())){
				errorList.add(new ValidMessage("当标的公司上市状态为未上市时，可比公司不能为空！"));
			}
			if(dto.getCompStockid()==null||"".equals(dto.getCompStockid())){
				errorList.add(new ValidMessage("当标的公司上市状态为未上市时，可比公司股票代码不能为空！"));
			}
			if(dto.getMarketMulti()==null||"".equals(dto.getMarketMulti())){
				errorList.add(new ValidMessage("当标的公司上市状态为未上市时，市场乘数方法不能为空！"));
			}
			if("01".equals(dto.getMarketMulti())){
				if(dto.getFinprodNetasset()==null||"".equals(dto.getFinprodNetasset())){
					errorList.add(new ValidMessage("当市场乘数方法为市净率法时，标的公司每股净资产不能为空！"));
				}
			}
			if("02".equals(dto.getMarketMulti())){
				if(dto.getFinprodProit()==null||"".equals(dto.getFinprodProit())){
					errorList.add(new ValidMessage("当市场乘数方法为市盈率法时，标的公司每股收益不能为空！"));
				}
			}
		}
//			errorList.add(new ValidMessage("数据库中已存在该资产代码"));
			
		return errorList;
	}

}
