/*
 * 文件名：CfetsRepoDealQuotedPriceService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yufei
 * 修改时间：2020年5月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.bankuplink.repo.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.bankuplink.bond.service.CfetsBondDealQuotedPrice002Service;
import com.joyintech.tams.invest.bankuplink.common.service.CfetsQuotedCommonService;
import com.joyintech.tams.invest.bankuplink.repo.dto.CfetsRepoDealQuotedPrice001Dto;
import com.joyintech.tams.invest.bankuplink.repo.dto.CfetsRepoDealQuotedPrice003Dto;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.common.BussRepoSecDto;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author yufei
 * @version 1.0 2020年5月14日
 * @see CfetsRepoDealQuotedPriceService
 * @since 1.0
 */
@Service
public class CfetsRepoDealQuotedPrice002Service extends CommonExportService<CfetsRepoDealQuotedPrice001Dto>
		implements BaseProcessService<CfetsRepoDealQuotedPrice001Dto> {

	/**
	 * {@linkplain}
	 */
	@Autowired
	private IPlatformDate iPlatformDate;

	/**
	 * {@linkplain}
	 */
	@Autowired
	private CfetsQuotedCommonService cfetsQuotedCommonService;

	/**
	 * {@linkplain}
	 */
	@Autowired
	private CfetsRepoDealQuotedPrice003Service cfetsRepoDealQuotedPrice003Service;

	/**
	 * {@linkplain}
	 */
	@Autowired
	private CfetsRepoDealQuotedPrice001Service cfetsRepoDealQuotedPrice001Service;

	/**
	 * {@linkplain}
	 */
	@Autowired
	private CfetsBondDealQuotedPrice002Service cfetsBondDealQuotedPrice002Service;

	@Autowired
	private MstWorkflowCommonService mstWorkflowCommonServie;

	/**
	 * 根据委托代码查询委托基本信息
	 * 
	 * @param entrustId
	 *            委托代码
	 * @return 委托基本信息
	 * @see
	 * @since 1.0
	 */
	public CfetsRepoDealQuotedPrice001Dto queryEntrustInfoByEntrustId(String entrustId, String currQuoteId) {
		CfetsRepoDealQuotedPrice001Dto cfetsRepoDealQuotedPrice001Dto = new CfetsRepoDealQuotedPrice001Dto();
		Map<String, Object> param = new HashMap<String, Object>();
		// param.put("platDate", iPlatformDate.getSysDateStr());
		param.put("loginUserId", LoginUserUtil.getUserName());
		param.put("entrustId", entrustId);
		if (StringUtil.isNotBlank(currQuoteId)) {
		    param.put("currQuoteId", currQuoteId);
		}
		
		List<CfetsRepoDealQuotedPrice001Dto> pageList = restTemplate.postForList(
				ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsRepoDealQuotedPrice001/queryEntrustOrderList",
				param, CfetsRepoDealQuotedPrice001Dto.class);

		if (pageList != null && !StringUtil.isEmptyList(pageList)) {
			BeanUtil.copyPropertiesIgnoreNull(cfetsRepoDealQuotedPrice001Dto, pageList.get(0));
		}

		// 计算回购期限 add by zhangliang 2020-07-18
		/*
		 * 这个 回购期限的默认值（根据委托的起息日和到期日）的逻辑，再修改下： 1.如果委托到期日的前N日是连续的节假日：回购期限=到期日-起息日-N个节假日
		 * N为>=1的数值 2.如果委托到期日的前N日不是连续节假日，回购期限=委托到期日-委托起息日
		 */
		if (StringUtil.isNotEmpty(cfetsRepoDealQuotedPrice001Dto.getRepoMdate()) && StringUtil.isNotEmpty(cfetsRepoDealQuotedPrice001Dto.getRepoVdate())
				&& cfetsRepoDealQuotedPrice001Dto.getRepoVdate().compareTo(cfetsRepoDealQuotedPrice001Dto.getRepoMdate()) != 0) {
			Date nextWorkDay = cfetsQuotedCommonService.getNextWorkDay(cfetsRepoDealQuotedPrice001Dto.getRepoVdate());
			if (nextWorkDay.compareTo(cfetsRepoDealQuotedPrice001Dto.getRepoMdate()) == 0) {
				cfetsRepoDealQuotedPrice001Dto.setTerm(1L);
			}
		}

		// 清算速度
		if (StringUtil.equals(cfetsRepoDealQuotedPrice001Dto.getSettleSpreed(), "0")) {
			cfetsRepoDealQuotedPrice001Dto.setSettleSpreed(BussinessDictCodeList.清算速度_T加0.getValue());
		} else if (StringUtil.equals(cfetsRepoDealQuotedPrice001Dto.getSettleSpreed(), "1")) {
			cfetsRepoDealQuotedPrice001Dto.setSettleSpreed(BussinessDictCodeList.清算速度_T加1.getValue());
		}
		// 交割日期
		Date platDate = iPlatformDate.getSysDate();
		if (StringUtil.equals(cfetsRepoDealQuotedPrice001Dto.getSettleSpreed(),
				BussinessDictCodeList.清算速度_T加1.getValue())) {
			platDate = cfetsQuotedCommonService.getNextWorkDay(platDate);
		}
		cfetsRepoDealQuotedPrice001Dto.setSettleDate(platDate);
		// 结算方式
		cfetsRepoDealQuotedPrice001Dto.setSettleMethod(BussinessDictCodeList.结算方式_券款对付.getValue());
		// 清算类型
		cfetsRepoDealQuotedPrice001Dto.setSettleType(BussinessDictCodeList.清算类型_全额清算.getValue());
		// 结算币种
		cfetsRepoDealQuotedPrice001Dto.setSettleCcy(BussinessDictCodeList.币种_人民币.getValue());

		if (cfetsRepoDealQuotedPrice001Dto.getPledgePrinTotal() != null) {
			cfetsRepoDealQuotedPrice001Dto.setPledgePrinTotal(
					cfetsRepoDealQuotedPrice001Dto.getPledgePrinTotal().divide(new BigDecimal(10000)));// 券面总额合计处理
		}
		return cfetsRepoDealQuotedPrice001Dto;
	}

	/**
	 * 根据查询查询符合条件的委托列表
	 * 
	 * @param param
	 *            入参
	 * @return 委托列表
	 * @see
	 * @since 1.0
	 */
	public Page<SelectOptionDto> queryEntrustInfoList(Map<String, Object> param) {
		param.put("platDate", iPlatformDate.getSysDateStr());
		param.put("loginUserId", LoginUserUtil.getUserName());
		Page<SelectOptionDto> pageList = restTemplate.postForPage(
				ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsRepoDealQuotedPrice001/queryEntrustInfoList", param,
				SelectOptionDto.class);
		return pageList;
	}

	/**
	 * 质押券对话报价新增<br />
	 * 对话报价信息入库
	 * 
	 * @param cfetsRepoDealQuotedPrice001Dto
	 *            现券对话报价信息
	 * @return boolean 是否成功
	 */
	public boolean save(CfetsRepoDealQuotedPrice001Dto cfetsRepoDealQuotedPrice001Dto) {
		return restTemplate.postForObject(
				ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsRepoDealQuotedPrice001/insertRepoDealQuote",
				cfetsRepoDealQuotedPrice001Dto, boolean.class);
	}

	/**
	 * 质押券对话报价新增发送<br />
	 * 对话报价信息入库
	 * 
	 * @param cfetsRepoDealQuotedPrice001Dto
	 *            现券对话报价信息
	 * @return boolean 是否成功
	 */
	public boolean submit(CfetsRepoDealQuotedPrice001Dto cfetsRepoDealQuotedPrice001Dto) {
		return restTemplate.postForObject(
				ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsRepoDealQuotedPrice001/sendNewRepoDealQuote",
				cfetsRepoDealQuotedPrice001Dto, boolean.class);
	}

	/**
	 * 根据quoteId查询对话报价信息<br/>
	 * <功能详细描述>
	 * 
	 * @param quoteId
	 * @return 对话报价实体
	 * @see
	 * @since 1.0
	 */
	public CfetsRepoDealQuotedPrice001Dto queryEntrustInfoByQuoteId(String quoteId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("quoteId", quoteId);
		param.put("platDate", iPlatformDate.getSysDateStr());
		CfetsRepoDealQuotedPrice001Dto dto = restTemplate.postForObject(
				ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsRepoDealQuotedPrice001/queryEntrustInfoByQuoteId",
				param, CfetsRepoDealQuotedPrice001Dto.class);
		if (dto.getPledgePrinTotal() != null) {
			dto.setPledgePrinTotal(dto.getPledgePrinTotal().divide(new BigDecimal(10000)));// 券面总额合计处理
		}
		return dto;
	}

	public AjaxResult checkEdit(Map<String, Object> param) {
		AjaxResult ar = new AjaxResult();
		// 验证信息
		List<ValidMessage> validList = new ArrayList<ValidMessage>();
		String entrustId = (String) param.get("entrustId");
		String operation = (String) param.get("operation");
		String quoteId = (String) param.get("quoteId");
		CfetsRepoDealQuotedPrice001Dto dto = null;
		if(StringUtil.isNotEmpty(quoteId)){
			validList.addAll(isInWflProcess(quoteId,param.get("pageId").toString()));
			if(validList.size()>0){
				ar.setErrorList(validList);
				return ar;
			}
		}
		if (StringUtil.isNotEmpty(entrustId)) {
			dto = this.queryEntrustInfoByEntrustId(entrustId, quoteId);
		}

		if (StringUtil.isNotEmpty(operation)
				&& StringUtil.equals(operation, "CfetsRepoDealQuotedPrice001.dayOff.revoke")) {
			// 删除操作校验
			CfetsRepoDealQuotedPrice001Dto dayOffer = StringUtil.isNotEmpty(quoteId)
					? this.queryEntrustInfoByQuoteId(quoteId)
					: null;
			if (dayOffer == null) {
				validList.add(new ValidMessage("报价不存在，请检查数据状态！"));
			}
			// 发送状态
			String sendStatus = (String) param.get("sendStatus");
			if (!StringUtil.isNotEmpty(sendStatus) || !StringUtil.equals(sendStatus, dayOffer.getSendStatus())) {
				validList.add(new ValidMessage("状态已变更，请重新检查数据状态！"));
			}
			// 发送状态
			if (!StringUtil.equals(BussinessDictCodeList.银行间上行发送状态_待发送.getValue(), dayOffer.getSendStatus())) {
				validList.add(new ValidMessage("报价不满足【发送状态为待发送】的条件，请检查数据状态！"));
			}
			ar.setData(this.getMessage("COMMON_C_0001", this.getMessage("SysCommon.save")));
		} else if (StringUtil.isNotEmpty(operation)
				&& StringUtil.equals(operation, "CfetsRepoDealQuotedPrice001.dayOff.restore")) {
			// 回退操作校验
			CfetsRepoDealQuotedPrice001Dto dayOffer = StringUtil.isNotEmpty(quoteId)
					? this.queryEntrustInfoByQuoteId(quoteId)
					: null;
			if (dayOffer == null) {
				validList.add(new ValidMessage("报价不存在，请检查数据状态！"));
			}
			// 校验交易状态
			String tradeStatus = (String) param.get("tradeStatus");
			if (!StringUtil.isNotEmpty(tradeStatus) || !StringUtil.equals(tradeStatus, dayOffer.getTradeStatus())) {
				validList.add(new ValidMessage("状态已变更，请重新检查数据状态！"));
			}
			// 发送状态
			String sendStatus = (String) param.get("sendStatus");
			if (!StringUtil.isNotEmpty(sendStatus) || !StringUtil.equals(sendStatus, dayOffer.getSendStatus())) {
				validList.add(new ValidMessage("状态已变更，请重新检查数据状态！"));
			}
			// 发送状态
			if (!StringUtil.equals(BussinessDictCodeList.银行间上行发送状态_失败.getValue(), dayOffer.getSendStatus())) {
				validList.add(new ValidMessage("报价不满足【交易状态为新增中或修改中或交谈中或成交中或终止中或撤回中、发送状态为失败】的条件，请检查数据状态！"));
			}else{
                if (StringUtil.equals(BussinessDictCodeList.银行间上行本方角色_询价方.getValue(), dayOffer.getQuoteRoleType())) {
                    // 校验交易状态
                    if (!StringUtil.equals(BussinessDictCodeList.银行间上行报价状态_新增中.getValue(), dayOffer.getTradeStatus())
                            && !StringUtil.equals(BussinessDictCodeList.银行间上行报价状态_成交中.getValue(), dayOffer.getTradeStatus())
                            && !StringUtil.equals(BussinessDictCodeList.银行间上行报价状态_终止中.getValue(), dayOffer.getTradeStatus())
                            && !StringUtil.equals(BussinessDictCodeList.银行间上行报价状态_撤回中.getValue(), dayOffer.getTradeStatus())
                            &&!StringUtil.equals(BussinessDictCodeList.银行间上行报价状态_修改中.getValue(), dayOffer.getTradeStatus())
                            &&!StringUtil.equals(BussinessDictCodeList.银行间上行报价状态_交谈中.getValue(), dayOffer.getTradeStatus())) {
                        validList.add(new ValidMessage("报价不满足【交易状态为新增中或修改中或交谈中或成交中或终止中或撤回中、发送状态为失败】的条件，请检查数据状态！"));
                    }
                    /*if (!StringUtil.equals(BussinessDictCodeList.银行间上行报价状态_新增中.getValue(), dayOffer.getTradeStatus())
                            && !StringUtil.equals(BussinessDictCodeList.银行间上行报价状态_成交中.getValue(), dayOffer.getTradeStatus())
                            && !StringUtil.equals(BussinessDictCodeList.银行间上行报价状态_终止中.getValue(), dayOffer.getTradeStatus())
                            && !StringUtil.equals(BussinessDictCodeList.银行间上行报价状态_撤回中.getValue(), dayOffer.getTradeStatus())) {
                        validList.add(new ValidMessage("报价不满足【交易状态为新增中或修改中或交谈中或成交中或终止中或撤回中、发送状态为失败】的条件，请检查数据状态！"));
                    }*/
                }
                else if(StringUtil.equals(BussinessDictCodeList.银行间上行本方角色_回复方.getValue(), dayOffer.getQuoteRoleType())) {
                    if (!StringUtil.equals(BussinessDictCodeList.银行间上行报价状态_新增中.getValue(), dayOffer.getTradeStatus())
                            && !StringUtil.equals(BussinessDictCodeList.银行间上行报价状态_成交中.getValue(), dayOffer.getTradeStatus())
                            && !StringUtil.equals(BussinessDictCodeList.银行间上行报价状态_终止中.getValue(), dayOffer.getTradeStatus())
                            && !StringUtil.equals(BussinessDictCodeList.银行间上行报价状态_撤回中.getValue(), dayOffer.getTradeStatus())
                            &&!StringUtil.equals(BussinessDictCodeList.银行间上行报价状态_交谈中.getValue(), dayOffer.getTradeStatus())) {
                        validList.add(new ValidMessage("报价不满足【交易状态为新增中或修改中或交谈中或成交中或终止中或撤回中、发送状态为失败】的条件，请检查数据状态！"));
                    }
                }
            }
			ar.setData(this.getMessage("COMMON_C_0001", this.getMessage("SysCommon.save")));
		} else if (StringUtil.isNotEmpty(quoteId) && StringUtil.isNotEmpty(operation)
				&& StringUtil.equals(operation, "CfetsRepoDealQuotedPrice001.revert")) {
			// 校验撤回
			CfetsRepoDealQuotedPrice001Dto dayOffer = this.queryEntrustInfoByQuoteId(quoteId);
			// 发送状态
			String sendStatus = (String) param.get("sendStatus");
			// 交易状态
			String tradeStatus = (String) param.get("tradeStatus");
			boolean flag =false;
			if (!StringUtil.equals(sendStatus, dayOffer.getSendStatus())
					|| !StringUtil.equals(tradeStatus, dayOffer.getTradeStatus())) {
//				validList.add(new ValidMessage("状态已变更，请重新检查数据状态！"));
				flag =true;
			}
            // 校验本方角色
            if (StringUtil.equals(BussinessDictCodeList.银行间上行本方角色_询价方.getValue(), dayOffer.getQuoteRoleType())) {
                if (!((StringUtil.equals(dayOffer.getSendStatus(), BussinessDictCodeList.银行间上行发送状态_成功.getValue())
                        && (StringUtil.equals(dayOffer.getTradeStatus(), BussinessDictCodeList.银行间上行报价状态_已新增.getValue())
                        || StringUtil.equals(dayOffer.getTradeStatus(), BussinessDictCodeList.银行间上行报价状态_已修改.getValue())
                        || StringUtil.equals(dayOffer.getTradeStatus(), BussinessDictCodeList.银行间上行报价状态_已交谈.getValue()))))) {
//                    validList.add(new ValidMessage("报价不满足【交易状态为已新增、已修改、已交谈、发送状态为成功】的条件，请检查数据状态！"));
                	flag =true;
                }
                //validList.add(new ValidMessage("只能撤回本方角色为询价方的报价数据！"));
            }
			else if(StringUtil.equals(BussinessDictCodeList.银行间上行本方角色_回复方.getValue(), dayOffer.getQuoteRoleType())){
                if (!(StringUtil.equals(dayOffer.getSendStatus(), BussinessDictCodeList.银行间上行发送状态_成功.getValue())
                        &&StringUtil.equals(dayOffer.getTradeStatus(), BussinessDictCodeList.银行间上行报价状态_已交谈.getValue()))) {
//                    validList.add(new ValidMessage("报价不满足【交易状态为已交谈、发送状态为成功】的条件，请检查数据状态！"));
                	flag =true;
                }
            }
            if(flag){
				validList.add(new ValidMessage("交易状态和发送状态分别为已新增/成功，已修改/成功，已交谈/成功的对话报价，才能执行撤回操作！"));
			}
			ar.setData(this.getMessage("COMMON_C_0001", this.getMessage("SysCommon.save")));
		} else if (StringUtil.isNotEmpty(quoteId) && StringUtil.isNotEmpty(operation)
				&& StringUtil.equals(operation, "CfetsRepoDealQuotedPrice001.reply")) {
			// 当日报价回复校验
			CfetsRepoDealQuotedPrice001Dto dayOffer = this.queryEntrustInfoByQuoteId(quoteId);
			// 交易状态
			String tradeStatus = (String) param.get("tradeStatus");
			if (!StringUtil.isNotEmpty(tradeStatus) || !StringUtil.equals(tradeStatus, dayOffer.getTradeStatus())
					|| !StringUtil.equals(tradeStatus, BussinessDictCodeList.银行间上行报价状态_待回复.getValue())) {
				validList.add(new ValidMessage("状态已变更，请重新检查数据状态！"));
			}
			// 发送状态
			String sendStatus = (String) param.get("sendStatus");
			if (StringUtil.isNotEmpty(sendStatus) || StringUtil.isNotEmpty(dayOffer.getSendStatus())) {
				validList.add(new ValidMessage("状态已变更，请重新检查数据状态！"));
			}
			ar.setData(this.getMessage("COMMON_C_0001", this.getMessage("SysCommon.save")));
		} else if (StringUtil.isNotEmpty(operation)
				&& StringUtil.equals(operation, "CfetsRepoDealQuotedPrice001.reply.reject")) {
			// 回复拒绝操作校验
			if (StringUtil.isNotEmpty(quoteId)) {
				// 当日报价双击校验
				CfetsRepoDealQuotedPrice001Dto dayOffer = this.queryEntrustInfoByQuoteId(quoteId);
				if (dayOffer == null) {
					validList.add(new ValidMessage("报价不存在，请检查数据状态！"));
				}
				// 交易状态
				String tradeStatus = (String) param.get("tradeStatus");
				if (!StringUtil.isNotEmpty(tradeStatus) || !StringUtil.equals(tradeStatus, dayOffer.getTradeStatus())
						|| !StringUtil.equals(tradeStatus, BussinessDictCodeList.银行间上行报价状态_待回复.getValue())) {
					validList.add(new ValidMessage("报价不满足【交易状态为待回复】的条件，请检查数据状态！"));
				}
			}
			ar.setData(this.getMessage("COMMON_C_0001", this.getMessage("SysCommon.save")));
		} else if (StringUtil.isNotEmpty(operation)
				&& StringUtil.equals(operation, "CfetsRepoDealQuotedPrice001.edit")) {
			// 当日报价双击校验
			CfetsRepoDealQuotedPrice001Dto dayOffer = this.queryEntrustInfoByQuoteId(quoteId);
			String sendStatus = (String) param.get("sendStatus");
			boolean flag=false;
			// 发送状态
			if (!StringUtil.equals(sendStatus, dayOffer.getSendStatus())) {
//				validList.add(new ValidMessage("状态已变更，请重新检查数据状态！"));
				flag=true;
			}
			if (!((StringUtil.equals(dayOffer.getSendStatus(), BussinessDictCodeList.银行间上行发送状态_成功.getValue())
					&& (StringUtil.equals(dayOffer.getTradeStatus(), BussinessDictCodeList.银行间上行报价状态_已新增.getValue()))
					|| StringUtil.equals(dayOffer.getTradeStatus(), BussinessDictCodeList.银行间上行报价状态_已修改.getValue())
					|| StringUtil.equals(dayOffer.getTradeStatus(), BussinessDictCodeList.银行间上行报价状态_已交谈.getValue()))
					|| (null == dayOffer.getSendStatus() && StringUtil
							.equals(dayOffer.getTradeStatus(), BussinessDictCodeList.银行间上行报价状态_待回复.getValue())))) {
				// 银行间上行报价状态_已新增)
//				validList.add(new ValidMessage("当前数据状态不符合修改要求！"));
				flag=true;
			}
			if(flag){
				validList.add(new ValidMessage("交易状态和发送状态分别为已新增/成功，已修改/成功，已交谈/成功，待回复/空的对话报价，才能执行修改操作！"));
			}
			ar.setData(this.getMessage("COMMON_C_0001", this.getMessage("SysCommon.save")));
		} else if (StringUtil.isNotEmpty(operation)
				&& StringUtil.equals(operation, "CfetsRepoDealQuotedPrice001.new")) {
			// 对话报价新增校验
			// 判断是否选择委托
			if (StringUtil.isNotEmpty(entrustId)) {
				// 对话报价校验
				if (dto == null) {
					validList.add(new ValidMessage("指令代码不存在"));
				}
				if (!StringUtil.equals(dto.getEntrustStatus(), BussinessDictCodeList.委托状态_已接收.getValue())) {
					validList.add(new ValidMessage(this.getMessage("CFETS_COMMON_R_024")));
				}
				if (StringUtil.equals(dto.getEntrustStatus(), BussinessDictCodeList.委托状态_已接收.getValue())
						&& dto.getTransactioncom().compareTo(new BigDecimal(100)) >= 0) {
					validList.add(new ValidMessage(this.getMessage("CFETS_COMMON_R_023")));
				}
				/*
				 * if ((DateUtil.compareDate(dto.getVdate(), iPlatformDate.getSysDate()) == 1)
				 * || (DateUtil.compareDate(dto.getMdate(), iPlatformDate.getSysDate()) == -1))
				 * { validList.add(new ValidMessage(this.getMessage("CFETS_COMMON_R_022"))); }
				 */
			}
			ar.setData(this.getMessage("COMMON_C_0001", this.getMessage("SysCommon.save")));
		} else if (StringUtil.isNotEmpty(operation)
				&& StringUtil.equals(operation, "CfetsRepoDealQuotedPrice001.copy")) {
			// 当日报价复制校验
			CfetsRepoDealQuotedPrice001Dto dayOffer = this.queryEntrustInfoByQuoteId(quoteId);
			if (dayOffer != null) {
				boolean  flag = false;
				// 校验本方角色
				if (!StringUtil.equals(BussinessDictCodeList.银行间上行本方角色_询价方.getValue(), dayOffer.getQuoteRoleType())) {
//					validList.add(new ValidMessage("只能复制本方角色为询价方的报价数据！"));
					flag=true;
				}
				// 交易状态
				String tradeStatus = (String) param.get("tradeStatus");
				String sendStatus = (String) param.get("sendStatus");
				if (!StringUtil.equals(tradeStatus, dayOffer.getTradeStatus())
						|| !StringUtil.equals(sendStatus, dayOffer.getSendStatus())) {
//					validList.add(new ValidMessage("状态已变更，请重新检查数据状态！"));
					flag=true;
				}
				// 校验交易状态
				if (!StringUtil.equals(BussinessDictCodeList.银行间上行报价状态_已新增.getValue(), dayOffer.getTradeStatus())
						&& !StringUtil.equals(BussinessDictCodeList.银行间上行报价状态_已成交.getValue(), dayOffer.getTradeStatus())
						&& !StringUtil.equals(BussinessDictCodeList.银行间上行报价状态_已终止.getValue(), dayOffer.getTradeStatus())
						&& !StringUtil.equals(BussinessDictCodeList.银行间上行报价状态_已撤回.getValue(),dayOffer.getTradeStatus())
                        && !StringUtil.equals(BussinessDictCodeList.银行间上行报价状态_已修改.getValue(),dayOffer.getTradeStatus())
                        && !StringUtil.equals(BussinessDictCodeList.银行间上行报价状态_已交谈.getValue(),dayOffer.getTradeStatus())) {
//					validList.add(new ValidMessage("只能复制交易状态为已新增/已成交/已终止/已撤回/已修改/已交谈的报价数据！"));
					flag=true;
				}
				/*
				 * if (!StringUtil.equals(BussinessDictCodeList.银行间上行发送状态_成功.getValue(),
				 * dayOffer.getSendStatus())) { validList.add(new
				 * ValidMessage("只能复制发送状态为成功的报价数据！")); }
				 */
				if(flag){
					validList.add(new ValidMessage("本方角色为询价方，交易状态和发送状态分别为已新增/成功，已修改/成功，已交谈/成功，已撤回/成功，已成交/成功，已终止 /成功的对话报价，才能执行复制操作！"));
				}
			}
			ar.setData(this.getMessage("COMMON_C_0001", this.getMessage("SysCommon.save")));
		} else {
			ar.setData(this.getMessage("COMMON_C_0001", this.getMessage("SysCommon.save")));
		}
		ar.setErrorList(validList);
		return ar;
	}

	/**
	 * 校验回复成交的质押品数据<br/>
	 * <功能详细描述>
	 * 
	 * @param dto
	 *            委托对象
	 * @param dayOffer
	 *            当日报价对象
	 * @see
	 * @since 1.0
	 */
	public String checkPledgeBondList(CfetsRepoDealQuotedPrice001Dto dto, CfetsRepoDealQuotedPrice001Dto dayOffer) {
		// 验证信息
		List<ValidMessage> validList = new ArrayList<ValidMessage>();
		if (!StringUtil.isEmptyList(dayOffer.getPledgeBondList())) {
			if (StringUtil.equals(dayOffer.getPs(), BussinessDictCodeList.回购拆借方向_正回购.getValue())) {
				// 查询债券相关信息
				List<CfetsRepoDealQuotedPrice003Dto> pledgeBondList = null;
				List<String> bondCodes = dayOffer.getPledgeBondList().stream().map(obj -> obj.getBondCode())
						.collect(Collectors.toList());
				Map<String, Object> param = new HashMap<>();
				param.put("portfolioId", dto == null ? dayOffer.getPortfolioId() : dto.getPortfolioId());
				param.put("secAcctId", dto == null ? "" : dto.getSecAcctId());
				param.put("finprodMarketIds", bondCodes);
				// 正回购
				pledgeBondList = cfetsRepoDealQuotedPrice003Service.queryPledgeBondList(param).getRoot();
				if (!StringUtil.isEmptyList(pledgeBondList)) {
					pledgeBondList.forEach(x -> {
						dayOffer.getPledgeBondList().forEach(obj -> {
							if (obj.getBondCode().equals(x.getBondCode())) {
								// 校验本次质押是否大于剩余可质押面额
								if (x.getAvaPledgeNumber().compareTo(
										ArithmeticUtil.multiply(obj.getPledgePrin(), new BigDecimal(10000))) == -1) {
									validList.add(
											new ValidMessage(this.getMessage("CFETS_COMMON_R_025", obj.getBondCode())));
								}
							}
						});
					});
				}
			}
		}
		String result = "";
		if (!StringUtil.isEmptyList(validList)) {
			for (ValidMessage validMessage : validList) {
				result += validMessage.getMessage();
			}
		}
		return result;
	}

	/**
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @param param
	 * @see
	 * @since 1.0
	 */
	public boolean revert(Map<String, Object> param) {
		param.put("userName", LoginUserUtil.getUserName());
		param.put("deptCode", LoginUserUtil.getDeptCode());
		return restTemplate.postForObject(
				ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsRepoDealQuotedPrice001/revert", param,
				boolean.class);

	}

	/**
	 * 拒绝 <功能详细描述>
	 * 
	 * @param param
	 *            入参
	 * @see
	 * @since 1.0
	 */
	public boolean reject(Map<String, Object> param) {
		param.put("userName", LoginUserUtil.getUserName());
		param.put("deptCode", LoginUserUtil.getDeptCode());
		restTemplate.postForObject(ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsRepoDealQuotedPrice001/reject",
				param, boolean.class);
		return true;
	}

	/**
	 * 成交 <功能详细描述>
	 * 
	 * @param param
	 * @see
	 * @since 1.0
	 */
	public boolean deal(Map<String, Object> param) {
		param.put("userName", LoginUserUtil.getUserName());
		param.put("deptCode", LoginUserUtil.getDeptCode());
		restTemplate.postForObject(ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsRepoDealQuotedPrice001/deal",
				param, boolean.class);
		return true;
	}

	/**
	 * 回退报价 <功能详细描述>
	 * 
	 * @param param
	 *            参数
	 * @return 结果
	 * @see
	 * @since 1.0
	 */
	public boolean restoreDayOff(Map<String, Object> param) {
		param.put("userName", LoginUserUtil.getUserName());
		param.put("deptCode", LoginUserUtil.getDeptCode());
		restTemplate.postForObject(
				ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsRepoDealQuotedPrice001/restoreDayOff", param,
				boolean.class);
		return true;
	}

	/**
	 * 删除报价 <功能详细描述>
	 * 
	 * @param param
	 *            参数
	 * @return 结果
	 * @see
	 * @since 1.0
	 */
	public boolean deleteDayOff(Map<String, Object> param) {
		param.put("userName", LoginUserUtil.getUserName());
		param.put("deptCode", LoginUserUtil.getDeptCode());
		restTemplate.postForObject(
				ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsRepoDealQuotedPrice001/deleteDayOff", param,
				boolean.class);
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doComplete(CfetsRepoDealQuotedPrice001Dto entity, OperationType type) {
		if (StringUtil.equals(entity.getQuotaPageModel(), "dialogueResponse")) {
			// 对话回应
			if (StringUtil.equals(entity.getOperatorType(), "done")) {
				// 成交
				restTemplate.postForObject(ServiceApplication.BANKUPLINK_SERVICE_NAME
						+ "/CfetsRepoDealQuotedPrice001/deal", entity, boolean.class);
			} else if (StringUtil.equals(entity.getOperatorType(), "refuse")) {
				// 拒绝
				restTemplate.postForObject(ServiceApplication.BANKUPLINK_SERVICE_NAME
						+ "/CfetsRepoDealQuotedPrice001/reject", entity, boolean.class);
			}
		} else if (StringUtil.equals(entity.getQuotaPageModel(), "new")) {
			// 新增
			if (StringUtil.equals(entity.getOperatorType(), "save")) {
				// 保存
				restTemplate.postForObject(ServiceApplication.BANKUPLINK_SERVICE_NAME
						+ "/CfetsRepoDealQuotedPrice001/insertRepoDealQuote", entity, boolean.class);
			} else if (StringUtil.equals(entity.getOperatorType(), "submit")) {
				// 提交
				restTemplate.postForObject(ServiceApplication.BANKUPLINK_SERVICE_NAME
						+ "/CfetsRepoDealQuotedPrice001/sendNewRepoDealQuote", entity, boolean.class);
			}
		} else if (StringUtil.equals(entity.getQuotaPageModel(), "edit")) {
			// 修改
			if (StringUtil.equals(entity.getOperatorType(), "save")) {
				// 保存
				restTemplate.postForObject(ServiceApplication.BANKUPLINK_SERVICE_NAME
						+ "/CfetsRepoDealQuotedPrice001/insertRepoDealQuote", entity, boolean.class);
			} else if (StringUtil.equals(entity.getOperatorType(), "submit")) {
				// 提交
				restTemplate.postForObject(ServiceApplication.BANKUPLINK_SERVICE_NAME
						+ "/CfetsRepoDealQuotedPrice001/modify", entity, boolean.class);
			}
		}
		else {
			//撤销
			if(StringUtil.equals(entity.getOperatorType(), "revoke")){
				restTemplate.postForObject(
						ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsRepoDealQuotedPrice001/doHisRevoke", entity,
						Boolean.class);
			}
		}
		doDeleteWflDataForRepo(entity.getWorkflowId());
	}

	/**
	 * 对话报价回复成交校验
	 * 
	 * @param param
	 *            参数
	 * @return 校验结果
	 * @see
	 * @since 1.0
	 */
	public AjaxResult checkRepoDeal(CfetsRepoDealQuotedPrice001Dto cfetsRepoDealQuotedPrice001Dto) {
		AjaxResult ar = new AjaxResult();
		StringBuffer sb = new StringBuffer();
		// 验证信息
		List<ValidMessage> validList = new ArrayList<ValidMessage>();
		String entrustId = cfetsRepoDealQuotedPrice001Dto.getEntrustId();
		String quoteId = cfetsRepoDealQuotedPrice001Dto.getQuoteId();
		CfetsRepoDealQuotedPrice001Dto dayOffer = null;
		// 回复成交操作校验
		if (StringUtil.isNotEmpty(quoteId)) {
			// 当日报价双击校验
			dayOffer = this.queryEntrustInfoByQuoteId(quoteId);
			if (dayOffer != null) {
				// 交易状态
				String tradeStatus = cfetsRepoDealQuotedPrice001Dto.getTradeStatus();
				if (!StringUtil.isNotEmpty(tradeStatus) || !StringUtil.equals(tradeStatus, dayOffer.getTradeStatus())
						|| !StringUtil.equals(tradeStatus, BussinessDictCodeList.银行间上行报价状态_待回复.getValue())) {
					validList.add(new ValidMessage("报价不满足【交易状态为待回复】的条件，请检查数据状态！"));
				}
			} else {
				validList.add(new ValidMessage("报价信息不存在"));
				ar.setErrorList(validList);
				return ar;
			}
		}
		//多组合
		if(!StringUtils.equals(cfetsRepoDealQuotedPrice001Dto.getIsDzh(),"Y")){
			// 存在委托代码
			if (!StringUtil.isNotEmpty(entrustId)
					&& StringUtil.equals("01", CacheUtil.getSysParam("ibuFlag_answerQuoteWithOutEntrust"))) {
				// 硬阻挡无委托模式
				validList.add(new ValidMessage(this.getMessage("CFETS_COMMON_E_009")));
				ar.setErrorList(validList);
				return ar;
			} else if (!StringUtil.isNotEmpty(entrustId)
					&& StringUtil.equals("02", CacheUtil.getSysParam("ibuFlag_answerQuoteWithOutEntrust"))) {
				// 软阻挡无委托模式
				sb.append(this.getMessage("CFETS_COMMON_E_009"));
				sb.append(GlobalConstant.HTML_NEWLINE);
				// 如果无委托回复成交，校验是否必填组合代码
				String portfolioId = cfetsRepoDealQuotedPrice001Dto.getPortfolioId();
				if (!StringUtil.isNotEmpty(portfolioId)) {
					validList.add(new ValidMessage("portfolioName", "请选择组合代码"));
				}
			} else if (StringUtil.isNotEmpty(entrustId)) {
				// 有委托模式
				CfetsRepoDealQuotedPrice001Dto dto = this.queryEntrustInfoByEntrustId(entrustId, quoteId);
				// 委托代码是否存在
				if (dto != null) {
					// 需校验回购利率
					String ps03 = BussinessDictCodeList.回购拆借方向_正回购.getValue();
					if (StringUtil.equals(dto.getPs(), ps03)) {
						if ((dto.getPrice() != null && dayOffer.getPrice() != null
							&& dto.getPrice().compareTo(dayOffer.getPrice()) == -1)) {
							// 正回购，需校验回购利率不能大于委托指令的回购利率
							if (StringUtil.equals("01", CacheUtil.getSysParam("ibuFlag_repoOverEntrustPrice"))) {
								// 硬阻挡
								validList.add(new ValidMessage("报价回购利率不能大于指令回购利率" + dto.getPrice()));
								ar.setErrorList(validList);
								return ar;
							} else {
								// 软阻挡
								sb.append("报价回购利率不能大于指令回购利率" + dto.getPrice());
								sb.append(GlobalConstant.HTML_NEWLINE);
							}
						}
						// 校验质押品是否超过可质押面额
						List<CfetsRepoDealQuotedPrice003Dto> pledgeBondList = null;
						if (StringUtil.isEmptyList(dayOffer.getPledgeBondList())) {
							// 本币客户端发起的逆回购报价，未添加质押券，本方点击回复后添加指令点击成交时报异常，应该提示无质押券信息不可成交。
							validList.add(new ValidMessage("无质押券信息不可成交"));
							ar.setErrorList(validList);
							return ar;
						}
						else {
						 // 获取报价质押品代码集合
							List<String> bondCodes = dayOffer.getPledgeBondList().stream().map(obj -> obj.getBondCode())
									.collect(Collectors.toList());
							Map<String, Object> queryParam = new HashMap<>();
							queryParam.put("portfolioId", dto.getPortfolioId());
							queryParam.put("mySide", dayOffer.getMySide());
							queryParam.put("secAcctId", dto.getSecAcctId());
							queryParam.put("finprodMarketIds", bondCodes);
							// 正回购
							pledgeBondList = cfetsRepoDealQuotedPrice003Service.queryPledgeBondList(queryParam).getRoot();
							if (!StringUtil.isEmptyList(pledgeBondList)) {
								for (CfetsRepoDealQuotedPrice003Dto x : pledgeBondList) {
									cfetsRepoDealQuotedPrice001Dto.getBussRepoSec().getPledgeBondList().forEach(obj -> {
										if (obj.getBondCode().equals(x.getBondCode())) {
											// 校验本次质押是否大于剩余可质押面额
											if (obj.getPledgePrin().compareTo(x.getAvaPledgePrin()) == 1) {
												validList.add(new ValidMessage(
														this.getMessage("CFETS_COMMON_R_025", obj.getBondCode())));
											}
										}
									});
								}
								/*for (CfetsRepoDealQuotedPrice003Dto x : pledgeBondList) {
									dayOffer.getPledgeBondList().forEach(obj -> {
										if (obj.getBondCode().equals(x.getBondCode())) {
											// 校验本次质押是否大于剩余可质押面额
											if (ArithmeticUtil.doAdd(x.getAvaPledgeNumber(), obj.getPledgePrin()).compareTo(obj.getPledgePrin()) == -1) {
												validList.add(new ValidMessage(
														this.getMessage("CFETS_COMMON_R_025", obj.getBondCode())));
											}
										}
									});
								}*/
							}
						}
					}
					// 逆回购
					String ps04 = BussinessDictCodeList.回购拆借方向_逆回购.getValue();
					// 逆回购，需实时校验该笔报价的交易金额需要小于或等于委托剩余报价额度
					if (StringUtil.equals(dto.getPs(), ps04)) {
						if ((dayOffer.getPrice() != null && dto.getPrice() != null
							&& dayOffer.getPrice().compareTo(dto.getPrice()) == -1)) {
							if (StringUtil.equals("01", CacheUtil.getSysParam("ibuFlag_repoOverEntrustPrice"))) {
								// 硬阻挡
								validList.add(new ValidMessage("报价回购利率不能小于指令回购利率" + dto.getPrice()));
								ar.setErrorList(validList);
								return ar;
							} else {
								// 软阻挡
								sb.append("报价回购利率不能小于指令回购利率" + dto.getPrice());
								sb.append(GlobalConstant.HTML_NEWLINE);
							}
						}
						// 逆回购，需实时校验该笔报价的交易金额需要小于或等于委托剩余报价额度
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("entrustId", entrustId);
						map.put("quoteId", quoteId);
						BigDecimal dealedAmt = cfetsBondDealQuotedPrice002Service.queryEntrustDealedAmt(map);
						if (dayOffer.getDealAmt().compareTo(ArithmeticUtil.subtract(dto.getEntrustNum(), dealedAmt)) > 0) {
							if (StringUtil.equals("01", CacheUtil.getSysParam("ibuFlag_repoOverEntrustAvaAmt"))) {
								// 硬阻挡
								validList.add(new ValidMessage("交易金额不能大于该笔指令的剩余报价额度" + dto.getAvaNum()));
								ar.setErrorList(validList);
								return ar;
							} else {
								// 软阻挡
								sb.append("交易金额需要<=该笔指令的剩余报价额度" + dto.getAvaNum());
								sb.append(GlobalConstant.HTML_NEWLINE);
							}
						}
						// 如果为逆回购，需要检查质押券发行人的R1额度
	/*					if (!StringUtil.equals(CacheUtil.getSysParam("ibuFlag_repoSendPledgeQuoteQuery"), "N")) {
							dto.setPledgeBondList(cfetsRepoDealQuotedPrice001Dto.getPledgeBondList());
							String msg = cfetsRepoDealQuotedPrice001Service.checkPledgeCreditAmtLimit(dto);
							if (StringUtil.isNotEmpty(msg)) {
								if (!StringUtil.equals(CacheUtil.getSysParam("ibuFlag_repoPledgeQuoteOverCanContinue"),
										"02")) {
									validList.add(new ValidMessage(msg));
								} else {
									// 软阻挡
									sb.append(msg);
									sb.append(GlobalConstant.HTML_NEWLINE);
								}
							}
						}*/
					}
				} else {
					validList.add(new ValidMessage(entrustId + "不存在有效的指令"));
				}
			}
		}
		ar.setErrorList(validList);
		if (StringUtil.isEmptyList(validList) && sb.length() == 0) {
			sb.append(this.getMessage("CFETS_COMMON_E_032"));
		} else if (sb.length() > 0) {
			sb.append("请确认是否继续？");
		}
		ar.setData(sb);
		return ar;
	}

	public void   checkRepoDealByEntrustId(){

	}
	/**
	 * 根据实际指令类型来生成流程信息 <一句话功能简述> <功能详细描述>
	 * 
	 * @param dto
	 * @return
	 * @see
	 * @since 1.0
	 */
	public WorkFlowParameter<CfetsRepoDealQuotedPrice001Dto> getWorkFlowParameter(CfetsRepoDealQuotedPrice001Dto dto) {
		WorkFlowParameter<CfetsRepoDealQuotedPrice001Dto> parameter = new WorkFlowParameter<CfetsRepoDealQuotedPrice001Dto>();
		// TODO 根据实际指令类型来生成流程信息
		return parameter;
	}
	
	/**
	 * 插入流程表数据
	 * 
	 * @param map
	 *            入参
	 * @return
	 * @see
	 * @since 1.0
	 */
	public Boolean insertWflRepoDealQuote(CfetsRepoDealQuotedPrice001Dto dto) {
		return restTemplate.postForObject(
				ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsRepoDealQuotedPrice001/insertWflRepoDealQuote",
				dto, Boolean.class);
	}
	
	/**
	 * 插入操作
	 */
	@Override
	public void doInsert(CfetsRepoDealQuotedPrice001Dto entity, OperationType type) {
		//历史报价页面撤销  不生成业务流程数据  同交易
		if ("save".equals(entity.getOperatorType())||StringUtil.equals(entity.getOperatorType(),"revoke")) {
			return;
		}
		insertWflRepoDealQuote(entity);
		doInsetOrUpdateForPtlPositionHold(entity);
	}

	/**
	 * 更新操作
	 */
	@Override
	public void doUpdate(CfetsRepoDealQuotedPrice001Dto entity) {
		Map<String, Object> map = new HashMap<>();
		if (!StringUtil.equals("revoke", entity.getOperatorType())
				&& !StringUtil.equals("delete", entity.getOperatorType())) {
			// 重新insert
			insertWflRepoDealQuote(entity);
			doInsetOrUpdateForPtlPositionHold(entity);
			ProcessMark newEntity;
			try {
				newEntity = WorkFlowUtil.getBusinessObjectFromRequest(CfetsRepoDealQuotedPrice001Dto.class, true);
				// 工作流ID
				newEntity.setWorkflowId(entity.getWorkflowId());
				newEntity.setStatus(entity.getStatus());
				newEntity.setBusinessData(JsonUtils.object2Json(entity));
				// 更新业务表数据
				// 操作留痕表
				WorkflowContext.getProcessMarkService().insertIntoProcessMark(newEntity);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void doDelete(String workflowId) {
	    //作废流程需要处理现金流水
		doDeleteWflDataForRepo(workflowId);
	}
	
    /**
     * 退回
     * @param entity
     */
    @Override
	public void doBack(CfetsRepoDealQuotedPrice001Dto entity){
		//回退释放头寸
    	doDeletePtlPositionHold(entity.getWorkflowId());
    	//退回操作重新占用该条报价单之前的
		if(StringUtil.isNotEmpty(entity.getQuoteId())){
			CfetsRepoDealQuotedPrice001Dto cfetsRepoDealQuotedPrice001Dto = new CfetsRepoDealQuotedPrice001Dto();
			cfetsRepoDealQuotedPrice001Dto.setQuoteId(entity.getQuoteId());
			doInsetOrUpdateForPtlPositionHold(cfetsRepoDealQuotedPrice001Dto);
		}
    }
	
	
	 /**
     * 删除持仓占用
     * @param workflowId
     */
    private void doDeletePtlPositionHold(String workflowId) {
        Map<String,Object> param = new HashMap<>();
        param.put("workflowId",workflowId);
        param.put("pageWflType","REPO");
        restTemplate.postForObject(ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsBondDealQuotedPrice/doDeletePtlPositionHold", param,
            Boolean.class);
    }

	/**
	 * 
	 * @param workflowId
	 */
	private void doDeleteWflDataForRepo(String workflowId) {
		Map<String, Object> param = new HashMap<>();
		param.put("workflowId", workflowId);
		restTemplate.postForObject(
				ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsRepoDealQuotedPrice001/doDeleteWflDataForRepo",
				param, Boolean.class);
	}

	/**
	 * 插入和修改时，指令持仓流水 、现金流水处理 add by amyli 20201230
	 * 
	 * @param dto
	 */
	public void doInsetOrUpdateForPtlPositionHold(CfetsRepoDealQuotedPrice001Dto dto) {
		restTemplate.postForObject(ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsRepoDealQuotedPrice001/doInsetOrUpdateForPtlPositionHold", dto, Integer.class);
	}


	/**
	 * 委托已报额度查询
	 * @author zhangnian
	 * @date 2021/1/13 0013
	 * @param  * @param map
	 * @return java.math.BigDecimal
	*/
	public BigDecimal selectUsedAmt(Map<String,Object> map){
		return restTemplate.postForObject(
				ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsRepoDealQuotedPrice001/selectUsedAmt",
				map, BigDecimal.class);
	}

	/**
	 * 查找组合对应的证券账户
	 * @author zhangnian
	 * @date 2021/1/13 0013
	 * @param  * @param null
	 * @return
	 */
	public String qryPortfolioSecAcctId(Map<String,Object> map){
		return restTemplate.postForObject(
				ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsQuotedCommon/qryPortfolioSecAcctId",
				map, String.class);
	}

	/**
	 *功能描述   check是否存在流程中
	 * @author zhangnian
	 * @date 2021/1/15 0015
	 * @param  * @param quoteId
	 * @return java.util.List<com.joyintech.framework.validation.ValidMessage>
	*/
	public List<ValidMessage>  isInWflProcess(String quoteId,String pageId){
		//check流程中
		List<ValidMessage> validList = new ArrayList<ValidMessage>();
		WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
		wflValidInputParameter.setPageId(StringUtil.isNotEmpty(pageId)?pageId:"CfetsRepoDealQuotedPrice002");
		//设置报价编号
		wflValidInputParameter.setBusinessDataPk1(quoteId);
		if(StringUtil.isNotEmpty(mstWorkflowCommonServie)&&mstWorkflowCommonServie.isInWflProcess(wflValidInputParameter)){
			validList.add(new ValidMessage(getMessage("BUSS_COM_E_0023")));
		}
		return validList;
	}
	
	/**
	 * 流程中查询报价对话历史
	 * @param dto
	 * @return
	 */
	public CfetsRepoDealQuotedPrice001Dto selectDialogueRecord(CfetsRepoDealQuotedPrice001Dto dto) {
		return restTemplate.postForObject(
				ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsRepoDealQuotedPrice001/selectDialogueRecord",
				dto, CfetsRepoDealQuotedPrice001Dto.class);
	}
	
	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.config("/com/joyintech/tams/invest/bankuplink/bond/conf/export_conf3.xml")
		.dataSource(DataSourceType.MASERT);
	}
    
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<CfetsRepoDealQuotedPrice001Dto> selectPageData(Map<String, Object> selectParam) {
		Page<CfetsRepoDealQuotedPrice001Dto> page = new Page<CfetsRepoDealQuotedPrice001Dto>();
		// 导出特殊处理
		List<CfetsRepoDealQuotedPrice001Dto> list = cfetsRepoDealQuotedPrice001Service.queryEntrustOrderList(selectParam);
		page.setRoot(list);
		return page;
	}

	public void 单独处理页面质押券列表(CfetsRepoDealQuotedPrice001Dto cfetsRepoDealQuotedPrice001Dto){
		BussRepoSecDto bussRepoSecDto = new BussRepoSecDto();
		if(null==cfetsRepoDealQuotedPrice001Dto.getBussRepoSec()){
			if(!StringUtil.isEmptyList(cfetsRepoDealQuotedPrice001Dto.getPledgeBondList())){
				List<PledgeBondInfoDto> pledgeBondList = new ArrayList<>();
				cfetsRepoDealQuotedPrice001Dto.getPledgeBondList().forEach(item -> {
					PledgeBondInfoDto pledgeBondInfoDto = new PledgeBondInfoDto();
					BeanUtil.copyPropertiesIgnoreNull(pledgeBondInfoDto,item);
					pledgeBondInfoDto.setPledgeRatio(item.getPledgeRate());
					pledgeBondInfoDto.setFirstNetPrice(item.getNetPrice());
					pledgeBondList.add(pledgeBondInfoDto);
				});
				bussRepoSecDto.setPledgeBondList(pledgeBondList);
				if(null != cfetsRepoDealQuotedPrice001Dto.getPortfolioId()){
					bussRepoSecDto.setPortfolioId(cfetsRepoDealQuotedPrice001Dto.getPortfolioId());
				}
				if(null != cfetsRepoDealQuotedPrice001Dto.getPs()){
					bussRepoSecDto.setPs(cfetsRepoDealQuotedPrice001Dto.getPs());
				}
			}
			cfetsRepoDealQuotedPrice001Dto.setBussRepoSec(bussRepoSecDto);
		}
	}
}
