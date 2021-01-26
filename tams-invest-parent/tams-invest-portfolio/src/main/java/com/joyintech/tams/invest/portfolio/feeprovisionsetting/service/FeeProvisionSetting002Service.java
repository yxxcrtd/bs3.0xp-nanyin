package com.joyintech.tams.invest.portfolio.feeprovisionsetting.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.TrdAccountmfConfigdetailDto;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.invest.portfolio.feeprovisionsetting.dto.FeeProvisionSetting001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 费用预提设置新建 service层
 * @author
 *
 */
@Service
public class FeeProvisionSetting002Service extends BaseService<FeeProvisionSetting001Dto> implements BaseProcessService<FeeProvisionSetting001Dto>{

	@Autowired
	private IPlatformDate iPlatformDate;

	@Override
	public void doComplete(FeeProvisionSetting001Dto entity, OperationType type) {
		if (type == OperationType.NEW) {
			entity.setDealStatus(DictCodeListExt1.有效状态_有效.getValue());
			this.updateData(entity);
		}else if(type == OperationType.MODIFY){
			entity.setDealStatus(DictCodeListExt1.有效状态_有效.getValue());
			restTemplate.postForObject(
					ServiceApplication.PORTFOLIO_SERVICE_NAME + "/FeeProvisionSetting/modify", entity,
					Integer.class);
		}else if(type == OperationType.REVOKE){
			entity.setDealStatus(DictCodeListExt1.有效状态_撤销.getValue());
			restTemplate.postForObject(
					ServiceApplication.PORTFOLIO_SERVICE_NAME + "/FeeProvisionSetting/revoke", entity,
					Integer.class);
		}
	}

	/**
	 *
	 * 编辑查询页面客户评级表查询分页
	 * <功能详细描述>
	 * @param pageParam pageParam
	 * @return Page<PaRskCustomerRating>
	 * @throws Exception Exception
	 * @see
	 * @since 1.0
	 */
	public Page<TrdAccountmfConfigdetailDto> selectDetail(Map<String, Object> pageParam)
			throws Exception {
		String workflowId = (String)pageParam.get("workflowId");
		if (StringUtil.isNotEmpty(workflowId)) {
			FeeProvisionSetting001Dto feeProvisionSetting001Dto = this.getBussinessEntityByWorkflowId(
					workflowId);
			Page<TrdAccountmfConfigdetailDto> page = new Page<>();
			page.setRoot(feeProvisionSetting001Dto.getTrdAccountmfConfigdetail());
			page.setRecords(feeProvisionSetting001Dto.getTrdAccountmfConfigdetail().size());
			return page;
		}else if(StringUtil.isNotEmpty((String)pageParam.get("seqNo"))){
			return restTemplate.postForPage(
					ServiceApplication.PORTFOLIO_SERVICE_NAME + "/FeeProvisionSetting/selectDetail",
					pageParam, TrdAccountmfConfigdetailDto.class);
		}
		return new Page<>();
	}

	/**
	 * 获取账号信息
	 * @param param 联想文字
	 * @see
	 * @since 1.0
	 * @return 账号实体类
	 */
	public List<SelectOptionDto> getBooksetId(Map<String,Object> param){
		return restTemplate.postForList(
						ServiceApplication.PORTFOLIO_SERVICE_NAME + "/FeeProvisionSetting/getBooksetId",param, SelectOptionDto.class);
	}
	/**
	 * 获取账号信息
	 * @param param 联想文字
	 * @see
	 * @since 1.0
	 * @return 账号实体类
	 */
	public String selectOrgInfo(Map<String,Object> param){
		return restTemplate.postForObject(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/FeeProvisionSetting/selectOrgInfo",param, String.class);
	}


	public FeeProvisionSetting001Dto selectEntityByID(String seqNo){
		return restTemplate.postForObject(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/FeeProvisionSetting/selectEntityByID", seqNo,FeeProvisionSetting001Dto.class);
	}

	/**
	 * 保存check
	 * @param dto
	 * @return
	 */
	public AjaxResult check(FeeProvisionSetting001Dto dto){
		// 验证消息
		Set<String> message = new HashSet<String>();
		AjaxResult ar = new AjaxResult();
		if(StringUtil.isEmpty(dto.getSecAcctId())){
			message.add("证券账户不能为空!");
		}
		if(StringUtil.isEmpty(dto.getPayDate())){
			message.add("几号不能为空!");
		}
		if(dto.getPositionStartDate() == null){
			message.add("计提开始日不能为空!");
		}
		if(dto.getPositionEndDate() == null){
			message.add("计提结束日不能为空!");
		}
		if(dto.getFeeAmtTotal() == null){
			message.add("每季费用金额不能为空!");
		}
		if(StringUtil.isEmpty(dto.getRegistOrg())){
			message.add("登记托管机构不能为空!");
		}
		if(StringUtil.isEmptyList(dto.getTrdAccountmfConfigdetail())){
			message.add("产品费用明细不能为空!");
		}else{
			for(int i = 0; i < dto.getTrdAccountmfConfigdetail().size(); i++){
				TrdAccountmfConfigdetailDto detail = dto.getTrdAccountmfConfigdetail().get(i);
				if(StringUtil.isEmpty(detail.getBooksetId())){
					message.add("产品费用明细,第"+(i+1)+"行，账套代码不能为空!");
				}
				if(detail.getFeeAmtDetail() == null){
					message.add("产品费用明细,第"+(i+1)+"行，每季费用金额不能为空!");
				}
				if(StringUtil.isNotEmpty(detail.getFeeAmtDetail()) && detail.getFeeAmtDetail().compareTo(BigDecimal.ZERO) == 0){
					message.add("产品费用明细,第"+(i+1)+"行，每季费用金额不能为0!");
				}
			}
		}
		if(CollectionUtils.isNotEmpty(message)){
			List<ValidMessage> errorList = new ArrayList<>();
			message.forEach(action->{
				errorList.add(new ValidMessage(action));
			});
			ar.setErrorList(errorList);
			return ar;
		}
		Date platDate = iPlatformDate.getSysDate(); // 平台日期
		Date maxVdate = getMaxVdate(dto.getTrdAccountmfConfigdetail());// 获取最大产品起息日
//        a.计提开始日>=平台日 AND 计提开始日>=最大的产品起息日
		if(!(dto.getPositionStartDate().getTime() >= platDate.getTime() && dto.getPositionStartDate().getTime()>=maxVdate.getTime())) {
			message.add("计提开始日必须满足，计提开始日>=平台日 AND 计提开始日>=最大的产品起息日!");
		}
//        b.计提结束日>计提开始日 AND 计提结束日<=最小的产品到期日
		Date minMdate = getMinMdate(dto.getTrdAccountmfConfigdetail());// 获取最小的产品到期日
		if(!(dto.getPositionEndDate().getTime() > dto.getPositionStartDate().getTime() && dto.getPositionEndDate().getTime() <= minMdate.getTime())){
			message.add("计提结束日必须满足，计提结束日>计提开始日 AND 计提结束日<=最小的产品到期日!");
		}
//        c.同一个产品只能有一条明细数据
		if(!isIdentical(dto.getTrdAccountmfConfigdetail())){
			message.add("同一个产品只能有一条明细数据!");
		}
//        d.明细数据每季度费用汇总金额 = 设置的每季度费用
		if(!isEqualFeeAmt(dto)){
			message.add("明细数据每季度费用汇总金额 = 设置的每季度费用!");
		}
		//费用不为4500，不允许提交
		if(dto.getFeeAmtTotal().compareTo(new BigDecimal(4500)) != 0) {
			message.add("每季度费用金额不为4500，不允许提交!");
		}
//        e.判断区间是否重复

		if(isRepetition(dto)){
			Date date = getRepetitionDate(dto);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			message.add("该证券账户计提开始日小于等于实际到期日"+format.format(date)+"，每季费用金额"+dto.getFeeAmtTotal()+"元，可能存在重复付费");
		}

		if(CollectionUtils.isNotEmpty(message)){
			List<ValidMessage> errorList = new ArrayList<>();
			message.forEach(action->{
				errorList.add(new ValidMessage(action));
			});
			ar.setErrorList(errorList);
		}
		return ar;
	}

	private Date getMaxVdate(List<TrdAccountmfConfigdetailDto> list){
		return restTemplate.postForObject(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/FeeProvisionSetting/getMaxVdate", list ,Date.class);
	}

	private Date getMinMdate(List<TrdAccountmfConfigdetailDto> list){
		return restTemplate.postForObject(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/FeeProvisionSetting/getMinMdate", list ,Date.class);
	}

	private boolean isIdentical(List<TrdAccountmfConfigdetailDto> list){
		Set<String> set = new HashSet<>();
		for(TrdAccountmfConfigdetailDto dto:list){
			set.add(dto.getBooksetId());
		}
		return set.size() == list.size();
	}

	private boolean isEqualFeeAmt(FeeProvisionSetting001Dto dto){
		BigDecimal tmpTotal =  new BigDecimal(0);
		List<TrdAccountmfConfigdetailDto> list = dto.getTrdAccountmfConfigdetail();
		for(TrdAccountmfConfigdetailDto detail : list){
			tmpTotal = tmpTotal.add(detail.getFeeAmtDetail());
		}
		return tmpTotal.compareTo(dto.getFeeAmtTotal()) == 0;
	}

	private boolean isRepetition(FeeProvisionSetting001Dto dto){
		int count = restTemplate.postForObject(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/FeeProvisionSetting/selectCount", dto,
				Integer.class);
		return count > 0;
	}

	//获取重复的最大实际到期日
	private Date getRepetitionDate(FeeProvisionSetting001Dto dto){
		Date date = restTemplate.postForObject(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/FeeProvisionSetting/getRepetitionDate", dto,
				Date.class);
		return date;
	}

	public void updateData(FeeProvisionSetting001Dto entity){
		restTemplate.postForObject(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/FeeProvisionSetting/revoke", entity,
				Integer.class);
	}
	public void insertData(FeeProvisionSetting001Dto entity){
		restTemplate.postForObject(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/FeeProvisionSetting/insert", entity,
				Integer.class);
	}
	
	/**
     * 作废操作,清空业务表 {@inheritDoc}
     */
    @Override
    public void doDelete(String workflowId) {
    	// 根据工作流与留痕处理提供的共通方法获取业务实体
    	FeeProvisionSetting001Dto entity = this.getBussinessEntityByWorkflowId(workflowId);
    	restTemplate.postForObject(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/FeeProvisionSetting/deleteNewData", entity,
				Integer.class);
    }
    
    /**
     * 流程中修改{@inheritDoc}
     */
    @Override
    public void doUpdate(FeeProvisionSetting001Dto entity) {
        if (!StringUtil.equals("revoke", entity.getOperateType()) && !StringUtil.equals("delete", entity.getOperateType())) {
        	FeeProvisionSetting001Dto qryDto = selectEntityByID(entity.getSeqNo());
    		if(StringUtil.isNotEmpty(qryDto)  && StringUtil.equals(qryDto.getDealStatus(), "04")){//流程中
    			entity.setDealStatus(DictCodeListExt1.有效状态_流程中.getValue());
    			restTemplate.postForObject(
    					ServiceApplication.PORTFOLIO_SERVICE_NAME + "/FeeProvisionSetting/modify", entity,
    					Integer.class);
    		}
        }
    }

}
