package com.joyintech.tams.exchange.terminal.trade.kdgateway.bean;

public class KDEntrust {
    private String CUSTOMER;
    private String USER_CODE;
    private String MARKET;
    private String SECU_ACC;
    private String ACCOUNT;
    private String SEAT;
    //    private String SECU_INTL;
    private String SECU_CODE;
    private String TRD_ID;
    private String TRD_IDS;
    private String PRICE;
    private String QTY;
    private String BIZ_NO;
    private String EXT_INST;
    private String EXT_REC_NUM;
    private String OP_REMARK;
    private String MATCH_SEAT;
    private String MATCH_NUM;
    private String ORDER_TYPE;
    private String LINK_SECU_ACC;
    private String MAC_ADDR;
    private String HD_ID;
    private String TRD_TERMCODE;
    private String SERIAL_NO;
    private String AUTO_BUY;
    private String AUTO_BUY_DATE;
    private String BJ_TRD_DATE;
    private String MATCHED_SN;////初始合同序号  购回时用，上海为对手交易员代码
    private String YD_BOND_CODE;
    private String YD_FEE_RATE;//初始百元利率
    private String YD_BB_AMT;//购回日期
    private String LOF_TACODE;
    private String TIME_IN_FORCE;
    private String VID;
    private String MEETINGSEQ;
    private String CJSB_TYPE;
    private String MSG_SERIAL_NO;
    private String EXT_ORDER_ID;//对手方交易员
    private String LINKMAN;//联系人
    private String LINKADD;//联系地址
    private String NONTRDSHARE;//特定减持
    // ‘***,******’（上海固收平台指定对手方交易：前三位表示本方交易商代码，后六位表示本方交易员代码，逗号分隔），本方交易员可不送，默认取公共参数SHXY_COMPANY（交易商）、SHXY_TRADER（交易员代码）
    private String EXT_BIZ_NO;//本方交易员
    private String YD_GHFS;//订单数量2
    private String SHARE_CLS;//股份性质

    // 协议回购入参
    private String BOARD;//交易板块
    private String GP_CONTRACTS;//初始金额
    private String YD_BB_DATE;//购回日期

    private String LD_SECU_ACC;//融出方股东号  购回时用，上海为对手交易商号

    private String RECORD_SN;
    private String GET_ORDERS_MODE;

    private String ACCEPTFLAG;

    public String getCUSTOMER() {
        return CUSTOMER;
    }

    public void setCUSTOMER(String CUSTOMER) {
        this.CUSTOMER = CUSTOMER;
    }

    public String getUSER_CODE() {
        return USER_CODE;
    }

    public void setUSER_CODE(String USER_CODE) {
        this.USER_CODE = USER_CODE;
    }

    public String getMARKET() {
        return MARKET;
    }

    public void setMARKET(String MARKET) {
        this.MARKET = MARKET;
    }

    public String getSECU_ACC() {
        return SECU_ACC;
    }

    public void setSECU_ACC(String SECU_ACC) {
        this.SECU_ACC = SECU_ACC;
    }

    public String getACCOUNT() {
        return ACCOUNT;
    }

    public void setACCOUNT(String ACCOUNT) {
        this.ACCOUNT = ACCOUNT;
    }

    public String getSEAT() {
        return SEAT;
    }

    public void setSEAT(String SEAT) {
        this.SEAT = SEAT;
    }

    public String getSECU_CODE() {
        return SECU_CODE;
    }

    public void setSECU_CODE(String SECU_CODE) {
        this.SECU_CODE = SECU_CODE;
    }

    public String getTRD_ID() {
        return TRD_ID;
    }

    public void setTRD_ID(String TRD_ID) {
        this.TRD_ID = TRD_ID;
    }

    public String getPRICE() {
        return PRICE;
    }

    public void setPRICE(String PRICE) {
        this.PRICE = PRICE;
    }

    public String getQTY() {
        return QTY;
    }

    public void setQTY(String QTY) {
        this.QTY = QTY;
    }

    public String getBIZ_NO() {
        return BIZ_NO;
    }

    public void setBIZ_NO(String BIZ_NO) {
        this.BIZ_NO = BIZ_NO;
    }

    public String getEXT_INST() {
        return EXT_INST;
    }

    public void setEXT_INST(String EXT_INST) {
        this.EXT_INST = EXT_INST;
    }

    public String getEXT_REC_NUM() {
        return EXT_REC_NUM;
    }

    public void setEXT_REC_NUM(String EXT_REC_NUM) {
        this.EXT_REC_NUM = EXT_REC_NUM;
    }

    public String getOP_REMARK() {
        return OP_REMARK;
    }

    public void setOP_REMARK(String OP_REMARK) {
        this.OP_REMARK = OP_REMARK;
    }

    public String getMATCH_SEAT() {
        return MATCH_SEAT;
    }

    public void setMATCH_SEAT(String MATCH_SEAT) {
        this.MATCH_SEAT = MATCH_SEAT;
    }

    public String getMATCH_NUM() {
        return MATCH_NUM;
    }

    public void setMATCH_NUM(String MATCH_NUM) {
        this.MATCH_NUM = MATCH_NUM;
    }

    public String getORDER_TYPE() {
        return ORDER_TYPE;
    }

    public void setORDER_TYPE(String ORDER_TYPE) {
        this.ORDER_TYPE = ORDER_TYPE;
    }

    public String getLINK_SECU_ACC() {
        return LINK_SECU_ACC;
    }

    public void setLINK_SECU_ACC(String LINK_SECU_ACC) {
        this.LINK_SECU_ACC = LINK_SECU_ACC;
    }

    public String getMAC_ADDR() {
        return MAC_ADDR;
    }

    public void setMAC_ADDR(String MAC_ADDR) {
        this.MAC_ADDR = MAC_ADDR;
    }

    public String getHD_ID() {
        return HD_ID;
    }

    public void setHD_ID(String HD_ID) {
        this.HD_ID = HD_ID;
    }

    public String getTRD_TERMCODE() {
        return TRD_TERMCODE;
    }

    public void setTRD_TERMCODE(String TRD_TERMCODE) {
        this.TRD_TERMCODE = TRD_TERMCODE;
    }

    public String getSERIAL_NO() {
        return SERIAL_NO;
    }

    public void setSERIAL_NO(String SERIAL_NO) {
        this.SERIAL_NO = SERIAL_NO;
    }

    public String getAUTO_BUY() {
        return AUTO_BUY;
    }

    public void setAUTO_BUY(String AUTO_BUY) {
        this.AUTO_BUY = AUTO_BUY;
    }

    public String getAUTO_BUY_DATE() {
        return AUTO_BUY_DATE;
    }

    public void setAUTO_BUY_DATE(String AUTO_BUY_DATE) {
        this.AUTO_BUY_DATE = AUTO_BUY_DATE;
    }

    public String getBJ_TRD_DATE() {
        return BJ_TRD_DATE;
    }

    public void setBJ_TRD_DATE(String BJ_TRD_DATE) {
        this.BJ_TRD_DATE = BJ_TRD_DATE;
    }

    public String getMATCHED_SN() {
        return MATCHED_SN;
    }

    public void setMATCHED_SN(String MATCHED_SN) {
        this.MATCHED_SN = MATCHED_SN;
    }

    public String getYD_BOND_CODE() {
        return YD_BOND_CODE;
    }

    public void setYD_BOND_CODE(String YD_BOND_CODE) {
        this.YD_BOND_CODE = YD_BOND_CODE;
    }

    public String getYD_FEE_RATE() {
        return YD_FEE_RATE;
    }

    public void setYD_FEE_RATE(String YD_FEE_RATE) {
        this.YD_FEE_RATE = YD_FEE_RATE;
    }

    public String getYD_BB_AMT() {
        return YD_BB_AMT;
    }

    public void setYD_BB_AMT(String YD_BB_AMT) {
        this.YD_BB_AMT = YD_BB_AMT;
    }

    public String getLOF_TACODE() {
        return LOF_TACODE;
    }

    public void setLOF_TACODE(String LOF_TACODE) {
        this.LOF_TACODE = LOF_TACODE;
    }

    public String getTIME_IN_FORCE() {
        return TIME_IN_FORCE;
    }

    public void setTIME_IN_FORCE(String TIME_IN_FORCE) {
        this.TIME_IN_FORCE = TIME_IN_FORCE;
    }

    public String getVID() {
        return VID;
    }

    public void setVID(String VID) {
        this.VID = VID;
    }

    public String getMEETINGSEQ() {
        return MEETINGSEQ;
    }

    public void setMEETINGSEQ(String MEETINGSEQ) {
        this.MEETINGSEQ = MEETINGSEQ;
    }

    public String getCJSB_TYPE() {
        return CJSB_TYPE;
    }

    public void setCJSB_TYPE(String CJSB_TYPE) {
        this.CJSB_TYPE = CJSB_TYPE;
    }

    public String getMSG_SERIAL_NO() {
        return MSG_SERIAL_NO;
    }

    public void setMSG_SERIAL_NO(String MSG_SERIAL_NO) {
        this.MSG_SERIAL_NO = MSG_SERIAL_NO;
    }

    public String getEXT_ORDER_ID() {
        return EXT_ORDER_ID;
    }

    public void setEXT_ORDER_ID(String EXT_ORDER_ID) {
        this.EXT_ORDER_ID = EXT_ORDER_ID;
    }

    public String getLINKMAN() {
        return LINKMAN;
    }

    public void setLINKMAN(String LINKMAN) {
        this.LINKMAN = LINKMAN;
    }

    public String getLINKADD() {
        return LINKADD;
    }

    public void setLINKADD(String LINKADD) {
        this.LINKADD = LINKADD;
    }

    public String getNONTRDSHARE() {
        return NONTRDSHARE;
    }

    public void setNONTRDSHARE(String NONTRDSHARE) {
        this.NONTRDSHARE = NONTRDSHARE;
    }

    public String getEXT_BIZ_NO() {
        return EXT_BIZ_NO;
    }

    public void setEXT_BIZ_NO(String EXT_BIZ_NO) {
        this.EXT_BIZ_NO = EXT_BIZ_NO;
    }

    public String getYD_GHFS() {
        return YD_GHFS;
    }

    public void setYD_GHFS(String YD_GHFS) {
        this.YD_GHFS = YD_GHFS;
    }

    public String getSHARE_CLS() {
        return SHARE_CLS;
    }

    public void setSHARE_CLS(String SHARE_CLS) {
        this.SHARE_CLS = SHARE_CLS;
    }

    public String getBOARD() {
        return BOARD;
    }

    public void setBOARD(String BOARD) {
        this.BOARD = BOARD;
    }

    public String getGP_CONTRACTS() {
        return GP_CONTRACTS;
    }

    public void setGP_CONTRACTS(String GP_CONTRACTS) {
        this.GP_CONTRACTS = GP_CONTRACTS;
    }

    public String getYD_BB_DATE() {
        return YD_BB_DATE;
    }

    public void setYD_BB_DATE(String YD_BB_DATE) {
        this.YD_BB_DATE = YD_BB_DATE;
    }

    public String getLD_SECU_ACC() {
        return LD_SECU_ACC;
    }

    public void setLD_SECU_ACC(String LD_SECU_ACC) {
        this.LD_SECU_ACC = LD_SECU_ACC;
    }

    public String getRECORD_SN() {
        return RECORD_SN;
    }

    public void setRECORD_SN(String RECORD_SN) {
        this.RECORD_SN = RECORD_SN;
    }

    public String getGET_ORDERS_MODE() {
        return GET_ORDERS_MODE;
    }

    public void setGET_ORDERS_MODE(String GET_ORDERS_MODE) {
        this.GET_ORDERS_MODE = GET_ORDERS_MODE;
    }

    public String getTRD_IDS() {
        return TRD_IDS;
    }

    public void setTRD_IDS(String TRD_IDS) {
        this.TRD_IDS = TRD_IDS;
    }

    public String getACCEPTFLAG() {
        return ACCEPTFLAG;
    }

    public void setACCEPTFLAG(String ACCEPTFLAG) {
        this.ACCEPTFLAG = ACCEPTFLAG;
    }
}
