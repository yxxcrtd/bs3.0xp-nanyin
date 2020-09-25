package com.joyintech.tams.disclosure.interf.interf.message.socketclient;

import org.jdom.Element;

import java.util.HashMap;
import java.util.Map;

/**
 * 报文头部
 * Created by Administrator on 2017/7/14 0014.
 */
public class SendHeardUtil {
    /**
     * 根节点
     */
    public static final String service = "service";

    /**
     * SYS_HEAD部分
     */
    public static final String SYS_HEAD = "SYS_HEAD";

    public static final Element SvcId = new Element("SvcId");

    public static final Element SvcScnId = new Element("SvcScnId");

    public static final Element CnsmSysId = new Element("CnsmSysId");

    public static final Element ChnlType = new Element("ChnlType");

    public static final Element SrcSysId = new Element("SrcSysId");

    public static final Element CnsmSysSeqNo = new Element("CnsmSysSeqNo");

    public static final Element SrcSysSeqNo = new Element("SrcSysSeqNo");

    public static final Element Mac = new Element("Mac");

    public static final Element MacOrgId = new Element("MacOrgId");

    public static final Element TranMode = new Element("TranMode");

    public static final Element TranDate = new Element("TranDate");

    public static final Element TranTime = new Element("TranTime");

    public static final Element TmnlNo = new Element("TmnlNo");

    public static final Element SrcSySysnlNo = new Element("SrcSySysnlNo");

    public static final Element CnsmSysSvrId = new Element("CnsmSysSvrId");

    public static final Element SrcSysSvrId = new Element("SrcSysSvrId");

    /**
     * APP_HEAD部分
     */
    public static final String APP_HEAD = "APP_HEAD";

    public static final Element TellerId = new Element("TellerId");

    public static final Element BranchId = new Element("BranchId");

    public static final Element TlrPswd = new Element("TlrPswd");

    public static final Element TlrLvl = new Element("TlrLvl");

    public static final Element TlrType = new Element("TlrType");

    public static final Element ChkFlag = new Element("ChkFlag");

    public static final Element AuthFlag = new Element("AuthFlag");

    public static final Element AuthTlrId = new Element("AuthTlrId");

    public static final Element AuthTlrPswd = new Element("AuthTlrPswd");

    public static final Element AuthBrchId = new Element("AuthBrchId");

    /**
     * BODY部分
     */
    public static final String BODY = "BODY";
}
