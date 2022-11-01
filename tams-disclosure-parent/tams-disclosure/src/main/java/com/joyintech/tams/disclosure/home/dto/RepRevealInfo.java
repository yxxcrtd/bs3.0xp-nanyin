package com.joyintech.tams.disclosure.home.dto;

public class RepRevealInfo {

    /**
     * 预计披露
     */
    private String expectRevealNum;

    /**
     * 已披露
     */
    private String revealNum;

    /**
     * 生成失败
     */
    private String genFailNum;

    /**
     * 待确认
     */
    private String toBeConfirmedNum;

    /**
     * 待复核
     */
    private String toBeReviewedNum;

    /**
     * 待托管复核
     */
    private String toBeTgReviewedNum;

    private int expectRevealCounts;  //预计披露
    private int revealCounts;  //已披露
    private int toBeRevealedCounts; //待披露
    private int genFailCounts;  //生成成功
    private int toBeConfirmedCounts;  //待确认
    private int toBeReviewedCounts;  //待复核
    private int toBeTgReviewedCounts;  //待托管复核
    
    /**
     * 待披露
     */
    private String toBeRevealedNum;

    public String getExpectRevealNum() {
        return expectRevealNum;
    }

    public void setExpectRevealNum(String expectRevealNum) {
        this.expectRevealNum = expectRevealNum;
    }

    public String getRevealNum() {
        return revealNum;
    }

    public void setRevealNum(String revealNum) {
        this.revealNum = revealNum;
    }

    public String getGenFailNum() {
        return genFailNum;
    }

    public void setGenFailNum(String genFailNum) {
        this.genFailNum = genFailNum;
    }

    public String getToBeConfirmedNum() {
        return toBeConfirmedNum;
    }

    public void setToBeConfirmedNum(String toBeConfirmedNum) {
        this.toBeConfirmedNum = toBeConfirmedNum;
    }

    public String getToBeReviewedNum() {
        return toBeReviewedNum;
    }

    public void setToBeReviewedNum(String toBeReviewedNum) {
        this.toBeReviewedNum = toBeReviewedNum;
    }

    public String getToBeTgReviewedNum() {
        return toBeTgReviewedNum;
    }

    public void setToBeTgReviewedNum(String toBeTgReviewedNum) {
        this.toBeTgReviewedNum = toBeTgReviewedNum;
    }

    public String getToBeRevealedNum() {
        return toBeRevealedNum;
    }

    public void setToBeRevealedNum(String toBeRevealedNum) {
        this.toBeRevealedNum = toBeRevealedNum;
    }

	public int getExpectRevealCounts() {
		return expectRevealCounts;
	}

	public void setExpectRevealCounts(int expectRevealCounts) {
		this.expectRevealCounts = expectRevealCounts;
	}

	public int getRevealCounts() {
		return revealCounts;
	}

	public void setRevealCounts(int revealCounts) {
		this.revealCounts = revealCounts;
	}

	public int getToBeRevealedCounts() {
		return toBeRevealedCounts;
	}

	public void setToBeRevealedCounts(int toBeRevealedCounts) {
		this.toBeRevealedCounts = toBeRevealedCounts;
	}

	public int getGenFailCounts() {
		return genFailCounts;
	}

	public void setGenFailCounts(int genFailCounts) {
		this.genFailCounts = genFailCounts;
	}

	public int getToBeConfirmedCounts() {
		return toBeConfirmedCounts;
	}

	public void setToBeConfirmedCounts(int toBeConfirmedCounts) {
		this.toBeConfirmedCounts = toBeConfirmedCounts;
	}

	public int getToBeReviewedCounts() {
		return toBeReviewedCounts;
	}

	public void setToBeReviewedCounts(int toBeReviewedCounts) {
		this.toBeReviewedCounts = toBeReviewedCounts;
	}

	public int getToBeTgReviewedCounts() {
		return toBeTgReviewedCounts;
	}

	public void setToBeTgReviewedCounts(int toBeTgReviewedCounts) {
		this.toBeTgReviewedCounts = toBeTgReviewedCounts;
	}
    
}
