package com.joyintech.tams.invest.portfolio.ptlinveststrategypool.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.PtlInvStrategyMapper;
import com.joyintech.tams.common.base.entity.PtlInvStrategy;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.invest.portfolio.ptlinveststrategypool.dto.PtlInvestStrategyPool001Dto;
import com.joyintech.tams.invest.portfolio.ptlinveststrategypool.dto.PtlInvestStrategyPoolDetailDto;
import com.joyintech.tams.invest.portfolio.ptlinveststrategypool.service.PtlInvestStrategyPool001Service;

@Component
public class PtlInvestStrategyPool001Validator implements BeanValidator{
    @Autowired
    private PtlInvestStrategyPool001Service ptlInvestStrategyPool001Service;

    @Autowired
    private BussCommonMapperDelegateService bussCommonMapperDelegateService;
	@Override
	public List<ValidMessage> valid(Object value) {
		PtlInvestStrategyPool001Dto dto = (PtlInvestStrategyPool001Dto) value;
		ArrayList<ValidMessage> errorList = new ArrayList<>();
		if(StringUtil.equals("delete", dto.getOperateType())){
			if(bussCommonMapperDelegateService.selectBySinglePrimaryKey(PtlInvStrategyMapper.class, dto.getStrategyId(), PtlInvStrategy.class)==null){
                errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0022")));
			}
			if(ptlInvestStrategyPool001Service.selectIsPtlPortfolioCount(dto.getStrategyId())>0){
                errorList.add(new ValidMessage(getMessage("PTL_COM_E_0013")));
			}
		}
		else{
			if(StringUtil.isEmptyList(dto.getPtlInvestStrategyPoolDetailList())){
				errorList.add(new ValidMessage("策略明细列表不能为空"));
			}
			else{
				List<PtlInvestStrategyPoolDetailDto> list =dto.getPtlInvestStrategyPoolDetailList();
				Map<String, PtlInvestStrategyPoolDetailDto> goalInfoMap1 = list.stream().collect(
	                    Collectors.toMap(PtlInvestStrategyPoolDetailDto:: getDetailKey, a -> a, (k1, k2) -> k1));
				if(goalInfoMap1.size()!=list.size()){
					errorList.add(new ValidMessage(getMessage("PTL_COM_E_0018")));
				}
				BigDecimal radio = BigDecimal.ZERO;
				for(int i=0; i<list.size(); i++){
					PtlInvestStrategyPoolDetailDto detail = list.get(i);
					if(StringUtil.isEmpty(detail.getType2())){
		                errorList.add(new ValidMessage(getMessage("PTL_STR_E_0001",String.valueOf(i+1),getMessage("PtlInvestStrategyPool001.type2"))));
					}
					else{
						if((StringUtil.isNotEmpty(detail.getType4())||StringUtil.isNotEmpty(detail.getType5()))
								&&StringUtil.isEmpty(detail.getType2())){
			                errorList.add(new ValidMessage("第"+String.valueOf(i+1)+"行，当资产三级分类为空时，资产四级和资产五级分类不可填"));
						}
						else if(StringUtil.isNotEmpty(detail.getType5())&&StringUtil.isEmpty(detail.getType4())){
			                errorList.add(new ValidMessage("第"+String.valueOf(i+1)+"行，当资产四级分类为空时，资产五级分类不可填"));
						}
					}
					if(detail.getRatio()==null){
		                errorList.add(new ValidMessage(getMessage("PTL_STR_E_0001",String.valueOf(i+1),getMessage("PtlInvestStrategyPool001.ratio"))));
					}
					else{
						radio = radio.add(detail.getRatio());
					}
				}
				if(radio.compareTo(new BigDecimal(100))!=0){
	                errorList.add(new ValidMessage("策略明细列表中的比例之和必须等于100"));
				}
			}
		}
		return errorList;
	}

}
