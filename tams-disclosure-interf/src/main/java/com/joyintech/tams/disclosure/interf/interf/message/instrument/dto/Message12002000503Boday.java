package com.joyintech.tams.disclosure.interf.interf.message.instrument.dto;

import org.jdom.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/17 0017.
 * 批量不同内容短信转发（03）
 */
public class Message12002000503Boday {
    //用户号
    public static final Element UserNo = new Element("UserNo");
    //交易密码
    public static final Element TranPswd = new Element("TranPswd");
    //短信包
    public static final Element SmsPkg = new Element("SmsPkg");

    public static List<Element> Body12002000503() {
        List<Element> Body = new ArrayList<>();
        Body.add(UserNo);
        Body.add(TranPswd);
        Body.add(SmsPkg);
        return Body;
    }
}
