import com.joyintech.tams.exchange.common.bean.EnableQryBean;
import com.joyintech.tams.exchange.common.bean.HistoryQryBean;
import com.joyintech.tams.exchange.common.bean.QuoteQryBean;
import com.joyintech.tams.exchange.common.bean.ServerExtendInfo;
import com.joyintech.tams.exchange.common.constant.EntrustBs;
import com.joyintech.tams.exchange.common.constant.EntrustProp;
import com.joyintech.tams.exchange.common.constant.Market;
import com.joyintech.tams.exchange.common.constant.QuoteType;
import com.joyintech.tams.exchange.common.db.domain.Account;
import com.joyintech.tams.exchange.common.db.domain.CancelEntrustBean;
import com.joyintech.tams.exchange.common.db.domain.Entrust;
import com.joyintech.tams.exchange.common.db.domain.IntegratedEntrust;
import com.joyintech.tams.exchange.common.net.handler.CXServerDecoder;
import com.joyintech.tams.exchange.common.net.handler.CXServerEncoder;
import com.joyintech.tams.exchange.common.net.handler.CXServerHandler;
import com.joyintech.tams.exchange.terminal.TerminalApplication;
import com.joyintech.tams.exchange.terminal.agent.TerminalAgent;
import com.joyintech.tams.exchange.terminal.trade.TradeResponseImpl;
import com.joyintech.tams.exchange.terminal.trade.jsd.JSDTradeApi;
import com.joyintech.tams.exchange.terminal.trade.jsd.JSDTradeClient;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespLoginData;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespLoginHeader;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * create By MAXX on 2020/7/24
 */
@SpringBootTest(classes = TerminalApplication.class)
@RunWith(SpringRunner.class)
public class JSDTest {
    private static final Logger logger = LoggerFactory.getLogger(JSDTest.class);

    @Autowired
    private JSDTradeApi jsdTradeApi;
    String fundAccount = "40650800";//无出入库权限
    //String fundAccount = "40438435";//有出入库权限
    Account account = new Account();
    @Autowired
    private TerminalAgent agent;
    @Autowired
    private TradeResponseImpl tradeResponseImpl;

    @Autowired
    private JSDTradeClient jsdTradeClient;
    private EmbeddedChannel channel = new EmbeddedChannel();
    private Long requestId = 1L;
    private EntrustProp[] entrustPropListByGPSZBUY = new EntrustProp[]{
            EntrustProp.NORMAL,
            EntrustProp.COUNTERPARTY,
            EntrustProp.OWN_FIRST,
            EntrustProp.IMMEDIATE_TO_CANCEL,
            EntrustProp.FILL,
            EntrustProp.BEST5_TO_CANCEL
    };

    private EntrustProp[] integratedEntrustPropArray = new EntrustProp[]{
            EntrustProp.INTENTION,
            EntrustProp.SET_PRICE,
            EntrustProp.CONFIRM,
            //EntrustProp.LIMIT_PRICE,
            EntrustProp.MUTUAL_TRADE,
            EntrustProp.LARGE_AFTER_CLOSING,
            EntrustProp.LARGE_AFTER_CLOSING_PRICE,
            EntrustProp.LARGE_AFTER_CLOSING_WEIGHT_PRICE
    };
    //固收
    private EntrustProp[] integratedEntrustPropSH = new EntrustProp[]{
            EntrustProp.SH_FIXEDINCOME_BAOJIA_CONFIRM,
            EntrustProp.SH_FIXEDINCOME_TRADE_CLICK,
            EntrustProp.SH_FIXEDINCOME_BAOJIA_OPPOSITE,
            EntrustProp.SH_FIXEDINCOME_KZHCJSB_,
            EntrustProp.SH_FIXEDINCOME_ZYJCJSB
    };
    private EntrustProp[] integratedEntrustPropSH_REPURCHASE = new EntrustProp[]{
            EntrustProp.SH_REPURCHASE_INTENTION,
            EntrustProp.SH_REPURCHASE_TRADE,
            EntrustProp.SH_REPURCHASE_TRADE_CONFIRM,
            EntrustProp.SH_REPURCHASE_TRADE_REFUSE
    };
    private EntrustProp[] integratedEntrustPropSZ_REPURCHASE = new EntrustProp[]{
            EntrustProp.SZ_REPURCHASE_BEGIN
    };
    //上交所市价
    private EntrustProp[] entrustPropListByGPSH = new EntrustProp[]{
            EntrustProp.NORMAL,
            EntrustProp.BEST5_TO_LIMIT,
            EntrustProp.BEST5_TO_CANCEL
    };
    private EntrustBs[] entrustBsList = new EntrustBs[]{
            EntrustBs.BUY,
            EntrustBs.SELL
    };

    private Market[] marketList = new Market[]{
            Market.SHSE,
            Market.SZSE
    };

    @Before
    public void getAccountByLogin() {
        ChannelPipeline channelPipeline = channel.pipeline();
        channelPipeline.addLast("encode", new CXServerEncoder())
                       .addLast("decode", new CXServerDecoder())
                       .addLast("handler", new CXServerHandler(TerminalAgent.class));
        agent.setChannel(channel);
        tradeResponseImpl.setTerminalAgent(agent);
        account.setFinanceAccount(fundAccount);
        account.setPwd("123456");
        account.setId("40650800");//无出入库权限
        //account.setId("5:40438435");//有出入库权限
        jsdTradeApi.reqClientLogin(account);
    }

    /***
     * @Description: 登录
     * @Param: []
     * @return: void
     * @Author: MAXX
     * @Date: 2020/7/24
     */
    @Test
    public void login() {
    }

    /***
     * @Description: 证券委托查询
     * @Param: []
     * @return: void
     * @Author: MAXX
     * @Date: 2020/7/24
     */
    @Test
    public void reqEntrusts() {
        RespLoginHeader respLoginHeader = JSDTradeClient.getLoginHeaderByLoginMap(account.getId());
        jsdTradeApi.reqQryEntrust(account, requestId, respLoginHeader);
    }

    /***
     * @Description: 证券成交查询
     * @Param: []
     * @return: void
     * @Author: MAXX
     * @Date: 2020/7/25
     */
    @Test
    public void reqTrades() {
        RespLoginHeader respLoginHeader = JSDTradeClient.getLoginHeaderByLoginMap(account.getId());
        jsdTradeApi.reqQryTrade(account, requestId, respLoginHeader);
    }

    /***
     * @Description: 普通委托
     * @Param: []
     * @return: void
     * @Author: MAXX
     * @Date: 2020/7/25
     */
    @Test
    public void entrust() {
        RespLoginHeader respLoginHeader = JSDTradeClient.getLoginHeaderByLoginMap(account.getId());
        List<RespLoginData> dataList = JSDTradeClient.getLoginDataById(account.getId());
        Map<Character, RespLoginData> dataMap = dataList.stream().collect(Collectors.toMap(RespLoginData::getMarket, respLoginData -> respLoginData));
        jsdTradeClient.setITerminalMsgNotify(agent);
        ServerExtendInfo serverExtendInfo = new ServerExtendInfo();
        jsdTradeClient.init(agent, serverExtendInfo, agent);
        Entrust entrust = new Entrust();
        entrust.setId("e20072511101100002");
        /*entrust.setInstructionId("WTBOND202003262015");
        entrust.setPortfolioId("JSDCSCP01");
        entrust.setUserId("mxx1");
        entrust.setUsername("马晓晓");*/
        agent.addRequestIdEntrustMap(entrust.getId(), requestId);
        entrust.setAccountId(account.getId());
        entrust.setFinanceAccount(account.getFinanceAccount());
        entrust.setAccountName(account.getAccountName());
        for (int m = 0; m < marketList.length; m++) {
            entrust.setMarket(marketList[m].value);
            logger.info("============" + marketList[m].name + "===============");
            /*logger.info("============股票===============");
            if (Market.SZSE.value.equals(marketList[m].value)) {
                entrust.setStockCode("000002.SZ");

            } else {
                entrust.setStockCode("601985.SH");
            }
            for (int j = 0; j < entrustBsList.length; j++) {
                logger.info("============" + entrustBsList[j].name + "===============");
                entrust.setEntrustBs(entrustBsList[j].value);
                if (Market.SZSE.value.equals(marketList[m].value)) {//深圳
                    for (int i = 0; i < entrustPropListByGPSZBUY.length; i++) {
                        int num = (int) (Math.random() * 5) + 1;
                        entrust.setEntrustVolume(100 * num);
                        entrust.setEntrustPrice(17.98);
                        logger.info("============" + entrustPropListByGPSZBUY[i].name + " begin===============");
                        requestId++;
                        agent.addRequestIdEntrustMap(entrust.getId(), requestId);
                        entrust.setEntrustProp(entrustPropListByGPSZBUY[i].value);
                        jsdTradeApi.entrust(entrust, account, requestId, respLoginHeader, dataMap);

                        logger.info("============" + entrustPropListByGPSZBUY[i].name + " end===============");
                    }
                } else {//上海

                    for (int i = 0; i < entrustPropListByGPSH.length; i++) {
                        int num = (int) (Math.random() * 5) + 1;
                        entrust.setEntrustVolume(100 * num);
                        entrust.setEntrustPrice(17.98);
                        logger.info("============" + entrustPropListByGPSH[i].name + " begin===============");
                        requestId++;
                        agent.addRequestIdEntrustMap(entrust.getId(), requestId);
                        entrust.setEntrustProp(entrustPropListByGPSH[i].value);
                        jsdTradeApi.entrust(entrust, account, requestId, respLoginHeader, dataMap);

                        logger.info("============" + entrustPropListByGPSH[i].name + " end===============");
                    }

                }

            }*/


            /*logger.info("============股票===============");
            if (Market.SZSE.value.equals(marketList[m].value)) {
                entrust.setStockCode("000002.SZ");

            } else {
                entrust.setStockCode("601985.SH");
            }
            for (int j = 0; j < entrustBsList.length; j++) {
                logger.info("============" + entrustBsList[j].name + "===============");
                entrust.setEntrustBs(entrustBsList[j].value);
                if (Market.SZSE.value.equals(marketList[m].value)) {//深圳

                    int num = (int) (Math.random() * 5) + 1;
                    entrust.setEntrustVolume(100 * num);
                    entrust.setEntrustPrice(17.98);
                    logger.info("============" + EntrustProp.PS.name + " begin===============");
                    requestId++;
                    agent.addRequestIdEntrustMap(entrust.getId(), requestId);
                    entrust.setEntrustProp(EntrustProp.PS.value);
                    jsdTradeApi.entrust(entrust, account, requestId, respLoginHeader, dataMap);

                    logger.info("============" + EntrustProp.PS.name + " end===============");

                } else {//上海


                    int num = (int) (Math.random() * 5) + 1;
                    entrust.setEntrustVolume(100 * num);
                    entrust.setEntrustPrice(17.98);
                    logger.info("============" + EntrustProp.PS.name + " begin===============");
                    requestId++;
                    agent.addRequestIdEntrustMap(entrust.getId(), requestId);
                    entrust.setEntrustProp(EntrustProp.PS.value);
                    jsdTradeApi.entrust(entrust, account, requestId, respLoginHeader, dataMap);

                    logger.info("============" + EntrustProp.PS.name + " end===============");


                }

            }*/
            logger.info("============债券===============");
            if (Market.SZSE.value.equals(marketList[m].value)) {
                entrust.setStockCode("100303.SZ");
            } else {
                entrust.setStockCode("157000.SH");
            }
            for (int j = 0; j < entrustBsList.length; j++) {
                logger.info("============" + entrustBsList[j].name + "===============");
                entrust.setEntrustBs(entrustBsList[j].value);
                int num = (int) (Math.random() * 5) + 1;
                entrust.setEntrustVolume(100 * num);
                entrust.setEntrustPrice(new BigDecimal(17.98));
                logger.info("============" + EntrustProp.NORMAL.name + " begin===============");
                requestId++;
                agent.addRequestIdEntrustMap(entrust.getId(), requestId);
                entrust.setEntrustProp(EntrustProp.NORMAL.value);
                jsdTradeApi.entrust(entrust, account, requestId, respLoginHeader, dataMap);
                logger.info("============" + EntrustProp.NORMAL.name + " end===============");
            }

            /*logger.info("============债转股===============");
            if (Market.SZSE.value.equals(marketList[m].value)) {
                entrust.setStockCode("128086.SZ");

            } else {
                entrust.setStockCode("191557.SH");
            }
            for (int j = 0; j < entrustBsList.length; j++) {
                logger.info("============" + entrustBsList[j].name + "===============");
                entrust.setEntrustBs(entrustBsList[j].value);
                int num = (int) (Math.random() * 5) + 1;
                entrust.setEntrustVolume(100 * num);
                entrust.setEntrustPrice(17.98);
                logger.info("============" + EntrustProp.CHANGE_TO_STOCK.name + " begin===============");
                requestId++;
                agent.addRequestIdEntrustMap(entrust.getId(), requestId);
                entrust.setEntrustProp(EntrustProp.CHANGE_TO_STOCK.value);
                jsdTradeApi.entrust(entrust, account, requestId, respLoginHeader, dataMap);

                logger.info("============" + EntrustProp.CHANGE_TO_STOCK.name + " end===============");


            }*/

            /*logger.info("============基金===============");
            if (Market.SZSE.value.equals(marketList[m].value)) {
                entrust.setStockCode("160220.SZ");

            } else {
                entrust.setStockCode("510500.SH");
            }
            for (int j = 0; j < entrustBsList.length; j++) {
                logger.info("============" + entrustBsList[j].name + "===============");
                entrust.setEntrustBs(entrustBsList[j].value);
                if (Market.SZSE.value.equals(marketList[m].value)) {//深圳
                    for (int i = 0; i < entrustPropListByGPSZBUY.length; i++) {
                        int num = (int) (Math.random() * 5) + 1;
                        entrust.setEntrustVolume(100 * num);
                        entrust.setEntrustPrice(17.98);
                        logger.info("============" + entrustPropListByGPSZBUY[i].name + " begin===============");
                        requestId++;
                        agent.addRequestIdEntrustMap(entrust.getId(), requestId);
                        entrust.setEntrustProp(entrustPropListByGPSZBUY[i].value);
                        jsdTradeApi.entrust(entrust, account, requestId, respLoginHeader, dataMap);

                        logger.info("============" + entrustPropListByGPSZBUY[i].name + " end===============");
                    }
                } else {
                    for (int i = 0; i < entrustPropListByGPSH.length; i++) {
                        int num = (int) (Math.random() * 5) + 1;
                        entrust.setEntrustVolume(100 * num);
                        entrust.setEntrustPrice(17.98);
                        logger.info("============" + entrustPropListByGPSH[i].name + " begin===============");
                        requestId++;
                        agent.addRequestIdEntrustMap(entrust.getId(), requestId);
                        entrust.setEntrustProp(entrustPropListByGPSH[i].value);
                        jsdTradeApi.entrust(entrust, account, requestId, respLoginHeader, dataMap);

                        logger.info("============" + entrustPropListByGPSH[i].name + " end===============");
                    }

                }

            }*/

           /* logger.info("============质押券===============");
            if (Market.SZSE.value.equals(marketList[m].value)) {
                entrust.setStockCode("100303.SZ");

            } else {
                entrust.setStockCode("090107.SH");
            }
            //for (int j = entrustBsList.length - 1; j >= 0; j--) {
            logger.info("============" + entrustBsList[1].name + "===============");
            entrust.setEntrustBs(entrustBsList[1].value);


            int num = (int) (Math.random() * 5) + 1;
            entrust.setEntrustVolume(1000000 * num);
            entrust.setEntrustPrice(new BigDecimal(8.67D));
            logger.info("============" + EntrustProp.ZYCRK.name + " begin===============");
            requestId++;
            agent.addRequestIdEntrustMap(entrust.getId(), requestId);
            entrust.setEntrustProp(EntrustProp.ZYCRK.value);
            jsdTradeApi.entrust(entrust, account, requestId, respLoginHeader, dataMap);

            logger.info("============" + EntrustProp.ZYCRK.name + " end===============");

*/
            //}


            /*logger.info("============质押式回购===============");
            if (Market.SZSE.value.equals(marketList[m].value)) {
                entrust.setStockCode("131810.SZ");

            } else {
                entrust.setStockCode("204001.SH");
            }
            for (int j = 0; j < entrustBsList.length; j++) {
                logger.info("============" + entrustBsList[j].name + "===============");
                entrust.setEntrustBs(entrustBsList[j].value);
                if (Market.SZSE.value.equals(marketList[m].value)) {//深圳


                    entrust.setEntrustVolume(100);
                    entrust.setEntrustPrice(8.67);
                    logger.info("============" + EntrustProp.NORMAL.name + " begin===============");
                    requestId++;
                    agent.addRequestIdEntrustMap(entrust.getId(), requestId);
                    entrust.setEntrustProp(EntrustProp.NORMAL.value);
                    jsdTradeApi.entrust(entrust, account, requestId, respLoginHeader, dataMap);

                    logger.info("============" + EntrustProp.NORMAL.name + " end===============");

                } else {


                    entrust.setEntrustVolume(100);
                    entrust.setEntrustPrice(17.98);
                    logger.info("============" + EntrustProp.NORMAL.name + " begin===============");
                    requestId++;
                    agent.addRequestIdEntrustMap(entrust.getId(), requestId);
                    entrust.setEntrustProp(EntrustProp.NORMAL.value);
                    jsdTradeApi.entrust(entrust, account, requestId, respLoginHeader, dataMap);

                    logger.info("============" + EntrustProp.NORMAL.name + " end===============");


                }

            }*/
        }
    }

    /***
     * @Description: 委托撤单
     * @Param: []
     * @return: void
     * @Author: MAXX
     * @Date: 2020/7/29
     */
    @Test
    public void cancelEntrust() {
        RespLoginHeader respLoginHeader = JSDTradeClient.getLoginHeaderByLoginMap(account.getId());
        CancelEntrustBean cancelEntrustBean = new CancelEntrustBean();
        cancelEntrustBean.setMarket(Market.SHSE.value);
        cancelEntrustBean.setBrokerEntrustId("38394");
        cancelEntrustBean.setId("e20072511101100002");
        cancelEntrustBean.setCancelId("e20072511101300002");
        agent.addRequestIdEntrustMap(cancelEntrustBean.getId(), requestId);
        jsdTradeApi.cancelEntrust(cancelEntrustBean, account, respLoginHeader);
    }

    /***
     * @Description: 综合业务委托确认 固收 债券质押式协议回购
     * @Param: []
     * @return: void
     * @Author: MAXX
     * @Date: 2020/7/30
     */
    @Test
    public void integratedEntrust() {
        RespLoginHeader respLoginHeader = JSDTradeClient.getLoginHeaderByLoginMap(account.getId());
        List<RespLoginData> dataList = JSDTradeClient.getLoginDataById(account.getId());
        Map<Character, RespLoginData> dataMap = dataList.stream().collect(Collectors.toMap(RespLoginData::getMarket, respLoginData -> respLoginData));
        jsdTradeClient.setITerminalMsgNotify(agent);
        ServerExtendInfo serverExtendInfo = new ServerExtendInfo();
        jsdTradeClient.init(agent, serverExtendInfo, agent);
        IntegratedEntrust integratedEntrust = new IntegratedEntrust();
        integratedEntrust.setId("e20072511101100002");

        /*integratedEntrust.setMarket(marketList[1].value);
        logger.info("============" + marketList[1].name + "===============");
        logger.info("============股票===============");
        if (Market.SZSE.value.equals(marketList[1].value)) {
            integratedEntrust.setStockCode("000002.SZ");

        } else {
            integratedEntrust.setStockCode("601985.SH");
        }

        integratedEntrust.setEntrustVolume(300000);
        integratedEntrust.setEntrustPrice(0.89);
        integratedEntrust.setContractId("12345");
        integratedEntrust.setLiaisonName("qq");
        integratedEntrust.setLiaisonTel("18978654567");
        integratedEntrust.setPropSeatNo("E09876");
        for (int i = 0; i < integratedEntrustPropArray.length; i++) {
            integratedEntrust.setEntrustProp(integratedEntrustPropArray[i].value);
            logger.info("============" + integratedEntrustPropArray[i].name + "===============");

            for (int j = 0; j < entrustBsList.length; j++) {
                logger.info("============" + entrustBsList[j].name + "===============");
                integratedEntrust.setEntrustBs(entrustBsList[j].value);
                requestId++;
                agent.addRequestIdEntrustMap(integratedEntrust.getId(), requestId);
                jsdTradeApi.integratedEntrust(integratedEntrust, requestId, account, respLoginHeader, dataMap);

            }


        }
*/
        /*integratedEntrust.setMarket(marketList[1].value);
        logger.info("============" + marketList[1].name + "===============");*/
        /*
         logger.info("============债券===============");
        if (Market.SZSE.value.equals(marketList[1].value)) {
            integratedEntrust.setStockCode("100303.SZ");

        } else {
            integratedEntrust.setStockCode("010107.SH");
        }

        integratedEntrust.setEntrustVolume(300000);
        integratedEntrust.setEntrustPrice(0.89);
        integratedEntrust.setContractId("12345");
        integratedEntrust.setLiaisonName("qq");
        integratedEntrust.setLiaisonTel("18978654567");
        integratedEntrust.setPropSeatNo("E09876");
        for (int i = 0; i < integratedEntrustPropArray.length; i++) {
            integratedEntrust.setEntrustProp(integratedEntrustPropArray[i].value);
            logger.info("============" + integratedEntrustPropArray[i].name + "===============");

            for (int j = 0; j < entrustBsList.length; j++) {
                logger.info("============" + entrustBsList[j].name + "===============");
                integratedEntrust.setEntrustBs(entrustBsList[j].value);
                requestId++;
                agent.addRequestIdEntrustMap(integratedEntrust.getId(), requestId);
                jsdTradeApi.integratedEntrust(integratedEntrust, requestId, account, respLoginHeader, dataMap);

            }


        }*/
        /*integratedEntrust.setMarket(marketList[0].value);
        logger.info("============" + marketList[0].name + "===============");
        logger.info("============债券===============");
        if (Market.SZSE.value.equals(marketList[0].value)) {
            integratedEntrust.setStockCode("100303.SZ");

        } else {
            integratedEntrust.setStockCode("010107.SH");
        }

        integratedEntrust.setEntrustVolume(300000);
        integratedEntrust.setEntrustPrice(0.89);
        integratedEntrust.setContractId("12345");
        integratedEntrust.setLiaisonName("qq");
        integratedEntrust.setLiaisonTel("18978654567");
        integratedEntrust.setPropSeatNo("E09876");
        for (int i = 0; i < integratedEntrustPropSH.length; i++) {
            integratedEntrust.setEntrustProp(integratedEntrustPropSH[i].value);
            logger.info("============" + integratedEntrustPropSH[i].name + "===============");

            for (int j = 0; j < entrustBsList.length; j++) {
                logger.info("============" + entrustBsList[j].name + "===============");
                integratedEntrust.setEntrustBs(entrustBsList[j].value);
                requestId++;
                agent.addRequestIdEntrustMap(integratedEntrust.getId(), requestId);
                jsdTradeApi.integratedEntrust(integratedEntrust, requestId, account, respLoginHeader, dataMap);

            }


        }*/
      /*  logger.info("============基金===============");
        if (Market.SZSE.value.equals(marketList[1].value)) {
            integratedEntrust.setStockCode("160220.SZ");

        } else {
            integratedEntrust.setStockCode("510500.SH");
        }

        integratedEntrust.setEntrustVolume(2000000);
        integratedEntrust.setEntrustPrice(0.01);
        integratedEntrust.setContractId("12345");
        integratedEntrust.setLiaisonName("qq");
        integratedEntrust.setLiaisonTel("18978654567");
        integratedEntrust.setPropSeatNo("E09876");
        for (int i = 0; i < integratedEntrustPropArray.length; i++) {
            integratedEntrust.setEntrustProp(integratedEntrustPropArray[i].value);
            logger.info("============" + integratedEntrustPropArray[i].name + "===============");

            for (int j = 0; j < entrustBsList.length; j++) {
                logger.info("============" + entrustBsList[j].name + "===============");
                integratedEntrust.setEntrustBs(entrustBsList[j].value);
                requestId++;
                agent.addRequestIdEntrustMap(integratedEntrust.getId(), requestId);
                jsdTradeApi.integratedEntrust(integratedEntrust, requestId, account, respLoginHeader, dataMap);

            }


        }*/


       /* logger.info("============质押式回购===============");
        if (Market.SZSE.value.equals(marketList[1].value)) {
            integratedEntrust.setStockCode("131810.SZ");

        } else {
            integratedEntrust.setStockCode("204001.SH");
        }
        integratedEntrust.setEntrustVolume(300000);
        integratedEntrust.setEntrustPrice(0.89);
        integratedEntrust.setContractId("12345");
        integratedEntrust.setLiaisonName("qq");
        integratedEntrust.setLiaisonTel("18978654567");
        integratedEntrust.setPropSeatNo("E09876");
        for (int i = 0; i < integratedEntrustPropArray.length; i++) {
            integratedEntrust.setEntrustProp(integratedEntrustPropArray[i].value);
            logger.info("============" + integratedEntrustPropArray[i].name + "===============");

            for (int j = 0; j < entrustBsList.length; j++) {
                logger.info("============" + entrustBsList[j].name + "===============");
                integratedEntrust.setEntrustBs(entrustBsList[j].value);
                requestId++;
                agent.addRequestIdEntrustMap(integratedEntrust.getId(), requestId);
                jsdTradeApi.integratedEntrust(integratedEntrust, requestId, account, respLoginHeader, dataMap);

            }


        }*/
        integratedEntrust.setMarket(marketList[0].value);
        logger.info("============" + marketList[0].name + "===============");
        logger.info("============质押式协议回购===============");
        if (Market.SZSE.value.equals(marketList[0].value)) {
            integratedEntrust.setStockCode("100303.SZ");
        } else {
            integratedEntrust.setStockCode("090107.SH");
        }
        integratedEntrust.setEntrustVolume(300000);
        integratedEntrust.setEntrustPrice(new BigDecimal(0.89D));
        integratedEntrust.setContractId("12345");
        integratedEntrust.setLiaisonName("qq");
        integratedEntrust.setLiaisonTel("18978654567");
        integratedEntrust.setPropSeatNo("E09876");
        for (int i = 0; i < integratedEntrustPropSH_REPURCHASE.length; i++) {
            integratedEntrust.setEntrustProp(integratedEntrustPropSH_REPURCHASE[i].value);
            logger.info("============" + integratedEntrustPropSH_REPURCHASE[i].name + "===============");
            for (int j = 0; j < entrustBsList.length; j++) {
                logger.info("============" + entrustBsList[j].name + "===============");
                integratedEntrust.setEntrustBs(entrustBsList[j].value);
                requestId++;
                agent.addRequestIdEntrustMap(integratedEntrust.getId(), requestId);
                jsdTradeApi.integratedEntrust(integratedEntrust, requestId, account, respLoginHeader, dataMap);
            }
        }
    }

    /***
     * @Description: 综合业务委托撤单
     * @Param: []
     * @return: void
     * @Author: MAXX
     * @Date: 2020/7/30
     */
    @Test
    public void cancelIntegratedEntrust() {
        jsdTradeClient.setITerminalMsgNotify(agent);
        ServerExtendInfo serverExtendInfo = new ServerExtendInfo();
        jsdTradeClient.init(agent, serverExtendInfo, agent);
        CancelEntrustBean cancelEntrustBean = new CancelEntrustBean();
        cancelEntrustBean.setId("e20072511101100002");
        cancelEntrustBean.setCancelId("e20072511121100002");
        agent.addRequestIdEntrustMap(cancelEntrustBean.getId(), requestId);
        cancelEntrustBean.setMarket(Market.SZSE.value);
        cancelEntrustBean.setBrokerEntrustId("631704041");
        RespLoginHeader respLoginHeader = JSDTradeClient.getLoginHeaderByLoginMap(account.getId());
        jsdTradeApi.cancelIntegratedEntrust(cancelEntrustBean, account, respLoginHeader);
    }

    /***
     * @Description: 大宗成交主动拉取
     * @Param: []
     * @return: void
     * @Author: MAXX
     * @Date: 2020/7/30 
     */
    @Test
    public void qryIntegratedTrade() {
        RespLoginHeader respLoginHeader = JSDTradeClient.getLoginHeaderByLoginMap(account.getId());
        jsdTradeApi.qryProtocolBackTrade(account, respLoginHeader);
    }

    @Test
    public void queryIntegratedQuotes() {
        RespLoginHeader respLoginHeader = JSDTradeClient.getLoginHeaderByLoginMap(account.getId());
        QuoteQryBean quoteQryBean = new QuoteQryBean();
        quoteQryBean.setQuoteType(QuoteType.BULK_QUOTE.value);
        quoteQryBean.setStockCode("000002.SZ");
        jsdTradeApi.queryIntegratedQuotes(quoteQryBean, requestId, account, respLoginHeader);
    }

    /*** 
     * @Description: 大宗委托主动拉取
     * @Param: []
     * @return: void
     * @Author: MAXX
     * @Date: 2020/7/30 
     */
    @Test
    public void qryIntegratedOrder() {
        RespLoginHeader respLoginHeader = JSDTradeClient.getLoginHeaderByLoginMap(account.getId());
        jsdTradeApi.qryIntegratedOrder(account, respLoginHeader);
    }

    /***
     * @Description: 最大交易数量
     * @Param: []
     * @return: void
     * @Author: MAXX
     * @Date: 2020/7/29 
     */
    @Test
    public void queryEnable() {
        RespLoginHeader respLoginHeader = JSDTradeClient.getLoginHeaderByLoginMap(account.getId());
        List<RespLoginData> dataList = JSDTradeClient.getLoginDataById(account.getId());
        Map<Character, RespLoginData> dataMap = dataList.stream().collect(Collectors.toMap(RespLoginData::getMarket, respLoginData -> respLoginData));
        EnableQryBean qryBean = new EnableQryBean();
        qryBean.setStockCode("000002.SZ");
        qryBean.setEntrustProp(EntrustProp.NORMAL.value);
        qryBean.setEntrustBs(EntrustBs.BUY.value);
        qryBean.setEntrustPrice(65.8D);
        jsdTradeApi.queryEnable(qryBean, requestId, account, respLoginHeader, dataMap);
    }

    /*** 
     * @Description: 历史证券成交查询
     * @Param: []
     * @return: void
     * @Author: MAXX
     * @Date: 2020/7/29 
     */
    @Test
    public void queryHistoryTrade() {
        RespLoginHeader respLoginHeader = JSDTradeClient.getLoginHeaderByLoginMap(account.getId());
        HistoryQryBean historyQryBean = new HistoryQryBean();
        historyQryBean.setStartDay("20200725");
        historyQryBean.setEndDay("20200729");
        historyQryBean.setPageSize(100);
        jsdTradeApi.queryHistoryTrade(historyQryBean, requestId, account, respLoginHeader);
    }

    /***
     * @Description: 客户资金快速查询
     * @Param: []
     * @return: void
     * @Author: MAXX
     * @Date: 2020/7/29
     */
    @Test
    public void queryCustomerFinance() {
        RespLoginHeader respLoginHeader = JSDTradeClient.getLoginHeaderByLoginMap(account.getId());
        jsdTradeApi.queryCustomerFinance(2L, account, respLoginHeader);
    }
}
