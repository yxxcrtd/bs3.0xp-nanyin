package com.joyintech.tams.report.system.chbfeedbackfilemanagement.dto;

import java.util.List;
import com.joyintech.tams.report.system.chbfeedbackfilemanagement.dto.ChbFeedbackFileMatchDetailDto;

/**
 * 反馈文件匹配明细信息
 */
public class ChbFeedbackFileManagement002Dto {

    /**
     * 反馈-报文编码
     */
    private String rtnRepMesNo;

    /**
     * 反馈文件明细数
     */
    private int feedbackFileNum;

    /**
     * 中债通过数
     */
    private int chbCorrectNum;

    /**
     * 中债退回数
     */
    private int chbErrorNum;

    /**
     * 登记表匹配明细数
     */
    private int regMatchNum;

    /**
     * 登记表异常明细数
     */
    private int regErrorNum;

    /**
     * 登记表发送中明细数
     */
    private int regSendingNum;

    /**
     * 登记表其他状态明细数
     */
    private int regOthersNum;

    /**
     * 匹配成功标志
     */
    private String matchedFlag;

    /**
     * 匹配结果概述
     */
    private String matchResultMsg;

    /**
     * 页面明细信息
     */
    private List<ChbFeedbackFileMatchDetailDto> detailList;

    public String getRtnRepMesNo() {
        return rtnRepMesNo;
    }

    public void setRtnRepMesNo(String rtnRepMesNo) {
        this.rtnRepMesNo = rtnRepMesNo;
    }

    public int getFeedbackFileNum() {
        return feedbackFileNum;
    }

    public void setFeedbackFileNum(int feedbackFileNum) {
        this.feedbackFileNum = feedbackFileNum;
    }

    public int getChbCorrectNum() {
        return chbCorrectNum;
    }

    public void setChbCorrectNum(int chbCorrectNum) {
        this.chbCorrectNum = chbCorrectNum;
    }

    public int getChbErrorNum() {
        return chbErrorNum;
    }

    public void setChbErrorNum(int chbErrorNum) {
        this.chbErrorNum = chbErrorNum;
    }

    public int getRegMatchNum() {
        return regMatchNum;
    }

    public void setRegMatchNum(int regMatchNum) {
        this.regMatchNum = regMatchNum;
    }

    public int getRegErrorNum() {
        return regErrorNum;
    }

    public void setRegErrorNum(int regErrorNum) {
        this.regErrorNum = regErrorNum;
    }

    public int getRegSendingNum() {
        return regSendingNum;
    }

    public void setRegSendingNum(int regSendingNum) {
        this.regSendingNum = regSendingNum;
    }

    public int getRegOthersNum() {
        return regOthersNum;
    }

    public void setRegOthersNum(int regOthersNum) {
        this.regOthersNum = regOthersNum;
    }

    public String getMatchedFlag() {
        return matchedFlag;
    }

    public void setMatchedFlag(String matchedFlag) {
        this.matchedFlag = matchedFlag;
    }

    public String getMatchResultMsg() {
        return matchResultMsg;
    }

    public void setMatchResultMsg(String matchResultMsg) {
        this.matchResultMsg = matchResultMsg;
    }

    public List<ChbFeedbackFileMatchDetailDto> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<ChbFeedbackFileMatchDetailDto> detailList) {
        this.detailList = detailList;
    }
}
