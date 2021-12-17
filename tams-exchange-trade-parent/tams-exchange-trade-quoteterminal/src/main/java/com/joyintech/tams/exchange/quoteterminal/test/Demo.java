package com.joyintech.tams.exchange.quoteterminal.test;

import cn.com.wind.td.tdf.*;

public class Demo {
	private final  boolean outputToScreen = false;
	/***********************configuration***************************************/
	private final String openMarket = "SH-2;SZ-2";
	private final int openTime = 0;
	private final String subscription = "000001.sz";
	private final int openTypeFlags = DATA_TYPE_FLAG.DATA_TYPE_NONE;
	//环境设置参数
	private final int HEART_BEAT_INTERVAL = 0;//Heart Beat间隔（秒数）, 若值为0则表示默认值10秒钟
	private final int MISSED_BEART_COUNT = 0;//如果没有收到心跳次数超过这个值，且没收到其他任何数据，则判断为掉线，若值0为默认次数2次
	private final int OPEN_TIME_OUT = 0;//在调TDF_Open期间，接收每一个数据包的超时时间（秒数，不是TDF_Open调用总的最大等待时间），若值为0则默认30秒
	/***********************configuration***************************************/
	TDFClient client = new TDFClient();
	Demo(String ip, int port, String username, String password) {
		this.quitFlag = false;
		this.LastPrintTime = System.currentTimeMillis();
		//构造配置参数
		TDF_OPEN_SETTING setting = new TDF_OPEN_SETTING();
		setting.setIp(ip);                                       //服务器IP
		setting.setPort( Integer.toString(port));                //服务器端口
		setting.setUser(username);                               //登录用户名
		setting.setPwd(password);                                //登录密码

		//初始订阅配置
		setting.setMarkets(openMarket);                 //市场列表，不用区分大小写，用英文字符 ; 分割；如果为空，则订阅全部市场。sh;sz;cf;shf;czc;dce;
		setting.setSubScriptions(subscription);         //订阅代码列表.格式为代码.市场简称（大写），例如000001.SZ;600000.SH. 代码之间用英文字符 ; 分割。如果为空，则订阅全部代码
		setting.setTypeFlags(openTypeFlags);		        //订阅的数据类型（默认全部订阅）参见DATA_TYPE_FLAG
		setting.setTime(openTime);	                    //-1表示从头传输，0表示最新行情（默认0）
		//不建议修改配置
		setting.setConnectionID(0);

		//环境设置，在Open之前调用
		setEnv(TDF_ENVIRON_SETTING.TDF_ENVIRON_HEART_BEAT_INTERVAL,HEART_BEAT_INTERVAL);
		setEnv(TDF_ENVIRON_SETTING.TDF_ENVIRON_MISSED_BEART_COUNT,MISSED_BEART_COUNT);
		setEnv(TDF_ENVIRON_SETTING.TDF_ENVIRON_OPEN_TIME_OUT,OPEN_TIME_OUT);
		//setEnv(TDF_ENVIRON_SETTING.TDF_ENVIRON_OUT_LOG,1);//输出日志到当前目录，否则需创建log目录后输出到log目录

		int err = client.open(setting);                //这里会做连接，登录，收取代码表操作，全部完成后返回（此函数会阻塞一段时间）
		if (err!=TDF_ERR.TDF_ERR_SUCCESS) {
			System.out.printf("Can't connect to %s:%d. 程序退出！\n", ip, port);
			System.exit(err);
		}
	}

	//双活模式
	Demo(TDF_SERVER_INFO[] serverInfo)
	{
		this.quitFlag = false;
		this.LastPrintTime = System.currentTimeMillis();
		/**
		 * 服务器设置
		 */
		TDF_OPEN_SETTING_EXT setting_ext = new TDF_OPEN_SETTING_EXT();
		setting_ext.setServerInfo(serverInfo);
		setting_ext.setMarkets(openMarket);
		setting_ext.setTime(openTime);
		setting_ext.setSubScriptions(subscription);
		setting_ext.setTypeFlags(openTypeFlags);
		setting_ext.setConnectionID(0);

		//环境设置，在Open之前调用
		setEnv(TDF_ENVIRON_SETTING.TDF_ENVIRON_HEART_BEAT_INTERVAL,HEART_BEAT_INTERVAL);
		setEnv(TDF_ENVIRON_SETTING.TDF_ENVIRON_MISSED_BEART_COUNT,MISSED_BEART_COUNT);
		setEnv(TDF_ENVIRON_SETTING.TDF_ENVIRON_OPEN_TIME_OUT,OPEN_TIME_OUT);
		//setEnv(TDF_ENVIRON_SETTING.TDF_ENVIRON_OUT_LOG,1);//输出日志到当前目录，否则需创建log目录后输出到log目录

		int err = client.open_ext(setting_ext);                //这里会做连接，登录，收取代码表操作，全部完成后返回（此函数会阻塞一段时间）
		if (err!=TDF_ERR.TDF_ERR_SUCCESS) {
			System.out.printf("Can't connect to %s:%d. 程序退出！\n", serverInfo[0].getIp(), serverInfo[0].getPort());
			System.exit(err);
		}
	}


	//代理模式
	Demo(String ip, int port, String username, String password,String proxy_ip, int proxy_port, String proxy_user, String proxy_pwd) {
		this.quitFlag = false;
		this.LastPrintTime = System.currentTimeMillis();
		TDF_OPEN_SETTING setting = new TDF_OPEN_SETTING();
		setting.setIp(ip);
		setting.setPort(Integer.toString(port));
		setting.setUser(username);
		setting.setPwd(password);

		setting.setMarkets(openMarket);
		setting.setTime(openTime);
		setting.setSubScriptions(subscription);
		setting.setTypeFlags(openTypeFlags);
		setting.setConnectionID(0);

		TDF_PROXY_SETTING proxySetting = new TDF_PROXY_SETTING();
		proxySetting.setProxyHostIp(proxy_ip);
		proxySetting.setProxyPort(Integer.toString(proxy_port));
		proxySetting.setProxyUser(proxy_user);
		proxySetting.setProxyPwd(proxy_pwd);
		proxySetting.setProxyType(TDF_PROXY_TYPE.TDF_PROXY_HTTP11);

		//环境设置，在Open之前调用
		setEnv(TDF_ENVIRON_SETTING.TDF_ENVIRON_HEART_BEAT_INTERVAL,HEART_BEAT_INTERVAL);
		setEnv(TDF_ENVIRON_SETTING.TDF_ENVIRON_MISSED_BEART_COUNT,MISSED_BEART_COUNT);
		setEnv(TDF_ENVIRON_SETTING.TDF_ENVIRON_OPEN_TIME_OUT,OPEN_TIME_OUT);
		//setEnv(TDF_ENVIRON_SETTING.TDF_ENVIRON_OUT_LOG,1);//输出日志到当前目录，否则需创建log目录后输出到log目录

		int err = client.openProxy(setting, proxySetting);
		if (err != TDF_ERR.TDF_ERR_SUCCESS) {
			System.out.printf("Can't connect to %s:%d. 程序退出！\n", ip, port);
			System.exit(err);
		}
	}

	protected Boolean quitFlag;
	private long LastPrintTime;

	public void setQuitFlag(Boolean para){
		this.quitFlag = para;
	}
	public int setEnv(int Env,int value){
		return client.setEnv(Env, value);
	}
	public TDF_OPTION_CODE getOptionCodeInfo(String szWindCode)
	{
		return client.getOptionCodeInfo(szWindCode);
	}

	void printCodeTable() {
		TDF_CODE[] codes = client.getCodeTable("SH");
		PrintHelper.printCodeTable(codes);
	}
	void setSusbScription(String szWindCode,int style)
	{
		client.setSubscription(szWindCode , style );
	}

	void run() {                                   //这里会循环取数据! 目前JavaAPI的机制是收取的数据存在队列中，等待用户读取。如果用户读取过慢，可能会积压或者丢失数据，所以这里处理要快
		while (!quitFlag) {
			TDF_MSG msg = client.getMessage(100);       //getMessage是取数据函数。参数含义是：等待多少ms还没数据可读就返回。 如果调用时已有数据可读，立刻返回
			if (msg==null)
				continue;

			switch(msg.getDataType()) {                           //消息分2类：系统消息（心跳，网络断开，网络连接结果，收到登陆响应，收到代码表，收到闭市，收到行情变更）
				//数据消息（行情，指数，期货，逐笔成交，逐笔委托，委托队列）。 使用getDataType获取消息类型
				//系统消息
				case TDF_MSG_ID.MSG_SYS_HEART_BEAT :
					System.out.println("收到心跳信息！");
					break;
				case TDF_MSG_ID.MSG_SYS_DISCONNECT_NETWORK:
					System.out.println("网络断开！");
					//quitFlag = true;
					break;
				case TDF_MSG_ID.MSG_SYS_CONNECT_RESULT:{
					TDF_MSG_DATA data = TDFClient.getMessageData(msg, 0);                  //getMessageData: TDF_MSG可能包含多条对应类型的消息，使用此函数取条N条
					System.out.println("网络连接结果：");
					PrintHelper.printConnectResult(data.getConnectResult());               //再使用对应方法取具体数据
					break;
				}
				case TDF_MSG_ID.MSG_SYS_LOGIN_RESULT:{
					TDF_MSG_DATA data = TDFClient.getMessageData(msg, 0);
					PrintHelper.printLoginResult(data.getLoginResult());
					break;
				}
				case TDF_MSG_ID.MSG_SYS_CODETABLE_RESULT:{
					System.out.println("收到代码表！");
					//TDF_MSG_DATA data = TDFClient.getMessageData(msg, 0);
					//PrintHelper.printCodeTableResult(data.getCodeTableResult());
					//printCodeTable();
					break;
				}
				case TDF_MSG_ID.MSG_SYS_MARKET_CLOSE:{
					TDF_MSG_DATA data = TDFClient.getMessageData(msg, 0);
					PrintHelper.printMarketClose(data.getMarketClose());
					break;
				}
				case TDF_MSG_ID.MSG_SYS_QUOTATIONDATE_CHANGE: {
					TDF_MSG_DATA data = TDFClient.getMessageData(msg, 0);
					PrintHelper.printDateChange(data.getDateChange());
					break;
				}
				//数据消息
				case TDF_MSG_ID.MSG_DATA_MARKET:
					if (outputToScreen){
						for (int i=0; i<msg.getAppHead().getItemCount(); i++) {
							TDF_MSG_DATA data = TDFClient.getMessageData(msg, i);
							PrintHelper.printDataMarket(data.getMarketData());
						}
					}
					if(System.currentTimeMillis() - LastPrintTime  > 10 * 1000 && msg.getAppHead().getItemCount()>0){
						PrintHelper.printDataMarket(TDFClient.getMessageData(msg, 0).getMarketData());
						System.gc();
						LastPrintTime = System.currentTimeMillis();
					}
					PrintHelper.SaveData( new DataInfo(msg,PrintHelper.GetCurrentTime()) );

					break;
				case TDF_MSG_ID.MSG_DATA_INDEX:
					if (outputToScreen){
						for (int i=0; i<msg.getAppHead().getItemCount(); i++) {
							TDF_MSG_DATA data = TDFClient.getMessageData(msg, i);
							PrintHelper.printIndexData(data.getIndexData());
						}
					}
					if(System.currentTimeMillis() - LastPrintTime  > 10 * 1000 && msg.getAppHead().getItemCount()>0){
						PrintHelper.printIndexData(TDFClient.getMessageData(msg, 0).getIndexData());
						System.gc();
						LastPrintTime = System.currentTimeMillis();
					}
					PrintHelper.SaveData( new DataInfo(msg,PrintHelper.GetCurrentTime()) );
					break;
				case TDF_MSG_ID.MSG_DATA_FUTURE:
					if (outputToScreen){
						for (int i=0; i<msg.getAppHead().getItemCount(); i++) {
							TDF_MSG_DATA data = TDFClient.getMessageData(msg, i);
							PrintHelper.printFutureData(data.getFutureData());
						}
					}
					if(System.currentTimeMillis() - LastPrintTime  > 10 * 1000 && msg.getAppHead().getItemCount()>0){
						PrintHelper.printFutureData(TDFClient.getMessageData(msg, 0).getFutureData());
						System.gc();
						LastPrintTime = System.currentTimeMillis();
					}
					PrintHelper.SaveData( new DataInfo(msg,PrintHelper.GetCurrentTime()));
					break;
				case TDF_MSG_ID.MSG_DATA_TRANSACTION:
					if (outputToScreen){
						for (int i=0; i<msg.getAppHead().getItemCount(); i++) {
							TDF_MSG_DATA data = TDFClient.getMessageData(msg, i);
							PrintHelper.printTransaction(data.getTransaction());
						}
					}
					if(System.currentTimeMillis() - LastPrintTime  > 10 * 1000 && msg.getAppHead().getItemCount()>0){
						PrintHelper.printTransaction(TDFClient.getMessageData(msg, 0).getTransaction());
						System.gc();
						LastPrintTime = System.currentTimeMillis();
					}
					PrintHelper.SaveData( new DataInfo(msg,PrintHelper.GetCurrentTime()) );
					break;
				case TDF_MSG_ID.MSG_DATA_ORDERQUEUE:
					if (outputToScreen){
						for (int i=0; i<msg.getAppHead().getItemCount(); i++) {
							TDF_MSG_DATA data = TDFClient.getMessageData(msg, i);
							PrintHelper.printOrderQueue(data.getOrderQueue());
						}
					}
					if(System.currentTimeMillis() - LastPrintTime  > 10 * 1000 && msg.getAppHead().getItemCount()>0){
						PrintHelper.printOrderQueue(TDFClient.getMessageData(msg, 0).getOrderQueue());
						System.gc();
						LastPrintTime = System.currentTimeMillis();
					}
					PrintHelper.SaveData( new DataInfo(msg,PrintHelper.GetCurrentTime()) );
					break;
				case TDF_MSG_ID.MSG_DATA_ORDER:
					if (outputToScreen){
						for (int i=0; i<msg.getAppHead().getItemCount(); i++) {
							TDF_MSG_DATA data = TDFClient.getMessageData(msg, i);
							PrintHelper.printOrder(data.getOrder());
						}
					}
					if(System.currentTimeMillis() - LastPrintTime  > 10 * 1000 && msg.getAppHead().getItemCount()>0){
						PrintHelper.printOrder(TDFClient.getMessageData(msg, 0).getOrder());
						System.gc();
						LastPrintTime = System.currentTimeMillis();
					}
					PrintHelper.SaveData( new DataInfo(msg,PrintHelper.GetCurrentTime()) );
					break;
				//BBQ现券成交数据
				case TDF_MSG_ID.MSG_DATA_BBQTRANSACTION:
					if (outputToScreen){
						for (int i=0; i<msg.getAppHead().getItemCount(); i++) {
							TDF_MSG_DATA data = TDFClient.getMessageData(msg, i);
							PrintHelper.printBbqTransaction(data.getBbqtransaction());
						}
					}
					if(System.currentTimeMillis() - LastPrintTime  > 10 * 1000 && msg.getAppHead().getItemCount()>0){
						PrintHelper.printBbqTransaction(TDFClient.getMessageData(msg, 0).getBbqtransaction());
						System.gc();
						LastPrintTime = System.currentTimeMillis();
					}
					PrintHelper.SaveData( new DataInfo(msg,PrintHelper.GetCurrentTime()));
					break;
				//BBQ现券报价数据
				case TDF_MSG_ID.MSG_DATA_BBQBID:
					if (outputToScreen){
						for (int i=0; i<msg.getAppHead().getItemCount(); i++) {
							TDF_MSG_DATA data = TDFClient.getMessageData(msg, i);
							PrintHelper.printBbqBid(data.getBbqbid());
						}
					}
					if(System.currentTimeMillis() - LastPrintTime  > 10 * 1000 && msg.getAppHead().getItemCount()>0){
						PrintHelper.printBbqBid(TDFClient.getMessageData(msg, 0).getBbqbid());
						System.gc();
						LastPrintTime = System.currentTimeMillis();
					}
					PrintHelper.SaveData( new DataInfo(msg,PrintHelper.GetCurrentTime()) );
					break;
				case TDF_MSG_ID.MSG_DATA_BROKERQUEUE:
					if (outputToScreen){
						for (int i=0; i<msg.getAppHead().getItemCount(); i++) {
							TDF_MSG_DATA data = TDFClient.getMessageData(msg, i);
							PrintHelper.printBrokerQueue(data.getBrokerQueue());
						}
					}
					if(System.currentTimeMillis() - LastPrintTime  > 10 * 1000 && msg.getAppHead().getItemCount()>0){
						PrintHelper.printBrokerQueue(TDFClient.getMessageData(msg, 0).getBrokerQueue());
						System.gc();
						LastPrintTime = System.currentTimeMillis();
					}
					PrintHelper.SaveData( new DataInfo(msg,PrintHelper.GetCurrentTime()) );
					break;
				default:
					break;
			}
		}
		client.close();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean testSub   = false;//test for subscription
		if (args.length != 4) {
			System.out.println("usage:  Demo ip port user password");
			System.exit(1);
		}

		// Proxy Mode
		/*Demo demo = new Demo("10.100.3.163", 20200, "dev_test", "dev_test",
				"10.100.6.125", 3128, "", "");*/
		//命令行输入模式
		Demo demo = new Demo(args[0], Integer.parseInt(args[1]), args[2], args[3]);  //这里会打开到Server的连接。具体看实现代码

		//一般配置模式
		//Demo demo = new Demo("10.100.3.221", 6221, "dev_test", "dev_test");

		/*
		 * 双（多）活模式
		 */
		/*
		int MaxServerNum = 2;//需要使用的连接数
		TDF_SERVER_INFO[] serverInfo = new TDF_SERVER_INFO[MaxServerNum];
		for(int i = 0; i < MaxServerNum; i++)
			serverInfo[i] = new TDF_SERVER_INFO();
		//双活以下方式配置
		serverInfo[0].setIp("10.100.3.163");
		serverInfo[0].setPort("20201");
		serverInfo[0].setUser("dev_test");
		serverInfo[0].setPwd("dev_test");

		serverInfo[1].setIp("10.100.2.199");
		serverInfo[1].setPort("6221");
		serverInfo[1].setUser("dev_test");
		serverInfo[1].setPwd("dev_test");
		//连接
		Demo demo = new Demo(serverInfo);
		//双活结束
		*/

		DataHandler dh = new DataHandler (demo);
		Thread t1 = new Thread(dh);
		t1.start();	                                                                //这个线程会持续的取数据（调用getMessage方法）
		try{
			Thread.sleep(4*1000);
		}catch(Exception e){
		}
		//订阅
		if(testSub)
		{
			demo.setSusbScription("600680.SH;000002.sz",SUBSCRIPTION_STYLE.SUBSCRIPTION_SET);
		}
		DataWrite dw = new DataWrite (demo);
		Thread t2 = new Thread ( dw );
		t2.start();
		System.out.println("press anything to quit the program.");
		try {
			System.in.read();
			demo.setQuitFlag(true);
			dw.setQuitFlag(true);
			t1.join();
			System.out.println("Thread1 Quit!");
			t2.join();
			System.out.println("Thread2 Quit!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Quit the program!");
		System.exit(0);
	}
}


class DataHandler  implements Runnable {
	protected Demo demo;
	public DataHandler ( Demo  d) {
		this.demo = d;
	}
	public void run ( ) {
		demo.run();
	}
}
class DataWrite  implements Runnable { //写数据条件：时间间隔>WRITE_GAP && 数据量>LIST_LEN
	private static final int WRITE_GAP = 5;
	private static final int LIST_LEN = 20000;
	public DataWrite ( Demo  d) {
		quitFlag = false;
		lastWriteTime = System.currentTimeMillis();
		this.demo = d;
	}
	private  Demo demo;
	protected Boolean quitFlag;
	private long lastWriteTime;
	public void setQuitFlag(Boolean para){
		this.quitFlag = para;

	}
	public void run ( ) {
		while(!quitFlag ){
			if (System.currentTimeMillis() - lastWriteTime < WRITE_GAP * 1000){
				try{
					Thread.sleep(2*1000);//有机会接收退出信号
					continue;
				}catch(Exception e){
					e.printStackTrace();
					System.exit(0);
				}
			}
			if (PrintHelper.IsListFull(LIST_LEN)){
				PrintHelper.WriteData(demo);
			}
			lastWriteTime = System.currentTimeMillis();
		}
		PrintHelper.WriteData(demo);
		PrintHelper.CloseDataFiles();
	}
}