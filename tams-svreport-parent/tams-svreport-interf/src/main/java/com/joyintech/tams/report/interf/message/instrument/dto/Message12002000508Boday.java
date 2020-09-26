package com.joyintech.tams.report.interf.message.instrument.dto;

import org.jdom.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/17 0017.
 * 行内单笔短信发送（08）
 */
public class Message12002000508Boday {
    //报文长度
    public static final Element PACKLEN = new Element("packlen");
    //交易码
    public static final Element TRADECODE = new Element("tradecode");
    //渠道代码
    public static final Element CHNLCD = new Element("ChnlCd");
    //签约机构Id
    public static final Element SGNBRCHILD = new Element("SgnBrchId");
    //实时标志
    public static final Element RLTMFLG = new Element("RlTmFlg");
    //预约时间戳
    public static final Element BOOKTMSTMP = new Element("BookTmStmp");
    //客户号
    public static final Element CLIENTNO = new Element("ClientNo");
    //账号
    public static final Element ACCTNO = new Element("AcctNo");
    //手机号码
    public static final Element MOBLIENO = new Element("MobileNo");
    // 消息内容
    public static final Element MSGCNTNT = new Element("MsgCntnt");

    public static List<Element> body12002000508() {
        List<Element> body = new ArrayList<>();
        body.add(PACKLEN);
        body.add(TRADECODE);
        body.add(CHNLCD);
        body.add(SGNBRCHILD);
        body.add(RLTMFLG);
        body.add(BOOKTMSTMP);
        body.add(CLIENTNO);
        body.add(ACCTNO);
        body.add(MOBLIENO);
        body.add(MSGCNTNT);
        return body;
    }
}
