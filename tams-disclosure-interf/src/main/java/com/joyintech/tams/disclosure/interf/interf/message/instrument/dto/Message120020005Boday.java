package com.joyintech.tams.disclosure.interf.interf.message.instrument.dto;

import org.jdom.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/17 0017.
 * 批量相同内容短信转发（02）
 */
public class Message120020005Boday {
    //用户号
    public static final Element UserNo = new Element("UserNo");
    //交易密码
    public static final Element TranPswd = new Element("TranPswd");
    //目标号码
    public static final Element TrgtMblNos = new Element("TrgtMblNos");
    //短信信息
    public static final Element SmsMsg = new Element("SmsMsg");
    //手机号码个数
    public static final Element MblNoNum = new Element("MblNoNum");
    //子端口号码
    public static final Element SubPortNo = new Element("SubPortNo");
    //业务类型
    public static final Element BsnsType = new Element("BsnsType");
    //参数1(最大长度64字节(必填，默认输入：0))
    public static final Element Parameter1 = new Element("Parameter1");
    //参数2(最大长度64字节(必填，默认输入：0))
    public static final Element Parameter2 = new Element("Parameter2");
    //参数3(最大长度64字节  (必填，默认输入研发部人员给予的4位数字渠道号码，如（66XX）))
    public static final Element Parameter3 = new Element("Parameter3");
    //参数3(最大长度64字节 (可选)（不填）)
    public static final Element Parameter4 = new Element("Parameter4");
    //消息Id
    public static final Element MsgId = new Element("MsgId");
    //模块Id
    public static final Element ModuleId = new Element("ModuleId");
    //预约时间戳
    public static final Element BookTmStmp = new Element("BookTmStmp");
    //有效时间戳
    public static final Element VldTmStmp = new Element("VldTmStmp");
    //状态报告标志
    public static final Element StRptFlg = new Element("StRptFlg");

    public static List<Element> Body120020005() {
        List<Element> Body = new ArrayList<>();
        Body.add(UserNo);
        Body.add(TranPswd);
        Body.add(TrgtMblNos);
        Body.add(SmsMsg);
        Body.add(MblNoNum);
        Body.add(SubPortNo);
        Body.add(BsnsType);
        Body.add(Parameter1);
        Body.add(Parameter2);
        Body.add(Parameter3);
        Body.add(Parameter4);
        Body.add(MsgId);
        Body.add(ModuleId);
        Body.add(BookTmStmp);
        Body.add(VldTmStmp);
        Body.add(StRptFlg);
        return Body;
    }
}
