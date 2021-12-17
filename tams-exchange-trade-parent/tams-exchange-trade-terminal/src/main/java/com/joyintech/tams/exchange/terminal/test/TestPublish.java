package com.joyintech.tams.exchange.terminal.test;

import com.hundsun.mcapi.MCServers;
import com.hundsun.mcapi.exception.MCException;
import com.hundsun.mcapi.exception.MCPublishException;
import com.hundsun.mcapi.interfaces.IPublisher;
import com.hundsun.t2sdk.common.share.dataset.DatasetService;
import com.hundsun.t2sdk.impl.client.T2Services;
import com.hundsun.t2sdk.interfaces.T2SDKException;
import com.hundsun.t2sdk.interfaces.share.dataset.IDataset;
import com.joyintech.tams.exchange.common.db.domain.LoginInfo;

import java.io.IOException;
import java.util.Objects;

/**
 * @Author: think
 * @Description:
 * @Date: Created in 11:22 2021/7/29
 */
public class TestPublish {
    static String fundAccount = "65001608";
    static String password = "111111";
    static String op_entrust_way = "3";
    static LoginInfo loginInfo = null;

    public static void main(String[] args) {
        T2Services server = T2Services.getInstance();
        server.setT2sdkConfigString("tams-exchange-terminal/ufxconfig/t2sdk_hundsun-config.xml");
        // TODO Auto-generated method stub
        try {
            // 启动T2SDK，只需要在服务器启动时调用一次就可以了
            server.init();
            server.start();

            UfxTest.client = server.getClient("ar_tree");
            UfxTest.login(fundAccount, password, UfxTest.getOpStation());//登录
            Thread.sleep(2000L);
            if (Objects.nonNull(loginInfo)) {
                MCServers.MCInit();

            }
            // 获取数据集
            IDataset dataset = DatasetService.getDefaultInstance().getDataset();
            // 填充数据集字段
            dataset.addColumn("branch_no");
            // dataset.addColumn("operator_no");
            dataset.addColumn("fund_account");
            dataset.appendRow();
            dataset.updateString("branch_no", "65");
            // dataset.updateString("operator_no", "1");
            dataset.updateString("fund_account", "65001608");
            // 获取MC服务中发布接口
            IPublisher publisher = MCServers.GetPublisher();
            // 发布消息
            while (true) {
                int iRet = publisher.PubMsg("secu.deal_return", dataset,
                        5000);
                System.out.println("发布返回结果：" + iRet);
                System.out.println("是否退出，[y]退出，[其他字符]继续");
                char ch = '0';
                try {
                    ch = (char) System.in.read();

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    // e.printStackTrace();
                }
                if (ch == 'y') {
                    break;
                }
            }
            // 释放资源
            MCServers.Destroy();
            server.stop();
        } catch (T2SDKException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MCException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MCPublishException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

