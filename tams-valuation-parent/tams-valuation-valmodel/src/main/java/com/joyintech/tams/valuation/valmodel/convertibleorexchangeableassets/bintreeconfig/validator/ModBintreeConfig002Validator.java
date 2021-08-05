package com.joyintech.tams.valuation.valmodel.convertibleorexchangeableassets.bintreeconfig.validator;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.valmodel.convertibleorexchangeableassets.bintreeconfig.dto.ModBintreeConfigDto;
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

	
	@Override
	public List<ValidMessage> valid(Object value) {
		ArrayList<ValidMessage> errorList = new ArrayList<>();
		ModBintreeConfigDto dto=(ModBintreeConfigDto)value;
		if("01".equals(dto.getStockBordsttus())){//上市
			if(dto.getStockId()==null||"".equals(dto.getStockId())){
				errorList.add(new ValidMessage("当标的公司上市状态为上市时，标的股票代码不能为空！"));
			}
		}else{//未上市
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
