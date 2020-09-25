package com.joyintech.tams.invest.product.rddesign.productsimulationtrial.validator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.product.rddesign.productsimulationtrial.dto.ProductSimulationTrial004Dto;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * 产品收集删除修改验证
 * 
 * @author yaozhenguo
 * @version 1.0 2019年9月226日
 * @see ProductSimulationTrial001Validator
 * @since 1.0
 */
@Component
public class ProductSimulationTrial001Validator implements BeanValidator {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ValidMessage> valid(Object value) {
		// TODO Auto-generated method stub
		ProductSimulationTrial004Dto productSimulationTrial004Dto = (ProductSimulationTrial004Dto)value;
		List<ValidMessage> errorList = new ArrayList<>();

		//资产大类不为空
		if (!StringUtil.isNotEmpty(productSimulationTrial004Dto.getAssetType())){
			errorList.add(new ValidMessage("assetType",
					this.getMessage("BUSS_COM_E_0038",
							this.getMessage("ProductSimulationTrial002.assetType"))));
		}

		//资产风险等级不为空
		if (!StringUtil.isNotEmpty(productSimulationTrial004Dto.getAssetRiskLevel())){
			errorList.add(new ValidMessage("assetRiskLevel",
					this.getMessage("BUSS_COM_E_0038",
							this.getMessage("ProductSimulationTrial002.assetRiskLevel"))));
		}

		//模拟匹配金额不为空
        if (!StringUtil.isNotEmpty(productSimulationTrial004Dto.getMockAmt())){
            errorList.add(new ValidMessage("mockAmt",
                    this.getMessage("BUSS_COM_E_0038",
                            this.getMessage("ProductSimulationTrial002.mockAmt"),"0")));
        }

		//模拟匹配份额不为空
        if (!StringUtil.isNotEmpty(productSimulationTrial004Dto.getMockShareAmt())){
            errorList.add(new ValidMessage("mockShareAmt",
                    this.getMessage("BUSS_COM_E_0038",
                            this.getMessage("ProductSimulationTrial002.mockShareAmt"),"0")));
        }

		//利率类型不为空
		if (!StringUtil.isNotEmpty(productSimulationTrial004Dto.getRateType())){
			errorList.add(new ValidMessage("rateType",
					this.getMessage("BUSS_COM_E_0038",
							this.getMessage("ProductSimulationTrial002.rateType"))));
		}else{
			//固定利率类型
			if ("01".equals(productSimulationTrial004Dto.getRateType())){
				//上限收益率不为空
				if(!StringUtil.isNotEmpty(productSimulationTrial004Dto.getRateMax())){
					errorList.add(new ValidMessage("rateMax",
							this.getMessage("BUSS_COM_E_0038",
									this.getMessage("ProductSimulationTrial002.rateMax"))));
				}
				//下限收益率不为空
				if(!StringUtil.isNotEmpty(productSimulationTrial004Dto.getRateMix())){
					errorList.add(new ValidMessage("rateMix",
							this.getMessage("BUSS_COM_E_0038",
									this.getMessage("ProductSimulationTrial002.rateMix"))));
				}
			}else if("02".equals(productSimulationTrial004Dto.getRateType())){//浮动利率类型
				//上限基准不为空
				if(!StringUtil.isNotEmpty(productSimulationTrial004Dto.getBenchmarkMax())){
					errorList.add(new ValidMessage("benchmarkMax",
							this.getMessage("BUSS_COM_E_0038",
									this.getMessage("ProductSimulationTrial002.floatMaxRate1"))));
				}
				//上限系数不为空
                if(!StringUtil.isNotEmpty(productSimulationTrial004Dto.getCoefficientMax())){
                    errorList.add(new ValidMessage("coefficientMax",
                            this.getMessage("BUSS_COM_E_0038",
                                    this.getMessage("ProductSimulationTrial002.coefficientMax1"),"0")));
                }
				//上限利差不为空
                if(!StringUtil.isNotEmpty(productSimulationTrial004Dto.getSpreadRateMax())){
                    errorList.add(new ValidMessage("spreadRateMax",
                            this.getMessage("BUSS_COM_E_0038",
                                    this.getMessage("ProductSimulationTrial002.spreadRateMax1"),"0")));
                }
				//下限利差不为空
				if(!StringUtil.isNotEmpty(productSimulationTrial004Dto.getBenchmarkMin())){
					errorList.add(new ValidMessage("benchmarkMin",
							this.getMessage("BUSS_COM_E_0038",
									this.getMessage("ProductSimulationTrial002.floatMinRate1"))));
				}
				//下限系数不为空
                if(!StringUtil.isNotEmpty(productSimulationTrial004Dto.getCoefficientMin())){
                    errorList.add(new ValidMessage("coefficientMin",
                            this.getMessage("BUSS_COM_E_0038",
                                    this.getMessage("ProductSimulationTrial002.coefficientMin1"),"0")));
                }
				//下限利差不为空
                if(!StringUtil.isNotEmpty(productSimulationTrial004Dto.getSpreadRateMin())){
                    errorList.add(new ValidMessage("spreadRateMin",
                            this.getMessage("BUSS_COM_E_0038",
                                    this.getMessage("ProductSimulationTrial002.spreadRateMin1"),"0")));
                }
			}
		}

		return errorList;
	}

}
