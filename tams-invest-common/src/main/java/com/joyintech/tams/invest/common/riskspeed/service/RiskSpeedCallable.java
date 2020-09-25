package com.joyintech.tams.invest.common.riskspeed.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.riskspeed.dto.IRiskSpeed;
import com.joyintech.tams.invest.common.riskspeed.dto.RiskSpeedDto;
import com.joyintech.tams.invest.common.risktrial.dao.RiskTrialCommMapper;
import com.joyintech.tams.invest.common.risktrial.dto.*;
import com.joyintech.tams.invest.common.risktrial.reflect.PluginClass;
import com.joyintech.tams.invest.common.risktrial.reflect.PluginClassDao;
import com.joyintech.tams.invest.common.risktrial.service.SupervisionCalcSvc;
import com.joyintech.tams.invest.common.risktrial.util.Const;
import com.joyintech.tams.invest.common.risktrial.util.RiskUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;


@Service
public class RiskSpeedCallable<T extends IRiskSpeed> extends BaseService {

	/**
	 * 日志
	 */
	private final Logger logger = LoggerFactory.getLogger(getClass());
	 
	@Autowired
	private SupervisionCalcSvc supervisionCalcSvc;
	@Autowired
	private RiskTrialCommMapper riskTrialCommMapper;

	/**
	 * 获取总投与已投金额
	 * @param taskList
	 * @param inputDto
	 * @return
	 * @throws Exception
	 */
	public List<RiskSpeedDto> getRiskSpeedValue(String speedType, RiskSpeedDto riskSpeedDto, List<SupervisionTargetDto> taskList, InvestTradeTrialInput inputDto) throws Exception{
		List<RiskSpeedDto> riskTrialRetList=new ArrayList<>();
		for (SupervisionTargetDto rskTaskDto : taskList) {
			// 构造
			SystemParam sysParam = new SystemParam();
			BeanUtil.copyPropertiesIgnoreNull(sysParam, inputDto);
			//add by 裴家俊     2019-02-25  添加交易编号条件作为去除本身这笔流程中交易
			sysParam.setTradeCode(RiskUtil.convertPortfCode(StringUtil.isNotEmpty(inputDto.getTradeCode())?inputDto.getTradeCode():"999999999"+Const.tradefix));
			sysParam.setTrdProductDeal(inputDto.getTrdProductDeal()!=null?inputDto.getTrdProductDeal():new TrdProductDeal());
			//---add by jjpei 20190816--塞交易dto进参数实体中-------
			String objectCode = rskTaskDto.getPortfolioLevel().equals(DictCodeListExt1.组合层次_组合包.getValue())?rskTaskDto.getPortfConstId():
					rskTaskDto.getPortfolioId();
			Map<String,Object> paramMap=new HashMap<>();
			paramMap.put("indId",rskTaskDto.getIndId());
			paramMap.put("portfolioLevel",rskTaskDto.getPortfolioLevel());
			paramMap.put("portfolioId",rskTaskDto.getPortfolioId());
			List<String> pageNames= riskTrialCommMapper.selectPageName(paramMap);
			StringBuilder pageName=new StringBuilder();
			pageNames.forEach(item->{
				pageName.append(item+",");
			});
			try {
				sysParam.setPortfolioCode(RiskUtil.convertPortfCode(objectCode));// 单个组合成分/组合包成分
				// 调用基础算法运算符
				ExprTreeMath exprTree = new ExprTreeMath(rskTaskDto.getExpr());
				Set<String> paramNames = exprTree.getParamList();
				Map<String, Object> params = new HashMap<>();
				for (String paramName : paramNames) {
					SupervisionParameterDto indicatorParam = supervisionCalcSvc.getIndicatorParam(paramName,
							DictCodeList.指标参数粒度_实时.getValue());
					if (DictCodeListExt1.指标参数实现类型_SQL.getValue().equals(indicatorParam.getParaDisplayType())) {
						String sql = indicatorParam.getContent();
						Object paramValue = supervisionCalcSvc.getIndicatorParamValue(sql, sysParam, rskTaskDto.getIndType(),rskTaskDto.getFoulCond(),"01");// 获取每个参数实际值
						paramLogDeal(paramValue,rskTaskDto,params,paramName,"组合(包)代码:" + objectCode + "指标名称:" + rskTaskDto.getIndName() + "参数:" + paramName
								+ "值为null\n");
					}
					if (DictCodeListExt1.指标参数实现类型_类名.getValue().equals(indicatorParam.getParaDisplayType())) {
						String className = indicatorParam.getContent();
						PluginClass plugclass = (PluginClass) Class.forName(RiskUtil.COMMON_PLUGIN_NAME+className.trim()).newInstance();
						plugclass.setSysParameter(sysParam,
								(PluginClassDao) SpringContextUtil.getBean(RiskUtil.COMMON_PLUGIN_REFLECT),inputDto);
						Object paramValue = plugclass.getParamterValue();// 获取每个参数实际值
						paramLogDeal(paramValue,rskTaskDto,params,paramName,"组合(包)代码:" + objectCode + "指标名称:" + rskTaskDto.getIndName() + "参数:" + paramName
								+ "值为null\n");
					}
					if (DictCodeListExt1.指标参数实现类型_固定值.getValue().equals(indicatorParam.getParaDisplayType())) {
						params.put(paramName, indicatorParam.getContent());
					}
				}
				if(null!=params) {
					RiskSpeedDto returnDto=new RiskSpeedDto();
					String exprMath = exprTree.replaceParamByMath(rskTaskDto.getExpr(), params, rskTaskDto.getIndType());
					if(speedType.equals("TOTAL")||speedType.equals("ALL"))  {
						Map<String, Object> expMapTotal = RiskUtil.dealParamTotal(exprTree, exprMath, RiskUtil.getVdetailVal(rskTaskDto.getFoulCond()));
						returnDto.setTotalAmt (expMapTotal.get(RiskUtil.TOT_AMT));
					}
					if(speedType.equals("HAS")||speedType.equals("ALL"))  {
						Map<String, Object> expMapHas = RiskUtil.dealParamHas(exprTree, exprMath, RiskUtil.getVdetailVal(rskTaskDto.getFoulCond()));
						returnDto.setHasAmt(expMapHas.get(RiskUtil.HAS_AMT));
					}

					/*//比例类特殊处理：分母（可投总金额）=分子（已投金额）/百分比

					if(rskTaskDto.getIndType().equals(DictCodeListExt1.指标类型_限制投资_比例类.getValue())&& StringUtil.isNotEmpty(returnDto.getHasAmt()) &&(new BigDecimal(returnDto.getHasAmt().toString()).compareTo(new BigDecimal("0"))!=0))
					{
						returnDto.setTotalAmt( new BigDecimal( returnDto.getHasAmt().toString()).divide(new BigDecimal(RiskUtil.getVdetailVal(rskTaskDto.getFoulCond())).divide(new BigDecimal(100))));
					}*/
					returnDto.setPackageName(pageName==null?"":pageName.toString().substring(0,pageName.toString().length()-1));
					returnDto.setIndType(rskTaskDto.getIndType());
					returnDto.setIndCode(rskTaskDto.getIndId());
					returnDto.setIndName(rskTaskDto.getIndName());
					returnDto.setFoulCode(rskTaskDto.getFoulCond());

					riskTrialRetList.add(returnDto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return riskTrialRetList;
	}

	/**
	 * 处理异常参数日志
	 * @param paramValue
	 * @param rskTaskDto
	 * @param params
	 * @param paramName
	 * @param message
	 */
	private void paramLogDeal(Object paramValue,SupervisionTargetDto rskTaskDto,Map<String, Object> params,String paramName,String message){
		boolean logFlag=true;
		if (paramValue == null) {
			if(rskTaskDto.getIndType().indexOf(RiskUtil.LIMIT)!=-1){
				params.put(paramName,new BigDecimal(0));
			}
			else{
				params=null;
			}
			logger.warn(message);
		}
		else{
			params.put(paramName,paramValue);
		}
	}

}
