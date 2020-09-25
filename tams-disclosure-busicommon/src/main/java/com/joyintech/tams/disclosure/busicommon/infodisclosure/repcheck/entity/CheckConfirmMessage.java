/*
 * 文件名：CheckConfirmMessage.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wanghb
 * 修改时间：2019年3月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.disclosure.busicommon.infodisclosure.repcheck.entity;


/**
 * 校验结果数据
 * @author wanghb
 * @version 1.0 2019年3月21日
 * @see CheckConfirmMessage
 * @since 1.0
 */
public class CheckConfirmMessage {
    /**
     * 是否通过
     */
    private boolean flag;
    /**
     * 校验信息
     */
    private String msg;
    
    /**
     * 获取 flag
     * @return flag.
     */
    public boolean isFlag() {
        return flag;
    }
    
    /**
     * 设置 flag
     * @param flag flag
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    
    /**
     * 获取 msg
     * @return msg.
     */
    public String getMsg() {
        return msg;
    }
    
    /**
     * 设置 msg
     * @param msg msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
}
