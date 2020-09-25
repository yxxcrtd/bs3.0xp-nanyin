package com.joyintech.tams.disclosure.interf.interf.message.instrument.dto;

import org.jdom.Element;

import com.joyintech.tams.disclosure.interf.interf.message.socketclient.XMLHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/14 0014.
 * 单笔短信转发（01）
 */
public class Message820001Boday {

    //渠道代码
    public static final Element ChnlCd = new Element("ChnlCd");
    //签约机构Id
    public static final Element SgnBrchId = new Element("SgnBrchId");
    //实时标志
    public static final Element RlTmFlg = new Element("RlTmFlg");
    //预约时间戳
    public static final Element BookTmStmp = new Element("BookTmStmp");
    //客户号
    public static final Element ClientNo = new Element("ClientNo");
    //账号
    public static final Element AcctNo = new Element("AcctNo");
    //手机号码
    public static final Element MobileNo = new Element("MobileNo");
    //消息内容
    public static final Element MsgCntnt = new Element("MsgCntnt");

    public static List<Element> Body820001(){
        List<Element> Body = new ArrayList<>();
        Body.add(ChnlCd);
        Body.add(SgnBrchId);
        Body.add(RlTmFlg);
        Body.add(BookTmStmp);
        Body.add(ClientNo);
        Body.add(AcctNo);
        Body.add(MobileNo);
        Body.add(MsgCntnt);
        return Body;
    }
}
