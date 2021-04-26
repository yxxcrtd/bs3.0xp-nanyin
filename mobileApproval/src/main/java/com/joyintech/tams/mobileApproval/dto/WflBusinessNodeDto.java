package com.joyintech.tams.mobileApproval.dto;

public class WflBusinessNodeDto {

    //用户id
    private String userId;
    
  //用户名
    private String userName;

    //审批意见
    private String opinions="";
    
    //时间
    private String createDate;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOpinions() {
		return opinions;
	}

	public void setOpinions(String opinions) {
		if(opinions==null) {
			this.opinions="";
		}else {
			this.opinions = opinions;
		}
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}
