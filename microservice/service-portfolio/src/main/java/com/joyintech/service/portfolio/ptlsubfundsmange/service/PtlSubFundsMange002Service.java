package com.joyintech.service.portfolio.ptlsubfundsmange.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.portfolio.ptlsingleissuerpositionanalysis.dto.PtlSingleIssuerPositionAnalysisDto;
import com.joyintech.service.portfolio.ptlsubfundsmange.dao.PtlSubFundsMange002Mapper;
import com.joyintech.service.portfolio.ptlsubfundsmange.dto.PtlSubFundsMange002Dto;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("backPtlSubFundsMange002Service")
@Transactional(rollbackFor = Exception.class)
public class PtlSubFundsMange002Service extends BaseService<PtlSingleIssuerPositionAnalysisDto> {

	@Autowired
	private PtlSubFundsMange002Mapper ptlSubFundsMange002Mapper;

	/**
	 * 通过产品代码，获取产品名称
	 * 
	 * @param pageParam
	 * @return
	 */
	public FinProduct queryProdName(Map<String, Object> pageParam) {
		FinProduct finProduct = ptlSubFundsMange002Mapper
				.selectFinProductByFinprodMarketId(pageParam.get("finprodId").toString());
		return finProduct;
	}

	/**
	 * 检查：数据存在有效或者流程中的数据
	 * 
	 * @param param
	 * @return
	 */
	public int queryDataEffOrInWorkFlowCount(Map<String, Object> param) {
		return ptlSubFundsMange002Mapper.queryDataEffOrInWorkFlowCount(param);
	}

	/**
	 * 获取资金分配主信息(不同的数据状态)
	 * 
	 * @param pageParam
	 * @return
	 */
	public PtlSubFundsMange002Dto queryPtlSubFundsWithStatue(Map<String, Object> param) {
		String pageDataStatus = param.get("pageDataStatus") == null ? "" : param.get("pageDataStatus").toString();
		param.put("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
		List<PtlSubFundsMange002Dto> pList = null;
		// 1)若画面.有效状态=“流程中”时
		if (StringUtil.equals(DictCodeListExt1.画面数据状态_流程中.getValue(), pageDataStatus)) {
			pList = ptlSubFundsMange002Mapper.queryPtlSubFundsInWorkFlow(param);
		}
		// 2)若画面.有效状态=“有效”时
		else if (StringUtil.equals(DictCodeListExt1.画面数据状态_有效.getValue(), pageDataStatus)) {
			pList = ptlSubFundsMange002Mapper.queryPtlSubFundsInEffective(param);
		}
		if (!CollectionUtils.isEmpty(pList)) {
			// 查询出的数据主信息部分都一致，取其中一条即可
			return pList.get(0);
		}
		return null;
	}

	/**
	 * 获取子组合资金分配明细列表(不同的数据状态)
	 * 
	 * @param pageParam
	 * @return
	 */
	public List<PtlSubFundsMange002Dto> selectPortfolioDetail(Map<String, Object> param) {
		//add by leipeng 20190910 增加产品托管户查询条件
		param.put("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
		param.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
		String pageDataStatus = param.get("pageDataStatus") == null ? "" : param.get("pageDataStatus").toString();
		List<PtlSubFundsMange002Dto> pList = new ArrayList<PtlSubFundsMange002Dto>();
		// 新建 or 3)若画面.有效状态=“待处理”时
		if (StringUtil.equals(DictCodeListExt1.画面数据状态_新建.getValue(), pageDataStatus)
				|| StringUtil.equals(DictCodeListExt1.画面数据状态_待处理.getValue(), pageDataStatus)) {
			pList = ptlSubFundsMange002Mapper.selectPortfolioDetail(param);

			// 初始化分配金额根据交易金额和比例计算分配金额，最后一个子组合的分配金额倒减
			if (!CollectionUtils.isEmpty(pList) && pList.size() > 1) {
				BigDecimal tradeAmtfpAll = BigDecimal.ZERO;
				PtlSubFundsMange002Dto dto;
				BigDecimal disPercent = BigDecimal.ZERO;
				for (int i = 0; i < pList.size(); i++) {
					dto = pList.get(i);
					if (i != pList.size() - 1) {
						// 分配金额=交易金额*比例
						if(null != dto.getDisPercent()) {
							disPercent = dto.getDisPercent();
						}
						dto.setTradeAmtfp(ArithmeticUtil.multiply(ArithmeticUtil.multiply(disPercent,new BigDecimal(0.01)), dto.getTradeAmt()).setScale(2,BigDecimal.ROUND_HALF_UP));
						tradeAmtfpAll = ArithmeticUtil.doAdd(tradeAmtfpAll,dto.getTradeAmtfp());//已分配的总和，给倒减用
					} else {
						// 倒减
						dto.setTradeAmtfp(ArithmeticUtil.subtract(dto.getTradeAmt(), tradeAmtfpAll));
					}
				}
			}
		}
		// 查看模式
		// 2)若画面.有效状态=“有效”时
		else if (StringUtil.equals(DictCodeListExt1.画面数据状态_有效.getValue(), pageDataStatus)) {
			pList = ptlSubFundsMange002Mapper.queryPtlSubFundsInEffective(param);
			if (!CollectionUtils.isEmpty(pList)) {
				pList.forEach(dto -> 
					dto.setTradeAmtfp(StringUtil.equals(DictCodeList.交易方向_产品赎回.getValue(), dto.getPs())
						? ArithmeticUtil.multiply(dto.getTradeAmtfp(), new BigDecimal(-1)) : dto.getTradeAmtfp()));
			}
			
			// 初始化比例根据交易金额和分配金额计算比例，最后一个子组合的比例倒减
			if (!CollectionUtils.isEmpty(pList) && pList.size() > 1) {
				PtlSubFundsMange002Dto dto;
				BigDecimal disPercentAll = BigDecimal.ZERO;
				for (int i = 0; i < pList.size(); i++) {
					dto = pList.get(i);
					if (i != pList.size() - 1) {
						// 比例=分配金额/交易金额
						if(null != dto.getTradeAmtfp()&&null!=dto.getTradeAmt()) {
							dto.setDisPercent(ArithmeticUtil.multiply(dto.getTradeAmtfp().divide(dto.getTradeAmt(),4,BigDecimal.ROUND_HALF_UP),new BigDecimal(100)));
						}
						disPercentAll = ArithmeticUtil.doAdd(disPercentAll,dto.getDisPercent());//已求得的比例总和，给倒减用
					} else {
						// 倒减
						dto.setDisPercent(ArithmeticUtil.subtract(new BigDecimal(100), disPercentAll));
					}
				}
			}
		}
		// 1)若画面.有效状态=“流程中”时
//		else if (StringUtil.equals("true", param.get("isTodoList"))) {
//			pList = ptlSubFundsMange002Mapper.queryPtlSubFundsInWorkFlow(param);
//		}
		else if (StringUtil.equals(DictCodeListExt1.画面数据状态_流程中.getValue(), pageDataStatus)) {
			pList = ptlSubFundsMange002Mapper.queryPtlSubFundsInWorkFlow(param);
			if (!CollectionUtils.isEmpty(pList)) {
				pList.forEach(dto -> 
					dto.setTradeAmtfp(StringUtil.equals(DictCodeList.交易方向_产品赎回.getValue(), dto.getPs())
						? ArithmeticUtil.multiply(dto.getTradeAmtfp(), new BigDecimal(-1)) : dto.getTradeAmtfp()));
			}
		}
		return pList;
	}

	/**
	 * 更新组合现金流水中拆分标识
	 * 
	 * @param dto
	 * @return
	 */
	public int updatePtlCashPositionFlowBySeqnos(Map<String, Object> param) {
		return ptlSubFundsMange002Mapper.updatePtlCashPositionFlowBySeqnos(param);
	}

}
