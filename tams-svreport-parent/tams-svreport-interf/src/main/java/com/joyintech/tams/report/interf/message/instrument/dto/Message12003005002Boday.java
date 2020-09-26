package com.joyintech.tams.report.interf.message.instrument.dto;

import org.jdom.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/17 0017.
 * 下行短信查询（02）
 */
public class Message12003005002Boday {
    //通道号码
    public static final Element SMSNO = new Element("SmsNo");
    //页码
    public static final Element PAGE = new Element("Page");
    //每页记录数
    public static final Element PERPGNUM = new Element("PerPgNum");
    //手机号码
    public static final Element MOBILENO = new Element("MobileNo");
    //查询类型
    public static final Element QRYTYPE = new Element("QryType");
    //查询起始日期
    public static final Element STARTDATE = new Element("StartDate");
    //查询结束日期
    public static final Element ENDDATE = new Element("EndDate");

    public static List<Element> body12003005002() {
        List<Element> body = new ArrayList<>();
        body.add(SMSNO);
        body.add(PAGE);
        body.add(PERPGNUM);
        body.add(MOBILENO);
        body.add(QRYTYPE);
        body.add(STARTDATE);
        body.add(ENDDATE);
        return body;
    }
}
