package com.joyintech.tams.report.submitmaintenance.businessdetailed.valid;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.report.common.util.IBDDataCheckUtil;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.investormerger.dto.ChbEquHoldCheckDto;
import com.joyintech.tams.report.submitmaintenance.businessdetailed.dao.EquityBusiDetails001Mapper;
import com.joyintech.tams.report.submitmaintenance.businessdetailed.dto.EquiteBusiDetails001Dto;
import com.joyintech.tams.report.submitmaintenance.equityinfo.dto.EquityInfo001Dto;
import com.joyintech.tams.report.submitmaintenance.exchangerate.dao.Exchangerate001Mapper;
import com.joyintech.tools.collectors.JoyinCollectors;

/**
 * 投资者明细信息管理-验证类
 * @author qiangchengxi
 *
 */
@Transactional
@Component
public class EquiteBusiDetailsValidator implements BeanValidator{

	@Autowired
    private EquityBusiDetails001Mapper equityBusiDetails001Mapper;
	 /**
     * 查询对应币种的汇率
     */
    @Autowired
    private Exchangerate001Mapper exchangerate001Mapper;
    
	@Override
	public List<ValidMessage> valid(Object value) {
		EquiteBusiDetails001Dto dto = (EquiteBusiDetails001Dto) value;
		ArrayList<ValidMessage> errorList = new ArrayList<>();
		if(StringUtil.equals(dto.getOperateType(), "delete")){
			//1、“删除”状态的数据不能删除。2、批量删除数据时，必须同时选择数据状态相同的数据
			String status=dto.getSelectedItem().get(0).getDataStatus();
			for(EquiteBusiDetails001Dto item:dto.getSelectedItem()){
				if(StringUtil.equals(item.getDataStatus(), DictCodeListExt1.维护数据状态_删除.getValue())){
					errorList.add(new ValidMessage("所选数据中存在已删除数据！"));
					break;
				}
				if(!StringUtil.equals(status, item.getDataStatus())){
					errorList.add(new ValidMessage("只能删除一种数据状态的数据！"));
					break;
				}
			}
		}else if(StringUtil.equals(dto.getOperateType(), "edit")){
			if(StringUtil.equals(dto.getDataStatus(), DictCodeListExt1.维护数据状态_删除.getValue())){
				errorList.add(new ValidMessage("不能编辑已经被删除的数据！"));
			}
		}else if(StringUtil.equals(dto.getOperateType(), "submit")){
			/**
			 * 判断修改的"业务确认日"是否跨区间，是则不能更新
			 * add by hpp 2020-01-03
			 */
			EquiteBusiDetails001Dto oldinfo= equityBusiDetails001Mapper.selectByPrimaryKey(dto);
//			int monthnew = DateUtil.getMonth(DateUtil.format(dto.getEquityConfDate()));
//        	int monthold = DateUtil.getMonth(DateUtil.format(oldinfo.getEquityConfDate()));
			String equityConfDateNEW = DateUtil.format(dto.getEquityConfDate()).substring(0, 7);
        	String equityConfDateOLD = DateUtil.format(oldinfo.getEquityConfDate()).substring(0, 7);
        	if(!StringUtil.equals(equityConfDateNEW, equityConfDateOLD)){
        		errorList.add(new ValidMessage(this.getMessage("CHB_EQUITY_EQUITYCONFDATE")));
        	}
			
			 //获取所有的产品登记编码
		    	List<Map<String, String>> regList = equityBusiDetails001Mapper.queryAllRegCode();
	            	Map<String, String> allProdIdMap = regList.stream().collect(
	            	   JoyinCollectors.toMap(x -> x.get("REG_CODE"), x -> x.get("OPERATE_MODE")));
		    	dto.setOperateMode(allProdIdMap.get(dto.getRegCode()));
			List<ValidMessage> errs = IBDDataCheckUtil.checkDataForPageValid(dto, ReportGlobalConstant.COMMON_CHECK_DATA_TYPE_EQUITY_DETAILS);
			errorList.addAll(errs);
			 // 折算人民币处理
			  // 汇率mapper
          Map<String, BigDecimal> rateMap = new HashMap<>();
			BigDecimal amountCny = dto.getAmountCny();
			String ccy=dto.getCcy();
          if (StringUtil.isNotEmpty(ccy) && StringUtil.isNotEmpty(dto.getEquityConfDate())
              && StringUtil.isNotEmpty(dto.getAmount())) {
              if (!StringUtil.isNotEmpty(amountCny)
                  && !StringUtil.equals(ccy, DictCodeListExt1.币种_中债_人民币.getValue())) {
                  String key = DateUtil.format(dto.getEquityConfDate()) + ccy;
                  if (rateMap.containsKey(key)) {
                      dto.setAmountCny(ArithmeticUtil.multiply(rateMap.get(key),
                          dto.getAmount()).setScale(2, BigDecimal.ROUND_HALF_UP));
                  }
                  else {
                      Map<String, Object> mapObj = new HashMap<String, Object>();
                      // 原币种
                      mapObj.put("CCY_ORIGINAL", dto.getCcy());
  					// 持有日期
                      mapObj.put("HOLDDATE", dto.getEquityConfDate());
  					// 折算币种
                      mapObj.put("CCY_CONVERT", DictCodeListExt1.币种_中债_人民币.getValue());
  					// 汇率计算规则
                      mapObj.put("pricetype",SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_EXCHANGE_SOURCE));
                      BigDecimal rate = exchangerate001Mapper.selectNewRate(mapObj);
                      if (rate != null) {
                          dto.setAmountCny(
                              ArithmeticUtil.multiply(rate, dto.getAmount()).setScale(2,
                                  BigDecimal.ROUND_HALF_UP));
                          rateMap.put(key, rate);
                      }
                      else {
                          ValidMessage validMessage = new ValidMessage("amountCny", this.getMessage("CHB_EQUITY_RATE"));
                          errorList.add(validMessage);
                      }
                  }
              }
              else if (!StringUtil.isNotEmpty(amountCny)
                       && StringUtil.equals(ccy, DictCodeListExt1.币种_中债_人民币.getValue())) {
                  dto.setAmountCny(dto.getAmount());
              }
          }
			if(StringUtil.isNotEmpty(dto.getIndentifyMark())){
				//判断识别标识是否在投资者身份信息表中
				EquityInfo001Dto querydto = new EquityInfo001Dto();
				querydto.setBankCode(dto.getRegistBankCode());
				querydto.setIndentifyMark(dto.getIndentifyMark());
				EquityInfo001Dto infoResult = equityBusiDetails001Mapper.selectEquityInfoByPrimaryKey(querydto);

		        //判断识别标识是否在 权益人信息检查表中
				ChbEquHoldCheckDto queryCheckDto = new ChbEquHoldCheckDto();
				queryCheckDto.setRegistBankCode(dto.getRegistBankCode());
				queryCheckDto.setIndentifyMark(dto.getIndentifyMark());
				ChbEquHoldCheckDto checkResult = equityBusiDetails001Mapper.selectChbEquHoldCheckByPrimaryKey(queryCheckDto);
		        if(!StringUtil.isNotEmpty(infoResult) && !StringUtil.isNotEmpty(checkResult)){
		        	errorList.add(new ValidMessage(this.getMessage("CHB_EQUITY_INDENTIFYMARK")));
		        }
			}
		}
		
		return errorList;
	}

}
