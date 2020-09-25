package com.joyintech.tams.invest.portfolio.trdpbrelation.validator;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.portfolio.trdpbrelation.dto.TrdPbRelation001Dto;
import com.joyintech.tams.invest.portfolio.trdpbrelation.service.TrdPbRelation001Service;
/**
 * PB编号管理Validator
 * 〈功能详细描述〉
 * @author caoxue
 * @version 1.0 2019年12月24日
 * @see TrdPbRelation001Validator
 * @since 1.0
 *
 */
@Component
public class TrdPbRelation001Validator implements BeanValidator{
    @Autowired
    private TrdPbRelation001Service trdPbRelation001Service;
	@Override
	public List<ValidMessage> valid(Object value) {
		TrdPbRelation001Dto dto = (TrdPbRelation001Dto) value;
		ArrayList<ValidMessage> errorList = new ArrayList<>();
		if(StringUtil.equals("submit", dto.getOperateType())){
			if(StringUtil.isEmptyList(dto.getSelectedItem())){
				errorList.add(new ValidMessage("PB编号列表不能为空"));
			}
			else{
				List<TrdPbRelation001Dto> list =dto.getSelectedItem();
				Map<String, TrdPbRelation001Dto> goalInfoMap1 = list.stream().collect(
						Collectors.toMap(TrdPbRelation001Dto:: getPrdPortfolioId, a -> a, (k1, k2) -> k1));
				if(goalInfoMap1.size()!=list.size()){
					errorList.add(new ValidMessage(getMessage("TRD_PB_E_001")));
				}
				Map<String, TrdPbRelation001Dto> goalInfoMap2 = list.stream().collect(
	                    Collectors.toMap(TrdPbRelation001Dto:: getPbId, a -> a, (k1, k2) -> k1));
				if(goalInfoMap2.size()!=list.size()){
					errorList.add(new ValidMessage(getMessage("TRD_PB_E_004")));
				}
				for(int i=0; i<list.size(); i++){
					TrdPbRelation001Dto detail = list.get(i);
					if(StringUtil.isEmpty(detail.getFactorType())){//非空验证
		                errorList.add(new ValidMessage(getMessage("PTL_STR_E_0001",String.valueOf(i+1),getMessage("TrdPbRelation001.factorType"))));
					}
					if(StringUtil.isEmpty(detail.getPrdPortfolioId())){//非空验证
						errorList.add(new ValidMessage(getMessage("PTL_STR_E_0001",String.valueOf(i+1),getMessage("TrdPbRelation001.prdPortfolioId"))));
					}else{//产品/子组合代码重复验证
						TrdPbRelation001Dto entity =new TrdPbRelation001Dto();
						entity.setPrdPortfolioId(detail.getPrdPortfolioId());
						TrdPbRelation001Dto resultDto=trdPbRelation001Service.getTrdPbRelation(entity);
						if(resultDto!=null&&!resultDto.getSeqno().equals(dto.getSeqno())){
							errorList.add(new ValidMessage(getMessage("TRD_PB_E_002",String.valueOf(i+1),detail.getPrdPortfolioId())));
						}
					}
					if(StringUtil.isEmpty(detail.getPrdPortfolioName())){//非空验证
						errorList.add(new ValidMessage(getMessage("PTL_STR_E_0001",String.valueOf(i+1),getMessage("TrdPbRelation001.prdPortfolioName"))));
					}
					if(StringUtil.isEmpty(detail.getPbId())){//非空验证
						errorList.add(new ValidMessage(getMessage("PTL_STR_E_0001",String.valueOf(i+1),getMessage("TrdPbRelation001.pbId"))));
					}else{//PB编号重复验证
						TrdPbRelation001Dto entity =new TrdPbRelation001Dto();
						entity.setPbId(detail.getPbId());
						TrdPbRelation001Dto resultDto=trdPbRelation001Service.getTrdPbRelation(entity);
						if(resultDto!=null&&!resultDto.getSeqno().equals(dto.getSeqno())){
							errorList.add(new ValidMessage(getMessage("TRD_PB_E_003",String.valueOf(i+1),detail.getPbId())));
						}
						//PB编号长度验证
						try {
							if((detail.getPbId().getBytes("UTF-8")).length>32){
								errorList.add(new ValidMessage(getMessage("TRD_PB_E_005",String.valueOf(i+1),detail.getPbId())));
							}
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
			}
		}
		return errorList;
	}

}
