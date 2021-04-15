package com.joyintech.tams.invest.basicdata.assetdeclarationcodemaintenance.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.basicdata.assetdeclarationcodemaintenance.dto.AssetDeclarationCodeMaintenanceDto;
import com.joyintech.tams.invest.basicdata.assetdeclarationcodemaintenance.service.AssetDeclarationCodeMaintenanceService;

@Transactional
@Component
public class AssetDeclarationCodeMaintenanceValidator implements BeanValidator {

	@Autowired
	private AssetDeclarationCodeMaintenanceService assetDeclarationCodeMaintenanceService;

	@Override
	public List<ValidMessage> valid(Object value) {
		AssetDeclarationCodeMaintenanceDto dto = (AssetDeclarationCodeMaintenanceDto) value;
		List<ValidMessage> resultList = new ArrayList<ValidMessage>();
		if (StringUtil.equals(dto.getOperateType(), "new")) {
			// 添加 check验证
			AssetDeclarationCodeMaintenanceDto entity = new AssetDeclarationCodeMaintenanceDto();
			try {
				entity = assetDeclarationCodeMaintenanceService.selectByPrimaryKey(dto);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if (entity != null) {
				resultList.add(new ValidMessage(this.getMessage("MST_ASSET_DECLARATION_CODE_E_0009", entity.getFinprodId(), entity.getOthCodeType())));
			}
		}
		AssetDeclarationCodeMaintenanceDto entity = new AssetDeclarationCodeMaintenanceDto();
		try {
			entity = assetDeclarationCodeMaintenanceService.selectByUniqueKey(dto);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (entity != null) {
			resultList.add(new ValidMessage(this.getMessage("MST_ASSET_DECLARATION_CODE_E_0010")));
		}
		return resultList;
	}

}
