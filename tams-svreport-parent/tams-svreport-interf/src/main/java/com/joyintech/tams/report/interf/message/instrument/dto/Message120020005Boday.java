package com.joyintech.tams.report.interf.message.instrument.dto;

import org.jdom.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/17 0017.
 * 批量相同内容短信转发（02）
 */
public class Message120020005Boday {
    //用户号
    public static final Element USERNO = new Element("UserNo");
    //交易密码
    public static final Element TRANPSWD = new Element("TranPswd");
    //目标号码
    public static final Element TRGTMBLNOS = new Element("TrgtMblNos");
    //短信信息
    public static final Element SMSMSG = new Element("SmsMsg");
    //手机号码个数
    public static final Element MBLNONUM = new Element("MblNoNum");
    //子端口号码
    public static final Element SUBPORTNO = new Element("SubPortNo");
    //业务类型
    public static final Element BSNSTYPE = new Element("BsnsType");
    //参数1(最大长度64字节(必填，默认输入：0))
    public static final Element PARAMETER1 = new Element("Parameter1");
    //参数2(最大长度64字节(必填，默认输入：0))
    public static final Element PARAMETER2 = new Element("Parameter2");
    //参数3(最大长度64字节  (必填，默认输入研发部人员给予的4位数字渠道号码，如（66XX）))
    public static final Element PARAMETER3 = new Element("Parameter3");
    //参数3(最大长度64字节 (可选)（不填）)
    public static final Element PARAMETER4 = new Element("Parameter4");
    //消息Id
    public static final Element MSGID = new Element("MsgId");
    //模块Id
    public static final Element MODULEID = new Element("ModuleId");
    //预约时间戳
    public static final Element BOOKTMSTMP = new Element("BookTmStmp");
    //有效时间戳
    public static final Element VLDTMSTMP = new Element("VldTmStmp");
    //状态报告标志
    public static final Element STRPTFLG = new Element("StRptFlg");

    public static List<Element> body120020005() {
        List<Element> body = new ArrayList<>();
        body.add(USERNO);
        body.add(TRANPSWD);
        body.add(TRGTMBLNOS);
        body.add(SMSMSG);
        body.add(MBLNONUM);
        body.add(SUBPORTNO);
        body.add(BSNSTYPE);
        body.add(PARAMETER1);
        body.add(PARAMETER2);
        body.add(PARAMETER3);
        body.add(PARAMETER4);
        body.add(MSGID);
        body.add(MODULEID);
        body.add(BOOKTMSTMP);
        body.add(VLDTMSTMP);
        body.add(STRPTFLG);
        return body;
    }
}
