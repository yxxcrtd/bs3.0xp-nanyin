package com.joyintech.tams.report.interf.message.socketclient;

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
    public static final String SERVICE = "service";

    /**
     * SYS_HEAD部分
     */
    public static final String SYS_HEAD = "SYS_HEAD";

    public static final Element SVCID = new Element("SvcId");

    public static final Element SVCSCNID = new Element("SvcScnId");

    public static final Element CNSMSYSID = new Element("CnsmSysId");

    public static final Element CHNLTYPE = new Element("ChnlType");

    public static final Element SRCSYSID = new Element("SrcSysId");

    public static final Element CNSMSYSSEQNO = new Element("CnsmSysSeqNo");

    public static final Element SRCSYSSEQNO = new Element("SrcSysSeqNo");

    public static final Element MAC = new Element("Mac");

    public static final Element MACORGID = new Element("MacOrgId");

    public static final Element TRANMODE = new Element("TranMode");

    public static final Element TRANDATE = new Element("TranDate");

    public static final Element TRANTIME = new Element("TranTime");

    public static final Element TMNLNO = new Element("TmnlNo");

    public static final Element SRCSYSYSNLNO = new Element("SrcSySysnlNo");

    public static final Element CNSMSYSSVRID = new Element("CnsmSysSvrId");

    public static final Element SRCSYSSVRID = new Element("SrcSysSvrId");

    /**
     * APP_HEAD部分
     */
    public static final String APP_HEAD = "APP_HEAD";

    public static final Element TELLERID = new Element("TellerId");

    public static final Element BRANCHID = new Element("BranchId");

    public static final Element TLRPSWD = new Element("TlrPswd");

    public static final Element TLRLVL = new Element("TlrLvl");

    public static final Element TLRTYPE = new Element("TlrType");

    public static final Element CHKFLAG = new Element("ChkFlag");

    public static final Element AUTHFLAG = new Element("AuthFlag");

    public static final Element AUTHTLRID = new Element("AuthTlrId");

    public static final Element AUTHTLRPSWD = new Element("AuthTlrPswd");

    public static final Element AUTHBRCHID = new Element("AuthBrchId");

    /**
     * BODY部分
     */
    public static final String BODY = "BODY";
}
