package com.joyintech.tams.exchange.terminal.test;

import com.hundsun.mcapi.MCServers;
import com.hundsun.mcapi.exception.MCException;
import com.hundsun.mcapi.interfaces.ISubscriber;
import com.hundsun.mcapi.subscribe.MCSubscribeParameter;
import com.hundsun.t2sdk.impl.client.T2Services;
import com.hundsun.t2sdk.interfaces.T2SDKException;
import com.joyintech.tams.exchange.common.db.domain.LoginInfo;

import java.io.IOException;
import java.util.Objects;

/**
 * @Author: think
 * @Description:
 * @Date: Created in 17:10 2021/7/28
 */
public class TestSubscribe {
    private static String configPath = "tams-exchange-terminal/ufxconfig/t2sdk_hundsun-config.xml";
    static String fundAccount = "65001608";
    static String password = "111111";
    static String op_entrust_way = "3";
    static LoginInfo loginInfo = null;

    public static void main(String[] args) throws Exception {
        T2Services server = T2Services.getInstance();
        server.setT2sdkConfigString("tams-exchange-terminal/ufxconfig/t2sdk_hundsun-config.xml");
        // TODO Auto-generated method stub
        //启动T2SDK，只需要在服务器启动时调用一次就可以了
        try {
            server.init();
        } catch (T2SDKException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        server.start();
//        server = server.getClient("ar_tree");
        //初始化MC服务
        try {
            UfxTest.client = server.getClient("ar_tree");
            UfxTest.login(fundAccount, password, UfxTest.getOpStation());//登录
            Thread.sleep(2000L);
            if (Objects.nonNull(loginInfo)) {
                MCServers.MCInit();

            }
        } catch (T2SDKException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MCException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //获取MC服务中订阅接口
        ISubscriber subscriber = MCServers.GetSubscriber();
        MCSubscribeParameter subParam = new MCSubscribeParameter();
        subParam.SetTopicName("secu.deal_return");
        byte[] data = {1, 3, 5, 8, 9, 10, 2, 3, 0};
        subParam.SetAppData(data);
        subParam.SetFromNow(true);
        subParam.SetReplace(false);
        subParam.SetFilter("branch_no", "65");
        subParam.SetFilter("fund_account", "65001608");
        int iRet = subscriber.SubscribeTopic(subParam, 3000);
        //返回iRet>0表示成功
        System.out.println("RESULT deal_return:" + iRet + "---- subscriber  --" + subscriber);
        System.out.println("**********************************************");
        System.out.println("输入任意字符取消订阅");

//        ISubscriber subscriber1 = MCServers.GetSubscriber();
        MCSubscribeParameter subParam1 = new MCSubscribeParameter();
        subParam1.SetTopicName("secu.entrustwriteback");
        byte[] data1 = {1, 3, 5, 8, 9, 10, 2, 3, 0};
        subParam1.SetAppData(data1);
        subParam1.SetFromNow(true);
        subParam1.SetReplace(false);
        subParam1.SetFilter("branch_no", "65");
        subParam1.SetFilter("fund_account", "65001608");
        int iRet1 = subscriber.SubscribeTopic(subParam1, 3000);
        //返回iRet>0表示成功
        System.out.println("RESULT entrustwriteback:" + iRet1 + "----- subscriber --- " + subscriber);
        System.out.println("**********************************************");
        System.out.println("输入任意字符取消订阅");

        try {
            System.in.read();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }
        iRet = subscriber.CancelSubscribeTopic(iRet);
        System.out.println("CancelSubscribeTopic:" + subParam.GetTopicName() + " 返回码：" + iRet);
        System.out.println("输入任意字符退出");
        try {
            System.in.read();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }
        //释放资源
        MCServers.Destroy();
        server.stop();
        System.out.println("退出！");
    }
}
