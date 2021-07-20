package com.joyintech.tams.invest.risk.creditmanagement.creditrulemanage.validator;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.risk.creditmanagement.creditrulemanage.dto.CreditRuleDto;
import com.joyintech.tams.invest.risk.creditmanagement.creditrulemanage.service.CreditRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreditRuleValidator implements BeanValidator{

	@Autowired
	private CreditRuleService creditRuleService;

	@Override
	public List<ValidMessage> valid(Object value) {
		List<ValidMessage> errorList = new ArrayList<ValidMessage>();
		CreditRuleDto dto = (CreditRuleDto)value;
		if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
			ArrayList<String> AstTypeName2 = new ArrayList ();
			if(StringUtil.isNotEmpty(dto.getAstType2 ())){
				String TypeNameList[] = dto.getAstType2 ().split (",");
				for (int i = 0; i < TypeNameList.length; i++) {
					AstTypeName2.add (TypeNameList[i]);
				}
			}
			//校验数据重复性
			List<CreditRuleDto> creditRuleDto = creditRuleService.checkRule(dto);
			if (StringUtil.isNotEmpty(creditRuleDto)) {
				if (AstTypeName2.size () > 1){
					for (int j = 0; j < AstTypeName2.size (); j++){
						for (int i = 0; i < creditRuleDto.size (); i++) {
							if (StringUtil.isNotEmpty (dto.getAstType2 ()) && StringUtil.isNotEmpty (creditRuleDto.get (i).getAstType2 ())) {
								if (!(dto.getAstType2 ().equals (creditRuleDto.get (i).getAstType2 ()))) {
									continue;
								}else {
									if (StringUtil.isNotEmpty (dto.getAstType3 ()) && StringUtil.isNotEmpty (creditRuleDto.get (i).getAstType3 ())){
										if (!(dto.getAstType3 ().equals (creditRuleDto.get (i).getAstType3 ()))){
											continue;
										}
									}
								}
							}

							String errmag = getMessage ("CeRule002.astType1") + "：" + creditRuleDto.get (i).getAstType1Name ();
							if (StringUtil.isNotEmpty (creditRuleDto.get (i).getAstType2 ())) {
								errmag += "，" + getMessage ("CeRule002.astType2") + "：" + creditRuleDto.get (i).getAstType2Name ();
							}
							if (StringUtil.isNotEmpty (creditRuleDto.get (i).getAstType3 ())) {
								errmag += "，" + getMessage ("CeRule002.astType3") + "：" + creditRuleDto.get (i).getAstType3Name ();
							}
							if (StringUtil.isNotEmpty (creditRuleDto.get (i).getAstType4 ())) {
								errmag += "，" + getMessage ("CeRule002.astType4") + "：" + creditRuleDto.get (i).getAstType4Name ();
							}
							if (StringUtil.isNotEmpty (creditRuleDto.get (i).getAstType5 ())) {
								errmag += "，" + getMessage ("CeRule002.astType5") + "：" + creditRuleDto.get (i).getAstType5Name ();
							}
							errorList.add (new ValidMessage (this.getMessage ("RSK_W_0075", errmag)));
						}

					}
				}else {
					for (int i = 0; i < creditRuleDto.size (); i++) {
						if (StringUtil.isNotEmpty (dto.getAstType2 ()) && StringUtil.isNotEmpty (creditRuleDto.get (i).getAstType2 ())) {
							if (!(dto.getAstType2 ().equals (creditRuleDto.get (i).getAstType2 ()))) {
								continue;
							}else {
								if (StringUtil.isNotEmpty (dto.getAstType3 ()) && StringUtil.isNotEmpty (creditRuleDto.get (i).getAstType3 ())){
									if (!(dto.getAstType3 ().equals (creditRuleDto.get (i).getAstType3 ()))){
										continue;
									}
								}
							}
						}

						String errmag = getMessage ("CeRule002.astType1") + "：" + creditRuleDto.get (i).getAstType1Name ();
						if (StringUtil.isNotEmpty (creditRuleDto.get (i).getAstType2 ())) {
							errmag += "，" + getMessage ("CeRule002.astType2") + "：" + creditRuleDto.get (i).getAstType2Name ();
						}
						if (StringUtil.isNotEmpty (creditRuleDto.get (i).getAstType3 ())) {
							errmag += "，" + getMessage ("CeRule002.astType3") + "：" + creditRuleDto.get (i).getAstType3Name ();
						}
						if (StringUtil.isNotEmpty (creditRuleDto.get (i).getAstType4 ())) {
							errmag += "，" + getMessage ("CeRule002.astType4") + "：" + creditRuleDto.get (i).getAstType4Name ();
						}
						if (StringUtil.isNotEmpty (creditRuleDto.get (i).getAstType5 ())) {
							errmag += "，" + getMessage ("CeRule002.astType5") + "：" + creditRuleDto.get (i).getAstType5Name ();
						}
						errorList.add (new ValidMessage (this.getMessage ("RSK_W_0075", errmag)));
					}
				}
			}

		}
		return errorList;
	}

}
