package com.joyintech.tams.disclosure.interf.interf.message.instrument.dto;

import org.jdom.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/17 0017.
 * 上行短信查询（01）
 */
public class Message12003005001Boday {
    //通道号码
    public static final Element SmsNo = new Element("SmsNo");
    //页码
    public static final Element Page = new Element("Page");
    //每页记录数
    public static final Element PerPgNum = new Element("PerPgNum");
    //手机号码
    public static final Element MobileNo = new Element("MobileNo");
    //查询类型
    public static final Element QryType = new Element("QryType");
    //查询起始日期
    public static final Element StartDate = new Element("StartDate");
    //查询结束日期
    public static final Element EndDate = new Element("EndDate");

    public static List<Element> Body12003005001() {
        List<Element> Body = new ArrayList<>();
        Body.add(SmsNo);
        Body.add(Page);
        Body.add(PerPgNum);
        Body.add(MobileNo);
        Body.add(QryType);
        Body.add(StartDate);
        Body.add(EndDate);
        return Body;
    }
}
