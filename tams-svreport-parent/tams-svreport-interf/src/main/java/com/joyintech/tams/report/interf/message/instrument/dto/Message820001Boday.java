package com.joyintech.tams.report.interf.message.instrument.dto;

import com.joyintech.tams.report.interf.message.socketclient.XMLHandler;
import org.jdom.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/14 0014.
 * 单笔短信转发（01）
 */
public class Message820001Boday {

    //渠道代码
    public static final Element CHNLCD = new Element("ChnlCd");
    //签约机构Id
    public static final Element SGNBRCHID = new Element("SgnBrchId");
    //实时标志
    public static final Element RLTMFLG = new Element("RlTmFlg");
    //预约时间戳
    public static final Element BOOKTMSTMP = new Element("BookTmStmp");
    //客户号
    public static final Element CLIENTNO = new Element("ClientNo");
    //账号
    public static final Element ACCTNO = new Element("AcctNo");
    //手机号码
    public static final Element MOBILENO = new Element("MobileNo");
    //消息内容
    public static final Element MSGCNTNT = new Element("MsgCntnt");

    public static List<Element> body820001(){
        List<Element> body = new ArrayList<>();
        body.add(CHNLCD);
        body.add(SGNBRCHID);
        body.add(RLTMFLG);
        body.add(BOOKTMSTMP);
        body.add(CLIENTNO);
        body.add(ACCTNO);
        body.add(MOBILENO);
        body.add(MSGCNTNT);
        return body;
    }
}
