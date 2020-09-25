package com.joyintech.tams.disclosure.interf.interf.message.instrument.service;
/*
 * 文件名：MessageService.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：恒丰银行资管系统 修改人：renjian 修改时间：2017年2月27日 修改内容：新建 系统名称：恒丰银行资管系统
 
package com.joyintech.tams.disclosure.interf.common.interf.message.instrument.service;


import java.io.IOException;
import java.util.Map;

import org.jdom.Element;
import org.jdom.JDOMException;

import com.joyintech.tams.disclosure.interf.common.interf.message.instrument.dto.Message12002000503Boday;
import com.joyintech.tams.disclosure.interf.common.interf.message.instrument.dto.Message12002000508Boday;
import com.joyintech.tams.disclosure.interf.common.interf.message.instrument.dto.Message120020005Boday;
import com.joyintech.tams.disclosure.interf.common.interf.message.instrument.dto.Message12003005001Boday;
import com.joyintech.tams.disclosure.interf.common.interf.message.instrument.dto.Message12003005002Boday;
import com.joyintech.tams.disclosure.interf.common.interf.message.instrument.dto.Message820001Boday;
import com.joyintech.tams.disclosure.interf.common.interf.message.instrument.dto.Message820001Entity;
import com.joyintech.tams.disclosure.interf.common.interf.message.socketclient.SocketClient;
import com.joyintech.tams.disclosure.interf.common.interf.message.socketclient.XMLHandler;

*//**
 * 单笔短信转发（01）
 * Created by Administrator on 2017/7/14 0014.
 *//*
public class Service extends XMLHandler {

    *//**
     * 820001报文BODY添加值
     *
     * @return
     *//*

    public String Message820001(Message820001Entity message820001Entity) throws JDOMException, IOException {
        this.service();
        Map<Element, String> Sys_Head_Body = XMLHandler.Sys_Head_Body();
        Sys_Head_Body.put(SvcId,"MMP");
        Sys_Head_Body.put(SvcScnId,"NNP");
        Map<Element, String> App_Head_Body =XMLHandler.App_Head_Body();
        //创建SYS_HEAD以及子节点
        for(Map.Entry<Element, String> vo : Sys_Head_Body.entrySet()){
            SYS_HEAD.addContent(vo.getKey());
            vo.getKey().addContent(vo.getValue());
        }
        //创建APP_HEAD节点
        for(Map.Entry<Element, String> vo : App_Head_Body.entrySet()){
            APP_HEAD.addContent(vo.getKey());
            vo.getKey().addContent(vo.getValue());
        }
        for(Element body: Message820001Boday.Body820001()){
            XMLHandler.BODY.addContent(body);}
        //渠道代码
        Message820001Boday.ChnlCd.addContent("11111111111111");
        //签约机构Id
        Message820001Boday.SgnBrchId.addContent("3333333");
        //实时标志
        Message820001Boday.RlTmFlg.addContent("4444444444");
        //预约时间戳
        Message820001Boday.BookTmStmp.addContent(message820001Entity.getBookTmStmp());
        //客户号
        Message820001Boday.ClientNo.addContent(message820001Entity.getClientNo());
        //账号
        Message820001Boday.AcctNo.addContent(message820001Entity.getAcctNo());
        //手机号码
        Message820001Boday.MobileNo.addContent(message820001Entity.getMobileNo());
        //消息内容
        Message820001Boday.MsgCntnt.addContent(message820001Entity.getMsgCntnt());
        //发送组装后的报文
        String Client820001 = SocketClient.SocketClient(XMLHandler.BuildXML());
        System.out.println(XMLHandler.BuildXML());
        //获取所有子节点
        Map<String, Object> map =XMLHandler.parseXML(Client820001,"节点");// TODO: 2017/7/24 0024  传一个默认取值的节点名称
        for(Object value:map.values()){
            System.out.println(value);
        }
        return null;
    }

    *//**
     * 批量相同内容短信转发（02）
     *
     * @return
     *//*
    public  String Message120020005() throws JDOMException, IOException {
        for(Element body: Message120020005Boday.Body120020005()){
            XMLHandler.BODY.addContent(body); }
        //用户号
        Message120020005Boday.UserNo.addContent("77777");
        //交易密码
        Message120020005Boday.TranPswd.addContent("77777");
        //目标号码
        Message120020005Boday.TrgtMblNos.addContent("77777");
        //短信信息
        Message120020005Boday.SmsMsg.addContent("77777");
        //手机号码个数
        Message120020005Boday.MblNoNum.addContent("77777");
        //子端口号码
        Message120020005Boday.SubPortNo.addContent("77777");
        //业务类型
        Message120020005Boday.BsnsType.addContent("77777");
        //参数1(最大长度64字节(必填，默认输入：0))
        Message120020005Boday.Parameter1.addContent("77777");
        //参数2(最大长度64字节(必填，默认输入：0))
        Message120020005Boday.Parameter2.addContent("77777");
        //参数3(最大长度64字节  (必填，默认输入研发部人员给予的4位数字渠道号码，如（66XX）))
        Message120020005Boday.Parameter3.addContent("77777");
        //参数3(最大长度64字节 (可选)（不填）)
        Message120020005Boday.Parameter4.addContent("77777");
        //消息Id
        Message120020005Boday.MsgId.addContent("77777");
        //模块Id
        Message120020005Boday.ModuleId.addContent("77777");
        //预约时间戳
        Message120020005Boday.BookTmStmp.addContent("77777");
        //有效时间戳
        Message120020005Boday.VldTmStmp.addContent("77777");
        //状态报告标志
        Message120020005Boday.StRptFlg.addContent("77777");
        //发送组装后的报文
        String Client120020005 = SocketClient.SocketClient(XMLHandler.BuildXML());
        //获取所有子节点
        Map<String, Object> map =XMLHandler.parseXML(Client120020005,"节点");
        for(Object value:map.values()){
            System.out.println(value);
        }
        return null;
    }

   *//**
     * 批量不同内容短信转发
     *
     * @return
     * @throws JDOMException
     *//*
    public  String Message12002000503() throws JDOMException, IOException {
        for(Element body: Message12002000503Boday.Body12002000503()){
            XMLHandler.BODY.addContent(body); }
        //用户号
        Message12002000503Boday.UserNo.addContent("77777");
        //交易密码
        Message12002000503Boday.TranPswd.addContent("77777");
        //短信包
        Message12002000503Boday.SmsPkg.addContent("77777");
        //发送组装后的报文
        String Client12002000503 = SocketClient.SocketClient(XMLHandler.BuildXML());
        //获取所有子节点
        Map<String, Object> map =XMLHandler.parseXML(Client12002000503,"节点");
        for(Object value:map.values()){
            System.out.println(value);
        }
        return null;
    }

     *//**
     * 行内单笔短信发送（08）
     *
     * @return
     * @throws JDOMException
     **//*
    public  String Message12002000508() throws JDOMException, IOException {
        for(Element body: Message12002000508Boday.Body12002000508()){
            XMLHandler.BODY.addContent(body); }
        //报文长度
        Message12002000508Boday.packlen.addContent("77777");
        //交易码
        Message12002000508Boday.tradecode.addContent("77777");
        //渠道代码
        Message12002000508Boday.ChnlCd.addContent("77777");
        //签约机构Id
        Message12002000508Boday.SgnBrchId.addContent("77777");
        //实时标志
        Message12002000508Boday.RlTmFlg.addContent("77777");
        //预约时间戳
        Message12002000508Boday.BookTmStmp.addContent("77777");
        //客户号
        Message12002000508Boday.ClientNo.addContent("77777");
        //账号
        Message12002000508Boday.AcctNo.addContent("77777");
        //手机号码
        Message12002000508Boday.MobileNo.addContent("77777");
        // 消息内容
        Message12002000508Boday.MsgCntnt.addContent("77777");
        //发送组装后的报文
        String Client12002000508 = SocketClient.SocketClient(XMLHandler.BuildXML());
        //获取所有子节点
        Map<String, Object> map =XMLHandler.parseXML(Client12002000508,"节点");
        for(Object value:map.values()){
            System.out.println(value);
        }
        return null;
    }

    *//**
     * 上行短信查询（01）
     *
     * @return
     * @throws JDOMException
     **//*
    public  String Message12003005001() throws JDOMException, IOException {
        //通道号码
        Message12003005001Boday.SmsNo.addContent("77777");
        //页码
        Message12003005001Boday.Page.addContent("77777");
        //每页记录数
        Message12003005001Boday.PerPgNum.addContent("77777");
        //手机号码
        Message12003005001Boday.MobileNo.addContent("77777");
        //查询类型
        Message12003005001Boday.QryType.addContent("77777");
        //查询起始日期
        Message12003005001Boday.StartDate.addContent("77777");
        //查询结束日期
        Message12003005001Boday.EndDate.addContent("77777");
        //发送组装后的报文
        String Client12003005001 = SocketClient.SocketClient(XMLHandler.BuildXML());
        //获取所有子节点
        Map<String, Object> map =XMLHandler.parseXML(Client12003005001,"节点");
        for(Object value:map.values()){
            System.out.println(value);
        }
        return null;
    }

    *//**
     * 下行短信查询（02）
     *
     * @return
     * @throws JDOMException
     **//*
    public  String Message12003005002() throws JDOMException, IOException{
        //通道号码
        Message12003005002Boday.SmsNo.addContent("77777");
        //页码
        Message12003005002Boday.Page.addContent("77777");
        //每页记录数
        Message12003005002Boday.PerPgNum.addContent("77777");
        //手机号码
        Message12003005002Boday.MobileNo.addContent("77777");
        //查询类型
        Message12003005002Boday.QryType.addContent("77777");
        //查询起始日期
        Message12003005002Boday.StartDate.addContent("77777");
        //查询结束日期
        Message12003005002Boday.EndDate.addContent("77777");
        //发送组装后的报文
        String Client12003005002 = SocketClient.SocketClient(XMLHandler.BuildXML());
        //获取所有子节点
        Map<String, Object> map =XMLHandler.parseXML(Client12003005002,"节点");
        for(Object value:map.values()){
            System.out.println(value);
        }
        return null;
    }

    *//**
     * 添加父节点
     *//*
    public static void service(){
        service.addContent(SYS_HEAD);
        service.addContent(APP_HEAD);
        service.addContent(BODY);
    }
}
*/