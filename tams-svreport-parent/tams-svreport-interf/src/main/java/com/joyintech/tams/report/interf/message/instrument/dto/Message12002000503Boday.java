package com.joyintech.tams.report.interf.message.instrument.dto;

import org.jdom.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/17 0017.
 * 批量不同内容短信转发（03）
 */
public class Message12002000503Boday {
    //用户号
    public static final Element USERNO = new Element("UserNo");
    //交易密码
    public static final Element TRANPSWD = new Element("TranPswd");
    //短信包
    public static final Element SMSPKG = new Element("SmsPkg");

    public static List<Element> body12002000503() {
        List<Element> body = new ArrayList<>();
        body.add(USERNO);
        body.add(TRANPSWD);
        body.add(SMSPKG);
        return body;
    }
}
