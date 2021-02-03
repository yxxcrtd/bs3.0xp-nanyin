package com.joyintech.tams.invest.trade.transfer.assign.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.period.trdsecoverdueduration.TrdSecOverdueDurationZrDto;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import javax.validation.Valid;

/**
 * @ClassName TrdSecOverdueDurationDto
 * @Description: TODO
 * @Author wangpeng
 * @Date 2019/12/12 0004
 * @Version V1.0
 **/
@PageId("TrdSecOverdueDuration003")
public class TrdSecOverdueDuration003Dto extends TrdSecOverdueDurationZrDto implements ProcessEntity{
	/**
	 * 人员信息
	 */
	@Valid
	private TrdInvestPersonInfo002Dto trdInvestPersonInfo;
	/**
	 * 获取 人员信息
	 *
	 * @return trdInvestPersonInfo 人员信息
	 */
	public TrdInvestPersonInfo002Dto getTrdInvestPersonInfo() {
		return this.trdInvestPersonInfo;
	}
	/**
	 * 设置 人员信息
	 *
	 * @param trdInvestPersonInfo 人员信息
	 */
	public void setTrdInvestPersonInfo(TrdInvestPersonInfo002Dto trdInvestPersonInfo) {
		this.trdInvestPersonInfo = trdInvestPersonInfo;
	}
	@Override
	@PrimaryKey(PrimaryKey.Field.PK_1)
	public String getTotleTradeId() {
		return StringUtil.isNotEmpty(super.getTotleTradeId())?super.getTotleTradeId(): IDGenerator.generate();
	}
	@Override
	@PrimaryKey(PrimaryKey.Field.PK_2)
	@NotEmpty(fieldName = "TrdSecOverdueDuration003.portfolioId",groups={ValidateGroupEdit.class})
	public String getPortfolioId() {
		return super.getPortfolioId();
	}
	@Override	@PrimaryKey(PrimaryKey.Field.PK_3)
	@NotEmpty(fieldName = "TrdSecOverdueDuration003.finprodId",groups={ValidateGroupEdit.class})
	public String getFinprodId() {
		return super.getFinprodId();
	}
	@Override
	@PrimaryKey(PrimaryKey.Field.PK_4)
	public String getSecAcctId() {
		return super.getSecAcctId();
	}
	@PrimaryKey(PrimaryKey.Field.PK_5)
	public String getBjBranchStr() {
		if(super.getBjBranch()!=null){
			return super.getBjBranch().toString();
		}
		else{
			return null;
		}
	}
	@PrimaryKey(PrimaryKey.Field.PK_6)
	public String getLiBranchStr() {
		if(super.getLiBranch()!=null){
			return super.getLiBranch().toString();
		}
		else{
			return null;
		}
	}
	public String getStatusValidStr(){
		return SysDataUtil.getNameFromDataDict(DictCode.有效状态,super.getStatusValid());
	}
}
