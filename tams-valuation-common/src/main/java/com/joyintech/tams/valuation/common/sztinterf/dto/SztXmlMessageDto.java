package com.joyintech.tams.valuation.common.sztinterf.dto;/**
 * @author yanya
 * @create 2019-12-12 11:29
 */

import com.joyintech.framework.base.BaseEntity;

/**
 * @ClassName SztXmlMessageDto
 * @Description: 深圳通报文实体类dto
 * @Author Administrator
 * @Date 2019/12/12 0012 
 * @Version V1.0
 **/
public class SztXmlMessageDto extends BaseEntity {

    private static final long serialVersionUID = -2062240093577539524L;

    //报文信息
    private String resp;
    //发送条数
    private int sendNum;
    //业务流水号
    private String serialNo;

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public int getSendNum() {
        return sendNum;
    }

    public void setSendNum(int sendNum) {
        this.sendNum = sendNum;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }
}
