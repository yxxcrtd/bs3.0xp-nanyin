package com.joyintech.tams.invest.common;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.component.business.common.dto
 * @Description: 组件共通返回结果
 * @date Date : 2018年09月27日 15:54
 */
public class CommonResult {

    /**
     * 成功
     */
    public static final int SUCCED = 1;

    /**
     * 失败
     */
    public static final int FAILD = 0;

    /**
     * 返回代码：0 失败，1 成功
     */
    private int returnCode;

    /**
     * 返回消息
     */
    private String returnMessage;
    public CommonResult(){
        super();
    }

    public CommonResult(int returnCode, String returnMessage) {
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
    }

    /**
     * 获取 returnCode
     *
     * @return returnCode
     */
    public int getReturnCode() {
        return returnCode;
    }

    /**
     * 设置 returnCode
     *
     * @param returnCode returnCode
     */
    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * 获取 returnMessage
     *
     * @return returnMessage
     */
    public String getReturnMessage() {
        return returnMessage;
    }

    /**
     * 设置 returnMessage
     *
     * @param returnMessage returnMessage
     */
    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }
}
