package com.joyintech.background.component.uplink.interbank.database.entity;

import java.util.Date;

public class IbuQuoteErrlog extends IbuQuoteErrlogKey {
    /**
     * 错误码
     */
    private String errCode;

    @Override
	public String getQuoteId() {
		// TODO Auto-generated method stub
		return super.getQuoteId();
	}

	@Override
	public void setQuoteId(String quoteId) {
		// TODO Auto-generated method stub
		super.setQuoteId(quoteId);
	}

	@Override
	public Long getVersion() {
		// TODO Auto-generated method stub
		return super.getVersion();
	}

	@Override
	public void setVersion(Long version) {
		// TODO Auto-generated method stub
		super.setVersion(version);
	}

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return super.getStatus();
	}

	@Override
	public void setStatus(String status) {
		// TODO Auto-generated method stub
		super.setStatus(status);
	}

	@Override
	public String getCreateUser() {
		// TODO Auto-generated method stub
		return super.getCreateUser();
	}

	@Override
	public void setCreateUser(String createUser) {
		// TODO Auto-generated method stub
		super.setCreateUser(createUser);
	}

	@Override
	public String getCreateDept() {
		// TODO Auto-generated method stub
		return super.getCreateDept();
	}

	@Override
	public void setCreateDept(String createDept) {
		// TODO Auto-generated method stub
		super.setCreateDept(createDept);
	}

	@Override
	public Date getCreateTime() {
		// TODO Auto-generated method stub
		return super.getCreateTime();
	}

	@Override
	public void setCreateTime(Date createTime) {
		// TODO Auto-generated method stub
		super.setCreateTime(createTime);
	}

	@Override
	public String getUpdateUser() {
		// TODO Auto-generated method stub
		return super.getUpdateUser();
	}

	@Override
	public void setUpdateUser(String updateUser) {
		// TODO Auto-generated method stub
		super.setUpdateUser(updateUser);
	}

	@Override
	public Date getUpdateTime() {
		// TODO Auto-generated method stub
		return super.getUpdateTime();
	}

	@Override
	public void setUpdateTime(Date updateTime) {
		// TODO Auto-generated method stub
		super.setUpdateTime(updateTime);
	}

	@Override
	public String getWorkflowId() {
		// TODO Auto-generated method stub
		return super.getWorkflowId();
	}

	@Override
	public void setWorkflowId(String workflowId) {
		// TODO Auto-generated method stub
		super.setWorkflowId(workflowId);
	}

	@Override
	public String getUpdateFlg() {
		// TODO Auto-generated method stub
		return super.getUpdateFlg();
	}

	@Override
	public void setUpdateFlg(String updateFlg) {
		// TODO Auto-generated method stub
		super.setUpdateFlg(updateFlg);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	/**
     * 错误信息
     */
    private String errMsg;

    /**
     * 取得 错误码
     * @return 错误码
     */
    public String getErrCode() {
        return errCode;
    }

    /**
     * 设置 错误码
     * @param errCode 错误码
     */
    public void setErrCode(String errCode) {
        this.errCode = errCode == null ? null : errCode.trim();
    }

    /**
     * 取得 错误信息
     * @return 错误信息
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * 设置 错误信息
     * @param errMsg 错误信息
     */
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg == null ? null : errMsg.trim();
    }
}